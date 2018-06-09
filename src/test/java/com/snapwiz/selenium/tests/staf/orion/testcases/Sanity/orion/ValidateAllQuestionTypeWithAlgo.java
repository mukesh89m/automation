package com.snapwiz.selenium.tests.staf.orion.testcases.Sanity.orion;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.Redis;
import com.snapwiz.selenium.tests.staf.orion.apphelper.*;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.CMS.NewQuestionDataEntry;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.PerformanceReportInstructor;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Click;
import com.snapwiz.selenium.tests.staf.orion.uihelper.ScrollElement;
import com.snapwiz.selenium.tests.staf.orion.uihelper.UIElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukesh on 18/11/15.
 */
public class ValidateAllQuestionTypeWithAlgo extends Driver {
    @Test(priority = 1, enabled = true)
    public void validateTrueFalse() {
        WebDriver driver=Driver.getWebDriver();
        String questionID=null;
        try {
                ReportUtil.log("Description", "This test validates the creation of true or false type question with algorithmic", "info");
                NewQuestionDataEntry newQuestionDataEntry = null;
                Preview preview = null;
                questionID=create(44, "qtn-type-true-false-img"); //create true false question

                newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
                //newQuestionDataEntry.preview_link.click(); //click on the preview link
                WebDriverUtil.clickOnElementUsingJavascript(newQuestionDataEntry.preview_link);
                WebDriverUtil.switchToNewWindow();

                WebDriverUtil.waitForAjax(driver,25);
                preview = PageFactory.initElements(driver, Preview.class);
                WebDriverUtil.waitUntilTextToBePresentInElement(preview.questionLabel,"Q",60);

                driver.findElement(By.xpath("//td[@classname='qtn-label'][contains(text(),'A')]")).click();
                ReportUtil.log("Attempting question", "Selected correct answer", "pass");
                preview.checkAnswer_button.click();//click on the check Answer button

                //Expected #1. It should show message as "You got it right"
                CustomAssert.assertEquals(preview.trueNotificationMsg.getText().trim(), "You got it right.", "Verify notification message for right answer", "Message \"You got it right\" is displayed", "Message \"You got it right\" is not displayed");

                driver.findElement(By.xpath("//td[@classname='qtn-label'][contains(text(),'B')]")).click();//click on the B answer which is incorrect
                ReportUtil.log("Attempting question", "Selected correct answer", "pass");
                preview.checkAnswer_button.click();//click on the check Answer button

                //Expected #2. It should show message as "You got it wrong"
                CustomAssert.assertEquals(preview.wrongNotificationMsg.getText().trim(), "You got it wrong.","Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displaying ");

        } catch (Exception e) {
            Assert.fail("Exception in TC validateTrueFalse of class ValidateAllQuestionTypeWithAlgo", e);

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
            ReportUtil.log("Description", "This test validates the creation of multiple choice type question with algorithmic", "info");
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            questionID=create(33, "qtn-multiple-choice-img"); //create  multiple choice

            newQuestionDataEntry.preview_link.click(); //click on the preview link
            WebDriverUtil.switchToNewWindow();

            WebDriverUtil.waitForAjax(driver,25);
            preview = PageFactory.initElements(driver, Preview.class);
            WebDriverUtil.waitUntilTextToBePresentInElement(preview.questionLabel,"Q",60);
            List<WebElement> ansChoiceText = driver.findElements(By.xpath("//span[@id='qoption']//td[3]"));
            Thread.sleep(3000);

            int index = 0;
            for (WebElement ele : ansChoiceText) {
                Thread.sleep(5000);
                if (ele.getText().trim().equals("3")) {
                    break;
                }
                index++;

            }
            System.out.println("Index:" + index);
            List<WebElement> ansChoiceOption = driver.findElements(By.className("qtn-label"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ansChoiceOption.get(index));
            ReportUtil.log("Attempting question", "Selected correct answer", "pass");

            preview.checkAnswer_button.click();//click on the check Answer button
            //Expected #1. It should show message as "You got it right"
            CustomAssert.assertEquals(preview.trueNotificationMsg.getText().trim(), "You got it right.","Verify notification message for right answer","Message  \"You got it right\" is displayed", "Message  \"You got it right\" is not displayed ");

            preview.select_multipleChoice.get(0).click();//click on the A answer which is incorrect byDefault
            ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it wrong"
            CustomAssert.assertEquals(preview.wrongNotificationMsg.getText().trim(), "You got it wrong.","Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displaying ");


        } catch (Exception e) {
            Assert.fail("Exception in TC validateMultipleChoice of class ValidateAllQuestionTypeWithAlgo", e);

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
            ReportUtil.log("Description", "This test validates the creation of multiple selection type question with algorithmic", "info");
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            questionID=create(33, "qtn-multiple-selection-img"); //create  multiple-selection


            //Getting question id's
            String choice1ID = null;
            String choice2ID = null;
            String choice3ID = null;
            String choice4ID = null;

            choice1ID = WebDriverUtil.executeJavascript("return document.getElementById('choice1HTML').getAttribute('choicecontentid');");
            choice2ID = WebDriverUtil.executeJavascript("return document.getElementById('choice2HTML').getAttribute('choicecontentid');");
            choice3ID = WebDriverUtil.executeJavascript("return document.getElementById('choice3HTML').getAttribute('choicecontentid');");
            choice4ID = WebDriverUtil.executeJavascript("return document.getElementById('choice4HTML').getAttribute('choicecontentid');");


            newQuestionDataEntry.preview_link.click(); //click on the preview link
            WebDriverUtil.switchToNewWindow();

            WebDriverUtil.waitForAjax(driver,25);
            preview = PageFactory.initElements(driver, Preview.class);
            WebDriverUtil.waitUntilTextToBePresentInElement(preview.questionLabel,"Q",60);
//            List<WebElement> ansChoiceOption = driver.findElements(By.className("qtn-label"));
//            List<WebElement> ansChoiceText = driver.findElements(By.xpath("//span[@id='qoption']//td[3]"));
//            for(int i=0;i<ansChoiceText.size()-1;i++){
//                if(ansChoiceText.get(i).getText().trim().equals("3")){
//                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ansChoiceOption.get(i));
//                    Thread.sleep(3000);
//
//                }
//            }

            //Click on all correct answer

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("[choiceid=\"" + choice1ID + "\"]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("[choiceid=\"" + choice2ID + "\"]")));
            ReportUtil.log("Attempting question", "Selected correct answers", "pass");

            preview.checkAnswer_button.click();//click on the check Answer button
            //Expected #1. It should show message as "You got it right"
            CustomAssert.assertEquals(preview.trueNotificationMsg.getText().trim(), "You got it right.","Verify notification message for right answer","Message  \"You got it right\" is displayed", "Message  \"You got it right\" is not displayed ");


//            List<WebElement> ansChoiceOption1 = driver.findElements(By.className("qtn-label"));
//            List<WebElement> ansChoiceText1 = driver.findElements(By.xpath("//span[@id='qoption']//td[3]"));
//            for(int i=0;i<ansChoiceText1.size()-1;i++){
//                if(ansChoiceText1.get(i).getText().trim().equals("3")){
//                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ansChoiceOption1.get(i));
//                    Thread.sleep(3000);
//
//                }
//            }
//            List<WebElement> ansChoiceOption2 = driver.findElements(By.className("qtn-label"));
//            List<WebElement> ansChoiceText2 = driver.findElements(By.xpath("//span[@id='qoption']//td[3]"));
//            for(int i=0;i<ansChoiceText2.size()-1;i++){
//                if(ansChoiceText2.get(i).getText().trim().equals("2")){
//                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ansChoiceOption2.get(i));
//                    Thread.sleep(3000);
//                    break;
//
//                }
//            }

            //unselect the correct answer and select wrong answer choices
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@choiceid='" + choice1ID + "']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@choiceid='" + choice2ID + "']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@choiceid='" + choice3ID + "']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@choiceid='" + choice4ID + "']")));
            ReportUtil.log("Attempting question", "Selected wrong answers", "pass");
            //8. Click on preview button, select the wrong answer and click on "Check answer" button
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it wrong"
            Assert.assertEquals(preview.wrongNotificationMsg.getText().trim(), "You got it wrong.", "Message  \"You got it wrong.\" is not displaying ");

//            List<WebElement> ansChoiceOptionp = driver.findElements(By.className("qtn-label"));
//            List<WebElement> ansChoiceTextp = driver.findElements(By.xpath("//span[@id='qoption']//td[3]"));
//            for(int i=0;i<ansChoiceTextp.size()-1;i++){
//                if(ansChoiceTextp.get(i).getText().trim().equals("3")){
//                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ansChoiceOptionp.get(i));
//                    break;
//
//                }
//            }

            //select one correct and one wrong answer choice
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@choiceid='" + choice1ID + "']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@choiceid='" + choice4ID + "']")));
            ReportUtil.log("Attempting question", "Selected partially correct answer", "pass");

            preview.checkAnswer_button.click();//click on the check Answer button
            CustomAssert.assertEquals(preview.partialcorrectNotificationMsg.getText().trim(), "You got it Partially Correct.", "Verify notification message for partially correct answer","Message  \"You got it Partially Correct.\" is displayed","Message  \"You got it Partially Correct.\" is not dispalyed");


        } catch (Exception e) {
            Assert.fail("Exception in TC validateMultipleSelection of class ValidateAllQuestionTypeWithAlgo", e);

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
            ReportUtil.log("Description", "This test validates the text entry type question with algorithmic", "info");
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            questionID=create(33, "qtn-text-entry-img"); //create text-entry

            newQuestionDataEntry.preview_link.click(); //click on the preview link
            WebDriverUtil.switchToNewWindow();

            WebDriverUtil.waitForAjax(driver,25);
            preview = PageFactory.initElements(driver, Preview.class);
            WebDriverUtil.waitUntilTextToBePresentInElement(preview.questionLabel,"Q",60);

            preview.textEntry_textBox1.sendKeys("3");
            preview.textEntry_textBox.sendKeys("3");
            ReportUtil.log("Attempting question", "Selected correct answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it right"
            CustomAssert.assertEquals(preview.trueNotificationMsg.getText().trim(), "You got it right.","Verify notification message for right answer","Message  \"You got it right\" is displayed", "Message  \"You got it right\" is not displayed ");

            //Tc row no 43
            preview.textEntry_textBox1.clear();
            preview.textEntry_textBox1.sendKeys("4");
            preview.textEntry_textBox.clear();
            preview.textEntry_textBox.sendKeys("4");
            ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button
            Thread.sleep(2000);
            //Expected #1. It should show message as "You got it wrong"
            CustomAssert.assertEquals(preview.wrongNotificationMsg.getText().trim(), "You got it wrong.","Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displaying ");

            preview.textEntry_textBox1.clear();
            preview.textEntry_textBox.clear();
            preview.textEntry_textBox1.sendKeys("3");
            preview.textEntry_textBox.sendKeys("4");
            ReportUtil.log("Attempting question", "Selected partially correct answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button
            Thread.sleep(2000);
            //Expected #1. It should show message as "You got it Partially Correct"
            CustomAssert.assertEquals(preview.partialcorrectNotificationMsg.getText().trim(), "You got it Partially Correct.", "Verify notification message for partially correct answer","Message  \"You got it Partially Correct.\" is displayed","Message  \"You got it Partially Correct.\" is not dispalyed");


        } catch (Exception e) {
            Assert.fail("Exception in TC validateTextEntry of class ValidateAllQuestionTypeWithAlgo", e);

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
            ReportUtil.log("Description", "This test validates the creation advanced numeric type question with algorithmic", "info");
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            questionID=create(33, "qtn-numeric-maple-img"); //create advanced numeric

            newQuestionDataEntry.preview_link.click(); //click on the preview link
            WebDriverUtil.switchToNewWindow();

            WebDriverUtil.waitForAjax(driver,25);
            preview = PageFactory.initElements(driver, Preview.class);
            WebDriverUtil.waitUntilTextToBePresentInElement(preview.questionLabel,"Q",60);

            preview.textEntry_textBox.sendKeys("3");
            ReportUtil.log("Attempting question", "Selected correct answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it right"
            CustomAssert.assertEquals(preview.trueNotificationMsg.getText().trim(), "You got it right.","Verify notification message for right answer","Message  \"You got it right\" is displayed", "Message  \"You got it right\" is not displayed ");

            //Tc row no 43
            preview.textEntry_textBox.clear();
            preview.textEntry_textBox.sendKeys("4");
            ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button
            Thread.sleep(2000);
            //Expected #1. It should show message as "You got it wrong"
            CustomAssert.assertEquals(preview.wrongNotificationMsg.getText().trim(), "You got it wrong.","Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displaying ");


        } catch (Exception e) {
            Assert.fail("Exception in TC validateAdvancedNumeric of class ValidateAllQuestionTypeWithAlgo", e);

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
            ReportUtil.log("Description", "This test validates the creation of numeric entry with units type question with algorithmic", "info");
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            questionID=create(33, "qtn-text-entry-numeric-units-img"); //create text-entry-numeric-units

            newQuestionDataEntry.preview_link.click(); //click on the preview link
            WebDriverUtil.switchToNewWindow();

            WebDriverUtil.waitForAjax(driver,25);
            preview = PageFactory.initElements(driver, Preview.class);
            WebDriverUtil.waitUntilTextToBePresentInElement(preview.questionLabel,"Q",60);
            WebDriverUtil.waitTillVisibilityOfElement(preview.textEntry_textBox, 60);

            preview.textEntry_textBox.sendKeys("101");
            ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it wrong"
            CustomAssert.assertEquals(preview.wrongNotificationMsg.getText().trim(), "You got it wrong.","Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displaying ");


            //Tc row no 43
            //preview.textEntry_textBox.clear();
            WebDriverUtil.executeJavascript("arguments[0].value=\"\"", preview.textEntry_textBox); //clear text box value
            preview.textEntry_textBox.sendKeys("3");
            preview.selectUnits_dropdown.click();
            new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.className("sbOptions")));
            WebDriverUtil.clickOnElementUsingJavascript(preview.selectOptionUnitFeet);//select feet
            ReportUtil.log("Attempting question", "Selected correct answer", "pass");

            preview.checkAnswer_button.click();//click on the check Answer button
            Thread.sleep(2000);
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.xpath("//div[text()='You got it right.']")), 40);
            //Expected #1. It should show message as "You got it wrong"
            CustomAssert.assertEquals(preview.trueNotificationMsg.getText().trim(), "You got it right.","Verify notification message for right answer","Message  \"You got it right\" is displayed", "Message  \"You got it right\" is not displayed ");

            preview.textEntry_textBox.clear();
            preview.textEntry_textBox.sendKeys("101");
            preview.checkAnswer_button.click();//click on the check Answer button
            Thread.sleep(2000);
            //Expected #1. It should show message as "You got it partilaly correct"
            CustomAssert.assertEquals(preview.partialcorrectNotificationMsg.getText().trim(), "You got it Partially Correct.", "Verify notification message for partially correct answer","Message  \"You got it Partially Correct.\" is displayed","Message  \"You got it Partially Correct.\" is not dispalyed");


        } catch (Exception e) {
            Assert.fail("Exception in TC validateNumericEntryWithUnits of class ValidateAllQuestionTypeWithAlgo", e);

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
            ReportUtil.log("Description", "This test validates the creation of expression evaluator type question with algorithmic", "info");
            NewQuestionDataEntry newQuestionDataEntry = null;
            Preview preview = null;
            questionID=create(20, "qtn-math-symbolic-notation-img"); //create expression evaluator question

            newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
            newQuestionDataEntry.preview_link.click(); //click on the preview link
            WebDriverUtil.switchToNewWindow();

            WebDriverUtil.waitForAjax(driver,25);
            preview = PageFactory.initElements(driver, Preview.class);
            WebDriverUtil.waitUntilTextToBePresentInElement(preview.questionLabel,"Q",60);


            new Click().clickBycssselector("div[class='display-correct-answer-math-editor btn sty-green']");
            driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys("5");//enter wrong answer
            driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
            ReportUtil.log("Attempting question", "Entered wrong answer", "pass");
            preview = PageFactory.initElements(driver, Preview.class);
            preview.checkAnswer_button.click();//click on the check Answer button
            WebDriverUtil.waitTillVisibilityOfElement(preview.wrong_message,60);
            //Expected #1. It should show message as "You got it wrong"
            CustomAssert.assertEquals(preview.wrongNotificationMsg.getText().trim(), "You got it wrong.", "Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displaying ");

            new Click().clickBycssselector("div[class='display-correct-answer-math-editor btn sty-green']");
            driver.findElement(By.xpath("//span[text()='Delete']")).click();//clear the field
            Thread.sleep(3000);
            new Click().clickBycssselector("div[class='display-correct-answer-math-editor btn sty-green']");
            driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys("3");//enter correct answer
            driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
            ReportUtil.log("Attempting question", "Entered correct answer", "pass");
            preview = PageFactory.initElements(driver, Preview.class);
            preview.checkAnswer_button.click();//click on the check Answer button
            preview.checkAnswer_button.click();//click on the check Answer button

            WebDriverUtil.waitForAjax(driver,20);
            WebDriverUtil.waitTillVisibilityOfElement(preview.right_message,60);
            CustomAssert.assertEquals(preview.trueNotificationMsg.getText().trim(), "You got it right.","Verify notification message for right answer","Message  \"You got it right\" is displayed", "Message  \"You got it right\" is not displayed ");


        } catch (Exception e) {
            Assert.fail("Exception in TC validateExpressoinEvaluater of class ValidateAllQuestionTypeWithAlgo", e);

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
            ReportUtil.log("Description", "This test validates the creation of essay type question with algorithmic", "info");
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            String textAreaValue=null;
            WebElement textArea=null;
            questionID=create(33, "qtn-essay-type"); //create Essay Type

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
            Assert.fail("Exception in TC validateEssayQuestion of class ValidateAllQuestionTypeWithAlgo", e);

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
            ReportUtil.log("Description", "This test validates the creation of text entry with dropdown question with algorithmic", "info");
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            questionID=create(33, "qtn-text-entry-drop-down-img"); //create text-entry-drop-down

            newQuestionDataEntry.preview_link.click(); //click on the preview link
            WebDriverUtil.switchToNewWindow();

            WebDriverUtil.waitForAjax(driver,25);
            preview = PageFactory.initElements(driver, Preview.class);
            WebDriverUtil.waitUntilTextToBePresentInElement(preview.questionLabel,"Q",60);

            new Click().clickByxpath("//div[@id='question-raw-content']//div/a");
            preview.select_dropdown.get(2).click();
            ReportUtil.log("Attempting question", "Selected correct answer", "pass");

            preview.checkAnswer_button.click();//click on the check Answer button
            CustomAssert.assertEquals(preview.trueNotificationMsg.getText().trim(), "You got it right.","Verify notification message for right answer","Message  \"You got it right\" is displayed", "Message  \"You got it right\" is not displayed ");

            new Click().clickByxpath("//div[@id='question-raw-content']//div/a");
            preview.select_dropdown.get(3).click();
            Thread.sleep(2000);
            ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
            preview.checkAnswer_button.click();//click on the check Answer button
            //Expected #1. It should show message as "You got it wrong"
            CustomAssert.assertEquals(preview.wrongNotificationMsg.getText().trim(), "You got it wrong.","Verify notification message for wrong answer", "Message  \"You got it wrong\" is displayed", "Message  \"You got it wrong\" is not displaying ");


        } catch (Exception e) {
            Assert.fail("Exception in TC validateTextEntryWithDropDown of class ValidateAllQuestionTypeWithAlgo", e);

        }
        finally {
            if (questionID!=null) {
                new Assignment().updateQuestionStatus(questionID, 90);
            }
            new Redis().deleteKeys();
        }
    }


    @Test(priority = 10, enabled = true)
    public void performanceSummaryReport() {
        WebDriver driver=Driver.getWebDriver();
        String[] questionId=null;
        try {
            ReportUtil.log("Description", "This test validates the performance report after attempting all type for question which are algorithmic", "info");
            PerformanceReportInstructor performanceReportInstructor = PageFactory.initElements(driver, PerformanceReportInstructor.class);
            List<String> questionIdList = new ArrayList<String>();


            questionIdList.add(create(444, "qtn-type-true-false-img")); //create true false question
            questionIdList.add(create(333, "qtn-multiple-choice-img")); //create  multiple choice
            questionIdList.add(create(333, "qtn-multiple-selection-img")); //create  multiple-selection
            questionIdList.add(create(333, "qtn-text-entry-img")); //create text-entry
            questionIdList.add(create(333, "qtn-numeric-maple-img")); //create advanced numeric
            questionIdList.add(create(333, "qtn-text-entry-numeric-units-img")); //create text-entry-numeric-units
            questionIdList.add(create(555, "qtn-math-symbolic-notation-img")); //create Expression evaluater
            questionIdList.add(create(333, "qtn-essay-type")); //create Essay Type
            questionIdList.add(create(333, "qtn-text-entry-drop-down-img")); //create text-entry-drop-down*/
            System.out.println("questionIdList" + questionIdList);

            questionId = new String[questionIdList.size()];
            questionId = questionIdList.toArray(questionId);

            new LoginUsingLTI().ltiLogin("78");//login as student
            new DiagnosticTest().startTest(1, 2);
           /* new Click().clickByxpath("//img[@title='Continue Diagnostic']");
            new Click().clickByxpath("//a[@class='al-user-profile-drop-down']");
            new Click().clickByxpath("//a[@title='My Reports']");*/
            Thread.sleep(10000);
            new DiagnosticTest().attemptAllCorrect(2, false, false);
            Assert.assertEquals(performanceReportInstructor.performanceChartLabel.getText().trim(), "Chapter Performance", "Course Performance text is not displayed inside pieChart");
            Assert.assertEquals(performanceReportInstructor.performanceSummaryTitle.getText().trim(), "Chapter Performance Summary", "User has not landed to Performance summary page");
            Assert.assertTrue(performanceReportInstructor.highchartContainer.isDisplayed(), "Report not Generated");
            Thread.sleep(3000);
            Assert.assertEquals(performanceReportInstructor.questionCards.get(0).getText().trim(), "2.29", "All questioncards is not displaying on right side.");

            performanceReportInstructor.questionCards.get(0).click(); //click on the second question card
            Thread.sleep(3000);
            Assert.assertTrue(performanceReportInstructor.questionName.getText().trim().contains("1+2"), "Question content is not displaying");
            Thread.sleep(2000);

            performanceReportInstructor.dashBoard_icon.click();//click on the dashboard icon
            Thread.sleep(2000);

            new PracticeTest().startPracticeTest();
            for (int i = 1; i <= 10; i++) {
                new PracticeTest().AttemptCorrectAnswer(2);
            }
            new PracticeTest().quitTestAndGoToReport();
            Thread.sleep(4000);
            Assert.assertEquals(performanceReportInstructor.performanceChartLabel.getText().trim(), "Chapter Performance", "Course Performance text is not displayed inside pieChart");
            Assert.assertEquals(performanceReportInstructor.performanceSummaryTitle.getText().trim(), "Chapter Performance Summary", "User has not landed to Performance summary page");
            Assert.assertTrue(performanceReportInstructor.highchartContainer.isDisplayed(), "Report not Generated");

            Assert.assertEquals(performanceReportInstructor.questionCards.get(0).getText().trim(), "2.39", "All questioncards is not displaying on right side.");

            performanceReportInstructor.questionCards.get(0).click(); //click on the second question card
            Thread.sleep(3000);

            if (performanceReportInstructor.questionName.getText().trim().equals(" ")) {
                Assert.fail("Question content is not displaying in instructor side");
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC performanceSummaryReport of class ValidateAllQuestionTypeWithAlgo", e);

        }
        finally {
            for (String str : questionId) {
                new Assignment().updateQuestionStatus(str, 90);
            }
            new Redis().deleteKeys();
        }
    }

    public String create(int dataIndex, String questionType) throws InterruptedException {
        WebDriver driver = Driver.getWebDriver();
        Config.readconfiguration();
        String questionID = null;
        try {
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String course = Config.course;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String questiontype = ReadTestData.readDataByTagName("", "questiontype", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String hint = ReadTestData.readDataByTagName("", "hint", Integer.toString(dataIndex));
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
            String practice_type = ReadTestData.readDataByTagName("", "practice_type", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String algorithmic = ReadTestData.readDataByTagName("", "algorithmic", Integer.toString(dataIndex));

            System.out.println("algorithmic:" + algorithmic);
            System.out.println("chapterName:" + chapterName);
            new DirectLogin().CMSLogin();
            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null) {
                    driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                } else if (courselevel != null) {
                    new Click().clickBycssselector("div[class='course-label node']");
                } else {
                    new UIElement().waitAndFindElement(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                    if (driver.findElements(By.xpath("//div[contains(@title,'" + chapterName + "')]")).size() > 1) {
                        System.out.println("inside if:" + chapterName);
                        new TopicOpen().openCMSLastChapter();
                    } else {
                        System.out.println("inside else:" + chapterName);
                        List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                        for (WebElement chapters : allChapters) {
                            if (chapters.getText().contains(chapterName)) {
                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chapters);
                                Thread.sleep(4000);
                                break;
                            }
                        }
                    }

                }
                List<WebElement> elements = driver.findElements(By.xpath("//div[@title='" + assessmentname + "']"));
                if (elements.size() == 0) {
                    driver.findElement(By.cssSelector("div.create-practice")).click();
                    if (practice_type == null) {
                        driver.findElement(By.className("create-regular-assessment-popup-item")).click();//click on the create regular assessment
                        new UIElement().waitAndFindElement(By.cssSelector("a[selectedid='Adaptive Component Diagnostic']"));
                        new Click().clickBycssselector("a[selectedid='Adaptive Component Diagnostic']");
                        new Click().clickbylinkText(questiontype);
                        driver.findElement(By.id("assessmentName")).clear();
                        driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
                        driver.findElement(By.id("questionSetName")).clear();
                        driver.findElement(By.id("questionSetName")).sendKeys(questionset);
                    } else {
                        driver.findElement(By.className("create-regular-assessment-popup-item")).click();//click on the create file assessment
                    }
                } else {
                    elements.get(elements.size() - 1).click();//click on the assessment name
                    Thread.sleep(9000);
                    new UIElement().waitAndFindElement(By.id("questionOptions"));
                    new Click().clickByid("questionOptions");
                    new UIElement().waitAndFindElement(By.id("addQuestion"));
                    new Click().clickByid("addQuestion");
                }

                driver.findElement(By.id(questionType)).click();


                if (questionType.equals("qtn-type-true-false-img")) //1.For true-false type question
                {
                    new Click().clickByid("question-raw-content");//click on Question
                    driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    driver.switchTo().defaultContent();

                    Thread.sleep(1000);
                    driver.findElement(By.id("choice1")).click();
                    //Setting choice 1 as correct answer
                    Actions action = new Actions(driver);
                    WebElement we = driver.findElement(By.id("0"));
                    action.moveToElement(we).build().perform();
//                    Thread.sleep(2000);
                    WebElement webElement = driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']"));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
//                    action.moveToElement(we).click(webElement).build().perform();

                }

                if (questionType.equals("qtn-multiple-choice-img")) //2. For multiple choice question.
                {
                    driver.findElement(By.id("question-raw-content")).click();
                    driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    driver.switchTo().defaultContent();
                    //Adding choice 1
                    driver.findElement(By.id("choice1")).click();
                    driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys("$a");
                    driver.switchTo().defaultContent();
                    //Adding choice 2
                    driver.findElement(By.id("choice2")).click();
                    driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys("$c");
                    driver.switchTo().defaultContent();
                    //Adding choice 3
                    driver.findElement(By.id("choice3")).click();
                    driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys("$b");
                    driver.switchTo().defaultContent();
                    //Adding choice 4
                    driver.findElement(By.id("choice4")).click();
                    driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys("5");
                    driver.switchTo().defaultContent();
                    //Setting choice 2 as correct answer
                    Actions action = new Actions(driver);
                    WebElement we = driver.findElement(By.id("choice2"));
                    action.moveToElement(we).build().perform();
                    WebElement webElement = driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']"));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
                    // driver.findElement(By.id("shuffle-answer-choices-checkbox")).click();

                }
                if (questionType.equals("qtn-multiple-selection-img"))  //3. For multiple sections
                {
                    driver.findElement(By.id("question-raw-content")).click();
//                    new  WebDriverWait(driver,90).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframe-question-edit"));
                    driver.switchTo().frame("iframe-question-edit");
                    driver.findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    driver.switchTo().defaultContent();
                    //Adding choice 1
                    driver.findElement(By.id("choice1")).click();
                    driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys("$c");
                    driver.switchTo().defaultContent();
                    //Adding choice 2
                    driver.findElement(By.id("choice2")).click();
                    driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys("$c");
                    driver.switchTo().defaultContent();
                    //Adding choice 3
                    driver.findElement(By.id("choice3")).click();
                    driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys("$b");
                    driver.switchTo().defaultContent();
                    //Adding choice 4
                    driver.findElement(By.id("choice4")).click();
                    driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys("$a");
                    driver.switchTo().defaultContent();
                    //Setting choice 2 as correct answer
                    Actions action1 = new Actions(driver);
                    WebElement we1 = driver.findElement(By.id("choice1"));
                    action1.moveToElement(we1).build().perform();
                    WebElement webElement = driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']"));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);

                    Actions action = new Actions(driver);
                    WebElement we = driver.findElement(By.id("choice2"));
                    action.moveToElement(we).build().perform();
                    WebElement webElement1 = driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']"));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement1);


                    // driver.findElement(By.id("shuffle-answer-choices-checkbox")).click();

                }
                if (questionType.equals("qtn-text-entry-img")) // 4.For Text Entry type.
                {
                    driver.findElement(By.id("question-raw-content")).click();
                    new Click().clickByid("addtextbox"); //click on the add textbox
                    driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    driver.switchTo().defaultContent();
                    // Adding correct answer choice
                    driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
                    driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
                    driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys("$c");
                    Thread.sleep(3000);

                    List<WebElement> elements1 = driver.findElements(By.cssSelector("input[id='correct-ans-text']"));
                    elements1.get(1).click();
                    elements1.get(1).clear();
                    elements1.get(1).sendKeys("$c");
                    driver.findElement(By.id("done-button")).click(); // Accept answer
                    Thread.sleep(2000);
                }

                if (questionType.equals("qtn-numeric-maple-img")) //5. Adding Advanced numeric question
                {
                    driver.findElement(By.id("question-raw-content")).click();
                    driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    driver.switchTo().defaultContent();
                    // Adding correct answer choice
                    driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
                    driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
                    driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys("$c");

                    Thread.sleep(3000);
                    // Rest two boxes are filled automatically.
                    //Adding alternate answer choice.
                    driver.findElement(By.id("right-alt-container-1")).click();
                    driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
                    driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
                    driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys("$c");
                    Thread.sleep(2000);
                    // Accept answer
                    driver.findElement(By.id("done-button")).click();
                    Thread.sleep(2000);
                    // Allow for using white board.
                    driver.findElement(By.id("display-write-board-checkbox")).click();
                }
                if (questionType.equals("qtn-text-entry-numeric-units-img")) //6. Adding text entry numeric with units.
                {
                    driver.findElement(By.id("question-raw-content")).click();
                    driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    driver.switchTo().defaultContent();
                    // Adding correct answer choice
                    driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
                    driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
                    driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys("$c");

                    Thread.sleep(3000);
                    // Rest two boxes are filled automatically.
                    driver.findElement(By.id("add-more-entry")).click();
                    Thread.sleep(3000);
                    // Selecting particular unit (eg. feet here)
                    List<WebElement> unitvalues = driver.findElements(By.cssSelector("#cms-qtextentry-more-units-container>ul>li"));
                    for (WebElement units : unitvalues) {
                        System.out.println(units.getText());
                        if (units.getText().equals("feet")) {
                            units.click();
                            System.out.println("Selected feet from units dropdown list");
                            break;
                        }
                    }
                    driver.findElement(By.id("done-button")).click(); // Accept answer
                    Thread.sleep(2000);
                    driver.findElement(By.id("display-write-board-checkbox")).click(); // Allow for using white board.
                }

                if (questionType.equals("qtn-math-symbolic-notation-img")) // 7. Adding expressoin evaluater
                {
                    driver.findElement(By.id("question-raw-content")).click();
                    driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    driver.switchTo().defaultContent();
                    Thread.sleep(1000);
                    // Adding Correct answer
                    driver.findElement(By.cssSelector("div[class='right-container math-correct-answer-input-container']")).click();
                    Thread.sleep(1000);
                    driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys(Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "c");
                    driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();

                    // Accept answer
                    driver.findElement(By.id("done-button")).click();
                    Thread.sleep(1000);
                    // Allow for using white board.
                    driver.findElement(By.id("display-write-board-checkbox")).click();
                    Thread.sleep(1000);
                }
                if (questionType.equals("qtn-essay-type")) // 8. Adding Essay type question
                {

                    driver.findElement(By.id("question-raw-content")).click();
                    driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    driver.switchTo().defaultContent();
                    //Adding height for text entry.
                    driver.findElement(By.id("essay-question-height")).click();
                    driver.findElement(By.id("essay-question-height")).sendKeys("2");
                    // Allow for using white board.
                    driver.findElement(By.id("display-write-board-checkbox")).click();
                }
                if (questionType.equals("qtn-text-entry-drop-down-img")) //9. For text entry drop down
                {
                    driver.findElement(By.id("question-raw-content")).click();
                    driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    driver.switchTo().defaultContent();
                    // Adding Entry 1
                    driver.findElement(By.id("entry-0")).click();
                    driver.findElement(By.id("unit-name-edit-entry-0")).clear();
                    driver.findElement(By.id("unit-name-edit-entry-0")).sendKeys("$a");
                    Thread.sleep(2000);
                    // Accepting answer
                    WebElement menuitem = driver.findElement(By.id("entry-1"));
                    Locatable hoverItem = (Locatable) menuitem;
                    Mouse mouse = ((HasInputDevices) driver).getMouse();
                    mouse.mouseMove(hoverItem.getCoordinates());
                    List<WebElement> selectanswerticks = driver.findElements(By.className("mark-selected"));
                    selectanswerticks.get(1).click(); //select second option as correct answer

                    // Adding Entry 2
                    Actions action = new Actions(driver);
                    action.doubleClick(driver.findElement(By.id("entry-1")));
                    action.perform();
                    driver.findElement(By.id("unit-name-edit-entry-1")).clear();
                    driver.findElement(By.id("unit-name-edit-entry-1")).sendKeys("$c");
                    Thread.sleep(2000);

                    // Adding Entry 3
                    action = new Actions(driver);
                    action.doubleClick(driver.findElement(By.id("entry-2")));
                    action.perform();
                    Thread.sleep(2000);
                    driver.findElement(By.id("unit-name-edit-entry-2")).clear();
                    driver.findElement(By.id("unit-name-edit-entry-2")).sendKeys("$b");
                    Thread.sleep(2000);

                    //clicking on add more entry
                    driver.findElement(By.id("add-new-entry")).click();
                    //Adding entry 4
                    action.doubleClick(driver.findElement(By.id("entry-3")));
                    driver.findElement(By.id("unit-name-edit-entry-3")).clear();
                    driver.findElement(By.id("unit-name-edit-entry-3")).sendKeys("6");
                    // Accepting answer
                    driver.findElement(By.id("done-button")).click();
                }

                saveQuestion(learningobjective, solutionText, hint, useWriteBoard, difficultylevel, variationLevel, dataIndex, algorithmic);

                questionID = driver.findElement(By.id("question-id-label")).getText().trim();

                ReportUtil.log("Question Creation", "Question creation is successfull for the type '" + questiontype + "'", "pass");

            }

        } catch (InterruptedException e) {
            ReportUtil.log("Question Creation", "Unable to create question as there was a exception " + e.getMessage(), "fail");
            Assert.fail("Exception while creating question", e);
        }
        System.out.println("questionID:" + questionID);
        return questionID;

    }


    public void saveQuestion(String learningobjective, String solutionText, String hint, String useWriteBoard, String difficultylevel, String variationLevel, int dataIndex, String algorithmic) {
        Config.readconfiguration();
        WebDriver driver=Driver.getWebDriver();
        try {

            if (useWriteBoard != null) {
                new Click().clickBycssselector("#content-writeboard > #writeboard");//check the use writeboard check box
            }
            if (difficultylevel != null) {
//                new ComboBox().selectValue(7, difficultylevel);
                String id = driver.findElement(By.xpath("//*[@selectedid=\"difficulty level\"]")).getAttribute("id").split("_")[1];
                driver.findElement(By.id("sbSelector_" + id)).click();
                Thread.sleep(1000);
                String xpath = "//*[@id=\"footer\"]//ul[@id=\"sbOptions_" + id + "\"]//a[.=\"" + difficultylevel + "\"]";
                WebElement element = driver.findElement(By.xpath(xpath));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

            }
            if (learningobjective != null)
                associateTlo(dataIndex, learningobjective);//add TLO

            if (driver.findElements(By.xpath("//*[@id='instructor-only-check-box-div']/label")).size() == 1) {
                driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click();
            } //checking 'Enable for intructor only'

            if (solutionText != null || solutionText.equals("true")) {
                driver.findElement(By.cssSelector("#explanation")).click();
//                Thread.sleep(1000);
                driver.switchTo().frame("iframe-explanation").findElement(By.xpath("/html/body")).sendKeys(solutionText);//enter solution text
                driver.switchTo().defaultContent();
            }

            if (hint != null) {
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("#text-hint #hint")));
//                Thread.sleep(1000);
                driver.switchTo().frame("iframe-hint").findElement(By.xpath("/html/body")).sendKeys(hint);//enter hint text
                driver.switchTo().defaultContent();
//                Thread.sleep(3000);
            }

            System.out.println("algorithmic:" + algorithmic);
            if (algorithmic != null) {
                if (algorithmic.equals("true")) {
                    System.out.println("hi m here ");
                    WebElement element = driver.findElement(By.id("question-parameters"));
                    new ScrollElement().scrollToViewOfElement(element);
                    new Click().clickByxpath("//*[@id='question-parameters']/div[1]"); //click on the + icon to expan
                    driver.findElement(By.className("ace_text-input")).sendKeys("var " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "a = 1;\n" +
                            "var " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "b = 2;\n" +
                            "var " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "c = " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "a+" + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "b;");

                }
            }
            if (variationLevel != null) {
                new Click().clickByxpath("//div[@class='footer-bloomcode']/div/a");
                new Click().clickByxpath("//div[@class='footer-bloomcode']//ul/li/a[@rel='" + variationLevel + "']");
            }

           /* List<WebElement>elements= driver.findElements(By.xpath("//a[@title='Draft']"));
            for(WebElement ele:elements){
                if(ele.isDisplayed()){
                    ele.click();//click on the draft
                    break;
                }
            }*/

//            new Click().clickbylinkText("Draft"); //click on Draft option
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.linkText("Draft")));
            new Click().clickByxpath("//a[@rel='80']");    //click on Publish
            new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
            new Click().clickByid("saveQuestionDetails1");//click on save button
        } catch (Exception e) {
            Assert.fail("Exception while saving a question", e);
        }
    }

    public void associateTlo(int dataIndex, String learningobjective) {
        Config.readconfiguration();
        WebDriver driver=Driver.getWebDriver();
        try {
            driver.findElement(By.id("learing-objectives-span")).click(); //Clicking on Learning objective drop-down from footer
            new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.id("link-add-learning-objectives")));
            driver.findElement(By.id("link-add-learning-objectives")).click(); //clicking on 'Add Learning Objective' link
            Thread.sleep(2000);
            if (learningobjective.equals("true"))
                driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[3]/label")).click();
            else {
                WebElement element = driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[" + learningobjective + "]/label"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[" + learningobjective + "]/label")).click();
                Thread.sleep(3000);
            }
            new Click().clickBycssselector("span.add-collection-to-subtopic");//click on Associate
        } catch (Exception e) {
            Assert.fail("Exception in app helper QuestionCreate in method associateTlo.", e);
        }
    }

    @AfterMethod
    public void close() {
        //driver.quit();
    }

}