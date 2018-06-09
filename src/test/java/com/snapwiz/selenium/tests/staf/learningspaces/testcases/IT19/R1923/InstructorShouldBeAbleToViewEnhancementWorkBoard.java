package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R1923;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.EngagementReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by priyanka on 3/3/2015.
 */
public class InstructorShouldBeAbleToViewEnhancementWorkBoard extends Driver{

    @Test(priority = 1, enabled = true)
    public void viewResponsesOfWorkBoardEnabledQuestionWithNODataOverWorkBoard() {

        try {
            //tc row no 323
            Questions questions = PageFactory.initElements(driver, Questions.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            new Assignment().create(323);//Create an assignment
            new Assignment().addQuestions(323, "multiplechoice", "");
            new LoginUsingLTI().ltiLogin("323_1");//login as student
            new LoginUsingLTI().ltiLogin("323");//login as instructor
            new Assignment().assignToStudent(323);//assign assignment to student
            new LoginUsingLTI().ltiLogin("323_1");//login as student
            new Assignment().submitAssignmentAsStudent(323);//submit assignment
            new LoginUsingLTI().ltiLogin("323");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            Assert.assertEquals(assignmentResponsesPage.workBoardMessage.getText(),"No workboard submitted","No workboard submitted message is appearong in top right corner of the question pane.");
            Assert.assertEquals(assignmentResponsesPage.plusWorkBoard.getText(),"+ Workboard feedback","Workboard feedback button is not available in the top right corner of the question pane.");

            //tc row no 325
            assignmentResponsesPage.plusWorkBoard.click();//click on +workboard feedback
            Thread.sleep(3000);
            String crossIcon =assignmentResponsesPage.crossIconOnWorkBoard.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(assignmentResponsesPage.frame);//switch to frame
            boolean toolPanel = assignmentResponsesPage.toolControl.isDisplayed();
            if (toolPanel == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            questions.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardInInstructorSide("text", 323);
            boolean workBoard1 = assignmentResponsesPage.plusWorkBoard.isDisplayed();
            if (workBoard1 == true) {
                Assert.fail("It is showing '+ Workboard feedback' button when pop_up is shown.");
            }
            String crossIcon1 = assignmentResponsesPage.crossIconOnWorkBoard.getText();
            Assert.assertEquals(crossIcon1, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");

            //tc row no 331
            assignmentResponsesPage.crossIconOnWorkBoard.click();//click on 'x'
            if(assignmentResponsesPage.getFeedbackTextArea().isDisplayed()==false){
                Assert.fail("Enter Feedback text field is not available.");
            }
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save


        } catch (Exception e) {
            Assert.fail("Exception in test case viewResponsesOfWorkBoardEnabledQuestionWithNODataOverWorkBoard of class InstructorShouldBeAbleToViewEnhancementWorkBoard", e);
        }
    }


    @Test(priority = 2, enabled = true)
    public void viewResponsesOfWorkBoardEnabledQuestionWithDataOverWorkBoard() {

        try {
            //tc row no 334
            Questions questions = PageFactory.initElements(driver, Questions.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            new Assignment().create(334);//Create an assignment
            new LoginUsingLTI().ltiLogin("334_1");//login as student
            new LoginUsingLTI().ltiLogin("334");//login as instructor
            new Assignment().assignToStudent(334);//assign assignment to student
            new LoginUsingLTI().ltiLogin("334_1");//login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//click on assessment
            Thread.sleep(10000);
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 334);//enter text in workBoard
            questions.crossIcon.click();//click on 'x'
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new Assignment().submitButtonInQuestionClick();//click on submit
            new LoginUsingLTI().ltiLogin("334");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            Assert.assertEquals(assignmentResponsesPage.workBoardMessage.getText(),"Review students workboard","No workboard submitted message is appearong in top right corner of the question pane.");
            Assert.assertEquals(assignmentResponsesPage.plusWorkBoard.getText(),"+ Workboard feedback","Workboard feedback button is not available in the top right corner of the question pane.");

            //tc row no 336
            assignmentResponsesPage.plusWorkBoard.click();//click on +workboard feedback
            Thread.sleep(3000);
            String crossIcon =assignmentResponsesPage.crossIconOnWorkBoard.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(assignmentResponsesPage.frame);//switch to frame
            boolean toolPanel = assignmentResponsesPage.toolControl.isDisplayed();
            if (toolPanel == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            questions.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardInInstructorSide("text", 323);
            boolean workBoard1 = assignmentResponsesPage.plusWorkBoard.isDisplayed();
            if (workBoard1 == true) {
                Assert.fail("It is showing '+ Workboard feedback' button when pop_up is shown.");
            }
            String crossIcon1 = assignmentResponsesPage.crossIconOnWorkBoard.getText();
            Assert.assertEquals(crossIcon1, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");

            //tc row no 343
            assignmentResponsesPage.crossIconOnWorkBoard.click();//click on 'x'
            if(assignmentResponsesPage.getFeedbackTextArea().isDisplayed()==false){
                Assert.fail("Enter Feedback text field is not available.");
            }
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save


        } catch (Exception e) {
            Assert.fail("Exception in test case viewResponsesOfWorkBoardEnabledQuestionWithDataOverWorkBoard of class InstructorShouldBeAbleToViewEnhancementWorkBoard", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void viewResponsesOfWriteBoardEnabledQuestionWithDataOverWriteboard() {

        try {
            //tc row no 346
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            new Assignment().create(346);//Create an assignment
            new Assignment().addQuestions(346, "writeboard", "");
            new LoginUsingLTI().ltiLogin("346_1");//login as student
            new LoginUsingLTI().ltiLogin("346");//login as instructor
            new Assignment().assignToStudent(346);//assign assignment
            new LoginUsingLTI().ltiLogin("346_1");//login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//click on assessment
            new WebDriverWait(driver,3000).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(true, "correct", 346);
            assignments.getNextQuestion().click();//click on next button
            new AttemptQuestion().writeBoard(true, "correct", 346);
            Thread.sleep(2000);
            assignments.getFinishAssignment().click();//click on finish
            new LoginUsingLTI().ltiLogin("346");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(driver.findElement(By.xpath("(//div[@class='idb-gradebook-content-coloumn'])[2]"))); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            boolean viewYourWork = new BooleanValue().presenceOfElement(346, By.id("show-your-work-label"));
            Assert.assertEquals(viewYourWork,false,"The +workboard button in top right corner is displaying");
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'whiteBoard_iframe_kedukWBTEACHER__whiteBoard')]")));
            new Click().clickByid("text-btn");//click on text button
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
            Thread.sleep(3000);
            new TextSend().textsendbyid("text", "textEditor");
            new Click().clickbylistcssselector("button[type='button']", 2);//click on update
            driver.switchTo().defaultContent();//switch to main window
            Thread.sleep(1000);
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save



        } catch (Exception e) {
            Assert.fail("Exception in test case viewResponsesOfWriteBoardEnabledQuestionWithDataOverWriteboard of class InstructorShouldBeAbleToViewEnhancementWorkBoard", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void assessmentResponsesForWorkBoardEnabledQuestionWithNoDataOverWorkBoard() {

        try {
            //tc row no 350
            Questions questions = PageFactory.initElements(driver, Questions.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            EngagementReport engagementReport = PageFactory.initElements(driver,EngagementReport.class);
            new Assignment().create(350);//Create an assignment
            new LoginUsingLTI().ltiLogin("350_1");//login as student
            new TOCShow().chaptertree();//click on toc
            new TopicOpen().chapterOpen(2);//click on third chapter
            new SelectCourse().selectInvisibleAssignment("IT19_23_static_Assessment_350");
            Thread.sleep(5000);
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new Assignment().submitButtonInQuestionClick();//click on submit
            Thread.sleep(3000);
            assignments.finish.click();//click on finish

            new LoginUsingLTI().ltiLogin("350");//login as instructor
            new Navigator().NavigateTo("Engagement Report");//navigate to current Assignment
            engagementReport.assessment.click();//click on assessment
            new TopicOpen().chapterOpen(2);//click on third chapter
            new SelectCourse().selectInvisibleAssignment("IT19_23_static_Assessment_350");//click on particular assessment
            Thread.sleep(5000);
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            Assert.assertEquals(assignmentResponsesPage.workBoardMessage.getText(),"No workboard submitted","No workboard submitted message is appearong in top right corner of the question pane.");
            Assert.assertEquals(assignmentResponsesPage.plusWorkBoard.getText(),"+ Workboard feedback","Workboard feedback button is not available in the top right corner of the question pane.");

            //tc row no 352
            assignmentResponsesPage.plusWorkBoard.click();//click on +workboard feedback
            Thread.sleep(3000);
            String crossIcon =assignmentResponsesPage.crossIconOnWorkBoard.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(assignmentResponsesPage.frame);//switch to frame
            boolean toolPanel = assignmentResponsesPage.toolControl.isDisplayed();
            if (toolPanel == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            questions.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardInInstructorSide("text", 350);
            boolean workBoard1 = assignmentResponsesPage.plusWorkBoard.isDisplayed();
            if (workBoard1 == true) {
                Assert.fail("It is showing '+ Workboard feedback' button when pop_up is shown.");
            }
            String crossIcon1 = assignmentResponsesPage.crossIconOnWorkBoard.getText();
            Assert.assertEquals(crossIcon1, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");

            //tc row no 358
            assignmentResponsesPage.crossIconOnWorkBoard.click();//click on 'x'
            if(assignmentResponsesPage.getFeedbackTextArea().isDisplayed()==false){
                Assert.fail("Enter Feedback text field is not available.");
            }
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save


        } catch (Exception e) {
            Assert.fail("Exception in test case assessmentResponsesForWorkBoardEnabledQuestionWithNoDataOverWorkBoard of class InstructorShouldBeAbleToViewEnhancementWorkBoard", e);
        }
    }


    @Test(priority = 5, enabled = true)
    public void assessmentResponsesOfWorkBoardEnabledQuestionWithDataOverWorkBoard() {

        try {
            //tc row no 361
            Questions questions = PageFactory.initElements(driver, Questions.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            EngagementReport engagementReport = PageFactory.initElements(driver,EngagementReport.class);
            new Assignment().create(361);//Create an assignment
            new LoginUsingLTI().ltiLogin("361_1");//login as student
            new TOCShow().chaptertree();//click on toc
            new TopicOpen().chapterOpen(2);//click on third chapter
            new SelectCourse().selectInvisibleAssignment("IT1923_static_Assessment_361");//click on particular assessment
            Thread.sleep(5000);
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 361);//enter text in workBoard
            questions.crossIcon.click();//click on 'x'
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new Assignment().submitButtonInQuestionClick();//click on submit
            Thread.sleep(3000);
            assignments.finish.click();//click on finish

            new LoginUsingLTI().ltiLogin("361");//login as instructor
            new Navigator().NavigateTo("Engagement Report");//navigate to current Assignment
            engagementReport.assessment.click();//click on assessment
            new TopicOpen().chapterOpen(2);//click on third chapter
            new SelectCourse().selectInvisibleAssignment("IT1923_static_Assessment_361");//click on particular assessment
            Thread.sleep(5000);
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            Assert.assertEquals(assignmentResponsesPage.workBoardMessage.getText(),"Review students workboard","No workboard submitted message is appearong in top right corner of the question pane.");
            Assert.assertEquals(assignmentResponsesPage.plusWorkBoard.getText(),"+ Workboard feedback","Workboard feedback button is not available in the top right corner of the question pane.");

              //tc row no 363

            assignmentResponsesPage.plusWorkBoard.click();//click on +workboard feedback
            Thread.sleep(3000);
            String crossIcon =assignmentResponsesPage.crossIconOnWorkBoard.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(assignmentResponsesPage.frame);//switch to frame
            boolean toolPanel = assignmentResponsesPage.toolControl.isDisplayed();
            if (toolPanel == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            questions.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardInInstructorSide("text", 361);
            boolean workBoard1 = assignmentResponsesPage.plusWorkBoard.isDisplayed();
            if (workBoard1 == true) {
                Assert.fail("It is showing '+ Workboard feedback' button when pop_up is shown.");
            }
            String crossIcon1 = assignmentResponsesPage.crossIconOnWorkBoard.getText();
            Assert.assertEquals(crossIcon1, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");

            //tc row no 371
            assignmentResponsesPage.crossIconOnWorkBoard.click();//click on 'x'
            if(assignmentResponsesPage.getFeedbackTextArea().isDisplayed()==false){
                Assert.fail("Enter Feedback text field is not available.");
            }
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save


        } catch (Exception e) {
            Assert.fail("Exception in test case assessmentResponsesOfWorkBoardEnabledQuestionWithDataOverWorkBoard of class InstructorShouldBeAbleToViewEnhancementWorkBoard", e);
        }
    }


    @Test(priority = 6, enabled = true)
    public void assessmentResponsesOfWorkBoardEnabledQuestionWithDataOverWriteBoard() {

        try {
            //tc row no 374
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            EngagementReport engagementReport = PageFactory.initElements(driver,EngagementReport.class);
            new Assignment().create(374);//Create an assignment
            new Assignment().addQuestions(374, "writeboard", "");
            new LoginUsingLTI().ltiLogin("374_1");//login as student
            new TOCShow().chaptertree();//click on toc
            new TopicOpen().chapterOpen(2);//click on third chapter
            new SelectCourse().selectInvisibleAssignment("IT1923_static_Assessment_374");//click on particular assessment
            new AttemptTest().StaticTest();
            new LoginUsingLTI().ltiLogin("374");//login as instructor
            new Navigator().NavigateTo("Engagement Report");//navigate to current Assignment
            engagementReport.assessment.click();//click on assessment
            new TopicOpen().chapterOpen(2);//click on third chapter
            new SelectCourse().selectInvisibleAssignment("IT1923_static_Assessment_374");//click on particular assessment
            Thread.sleep(5000);
            new MouseHover().mouserhoverbywebelement(driver.findElement(By.xpath("(//div[@class='idb-gradebook-content-coloumn'])[2]"))); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(4000);
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'whiteBoard_iframe_kedukWBTEACHER__whiteBoard')]")));
            new Click().clickByid("text-btn");//click on text button
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
            Thread.sleep(3000);
            new TextSend().textsendbyid("text", "textEditor");
            new Click().clickbylistcssselector("button[type='button']", 2);//click on update
            driver.switchTo().defaultContent();//switch to main window
            Thread.sleep(1000);
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save


        } catch (Exception e) {
            Assert.fail("Exception in test case assessmentResponsesOfWorkBoardEnabledQuestionWithDataOverWriteBoard of class InstructorShouldBeAbleToViewEnhancementWorkBoard", e);
        }
    }



}
