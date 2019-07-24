/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.actions.internal.refactoringTesting;

import com.intellij.openapi.project.Project
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.ProjectScope
import org.jetbrains.kotlin.idea.KotlinFileType
import org.jetbrains.kotlin.idea.refactoring.move.moveDeclarations.*
import org.jetbrains.kotlin.idea.stubindex.KotlinSourceFilterScope
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtEnumEntry

internal class NestedClassMoveRefactoringCase : RandomMoveRefactoringCase {

    private data class DescriptorAndCaseText(val descriptor: MoveDeclarationsDescriptor, val caseText: String)

    override fun tryCreateAndRun(project: Project): RandomMoveRefactoringResult {
        repeat(100) {
            val descriptorAndText = randomMoveAndReturnErrorText(project)
            if (descriptorAndText !== null) {
                MoveKotlinDeclarationsProcessor(descriptorAndText.descriptor, Mover.Default).run()
                return RandomMoveRefactoringResult(true, descriptorAndText.caseText)
            }

        }
        return RandomMoveRefactoringResult(false)
    }

    private fun KtDeclaration.isMoveCompatible() = this is KtClass && this !is KtEnumEntry

    private fun getErrorMessage(sources: List<KtDeclaration>, target: KtDeclaration): String {
        return StringBuilder().also { sb ->
            sb.appendln("Sources:")
            for (source in sources) {
                sb.appendln(source.text)
            }
            sb.appendln("Target")
            sb.appendln(target.text)
        }.toString()
    }

    private fun randomMoveAndReturnErrorText(project: Project): DescriptorAndCaseText? {
        val scope = KotlinSourceFilterScope.projectSources(ProjectScope.getContentScope(project), project)
        val ktFiles = FileTypeIndex.getFiles(KotlinFileType.INSTANCE, scope).toList()

        if (ktFiles.isEmpty()) return null

        val sourceClassElements = getRandomFileClassElements(project, ktFiles)
        if (sourceClassElements.isEmpty()) return null
        val sources = sourceClassElements.randomElements().filter { it.isMoveCompatible() }.distinct()

        if (sources.isEmpty()) return null
        val targetClassElements = getRandomFileClassElements(project, ktFiles)

        if (targetClassElements.isEmpty()) return null
        val target = targetClassElements.randomElement()

        val handler = MoveKotlinDeclarationsHandler()

        val sourcesArray = sources.toTypedArray()

        val caseText = getErrorMessage(sources, target)

        if (handler.isValidTarget(target, sourcesArray) && handler.canMove(sourcesArray, target)) {

            val targetMoveElement = KotlinMoveTargetForExistingElement(target as KtClassOrObject)
            val delegate = MoveDeclarationsDelegate.NestedClass()
            val descriptor = MoveDeclarationsDescriptor(
                project,
                MoveSource(sources),
                targetMoveElement,
                delegate,
                false,
                false,
                false,
                null,
                false
            )

            return DescriptorAndCaseText(descriptor, caseText)
        }
        return null
    }
}
