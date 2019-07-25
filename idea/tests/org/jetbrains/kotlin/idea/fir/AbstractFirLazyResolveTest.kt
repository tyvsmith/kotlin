/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.fir

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.intellij.openapi.util.io.FileUtil
import com.intellij.psi.PsiDocumentManager
import com.intellij.testFramework.LightProjectDescriptor
import junit.framework.TestCase
import org.jetbrains.kotlin.fir.render
import org.jetbrains.kotlin.idea.jsonUtils.getString
import org.jetbrains.kotlin.idea.test.KotlinLightCodeInsightFixtureTestCase
import org.jetbrains.kotlin.idea.test.KotlinLightProjectDescriptor
import org.jetbrains.kotlin.idea.test.KotlinWithJdkAndRuntimeLightProjectDescriptor
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.test.KotlinTestUtils
import java.io.File

abstract class AbstractFirLazyResolveTest : KotlinLightCodeInsightFixtureTestCase() {
    override fun getProjectDescriptor(): LightProjectDescriptor {
        if (KotlinTestUtils.isAllFilesPresentTest(getTestName(false))) return super.getProjectDescriptor()
        val testFile = File(testDataPath, fileName())
        val config = JsonParser().parse(FileUtil.loadFile(testFile, true)) as JsonObject
        val withRuntime = config["withRuntime"]?.asBoolean ?: false
        return if (withRuntime)
            KotlinWithJdkAndRuntimeLightProjectDescriptor.INSTANCE
        else
            KotlinLightProjectDescriptor.INSTANCE
    }

    fun doTest(path: String) {
        val testFile = File(path)
        val config = JsonParser().parse(FileUtil.loadFile(testFile, true)) as JsonObject

        val mainFilePath = config.getString("mainFile")
        val mainFile = File(testFile.parent, mainFilePath)
        val mainFileText = FileUtil.loadFile(mainFile, true)
        TestCase.assertTrue("\"<caret>\" is missing in file \"$mainFilePath\"", mainFileText.contains("<caret>"))

        val projectDir = path.removePrefix(testDataPath).substringBeforeLast('/')
        myFixture.copyDirectoryToProject(projectDir, "")
        PsiDocumentManager.getInstance(myFixture.project).commitAllDocuments()

        myFixture.configureFromTempProjectFile(mainFilePath)
        val referenceToResolve = myFixture.getReferenceAtCaretPositionWithAssertion(mainFilePath)
        val elementToResolve = referenceToResolve.element
        val expressionToResolve = when {
            elementToResolve.parent is KtCallExpression -> elementToResolve.parent
            else -> elementToResolve
        } as KtExpression

        val resolveState = expressionToResolve.firResolveState()
        val firExpression = expressionToResolve.getOrBuildFir(resolveState)

        val resultsDump = firExpression.render()
        KotlinTestUtils.assertEqualsToFile(File(testFile.parent, "results.txt"), resultsDump)
    }
}