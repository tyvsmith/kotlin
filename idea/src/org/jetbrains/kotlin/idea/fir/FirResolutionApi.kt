/*
 * Copyright 2010-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.fir

import org.jetbrains.kotlin.cfg.pseudocode.containingDeclarationForPseudocode
import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.builder.RawFirBuilder
import org.jetbrains.kotlin.fir.declarations.*
import org.jetbrains.kotlin.fir.expressions.FirExpression
import org.jetbrains.kotlin.fir.resolve.FirProvider
import org.jetbrains.kotlin.fir.resolve.impl.FirProviderImpl
import org.jetbrains.kotlin.fir.resolve.transformers.*
import org.jetbrains.kotlin.fir.scopes.ProcessorAction
import org.jetbrains.kotlin.fir.scopes.impl.FirTopLevelDeclaredMemberScope
import org.jetbrains.kotlin.fir.symbols.ConeCallableSymbol
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol
import org.jetbrains.kotlin.fir.types.ConeKotlinType
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef
import org.jetbrains.kotlin.fir.visitors.FirTransformer
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.containingClassOrObject

private val resolveStageToTransformerMap = mutableMapOf(
    FirResolveStage.RAW_FIR to emptyList(),
    FirResolveStage.SUPER_TYPES to listOf(
        FirImportResolveTransformer(),
        FirSupertypeResolverTransformer()
    ),
    FirResolveStage.DECLARATIONS to listOf(
        FirImportResolveTransformer(),
        FirSupertypeResolverTransformer(),
        FirTypeResolveTransformer(),
        FirStatusResolveTransformer()
    ),
    FirResolveStage.EXPRESSIONS to listOf(
        FirImportResolveTransformer(),
        FirSupertypeResolverTransformer(),
        FirTypeResolveTransformer(),
        FirStatusResolveTransformer(),
        FirImplicitTypeBodyResolveTransformerAdapter(),
        FirBodyResolveTransformerAdapter()
    )
)

private val FirResolveStage.stubMode: Boolean
    get() = this != FirResolveStage.EXPRESSIONS

private val FirResolveStage.transformers: List<FirTransformer<Nothing?>>
    get() = resolveStageToTransformerMap[this] ?: emptyList()

private fun KtClassOrObject.relativeFqName(): FqName {
    val className = this.nameAsSafeName
    val parentFqName = this.containingClassOrObject?.relativeFqName()
    return parentFqName?.child(className) ?: FqName.topLevel(className)
}

private fun FirFile.findCallableMember(
    provider: FirProvider, packageFqName: FqName, klassFqName: FqName?, declName: Name
): FirCallableMemberDeclaration<*> {
    val memberScope =
        if (klassFqName == null) FirTopLevelDeclaredMemberScope(this, session)
        else provider.getClassDeclaredMemberScope(ClassId(packageFqName, klassFqName, false))!!
    var result: FirCallableMemberDeclaration<*>? = null
    val processor = { symbol: ConeCallableSymbol ->
        val firSymbol = symbol as? FirBasedSymbol<*>
        val fir = firSymbol?.fir as? FirCallableMemberDeclaration<*>
        if (fir?.psi == this) {
            result = fir
            ProcessorAction.STOP
        } else {
            ProcessorAction.NEXT
        }
    }
    if (this is KtNamedFunction) {
        memberScope.processFunctionsByName(declName, processor)
    } else {
        memberScope.processPropertiesByName(declName, processor)
    }

    return result!!
}

fun KtCallableDeclaration.getOrBuildFir(
    state: FirResolveState,
    stage: FirResolveStage = FirResolveStage.DECLARATIONS
): FirCallableMemberDeclaration<*> {
    val session = state.getSession(this)

    val file = this.containingKtFile
    val packageFqName = file.packageFqName
    val klassFqName = this.containingClassOrObject?.relativeFqName()
    val declName = this.nameAsSafeName

    val firProvider = FirProvider.getInstance(session) as IdeFirProvider
    val firFile = firProvider.getOrBuildFile(file)
    val memberSymbol = firFile.findCallableMember(firProvider, packageFqName, klassFqName, declName).symbol
    memberSymbol.fir.runResolve(stage, state, firFile)
    return memberSymbol.fir
}

private fun FirDeclaration.runResolve(toStage: FirResolveStage, state: FirResolveState, file: FirFile) {
    if (this.resolveStage < toStage) {
        // TODO
    }
}

fun KtExpression.getOrBuildFir(
    state: FirResolveState,
    stage: FirResolveStage = FirResolveStage.EXPRESSIONS
): FirExpression {
    val container = this.containingDeclarationForPseudocode ?: error("WTF?")
    val containerFir = container.getOrBuildFir(state, stage)
    return state[this] as? FirExpression ?: run {
        containerFir.accept(object : FirVisitorVoid() {
            override fun visitElement(element: FirElement) {
                (element.psi as? KtElement)?.let { state.record(it, element) }
                element.acceptChildren(this)
            }
        })
        return state[this] as FirExpression
    }
}

val FirTypedDeclaration.coneTypeSafe: ConeKotlinType? get() = (this.returnTypeRef as? FirResolvedTypeRef)?.type