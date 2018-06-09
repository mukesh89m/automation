package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT24.R2416;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CreateQuestionCMS;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.QuestionType;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

/**
 * Created by murthis on 14-12-2015.
 */
public class ComparisonWithStudentAnswer extends Driver {

   @Test
    public void comparisonWithStudentAnswer(){
        try {
            WebDriver driver=Driver.getWebDriver();
//                log("Description","This testcase validates Comparsion with student answer section under Express Evaluator question","info");
                new DirectLogin().CMSLogin();//log into CMS
                new SelectCourse().selectcourse();//Select a course
                new SelectCourse().selectChapterByIndex(0);//select 1st chapter
                new Assignment().selectParticularQuestionInCMS("Expression Evaluator", 10);

                // “Comparison with student answer” heading should be displayed below " Syntax settings.
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOf(manageContent.qInstance));

                Assert.assertEquals(manageContent.comparisonWithStudentAnswerHeader.isDisplayed(), true, "Comparison with student Answer header is displayed below the syntax settings");
//                log("Verify Comparison with student Answer header","Comparison with student Answer header is displayed below the syntax settings","pass");

                // Two radio options "Mathematically equal" and "Literally equal" should be available.
                Assert.assertEquals(manageContent.literallyEqualRadioLabel.isDisplayed(), true, "Literally equal radio button is displayed below the syntax settings");
//                log("Verify Literally equal radio button","Literally equal radio button is displayed below the syntax settings","pass");

                Assert.assertEquals(manageContent.mathematicallyEqualRadioLabel.isDisplayed(), true, "Mathematically equal radio button is displayed below the syntax settings");
//                log("Verify Mathematically equal radio button","Mathematically equal radio button is displayed below the syntax settings","pass");

                //"Mathematically equal" radio button should be selected by default.
                Assert.assertEquals(manageContent.mathematicallyEqualRadio.isSelected(), true, "Mathematically equal radio button is selected by default");

                //Click on "Literally equal" radio button.
                manageContent.literallyEqualRadio.click();

                //User should be able to switch between the radio buttons.
                Assert.assertEquals(manageContent.literallyEqualRadio.isSelected(), true, "User is unable to switch between the Mathematically equal radio button and Literally equal radio button.");

                //Make some changes in the question editing screen
                manageContent.question_EditBox.click();
                manageContent.question_EditBox.sendKeys("Testing entering value in the text box");

                manageContent.questionEditorCancelLink.click();

                //Navigate back to the same question
                new SelectCourse().selectChapterByIndex(0);//select 1st chapter
                new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOf(manageContent.listAssociatedContent.get(0)));

                //The changes done with "Comparison with student answer" should not be saved.
                String AssessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(15));
                WebElement CurrentAssessmentName;
                try {
                    CurrentAssessmentName = driver.findElement(By.xpath("//*[@id='collections-data-wrapper']//li[@id='assessment']/div[contains(.,'" + AssessmentName + "')]"));
                } catch (org.openqa.selenium.NoSuchElementException nse) {
                    CurrentAssessmentName = null;
                }

                Assert.assertEquals(CurrentAssessmentName == null, true, "The changes done with \"Comparison with student answer\" should not be saved");

            } catch (Throwable e) {
                Assert.fail("Exception in TC comparionWithStudentAnswer of class ComparionWithStudentAnswer", e);

            }
        }

    @Test
    public void saveAndPreviewMathematicallyEqual(){
        try{

            WebDriver driver=Driver.getWebDriver();
//            1. Login as user CMS.
//            2. Select any course.
//            3. Under any chapter. Create a normal assessment->Static/Assignment/Diagnostic/Practice.
//            4. Select Expression Evaluator question type.
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Expression Evaluator", 15);

            //wait till the element appears.
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOf(manageContent.qInstance));

            //5.Create the question such that the answer is like for ex: sqrt(25)
            manageContent.question_EditBox.click();
            new UIElement().waitAndFindElement(manageContent.redactorToolbar_SquareRoot);

            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",manageContent.redactorToolbar_SquareRoot);
//          manageContent.redactorToolbar_SquareRoot.click();

            //click on square root
            manageContent.wrsDialog_ToolBar_SquareRoot.click();

            manageContent.wrsEdit.sendKeys("25");

            manageContent.wrsSave.click();

            manageContent.question_EditBox.sendKeys("?");

            //6. Select "Mathematically equal" radio button.
            manageContent.mathematicallyEqualRadio.click();

//            //Enter the correct answer
            manageContent.correctAnswerEdit.click();
//
            manageContent.wrsAnswerEdit.sendKeys("5");
            manageContent.wrsAnswerSave.click();

            //click on accept answer
            manageContent.mapleAcceptAnswer.click();

            //7. Save the question.
            manageContent.save_Button.click();

            //1. The question should be saved successfully with the same created parameters.
            Assert.assertEquals(manageContent.preview_Button.isDisplayed(),true,"The question is not saved");

            //click on preview button
            manageContent.preview_Button.click();

            //Switch to preview window
            Set<String> windowHandles=driver.getWindowHandles();

            for(String str:windowHandles){
                driver.switchTo().window(str);
            }

            //Click on answer edit box to open the dialog window.
            Preview questionPreview = PageFactory.initElements(driver, Preview.class);

            //9. Attempt the question.
            //10. Pass mathematically equal answer. for ex: 5
            questionPreview.answerEdit.click();

            questionPreview.wrsAnswerEdit.sendKeys("5");

            questionPreview.editAnswerMathDialog_Save.click();

            //11. Click on "Check answer" button.
            questionPreview.checkAnswer_button.click();

            //"You got it right" message should be displayed.
            Assert.assertEquals(questionPreview.notificationMsg.getText(),"You got it right.","You got it right message is not displayed");

//            12. Attempt the question.
//            13. Pass literally equal answer. for ex: sqrt(25)
//            14. Click on "Check answer" button.

            questionPreview.answerEdit.click();

           //clear the previous answer
            questionPreview.editAnswerMathDialog_Delete.click();

            questionPreview.editAnswerMathDialog_ToolBar_SquareRoot.click();

            questionPreview.wrsAnswerEdit.sendKeys("25");

            questionPreview.editAnswerMathDialog_Save.click();

            //11. Click on "Check answer" button.
            questionPreview.checkAnswer_button.click();

            //"You got it right" message should be displayed.
            Assert.assertEquals(questionPreview.notificationMsg.getText(),"You got it right.","You got it right message is not displayed");

            driver.close();

        }
        catch(Exception e) {
                Assert.fail("Exception in TC comparionWithStudentAnswer of class saveAndPreviewMathematicallyEqual", e);
        }
    }

    @Test
    public void saveAndPreviewLiterallyEqual() {

        try {
            WebDriver driver=Driver.getWebDriver();
//            1. Login as user CMS.
//            2. Select any course.
//            3. Under any chapter. Create a normal assessment->Static/Assignment/Diagnostic/Practice.
//            4. Select Expression Evaluator question type.
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Expression Evaluator", 15);

            //wait till the element appears.
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOf(manageContent.qInstance));

            //5.Create the question such that the answer is like for ex: sqrt(25)
            manageContent.question_EditBox.click();
            new UIElement().waitAndFindElement(manageContent.redactorToolbar_SquareRoot);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", manageContent.redactorToolbar_SquareRoot);
//          manageContent.redactorToolbar_SquareRoot.click();

            //click on square root
            manageContent.wrsDialog_ToolBar_SquareRoot.click();

            manageContent.wrsEdit.sendKeys("25");

            manageContent.wrsSave.click();

            manageContent.question_EditBox.sendKeys("?");

            //6. Select "Literally equal" radio button.
            manageContent.literallyEqualRadio.click();

//            //Enter the correct answer
            manageContent.correctAnswerEdit.click();
//
            manageContent.wrsAnswerEdit.sendKeys("5");
            manageContent.wrsAnswerSave.click();

            //click on accept answer
            manageContent.mapleAcceptAnswer.click();

            //7. Save the question.
            manageContent.save_Button.click();

            //1. The question should be saved successfully with the same created parameters.
            Assert.assertEquals(manageContent.preview_Button.isDisplayed(), true, "The question is not saved");

            //click on preview button
            manageContent.preview_Button.click();

            //Switch to preview window
            Set<String> windowHandles = driver.getWindowHandles();

            for (String str : windowHandles) {
                driver.switchTo().window(str);
            }

            //Click on answer edit box to open the dialog window.
            Preview questionPreview = PageFactory.initElements(driver, Preview.class);

            //9. Attempt the question.
            //10. Pass mathematically equal answer. for ex: 5
            new WebDriverWait(driver, 100).until(ExpectedConditions.visibilityOf(questionPreview.answerEdit));

            questionPreview.answerEdit.click();

            questionPreview.wrsAnswerEdit.sendKeys("5");

            questionPreview.editAnswerMathDialog_Save.click();

            //11. Click on "Check answer" button.
            questionPreview.checkAnswer_button.click();

            //"You got it right" message should be displayed.
            Assert.assertEquals(questionPreview.notificationMsg.getText(), "You got it right.", "You got it right message is not displayed");

//            12. Attempt the question.
//            13. Pass literally equal answer. for ex: sqrt(25)
//            14. Click on "Check answer" button.

            questionPreview.answerEdit.click();

            //clear the previous answer
            questionPreview.editAnswerMathDialog_Delete.click();

//            questionPreview.editAnswerMathDialog_ToolBar_SquareRoot.click();

            questionPreview.wrsAnswerEdit.sendKeys("5");

            questionPreview.editAnswerMathDialog_Save.click();

            //11. Click on "Check answer" button.
            questionPreview.checkAnswer_button.click();

            //"You got it right" message should be displayed.
            Assert.assertEquals(questionPreview.notificationMsg.getText(), "You got it right.", "You got it right message is not displayed");

            driver.close();

        } catch (Exception e) {
            Assert.fail("Exception in TC saveAndPreviewLiterallyEqual  of class comparisonWithStudentAnswer", e);
        }
    }

    @Test
    public void expressionEvaluatorAsPartOfMPQ(){
            try{
                WebDriver driver=Driver.getWebDriver();
    //        1. Login as user CMS.
    //        2. Select any course.
    //        3. Under any chapter. Create a normal assessment->Static/Assignment/Diagnostic/Practice.
    //        4. Select MPQ question type->Save the stem->Select an expression evaluator question type as a part of multi parts.

            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Multi Part", 23);

            //wait till the element appears.
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOf(manageContent.qInstance));

            //manageContent.multiPartQuestion_EditBox.click();
            manageContent.multiPartQuestion_EditBox.sendKeys("This a sample multi part question for testing");
            manageContent.save_Button.click();

            CreateQuestionCMS cqCMS=PageFactory.initElements(driver,CreateQuestionCMS.class);
            new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(cqCMS.AddNewQuestionPart));

            cqCMS.AddNewQuestionPart.click();

            new QuestionType(driver).select("Expression Evaluator");
                new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(manageContent.question_EditBox));

            Assert.assertEquals(manageContent.comparisonWithStudentAnswerHeader.isDisplayed(), true, "Comparison with student Answer header is displayed below the syntax settings");

            // Two radio options "Mathematically equal" and "Literally equal" should be available.
            Assert.assertEquals(manageContent.literallyEqualRadioLabel.isDisplayed(), true, "Literally equal radio button is displayed below the syntax settings");
            Assert.assertEquals(manageContent.mathematicallyEqualRadioLabel.isDisplayed(), true, "Mathematically equal radio button is displayed below the syntax settings");

            //"Mathematically equal" radio button should be selected by default.
            Assert.assertEquals(manageContent.mathematicallyEqualRadio.isSelected(), true, "Mathematically equal radio button is selected by default");

            //Click on "Literally equal" radio button.
            manageContent.literallyEqualRadio.click();

            //User should be able to switch between the radio buttons.
            Assert.assertEquals(manageContent.literallyEqualRadio.isSelected(), true, "User should be able to switch between the radio buttons.");

            //Make some changes in the question editing screen
//            manageContent.question_EditBox.click();
            manageContent.question_EditBox.sendKeys("Testing entering value in the text box");

            //Click on cancel in the notification dialog for save changes?
            manageContent.questionEditorCancelLink.click();
                new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOf(manageContent.notificationDialog));
            manageContent.notificationSaveChangesCancel.click();
                new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOf(manageContent.notificationQuestionDelete));
            manageContent.notificationQuestionDelete.click();
                new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOf(manageContent.notificationDialog));
            manageContent.notificationSaveChangesYes.click();
//            manageContent.questionEditorCancelLink.click();
//            manageContent.questionEditorCancelLink.click();
//            manageContent.questionEditorCancelLink.click();
//            manageContent.questionEditorCancelLink.click();
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",manageContent.questionEditorCancelLink);
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",manageContent.questionEditorCancelLink);
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",manageContent.questionEditorCancelLink);
            new WebDriverWait(driver, 90).until(ExpectedConditions.elementToBeClickable(manageContent.courseStructureTree));


            //Navigate back to the same question
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOf(manageContent.listAssociatedContent.get(0)));

            //The changes done with "Comparison with student answer" should not be saved.
            String AssessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(23));
            WebElement CurrentAssessmentName;
            try {
                CurrentAssessmentName = driver.findElement(By.xpath("//*[@id='collections-data-wrapper']//li[@id='assessment']/div[contains(.,'" + AssessmentName + "')]"));
            } catch (org.openqa.selenium.NoSuchElementException nse) {
                CurrentAssessmentName = null;
            }

            Assert.assertEquals(CurrentAssessmentName == null, true, "The changes done with \"Comparison with student answer\" should not be saved");

        } catch (Exception e) {
            Assert.fail("Exception in TC saveAndPreviewLiterallyEqual  of class comparisonWithStudentAnswer", e);
        }

    }
  }
