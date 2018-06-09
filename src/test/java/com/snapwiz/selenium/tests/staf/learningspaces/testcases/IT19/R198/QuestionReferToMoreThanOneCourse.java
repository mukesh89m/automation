package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R198;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.OpenSearchPage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Questions;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Lesson;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Summary;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shashank on 02-02-2015.
 */
public class QuestionReferToMoreThanOneCourse extends Driver {
    String questionName = "True False multiple reference of same Question21";

    @Test(priority = 1)
    public void checkQuestionWithMultipleReference() {
        try {
            String lscourse=Config.lscourse;
            Lesson lesson = PageFactory.initElements(driver, Lesson.class);
            Summary summary = PageFactory.initElements(driver, Summary.class);
            BooleanValue booleanValue = new BooleanValue();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Questions questions = new Questions();
            int dataIndex = 52;
            //Create question
            new Assignment().create(52);
            //Navigate to search screen
            new Click().clickByid("content-search-icon");
            //Search question created above
            new OpenSearchPage().searchquestion(questionName);
            //copy question to another course
            questions.copySecondQuestionWithOutReferenceToOtherCourse(0);
            //navigate to another course
            new Click().clickbyxpath("//div[text()='Change']");
            Thread.sleep(1000);
            new SelectCourse().selectLSCourse();
            //select chapter
            new SelectCourse().selectchapter("Ch 2A: The Russian Realm");
            //click on publish icon
            new Click().clickBycssselector("span[class='publish-chapter-lesson-icon publish-chapter-lesson']");
            Thread.sleep(1000);
            //click on question tab
            lesson.questionTabUnderPublish.click();
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Questions')]")));
            //click on first checkbox
            new Click().clickbyxpath("//input[@status='10' and @class='question-publish-status-check']");
            //publish question
            new Click().clickBycssselector("span[class='btn sty-green save publish-chapter-questions']");
            Thread.sleep(200);
            new Click().clickBycssselector("span[class='cms-notification-message-ignore-changes cms-notification-yes-publish-content']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'All selected questions for this chapter have been published')]")));
            //click on publish icon
            new Click().clickBycssselector("span[class='publish-chapter-lesson-icon publish-chapter-lesson']");
            Thread.sleep(1000);
            //click on question tab
            lesson.questionTabUnderPublish.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Questions')]")));
            //get count of number of question in Draft as well as Publish
            String draftCountText = lesson.checkBoxDraftStatusQuestion.getText();
            String publishCountText = lesson.checkBoxPublishStatusQuestion.getText();
            int draftCount = Integer.parseInt(draftCountText.substring(0, draftCountText.indexOf(" ")));
            int publishCount = Integer.parseInt(publishCountText.substring(0, publishCountText.indexOf(" ")));
            //navigate to particular assessment
            new SelectCourse().selectAssessmentByIndex(0);
            Thread.sleep(500);
            wait.until(ExpectedConditions.elementToBeClickable(manageContent.jumpToQuestion));
            manageContent.jumpToQuestion.click();
            //get count of total number of question
            int questionCount = manageContent.totalNumberOfQuestions.size() / 2;
            //click on last question
            new Click().clickbyxpath("//div[@class='overview']/following::li[" + questionCount + "]/a");
            Thread.sleep(2000);
            //check question name and status
            Assert.assertTrue(manageContent.fetchQuestionDescription.getText().contains(questionName), "Question name dose not match");
            Assert.assertTrue(booleanValue.isElementPresent(manageContent.disabledSaveButton), "Question status is not publish");
            //create new version of same question
            summary.newVersion.click();
            Thread.sleep(500);
            summary.revision.click();
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", summary.createNewVersion);
            Thread.sleep(1000);
            summary.saveButton.click();
            Thread.sleep(1000);
            manageContent.manageContent.click();
            wait.until(ExpectedConditions.elementToBeClickable(manageContent.textAssociatedContent));
            new SelectCourse().selectchapter("Ch 2A: The Russian Realm");
            //click on publish icon
            new Click().clickBycssselector("span[class='publish-chapter-lesson-icon publish-chapter-lesson']");
            Thread.sleep(1000);
            //click on question tab
            lesson.questionTabUnderPublish.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Questions')]")));
            //get count of number of question in Draft as well as Publish
            String draftCountTextNewVersion = lesson.checkBoxDraftStatusQuestion.getText();
            String publishCountTextNewVersion = lesson.checkBoxPublishStatusQuestion.getText();
            int draftCountAfterNewVersion = Integer.parseInt(draftCountText.substring(0, draftCountText.indexOf(" ")));
            int publishCountAfterNewVersion = Integer.parseInt(publishCountText.substring(0, publishCountText.indexOf(" ")));

            if (draftCount < draftCountAfterNewVersion && publishCount > publishCountAfterNewVersion)
                Assert.fail("Questions count are not updating properly");

            new SelectCourse().selectAssessmentByIndex(0);
            Thread.sleep(500);
            wait.until(ExpectedConditions.elementToBeClickable(manageContent.jumpToQuestion));
            manageContent.jumpToQuestion.click();
            //click on last question
            new Click().clickbyxpath("//div[@class='overview']/following::li[" + questionCount + "]/a");
            Thread.sleep(2000);
            //change status to publish
            new Click().clickbyxpath("//a[contains(text(),'Draft')]");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", summary.publish_dropdown);
            Thread.sleep(500);
            summary.publish_dropdown.click();
            Thread.sleep(500);
            summary.saveButton.click();
            Thread.sleep(2000);
            manageContent.manageContent.click();
            wait.until(ExpectedConditions.elementToBeClickable(manageContent.textAssociatedContent));
            new SelectCourse().selectchapter("Ch 2A: The Russian Realm");
            //click on publish icon
            new Click().clickBycssselector("span[class='publish-chapter-lesson-icon publish-chapter-lesson']");
            Thread.sleep(1000);
            //click on question tab
            lesson.questionTabUnderPublish.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Questions')]")));
            //get count of number of question in Draft as well as Publish
            String draftCountTextAfterPublish = lesson.checkBoxDraftStatusQuestion.getText();
            String publishCountTextAfterPublish = lesson.checkBoxPublishStatusQuestion.getText();
            int draftCountAfterPublish = Integer.parseInt(draftCountText.substring(0, draftCountText.indexOf(" ")));
            int publishCountAfterPublish = Integer.parseInt(publishCountText.substring(0, publishCountText.indexOf(" ")));

            if (draftCountAfterPublish < draftCountAfterNewVersion && publishCountAfterPublish > publishCountAfterNewVersion)
                Assert.fail("Questions count are not updating properly");

            //deactivate the question
            new SelectCourse().selectAssessmentByIndex(0);
            Thread.sleep(500);
            wait.until(ExpectedConditions.elementToBeClickable(manageContent.jumpToQuestion));
            manageContent.jumpToQuestion.click();
            //click on last question
            new Click().clickbyxpath("//div[@class='overview']/following::li[" + questionCount + "]/a");
            Thread.sleep(2000);
            summary.newVersion.click();
            Thread.sleep(500);
            summary.revision.click();
            Thread.sleep(500);
            summary.deactivateButton.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Deactivated']")));
            //navigate to manage content page
            Thread.sleep(1000);
            manageContent.manageContent.click();
            wait.until(ExpectedConditions.elementToBeClickable(manageContent.textAssociatedContent));
            new SelectCourse().selectchapter("Ch 2A: The Russian Realm");
            //click on publish icon
            new Click().clickBycssselector("span[class='publish-chapter-lesson-icon publish-chapter-lesson']");
            Thread.sleep(1000);
            //click on question tab
            lesson.questionTabUnderPublish.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Questions')]")));
            //get count of number of question in Draft as well as Publish
            String publishCountTextAfterDeactivate = lesson.checkBoxPublishStatusQuestion.getText();
            int publishCountAfterDeactivate = Integer.parseInt(publishCountText.substring(0, publishCountText.indexOf(" ")));
            if (publishCountAfterDeactivate < draftCountAfterNewVersion)
                Assert.fail("Questions count are not updating properly");


        } catch (Exception e) {
            Assert.fail("Exception in testcase publishQuestionChapterLevel in class checkQuestionWithMultipleReference", e);
        }

    }

    @Test(priority = 2)
    public void createNewChapterAndCheckRelatedCases() {
        try {
            Lesson lesson = PageFactory.initElements(driver, Lesson.class);
            Summary summary = PageFactory.initElements(driver, Summary.class);
            BooleanValue booleanValue = new BooleanValue();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Questions questions = new Questions();
            new Assignment().createChapter(61);
            manageContent.manageContent.click();
            WebDriverWait wait=new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.elementToBeClickable(manageContent.textAssociatedContent));
            Thread.sleep(1000);
            new Click().clickbyxpath("//div[contains(text(),'Climate conditions around the World')]");
            new Click().clickBycssselector("span[class='publish-chapter-lesson-icon publish-chapter-lesson']");
            Thread.sleep(1000);
            //click on question tab
            lesson.lessonTabUnderPublish.click();
           /* wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Questions')]")));*/
            Thread.sleep(2000);

            Assert.assertEquals(manageContent.textUnderLessontabFirstLine.getText(),"There are no lessons in this chapter.");
            Assert.assertEquals(manageContent.textUnderLessontabSecondLine.getText(),"Please revisit when lessons are added to this chapter.");
            lesson.questionTabUnderPublish.click();
            Thread.sleep(2000);
            //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Questions')]")));
            Assert.assertEquals(manageContent.textUnderQuestiontabFirstLine.getText(),"There are no questions in this chapter.");
            Assert.assertEquals(manageContent.textUnderQuestiontabSecondLine.getText(),"Please revisit when questions are added to this chapter.");



        } catch (Exception e) {
            Assert.fail("Exception in testcase publishQuestionChapterLevel in class checkQuestionWithMultipleReference", e);
        }


    }
}
