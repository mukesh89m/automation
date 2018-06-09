package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.cmsFlow;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

/**
 * Created by mukesh on 15/3/16.
 */
public class CreateChapterAndTlo extends Driver{

    ManageContent manageContent;
    Preview preview;
    String notificationMessageActual = null;
    String notificationMessageExpected = null;
    String previewQuestionText=null;
    String previewQuestionTextExpected=null;
    String previewAssessmentName=null;
    String actualPointAvailable=null;
    String tlos=null;
    String actualTlos=null;
    String courseName=null;
    String tlo=null;
    @BeforeMethod
    public void initializeWebElement() {
        WebDriver driver=Driver.getWebDriver();
         manageContent= PageFactory.initElements(driver,ManageContent.class);
         preview = PageFactory.initElements(driver, Preview.class);

    }

    @Test(priority = 1,enabled = true)
    public void createChapter(){
        try {

            new Assignment().createTlo(1); //create tlo
            ReportUtil.log("create tlo", "TLO created successfully", "pass");
            new Assignment().createChapter(1, 2); //create chapter
            ReportUtil.log("create Chapter", "Chapter created successfully", "pass");
            new Assignment().publishchapter(1);//publish chapter
            ReportUtil.log("publish Chapter", "Chapter publish successfully", "pass");
            new Assignment().create(1);//Create an diagnostic test
            ReportUtil.log("create diagnostic test", "diagnostic test created successfully", "pass");
            new Assignment().create(2);//Create an practice test
            ReportUtil.log("create practice test", "practice test created successfully", "pass");
            new Assignment().create(3);//Create an static test
            ReportUtil.log("create assessment", "assessment created successfully", "pass");
            WebDriverUtil.waitTillVisibilityOfElement(manageContent.manageContent,50);
            manageContent.manageContent.click();//click on the manage content
            List<WebElement> assessmentNames=manageContent.assessmentName;
            CustomAssert.assertEquals(assessmentNames.get(0).getAttribute("title"),"Diagnostic_Assessment","Verify Diagnostic_Assessment Text","Diagnostic_Assessment Created successfully","Diagnostic_Assessment not Created successfully");
            CustomAssert.assertEquals(assessmentNames.get(1).getAttribute("title"),"Practice_Assessment","Verify Practice_Assessment Text","Practice_Assessment Created successfully","Practice_Assessment not Created successfully");
            CustomAssert.assertEquals(assessmentNames.get(2).getAttribute("title"),"Static_Assessment","Verify Static_Assessment Text","Static_Assessment Created successfully","Static_Assessment not Created successfully");

        } catch (Exception e) {
            Assert.fail("Exception in TC of createChapter of class CreateChapterAndTlo",e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void createTrueFalseQuestionWithHintAndSolution(){
        try {
            String questionText = ReadTestData.readDataByTagName("", "questiontext", "4");
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "4");

            new Assignment().create(4);
            WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
            tlo=preview.learning_objective_text.getText().trim();
            Thread.sleep(3000);
            WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
            manageContent.preview_Button.click();//click on preview
            WebDriverUtil.switchToNewWindow();
            WebDriverUtil.waitTillVisibilityOfElement(preview.previewPage_HeaderLogo, 240);

            courseName = preview.previewCourseName.getText().trim();
            CustomAssert.assertEquals(courseName, "Biology", "Verify Course Name in preview page", "Course name is displayed in preview page","Course name is not displayed in preview page");

            previewQuestionText = preview.preview_questionName.getText().trim();
            previewQuestionTextExpected ="True False " + questionText;
            CustomAssert.assertEquals(previewQuestionText, previewQuestionTextExpected, "Verify Question Name in preview page", "Question name is displayed in preview page","Question name is not displayed in preview page");

            previewAssessmentName = preview.questionPreviewHeader.getText().trim();
            CustomAssert.assertEquals(previewAssessmentName, assessmentName, "Verify Assessment Name in preview page", "Assessment name is displayed in preview page","Assessment name is not displayed in preview page");

            actualTlos=preview.preview_tlos.getText().trim();
            CustomAssert.assertEquals(actualTlos, "• "+tlo, "Verify learning objective in preview page", "learning objective is displayed in preview page", "learning objective is not displayed in preview page");

            actualPointAvailable = preview.pointAvailable_label.getText().trim();
            CustomAssert.assertEquals(actualPointAvailable.trim(),"Points Available : 1", "Verify Points Available label in preview page", "Points Available label is displayed in preview page","Points Available label is not displayed in preview page");

            if(!(preview.preview_score.get(0).getText().trim().equals("95.0%")&& preview.preview_score.get(1).getText().trim().equals("Students got it correct"))){
                CustomAssert.fail("Verify Score","score is not displayed in preview page");
            }

            preview.trueFalseAnswerLabel.get(0).click();//click on option 'A'
            preview.checkAnswer_button.click();//click on answer choice
            notificationMessageActual = preview.notificationMsg.getText().trim();
            ReportUtil.log("Attempting question", "Selected right answer", "pass");
            notificationMessageExpected = "You got it right.";
            CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for right answer", "Message \"You got it right\" is displayed", "Message \"You got it right\" is not displayed");
            Thread.sleep(2000);
            preview.trueFalseAnswerLabel.get(1).click();//click on option 'B'
            preview.checkAnswer_button.click();//click on answer choice
            ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
            notificationMessageActual = preview.notificationMsg.getText().trim();
            notificationMessageExpected = "You got it wrong.";
            CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");
            preview.hint_button.click();//click on hint
            String hintContent = preview.hintContent.getText();
            CustomAssert.assertEquals(hintContent, "Hint Text", "Verify hint text","The hint data is appearing correct","The hint data is not appearing.");
            Thread.sleep(3000);
            preview.solution_Button.click();//click on solution
            String solutionContent = preview.solutionContent.getText();
            CustomAssert.assertEquals(solutionContent, "Solution Text", "verify Solution Text","The solution data is appearing correct ","The solution data is not appearing.");
            CustomAssert.assertTrue(preview.preview_image.getAttribute("src").contains("https://s3.amazonaws.com/content-d/253/1mb-"), "Verify image question content", "image is uploaded successfully for question content", "image is not uploaded successfully for question content");


        } catch (Exception e) {
            Assert.fail("Exception in TC of createTrueFalseQuestionWithHintAndSolution of class CreateChapterAndTlo",e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void createMultipleChoiceQuestionWithContentImage(){
        try {
            String questionText = ReadTestData.readDataByTagName("", "questiontext", "5");
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "5");

            new Assignment().create(5);//Create an multiple choice question
            ReportUtil.log("Create Multiple Choice Question", "Multiple Choice Question created successfully", "pass");
            WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
            tlo=preview.learning_objective_text.getText().trim();
            Thread.sleep(3000);
            WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
            manageContent.preview_Button.click();//click on preview
            WebDriverUtil.switchToNewWindow();
            WebDriverUtil.waitTillVisibilityOfElement(preview.previewPage_HeaderLogo, 240);
            courseName = preview.previewCourseName.getText().trim();
            CustomAssert.assertEquals(courseName, "Biology", "Verify Course Name in preview page", "Course name is displayed in preview page","Course name is not displayed in preview page");

            previewQuestionText = preview.preview_questionName.getText().trim();
            previewQuestionTextExpected ="Multiple Choice "+questionText;
            CustomAssert.assertEquals(previewQuestionText, previewQuestionTextExpected, "Verify Question Name in preview page", "Question name is displayed in preview page","Question name is not displayed in preview page");

            previewAssessmentName = preview.questionPreviewHeader.getText().trim();
            CustomAssert.assertEquals(previewAssessmentName, assessmentName, "Verify Assessment Name in preview page", "Assessment name is displayed in preview page","Assessment name is not displayed in preview page");

            actualTlos=preview.preview_tlos.getText().trim();
            CustomAssert.assertEquals(actualTlos, "• "+tlo, "Verify learning objective in preview page", "learning objective is displayed in preview page", "learning objective is not displayed in preview page");

            actualPointAvailable = preview.pointAvailable_label.getText().trim();
            CustomAssert.assertEquals(actualPointAvailable.trim(),"Points Available : 1", "Verify Points Available label in preview page", "Points Available label is displayed in preview page","Points Available label is not displayed in preview page");

            if(!(preview.preview_score.get(0).getText().trim().equals("95.0%")&& preview.preview_score.get(1).getText().trim().equals("Students got it correct"))){
                CustomAssert.fail("Verify Score","score is not displayed in preview page");
            }
            preview.selectMultipleChoice.get(0).click();//click on option 'A'
            preview.checkAnswer_button.click();//click on answer choice
            notificationMessageActual = preview.notificationMsg.getText().trim();
            notificationMessageExpected = "You got it right.";
            CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for right answer", "Message \"You got it right\" is displayed", "Message \"You got it right\" is not displayed");
            Thread.sleep(2000);
            preview.selectMultipleChoice.get(0).click();//click on option 'B'
            Thread.sleep(3000);
            preview.checkAnswer_button.click();//click on answer choice
            ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
            Thread.sleep(3000);
            notificationMessageActual = preview.notificationMsg.getText().trim();
            notificationMessageExpected = "You got it wrong.";
            CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");
            preview.hint_button.click();//click on hint
            String hintContent = preview.hintContent.getText();
            CustomAssert.assertEquals(hintContent, "Hint Text", "Verify hint text","The hint data is appearing correct","The hint data is not appearing.");
            Thread.sleep(3000);
            preview.solution_Button.click();//click on solution
            String solutionContent = preview.solutionImage.getAttribute("src");
            CustomAssert.assertTrue(solutionContent.contains("https://s3.amazonaws.com/content-d/253/1mb-"), "Verify image in Solution content", "image is uploaded successfully for solution content", "image is not uploaded successfully for solution content");


        } catch (Exception e) {
            Assert.fail("Exception in TC of createMultipleChoiceQuestionWithContentImage of class CreateChapterAndTlo",e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void createMultipleSelectionQuestionWithHintImage(){
        try {
            String questionText = ReadTestData.readDataByTagName("", "questiontext", "6");
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "6");

            new Assignment().create(6);//Create an multiple Selection question
            ReportUtil.log("Create Multiple Selection Question", "Multiple Selection Question created successfully", "pass");
            WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
            tlo=preview.learning_objective_text.getText().trim();
            Thread.sleep(3000);
            WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
            manageContent.preview_Button.click();//click on preview
            WebDriverUtil.switchToNewWindow();
            WebDriverUtil.waitTillVisibilityOfElement(preview.previewPage_HeaderLogo, 240);
            courseName = preview.previewCourseName.getText().trim();
            CustomAssert.assertEquals(courseName, "Biology", "Verify Course Name in preview page", "Course name is displayed in preview page","Course name is not displayed in preview page");

            previewQuestionText = preview.preview_questionName.getText().trim();
            previewQuestionTextExpected ="Multi Selection " + questionText;
            CustomAssert.assertEquals(previewQuestionText, previewQuestionTextExpected, "Verify Question Name in preview page", "Question name is displayed in preview page","Question name is not displayed in preview page");

            previewAssessmentName = preview.questionPreviewHeader.getText().trim();
            CustomAssert.assertEquals(previewAssessmentName, assessmentName, "Verify Assessment Name in preview page", "Assessment name is displayed in preview page","Assessment name is not displayed in preview page");

            actualTlos=preview.preview_tlos.getText().trim();
            CustomAssert.assertEquals(actualTlos, "• "+tlo, "Verify learning objective in preview page", "learning objective is displayed in preview page", "learning objective is not displayed in preview page");

            actualPointAvailable = preview.pointAvailable_label.getText().trim();
            CustomAssert.assertEquals(actualPointAvailable.trim(),"Points Available : 1", "Verify Points Available label in preview page", "Points Available label is displayed in preview page","Points Available label is not displayed in preview page");

            if(!(preview.preview_score.get(0).getText().trim().equals("95.0%")&& preview.preview_score.get(1).getText().trim().equals("Students got it correct"))){
                CustomAssert.fail("Verify Score","score is not displayed in preview page");
            }
            preview.multipleSelection.get(0).click();//click on option 'A'
            preview.multipleSelection.get(1).click();//click on option 'B'
            preview.checkAnswer_button.click();//click on answer choice
            notificationMessageActual = preview.notificationMsg.getText().trim();
            notificationMessageExpected = "You got it right.";
            CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for right answer", "Message \"You got it right\" is displayed", "Message \"You got it right\" is not displayed");
            Thread.sleep(2000);
            preview.multipleSelection.get(2).click();//click on option 'B'
            preview.checkAnswer_button.click();//click on answer choice
            ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
            notificationMessageActual = preview.notificationMsg.getText().trim();
            notificationMessageExpected = "You got it wrong.";
            CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");
            preview.solution_Button.click();//click on solution
            Thread.sleep(2000);
            String solutionContent = preview.solutionContent.getText();
            CustomAssert.assertEquals(solutionContent, "Solution Text", "verify Solution Text", "The solution data is appearing correct ", "The solution data is not appearing.");
            preview.hint_button.click();//click on hint
            String hintContent = preview.hintImage.getAttribute("src");
            CustomAssert.assertTrue(hintContent.contains("https://s3.amazonaws.com/content-d/253/1mb-"), "Verify image in Hint content", "image is uploaded successfully for hint content", "image is not uploaded successfully for hint content");

        } catch (Exception e) {
            Assert.fail("Exception in TC of createMultipleSelectionQuestionWithHintImage of class CreateChapterAndTlo",e);
        }
    }

    @Test(priority=5,enabled = true)
    public void textEntryQuestionWithAlgorithm() {
        {
            try {
                String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "7");
                new QuestionCreate().textEntryWithAlgorithm(7);//Create an Text Entry question
                ReportUtil.log("Create Text Entry Question", "Text Entry Question created successfully", "pass");
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                tlo=preview.learning_objective_text.getText().trim();
                Thread.sleep(3000);
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                manageContent.preview_Button.click();//click on preview
                WebDriverUtil.switchToNewWindow();
                WebDriverUtil.waitTillVisibilityOfElement(preview.previewPage_HeaderLogo, 240);
                courseName = preview.previewCourseName.getText().trim();
                CustomAssert.assertEquals(courseName, "Biology", "Verify Course Name in preview page", "Course name is displayed in preview page","Course name is not displayed in preview page");

                previewQuestionText = preview.preview_questionName.getText().trim();
                CustomAssert.assertEquals(previewQuestionText, "1+2", "Verify Question Name in preview page", "Question name is displayed in preview page","Question name is not displayed in preview page");

                previewAssessmentName = preview.questionPreviewHeader.getText().trim();
                CustomAssert.assertEquals(previewAssessmentName, assessmentName, "Verify Assessment Name in preview page", "Assessment name is displayed in preview page","Assessment name is not displayed in preview page");

                actualTlos=preview.preview_tlos.getText().trim();
                CustomAssert.assertEquals(actualTlos, "• "+tlo, "Verify learning objective in preview page", "learning objective is displayed in preview page", "learning objective is not displayed in preview page");

                actualPointAvailable = preview.pointAvailable_label.getText().trim();
                CustomAssert.assertEquals(actualPointAvailable.trim(),"Points Available : 1", "Verify Points Available label in preview page", "Points Available label is displayed in preview page","Points Available label is not displayed in preview page");

                if(!(preview.preview_score.get(0).getText().trim().equals("95.0%")&& preview.preview_score.get(1).getText().trim().equals("Students got it correct"))){
                    CustomAssert.fail("Verify Score","score is not displayed in preview page");
                }

                preview.textEntryField.sendKeys("2");
                preview.checkAnswer_button.click();//click on answer choice
                String wrongNotificationMessage = preview.notificationMsg.getText();
                ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
                notificationMessageExpected = "You got it wrong.";
                CustomAssert.assertEquals(wrongNotificationMessage, notificationMessageExpected, "Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");
                Thread.sleep(2000);
                preview.textEntryField.clear();
                preview.textEntryField.sendKeys("3");
                preview.checkAnswer_button.click();//click on answer choice
                Thread.sleep(1000);
                String rightNotificationMessage = preview.notificationMsg.getText();
                ReportUtil.log("Attempting question", "Selected right answer", "pass");
                notificationMessageExpected = "You got it right.";
                CustomAssert.assertEquals(rightNotificationMessage, notificationMessageExpected, "Verify notification message for right answer", "Message \"You got it right\" is displayed", "Message \"You got it right\" is not displayed");
                preview.hint_button.click();//click on hint
                String hintContent = preview.hintContent.getText();
                CustomAssert.assertEquals(hintContent, "Hint Text", "Verify hint text","The hint data is appearing correct","The hint data is not appearing.");
                Thread.sleep(3000);
                preview.solution_Button.click();//click on solution
                String solutionContent = preview.solutionContent.getText();
                CustomAssert.assertEquals(solutionContent, "Solution Text", "verify Solution Text", "The solution data is appearing correct ", "The solution data is not appearing.");

            } catch (Exception e) {
                Assert.fail("Exception in TC textEntryQuestionWithAlgorithm of class CreateChapterAndTlo",e);
            }
        }

    }

    @Test(priority=6,enabled = true)
    public void advancedNumericWithAlgorithm() {
        {
            try {
                String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "8");
                new QuestionCreate().advancedNumericWithAlgorithm(8);//Create an Advanced Numeric question
                ReportUtil.log("Create Advanced Numeric With Algorithm Question", "Advanced Numeric With Algorithm Question created successfully", "pass");
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                tlo=preview.learning_objective_text.getText().trim();
                Thread.sleep(3000);
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                manageContent.preview_Button.click();//click on preview
                WebDriverUtil.switchToNewWindow();
                WebDriverUtil.waitTillVisibilityOfElement(preview.previewPage_HeaderLogo, 240);
                courseName = preview.previewCourseName.getText().trim();
                CustomAssert.assertEquals(courseName, "Biology", "Verify Course Name in preview page", "Course name is displayed in preview page","Course name is not displayed in preview page");

                previewQuestionText = preview.preview_questionName.getText().trim();
                CustomAssert.assertEquals(previewQuestionText, "1+2", "Verify Question Name in preview page", "Question name is displayed in preview page","Question name is not displayed in preview page");

                previewAssessmentName = preview.questionPreviewHeader.getText().trim();
                CustomAssert.assertEquals(previewAssessmentName, assessmentName, "Verify Assessment Name in preview page", "Assessment name is displayed in preview page","Assessment name is not displayed in preview page");

                actualTlos=preview.preview_tlos.getText().trim();
                CustomAssert.assertEquals(actualTlos, "• "+tlo, "Verify learning objective in preview page", "learning objective is displayed in preview page", "learning objective is not displayed in preview page");

                actualPointAvailable = preview.pointAvailable_label.getText().trim();
                CustomAssert.assertEquals(actualPointAvailable.trim(),"Points Available : 1", "Verify Points Available label in preview page", "Points Available label is displayed in preview page","Points Available label is not displayed in preview page");

                if(!(preview.preview_score.get(0).getText().trim().equals("95.0%")&& preview.preview_score.get(1).getText().trim().equals("Students got it correct"))){
                    CustomAssert.fail("Verify Score","score is not displayed in preview page");
                }
                preview.textEntryFields.get(0).sendKeys("3");
                preview.textEntryFields.get(1).sendKeys("3");
                preview.checkAnswer_button.click();//click on answer choice
                new UIElement().waitAndFindElement(preview.notificationMsg);
                notificationMessageActual = preview.notificationMsg.getText().trim();
                ReportUtil.log("Attempting question", "Selected right answer", "pass");
                notificationMessageExpected = "You got it right.";
                CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for right answer", "Message \"You got it right\" is displayed", "Message \"You got it right\" is not displayed");
                Thread.sleep(2000);
                preview.textEntryFields.get(0).clear();
                preview.textEntryFields.get(1).clear();
                preview.textEntryFields.get(0).sendKeys("5");
                preview.textEntryFields.get(1).sendKeys("5");
                preview.checkAnswer_button.click();//click on answer choice
                WebDriverUtil.waitTillVisibilityOfElement(preview.gotItWrong, 50);
                notificationMessageActual = preview.notificationMsg.getText().trim();
                ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
                notificationMessageExpected = "You got it wrong.";
                CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");
                preview.textEntryFields.get(1).clear();
                preview.textEntryFields.get(1).sendKeys("3");
                preview.checkAnswer_button.click();//click on answer choice
                Thread.sleep(3000);
                WebDriverUtil.waitTillVisibilityOfElement(preview.gotItPartiallyCorrect, 50);
                notificationMessageActual = preview.notificationMsg.getText().trim();
                ReportUtil.log("Attempting question", "Selected partially correct answer", "pass");
                notificationMessageExpected = "You got it partially correct.";
                CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for wrong answer", "Message  \"You got it partially correct.\" is displayed", "Message  \"You got it partially correct.\" is not displayed");
                preview.hint_button.click();//click on hint
                String hintContent = preview.hintContent.getText();
                CustomAssert.assertEquals(hintContent, "Hint Text", "Verify hint text","The hint data is appearing correct","The hint data is not appearing.");
                Thread.sleep(3000);
                preview.solution_Button.click();//click on solution
                String solutionContent = preview.solutionContent.getText();
                CustomAssert.assertEquals(solutionContent, "Solution Text", "verify Solution Text", "The solution data is appearing correct ", "The solution data is not appearing.");


            } catch (Exception e) {
                Assert.fail("Exception in TC advancedNumericWithAlgorithm of class CreateChapterAndTlo" + e);
            }
        }
    }

    @Test(priority=7,enabled = true)
    public void expressionEvaluatorWithAlgorithmic() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "9");
                new QuestionCreate().expressionEvaluatorWithAlgorithmic(9);//Create an Expression Evaluator question
                ReportUtil.log("Create Expression Evaluator With Algorithm Question", "Expression Evaluator With Algorithm Question created successfully", "pass");
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                tlo=preview.learning_objective_text.getText().trim();
                Thread.sleep(3000);
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                manageContent.preview_Button.click();//click on preview
                WebDriverUtil.switchToNewWindow();
                WebDriverUtil.waitTillVisibilityOfElement(preview.previewPage_HeaderLogo, 240);
                courseName = preview.previewCourseName.getText().trim();
                CustomAssert.assertEquals(courseName, "Biology", "Verify Course Name in preview page", "Course name is displayed in preview page","Course name is not displayed in preview page");

                previewQuestionText = preview.preview_questionName.getText().trim();
                CustomAssert.assertEquals(previewQuestionText, "1+2", "Verify Question Name in preview page", "Question name is displayed in preview page","Question name is not displayed in preview page");

                previewAssessmentName = preview.questionPreviewHeader.getText().trim();
                CustomAssert.assertEquals(previewAssessmentName, assessmentName, "Verify Assessment Name in preview page", "Assessment name is displayed in preview page","Assessment name is not displayed in preview page");

                actualTlos=preview.preview_tlos.getText().trim();
                CustomAssert.assertEquals(actualTlos, "• "+tlo, "Verify learning objective in preview page", "learning objective is displayed in preview page", "learning objective is not displayed in preview page");

                actualPointAvailable = preview.pointAvailable_label.getText().trim();
                CustomAssert.assertEquals(actualPointAvailable.trim(),"Points Available : 1", "Verify Points Available label in preview page", "Points Available label is displayed in preview page","Points Available label is not displayed in preview page");

                if(!(preview.preview_score.get(0).getText().trim().equals("95.0%")&& preview.preview_score.get(1).getText().trim().equals("Students got it correct"))){
                    CustomAssert.fail("Verify Score","score is not displayed in preview page");
                }
                preview.expressionEvaluator.click();
                new Click().clickByid("answer_math_edit");
                driver.findElement(By.className("wrs_focusElement")).sendKeys("3");
                driver.findElement(By.id("wiris-answer-container-save-choice1")).click();
                preview.checkAnswer_button.click();//click on answer choice
                notificationMessageActual = preview.notificationMsg.getText().trim();
                ReportUtil.log("Attempting question", "Selected right answer", "pass");
                notificationMessageExpected = "You got it right.";
                CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for right answer", "Message \"You got it right\" is displayed", "Message \"You got it right\" is not displayed");
                Thread.sleep(2000);
                preview.expressionEvaluator.click();
                new Click().clickByid("answer_math_edit");
                new QuestionCreate().enterValueInMathMLEditor("Square root", "5");
                preview.checkAnswer_button.click();//click on answer choice
                WebDriverUtil.waitTillVisibilityOfElement(preview.gotItWrong,50);
                notificationMessageActual = preview.notificationMsg.getText().trim();
                ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
                notificationMessageExpected = "You got it wrong.";
                CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");
                preview.hint_button.click();//click on hint
                String hintContent = preview.hintContent.getText();
                CustomAssert.assertEquals(hintContent, "Hint Text", "Verify hint text","The hint data is appearing correct","The hint data is not appearing.");
                Thread.sleep(3000);
                preview.solution_Button.click();//click on solution
                String solutionContent = preview.solutionContent.getText();
                CustomAssert.assertEquals(solutionContent, "Solution Text", "verify Solution Text", "The solution data is appearing correct ", "The solution data is not appearing.");

            } catch (Exception e) {
                Assert.fail("Exception in TC expressionEvaluatorWithAlgorithmic of class CreateChapterAndTlo",e);
            }
        }

    }

    @Test(priority=8,enabled = true)
    public void essayQuestionWithAudio() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                String questionText = ReadTestData.readDataByTagName("", "questiontext", "10");
                String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "10");
                new Assignment().create(10);//Create an Essay question
                ReportUtil.log("Create essay question with audio", "essay question with audio created successfully", "pass");
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                tlo=preview.learning_objective_text.getText().trim();
                Thread.sleep(3000);
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                manageContent.preview_Button.click();//click on preview
                WebDriverUtil.switchToNewWindow();
                WebDriverUtil.waitTillVisibilityOfElement(preview.previewPage_HeaderLogo, 240);
                courseName = preview.previewCourseName.getText().trim();
                CustomAssert.assertEquals(courseName, "Biology", "Verify Course Name in preview page", "Course name is displayed in preview page","Course name is not displayed in preview page");

                previewQuestionText = preview.audioPreview_questionName.getText().trim();
                previewQuestionTextExpected ="Essay "+questionText;

                CustomAssert.assertTrue(previewQuestionText.contains(previewQuestionTextExpected), "Verify Question Name in preview page", "Question name is displayed in preview page","Question name is not displayed in preview page");

                previewAssessmentName = preview.questionPreviewHeader.getText().trim();
                CustomAssert.assertEquals(previewAssessmentName, assessmentName, "Verify Assessment Name in preview page", "Assessment name is displayed in preview page","Assessment name is not displayed in preview page");

                actualTlos=preview.preview_tlos.getText().trim();
                CustomAssert.assertEquals(actualTlos, "• "+tlo, "Verify learning objective in preview page", "learning objective is displayed in preview page", "learning objective is not displayed in preview page");

                actualPointAvailable = preview.pointAvailable_label.getText().trim();
                CustomAssert.assertEquals(actualPointAvailable.trim(),"Points Available : 1", "Verify Points Available label in preview page", "Points Available label is displayed in preview page","Points Available label is not displayed in preview page");

                if(!(preview.preview_score.get(0).getText().trim().equals("95.0%")&& preview.preview_score.get(1).getText().trim().equals("Students got it correct"))){
                    CustomAssert.fail("Verify Score","score is not displayed in preview page");
                }
                Thread.sleep(5000);
                WebDriverUtil.waitTillVisibilityOfElement(preview.playButton, 50);
                preview.playButton.click(); //click on the play button
                String time="00:00";
                Thread.sleep(9000);
                String playTime=preview.playTime.getText().trim();
                System.out.println("playTime:" + playTime);
                CustomAssert.assertNotEquals(playTime,time,"Verify Play time","Time is increasing when you click on the play button","Time is not increasing when you click on the play button");
                preview.hint_button.click();//click on hint
                String hintContent = preview.hintContent.getText();
                CustomAssert.assertEquals(hintContent, "Hint Text", "Verify hint text","The hint data is appearing correct","The hint data is not appearing.");
                Thread.sleep(3000);
                preview.solution_Button.click();//click on solution
                String solutionContent = preview.solutionContent.getText();
                CustomAssert.assertEquals(solutionContent, "Solution Text", "verify Solution Text", "The solution data is appearing correct ", "The solution data is not appearing.");

                preview.mathEditor.click();
                preview.cancelButton.click();
                preview.language.click();


            } catch (Exception e) {
                Assert.fail("Exception in TC essayQuestionWithAudio of class CreateChapterAndTlo",e);
            }
        }

    }

    @Test(priority=9,enabled = true)
    public void writeBoardWithAudio() {
        {
            try {
                String questionText = ReadTestData.readDataByTagName("", "questiontext", "11");
                String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "11");
                new Assignment().create(11);//Create an writeBoard question
                ReportUtil.log("Create writeBoard question with audio", " writeBoard question with audio created successfully", "pass");
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                tlo=preview.learning_objective_text.getText().trim();
                Thread.sleep(3000);
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                manageContent.preview_Button.click();//click on preview
                WebDriverUtil.switchToNewWindow();
                WebDriverUtil.waitTillVisibilityOfElement(preview.previewPage_HeaderLogo, 240);
                courseName = preview.previewCourseName.getText().trim();
                CustomAssert.assertEquals(courseName, "Biology", "Verify Course Name in preview page", "Course name is displayed in preview page","Course name is not displayed in preview page");

                previewQuestionText = preview.audioPreview_questionName.getText().trim();
                previewQuestionTextExpected ="Write Board "+questionText;

                CustomAssert.assertTrue(previewQuestionText.contains(previewQuestionTextExpected), "Verify Question Name in preview page", "Question name is displayed in preview page","Question name is not displayed in preview page");

                previewAssessmentName = preview.questionPreviewHeader.getText().trim();
                CustomAssert.assertEquals(previewAssessmentName, assessmentName, "Verify Assessment Name in preview page", "Assessment name is displayed in preview page","Assessment name is not displayed in preview page");

                actualTlos=preview.preview_tlos.getText().trim();
                CustomAssert.assertEquals(actualTlos, "• "+tlo, "Verify learning objective in preview page", "learning objective is displayed in preview page", "learning objective is not displayed in preview page");

                actualPointAvailable = preview.pointAvailable_label.getText().trim();
                CustomAssert.assertEquals(actualPointAvailable.trim(),"Points Available : 1", "Verify Points Available label in preview page", "Points Available label is displayed in preview page","Points Available label is not displayed in preview page");

                if(!(preview.preview_score.get(0).getText().trim().equals("95.0%")&& preview.preview_score.get(1).getText().trim().equals("Students got it correct"))){
                    CustomAssert.fail("Verify Score","score is not displayed in preview page");
                }
                preview.hint_button.click();//click on hint
                String hintContent = preview.hintContent.getText();
                CustomAssert.assertEquals(hintContent, "Hint Text", "Verify hint text","The hint data is appearing correct","The hint data is not appearing.");
                Thread.sleep(3000);
                preview.solution_Button.click();//click on solution
                String solutionContent = preview.solutionContent.getText();
                CustomAssert.assertEquals(solutionContent, "Solution Text", "verify Solution Text", "The solution data is appearing correct ", "The solution data is not appearing.");

                Thread.sleep(5000);
                WebDriverUtil.waitTillVisibilityOfElement(preview.playButton,50);
                preview.playButton.click(); //click on the play button
                String time="00:00";
                Thread.sleep(9000);
                String playTime=preview.playTime.getText().trim();
                System.out.println("playTime:"+playTime);
                CustomAssert.assertNotEquals(playTime,time,"Verify Play time","Time is increasing when you click on the play button","Time is not increasing when you click on the play button");
                new WriteBoard().enterTextInWriteBoardFromCMS("text",11);


            } catch (Exception e) {
                Assert.fail("Exception in TC writeBoardWithAudio of class CreateChapterAndTlo",e);
            }
        }

    }

    @Test(priority=10,enabled = true)
    public void textEntryWithDropDownWithVideo() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                String questionText = ReadTestData.readDataByTagName("", "questiontext", "12");
                String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "12");
                new Assignment().create(12);//Create an TextEntryWithDropDown question
                ReportUtil.log("Create TextEntryWithDropDown question ", "TextEntryWithDropDown created successfully", "pass");
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                tlo=preview.learning_objective_text.getText().trim();
                Thread.sleep(3000);
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                manageContent.preview_Button.click();//click on preview
                WebDriverUtil.switchToNewWindow();
                WebDriverUtil.waitTillVisibilityOfElement(preview.previewPage_HeaderLogo, 240);
                courseName = preview.previewCourseName.getText().trim();
                CustomAssert.assertEquals(courseName, "Biology", "Verify Course Name in preview page", "Course name is displayed in preview page","Course name is not displayed in preview page");

                previewQuestionText = preview.preview_questionName.getText().trim();
                previewQuestionTextExpected ="Text Drop Down "+questionText;

                CustomAssert.assertTrue(previewQuestionText.contains(previewQuestionTextExpected), "Verify Question Name in preview page", "Question name is displayed in preview page","Question name is not displayed in preview page");

                previewAssessmentName = preview.questionPreviewHeader.getText().trim();
                CustomAssert.assertEquals(previewAssessmentName, assessmentName, "Verify Assessment Name in preview page", "Assessment name is displayed in preview page","Assessment name is not displayed in preview page");

                actualTlos=preview.preview_tlos.getText().trim();
                CustomAssert.assertEquals(actualTlos, "• "+tlo, "Verify learning objective in preview page", "learning objective is displayed in preview page", "learning objective is not displayed in preview page");

                actualPointAvailable = preview.pointAvailable_label.getText().trim();
                CustomAssert.assertEquals(actualPointAvailable.trim(),"Points Available : 1", "Verify Points Available label in preview page", "Points Available label is displayed in preview page","Points Available label is not displayed in preview page");

                if(!(preview.preview_score.get(0).getText().trim().equals("95.0%")&& preview.preview_score.get(1).getText().trim().equals("Students got it correct"))){
                    CustomAssert.fail("Verify Score","score is not displayed in preview page");
                }
                preview.textEntryDropDown.click();
                preview.checkAnswer_button.click();//click on answer choice
                new UIElement().waitAndFindElement(preview.notificationMsg);
                notificationMessageActual = preview.notificationMsg.getText().trim();
                ReportUtil.log("Attempting question", "Selected right answer", "pass");
                notificationMessageExpected = "You got it right.";
                CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for right answer", "Message \"You got it right\" is displayed", "Message \"You got it right\" is not displayed");

                Thread.sleep(2000);
                driver.findElement(By.xpath("(//select[@class='question-raw-content-dropdown'])[1]")).click();
                WebElement ele= driver.findElement(By.xpath("(//select[@class='question-raw-content-dropdown'])[1]"));
                Select sel = new Select(ele);
                sel.selectByIndex(2);
                preview.checkAnswer_button.click();//click on answer choice
                Thread.sleep(2000);
                WebDriverUtil.waitTillVisibilityOfElement(preview.gotItWrong,50);
                notificationMessageActual = preview.notificationMsg.getText().trim();
                ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
                notificationMessageExpected = "You got it wrong.";
                preview.hint_button.click();//click on hint
                String hintContent = preview.hintContent.getText();
                CustomAssert.assertEquals(hintContent, "Hint Text", "Verify hint text","The hint data is appearing correct","The hint data is not appearing.");
                Thread.sleep(3000);
                preview.solution_Button.click();//click on solution
                String solutionContent = preview.solutionContent.getText();
                CustomAssert.assertEquals(solutionContent, "Solution Text", "verify Solution Text", "The solution data is appearing correct ", "The solution data is not appearing.");

                CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");
                CustomAssert.assertTrue(preview.video.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");

            } catch (Exception e) {
                Assert.fail("Exception in TC textEntryWithDropDownWithVideo of class CreateChapterAndTlo",e);
            }
        }

    }

    @Test(priority=11,enabled = true)
    public void dragAndDropQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                String questionText = ReadTestData.readDataByTagName("", "questiontext", "13");
                String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "13");
                new Assignment().create(13);
                ReportUtil.log("Create DragAndDrop question ", "DragAndDrop created successfully", "pass");
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                tlo=preview.learning_objective_text.getText().trim();
                Thread.sleep(3000);
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                manageContent.preview_Button.click();//click on preview
                WebDriverUtil.switchToNewWindow();
                WebDriverUtil.waitTillVisibilityOfElement(preview.previewPage_HeaderLogo, 240);
                courseName = preview.previewCourseName.getText().trim();
                CustomAssert.assertEquals(courseName, "Biology", "Verify Course Name in preview page", "Course name is displayed in preview page","Course name is not displayed in preview page");

                previewQuestionText = preview.preview_questionName.getText().trim();
                previewQuestionTextExpected ="Drag and Drop " + questionText;

                CustomAssert.assertTrue(previewQuestionText.contains(previewQuestionTextExpected), "Verify Question Name in preview page", "Question name is displayed in preview page","Question name is not displayed in preview page");

                previewAssessmentName = preview.questionPreviewHeader.getText().trim();
                CustomAssert.assertEquals(previewAssessmentName, assessmentName, "Verify Assessment Name in preview page", "Assessment name is displayed in preview page","Assessment name is not displayed in preview page");

                WebDriverUtil.clickOnElementUsingJavascript(preview.performance_label);
                Thread.sleep(3000);
                actualTlos=preview.preview_tlos.getText().trim();
                CustomAssert.assertEquals(actualTlos, "• "+tlo, "Verify learning objective in preview page", "learning objective is displayed in preview page", "learning objective is not displayed in preview page");

                actualPointAvailable = preview.pointAvailable_label.getText().trim();
                CustomAssert.assertEquals(actualPointAvailable.trim(),"Points Available : 1", "Verify Points Available label in preview page", "Points Available label is displayed in preview page","Points Available label is not displayed in preview page");

                if(!(preview.preview_score.get(0).getText().trim().equals("95.0%")&& preview.preview_score.get(1).getText().trim().equals("Students got it correct"))){
                    CustomAssert.fail("Verify Score","score is not displayed in preview page");
                }

                List<WebElement> answerstodrag = preview.preview_draggable_answer;
                Actions ac = new Actions(driver);
                ac.clickAndHold(answerstodrag.get(0))
                        .moveToElement(preview.preview_answer_choice)
                        .release()
                        .build()
                        .perform();
                preview.checkAnswer_button.click();//click on answer choice
                new UIElement().waitAndFindElement(preview.notificationMsg);
                notificationMessageActual = preview.notificationMsg.getText().trim();
                ReportUtil.log("Attempting question", "Selected right answer", "pass");
                notificationMessageExpected = "You got it right.";
                CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for right answer", "Message \"You got it right\" is displayed", "Message \"You got it right\" is not displayed");

                ac.clickAndHold(answerstodrag.get(1))
                        .moveToElement(preview.preview_answer_choice)
                        .release()
                        .build()
                        .perform();
                preview.checkAnswer_button.click();//click on answer choice
                WebDriverUtil.waitTillVisibilityOfElement(preview.gotItWrong, 50);
                notificationMessageActual = preview.notificationMsg.getText().trim();
                ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
                notificationMessageExpected = "You got it wrong.";
                CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");
                preview.hint_button.click();//click on hint
                String hintContent = preview.hintContent.getText();
                CustomAssert.assertEquals(hintContent, "Hint Text", "Verify hint text","The hint data is appearing correct","The hint data is not appearing.");
                Thread.sleep(3000);
                preview.solution_Button.click();//click on solution
                String solutionContent = preview.solutionContent.getText();
                CustomAssert.assertEquals(solutionContent, "Solution Text", "verify Solution Text", "The solution data is appearing correct ", "The solution data is not appearing.");


            } catch (Exception e) {
                Assert.fail("Exception in TC dragAndDropQuestionTypes of class CreateChapterAndTlo" + e);
            }
        }

    }

    @Test(priority=12,enabled = true)
    public void matchTheFollowing() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                String questionText = ReadTestData.readDataByTagName("", "questiontext", "14");
                String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "14");
                new Assignment().create(14);
                ReportUtil.log("Create MatchTheFollowing question ", "MatchTheFollowing question created successfully", "pass");
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                tlo=preview.learning_objective_text.getText().trim();
                Thread.sleep(3000);
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                manageContent.preview_Button.click();//click on preview
                WebDriverUtil.switchToNewWindow();
                WebDriverUtil.waitTillVisibilityOfElement(preview.previewPage_HeaderLogo, 240);
                courseName = preview.previewCourseName.getText().trim();
                CustomAssert.assertEquals(courseName, "Biology", "Verify Course Name in preview page", "Course name is displayed in preview page","Course name is not displayed in preview page");

                previewQuestionText = preview.preview_questionName.getText().trim();
                previewQuestionTextExpected ="Match the Following " + questionText;

                CustomAssert.assertTrue(previewQuestionText.contains(previewQuestionTextExpected), "Verify Question Name in preview page", "Question name is displayed in preview page","Question name is not displayed in preview page");

                previewAssessmentName = preview.questionPreviewHeader.getText().trim();
                CustomAssert.assertEquals(previewAssessmentName, assessmentName, "Verify Assessment Name in preview page", "Assessment name is displayed in preview page","Assessment name is not displayed in preview page");

                WebDriverUtil.clickOnElementUsingJavascript(preview.performance_label);
                actualTlos=preview.preview_tlos.getText().trim();
                CustomAssert.assertEquals(actualTlos, "• "+tlo, "Verify learning objective in preview page", "learning objective is displayed in preview page", "learning objective is not displayed in preview page");

                actualPointAvailable = preview.pointAvailable_label.getText().trim();
                CustomAssert.assertEquals(actualPointAvailable.trim(),"Points Available : 1", "Verify Points Available label in preview page", "Points Available label is displayed in preview page","Points Available label is not displayed in preview page");

                if(!(preview.preview_score.get(0).getText().trim().equals("95.0%")&& preview.preview_score.get(1).getText().trim().equals("Students got it correct"))){
                    CustomAssert.fail("Verify Score","score is not displayed in preview page");
                }
                List<WebElement> answerSize=preview.matchTheFollowing_preview_draggable_answer;
                CustomAssert.assertEquals(answerSize.size(),7,"Verify distractor and new now added","Question is saved after added distractor and new Row","Question is not saved after distractor and new Row");
                List<WebElement> dragNDrop =preview.matchTheFollowing_preview_draggable_answer;
                Actions ac = new Actions(driver);
                ac.clickAndHold(dragNDrop.get(0))
                        .moveToElement(preview.matchTheFollowing_preview_draggable_choice.get(0))
                        .release()
                        .build()
                        .perform();
                preview.checkAnswer_button.click();//click on answer choice
                WebDriverUtil.waitTillVisibilityOfElement(preview.notificationMsg, 50);
                notificationMessageActual = preview.notificationMsg.getText().trim();
                ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
                notificationMessageExpected = "You got it wrong.";
                String notificationMessageExpected1="You got it partially correct.";
                if(!(notificationMessageActual.equals(notificationMessageExpected)||notificationMessageActual.equals(notificationMessageExpected1))){
                    CustomAssert.fail("Verify notification message","Message  \"You got it wrong\" or \"You got it partially correct.\" is not displayed\",");
                }
                preview.hint_button.click();//click on hint
                String hintContent = preview.hintContent.getText();
                CustomAssert.assertEquals(hintContent, "Hint Text", "Verify hint text","The hint data is appearing correct","The hint data is not appearing.");
                Thread.sleep(3000);
                preview.solution_Button.click();//click on solution
                String solutionContent = preview.solutionContent.getText();
                CustomAssert.assertEquals(solutionContent, "Solution Text", "verify Solution Text", "The solution data is appearing correct ", "The solution data is not appearing.");


            }
            catch (Exception e) {
                Assert.fail("Exception in TC matchTheFollowing of class CreateChapterAndTlo" + e);
            }
        }

    }

    @Test(priority=13,enabled = true)
    public void labelAnImageTextQuestionTypes() {
        {
            try {
                String questionText = ReadTestData.readDataByTagName("", "questiontext", "15");
                String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "15");

                new Assignment().create(15);
                ReportUtil.log("Create labelAnImage question ", "labelAnImage question created successfully", "pass");
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                tlo=preview.learning_objective_text.getText().trim();
                Thread.sleep(3000);
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                manageContent.preview_Button.click();//click on preview
                WebDriverUtil.switchToNewWindow();
                WebDriverUtil.waitTillVisibilityOfElement(preview.previewPage_HeaderLogo, 240);
                courseName = preview.previewCourseName.getText().trim();
                CustomAssert.assertEquals(courseName, "Biology", "Verify Course Name in preview page", "Course name is displayed in preview page","Course name is not displayed in preview page");
                notificationMessageActual = preview.preview_questionName.getText().trim();
                notificationMessageExpected = "Label An Image(Text) question text"+ questionText;
                CustomAssert.assertTrue(notificationMessageActual.contains(notificationMessageExpected), "Verify Question Name in preview page", "Question name is displayed in preview page","Question name is not displayed in preview page");

                previewAssessmentName = preview.questionPreviewHeader.getText().trim();
                CustomAssert.assertEquals(previewAssessmentName, assessmentName, "Verify Assessment Name in preview page", "Assessment name is displayed in preview page","Assessment name is not displayed in preview page");

                actualTlos=preview.preview_tlos.getText().trim();
                CustomAssert.assertEquals(actualTlos, "• "+tlo, "Verify learning objective in preview page", "learning objective is displayed in preview page", "learning objective is not displayed in preview page");

                actualPointAvailable = preview.pointAvailable_label.getText().trim();
                CustomAssert.assertEquals(actualPointAvailable.trim(),"Points Available : 1", "Verify Points Available label in preview page", "Points Available label is displayed in preview page","Points Available label is not displayed in preview page");

                if(!(preview.preview_score.get(0).getText().trim().equals("95.0%")&& preview.preview_score.get(1).getText().trim().equals("Students got it correct"))){
                    CustomAssert.fail("Verify Score","score is not displayed in preview page");
                }

                preview.labelAnImageText_answer.sendKeys("Answer 1");
                preview.checkAnswer_button.click();//click on answer choice
                new UIElement().waitAndFindElement(preview.notificationMsg);
                notificationMessageActual = preview.notificationMsg.getText().trim();
                ReportUtil.log("Attempting question", "Selected right answer", "pass");
                notificationMessageExpected = "You got it right.";
                CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for right answer", "Message \"You got it right\" is displayed", "Message \"You got it right\" is not displayed");

                preview.labelAnImageText_answer.clear();
                preview.labelAnImageText_answer.sendKeys("Answer 2");
                preview.checkAnswer_button.click();//click on answer choice
                WebDriverUtil.waitTillVisibilityOfElement(preview.gotItWrong,50);
                notificationMessageActual = preview.notificationMsg.getText().trim();
                ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
                notificationMessageExpected = "You got it wrong.";
                CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");
                preview.hint_button.click();//click on hint
                String hintContent = preview.hintContent.getText();
                CustomAssert.assertEquals(hintContent, "Hint Text", "Verify hint text","The hint data is appearing correct","The hint data is not appearing.");
                Thread.sleep(3000);
                preview.solution_Button.click();//click on solution
                String solutionContent = preview.solutionContent.getText();
                CustomAssert.assertEquals(solutionContent, "Solution Text", "verify Solution Text", "The solution data is appearing correct ", "The solution data is not appearing.");

            } catch (Exception e) {
                Assert.fail("Exception in TC labelAnImageTextQuestionTypes of class CreateChapterAndTlo",e);
            }
        }

    }

    @Test(priority=14,enabled = true)
    public void labelAnImageDropdown() {
        {
            try {
                String questionText = ReadTestData.readDataByTagName("", "questiontext", "16");
                String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "16");

                new Assignment().create(16);
                ReportUtil.log("Create labelAnImageDropdown question ", "labelAnImageDropdown question created successfully", "pass");
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                tlo=preview.learning_objective_text.getText().trim();
                Thread.sleep(3000);
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                manageContent.preview_Button.click();//click on preview
                WebDriverUtil.switchToNewWindow();
                WebDriverUtil.waitTillVisibilityOfElement(preview.previewPage_HeaderLogo, 240);
                courseName = preview.previewCourseName.getText().trim();
                CustomAssert.assertEquals(courseName, "Biology", "Verify Course Name in preview page", "Course name is displayed in preview page","Course name is not displayed in preview page");

                notificationMessageActual = preview.preview_questionName.getText().trim();
                notificationMessageExpected = "Label An Image(dropdown)"+ questionText;
                CustomAssert.assertTrue(notificationMessageActual.contains(notificationMessageExpected), "Verify Question Name in preview page", "Question name is displayed in preview page","Question name is not displayed in preview page");
                previewAssessmentName = preview.questionPreviewHeader.getText().trim();
                CustomAssert.assertEquals(previewAssessmentName, assessmentName, "Verify Assessment Name in preview page", "Assessment name is displayed in preview page","Assessment name is not displayed in preview page");

                actualTlos=preview.preview_tlos.getText().trim();
                CustomAssert.assertEquals(actualTlos, "• "+tlo, "Verify learning objective in preview page", "learning objective is displayed in preview page", "learning objective is not displayed in preview page");

                actualPointAvailable = preview.pointAvailable_label.getText().trim();
                CustomAssert.assertEquals(actualPointAvailable.trim(),"Points Available : 1", "Verify Points Available label in preview page", "Points Available label is displayed in preview page","Points Available label is not displayed in preview page");

                if(!(preview.preview_score.get(0).getText().trim().equals("95.0%")&& preview.preview_score.get(1).getText().trim().equals("Students got it correct"))){
                    CustomAssert.fail("Verify Score","score is not displayed in preview page");
                }

                WebDriverUtil.selectItemByIndex(preview.labelAnImageDropDown_answer.get(0), 1);
                Thread.sleep(2000);
                WebDriverUtil.selectItemByIndex(preview.labelAnImageDropDown_answer.get(1), 1);
                Thread.sleep(2000);
                WebDriverUtil.selectItemByIndex(preview.labelAnImageDropDown_answer.get(2), 1);
                preview.checkAnswer_button.click();//click on answer choice
                new UIElement().waitAndFindElement(preview.notificationMsg);
                notificationMessageActual = preview.notificationMsg.getText().trim();
                ReportUtil.log("Attempting question", "Selected right answer", "pass");
                notificationMessageExpected = "You got it right.";
                CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for right answer", "Message \"You got it right\" is displayed", "Message \"You got it right\" is not displayed");
                preview.labelAnImageDropDown_CorrectAnswer.get(0).click();
                Thread.sleep(1000);
                preview.labelAnImageDropDown_CorrectAnswer.get(0).click();
                Thread.sleep(1000);
                preview.labelAnImageDropDown_CorrectAnswer.get(0).click();
                Thread.sleep(1000);
                WebDriverUtil.selectItemByIndex(preview.labelAnImageDropDown_answer.get(0), 2);
                Thread.sleep(2000);
                WebDriverUtil.selectItemByIndex(preview.labelAnImageDropDown_answer.get(1),2);
                Thread.sleep(2000);
                WebDriverUtil.selectItemByIndex(preview.labelAnImageDropDown_answer.get(2),2);
                preview.checkAnswer_button.click();//click on answer choice
                WebDriverUtil.waitTillVisibilityOfElement(preview.gotItWrong,50);
                notificationMessageActual = preview.notificationMsg.getText().trim();
                ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
                notificationMessageExpected = "You got it wrong.";
                CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");
                preview.hint_button.click();//click on hint
                String hintContent = preview.hintContent.getText();
                CustomAssert.assertEquals(hintContent, "Hint Text", "Verify hint text","The hint data is appearing correct","The hint data is not appearing.");
                Thread.sleep(3000);
                preview.solution_Button.click();//click on solution
                String solutionContent = preview.solutionContent.getText();
                CustomAssert.assertEquals(solutionContent, "Solution Text", "verify Solution Text", "The solution data is appearing correct ", "The solution data is not appearing.");

            } catch (Exception e) {
                Assert.fail("Exception in TC labelAnImageDropdown of class CreateChapterAndTlo",e);
            }
        }
    }

    @Test(priority=15,enabled = true)
    public void multiPartQuestion() {
        {
            try {

                String questionText = ReadTestData.readDataByTagName("", "questiontext", "17");
                String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "17");
                new Assignment().create(17);
                ReportUtil.log("Create multiPart question ", "multiPart question created successfully", "pass");
                manageContent.preview_Button.click();//click on preview
                WebDriverUtil.switchToNewWindow();
                WebDriverUtil.waitTillVisibilityOfElement(preview.previewPage_HeaderLogo, 240);

                previewAssessmentName = preview.questionPreviewHeader.getText().trim();
                CustomAssert.assertEquals(previewAssessmentName, assessmentName, "Verify Assessment Name in preview page", "Assessment name is displayed in preview page","Assessment name is not displayed in preview page");

                actualTlos=preview.preview_tlos.getText().trim();
                if(actualTlos.equals(" ")){
                    CustomAssert.fail("Verify learning objective in preview page","learning objective is not displayed in preview page");
                }

                actualPointAvailable = preview.total_point.getText().trim();
                CustomAssert.assertEquals(actualPointAvailable.trim(),"Total Points: 2", "Verify Points Available label in preview page", "Points Available label is displayed in preview page","Points Available label is not displayed in preview page");

                if(!(preview.preview_score.get(0).getText().trim().equals("95.0%")&& preview.preview_score.get(1).getText().trim().equals("Students got it correct"))){
                    CustomAssert.fail("Verify Score","score is not displayed in preview page");
                }
                notificationMessageActual = preview.preview_questionName.getText().trim();
                CustomAssert.assertEquals(notificationMessageActual, questionText, "Verify Question Name in preview page", "Question name is displayed in preview page","Question name is not displayed in preview page");
                preview.trueFalseAnswerLabel.get(0).click();//click on option 'A'
                preview.checkAnswerButton_MPQ.get(0).click();//click on answer choice
                Thread.sleep(2000);
                notificationMessageActual = preview.mpq_notification.getText().trim();
                ReportUtil.log("Attempting question", "Selected right answer", "pass");
                notificationMessageExpected = "You got it right";
                CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for right answer", "Message \"You got it right\" is displayed", "Message \"You got it right\" is not displayed");
                Thread.sleep(2000);
                preview.trueFalseAnswerLabel.get(1).click();//click on option 'B'
                preview.checkAnswerButton_MPQ.get(0).click();//click on answer choice
                Thread.sleep(2000);
                WebDriverUtil.waitTillVisibilityOfElement(preview.mpq_notification,120);
                ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
                notificationMessageActual = preview.mpq_notification.getText().trim();
                notificationMessageExpected = "You got it wrong";
                CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");
                preview.multipart_hint.click();//click on hint
                String hintContent = preview.multipart_hintContent.getText();
                CustomAssert.assertEquals(hintContent, "Hint Text", "Verify hint text","The hint data is appearing correct","The hint data is not appearing.");
                Thread.sleep(3000);
                preview.multipart_solution.click();//click on solution
                String solutionContent = preview.multipart_solutionContent.getText();
                CustomAssert.assertEquals(solutionContent, "Solution Text", "verify Solution Text", "The solution data is appearing correct ", "The solution data is not appearing.");

            } catch (Exception e) {
                Assert.fail("Exception in TC multiPartQuestion of class CreateChapterAndTlo",e);
            }
        }
    }

    @Test(priority=17,enabled = true)
    public void dependentMultiPartQuestion() {
        {
            try {

                String questionText = ReadTestData.readDataByTagName("", "questiontext", "19");
                String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "19");
                new Assignment().create(19);
                ReportUtil.log("Create multiPart question ", "multiPart question created successfully", "pass");
                manageContent.preview_Button.click();//click on preview
                WebDriverUtil.switchToNewWindow();
                WebDriverUtil.waitTillVisibilityOfElement(preview.previewPage_HeaderLogo, 240);

                previewAssessmentName = preview.questionPreviewHeader.getText().trim();
                CustomAssert.assertEquals(previewAssessmentName, assessmentName, "Verify Assessment Name in preview page", "Assessment name is displayed in preview page","Assessment name is not displayed in preview page");

                actualTlos=preview.preview_tlos.getText().trim();
                if(actualTlos.equals(" ")){
                    CustomAssert.fail("Verify learning objective in preview page","learning objective is not displayed in preview page");
                }

                actualPointAvailable = preview.total_point.getText().trim();
                CustomAssert.assertEquals(actualPointAvailable.trim(),"Total Points: 2", "Verify Points Available label in preview page", "Points Available label is displayed in preview page","Points Available label is not displayed in preview page");

                if(!(preview.preview_score.get(0).getText().trim().equals("95.0%")&& preview.preview_score.get(1).getText().trim().equals("Students got it correct"))){
                    CustomAssert.fail("Verify Score","score is not displayed in preview page");
                }
                notificationMessageActual = preview.preview_questionName.getText().trim();
                CustomAssert.assertEquals(notificationMessageActual, questionText, "Verify Question Name in preview page", "Question name is displayed in preview page","Question name is not displayed in preview page");
                preview.trueFalseAnswerLabel.get(0).click();//click on option 'A'
                preview.checkAnswerButton_MPQ.get(0).click();//click on answer choice
                WebDriverUtil.waitTillVisibilityOfElement(preview.mpq_notification,120);
                notificationMessageActual = preview.mpq_notification.getText().trim();
                ReportUtil.log("Attempting question", "Selected right answer", "pass");
                notificationMessageExpected = "You got it right";
                CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for right answer", "Message \"You got it right\" is displayed", "Message \"You got it right\" is not displayed");
                Thread.sleep(2000);
                preview.trueFalseAnswerLabel.get(1).click();//click on option 'B'
                preview.checkAnswerButton_MPQ.get(0).click();//click on answer choice
                Thread.sleep(2000);
                ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
                notificationMessageActual = preview.mpq_notification.getText().trim();
                notificationMessageExpected = "You got it wrong";
                CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");
                preview.multipart_hint.click();//click on hint
                String hintContent = preview.multipart_hintContent.getText();
                CustomAssert.assertEquals(hintContent, "Hint Text", "Verify hint text","The hint data is appearing correct","The hint data is not appearing.");
                Thread.sleep(3000);
                preview.multipart_solution.click();//click on solution
                Thread.sleep(2000);
                String solutionContent = preview.multipart_solutionContent.getText();
                CustomAssert.assertEquals(solutionContent, "Solution Text", "verify Solution Text", "The solution data is appearing correct ", "The solution data is not appearing.");

            } catch (Exception e) {
                Assert.fail("Exception in TC dependentMultiPartQuestion of class CreateChapterAndTlo",e);
            }
        }
    }

    @Test(priority=16,enabled = true)
    public void audioQuestion() {
        {
            try {
                String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "18");
                new Assignment().create(18);
                ReportUtil.log("Create audio question ", "audio question created successfully", "pass");
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                tlo=preview.learning_objective_text.getText().trim();
                Thread.sleep(3000);
                WebDriverUtil.clickOnElementUsingJavascript(preview.learing_objectives_link);
                manageContent.preview_Button.click();//click on preview
                WebDriverUtil.switchToNewWindow();
                WebDriverUtil.waitTillVisibilityOfElement(preview.previewPage_HeaderLogo, 240);

                courseName = preview.previewCourseName.getText().trim();
                CustomAssert.assertEquals(courseName, "Biology", "Verify Course Name in preview page", "Course name is displayed in preview page","Course name is not displayed in preview page");

                previewAssessmentName = preview.questionPreviewHeader.getText().trim();
                CustomAssert.assertEquals(previewAssessmentName, assessmentName, "Verify Assessment Name in preview page", "Assessment name is displayed in preview page","Assessment name is not displayed in preview page");

                actualTlos=preview.preview_tlos.getText().trim();
                CustomAssert.assertEquals(actualTlos, "• "+tlo, "Verify learning objective in preview page", "learning objective is displayed in preview page", "learning objective is not displayed in preview page");

                actualPointAvailable = preview.pointAvailable_label.getText().trim();
                CustomAssert.assertEquals(actualPointAvailable.trim(),"Points Available : 1", "Verify Points Available label in preview page", "Points Available label is displayed in preview page","Points Available label is not displayed in preview page");

                if(!(preview.preview_score.get(0).getText().trim().equals("95.0%")&& preview.preview_score.get(1).getText().trim().equals("Students got it correct"))){
                    CustomAssert.fail("Verify Score","score is not displayed in preview page");
                }

                notificationMessageActual = preview.preview_questionName.getText().trim();
                CustomAssert.assertTrue(notificationMessageActual.contains("audio audioQuestionText"), "Verify Question Name in preview page", "Question name is displayed in preview page","Question name is not displayed in preview page");

                preview.hint_button.click();//click on hint
                String hintContent = preview.hintContent.getText();
                CustomAssert.assertEquals(hintContent, "Hint Text", "Verify hint text","The hint data is appearing correct","The hint data is not appearing.");
                Thread.sleep(3000);
                preview.solution_Button.click();//click on solution
                String solutionContent = preview.solutionContent.getText();
                CustomAssert.assertEquals(solutionContent, "Solution Text", "verify Solution Text", "The solution data is appearing correct ", "The solution data is not appearing.");


            } catch (Exception e) {
                Assert.fail("Exception in TC audioQuestion of class CreateChapterAndTlo",e);
            }
        }
    }

}
