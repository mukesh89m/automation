package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R238;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.PerformanceSummary;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
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

/**
 * Created by priyanka on 9/8/2015.
 */
public class InstructorAssignAssignmentHavingMonospaceFont extends Driver {

    @Test(priority = 1, enabled = true)
    public void InstructorAssignAssignmentTrueFalseMonospaceFont() {

        try {
            //TC row no 488
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new LoginUsingLTI().ltiLogin("488"); //login as instructor
            new Assignment().assignToStudent(488);

            new LoginUsingLTI().ltiLogin("488_1");//login as student
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assessment name
            Assert.assertEquals(courseStreamPage.questionText.getCssValue("font-family"), "monospace", "question text is not in monospace font");
            currentAssignments.answerChoice.get(0).click();
            courseStreamPage.finishAssignment.click();//click on finish
            verifyMonoSpaceFontAtStudentSide();

            new LoginUsingLTI().ltiLogin("488");//log in as instructor
            verifyMonoSpaceFontInTeacherFeedback();

        } catch (Exception e) {
            Assert.fail("Exception in TC InstructorAssignAssignmentTrueFalseMonospaceFont of class InstructorAssignAssignmentHavingMonospaceFont", e);
        }
    }



    @Test(priority = 2, enabled = true)
    public void InstructorAssignAssignmentMultipleChoiceMonospaceFont() {

        try {
            //TC row no 488
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("4881"); //login as instructor
            new Assignment().assignToStudent(4881);

            new LoginUsingLTI().ltiLogin("4881_1");//login as student
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assessment name
            Assert.assertEquals(courseStreamPage.questionText.getCssValue("font-family"), "monospace", "question text is not in monospace font");
            courseStreamPage.finishAssignment.click();//click on finish
            verifyMonoSpaceFontAtStudentSide();

            new LoginUsingLTI().ltiLogin("4881");//log in as instructor
            verifyMonoSpaceFontInTeacherFeedback();

        } catch (Exception e) {
            Assert.fail("Exception in TC InstructorAssignAssignmentMultipleChoiceMonospaceFont of class InstructorAssignAssignmentHavingMonospaceFont", e);
        }
    }


    @Test(priority = 3, enabled = true)
    public void InstructorAssignAssignmentMultipleSelectionMonospaceFont() {

        try {
            //TC row no 488
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("4882"); //login as instructor
            new Assignment().assignToStudent(4882);

            new LoginUsingLTI().ltiLogin("4882_1");//login as student
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assessment name
            Assert.assertEquals(courseStreamPage.questionText.getCssValue("font-family"), "monospace", "question text is not in monospace font");
            courseStreamPage.finishAssignment.click();//click on finish
            verifyMonoSpaceFontAtStudentSide();

            new LoginUsingLTI().ltiLogin("4882");//log in as instructor
            verifyMonoSpaceFontInTeacherFeedback();

        } catch (Exception e) {
            Assert.fail("Exception in TC InstructorAssignAssignmentMultipleSelectionMonospaceFont of class InstructorAssignAssignmentHavingMonospaceFont", e);
        }
    }


    @Test(priority = 4, enabled = true)
    public void InstructorAssignAssignmentTextEntryMonospaceFont() {

        try {
            //TC row no 488
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("4883"); //login as instructor
            new Assignment().assignToStudent(4883);

            new LoginUsingLTI().ltiLogin("4883_1");//login as student
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assessment name
            Assert.assertEquals(courseStreamPage.questionText.getCssValue("font-family"), "monospace", "question text is not in monospace font");
            courseStreamPage.finishAssignment.click();//click on finish
            verifyMonoSpaceFontAtStudentSide();

            new LoginUsingLTI().ltiLogin("4883");//log in as instructor
            verifyMonoSpaceFontInTeacherFeedback();

        } catch (Exception e) {
            Assert.fail("Exception in TC InstructorAssignAssignmentTextEntryMonospaceFont of class InstructorAssignAssignmentHavingMonospaceFont", e);
        }
    }



    @Test(priority = 5, enabled = true)
    public void InstructorAssignAssignmentTextDropDownMonospaceFont() {

        try {
            //TC row no 488
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("4884"); //login as instructor
            new Assignment().assignToStudent(4884);

            new LoginUsingLTI().ltiLogin("4884_1");//login as student
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assessment name
            Assert.assertEquals(courseStreamPage.questionText.getCssValue("font-family"), "monospace", "question text is not in monospace font");
            courseStreamPage.finishAssignment.click();//click on finish
            verifyMonoSpaceFontAtStudentSide();

            new LoginUsingLTI().ltiLogin("4884");//log in as instructor
            verifyMonoSpaceFontInTeacherFeedback();

        } catch (Exception e) {
            Assert.fail("Exception in TC InstructorAssignAssignmentTextDropDownMonospaceFont of class InstructorAssignAssignmentHavingMonospaceFont", e);
        }
    }


    @Test(priority = 6, enabled = true)
    public void InstructorAssignAssignmentNumericEntryMonospaceFont() {

        try {
            //TC row no 488
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("4885"); //login as instructor
            new Assignment().assignToStudent(4885);

            new LoginUsingLTI().ltiLogin("4885_1");//login as student
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assessment name
            Assert.assertEquals(courseStreamPage.questionText.getCssValue("font-family"), "monospace", "question text is not in monospace font");
            courseStreamPage.finishAssignment.click();//click on finish
            verifyMonoSpaceFontAtStudentSide();

            new LoginUsingLTI().ltiLogin("4885");//log in as instructor
            verifyMonoSpaceFontInTeacherFeedback();

        } catch (Exception e) {
            Assert.fail("Exception in TC InstructorAssignAssignmentNumericEntryMonospaceFont of class InstructorAssignAssignmentHavingMonospaceFont", e);
        }
    }



    @Test(priority = 7, enabled = true)
    public void InstructorAssignAssignmentAdvancedNumericMonospaceFont() {

        try {
            //TC row no 488
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("4886"); //login as instructor
            new Assignment().assignToStudent(4886);

            new LoginUsingLTI().ltiLogin("4886_1");//login as student
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assessment name
            Assert.assertEquals(courseStreamPage.questionText.getCssValue("font-family"), "monospace", "question text is not in monospace font");
            courseStreamPage.finishAssignment.click();//click on finish
            verifyMonoSpaceFontAtStudentSide();

            new LoginUsingLTI().ltiLogin("4886");//log in as instructor
            verifyMonoSpaceFontInTeacherFeedback();

        } catch (Exception e) {
            Assert.fail("Exception in TC InstructorAssignAssignmentAdvancedNumericMonospaceFont of class InstructorAssignAssignmentHavingMonospaceFont", e);
        }
    }


    @Test(priority = 8, enabled = true)
    public void InstructorAssignAssignmentExpressionEvaluatorMonospaceFont() {

        try {
            //TC row no 488
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("4887"); //login as instructor
            new Assignment().assignToStudent(4887);

            new LoginUsingLTI().ltiLogin("4887_1");//login as student
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assessment name
            Assert.assertEquals(courseStreamPage.questionText.getCssValue("font-family"), "monospace", "question text is not in monospace font");
            courseStreamPage.finishAssignment.click();//click on finish
            verifyMonoSpaceFontAtStudentSide();

            new LoginUsingLTI().ltiLogin("4887");//log in as instructor
            verifyMonoSpaceFontInTeacherFeedback();

        } catch (Exception e) {
            Assert.fail("Exception in TC InstructorAssignAssignmentExpressionEvaluatorMonospaceFont of class InstructorAssignAssignmentHavingMonospaceFont", e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void InstructorAssignAssignmentEssayTypeMonospaceFont() {

        try {
            //TC row no 488
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("4888"); //login as instructor
            new Assignment().assignToStudent(4888);

            new LoginUsingLTI().ltiLogin("4888_1");//login as student
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assessment name
            Assert.assertEquals(courseStreamPage.questionText.getCssValue("font-family"), "monospace", "question text is not in monospace font");
            courseStreamPage.finishAssignment.click();//click on finish
            verifyMonoSpaceFontAtStudentSide();

            new LoginUsingLTI().ltiLogin("4888");//log in as instructor
            verifyMonoSpaceFontInTeacherFeedback();

        } catch (Exception e) {
            Assert.fail("Exception in TC InstructorAssignAssignmentEssayTypeMonospaceFont of class InstructorAssignAssignmentHavingMonospaceFont", e);
        }
    }


    @Test(priority = 10, enabled = true)
    public void InstructorAssignAssignmentWorkBoardMonospaceFont() {

        try {
            //TC row no 488
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("4889"); //login as instructor
            new Assignment().assignToStudent(4889);

            new LoginUsingLTI().ltiLogin("4889_1");//login as student
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assessment name
            Assert.assertEquals(courseStreamPage.questionText.getCssValue("font-family"), "monospace", "question text is not in monospace font");
            courseStreamPage.finishAssignment.click();//click on finish
            verifyMonoSpaceFontAtStudentSide();

            new LoginUsingLTI().ltiLogin("4889");//log in as instructor
            verifyMonoSpaceFontInTeacherFeedback();

        } catch (Exception e) {
            Assert.fail("Exception in TC InstructorAssignAssignmentWorkBoardMonospaceFont of class InstructorAssignAssignmentHavingMonospaceFont", e);
        }
    }


    @Test(priority = 11, enabled = true)
    public void InstructorAssignAssignmentAudioMonospaceFont() {

        try {
            //TC row no 488
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("48810"); //login as instructor
            new Assignment().assignToStudent(48810);

            new LoginUsingLTI().ltiLogin("48810_1");//login as student
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assessment name
            Assert.assertEquals(courseStreamPage.questionText.getCssValue("font-family"), "monospace", "question text is not in monospace font");
            courseStreamPage.finishAssignment.click();//click on finish
            verifyMonoSpaceFontAtStudentSide();

            new LoginUsingLTI().ltiLogin("48810");//log in as instructor
            verifyMonoSpaceFontInTeacherFeedback();

        } catch (Exception e) {
            Assert.fail("Exception in TC InstructorAssignAssignmentAudioMonospaceFont of class InstructorAssignAssignmentHavingMonospaceFont", e);
        }
    }


    @Test(priority = 12, enabled = true)
    public void InstructorAssignAssignmentMatchTheFollowingMonospaceFont() {

        try {
            //TC row no 488
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("48811"); //login as instructor
            new Assignment().assignToStudent(48811);

            new LoginUsingLTI().ltiLogin("48811_1");//login as student
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assessment name
            Assert.assertEquals(courseStreamPage.questionText.getCssValue("font-family"), "monospace", "question text is not in monospace font");
            courseStreamPage.finishAssignment.click();//click on finish
            verifyMonoSpaceFontAtStudentSide();

            new LoginUsingLTI().ltiLogin("48811");//log in as instructor
            verifyMonoSpaceFontInTeacherFeedback();

        } catch (Exception e) {
            Assert.fail("Exception in TC InstructorAssignAssignmentMatchTheFollowingMonospaceFont of class InstructorAssignAssignmentHavingMonospaceFont", e);
        }
    }

    @Test(priority = 13, enabled = true)
    public void InstructorAssignAssignmentDragDropMonospaceFont() {

        try {
            //TC row no 488
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("48812"); //login as instructor
            new Assignment().assignToStudent(48812);

            new LoginUsingLTI().ltiLogin("48812_1");//login as student
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assessment name
            Assert.assertEquals(courseStreamPage.questionText.getCssValue("font-family"), "monospace", "question text is not in monospace font");
            courseStreamPage.finishAssignment.click();//click on finish
            verifyMonoSpaceFontAtStudentSide();

            new LoginUsingLTI().ltiLogin("48812");//log in as instructor
            verifyMonoSpaceFontInTeacherFeedback();

        } catch (Exception e) {
            Assert.fail("Exception in TC InstructorAssignAssignmentDragDropMonospaceFont of class InstructorAssignAssignmentHavingMonospaceFont", e);
        }
    }


    @Test(priority = 14, enabled = true)
    public void InstructorAssignAssignmentLabelAnImageTextMonospaceFont() {

        try {
            //TC row no 488
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("48813"); //login as instructor
            new Assignment().assignToStudent(48813);

            new LoginUsingLTI().ltiLogin("48813_1");//login as student
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assessment name
            Assert.assertEquals(courseStreamPage.questionText.getCssValue("font-family"), "monospace", "question text is not in monospace font");
            courseStreamPage.finishAssignment.click();//click on finish
            verifyMonoSpaceFontAtStudentSide();

            new LoginUsingLTI().ltiLogin("48813");//log in as instructor
            verifyMonoSpaceFontInTeacherFeedback();

        } catch (Exception e) {
            Assert.fail("Exception in TC InstructorAssignAssignmentLabelAnImageTextMonospaceFont of class InstructorAssignAssignmentHavingMonospaceFont", e);
        }
    }


    @Test(priority = 15, enabled = true)
    public void InstructorAssignAssignmentLAnImageDropDownMonospaceFont() {

        try {
            //TC row no 488
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("48814"); //login as instructor
            new Assignment().assignToStudent(48814);

            new LoginUsingLTI().ltiLogin("48814_1");//login as student
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assessment name
            Assert.assertEquals(courseStreamPage.questionText.getCssValue("font-family"), "monospace", "question text is not in monospace font");
            courseStreamPage.finishAssignment.click();//click on finish
            verifyMonoSpaceFontAtStudentSide();

            new LoginUsingLTI().ltiLogin("48814");//log in as instructor
            verifyMonoSpaceFontInTeacherFeedback();

        } catch (Exception e) {
            Assert.fail("Exception in TC InstructorAssignAssignmentLAnImageDropDownMonospaceFont of class InstructorAssignAssignmentHavingMonospaceFont", e);
        }
    }



    public void verifyMonoSpaceFontAtStudentSide() {
        try {
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);

            //Assert.assertEquals(performanceSummary.getQuestionCardList().get(0).getCssValue("font-family"), "monospace", "question text on question card is not in monospace font");
            Thread.sleep(2000);
            new ClickOnquestionCard().clickonquestioncard(0);
            assignments.getDiscussionTab().click();
            assignments.getNewButton().click();
            assignments.getEditBox().sendKeys("This is Discussion");
            assignments.getSubmit().click();
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.getJumpOut().click();//click on jump out
            new UIElement().waitAndFindElement(By.xpath("//label[@id='question-content-label']/inline"));
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assessment name
            new ClickOnquestionCard().clickonquestioncard(0);
            Assert.assertEquals(courseStreamPage.questionText.getCssValue("font-family"), "monospace", "question text is not in monospace font");
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assessment name
            proficiencyReport.getBarChart().click();//click on bar
            Assert.assertEquals(courseStreamPage.questionText.getCssValue("font-family"), "monospace", "question text is not in monospace font");

        } catch (Exception e) {
            Assert.fail("Exception in TC verifyMonoSpaceFontAtStudentSide of class InstructorAssignAssignmentHavingMonospaceFont", e);

        }
    }






    public void verifyMonoSpaceFontInTeacherFeedback() {
        try {

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Gradebook gradebook = PageFactory.initElements(driver, Gradebook.class);

            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getViewGrade_link().click();//click on view student responses
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Assert.assertEquals(assignmentResponsesPage.T_Icon.isDisplayed(), true, "T-icon is not displaying");
            assignmentResponsesPage.T_Icon.click();//click on t-icon
            assignmentResponsesPage.fontFamily.click();//click on font family icon
            Assert.assertEquals(assignmentResponsesPage.openSansFont.get(0).isDisplayed(), true, "open sans is not checked by default");
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is feedback");
            // Assert.assertEquals(assignmentResponsesPage.text_Feedback.getCssValue("font-family"),"Open Sans","question text is not in open sans font");
            Actions actions = new Actions(driver);
            WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='view-user-question-performance-feedback-box']/p")));
            actions.moveToElement(element, 0, 30)
                    .clickAndHold()
                    .moveByOffset(510, 10)
                    .release()
                    .perform();
            assignmentResponsesPage.fontFamily.click();//click on font family icon
            assignmentResponsesPage.monoSpaceFont.get(0).click();//click on monospace
            System.out.println("1 : " + assignmentResponsesPage.text_FeedbackAfterSelect.getCssValue("font-family"));
            Assert.assertEquals(assignmentResponsesPage.text_FeedbackAfterSelect.getCssValue("font-family"), "monospace", "question text is not in open sans font");
            new Navigator().NavigateTo("Gradebook");// navigate to grade book
            gradebook.getAllAssignmentName().get(0).click();//click on assignment name
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Assert.assertEquals(assignmentResponsesPage.T_Icon.isDisplayed(), true, "T-icon is not displaying");
            assignmentResponsesPage.T_Icon.click();//click on t-icon
            assignmentResponsesPage.fontFamily.click();//click on font family icon
            Assert.assertEquals(assignmentResponsesPage.openSansFont.get(0).isDisplayed(), true, "open sans is not checked by default");
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is feedback");
            // Assert.assertEquals(assignmentResponsesPage.text_Feedback.getCssValue("font-family"),"Open Sans","question text is not in open sans font");
            WebElement element1 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='view-user-question-performance-feedback-box']/p")));
            actions.moveToElement(element1, 0, 30)
                    .clickAndHold()
                    .moveByOffset(510, 10)
                    .release()
                    .perform();
            assignmentResponsesPage.fontFamily.click();//click on font family icon
            assignmentResponsesPage.monoSpaceFont.get(0).click();//click on monospace
            Assert.assertEquals(assignmentResponsesPage.text_FeedbackAfterSelect.getCssValue("font-family"), "monospace", "question text is not in open sans font");

        } catch (Exception e) {
            Assert.fail("Exception in TC verifyMonoSpaceFontInTeacherFeedback of class InstructorAssignAssignmentHavingMonospaceFont", e);

        }
    }

}