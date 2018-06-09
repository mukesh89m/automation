package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT21.R217;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.EngagementReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
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
 * Created by Mukesh on 6/25/15.
 */
public class ViewYourWorkButtonDisable extends Driver{

    @Test(priority = 1,enabled = true)
    public void viewYourWorkButtonDisable()
    {
        try {
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            Questions questions = PageFactory.initElements(driver,Questions.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);


           new LoginUsingLTI().ltiLogin("157"); //login as instructor
            new LoginUsingLTI().ltiLogin("157_1"); //login as student*/
            new Assignment().create(157);
            new Assignment().addQuestions(157,"truefalse","");

            new LoginUsingLTI().ltiLogin("157");//login as instructor
            new Assignment().assignToStudent(157); //assign to the student

            new LoginUsingLTI().ltiLogin("157_1"); //login as student
            new Assignment().openAssignmentFromAssignmentPage(157);
            assignmentResponsesPage.plusWorkBoard.click();

            //Tc row no 158
            new Assignment().openAssignmentFromAssignmentPage(157);
            Assert.assertEquals(new BooleanValue().presenceOfElement(157,By.partialLinkText("View your work")),false,"View your work button should not be seen over the question content");

            //Tc row no 159
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new WriteBoard().drawSquareInWriteBoardInStudentSide(157);//draw square in workBoard
            questions.crossIcon.click();
            new Assignment().nextButtonInQuestionClick();
            Thread.sleep(2000);

            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new WriteBoard().drawSquareInWriteBoardInStudentSide(157);//draw square in workBoard
            questions.crossIcon.click();
            new Assignment().submitButtonInQuestionClick();//click on submit
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("157");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link

            new WriteBoard().drawSquareInWriteBoardInstructorSide(385);//draw square in workBoard
            assignmentResponsesPage.crossIconOnWorkBoard.click();//click on 'x'
            assignmentResponsesPage.getSaveButton().click();//click on save
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save

            new Assignment().releaseGrades(157, "Release Grade for All");
            new LoginUsingLTI().ltiLogin("157_1"); //login as student
            new Navigator().NavigateTo("Assignments");
            currentAssignments.getAssessmentName().click();
            new QuestionCard().clickOnCard("157",0);


            //Tc row no 160
            String viewYrWork= assignmentResponsesPage.plusWorkBoard.getText().trim();
            if(!viewYrWork.contains("View your work")) {
                Assert.fail("View your work button is not present with \"Instructor feedback\" icon next to button.");
            }

            Assert.assertEquals(new BooleanValue().presenceOfElement(157,By.className("whiteboard-feedback-teacher")),true,"Instructor feedback icon is not present");

           //Tc row no 161
            assignmentResponsesPage.plusWorkBoard.click();
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[starts-with(@id,'whiteBoard_iframe_kedukWBSTUDENT')]")));
            Assert.assertEquals(new BooleanValue().isElementPresent(assignmentResponsesPage.teacherFeedback),true,"Instructor feedback icon is not  enabled ");

            //Tc row no 162
            assignmentResponsesPage.teacherFeedback.click(); //click on the instructor icon


        } catch (Exception e) {
            Assert.fail("Exception in testcase viewYourWorkButtonDisable of class ViewYourWorkButtonDisable ",e);
        }

    }
    @Test(priority = 2,enabled = true)
    public void jumpOutViewFromCourseStream()
    {
        try {
            //Tc row no 165
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            Questions questions = PageFactory.initElements(driver,Questions.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            CourseStreamPage courseStreamPage=PageFactory.initElements(driver,CourseStreamPage.class);


            new LoginUsingLTI().ltiLogin("165"); //login as instructor
            new LoginUsingLTI().ltiLogin("165_1"); //login as student
            new Assignment().create(165);
            new Assignment().addQuestions(165,"all","");
            new LoginUsingLTI().ltiLogin("165");//login as instructor
            new Assignment().assignToStudent(165); //assign to the student

            new LoginUsingLTI().ltiLogin("165_1"); //login as student
            new Assignment().openAssignmentFromAssignmentPage(165); //open assignment from assignment page

            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new WriteBoard().drawSquareInWriteBoardInStudentSide(165);//draw square in workBoard
            questions.crossIcon.click();
            new Assignment().submitButtonInQuestionClick();//click on submit
            Thread.sleep(2000);

            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new WriteBoard().drawSquareInWriteBoardInStudentSide(165);//draw square in workBoard
            questions.crossIcon.click();
            new Assignment().submitButtonInQuestionClick();//click on submit
            Thread.sleep(2000);

            new Assignment().submitAssignmentAsStudent(165);
            new Navigator().NavigateTo("Assignments");
            currentAssignments.getAssessmentName().click();
            new QuestionCard().clickOnCard("165",0);
            new Navigator().navigateToTab("Discussion"); //navigate to question tab
            new Discussion().postDiscussion("new Discussion");

            new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
            courseStreamPage.getJumpOut().click(); //click on the jumpout icon


        } catch (Exception e) {
            Assert.fail("Exception in testcase jumpOutViewFromCourseStream of class ViewYourWorkButtonDisable ",e);
        }

    }

    @Test(priority = 3,enabled = true)
    public void reachAssignmentFromAssignmentTab()
    {
        try {
            //Tc row no 168
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            Questions questions = PageFactory.initElements(driver,Questions.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            CourseStreamPage courseStreamPage=PageFactory.initElements(driver,CourseStreamPage.class);
            AssignmentTab assignmentTab=PageFactory.initElements(driver,AssignmentTab.class);

            new LoginUsingLTI().ltiLogin("168_1"); //login as student
            new LoginUsingLTI().ltiLogin("168");//login as instructor
            new Assignment().assignToStudent(168); //assign to the student

            new LoginUsingLTI().ltiLogin("168_1"); //login as student
            new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments");
            assignmentTab.rightArrow.click();//click on the right arrow
            assignmentTab.open_button.click(); //click on the open button
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new WriteBoard().drawSquareInWriteBoardInStudentSide(168);//draw square in workBoard
            questions.crossIcon.click();
            new Assignment().nextButtonInQuestionClick();//click on submit
            Thread.sleep(2000);

            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new WriteBoard().drawSquareInWriteBoardInStudentSide(168);//draw square in workBoard
            questions.crossIcon.click();
            new Assignment().nextButtonInQuestionClick();//click on submit
            Thread.sleep(2000);

            new Assignment().submitAssignmentAsStudent(168);
            new Navigator().NavigateTo("Assignments");
            currentAssignments.getAssessmentName().click();
            new QuestionCard().clickOnCard("168",0);

            String viewYrWork= assignmentResponsesPage.plusWorkBoard.getText().trim();
            if(!viewYrWork.contains("View your work")) {
                Assert.fail("View your work button is not present with \"Instructor feedback\" icon next to button.");
            }
            assignmentResponsesPage.plusWorkBoard.click();
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[starts-with(@id,'whiteBoard_iframe_kedukWBSTUDENT')]")));
            Assert.assertEquals(new BooleanValue().isElementPresent(assignmentResponsesPage.teacherFeedback),true,"Instructor feedback icon is not  enabled ");



        } catch (Exception e) {
            Assert.fail("Exception in testcase reachAssignmentFromAssignmentTab of class ViewYourWorkButtonDisable ",e);
        }

    }

    @Test(priority = 4,enabled = true)
    public void reachAssignmentFromDashboard()
    {
        try {
            //Tc row no 169
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            Questions questions = PageFactory.initElements(driver,Questions.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            Dashboard dashboard=PageFactory.initElements(driver,Dashboard.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver,CourseStreamPage.class);
            new LoginUsingLTI().ltiLogin("169_1"); //login as student
            new LoginUsingLTI().ltiLogin("169");//login as instructor
            new Assignment().assignToStudent(169); //assign to the student

            new LoginUsingLTI().ltiLogin("169_1"); //login as student
            dashboard.assignment_Link.click(); //click on the assignment link
             courseStreamPage.assessmentName.click();
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new WriteBoard().drawSquareInWriteBoardInStudentSide(169);//draw square in workBoard
            questions.crossIcon.click();
            new Assignment().nextButtonInQuestionClick();//click on submit
            Thread.sleep(2000);

            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new WriteBoard().drawSquareInWriteBoardInStudentSide(169);//draw square in workBoard
            questions.crossIcon.click();
            new Assignment().nextButtonInQuestionClick();//click on submit
            Thread.sleep(2000);

            new Assignment().submitAssignmentAsStudent(169);
            new Navigator().NavigateTo("Assignments");
            currentAssignments.getAssessmentName().click();
            new QuestionCard().clickOnCard("169",0);

            String viewYrWork= assignmentResponsesPage.plusWorkBoard.getText().trim();
            if(!viewYrWork.contains("View your work")) {
                Assert.fail("View your work button is not present with \"Instructor feedback\" icon next to button.");
            }
            assignmentResponsesPage.plusWorkBoard.click();
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[starts-with(@id,'whiteBoard_iframe_kedukWBSTUDENT')]")));
            Assert.assertEquals(new BooleanValue().isElementPresent(assignmentResponsesPage.teacherFeedback),true,"Instructor feedback icon is not  enabled ");


        } catch (Exception e) {
            Assert.fail("Exception in testcase reachAssignmentFromDashboard of class ViewYourWorkButtonDisable ",e);
        }

    }
    @Test(priority = 5,enabled = true)
    public void  workboardOverlaySize()
    {
        try {
            //Tc row no 170
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            EngagementReport engagementReport=PageFactory.initElements(driver,EngagementReport.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            Questions questions = PageFactory.initElements(driver,Questions.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);


            new Assignment().create(170);
            new Assignment().addQuestions(170,"truefalse","");


            new LoginUsingLTI().ltiLogin("170");//login as instructor
            new LoginUsingLTI().ltiLogin("170_1"); //login as student
            new Navigator().NavigateTo("e-Textbook");
            new SelectCourse().selectInvisibleAssignment("Assignment_IT21_R217_170");

            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new WriteBoard().drawSquareInWriteBoardInStudentSide(170);//draw square in workBoard
            questions.crossIcon.click();//click on 'x'
            assignments.button_submitAnswer.click();//click

            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new WriteBoard().drawSquareInWriteBoardInStudentSide(170);//draw square in workBoard
            questions.crossIcon.click();//click on 'x'
            assignments.next_Button.click(); //click on the next button
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("170");//login as instructor
            new Navigator().NavigateTo("Engagement Report");//navigate to engagement report
            engagementReport.assessment.click(); //click on the assessments
            new SelectCourse().selectInvisibleAssignment("Assignment_IT21_R217_170");
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link

            //Tc row no 171
            Assert.assertEquals(assignmentResponsesPage.workBoardMessage.getText(),"Review students workboard","Workboard related message is not displayed accordingly( Review student's workboard).");
            Assert.assertEquals(assignmentResponsesPage.plusWorkBoard.getText(),"+ Workboard feedback","Workboard feedback button is not available in the top right corner of the question pane.");

            //Tc row no 172
            assignmentResponsesPage.plusWorkBoard.click();//click on +workboard feedback
            String crossIcon =assignmentResponsesPage.crossIconOnWorkBoard.getText();
            Assert.assertEquals(crossIcon, "x", "The overlay must seen.");

            //Tc row no 176
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'whiteBoard_iframe_kedukWBTEACHER__whiteBoard')]")));
            Assert.assertEquals(assignmentResponsesPage.teacherFeedback.isDisplayed(),false, "The feedback icon in the tool bar is not enabled.");
            //Tc row no 178 & 179

            new Click().clickByid("square-btn");//click inside the board
            Thread.sleep(2000);
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
            Thread.sleep(3000);
            driver.switchTo().defaultContent();
            driver.findElement(By.xpath("/html/body")).click();
            assignmentResponsesPage.getScore().clear();
            assignmentResponsesPage.getScore().sendKeys("0.5");
            Thread.sleep(1000);
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save
            questions.crossIcon.click();//click on 'x'

            //Tc row no 184
            Assert.assertEquals(new BooleanValue().presenceOfElement(184,By.className("close-iframe-question-content")),true,"overlay is not closed");
            assignmentResponsesPage.getSaveButton().click();//click on save


        } catch (Exception e) {
            Assert.fail("Exception in testcase workboardOverlaySize of class ViewYourWorkButtonDisable ",e);
        }

    }

    @Test(priority = 5,enabled = true)
    public void  workboardOverlaySizeForCustomAssignment()
    {
        try {
            //Tc row no 186
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "186");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "186");
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            Questions questions = PageFactory.initElements(driver,Questions.class);
            MyQuestionBank myQuestionBank=PageFactory.initElements(driver,MyQuestionBank.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);


            new Assignment().create(186);
            new Assignment().addQuestions(186,"truefalse","");

            new LoginUsingLTI().ltiLogin("186_1"); //login as student
            new LoginUsingLTI().ltiLogin("186");//login as instructor

            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);

            new AssignLesson().selectQuestionForCustomAssignment("186");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later

            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='resource-title']")));
            new AssignLesson().Assigncustomeassignemnt(186);//assign assignment


            new LoginUsingLTI().ltiLogin("186_1"); //login as student
            new Assignment().openAssignmentFromAssignmentPage(186);

            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new WriteBoard().drawSquareInWriteBoardInStudentSide(186);//draw square in workBoard
            questions.crossIcon.click();//click on 'x'
            new Assignment().nextButtonInQuestionClick();

            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new WriteBoard().drawSquareInWriteBoardInStudentSide(186);//draw square in workBoard
            questions.crossIcon.click();//click on 'x'
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("186");//login as instructor
            new Navigator().NavigateTo("Current Assignments"); //navigate to the current assignment
            currentAssignments.getViewGrade_link().click(); //click on the view student response

            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link

            //Tc row no 186
            Assert.assertEquals(assignmentResponsesPage.workBoardMessage.getText(),"Review students workboard","Workboard related message is not displayed accordingly( Review student's workboard).");
            Assert.assertEquals(assignmentResponsesPage.plusWorkBoard.getText(),"+ Workboard feedback","Workboard feedback button is not available in the top right corner of the question pane.");

            //Tc row no 187
            assignmentResponsesPage.plusWorkBoard.click();//click on +workboard feedback
            String crossIcon =assignmentResponsesPage.crossIconOnWorkBoard.getText();
            Assert.assertEquals(crossIcon, "x", "The overlay must seen.");

            //Tc row no 176
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'whiteBoard_iframe_kedukWBTEACHER__whiteBoard')]")));
            Assert.assertEquals(assignmentResponsesPage.teacherFeedback.isDisplayed(),false, "The feedback icon in the tool bar is not enabled.");
            //Tc row no 178 & 179

            new Click().clickByid("square-btn");//click inside the board
            Thread.sleep(2000);
            String widthvalue2= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue2+"']")).click();
            Thread.sleep(3000);
            driver.switchTo().defaultContent();
            driver.findElement(By.xpath("/html/body")).click();
            assignmentResponsesPage.getScore().clear();
            assignmentResponsesPage.getScore().sendKeys("0.5");
            Thread.sleep(1000);
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save
            assignmentResponsesPage.crossIconOnWorkBoard.click();//click on 'x'

            Assert.assertEquals(new BooleanValue().presenceOfElement(184, By.className("close-iframe-question-content")), true, "overlay is not closed");
            assignmentResponsesPage.getSaveButton().click();//click on save

            new WriteBoard().instructorEnterTextInWriteBoard("text",200);
            assignmentResponsesPage.getSaveButton().click();//click on save

        } catch (Exception e) {
            Assert.fail("Exception in testcase workboardOverlaySizeForCustomAssignment of class ViewYourWorkButtonDisable ",e);
        }

    }

    @Test(priority = 6,enabled = true)
    public void  TryItOptionInMyQuestionBank()
    {
        try {
            //Tc row no 203
            QuestionBank questionBank=PageFactory.initElements(driver,QuestionBank.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            Questions questions = PageFactory.initElements(driver,Questions.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);

            new LoginUsingLTI().ltiLogin("186_1"); //login as student
            new LoginUsingLTI().ltiLogin("186");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            String parentWindow = driver.getWindowHandle();
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new UIElement().waitAndFindElement(By.className("true-false-student-answer-label"));

            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new WriteBoard().drawSquareInWriteBoardInStudentSide(170);//draw square in workBoard
            questions.crossIcon.click();//click on 'x'
            currentAssignments.next_Button.click();//click on next button
            Thread.sleep(2000);
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new WriteBoard().drawSquareInWriteBoardInStudentSide(170);//draw square in workBoard
            questions.crossIcon.click();//click on 'x'
            currentAssignments.finishButton.click();//click on finish button

            driver.switchTo().window(parentWindow); //switch back to the current window

            new LoginUsingLTI().ltiLogin("186");//login as instructor
            new Navigator().NavigateTo("Current Assignments"); //navigate to the current assignment
            currentAssignments.getViewGrade_link().click(); //click on the view student response

            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link

            //Tc row no 171
            Assert.assertEquals(assignmentResponsesPage.workBoardMessage.getText(),"Review students workboard","Workboard related message is not displayed accordingly( Review student's workboard).");
            Assert.assertEquals(assignmentResponsesPage.plusWorkBoard.getText(),"+ Workboard feedback","Workboard feedback button is not available in the top right corner of the question pane.");

            //Tc row no 172
            assignmentResponsesPage.plusWorkBoard.click();//click on +workboard feedback
            String crossIcon =assignmentResponsesPage.crossIconOnWorkBoard.getText();
            Assert.assertEquals(crossIcon, "x", "The overlay must seen.");

            //Tc row no 176
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'whiteBoard_iframe_kedukWBTEACHER__whiteBoard')]")));
            Assert.assertEquals(assignmentResponsesPage.teacherFeedback.isDisplayed(),false, "The feedback icon in the tool bar is not enabled.");
            //Tc row no 178 & 179

            new Click().clickByid("square-btn");//click inside the board
            Thread.sleep(2000);
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
            Thread.sleep(3000);
            driver.switchTo().defaultContent();
            driver.findElement(By.xpath("/html/body")).click();
            assignmentResponsesPage.getScore().clear();
            assignmentResponsesPage.getScore().sendKeys("0.5");
            Thread.sleep(1000);
            assignmentResponsesPage.getFeedbackTextArea().clear();
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save
            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            questions.crossIcon.click();//click on 'x'

            Assert.assertEquals(new BooleanValue().presenceOfElement(184, By.className("close-iframe-question-content")), true, "overlay is not closed");
            assignmentResponsesPage.getSaveButton().click();//click on save
            html.sendKeys(Keys.chord(Keys.CONTROL, "0"));

        } catch (Exception e) {
            Assert.fail("Exception in testcase TryItOptionInMyQuestionBank of class ViewYourWorkButtonDisable ",e);
        }

    }


    @Test(priority = 7,enabled = true)
    public void  instructorAssignmentViewFromGradeBookPage()
    {
        try {
            //Tc row no 203
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Questions questions = PageFactory.initElements(driver,Questions.class);
            Gradebook gradebook=PageFactory.initElements(driver,Gradebook.class);

            new LoginUsingLTI().ltiLogin("186_1"); //login as student
            new LoginUsingLTI().ltiLogin("186");//login as instructor
            new Navigator().NavigateTo("Gradebook"); //navigate to gradebook page
            gradebook.getAllAssignmentName().get(0).click(); //click on the assignment


            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link

            //Tc row no 171
            Assert.assertEquals(assignmentResponsesPage.workBoardMessage.getText(),"Review students workboard","Workboard related message is not displayed accordingly( Review student's workboard).");
            Assert.assertEquals(assignmentResponsesPage.plusWorkBoard.getText(),"+ Workboard feedback","Workboard feedback button is not available in the top right corner of the question pane.");

            //Tc row no 172
            assignmentResponsesPage.plusWorkBoard.click();//click on +workboard feedback
            String crossIcon =assignmentResponsesPage.crossIconOnWorkBoard.getText();
            Assert.assertEquals(crossIcon, "x", "The overlay must seen.");

            //Tc row no 176
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'whiteBoard_iframe_kedukWBTEACHER__whiteBoard')]")));
            Assert.assertEquals(assignmentResponsesPage.teacherFeedback.isDisplayed(),false, "The feedback icon in the tool bar is not enabled.");
            //Tc row no 178 & 179

            new Click().clickByid("square-btn");//click inside the board
            Thread.sleep(2000);
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
            driver.switchTo().defaultContent();
            driver.findElement(By.xpath("/html/body")).click();
            assignmentResponsesPage.getScore().clear();
            assignmentResponsesPage.getScore().sendKeys("0.5");
            assignmentResponsesPage.getFeedbackTextArea().clear();
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            assignmentResponsesPage.getSaveButton().click();//click on save

            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            questions.crossIcon.click();//click on 'x'
            Assert.assertEquals(new BooleanValue().presenceOfElement(184, By.className("close-iframe-question-content")), true, "overlay is not closed");

            new WriteBoard().instructorEnterTextInWriteBoard("text", 200);
            assignmentResponsesPage.getSaveButton().click();//click on save*/
            html.sendKeys(Keys.chord(Keys.CONTROL, "0"));

        } catch (Exception e) {
            Assert.fail("Exception in testcase instructorAssignmentViewFromGradeBookPage of class ViewYourWorkButtonDisable ",e);
        }

    }
    @Test(priority = 8,enabled = true)
    public void matchFollowingQuestion()
    {
        try {
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            Questions questions = PageFactory.initElements(driver,Questions.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);

            new Assignment().create(130);
            new Assignment().addQuestions(130,"draganddrop","");
            new Assignment().addQuestions(130,"matchthefollowing","");

            new LoginUsingLTI().ltiLogin("130"); //login as instructor
            new LoginUsingLTI().ltiLogin("130_1"); //login as student

            new LoginUsingLTI().ltiLogin("130");//login as instructor
            new Assignment().assignToStudent(130); //assign to the student

            new LoginUsingLTI().ltiLogin("130_1"); //login as student
            new Assignment().openAssignmentFromAssignmentPage(130);
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new WriteBoard().drawSquareInWriteBoardInStudentSide(157);//draw square in workBoard
            questions.crossIcon.click();
            new Assignment().nextButtonInQuestionClick();//click on Next button
            assignmentResponsesPage.plusWorkBoard.click();

            driver.switchTo().frame(questions.frame);
            if(!assignments.workBoard_overlay.isDisplayed()){
                Assert.fail("Workboard overlay is not seen");
            }

            new Click().clickByid("square-btn");//click inside the board
            Thread.sleep(2000);
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
            driver.switchTo().defaultContent();
            driver.findElement(By.xpath("/html/body")).click();

            if(!questions.crossIcon.isDisplayed()){
                Assert.fail("close icon is not present over workboard overlay");

            }
            if(assignmentResponsesPage.plusWorkBoard.isDisplayed()){
                Assert.fail("Workboard overlay is present on \"Performance\" bar.");
            }
            questions.crossIcon.click();//click on 'x'
            if(questions.crossIcon.isDisplayed()){
                Assert.fail("close icon is present");

            }

            //Tc row no 133
            new UIElement().waitAndFindElement(By.cssSelector("i[class='ls-arrow ls-arrow--left']"));
            List<WebElement> performance = assignments.performancebar_leftarrow;
            for(WebElement ele:performance){
            if(ele.isDisplayed()) {
                ele.click(); //click on the performance bar
              }
            }

            if(!driver.findElement(By.className("al-content-box-wrapper")).isDisplayed()){
                Assert.fail("Workboard overlay is present on \"Performance\" bar.");

            }

            //Tc row no 135
            new UIElement().waitAndFindElement(By.cssSelector("i[class='ls-arrow ls-arrow--right']"));
            List<WebElement> performance1 = assignments.performancebar_rightarrow;
            for(WebElement ele:performance1){
                if(ele.isDisplayed()) {
                    ele.click(); //click on the performance bar

                }
            }
            if(!assignmentResponsesPage.plusWorkBoard.isDisplayed()){
                Assert.fail("Workboard overlay is not present on \"Performance\" bar.");
            }

            String viewYrWork= assignmentResponsesPage.plusWorkBoard.getText().trim();
            if(viewYrWork.contains("View your work")) {
                Assert.fail("View your work button is not present with \"Instructor feedback\" icon next to button.");
            }

            Assert.assertEquals(new BooleanValue().presenceOfElement(136,By.className("whiteboard-feedback-teacher")),false,"Instructor feedback icon is not present");

            new Assignment().submitAssignmentAsStudent(130);

            new LoginUsingLTI().ltiLogin("130"); //login as instructor
            new Assignment().releaseGrades(130, "Release Grade for All");

            new LoginUsingLTI().ltiLogin("130_1"); //login as student
            new Navigator().NavigateTo("Assignments");
            currentAssignments.getAssessmentName().click();
            new QuestionCard().clickOnCard("168",0);

            String viewYrWork1= assignmentResponsesPage.plusWorkBoard.getText().trim();
            if(!viewYrWork1.contains("View your work")) {
                Assert.fail("View your work button is not present with \"Instructor feedback\" icon next to button.");
            }

            Assert.assertEquals(new BooleanValue().presenceOfElement(157,By.className("whiteboard-feedback-teacher")),false,"Instructor feedback icon is not present");

            assignmentResponsesPage.plusWorkBoard.click(); //click on view yr work
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[starts-with(@id,'whiteBoard_iframe_kedukWBSTUDENT')]")));
            Assert.assertEquals(new BooleanValue().isElementPresent(assignmentResponsesPage.teacherFeedback),true,"Instructor feedback icon is not  enabled ");

            assignmentResponsesPage.teacherFeedback.click(); //click on the instructor icon


        } catch (Exception e) {
            Assert.fail("Exception in testcase matchFollowingQuestion of class ViewYourWorkButtonDisable ",e);
        }

    }

    @Test(priority = 9,enabled = true)
    public void instructorTooltip()
    {
        try {
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            Questions questions = PageFactory.initElements(driver,Questions.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);


            new LoginUsingLTI().ltiLogin("163"); //login as instructor
            new LoginUsingLTI().ltiLogin("163_1"); //login as student
            new Assignment().create(163);
            new Assignment().addQuestions(163,"truefalse","");

            new LoginUsingLTI().ltiLogin("163");//login as instructor
            new Assignment().assignToStudent(163); //assign to the student

            new LoginUsingLTI().ltiLogin("163_1"); //login as student
            new Assignment().openAssignmentFromAssignmentPage(163);
            assignmentResponsesPage.plusWorkBoard.click();
            //Tc row no 158
            driver.switchTo().frame(questions.frame);
            Assert.assertEquals(questions.teacherFeedback.getAttribute("title").trim(),"Show instructor comments","Show instructor comments");
            driver.switchTo().defaultContent();

            questions.crossIcon.click(); //click on the cross icon
            new AttemptQuestion().trueFalse(false, "incorrect", 163);
            new WriteBoard().enterTextInWriteBoardFromCMS("text",163);
            driver.findElement(By.className("close-iframe-question-content")).click();
            assignments.getNextQuestion().click();
            new AttemptQuestion().trueFalse(false,"incorrect",192);
            assignments.getFinishAssignment().click();

            new LoginUsingLTI().ltiLogin("163");//login as instructor
            new Assignment().releaseGrades(163, "Release Grade for All");

            new LoginUsingLTI().ltiLogin("163_1"); //login as student
            new Navigator().NavigateTo("Proficiency Report"); //navigate to proficiency report
            new QuestionCard().clickOnCard("168",1);
            assignmentResponsesPage.plusWorkBoard.click(); //click on view yr work
            driver.switchTo().frame(questions.frame);
            Assert.assertEquals(questions.teacherFeedback.getAttribute("title").trim(),"Show instructor comments","Show instructor comments");


        } catch (Exception e) {
            Assert.fail("Exception in testcase instructorTooltip of class ViewYourWorkButtonDisable ",e);
        }

    }
}
