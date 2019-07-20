/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.expressions

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.fir.visitors.FirTransformer
import org.jetbrains.kotlin.fir.visitors.FirVisitor

abstract class FirWrappedDelegateExpression(psi: PsiElement?) : FirWrappedExpression(psi) {
    abstract val delegateProvider: FirExpression

    abstract val useDelegateProvider: Boolean

    override val typeRef: FirTypeRef
        get() = if (useDelegateProvider) delegateProvider.typeRef else expression.typeRef

    override fun <R, D> accept(visitor: FirVisitor<R, D>, data: D): R {
        return visitor.visitWrappedDelegateExpression(this, data)
    }

    override fun <R, D> acceptChildren(visitor: FirVisitor<R, D>, data: D) {
        delegateProvider.accept(visitor, data)
        super.acceptChildren(visitor, data)
    }

    abstract fun <D> transformChildrenOnce(transformer: FirTransformer<D>, data: D): FirElement
}