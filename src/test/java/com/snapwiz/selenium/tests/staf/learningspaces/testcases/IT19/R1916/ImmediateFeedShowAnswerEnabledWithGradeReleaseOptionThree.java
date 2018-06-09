package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R1916;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by sumit on 14/1/15.
 */
public class ImmediateFeedShowAnswerEnabledWithGradeReleaseOptionThree extends Driver {
    @Test(priority = 1, enabled = true)
    public void immediateFeedShowAnswerEnabledWithGradeReleaseOptionThree()
    {
        try
        {
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);//click on go to next question link on pop-up
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", "136");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "136");

            new Assignment().create(136);
            new Assignment().addQuestions(136, "truefalse", assessmentname);
            new Assignment().addQuestions(136, "textentry", assessmentname);
            new Assignment().addQuestions(136, "multipleselection", assessmentname);
            new Assignment().addQuestions(136, "essay", assessmentname);
            new Assignment().addQuestions(136, "multiplechoice", assessmentname);
            new Assignment().addQuestions(136, "truefalse", assessmentname);


            new LoginUsingLTI().ltiLogin("136_1");//create a student
            new LoginUsingLTI().ltiLogin("136");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicy, "136 Policy description text", "1",null, true, "3", "1", "Release as they are being graded", "", "", "");
            new Assignment().assignToStudent(136);

            new LoginUsingLTI().ltiLogin("136_1");//create a student
            new Assignment().openAssignmentFromCourseStream("136");
            new AttemptQuestion().trueFalse(false, "correct", 136);//attempt option A
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//input[@title='Next Question']"));
            //TC row no. 137
            String correctNotice = assignments.getNotification().getText();
            if(!correctNotice.contains("You got it correct")){
                Assert.fail("\"You got it correct\" message is not displayed.");
            }
            //TC row no. 138
            String str = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }

            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            //TC row no. 139
            new AttemptQuestion().trueFalse(false, "incorrect", 136);//attempt option B
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//input[@title='Next Question']"));
            Thread.sleep(5000);
            String incorrectNotice = assignments.getNotification().getText();
            if(!incorrectNotice.contains("You got it incorrect")){
                Assert.fail("\"You got it incorrect\" message is not displayed.");
            }
            //TC row no. 140
            String reattempt = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            String str1 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str1.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }
            //TC row no. 142
            boolean found = new BooleanValue().presenceOfElement(136, By.cssSelector("div[class='true-false-student-tick true-false-student-right']"));
            Assert.assertEquals(found, true, "Show Correct Answer option is not displayed for a true false type question.");
            //TC row no. 143
            assignments.getReAttemptLinkOnPopUp().click();//click on reattempt
            //no need to select option B again it remains selected from previous attempt
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//input[@title='Next Question']"));
            String incorrectNotice1 = assignments.getNotification().getText();
            if(!incorrectNotice1.contains("You got it incorrect")){
                Assert.fail("\"You got it incorrect\" message is not displayed.");
            }
            //TC row no. 144
            String str2 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str2.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }
            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().textEntry(false, "incorrect", 136);//attempt incorrect
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//input[@title='Next Question']"));
            //TC row no. 145
            new Click().clickbylinkText("Show Correct Answer");
            String corrAnswer = new TextFetch().textfetchbyclass("correct-answer-wrapper");
            if(!corrAnswer.contains("Correct Answer")){
                Assert.fail("Correct answer is not displayed for text entry type question in on clicking \"Show Correct Answer\" link.");
            }

            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().multipleSelection(false, "partiallycorrect", 136);//attempt incorrect
            new Assignment().nextButtonInQuestionClick();//click on Next button
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(By.xpath("//input[@title='Next Question']"));
            //TC row no. 146
            String incorrectNotice2 = assignments.getNotification().getText();
            if(!incorrectNotice2.contains("You got it partially correct")){
                Assert.fail("\"You got it partially correct\" message is not displayed.");
            }
            //TC row no. 147
            String reattempt1 = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt1.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            String str3 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str3.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }
            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().essay(false, "incorrect", 136);//attempt incorrect
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//input[@title='Next Question']"));
            //TC row no. 149
            String incorrectNotice3 = assignments.getNotification().getText();
            if(!incorrectNotice3.contains("Your instructor will provide feedback")){
                Assert.fail("\"Your instructor will provide feedback\" message is not displayed.");
            }

            //TC row no. 150
            String reattempt2 = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt2.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            String str4 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str4.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }

            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().multipleChoice(false, "skip", 136);//skip the question
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//input[@title='Next Question']"));
            Thread.sleep(2000);
            //TC row no. 151
            String incorrectNotice4 = assignments.getNotification().getText();
            if(!incorrectNotice4.contains("You have skipped the question")){
                Assert.fail("\"'You have skipped the question\" message is not displayed.");
            }

            //TC row no. 152
            String reattempt3 = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt3.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            String str5 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str5.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }


            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().trueFalse(false, "incorrect", 136);//attempt incorrect
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//input[@title='Finish Assignment']"));
            //TC row no. 154
            String finishLink = assignments.getGoToNextLinkOnPopUp().getText();
            if(!finishLink.contains("Finish assignment")){
                Assert.fail("Finish assignment link is not found on pop-up");
            }
            //TC row no. 155
            String reattempt4 = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt4.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            //TC row no. 156
            assignments.getReAttemptLinkOnPopUp().click();//click on reattempt
            //no need to select option B again as it remains selected from previous attempt
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//input[@title='Finish Assignment']"));
            String incorrectNotice5 = assignments.getNotification().getText();
            if(!incorrectNotice5.contains("You got it incorrect")){
                Assert.fail("\"You got it incorrect\" message is not displayed.");
            }
            //TC row no. 157
            String finishLink1 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!finishLink1.contains("Finish assignment")){
                Assert.fail("Finish assignment link is not found on pop-up");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in class ImmediateFeedShowAnswerEnabledWithGradeReleaseOptionThree in method immediateFeedShowAnswerEnabledWithGradeReleaseOptionThree.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void immediateFeedShowAnswerEnabledWithGradeReleaseOptionThreeUnlimitedShowAnswer()
    {
        try {
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);//click on go to next question link on pop-up

            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", "158");

            new Assignment().create(158);
            new LoginUsingLTI().ltiLogin("158_1");//create a student

            new LoginUsingLTI().ltiLogin("158");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicy, "158 Policy description text", "1", null, true, "Unlimited", "1", "Release as they are being graded", "", "", "");
            new Assignment().assignToStudent(158);

            new LoginUsingLTI().ltiLogin("158_1");//create a student
            new Assignment().openAssignmentFromCourseStream("158");
            new AttemptQuestion().trueFalse(false, "incorrect", 158);//attempt option B
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//input[@title='Finish Assignment']"));
            //TC row no. 158
            String finishLink = assignments.getGoToNextLinkOnPopUp().getText();
            if (!finishLink.contains("Finish assignment")) {
                Assert.fail("Finish assignment link is not found on pop-up");
            }
            //TC row no. 159
            String reattempt = assignments.getReAttemptLinkOnPopUp().getText();
            if (!reattempt.contains("Reattempt")) {
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            //TC row no. 160
            assignments.getReAttemptLinkOnPopUp().click();//click on reattempt
            //no need to select option B again as it remains selected from previous attempt
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            Thread.sleep(2000);
            new UIElement().waitAndFindElement(By.xpath("//input[@title='Finish Assignment']"));
            String correctNotice1 = assignments.getNotification().getText();
            if (!correctNotice1.contains("You got it incorrect")) {
                Assert.fail("\"You got it incorrect\" message is displayed.");
            }
            //TC row no. 161
            String finishLink1 = assignments.getGoToNextLinkOnPopUp().getText();
            if (!finishLink1.contains("Finish assignment")) {
                Assert.fail("Finish assignment link is not found on pop-up");
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class ImmediateFeedShowAnswerEnabledWithGradeReleaseOptionThree in method immediateFeedShowAnswerEnabledWithGradeReleaseOptionThreeUnlimitedShowAnswer.", e);
        }
    }

}
