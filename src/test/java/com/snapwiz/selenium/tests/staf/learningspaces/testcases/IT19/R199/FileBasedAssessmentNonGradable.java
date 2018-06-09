package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R199;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by shashank on 26-02-2015.
 */
public class FileBasedAssessmentNonGradable extends Driver {
    @Test(priority = 1)
    public void fileBasedAssessmentNonGradable() {
        try {
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(61));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(61));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(61));
            String secondFilename = ReadTestData.readDataByTagName("", "secondFilename", Integer.toString(61));
            String randomAssessment="AssessmentWithFile";

            ManageContent manageContent= PageFactory.initElements(driver, ManageContent.class);
            AssignmentTab assignmentTab= PageFactory.initElements(driver,AssignmentTab.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage .class);
            LessonPage lessonPage= PageFactory.initElements(driver,LessonPage.class);
            WebDriverWait wait=new WebDriverWait(driver,500);
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();//select Biology course
            new SelectCourse().selectChapterByIndex(5); //select first chapter
            Thread.sleep(3000);
            manageContent.createPractice.click();//click on create practice
            manageContent.createFileBasedAssessment.click();//click on file based assessment
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='File Based Assessment']")));
            new Click().clickbyxpath("//a[@id='uploadFile']");
            Thread.sleep(2000);

            new KeysSend().sendKeyBoardKeys("$"+ Config.fileUploadPath+filename+"^");
            driver.findElement(By.xpath("//*[@id='cms-file-assessment-popup-title']")).sendKeys(randomAssessment);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ls-delete-file-icon']")));
            new Click().clickbyxpath("//span[@class='cms-file-assessment-popup-save']");
            Thread.sleep(3000);
            new LoginUsingLTI().ltiLogin("275");
            new LoginUsingLTI().ltiLogin("61");
            new Click().clickbyxpath(".//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            driver.findElement(By.linkText("Assignments")).click();//click on Assignments
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='New Assignment']")).isDisplayed(),"Exception in testcase createFileBasedAssessment in class FileBasedAssessmentNonGradable");
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Current Assignments']")).isDisplayed(),"Exception in testcase createFileBasedAssessment in class FileBasedAssessmentNonGradable");
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='My Question Bank']")).isDisplayed(),"Exception in testcase createFileBasedAssessment in class FileBasedAssessmentNonGradable");
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Policies']")).isDisplayed(),"Exception in testcase createFileBasedAssessment in class FileBasedAssessmentNonGradable");
            driver.findElement(By.linkText("New Assignment")).click();//click on Assignments
            Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Create Custom Assignment']")).isDisplayed(),"Exception in testcase createFileBasedAssessment in class FileBasedAssessmentNonGradable");
            Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Use Pre-created Assignment']")).isDisplayed(),"Exception in testcase createFileBasedAssessment in class FileBasedAssessmentNonGradable");
            Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Create File based Assignment']")).isDisplayed(),"Exception in testcase createFileBasedAssessment in class FileBasedAssessmentNonGradable");
            new Click().clickbyxpath("//div[text()='Use Pre-created Assignment']");
            Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ls_assessment_link']")).getText().contains(randomAssessment),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            assignmentTab.assignThis.click();
            Thread.sleep(1000);
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ir-ls-assign-dialog-header']")).getText().contains(randomAssessment),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.AssignTO.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.gradable.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.gradingPolicy.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.accessibleDate.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.dueDate.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.additionalNotes.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.AssignButton.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.cancel.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            //Assert.assertTrue(!assignmentTab.totalPoints.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            assignmentTab.gradable.click();
            Thread.sleep(2000);
            Assert.assertTrue(assignmentTab.totalPoints.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
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
            new Navigator().NavigateTo("Current Assignments");
            Assert.assertTrue(driver.findElement(By.xpath("//span[@class='ls-assignment-name instructor-assessment-review']")).getText().contains(randomAssessment),
                    "Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            new LoginUsingLTI().ltiLogin("275");
            new Navigator().NavigateTo("Assignments");
            assignments.getAssignmentName().click();
            driver.findElement(By.xpath("//a[@id='uploadFile']"));
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + secondFilename + "^");
            Thread.sleep(3000);
            Assert.assertTrue(assignmentResponsesPage.getUploadFileList().get(0).getText().contains(filename), "Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(assignmentResponsesPage.getUploadFileList().get(1).getText().contains(secondFilename), "Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            assignmentResponsesPage.getFinishAssignment().click();
            new LoginUsingLTI().ltiLogin("61");
            new Click().clickbyxpath(".//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            new Navigator().NavigateTo("Current Assignments");
            driver.findElement(By.xpath("//span[@title='View Student Responses']")).click();
            Thread.sleep(2000);
            Assert.assertTrue(assignmentResponsesPage.getViewResponse_link().getText().contains("100"),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Actions action=new Actions(driver);
            action.moveToElement(driver.findElement(By.className("idb-gradebook-question-content"))).build().perform();
            action.moveToElement(driver.findElement(By.className("ls-view-response-link"))).click().build().perform();
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("Instructor side respose");
            assignmentResponsesPage.getSaveButton().click();
            Assert.assertTrue(assignmentResponsesPage.getSavedSuccessfullyMessage().getText().contains("Saved successfully."),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            new Click().clickbyxpath("//span[@id='close-view-responses']");
            Thread.sleep(2000);
            assignmentResponsesPage.getReleaseFeedbackForAll().click();
            Assert.assertTrue(assignmentResponsesPage.getReviewStatus().getText().contains("Reviewed"),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(driver.findElement(By.xpath("//div[@title='Feedback Released']")).getText().contains("Feedback Released"),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            new LoginUsingLTI().ltiLogin("275");
            new Navigator().NavigateTo("Assignments");
            new Click().clickBycssselector("span.ls-assignment-name instructor-assessment-review");
            Assert.assertTrue(driver.findElements(By.xpath("//a[@id='uploadFile']")).size()==0,"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");





        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createFileBasedAssessment in class FileBasedAssessmentNonGradable", e);
        }
    }


    @Test(priority = 2)
    public void fileBasedAssessmentGradable() {
        try {
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(61));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(61));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(61));
            String secondFilename = ReadTestData.readDataByTagName("", "secondFilename", Integer.toString(61));
            String randomAssessment="AssessmentWithFile";
            ManageContent manageContent= PageFactory.initElements(driver, ManageContent.class);
            AssignmentTab assignmentTab= PageFactory.initElements(driver,AssignmentTab.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage .class);
            LessonPage lessonPage= PageFactory.initElements(driver,LessonPage.class);
            WebDriverWait wait=new WebDriverWait(driver,2000);
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();//select Biology course
            new SelectCourse().selectChapterByIndex(5); //select first chapter
            Thread.sleep(3000);
            manageContent.createPractice.click();//click on create practice
            manageContent.createFileBasedAssessment.click();//click on file based assessment
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='File Based Assessment']")));
            new Click().clickbyxpath("//a[@id='uploadFile']");
            Thread.sleep(2000);

            new KeysSend().sendKeyBoardKeys("$"+ Config.fileUploadPath+filename+"^");
            driver.findElement(By.xpath("//*[@id='cms-file-assessment-popup-title']")).sendKeys(randomAssessment);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ls-delete-file-icon']")));
            new Click().clickbyxpath("//span[@class='cms-file-assessment-popup-save']");
            Thread.sleep(3000);
            new LoginUsingLTI().ltiLogin("275");
            new LoginUsingLTI().ltiLogin("61");
            new Click().clickbyxpath(".//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            driver.findElement(By.linkText("Assignments")).click();//click on Assignments
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='New Assignment']")).isDisplayed(),"Exception in testcase createFileBasedAssessment in class FileBasedAssessmentNonGradable");
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Current Assignments']")).isDisplayed(),"Exception in testcase createFileBasedAssessment in class FileBasedAssessmentNonGradable");
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='My Question Bank']")).isDisplayed(),"Exception in testcase createFileBasedAssessment in class FileBasedAssessmentNonGradable");
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Policies']")).isDisplayed(),"Exception in testcase createFileBasedAssessment in class FileBasedAssessmentNonGradable");
            driver.findElement(By.linkText("New Assignment")).click();//click on Assignments
            Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Create Custom Assignment']")).isDisplayed(),"Exception in testcase createFileBasedAssessment in class FileBasedAssessmentNonGradable");
            Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Use Pre-created Assignment']")).isDisplayed(),"Exception in testcase createFileBasedAssessment in class FileBasedAssessmentNonGradable");
            Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Create File based Assignment']")).isDisplayed(),"Exception in testcase createFileBasedAssessment in class FileBasedAssessmentNonGradable");
            new Click().clickbyxpath("//div[text()='Use Pre-created Assignment']");
            Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ls_assessment_link']")).getText().contains(randomAssessment),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            assignmentTab.assignThis.click();
            Thread.sleep(1000);
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ir-ls-assign-dialog-header']")).getText().contains(randomAssessment),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.AssignTO.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.gradable.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.gradingPolicy.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.accessibleDate.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.dueDate.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.additionalNotes.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.AssignButton.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(assignmentTab.cancel.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(!assignmentTab.totalPoints.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            assignmentTab.gradable.click();
            Thread.sleep(2000);
            Assert.assertTrue(assignmentTab.totalPoints.isDisplayed(),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
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
            new Navigator().NavigateTo("Current Assignments");
            Assert.assertTrue(driver.findElement(By.xpath("//span[@class='ls-assignment-name instructor-assessment-review']")).getText().contains(randomAssessment),
                    "Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            new LoginUsingLTI().ltiLogin("275");
            new Navigator().NavigateTo("Assignments");
            assignments.getAssignmentName().click();
            driver.findElement(By.xpath("//a[@id='uploadFile']"));
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + secondFilename + "^");
            Thread.sleep(3000);
            Assert.assertTrue(assignmentResponsesPage.getUploadFileList().get(0).getText().contains(filename), "Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(assignmentResponsesPage.getUploadFileList().get(1).getText().contains(secondFilename), "Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            assignmentResponsesPage.getFinishAssignment().click();
            new LoginUsingLTI().ltiLogin("61");
            new Click().clickbyxpath(".//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            new Navigator().NavigateTo("Current Assignments");
            driver.findElement(By.xpath("//span[@title='View Student Responses']")).click();
            Thread.sleep(2000);
            Assert.assertTrue(assignmentResponsesPage.getViewResponse_link().getText().contains("100"),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Actions action=new Actions(driver);
            action.moveToElement(driver.findElement(By.className("idb-gradebook-question-content"))).build().perform();
            action.moveToElement(driver.findElement(By.className("ls-view-response-link"))).click().build().perform();
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("Instructor side respose");
            assignmentResponsesPage.getSaveButton().click();
            Assert.assertTrue(assignmentResponsesPage.getSavedSuccessfullyMessage().getText().contains("Saved successfully."),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            new Click().clickbyxpath("//span[@id='close-view-responses']");
            Thread.sleep(2000);
            assignmentResponsesPage.getReleaseFeedbackForAll().click();
            Assert.assertTrue(assignmentResponsesPage.getReviewStatus().getText().contains("Reviewed"),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            Assert.assertTrue(driver.findElement(By.xpath("//div[@title='Feedback Released']")).getText().contains("Feedback Released"),"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");
            new LoginUsingLTI().ltiLogin("275");
            new Navigator().NavigateTo("Assignments");
            new Click().clickBycssselector("span.ls-assignment-name instructor-assessment-review");
            Assert.assertTrue(driver.findElements(By.xpath("//a[@id='uploadFile']")).size()==0,"Exception in FileBasedAssessmentNonGradable in method createFileBasedAssessment");





        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createFileBasedAssessment in class FileBasedAssessmentNonGradable", e);
        }
    }
    @Test(priority = 2)
    public void deleteFileBasedAssessment() {
        try {
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(61));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(61));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(61));
            String secondFilename = ReadTestData.readDataByTagName("", "secondFilename", Integer.toString(61));
            String randomAssessment = "DeleteAssessmentWithFile";
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            WebDriverWait wait = new WebDriverWait(driver, 2000);
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();//select Biology course
            new SelectCourse().selectChapterByIndex(5); //select first chapter
            Thread.sleep(3000);
            manageContent.createPractice.click();//click on create practice
            manageContent.createFileBasedAssessment.click();//click on file based assessment
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='File Based Assessment']")));
            new Click().clickbyxpath("//a[@id='uploadFile']");
            Thread.sleep(2000);

            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            driver.findElement(By.xpath("//*[@id='cms-file-assessment-popup-title']")).sendKeys(randomAssessment);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ls-delete-file-icon']")));
            new Click().clickbyxpath("//span[@class='cms-file-assessment-popup-save']");
            Thread.sleep(3000);
            Actions action=new Actions(driver);
            action.moveToElement(driver.findElement(By.xpath("//div[@title='"+randomAssessment+"']"))).build().perform();
            action.moveToElement(driver.findElement(By.xpath("//div[@title='"+randomAssessment+"/following::span[@class='delete-category']']"))).click().build().perform();
            Assert.assertTrue(driver.findElements(By.xpath("//span[text(),'Yes, delete the assessment']")).size()==1,"Exception in CreateAssessment in method createFileBasedAssessment");
            new Click().clickbyxpath("//span[text(),'Yes, delete the assessment']");



        } catch (Exception e) {
            Assert.fail("Exception in testcase createFileBasedAssessment in class FileBasedAssessmentNonGradable", e);
        }
    }
}
