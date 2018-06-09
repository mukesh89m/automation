package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.timeBasedAssignment;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyActivity.MyActivity;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Priyanka on 02-06-2016.
 */
public class InstructorSetATimeLimitOnAssignments extends Driver {
    WebDriver driver;
    AssignmentResponsesPage assignmentResponsesPage;
    AssessmentResponses assessmentResponses;
    CurrentAssignments currentAssignments;
    CourseStreamPage courseStream;
    NewAssignment newAssignment;
    AssignmentTab assignmentTab;
    QuestionBank questionBank;
    QuestionPage questionPage;
    Assignments assignments;
    LessonPage lessonPage;
    MyActivity myActivity;
    Dashboard dashBoard;
    Policies policies;
    String actual = "";
    String expected = "";

    @BeforeMethod
    public void inItElement() {
        driver = Driver.getWebDriver();
        assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
        assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);
        currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
        courseStream = PageFactory.initElements(driver, CourseStreamPage.class);
        newAssignment = PageFactory.initElements(driver, NewAssignment.class);
        assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
        questionPage = PageFactory.initElements(driver, QuestionPage.class);
        questionBank = PageFactory.initElements(driver, QuestionBank.class);
        assignments = PageFactory.initElements(driver, Assignments.class);
        lessonPage = PageFactory.initElements(driver, LessonPage.class);
        myActivity = PageFactory.initElements(driver, MyActivity.class);
        dashBoard = PageFactory.initElements(driver, Dashboard.class);
        policies = PageFactory.initElements(driver, Policies.class);

    }

    @Test(priority = 1, enabled = true)
    public void instructorAbleToS1etTimeLimitOnAssignments() {
        try {

            ReportUtil.log("Description", "Test case validates time limit option in policy page", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "1");

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "", "1");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1 successfully", "Pass");

            verificationOfTimeLimit();

            new Navigator().NavigateTo("My Assignments");//navigate to my assignments
            ReportUtil.log("Navigate to My Assignments", "Instructor navigated to My Assignments in successfully", "pass");

            questionBank.getQuestionBankTitle().click();//click on question bank
            selectPolicy();//select add new policy from assign popup
            new MouseHover().mouserhoverbywebelement(policies.policyName);//mouse hover the name field
            policies.editIcon_PolicyName.click(); //click on edit icon
            driver.switchTo().activeElement().sendKeys(assignmentpolicyname);
            new UIElement().waitAndFindElement(By.id("score"));
            driver.findElement(By.id("score")).sendKeys("1");
            policies.radioButton.get(0).click();//click on enable radio button
            policies.minuteTextBox.sendKeys("1");
            Thread.sleep(1000);
            List<WebElement> saves = policies.useThisPolicy;
            for(WebElement save :saves){
                if(save.isDisplayed()==true){
                    save.click();//click on Save Policy
                }
            }
            selectPolicy();//select add new policy from assign popup
            verificationOfTimeLimit();

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }


    @Test(priority = 2, enabled = true)
    public void policyWithExtendAfterDueDateOptionDisabled() {
        try {

            ReportUtil.log("Description", "Test case validates time limit icon", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "2");

            new Assignment().create(2);
            ReportUtil.log("Create assessment", "Created assessment with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("2_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("2");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");


            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "", "2");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1 successfully", "Pass");

            new Assignment().assignAssignmentWithDueDate(2);//assign to student
            ReportUtil.log("Instructor assign assignment", "Instructor assigned assignment to class section with due date", "Pass");


            //Current Assignment page
            new Navigator().NavigateTo("Class Assignments");
            ReportUtil.log("Navigate to Class Assignments", "Instructor navigated to Class Assignments successfully", "pass");
            verificationOfTimeIcon(0);

            //Assignment response page
            currentAssignments.getViewGrade_link().click();//click on view student response
            verificationOfTimeIcon(1);

            //e-textbook(Assignment tab)
            new TOCShow().chaptertree();//click on chapter tree
            new TOCShow().tocHide();//click on 'x' on lesson page
            new Navigator().navigateToTab("Assignments");
            ReportUtil.log("Navigate to Class Assignments tab", "Instructor navigated to Class Assignments tab successfully", "pass");

            actual = assignmentTab.timeAssignmentLabel.getText().trim();
            expected = "Timed Assignment: 6 minutes";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment label", "Time assignment label is displayed", "Time assignment label is not displayed");
            CustomAssert.assertEquals(assignmentTab.timerIcon.isDisplayed(), true, "Verify timer icon", "Timer icon is displayed", "Time icon is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in test case policyWithExtendAfterDueDateOptionDisabled in class InstructorSetATimeLimitOnAssignments", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void studentCanViewAndAttemptAssignment() {
        try {

            ReportUtil.log("Description", "Test case validates student can view time limit option and can attempt time assignment", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "3");

            new Assignment().create(3);
            ReportUtil.log("Create assessment", "Created assessment with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("3_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("3");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "", "3");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(3);//assign to student
            ReportUtil.log("Instructor assign assignment", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("3_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            ReportUtil.log("Navigate to Assignments", "Student navigated to Assignments page successfully", "pass");

            verificationOfTimeIcon(0);
            assignments.assignmentName.click();//click on assignment name
            VerificationOfTimeIconOnStudentSide();

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }


    @Test(priority = 4, enabled = true)
    public void studentAttemptAssignmentFromDashBoard() {
        try {

            ReportUtil.log("Description", "Test case validates student can attempt assignment from dashboard", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "4");

            new Assignment().create(4);
            ReportUtil.log("Create assessment", "Created assessment with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("4_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("4");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "", "4");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(4);//assign to student
            ReportUtil.log("Instructor assign assignment", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("4_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");
            WebDriverUtil.clickOnElementUsingJavascript(dashBoard.upcoming_assignment);//click on assignment
            VerificationOfTimeIconOnStudentSide();

        } catch (Exception e) {
            Assert.fail("Exception in test case studentAttemptAssignmentFromDashBoard in class InstructorSetATimeLimitOnAssignments", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void studentAttemptAssignmentFromCourseStream() {
        try {

            ReportUtil.log("Description", "Test case validates student can attempt assignment from course stream page", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "5");

            new Assignment().create(5);
            ReportUtil.log("Create assessment", "Created assessment with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("5_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("5");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "", "5");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(5);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("5_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "Instructor navigated to Course Stream page successfully", "pass");
            WebDriverUtil.clickOnElementUsingJavascript(courseStream.assessmentName);//click on assignment name
            VerificationOfTimeIconOnStudentSide();

        } catch (Exception e) {
            Assert.fail("Exception in test case studentAttemptAssignmentFromCourseStream in class InstructorSetATimeLimitOnAssignments", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void studentAttemptAssignmentFromETextBook() {
        try {

            ReportUtil.log("Description", "Test case validates student can attempt assignment from e-textbook", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "6");

            new Assignment().create(6);
            ReportUtil.log("Create assessment", "Created assessment with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("6_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("6");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "", "6");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(6);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("6_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            new TOCShow().chaptertree();//click on chapter tree
            new TOCShow().tocHide();//click on 'x' on lesson page
            WebDriverUtil.clickOnElementUsingJavascript(lessonPage.TOCassignmentName);
            VerificationOfTimeIconOnStudentSide();

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }

    @Test(priority = 7, enabled = true)
    public void studentAttemptAssignmentFromAssignmentTab() {
        try {

            ReportUtil.log("Description", "Test case validates student can attempt assignment from assignment tab", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "7");

            new Assignment().create(7);
            ReportUtil.log("Create assessment", "Created assessment with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("7_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("7");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "", "7");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(7);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("7_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            openAssignmentFromAssignmentTab();
            actual = assignments.popUpMessage.getText();
            expected ="Timed Assignment\nOnce you begin, you will have 6 minutes to complete this assignment.\nIf you log out, or navigate away from this assignment, the timer will continue to run.\nWould you like to start the assignment now?\nYes\nNo";
            CustomAssert.assertEquals(actual,expected,"Verify popup message","popup message is as per expected","popup message is not as per expected");
            CustomAssert.assertEquals(assignments.closeIconOnPopUp.isDisplayed(),true,"Verify popup close icon","close icon is displayed","close icon is not displayed");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_noLink);//click on no
            assignmentTab.open_button.click();
            assignments.timeAssignment_yesLink.click();//click on yes
            CustomAssert.assertEquals(questionPage.timeRemaining.get(1).getText(),"Time Remaining","Verify time remaining text","Time remaining text is available","Time remaining text is not available");
            CustomAssert.assertTrue(assignments.button_close.isDisplayed(),"Verify close icon","Close icon is not available","close icon is available");
            new MouseHover().mouserhoverbywebelement(questionPage.timedAssignmentTimer);
            CustomAssert.assertEquals(questionPage.timedAssignmentTimer.getAttribute("title"),"Hide Timer","Verify hide time","Hide timer option is displayed","Hide timer option is not displayed");
            WebDriverUtil.clickOnElementUsingJavascript(questionPage.timedAssignmentTimer);//click on hide timer option
            CustomAssert.assertEquals(questionPage.timedAssignmentTimer.getAttribute("title"),"Show Timer","Verify time","timer is hidden","timer is not hidden");
            WebDriverUtil.clickOnElementUsingJavascript(questionPage.timedAssignmentTimer);//click on show timer option
            driver.findElement(By.xpath("/html/body")).click();//click on body
            CustomAssert.assertEquals(questionPage.timeRemaining.get(1).getText(),"Time Remaining","Verify time remaining text","Time remaining text is available","Time remaining text is not available");
            Thread.sleep(60000);
            CustomAssert.assertEquals(questionPage.timeWarning.getCssValue("color"),"rgba(255, 0, 0, 1)","Verify remaining time color","Remaining time color is red","Remaining time color is red");
            new AttemptQuestion().trueFalse(false,"correct",3);
            new Assignment().submitButtonInQuestionClick();

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }


    @Test(priority = 8, enabled = true)
    public void switchingTabFromAssignmentTabToLessonTab() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "8");

            new Assignment().create(8);
            ReportUtil.log("Create assessment", "Created assessment with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("8_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("8");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "", "8");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(8);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("8_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");
            new TOCShow().chaptertree();//click on chapter tree
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments");   //go to Assignments tab
            WebDriverUtil.clickOnElementUsingJavascript(assignmentTab.rightArrow);//click on'>'
            WebDriverUtil.waitTillVisibilityOfElement(assignmentTab.open_button, 60);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentTab.open_button);//click on open
            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink);//click on yes

            WebDriverUtil.clickOnElementUsingJavascript(lessonPage.getIntroductionTab());//click on lesson tab
            actual = lessonPage.roboNotificationMessage.getText().trim();
            expected = "You are about to leave this assignment. If you do, you can come back, but the timer will continue to run while you are away.\n\nAre you sure you want to leave this assignment?\nYes\nNo";
            CustomAssert.assertEquals(actual, expected, "Verify robo pop up", "Robo pop up is displayed", "Robo pop up is not displayed");
            CustomAssert.assertTrue(lessonPage.closeIconOfRoboNotificationMessage.isDisplayed(),"Verify close icon","close icon is displayed","close icon is not displayed");
            lessonPage.closeIconOfRoboNotificationMessage.click();//click on close on robo pop up

            lessonPage.getIntroductionTab().click();//click on lesson tab
            lessonPage.yesNo.get(1).click();//click on no

            lessonPage.quitPractice_Test.click();//click on quit
            Thread.sleep(1000);
            actual = lessonPage.roboNotificationMessage.getText().trim();
            expected = "You are about to leave this assignment. If you do, you can come back, but the timer will continue to run while you are away.\n\nAre you sure you want to leave this assignment?\nYes\nNo";
            CustomAssert.assertEquals(actual, expected, "Verify robo pop up", "Robo pop up is displayed", "Robo pop up is not displayed");

            lessonPage.closeAssignment.click();//click on close Assignment icon
            actual = lessonPage.roboNotificationMessage.getText().trim();
            expected = "You are about to leave this assignment. If you do, you can come back, but the timer will continue to run while you are away.\n\nAre you sure you want to leave this assignment?\nYes\nNo";
            CustomAssert.assertEquals(actual, expected, "Verify robo pop up", "Robo pop up is displayed", "Robo pop up is not displayed");

            lessonPage.yesLinkOnRobo.click();//click on yes
            CustomAssert.assertEquals(lessonPage.introduction_Text.getText(),"INTRODUCTION", "Verify lesson tab", "Student navigated to the lesson tab", "Student not navigated to the lesson tab");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }


    @Test(priority = 9, enabled = true)
    public void navigateToOtherSectionWhileAttemptingTimeAssignment() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "9");

            new Assignment().create(9);
            new Assignment().addQuestions(9, "truefalse", "");
            ReportUtil.log("Create assessment", "Asessment created with 2 true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("9_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("9");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "", "8");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(9);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("9_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            assignments.assignmentName.click();//click on assignment name
            assignments.timeAssignment_yesLink.click();//click on yes
            new AttemptQuestion().trueFalse(false,"correct",9);
            new Assignment().nextButtonInQuestionClick();//click on Next*/
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            assignments.assignmentName.click();//click on assignment name
            Thread.sleep(2000);
            String timeLeft=assignments.time.getAttribute("timetaken").trim();
            System.out.println(timeLeft);
            if(timeLeft.equals("0")){
                CustomAssert.fail("Verify remaining time"," Timer of the assignment still be not running with time added for navigating to other areas of application");
            }
            new AttemptQuestion().trueFalse(false,"correct",9);
            new Assignment().submitButtonInQuestionClick();
            ReportUtil.log("Student submit assignment", "Student submitted assignment successfully", "pass");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void notificationWhenTimeLimitExhaustsWhileInProgress() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "10");

            new Assignment().create(10);
            new Assignment().addQuestions(10, "truefalse", "");
            ReportUtil.log("Create assessment", "Asessment created with 2 true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("10_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("10");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "1", "", "Auto-release on assignment submission", "", "", "", "10");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(10);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("10_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            assignments.assignmentName.click();//click on assignment name
            assignments.timeAssignment_yesLink.click();//click on yes
            new AttemptQuestion().trueFalse(false,"correct",10);
            new Assignment().nextButtonInQuestionClick();//click on Next
            Thread.sleep(50000);
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");
            assignments.closeDueDate.click();//click on close

            assignments.assignmentName.click();//click on assignment name
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");
            assignments.closeDueDate.click();//click on close

            new Navigator().NavigateTo("Dashboard");//navigate to Dashboard
            dashBoard.upcoming_assignment.click();//click on assignment
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");
            assignments.closeDueDate.click();//click on close

            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            WebDriverUtil.clickOnElementUsingJavascript(courseStream.assessmentName);//click on assignment name
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.closeDueDate);//click on close

            new Navigator().NavigateTo("e-Textbook");//click on chapter tree
            WebDriverUtil.clickOnElementUsingJavascript(lessonPage.TOCassignmentName);
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.closeDueDate);//click on close

            new Navigator().NavigateTo("My Activity");//navigate to My Activity
            WebDriverUtil.clickOnElementUsingJavascript(myActivity.assessmentLink);
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.closeDueDate);//click on close

            new Navigator().NavigateTo("e-Textbook");//click on chapter tree
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments");   //go to Assignments tab
            WebDriverUtil.clickOnElementUsingJavascript(assignmentTab.rightArrow);
            WebDriverUtil.waitTillVisibilityOfElement(assignmentTab.open_button, 60);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentTab.open_button);
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.closeDueDate);//click on close

            new LoginUsingLTI().ltiLogin("10");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");

            currentAssignments.getViewGrade_link().click();//click on view student response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.timeExhaustedIcon);
            CustomAssert.assertEquals(assignmentResponsesPage.timeExhaustedIcon.getAttribute("title"),"Time limit exhausted","Verify Time limit exhausted icon","Time Exhausted icon is displayed","Time Exhausted icon is not displayed");
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            assignmentResponsesPage.updateTime.get(0).sendKeys("4");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            CustomAssert.assertEquals(assignmentResponsesPage.assignedTimeLimit.get(0).getText(), "Assigned time limit : 4 mins", "Verify updated time", "Time is updated", "Time is not updated");
            assignmentResponsesPage.updateTime.get(0).sendKeys("5");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button

            new LoginUsingLTI().ltiLogin("10_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            assignments.assignmentName.click();//click on assignment name
            assignments.timeAssignment_yesLink.click();//click on yes
            new AttemptQuestion().trueFalse(false,"correct",10);
            new Assignment().submitButtonInQuestionClick();
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getGoToNextLinkOnPopUp());

            new LoginUsingLTI().ltiLogin("10");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on view grades
            CustomAssert.assertTrue(assignmentResponsesPage.tickMark.isDisplayed(),"Verify green colour tick mark before student name", "Tick mark is displayed", "Tick mark is not displayed");


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }


    @Test(priority = 11, enabled = true)
    public void studentHasNotStartedTheAssignmentPolicyOne() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "11");

            new Assignment().create(11);
            ReportUtil.log("Create assessment", "Assessment Created  with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("11_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("11");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "", "11");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(11);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            currentAssignments.getViewGrade_link().click();//click on view student response
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected ="family, givenname\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 5 mins\n" +
                    "Change total time to mins\n" +
                    "Update Time";
            CustomAssert.assertEquals(actual, expected, "Verify student's time spent card", "All the details are displaying correct", "All the details are displaying correct");
            assignmentResponsesPage.updateTime.get(0).sendKeys("4");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button
            CustomAssert.assertEquals(assignmentResponsesPage.updateTimeError.get(0).getText(), "The minimum should be 6 minutes.", "Verify error message", "Error message is displayed", "Error message is not displayed");
            assignmentResponsesPage.updateTime.get(0).clear();
            assignmentResponsesPage.updateTime.get(0).sendKeys("5");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button
            CustomAssert.assertEquals(assignmentResponsesPage.updateTimeError.get(0).getText(), "The minimum should be 6 minutes.", "Verify error message", "Error message is displayed", "Error message is not displayed");
            assignmentResponsesPage.updateTime.get(0).clear();
            assignmentResponsesPage.updateTime.get(0).sendKeys("6");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            CustomAssert.assertEquals(assignmentResponsesPage.assignedTimeLimit.get(0).getText(), "Assigned time limit : 6 mins", "Verify updated time", "Time is updated", "Time is not updated");
            assignmentResponsesPage.updateTime.get(0).sendKeys("301");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button
            CustomAssert.assertEquals(assignmentResponsesPage.updateTimeError.get(0).getText(), "The maximum should be 300 minutes.", "Verify error message", "Error message is displayed", "Error message is not displayed");
            Thread.sleep(240000);
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on view grades
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(0),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected ="family, givenname\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 6 mins\n" +
                    "Enter Grade";
            CustomAssert.assertEquals(actual, expected, "Verify student's time spent card", "All the details are displaying correct", "All the details are displaying correct");
            assignmentResponsesPage.getEnterGrade().click();//click on enter grade
            assignmentResponsesPage.getGradeBox().get(0).sendKeys("2");
            driver.findElement(By.xpath("/html/body")).click();

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }


    @Test(priority = 12, enabled = true)
    public void studentIsInProgressAndSubmittedPolicyOne() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "12");

            new Assignment().create(12);
            ReportUtil.log("Create assessment", "Assessment Created  with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("12_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("12");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "", "12");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(12);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("12_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            assignments.assignmentName.click();//click on assignment name
            assignments.timeAssignment_yesLink.click();//click on yes
            new AttemptQuestion().trueFalse(false,"correct",12);

            new LoginUsingLTI().ltiLogin("12");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student response
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(0),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected ="family, givenname\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 5 mins\n" +
                    "Change total time to mins\n" +
                    "Update Time";
            CustomAssert.assertEquals(actual,expected,"Verify student's time spent card","All the details are displaying correct","All the details are displaying correct");
            assignmentResponsesPage.updateTime.get(0).sendKeys("6");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button
            CustomAssert.assertEquals(assignmentResponsesPage.updateTimeError.get(0).getText(),"Assignment in use","Verify error message","Error message is displayed","Error message is not displayed");
            assignmentResponsesPage.updateTime.get(0).clear();
            assignmentResponsesPage.updateTime.get(0).sendKeys("4");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button
            CustomAssert.assertEquals(assignmentResponsesPage.updateTimeError.get(0).getText(),"The minimum should be 6 minutes.","Verify error message","Error message is displayed","Error message is not displayed");

            new LoginUsingLTI().ltiLogin("12_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            assignments.assignmentName.click();//click on assignment name
            new AttemptQuestion().trueFalse(false,"correct",12);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("12");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on view grades
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            actual = assignmentResponsesPage.timeSpent.getText();
            if(!(actual.equals("Time spent : 0 min(s) 18 sec(s)")||actual.equals("Time spent : 0 min(s) 19 sec(s)")||actual.equals("Time spent : 0 min(s) 20 sec(s)")||actual.equals("Time spent : 0 min(s) 21 sec(s)"))) {
                CustomAssert.fail("Verify time spent", "Time spent is not equal to taken time");
            }
        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }


    @Test(priority = 13, enabled = true)
       public void studentHasNotStartedAssignmentPolicyTwo() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "13");

            new Assignment().create(13);
            ReportUtil.log("Create assessment", "Assessment Created  with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("13_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("13");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "1", "", "Auto-release on due date", "", "", "", "13");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 2", "Pass");

            new Assignment().assignAssignmentWithDueDate(13);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            currentAssignments.getViewGrade_link().click();//click on view student response
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(0),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected ="family, givenname\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 5 mins\n" +
                    "Change total time to mins\n" +
                    "Update Time";
            CustomAssert.assertEquals(actual, expected, "Verify student's time spent card", "All the details are displaying correct", "All the details are displaying correct");
            assignmentResponsesPage.updateTime.get(0).sendKeys("6");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            CustomAssert.assertEquals(assignmentResponsesPage.assignedTimeLimit.get(0).getText(), "Assigned time limit : 6 mins", "Verify updated time", "Time is updated", "Time is not updated");
            Thread.sleep(240000);
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on view grades
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(0),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected ="family, givenname\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 6 mins\n" +
                    "Enter Grade";
            CustomAssert.assertEquals(actual, expected, "Verify student's time spent card", "All the details are displaying correct", "All the details are displaying correct");
            assignmentResponsesPage.getEnterGrade().click();//click on enter grade
            assignmentResponsesPage.getGradeBox().get(0).sendKeys("2");
            driver.findElement(By.xpath("/html/body")).click();

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }


    @Test(priority = 14, enabled = true)
    public void studentIsInProgressAndSubmittedPolicyTwo() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "14");

            new Assignment().create(14);
            ReportUtil.log("Create assessment", "Assessment Created  with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("14_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("14");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "1", "", "Auto-release on due date", "", "", "", "14");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(14);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("14_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            WebDriverUtil.clickOnElementUsingJavascript(assignments.assignmentName);//click on assignment name
            assignments.timeAssignment_yesLink.click();//click on yes
            new AttemptQuestion().trueFalse(false,"correct",14);

            new LoginUsingLTI().ltiLogin("14");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student response
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected ="family, givenname\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 5 mins\n" +
                    "Change total time to mins\n" +
                    "Update Time";
            CustomAssert.assertEquals(actual,expected,"Verify student's time spent card","All the details are displaying correct","All the details are displaying correct");
            assignmentResponsesPage.updateTime.get(0).sendKeys("6");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button
            CustomAssert.assertEquals(assignmentResponsesPage.updateTimeError.get(0).getText(),"Assignment in use","Verify error message","Error message is displayed","Error message is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }

    @Test(priority = 15, enabled = true)
    public void dueDateIsElapsedPolicyTwo() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "15");
            String familyname = ReadTestData.readDataByTagName("", "familyname", "15_1");
            String givenname = ReadTestData.readDataByTagName("", "givenname", "15_1");

            new Assignment().create(15);
            ReportUtil.log("Create assessment", "Assessment Created  with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("15_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");
            new LoginUsingLTI().ltiLogin("15_2");//log in as student2
            ReportUtil.log("Student2 login", "Student2 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("15");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "1", "", "Auto-release on due date", "", "", "", "15");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(15);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            Thread.sleep(120000);//wait for due date to expair

            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student response
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(1), false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(1));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected =familyname+", "+givenname+"\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 5 mins\n" +
                    "Change total time to mins\n" +
                    "Update Time";
            CustomAssert.assertEquals(actual,expected,"Verify student's time spent card","All the details are displaying correct","All the details are displaying correct");
            assignmentResponsesPage.updateTime.get(1).sendKeys("6");
            assignmentResponsesPage.updateTime_button.get(1).click();//click on update button
            extendDueDate(0,"15_1");//extend due date for student1
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(0), false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected ="family, givenname\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 5 mins\n" +
                    "Change total time to mins\n" +
                    "Update Time";
            CustomAssert.assertEquals(actual,expected,"Verify student's time spent card","All the details are displaying correct","All the details are displaying correct");
            CustomAssert.assertEquals(assignmentResponsesPage.assignedTimeLimit.get(0).getText(), "Assigned time limit : 5 mins", "Verify old time", "Time is not updated", "Time is  updated");

            new LoginUsingLTI().ltiLogin("15_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            assignments.assignmentName.click();//click on assignment name
            assignments.timeAssignment_yesLink.click();//click on yes
            Thread.sleep(1000);

            new LoginUsingLTI().ltiLogin("15");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student response
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(1), false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(1));

            assignmentResponsesPage.updateTime.get(1).sendKeys("7");
            assignmentResponsesPage.updateTime_button.get(1).click();//click on update button
            CustomAssert.assertEquals(assignmentResponsesPage.updateTimeError.get(1).getText(), "Assignment in use", "Verify error message", "Error message is displayed", "Error message is not displayed");
            extendDueDate(0, "15_1");
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(1), false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(1));

            assignmentResponsesPage.updateTime.get(1).sendKeys("8");
            assignmentResponsesPage.updateTime_button.get(1).click();//click on update button
            CustomAssert.assertEquals(assignmentResponsesPage.updateTimeError.get(1).getText(),"Assignment in use","Verify error message","Error message is displayed","Error message is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }


    @Test(priority = 16, enabled = true)
    public void timeLimitExhaustsByStudentWithPolicyTwo() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "16");

            new Assignment().create(16);
            new Assignment().addQuestions(16, "truefalse", "");
            ReportUtil.log("Create assessment", "Assessment Created  with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("16_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("16");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "1", "", "Auto-release on due date", "", "", "", "16");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(16);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("16_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            assignments.assignmentName.click();//click on assignment name
            assignments.timeAssignment_yesLink.click();//click on yes
            new AttemptQuestion().trueFalse(false,"correct",16);
            new Assignment().nextButtonInQuestionClick();//click on Next
            Thread.sleep(50000);
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");
            assignments.closeDueDate.click();//click on close

            new LoginUsingLTI().ltiLogin("16");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");

            currentAssignments.getViewGrade_link().click();//click on view student response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.timeExhaustedIcon);
            CustomAssert.assertEquals(assignmentResponsesPage.timeExhaustedIcon.getAttribute("title"),"Time limit exhausted","Verify Time limit exhausted icon","Time Exhausted icon is displayed","Time Exhausted icon is not displayed");
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            assignmentResponsesPage.updateTime.get(0).sendKeys("4");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            CustomAssert.assertEquals(assignmentResponsesPage.assignedTimeLimit.get(0).getText(), "Assigned time limit : 4 mins", "Verify updated time", "Time is updated", "Time is not updated");
            assignmentResponsesPage.updateTime.get(0).sendKeys("5");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button

            new LoginUsingLTI().ltiLogin("16_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            assignments.assignmentName.click();//click on assignment name
            assignments.timeAssignment_yesLink.click();//click on yes
            new AttemptQuestion().trueFalse(false,"correct",16);
            new Assignment().submitButtonInQuestionClick();
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getGoToNextLinkOnPopUp());

            new LoginUsingLTI().ltiLogin("16");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on view grades
            CustomAssert.assertTrue(assignmentResponsesPage.tickMark.isDisplayed(),"Verify green colour tick mark before student name", "Tick mark is displayed", "Tick mark is not displayed");


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }


    @Test(priority = 17, enabled = true)
    public void timeLimitAndDueDateExhaustsWithPolicyTwo() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "17");

            new Assignment().create(17);
            new Assignment().addQuestions(17, "truefalse", "");
            ReportUtil.log("Create assessment", "Assessment Created  with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("17_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("17");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "1", "", "Auto-release on due date", "", "", "", "17");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(17);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("17_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            assignments.assignmentName.click();//click on assignment name
            assignments.timeAssignment_yesLink.click();//click on yes
            new AttemptQuestion().trueFalse(false,"correct",17);
            new Assignment().nextButtonInQuestionClick();//click on Next
            Thread.sleep(120000);//wait till time limit and due time to exhaust
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");
            assignments.closeDueDate.click();//click on close

            new LoginUsingLTI().ltiLogin("17");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");

            currentAssignments.getViewGrade_link().click();//click on view student response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.timeExhaustedIcon);
            CustomAssert.assertEquals(assignmentResponsesPage.timeExhaustedIcon.getAttribute("title"),"Time limit exhausted","Verify Time limit exhausted icon","Time Exhausted icon is displayed","Time Exhausted icon is not displayed");
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            assignmentResponsesPage.updateTime.get(0).sendKeys("4");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            CustomAssert.assertEquals(assignmentResponsesPage.assignedTimeLimit.get(0).getText(), "Assigned time limit : 4 mins", "Verify updated time", "Time is updated", "Time is not updated");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }



    @Test(priority = 18, enabled = true)
    public void studentHasNotStartedAssignmentPolicyThree() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "18");

            new Assignment().create(18);
            ReportUtil.log("Create assessment", "Assessment Created  with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("18_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("18");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "1", "", "Release as they are being graded", "", "", "", "18");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 2", "Pass");

            new Assignment().assignAssignmentWithDueDate(18);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            currentAssignments.getViewGrade_link().click();//click on view student response
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(0),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected ="family, givenname\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 5 mins\n" +
                    "Change total time to mins\n" +
                    "Update Time";
            CustomAssert.assertEquals(actual, expected, "Verify student's time spent card", "All the details are displaying correct", "All the details are displaying correct");
            assignmentResponsesPage.updateTime.get(0).sendKeys("6");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            CustomAssert.assertEquals(assignmentResponsesPage.assignedTimeLimit.get(0).getText(), "Assigned time limit : 6 mins", "Verify updated time", "Time is updated", "Time is not updated");
            Thread.sleep(240000);
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student response
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on view grades
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(0),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected ="family, givenname\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 6 mins\n" +
                    "Enter Grade";
            CustomAssert.assertEquals(actual, expected, "Verify student's time spent card", "All the details are displaying correct", "All the details are displaying correct");
            assignmentResponsesPage.getEnterGrade().click();//click on enter grade
            assignmentResponsesPage.getGradeBox().get(0).sendKeys("2");
            driver.findElement(By.xpath("/html/body")).click();

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }


    @Test(priority = 19, enabled = true)
    public void studentHasNotStartedAssignmentPolicyFour() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "19");

            new Assignment().create(19);
            ReportUtil.log("Create assessment", "Assessment Created  with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("19_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("19");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "1", "", "Release explicitly by the instructor", "", "", "", "19");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 2", "Pass");

            new Assignment().assignAssignmentWithDueDate(19);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            currentAssignments.getViewGrade_link().click();//click on view student response
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(0),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected ="family, givenname\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 5 mins\n" +
                    "Change total time to mins\n" +
                    "Update Time";
            CustomAssert.assertEquals(actual, expected, "Verify student's time spent card", "All the details are displaying correct", "All the details are displaying correct");
            assignmentResponsesPage.updateTime.get(0).sendKeys("6");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            CustomAssert.assertEquals(assignmentResponsesPage.assignedTimeLimit.get(0).getText(), "Assigned time limit : 6 mins", "Verify updated time", "Time is updated", "Time is not updated");
            Thread.sleep(240000);
            new Navigator().NavigateTo("Class Assignments");
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on view grades
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(0),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected ="family, givenname\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 6 mins\n" +
                    "Enter Grade";
            CustomAssert.assertEquals(actual, expected, "Verify student's time spent card", "All the details are displaying correct", "All the details are displaying correct");
            assignmentResponsesPage.getEnterGrade().click();//click on enter grade
            assignmentResponsesPage.getGradeBox().get(0).sendKeys("2");
            driver.findElement(By.xpath("/html/body")).click();

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }

    @Test(priority = 20, enabled = true)
    public void studentIsInProgressAndSubmittedPolicyThree() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "20");

            new Assignment().create(20);
            ReportUtil.log("Create assessment", "Assessment Created  with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("20_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("20");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "1", "", "Release as they are being graded", "", "", "", "20");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(20);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("20_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            WebDriverUtil.clickOnElementUsingJavascript(assignments.assignmentName);//click on assignment name
            assignments.timeAssignment_yesLink.click();//click on yes
            new AttemptQuestion().trueFalse(false,"correct",20);

            new LoginUsingLTI().ltiLogin("20");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student response
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected ="family, givenname\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 5 mins\n" +
                    "Change total time to mins\n" +
                    "Update Time";
            CustomAssert.assertEquals(actual,expected,"Verify student's time spent card","All the details are displaying correct","All the details are displaying correct");
            assignmentResponsesPage.updateTime.get(0).sendKeys("6");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button
            CustomAssert.assertEquals(assignmentResponsesPage.updateTimeError.get(0).getText(),"Assignment in use","Verify error message","Error message is displayed","Error message is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }


    @Test(priority = 21, enabled = true)
    public void studentIsInProgressAndSubmittedPolicyFour() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "21");

            new Assignment().create(21);
            ReportUtil.log("Create assessment", "Assessment Created  with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("21_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("21");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "1", "", "Release explicitly by the instructor", "", "", "", "21");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(21);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("21_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            assignments.assignmentName.click();//click on assignment name
            assignments.timeAssignment_yesLink.click();//click on yes
            new AttemptQuestion().trueFalse(false,"correct",21);

            new LoginUsingLTI().ltiLogin("21");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student response
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected ="family, givenname\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 5 mins\n" +
                    "Change total time to mins\n" +
                    "Update Time";
            CustomAssert.assertEquals(actual,expected,"Verify student's time spent card","All the details are displaying correct","All the details are displaying correct");
            assignmentResponsesPage.updateTime.get(0).sendKeys("6");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button
            CustomAssert.assertEquals(assignmentResponsesPage.updateTimeError.get(0).getText(),"Assignment in use","Verify error message","Error message is displayed","Error message is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }


    @Test(priority = 22, enabled = true)
    public void timeLimitExhaustsByStudentWithPolicyFour() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "22");

            new Assignment().create(22);
            new Assignment().addQuestions(22, "truefalse", "");
            ReportUtil.log("Create assessment", "Assessment Created  with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("22_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("22");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "1", "", "Release explicitly by the instructor", "", "", "", "22");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(22);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("22_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            assignments.assignmentName.click();//click on assignment name
            assignments.timeAssignment_yesLink.click();//click on yes
            new AttemptQuestion().trueFalse(false,"correct",22);
            new Assignment().nextButtonInQuestionClick();//click on Next
            Thread.sleep(50000);
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");
            assignments.closeDueDate.click();//click on close

            new LoginUsingLTI().ltiLogin("22");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");

            currentAssignments.getViewGrade_link().click();//click on view student response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.timeExhaustedIcon);
            CustomAssert.assertEquals(assignmentResponsesPage.timeExhaustedIcon.getAttribute("title"),"Time limit exhausted","Verify Time limit exhausted icon","Time Exhausted icon is displayed","Time Exhausted icon is not displayed");
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            assignmentResponsesPage.updateTime.get(0).sendKeys("4");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            CustomAssert.assertEquals(assignmentResponsesPage.assignedTimeLimit.get(0).getText(), "Assigned time limit : 4 mins", "Verify updated time", "Time is updated", "Time is not updated");
            assignmentResponsesPage.updateTime.get(0).sendKeys("5");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button

            new LoginUsingLTI().ltiLogin("22_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            assignments.assignmentName.click();//click on assignment name
            assignments.timeAssignment_yesLink.click();//click on yes
            new AttemptQuestion().trueFalse(false,"correct",22);
            new Assignment().submitButtonInQuestionClick();
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getGoToNextLinkOnPopUp());

            new LoginUsingLTI().ltiLogin("22");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on view grades
            CustomAssert.assertTrue(assignmentResponsesPage.tickMark.isDisplayed(),"Verify green colour tick mark before student name", "Tick mark is displayed", "Tick mark is not displayed");


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }

    @Test(priority = 23, enabled = true)
    public void dueDateIsElapsedPolicyOne() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "23");
            String familyname = ReadTestData.readDataByTagName("", "familyname", "23_1");
            String givenname = ReadTestData.readDataByTagName("", "givenname", "23_1");

            new Assignment().create(23);
            ReportUtil.log("Create assessment", "Assessment Created  with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("23_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");
            new LoginUsingLTI().ltiLogin("23_2");//log in as student2
            ReportUtil.log("Student2 login", "Student2 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("23");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "1", "", "Auto-release on assignment submission", "", "", "", "23");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(23);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            Thread.sleep(120000);//wait for due date to expair

            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student response
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(1),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(1));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected =familyname+", "+givenname+"\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 5 mins\n" +
                    "Change total time to mins\n" +
                    "Update Time";
            CustomAssert.assertEquals(actual,expected,"Verify student's time spent card","All the details are displaying correct","All the details are displaying correct");
            assignmentResponsesPage.updateTime.get(1).sendKeys("6");
            assignmentResponsesPage.updateTime_button.get(1).click();//click on update button
            extendDueDate(0,"23_1");//extend due date for student1
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(0),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected ="family, givenname\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 5 mins\n" +
                    "Change total time to mins\n" +
                    "Update Time";
            CustomAssert.assertEquals(actual,expected,"Verify student's time spent card","All the details are displaying correct","All the details are displaying correct");
            CustomAssert.assertEquals(assignmentResponsesPage.assignedTimeLimit.get(0).getText(), "Assigned time limit : 5 mins", "Verify old time", "Time is not updated", "Time is  updated");

            new LoginUsingLTI().ltiLogin("23_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            assignments.assignmentName.click();//click on assignment name
            assignments.timeAssignment_yesLink.click();//click on yes
            Thread.sleep(1000);

            new LoginUsingLTI().ltiLogin("23");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on view student response
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(1),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(1));

            assignmentResponsesPage.updateTime.get(1).sendKeys("7");
            assignmentResponsesPage.updateTime_button.get(1).click();//click on update button
            CustomAssert.assertEquals(assignmentResponsesPage.updateTimeError.get(1).getText(), "Assignment in use", "Verify error message", "Error message is displayed", "Error message is not displayed");
            extendDueDate(0, "23_1");
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(1),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(1));

            assignmentResponsesPage.updateTime.get(1).sendKeys("8");
            assignmentResponsesPage.updateTime_button.get(1).click();//click on update button
            CustomAssert.assertEquals(assignmentResponsesPage.updateTimeError.get(1).getText(),"Assignment in use","Verify error message","Error message is displayed","Error message is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }


    @Test(priority = 24, enabled = true)
    public void dueDateIsElapsedPolicyThree() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "24");
            String familyname = ReadTestData.readDataByTagName("", "familyname", "24_1");
            String givenname = ReadTestData.readDataByTagName("", "givenname", "24_1");

            new Assignment().create(24);
            ReportUtil.log("Create assessment", "Assessment Created  with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("24_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");
            new LoginUsingLTI().ltiLogin("24_2");//log in as student2
            ReportUtil.log("Student2 login", "Student2 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("24");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "1", "", "Release as they are being graded", "", "", "", "24");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(24);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            Thread.sleep(120000);//wait for due date to expair

            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student response
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(1),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(1));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected =familyname+", "+givenname+"\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 5 mins\n" +
                    "Change total time to mins\n" +
                    "Update Time";
            CustomAssert.assertEquals(actual,expected,"Verify student's time spent card","All the details are displaying correct","All the details are displaying correct");
            assignmentResponsesPage.updateTime.get(1).sendKeys("6");
            assignmentResponsesPage.updateTime_button.get(1).click();//click on update button
            extendDueDate(0,"24_1");//extend due date for student1
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(0),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected ="family, givenname\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 5 mins\n" +
                    "Change total time to mins\n" +
                    "Update Time";
            CustomAssert.assertEquals(actual,expected,"Verify student's time spent card","All the details are displaying correct","All the details are displaying correct");
            CustomAssert.assertEquals(assignmentResponsesPage.assignedTimeLimit.get(0).getText(), "Assigned time limit : 5 mins", "Verify old time", "Time is not updated", "Time is  updated");

            new LoginUsingLTI().ltiLogin("24_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            assignments.assignmentName.click();//click on assignment name
            assignments.timeAssignment_yesLink.click();//click on yes
            Thread.sleep(1000);

            new LoginUsingLTI().ltiLogin("24");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student response
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(1),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(1));

            assignmentResponsesPage.updateTime.get(1).sendKeys("7");
            assignmentResponsesPage.updateTime_button.get(1).click();//click on update button
            CustomAssert.assertEquals(assignmentResponsesPage.updateTimeError.get(1).getText(), "Assignment in use", "Verify error message", "Error message is displayed", "Error message is not displayed");
            extendDueDate(0, "24_1");
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(1),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(1));

            assignmentResponsesPage.updateTime.get(1).sendKeys("8");
            assignmentResponsesPage.updateTime_button.get(1).click();//click on update button
            CustomAssert.assertEquals(assignmentResponsesPage.updateTimeError.get(1).getText(),"Assignment in use","Verify error message","Error message is displayed","Error message is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in test case dueDateIsElapsedPolicyThree in class InstructorSetATimeLimitOnAssignments", e);
        }
    }

    @Test(priority = 25, enabled = true)
    public void dueDateIsElapsedPolicyFour() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "25");
            String familyname = ReadTestData.readDataByTagName("", "familyname", "25_1");
            String givenname = ReadTestData.readDataByTagName("", "givenname", "25_1");

            new Assignment().create(25);
            ReportUtil.log("Create assessment", "Assessment Created  with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("25_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");
            new LoginUsingLTI().ltiLogin("25_2");//log in as student2
            ReportUtil.log("Student2 login", "Student2 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("25");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "1", "", "Release explicitly by the instructor", "", "", "", "25");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(25);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            Thread.sleep(120000);//wait for due date to expair

            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student response
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(1),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(1));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected =familyname+", "+givenname+"\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 5 mins\n" +
                    "Change total time to mins\n" +
                    "Update Time";
            CustomAssert.assertEquals(actual,expected,"Verify student's time spent card","All the details are displaying correct","All the details are displaying correct");
            assignmentResponsesPage.updateTime.get(1).sendKeys("6");
            assignmentResponsesPage.updateTime_button.get(1).click();//click on update button
            extendDueDate(0,"25_1");//extend due date for student1
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(0),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            actual = assignmentResponsesPage.timeSpentCard.getText().trim();
            expected ="family, givenname\n" +
                    "Time spent : 0 min(s) 0 sec(s)\n" +
                    "Assigned time limit : 5 mins\n" +
                    "Change total time to mins\n" +
                    "Update Time";
            CustomAssert.assertEquals(actual,expected,"Verify student's time spent card","All the details are displaying correct","All the details are displaying correct");
            CustomAssert.assertEquals(assignmentResponsesPage.assignedTimeLimit.get(0).getText(), "Assigned time limit : 5 mins", "Verify old time", "Time is not updated", "Time is  updated");

            new LoginUsingLTI().ltiLogin("25_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            assignments.assignmentName.click();//click on assignment name
            assignments.timeAssignment_yesLink.click();//click on yes
            Thread.sleep(1000);

            new LoginUsingLTI().ltiLogin("25");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student response
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(1),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(1));

            assignmentResponsesPage.updateTime.get(1).sendKeys("7");
            assignmentResponsesPage.updateTime_button.get(1).click();//click on update button
            CustomAssert.assertEquals(assignmentResponsesPage.updateTimeError.get(1).getText(), "Assignment in use", "Verify error message", "Error message is displayed", "Error message is not displayed");
            extendDueDate(0, "25_1");
            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(1),false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(1));

            assignmentResponsesPage.updateTime.get(1).sendKeys("8");
            assignmentResponsesPage.updateTime_button.get(1).click();//click on update button
            CustomAssert.assertEquals(assignmentResponsesPage.updateTimeError.get(1).getText(),"Assignment in use","Verify error message","Error message is displayed","Error message is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }

    @Test(priority = 26, enabled = true)
    public void timeLimitAndDueDateExhaustsWithPolicyOne() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "26");

            new Assignment().create(26);
            new Assignment().addQuestions(26, "truefalse", "");
            ReportUtil.log("Create assessment", "Assessment Created  with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("26_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("26");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "1", "", "Auto-release on assignment submission", "", "", "", "26");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(26);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("26_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            assignments.assignmentName.click();//click on assignment name
            assignments.timeAssignment_yesLink.click();//click on yes
            new AttemptQuestion().trueFalse(false,"correct",26);
            new Assignment().nextButtonInQuestionClick();//click on Next
            Thread.sleep(120000);//wait till time limit and due time to exhaust
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");
            assignments.closeDueDate.click();//click on close

            new LoginUsingLTI().ltiLogin("26");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");

            currentAssignments.getViewGrade_link().click();//click on view student response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.timeExhaustedIcon);
            CustomAssert.assertEquals(assignmentResponsesPage.timeExhaustedIcon.getAttribute("title"),"Time limit exhausted","Verify Time limit exhausted icon","Time Exhausted icon is displayed","Time Exhausted icon is not displayed");
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            assignmentResponsesPage.updateTime.get(0).sendKeys("4");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            CustomAssert.assertEquals(assignmentResponsesPage.assignedTimeLimit.get(0).getText(), "Assigned time limit : 4 mins", "Verify updated time", "Time is updated", "Time is not updated");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }


    @Test(priority = 27, enabled = true)
    public void timeLimitAndDueDateExhaustsWithPolicyThree() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "27");

            new Assignment().create(27);
            new Assignment().addQuestions(27, "truefalse", "");
            ReportUtil.log("Create assessment", "Assessment Created  with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("27_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("27");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "1", "", "Release as they are being graded", "", "", "", "27");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(27);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("27_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            WebDriverUtil.clickOnElementUsingJavascript(assignments.assignmentName);//click on assignment name
            assignments.timeAssignment_yesLink.click();//click on yes
            new AttemptQuestion().trueFalse(false,"correct",27);
            new Assignment().nextButtonInQuestionClick();//click on Next
            Thread.sleep(120000);//wait till time limit and due time to exhaust
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");
            assignments.closeDueDate.click();//click on close

            new LoginUsingLTI().ltiLogin("27");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");

            currentAssignments.getViewGrade_link().click();//click on view student response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.timeExhaustedIcon);
            CustomAssert.assertEquals(assignmentResponsesPage.timeExhaustedIcon.getAttribute("title"),"Time limit exhausted","Verify Time limit exhausted icon","Time Exhausted icon is displayed","Time Exhausted icon is not displayed");
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            assignmentResponsesPage.updateTime.get(0).sendKeys("4");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            Thread.sleep(1000);
            CustomAssert.assertEquals(assignmentResponsesPage.assignedTimeLimit.get(0).getText(), "Assigned time limit : 4 mins", "Verify updated time", "Time is updated", "Time is not updated");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }


    @Test(priority = 28, enabled = true)
    public void timeLimitAndDueDateExhaustsWithPolicyFour() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "28");

            new Assignment().create(28);
            new Assignment().addQuestions(28, "truefalse", "");
            ReportUtil.log("Create assessment", "Assessment Created  with true false question successfully", "pass");

            new LoginUsingLTI().ltiLogin("28_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("28");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Policies");
            ReportUtil.log("Navigate to Policies", "Instructor navigated to Policies page successfully", "pass");
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "1", "", "Release explicitly by the instructor", "", "", "", "28");//till save policy
            ReportUtil.log("Create policy", "Created policy with release option 1", "Pass");

            new Assignment().assignAssignmentWithDueDate(28);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("28_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            WebDriverUtil.clickOnElementUsingJavascript(assignments.assignmentName);//click on assignment name
            assignments.timeAssignment_yesLink.click();//click on yes
            new AttemptQuestion().trueFalse(false,"correct",28);
            new Assignment().nextButtonInQuestionClick();//click on Next
            Thread.sleep(120000);//wait till time limit and due time to exhaust
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");
            assignments.closeDueDate.click();//click on close

            new LoginUsingLTI().ltiLogin("28");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");

            currentAssignments.getViewGrade_link().click();//click on view student response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.timeExhaustedIcon);
            CustomAssert.assertEquals(assignmentResponsesPage.timeExhaustedIcon.getAttribute("title"),"Time limit exhausted","Verify Time limit exhausted icon","Time Exhausted icon is displayed","Time Exhausted icon is not displayed");
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            assignmentResponsesPage.updateTime.get(0).sendKeys("4");
            assignmentResponsesPage.updateTime_button.get(0).click();//click on update button
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            CustomAssert.assertEquals(assignmentResponsesPage.assignedTimeLimit.get(0).getText(), "Assigned time limit : 4 mins", "Verify updated time", "Time is updated", "Time is not updated");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }


    public void extendDueDate(int share,String dataIndex){
        try {
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);

            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click(); //click on the update assignment
            assignmentResponsesPage.extendDueDateLabel.click();//click on the extend due date
            List<WebElement> allClassSection = driver.findElements(By.cssSelector("li[class='bit-box']"));
            for (WebElement classSection : allClassSection) {
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElements(By.className("closebutton")).get(driver.findElements(By.className("closebutton")).size() - 1));//click on close symbol
                String assignToField = new TextFetch().textfetchbyclass("holder");
                if (assignToField.length() == 0) {
                    break;
                }
            }

            newAssignment.classSection.get(share).sendKeys(shareWithInitialString);
            Thread.sleep(2000);
            newAssignment.shareWithClass.click();
            currentAssignments.getNewDueDateField().click();//click on date picker-month
            currentAssignments.Next_Date_picker.click();//click on next month arrow
            Thread.sleep(2000);
            driver.findElement(By.linkText("6")).click();//select date
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.updateExtendDueDate);//click on re-assign button
            Thread.sleep(1000);

        }catch (Exception e){
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }



    public void verificationOfTimeLimit() {
        try {
            CustomAssert.assertEquals(policies.timeLimit.isDisplayed(), true, "Verify time limit option", "Time limit option is displayed", "Time limit option is not displayed");
            CustomAssert.assertEquals(policies.radioButton.get(1).getAttribute("value"), "false", "Verify disable radio button", "By default disable radio button is selected", "By default disable radio button is not selected");
            policies.helpIcon_Time.click();//click on help icon of time limit
            actual = policies.helpMessage.getText();
            expected = "Select Enable if you want to set a time limit for any assignment. The assignment should be finished in one session only.";
            CustomAssert.assertEquals(actual, expected, "Verify help message", "Help message is as per expected", "Help message is not as per expected");

            //Verify error message :Time limit cannot be more than 300 minutes.

            policies.radioButton.get(0).click();//click on enable radio button
            CustomAssert.assertEquals(policies.minuteTextBox.isDisplayed(), true, "Verify minute textBox", "minute textBox is displayed", "minute TextBox is not displayed");
            CustomAssert.assertEquals(policies.minuteText.get(2).isDisplayed(), true, "Verify minute text", "minute text is displayed", "minute text is not displayed");
            policies.minuteTextBox.clear();
            policies.minuteTextBox.sendKeys("301");
            WebDriverUtil.clickOnElementUsingJavascript(policies.getAssignmentPolicySaveButton());//click on save policy
            Thread.sleep(1000);
            CustomAssert.assertEquals(policies.minuteTextBox.getCssValue("border-top-color"), "rgba(255, 0, 0, 1)", "Verify minute textBox color", "minute textBox colour is red", "minute TextBox color is not red");
            actual = policies.durationError.getText();
            expected = "Time limit cannot be more than 300 minutes.";
            CustomAssert.assertEquals(actual, expected, "Verify duration error message", "Duration error message is as per expected", "Duration error message is not as per expected");

            //Verify error message :Time limit cannot be less than 1 minute.

            policies.minuteTextBox.clear();
            policies.minuteTextBox.sendKeys("0");
            WebDriverUtil.clickOnElementUsingJavascript(policies.getAssignmentPolicySaveButton());//click on save policy
            Thread.sleep(1000);
            CustomAssert.assertEquals(policies.minuteTextBox.getCssValue("border-top-color"), "rgba(255, 0, 0, 1)", "Verify minute textBox color", "minute textBox colour is red", "minute TextBox color is not red");
            actual = policies.durationError.getText();
            expected = "Time limit cannot be less than 1 minute.";
            CustomAssert.assertEquals(actual, expected, "Verify duration error message", "Duration error message is as per expected", "Duration error message is not as per expected");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }
    }


    public void verificationOfTimeIcon(int index) {

        try {
            List<WebElement> timeAssignmentLabels=currentAssignments.timedAssignmentLabel;
            for(WebElement we : timeAssignmentLabels){
                if(we.isDisplayed()==true){
                    actual = we.getText().trim();
                    expected = "Timed Assignment: 6 minutes";
                    CustomAssert.assertEquals(actual, expected, "Verify time assignment label", "Time assignment label is displayed", "Time assignment label is not displayed");
                }
            }
            List<WebElement> timerIcon=currentAssignments.timerIcon;
            for(WebElement we : timerIcon){
                if(we.isDisplayed()){
                    CustomAssert.assertEquals(we.isDisplayed(), true, "Verify timer icon", "Timer icon is displayed", "Time icon is not displayed");
                }
            }

            currentAssignments.helpIcon_TimedAssignment.get(index).click();//click on help icon
            actual = currentAssignments.helpMessage_TimedAssignment.get(index).getText().trim();
            String expected1="This is a timed assignment that must be completed in one continuous session.";
            String expected2 = "This is a timed assignment that must be completed in one continuous session. Your time limit for this assignment is 6 minutes.";

            if(actual.equals(expected1)) {
                CustomAssert.assertEquals(actual, expected1, "Verify time assignment help message", "Time assignment help message is displayed", "Time assignment message help  is not displayed");
            }
            else{
                CustomAssert.assertEquals(actual, expected2, "Verify time assignment help message", "Time assignment help message is displayed", "Time assignment message help  is not displayed");

            }
        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);

        }

    }

    public void selectPolicy() {

        try {
            List<WebElement> assign1 = questionBank.assignThisLink;

            for (int i = 0; i < assign1.size(); i++) {
                if (assign1.get(i).isDisplayed()) {
                    WebDriverUtil.clickOnElementUsingJavascript(assign1.get(i));
                    break;
                }
            }
            Thread.sleep(3000);
            policies.selectPolicy.click();//click on select assignment policy
            WebDriverUtil.clickOnElementUsingJavascript(policies.addNewPolicy);//lick on add new policy
            Thread.sleep(1000);

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);

        }

    }

    public void VerificationOfTimeIconOnStudentSide() {

        try {

            actual = assignments.popUpMessage.getText();
            expected ="Timed Assignment\nOnce you begin, you will have 6 minutes to complete this assignment.\nIf you log out, or navigate away from this assignment, the timer will continue to run.\nWould you like to start the assignment now?\nYes\nNo";
            CustomAssert.assertEquals(actual,expected,"Verify popup message","popup message is as per expected","popup message is not as per expected");
            CustomAssert.assertEquals(assignments.closeIconOnPopUp.isDisplayed(),true,"Verify popup close icon","close icon is displayed","close icon is not displayed");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_noLink);//click on no
            try {
                assignments.assignmentName.click();//click on assignment name
            }
            catch (Exception e){
                try {
                    WebDriverUtil.clickOnElementUsingJavascript(dashBoard.upcoming_assignment);//click on assignment
                } catch (Exception e1) {
                    try {
                        WebDriverUtil.clickOnElementUsingJavascript(courseStream.assessmentName);//click on assignment name
                    } catch (Exception e2) {
                        try {
                            WebDriverUtil.waitTillVisibilityOfElement(assignmentTab.open_button, 20);
                            WebDriverUtil.clickOnElementUsingJavascript(assignmentTab.open_button);
                        } catch (Exception e3) {
                            WebDriverUtil.clickOnElementUsingJavascript(lessonPage.TOCassignmentName);
                        }
                    }
                }

            }
            assignments.timeAssignment_yesLink.click();//click on yes
            CustomAssert.assertEquals(questionPage.timeRemaining.get(1).getText(),"Time Remaining","Verify time remaining text","Time remaining text is available","Time remaining text is not available");
            CustomAssert.assertTrue(assignments.button_close.isDisplayed(),"Verify close icon","Close icon is not available","close icon is available");
            new MouseHover().mouserhoverbywebelement(questionPage.timedAssignmentTimer);
            CustomAssert.assertEquals(questionPage.timedAssignmentTimer.getAttribute("title"),"Hide Timer","Verify hide time","Hide timer option is displayed","Hide timer option is not displayed");
            WebDriverUtil.clickOnElementUsingJavascript(questionPage.timedAssignmentTimer);//click on hide timer option
            CustomAssert.assertEquals(questionPage.timedAssignmentTimer.getAttribute("title"),"Show Timer","Verify time","timer is hidden","timer is not hidden");
            WebDriverUtil.clickOnElementUsingJavascript(questionPage.timedAssignmentTimer);//click on show timer option
            driver.findElement(By.xpath("/html/body")).click();//click on body
            CustomAssert.assertEquals(questionPage.timeRemaining.get(1).getText(),"Time Remaining","Verify time remaining text","Time remaining text is available","Time remaining text is not available");
            Thread.sleep(60000);
            CustomAssert.assertEquals(questionPage.timeWarning.getCssValue("color"),"rgba(255, 0, 0, 1)","Verify remaining time color","Remaining time color is red","Remaining time color is red");
            new AttemptQuestion().trueFalse(false,"correct",3);
            new Assignment().submitButtonInQuestionClick();


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);

        }

    }

    public void openAssignmentFromAssignmentTab() {
        try {

            new TOCShow().chaptertree();//click on chapter tree
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments");   //go to Assignments tab
            WebDriverUtil.clickOnElementUsingJavascript(assignmentTab.rightArrow);//click on arrow
            WebDriverUtil.waitTillVisibilityOfElement(assignmentTab.open_button, 20);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentTab.open_button);//click on open

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSetTimeLimitOnAssignments in class InstructorSetATimeLimitOnAssignments", e);
        }

    }
}