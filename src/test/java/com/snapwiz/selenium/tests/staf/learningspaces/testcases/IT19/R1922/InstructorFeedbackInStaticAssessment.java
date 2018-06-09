package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R1922;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
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
 * Created by sumit on 16/1/15.
 */
public class InstructorFeedbackInStaticAssessment extends Driver{

    @Test
    public void instructorFeedbackInStaticAssessment()
    {
        try
        {
            EngagementReport engagementReport = PageFactory.initElements(driver, EngagementReport.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "9");
            new Assignment().create(9);
            new Assignment().addQuestions(9, "multiplechoice", assessmentname);
            new Assignment().addQuestions(9, "essay", assessmentname);

            new LoginUsingLTI().ltiLogin("9_1");//create a student
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().topicOpen(assessmentname);//open the static assessment to make it in progress

            new LoginUsingLTI().ltiLogin("9");
            new Navigator().NavigateTo("Engagement Report");
            engagementReport.getReadingCount().click();//click on
            Thread.sleep(2000);
            //TC row no. 9
            String url = driver.getCurrentUrl();
            if(!url.contains("eTextBook")){
                Assert.fail("Click on \"Assessments\" cell present under \"Study & Practice Completion\" for student1 instructor doesn't navigate to TOC view");
            }
            new TopicOpen().topicOpen(assessmentname);//open the static assessment

            String title = assignmentResponsesPage.getPageTitle().getText();
            Assert.assertEquals(title, "Assessment Responses", "Instructor is not navigated to Assessment Response page of that student.");

            String tabName = assignmentResponsesPage.getTabName().getText();
            Assert.assertEquals(tabName, "Response - "+assessmentname, "Tab name is not correctly displayed.");

            String assignmentName = assignments.getAssignmentName().getText();
            Assert.assertEquals(assignmentName, assessmentname, " The name of the assessment is not shown below the \"Assessment Response\" label.");

            String points = assignmentResponsesPage.getTotalPoints().getText();
            Assert.assertEquals(points, "Total Points: 3", "Total points - 3, is not displayed in that assessment.");

            String studentName = assignmentResponsesPage.getStudentNameOnTopRight().getText();
            Assert.assertEquals(studentName.trim(), "Student:    "+ Config.familyname+", "+Config.givenname, "The name of that student does not show on the top right part of the page..");

            String studentProfileIcon = assignmentResponsesPage.getStudentProfileIcon().getAttribute("src");
            if(!studentProfileIcon.contains("user-default-thumbnail.png")){
                Assert.fail("The thumbnail of that student does not show on the top right part of the page.");
            }

            String studentName1 = assignmentResponsesPage.getStudentName().getText();
            Assert.assertEquals(studentName1, Config.familyname+", "+Config.givenname, "The name of that student does not show under name column of assessment response page.");

            String totalMarksColumn = assignmentResponsesPage.getTotalMarksColumn().getText().replaceAll("\n", " ");
            Assert.assertEquals(totalMarksColumn, "Total Marks", "Total Marks column does not show in assessment response page.");

            String totalMarks = assignmentResponsesPage.getTotalMarks().getText();
            Assert.assertEquals(totalMarks, "0.0", "Total Marks is not shown in assessment response page.");

            String questionLabel = assignmentResponsesPage.getQuestionLabels().get(0).getText();
            Assert.assertEquals(questionLabel, "Q1", "Question labels Q1, Q2 etc. are not shown in assessment response page.");

            new LoginUsingLTI().ltiLogin("9_1");//login as student
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().topicOpen(assessmentname);//open the static assessment
            new WriteBoard().enterTextInWriteBoard("text", 9);
            new AttemptQuestion().trueFalse(false, "correct", 9);
            new Assignment().submitButtonInQuestionClick();//click on submit
            new Assignment().nextButtonInQuestionClick();//click on next
            new Assignment().submitButtonInQuestionClick();//click on submit
            new Assignment().nextButtonInQuestionClick();//skip the 2nd question
            new AttemptQuestion().essay(false, "correct", 9);//submit the 3rd and last question
            new Assignment().submitButtonInQuestionClick();//click on submit
            new Assignment().nextButtonInQuestionClick();//click on Finish button

            new LoginUsingLTI().ltiLogin("9");
            new Navigator().NavigateTo("Engagement Report");
            engagementReport.getReadingCount().click();
            new TopicOpen().topicOpen(assessmentname);//open the static assessment
            //TC row no. 19, 28
            String totalMarks1 = assignmentResponsesPage.getTotalMarks().getText();
            Assert.assertEquals(totalMarks1, "1.0", "Total Marks is not shown in assessment response page.");

            String percentage = assignmentResponsesPage.getPercentComplete().getText();
            Assert.assertEquals(percentage, "67 %", "Percentage complete is not shown in assessment response page.");

            //TC row no. 20
            new MouseHover().mouserhoverbywebelement(driver.findElement(By.cssSelector("span[class='idb-gradebook-question-content idb-question-fully-scored ']")));
            String viewResponse = assignmentResponsesPage.getViewResponseLink().getText();
            Assert.assertEquals(viewResponse, "View Response", "\"View Response\" link is not there.");

            //TC row no. 22
            String refreshButton = assignmentResponsesPage.getRefreshButton().getCssValue("background-image");
            if(!refreshButton.contains("")){
                Assert.fail("Refresh button is not there in assessment response page");
            }
            //TC row no. 23
            assignments.getAssignmentName().click();//click on Assessment name
            Thread.sleep(10000);

            //TC row no 24, 25
            String str = new RandomString().randomstring(20);
            new ReportContentIssue().reportContentIssue(str);

            //TC row no. 26
            new TabClose().tabClose(1);//close the preview tab
            Thread.sleep(2000);
            assignmentResponsesPage.getBackButton().click();//click on back button
            Thread.sleep(2000);

            //TC row no. 27
            String url1 = driver.getCurrentUrl();
            if(!url1.contains("eTextBook")){
                Assert.fail("Click on the back arrow ion present beside the Assessment Response label instructor doesn't navigate to TOC view");
            }
            //TC row no. 31 - 34
            new TopicOpen().topicOpen(assessmentname);//open the static assessment

            WebElement we = assignmentResponsesPage.getStudentName();
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
            new MouseHover().mouserhoverbywebelement(driver.findElement(By.cssSelector("span[class='idb-gradebook-question-content idb-question-partial-scored']")));
            WebElement we1 = assignmentResponsesPage.getViewResponseLink();
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", we1);//click on View Response
            //TC row no. 36, 37
            String name = assignmentResponsesPage.getAssessmentName().getText();
            Assert.assertEquals(name, assessmentname, "Assessment name is not displayed in Assessment response page for a particular question.");

            String qIndex = assignmentResponsesPage.getQuestionIndex().getText();
            Assert.assertEquals(qIndex, "(1 of 3)", "Question index near Assessment name is not displayed in Assessment response page for a particular question.");

            //TC row no. 38
            String studentName2 = assignmentResponsesPage.getStudentLabelAndName().getText();
            Assert.assertEquals(studentName2.trim(), "Student : family, givenname", "Student name is not displayed in Assessment response page for a particular question.");

            //TC row no. 40
            String score = assignmentResponsesPage.getScore().getAttribute("value");
            Assert.assertEquals(score, "0.7", " score is not displayed in Assessment response page for a particular question.");

            //TC row no. 41
            boolean feedback = new BooleanValue().presenceOfElement(9, By.id("view-user-question-performance-feedback-box"));
            Assert.assertEquals(feedback, true, "Feedback textbox is not found in Assessment response page for a particular question.");

            //TC row no. 43
            String save = assignmentResponsesPage.getSaveButton().getText();
            Assert.assertEquals(save, "Save", "Save button is not found in Assessment response page for a particular question.");

            //TC row no. 46
            String writeBoard = assignmentResponsesPage.getWriteBoardLabel().getText();
            Assert.assertEquals(writeBoard, "Review students workboard", "Review students workboard is not found in Assessment response page for a particular question.");
            String writeBoardFeedback = assignmentResponsesPage.getWriteBoardFeedback().getText();
            Assert.assertEquals(writeBoardFeedback, "+ Workboard feedback", "+ Workboard feedback is not found in Assessment response page for a particular question.");

            //TC row no. 47, 48
            assignmentResponsesPage.getFeedbackTextArea().clear();
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("Feedback text");
            assignmentResponsesPage.getSaveButton().click();
            String message = assignmentResponsesPage.getSavedSuccessfullyMessage().getText();
            Assert.assertEquals(message.trim(), "Saved successfully.", "Saved successfully message is not displayed");

            //TC row no. 45
            assignmentResponsesPage.getNextArrow().click();//click on right arrow
            Thread.sleep(2000);
            String writeBoardFeedback1 = assignmentResponsesPage.noSubmmitedWriteBoardFeedback.getText();
            Assert.assertEquals(writeBoardFeedback1, "No workboard submitted", "No Writeboard work submitted by student. Click here to enter feedback using Writeboard is not found in Assessment response page for a particular question.");
            assignmentResponsesPage.getFeedbackTextArea().clear();
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("Feedback text 2");
            //TC row no. 51
            assignmentResponsesPage.getPreviousArrow().click();//click on left arrow
            assignmentResponsesPage.getNextArrow().click();//click on right arrow
            String text = assignmentResponsesPage.getFeedbackTextArea().getText();
            Assert.assertEquals(text, "Feedback text 2", "Feedback text does not save if click on arrow.");

            new TabClose().tabClose(1);
            assignmentResponsesPage.getRefreshButton().click();
            Thread.sleep(2000);
            //TC row no. 52, 49
            String feedbackIcon = assignmentResponsesPage.getCommentIcon().getAttribute("innerHTML");
            if(!feedbackIcon.contains("feedback-notification-icon.png")){
                Assert.fail("Feed back icon is absent after the instructor provides feedback");
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class InstructorFeedbackInStaticAssessment in method instructorFeedbackInStaticAssessment.", e);
        }
    }

}
