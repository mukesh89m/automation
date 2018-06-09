package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R199;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.QuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by shashank on 25-02-2015.
 */
public class FileAssessmentAtLTILevel extends  Driver{
    @Test(priority = 1)
    public void createFileBasedAssessment() {
        try {
            String randomAssessment="TestFileBasedAssessment";
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(60));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(60));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(60));
            String secondFilename = ReadTestData.readDataByTagName("", "secondFilename", Integer.toString(60));
            ManageContent manageContent= PageFactory.initElements(driver,ManageContent.class);
            AssignmentTab assignmentTab= PageFactory.initElements(driver,AssignmentTab.class);
            QuestionBank questionBank= PageFactory.initElements(driver,QuestionBank.class);

            LessonPage lessonPage= PageFactory.initElements(driver,LessonPage.class);
            MyQuestionBank myQuestionBank=PageFactory.initElements(driver,MyQuestionBank.class);
            WebDriverWait wait=new WebDriverWait(driver,2000);
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();//select Biology course
            new SelectCourse().selectChapterByIndex(5); //select sixth chapter
            Thread.sleep(3000);
            manageContent.createPractice.click();//click on create practice
            manageContent.createFileBasedAssessment.click();//click on file based assessment
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='File Based Assessment']")));
            new Click().clickbyxpath("//a[@id='uploadFile']");
            Thread.sleep(2000);

            new KeysSend().sendKeyBoardKeys("$"+ Config.fileUploadPath+filename+"^");
            driver.findElement(By.id("cms-file-assessment-popup-title")).sendKeys(randomAssessment);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ls-delete-file-icon']")));
            new Click().clickbyxpath("//span[@class='cms-file-assessment-popup-save']");
            Thread.sleep(3000);
            new LoginUsingLTI().ltiLogin("60");
            new Navigator().NavigateTo("e-Textbook");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            //open Photosynthesis as a chapter
            new TopicOpen().chapterOpen(5);
            //open introduction
            new SelectCourse().selectInvisibleAssignment("Introduction");
            new Navigator().navigateToTab("Assignments");
            Thread.sleep(5000);
            new Assignment().openAssignmentFromAssignmentTab(2);
            Thread.sleep(3000);
            driver.findElement(By.xpath("//span[contains(@id,'close-assessment')]")).click();
            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
            new Assignment().assignAssignmentFromAssignmentTab(7,randomAssessment);
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElement(By.xpath("(//div[starts-with(@class,'ir-ls-assign-dialog-header')])[2]")).getText().contains(randomAssessment),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.AssignTO.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.gradable.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.gradingPolicy.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.accessibleDate.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.dueDate.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.additionalNotes.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.AssignButton.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.cancel.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            assignmentTab.gradable.click();
            Thread.sleep(2000);
            Assert.assertTrue(assignmentTab.totalPoints.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            assignmentTab.helpIconTotalPoints.click();
            Thread.sleep(1000);
            Assert.assertTrue(assignmentTab.helpIconTextTotalPoints.getText().contains("Enter the points available for the file type assignment."),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Thread.sleep(3000);

            new LoginUsingLTI().ltiLogin("60");

            new Navigator().NavigateTo("Assignments");
            driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();
            assignmentTab.usePrecreatedAssignmentButton.click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("(//a[text()='All Chapters'])[3]")).click();
            Thread.sleep(1000);
            driver.findElement(By.linkText("Ch 4: Cell Structure")).click();
            Thread.sleep(1000);
            int count=0;
            for (WebElement we:assignmentTab.assignmentList) {
                if(we.getText().contains(randomAssessment))
                    count =1;
            }
            if (count==1)
                Assert.fail("Exception in CreateAssessment in method editFileBasedAssessment");
            Thread.sleep(5000);
            driver.findElement(By.xpath("(//a[starts-with(@id,'sbToggle_')])[5]")).click();
            Thread.sleep(1000);
            driver.findElement(By.linkText("Ch 6: Metabolism")).click();
            Thread.sleep(5000);
            new Click().clickByid("all-resource-search-textarea");//Click on search text box
            new TextSend().textsendbyid(randomAssessment, "all-resource-search-textarea");//Type as assessment name which we would like to search
            Thread.sleep(1000);
            new Click().clickByid("all-resource-search-button");//Click on Search icon
            Thread.sleep(5000);
            List<WebElement> previewElementsList = driver.findElements(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            for(int a=0;a<previewElementsList.size();a++){
                if(previewElementsList.get(a).isDisplayed()){
                    previewElementsList.get(a).click();
                    break;
                }
            }
            Thread.sleep(2000);
            Assert.assertTrue(assignmentTab.tabs.get(2).getText().contains(randomAssessment),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            driver.findElement(By.xpath("//span[contains(@class,'assignment-icon')]/following::span[@class='close_tab']")).click();
            Thread.sleep(3000);
            List<WebElement> assignThisElementsList = driver.findElements(By.className("assign-this"));
            for(int a=0;a<assignThisElementsList.size();a++){
                System.out.println(a);
                if(assignThisElementsList.get(a).isDisplayed()){
                    assignThisElementsList.get(a).click();
                    break;
                }

            }
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ir-ls-assign-dialog-header']")).getText().contains(randomAssessment),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.AssignTO.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.gradable.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.gradingPolicy.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.accessibleDate.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.dueDate.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.additionalNotes.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.AssignButton.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.cancel.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            assignmentTab.gradable.click();
            Thread.sleep(2000);
            Assert.assertTrue(assignmentTab.totalPoints.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            assignmentTab.helpIconTotalPoints.click();
            Thread.sleep(1000);
            Assert.assertTrue(assignmentTab.helpIconTextTotalPoints.getText().contains("Enter the points available for the file type assignment."),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            new Click().clickbyxpath("(//a[starts-with(@id,'sbToggle_')])[5]");
            Assert.assertTrue(driver.findElements(By.xpath("//a[text()='ir-ls-assign-dialog']")).size()==0,"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            assignmentTab.addToMyQuestionBank.click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[@title='My Question Bank']")).click();
            Thread.sleep(1000);
            new Click().clickByid("my-resource-search-textarea");
            new TextSend().textsendbyid(randomAssessment,"my-resource-search-textarea");
            new Click().clickByid("my-resource-search-button");
            Thread.sleep(5000);

            Assert.assertTrue(assignmentTab.assignmentList.get(0).getText().contains(randomAssessment),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(driver.findElements(By.xpath("//span[@title='customize-this']")).size()==0,"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            questionBank.getAssignThisButtton().click();
            Thread.sleep(1000);
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ir-ls-assign-dialog-header']")).getText().contains(randomAssessment),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.AssignTO.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.gradable.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.gradingPolicy.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.accessibleDate.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.dueDate.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.additionalNotes.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.AssignButton.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.cancel.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            assignmentTab.gradable.click();
            Thread.sleep(2000);
            Assert.assertTrue(assignmentTab.totalPoints.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            assignmentTab.helpIconTotalPoints.click();
            Thread.sleep(1000);
            Assert.assertTrue(assignmentTab.helpIconTextTotalPoints.getText().contains("Enter the points available for the file type assignment."),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            new Click().clickByid("total-points");
            new TextSend().textsendbyid("2","total-points");
            Thread.sleep(1000);
            driver.findElement(By.id("due-time")).click();
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals(duetime)) {
                    time.click();
                    break;
                }
            }
            driver.findElement(By.id("due-date")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText(duedate)).click();
            Thread.sleep(2000);
            assignmentTab.AssignButton.click();
            new Navigator().NavigateTo("Dashboard");
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='middle']/p")).getText().contains(randomAssessment),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createFileBasedAssessment in class FileAssessmentAtLTILevel", e);
        }

        }


    @Test(priority = 2)
    public void questionVersing() {
        try {
            String randomAssessment="randomAssessmentVerifyAtLTI";
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(60));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(60));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(60));
            String secondFilename = ReadTestData.readDataByTagName("", "secondFilename", Integer.toString(60));
            ManageContent manageContent= PageFactory.initElements(driver,ManageContent.class);
            AssignmentTab assignmentTab= PageFactory.initElements(driver,AssignmentTab.class);
            LessonPage lessonPage= PageFactory.initElements(driver,LessonPage.class);
            WebDriverWait wait=new WebDriverWait(driver,2000);
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();//select Biology course
            new SelectCourse().selectChapterByIndex(5); //select first chapter
            Thread.sleep(9000);
            new Click().clickByclassname("create-practice");
            new Click().clickByclassname("create-file-assessment-popup-item");
            //new Click().clickbyxpath("//div[@title='"+randomAssessment+"']");
            Thread.sleep(9000);
            new Click().clickbyxpath("//a[@id='uploadFile']");
            Thread.sleep(2000);
            System.out.println("Config.fileUploadPath+secondFilename : "  +Config.fileUploadPath+secondFilename);
            new KeysSend().sendKeyBoardKeys("$"+ Config.fileUploadPath+secondFilename+"^");

            driver.findElement(By.xpath("//*[@id='cms-file-assessment-popup-title']")).sendKeys(randomAssessment);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ls-delete-file-icon']/following::span[@class='ls-delete-file-icon']")));
            new Click().clickbyxpath("//span[@class='cms-file-assessment-popup-save']");
            Thread.sleep(3000);
            new LoginUsingLTI().ltiLogin("60");
            new Navigator().NavigateTo("e-Textbook");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            //open Photosynthesis as a chapter
            new TopicOpen().chapterOpen(7);
            Thread.sleep(3000);
            //open introduction
            //driver.findElement(By.xpath("//div[contains(@class,'ls-lesson-left-nav')]")).click();
            lessonPage.getIntroductionUnderLesson().get(1).click();
            Thread.sleep(5000);
            driver.findElement(By.xpath("//span[@title='Assignments']"));
            Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ls_assessment_link']")).getText().contains(randomAssessment),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            assignmentTab.rightArrow.click();
            assignmentTab.getOpen_button.click();
            assignmentTab.cancelMark.click();
            assignmentTab.assignmentsTab.click();
            assignmentTab.rightArrow.click();
            assignmentTab.newTabUnderArrow.click();
            assignmentTab.assignThis.click();
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ir-ls-assign-dialog-header']")).getText().contains(randomAssessment),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");

            Assert.assertTrue(assignmentTab.AssignTO.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.gradable.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.gradingPolicy.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.accessibleDate.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.dueDate.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.additionalNotes.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.AssignButton.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.cancel.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(!assignmentTab.totalPoints.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            assignmentTab.gradable.click();
            Thread.sleep(2000);
            Assert.assertTrue(assignmentTab.totalPoints.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            assignmentTab.helpIconTotalPoints.click();
            Thread.sleep(1000);
            Assert.assertTrue(assignmentTab.helpIconTextTotalPoints.getText().contains("Enter the points available for the file type assignment."),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            new Navigator().NavigateTo("New Assignment");
            assignmentTab.usePrecreatedAssignmentButton.click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//a[text()='All Chapters']")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[text()='Ch 4: Cell Structure']")).click();
            Thread.sleep(1000);
            int count=0;
            for (WebElement we:assignmentTab.assignmentList) {
                if(we.getText().contains(randomAssessment))
                    count =1;
            }
            if (count==1)
                Assert.fail("Exception in CreateAssessment in method editFileBasedAssessment");
            assignmentTab.previewLink.click();
            Thread.sleep(2000);
            Assert.assertTrue(assignmentTab.tabs.get(2).getText().contains(randomAssessment),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            driver.findElement(By.xpath("//span[contains(@class,'assignment-icon')]/following::span[@class='close_tab']")).click();
            Thread.sleep(2000);
            assignmentTab.assignThis.click();
            Thread.sleep(1000);
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ir-ls-assign-dialog-header']")).getText().contains(randomAssessment),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.AssignTO.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.gradable.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.gradingPolicy.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.accessibleDate.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.dueDate.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.additionalNotes.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.AssignButton.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.cancel.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(!assignmentTab.totalPoints.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            assignmentTab.gradable.click();
            Thread.sleep(2000);
            Assert.assertTrue(assignmentTab.totalPoints.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            assignmentTab.helpIconTotalPoints.click();
            Thread.sleep(1000);
            Assert.assertTrue(assignmentTab.helpIconTextTotalPoints.getText().contains("Enter the points available for the file type assignment."),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            driver.findElement(By.xpath("//a[text()='Ch 4: Cell Structure']")).click();
            Assert.assertTrue(driver.findElements(By.xpath("//a[text()='ir-ls-assign-dialog']")).size()==0,"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            assignmentTab.addToMyQuestionBank.click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[@title='My Question Bank']")).click();
            Thread.sleep(1000);
            Assert.assertTrue(assignmentTab.assignmentList.get(0).getText().contains(randomAssessment),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(driver.findElements(By.xpath("//span[@title='customize-this']")).size()==0,"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            assignmentTab.assignThis.click();
            Thread.sleep(1000);
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ir-ls-assign-dialog-header']")).getText().contains(randomAssessment),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.AssignTO.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.gradable.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.gradingPolicy.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.accessibleDate.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.dueDate.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.additionalNotes.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.AssignButton.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.cancel.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            Assert.assertTrue(!assignmentTab.totalPoints.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            assignmentTab.gradable.click();
            Thread.sleep(2000);
            Assert.assertTrue(assignmentTab.totalPoints.isDisplayed(),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            assignmentTab.helpIconTotalPoints.click();
            Thread.sleep(1000);
            Assert.assertTrue(assignmentTab.helpIconTextTotalPoints.getText().contains("Enter the points available for the file type assignment."),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
            driver.findElement(By.id("due-time")).click();
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals(duetime)) {
                    time.click();
                    break;
                }
            }
            driver.findElement(By.id("due-date")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText(duedate)).click();
            Thread.sleep(2000);
            assignmentTab.AssignButton.click();
            new Navigator().NavigateTo("Dashboard");
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='middle']/p")).getText().contains(randomAssessment),"Exception in FileAssessmentAtLTILevel in method createFileBasedAssessment");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase publishQuestionChapterLevel in class checkQuestionWithMultipleReference", e);
        }

    }
}
