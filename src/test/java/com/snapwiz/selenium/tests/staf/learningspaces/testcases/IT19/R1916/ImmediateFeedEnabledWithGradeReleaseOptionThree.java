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
 * Created by sumit on 13/1/15.
 */
public class ImmediateFeedEnabledWithGradeReleaseOptionThree extends Driver {

    @Test(priority = 1, enabled = true)
    public void immediateFeedEnabledWithGradeReleaseOptionThree()
    {
        try
        {
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);//click on go to next question link on pop-up

            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "52");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "52");

            new Assignment().create(52);
            new Assignment().addQuestions(52, "truefalse", assessmentname);
            new Assignment().addQuestions(52, "truefalse", assessmentname);

            new LoginUsingLTI().ltiLogin("52_1");//create a student
            new LoginUsingLTI().ltiLogin("52");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "52 Policy description text", "1",null, true, "3", "", "Release as they are being graded", "", "", "");
            new Assignment().assignToStudent(52);

            new LoginUsingLTI().ltiLogin("52_1");//create a student
            new Assignment().openAssignmentFromCourseStream("52");
            //TC row no. 53
            new AttemptQuestion().trueFalse(false, "correct", 52);//attempt option A
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='nextQuestion']"));

            String str = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }
            //TC row no. 54
            String correctNotice = assignments.getNotification().getText();
            if(correctNotice.contains("You got it correct")){
                Assert.fail("\"You got it correct\" message is displayed.");
            }
            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            //TC row no. 55
            new AttemptQuestion().trueFalse(false, "incorrect", 52);//attempt option B
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='nextQuestion']"));
            String incorrectNotice = assignments.getNotification().getText();
            if(incorrectNotice.contains("You got it incorrect")){
                Assert.fail("\"You got it incorrect\" message is displayed.");
            }
            //TC row no. 56
            String reattempt = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            //TC row no. 58
            assignments.getReAttemptLinkOnPopUp().click();//click on reattempt
            new AttemptQuestion().trueFalse(false, "correct", 52);//attempt option A
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='nextQuestion']"));
            String correctNotice1 = assignments.getNotification().getText();
            if(correctNotice1.contains("You got it correct")){
                Assert.fail("\"You got it correct\" message is displayed.");
            }
            //TC row no. 59
            String str1 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str1.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }
            //TC row no. 60
            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().trueFalse(false, "incorrect", 52);//attempt option B
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='submit']"));
            String reattempt1 = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt1.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            //TC row no. 61
            String finishLink = assignments.getGoToNextLinkOnPopUp().getText();
            if(!finishLink.contains("Finish assignment")){
                Assert.fail("Finish assignment link is not found on pop-up");
            }

            assignments.getReAttemptLinkOnPopUp().click();//click on reattempt
            new AttemptQuestion().trueFalse(false, "incorrect", 52);//attempt option B
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='submit']"));
            //TC row no. 62
            String incorrectNotice1 = assignments.getNotification().getText();
            if(incorrectNotice1.contains("You got it correct")){
                Assert.fail("\"You got it incorrect\" message is displayed.");
            }
            //TC row no. 63

            String finishLink1 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!finishLink1.contains("Finish assignment")){
                Assert.fail("Finish assignment link is not found on pop-up");
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class ImmediateFeedEnabledWithGradeReleaseOptionThree in method immediateFeedEnabledWithGradeReleaseOptionThree.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void immediateFeedEnabledWithGradeReleaseOptionThreeUnlimitedShowAnswer()
    {
        try {
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);//click on go to next question link on pop-up

            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "64");

            new Assignment().create(64);
            new LoginUsingLTI().ltiLogin("64_1");//create a student

            new LoginUsingLTI().ltiLogin("64");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "64 Policy description text", "1", null, true, "Unlimited", "", "Release as they are being graded", "", "", "");
            new Assignment().assignToStudent(64);

            new LoginUsingLTI().ltiLogin("64_1");//create a student
            new Assignment().openAssignmentFromCourseStream("64");
            new AttemptQuestion().trueFalse(false, "incorrect", 64);//attempt option B
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='submit']"));
            //TC row no. 64
            String finishLink = assignments.getGoToNextLinkOnPopUp().getText();
            if (!finishLink.contains("Finish assignment")) {
                Assert.fail("Finish assignment link is not found on pop-up");
            }
            //TC row no. 65
            String reattempt = assignments.getReAttemptLinkOnPopUp().getText();
            if (!reattempt.contains("Reattempt")) {
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            //TC row no. 66
            assignments.getReAttemptLinkOnPopUp().click();//click on reattempt
            new AttemptQuestion().trueFalse(false, "incorrect", 64);//attempt option B
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='submit']"));
            String correctNotice1 = assignments.getNotification().getText();
            if (correctNotice1.contains("You got it incorrect")) {
                Assert.fail("\"You got it incorrect\" message is displayed.");
            }
            //TC row no. 67
            String finishLink1 = assignments.getGoToNextLinkOnPopUp().getText();
            if (!finishLink1.contains("Finish assignment")) {
                Assert.fail("Finish assignment link is not found on pop-up");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in class ImmediateFeedEnabledWithGradeReleaseOptionThree in method immediateFeedEnabledWithGradeReleaseOptionThreeUnlimitedShowAnswer.", e);
        }
    }

}
