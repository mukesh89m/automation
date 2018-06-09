package com.snapwiz.selenium.tests.staf.orion.testcases.Sanity.orion;

import com.google.common.base.Predicate;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.Redis;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.CMS.NewQuestionDataEntry;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.Dashboard.DiagTest;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.PerformanceReportInstructor;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Click;
import com.snapwiz.selenium.tests.staf.orion.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by mukesh on 16/11/15.
 */
public class ValidateAllQuestionType extends Driver {
    @Test(priority = 1, enabled = true)
    public void validateTrueFalse() {
        String questionID=null;
        try {
            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("Description", "This test validates the creation of true or false type question", "info");
            NewQuestionDataEntry newQuestionDataEntry = null;
            Preview preview = null;
            String notificationMessageActual = null;
            String notificationMessageExpected = null;

            questionID=new Assignment().create(4, "qtn-type-true-false-img"); //create true false question
            ReportUtil.log("Question Creation", "True or false question created successfully", "pass");

            newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
            newQuestionDataEntry.preview_link.click(); //click on the preview link
            WebDriverUtil.switchToNewWindow();

            preview = PageFactory.initElements(driver, Preview.class);
            WebDriverUtil.waitUntilTextToBePresentInElement(preview.questionLabel,"Q",60);

            driver.findElement(By.xpath("//td[@classname='qtn-label'][contains(text(),'A')]")).click();
            ReportUtil.log("Attempting question", "Selected correct answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it right"
            notificationMessageActual = preview.trueNotificationMsg.getText().trim();
            notificationMessageExpected = "You got it right.";
            CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for right answer", "Message \"You got it right\" is displayed", "Message \"You got it right\" is not displayed");

            preview.trueFalseAnswer_label.get(0).click();//click on the B answer which is incorrect
            ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #2. It should show message as "You got it wrong"
            notificationMessageActual = preview.wrongNotificationMsg.getText().trim();
            notificationMessageExpected = "You got it wrong.";
            CustomAssert.assertEquals(notificationMessageActual, notificationMessageExpected, "Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in TC validateTrueFalse of class ValidateAllQuestionType", e);

        }
        finally {
            if (questionID!=null) {
                new Assignment().updateQuestionStatus(questionID, 90);
            }
            new Redis().deleteKeys();
        }
    }

    @Test(priority = 2, enabled = true)
    public void validateMultipleChoice() {
        WebDriver driver=Driver.getWebDriver();
        String questionID=null;
        try {
            ReportUtil.log("Description", "This test validates the creation of multiple choice type question", "info");
            NewQuestionDataEntry newQuestionDataEntry = null;
            Preview preview = null;
            questionID=new Assignment().create(3, "qtn-multiple-choice-img"); //create  multiple choice
            ReportUtil.log("Question Creation", "multiple choice question created successfully", "pass");

            newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
            newQuestionDataEntry.preview_link.click(); //click on the preview link
            WebDriverUtil.switchToNewWindow();

            preview = PageFactory.initElements(driver, Preview.class);
            WebDriverUtil.waitUntilTextToBePresentInElement(preview.questionLabel,"Q",60);

            driver.findElement(By.xpath("//td[@classname='qtn-label'][contains(text(),'A')]")).click();//click on wrong answer
            ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it right"
            CustomAssert.assertEquals(preview.wrongNotificationMsg.getText().trim(), "You got it wrong.", "Verify notification message for wrong answer", "Message \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");


            preview.select_multipleChoice.get(0).click();//click on the A answer which is incorrect byDefault
            ReportUtil.log("Attempting question", "Selected right answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it wrong"
            CustomAssert.assertEquals(preview.trueNotificationMsg.getText().trim(), "You got it right.", "Verify notification message for right answer", "Message  \"You got it right\" is displayed", "Message  \"You got it right\" is not displayed");


        } catch (Exception e) {
            Assert.fail("Exception in TC validateMultipleChoice of class ValidateAllQuestionType", e);

        }
        finally {
            if (questionID!=null) {
                new Assignment().updateQuestionStatus(questionID, 90);
            }
            new Redis().deleteKeys();
        }
    }

    @Test(priority = 3, enabled = true)
    public void validateMultipleSelection() {
        WebDriver driver=Driver.getWebDriver();
        String questionID=null;
        try {
            ReportUtil.log("Description", "This test validates the creation of multiple selection type question", "info");
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            questionID=new Assignment().create(3, "qtn-multiple-selection-img"); //create  multiple-selection
            ReportUtil.log("Question Creation", "multiple selection question created successfully", "pass");

            newQuestionDataEntry.preview_link.click(); //click on the preview link
            for (String child : driver.getWindowHandles()) {
                driver.switchTo().window(child);
            }

            //new UIElement().waitAndFindElement(preview.questionLabel);
            new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@classname='qtn-label'][contains(text(),'A')]")));
            driver.findElement(By.xpath("//td[@classname='qtn-label'][contains(text(),'A')]")).click();
            ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it wrong"
            CustomAssert.assertEquals(preview.wrongNotificationMsg.getText().trim(), "You got it wrong.", "Verify notification message for wrong answer", "Message \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");

            //8. Click on preview button, select the right answer and click on "Check answer" button
            preview.select_multipleChoice.get(0).click();//click on the A answer which is correct
            preview.select_multipleChoice.get(1).click();//click on the B answer which is correct
            ReportUtil.log("Attempting question", "Selected right answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it right"
            CustomAssert.assertEquals(preview.trueNotificationMsg.getText().trim(), "You got it right.", "Verify notification message for right answer", "Message  \"You got it right\" is displayed", "Message  \"You got it right\" is not displayed");


        } catch (Exception e) {
            Assert.fail("Exception in TC validateMultipleSelection of class ValidateAllQuestionType", e);

        }
        finally {
            if (questionID!=null) {
                new Assignment().updateQuestionStatus(questionID, 90);
            }
            new Redis().deleteKeys();
        }
    }

    @Test(priority = 4, enabled = true)
    public void validateTextEntry() {
        WebDriver driver=Driver.getWebDriver();
        String questionID=null;
        try {
            ReportUtil.log("Description", "This test validates the creation of text entry type question", "info");
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            questionID=new Assignment().create(3, "qtn-text-entry-img"); //create text-entry

            newQuestionDataEntry.preview_link.click(); //click on the preview link
            for (String child : driver.getWindowHandles()) {
                driver.switchTo().window(child);
            }

            new UIElement().waitAndFindElement(preview.questionPreview_header);
            Thread.sleep(4000);
            ReportUtil.log("Attempting question", "Selected right answer", "pass");
            preview.textEntry_textBox1.sendKeys("TextEntryQuestion");
            preview.textEntry_textBox.sendKeys("TextEntryQuestion1");
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it right"
            CustomAssert.assertEquals(preview.trueNotificationMsg.getText().trim(), "You got it right.", "Verify notification message for right answer", "Message  \"You got it right\" is displayed", "Message  \"You got it right\" is not displayed");

            //Tc row no 43
            preview.textEntry_textBox1.clear();
            preview.textEntry_textBox1.sendKeys("TextEntry");
            preview.textEntry_textBox.clear();
            preview.textEntry_textBox.sendKeys("TextEntry");
            ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button
            Thread.sleep(2000);
            //Expected #1. It should show message as "You got it wrong"
            CustomAssert.assertEquals(preview.wrongNotificationMsg.getText().trim(), "You got it wrong.", "Verify notification message for wrong answer", "Message \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");

            preview.textEntry_textBox1.clear();
            preview.textEntry_textBox.clear();
            preview.textEntry_textBox1.sendKeys("TextEntryQuestion");
            preview.textEntry_textBox.sendKeys("TextEntryQuestion");
            ReportUtil.log("Attempting question", "Selected partially correct answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button
            Thread.sleep(2000);
            //Expected #1. It should show message as "You got it Partially Correct"
            CustomAssert.assertEquals(preview.partialcorrectNotificationMsg.getText().trim(), "You got it Partially Correct.", "Verify notification message for partially correct answer","Message  \"You got it Partially Correct.\" is displayed","Message  \"You got it Partially Correct.\" is not dispalyed");


        } catch (Exception e) {
            Assert.fail("Exception in TC validateTextEntry of class ValidateAllQuestionType", e);

        }
        finally {
            if (questionID!=null) {
                new Assignment().updateQuestionStatus(questionID, 90);
            }
            new Redis().deleteKeys();
        }
    }

    @Test(priority = 5, enabled = true)
    public void validateAdvancedNumeric() {
        WebDriver driver=Driver.getWebDriver();
        String questionID=null;
        try {
            ReportUtil.log("Description", "This test validates the creation of advanced numeric type question", "info");
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            questionID=new Assignment().create(3, "qtn-numeric-maple-img"); //create advanced numeric

            newQuestionDataEntry.preview_link.click(); //click on the preview link
            for (String child : driver.getWindowHandles()) {
                driver.switchTo().window(child);
            }

            new UIElement().waitAndFindElement(preview.questionLabel);
            new UIElement().waitAndFindElement(preview.questionLabel);
            Thread.sleep(4000);
            preview.textEntry_textBox1.sendKeys("advancednumeric");
            preview.textEntry_textBox.sendKeys("advancednumeric");
            ReportUtil.log("Attempting question", "Selected right answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it right"
            CustomAssert.assertEquals(preview.trueNotificationMsg.getText().trim(), "You got it right.", "Verify notification message for right answer", "Message  \"You got it right\" is displayed", "Message  \"You got it right\" is not displayed");

            //Tc row no 43
            preview.textEntry_textBox1.clear();
            preview.textEntry_textBox1.sendKeys("advancednumeric1");
            preview.textEntry_textBox.clear();
            preview.textEntry_textBox.sendKeys("advancednumeric1");
            ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button
            Thread.sleep(2000);

            //Expected #1. It should show message as "You got it wrong"
            CustomAssert.assertEquals(preview.wrongNotificationMsg.getText().trim(), "You got it wrong.", "Verify notification message for wrong answer", "Message \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");

            preview.textEntry_textBox1.clear();
            preview.textEntry_textBox.clear();
            preview.textEntry_textBox1.sendKeys("advancednumeric");
            preview.textEntry_textBox.sendKeys("advancednumeric1");
            ReportUtil.log("Attempting question", "Selected partially correct answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button
            Thread.sleep(2000);
            //Expected #1. It should show message as "You got it Partially Correct"
            CustomAssert.assertEquals(preview.partialcorrectNotificationMsg.getText().trim(), "You got it Partially Correct.", "Verify notification message for partially correct answer","Message  \"You got it Partially Correct.\" is displayed","Message  \"You got it Partially Correct.\" is not dispalyed");


        } catch (Exception e) {
            Assert.fail("Exception in TC validateAdvancedNumeric of class ValidateAllQuestionType", e);

        }
        finally {
            if (questionID!=null) {
                new Assignment().updateQuestionStatus(questionID, 90);
            }
            new Redis().deleteKeys();
        }
    }

    @Test(priority = 6, enabled = true)
    public void validateNumericEntryWithUnits() {
        WebDriver driver=Driver.getWebDriver();
        String questionID=null;
        try {
            ReportUtil.log("Description", "This test validates the creation of numeric entry with units type question", "info");
            NewQuestionDataEntry newQuestionDataEntry = null;
            Preview preview = null;
            String quesPosition=null;
            questionID=new Assignment().create(3, "qtn-text-entry-numeric-units-img"); //create text-entry-numeric-units

            newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
            quesPosition=newQuestionDataEntry.curQuestionPosition.getText();

            newQuestionDataEntry.preview_link.click(); //click on the preview link
            WebDriverUtil.switchToNewWindow();//switch to child/preview window

            preview = PageFactory.initElements(driver, Preview.class);
            //wait for page loads in child window/preview window
            WebDriverUtil.waitUntilTextToBePresentInElement(preview.questionLabel,quesPosition,120);

            preview.textEntry_textBox.sendKeys("101"); //enter wrong answer
            ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it right"
            CustomAssert.assertEquals(preview.wrongNotificationMsg.getText().trim(), "You got it wrong.", "Verify notification message for wrong answer", "Message \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");

            //enter right answer
            preview.textEntry_textBox.clear();
            preview.textEntry_textBox.sendKeys("10");
            preview.selectUnits_dropdown.click();

            new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.className("sbOptions")));
            WebDriverUtil.clickOnElementUsingJavascript(preview.selectOptionUnitFeet);

            //wait till dropdown to be open and click on the option 'feet'
//            final WebElement ddValue = preview.selectOptionUnitFeet;
//            new FluentWait<WebDriver>(driver)
//                    .withTimeout(60, TimeUnit.SECONDS)
//                    .pollingEvery(10, TimeUnit.MILLISECONDS)
//                    .until(new Predicate<WebDriver>() {
//                        public boolean apply(WebDriver d) {
//                            return (ddValue.isDisplayed());
//                        }
//                    });
//            ddValue.click();

            ReportUtil.log("Attempting question", "Selected right answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button
            WebDriverUtil.waitUntilTextToBePresentInElement(preview.quesAnsNotificationMsg,"You got it right.",120); //wait till the you got it right text to be visible
            //Expected #2. It should show message as "You got it right"
            CustomAssert.assertEquals(preview.trueNotificationMsg.getText().trim(), "You got it right.", "Verify notification message for right answer", "Message  \"You got it right\" is displayed", "Message  \"You got it right\" is not displayed");

            //enter partially correct answer
            preview.textEntry_textBox.clear();
            preview.textEntry_textBox.sendKeys("101");
            ReportUtil.log("Attempting question", "Selected partially correct answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button

            WebDriverUtil.waitUntilTextToBePresentInElement(preview.quesAnsNotificationMsg,"You got it Partially Correct.",120); //wait till the "you got it Partially Correct" text to be visible
            //Expected #3. It should show message as "You got it Partially Correct"
            CustomAssert.assertEquals(preview.partialcorrectNotificationMsg.getText().trim(), "You got it Partially Correct.", "Verify notification message for partially correct answer","Message  \"You got it Partially Correct.\" is displayed","Message  \"You got it Partially Correct.\" is not dispalyed");


        } catch (Exception e) {
            Assert.fail("Exception in TC validateNumericEntryWithUnits of class ValidateAllQuestionType", e);

        }
        finally {
            if (questionID!=null) {
                new Assignment().updateQuestionStatus(questionID, 90);
            }
            new Redis().deleteKeys();
        }
    }

    @Test(priority = 7, enabled = true)
    public void validateExpressionEvaluator() {
        WebDriver driver=Driver.getWebDriver();
        String questionID=null;
        try {
            ReportUtil.log("Description", "This test validates the creation of expression evaluator type question", "info");
            NewQuestionDataEntry newQuestionDataEntry=null;
            Preview preview = null;

            questionID=new Assignment().create(36, "qtn-math-symbolic-notation-img"); //create Expression evaluater
            newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
            newQuestionDataEntry.preview_link.click(); //click on the preview link
            WebDriverUtil.switchToNewWindow();

            WebDriverUtil.waitForAjax(driver,25);
            preview = PageFactory.initElements(driver, Preview.class);
            WebDriverUtil.waitUntilTextToBePresentInElement(preview.questionLabel,"Q",60);

            new Click().clickBycssselector("div[class='display-correct-answer-math-editor btn sty-green']");
            driver.findElement(By.cssSelector("button[title='Square root']")).click();
            driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys("54");
            driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
            ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button
            WebDriverUtil.waitForAjax(driver,20);
            //Expected #1. It should show message as "You got it wrong"
            CustomAssert.assertEquals(preview.wrongNotificationMsg.getText().trim(), "You got it wrong.", "Verify notification message for wrong answer", "Message \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");

            new Click().clickBycssselector("div[class='display-correct-answer-math-editor btn sty-green']");
            driver.findElement(By.xpath("//span[text()='Delete']")).click();

            new Click().clickBycssselector("div[class='display-correct-answer-math-editor btn sty-green']");
            driver.findElement(By.cssSelector("button[title='Square root']")).click();
            driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys("5");
            driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
            ReportUtil.log("Attempting question", "Selected right answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button
            WebDriverUtil.clickOnElementUsingJavascript(preview.checkAnswer_button);
            WebDriverUtil.waitForAjax(driver,10);
            //Expected #1. It should show message as "You got it right"
            CustomAssert.assertEquals(preview.trueNotificationMsg.getText().trim(), "You got it right.", "Verify notification message for right answer", "Message  \"You got it right\" is displayed", "Message  \"You got it right\" is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in TC validateExpressoinEvaluater of class ValidateAllQuestionType", e);

        }
        finally {
            if (questionID!=null) {
                new Assignment().updateQuestionStatus(questionID, 90);
            }
            new Redis().deleteKeys();
        }
    }


    @Test(priority = 8, enabled = true)
    public void validateEssayQuestion() {
        WebDriver driver=Driver.getWebDriver();
        String questionID=null;
        try {
            ReportUtil.log("Description", "This test validates the creation of essay type question", "info");
            NewQuestionDataEntry newQuestionDataEntry = null;
            Preview preview = null;
            String textAreaValue=null;
            WebElement textArea=null;
            questionID=new Assignment().create(3, "qtn-essay-type"); //create Essay Type

            newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
            newQuestionDataEntry.preview_link.click(); //click on the preview link
            WebDriverUtil.switchToNewWindow();

            WebDriverUtil.waitForAjax(driver,25);
            preview = PageFactory.initElements(driver, Preview.class);
            WebDriverUtil.waitUntilTextToBePresentInElement(preview.questionLabel,"Q",60);
            WebDriverUtil.waitTillVisibilityOfElement(preview.essayTextBox,120);

            driver.switchTo().frame("iframe-text-area-block");
            textArea=driver.findElement(By.xpath("html/body"));
            textArea.sendKeys("Testing");
            textAreaValue=textArea.getText().trim();

            CustomAssert.assertEquals(textAreaValue,"Testing","Verify essay type box is editable","Essay type box is editable and able to enter the text","essay type box is not editable on question preview page");

        } catch (Exception e) {
            Assert.fail("Exception in TC validateEssayQuestion of class ValidateAllQuestionType", e);

        }
        finally {
            if (questionID!=null) {
                new Assignment().updateQuestionStatus(questionID, 90);
            }
            new Redis().deleteKeys();
        }
    }

    @Test(priority = 9, enabled = true)
    public void validateTextEntryWithDropDown() {
        WebDriver driver=Driver.getWebDriver();
        String questionID=null;
        try {
            ReportUtil.log("Description", "This test validates the creation of text entry with dropdown type question", "info");
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            questionID=new Assignment().create(3, "qtn-text-entry-drop-down-img"); //create text-entry-drop-down

            newQuestionDataEntry.preview_link.click(); //click on the preview link
            for (String child : driver.getWindowHandles()) {
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(preview.questionLabel);
            new Click().clickByxpath("//div[@id='question-raw-content']//div/a");
            preview.select_dropdown.get(2).click();
            ReportUtil.log("Attempting question", "Selected right answer", "pass");

            preview.checkAnswer_button.click();//click on the check Answer button
            CustomAssert.assertEquals(preview.trueNotificationMsg.getText().trim(), "You got it right.", "Verify notification message for right answer", "Message  \"You got it right\" is displayed", "Message  \"You got it right\" is not displayed");

            new Click().clickByxpath("//div[@id='question-raw-content']//div/a");
            preview.select_dropdown.get(3).click();
            Thread.sleep(2000);
            ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button
            //Expected #1. It should show message as "You got it wrong"
            CustomAssert.assertEquals(preview.wrongNotificationMsg.getText().trim(), "You got it wrong.", "Verify notification message for wrong answer", "Message \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displayed");


        } catch (Exception e) {
            Assert.fail("Exception in TC validateTextEntryWithDropDown of class ValidateAllQuestionType", e);

        }
        finally {
            if (questionID!=null) {
                new Assignment().updateQuestionStatus(questionID, 90);
            }
            new Redis().deleteKeys();
        }
    }

        WebDriver driver=Driver.getWebDriver();


    @Test(priority = 11, enabled = true)
    public void performanceSummaryReport() {
        WebDriver driver=Driver.getWebDriver();
        String[] questionId=null;
        try {
            ReportUtil.log("Description", "This test validates the performance report for all type of question", "info");
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(44));
            System.out.println("questiontext:" + questiontext);
            PerformanceReportInstructor performanceReportInstructor = PageFactory.initElements(driver, PerformanceReportInstructor.class);

            List<String> questionIdList = new ArrayList<String>();
            questionIdList.add(new Assignment().create(44, "qtn-type-true-false-img")); //create true false question
            questionIdList.add(new Assignment().create(33, "qtn-multiple-choice-img")); //create  multiple choice
            questionIdList.add(new Assignment().create(33, "qtn-multiple-selection-img")); //create  multiple-selection
            questionIdList.add(new Assignment().create(33, "qtn-text-entry-img")); //create text-entry
            questionIdList.add(new Assignment().create(33, "qtn-numeric-maple-img")); //create advanced numeric
            questionIdList.add(new Assignment().create(33, "qtn-text-entry-numeric-units-img")); //create text-entry-numeric-units
            questionIdList.add(new Assignment().create(55, "qtn-math-symbolic-notation-img")); //create Expression evaluater
            questionIdList.add(new Assignment().create(33, "qtn-essay-type")); //create Essay Type
            questionIdList.add(new Assignment().create(33, "qtn-text-entry-drop-down-img")); //create text-entry-drop-down*//**//*
            System.out.println("questionIdList" + questionIdList);

            questionId = new String[questionIdList.size()];
            questionId = questionIdList.toArray(questionId);

            System.out.println("questionIdList:"+ questionIdList.toString());
            new LoginUsingLTI().ltiLogin("74");//login as student
            new DiagnosticTest().startTest(0, 2);


            DiagTest diagTest=PageFactory.initElements(driver, DiagTest.class); //get number of questions in a diag test
            String questionNumberFull=diagTest.questionNumber.getText();
            String noOfQuestions=questionNumberFull.split(" ")[2].replace(")","").trim();

            new DiagnosticTest().attemptAllCorrect(2, false, false);
            WebDriverUtil.waitTillVisibilityOfElement(performanceReportInstructor.performanceChartLabel,50);
            CustomAssert.assertEquals(performanceReportInstructor.performanceChartLabel.getText().trim(), "Chapter Performance","Veriy course performance text is displayed","Course Performance text is displayed inside pieChart","Course Performance text is not displayed inside pieChart");
            CustomAssert.assertEquals(performanceReportInstructor.performanceSummaryTitle.getText().trim(), "Chapter Performance Summary","Verify user is landed on performance summary page" , "User has landed to Performance summary page","User has not landed to Performance summary page");
            CustomAssert.assertTrue(performanceReportInstructor.highchartContainer.isDisplayed(),"Verify Report","Report is generated", "Report not generated");
            Thread.sleep(3000);
            CustomAssert.assertEquals(performanceReportInstructor.questionCards.get(0).getText().trim(), "1."+noOfQuestions,"Verify questions in the question card","All questions are dispalyed int he question card", "All questioncards is not displaying on right side.");

            performanceReportInstructor.questionCards.get(0).click(); //click on the second question card
            Thread.sleep(3000);
            CustomAssert.assertTrue(performanceReportInstructor.questionName.getText().trim().contains(questiontext),"Verify Question content","Question content is displayed", "Question content is not displaying");
            Thread.sleep(2000);

            performanceReportInstructor.dashBoard_icon.click();//click on the dashboard icon
            Thread.sleep(2000);
            new LoginUsingLTI().ltiLogin("74");//login as student
            new PracticeTest().startTest();
            for (int i = 1; i <= 10; i++) {
                new PracticeTest().AttemptCorrectAnswer(2);
            }
            new PracticeTest().quitTestAndGoToReport();

            //DiagTest diagTest=PageFactory.initElements(driver, DiagTest.class); //get number of questions in a diag test
           // String questionNumberFull=diagTest.questionNumber.getText();
           // String noOfQuestions=questionNumberFull.split(" ")[2].replace(")","").trim();

            Thread.sleep(4000);
            CustomAssert.assertEquals(performanceReportInstructor.performanceChartLabel.getText().trim(), "Chapter Performance", "Verify chapter performance text","Course Performance text is displayed inside pieChart","Course Performance text is not displayed inside pieChart");
            CustomAssert.assertEquals(performanceReportInstructor.performanceSummaryTitle.getText().trim(), "Chapter Performance Summary","Verify user landing page","User is landed on Performance summary page","User has not landed on Performance summary page");
            CustomAssert.assertTrue(performanceReportInstructor.highchartContainer.isDisplayed(),"Verify Report","Report is generated", "Report not generated");

            CustomAssert.assertEquals(performanceReportInstructor.questionCards.get(0).getText().trim(), "1.38","Verify questions in the question card","All questions are dispalyed int he question card", "All questioncards is not displaying on right side.");
            performanceReportInstructor.questionCards.get(0).click(); //click on the second question card
            Thread.sleep(3000);

            if (performanceReportInstructor.questionName.getText().trim().equals(" ")) {
                CustomAssert.fail("Verify question content","Question content is not displaying in instructor side");
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC performanceSummaryReport of class ValidateAllQuestionType", e);

        }
        finally {
            for (String str : questionId) {
                new Assignment().updateQuestionStatus(str, 90);
            }
            new Redis().deleteKeys();
        }
    }

}