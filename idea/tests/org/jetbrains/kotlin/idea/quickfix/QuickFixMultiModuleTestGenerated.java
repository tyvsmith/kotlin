/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.quickfix;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TargetBackend;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("idea/testData/multiModuleQuickFix")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class QuickFixMultiModuleTestGenerated extends AbstractQuickFixMultiModuleTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
    }

    @TestMetadata("abstract")
    public void testAbstract() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/abstract/");
    }

    @TestMetadata("abstractClassWithJdk")
    public void testAbstractClassWithJdk() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/abstractClassWithJdk/");
    }

    @TestMetadata("actualImplementAsConstructorParam")
    public void testActualImplementAsConstructorParam() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/actualImplementAsConstructorParam/");
    }

    @TestMetadata("actualNoImplementAsConstructorParam")
    public void testActualNoImplementAsConstructorParam() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/actualNoImplementAsConstructorParam/");
    }

    @TestMetadata("actualWithoutExpect")
    public void testActualWithoutExpect() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/actualWithoutExpect/");
    }

    @TestMetadata("addActualToClass")
    public void testAddActualToClass() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/addActualToClass/");
    }

    @TestMetadata("addActualToClassMember")
    public void testAddActualToClassMember() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/addActualToClassMember/");
    }

    @TestMetadata("addActualToTopLevelMember")
    public void testAddActualToTopLevelMember() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/addActualToTopLevelMember/");
    }

    @TestMetadata("addFunctionToCommonClassFromJavaUsage")
    public void testAddFunctionToCommonClassFromJavaUsage() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/addFunctionToCommonClassFromJavaUsage/");
    }

    @TestMetadata("addOperatorByActual")
    public void testAddOperatorByActual() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/addOperatorByActual/");
    }

    @TestMetadata("addOperatorByExpect")
    public void testAddOperatorByExpect() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/addOperatorByExpect/");
    }

    public void testAllFilesPresentInMultiModuleQuickFix() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("idea/testData/multiModuleQuickFix"), Pattern.compile("^([^\\.]+)$"), TargetBackend.ANY, false);
    }

    @TestMetadata("annotation")
    public void testAnnotation() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/annotation/");
    }

    @TestMetadata("annotationOptionalExpectation")
    public void testAnnotationOptionalExpectation() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/annotationOptionalExpectation/");
    }

    @TestMetadata("annotationOptionalExpectationNoDir")
    public void testAnnotationOptionalExpectationNoDir() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/annotationOptionalExpectationNoDir/");
    }

    @TestMetadata("class")
    public void testClass() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/class/");
    }

    @TestMetadata("classFunction")
    public void testClassFunction() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/classFunction/");
    }

    @TestMetadata("classFunctionSameSignature")
    public void testClassFunctionSameSignature() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/classFunctionSameSignature/");
    }

    @TestMetadata("classFunctionWithConstructor")
    public void testClassFunctionWithConstructor() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/classFunctionWithConstructor/");
    }

    @TestMetadata("classFunctionWithConstructorAndParameters")
    public void testClassFunctionWithConstructorAndParameters() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/classFunctionWithConstructorAndParameters/");
    }

    @TestMetadata("classFunctionWithIncompatibleConstructor")
    public void testClassFunctionWithIncompatibleConstructor() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/classFunctionWithIncompatibleConstructor/");
    }

    @TestMetadata("classOverloadedFunction")
    public void testClassOverloadedFunction() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/classOverloadedFunction/");
    }

    @TestMetadata("classPropertyInConstructor")
    public void testClassPropertyInConstructor() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/classPropertyInConstructor/");
    }

    @TestMetadata("classSomeProperties")
    public void testClassSomeProperties() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/classSomeProperties/");
    }

    @TestMetadata("classWithBase")
    public void testClassWithBase() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/classWithBase/");
    }

    @TestMetadata("classWithIncompilableFunction")
    public void testClassWithIncompilableFunction() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/classWithIncompilableFunction/");
    }

    @TestMetadata("classWithJdk")
    public void testClassWithJdk() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/classWithJdk/");
    }

    @TestMetadata("companionAbsence")
    public void testCompanionAbsence() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/companionAbsence/");
    }

    @TestMetadata("constructorWithDelegation")
    public void testConstructorWithDelegation() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/constructorWithDelegation/");
    }

    @TestMetadata("constructorWithJdk")
    public void testConstructorWithJdk() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/constructorWithJdk/");
    }

    @TestMetadata("convertActualEnumToSealedClass")
    public void testConvertActualEnumToSealedClass() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/convertActualEnumToSealedClass/");
    }

    @TestMetadata("convertActualSealedClassToEnum")
    public void testConvertActualSealedClassToEnum() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/convertActualSealedClassToEnum/");
    }

    @TestMetadata("convertExpectEnumToSealedClass")
    public void testConvertExpectEnumToSealedClass() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/convertExpectEnumToSealedClass/");
    }

    @TestMetadata("convertExpectSealedClassToEnum")
    public void testConvertExpectSealedClassToEnum() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/convertExpectSealedClassToEnum/");
    }

    @TestMetadata("convertPropertyGetterToInitializer")
    public void testConvertPropertyGetterToInitializer() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/convertPropertyGetterToInitializer/");
    }

    @TestMetadata("convertPropertyToFunction")
    public void testConvertPropertyToFunction() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/convertPropertyToFunction/");
    }

    @TestMetadata("createActualWithRootPackage")
    public void testCreateActualWithRootPackage() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/createActualWithRootPackage/");
    }

    @TestMetadata("createClassFromUsageImport")
    public void testCreateClassFromUsageImport() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/createClassFromUsageImport/");
    }

    @TestMetadata("createClassFromUsageRef")
    public void testCreateClassFromUsageRef() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/createClassFromUsageRef/");
    }

    @TestMetadata("createExpectWithRootPackage")
    public void testCreateExpectWithRootPackage() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/createExpectWithRootPackage/");
    }

    @TestMetadata("createFunInExpectClass")
    public void testCreateFunInExpectClass() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/createFunInExpectClass/");
    }

    @TestMetadata("createTestOnExpect")
    public void testCreateTestOnExpect() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/createTestOnExpect/");
    }

    @TestMetadata("createValInExpectClass")
    public void testCreateValInExpectClass() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/createValInExpectClass/");
    }

    @TestMetadata("createVarInExpectClass")
    public void testCreateVarInExpectClass() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/createVarInExpectClass/");
    }

    @TestMetadata("defaultParameterInExpected")
    public void testDefaultParameterInExpected() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/defaultParameterInExpected/");
    }

    @TestMetadata("defaultParameterInExpectedClass")
    public void testDefaultParameterInExpectedClass() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/defaultParameterInExpectedClass/");
    }

    @TestMetadata("defaultParameterInExpectedConstructor")
    public void testDefaultParameterInExpectedConstructor() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/defaultParameterInExpectedConstructor/");
    }

    @TestMetadata("deprecatedHeader")
    public void testDeprecatedHeader() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/deprecatedHeader/");
    }

    @TestMetadata("deprecatedHeaderImpl")
    public void testDeprecatedHeaderImpl() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/deprecatedHeaderImpl/");
    }

    @TestMetadata("deprecatedImpl")
    public void testDeprecatedImpl() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/deprecatedImpl/");
    }

    @TestMetadata("deprecatedImplHeader")
    public void testDeprecatedImplHeader() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/deprecatedImplHeader/");
    }

    @TestMetadata("enum")
    public void testEnum() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/enum/");
    }

    @TestMetadata("expectAnnotation")
    public void testExpectAnnotation() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectAnnotation/");
    }

    @TestMetadata("expectAnnotation2")
    public void testExpectAnnotation2() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectAnnotation2/");
    }

    @TestMetadata("expectClass")
    public void testExpectClass() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectClass/");
    }

    @TestMetadata("expectClassCommented")
    public void testExpectClassCommented() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectClassCommented/");
    }

    @TestMetadata("expectClassFunction")
    public void testExpectClassFunction() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectClassFunction/");
    }

    @TestMetadata("expectClassNoAccessOnMember")
    public void testExpectClassNoAccessOnMember() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectClassNoAccessOnMember/");
    }

    @TestMetadata("expectClassOnMember")
    public void testExpectClassOnMember() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectClassOnMember/");
    }

    @TestMetadata("expectClassProperty")
    public void testExpectClassProperty() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectClassProperty/");
    }

    @TestMetadata("expectClassPropertyInConstructor")
    public void testExpectClassPropertyInConstructor() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectClassPropertyInConstructor/");
    }

    @TestMetadata("expectClassWithAliases")
    public void testExpectClassWithAliases() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectClassWithAliases/");
    }

    @TestMetadata("expectClassWithConstructorWithParametersWithoutValVar")
    public void testExpectClassWithConstructorWithParametersWithoutValVar() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectClassWithConstructorWithParametersWithoutValVar/");
    }

    @TestMetadata("expectClassWithInitializer")
    public void testExpectClassWithInitializer() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectClassWithInitializer/");
    }

    @TestMetadata("expectClassWithPlatformNested")
    public void testExpectClassWithPlatformNested() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectClassWithPlatformNested/");
    }

    @TestMetadata("expectClassWithSecondaryConstructor")
    public void testExpectClassWithSecondaryConstructor() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectClassWithSecondaryConstructor/");
    }

    @TestMetadata("expectClassWithSecondaryConstructor2")
    public void testExpectClassWithSecondaryConstructor2() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectClassWithSecondaryConstructor2/");
    }

    @TestMetadata("expectClassWithSupertype")
    public void testExpectClassWithSupertype() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectClassWithSupertype/");
    }

    @TestMetadata("expectCompanion")
    public void testExpectCompanion() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectCompanion/");
    }

    @TestMetadata("expectDataClass")
    public void testExpectDataClass() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectDataClass/");
    }

    @TestMetadata("expectEnum")
    public void testExpectEnum() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectEnum/");
    }

    @TestMetadata("expectEnumComplex")
    public void testExpectEnumComplex() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectEnumComplex/");
    }

    @TestMetadata("expectEnumEmpty")
    public void testExpectEnumEmpty() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectEnumEmpty/");
    }

    @TestMetadata("expectFunWithAccessibleAlias")
    public void testExpectFunWithAccessibleAlias() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectFunWithAccessibleAlias/");
    }

    @TestMetadata("expectFunWithAccessibleExpansion")
    public void testExpectFunWithAccessibleExpansion() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectFunWithAccessibleExpansion/");
    }

    @TestMetadata("expectFunWithAccessibleParameter")
    public void testExpectFunWithAccessibleParameter() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectFunWithAccessibleParameter/");
    }

    @TestMetadata("expectFunWithAccessibleTypeFromCommon")
    public void testExpectFunWithAccessibleTypeFromCommon() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectFunWithAccessibleTypeFromCommon/");
    }

    @TestMetadata("expectFunWithInaccessibleBounds")
    public void testExpectFunWithInaccessibleBounds() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectFunWithInaccessibleBounds/");
    }

    @TestMetadata("expectFunWithInaccessibleParameter")
    public void testExpectFunWithInaccessibleParameter() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectFunWithInaccessibleParameter/");
    }

    @TestMetadata("expectFunWithInaccessibleTypeParameter")
    public void testExpectFunWithInaccessibleTypeParameter() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectFunWithInaccessibleTypeParameter/");
    }

    @TestMetadata("expectFunWithJdk")
    public void testExpectFunWithJdk() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectFunWithJdk/");
    }

    @TestMetadata("expectFunction")
    public void testExpectFunction() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectFunction/");
    }

    @TestMetadata("expectInlineClass")
    public void testExpectInlineClass() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectInlineClass/");
    }

    @TestMetadata("expectInlineClass2")
    public void testExpectInlineClass2() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectInlineClass2/");
    }

    @TestMetadata("expectInnerEnum")
    public void testExpectInnerEnum() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectInnerEnum/");
    }

    @TestMetadata("expectNestedClass")
    public void testExpectNestedClass() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectNestedClass/");
    }

    @TestMetadata("expectPrimaryConstructor")
    public void testExpectPrimaryConstructor() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectPrimaryConstructor/");
    }

    @TestMetadata("expectProperty")
    public void testExpectProperty() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectProperty/");
    }

    @TestMetadata("expectSealedClass")
    public void testExpectSealedClass() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectSealedClass/");
    }

    @TestMetadata("expectTypeAlias")
    public void testExpectTypeAlias() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectTypeAlias/");
    }

    @TestMetadata("expectWithAnnotations")
    public void testExpectWithAnnotations() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/expectWithAnnotations/");
    }

    @TestMetadata("function")
    public void testFunction() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/function/");
    }

    @TestMetadata("functionSameFile")
    public void testFunctionSameFile() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/functionSameFile/");
    }

    @TestMetadata("functionTypeParameterToReceiverByHeader")
    public void testFunctionTypeParameterToReceiverByHeader() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/functionTypeParameterToReceiverByHeader/");
    }

    @TestMetadata("functionTypeParameterToReceiverByImpl")
    public void testFunctionTypeParameterToReceiverByImpl() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/functionTypeParameterToReceiverByImpl/");
    }

    @TestMetadata("functionTypeReceiverToParameterByHeader")
    public void testFunctionTypeReceiverToParameterByHeader() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/functionTypeReceiverToParameterByHeader/");
    }

    @TestMetadata("functionTypeReceiverToParameterByImpl")
    public void testFunctionTypeReceiverToParameterByImpl() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/functionTypeReceiverToParameterByImpl/");
    }

    @TestMetadata("generateEqualsInExpect")
    public void testGenerateEqualsInExpect() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/generateEqualsInExpect/");
    }

    @TestMetadata("generateHashCodeInExpect")
    public void testGenerateHashCodeInExpect() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/generateHashCodeInExpect/");
    }

    @TestMetadata("implementAbstractExpectMemberInheritedFromInterface")
    public void testImplementAbstractExpectMemberInheritedFromInterface() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/implementAbstractExpectMemberInheritedFromInterface/");
    }

    @TestMetadata("implementMembersInActualClassNoExpectMember")
    public void testImplementMembersInActualClassNoExpectMember() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/implementMembersInActualClassNoExpectMember/");
    }

    @TestMetadata("implementMembersInImplClassNonImplInheritor")
    public void testImplementMembersInImplClassNonImplInheritor() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/implementMembersInImplClassNonImplInheritor/");
    }

    @TestMetadata("importClassInCommon")
    public void testImportClassInCommon() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/importClassInCommon/");
    }

    @TestMetadata("importClassInFromProductionInCommonTests")
    public void testImportClassInFromProductionInCommonTests() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/importClassInFromProductionInCommonTests/");
    }

    @TestMetadata("importCommonClassInJs")
    public void testImportCommonClassInJs() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/importCommonClassInJs/");
    }

    @TestMetadata("importCommonClassInJvm")
    public void testImportCommonClassInJvm() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/importCommonClassInJvm/");
    }

    @TestMetadata("importCommonFunInJvm")
    public void testImportCommonFunInJvm() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/importCommonFunInJvm/");
    }

    @TestMetadata("importExpectClassWithActualInJvm")
    public void testImportExpectClassWithActualInJvm() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/importExpectClassWithActualInJvm/");
    }

    @TestMetadata("importExpectClassWithoutActualInJvm")
    public void testImportExpectClassWithoutActualInJvm() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/importExpectClassWithoutActualInJvm/");
    }

    @TestMetadata("importFunInCommon")
    public void testImportFunInCommon() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/importFunInCommon/");
    }

    @TestMetadata("inlineClass")
    public void testInlineClass() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/inlineClass/");
    }

    @TestMetadata("interface")
    public void testInterface() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/interface/");
    }

    @TestMetadata("makeInlineFromExpect")
    public void testMakeInlineFromExpect() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/makeInlineFromExpect/");
    }

    @TestMetadata("makeInternalFromExpect")
    public void testMakeInternalFromExpect() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/makeInternalFromExpect/");
    }

    @TestMetadata("makeOpenFromActual")
    public void testMakeOpenFromActual() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/makeOpenFromActual/");
    }

    @TestMetadata("makeOpenFromExpect")
    public void testMakeOpenFromExpect() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/makeOpenFromExpect/");
    }

    @TestMetadata("mayBeConstantWithActual")
    public void testMayBeConstantWithActual() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/mayBeConstantWithActual/");
    }

    @TestMetadata("memberFunParameterToReceiverByHeader")
    public void testMemberFunParameterToReceiverByHeader() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/memberFunParameterToReceiverByHeader/");
    }

    @TestMetadata("memberFunParameterToReceiverByImpl")
    public void testMemberFunParameterToReceiverByImpl() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/memberFunParameterToReceiverByImpl/");
    }

    @TestMetadata("memberFunReceiverToParameterByHeader")
    public void testMemberFunReceiverToParameterByHeader() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/memberFunReceiverToParameterByHeader/");
    }

    @TestMetadata("memberFunReceiverToParameterByImpl")
    public void testMemberFunReceiverToParameterByImpl() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/memberFunReceiverToParameterByImpl/");
    }

    @TestMetadata("memberFunToExtensionByHeader")
    public void testMemberFunToExtensionByHeader() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/memberFunToExtensionByHeader/");
    }

    @TestMetadata("memberFunToExtensionByImpl")
    public void testMemberFunToExtensionByImpl() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/memberFunToExtensionByImpl/");
    }

    @TestMetadata("memberValToExtensionByHeader")
    public void testMemberValToExtensionByHeader() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/memberValToExtensionByHeader/");
    }

    @TestMetadata("memberValToExtensionByHeaderWithInapplicableImpl")
    public void testMemberValToExtensionByHeaderWithInapplicableImpl() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/memberValToExtensionByHeaderWithInapplicableImpl/");
    }

    @TestMetadata("memberValToExtensionByImpl")
    public void testMemberValToExtensionByImpl() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/memberValToExtensionByImpl/");
    }

    @TestMetadata("nested")
    public void testNested() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/nested/");
    }

    @TestMetadata("object")
    public void testObject() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/object/");
    }

    @TestMetadata("orderHeader")
    public void testOrderHeader() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/orderHeader/");
    }

    @TestMetadata("orderImpl")
    public void testOrderImpl() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/orderImpl/");
    }

    @TestMetadata("package")
    public void testPackage() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/package/");
    }

    @TestMetadata("packageIncorrect")
    public void testPackageIncorrect() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/packageIncorrect/");
    }

    @TestMetadata("packageIncorrectEmpty")
    public void testPackageIncorrectEmpty() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/packageIncorrectEmpty/");
    }

    @TestMetadata("primaryConstructor")
    public void testPrimaryConstructor() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/primaryConstructor/");
    }

    @TestMetadata("primaryConstructorAbsence")
    public void testPrimaryConstructorAbsence() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/primaryConstructorAbsence/");
    }

    @TestMetadata("property")
    public void testProperty() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/property/");
    }

    @TestMetadata("safeDeleteForbiddenFromActual")
    public void testSafeDeleteForbiddenFromActual() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/safeDeleteForbiddenFromActual/");
    }

    @TestMetadata("safeDeleteFromActual")
    public void testSafeDeleteFromActual() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/safeDeleteFromActual/");
    }

    @TestMetadata("safeDeleteUsedInAnotherPlatform")
    public void testSafeDeleteUsedInAnotherPlatform() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/safeDeleteUsedInAnotherPlatform/");
    }

    @TestMetadata("sealed")
    public void testSealed() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/sealed/");
    }

    @TestMetadata("sealedSubclass")
    public void testSealedSubclass() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/sealedSubclass/");
    }

    @TestMetadata("secondaryConstructorAbsence")
    public void testSecondaryConstructorAbsence() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/secondaryConstructorAbsence/");
    }

    @TestMetadata("withFakeJvm")
    public void testWithFakeJvm() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/withFakeJvm/");
    }

    @TestMetadata("withTest")
    public void testWithTest() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/withTest/");
    }

    @TestMetadata("withTestDummy")
    public void testWithTestDummy() throws Exception {
        runTest("idea/testData/multiModuleQuickFix/withTestDummy/");
    }
}
