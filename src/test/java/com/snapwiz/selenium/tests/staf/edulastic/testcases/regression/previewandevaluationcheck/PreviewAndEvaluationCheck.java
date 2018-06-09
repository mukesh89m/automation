package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.previewandevaluationcheck;

import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentDetailsPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.GraphingQuestionCreation;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.preview.PreviewPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.*;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by pragyas on 06-06-2016.
 */
public class PreviewAndEvaluationCheck extends Driver{

    PreviewPage previewPage;
    WebDriver driver;

    @BeforeMethod
    public void init(){
        driver = getWebDriver();
        previewPage = PageFactory.initElements(driver,PreviewPage.class);
    }

    @Test(priority = 1,enabled = true)
    //This test cases uses the assignment created in AssignmentFlowWithReport class in sanitymastercode package
    public void previewAndEvaluationCheck(){
        try{

            ReportUtil.log("Description", "This test case validates the preview and evaluation of an assessment at instructor side", "info");

            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver, GraphingQuestionCreation.class);

            String appendChar = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            //String appendChar = "aefx";
            int dataIndex = 1;

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().enterAndSelectSchool("987654963",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Assignment().openUseExistingAssignment(dataIndex,"district");
            new WebDriverUtil().waitTillVisibilityOfElement(assessmentDetailsPage.preview,60);
            assessmentDetailsPage.preview.click();//Click on preview button

            driver.switchTo().frame("iframe");
            new WebDriverUtil().waitTillVisibilityOfElement(previewPage.getAnswerOption().get(0),240);
            Thread.sleep(4000);
            //1.correct answer for true false
            previewPage.getAnswerOption().get(0).click();
            previewPage.button_next.click();//Click on next button

            //2.correct answer for multiple choice
            previewPage.getMultipleChoiceAnswerOption().get(0).click();
            previewPage.button_next.click();//Click on next button

            //3.Partial correct answer for multiple selection
            previewPage.getMultipleSelectionAnswerOption().get(0).click();
            previewPage.getMultipleSelectionAnswerOption().get(1).click();
            previewPage.button_next.click();//Click on next button

            //4.incorrect answer for text drop down
            new SelectDropDown().selectByText(previewPage.textDropDown,"Answer2");
            previewPage.button_next.click();//Click on next button

            Thread.sleep(2000);
            //5.Skip essay question type
            previewPage.button_next.click();//Click on next button

            //6.Correct answer for text entry
            previewPage.getInputTextEntry().sendKeys("Correct Answer");
            previewPage.button_next.click();//Click on next button

            //7.Incorrect answer for numeric entry with units
            previewPage.getTextField_QuestionInputNumericEntry().sendKeys("20");
            new SelectDropDown().selectByText(previewPage.textDropDown, "feet");
            previewPage.button_next.click();//Click on next button

            //8.Incorrect answer for numeric
            Thread.sleep(2000);
            previewPage.getTextArea_CorrectAnswerEditWindow_expressionEvaluator().sendKeys("5");
            previewPage.button_next.click();//Click on next button

            //9.Attempt multipart question(multiple choice as correct and multiple selection as partial correct)
            previewPage.getTextinputfield().sendKeys("essay type question");
            previewPage.option_multipleSelection_multipart.get(0).click();
            previewPage.option_multipleSelection_multipart.get(1).click();
            WebDriverUtil.clickOnElementUsingJavascript(previewPage.getMultipleChoiceAnswerOption().get(0));
            previewPage.button_next.click();//Click on next button

            Thread.sleep(1000);
            //10.Correct answer for GraphPlotter
            previewPage.button_next.click();//Click on next button

            //11.Correct answer for match the following
            for(int i=3;i<6;i++)
            {
                if(previewPage.mathMlEditBox.get(i).getText().replaceAll("/n"," ").trim().contains("5"))
                {
                    new DragAndDrop().dragAndDrop(previewPage.mathMlEditBox.get(i),previewPage.editBox_droppable.get(0));
                }
                if(previewPage.mathMlEditBox.get(i).getText().replaceAll("/n"," ").trim().contains("6"))
                {
                    new DragAndDrop().dragAndDrop(previewPage.mathMlEditBox.get(i),previewPage.editBox_droppable.get(3));

                }
                if(previewPage.mathMlEditBox.get(i).getText().replaceAll("/n"," ").trim().contains("7"))

                {
                    new DragAndDrop().dragAndDrop(previewPage.mathMlEditBox.get(i),previewPage.editBox_droppable.get(4));

                }
            }

            if(previewPage.text_editBox.get(1).getText().contains("1000"))
            {

                new DragAndDrop().dragAndDrop(previewPage.text_editBox.get(1), previewPage.editBox_droppable.get(1));
                new DragAndDrop().dragAndDrop(previewPage.text_editBox.get(2), previewPage.editBox_droppable.get(2));
            }
            else
            {
                new DragAndDrop().dragAndDrop(previewPage.text_editBox.get(1), previewPage.editBox_droppable.get(2));
                new DragAndDrop().dragAndDrop(previewPage.text_editBox.get(2), previewPage.editBox_droppable.get(1));
            }

            previewPage.button_next.click();//Click on next button

            //12.partial correct answer for multiple selection
            previewPage.getMultipleSelectionAnswerOption().get(0).click();
            previewPage.getMultipleSelectionAnswerOption().get(1).click();
            previewPage.button_next.click();//Click on next button

            //13.Correct answer for expression evaluator
            previewPage.getAnsweringSpace_ExpressionEvaluatorPreview().click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","5");
            previewPage.button_next.click();//Click on next button

            //14.Correct for Label an image drop down
            new WebDriverUtil().selectItemByIndex(previewPage.getDropDown_labelAnImageDropDown(), 1);
            previewPage.button_next.click();//Click on next button

            //15.Correct answer for graphing
            graphing.getLabelsInGrid().get(19).click();//Place the point on graph
            previewPage.button_next.click();//Click on next button

            //16.Incorrect answer for classification
            List<WebElement> answersToDrop = previewPage.answerChoiceToDrop;
            WebElement rhsFields = previewPage.classToDrop.get(0);
            answersToDrop.get(3).click();
            new DragAndDrop().dragAndDrop(answersToDrop.get(3), rhsFields);
            previewPage.button_next.click();//Click on next button

            //17.Correct answer for cloze matrix
            previewPage.input_clozeMatrix.get(0).click();
            previewPage.input_clozeMatrix.clear();
            driver.switchTo().activeElement().sendKeys("1");
            previewPage.button_next.click();//Click on next button

            //18.Correct answer for fraction editor
            previewPage.column_fractionEditor.get(0).click();
            previewPage.button_next.click();//Click on next button

            //19.partial correct answer for cloze formula
            new DragAndDrop().dragAndDrop(previewPage.getAnswerChoice1_clozeFormula(),previewPage.getBlankSpace5());
            new DragAndDrop().dragAndDrop(previewPage.getAnswerChoice2_clozeFormula(),previewPage.getBlankSpace4());
            new DragAndDrop().dragAndDrop(previewPage.getAnswerChoice3_clozeFormula(),previewPage.getBlankSpace3());
            new DragAndDrop().dragAndDrop(previewPage.getAnswerChoice5_clozeFormula(),previewPage.getBlankSpace2());
            new DragAndDrop().dragAndDrop(previewPage.getAnswerChoice4_clozeFormula(),previewPage.getBlankSpace1());
            previewPage.button_next.click();//Click on next button

            //20.Correct answer for sentence correction and submit it
            Thread.sleep(2000);
            previewPage.button_next.click();//click on submit button

            previewPage.getSubmitButton().click();//click on submit button

            //Verify the total score and question point
            CustomAssert.assertEquals(previewPage.totalScoreObtained.getText(),"11.19","Verify the total score obtained","Total score obtained is displayed as expected","Total score is not displayed as expected");
            CustomAssert.assertEquals(previewPage.questionPoint.getText(),"21","Verify the question point","Question point is displayed as expected","Question point is not displayed as expected");

            previewPage.button_continue.click();//Click on continue button

            //Verify the individual question point for all the questions
            verifyTrueFalseQuestion();
            Thread.sleep(1000);
            verifyMultipleChoiceQuestion();
            Thread.sleep(1000);
            verifyMultiSelectionQuestion();
            Thread.sleep(1000);
            verifyTextDropdownQuestion();
            Thread.sleep(1000);
            verifyEssayQuestion();
            Thread.sleep(1000);
            verifyTextEntryQuestion();
            Thread.sleep(1000);
            verifyNumericEntryWithUnitsQuestion();
            Thread.sleep(1000);
            verifyNumericQuestion();
            Thread.sleep(1000);
            verifyMultipartQuestion();
            Thread.sleep(1000);
            verifyGraphPlotterQuestion();
            Thread.sleep(1000);
            verifyMatchTheFollowingQuestion();
            Thread.sleep(1000);
            verifyMultiSelectionQuestion();
            Thread.sleep(1000);
            verifyExpressionEvaluatorQuestion();
            Thread.sleep(1000);
            verifyLabelAnImageDropdownQuestion();
            Thread.sleep(1000);
            verifyGraphingQuestion();
            Thread.sleep(1000);
            verifyClassificationQuestion();
            Thread.sleep(1000);
            verifyClozeMatrixQuestion();
            Thread.sleep(1000);
            verifyFractionEditorQuestion();
            Thread.sleep(1000);
            verifyClozeFormulaQuestion();
            Thread.sleep(1000);
            verifySentenceCorrectionQuestion();
            Thread.sleep(1000);

        }catch (Exception e){
            Assert.fail("Exception in testcase 'previewAndEvaluationCheck' in class 'PreviewAndEvaluationCheck'", e);

        }
    }

    public void verifyTrueFalseQuestion(){
        try{

            CustomAssert.assertEquals(previewPage.scoreObtained.getAttribute("value"),"1","Verify the score for true false","Score is displayed as expected","Score is not displayed as expected");
            previewPage.nextArrow.click();//click on next arrow

        }catch (Exception e){
            Assert.fail("Exception in testcase 'verifyTrueFalseQuestion' in class 'PreviewAndEvaluationCheck'", e);

        }
    }



    public void verifyMultipleChoiceQuestion(){
        try{

            CustomAssert.assertEquals(previewPage.scoreObtained.getAttribute("value"),"1","Verify the score for multiple choice","Score is displayed as expected","Score is not displayed as expected");
            new WebDriverUtil().waitTillVisibilityOfElement(previewPage.nextArrow,20);
            previewPage.nextArrow.click();//click on next arrow

        }catch (Exception e){
            Assert.fail("Exception in testcase 'verifyMultipleChoiceQuestion' in class 'PreviewAndEvaluationCheck'", e);

        }
    }

    public void verifyMultiSelectionQuestion(){
        try{

            CustomAssert.assertEquals(previewPage.scoreObtained.getAttribute("value"),"0.33","Verify the score for Multiple selection","Score is displayed as expected","Score is not displayed as expected");
            new WebDriverUtil().waitTillVisibilityOfElement(previewPage.nextArrow,20);
            previewPage.nextArrow.click();//click on next arrow

        }catch (Exception e){
            Assert.fail("Exception in testcase 'verifyMultiSelectionQuestion' in class 'PreviewAndEvaluationCheck'", e);

        }
    }

    public void verifyTextDropdownQuestion(){
        try{

            CustomAssert.assertEquals(previewPage.scoreObtained.getAttribute("value"),"0","Verify the score for Text drop down","Score is displayed as expected","Score is not displayed as expected");
            new WebDriverUtil().waitTillVisibilityOfElement(previewPage.nextArrow,20);
            previewPage.nextArrow.click();//click on next arrow

        }catch (Exception e){
            Assert.fail("Exception in testcase 'verifyTextDropdownQuestion' in class 'PreviewAndEvaluationCheck'", e);

        }
    }

    public void verifyEssayQuestion(){
        try{

            CustomAssert.assertEquals(previewPage.scoreObtained.getAttribute("value"),"0","Verify the score for Multiple selection","Score is displayed as expected","Score is not displayed as expected");
            new WebDriverUtil().waitTillVisibilityOfElement(previewPage.nextArrow,20);
            previewPage.nextArrow.click();//click on next arrow

        }catch (Exception e){
            Assert.fail("Exception in testcase 'verifyEssayQuestion' in class 'PreviewAndEvaluationCheck'", e);

        }
    }

    public void verifyTextEntryQuestion(){
        try{

            CustomAssert.assertEquals(previewPage.scoreObtained.getAttribute("value"),"1","Verify the score for Multiple selection","Score is displayed as expected","Score is not displayed as expected");
            new WebDriverUtil().waitTillVisibilityOfElement(previewPage.nextArrow,20);
            previewPage.nextArrow.click();//click on next arrow

        }catch (Exception e){
            Assert.fail("Exception in testcase 'verifyTextEntryQuestion' in class 'PreviewAndEvaluationCheck'", e);

        }
    }

    public void verifyNumericEntryWithUnitsQuestion(){
        try{

            CustomAssert.assertEquals(previewPage.scoreObtained.getAttribute("value"),"0","Verify the score for Multiple selection","Score is displayed as expected","Score is not displayed as expected");
            new WebDriverUtil().waitTillVisibilityOfElement(previewPage.nextArrow,20);
            previewPage.nextArrow.click();//click on next arrow

        }catch (Exception e){
            Assert.fail("Exception in testcase 'verifyNumericEntryWithUnitsQuestion' in class 'PreviewAndEvaluationCheck'", e);

        }
    }

    public void verifyNumericQuestion(){
        try{

            CustomAssert.assertEquals(previewPage.scoreObtained.getAttribute("value"),"0","Verify the score for Multiple selection","Score is displayed as expected","Score is not displayed as expected");
            new WebDriverUtil().waitTillVisibilityOfElement(previewPage.nextArrow,20);
            previewPage.nextArrow.click();//click on next arrow

        }catch (Exception e){
            Assert.fail("Exception in testcase 'verifyNumericQuestion' in class 'PreviewAndEvaluationCheck'", e);

        }
    }

    public void verifyMultipartQuestion(){
        try{

            CustomAssert.assertEquals(previewPage.scoreObtained.getAttribute("value"),"","Verify the score for Multiple selection","Score is displayed as expected","Score is not displayed as expected");
            new WebDriverUtil().waitTillVisibilityOfElement(previewPage.nextArrow,20);
            previewPage.nextArrow.click();//click on next arrow

        }catch (Exception e){
            Assert.fail("Exception in testcase 'verifyMultipartQuestion' in class 'PreviewAndEvaluationCheck'", e);

        }
    }

    public void verifyGraphPlotterQuestion(){
        try{

            CustomAssert.assertEquals(previewPage.scoreObtained.getAttribute("value"),"1","Verify the score for Multiple selection","Score is displayed as expected","Score is not displayed as expected");
            new WebDriverUtil().waitTillVisibilityOfElement(previewPage.nextArrow,20);
            previewPage.nextArrow.click();//click on next arrow

        }catch (Exception e){
            Assert.fail("Exception in testcase 'verifyGraphPlotterQuestion' in class 'PreviewAndEvaluationCheck'", e);

        }
    }

    public void verifyMatchTheFollowingQuestion(){
        try{

            CustomAssert.assertEquals(previewPage.scoreObtained.getAttribute("value"),"1","Verify the score for Multiple selection","Score is displayed as expected","Score is not displayed as expected");
            new WebDriverUtil().waitTillVisibilityOfElement(previewPage.nextArrow,20);
            previewPage.nextArrow.click();//click on next arrow

        }catch (Exception e){
            Assert.fail("Exception in testcase 'verifyMatchTheFollowingQuestion' in class 'PreviewAndEvaluationCheck'", e);

        }
    }

    public void verifyExpressionEvaluatorQuestion(){
        try{

            CustomAssert.assertEquals(previewPage.scoreObtained.getAttribute("value"),"1","Verify the score for Multiple selection","Score is displayed as expected","Score is not displayed as expected");
            new WebDriverUtil().waitTillVisibilityOfElement(previewPage.nextArrow,20);
            previewPage.nextArrow.click();//click on next arrow

        }catch (Exception e){
            Assert.fail("Exception in testcase 'verifyExpressionEvaluatorQuestion' in class 'PreviewAndEvaluationCheck'", e);

        }
    }

    public void verifyLabelAnImageDropdownQuestion(){
        try{

            CustomAssert.assertEquals(previewPage.scoreObtained.getAttribute("value"),"0.33","Verify the score for Multiple selection","Score is displayed as expected","Score is not displayed as expected");
            previewPage.nextArrow.click();//click on next arrow

        }catch (Exception e){
            Assert.fail("Exception in testcase 'verifyLabelAnImageDropdownQuestion' in class 'PreviewAndEvaluationCheck'", e);

        }
    }

    public void verifyGraphingQuestion(){
        try{

            CustomAssert.assertEquals(previewPage.scoreObtained.getAttribute("value"),"1","Verify the score for Multiple selection","Score is displayed as expected","Score is not displayed as expected");
            previewPage.nextArrow.click();//click on next arrow

        }catch (Exception e){
            Assert.fail("Exception in testcase 'verifyGraphingQuestion' in class 'PreviewAndEvaluationCheck'", e);

        }
    }

    public void verifyClassificationQuestion(){
        try{

            CustomAssert.assertEquals(previewPage.scoreObtained.getAttribute("value"),"0","Verify the score for Multiple selection","Score is displayed as expected","Score is not displayed as expected");
            previewPage.nextArrow.click();//click on next arrow

        }catch (Exception e){
            Assert.fail("Exception in testcase 'verifyClassificationQuestion' in class 'PreviewAndEvaluationCheck'", e);

        }
    }

    public void verifyClozeMatrixQuestion(){
        try{

            CustomAssert.assertEquals(previewPage.scoreObtained.getAttribute("value"),"1","Verify the score for Multiple selection","Score is displayed as expected","Score is not displayed as expected");
            previewPage.nextArrow.click();//click on next arrow

        }catch (Exception e){
            Assert.fail("Exception in testcase 'verifyClozeMatrixQuestion' in class 'PreviewAndEvaluationCheck'", e);

        }
    }


    public void verifyFractionEditorQuestion(){
        try{

            CustomAssert.assertEquals(previewPage.scoreObtained.getAttribute("value"),"1","Verify the score for Multiple selection","Score is displayed as expected","Score is not displayed as expected");
            previewPage.nextArrow.click();//click on next arrow

        }catch (Exception e){
            Assert.fail("Exception in testcase 'verifyFractionEditorQuestion' in class 'PreviewAndEvaluationCheck'", e);

        }
    }


    public void verifyClozeFormulaQuestion(){
        try{

            CustomAssert.assertEquals(previewPage.scoreObtained.getAttribute("value"),"0.2","Verify the score for Multiple selection","Score is displayed as expected","Score is not displayed as expected");
            previewPage.nextArrow.click();//click on next arrow

        }catch (Exception e){
            Assert.fail("Exception in testcase 'verifyClozeFormulaQuestion' in class 'PreviewAndEvaluationCheck'", e);

        }
    }


    public void verifySentenceCorrectionQuestion(){
        try{

            CustomAssert.assertEquals(previewPage.scoreObtained.getAttribute("value"),"1","Verify the score for Multiple selection","Score is displayed as expected","Score is not displayed as expected");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'verifySentenceCorrectionQuestion' in class 'PreviewAndEvaluationCheck'", e);

        }
    }







}
