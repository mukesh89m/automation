package com.snapwiz.selenium.tests.staf.learningspaces.testcases.regression.assignment;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Policies;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by mukesh on 7/1/16.
 */
public class Policy extends Driver {

    @Test(priority = 1,enabled = true)
    public void createPolicyWithDefaultValue(){
        try {

            String assignmentPolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", "1");
            Policies policies=PageFactory.initElements(driver,Policies.class);
            new LoginUsingLTI().ltiLogin("1");//login as instructor
            new Navigator().NavigateTo("Policies");//go to Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicy, "", "", "", false, "1", "", "", "", "", "");

            new Navigator().navigateToTab("Assignment Policies"); //navigate to Assignment Policies tab
            policies.copyPolicy_link.click(); //    click on the copy policy link

            new Navigator().navigateToTab("Assignment Policies"); //navigate to Assignment Policies tab
            policies.updatePolicy_link.click(); //click on the update policy link

            new Navigator().navigateToTab("Assignment Policies"); //navigate to Assignment Policies tab
            policies.deletePolicy_link.click(); //click on the update policy link

        } catch (Exception e) {
            Assert.fail("Exception in TC createPolicyWithDefaultValue of class Policy ",e);
        }

    }

   @Test(priority = 2,enabled = true)
    public void copyPolicy(){
        try {

            Policies policies=PageFactory.initElements(driver,Policies.class);
            new LoginUsingLTI().ltiLogin("1");//login as instructor
            new Navigator().NavigateTo("Policies");//go to Policies

            policies.copyPolicy_link.click(); //click on the copy policy link
            if(!policies.allowCollaboration.get(0).getAttribute("checked").equals("true"))
                Assert.fail("\"Allow Collaboration\" is not in \"enabled\" state");

            Assert.assertEquals(policies.gradeReleaseOptions.get(3).getAttribute("checked"),"true","GradeRelease Options four is not selected by default ");

            new AssignmentPolicy().enterPolicyName("copied policy");
            policies.save_button.click();//click on the save button
            Thread.sleep(5000);
            new Navigator().navigateToTab("Assignment Policies"); //navigate to Assignment Policies tab

            Assert.assertEquals(policies.policyName_assignmentPolicies.get(0).getText(),"copied policy");
            policies.copyPolicy_link.click(); //click on the copy policy link

            if(!policies.allowCollaboration.get(0).getAttribute("checked").equals("true"))
                Assert.fail("\"Allow Collaboration\" is not in \"enabled\" state");

            Assert.assertEquals(policies.gradeReleaseOptions.get(3).getAttribute("checked"),"true","GradeRelease Options four is not selected by default ");


        } catch (Exception e) {
            Assert.fail("Exception in TC copyPolicy of class Policy ",e);
        }

    }


    @Test(priority = 3,enabled = true)
    public void updatedPolicy(){
        try {

            Policies policies=PageFactory.initElements(driver,Policies.class);
            new LoginUsingLTI().ltiLogin("1");//login as instructor
            new Navigator().NavigateTo("Policies");//go to Policies

            policies.updatePolicy_link.click(); //click on the update policy link
            Assert.assertEquals(policies.policyName.getText(),"copied policy");

            policies.immediateFeedback.get(0).click(); //click on the enable
            policies.save_button.click();//click on the save button
            Thread.sleep(5000);

            new Navigator().navigateToTab("Assignment Policies"); //navigate to Assignment Policies tab
            policies.updatePolicy_link.click(); //click on the update policy link

            if(!policies.updateAllowCollaboration.get(0).getAttribute("checked").equals("true"))
                Assert.fail("\"Allow Collaboration\" is not in \"enabled\" state");

            Assert.assertEquals(policies.gradeReleaseOptions.get(3).getAttribute("checked"),"true","GradeRelease Options four is not selected by default ");

            Assert.assertEquals(policies.immediateFeedback.get(0).getAttribute("checked"),"true","immediate feedback is not enable");


        } catch (Exception e) {
            Assert.fail("Exception in TC updatedPolicy of class Policy ",e);
        }

    }

    /**
     *delete existing policy
     */

    @Test(priority = 3,enabled = true)
    public void deletePolicy(){
        try {

            Policies policies=PageFactory.initElements(driver,Policies.class);
            new LoginUsingLTI().ltiLogin("1");//login as instructor
            new Navigator().NavigateTo("Policies");//go to Policies
            policies.deletePolicy_link.click(); //click on the delete policy
            Thread.sleep(6800);
            Assert.assertEquals(policies.policyNotification.get(0).getText().trim(),"Yes, Delete");
            Assert.assertEquals(policies.policyNotification.get(1).getText().trim(),"No, Cancel");
            policies.policyNotification.get(0).click(); // click on the yes
            for (int i = 1; i < 3; i++) {
                try {
                    Assert.assertNotEquals(policies.policyName_assignmentPolicies.get(0).getText(),"copied policy");
                    break;
                } catch (Exception e) {

                }
            }

        } catch (Exception e) {
            Assert.fail("Exception in TC deletePolicy of class Policy ",e);
        }

    }

    /**
     * once policy is used while assigning assignment instructor can view policy
     */
    @Test(priority = 4,enabled = true)
    public void viewPolicy(){
        try {

            Policies policies=PageFactory.initElements(driver,Policies.class);

            new Assignment().create(1); //create true false question

            new LoginUsingLTI().ltiLogin("1");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(1);//assign to student

            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.viewPolicy_link.click();

            if(!policies.viewAllowCollaboration.get(0).getAttribute("disabled").equals("true"))
                Assert.fail("\"Allow Collaboration\" is not in \"enabled\" state");

            Assert.assertEquals(policies.gradeReleaseOptions.get(3).getAttribute("disabled"),"true","GradeRelease Options four is not disabled by default ");

            Assert.assertEquals(policies.immediateFeedback.get(0).getAttribute("disabled"),"true","immediate feedback is not disabled");


        } catch (Exception e) {
            Assert.fail("Exception in TC viewPolicy of class Policy ",e);
        }

    }

    @Test(priority = 5, enabled = true)
    public void immediateFeedEnabledWithGradeReleaseOptionFour()
    {
        try
        {
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);//click on go to next question link on pop-up

            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "68");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "68");

            new Assignment().create(68);
            new Assignment().addQuestions(68, "truefalse", assessmentname);
            new Assignment().addQuestions(68, "truefalse", assessmentname);

            new LoginUsingLTI().ltiLogin("68_1");//create a student
            new LoginUsingLTI().ltiLogin("68");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "68 Policy description text", "1",null, true, "3", "", "Release explicitly by the instructor", "", "", "");
            new Assignment().assignToStudent(68);

            new LoginUsingLTI().ltiLogin("68_1");//create a student
            new Assignment().openAssignmentFromCourseStream("68");
            //TC row no. 53
            new AttemptQuestion().trueFalse(false, "correct", 68);//attempt option A
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
            new AttemptQuestion().trueFalse(false, "incorrect", 68);//attempt option B
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
            new AttemptQuestion().trueFalse(false, "correct", 68);//attempt option A
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
            new AttemptQuestion().trueFalse(false, "incorrect", 68);//attempt option B
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
            new AttemptQuestion().trueFalse(false, "incorrect", 68);//attempt option B
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
            Assert.fail("Exception in TC immediateFeedEnabledWithGradeReleaseOptionFour of class Policy", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void immediateFeedEnabledWithGradeReleaseOptionOne()
    {
        try
        {
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);//click on go to next question link on pop-up
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "70");

            new LoginUsingLTI().ltiLogin("70_1");//create a student
            new LoginUsingLTI().ltiLogin("70");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "70 Policy description text", "1",null, true, "3", "", "Auto-release on assignment submission", "", "", "");
            new Assignment().assignToStudent(70);

            new LoginUsingLTI().ltiLogin("70_1");//create a student
            new Assignment().openAssignmentFromCourseStream("70");
            //TC row no. 21
            new AttemptQuestion().trueFalse(false, "correct", 70);//attempt option A
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
            new AttemptQuestion().trueFalse(false, "incorrect", 70);//attempt option B
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
            new AttemptQuestion().trueFalse(false, "correct", 70);//attempt option A
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
            new AttemptQuestion().trueFalse(false, "incorrect", 70);//attempt option B
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
            new AttemptQuestion().trueFalse(false, "incorrect", 70);//attempt option B
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
            Assert.fail("Exception in TC immediateFeedEnabledWithGradeReleaseOptionOne of class Policy", e);
        }
    }


    @Test(priority = 7, enabled = true)
    public void immediateFeedEnabledWithGradeReleaseOptionThree()
    {
        try
        {
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);//click on go to next question link on pop-up

            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "52");

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
            Assert.fail("Exception in TC  immediateFeedEnabledWithGradeReleaseOptionThree of class Policy", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void immediateFeedEnabledWithGradeReleaseOptionTwo()
    {
        try
        {
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);//click on go to next question link on pop-up

            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "36");

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
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='nextQuestion']"));
            String str = assignments.getGoToNextLinkOnPopUp().getText();
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
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='nextQuestion']"));
            String incorrectNotice = assignments.getNotification().getText();
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
            new UIElement().waitAndFindElement(By.xpath("//a[@mode='nextQuestion']"));
            String correctNotice1 = assignments.getNotification().getText();
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
            Assert.fail("Exception in TC immediateFeedEnabledWithGradeReleaseOptionTwo class Policy", e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void immediateFeedShowAnswerEnabledWithGradeReleaseOptionFour()
    {
        try
        {
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);//click on go to next question link on pop-up
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", "162");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "162");

            new Assignment().create(162);
            new Assignment().addQuestions(162, "truefalse", assessmentname);
            new Assignment().addQuestions(162, "textentry", assessmentname);
            new Assignment().addQuestions(162, "multipleselection", assessmentname);
            new Assignment().addQuestions(162, "essay", assessmentname);
            new Assignment().addQuestions(162, "multiplechoice", assessmentname);
            new Assignment().addQuestions(162, "truefalse", assessmentname);


            new LoginUsingLTI().ltiLogin("162_1");//create a student
            new LoginUsingLTI().ltiLogin("162");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicy, "162 Policy description text", "1",null, true, "3", "1", "Release explicitly by the instructor", "", "", "");
            new Assignment().assignToStudent(162);

            new LoginUsingLTI().ltiLogin("162_1");//create a student
            new Assignment().openAssignmentFromCourseStream("162");
            new AttemptQuestion().trueFalse(false, "correct", 162);//attempt option A
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//input[@title='Next Question']"));
            //TC row no. 163
            String correctNotice = assignments.getNotification().getText();
            if(!correctNotice.contains("You got it correct")){
                Assert.fail("\"You got it correct\" message is not displayed.");
            }
            //TC row no. 164
            String str = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }

            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            //TC row no. 165
            new AttemptQuestion().trueFalse(false, "incorrect", 162);//attempt option B
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//span[@mode='nextQuestion']"));
            Thread.sleep(5000);
            String incorrectNotice = assignments.getNotification().getText();
            if(!incorrectNotice.contains("You got it incorrect")){
                Assert.fail("\"You got it incorrect\" message is not displayed.");
            }
            //TC row no. 166
            String reattempt = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            String str1 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str1.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }
            //TC row no. 168
            boolean found = new BooleanValue().presenceOfElement(162, By.cssSelector("div[class='true-false-student-tick true-false-student-right']"));
            Assert.assertEquals(found, true, "Show Correct Answer option is not displayed for a true false type question.");
            //TC row no. 169
            assignments.getReAttemptLinkOnPopUp().click();//click on reattempt
            //no need to select option B again it remains selected from previous attempt
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//span[@mode='nextQuestion']"));
            String incorrectNotice1 = assignments.getNotification().getText();
            if(!incorrectNotice1.contains("You got it incorrect")){
                Assert.fail("\"You got it incorrect\" message is not displayed.");
            }
            //TC row no. 170
            String str2 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str2.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }
            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().textEntry(false, "incorrect", 162);//attempt incorrect
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//input[@title='Next Question']"));
            //TC row no. 171
            new Click().clickbylinkText("Show Correct Answer");
            String corrAnswer = new TextFetch().textfetchbyclass("correct-answer-wrapper");
            if(!corrAnswer.contains("Correct Answer")){
                Assert.fail("Correct answer is not displayed for text entry type question in on clicking \"Show Correct Answer\" link.");
            }

            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().multipleSelection(false, "partiallycorrect", 162);//attempt incorrect
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//span[@mode='nextQuestion']"));
            //TC row no. 172
            String incorrectNotice2 = assignments.getNotification().getText();
            if(!incorrectNotice2.contains("You got it partially correct")){
                Assert.fail("\"You got it partially correct\" message is not displayed.");
            }
            //TC row no. 173
            String reattempt1 = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt1.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            String str3 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str3.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }
            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().essay(false, "incorrect", 162);//attempt incorrect
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//span[@mode='nextQuestion']"));
            //TC row no. 175
            String incorrectNotice3 = assignments.getNotification().getText();
            if(!incorrectNotice3.contains("Your instructor will provide feedback")){
                Assert.fail("\"Your instructor will provide feedback\" message is not displayed.");
            }

            //TC row no. 176
            String reattempt2 = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt2.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            String str4 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str4.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }

            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().multipleChoice(false, "skip", 162);//skip the question
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//span[@mode='nextQuestion']"));
            Thread.sleep(2000);
            //TC row no. 177
            String incorrectNotice4 = assignments.getNotification().getText();
            if(!incorrectNotice4.contains("You have skipped the question")){
                Assert.fail("\"'You have skipped the question\" message is not displayed.");
            }

            //TC row no. 178
            String reattempt3 = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt3.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            String str5 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str5.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }

            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().trueFalse(false, "incorrect", 162);//attempt incorrect
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//span[@mode='submit']"));
            //TC row no. 180
            String finishLink = assignments.getGoToNextLinkOnPopUp().getText();
            if(!finishLink.contains("Finish assignment")){
                Assert.fail("Finish assignment link is not found on pop-up");
            }
            //TC row no. 181
            String reattempt4 = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt4.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            //TC row no. 182
            assignments.getReAttemptLinkOnPopUp().click();//click on reattempt
            //no need to select option B again as it remains selected from previous attempt
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//span[@mode='submit']"));
            Thread.sleep(3000);
            String incorrectNotice5 = assignments.getNotification().getText();
            if(!incorrectNotice5.contains("You got it incorrect")){
                Assert.fail("\"You got it incorrect\" message is not displayed.");
            }
            //TC row no. 183
            String finishLink1 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!finishLink1.contains("Finish assignment")){
                Assert.fail("Finish assignment link is not found on pop-up");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC immediateFeedShowAnswerEnabledWithGradeReleaseOptionFour class Policy", e);
        }
    }


    @Test(priority = 10, enabled = true)
    public void immediateFeedShowAnswerEnabledWithGradeReleaseOptionOne()
    {
        try
        {
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);//click on go to next question link on pop-up

            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", "84");


            new LoginUsingLTI().ltiLogin("84_1");//create a student
            new LoginUsingLTI().ltiLogin("84");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicy, "84 Policy description text", "1",null, true, "3", "1", "Auto-release on assignment submission", "", "", "");
            new Assignment().assignToStudent(84);

            new LoginUsingLTI().ltiLogin("84_1");//create a student
            new Assignment().openAssignmentFromCourseStream("84");
            new AttemptQuestion().trueFalse(false, "correct", 84);//attempt option A
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//input[@title='Next Question']"));
            //TC row no. 85
            String correctNotice = assignments.getNotification().getText();
            if(!correctNotice.contains("You got it correct")){
                Assert.fail("\"You got it correct\" message is not displayed.");
            }
            //TC row no. 86
            String str = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }

            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            //TC row no. 87
            new AttemptQuestion().trueFalse(false, "incorrect", 84);//attempt option B
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//input[@title='Next Question']"));
            Thread.sleep(3000);
            String incorrectNotice = assignments.getNotification().getText();
            if(!incorrectNotice.contains("You got it incorrect")){
                Assert.fail("\"You got it incorrect\" message is not displayed.");
            }
            //TC row no. 88
            String reattempt = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            String str1 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str1.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }
            //TC row no. 90
            boolean found = new BooleanValue().presenceOfElement(84, By.cssSelector("div[class='true-false-student-tick true-false-student-right']"));
            Assert.assertEquals(found, true, "Show Correct Answer option is not displayed for a true false type question.");
            //TC row no. 91
            assignments.getReAttemptLinkOnPopUp().click();//click on reattempt
            //no need to select option B again it remains selected from previous attempt
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//input[@title='Next Question']"));
            Thread.sleep(3000);
            String incorrectNotice1 = assignments.getNotification().getText();
            if(!incorrectNotice1.contains("You got it incorrect")){
                Assert.fail("\"You got it incorrect\" message is not displayed.");
            }
            //TC row no. 92
            String str2 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str2.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }
            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().textEntry(false, "incorrect", 84);//attempt incorrect
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//input[@title='Next Question']"));
            //TC row no. 93
            new Click().clickbylinkText("Show Correct Answer");
            String corrAnswer = new TextFetch().textfetchbyclass("correct-answer-wrapper");
            if(!corrAnswer.contains("Correct Answer")){
                Assert.fail("Correct answer is not displayed for text entry type question in on clicking \"Show Correct Answer\" link.");
            }

            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().multipleSelection(false, "partiallycorrect", 84);//attempt incorrect
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//input[@title='Next Question']"));
            Thread.sleep(5000);
            //TC row no. 94
            String incorrectNotice2 = assignments.getNotification().getText();
            if(!incorrectNotice2.contains("You got it partially correct")){
                Assert.fail("\"You got it partially correct\" message is not displayed.");
            }
            //TC row no. 95
            String reattempt1 = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt1.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            String str3 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str3.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }
            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().essay(false, "incorrect", 84);//attempt incorrect
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//span[@mode='nextQuestion']"));
            //TC row no. 97
            String incorrectNotice3 = assignments.getNotification().getText();
            if(!incorrectNotice3.contains("Your instructor will provide feedback")){
                Assert.fail("\"Your instructor will provide feedback\" message is not displayed.");
            }

            //TC row no. 98
            String reattempt2 = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt2.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            String str4 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str4.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }

            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().multipleChoice(false, "skip", 84);//skip the question
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//span[@mode='nextQuestion']"));
            Thread.sleep(2000);
            //TC row no. 99
            String incorrectNotice4 = assignments.getNotification().getText();
            if(!incorrectNotice4.contains("You have skipped the question")){
                Assert.fail("\"'You have skipped the question\" message is not displayed.");
            }

            //TC row no. 100
            String reattempt3 = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt3.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            String str5 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str5.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }


            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().trueFalse(false, "incorrect", 84);//attempt incorrect
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//span[@mode='submit']"));
            //TC row no. 102
            String finishLink = assignments.getGoToNextLinkOnPopUp().getText();
            if(!finishLink.contains("Finish assignment")){
                Assert.fail("Finish assignment link is not found on pop-up");
            }
            //TC row no. 103
            String reattempt4 = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt4.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            //TC row no. 104
            assignments.getReAttemptLinkOnPopUp().click();//click on reattempt
            //no need to select option B again as it remains selected from previous attempt
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//span[@mode='submit']"));
            Thread.sleep(3000);
            String incorrectNotice5 = assignments.getNotification().getText();
            if(!incorrectNotice5.contains("You got it incorrect")){
                Assert.fail("\"You got it incorrect\" message is not displayed.");
            }
            //TC row no. 105
            String finishLink1 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!finishLink1.contains("Finish assignment")){
                Assert.fail("Finish assignment link is not found on pop-up");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in class ImmediateFeedShowAnswerEnabledWithGradeReleaseOptionOne in method immediateFeedShowAnswerEnabledWithGradeReleaseOptionOne.", e);
        }
    }


    @Test(priority = 11, enabled = true)
    public void immediateFeedShowAnswerEnabledWithGradeReleaseOptionTwo()
    {
        try
        {
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);//click on go to next question link on pop-up

            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", "110");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "110");


            new LoginUsingLTI().ltiLogin("110_1");//create a student
            new LoginUsingLTI().ltiLogin("110");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicy, "110 Policy description text", "1",null, true, "3", "1", "Auto-release on due date", "", "", "");
            new Assignment().assignToStudent(110);

            new LoginUsingLTI().ltiLogin("110_1");//create a student
            new Assignment().openAssignmentFromCourseStream("110");
            new AttemptQuestion().trueFalse(false, "correct", 110);//attempt option A
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//span[@mode='nextQuestion']"));

            //TC row no. 111
            String correctNotice = assignments.getNotification().getText();
            if(!correctNotice.contains("You got it correct")){
                Assert.fail("\"You got it correct\" message is not displayed.");
            }
            //TC row no. 112
            String str = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }

            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            //TC row no. 113
            new AttemptQuestion().trueFalse(false, "incorrect", 110);//attempt option B
            new Assignment().nextButtonInQuestionClick();//click on Next button
            Thread.sleep(4000);
            new UIElement().waitAndFindElement(By.xpath("//span[@mode='nextQuestion']"));
            String incorrectNotice = assignments.getNotification().getText();
            if(!incorrectNotice.contains("You got it incorrect")){
                Assert.fail("\"You got it incorrect\" message is not displayed.");
            }
            //TC row no. 114
            String reattempt = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            String str1 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str1.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }
            //TC row no. 116
            boolean found = new BooleanValue().presenceOfElement(110, By.cssSelector("div[class='true-false-student-tick true-false-student-right']"));
            Assert.assertEquals(found, true, "Show Correct Answer option is not displayed for a true false type question.");
            //TC row no. 117
            assignments.getReAttemptLinkOnPopUp().click();//click on reattempt
            //no need to select option B again it remains selected from previous attempt
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//span[@mode='nextQuestion']"));
            Thread.sleep(3000);
            String incorrectNotice1 = assignments.getNotification().getText();
            if(!incorrectNotice1.contains("You got it incorrect")){
                Assert.fail("\"You got it incorrect\" message is not displayed.");
            }
            //TC row no. 118
            String str2 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str2.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }
            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().textEntry(false, "incorrect", 110);//attempt incorrect
            new Assignment().nextButtonInQuestionClick();//click on Next button

            //TC row no. 119
            new Click().clickbylinkText("Show Correct Answer");
            String corrAnswer = new TextFetch().textfetchbyclass("correct-answer-wrapper");
            if(!corrAnswer.contains("Correct Answer")){
                Assert.fail("Correct answer is not displayed for text entry type question in on clicking \"Show Correct Answer\" link.");
            }

            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().multipleSelection(false, "partiallycorrect", 110);//attempt incorrect
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//span[@mode='nextQuestion']"));
            //TC row no. 120
            String incorrectNotice2 = assignments.getNotification().getText();
            if(!incorrectNotice2.contains("You got it partially correct")){
                Assert.fail("\"You got it partially correct\" message is not displayed.");
            }
            //TC row no. 121
            String reattempt1 = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt1.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            String str3 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str3.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }
            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().essay(false, "incorrect", 110);//attempt incorrect
            new Assignment().nextButtonInQuestionClick();//click on Next button
            new UIElement().waitAndFindElement(By.xpath("//span[@mode='nextQuestion']"));

            //TC row no. 123
            String incorrectNotice3 = assignments.getNotification().getText();
            if(!incorrectNotice3.contains("Your instructor will provide feedback")){
                Assert.fail("\"Your instructor will provide feedback\" message is not displayed.");
            }

            //TC row no. 124
            String reattempt2 = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt2.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            String str4 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str4.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }

            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().multipleChoice(false, "skip", 110);//skip the question
            new Assignment().nextButtonInQuestionClick();//click on Next button
            //TC row no. 125
            new UIElement().waitAndFindElement(By.xpath("//span[@mode='nextQuestion']"));
            String incorrectNotice4 = assignments.getNotification().getText();
            if(!incorrectNotice4.contains("You have skipped the question")){
                Assert.fail("\"'You have skipped the question\" message is not displayed.");
            }

            //TC row no. 126
            String reattempt3 = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt3.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            String str5 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!str5.contains("Go to next question")){
                Assert.fail("Go to next question link is not found on pop-up");
            }


            assignments.getGoToNextLinkOnPopUp().click();//click on go to next question link on pop-up
            new AttemptQuestion().trueFalse(false, "incorrect", 110);//attempt incorrect
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            Thread.sleep(5000);

            //TC row no. 128
            String finishLink = assignments.getGoToNextLinkOnPopUp().getText();
            if(!finishLink.contains("Finish assignment")){
                Assert.fail("Finish assignment link is not found on pop-up");
            }
            //TC row no. 129
            String reattempt4 = assignments.getReAttemptLinkOnPopUp().getText();
            if(!reattempt4.contains("Reattempt")){
                Assert.fail("Reattempt link is not found on pop-up.");
            }
            //TC row no. 130
            assignments.getReAttemptLinkOnPopUp().click();//click on reattempt
            //no need to select option B again as it remains selected from previous attempt
            new Assignment().submitButtonInQuestionClick();//click on Submit button
            new UIElement().waitAndFindElement(By.xpath("//span[@mode='submit']"));
            Thread.sleep(3000);
            String incorrectNotice5 = assignments.getNotification().getText();
            if(!incorrectNotice5.contains("You got it incorrect")){
                Assert.fail("\"You got it incorrect\" message is not displayed.");
            }
            //TC row no. 131
            String finishLink1 = assignments.getGoToNextLinkOnPopUp().getText();
            if(!finishLink1.contains("Finish assignment")){
                Assert.fail("Finish assignment link is not found on pop-up");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC immediateFeedShowAnswerEnabledWithGradeReleaseOptionTwo of class ", e);
        }
    }


    @Test(priority = 12, enabled = true)
    public void courseAssignmentPolicy() {
        try {
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            new DirectLogin().CMSLogin(); //CMS login as author
            new SelectCourse().selectcourse();
            courseOutline.courseDetails.click(); //click on the courseDetails icon

            //Tc row no 11
            //It should show "Course Details" option and an additional option as “Course Assignment Policy"
            Assert.assertEquals(courseOutline.courseDetails.getText().trim(), "Course Details", "Course Details is not displaying");
            Assert.assertEquals(courseOutline.courseAssignmentPolicyLink.getText().trim(), "Course Settings", "Course Assignment Policy is not displaying");

            //Tc row no 12
            //Click on "Course Assignment Policy option"
            courseOutline.courseAssignmentPolicyLink.click();//Click on "Course Assignment Policy option"
            Thread.sleep(3000);
            //Expected:Dropdown should get closed and "a popup should open".
            List<WebElement> list = driver.findElements(By.xpath("/*//*[text()='Course Details']"));
            Assert.assertTrue(list.size() > 0, "Dropdown is displayed after clicking on course Assignment Policy link");
            Assert.assertEquals(courseOutline.courseAssignmentPolicyHeader.isDisplayed(), true, " popup is not getting open");

            //Tc row no 13
            //Expected :"It should show the title as ""Course Assignment Policy"" in Green color.
            Thread.sleep(5000);
            String textColor = courseOutline.courseAssignmentPolicyHeader.getCssValue("color");
            System.out.println("textColor:" + textColor);
            if (!textColor.trim().equals("rgba(26, 137, 2, 1)"))
                Assert.fail("Course Assignment Policy in Green color");
            Assert.assertEquals(courseOutline.courseAssignmentPolicyHeader.getText().trim(), "Course Settings", "Course Assignment Policy title is not displaying");

            //Tc row no 14 """Help icon"" should be there after ""Title"".
            Assert.assertEquals(courseOutline.helpIcon_position.isDisplayed(), true, "Help icon is not after Title");

            //Tc row no 15
            //Click on help icon next to "Title".
            courseOutline.getHelpIcon().click();//click on the help pop up
            String popText = "Use this setting to enable or disable course level options which are applicable to specific courses only.";
            Assert.assertEquals(courseOutline.helpText.getText().trim(), popText, "Use this policy to enable or disable special policy setting which are applicable to specific courses only. is not showing");

            //Tc row no 16
            //Expected:Language grading policy label should be available
            Assert.assertEquals(courseOutline.gradingPolicy_Text.get(0).getText().trim(), "Language grading policy", "Language grading policy is not available");
            //Tc row no 17
            //Expected:"Help icon" should also be there after "Language grading policy".
            Assert.assertEquals(courseOutline.helpIcon_afterPolicy.isDisplayed(), true, "Help icon is not after Language grading policy");

            //Tc row no 18
            //7.Click on help icon next to “Language grading policy
            courseOutline.helpIcon_afterPolicy.click(); //click on the help icon after Language grading policy
            //Expected:
            String popText1 = "Disable option will hide the “Language Options” field in the create assignment policy page. Enable this to appear in Spanish or other language courses where accents are to be evaluated.";
            Assert.assertEquals(courseOutline.helpText_afterPolicy.getText().trim(), popText1, "Help message is not displaying");

            //Tc row no 19
            //Expected:Two values with its radio button"Enable" & "Disable" should be there.
            Assert.assertEquals(courseOutline.enable_radioButton_Text.isDisplayed(), true, "Enable radio button is not displayed");
            Assert.assertEquals(courseOutline.disable_radioButton_Text.get(0).isDisplayed(), true, "disable radio button is not displayed");

            //Tc row no 20
            //Cancel link should be there.
            Assert.assertEquals(courseOutline.cancel_button.getText().trim(), "Cancel", "Cancel link is not available");
            //Tc row no 21
            Assert.assertEquals(courseOutline.save_button.getText().trim(), "Save", "Save button is not available");

            //Tc row no 22
            //The default value for “Language grading policy” should be “Disable”.
            Assert.assertEquals(courseOutline.disable_radioButton_Text.get(0).isEnabled(), true, "Default value of Language grading policy is not disable");

            //Tc row no 23
            //"8. Select value ""Enable"". 9.Click on ""Cancel Link"". "
            courseOutline.enable_radioButton.click();//click on the enable radio button
            courseOutline.cancel_button.click();//click on the cancel button

            Thread.sleep(3000);
            //Expected:" The popup should be closed & the changes should not be saved."
            List<WebElement> list1 = driver.findElements(By.className("course-assignment-policy-body-content"));
            Assert.assertTrue(list1.size() == 0, "Dropdown is displayed after clicking on course Assignment Policy link");
            //Tc row no 25
            goToCourseAssignmentPolicy(25);
            Thread.sleep(2000);
            courseOutline.enable_radioButton.click();//click on the enable radio button
            courseOutline.save_button.click();
            goToCourseAssignmentPolicy(25);

            Assert.assertEquals(courseOutline.enable_radioButton.isSelected(), true, "Default value of Language grading policy is not disable");

            goToCourseAssignmentPolicy(26);
            courseOutline.disable_radioButton.click();//click on the enable radio button
            courseOutline.save_button.click();
            goToCourseAssignmentPolicy(26);

            Assert.assertEquals(courseOutline.disable_radioButton.isSelected(), true, "Default value of Language grading policy is not disable");


        } catch (Exception e) {
            Assert.fail("Exception in test case courseAssignmentPolicy of class CourseAssignmentPolicy:", e);
        }
    }

    public  void goToCourseAssignmentPolicy(int dataIndex) throws InterruptedException {
        CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
        courseOutline.courseDetails.click(); //click on the courseDetails icon
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@rel='3']")).click();

    }


    @Test(priority = 13,enabled = true)
    public void submitAssignmentWithCollaborationDisabled()
    {
        try
        {
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", "111");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "111");
            new Assignment().create(111);
            new Assignment().addQuestionsWithCustomizedQuestionText(111, "qtn-type-true-false-img", assessmentname, 3);

            new LoginUsingLTI().ltiLogin("111");//login as instructor
            new Navigator().NavigateTo("Policies");//go to Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicy, "", "1", "", false, "1", "", "", "", "", "");
            new AssignmentPolicy().updateAllowCollaboration("111", false);

            new LoginUsingLTI().ltiLogin("111_1"); //Creating user student 1
            new LoginUsingLTI().ltiLogin("111_2");//Creating user student 2

            new LoginUsingLTI().ltiLogin("111"); //  Logging in as instructor
            new Assignment().assignToStudent(111);  //Assigning assignment

            new LoginUsingLTI().ltiLogin("111_1"); //login as student 1
            new Assignment().openAssignmentFromCourseStream("111");//open the assignment

            //TC row no. 111....6. Submit the first question..Student should navigate to next question
            new SelectAnswerAndSubmit().staticAssignment("A");//submit 1st question
            String qNo = driver.findElement(By.className("current-question-index")).getText();
            if(!qNo.contains("2"))
                Assert.fail("After submitting the 1st question with policy \"Allow Collaboration\" Disabled  the student does not navigate to second question");

            //TC row no. 55 ... 7. Click on question dropdown to navigate the previous question..Discussion and Star tab should not appear
            new StaticAssignmentSubmit().navigateFromQuestionDropdown("1");//navigate question 1
            boolean isTabPresent = false;

            List<WebElement> allTabs = driver.findElements(By.className("tabs"));
            for(WebElement tab: allTabs)
            {
                System.out.println("tabd: "+tab.getText());
                if(tab.getText().equals("Discussion"))
                {
                    isTabPresent = true;
                    break;
                }
            }
            if(isTabPresent == true)
                Assert.fail("The discussion tab for attempted question appears for assignment with policy \"Allow Collaboration\" Disabled.");
            //TC row no. 56..""8. Finish the assignment and go to report page  9. Click on any question card ...Discussion and Star tab should appear
            new Assignment().submitAssignmentAsStudent(111);
            new QuestionCard().clickOnCard("", 0);//click on 1st question's question card
            new Navigator().navigateToTab("Add to My Notes");//Star tab appears
            new Navigator().navigateToTab("Discussion");//discussion tab appears

            //TC row no. 57.. ..10. Add a discussion in discussion tab..Posted Discussion should appear under Discussion and Star tab
            String discussionText = new RandomString().randomstring(10);
            new Discussion().postDiscussion(discussionText);

            boolean isPresentInDiscussionTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isPresentInDiscussionTab == false)
                Assert.fail("Discussion posted for question of assignment with policy \"Allow Collaboration\" Disabled is not present under discussion tab.");

            boolean isPresentInStarTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", discussionText);
            if(isPresentInStarTab == false)
                Assert.fail("Discussion posted for question of assignment with policy \"Allow Collaboration\" Disabled is not present under Star tab.");

            boolean isPresentInAssignmentsTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Assignments", discussionText);
            if(isPresentInAssignmentsTab == true)
                Assert.fail("Discussion posted for question of assignment with policy \"Allow Collaboration\" Disabled is present under Assignments tab.");

            //TC row no. 58..."11. Login as student2. 12. Go to course stream page"...Posted Discussion by student1 should not appear in Course Stream page
            new LoginUsingLTI().ltiLogin("111_2");//login as student 2
            new Navigator().NavigateTo("Course Stream");
            boolean isPresent = new CourseStream().isPresentCourseStream("discussion");
            if(isPresent == true)
                Assert.fail("Discussion posted for question of assignment with policy \"Allow Collaboration\" Disabled by student 1 is present in Course Stream for student 2.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC submitAssignmentWithCollaborationDisabled in class Policy", e);
        }
    }

    @Test(priority = 14,enabled = true)
    public void submitAssignmentWithCollaborationEnabled()
    {
        try
        {
            String policyname = ReadTestData.readDataByTagName("", "policyname", "112");

            new LoginUsingLTI().ltiLogin("112_1");//create a student1
            new LoginUsingLTI().ltiLogin("112_2");//create a student2

            new LoginUsingLTI().ltiLogin("112");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(policyname, "112 Policy description text", "1",null, false, "1", "", null, "", "", "");
            new Assignment().assignToStudent(112);

            new LoginUsingLTI().ltiLogin("112_1");//login as student1
            new Assignment().openAssignmentFromCourseStream("112");//open the assignment

            //TC row no. 112....13. Submit the first question..Student should navigate to next question
            new SelectAnswerAndSubmit().staticAssignment("A");//submit 1st question
            String qNo = driver.findElement(By.className("current-question-index")).getText();
            if(!qNo.contains("2"))
                Assert.fail("After submitting the 1st question with assignment policy the student does not navigate to second question");

            //TC row no. 30 ... 14. Click on question dropdown to navigate the previous question..Discussion and Star tab should appear at the right side of the question preview page for first question



            new StaticAssignmentSubmit().navigateFromQuestionDropdown("1");//navigate question 1
            new Navigator().navigateToTab("Add to My Notes");//Star tab appears
            new Navigator().navigateToTab("Discussion");//discussion tab appears

            //TC row no. 31....15. Add a discussion in Discussion tab through "+New Discussion" button...Posted Discussion should appear under Discussion and Star tab only
            String discussionText = new RandomString().randomstring(10);
            new Discussion().postDiscussion(discussionText);

            boolean isPresentInDiscussionTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isPresentInDiscussionTab == false)
                Assert.fail("Discussion is not present under discussion tab.");

            boolean isPresentInStarTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", discussionText);
            if(isPresentInStarTab == false)
                Assert.fail("Discussion is not present under Star tab.");

            boolean isPresentInAssignmentsTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Assignments", discussionText);
            if(isPresentInAssignmentsTab == true)
                Assert.fail("Discussion is present under Assignments tab.");


            boolean isPresentInAboutTab = new Discussion().isDiscussionOrNotePresentUnderTabs("About", discussionText);
            if(isPresentInAboutTab == true)
                Assert.fail("Discussion is present under About tab.");

            //TC row no. 32...16. Add a note in Star tab through "+New Note" button...Posted note should appear under Star tab only
            new Navigator().navigateToTab("Add to My Notes");//go to Star tab
            String notesText = new RandomString().randomstring(10);
            new Discussion().postNote(notesText);

            boolean isNotePresentInDiscussionTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", notesText);
            if(isNotePresentInDiscussionTab == true)
                Assert.fail("Notes is present under Discussion tab.");

            boolean isNotePresentInStarTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", notesText);
            if(isNotePresentInStarTab == false)
                Assert.fail("Notes is not present under Star tab.");

            boolean isNotePresentInAssignmentsTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Assignments", notesText);
            if(isNotePresentInAssignmentsTab == true)
                Assert.fail("notes is present under Assignments tab.");

            boolean isNotePresentInAboutTab = new Discussion().isDiscussionOrNotePresentUnderTabs("About", notesText);
            if(isNotePresentInAboutTab == true)
                Assert.fail("Note is present under About tab.");

            //TC row no. 33.."17. Attempt each question and Finish the assignment18. Go to report page and click on question card for which the discussion and note were posted"
            //Posted discussion should be present under Discussion and Star tab
            new Assignment().submitAssignmentAsStudent(112);
            new QuestionCard().clickOnCard("", 0);//click on 1st question's question card
            boolean isDiscussionPresentInDiscussionTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isDiscussionPresentInDiscussionTab == false)
                Assert.fail("Added discussion is not present under discussion tab after navigating from question card.");

            boolean isDiscussionPresentInStarTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", discussionText);
            if(isDiscussionPresentInStarTab == false)
                Assert.fail("Added discussion is not present under discussion tab after navigating from question card.");

            //TC row no. 34...Posted note should be present under Star tab
            boolean isNotePresentInStarTab1 = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", notesText);
            if(isNotePresentInStarTab1 == false)
                Assert.fail("Added Note is not present under Star tab after navigating from question card.");

            new LoginUsingLTI().ltiLogin("112_2"); //login as student 2
            new Assignment().openAssignmentFromCourseStream("112");//open the assignment

            //TC row no. 35....19. Submit as student 2...Discussion posted by student 1 for question1 should appear under Discussion tab of student2
            new SelectAnswerAndSubmit().staticAssignment("A");//submit 1st question
            new StaticAssignmentSubmit().navigateFromQuestionDropdown("1");//navigate question 1
            boolean isDiscussionPresentForStudent2 = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isDiscussionPresentForStudent2 == false)
                Assert.fail("Discussion added by student 1 on 1st question is not present under discussion tab for student 2.");

            boolean isNotePresentForStudent2 = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", notesText);
            if(isNotePresentForStudent2 == true)
                Assert.fail("Note added by student 1 on 1st question is present under Star tab for student 2.");


            //TC row no. 37..."22. Complete the assignment..23. Go to Course Stream"...Discussion posted by student1 should appear in Course Stream
            new Assignment().submitAssignmentAsStudent(112);//complete the assignment
            new Navigator().NavigateTo("Course Stream");
            boolean isPresent = new CourseStream().isPresentCourseStream("discussion");
            if(isPresent == false)
                Assert.fail("Discussion added by student 1 is absent Course Stream for student 2.");

            //TC row no. 38, 39..//23. Click on the jump-out icon of that posted discussion....Discussion posted by student 1 should appear under Discussion tab of student2
            driver.findElement(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']")).click();//click on Jump out icon
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[title='Discussion']")));
            boolean isDiscussionPresentForStudent3 = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isDiscussionPresentForStudent3 == false)
                Assert.fail("On clicking jump out icon from Course Stream, the Discussion added by student 1 on 1st question is not present under discussion tab for student 2.");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC submitAssignmentWithCollaborationEnabled in class Policy", e);
        }
    }

    @Test(priority = 15,enabled = true)
    public void submitAssignmentWithCollaborationEnabledWithStudentToInstructorSocialPolicy()
    {
        try
        {
            String policyname = ReadTestData.readDataByTagName("", "policyname", "113");

            new LoginUsingLTI().ltiLogin("113_1");//create a student1
            new LoginUsingLTI().ltiLogin("113_2");//create a student2

            new LoginUsingLTI().ltiLogin("113");
            new SetSocialPolicy().setSocialPolicy("113", "three"); //set Student to Instructor social policy
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(policyname, "113 Policy description text", "1",null, false, "1", "", null, "", "", "");
            new Assignment().assignToStudent(113);

            new LoginUsingLTI().ltiLogin("113_1");//login as student1
            new Assignment().openAssignmentFromCourseStream("113");//open the assignment

            //TC row no. 113....9. Submit the first question..Student should navigate to next question
            new SelectAnswerAndSubmit().staticAssignment("A");//submit 1st question
            String qNo = driver.findElement(By.className("question-display-label")).getText();
            if(!qNo.contains("2"))
                Assert.fail("Student does not navigate to second question after submitting the 1st question of an assignment with policy allow collaboration enabled and social policy is set as \"student to instructor\".");

            //TC row no. 72 ... 10. Click on question dropdown to navigate the previous question..Discussion and Star tab should appear at the right side of the question preview page for first question
            new StaticAssignmentSubmit().navigateFromQuestionDropdown("1");//navigate question 1
            new Navigator().navigateToTab("Add to My Notes");//Star tab appears
            new Navigator().navigateToTab("Discussion");//discussion tab appears

            //TC row no. 73....11. Add a discussion in Discussion tab through "+New Discussion" button...Posted Discussion should appear under Discussion and Star tab only
            new Navigator().navigateToTab("Discussion");//discussion tab appears
            String discussionText = new RandomString().randomstring(10);
            new Discussion().postDiscussion(discussionText);

            boolean isPresentInDiscussionTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isPresentInDiscussionTab == false)
                Assert.fail("Discussion is not present under discussion tab, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"Student to Instructor\".");

            boolean isPresentInStarTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", discussionText);
            if(isPresentInStarTab == false)
                Assert.fail("Discussion is not present under Star tab, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"Student to Instructor\".");

            boolean isPresentInAssignmentsTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Assignments", discussionText);
            if(isPresentInAssignmentsTab == true)
                Assert.fail("Discussion is present under Assignments tab, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"Student to Instructor\".");


            boolean isPresentInAboutTab = new Discussion().isDiscussionOrNotePresentUnderTabs("About", discussionText);
            if(isPresentInAboutTab == true)
                Assert.fail("Discussion is present under About tab, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"Student to Instructor\".");

            //TC row no. 74...12. Add a note in Star tab through "+New Note" button...Posted note should appear under Star tab only
            new Navigator().navigateToTab("Add to My Notes");//go to Star tab
            String notesText = new RandomString().randomstring(10);
            new Discussion().postNote(notesText);

            boolean isNotePresentInDiscussionTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", notesText);
            if(isNotePresentInDiscussionTab == true)
                Assert.fail("Notes is present under Discussion tab, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"Student to Instructor\".");

            boolean isNotePresentInStarTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", notesText);
            if(isNotePresentInStarTab == false)
                Assert.fail("Notes is not present under Star tab, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"Student to Instructor\".");

            boolean isNotePresentInAssignmentsTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Assignments", notesText);
            if(isNotePresentInAssignmentsTab == true)
                Assert.fail("notes is present under Assignments tab, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"Student to Instructor\".");

            boolean isNotePresentInAboutTab = new Discussion().isDiscussionOrNotePresentUnderTabs("About", notesText);
            if(isNotePresentInAboutTab == true)
                Assert.fail("Note is present under About tab, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"Student to Instructor\".");

            //TC row no. 75..""13. Attempt each question and Finish the assignment    14. Go to report page and click on question card for which the discussion and note were posted"
            //Posted discussion should be present under Discussion and Star tab
            new Assignment().submitAssignmentAsStudent(113);
            new QuestionCard().clickOnCard("", 0);//click on 1st question's question card
            boolean isDiscussionPresentInDiscussionTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isDiscussionPresentInDiscussionTab == false)
                Assert.fail("Added discussion is not present under discussion tab after navigating from question card, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"Student to Instructor\".");

            boolean isDiscussionPresentInStarTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", discussionText);
            if(isDiscussionPresentInStarTab == false)
                Assert.fail("Added discussion is not present under discussion tab after navigating from question card, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"Student to Instructor\".");

            //TC row no. 76...Posted note should be present under Star tab
            boolean isNotePresentInStarTab1 = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", notesText);
            if(isNotePresentInStarTab1 == false)
                Assert.fail("Added Note is not present under Star tab after navigating from question card, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"Student to Instructor\".");

            new LoginUsingLTI().ltiLogin("113_2"); //login as student 2
            new Assignment().openAssignmentFromCourseStream("113");//open the assignment

            //TC row no. 77....17. Submit as student 2...Discussion posted by student 1 for question1 should appear under Discussion tab of student2
            new SelectAnswerAndSubmit().staticAssignment("A");//submit 1st question
            new StaticAssignmentSubmit().navigateFromQuestionDropdown("1");//navigate question 1
            boolean isDiscussionPresentForStudent2 = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isDiscussionPresentForStudent2 == false)
                Assert.fail("Discussion added by student 1 on 1st question is not present under discussion tab for student 2, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"Student to Instructor\".");

            //TC row no. 78..Note posted by student 1 should not appear under Star tab of student2
            boolean isNotePresentForStudent2 = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", notesText);
            if(isNotePresentForStudent2 == true)
                Assert.fail("Note added by student 1 on 1st question is present under Star tab for student 2, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"Student to Instructor\".");


            //TC row no. 79..."17. Complete the assignment..18. Go to Course Stream"...Discussion posted by student1 should appear in Course Stream
            new Assignment().submitAssignmentAsStudent(113);//complete the assignment
            new Navigator().NavigateTo("Course Stream");
            boolean isPresent = new CourseStream().isPresentCourseStream("discussion");
            if(isPresent == false)
                Assert.fail("Discussion added by student 1 is absent Course Stream for student 2, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"Student to Instructor\".");

            //TC row no. 80, 81..//19. Click on the jump-out icon of that posted discussion....Discussion posted by student 1 should appear under Discussion tab of student2
            driver.findElement(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']")).click();//click on Jump out icon
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[title='Discussion']")));
            boolean isDiscussionPresentForStudent3 = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isDiscussionPresentForStudent3 == false)
                Assert.fail("On clicking jump out icon from Course Stream, the Discussion added by student 1 on 1st question is not present under discussion tab for student 2, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"Student to Instructor\".");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC submitAssignmentWithCollaborationEnabledWithStudentToInstructorSocialPolicy in class Policy", e);
        }
    }

    @Test(priority = 16,enabled = true)
    public void submitAssignmentWithCollaborationEnabledWithStudentToStudentSocialPolicy()
    {
        try
        {
            String policyname = ReadTestData.readDataByTagName("", "policyname", "114");

            new LoginUsingLTI().ltiLogin("114_1");//create a student1
            new LoginUsingLTI().ltiLogin("114_2");//create a student2

            new LoginUsingLTI().ltiLogin("114");
            new SetSocialPolicy().setSocialPolicy("114", "two"); //set Student to student social policy
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(policyname, "114 Policy description text", "1",null, false, "1", "", null, "", "", "");
            new Assignment().assignToStudent(114);

            new LoginUsingLTI().ltiLogin("114_1");//login as student1
            new Assignment().openAssignmentFromCourseStream("114");//open the assignment

            //TC row no. 114....9. Submit the first question..Student should navigate to next question
            new SelectAnswerAndSubmit().staticAssignment("A");//submit 1st question
            String qNo = driver.findElement(By.className("current-question-index")).getText();
            if(!qNo.contains("2"))
                Assert.fail("Student does not navigate to second question after submitting the 1st question of an assignment with policy allow collaboration enabled and social policy is set as \"student to student\".");

            //TC row no. 60 ... 10. Click on question dropdown to navigate the previous question..Discussion and Star tab should appear at the right side of the question preview page for first question
            new StaticAssignmentSubmit().navigateFromQuestionDropdown("1");//navigate question 1
            new Navigator().navigateToTab("Add to My Notes");//Star tab appears
            new Navigator().navigateToTab("Discussion");//discussion tab appears

            //TC row no. 61 ..11. Click on "+New Discussion" button..Visual indicator of social policy 2 should not appear in the Discussion text box
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='ls-toc-btn ls-right-new-btn']")));
            int visualIndicator = driver.findElements(By.className("social-policy-configuration")).size();
            System.out.println("visualIndicator: "+visualIndicator);
            if(visualIndicator > 2)//there will be 2 hidden icon and if another icon appears on discussion textbox then it will be more than 2
                Assert.fail("After clicking on +New Discussion the visual indicator is displayed on the popup of adding discussion.");

            //TC row no. 62....12. Add a discussion in Discussion tab through "+New Discussion" button...Posted Discussion should appear under Discussion and Star tab only
            new Navigator().navigateToTab("Discussion");//discussion tab appears
            String discussionText = new RandomString().randomstring(10);
            new Discussion().postDiscussion(discussionText);

            boolean isPresentInDiscussionTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isPresentInDiscussionTab == false)
                Assert.fail("Discussion is not present under discussion tab, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"student to student\".");

            boolean isPresentInStarTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", discussionText);
            if(isPresentInStarTab == false)
                Assert.fail("Discussion is not present under Star tab, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"student to student\".");

            boolean isPresentInAssignmentsTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Assignments", discussionText);
            if(isPresentInAssignmentsTab == true)
                Assert.fail("Discussion is present under Assignments tab, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"student to student\".");


            boolean isPresentInAboutTab = new Discussion().isDiscussionOrNotePresentUnderTabs("About", discussionText);
            if(isPresentInAboutTab == true)
                Assert.fail("Discussion is present under About tab, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"student to student\".");

            //TC row no. 63...13. Add a note in Star tab through "+New Note" button...Posted note should appear under Star tab only
            new Navigator().navigateToTab("Add to My Notes");//go to Star tab
            String notesText = new RandomString().randomstring(10);
            new Discussion().postNote(notesText);

            boolean isNotePresentInDiscussionTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", notesText);
            if(isNotePresentInDiscussionTab == true)
                Assert.fail("Notes is present under Discussion tab, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"student to student\".");

            boolean isNotePresentInStarTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", notesText);
            if(isNotePresentInStarTab == false)
                Assert.fail("Notes is not present under Star tab, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"student to student\".");

            boolean isNotePresentInAssignmentsTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Assignments", notesText);
            if(isNotePresentInAssignmentsTab == true)
                Assert.fail("notes is present under Assignments tab, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"student to student\".");

            boolean isNotePresentInAboutTab = new Discussion().isDiscussionOrNotePresentUnderTabs("About", notesText);
            if(isNotePresentInAboutTab == true)
                Assert.fail("Note is present under About tab, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"student to student\".");

            //TC row no. 64..""14. Attempt each question and Finish the assignment    15. Go to report page and click on question card for which the discussion and note were posted"
            //Posted discussion should be present under Discussion and Star tab
            new Assignment().submitAssignmentAsStudent(114);
            new QuestionCard().clickOnCard("", 0);//click on 1st question's question card
            boolean isDiscussionPresentInDiscussionTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isDiscussionPresentInDiscussionTab == false)
                Assert.fail("Added discussion is not present under discussion tab after navigating from question card, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"student to student\".");

            boolean isDiscussionPresentInStarTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", discussionText);
            if(isDiscussionPresentInStarTab == false)
                Assert.fail("Added discussion is not present under discussion tab after navigating from question card, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"student to student\".");

            //TC row no. 65...Posted note should be present under Star tab
            boolean isNotePresentInStarTab1 = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", notesText);
            if(isNotePresentInStarTab1 == false)
                Assert.fail("Added Note is not present under Star tab after navigating from question card, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"student to student\".");

            new LoginUsingLTI().ltiLogin("114_2"); //login as student 2
            new Assignment().openAssignmentFromCourseStream("114");//open the assignment

            //TC row no. 66....17. Submit as student 2...Discussion posted by student 1 for question1 should appear under Discussion tab of student2
            new SelectAnswerAndSubmit().staticAssignment("A");//submit 1st question
            new StaticAssignmentSubmit().navigateFromQuestionDropdown("1");//navigate question 1
            boolean isDiscussionPresentForStudent2 = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isDiscussionPresentForStudent2 == false)
                Assert.fail("Discussion added by student 1 on 1st question is not present under discussion tab for student 2, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"student to student\".");

            //TC row no. 67..Note posted by student 1 should not appear under Star tab of student2
            boolean isNotePresentForStudent2 = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", notesText);
            if(isNotePresentForStudent2 == true)
                Assert.fail("Note added by student 1 on 1st question is present under Star tab for student 2, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"student to student\".");


            //TC row no. 68..."18. Complete the assignment..19. Go to Course Stream"...Discussion posted by student1 should appear in Course Stream
            new Assignment().submitAssignmentAsStudent(114);//complete the assignment
            new Navigator().NavigateTo("Course Stream");
            boolean isPresent = new CourseStream().isPresentCourseStream("discussion");
            if(isPresent == false)
                Assert.fail("Discussion added by student 1 is absent Course Stream for student 2, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"student to student\".");

            //TC row no. 69, 70..//20. Click on the jump-out icon of that posted discussion....Discussion posted by student 1 should appear under Discussion tab of student2
            driver.findElement(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']")).click();//click on Jump out icon
            new WebDriverWait(driver, 360).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[title='Discussion']")));
            boolean isDiscussionPresentForStudent3 = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isDiscussionPresentForStudent3 == false)
                Assert.fail("On clicking jump out icon from Course Stream, the Discussion added by student 1 on 1st question is not present under discussion tab for student 2, for a question of an assignment with policy allow collaboration enabled and social policy is set as \"student to student\".");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC submitAssignmentWithCollaborationEnabledWithStudentToStudentSocialPolicy of class Policy.", e);
        }
    }

}

