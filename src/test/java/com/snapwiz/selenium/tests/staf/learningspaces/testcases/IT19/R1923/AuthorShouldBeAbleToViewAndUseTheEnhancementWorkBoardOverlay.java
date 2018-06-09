package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R1923;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Summary;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by priyanka on 2/25/2015.
 */
public class AuthorShouldBeAbleToViewAndUseTheEnhancementWorkBoardOverlay extends Driver {
    @Test(priority = 1, enabled = true)
    public void authorShouldBeAbleToViewAndUseTheEnhancementWorkBoardOverlay() {

        try {
            //tc row no 9
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Summary summary = PageFactory.initElements(driver, Summary.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectcourse();//select course biology
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            manageContent.createPractice.click();//click on create practice
            manageContent.createRegularAssessment.click();//click on regular assessment
            Thread.sleep(1000);
            manageContent.trueFalseQuestion.click();// click on truefalse question
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("content-label")));
            String workBoard = manageContent.workBoard.getText();
            Assert.assertEquals(workBoard, "Allow student to use workboard", "Option “Allow student to use writeboard” is not appear renamed as “Allow student to use workboard”.");
            new OpenSearchPage().openSearchPage();//open search page
            new OpenSearchPage().searchquestion("Assessment");//search question
            Actions action = new Actions(driver);
            action.moveToElement(summary.reviewQuestion).build().perform();
            action.moveToElement(summary.quickReviewExpand).click().build().perform();//click on quick preview
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='associated-content-details-label display-write-board']")));
            String workBoard1 = preview.workBoard.getText();
            Assert.assertEquals(workBoard1, "Allow student to use workboard:", "Option “Allow student to use writeboard” is not appear renamed as “Allow student to use workboard”.");
            new Click().clickbylistxpath("//div[@id = 'question-check-box-div']//label", 0);
            preview.launchReview.click();//click on launch review
            String workBoard2 = preview.workBoard.getText();
            Assert.assertEquals(workBoard2, "Allow student to use workboard:", "Option “Allow student to use writeboard” is not appear renamed as “Allow student to use workboard”.");


        } catch (Exception e) {
            Assert.fail("Exception in test case authorShouldBeAbleToViewAndUseTheEnhancementWorkBoardOverlay of class AuthorShouldBeAbleToViewAndUseTheEnhancementWorkboardOverlay", e);
        }


    }

    @Test(priority = 2, enabled = true)
    public void previewOfTrueFalseQuestionWithWorkBoardEnabled() {

        try {
            //tc row no 12
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            new Assignment().create(12);//Create an assignment
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("show-your-work-label")));
            String workBoard = preview.plusWorkBoard.getText();
            Assert.assertEquals(workBoard, "+ Workboard", "It is not take to the question preview page.");
            preview.plusWorkBoard.click();//click on +workboard
            String crossIcon = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPanel = preview.toolControl.isDisplayed();
            if (toolPanel == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 12);
            boolean workBoard1 = preview.plusWorkBoard.isDisplayed();
            if (workBoard1 == true) {
                Assert.fail("It is showing '+Work board' button when pop_up is shown.");
            }
            String crossIcon1 = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon1, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");
            preview.crossIcon.click();//click on 'x'
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);
            preview.textEntryPopUp.click();//click in middle
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            preview.trueFalseAnswerLabel.get(0).click();//click on option 'A'
            preview.checkAnswer_button.click();//click on answer choice
            String notificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(notificationMessage, "You got it right.", " It is not showing the message based on the selected option");
            preview.hint_button.click();//click on hint
            String hintContent = preview.hintContent.getText();
            Assert.assertEquals(hintContent, "Hint Text", "The hint data is not appearing.");
            Thread.sleep(3000);
            preview.solution_Button.click();//click on solution
            String solutionContent = preview.solutionContent.getText();
            Assert.assertEquals(solutionContent, "Solution Text", "The solution data is not appearing.");
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPane2 = preview.toolControl.isDisplayed();
            if (toolPane2 == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            preview.textEntryPopUp.click();//click in middle


        } catch (Exception e) {
            Assert.fail("Exception in test case previewOfTrueFalseQuestionWithWorkboardEnabled of class AuthorShouldBeAbleToViewAndUseTheEnhancementWorkboardOverlay", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void previewOfMultipleChoiceQuestionWithWorkBoardEnabled() {

        try {
            //tc row no 28
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            new Assignment().create(28);//Create an assignment
            new Assignment().addQuestions(28, "multiplechoice", "");
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("show-your-work-label")));
            String workBoard = preview.plusWorkBoard.getText();
            Assert.assertEquals(workBoard, "+ Workboard", "It is not take to the question preview page.");
            preview.plusWorkBoard.click();//click on +workboard
            String crossIcon = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPanel = preview.toolControl.isDisplayed();
            if (toolPanel == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 28);
            boolean workBoard1 = preview.plusWorkBoard.isDisplayed();
            if (workBoard1 == true) {
                Assert.fail("It is showing '+Work board' button when pop_up is shown.");
            }
            String crossIcon1 = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon1, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");
            preview.crossIcon.click();//click on 'x'
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);
            preview.textEntryPopUp.click();//click in middle
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            preview.selectMultipleChoice.get(0).click();//click on option 'A'
            preview.checkAnswer_button.click();//click on answer choice
            String notificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(notificationMessage, "You got it right.", " It is not showing the message based on the selected option");
            preview.hint_button.click();//click on hint
            String hintContent = preview.hintContent.getText();
            Assert.assertEquals(hintContent, "Hint Text", "The hint data is not appearing.");
            Thread.sleep(3000);
            preview.solution_Button.click();//click on solution
            String solutionContent = preview.solutionContent.getText();
            Assert.assertEquals(solutionContent, "Solution Text", "The solution data is not appearing.");
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPane2 = preview.toolControl.isDisplayed();
            if (toolPane2 == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            preview.textEntryPopUp.click();//click in middle


        } catch (Exception e) {
            Assert.fail("Exception in test case previewOfMultipleChoiceQuestionWithWorkBoardEnabled of class AuthorShouldBeAbleToViewAndUseTheEnhancementWorkboardOverlay", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void previewOfMultipleSelectionQuestionWithWorkBoardEnabled() {

        try {
            //tc row no 44
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            new Assignment().create(44);//Create an assignment
            new Assignment().addQuestions(44, "multipleselection", "");
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("show-your-work-label")));
            String workBoard = preview.plusWorkBoard.getText();
            Assert.assertEquals(workBoard, "+ Workboard", "It is not take to the question preview page.");
            preview.plusWorkBoard.click();//click on +workboard
            String crossIcon = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPanel = preview.toolControl.isDisplayed();
            if (toolPanel == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 44);
            boolean workBoard1 = preview.plusWorkBoard.isDisplayed();
            if (workBoard1 == true) {
                Assert.fail("It is showing '+Work board' button when pop_up is shown.");
            }
            String crossIcon1 = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon1, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");
            preview.crossIcon.click();//click on 'x'
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);
            preview.textEntryPopUp.click();//click in middle
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            preview.multipleSelection.get(0).click();//click on option 'A'
            preview.checkAnswer_button.click();//click on answer choice
            String notificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(notificationMessage, "You got it partially correct.", " It is not showing the message based on the selected option");
            preview.hint_button.click();//click on hint
            String hintContent = preview.hintContent.getText();
            Assert.assertEquals(hintContent, "Hint Text", "The hint data is not appearing.");
            Thread.sleep(3000);
            preview.solution_Button.click();//click on solution
            String solutionContent = preview.solutionContent.getText();
            Assert.assertEquals(solutionContent, "Solution Text", "The solution data is not appearing.");
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPane2 = preview.toolControl.isDisplayed();
            if (toolPane2 == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            preview.textEntryPopUp.click();//click in middle


        } catch (Exception e) {
            Assert.fail("Exception in test case previewOfMultipleSelectionQuestionWithWorkBoardEnabled of class AuthorShouldBeAbleToViewAndUseTheEnhancementWorkboardOverlay", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void previewOfTextEntryQuestionWithWorkBoardEnabled() {

        try {
            //tc row no 60
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            new Assignment().create(60);//Create an assignment
            new Assignment().addQuestions(60, "textentry", "");
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("show-your-work-label")));
            String workBoard = preview.plusWorkBoard.getText();
            Assert.assertEquals(workBoard, "+ Workboard", "It is not take to the question preview page.");
            preview.plusWorkBoard.click();//click on +workboard
            String crossIcon = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPanel = preview.toolControl.isDisplayed();
            if (toolPanel == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 60);
            boolean workBoard1 = preview.plusWorkBoard.isDisplayed();
            if (workBoard1 == true) {
                Assert.fail("It is showing '+Work board' button when pop_up is shown.");
            }
            String crossIcon1 = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon1, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");
            preview.crossIcon.click();//click on 'x'
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);
            preview.textEntryPopUp.click();//click in middle
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            preview.textEntryField.sendKeys("text");
            preview.checkAnswer_button.click();//click on answer choice
            String notificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(notificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");
            preview.hint_button.click();//click on hint
            String hintContent = preview.hintContent.getText();
            Assert.assertEquals(hintContent, "Hint Text", "The hint data is not appearing.");
            Thread.sleep(3000);
            preview.solution_Button.click();//click on solution
            String solutionContent = preview.solutionContent.getText();
            Assert.assertEquals(solutionContent, "Solution Text", "The solution data is not appearing.");
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPane2 = preview.toolControl.isDisplayed();
            if (toolPane2 == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            preview.textEntryPopUp.click();//click in middle


        } catch (Exception e) {
            Assert.fail("Exception in test case previewOfTextEntryQuestionWithWorkBoardEnabled of class AuthorShouldBeAbleToViewAndUseTheEnhancementWorkboardOverlay", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void previewOfTextEntryDropDownQuestionWithWorkBoardEnabled() {

        try {
            //tc row no 76
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            new Assignment().create(76);//Create an assignment
            new Assignment().addQuestions(76, "textdropdown", "");
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("show-your-work-label")));
            String workBoard = preview.plusWorkBoard.getText();
            Assert.assertEquals(workBoard, "+ Workboard", "It is not take to the question preview page.");
            preview.plusWorkBoard.click();//click on +workboard
            String crossIcon = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPanel = preview.toolControl.isDisplayed();
            if (toolPanel == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 76);
            boolean workBoard1 = preview.plusWorkBoard.isDisplayed();
            if (workBoard1 == true) {
                Assert.fail("It is showing '+Work board' button when pop_up is shown.");
            }
            String crossIcon1 = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon1, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");
            preview.crossIcon.click();//click on 'x'
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);
            preview.textEntryPopUp.click();//click in middle
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            preview.textEntryDropDown.click();
            preview.checkAnswer_button.click();//click on answer choice
            String notificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(notificationMessage, "You got it right.", " It is not showing the message based on the selected option");
            preview.hint_button.click();//click on hint
            String hintContent = preview.hintContent.getText();
            Assert.assertEquals(hintContent, "Hint Text", "The hint data is not appearing.");
            Thread.sleep(3000);
            preview.solution_Button.click();//click on solution
            String solutionContent = preview.solutionContent.getText();
            Assert.assertEquals(solutionContent, "Solution Text", "The solution data is not appearing.");
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPane2 = preview.toolControl.isDisplayed();
            if (toolPane2 == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            preview.textEntryPopUp.click();//click in middle


        } catch (Exception e) {
            Assert.fail("Exception in test case previewOfTextEntryDropDownQuestionWithWorkBoardEnabled of class AuthorShouldBeAbleToViewAndUseTheEnhancementWorkboardOverlay", e);
        }
    }

    @Test(priority = 7, enabled = true)
    public void previewOfNumericEntryWithUnitQuestionWithWorkBoardEnabled() {

        try {
            //tc row no 92
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            new Assignment().create(92);//Create an assignment
            new Assignment().addQuestions(92, "numericentrywithunits", "");
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("show-your-work-label")));
            String workBoard = preview.plusWorkBoard.getText();
            Assert.assertEquals(workBoard, "+ Workboard", "It is not take to the question preview page.");
            preview.plusWorkBoard.click();//click on +workboard
            String crossIcon = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPanel = preview.toolControl.isDisplayed();
            if (toolPanel == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 92);
            boolean workBoard1 = preview.plusWorkBoard.isDisplayed();
            if (workBoard1 == true) {
                Assert.fail("It is showing '+Work board' button when pop_up is shown.");
            }
            String crossIcon1 = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon1, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");
            preview.crossIcon.click();//click on 'x'
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);
            preview.textEntryPopUp.click();//click in middle
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            preview.numericEntryDropDown.click();
            preview.checkAnswer_button.click();//click on answer choice
            String notificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(notificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");
            preview.hint_button.click();//click on hint
            String hintContent = preview.hintContent.getText();
            Assert.assertEquals(hintContent, "Hint Text", "The hint data is not appearing.");
            Thread.sleep(3000);
            preview.solution_Button.click();//click on solution
            String solutionContent = preview.solutionContent.getText();
            Assert.assertEquals(solutionContent, "Solution Text", "The solution data is not appearing.");
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPane2 = preview.toolControl.isDisplayed();
            if (toolPane2 == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            preview.textEntryPopUp.click();//click in middle


        } catch (Exception e) {
            Assert.fail("Exception in test case previewOfNumericEntryWithUnitQuestionWithWorkBoardEnabled of class AuthorShouldBeAbleToViewAndUseTheEnhancementWorkboardOverlay", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void previewOfAdvancedNumericQuestionWithWorkBoardEnabled() {

        try {
            //tc row no 108
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            new Assignment().create(108);//Create an assignment
            new Assignment().addQuestions(108, "advancednumeric", "");
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("show-your-work-label")));
            String workBoard = preview.plusWorkBoard.getText();
            Assert.assertEquals(workBoard, "+ Workboard", "It is not take to the question preview page.");
            preview.plusWorkBoard.click();//click on +workboard
            String crossIcon = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPanel = preview.toolControl.isDisplayed();
            if (toolPanel == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 108);
            boolean workBoard1 = preview.plusWorkBoard.isDisplayed();
            if (workBoard1 == true) {
                Assert.fail("It is showing '+Work board' button when pop_up is shown.");
            }
            String crossIcon1 = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon1, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");
            preview.crossIcon.click();//click on 'x'
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);
            preview.textEntryPopUp.click();//click in middle
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            preview.textEntryField.sendKeys("text");
            preview.checkAnswer_button.click();//click on answer choice
            String notificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(notificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");
            preview.hint_button.click();//click on hint
            String hintContent = preview.hintContent.getText();
            Assert.assertEquals(hintContent, "Hint Text", "The hint data is not appearing.");
            Thread.sleep(3000);
            preview.solution_Button.click();//click on solution
            String solutionContent = preview.solutionContent.getText();
            Assert.assertEquals(solutionContent, "Solution Text", "The solution data is not appearing.");
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPane2 = preview.toolControl.isDisplayed();
            if (toolPane2 == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            preview.textEntryPopUp.click();//click in middle


        } catch (Exception e) {
            Assert.fail("Exception in test case previewOfAdvancedNumericQuestionWithWorkBoardEnabled of class AuthorShouldBeAbleToViewAndUseTheEnhancementWorkboardOverlay", e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void previewOfExpressionEvaluatorQuestionWithWorkBoardEnabled() {

        try {
            //tc row no 124
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            new Assignment().create(124);//Create an assignment
            new Assignment().addQuestions(124, "expressionevaluator", "");
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("show-your-work-label")));
            String workBoard = preview.plusWorkBoard.getText();
            Assert.assertEquals(workBoard, "+ Workboard", "It is not take to the question preview page.");
            preview.plusWorkBoard.click();//click on +workboard
            String crossIcon = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPanel = preview.toolControl.isDisplayed();
            if (toolPanel == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 124);
            boolean workBoard1 = preview.plusWorkBoard.isDisplayed();
            if (workBoard1 == true) {
                Assert.fail("It is showing '+Work board' button when pop_up is shown.");
            }
            String crossIcon1 = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon1, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");
            preview.crossIcon.click();//click on 'x'
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);
            preview.textEntryPopUp.click();//click in middle
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            preview.expressionEvaluator.click();
            new Click().clickByid("answer_math_edit");
            new QuestionCreate().enterValueInMathMLEditor("Square root", "5");
            preview.checkAnswer_button.click();//click on answer choice
            String notificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(notificationMessage, "You got it right.", " It is not showing the message based on the selected option");
            preview.hint_button.click();//click on hint
            String hintContent = preview.hintContent.getText();
            Assert.assertEquals(hintContent, "Hint Text", "The hint data is not appearing.");
            Thread.sleep(3000);
            preview.solution_Button.click();//click on solution
            String solutionContent = preview.solutionContent.getText();
            Assert.assertEquals(solutionContent, "Solution Text", "The solution data is not appearing.");
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPane2 = preview.toolControl.isDisplayed();
            if (toolPane2 == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            preview.textEntryPopUp.click();//click in middle


        } catch (Exception e) {
            Assert.fail("Exception in test case previewOfExpressionEvaluatorQuestionWithWorkBoardEnabled of class AuthorShouldBeAbleToViewAndUseTheEnhancementWorkboardOverlay", e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void previewOfEssayQuestionWithWorkBoardEnabled() {

        try {
            //tc row no 140
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            new Assignment().create(140);//Create an assignment
            new Assignment().addQuestions(140, "essay", "");
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("show-your-work-label")));
            String workBoard = preview.plusWorkBoard.getText();
            Assert.assertEquals(workBoard, "+ Workboard", "It is not take to the question preview page.");
            preview.plusWorkBoard.click();//click on +workboard
            String crossIcon = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPanel = preview.toolControl.isDisplayed();
            if (toolPanel == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 140);
            boolean workBoard1 = preview.plusWorkBoard.isDisplayed();
            if (workBoard1 == true) {
                Assert.fail("It is showing '+Work board' button when pop_up is shown.");
            }
            String crossIcon1 = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon1, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");
            preview.crossIcon.click();//click on 'x'
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);
            preview.textEntryPopUp.click();//click in middle
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            preview.essayTextBox.sendKeys("text");
            preview.hint_button.click();//click on hint
            String hintContent = preview.hintContent.getText();
            Assert.assertEquals(hintContent, "Hint Text", "The hint data is not appearing.");
            Thread.sleep(3000);
            preview.solution_Button.click();//click on solution
            String solutionContent = preview.solutionContent.getText();
            Assert.assertEquals(solutionContent, "Solution Text", "The solution data is not appearing.");
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPane2 = preview.toolControl.isDisplayed();
            if (toolPane2 == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            preview.textEntryPopUp.click();//click in middle


        } catch (Exception e) {
            Assert.fail("Exception in test case previewOfEssayQuestionWithWorkBoardEnabled of class AuthorShouldBeAbleToViewAndUseTheEnhancementWorkboardOverlay", e);
        }
    }

    @Test(priority = 11, enabled = true)
    public void previewOfWriteboardQuestionWithNoChangeExpected() {

        try {
            //tc row no 155
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();//select course biology
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            manageContent.createPractice.click();//click on create practice
            manageContent.createRegularAssessment.click();//click on regular assessment
            manageContent.writeBoardQuestion.click();//click on writeboard
            if(manageContent.workBoard.isDisplayed()==true){
                Assert.fail("The 'Allow student to use workboard' checkbox is appearing");
            }
            new Assignment().create(155);//Create an assignment
            new Assignment().addQuestions(155, "writeboard", "");
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            preview.hint_button.click();//click on hint
            String hintContent = preview.hintContent.getText();
            Assert.assertEquals(hintContent, "Hint Text", "The hint data is not appearing.");
            Thread.sleep(3000);
            preview.solution_Button.click();//click on solution
            String solutionContent = preview.solutionContent.getText();
            Assert.assertEquals(solutionContent, "Solution Text", "The solution data is not appearing.");


        } catch (Exception e) {
            Assert.fail("Exception in test case previewOfWriteboardQuestionWithNoChangeExpected of class AuthorShouldBeAbleToViewAndUseTheEnhancementWorkboardOverlay", e);
        }
    }

    @Test(priority = 12, enabled = true)
    public void previewOfAudioQuestionWithWorkBoardEnabled() {

        try {
            //tc row no 160
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            new Assignment().create(160);//Create an assignment
            new Assignment().addQuestions(160, "audio", "");
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("show-your-work-label")));
            String workBoard = preview.plusWorkBoard.getText();
            Assert.assertEquals(workBoard, "+ Workboard", "It is not take to the question preview page.");
            preview.plusWorkBoard.click();//click on +workboard
            String crossIcon = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPanel = preview.toolControl.isDisplayed();
            if (toolPanel == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 160);
            boolean workBoard1 = preview.plusWorkBoard.isDisplayed();
            if (workBoard1 == true) {
                Assert.fail("It is showing '+Work board' button when pop_up is shown.");
            }
            String crossIcon1 = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon1, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");
            preview.crossIcon.click();//click on 'x'
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            preview.hint_button.click();//click on hint
            String hintContent = preview.hintContent.getText();
            Assert.assertEquals(hintContent, "Hint Text", "The hint data is not appearing.");
            Thread.sleep(3000);
            preview.solution_Button.click();//click on solution
            String solutionContent = preview.solutionContent.getText();
            Assert.assertEquals(solutionContent, "Solution Text", "The solution data is not appearing.");
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPane2 = preview.toolControl.isDisplayed();
            if (toolPane2 == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();

        } catch (Exception e) {
            Assert.fail("Exception in test case previewOfAudioQuestionWithWorkBoardEnabled of class AuthorShouldBeAbleToViewAndUseTheEnhancementWorkboardOverlay", e);
        }
    }

    @Test(priority = 13, enabled = true)
    public void previewOfMPQ() {

        try {
            //tc row no 175
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            new Assignment().create(175);//Create an assignment
            new Assignment().addQuestions(175, "expressionevaluator", "");
            new Assignment().addQuestions(175, "all", "");//Create all type of questions
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("show-your-work-label")));
            String workBoard = preview.plusWorkBoard.getText();
            Assert.assertEquals(workBoard, "+ Workboard", "It is not take to the question preview page.");
            preview.plusWorkBoard.click();//click on +workboard
            String crossIcon = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPanel = preview.toolControl.isDisplayed();
            if (toolPanel == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 175);
            preview.crossIcon.click();//click on 'x'


        } catch (Exception e) {
            Assert.fail("Exception in test case previewOfMPQ of class AuthorShouldBeAbleToViewAndUseTheEnhancementWorkboardOverlay", e);
        }
    }

    @Test(priority = 14, enabled = true)
    public void checkUserInterfaceOfTheWorkboardWhenHintAndSolutionHasImageContent() {

        try {
            //tc row no 180
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            new Assignment().create(180);//Create an assignment
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("show-your-work-label")));
            String workBoard = preview.plusWorkBoard.getText();
            Assert.assertEquals(workBoard, "+ Workboard", "It is not take to the question preview page.");
            preview.plusWorkBoard.click();//click on +workboard
            String crossIcon = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPanel = preview.toolControl.isDisplayed();
            if (toolPanel == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 180);
            boolean workBoard1 = preview.plusWorkBoard.isDisplayed();
            if (workBoard1 == true) {
                Assert.fail("It is showing '+Work board' button when pop_up is shown.");
            }
            String crossIcon1 = preview.crossIcon.getText();
            Assert.assertEquals(crossIcon1, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");
            preview.crossIcon.click();//click on 'x'
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);
            preview.textEntryPopUp.click();//click in middle
            driver.switchTo().defaultContent();//switch to main window
            preview.crossIcon.click();//click on 'x'
            preview.trueFalseAnswerLabel.get(0).click();//click on option 'A'
            preview.checkAnswer_button.click();//click on answer choice
            String notificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(notificationMessage, "You got it right.", " It is not showing the message based on the selected option");
            preview.hint_button.click();//click on hint
            Thread.sleep(3000);
            if(preview.hintImage.isDisplayed()==false){
                Assert.fail("Hint image is not displaying");
            }

            Thread.sleep(3000);
            preview.solution_Button.click();//click on solution
            Thread.sleep(3000);

            if(preview.solutionImage.isDisplayed()==false){
                Assert.fail("solution image is not displaying");
            }
            preview.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(preview.frame);//switch to frame
            boolean toolPane2 = preview.toolControl.isDisplayed();
            if (toolPane2 == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            preview.textEntryPopUp.click();//click in middle


        } catch (Exception e) {
            Assert.fail("Exception in test case checkUserInterfaceOfTheWorkboardWhenHintAndSolutionHasImageContent of class AuthorShouldBeAbleToViewAndUseTheEnhancementWorkboardOverlay", e);
        }
    }

}

