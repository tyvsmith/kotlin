/*
 * Copyright 2010-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.fir

import com.intellij.openapi.project.Project
import org.jetbrains.kotlin.cfg.pseudocode.containingDeclarationForPseudocode
import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.builder.RawFirBuilder
import org.jetbrains.kotlin.fir.declarations.FirCallableMemberDeclaration
import org.jetbrains.kotlin.fir.declarations.FirFile
import org.jetbrains.kotlin.fir.declarations.FirResolveStage
import org.jetbrains.kotlin.fir.declarations.FirTypedDeclaration
import org.jetbrains.kotlin.fir.dependenciesWithoutSelf
import org.jetbrains.kotlin.fir.expressions.FirExpression
import org.jetbrains.kotlin.fir.java.FirJavaModuleBasedSession
import org.jetbrains.kotlin.fir.java.FirLibrarySession
import org.jetbrains.kotlin.fir.java.FirProjectSessionProvider
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
import org.jetbrains.kotlin.idea.caches.project.IdeaModuleInfo
import org.jetbrains.kotlin.idea.caches.project.ModuleSourceInfo
import org.jetbrains.kotlin.idea.caches.project.getModuleInfo
import org.jetbrains.kotlin.idea.caches.project.isLibraryClasses
import org.jetbrains.kotlin.idea.caches.resolve.IDEPackagePartProvider
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.containingClassOrObject
import org.jetbrains.kotlin.utils.addToStdlib.cast

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

private fun createLibrarySession(moduleInfo: IdeaModuleInfo, project: Project, provider: FirProjectSessionProvider): FirLibrarySession {
    val contentScope = moduleInfo.contentScope()
    return FirLibrarySession.create(moduleInfo, provider, contentScope, project, IDEPackagePartProvider(contentScope))
}

val KtElement.session: FirSession
    get() {
        val moduleInfo = this.getModuleInfo() as ModuleSourceInfo
        val sessionProvider = FirProjectSessionProvider(project)
        return sessionProvider.getSession(moduleInfo) ?: FirJavaModuleBasedSession(
            moduleInfo, sessionProvider, moduleInfo.contentScope()
        ).also {
            val ideaModuleInfo = moduleInfo.cast<IdeaModuleInfo>()
            ideaModuleInfo.dependenciesWithoutSelf().forEach {
                if (it is IdeaModuleInfo && it.isLibraryClasses()) {
                    createLibrarySession(it, project, sessionProvider)
                }
            }
        }
    }

private fun FirProviderImpl.getFile(file: KtFile, stage: FirResolveStage): FirFile {
    val packageFqName = file.packageFqName
    // TODO: minor file modifications should not force full rebuild (!)
    var cachedOrNewFirFile = getFirFilesByPackage(packageFqName).find { it.psi == file }
    if (cachedOrNewFirFile == null || cachedOrNewFirFile.resolveStage < FirResolveStage.DECLARATIONS) {
        // TODO: when we are at some resolve stage, we can omit stages which are already done
        println("FIR resolution: start transformation of ${file.name}")
        val builder = RawFirBuilder(session, stubMode = stage.stubMode)
        val firFile = builder.buildFirFile(file)
        cachedOrNewFirFile = firFile
        recordFile(firFile)

        for (transformer in stage.transformers) {
            transformer.transformFile(firFile, null)
        }
    }
    return cachedOrNewFirFile
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

fun KtCallableDeclaration.getOrBuildFir(stage: FirResolveStage = FirResolveStage.DECLARATIONS): FirCallableMemberDeclaration<*> {
    val session = this.session

    val file = this.containingKtFile
    val packageFqName = file.packageFqName
    val klassFqName = this.containingClassOrObject?.relativeFqName()
    val declName = this.nameAsSafeName

    val firProvider = FirProvider.getInstance(session) as FirProviderImpl
    val firFile = firProvider.getFile(file, stage)

    return firFile.findCallableMember(firProvider, packageFqName, klassFqName, declName)
}

fun KtExpression.getOrBuildFir(stage: FirResolveStage = FirResolveStage.EXPRESSIONS): FirExpression {
    val session = this.session

    val file = this.containingKtFile
    val packageFqName = file.packageFqName
    val container = this.containingDeclarationForPseudocode
    val klassFqName = container?.containingClassOrObject?.relativeFqName()
    val declName = (container as? KtNamedDeclaration)?.nameAsSafeName ?: error("Unsupported: ${container?.javaClass}")

    val firProvider = FirProvider.getInstance(session) as FirProviderImpl
    val firFile = firProvider.getFile(file, stage)
    val firCallableMember = firFile.findCallableMember(firProvider, packageFqName, klassFqName, declName)

    var result: FirExpression? = null
    firCallableMember.accept(object : FirVisitorVoid() {
        override fun visitElement(element: FirElement) {
            if (result == null) {
                element.acceptChildren(this)
            }
        }

        override fun visitExpression(expression: FirExpression) {
            if (expression.psi == this@getOrBuildFir) {
                result = expression
            } else {
                super.visitExpression(expression)
            }
        }

    })
    return result ?: error("Not found: ${this.javaClass}")
}

val FirTypedDeclaration.coneTypeSafe: ConeKotlinType? get() = (this.returnTypeRef as? FirResolvedTypeRef)?.type