package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R1922;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.EngagementReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by sumit on 20/1/15.
 */
public class RetakeStaticAssessment extends Driver {

    @Test
    public void retakeStaticAssessment()
    {
        try
        {
            EngagementReport engagementReport = PageFactory.initElements(driver, EngagementReport.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "57");
            new Assignment().create(57);

            new LoginUsingLTI().ltiLogin("57_1");//create a student
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().topicOpen(assessmentname);//open the static assessment
            new SelectAnswerAndSubmit().staticanswersubmit("A");

            new LoginUsingLTI().ltiLogin("57");
            new Navigator().NavigateTo("Engagement Report");
            engagementReport.getReadingCount().click();//click on
            Thread.sleep(2000);
            new TopicOpen().topicOpen(assessmentname);//open the static assessment
            Thread.sleep(2000);
            WebElement we = assignmentResponsesPage.getLink_UserName().get(0);
            new MouseHover().mouserhoverbywebelement(we);
            String enterGrade = assignmentResponsesPage.getEnterGrade().getText();

            Assert.assertEquals(enterGrade, "Enter Grade", "\"enter Grade\" link is not there in assessment response page.");
            assignmentResponsesPage.getEnterGrade().click();//click on Enter Grade
            List<WebElement> allGradeBox = assignmentResponsesPage.getGradeBox();
            for (WebElement gradeBox : allGradeBox) {
                gradeBox.clear();
                gradeBox.sendKeys("0.7");
                gradeBox.sendKeys(Keys.TAB);
            }
            driver.findElement(By.xpath("/html/body")).click();//click outside
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(driver.findElement(By.cssSelector("div[class='idb-gradebook-content-coloumn']")));
            WebElement we1 = assignmentResponsesPage.getViewResponseLink();
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", we1);//click on View Response
            assignmentResponsesPage.getFeedbackTextArea().clear();
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("Feedback text");
            assignmentResponsesPage.getSaveButton().click();

            new LoginUsingLTI().ltiLogin("57_1");//create a student
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().topicOpen(assessmentname);//open the static assessment
            lessonPage.getRetakeLink().click();//click on Retake
            new AttemptQuestion().trueFalse(false, "incorrect", 9);
            new Assignment().submitButtonInQuestionClick();//click on submit
            new Assignment().nextButtonInQuestionClick();//click on next

            new LoginUsingLTI().ltiLogin("57");
            new Navigator().NavigateTo("Engagement Report");
            engagementReport.getReadingCount().click();
            new TopicOpen().topicOpen(assessmentname);//open the static assessment
            WebElement we2 = assignmentResponsesPage.getLink_UserName().get(0);
            try {
                new MouseHover().mouserhoverbywebelement(we2);
            } catch (Exception e) {
                new MouseHover().mouserhoverbywebelement(we2);
            }

            Assert.assertEquals(new BooleanValue().presenceOfElement(57,By.xpath("//span[@title='Enter Grade']")), true, "\"enter Grade\" link is not there in assessment response page.");
            assignmentResponsesPage.getEnterGrade().click();//click on Enter Grade
            List<WebElement> allGradeBox1 = assignmentResponsesPage.getGradeBox();
            for (WebElement gradeBox : allGradeBox1) {
                gradeBox.clear();
                gradeBox.sendKeys("0.7");
                gradeBox.sendKeys(Keys.TAB);
            }
            driver.findElement(By.xpath("/html/body")).click();//click outside
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(driver.findElement(By.cssSelector("span[class='idb-gradebook-question-content idb-question-partial-scored']")));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", assignmentResponsesPage.getViewResponseLink());//click on View Response
            assignmentResponsesPage.getFeedbackTextArea().clear();
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("Feedback text");
            assignmentResponsesPage.getSaveButton().click();
            new TabClose().tabClose(1);
            assignmentResponsesPage.getRefreshButton().click();
            Thread.sleep(2000);
            String feedbackIcon = assignmentResponsesPage.getCommentIcon().getAttribute("innerHTML");
            if(!feedbackIcon.contains("feedback-notification-icon.png")){
                Assert.fail("Feed back icon is absent after the instructor provides feedback");
            }

        }
        catch (Exception e){
            Assert.fail("Exception in class RetakeStaticAssessment in method retakeStaticAssessment.", e);
        }
    }

}
