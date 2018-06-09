package com.snapwiz.selenium.tests.staf.learningspaces.testcases.sanityPack.ls;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.QuestionCard;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.QuestionCreate;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.PerformanceSummary;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 17-11-2015.
 */
public class LsCmsStudent extends Driver {
    @Test(priority=1,enabled = true)
    public void trueFalseQuestionTypes()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
           //tc row no 2
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            new Assignment().create(2);
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
            preview.trueFalseAnswerLabel.get(0).click();//click on option 'A'
            preview.checkAnswer_button.click();//click on answer choice
            String rightNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(rightNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");
            Thread.sleep(2000);
            preview.trueFalseAnswerLabel.get(1).click();//click on option 'B'
            preview.checkAnswer_button.click();//click on answer choice
            String wrongNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(wrongNotificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase trueFalseQuestionTypes of class LsCmsStudent" + e);
        }
    }


    @Test(priority=2,enabled = true)
    public void multipleChoiceQuestionTypes()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //tc row no 8
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            new Assignment().create(8);
            new Assignment().addQuestions(8, "multiplechoice", "");
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
            preview.selectMultipleChoice.get(0).click();//click on option 'A'
            preview.checkAnswer_button.click();//click on answer choice
            String rightNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(rightNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");
            Thread.sleep(2000);
            preview.selectMultipleChoice.get(0).click();//click on option 'B'
            preview.checkAnswer_button.click();//click on answer choice
            Thread.sleep(1000);
            String wrongNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(wrongNotificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase trueFalseQuestionTypes of class LsCmsStudent" + e);
        }
    }

    @Test(priority=3,enabled = true)
    public void multipleSelectionQuestionTypes()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //tc row no 8
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            new Assignment().create(13);
            new Assignment().addQuestions(13, "multipleselection", "");
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
            preview.multipleSelection.get(0).click();//click on option 'A'
            preview.multipleSelection.get(1).click();//click on option 'B'
            preview.checkAnswer_button.click();//click on answer choice
            String rightNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(rightNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");
            Thread.sleep(2000);
            preview.multipleSelection.get(2).click();//click on option 'B'
            preview.checkAnswer_button.click();//click on answer choice
            Thread.sleep(1000);
            String wrongNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(wrongNotificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase trueFalseQuestionTypes of class LsCmsStudent" + e);
        }
    }

    @Test(priority=4,enabled = true)
    public void textEntryQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //tc row no 18
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                Preview preview = PageFactory.initElements(driver, Preview.class);
                new Assignment().create(18);
                new Assignment().addQuestions(18, "textentry", "");
                manageContent.preview_Button.click();//click on preview
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
                preview.textEntryField.sendKeys("text");
                preview.checkAnswer_button.click();//click on answer choice
                String wrongNotificationMessage = preview.notificationMsg.getText();
                Assert.assertEquals(wrongNotificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");
                Thread.sleep(2000);
                preview.textEntryField.clear();
                preview.textEntryField.sendKeys("Correct Answer");
                preview.checkAnswer_button.click();//click on answer choice
                Thread.sleep(1000);
                String rightNotificationMessage = preview.notificationMsg.getText();
                Assert.assertEquals(rightNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");
            } catch (Exception e) {
                Assert.fail("Exception in testcase textEntryQuestionTypes of class LsCmsStudent" + e);
            }
        }

    }
    @Test(priority=5,enabled = true)
    public void advancedNumericQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //tc row no 24
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                Preview preview = PageFactory.initElements(driver, Preview.class);
                new Assignment().create(24);
                new Assignment().addQuestions(24, "advancednumeric", "");
                manageContent.preview_Button.click();//click on preview
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
                preview.textEntryField.sendKeys("text");
                preview.checkAnswer_button.click();//click on answer choice
                String wrongNotificationMessage = preview.notificationMsg.getText();
                Assert.assertEquals(wrongNotificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");
                Thread.sleep(2000);
                preview.textEntryField.clear();
                preview.textEntryField.sendKeys("10");
                preview.checkAnswer_button.click();//click on answer choice
                Thread.sleep(1000);
                String rightNotificationMessage = preview.notificationMsg.getText();
                Assert.assertEquals(rightNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");
            } catch (Exception e) {
                Assert.fail("Exception in testcase advancedNumericQuestionTypes of class LsCmsStudent" + e);
            }
        }

    }


    @Test(priority=6,enabled = true)
     public void numericEntryWithUnitsQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //tc row no 30
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                Preview preview = PageFactory.initElements(driver, Preview.class);
                new Assignment().create(30);
                new Assignment().addQuestions(30, "numericentrywithunits", "");
                manageContent.preview_Button.click();//click on preview
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
                preview.numericEntryDropDown.click();
                preview.checkAnswer_button.click();//click on answer choice
                String wrongNotificationMessage = preview.notificationMsg.getText();
                Assert.assertEquals(wrongNotificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");
                Thread.sleep(2000);
                preview.numericEntryTextBox.sendKeys("10");
                preview.checkAnswer_button.click();//click on answer choice
                Thread.sleep(1000);
                String rightNotificationMessage = preview.notificationMsg.getText();
                Assert.assertEquals(rightNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");
            } catch (Exception e) {
                Assert.fail("Exception in test case numericEntryWithUnitsQuestionTypes of class LsCmsStudent" + e);
            }
        }

    }


    @Test(priority=7,enabled = true)
    public void expressionEvaluatorQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //tc row no 36
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                Preview preview = PageFactory.initElements(driver, Preview.class);
                new Assignment().create(36);
                new Assignment().addQuestions(36, "expressionevaluator", "");
                manageContent.preview_Button.click();//click on preview
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
                preview.expressionEvaluator.click();
                new Click().clickByid("answer_math_edit");
                new QuestionCreate().enterValueInMathMLEditor("Square root", "6");
                preview.checkAnswer_button.click();//click on answer choice
                String wrongNotificationMessage = preview.notificationMsg.getText();
                Assert.assertEquals(wrongNotificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");
                Thread.sleep(2000);
                preview.expressionEvaluator.click();
                new Click().clickByid("answer_math_edit");
                new QuestionCreate().enterValueInMathMLEditor("Square root", "5");
                preview.checkAnswer_button.click();//click on answer choice
                Thread.sleep(1000);
                String rightNotificationMessage = preview.notificationMsg.getText();
                Assert.assertEquals(rightNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");
            } catch (Exception e) {
                Assert.fail("Exception in test case expressionEvaluatorQuestionTypes of class LsCmsStudent" + e);
            }
        }

    }


    @Test(priority=8,enabled = true)
    public void essayQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //tc row no 41
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                Preview preview = PageFactory.initElements(driver, Preview.class);
                new Assignment().create(41);
                new Assignment().addQuestions(41, "essay", "");
                manageContent.preview_Button.click();//click on preview
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
                preview.mathEditor.click();
                preview.cancelButton.click();
                preview.language.click();

            } catch (Exception e) {
                Assert.fail("Exception in test case expressionEvaluatorQuestionTypes of class LsCmsStudent" + e);
            }
        }

    }


    @Test(priority=9,enabled = true)
    public void textEntryWithDropDownQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //tc row no 45
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                Preview preview = PageFactory.initElements(driver, Preview.class);
                new Assignment().create(45);
                new Assignment().addQuestions(45, "textdropdown", "");
                manageContent.preview_Button.click();//click on preview
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
                preview.textEntryDropDown.click();
                preview.checkAnswer_button.click();//click on answer choice
                String rightNotificationMessage = preview.notificationMsg.getText();
                Assert.assertEquals(rightNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");
                Thread.sleep(2000);
                driver.findElement(By.xpath("(//select[@class='question-raw-content-dropdown'])[1]")).click();
                WebElement ele= driver.findElement(By.xpath("(//select[@class='question-raw-content-dropdown'])[1]"));
                Select sel = new Select(ele);
                sel.selectByIndex(2);
                preview.checkAnswer_button.click();//click on answer choice
                Thread.sleep(2000);
                String wrongNotificationMessage = preview.notificationMsg.getText();
                Assert.assertEquals(wrongNotificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");

            } catch (Exception e) {
                Assert.fail("Exception in test case expressionEvaluatorQuestionTypes of class LsCmsStudent" + e);
            }
        }

    }

    @Test(priority=10,enabled = true)
    public void multiPartQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //tc row no 51
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                new Assignment().create(51);
                new Assignment().addQuestions(51, "multipart", "");
                manageContent.preview_Button.click();//click on preview
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));

            } catch (Exception e) {
                Assert.fail("Exception in test case multiPartQuestionTypes of class LsCmsStudent" + e);
            }
        }

    }

    @Test(priority=11,enabled = true)
    public void dragAndDropQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //tc row no 54
                Preview preview = PageFactory.initElements(driver, Preview.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                new Assignment().create(54);
                new Assignment().addQuestions(54, "draganddrop", "");
                manageContent.preview_Button.click();//click on preview
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
                List<WebElement> answerstodrag = driver.findElements(By.cssSelector("div[class='dnd-preview-draggable-answer ui-draggable ui-draggable-handle']"));
                Actions ac = new Actions(driver);
                ac.clickAndHold(answerstodrag.get(0))
                        .moveToElement(driver.findElement(By.id("dragged-ans-choice")))
                        .release()
                        .build()
                        .perform();
                preview.checkAnswer_button.click();//click on answer choice
                String rightNotificationMessage = preview.notificationMsg.getText();
                Assert.assertEquals(rightNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");
            } catch (Exception e) {
                Assert.fail("Exception in test case dragAndDropQuestionTypes of class LsCmsStudent" + e);
            }
        }

    }


    @Test(priority=12,enabled = true)
    public void matchTheFollowingQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //tc row no 62
                Preview preview = PageFactory.initElements(driver, Preview.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                new Assignment().create(62);
                new Assignment().addQuestions(62, "matchthefollowing", "");
                manageContent.preview_Button.click();//click on preview
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));

            } catch (Exception e) {
                Assert.fail("Exception in test case matchTheFollowingQuestionTypes of class LsCmsStudent" + e);
            }
        }

    }


    @Test(priority=13,enabled = true)
    public void labelAnImageTextQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //tc row no 68
                Preview preview = PageFactory.initElements(driver, Preview.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                new Assignment().create(68);
                new Assignment().addQuestions(68, "labelAnImageText", "");
                manageContent.preview_Button.click();//click on preview
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));

            } catch (Exception e) {
                Assert.fail("Exception in test case labelAnImageTextQuestionTypes of class LsCmsStudent" + e);
            }
        }

    }



}