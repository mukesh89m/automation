package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT24.R2412;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.QuestionPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by durgapathi on 21/12/15.
 */
public class AttemptTheAssignmentUsingTheNewQuestionNavigator extends Driver {

    public void navigateToQuestionPage()
    {
        try
        {
            Dashboard dashboard = PageFactory.initElements(driver,Dashboard.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            QuestionPage questionPage = PageFactory.initElements(driver,QuestionPage.class);

            Assert.assertTrue(dashboard.dashBoard.isDisplayed(), "The user is not successfully logged in to the application and the default landing page should be displayed");
            //Assignments page should be opened
            new Navigator().NavigateTo("Assignments");
            Assert.assertTrue(questionPage.questionPage.isDisplayed(),"Assignments page should be opened");
            //Assignment should be opened in a new tab and the first question should be opened by default
            Assert.assertTrue(assignments.assignmentName.isDisplayed(),"Assignments are not available");
            assignments.assignmentName.click();
            Assert.assertEquals(questionPage.currentQuestion.get(0).getAttribute("qindex"),"1","Assignment should be opened in a new tab and the first question should be opened by default");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC navigateToQuestionPage of class AttemptTheAssignmentUsingTheNewQuestionNavigator", e);
        }
    }
    @Test(priority = 1,enabled = true)
    public void navigateToAssignment()
    {
        try
        {
            new Assignment().create(10);
            new Assignment().addQuestions(10, "truefalse", "");
            new Assignment().addQuestions(10, "truefalse", "");
            new Assignment().addQuestions(10, "truefalse", "");
            new Assignment().addQuestions(10, "truefalse", "");
            new Assignment().addQuestions(10, "truefalse", "");
            new Assignment().addQuestions(10, "truefalse", "");
            new Assignment().addQuestions(10, "truefalse", "");
            new Assignment().addQuestions(10, "truefalse", "");
            new Assignment().addQuestions(10, "truefalse", "");
            new Assignment().addQuestions(10, "truefalse", "");
            new Assignment().addQuestions(10, "truefalse", "");
            new Assignment().addQuestions(10, "truefalse", "");
            new Assignment().addQuestions(10, "truefalse", "");
            new Assignment().addQuestions(10, "truefalse", "");
            new Assignment().addQuestions(10, "truefalse", "");
            new Assignment().addQuestions(10,"essay","");
            new Assignment().addQuestions(10,"truefalse","");
            new Assignment().addQuestions(10, "truefalse", "");
            new LoginUsingLTI().ltiLogin("11");
            new Assignment().assignToStudent(11);
            new LoginUsingLTI().ltiLogin("10");
            navigateToQuestionPage();

        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC navigateToAssignment of class AttemptTheAssignmentUsingTheNewQuestionNavigator", e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void verifyTheViewOfTheQuestionNavigator()
    {
        try
        {
            QuestionPage questionPage = PageFactory.initElements(driver,QuestionPage.class);
            new LoginUsingLTI().ltiLogin("10");
            navigateToQuestionPage();
            // The questions should be displayed in bubble format
            Assert.assertTrue(questionPage.currentQuestion.get(0).isDisplayed(), "The questions should be displayed in bubble format");
            // Assignment name should be displayed above the question bubbles
            Assert.assertTrue(questionPage.assignmentName.isDisplayed(), "Assignment name should be displayed above the question bubbles");
            // Verify the Question Numbering
            Assert.assertEquals(questionPage.questionDetails.getText().trim(), "(1 of 19)", "It should be displayed as (x of y) ");
            //Verify the Question bubbles
            Assert.assertEquals(questionPage.currentQuestion.get(0).getAttribute("qindex"), "1", "First bubble should be highlighted in blue and rest should be with blue outline border");
            Assert.assertEquals(questionPage.notAttemptedQuestions.get(0).getAttribute("qindex"), "2", "First bubble should be highlighted in blue and rest should be with blue outline border");
            Assert.assertEquals(questionPage.currentQuestion.size(), 1, "First bubble should be highlighted in blue and rest should be with blue outline border");
            Assert.assertEquals(questionPage.notAttemptedQuestions.size(), 18, "First bubble should be highlighted in blue and rest should be with blue outline border");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC verifyTheViewOfTheQuestionNavigator of class AttemptTheAssignmentUsingTheNewQuestionNavigator", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void verifyTheViewAndAlignmentOfTheQuestionBubble()
    {
        try
        {
            QuestionPage questionPage = PageFactory.initElements(driver,QuestionPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            new LoginUsingLTI().ltiLogin("10");
            navigateToQuestionPage();
            //10 bubbles should be displayed by default on the page
            Assert.assertEquals(questionPage.questionsInPage1.size(), 10, "10 bubbles should be displayed by default on the page");
            //Arrow should be displayed after 10th bubble
            Assert.assertTrue(questionPage.nextPageNavigator.isDisplayed(), "Arrow should be displayed after 10th bubble");
            // Click on the arrow
            questionPage.nextPageNavigator.click();
            //The next set of 10 questions should be displayed and the 11th question should be selected by default
            Assert.assertEquals(questionPage.questionsInPage2.size(),9,"The next set of 10 questions should be displayed and the 11th question should be selected by default");
            Assert.assertEquals(questionPage.questionsInPage2.get(0).getAttribute("qindex"), "11", "The next set of 10 questions should be displayed and the 11th question should be selected by default");
            //Verify for the left arrow
            Assert.assertTrue(questionPage.prePageNavigator.isDisplayed(), "Left arrow should be displayed");
            //Click on the left arrow
            questionPage.prePageNavigator.click();
            //First 10 question bubble should be displayed and first question should be selected by default
            Assert.assertEquals(questionPage.questionsInPage1.size(),10,"10 bubbles should be displayed by default on the page");
            new Assignment().create(26);
            new Assignment().addQuestions(26, "truefalse", "");
            new LoginUsingLTI().ltiLogin("26");
            new Assignment().assignToStudent(26);
            new LoginUsingLTI().ltiLogin("27");
            new Navigator().NavigateTo("Assignments");
            assignments.assignmentName.click();
            // Verify the Question bubbles count
            Assert.assertEquals(questionPage.allQuestions.size(), 2, "Question bubbles should be displayed as per the Question Count of the assignment and should be center aligned");
            Assert.assertEquals(questionPage.questionNavigator.getCssValue("text-align"), "center", "Question bubbles should be displayed as per the Question Count of the assignment and should be center aligned");
            //Arrow should not be displayed
            Assert.assertFalse(questionPage.nextPageNavigator.isDisplayed(),"Arrow should not be displayed ");
            Assert.assertFalse(questionPage.prePageNavigator.isDisplayed(), "Arrow should not be displayed ");
            //Verify the no.s displayed in the bubble
            Assert.assertEquals(questionPage.currentQuestion.get(0).getAttribute("qindex"), "1", "The no.s displayed should be as per the Question of the assignment");
            Thread.sleep(1000);
            questionPage.allQuestions.get(1).click();
            Thread.sleep(2000);
            Assert.assertEquals(questionPage.currentQuestion.get(0).getAttribute("qindex"), "2", "The no.s displayed should be as per the Question of the assignment");
            //Click on the Expand Icon
            questionPage.expandButton.click();
            // The grid should be expanded and the Question bubbles should be center aligned
            Assert.assertEquals(questionPage.expandButton.getAttribute("title").trim(),"Collapse View","The grid should be expanded and the Question bubbles should be center aligned");
            Assert.assertEquals(questionPage.questionNavigator.getCssValue("text-align"),"center","The grid should be expanded and the Question bubbles should be center aligned ");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC verifyTheViewAndAlignmentOfTheQuestionBubble of class AttemptTheAssignmentUsingTheNewQuestionNavigator", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void verifyTheColorCodesOfTheQuestionBubbles()
    {
        try
        {
            QuestionPage questionPage = PageFactory.initElements(driver,QuestionPage.class);

            new LoginUsingLTI().ltiLogin("10");
            navigateToQuestionPage();
            // Assignment should be opened in a new tab and the first question should be opened by default
            Assert.assertEquals(questionPage.currentQuestion.get(0).getAttribute("qindex"), "1", "Assignment should be opened in a new tab and the first question should be opened by default");
            // Verify the Color of the Question Bubble
            Assert.assertEquals(questionPage.currentQuestion.get(0).getCssValue("color").trim(),"rgba(255, 255, 255, 1)", "Default 1st Qs bubble should not be in blue color");
            Assert.assertEquals(questionPage.currentQuestion.get(0).getCssValue("background-color").trim(), "rgba(76, 164, 232, 1)", "Default 1st Qs bubble background color should be in blue");
            for(int i = 0; i < questionPage.notAttemptedQuestions.size(); i++)
            {
                Assert.assertEquals(questionPage.notAttemptedQuestions.get(i).getCssValue("color").trim(),"rgba(172, 200, 255, 1)", "All the Question Bubble should be in blue color except the 1st bubble");
                Assert.assertEquals(questionPage.notAttemptedQuestions.get(i).getCssValue("background-color").trim(),"rgba(255, 255, 255, 1)", "All the Question Bubble should be in blue color except the 1st bubble");
            }
            // Attempt the question and move to next Question
            questionPage.trueFalseChoices.get(0).click();
            questionPage.submitAnswer.click();
            //questionPage.nextQuestion.click();
            Thread.sleep(2000);
            // The  question Bubble border should be displayed in Purple Color and the Current Question bubble should be displayed in filled Blue color
            Assert.assertEquals(questionPage.currentQuestion.get(0).getAttribute("qindex"), "2", "Assignment should be opened in a new tab and the first question should be opened by default");
            Assert.assertEquals(questionPage.attemptedQuestions.get(0).getCssValue("color").trim(), "rgba(128, 0, 128, 0.6)", "The  question Bubble border should be displayed in Purple Color");
            Assert.assertEquals(questionPage.currentQuestion.get(0).getCssValue("background-color").trim(), "rgba(76, 164, 232, 1)", "The Current Question bubble should be displayed in filled Blue color");
            // Skip the Question and move to next Question
            questionPage.submitAnswer.click();
            Thread.sleep(2000);
            // All the Question Bubble should be in blue color and the Current Question bubble should be displayed in filled Blue color
            Assert.assertEquals(questionPage.currentQuestion.get(0).getAttribute("qindex"), "3", "2nd question is not skipped and not moved to next question");
            Assert.assertEquals(questionPage.currentQuestion.get(0).getCssValue("background-color").trim(),"rgba(76, 164, 232, 1)", "The Current Question bubble should be displayed in filled Blue color");
            for(int i = 0; i < questionPage.notAttemptedQuestions.size(); i++)
            {
                Assert.assertEquals(questionPage.notAttemptedQuestions.get(i).getCssValue("color").trim(),"rgba(172, 200, 255, 1)", "All the Question Bubble should be in blue color except the 1st bubble");
                Assert.assertEquals(questionPage.notAttemptedQuestions.get(i).getCssValue("background-color").trim(),"rgba(255, 255, 255, 1)", "All the Question Bubble should be in blue color except the 1st bubble");
            }
            // Attempt the manually Graded question and move to next Question
            int essayQuestion = driver.findElements(By.className("essay-question-redactor-content-wrapper")).size();
            while(essayQuestion == 0 )
            {
                Thread.sleep(500);
                questionPage.trueFalseChoices.get(0).click();
                questionPage.submitAnswer.click();
                Thread.sleep(500);
                essayQuestion = driver.findElements(By.className("essay-question-redactor-content-wrapper")).size();
            }
            // Attempt the manually Graded question and move to next Question
            driver.findElement(By.id("html-editor-non-draggable")).click();
            driver.findElement(By.id("html-editor-non-draggable")).sendKeys("Test Essay Question : Solution Text");
            questionPage.submitAnswer.click();
            Thread.sleep(1000);
            // The  question Bubble border should be displayed in light purple Color and the Current Question bubble should be displayed in filled Blue color
            Assert.assertEquals(questionPage.attemptedQuestions.get(15).getCssValue("color").trim(), "rgba(128, 0, 128, 0.6)", "The  question Bubble border should be displayed in Purple Color");
            Assert.assertEquals(questionPage.currentQuestion.get(0).getCssValue("background-color").trim(),"rgba(76, 164, 232, 1)", "The Current Question bubble should be displayed in filled Blue color");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC verifyTheColorCodesOfTheQuestionBubbles of class AttemptTheAssignmentUsingTheNewQuestionNavigator", e);
        }
    }


}
