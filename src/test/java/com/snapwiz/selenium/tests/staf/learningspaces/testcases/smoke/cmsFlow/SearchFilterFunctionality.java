
package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.cmsFlow;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.OpenSearchPage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.QuestionCreate;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.SearchPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
 * Created by priyanka on 15-03-2016.
 */
public class SearchFilterFunctionality extends Driver {

    SearchPage searchPage;
    Preview preview;
    String notificationMessageActual = null;
    String notificationMessageExpected = null;
    @BeforeMethod
    public void inItElement() {
        WebDriver driver=Driver.getWebDriver();
        searchPage = PageFactory.initElements(driver,SearchPage.class);
        preview = PageFactory.initElements(driver, Preview.class);

    }
    @Test(priority = 1, enabled = true)
    public  void  searchBrowseFilterFunctionalityOfTrueFalse(){
        try {

            ReportUtil.log("Description","This test case validates search and browse page functionality of true false question","info");
            WebDriver driver=Driver.getWebDriver();
            new Assignment().create(1);
            ReportUtil.log("Create true false question", "True false question created successfully", "pass");

            searchFilterWithMedia("true","Ch 1: The Study of Life","Easy","Publish",1,2,"Evaluation","True / False");
            quickPreview("True / False","Publish","Easy.","Evaluation","The Study of Life",false);
            CustomAssert.assertTrue(searchPage.video.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");

            String parentWindow = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);
            CustomAssert.assertTrue(searchPage.video.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");

            preview.trueFalseAnswerLabel.get(0).click();//click on option 'A'
            preview.checkAnswer_button.click();//click on answer choice
            String rightNotificationMessage = preview.notificationMsg.getText();
            CustomAssert.assertEquals(rightNotificationMessage, "You got it right.","Verify right notification message","It is showing the message based on the selected option"," It is not showing the message based on the selected option");

            preview.trueFalseAnswerLabel.get(1).click();//click on option 'A'
            preview.checkAnswer_button.click();//click on answer choice
            String wrongNotificationMessage = preview.notificationMsg.getText();
            CustomAssert.assertEquals(wrongNotificationMessage, "You got it wrong.","Verify right notification message","It is showing the message based on the selected option"," It is not showing the message based on the selected option");
            driver.switchTo().window(parentWindow);

            launchReview("True / False","Publish","Easy.","Evaluation","The Study of Life",true);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",searchPage.icon_search);
            new WebDriverUtil().mouseHover(searchPage.questionText);
            searchPage.editIcon.click();//click on edit icon

            browseFunctionality("Search Questions","Ch 1: The Study of Life",2,"Questions with Media","Easy","Evaluation","Publish","True / False");
            quickPreview("True / False","Publish","Easy.","Evaluation","The Study of Life",false);
            CustomAssert.assertTrue(searchPage.video.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");

            String parentWindow1 = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);
            CustomAssert.assertTrue(searchPage.video.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");

            preview.trueFalseAnswerLabel.get(0).click();//click on option 'A'
            preview.checkAnswer_button.click();//click on answer choice
            String rightNotificationMessage1 = preview.notificationMsg.getText();
            CustomAssert.assertEquals(rightNotificationMessage1, "You got it right.","Verify right notification message","It is showing the message based on the selected option"," It is not showing the message based on the selected option");

            preview.trueFalseAnswerLabel.get(1).click();//click on option 'A'
            preview.checkAnswer_button.click();//click on answer choice
            String wrongNotificationMessage1 = preview.notificationMsg.getText();
            CustomAssert.assertEquals(wrongNotificationMessage1, "You got it wrong.","Verify right notification message","It is showing the message based on the selected option"," It is not showing the message based on the selected option");
            driver.switchTo().window(parentWindow1);

            launchReview("True / False","Publish","Easy.","Evaluation","The Study of Life",true);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",searchPage.icon_search);
            new WebDriverUtil().mouseHover(searchPage.questionText);
            searchPage.editIcon.click();//click on edit icon

        }
        catch (Exception e) {
            Assert.fail("Exception in TC searchBrowseFilterFunctionalityOfTrueFalse of class SearchFilterFunctionality", e);

        }
    }


    @Test(priority = 2, enabled = true)
    public  void  searchBrowseFilterFunctionalityOfMultipleChoice(){
        try {

            ReportUtil.log("Description","This test case validates search and browse page functionality of multiple Choice question","info");
            WebDriver driver=Driver.getWebDriver();
            new Assignment().create(2);
            ReportUtil.log("Create multiple choice question", "Multiple Choice question created successfully", "pass");

            searchFilterWithMedia("multiple","Ch 2: The Chemical Foundation of Life","Medium","Draft - Pending Images",1,2,"Synthesis","Multiple Choice");
            quickPreview("Multiple Choice","Draft - Pending Images","Medium.","Synthesis","The Chemical Foundation of Life",false);
            CustomAssert.assertTrue(searchPage.uploadImage.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");

            String parentWindow = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);
            preview.selectMultipleChoice.get(0).click();//click on option 'A'
            preview.checkAnswer_button.click();//click on answer choice
            String rightNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(rightNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");

            preview.selectMultipleChoice.get(1).click();//click on option 'B'
            preview.checkAnswer_button.click();//click on answer choice
            Thread.sleep(1000);
            String wrongNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(wrongNotificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");
            driver.switchTo().window(parentWindow);

            launchReview("Multiple Choice","Draft - Pending Images","Medium.","Synthesis","The Chemical Foundation of Life",true);
            edit();

            browseFunctionality("Search Questions","Ch 2: The Chemical Foundation of Life",2,"Questions with Media","Medium","Synthesis","Draft - Pending Images","Multiple Choice");
            quickPreview("Multiple Choice","Draft - Pending Images","Medium.","Synthesis","The Chemical Foundation of Life",false);
            CustomAssert.assertTrue(searchPage.uploadImage.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");

            String parentWindow1 = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);

            preview.selectMultipleChoice.get(0).click();//click on option 'A'
            preview.checkAnswer_button.click();//click on answer choice
            String rightNotificationMessage1 = preview.notificationMsg.getText();
            Assert.assertEquals(rightNotificationMessage1, "You got it right.", " It is not showing the message based on the selected option");

            Thread.sleep(3000);
            preview.selectMultipleChoice.get(1).click();//click on option 'B'
            preview.checkAnswer_button.click();//click on answer choice
            WebDriverUtil.waitTillVisibilityOfElement(preview.notificationMsg,120);
            String wrongNotificationMessage1 = preview.notificationMsg.getText();
            Assert.assertEquals(wrongNotificationMessage1, "You got it wrong.", " It is not showing the message based on the selected option");
            driver.switchTo().window(parentWindow1);

            launchReview("Multiple Choice","Draft - Pending Images","Medium.","Synthesis","The Chemical Foundation of Life",true);
            edit();

        }
        catch (Exception e) {
            Assert.fail("Exception in TC searchBrowseFilterFunctionalityOfMultipleChoice of class SearchFilterFunctionality", e);

        }
    }


    @Test(priority = 3, enabled = true)
    public  void  searchBrowseFilterFunctionalityOfMultipleSelection(){
        try {
            ReportUtil.log("Description","This test case validates search and browse page functionality of multiple selection question","info");
            WebDriver driver=Driver.getWebDriver();
            new Assignment().create(3);
            ReportUtil.log("Create multiple selection question", "Multiple Selection question created successfully", "pass");

            searchFilterWithMedia("multiple","Ch 3: Biological Macromolecules","Hard","Accuracy Check",1,2,"Analysis","Multiple Selection");

            quickPreview("Multiple Selection","Accuracy Check","Hard.","Analysis","Biological Macromolecules",false);
            CustomAssert.assertTrue(searchPage.image.getAttribute("src").contains("https://s3.amazonaws.com/content-d/253/img"), "Verify image", "Image is displayed", "Image is not displayed");

            String parentWindow = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);
            CustomAssert.assertTrue(searchPage.image.getAttribute("src").contains("https://s3.amazonaws.com/content-d/253/img"), "Verify image", "Image is displayed", "Image is not displayed");

            preview.multipleSelection.get(0).click();//click on option 'A'
            preview.checkAnswer_button.click();//click on answer choice
            String partialNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(partialNotificationMessage, "You got it partially correct.", " It is not showing the message based on the selected option");

            preview.multipleSelection.get(3).click();//click on option 'A'
            preview.checkAnswer_button.click();//click on answer choice
            Thread.sleep(1000);
            String wrongNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(wrongNotificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");

            preview.multipleSelection.get(0).click();//click on option 'A'
            preview.multipleSelection.get(1).click();//click on option 'B'
            preview.checkAnswer_button.click();//click on answer choice
            Thread.sleep(1000);
            String rightNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(rightNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");
            driver.switchTo().window(parentWindow);
            launchReview("Multiple Selection","Accuracy Check","Hard.","Analysis","Biological Macromolecules",true);
            edit();

            browseFunctionality("Search Questions","Ch 3: Biological Macromolecules",2,"Questions with Media","Hard","Analysis","Accuracy Check","Multiple Selection");

            quickPreview("Multiple Selection","Accuracy Check","Hard.","Analysis","Biological Macromolecules",false);
            CustomAssert.assertTrue(searchPage.image.getAttribute("src").contains("https://s3.amazonaws.com/content-d/253/img"), "Verify image", "Image is displayed", "Image is not displayed");

            String parentWindow1 = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);
            CustomAssert.assertTrue(searchPage.image.getAttribute("src").contains("https://s3.amazonaws.com/content-d/253/img"), "Verify image", "Image is displayed", "Image is not displayed");

            preview.multipleSelection.get(0).click();//click on option 'A'
            preview.checkAnswer_button.click();//click on answer choice
            String partialNotificationMessage1 = preview.notificationMsg.getText();
            Assert.assertEquals(partialNotificationMessage1, "You got it partially correct.", " It is not showing the message based on the selected option");

            preview.multipleSelection.get(3).click();//click on option 'A'
            preview.checkAnswer_button.click();//click on answer choice
            Thread.sleep(1000);
            String wrongNotificationMessage1 = preview.notificationMsg.getText();
            Assert.assertEquals(wrongNotificationMessage1, "You got it wrong.", " It is not showing the message based on the selected option");

            preview.multipleSelection.get(0).click();//click on option 'A'
            preview.multipleSelection.get(1).click();//click on option 'B'
            preview.checkAnswer_button.click();//click on answer choice
            Thread.sleep(1000);
            String rightNotificationMessage1 = preview.notificationMsg.getText();
            Assert.assertEquals(rightNotificationMessage1, "You got it right.", " It is not showing the message based on the selected option");
            driver.switchTo().window(parentWindow1);
            launchReview("Multiple Selection","Accuracy Check","Hard.","Analysis","Biological Macromolecules",true);
            edit();

        }
        catch (Exception e) {
            Assert.fail("Exception in TC searchBrowseFilterFunctionalityOfMultipleSelection of class SearchFilterFunctionality", e);

        }
    }

    @Test(priority = 4, enabled = true)
    public void searchBrowseFilterFunctionalityOfTextEntry(){
        try {
            ReportUtil.log("Description","This test case validates search and browse page functionality of textEntry question","info");
            WebDriver driver=Driver.getWebDriver();
            new Assignment().create(4);
            ReportUtil.log("Create text entry question", "Text entry question created successfully", "pass");

            searchFilterWithMedia("text","Ch 4: Cell Structure","Easy","QA",1,2,"Application","Text Entry");
            quickPreview("Text Entry","QA","Easy.","Application","Cell Structure",false);
            CustomAssert.assertTrue(searchPage.image.getAttribute("src").contains("https://s3.amazonaws.com/content-d/253/"), "Verify image", "Image is displayed", "Image is not displayed");

            String parentWindow = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);
            CustomAssert.assertTrue(searchPage.image.getAttribute("src").contains("https://s3.amazonaws.com/content-d/253/"), "Verify image", "Image is displayed", "Image is not displayed");

            preview.textEntryField.sendKeys("text");
            preview.checkAnswer_button.click();//click on answer choice
            String wrongNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(wrongNotificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");

            preview.textEntryField.clear();
            preview.textEntryField.sendKeys("Correct Answer");
            preview.checkAnswer_button.click();//click on answer choice
            Thread.sleep(1000);
            String rightNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(rightNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");
            driver.switchTo().window(parentWindow);
            launchReview("Text Entry","QA","Easy.","Application","Cell Structure",true);
            edit();

            browseFunctionality("Search Questions","Ch 4: Cell Structure",2,"Questions with Media","Easy","Application","QA","Text Entry");
            quickPreview("Text Entry","QA","Easy.","Application","Cell Structure",false);
            CustomAssert.assertTrue(searchPage.image.getAttribute("src").contains("https://s3.amazonaws.com/content-d/253/"), "Verify image", "Image is displayed", "Image is not displayed");

            String parentWindow1 = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);
            CustomAssert.assertTrue(searchPage.image.getAttribute("src").contains("https://s3.amazonaws.com/content-d/253/"), "Verify image", "Image is displayed", "Image is not displayed");

            preview.textEntryField.sendKeys("text");
            preview.checkAnswer_button.click();//click on answer choice
            String wrongNotificationMessage1 = preview.notificationMsg.getText();
            Assert.assertEquals(wrongNotificationMessage1, "You got it wrong.", " It is not showing the message based on the selected option");

            preview.textEntryField.clear();
            preview.textEntryField.sendKeys("Correct Answer");
            preview.checkAnswer_button.click();//click on answer choice
            Thread.sleep(1000);
            String rightNotificationMessage1 = preview.notificationMsg.getText();
            Assert.assertEquals(rightNotificationMessage1, "You got it right.", " It is not showing the message based on the selected option");
            driver.switchTo().window(parentWindow1);
            launchReview("Text Entry","QA","Easy.","Application","Cell Structure",true);
            edit();

        }
        catch (Exception e) {
            Assert.fail("Exception in TC searchBrowseFilterFunctionalityOfTextEntry of class SearchFilterFunctionality", e);

        }
    }

    @Test(priority = 5, enabled = true)
    public void searchBrowseFilterFunctionalityOfTextDropDown(){
        try {
            ReportUtil.log("Description","This test case validates search and browse page functionality of text Drop Down question","info");
            WebDriver driver=Driver.getWebDriver();
            new Assignment().create(5);
            ReportUtil.log("Create text drop down question", "text drop down question created successfully", "pass");

            searchFilterWithMedia("text","Ch 5: Structure and Function of Plasma Membranes","Medium","Need Revision",1,2,"Comprehension","Text Drop Down");

            quickPreview("Text Drop Down", "Need Revision", "Medium.", "Comprehension", "Structure and Function of Plasma Membranes", false);
            CustomAssert.assertTrue(searchPage.image.getAttribute("src").contains("https://s3.amazonaws.com/content-d/253/"), "Verify image", "Image is displayed", "Image is not displayed");
            String parentWindow = driver.getWindowHandle();

            fullPreview();
            Thread.sleep(1000);
            CustomAssert.assertTrue(searchPage.image.getAttribute("src").contains("https://s3.amazonaws.com/content-d/253/"), "Verify image", "Image is displayed", "Image is not displayed");

            preview.textEntryDropDown.click();
            Thread.sleep(1000);
            preview.checkAnswer_button.click();//click on answer choice
            String rightNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(rightNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");

            preview.textDropDown.click();
            Select s= new Select(preview.textDropDown);
            s.selectByValue("Answer2");
            preview.checkAnswer_button.click();//click on answer choice
            Thread.sleep(1000);
            String wrongNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(wrongNotificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");

            driver.switchTo().window(parentWindow);
            launchReview("Text Drop Down","Need Revision","Medium.","Comprehension","Structure and Function of Plasma Membranes",true);
            edit();

            browseFunctionality("Search Questions","Ch 5: Structure and Function of Plasma Membranes",2,"Questions with Media","Medium","Comprehension","Need Revision","Text Drop Down");

            quickPreview("Text Drop Down", "Need Revision", "Medium.", "Comprehension", "Structure and Function of Plasma Membranes", false);
            CustomAssert.assertTrue(searchPage.image.getAttribute("src").contains("https://s3.amazonaws.com/content-d/253/"), "Verify image", "Image is displayed", "Image is not displayed");
            String parentWindow1 = driver.getWindowHandle();

            fullPreview();
            Thread.sleep(1000);
            CustomAssert.assertTrue(searchPage.image.getAttribute("src").contains("https://s3.amazonaws.com/content-d/253/"), "Verify image", "Image is displayed", "Image is not displayed");

            preview.textEntryDropDown.click();
            Thread.sleep(1000);
            preview.checkAnswer_button.click();//click on answer choice
            String rightNotificationMessage1 = preview.notificationMsg.getText();
            Assert.assertEquals(rightNotificationMessage1, "You got it right.", " It is not showing the message based on the selected option");

            preview.textDropDown.click();
            Select s1= new Select(preview.textDropDown);
            s1.selectByValue("Answer2");
            preview.checkAnswer_button.click();//click on answer choice
            Thread.sleep(1000);
            String wrongNotificationMessage1 = preview.notificationMsg.getText();
            Assert.assertEquals(wrongNotificationMessage1, "You got it wrong.", " It is not showing the message based on the selected option");

            driver.switchTo().window(parentWindow1);
            launchReview("Text Drop Down","Need Revision","Medium.","Comprehension","Structure and Function of Plasma Membranes",true);
            edit();

        }
        catch (Exception e) {
            Assert.fail("Exception in TC searchBrowseFilterFunctionalityOfTextDropDown of class SearchFilterFunctionality", e);

        }
    }


    @Test(priority = 6, enabled = true)
    public void searchBrowseFilterFunctionalityOfNumericEntry(){
        try {
            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("Description","This test case validates search and browse page functionality of numeric entry question","info");

            new Assignment().create(6);
            ReportUtil.log("Create numeric entry question", "numeric entry question created successfully", "pass");

            searchFilterWithMedia("numeric","Ch 6: Metabolism","Hard","Approve",1,2,"Knowledge","Numeric Entry w/units");

            quickPreview("Numeric Entry w/Units","Approve","Hard.","Knowledge","Metabolism",false);
            CustomAssert.assertTrue(searchPage.image.getAttribute("src").contains("https://s3.amazonaws.com/content-d/253/"), "Verify image", "Image is displayed", "Image is not displayed");

            String parentWindow = driver.getWindowHandle();

            fullPreview();
            Thread.sleep(1000);
            CustomAssert.assertTrue(searchPage.image.getAttribute("src").contains("https://s3.amazonaws.com/content-d/253/"), "Verify image", "Image is displayed", "Image is not displayed");

            preview.numericEntryTextBox.sendKeys("10");
            Select s= new Select(preview.numericDropDown);
            s.selectByValue("feet");
            Thread.sleep(1000);
            preview.checkAnswer_button.click();//click on answer choice
            String rightNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(rightNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");

            preview.numericEntryTextBox.click();
            Select s1= new Select(preview.numericDropDown);
            s1.selectByValue("yards");
            preview.checkAnswer_button.click();//click on answer choice
            Thread.sleep(1000);
            String wrongNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(wrongNotificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");

            driver.switchTo().window(parentWindow);
            launchReview("Numeric Entry w/Units","Approve","Hard.","Knowledge","Metabolism",true);
            edit();

            browseFunctionality("Search Questions","Ch 6: Metabolism",2,"Questions with Media","Hard","Knowledge","Approve","Numeric Entry w/units");

            quickPreview("Numeric Entry w/Units","Approve","Hard.","Knowledge","Metabolism",false);
            CustomAssert.assertTrue(searchPage.image.getAttribute("src").contains("https://s3.amazonaws.com/content-d/253/"), "Verify image", "Image is displayed", "Image is not displayed");

            String parentWindow1 = driver.getWindowHandle();

            fullPreview();
            Thread.sleep(1000);
            CustomAssert.assertTrue(searchPage.image.getAttribute("src").contains("https://s3.amazonaws.com/content-d/253/"), "Verify image", "Image is displayed", "Image is not displayed");

            preview.numericEntryTextBox.sendKeys("10");
            Select s2= new Select(preview.numericDropDown);
            s2.selectByValue("feet");
            Thread.sleep(1000);
            preview.checkAnswer_button.click();//click on answer choice
            String rightNotificationMessage1 = preview.notificationMsg.getText();
            Assert.assertEquals(rightNotificationMessage1, "You got it right.", " It is not showing the message based on the selected option");

            preview.numericEntryTextBox.click();
            Select s3= new Select(preview.numericDropDown);
            s3.selectByValue("yards");
            preview.checkAnswer_button.click();//click on answer choice
            Thread.sleep(1000);
            String wrongNotificationMessage1 = preview.notificationMsg.getText();
            Assert.assertEquals(wrongNotificationMessage1, "You got it wrong.", " It is not showing the message based on the selected option");

            driver.switchTo().window(parentWindow1);
            launchReview("Numeric Entry w/Units","Approve","Hard.","Knowledge","Metabolism",true);
            edit();

        }
        catch (Exception e) {
            Assert.fail("Exception in TC searchBrowseFilterFunctionalityOfNumericEntry of class SearchFilterFunctionality", e);

        }
    }


    @Test(priority = 7, enabled = true)
    public void searchBrowseFilterFunctionalityOfAdvancedNumeric(){
        try {
            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("Description","This test case validates search and browse page functionality of advanced numeric question","info");

            new Assignment().create(7);
            ReportUtil.log("Create advanced numeric question", "advanced numeric question created successfully", "pass");

            searchFilterWithMedia("advanced","Ch 7: Cellular Respiration","Easy","Ready to Publish",1,2,"Evaluation","Advanced Numeric");
            quickPreview("Advanced Numeric","Ready to Publish","Easy.","Evaluation","Cellular Respiration",false);
            CustomAssert.assertTrue(searchPage.video.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");

            String parentWindow = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);      
            CustomAssert.assertTrue(searchPage.video.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");

            preview.textEntryField.sendKeys("10");
            preview.checkAnswer_button.click();//click on answer choice
            String rightNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(rightNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");

            preview.textEntryField.click();
            preview.textEntryField.clear();
            preview.textEntryField.sendKeys("20");
            preview.checkAnswer_button.click();//click on answer choice
            Thread.sleep(1000);
            String wrongNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(wrongNotificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");
            driver.switchTo().window(parentWindow);
            launchReview("Advanced Numeric","Ready to Publish","Easy.","Evaluation","Cellular Respiration",true);
            edit();

            browseFunctionality("Search Questions","Ch 7: Cellular Respiration",2,"Questions with Media","Easy","Evaluation","Ready to Publish","Advanced Numeric");

            quickPreview("Advanced Numeric","Ready to Publish","Easy.","Evaluation","Cellular Respiration",false);
            CustomAssert.assertTrue(searchPage.video.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");

            String parentWindow1 = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);
            CustomAssert.assertTrue(searchPage.video.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");

            preview.textEntryField.sendKeys("10");
            preview.checkAnswer_button.click();//click on answer choice
            String rightNotificationMessage1 = preview.notificationMsg.getText();
            Assert.assertEquals(rightNotificationMessage1, "You got it right.", " It is not showing the message based on the selected option");

            preview.textEntryField.click();
            preview.textEntryField.clear();
            preview.textEntryField.sendKeys("20");
            Thread.sleep(2000);
            preview.checkAnswer_button.click();//click on answer choice

            WebDriverUtil.waitTillVisibilityOfElement(preview.notificationMsg,120);
            String wrongNotificationMessage1 = preview.notificationMsg.getText();
            Assert.assertEquals(wrongNotificationMessage1, "You got it wrong.", " It is not showing the message based on the selected option");
            driver.switchTo().window(parentWindow1);
            launchReview("Advanced Numeric","Ready to Publish","Easy.","Evaluation","Cellular Respiration",true);
            edit();

        }
        catch (Exception e) {
            Assert.fail("Exception in TC searchBrowseFilterFunctionalityOfAdvancedNumeric of class SearchFilterFunctionality", e);

        }
    }


    @Test(priority = 8, enabled = true)
    public void searchBrowseFilterFunctionalityOfExpressionEvaluator(){
        try {

            ReportUtil.log("Description","This test case validates search and browse page functionality of expression evaluator question","info");
            WebDriver driver=Driver.getWebDriver();
            new Assignment().create(8);
            ReportUtil.log("Create expression evaluator question", "expression evaluator question created successfully", "pass");

            searchFilterWithMedia("expression","Ch 8: Photosynthesis","Medium","Draft",1,2,"Synthesis","Expression Evaluator");
            quickPreview("Expression Evaluator","Draft","Medium.","Synthesis","Photosynthesis",false);
            CustomAssert.assertTrue(searchPage.uploadImage.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");

            String parentWindow = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);

            preview.expressionEvaluator.click();
            new QuestionCreate().enterValueInMathMLEditor("Square root", "5");
            preview.checkAnswer_button.click();//click on answer choice
            String rightNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(rightNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");

            preview.expressionEvaluator.click();
            new QuestionCreate().enterValueInMathMLEditor("Square root", "6");
            preview.checkAnswer_button.click();//click on answer choice
            Thread.sleep(1000);
            String wrongNotificationMessage = preview.notificationMsg.getText();
            Assert.assertEquals(wrongNotificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");
            driver.switchTo().window(parentWindow);
            launchReview("Expression Evaluator","Draft","Medium.","Synthesis","Photosynthesis",true);
            edit();

            browseFunctionality("Search Questions","Ch 8: Photosynthesis",2,"Questions with Media","Medium","Synthesis","Draft","Expression Evaluator");
            quickPreview("Expression Evaluator","Draft","Medium.","Synthesis","Photosynthesis",false);
            CustomAssert.assertTrue(searchPage.uploadImage.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");

            String parentWindow1 = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);

            preview.expressionEvaluator.click();
            new QuestionCreate().enterValueInMathMLEditor("Square root", "5");
            preview.checkAnswer_button.click();//click on answer choice
            String rightNotificationMessage1 = preview.notificationMsg.getText();
            Assert.assertEquals(rightNotificationMessage1, "You got it right.", " It is not showing the message based on the selected option");

            preview.expressionEvaluator.click();
            new QuestionCreate().enterValueInMathMLEditor("Square root", "6");
            preview.checkAnswer_button.click();//click on answer choice
            Thread.sleep(1000);
            String wrongNotificationMessage1 = preview.notificationMsg.getText();
            Assert.assertEquals(wrongNotificationMessage1, "You got it wrong.", " It is not showing the message based on the selected option");
            driver.switchTo().window(parentWindow1);
            launchReview("Expression Evaluator","Draft","Medium.","Synthesis","Photosynthesis",true);
            edit();

        }
        catch (Exception e) {
            Assert.fail("Exception in TC searchBrowseFilterFunctionalityOfExpressionEvaluator of class SearchFilterFunctionality", e);

        }
    }


    @Test(priority = 9, enabled = true)
    public void searchBrowseFilterFunctionalityOfEssayType(){
        try {
            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("Description","This test case validates search and browse page functionality of essay type question","info");

            new Assignment().create(9);
            ReportUtil.log("Create essay type question", "essay type question created successfully", "pass");

            searchFilterWithMedia("essay","Ch 9: Cell Communication","Hard","Draft - Pending Images",1,2,"Analysis","Essay Type");
            quickPreview("Essay Type Question","Draft - Pending Images","Hard.","Analysis","Cell Communication",false);
            CustomAssert.assertTrue(searchPage.imageMedia.getAttribute("src").contains("https://s3.amazonaws.com/content-d/253/img"), "Verify image", "Image is displayed", "Image is not displayed");

            String parentWindow = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);

            driver.switchTo().window(parentWindow);
            launchReview("Essay Type Question","Draft - Pending Images","Hard.","Analysis","Cell Communication",true);
            edit();

            browseFunctionality("Search Questions","Ch 9: Cell Communication",2,"Questions with Media","Hard","Analysis","Draft - Pending Images","Essay Type");
            quickPreview("Essay Type Question","Draft - Pending Images","Hard.","Analysis","Cell Communication",false);
            CustomAssert.assertTrue(searchPage.imageMedia.getAttribute("src").contains("https://s3.amazonaws.com/content-d/253/img"), "Verify image", "Image is displayed", "Image is not displayed");
            String parentWindow1 = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);

            driver.switchTo().window(parentWindow1);
            launchReview("Essay Type Question","Draft - Pending Images","Hard.","Analysis","Cell Communication",true);
            edit();

        }
        catch (Exception e) {
            Assert.fail("Exception in TC searchBrowseFilterFunctionalityOfEssayType of class SearchFilterFunctionality", e);

        }
    }


    @Test(priority = 10, enabled = false)
    public void searchBrowseFilterFunctionalityOfWriteBoard(){
        try {

            ReportUtil.log("Description","This test case validates search and browse page functionality of text Drop Down question","info");
            WebDriver driver=Driver.getWebDriver();
            new Assignment().create(10);

            searchFilterWithMedia("writeboard","Ch 4: Cell Structure","Easy","QA",1,2,"Application","Workboard");

            quickPreview("Workboard","QA","Easy.","Application","Cell Structure",false);
            String parentWindow = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);

            driver.switchTo().window(parentWindow);
            launchReview("Workboard","QA","Easy.","Application","Cell Structure",true);
            edit();

            browseFunctionality("Search Questions","Ch 4: Cell Structure",2,"Questions with Media","Easy","Application","QA","Workboard");

        }
        catch (Exception e) {
            Assert.fail("Exception in TC searchBrowseFilterFunctionalityOfWriteBoard of class SearchFilterFunctionality", e);

        }
    }


    @Test(priority = 11, enabled = true)
    public void searchBrowseFilterFunctionalityOfAudio(){
        try {
            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("Description","This test case validates search and browse page functionality of audio question","info");

            new Assignment().create(11);
            ReportUtil.log("Create audio type question", "audio type question created successfully", "pass");

            searchFilterWithMedia("audio","Ch 5: Structure and Function of Plasma Membranes","Medium","Need Revision",1,2,"Comprehension","Audio Recorder");

            quickPreview("Audio","Need Revision","Medium.","Comprehension","Structure and Function of Plasma Membranes",false);
            CustomAssert.assertTrue(searchPage.uploadImage.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");
            String parentWindow = driver.getWindowHandle();

            fullPreview();
            Thread.sleep(1000);
            driver.switchTo().window(parentWindow);
            launchReview("Audio","Need Revision","Medium.","Comprehension","Structure and Function of Plasma Membranes",true);
            edit();

            browseFunctionality("Search Questions","Ch 5: Structure and Function of Plasma Membranes",2,"Questions with Media","Medium","Comprehension","Need Revision","Audio Recorder");
            quickPreview("Audio","Need Revision","Medium.","Comprehension","Structure and Function of Plasma Membranes",false);
            CustomAssert.assertTrue(searchPage.uploadImage.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");

            String parentWindow1 = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);
            driver.switchTo().window(parentWindow1);
            launchReview("Audio","Need Revision","Medium.","Comprehension","Structure and Function of Plasma Membranes",true);
            edit();
        }
        catch (Exception e) {
            Assert.fail("Exception in TC searchBrowseFilterFunctionalityOfAudio of class SearchFilterFunctionality", e);

        }
    }

    @Test(priority = 12, enabled = true)
    public void searchBrowseFilterFunctionalityOfMatchTheFollowing(){
        try {

            ReportUtil.log("Description","This test case validates search and browse page functionality of Match The Following question","info");
            WebDriver driver=Driver.getWebDriver();
            new Assignment().create(12);
            ReportUtil.log("Create match the following question", "match the following question created successfully", "pass");

            searchFilterWithMedia("match","Ch 6: Metabolism","Hard","Approve",1,2,"Knowledge","Match the following");

            quickPreview("Match the following","Approve","Hard.","Knowledge","Metabolism",false);
            CustomAssert.assertTrue(searchPage.video.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");
            String parentWindow = driver.getWindowHandle();

            fullPreview();
            Thread.sleep(1000);
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
            driver.switchTo().window(parentWindow);
            launchReview("Match the following","Approve","Hard.","Knowledge","Metabolism",true);
            edit();
            browseFunctionality("Search Questions","Ch 6: Metabolism",2,"Questions with Media","Hard","Knowledge","Approve","Match the following");
            quickPreview("Match the following","Approve","Hard.","Knowledge","Metabolism",false);
            CustomAssert.assertTrue(searchPage.video.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");
            String parentWindow1 = driver.getWindowHandle();

            fullPreview();
            Thread.sleep(1000);
            List<WebElement> dragNDrop1 =preview.matchTheFollowing_preview_draggable_answer;
            Actions ac1 = new Actions(driver);
            ac1.clickAndHold(dragNDrop1.get(0))
                    .moveToElement(preview.matchTheFollowing_preview_draggable_choice.get(0))
                    .release()
                    .build()
                    .perform();
            preview.checkAnswer_button.click();//click on answer choice
            WebDriverUtil.waitTillVisibilityOfElement(preview.notificationMsg, 50);
            notificationMessageActual = preview.notificationMsg.getText().trim();
            ReportUtil.log("Attempting question", "Selected wrong answer", "pass");
            notificationMessageExpected = "You got it wrong.";
            String notificationMessageExpected2="You got it partially correct.";
            if(!(notificationMessageActual.equals(notificationMessageExpected)||notificationMessageActual.equals(notificationMessageExpected2))){
                CustomAssert.fail("Verify notification message","Message  \"You got it wrong\" or \"You got it partially correct.\" is not displayed\",");
            }
            driver.switchTo().window(parentWindow1);
            launchReview("Match the following","Approve","Hard.","Knowledge","Metabolism",true);
            edit();
        }
        catch (Exception e) {
            Assert.fail("Exception in TC searchBrowseFilterFunctionalityOfMatchTheFollowing of class SearchFilterFunctionality", e);

        }
    }


    @Test(priority = 13, enabled = true)
    public void searchBrowseFilterFunctionalityOfDragAndDrop(){
        try {
            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("Description","This test case validates search and browse page functionality of drag and drop question","info");

            new Assignment().create(13);
            ReportUtil.log("Create drag and drop question", "drag and drop question created successfully", "pass");

            searchFilterWithMedia("drag","Ch 7: Cellular Respiration","Easy","Ready to Publish",1,2,"Evaluation","Drag and Drop");

            quickPreview("Drag and Drop","Ready to Publish","Easy.","Evaluation","Cellular Respiration",false);
            CustomAssert.assertTrue(searchPage.video.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");
            String parentWindow = driver.getWindowHandle();

            fullPreview();
            Thread.sleep(1000);
            CustomAssert.assertTrue(searchPage.video.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");

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

            driver.switchTo().window(parentWindow);
            launchReview("Drag and Drop","Ready to Publish","Easy.","Evaluation","Cellular Respiration",true);
            edit();

            browseFunctionality("Search Questions","Ch 7: Cellular Respiration",2,"Questions with Media","Easy","Evaluation","Ready to Publish","Drag and Drop");

            quickPreview("Drag and Drop","Ready to Publish","Easy.","Evaluation","Cellular Respiration",false);
            CustomAssert.assertTrue(searchPage.video.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");
            String parentWindow1 = driver.getWindowHandle();

            fullPreview();
            Thread.sleep(1000);
            CustomAssert.assertTrue(searchPage.video.isDisplayed(), "Verify video", "Video is displayed", "Video is not displayed");

            List<WebElement> answerstodrag1 = preview.preview_draggable_answer;
            Actions ac1 = new Actions(driver);
            ac1.clickAndHold(answerstodrag1.get(0))
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

            driver.switchTo().window(parentWindow1);
            launchReview("Drag and Drop","Ready to Publish","Easy.","Evaluation","Cellular Respiration",true);
            edit();
        }
        catch (Exception e) {
            Assert.fail("Exception in TC searchBrowseFilterFunctionalityOfDragAndDrop of class SearchFilterFunctionality", e);

        }
    }



    @Test(priority = 14, enabled = true)
    public void searchBrowseFilterFunctionalityOfLabelAnImageText(){
        try {

            ReportUtil.log("Description","This test case validates search and browse page functionality of label an image text question","info");
            WebDriver driver=Driver.getWebDriver();
            new Assignment().create(14);
            ReportUtil.log("Create label an image text question", "label an image text question created successfully", "pass");

            searchFilterWithMedia("label","Ch 8: Photosynthesis","Medium","Draft",1,2,"Synthesis","Label an image (Text)");
            quickPreview("Label an image (Text)","Draft","Medium.","Synthesis","Photosynthesis",false);

            String parentWindow = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);

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

            driver.switchTo().window(parentWindow);
            launchReview("Label an image (Text)","Draft","Medium.","Synthesis","Photosynthesis",true);
            edit();

            browseFunctionality("Search Questions","Ch 8: Photosynthesis",2,"Questions with Media","Medium","Synthesis","Draft","Label an image (Text)");

            quickPreview("Label an image (Text)","Draft","Medium.","Synthesis","Photosynthesis",false);

            String parentWindow1 = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);

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

            driver.switchTo().window(parentWindow1);
            launchReview("Label an image (Text)","Draft","Medium.","Synthesis","Photosynthesis",true);
            edit();


        }
        catch (Exception e) {
            Assert.fail("Exception in TC searchBrowseFilterFunctionalityOfLabelAnImageText of class SearchFilterFunctionality", e);

        }
    }


    @Test(priority = 15, enabled = true)
    public void searchBrowseFilterFunctionalityOfLabelAnImageDropDown(){
        try {

            ReportUtil.log("Description","This test case validates search and browse page functionality of label an image drop down question","info");
            WebDriver driver=Driver.getWebDriver();
            new Assignment().create(15);
            ReportUtil.log("Create label an image drop down question", "label an image drop down question created successfully", "pass");

            searchFilterWithMedia("label","Ch 9: Cell Communication","Hard","Draft - Pending Images",1,2,"Analysis","Label an image (Dropdown)");
            quickPreview("Label an image (Dropdown)","Draft - Pending Images","Hard.","Analysis","Cell Communication",false);
            CustomAssert.assertTrue(searchPage.image.isDisplayed(), "Verify image", "Image is displayed", "Image is not displayed");

            String parentWindow = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);

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
            Thread.sleep(5000);
            WebDriverUtil.clickOnElementUsingJavascript(preview.labelAnImageDropDown_CorrectAnswer.get(0));
            Thread.sleep(5000);
            WebDriverUtil.clickOnElementUsingJavascript(preview.labelAnImageDropDown_CorrectAnswer.get(0));
            Thread.sleep(5000);
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

            driver.switchTo().window(parentWindow);
            launchReview("Label an image (Dropdown)","Draft - Pending Images","Hard.","Analysis","Cell Communication",true);
            edit();

            browseFunctionality("Search Questions","Ch 9: Cell Communication",2,"Questions with Media","Hard","Analysis","Draft - Pending Images","Label an image (Dropdown)");
            quickPreview("Label an image (Dropdown)","Draft - Pending Images","Hard.","Analysis","Cell Communication",false);
            CustomAssert.assertTrue(searchPage.image.isDisplayed(), "Verify image", "Image is displayed", "Image is not displayed");

            String parentWindow1 = driver.getWindowHandle();
            fullPreview();
            Thread.sleep(1000);

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
            Thread.sleep(5000);
            preview.labelAnImageDropDown_CorrectAnswer.get(0).click();
            Thread.sleep(5000);
            preview.labelAnImageDropDown_CorrectAnswer.get(0).click();
            Thread.sleep(5000);
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

            driver.switchTo().window(parentWindow1);
            launchReview("Label an image (Dropdown)","Draft - Pending Images","Hard.","Analysis","Cell Communication",true);
            edit();

        }
        catch (Exception e) {
            Assert.fail("Exception in TC searchBrowseFilterFunctionalityOfLabelAnImageDropDown of class SearchFilterFunctionality", e);

        }
    }

    @Test(priority = 17, enabled = true)
    public  void  searchBrowseFilterFunctionalityWithOutMedia(){
        try {

            ReportUtil.log("Description","This test case validates media icon in search page","info");
            WebDriver driver=Driver.getWebDriver();
            String questionType=ReadTestData.readDataByTagName("","questionsTypes","17");
            new Assignment().create(17);
            new Assignment().addQuestions(17,"all","");

            new OpenSearchPage().openSearchPage();
            new OpenSearchPage().searchquestion("withoutmedia");
            Thread.sleep(9000);
            WebDriverUtil.waitTillVisibilityOfElement(searchPage.searchFiltersLink,50);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchPage.searchFiltersLink);//click on search filters link
            searchPage.allQuestion_DropDown.get(0).click();//click on all Question dropdown
            searchPage.allQuestionOptions.get(2).click();//click on all Question option
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchPage.searchFiltersLink);//click on search filters link

            String [] type=questionType.split(",");
            for(int i=2;i<=17;i++) {
                System.out.println(i);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchPage.searchFiltersLink);//click on search filters link
                searchPage.questionFilterDropdown.click();//click on all questionType dropdown
                Thread.sleep(2000);
                new ScrollElement().scrollToViewOfElement(driver.findElements(By.xpath("//a[text()='"+type[i-2]+"']")).get(0));
                Thread.sleep(2000);
                new WebDriverUtil().clickOnElementUsingJavascript(driver.findElements(By.xpath("//a[text()='"+type[i-2]+"']")).get(0));//click on all questionType option
                if(!searchPage.go_Button.isDisplayed())
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchPage.searchFiltersLink);//click on search filters link
                searchPage.go_Button.click();//click on go button
                boolean elementFound = false;
                try{
                    driver.findElement(By.className("cms-result-media-icon"));
                    elementFound = true;
                }
                catch (Exception e){
                    elementFound=false;
                }
                CustomAssert.assertEquals(elementFound,false,"verify media icon","media icon is not displaying","media icon is displaying");
            }
        }
        catch (Exception e) {
            Assert.fail("Exception in TC searchBrowseFilterFunctionalityOfMultiPart of class SearchFilterFunctionality", e);

        }
    }

    public void searchFilterWithMedia(String searchQuestion,String chapters,String difficultyLevel,String status,int questions,int objectives,String taxonomy,String questionType){
        try {
            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("Search Filter","Open search page,search question and select all the filters as per input","info");

            new OpenSearchPage().openSearchPage();
            new OpenSearchPage().searchquestion(searchQuestion);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchPage.searchFiltersLink);//click on search filters link

            WebDriverUtil.waitTillVisibilityOfElement( searchPage.allChapters_DropDown,120);
            searchPage.allChapters_DropDown.click();//click on all chapters dropdown
            new ScrollElement().scrollToViewOfElement(driver.findElement(By.xpath("//a[contains(@title,'All Chapters')]/../ul//li/a[@title='" + chapters + "']")));
            new WebDriverUtil().clickOnElementUsingJavascript(driver.findElement(By.xpath("//a[contains(@title,'All Chapters')]/../ul//li/a[@title='" + chapters + "']"))); //click on all chapters option
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchPage.searchFiltersLink);//click on search filters link

            WebDriverUtil.waitTillVisibilityOfElement(searchPage.difficultyLevel_DropDown,240);
            searchPage.difficultyLevel_DropDown.click();//click on difficulty Level dropdown
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='All Difficulty Level']//..//ul//li/a[@title='" + difficultyLevel + "']")));//click on difficulty Level option

            WebDriverUtil.waitTillVisibilityOfElement(searchPage.allStatus_DropDown,240);
            new WebDriverUtil().clickOnElementUsingJavascript(searchPage.allStatus_DropDown); //click on all status dropdown

            new ScrollElement().scrollToViewOfElement(driver.findElement(By.xpath("//a[@title='All Status']//..//ul//li/a[@title='" + status + "']")));//click on all status option
            new WebDriverUtil().clickOnElementUsingJavascript(driver.findElement(By.xpath("//a[@title='All Status']//..//ul//li/a[@title='" + status + "']")));//click on all status option
            Thread.sleep(2000);

            searchPage.allQuestion_DropDown.get(0).click();//click on all Question dropdown
            searchPage.allQuestionOptions.get(questions).click();//click on all Question option

            searchPage.allObjectives_DropDown.click();//click on all objectives dropdown
            searchPage.allObjectivesOptions.get(objectives).click();//click on all objectives option

            WebDriverUtil.clickOnElementUsingJavascript(searchPage.taxonomy_DropDown);//click on all taxonomy dropdown
            new ScrollElement().scrollToViewOfElement(driver.findElement(By.xpath("//a[starts-with(@title,'All Bloom')]//..//ul//li/a[@title='" + taxonomy + "']")));
            driver.findElement(By.xpath("//a[starts-with(@title,'All Bloom')]//..//ul//li/a[@title='" + taxonomy + "']")).click();//click on all taxonomy option

            WebDriverUtil.clickOnElementUsingJavascript(searchPage.allQuestionType_DropDown);//click on all questionType dropdown
            Thread.sleep(2000);
            new ScrollElement().scrollToViewOfElement(driver.findElement(By.xpath("//a[@title='All Question Type']//..//ul//li/a[@title='" + questionType + "']")));
            new WebDriverUtil().clickOnElementUsingJavascript(driver.findElement(By.xpath("//a[@title='All Question Type']//..//ul//li/a[@title='" + questionType + "']")));//click on all questionType option
            WebDriverUtil.clickOnElementUsingJavascript(searchPage.go_Button);//click on go button
            Thread.sleep(2000);
            if (!searchPage.mediaIcon.getCssValue("background-image").contains("multi-media.png")) {
                Assert.fail( "Question is not a media type");
            }

        }     catch (Exception e) {
            Assert.fail("Exception in TC extendDueDate of class SearchFilterFunctionality", e);

        }
    }


    public void quickPreview(String questionType,String status,String difficulty,String taxonomy,String chapterName,boolean insideLaunchReview){
        try{
            ReportUtil.log("Quick preview page","Verify questionType,status,difficulty,taxonomy,chapterName on quick preview page","info");

            if(insideLaunchReview!=true){
                new WebDriverUtil().mouseHover(searchPage.questionText);
                searchPage.quickPreview.click();
                CustomAssert.assertEquals(searchPage.chapterName.getText().trim(),chapterName, "Verify ChapterName", "ChapterName displaying", "ChapterName is not displaying");

            }
            CustomAssert.assertEquals(searchPage.questionType.getText().trim(), questionType, "Verify Question type", "Question type displaying", "Question type is not displaying");
            CustomAssert.assertEquals(searchPage.status.getText().trim(), status, "Verify status", "status displaying", "status is not displaying");
            CustomAssert.assertEquals(searchPage.difficultyLevel.get(0).getText().trim(), difficulty, "Verify difficulty level", "difficulty level displaying", "difficulty level is not displaying");
            CustomAssert.assertEquals(searchPage.difficultyLevel.get(1).getText().trim(), taxonomy, "Verify taxonomy", "taxonomy displaying", "taxonomy is not displaying");
            Thread.sleep(1000);

        }
        catch (Exception e){
            Assert.fail("Exception in TC quickPreview of class SearchFilterFunctionality", e);
        }
    }

    public void fullPreview() {
        try {
            WebDriver driver=Driver.getWebDriver();
            new WebDriverUtil().clickOnElementUsingJavascript(searchPage.go_Button);//click on go button
            Thread.sleep(1000);
            new WebDriverUtil().mouseHover(searchPage.questionText);
            searchPage.fullPreview.click();//click on full preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.className("control-label")));

        } catch (Exception e) {
            Assert.fail("Exception in TC fullPreview of class SearchFilterFunctionality", e);
        }
    }

    public void launchReview(String questionType,String status,String difficulty,String taxonomy,String ChapterName,boolean insideLaunchReview) {
        try {

            ReportUtil.log("Launch Review page","Verify questionType,status,difficulty,taxonomy on launch review page","info");

            searchPage.checkBox.click();//click on check box
            searchPage.launchReview.click();//click on launch review
            quickPreview(questionType, status, difficulty, taxonomy, ChapterName,insideLaunchReview);

        } catch (Exception e) {
            Assert.fail("Exception in TC launchReview of class SearchFilterFunctionality", e);
        }
    }

    public void edit(){
        try{
            WebDriver driver=Driver.getWebDriver();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",searchPage.icon_search);
            new WebDriverUtil().mouseHover(searchPage.questionText);
            searchPage.editIcon.click();//click on edit icon
            Thread.sleep(1000);
            String beforeEdit=searchPage.question_Text.getText();
            searchPage.question_Text.click();
            Thread.sleep(3000);
            searchPage.question_Text.sendKeys("text");
            searchPage.save_Button.click();//click on save button
            Thread.sleep(2000);
            if(beforeEdit.equals(searchPage.question_Text.getText()))
                Assert.fail("Question is not edited");

        } catch (Exception e) {
            Assert.fail("Exception in TC edit of class SearchFilterFunctionality", e);
        }
    }

    public void browseFunctionality(String content,String option,int objective,String question,String difficulty,String taxonomy,String status,String questionType){
        try{
            ReportUtil.log("Browse Filter","Open browse tab,select all the filters as per input","info");
            WebDriver driver=Driver.getWebDriver();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",searchPage.icon_search);
            Thread.sleep(2000);
            new WebDriverUtil().clickOnElementUsingJavascript(searchPage.browseTab);//click on browseTab

            searchPage.content_DropDown.click();//click on content dropdown
            Thread.sleep(1000);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.xpath("//a[@title='Select Content Type']//..//ul//..//li/a[@title='"+content+"']")));

            searchPage.option_DropDown.click();//click on option dropdown
            new ScrollElement().scrollToViewOfElement(driver.findElement(By.xpath("//a[@title='Select an option']//..//ul//..//li/a[@title='"+option+"']")));
            driver.findElement(By.xpath("//a[@title='Select an option']//..//ul//..//li/a[@title='"+option+"']")).click();

            Thread.sleep(1000);
            WebDriverUtil.waitTillVisibilityOfElement(searchPage.objectives_DropDown,120);
            new WebDriverUtil().clickOnElementUsingJavascript(searchPage.objectives_DropDown);
            searchPage.objectiveOption.get(objective).click();
            Thread.sleep(1000);

            List<WebElement> w = searchPage.allQuestion_DropDown;
            for(WebElement we:w){
                if(we.isDisplayed()){
                    we.click();
                }
            }
            Thread.sleep(2000);
            List<WebElement> w1 = driver.findElements(By.xpath("//a[@title='All Questions']//..//ul//..//li/a[@title='"+question+"']"));
            for(WebElement we1:w1){
                if(we1.isDisplayed()){
                    we1.click();
                }
            }

            searchPage.difficulty_DropDown.click();
            new ScrollElement().scrollToViewOfElement(driver.findElement(By.xpath("//a[@title='Select Difficulty Level']//..//ul//..//li/a[@title='"+difficulty+"']")));
            driver.findElement(By.xpath("//a[@title='Select Difficulty Level']//..//ul//..//li/a[@title='"+difficulty+"']")).click();

            searchPage.taxonomyDropDown.click();
            driver.findElement(By.xpath("//a[starts-with(@title,'Select Bloom')]//..//ul//..//li/a[@title='"+taxonomy+"']")).click();

            searchPage.statusDropDown.click();
            new ScrollElement().scrollToViewOfElement(driver.findElement(By.xpath("//a[@title='Select Status']//..//ul//..//li/a[@title='"+status+"']")));
            driver.findElement(By.xpath("//a[@title='Select Status']//..//ul//..//li/a[@title='"+status+"']")).click();

            searchPage.questionDropDown.click();
            new ScrollElement().scrollToViewOfElement(driver.findElement(By.xpath("//a[@title='Select Question Type']//..//ul//..//li/a[@title='"+questionType+"']")));
            driver.findElement(By.xpath("//a[@title='Select Question Type']//..//ul//..//li/a[@title='"+questionType+"']")).click();
            Thread.sleep(2000);
            if (!searchPage.mediaIcon.getCssValue("background-image").contains("multi-media.png")) {
                Assert.fail( "Question is not a media type");
            }

        } catch (Exception e) {
            Assert.fail("Exception in TC browseFunctionality of class SearchFilterFunctionality", e);
        }

    }




}