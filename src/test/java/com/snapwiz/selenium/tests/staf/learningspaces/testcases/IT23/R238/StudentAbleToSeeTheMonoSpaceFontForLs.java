package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R238;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.EngagementReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by priyanka on 9/15/2015.
 */
public class StudentAbleToSeeTheMonoSpaceFontForLs extends  Driver{
    @DataProvider(name = "questionType")
    public Object[][] getQuestionTypes() {
        return new Object[][] {
                {"TrueFalseQuestionType"},
                {"MultipleChoiceQuestionType"},
                {"MultipleSelectionQuestionType"},
                {"TextEntryQuestionType"},
                {"TextDropDownQuestionType"},
                {"NumericEntryQuestionType"},
                {"AdvancedNumericQuestionType"},
                {"ExpressionEvaluatorQuestionType"},
                {"EssayTypeQuestionType"},
                {"WorkBoardQuestionType"},
                {"AudioQuestionType"},
                {"MatchFollowingQuestionType"},
                {"DragDropQuestionType"},
                {"LabelAnImageTextQuestionType"},
                {"LabelAnImageDropDownQuestionType"},

        };
    }

    @Test(priority = 1, enabled = true,dataProvider = "questionType")
    public void StudentAbleToSeeTheMonoSpaceFontForTrueFalse(String assignmentname) {

        try {
            //TC row no 390
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            new LoginUsingLTI().ltiLogin("390"); //login as student
            new TOCShow().chaptertree();//click on chapter tree
            new SelectCourse().selectInvisibleAssignment(assignmentname);
            String font = lessonPage.monoSpaceText.getCssValue("font-family");
            Assert.assertEquals(font, "monospace", "The font of question text is not in monospace");
            lessonPage.hint_button.click();//click on hint
            Assert.assertEquals(lessonPage.hintText.getCssValue("font-family"), "monospace", "The font of question text is not in monospace");
            lessonPage.submitAnswer.click();//click on submit
            Thread.sleep(2000);
            lessonPage.hint_button.click();//click on hint
            Assert.assertEquals(lessonPage.hintText.getCssValue("font-family"),"monospace","The font of hint text is not in monospace");
            lessonPage.solution_button.click();//click on solution
            Assert.assertEquals(lessonPage.solutionText.getCssValue("font-family"),"monospace","The font of solution text is not in monospace");
            lessonPage.getButton_finish_lsCourse.click();//click on finish
            lessonPage.questionCard.get(0).click();//click on question card
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@id='question-content-label']/inline")));
            Assert.assertEquals(courseStreamPage.questionText.getCssValue("font-family"), "monospace", "question text is not in monospace font");
            new TOCShow().chaptertree();//click on chapter tree
            new SelectCourse().selectInvisibleAssignment(assignmentname);
            Thread.sleep(5000);
            for(int i=1;i<4;i++){
                try {
                    new Click().clickbylistcssselector("g.highcharts-series.highcharts-tracker > rect", 0);
                    break;
                } catch (Exception e) {
                }
            }
            Assert.assertEquals(courseStreamPage.questionText.getCssValue("font-family"), "monospace", "question text is not in monospace font");
            new TOCShow().chaptertree();//click on chapter tree
            new SelectCourse().selectInvisibleAssignment(assignmentname);
            Thread.sleep(3000);
            lessonPage.retake_Button.click();//click on retake button
            String font1 = lessonPage.monoSpaceText.getCssValue("font-family");
            Assert.assertEquals(font1,"monospace","The font of question text is not in monospace");
            lessonPage.submitAnswer.click();//click on submit
            // new Click().clickbyxpath("//div[@class='ls-static-practice-test-next-button']/input");

        } catch (Exception e) {
            Assert.fail("Exception in TC StudentAbleToSeeTheMonoSpaceFontForTrueFalse of class StudentAbleToSeeTheMonoSpaceFont", e);
        }
    }


    @Test(priority = 2, enabled = true,dataProvider = "questionType")
    public void instructorUserAbleToViewMonospaceFonAssessmentPreview(String assignmentname) {

        try {
            //TC row no 523
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            new LoginUsingLTI().ltiLogin("523"); //login as instructor
            new TOCShow().chaptertree();//click on chapter tree
            new SelectCourse().selectInvisibleAssignment(assignmentname);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@class='control-label']/inline")));
            String font = lessonPage.monospace_Text.getCssValue("font-family");
            Assert.assertEquals(font,"monospace","The font of question text is not in monospace");

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorUserAbleToViewMonospaceFonAssessmentPreview of class StudentAbleToSeeTheMonoSpaceFont", e);
        }
    }


    @Test(priority = 3, enabled = true,dataProvider = "questionType")
    public void instructorUserAbleToViewMonospaceFonAssessmentTryIt(String assignmentname) {

        try {
            //TC row no 540
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            new LoginUsingLTI().ltiLogin("540"); //login as instructor
            new TOCShow().chaptertree();//click on chapter tree
            new TopicOpen().clickOnStaticAssessmentArrow(assignmentname);
            lessonPage.tryIt_link.click();//click on try it
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            String font = lessonPage.monospace_Text.getCssValue("font-family");
            Assert.assertEquals(font, "monospace", "The font of question text is not in monospace");
            currentAssignments.hint_Button.click();//click on hint button
            String hintFont = lessonPage.hint_Text.getCssValue("font-family");
            Assert.assertEquals(hintFont,"monospace","The font of hint text is not in monospace");
            String solutionFont = lessonPage.solution_Text.getCssValue("font-family");
            Assert.assertEquals(solutionFont,"monospace","The font of solution text is not in monospace");
            currentAssignments.finishButton.click();//click on finish button

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorUserAbleToViewMonospaceFonAssessmentTryIt of class StudentAbleToSeeTheMonoSpaceFont", e);
        }
    }


    @Test(priority = 4, enabled = true,dataProvider = "questionType")
    public void instructorAbleToNavigateToEngagementReport(String assignmentname) {

        try {
            //TC row no 651
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            EngagementReport engagementReport = PageFactory.initElements(driver, EngagementReport.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);

            new LoginUsingLTI().ltiLogin("651_1"); //login as student
            new TOCShow().chaptertree();//click on chapter tree
            new SelectCourse().selectInvisibleAssignment(assignmentname);
            lessonPage.submitAnswer.click();//click on submit
            Thread.sleep(3000);
            try {
                driver.findElement(By.xpath("//div[@id='footer-info-content']/div[4]/input[@title='Finish']")).click();

            } catch (Exception e) {

                driver.findElement(By.xpath("//div[@id='footer-info-content']/div[4]/input[@title='Finish']")).click();

            }
            Thread.sleep(5000);
            new LoginUsingLTI().ltiLogin("651"); //login as instructor
            new Navigator().NavigateTo("Engagement Report");//navigate to engagement report
            engagementReport.assessmentBox.get(1).click();//click on assessment
            new SelectCourse().selectInvisibleAssignment(assignmentname);
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Assert.assertEquals(assignmentResponsesPage.T_Icon.isDisplayed(), true, "T-icon is not displaying");
            assignmentResponsesPage.T_Icon.click();//click on t-icon
            assignmentResponsesPage.fontFamily.click();//click on font family icon
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is feedback");
            Actions actions = new Actions(driver);
            WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='view-user-question-performance-feedback-box']/p")));
            actions.moveToElement(element, 0, 30)
                    .clickAndHold()
                    .moveByOffset(510, 10)
                    .release()
                    .perform();
            assignmentResponsesPage.fontFamily.click();//click on font family icon
            assignmentResponsesPage.monoSpaceFont.get(0).click();//click on monospace
            Assert.assertEquals(assignmentResponsesPage.text_FeedbackAfterSelect.getCssValue("font-family"), "monospace", "question text is not in open sans font");

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorAbleToNavigateToEngagementReport of class StudentAbleToSeeTheMonoSpaceFont", e);
        }
    }

}
