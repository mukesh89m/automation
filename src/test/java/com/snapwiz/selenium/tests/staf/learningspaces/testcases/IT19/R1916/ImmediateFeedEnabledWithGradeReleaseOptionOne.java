package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R1916;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by sumit on 12/1/15.
 */
public class ImmediateFeedEnabledWithGradeReleaseOptionOne extends Driver
{

    @Test(priority = 1, enabled = true)
    public void immediateFeedEnabledWithGradeReleaseOptionOne()
    {
        try
        {
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);//click on go to next question link on pop-up
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "20");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "20");

            new Assignment().create(20);
            new Assignment().addQuestions(20, "truefalse", assessmentname);
            new Assignment().addQuestions(20, "truefalse", assessmentname);

            new LoginUsingLTI().ltiLogin("20_1");//create a student
            new LoginUsingLTI().ltiLogin("20");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "20 Policy description text", "1",null, true, "3", "", "Auto-release on assignment submission", "", "", "");
            new Assignment().assignToStudent(20);

            new LoginUsingLTI().ltiLogin("20_1");//create a student
            new Assignment().openAssignmentFromCourseStream("20");
            //TC row no. 21
            new AttemptQuestion().trueFalse(false, "correct", 20);//attempt option A
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='nextQuestion']"));

            String str = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }
            //TC row no. 22
            String correctNotice = assignments.getNotification().getText();
            if(correctNotice.contains("You got it correct")){
                Assert.fail("\"You got it correct\" message is displayed.");
            }
            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            //TC row no. 23
            new AttemptQuestion().trueFalse(false, "incorrect", 20);//attempt option B
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='nextQuestion']"));

            String incorrectNotice = assignments.getNotification().getText();
            if(incorrectNotice.contains("You got it incorrect")){
                Assert.fail("\"You got it incorrect\" message is displayed.");
            }
            //TC row no. 24
            String reattempt = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            //TC row no. 26
            assignments.getReAttemptLinkOnPopUp().click();//click on reattempt
            new AttemptQuestion().trueFalse(false, "correct", 20);//attempt option A
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='nextQuestion']"));

            String correctNotice1 = assignments.getNotification().getText();
            if(correctNotice1.contains("You got it correct")){
                Assert.fail("\"You got it correct\" message is displayed.");
            }
            //TC row no. 27
            String str1 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str1.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }
            //TC row no. 28
            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().trueFalse(false, "incorrect", 20);//attempt option B
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='submit']"));

            String reattempt1 = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt1.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            //TC row no. 29
            String finishLink = assignments.getGoToNextLinkOnPopUp().getText();
            if(!finishLink.contains("Finish assignment")){
                Assert.fail("Finish assignment link is not found on pop-up");
            }

            assignments.getReAttemptLinkOnPopUp().click();//click on reattempt
            new AttemptQuestion().trueFalse(false, "incorrect", 20);//attempt option B
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='submit']"));

            //TC row no. 30
            String incorrectNotice1 = assignments.getNotification().getText();
            if(incorrectNotice1.contains("You got it incorrect")){
                Assert.fail("\"You got it incorrect\" message is displayed.");
            }
            //TC row no. 31

            String finishLink1 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!finishLink1.contains("Finish assignment")){
                Assert.fail("Finish assignment link is not found on pop-up");
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class ImmediateFeedEnabledWithGradeReleaseOptionOne in method immediateFeedEnabledWithGradeReleaseOptionOne.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void immediateFeedEnabledWithGradeReleaseOptionOneUnlimitedShowAnswer()
    {
        try {

            Assignments assignments = PageFactory.initElements(driver, Assignments.class);//click on go to next question link on pop-up
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "32");

           new Assignment().create(32);
            new LoginUsingLTI().ltiLogin("32_1");//create a student

            new LoginUsingLTI().ltiLogin("32");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "32 Policy description text", "1", null, true, "Unlimited", "", "Auto-release on assignment submission", "", "", "");
            new Assignment().assignToStudent(32);

            new LoginUsingLTI().ltiLogin("32_1");//create a student
            new Assignment().openAssignmentFromCourseStream("32");
            new AttemptQuestion().trueFalse(false, "incorrect", 32);//attempt option B
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='submit']"));

            //TC row no. 32
            String finishLink = assignments.getGoToNextLinkOnPopUp().getText();
            if (!finishLink.contains("Finish assignment")) {
                Assert.fail("Finish assignment link is not found on pop-up");
            }
            //TC row no. 33
            String reattempt = assignments.getReAttemptLinkOnPopUp().getText();
            if (!reattempt.contains("Reattempt")) {
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            //TC row no. 34
            assignments.getReAttemptLinkOnPopUp().click();//click on reattempt
            new AttemptQuestion().trueFalse(false, "incorrect", 32);//attempt option B
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='submit']"));

            String correctNotice1 = assignments.getNotification().getText();
            if (correctNotice1.contains("You got it incorrect")) {
                Assert.fail("\"You got it incorrect\" message is displayed.");
            }
            //TC row no. 35
            String finishLink1 = assignments.getGoToNextLinkOnPopUp().getText();
            if (!finishLink1.contains("Finish assignment")) {
                Assert.fail("Finish assignment link is not found on pop-up");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in class ImmediateFeedEnabledWithGradeReleaseOptionOne in method immediateFeedEnabledWithGradeReleaseOptionOneUnlimitedShowAnswer.", e);
        }
    }

}
