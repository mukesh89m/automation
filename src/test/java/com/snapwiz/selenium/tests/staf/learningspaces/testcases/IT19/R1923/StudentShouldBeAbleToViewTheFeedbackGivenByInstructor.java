package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R1923;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyActivity.MyActivity;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.EngagementReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by priyanka on 3/4/2015.
 */
public class StudentShouldBeAbleToViewTheFeedbackGivenByInstructor extends Driver{
    @Test(priority = 1, enabled = true)
    public void checkingWorkBoardFeedbackByInstructorInStudentAccount() {

        try {
            //tc row no 377
            Questions questions = PageFactory.initElements(driver, Questions.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            new Assignment().create(377);//Create an assignment
            new LoginUsingLTI().ltiLogin("377_1");//login as student
            new LoginUsingLTI().ltiLogin("377");//login as instructor
            new Assignment().assignToStudent(377);//assign assignment to student
            new LoginUsingLTI().ltiLogin("377_1");//login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//click on assessment
            Thread.sleep(10000);
            new WriteBoard().drawSquareInWriteBoardInStudentSide(377);//draw square in workBoard
            questions.crossIcon.click();//click on 'x'
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new Assignment().submitButtonInQuestionClick();//click on submit
            Thread.sleep(2000);
            new LoginUsingLTI().ltiLogin("377");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            new WriteBoard().drawSquareInWriteBoardInstructorSide(377);//draw square in workBoard
            assignmentResponsesPage.crossIconOnWorkBoard.click();//click on 'x'
            assignmentResponsesPage.getSaveButton().click();//click on save
            new Assignment().releaseGrades(377,"Release Feedback for All");
            new LoginUsingLTI().ltiLogin("377_1");//login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//click on assessment
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            driver.findElement(By.cssSelector("div[class='report-sidebar-question-card-sectn question-card-green']")).click();//click on question card
            Thread.sleep(2000);
            String showYourWorkLabel = questions.plusWorkBoard.getText();
            Assert.assertEquals(showYourWorkLabel, "+ View your work", "'View your work' button is not available");

            //tc row no 379
            questions.viewTeacherFeedbackButton.click();//click on view teacher feedback link
            questions.crossIcon.click();//click on 'x'
            questions.plusWorkBoard.click();//click on view your work

        } catch (Exception e) {
            Assert.fail("Exception in test case checkingWorkBoardFeedbackByInstructorInStudentAccount of class StudentShouldBeAbleToViewTheFeedbackGivenByInstructor", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void writeBoardQuestionPreviewOpenedFromPerformanceSummary() {

        try {
            //tc row no 385
            Questions questions = PageFactory.initElements(driver, Questions.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            new LoginUsingLTI().ltiLogin("385_1");//login as student
            new LoginUsingLTI().ltiLogin("385");//login as instructor
            new Assignment().assignToStudent(385);//assign assignment to student
            new LoginUsingLTI().ltiLogin("385_1");//login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//click on assessment
            Thread.sleep(20000);
            new WriteBoard().drawSquareInWriteBoardInStudentSide(385);
            questions.crossIcon.click();//click on 'x'
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new Assignment().submitButtonInQuestionClick();//click on submit
            Thread.sleep(2000);
            new LoginUsingLTI().ltiLogin("385");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            new WriteBoard().drawSquareInWriteBoardInstructorSide(385);//draw square in workBoard
            assignmentResponsesPage.crossIconOnWorkBoard.click();//click on 'x'
            assignmentResponsesPage.getSaveButton().click();//click on save
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save
            new Assignment().releaseGrades(385,"Release Feedback for All");
            new LoginUsingLTI().ltiLogin("385_1");//login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//click on assessment
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            driver.findElement(By.cssSelector("div[class='report-sidebar-question-card-sectn question-card-green']")).click();//click on question card
            Thread.sleep(2000);
            String showYourWorkLabel = questions.plusWorkBoard.getText();
            Assert.assertEquals(showYourWorkLabel, "+ View your work", "'View your work' button is not available");
            questions.viewTeacherFeedbackButton.click();//click on view teacher feedback link
            questions.crossIcon.click();//click on 'x'
            questions.plusWorkBoard.click();//click on view your work


        } catch (Exception e) {
            Assert.fail("Exception in test case writeBoardQuestionPreviewOpenedFromPerformanceSummary of class StudentShouldBeAbleToViewTheFeedbackGivenByInstructor", e);
        }
    }
    @Test(priority = 3, enabled = true)
    public void checkingWorkBoardFeaturesWhenNoFeedbackIsGivenByInstructor() {

        try {
            //tc row no 388
            Questions questions = PageFactory.initElements(driver, Questions.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            new Assignment().create(388);//Create an assignment
            new LoginUsingLTI().ltiLogin("388_1");//login as student
            new LoginUsingLTI().ltiLogin("388");//login as instructor
            new Assignment().assignToStudent(388);//assign assignment to student
            new LoginUsingLTI().ltiLogin("388_1");//login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//click on assessment
            Thread.sleep(10000);
            new WriteBoard().drawSquareInWriteBoardInStudentSide(388);//draw square in workBoard
            questions.crossIcon.click();//click on 'x'
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new Assignment().submitButtonInQuestionClick();//click on submit
            Thread.sleep(2000);
            new LoginUsingLTI().ltiLogin("388_1");//login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//click on assessment
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            driver.findElement(By.cssSelector("div[class='report-sidebar-question-card-sectn question-card-green']")).click();//click on question card
            Thread.sleep(2000);
            String showYourWorkLabel = questions.plusWorkBoard.getText();
            Assert.assertEquals(showYourWorkLabel, "+ View your work", "'View your work' button is not available");
            questions.plusWorkBoard.click();//click on view your work

        } catch (Exception e) {
            Assert.fail("Exception in test case checkingWorkBoardFeaturesWhenNoFeedbackIsGivenByInstructor of class StudentShouldBeAbleToViewTheFeedbackGivenByInstructor", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void writeBoardQuestionPreviewOpenedFromPerformanceSummaryWithNoWriteBoard() {

        try {
            //tc row no 392
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            new LoginUsingLTI().ltiLogin("392_1");//login as student
            new LoginUsingLTI().ltiLogin("392");//login as instructor
            new Assignment().assignToStudent(392);//assign assignment to student
            new LoginUsingLTI().ltiLogin("392_1");//login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//click on assessment
            Thread.sleep(10000);
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new Assignment().submitButtonInQuestionClick();//click on submit
            Thread.sleep(2000);
            new LoginUsingLTI().ltiLogin("392");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save
            new LoginUsingLTI().ltiLogin("392_1");//login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//click on assessment
            Thread.sleep(10000);
            driver.findElement(By.cssSelector("div[class='report-sidebar-question-card-sectn question-card-green']")).click();//click on question card
            Thread.sleep(2000);
            boolean viewYourWork = new BooleanValue().presenceOfElement(392, By.id("show-your-work-label"));
            Assert.assertEquals(viewYourWork,false,"The +workboard button in top right corner is displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test case writeBoardQuestionPreviewOpenedFromPerformanceSummaryWithNoWriteBoard of class StudentShouldBeAbleToViewTheFeedbackGivenByInstructor", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void questionPreviewOpenedFromProficiencyReportAtStudentSide() {

        try {
            //tc row no 395
            Questions questions = PageFactory.initElements(driver, Questions.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            new LoginUsingLTI().ltiLogin("395_1");//login as student
            new LoginUsingLTI().ltiLogin("395");//login as instructor
            new Assignment().assignToStudent(395);//assign assignment to student
            new LoginUsingLTI().ltiLogin("395_1");//login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//click on assessment
            Thread.sleep(10000);
            new WriteBoard().enterTextInWriteBoardFromCMS("text",395);
            questions.crossIcon.click();//click on 'x'
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new Assignment().submitButtonInQuestionClick();//click on submit
            Thread.sleep(2000);
          /*  new Navigator().NavigateTo("Proficiency Report");//navigate to proficiency report
            driver.findElement(By.cssSelector("div[class='report-sidebar-question-card-sectn question-card-green']")).click();//click on question card
            String showYourWorkLabel = questions.plusWorkBoard.getText();
            Assert.assertEquals(showYourWorkLabel, "+ View your work", "'View your work' button is not available");
            questions.plusWorkBoard.click();//click on view your work
*/

        } catch (Exception e) {
            Assert.fail("Exception in test case questionPreviewOpenedFromProficiencyReportAtStudentSide of class StudentShouldBeAbleToViewTheFeedbackGivenByInstructor", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void writeBoardQuestionPreviewOpenedFromProficiencyReport() {

        try {
            //tc row no 400
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            new LoginUsingLTI().ltiLogin("400_1");//login as student
            new LoginUsingLTI().ltiLogin("400");//login as instructor
            new Assignment().assignToStudent(400);//assign assignment to student
            new LoginUsingLTI().ltiLogin("400_1");//login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//click on assessment
            Thread.sleep(10000);
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new Assignment().submitButtonInQuestionClick();//click on submit
            Thread.sleep(2000);
            new LoginUsingLTI().ltiLogin("400");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save
            Thread.sleep(2000);


        } catch (Exception e) {
            Assert.fail("Exception in test case writeBoardQuestionPreviewOpenedFromProficiencyReport of class StudentShouldBeAbleToViewTheFeedbackGivenByInstructor", e);
        }
    }

    @Test(priority = 7, enabled = true)
    public void instructorWorkBoardFeedbackToWorkBoardEnabledQuestion() {

        try {
            //tc row no 403
            Questions questions = PageFactory.initElements(driver, Questions.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            EngagementReport engagementReport = PageFactory.initElements(driver,EngagementReport.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            new LoginUsingLTI().ltiLogin("403_1");//login as student
            new TOCShow().chaptertree();//click on toc
            new TopicOpen().chapterOpen(2);//click on third chapter
            new SelectCourse().selectInvisibleAssignment("IT1923_static_Assessment_361");//select particular assessment
            Thread.sleep(5000);
            new WriteBoard().drawSquareInWriteBoardInStudentSide(403);//draw square in workBoard
            questions.crossIcon.click();//click on 'x'
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new Assignment().submitButtonInQuestionClick();//click on submit
            Thread.sleep(3000);
            assignments.finish.click();//click on finish

            new LoginUsingLTI().ltiLogin("403");//login as instructor
            new Navigator().NavigateTo("Engagement Report");//navigate to current Assignment
            engagementReport.assessment.click();//click on assessment
            new TopicOpen().chapterOpen(2);//click on third chapter
            new SelectCourse().selectInvisibleAssignment("IT1923_static_Assessment_361");//select particular assessment
            Thread.sleep(5000);
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            new WriteBoard().drawSquareInWriteBoardInstructorSide(403);//draw square in workBoard
            assignmentResponsesPage.crossIconOnWorkBoard.click();//click on 'x'
            assignmentResponsesPage.getSaveButton().click();//click on save



        } catch (Exception e) {
            Assert.fail("Exception in test case instructorWorkBoardFeedbackToWorkBoardEnabledQuestion of class StudentShouldBeAbleToViewTheFeedbackGivenByInstructor", e);
        }
    }


    @Test(priority = 8, enabled = true)
    public void checkingEnhancedWorkBoardFeaturesWhenADiscussionIsPosted() {

        try {
            //tc row no 408
            Questions questions = PageFactory.initElements(driver, Questions.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("408_1");//login as student
            new LoginUsingLTI().ltiLogin("408");//login as instructor
            new Assignment().assignToStudent(408);//assign assignment to student
            new LoginUsingLTI().ltiLogin("408_1");//login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//click on assessment
            Thread.sleep(20000);
            new WriteBoard().drawSquareInWriteBoardInStudentSide(408);//draw square in workBoard
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new Assignment().submitButtonInQuestionClick();//click on submit
            Thread.sleep(2000);
            new LoginUsingLTI().ltiLogin("408");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            new WriteBoard().drawSquareInWriteBoardInstructorSide(408);//draw square in workBoard
            assignmentResponsesPage.crossIconOnWorkBoard.click();//click on 'x'
            assignmentResponsesPage.getSaveButton().click();//click on save
            new LoginUsingLTI().ltiLogin("408_1");//login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//click on assessment
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            driver.findElement(By.cssSelector("div[class='report-sidebar-question-card-sectn question-card-green']")).click();//click on question card
            Thread.sleep(2000);
            new ClickOnquestionCard().clickonquestioncard(0);//click on question card
            assignments.getDiscussionTab().click();//click on discussion tab
            assignments.getNewButton().click();//click on +new button
            assignments.getEditBox().sendKeys("aassdfdghh asdfgjj");
            assignments.getSubmit().click();//click on submit
            new Navigator().NavigateTo("Course Stream");//navigate to courseStream
            courseStreamPage.getJumpOut().click();//click on jumpOut
            Thread.sleep(5000);
            Assert.assertEquals(courseStreamPage.discussionBody.getText(),"aassdfdghh asdfgjj","The discussion posted is not visible in right panel of the page.");
            String showYourWorkLabel = questions.plusWorkBoard.getText().trim();
            Assert.assertEquals(showYourWorkLabel, "+ View your work", "'View your work' button is not available");
            questions.plusWorkBoard.click();//click on view your work
            String crossIcon =questions.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            questions.crossIcon.click();//click on 'x'

        } catch (Exception e) {
            Assert.fail("Exception in test case checkingEnhancedWorkBoardFeaturesWhenADiscussionIsPosted of class StudentShouldBeAbleToViewTheFeedbackGivenByInstructor", e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void checkingEnhancedWorkBoardFeaturesWhenAssignmentIsOpened() {

        try {
            //tc row no 413
            Questions questions = PageFactory.initElements(driver, Questions.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            MyActivity myActivity = PageFactory.initElements(driver,MyActivity.class);
            new LoginUsingLTI().ltiLogin("413_1");//login as student

            new LoginUsingLTI().ltiLogin("413");//login as instructor
            new Assignment().assignToStudent(413);//assign assignment to student

            new LoginUsingLTI().ltiLogin("413_1");//login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//click on assessment
            Thread.sleep(20000);
            new WriteBoard().drawSquareInWriteBoardInStudentSide(413);//draw square in workBoard
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new Assignment().submitButtonInQuestionClick();//click on submit
            Thread.sleep(2000);
            new LoginUsingLTI().ltiLogin("413");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            new WriteBoard().drawSquareInWriteBoardInstructorSide(413);//draw square in workBoard
            assignmentResponsesPage.crossIconOnWorkBoard.click();//click on 'x'
            assignmentResponsesPage.getSaveButton().click();//click on save
            new LoginUsingLTI().ltiLogin("413_1");//login as student
            new Navigator().NavigateTo("My Activity");//navigate to my activity
            myActivity.assessmentLink.click();//click on assessment
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            driver.findElement(By.cssSelector("div[class='report-sidebar-question-card-sectn question-card-green']")).click();//click on question card
            String showYourWorkLabel = questions.plusWorkBoard.getText();
            Assert.assertEquals(showYourWorkLabel, "+ View your work", "'View your work' button is not available");
            questions.plusWorkBoard.click();//click on view your work
            String crossIcon =questions.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            questions.crossIcon.click();//click on 'x'
            questions.viewTeacherFeedbackButton.click();//click on view teacher feedback

        } catch (Exception e) {
            Assert.fail("Exception in test case checkingEnhancedWorkBoardFeaturesWhenAssignmentIsOpened of class StudentShouldBeAbleToViewTheFeedbackGivenByInstructor", e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void checkingEnhancedWorkBoardFeaturesWhenAssignmentIsOpenedFromDashboard() {

        try {
            //tc row no 418
            Questions questions = PageFactory.initElements(driver, Questions.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            Dashboard dashboard = PageFactory.initElements(driver,Dashboard.class);
            new LoginUsingLTI().ltiLogin("418_1");//login as student
            new LoginUsingLTI().ltiLogin("418");//login as instructor
            new Assignment().assignToStudent(418);//assign assignment to student
            new LoginUsingLTI().ltiLogin("418_1");//login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//click on assessment
            Thread.sleep(10000);
            new WriteBoard().drawSquareInWriteBoardInStudentSide(418);
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new Assignment().submitButtonInQuestionClick();//click on submit
            Thread.sleep(2000);
            new LoginUsingLTI().ltiLogin("418");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            new WriteBoard().drawSquareInWriteBoardInstructorSide(418);//draw square in workBoard
            assignmentResponsesPage.crossIconOnWorkBoard.click();//click on 'x'
            assignmentResponsesPage.getSaveButton().click();//click on save
            new LoginUsingLTI().ltiLogin("418_1");//login as student
            dashboard.assessmentLink.get(0).click();//click on assessment on dashboard tile
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            driver.findElement(By.cssSelector("div[class='report-sidebar-question-card-sectn question-card-green']")).click();//click on question card
            String showYourWorkLabel = questions.plusWorkBoard.getText();
            Assert.assertEquals(showYourWorkLabel, "+ View your work", "'View your work' button is not available");
            questions.plusWorkBoard.click();//click on view your work
            String crossIcon =questions.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            questions.crossIcon.click();//click on 'x'
            questions.viewTeacherFeedbackButton.click();//click on view teacher feedback


        } catch (Exception e) {
            Assert.fail("Exception in test case checkingEnhancedWorkBoardFeaturesWhenAssignmentIsOpenedFromDashboard of class StudentShouldBeAbleToViewTheFeedbackGivenByInstructor", e);
        }
    }

    @Test(priority = 11, enabled = true)
    public void pageOpenedFromJumpOutOverCsEntryForDiscussionPostedToWriteBoard() {

        try {
            //tc row no 423
            Preview preview = PageFactory.initElements(driver,Preview.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            new LoginUsingLTI().ltiLogin("423_1");//login as student
            new LoginUsingLTI().ltiLogin("423");//login as instructor
            new Assignment().assignToStudent(423);//assign assignment to student
            new LoginUsingLTI().ltiLogin("423_1");//login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//click on assessment
            Thread.sleep(10000);
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new Assignment().submitButtonInQuestionClick();//click on submit
            Thread.sleep(2000);
            new LoginUsingLTI().ltiLogin("423");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            new WriteBoard().drawSquareInWriteBoardInstructorSide(423);//draw square in workBoard
            assignmentResponsesPage.crossIconOnWorkBoard.click();//click on 'x'
            assignmentResponsesPage.getSaveButton().click();//click on save

            new LoginUsingLTI().ltiLogin("423_1");//login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//click on assessment
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            driver.findElement(By.cssSelector("div[class='report-sidebar-question-card-sectn question-card-green']")).click();//click on question card
            Thread.sleep(2000);
            new ClickOnquestionCard().clickonquestioncard(0);//click on question card
            assignments.getDiscussionTab().click();//click on discussion tab
            assignments.getNewButton().click();//click on +new button
            assignments.getEditBox().sendKeys("aassdfdghh asdfgjj");
            assignments.getSubmit().click();//click on submit
            new Navigator().NavigateTo("Course Stream");//navigate to courseStream
            courseStreamPage.getJumpOut().click();//click on jumpout
            Thread.sleep(5000);
            Assert.assertEquals(courseStreamPage.discussionBody.getText(),"aassdfdghh asdfgjj","The discussion posted is not visible in right panel of the page.");
            boolean viewYourWork = new BooleanValue().presenceOfElement(423, By.id("show-your-work-label"));
            Assert.assertEquals(viewYourWork,false,"The +workboard button in top right corner is displaying");
            boolean viewYourWork1 = new BooleanValue().presenceOfElement(423, By.id("white-board-feedBack-link-text"));
            Assert.assertEquals(viewYourWork1,false,"View teacher feedback on Workboard link is displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test case pageOpenedFromJumpOutOverCsEntryForDiscussionPostedToWriteBoard of class StudentShouldBeAbleToViewTheFeedbackGivenByInstructor", e);
        }
    }


}
