/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.actions.internal.refactoringTesting

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.idea.core.util.toPsiFile
import org.jetbrains.kotlin.psi.KtClass
import kotlin.random.Random

internal fun <T> List<T>.randomElement(): T = this[Random.nextInt(0, this.size)]

internal fun <T> List<T>.randomElements(count: Int): List<T> =
    mutableListOf<T>().also { list -> repeat(count) { list.add(randomElement()) } }

internal fun <T> List<T>.randomElements(): List<T> = randomElements(Random.nextInt(0, size))

internal fun getRandomFileClassElements(project: Project, ktFiles: List<VirtualFile>): List<KtClass> {
    val randomSourceFile = ktFiles.randomElement()
    return PsiTreeUtil.collectElementsOfType(randomSourceFile.toPsiFile(project), KtClass::class.java).toList()
}

internal data class RandomMoveRefactoringResult(val applied: Boolean, val caseDataIfApplied: String? = null)

internal interface RandomMoveRefactoringCase {
    fun tryCreateAndRun(project: Project): RandomMoveRefactoringResult
}