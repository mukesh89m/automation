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
public class ImmediateFeedEnabledWithGradeReleaseOptionTwo extends Driver {
    
    @Test(priority = 1, enabled = true)
    public void immediateFeedEnabledWithGradeReleaseOptionTwo()
    {
        try
        {
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);//click on go to next question link on pop-up

            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "36");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "36");

            new Assignment().create(36);
            new Assignment().addQuestions(36, "truefalse", assessmentname);
            new Assignment().addQuestions(36, "truefalse", assessmentname);

            new LoginUsingLTI().ltiLogin("36_1");//create a student
            new LoginUsingLTI().ltiLogin("36");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "36 Policy description text", "1",null, true, "3", "", "Auto-release on due date", "", "", "");
            new Assignment().assignToStudent(36);

            new LoginUsingLTI().ltiLogin("36_1");//create a student
            new Assignment().openAssignmentFromCourseStream("36");
            //TC row no. 37
            new AttemptQuestion().trueFalse(false, "correct", 36);//attempt option A
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='nextQuestion']"));            String str = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }
            //TC row no. 38
            String correctNotice = assignments.getNotification().getText();
            if(correctNotice.contains("You got it correct")){
                Assert.fail("\"You got it correct\" message is displayed.");
            }
            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            //TC row no. 39
            new AttemptQuestion().trueFalse(false, "incorrect", 36);//attempt option B
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='nextQuestion']"));            String incorrectNotice = assignments.getNotification().getText();
            if(incorrectNotice.contains("You got it incorrect")){
                Assert.fail("\"You got it incorrect\" message is displayed.");
            }
            //TC row no. 40
            String reattempt = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            //TC row no. 42
            assignments.getReAttemptLinkOnPopUp().click();//click on reattempt
            new AttemptQuestion().trueFalse(false, "correct", 36);//attempt option A
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='nextQuestion']"));            String correctNotice1 = assignments.getNotification().getText();
            if(correctNotice1.contains("You got it correct")){
                Assert.fail("\"You got it correct\" message is displayed.");
            }
            //TC row no. 43
            String str1 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str1.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }
            //TC row no. 44
            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().trueFalse(false, "incorrect", 36);//attempt option B
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='submit']"));
            String reattempt1 = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt1.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            //TC row no. 45
            String finishLink = assignments.getGoToNextLinkOnPopUp().getText();
            if(!finishLink.contains("Finish assignment")){
                Assert.fail("Finish assignment link is not found on pop-up");
            }

            assignments.getReAttemptLinkOnPopUp().click();//click on reattempt
            new AttemptQuestion().trueFalse(false, "incorrect", 36);//attempt option B
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='submit']"));            //TC row no. 46
            String incorrectNotice1 = assignments.getNotification().getText();
            if(incorrectNotice1.contains("You got it incorrect")){
                Assert.fail("\"You got it incorrect\" message is displayed.");
            }
            //TC row no. 47

            String finishLink1 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!finishLink1.contains("Finish assignment")){
                Assert.fail("Finish assignment link is not found on pop-up");
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class immediateFeedEnabledWithGradeReleaseOptionTwo in method immediateFeedEnabledWithGradeReleaseOptionTwo.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void immediateFeedEnabledWithGradeReleaseOptionTwoUnlimitedShowAnswer()
    {
        try {
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);//click on go to next question link on pop-up

            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "48");

            new Assignment().create(48);
            new LoginUsingLTI().ltiLogin("48_1");//create a student

            new LoginUsingLTI().ltiLogin("48");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "48 Policy description text", "1", null, true, "Unlimited", "", "Auto-release on due date", "", "", "");
            new Assignment().assignToStudent(48);

            new LoginUsingLTI().ltiLogin("48_1");//create a student
            new Assignment().openAssignmentFromCourseStream("48");
            new AttemptQuestion().trueFalse(false, "incorrect", 48);//attempt option B
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='submit']"));            //TC row no. 48
            String finishLink = assignments.getGoToNextLinkOnPopUp().getText();
            if (!finishLink.contains("Finish assignment")) {
                Assert.fail("Finish assignment link is not found on pop-up");
            }
            //TC row no. 49
            String reattempt = assignments.getReAttemptLinkOnPopUp().getText();
            if (!reattempt.contains("Reattempt")) {
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            //TC row no. 50
            assignments.getReAttemptLinkOnPopUp().click();//click on reattempt
            new AttemptQuestion().trueFalse(false, "incorrect", 48);//attempt option B
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='submit']"));
            String correctNotice1 = assignments.getNotification().getText();
            if (correctNotice1.contains("You got it incorrect")) {
                Assert.fail("\"You got it incorrect\" message is displayed.");
            }
            //TC row no. 51
            String finishLink1 = assignments.getGoToNextLinkOnPopUp().getText();
            if (!finishLink1.contains("Finish assignment")) {
                Assert.fail("Finish assignment link is not found on pop-up");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in class immediateFeedEnabledWithGradeReleaseOptionTwo in method immediateFeedEnabledWithGradeReleaseOptionTwoUnlimitedShowAnswer.", e);
        }
    }

}
