package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R199;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

/**
 * Created by shashank on 24-02-2015.
 */
public class CreateAssessment extends Driver {
    @Test(priority = 1, enabled = true)
    public void createFileBasedAssessment() {
        try {
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(60));
            String secondFilename = ReadTestData.readDataByTagName("", "secondFilename", Integer.toString(60));

            String randomAssessment="randomAssessment874";
            WebDriverWait wait=new WebDriverWait(driver,500);
            ManageContent manageContent= PageFactory.initElements(driver,ManageContent.class);
            //Author login
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();//select Biology course
            new SelectCourse().selectChapterByIndex(0); //select first chapter
            Thread.sleep(3000);

            manageContent.createPractice.click();//click on create practice
            manageContent.createRegularAssessment .click();//click on file based assessment
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='create-question-header-sectn']")));
            Thread.sleep(2000);
            new Click().clickbyxpath("//a[@id='question-editor-cancel']");//click on cancel button
            Thread.sleep(3000);
            new SelectCourse().selectChapterByIndex(0);//select first chapter
            Thread.sleep(3000);
            manageContent.createPractice.click();//click on create practice
            Thread.sleep(2000);
            manageContent.createFileBasedAssessment.click();//click on file based assessment
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='File Based Assessment']")));
            if(!driver.findElement(By.xpath("//*[@id='cms-file-assessment-popup-title']")).isDisplayed())
            Assert.fail("Text field 'Name' is not present on page");//validate Name text field present on Popup
            if(!driver.findElement(By.xpath("//*[@id='question-prompt-raw-content']")).isDisplayed())
                Assert.fail("Text field 'Prompt 'is not present on page");//validate Prompt text field present on Popup
            if(!driver.findElement(By.xpath("//*[@id='cms-file-assessment-popup-checkbox']")).isDisplayed())
                Assert.fail("Checkbox 'reserved for assignment' is not present on page");//validate Checkbox 'reserved for assignment' present on Popup
            if(!driver.findElement(By.xpath("//*[@id='uploadFile']")).isDisplayed())
                Assert.fail("Link 'Upload file' is not present on page");//validate Link 'Upload file' present on Popup
            if(!driver.findElement(By.xpath("//*[@class='cms-file-assessment-popup-save']")).isDisplayed())
                Assert.fail("Button 'Save' is not present on page");//validate Button 'Save' present on Popup
            if(!driver.findElement(By.xpath("//*[@class='cms-file-assessment-popup-cancel']")).isDisplayed())
                Assert.fail("Link 'Cancel' is not present on page");//validate Link 'Cancel' present on Popup
            if(!driver.findElement(By.xpath("//*[@class='cms-file-assessment-popup-close']")).isDisplayed())
                Assert.fail("Dialog 'Cancel' is not present on page");//validate Dialog 'Cancel' present on Popup
            //check Check box is checked
            Assert.assertEquals(driver.findElement(By.xpath("//*[@id='cms-file-assessment-popup-checkbox']")).getAttribute("checked"),"true","Expection in CreateAssessment in method createFileBasedAssessment");
            //check Name field as a Mandatory field
            Assert.assertTrue(driver.findElement(By.xpath("//label[@class='cms-file-assessment-popup-labels']")).getText().contains("*"),"Expection in CreateAssessment in method createFileBasedAssessment");

            driver.findElement(By.xpath("//*[@id='question-prompt-raw-content']")).clear();
            Thread.sleep(2000);
            //Validate New popup on click of prompt field
            if(!driver.findElement(By.id("buttonPaneHeader-choice1")).isDisplayed())
                Assert.fail("Texteditor is not present on page");
            Thread.sleep(1000);


            driver.findElement(By.xpath("//*[@id='cms-file-assessment-popup-title']")).sendKeys(randomAssessment);//Type assessment name
            new Click().clickbyxpath("//*[@class='cms-file-assessment-popup-save']");//c;lick on save
            Thread.sleep(2000);
            new Click().clickbyxpath("//div[@title='"+randomAssessment+"']");//select assessment
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='cms-file-assessment-popup-save']")));
            if(!driver.findElement(By.xpath("//*[@id='cms-file-assessment-popup-uuid']")).isDisplayed())
                Assert.fail("Label 'UUID' is not present on page");//validate UUID on edit screen
            driver.findElement(By.xpath("//*[@class='cms-file-assessment-popup-cancel']")).click();//Click on cancel button
            Thread.sleep(2000);
            manageContent.createPractice.click();//click on create practice
            Thread.sleep(2000);
            manageContent.createFileBasedAssessment.click();//click on file based assessment
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='File Based Assessment']")));
            driver.findElement(By.xpath("//*[@id='cms-file-assessment-popup-title']")).sendKeys(randomAssessment + "12");
            new Click().clickbyxpath("//a[@id='uploadFile']");
            Thread.sleep(3000);
            Robot robot=new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            Thread.sleep(3000);
            new KeysSend().sendKeyBoardKeys("After Click on cancel");
            new Click().clickbyxpath("//span[@class='cms-file-assessment-popup-save']");
            Thread.sleep(3000);
            manageContent.createPractice.click();//click on create practice
            Thread.sleep(2000);
            manageContent.createFileBasedAssessment.click();//click on file based assessment
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='File Based Assessment']")));
            new Click().clickbyxpath("//a[@id='uploadFile']");
            Thread.sleep(2000);
            new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ls-delete-file-icon']")));
            Assert.assertTrue(driver.findElement(By.xpath("//span[@class='ls-file-delete-file-name ellipsis']")).getText().contains(filename),"Exception in CreateAssessment in method createFileBasedAssessment");
            new Click().clickbyxpath("//a[@id='uploadFile']");
            Thread.sleep(2000);
            new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+secondFilename+"^");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ls-delete-file-icon']/following::span[@class='ls-delete-file-icon']")));
            Assert.assertTrue(driver.findElement(By.xpath("//span[@class='ls-file-delete-file-name ellipsis']/following::span[@class='ls-file-delete-file-name ellipsis']")).getText().contains(secondFilename),"Exception in CreateAssessment in method createFileBasedAssessment");
            //Delete uploaded file
            manageContent.deleteUploadedFile.get(0).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id='cms-file-assessment-popup-title']")).sendKeys(randomAssessment + "143");
            new Click().clickbyxpath("//span[@class='delete-file-from-file-assessment']");
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElements(By.xpath("//span[@class='ls-file-delete-file-name ellipsis']")).size()==1,"Exception in CreateAssessment in method createFileBasedAssessment");
            manageContent.deleteUploadedFile.get(0).click();
            Thread.sleep(2000);
            new Click().clickbyxpath("//span[@class='donot-delete-file-from-file-assessment']");
            Thread.sleep(2000);
            new Click().clickbyxpath("//*[@class='cms-file-assessment-popup-save']");//c;lick on save
            Thread.sleep(2000);
            new Click().clickbyxpath("//div[@title='"+randomAssessment+"143']");//select assessment
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='cms-file-assessment-popup-save']")));
            if(!driver.findElement(By.xpath("//*[@id='cms-file-assessment-popup-uuid']")).isDisplayed())
                Assert.fail("Label 'UUID' is not present on page");//validate UUID on edit screen
            Assert.assertTrue(driver.findElements(By.xpath("//a[text()='Publish']")).size()==0,"Exception in CreateAssessment in method createFileBasedAssessment");
            Thread.sleep(2000);
            new Click().clickbyxpath("//span[@class='cms-file-assessment-popup-cancel']");//click on cancel button
            Thread.sleep(3000);
            Actions action=new Actions(driver);
            action.moveToElement(driver.findElement(By.xpath("//div[@title='randomAssessment874143']"))).build().perform();
            action.moveToElement(driver.findElement(By.xpath("//div[@title='randomAssessment874143']/following-sibling::span"))).click().build().perform();
            Assert.assertTrue(driver.findElements(By.xpath("//span[text(),'Yes, delete the assessment']")).size()==1,"Exception in CreateAssessment in method ceateFileBasedAssessment");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase publishQuestionChapterLevel in class checkQuestionWithMultipleReference", e);
        }
    }
    @Test(priority = 2,enabled = false)
    public void editFileBasedAssessment() {
        try {
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(60));
            String secondFilename = ReadTestData.readDataByTagName("", "secondFilename", Integer.toString(60));
            String randomAssessment="randomAssessment134";
            ManageContent manageContent= PageFactory.initElements(driver,ManageContent.class);
            WebDriverWait wait=new WebDriverWait(driver,20);
            //Author login
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();//select Biology course
            new SelectCourse().selectChapterByIndex(0); //select first chapter
            Thread.sleep(3000);
            manageContent.createPractice.click();//click on create practice
            Thread.sleep(2000);
            manageContent.createFileBasedAssessment.click();//click on file based assessment
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='File Based Assessment']")));
            new Click().clickbyxpath("//a[@id='uploadFile']");
            Thread.sleep(2000);
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ls-delete-file-icon']")));
            Assert.assertTrue(driver.findElement(By.xpath("//span[@class='ls-file-delete-file-name ellipsis']")).getText().contains(filename), "Exception in CreateAssessment in method createFileBasedAssessment");
            new Click().clickbyxpath("//a[@class='cms-file-assessment-popup-close']");
            manageContent.createPractice.click();//click on create practice
            Thread.sleep(2000);
            manageContent.createFileBasedAssessment.click();//click on file based assessment
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='File Based Assessment']")));
            new Click().clickbyxpath("//a[@id='uploadFile']");
            Thread.sleep(2000);

            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ls-delete-file-icon']")));
            Assert.assertTrue(driver.findElement(By.xpath("//span[@class='ls-file-delete-file-name ellipsis']")).getText().contains(filename), "Exception in CreateAssessment in method createFileBasedAssessment");
            driver.findElement(By.xpath("/*//*[@id='cms-file-assessment-popup-title']")).sendKeys(randomAssessment+"1");
            new Click().clickbyxpath("/*//*[@class='cms-file-assessment-popup-save']");//c;lick on save
            Thread.sleep(2000);
            new Click().clickbyxpath("//div[@title='"+randomAssessment+"1']");//select assessment
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/*//*[@class='cms-file-assessment-popup-save']")));
            if(!driver.findElement(By.xpath("/*//*[@id='cms-file-assessment-popup-uuid']")).isDisplayed())
                Assert.fail("Label 'UUID' is not present on page");//validate UUID on edit screen
            driver.findElement(By.xpath("/*//*[@id='question-prompt-raw-content']")).sendKeys("This is Create fileBased Assessment Prompt");
            new Click().clickbyxpath("/*//*[@class='cms-file-assessment-popup-save']");//click on save
            new Click().clickbyxpath("//div[@title='"+randomAssessment+"1']");//select assessment
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/*//*[@class='cms-file-assessment-popup-save']")));
            Assert.assertTrue(driver.findElement(By.xpath("/*//*[@id='question-prompt-raw-content']")).getText().contains("This is Create fileBased Assessment Prompt"),"Exception in CreateAssessment in method editFileBasedAssessment");
            new Click().clickbyxpath("//span[@class='cms-file-assessment-popup-cancel']");//click on cancel button
            Thread.sleep(2000);
            manageContent.searchLink.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Bulk Operations']")));
            new Click().clickbyxpath("//a[text()='Bulk Operations']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Copy Assessments']")));
            new Click().clickbyxpath("//a[text()='Copy Assessments']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Select an option')])[3]")));
            new Click().clickbyxpath("(//a[contains(text(),'Select an option')])[3]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Ch 1: The Study of Life')])[3]")));
            new Click().clickbyxpath("(//a[contains(text(),'Ch 1: The Study of Life')])[3]");
            Thread.sleep(3000);
            int count=0;
            for (WebElement we:manageContent.topicList) {
                if(we.getText().contains(randomAssessment + "1"))
                count =1;
            }
            if (count==1)
                Assert.fail("Exception in CreateAssessment in method editFileBasedAssessment");
            manageContent.cancelOnCopyAssesssment.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Bulk Operations']")));
            new Click().clickbyxpath("//a[text()='Bulk Operations']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Move Assessments']")));
            new Click().clickbyxpath("//a[text()='Move Assessments']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Select an option')])[3]")));
            new Click().clickbyxpath("(//a[contains(text(),'Select an option')])[3]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Ch 1: The Study of Life')])[3]")));
            new Click().clickbyxpath("(//a[contains(text(),'Ch 1: The Study of Life')])[3]");
            Thread.sleep(3000);
            count=0;
            for (WebElement we:manageContent.topicList) {
                if(we.getText().contains(randomAssessment + "1"))
                    count =1;
            }
            if (count==1)
                Assert.fail("Exception in CreateAssessment in method editFileBasedAssessment");
            manageContent.cancelOnCopyAssesssment.click();

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase publishQuestionChapterLevel in class checkQuestionWithMultipleReference", e);
        }
    }

}
