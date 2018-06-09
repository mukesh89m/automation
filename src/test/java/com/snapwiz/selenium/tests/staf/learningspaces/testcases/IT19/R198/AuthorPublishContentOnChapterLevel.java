package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R198;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Lesson;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Summary;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
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

/**
 * Created by shashank on 28-01-2015.
 */
public class AuthorPublishContentOnChapterLevel extends Driver {

    int count=0;
    int tempArray[]=new  int[8];

    @Test(priority = 1,enabled = true)
    public void publishQuestionChapterLevel() {
        try {
            BooleanValue booleanValue=new BooleanValue();
            Lesson lesson= PageFactory.initElements(driver,Lesson.class);
            ManageContent manageContent=PageFactory.initElements(driver,ManageContent.class);
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();

            //check publish icon should not display for section  use case-19.8.20
            manageContent.expandChapter.click();
            Thread.sleep(500);
            manageContent.sectionUnderChapter.click();
            if(booleanValue.isElementPresent(manageContent.publishIcon)) {
                Assert.fail("Publish icon is present on the screen");
            }

            new SelectCourse().selectChapterByIndex(0);
            new Click().clickBycssselector("span[class='publish-chapter-lesson-icon publish-chapter-lesson']");
            String data=lesson.publishQuestion_title.getText().trim();
            Assert.assertEquals(data, "Publish option for this Chapter", "Error");
            //Verify text under lessons
            Assert.assertTrue((lesson.checkBoxDraftStatusLesson).getText().contains("Draft status"), "not able to locate text");
            Assert.assertTrue((lesson.checkBoxDraftPendingImagesStatusLesson).getText().contains("Draft - Pending Images status"), "not able to locate text");
            Assert.assertTrue((lesson.checkBoxAccuracyCheckStatusLesson).getText().contains("Accuracy Check status"), "not able to locate text");
            Assert.assertTrue((lesson.checkBoxQAStatusLesson).getText().contains("QA status"), "not able to locate text");
            Assert.assertTrue((lesson.checkBoxRevisionStatusLesson).getText().contains("Need Revision status"), "not able to locate text");
            Assert.assertTrue((lesson.checkBoxApproveStatusLesson).getText().contains("Approve status"), "not able to locate text");
            Assert.assertTrue((lesson.checkBoxReadyToPublishStatusLesson).getText().contains("Ready to Publish status"), "not able to locate text");
            Assert.assertTrue((lesson.checkBoxPublishStatusLesson).getText().contains("Publish status"), "not able to locate text");
            lesson.questionTabUnderPublish.click();
            WebDriverWait wait = new WebDriverWait(driver, 500);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Questions')]")));
            //Verify text under lessons
            Assert.assertTrue((lesson.checkBoxDraftStatusQuestion).getText().contains("Draft status"), "not able to locate text");
            Assert.assertTrue((lesson.checkBoxDraftPendingImagesStatusQuestion).getText().contains("Draft - Pending Images status"), "not able to locate text");
            Assert.assertTrue((lesson.checkBoxAccuracyCheckStatusQuestion).getText().contains("Accuracy Check status"), "not able to locate text");
            Assert.assertTrue((lesson.checkBoxQAStatusQuestion).getText().contains("QA status"), "not able to locate text");
            Assert.assertTrue((lesson.checkBoxRevisionStatusQuestion).getText().contains("Need Revision status"), "not able to locate text");
            Assert.assertTrue((lesson.checkBoxApproveStatusQuestion).getText().contains("Approve status"), "not able to locate text");
            Assert.assertTrue((lesson.checkBoxReadyToPublishStatusQuestion).getText().contains("Ready to Publish status"), "not able to locate text");
            Assert.assertTrue((lesson.checkBoxPublishStatusQuestion).getText().contains("Publish status"), "not able to locate text");

            int status=10;
            int checkBoxIndex=0;
            //verify CheckBox disabled where question is Zero
            //verify Questions text present when questions count is more than One
            for(int i=0;i<8;i++) {
               checkBoxIndex= checkDisableCheckBox(driver.findElement(By.xpath("//input[@status='"+status+"' and @class='question-publish-status-check']/following-sibling::span")).getText(),status);
                status+=10;
            }
            //click on checkbox
            driver.findElement(By.xpath("//input[@status='"+checkBoxIndex+"' and @class='question-publish-status-check']")).click();
            //click on publish button
            new Click().clickBycssselector("span[class='btn sty-green save publish-chapter-questions']");
            Thread.sleep(200);
            //verify the alert message

                String notification=driver.findElement(By.xpath("//div[contains(@class,'cms-notification-message-body')]/div")).getText();
                String notificationMessage1="One of the questions in the list is a question that is referenced in multiple assessments/courses. Do you want to publish to all courses?";
            String notificationMessage2="You are about to publish selected questions. Do you want to continue?";

            if(!notification.equals(notificationMessage1) && !notification.equals(notificationMessage2) )
                Assert.fail("Notification not matching");

            //click on cancel
            new Click().clickbyxpath("//span[contains(@class,'cms-notification-message-back-to-question cms-notification-cancel-publish-content')]");
            Thread.sleep(200);
            Assert.assertEquals(driver.findElements(By.xpath("//div[contains(@class,'cms-notification-message-body')]/div")).size(), 0, "Error");
            //click on publish button
            new Click().clickBycssselector("span[class='btn sty-green save publish-chapter-questions']");
            Thread.sleep(200);
            //publish the questions
            new Click().clickBycssselector("span[class='cms-notification-message-ignore-changes cms-notification-yes-publish-content']");
            Thread.sleep(5000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='cms-notification-message-wrapper']")));
            Assert.assertEquals(driver.findElements(By.xpath("//span[contains(text(),'Questions')]")).size(), 0, "Error");
        }
        catch (Exception e) {
            Assert.fail("Exception in testcase publishQuestionChapterLevel in class AuthorPublishContentOnChapterLevel",e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void manageContentChapterLevel() {

        try {
            BooleanValue booleanValue=new BooleanValue();
            Lesson lesson= PageFactory.initElements(driver,Lesson.class);
            Summary summary=PageFactory.initElements(driver,Summary.class);
            new OpenSearchPage().openSearchPage(); //open search page  through cms
            new OpenSearchPage().searchquestion("true false");
            WebDriverWait wait = new WebDriverWait(driver, 10);
           // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'The Study of Life')]")));
            Actions action=new Actions(driver);
            action.moveToElement( summary.reviewQuestion).build().perform();
            action.moveToElement( summary.quickReviewExpand).click().build().perform();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Status:']")));
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Status:']/following-sibling::span")).getText().contains("Publish"), "not able to locate text");
            new Click().clickByid("tab-browse");
            //select Search Questions in select Content Type dropdown
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Select Content Type']")));
            new Click().clickbyxpath("//a[text()='Select Content Type']");
            Thread.sleep(200);
            new Click().clickbyxpath("//a[text()='Search Questions']");
            //select Ch 1: The Study of Life option in Select a option dropdown
            new Click().clickbyxpath("//a[text()='Select an option']");
            Thread.sleep(1000);
            new Click().clickbylinkText("Ch 1: The Study of Life");
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'content-search-results-block')]")));
            action.moveToElement(summary.chepterNameOnSummary).build().perform();
            action.moveToElement( summary.quickReviewExpand).click().build().perform();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Status:']")));
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Status:']/following-sibling::span")).getText().contains("Publish"), "not able to locate text");
            new Click().clickbyxpath("//div[contains(@class,'sbHolder')]/a[text()='Select Status']");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",summary.publish_dropdown);
            Thread.sleep(500);
            summary.publish_dropdown.click();
            UIElement uiElement=new UIElement();
            Thread.sleep(2000);
            action.moveToElement(summary.reviewQuestion).build().perform();
            action.moveToElement( summary.quickReviewExpand).click().build().perform();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Status:']")));
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Status:']/following-sibling::span")).getText().contains("Publish"), "not able to locate text");
            //capture count
            //*[contains(@class,'highcharts-title')]*//*[text()='Questions']/preceding-sibling::*
            summary.summary.click();

            uiElement.waitAndFindElement(By.xpath("//a[text()='Difficulty Level Count']"));
            summary.difficultyLevelCountDropdown.click();
            Thread.sleep(500);
            summary.questionStatusCountDropdown.click();
            int draftCount =0;
            int draftPendingImagesCount=0;
            int accuracyCheckCount=0;
            int qaCount=0;
            int needRevisionCount=0;
            int approveCount=0;
            int readyToPublishCount=0;
            int publishCount=0;

            int questionCount=Integer.parseInt(summary.fetchQuestionCount.getText());
             if(booleanValue.isElementPresent(summary.fetchDraftsCount)) {
                  draftCount = Integer.parseInt(summary.fetchDraftsCount.getText());
             }
            if(booleanValue.isElementPresent(summary.fetchDraftPendingImagesCount)) {
                draftPendingImagesCount = Integer.parseInt(summary.fetchDraftPendingImagesCount.getText());
            }
            if(booleanValue.isElementPresent(summary.fetchAccuracyCheckCount)) {
                accuracyCheckCount = Integer.parseInt(summary.fetchAccuracyCheckCount.getText());
            }
            if(booleanValue.isElementPresent(summary.fetchQACount)) {
                qaCount = Integer.parseInt(summary.fetchQACount.getText());
            }
            if(booleanValue.isElementPresent(summary.fetchNeedRevisionCount)) {
                needRevisionCount = Integer.parseInt(summary.fetchNeedRevisionCount.getText());
            }
            if(booleanValue.isElementPresent(summary.fetchApproveCount)) {
                approveCount = Integer.parseInt(summary.fetchApproveCount.getText());
            }
            if(booleanValue.isElementPresent(summary.fetchReadyToPublishCount)) {
                     readyToPublishCount = Integer.parseInt(summary.fetchReadyToPublishCount.getText());
            }
            if(booleanValue.isElementPresent(summary.fetchPublishCount)) {
                publishCount = Integer.parseInt(summary.fetchPublishCount.getText());
            }
            System.out.println(questionCount);
            //Create question
            new Assignment().create(38);
            new Assignment().create(39);
            new Assignment().create(40);
            new Assignment().create(41);
            new Assignment().create(42);
            new Assignment().create(43);
            new Assignment().create(44);
            new Assignment().create(45);

            Thread.sleep(1000);

            summary.summary.click();
            uiElement.waitAndFindElement(By.xpath("//div[@id='refresh-publish-content']"));
            summary.refreshSummaryCount.click();
            Thread.sleep(18000);
            summary.difficultyLevelCountDropdown.click();
            Thread.sleep(500);
            summary.questionStatusCountDropdown.click();
            Thread.sleep(500);
            summary.questionStatusCountDropdown.click();
            Thread.sleep(500);
            summary.difficultyLevelCountDropdown.click();
            Thread.sleep(500);

            int questionCountAfterRefresh=Integer.parseInt(summary.fetchQuestionCount.getText());

            int draftCountAfterRefresh=Integer.parseInt(summary.fetchDraftsCount.getText());
            int draftPendingImagesCountAfterRefresh=Integer.parseInt(summary.fetchDraftPendingImagesCount.getText());
            int accuracyCheckCountAfterRefresh=Integer.parseInt(summary.fetchAccuracyCheckCount.getText());
            int qaCountAfterRefresh=Integer.parseInt(summary.fetchQACount.getText());
            int needRevisionCountAfterRefresh=Integer.parseInt(summary.fetchNeedRevisionCount.getText());
            int approveCountAfterRefresh=Integer.parseInt(summary.fetchApproveCount.getText());
            int readyToPublishCountAfterRefresh=Integer.parseInt(summary.fetchReadyToPublishCount.getText());
            int publishCountAfterRefresh=Integer.parseInt(summary.fetchPublishCount.getText());


            if(questionCountAfterRefresh<=questionCount || draftCountAfterRefresh<=draftCount || draftPendingImagesCountAfterRefresh<=draftPendingImagesCount
                    || accuracyCheckCountAfterRefresh<=accuracyCheckCount || qaCountAfterRefresh<=qaCount || needRevisionCountAfterRefresh<=needRevisionCount
                    || approveCountAfterRefresh<=approveCount || readyToPublishCountAfterRefresh<=readyToPublishCount || publishCountAfterRefresh<=publishCount )
            {
                Assert.fail("Questions count are not updating properly");
            }

            //click on pie to view bar graph
            Thread.sleep(500);
            summary.fetchQuestionCount.click();
            uiElement.waitAndFindElement(By.xpath("//div[text()='Ch1']"));
            summary.chapterUnderGraph.click();
            uiElement.waitAndFindElement(By.xpath("/*//*[text()='Objectives']"));
            System.out.println(summary.chapterUnderGraph.getLocation().getX());
            System.out.println(summary.chapterUnderGraph.getLocation().getY());
            Robot robot=new Robot();
            robot.mouseMove(summary.chapterUnderGraph.getLocation().getX(),summary.chapterUnderGraph.getLocation().getY()-6);
     //       action.moveToElement( summary.showQuestions).click().build().perform();
            ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('cms-ccs-show-questions')[0].click();");
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Edit Question')]")));
            String questionText=summary.getQuestionText.getText();
            summary.editQuestion.click();
            Thread.sleep(1000);
            summary.newVersion.click();
            Thread.sleep(500);
            summary.revision.click();
            Thread.sleep(500);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", summary.createNewVersion);
            Thread.sleep(1000);
            summary.saveButton.click();
            Thread.sleep(1000);
            summary.summary.click();
            Thread.sleep(1000);
            new SelectCourse().selectChapterByIndex(0);
            new Click().clickBycssselector("span[class='publish-chapter-lesson-icon publish-chapter-lesson']");
            lesson.questionTabUnderPublish.click();
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Questions')]")));
            String textAfterFirstCheckBox=element.getText();
            int draftStatusCount=Integer.parseInt(textAfterFirstCheckBox.substring(0,textAfterFirstCheckBox.indexOf(" ")));
            Assert.assertTrue(draftStatusCount>0,"Draft Question count dose not match");


        }
        catch (Exception e) {
            Assert.fail("Exception in test case summary ChapterLevel in class AuthorPublishContentOnChapterLevel",e);
        }
    }

    @Test(priority = 3,enabled = true)//test case 19.8.16
    public void publishAllTypeOfQuestions() {

        try {

            Lesson lesson= PageFactory.initElements(driver,Lesson.class);
            ManageContent manageContent=PageFactory.initElements(driver,ManageContent.class);
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            new SelectCourse().selectChapterByIndex(0);
            new Click().clickBycssselector("span[class='publish-chapter-lesson-icon publish-chapter-lesson']");
            lesson.questionTabUnderPublish.click();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Questions')]")));
            int draftQuestionCount=Integer.parseInt( manageContent.getDraftQuestionCount.getText().substring(0, manageContent.getDraftQuestionCount.getText().indexOf(" ")));
            int draftPendingImagesQuestionCount=Integer.parseInt( manageContent.getDraftPendingImagesQuestionCount.getText().substring(0, manageContent.getDraftPendingImagesQuestionCount.getText().indexOf(" ")));
            int accuracyCheckQuestionCount=Integer.parseInt( manageContent.getAccuracyCheckQuestionCount.getText().substring(0, manageContent.getAccuracyCheckQuestionCount.getText().indexOf(" ")));
            int qaQuestionCount=Integer.parseInt( manageContent.getQAQuestionCount.getText().substring(0, manageContent.getQAQuestionCount.getText().indexOf(" ")));
            int needRevisionQuestionCount=Integer.parseInt( manageContent.getNeedRevisionQuestionCount.getText().substring(0, manageContent.getNeedRevisionQuestionCount.getText().indexOf(" ")));
            int approveQuestionCount=Integer.parseInt( manageContent.getApproveQuestionCount.getText().substring(0, manageContent.getApproveQuestionCount.getText().indexOf(" ")));
            int readyToPublishQuestionCount=Integer.parseInt( manageContent.getReadyToPublishQuestionCount.getText().substring(0, manageContent.getReadyToPublishQuestionCount.getText().indexOf(" ")));
            int publishCount=Integer.parseInt( manageContent.getPublishQuestionCount.getText().substring(0, manageContent.getPublishQuestionCount.getText().indexOf(" ")));
            //Create question
            new Assignment().create(38);
            new Assignment().create(39);
            new Assignment().create(40);
            new Assignment().create(41);
            new Assignment().create(42);
            new Assignment().create(43);
            new Assignment().create(44);
            new Assignment().create(45);
            manageContent.manageContent.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='COURSE OUTLINE']")));
            new SelectCourse().selectChapterByIndex(0);
            new Click().clickBycssselector("span[class='publish-chapter-lesson-icon publish-chapter-lesson']");
            lesson.questionTabUnderPublish.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Questions')]")));
            int draftQuestionCountAfter=Integer.parseInt( manageContent.getDraftQuestionCount.getText().substring(0, manageContent.getDraftQuestionCount.getText().indexOf(" ")));
            int draftPendingImagesQuestionCountAfter=Integer.parseInt( manageContent.getDraftPendingImagesQuestionCount.getText().substring(0, manageContent.getDraftPendingImagesQuestionCount.getText().indexOf(" ")));
            int accuracyCheckQuestionCountAfter=Integer.parseInt( manageContent.getAccuracyCheckQuestionCount.getText().substring(0, manageContent.getAccuracyCheckQuestionCount.getText().indexOf(" ")));
            int qaQuestionCountAfter=Integer.parseInt( manageContent.getQAQuestionCount.getText().substring(0, manageContent.getQAQuestionCount.getText().indexOf(" ")));
            int needRevisionQuestionCountAfter=Integer.parseInt( manageContent.getNeedRevisionQuestionCount.getText().substring(0, manageContent.getNeedRevisionQuestionCount.getText().indexOf(" ")));
            int approveQuestionCountAfter=Integer.parseInt( manageContent.getApproveQuestionCount.getText().substring(0, manageContent.getApproveQuestionCount.getText().indexOf(" ")));
            int readyToPublishQuestionCountAfter=Integer.parseInt( manageContent.getReadyToPublishQuestionCount.getText().substring(0, manageContent.getReadyToPublishQuestionCount.getText().indexOf(" ")));
            int publishCountAfter=Integer.parseInt( manageContent.getPublishQuestionCount.getText().substring(0, manageContent.getPublishQuestionCount.getText().indexOf(" ")));
            if(draftQuestionCount>=draftQuestionCountAfter && draftPendingImagesQuestionCount>=draftPendingImagesQuestionCountAfter && accuracyCheckQuestionCount>=accuracyCheckQuestionCountAfter
                    &&qaQuestionCount>=qaQuestionCountAfter &&needRevisionQuestionCount>=needRevisionQuestionCountAfter && approveQuestionCount>=accuracyCheckQuestionCountAfter
                    && readyToPublishQuestionCount>=readyToPublishQuestionCountAfter && publishCount>=publishCountAfter)
                Assert.fail("Question count not get increase after Creating question");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case summary ChapterLevel in class manageContentChapterLevel",e);
        }
    }


    public int checkDisableCheckBox(String text,int status)
    {
        int number=Integer.parseInt(text.substring(0,text.indexOf(" ")));
        if(number==0)
        {
            Assert.assertEquals(driver.findElement(By.xpath("//input[@status='"+status+"' and @class='question-publish-status-check']")).getAttribute("disabled"), "true", "Error");
        }
        else
        {
            tempArray[count]=status;
            count++;
        }
        if(number>1)
        {
            Assert.assertTrue(driver.findElement(By.xpath("//input[@status='"+status+"' and @class='question-publish-status-check']/following-sibling::span")).getText().contains("Questions"), "not able to locate questions in the text");
        }
        return tempArray[0];

    }
}
