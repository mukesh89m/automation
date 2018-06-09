package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.timeBasedAssignment;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.PerformanceTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.ClassSectionDropDown;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Perspective;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.group.Group;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.PerformanceSummary;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.PerformanceSummaryReport;
import com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.LTILogin;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

/**
 * Created by mukesh on 2/6/16.
 */
public class TimeLimitOptionEnableAndDueDateNotElapsed extends Driver {

    NewAssignment newAssignment;
    MyQuestionBank myQuestionBank;
    CurrentAssignments currentAssignments;
    AssessmentResponses assessmentResponses;
    CourseStreamPage courseStreamPage;
    Assignments assignments;
    Dashboard dashboard;
    PerformanceSummaryReport performanceSummaryReport;
    Policies policies;
    AssignmentResponsesPage assignmentResponsesPage;
    Preview preview;
    PerformanceTab performanceTab;
    LessonPage lessonPage;

    String actual = "";
    String expected = "";

    @BeforeMethod
    public void initializeWebElement() {
        WebDriver driver=Driver.getWebDriver();
        newAssignment = PageFactory.initElements(driver, NewAssignment.class);
        myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
        currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
        assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);
        courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
        assignments = PageFactory.initElements(driver, Assignments.class);
        dashboard = PageFactory.initElements(driver, Dashboard.class);
        performanceSummaryReport = PageFactory.initElements(driver, PerformanceSummaryReport.class);
        policies = PageFactory.initElements(driver, Policies.class);
        assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
        preview = PageFactory.initElements(driver, Preview.class);
        performanceTab = PageFactory.initElements(driver, PerformanceTab.class);
        lessonPage = PageFactory.initElements(driver, LessonPage.class);


    }

    @Test(priority = 1,enabled = true)
    public void timeLimitOptionEnableAndDueDateNotElapsed(){
        WebDriver driver= Driver.getWebDriver();

        try {
            String dataIndex="1";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            String timeDuration = ReadTestData.readDataByTagName("", "timeDuration", dataIndex);
            String updateTime = ReadTestData.readDataByTagName("", "updateTime", dataIndex);


            new Assignment().create(1);
            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");

            new LoginUsingLTI().ltiLogin("1_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("1_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("1");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "","1");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(1);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");



            new LoginUsingLTI().ltiLogin("1_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            actual =assignments.timeAssignment_header.getText().trim();
            expected="Timed Assignment";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment header", "time assignment header is  " + expected + "", "time assignment header is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(0).getText().trim();
            expected="Once you begin, you will have "+timeDuration+" minutes to complete this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(1).getText().trim();
            expected="If you log out, or navigate away from this assignment, the timer will continue to run.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(2).getText().trim();
            expected="Would you like to start the assignment now?";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            new LoginUsingLTI().ltiLogin("1");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Assignment().updateTimeValueOfTimedAssignment(1);

            new LoginUsingLTI().ltiLogin("1_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            CustomAssert.assertTrue(currentAssignments.timedAssignmentHelpInfo_icon.isDisplayed(), "verify timedAssignmentHelpInfo_icon", "timedAssignmentHelpInfo_icon is displaying", "timedAssignmentHelpInfo_icon is not displaying");
            currentAssignments.timedAssignmentHelpInfo_icon.click();
            Thread.sleep(2000);
            actual=assignments.timeAssignmentHelp_msg.getText().trim();
            expected="This is a timed assignment that must be completed in one continuous session. Your time limit for this assignment is "+updateTime+" minutes.";
            CustomAssert.assertEquals(actual, expected, "Verify timeAssignmentHelp_msg", "timeAssignmentHelp_msg is  " + expected + "", "timeAssignmentHelp_msg is not" + expected + "");

            actual =currentAssignments.timedAssignmentMinute.get(0).getText().trim();
            expected=updateTime+" minutes";
            CustomAssert.assertEquals(actual, expected, "Verify Changed time limit value", "Changed time limit value should be displayed after Timed Assignment label.", "Changed time limit value is not displayed after Timed Assignment label.");

            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            actual =assignments.timeAssignment_header.getText().trim();
            expected="Timed Assignment";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment header", "time assignment header is  " + expected + "", "time assignment header is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(0).getText().trim();
            expected="Once you begin, you will have "+updateTime+" minutes to complete this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(1).getText().trim();
            expected="If you log out, or navigate away from this assignment, the timer will continue to run.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(2).getText().trim();
            expected="Would you like to start the assignment now?";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            String timeLeft=assignments.timer.getAttribute("timetaken").trim();
            new AttemptQuestion().trueFalse(false,"correct",1);
            Thread.sleep(96000); //wait one minute six second
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.finish_button); //click on the finish button


            new LoginUsingLTI().ltiLogin("1_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            CustomAssert.assertTrue(currentAssignments.timedAssignmentHelpInfo_icon.isDisplayed(), "verify timedAssignmentHelpInfo_icon", "timedAssignmentHelpInfo_icon is displaying", "timedAssignmentHelpInfo_icon is not displaying");
            currentAssignments.timedAssignmentHelpInfo_icon.click();
            Thread.sleep(2000);
            actual=assignments.timeAssignmentHelp_msg.getText().trim();
            expected="This is a timed assignment that must be completed in one continuous session. Your time limit for this assignment is "+timeDuration+" minutes.";
            CustomAssert.assertEquals(actual, expected, "Verify timeAssignmentHelp_msg", "timeAssignmentHelp_msg is  " + expected + "", "timeAssignmentHelp_msg is not" + expected + "");

            actual =currentAssignments.timedAssignmentMinute.get(0).getText().trim();
            expected=timeDuration+" minutes";
            CustomAssert.assertEquals(actual, expected, "Verify Changed time limit value", "Changed time limit value should be displayed after Timed Assignment label.", "Changed time limit value is not displayed after Timed Assignment label.");
        } catch (Exception e) {
            Assert.fail("Exception in TC timeLimitOptionEnableAndDueDateNotElapsed of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void timeLimitOptionEnableAndDueDateNotElapsedPolicyTwo(){
        WebDriver driver= Driver.getWebDriver();

        try {
            String dataIndex="2";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            String timeDuration = ReadTestData.readDataByTagName("", "timeDuration", dataIndex);
            String updateTime = ReadTestData.readDataByTagName("", "updateTime", dataIndex);

            new Assignment().create(2);
            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");

            new LoginUsingLTI().ltiLogin("2_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("2_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("2");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, false, "1", "", "Auto-release on due date", "", "", "", "2");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(2);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("2_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            actual =assignments.timeAssignment_header.getText().trim();
            expected="Timed Assignment";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment header", "time assignment header is  " + expected + "", "time assignment header is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(0).getText().trim();
            expected="Once you begin, you will have "+timeDuration+" minutes to complete this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(1).getText().trim();
            expected="If you log out, or navigate away from this assignment, the timer will continue to run.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(2).getText().trim();
            expected="Would you like to start the assignment now?";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            new LoginUsingLTI().ltiLogin("2");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Assignment().updateTimeValueOfTimedAssignment(2);

            new LoginUsingLTI().ltiLogin("2_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            CustomAssert.assertTrue(currentAssignments.timedAssignmentHelpInfo_icon.isDisplayed(), "verify timedAssignmentHelpInfo_icon", "timedAssignmentHelpInfo_icon is displaying", "timedAssignmentHelpInfo_icon is not displaying");
            currentAssignments.timedAssignmentHelpInfo_icon.click();
            Thread.sleep(2000);
            actual=assignments.timeAssignmentHelp_msg.getText().trim();
            expected="This is a timed assignment that must be completed in one continuous session. Your time limit for this assignment is "+updateTime+" minutes.";
            CustomAssert.assertEquals(actual, expected, "Verify timeAssignmentHelp_msg", "timeAssignmentHelp_msg is  " + expected + "", "timeAssignmentHelp_msg is not" + expected + "");

            actual =currentAssignments.timedAssignmentMinute.get(0).getText().trim();
            expected=updateTime+" minutes";
            CustomAssert.assertEquals(actual, expected, "Verify Changed time limit value", "Changed time limit value should be displayed after Timed Assignment label.", "Changed time limit value is not displayed after Timed Assignment label.");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());

            actual =assignments.timeAssignmentNotification.get(0).getText().trim();
            expected="Once you begin, you will have "+updateTime+" minutes to complete this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            new AttemptQuestion().trueFalse(false,"correct",2);
            Thread.sleep(96000); //wait one minute six second
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.finish_button); //click on the finish button
            actual = performanceSummaryReport.chartTitle.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            String questionNo = performanceSummaryReport.performanceChart_questionNo.getText().trim();
            CustomAssert.assertEquals(questionNo, "1\nQuestions", "Verify Question Number", "Attempted Number of Question should be  " + questionNo + "", "Attempted Number of Question not displaying correctly");


            new LoginUsingLTI().ltiLogin("2_2");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            CustomAssert.assertTrue(currentAssignments.timedAssignmentHelpInfo_icon.isDisplayed(), "verify timedAssignmentHelpInfo_icon", "timedAssignmentHelpInfo_icon is displaying", "timedAssignmentHelpInfo_icon is not displaying");
            currentAssignments.timedAssignmentHelpInfo_icon.click();
            Thread.sleep(2000);
            actual=assignments.timeAssignmentHelp_msg.getText().trim();
            expected="This is a timed assignment that must be completed in one continuous session. Your time limit for this assignment is "+timeDuration+" minutes.";
            CustomAssert.assertEquals(actual, expected, "Verify timeAssignmentHelp_msg", "timeAssignmentHelp_msg is  " + expected + "", "timeAssignmentHelp_msg is not" + expected + "");

            actual =currentAssignments.timedAssignmentMinute.get(0).getText().trim();
            expected=timeDuration+" minutes";
            CustomAssert.assertEquals(actual, expected, "Verify Changed time limit value", "Changed time limit value should be displayed after Timed Assignment label.", "Changed time limit value is not displayed after Timed Assignment label.");

        } catch (Exception e) {
            Assert.fail("Exception in TC timeLimitOptionEnableAndDueDateNotElapsedPolicyTwo of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }

    }

    @Test(priority = 3,enabled = true)
    public void timeLimitOptionEnableAndDueDateNotElapsedPolicyThree(){
        WebDriver driver= Driver.getWebDriver();

        try {
            String dataIndex="3";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            String timeDuration = ReadTestData.readDataByTagName("", "timeDuration", dataIndex);
            String updateTime = ReadTestData.readDataByTagName("", "updateTime", dataIndex);

            new Assignment().create(3);
            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");

            new LoginUsingLTI().ltiLogin("3_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("3_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("3");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "","3");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(3);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("3_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            actual =assignments.timeAssignment_header.getText().trim();
            expected="Timed Assignment";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment header", "time assignment header is  " + expected + "", "time assignment header is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(0).getText().trim();
            expected="Once you begin, you will have "+timeDuration+" minutes to complete this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(1).getText().trim();
            expected="If you log out, or navigate away from this assignment, the timer will continue to run.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(2).getText().trim();
            expected="Would you like to start the assignment now?";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            new LoginUsingLTI().ltiLogin("3");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Assignment().updateTimeValueOfTimedAssignment(3);

            new LoginUsingLTI().ltiLogin("3_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            CustomAssert.assertTrue(currentAssignments.timedAssignmentHelpInfo_icon.isDisplayed(), "verify timedAssignmentHelpInfo_icon", "timedAssignmentHelpInfo_icon is displaying", "timedAssignmentHelpInfo_icon is not displaying");
            currentAssignments.timedAssignmentHelpInfo_icon.click();
            Thread.sleep(3000);
            actual=assignments.timeAssignmentHelp_msg.getText().trim();
            expected="This is a timed assignment that must be completed in one continuous session. Your time limit for this assignment is "+updateTime+" minutes.";
            CustomAssert.assertEquals(actual, expected, "Verify timeAssignmentHelp_msg", "timeAssignmentHelp_msg is  " + expected + "", "timeAssignmentHelp_msg is not" + expected + "");

            actual =currentAssignments.timedAssignmentMinute.get(0).getText().trim();
            expected=updateTime+" minutes";
            CustomAssert.assertEquals(actual, expected, "Verify Changed time limit value", "Changed time limit value should be displayed after Timed Assignment label.", "Changed time limit value is not displayed after Timed Assignment label.");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());

            actual =assignments.timeAssignmentNotification.get(0).getText().trim();
            expected="Once you begin, you will have "+updateTime+" minutes to complete this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            new AttemptQuestion().trueFalse(false,"correct",2);
            Thread.sleep(96000); //wait one minute six second
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.finish_button); //click on the finish button
            actual = performanceSummaryReport.chartTitle.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            String questionNo = performanceSummaryReport.performanceChart_questionNo.getText().trim();
            CustomAssert.assertEquals(questionNo, "1\nQuestions", "Verify Question Number", "Attempted Number of Question should be  " + questionNo + "", "Attempted Number of Question not displaying correctly");


            new LoginUsingLTI().ltiLogin("3_2");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            CustomAssert.assertTrue(currentAssignments.timedAssignmentHelpInfo_icon.isDisplayed(), "verify timedAssignmentHelpInfo_icon", "timedAssignmentHelpInfo_icon is displaying", "timedAssignmentHelpInfo_icon is not displaying");
            currentAssignments.timedAssignmentHelpInfo_icon.click();
            Thread.sleep(2000);
            actual=assignments.timeAssignmentHelp_msg.getText().trim();
            expected="This is a timed assignment that must be completed in one continuous session. Your time limit for this assignment is "+timeDuration+" minutes.";
            CustomAssert.assertEquals(actual, expected, "Verify timeAssignmentHelp_msg", "timeAssignmentHelp_msg is  " + expected + "", "timeAssignmentHelp_msg is not" + expected + "");

            actual =currentAssignments.timedAssignmentMinute.get(0).getText().trim();
            expected=timeDuration+" minutes";
            CustomAssert.assertEquals(actual, expected, "Verify Changed time limit value", "Changed time limit value should be displayed after Timed Assignment label.", "Changed time limit value is not displayed after Timed Assignment label.");

        } catch (Exception e) {
            Assert.fail("Exception in TC timeLimitOptionEnableAndDueDateNotElapsedPolicyThree of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void timeLimitOptionEnableAndDueDateNotElapsedPolicyFour(){
        WebDriver driver= Driver.getWebDriver();

        try {
            String dataIndex="4";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            String timeDuration = ReadTestData.readDataByTagName("", "timeDuration", dataIndex);
            String updateTime = ReadTestData.readDataByTagName("", "updateTime", dataIndex);

            new Assignment().create(4);
            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");

            new LoginUsingLTI().ltiLogin("4_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("4_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("4");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, false, "1", "", "Release explicitly by the instructor", "", "", "","4");//policy 4

            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(4);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("4_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            actual =assignments.timeAssignment_header.getText().trim();
            expected="Timed Assignment";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment header", "time assignment header is  " + expected + "", "time assignment header is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(0).getText().trim();
            expected="Once you begin, you will have "+timeDuration+" minutes to complete this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(1).getText().trim();
            expected="If you log out, or navigate away from this assignment, the timer will continue to run.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(2).getText().trim();
            expected="Would you like to start the assignment now?";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            new LoginUsingLTI().ltiLogin("4");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Assignment().updateTimeValueOfTimedAssignment(4);

            new LoginUsingLTI().ltiLogin("4_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            CustomAssert.assertTrue(currentAssignments.timedAssignmentHelpInfo_icon.isDisplayed(), "verify timedAssignmentHelpInfo_icon", "timedAssignmentHelpInfo_icon is displaying", "timedAssignmentHelpInfo_icon is not displaying");
            currentAssignments.timedAssignmentHelpInfo_icon.click();
            Thread.sleep(4000);
            actual=assignments.timeAssignmentHelp_msg.getText().trim();
            expected="This is a timed assignment that must be completed in one continuous session. Your time limit for this assignment is "+updateTime+" minutes.";
            CustomAssert.assertEquals(actual, expected, "Verify timeAssignmentHelp_msg", "timeAssignmentHelp_msg is  " + expected + "", "timeAssignmentHelp_msg is not" + expected + "");

            actual =currentAssignments.timedAssignmentMinute.get(0).getText().trim();
            expected=updateTime+" minutes";
            CustomAssert.assertEquals(actual, expected, "Verify Changed time limit value", "Changed time limit value should be displayed after Timed Assignment label.", "Changed time limit value is not displayed after Timed Assignment label.");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());

            actual =assignments.timeAssignmentNotification.get(0).getText().trim();
            expected="Once you begin, you will have "+updateTime+" minutes to complete this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            new AttemptQuestion().trueFalse(false,"correct",2);
            Thread.sleep(96000); //wait one minute six second
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.finish_button); //click on the finish button
            actual = performanceSummaryReport.chartTitle.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            String questionNo = performanceSummaryReport.performanceChart_questionNo.getText().trim();
            CustomAssert.assertEquals(questionNo, "1\nQuestions", "Verify Question Number", "Attempted Number of Question should be  " + questionNo + "", "Attempted Number of Question not displaying correctly");


            new LoginUsingLTI().ltiLogin("4_2");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            CustomAssert.assertTrue(currentAssignments.timedAssignmentHelpInfo_icon.isDisplayed(), "verify timedAssignmentHelpInfo_icon", "timedAssignmentHelpInfo_icon is displaying", "timedAssignmentHelpInfo_icon is not displaying");
            currentAssignments.timedAssignmentHelpInfo_icon.click();
            Thread.sleep(2000);
            actual=assignments.timeAssignmentHelp_msg.getText().trim();
            expected="This is a timed assignment that must be completed in one continuous session. Your time limit for this assignment is "+timeDuration+" minutes.";
            CustomAssert.assertEquals(actual, expected, "Verify timeAssignmentHelp_msg", "timeAssignmentHelp_msg is  " + expected + "", "timeAssignmentHelp_msg is not" + expected + "");

            actual =currentAssignments.timedAssignmentMinute.get(0).getText().trim();
            expected=timeDuration+" minutes";
            CustomAssert.assertEquals(actual, expected, "Verify Changed time limit value", "Changed time limit value should be displayed after Timed Assignment label.", "Changed time limit value is not displayed after Timed Assignment label.");

        } catch (Exception e) {
            Assert.fail("Exception in TC timeLimitOptionEnableAndDueDateNotElapsedPolicyFour of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void timeLimitOptionEnableAndTimeLimitExhausted(){
        WebDriver driver= Driver.getWebDriver();

        try {
            String dataIndex="5";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            String timeDuration = ReadTestData.readDataByTagName("", "timeDuration", dataIndex);
            String updateTime = ReadTestData.readDataByTagName("", "updateTime", dataIndex);

            new Assignment().create(5);
            new Assignment().addQuestions(5,"truefalse","");

            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");

            new LoginUsingLTI().ltiLogin("5_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("5_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("5");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, true, "1", "", "Auto-release on assignment submission", "", "", "","5");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(5);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");


            new LoginUsingLTI().ltiLogin("5_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            new AttemptQuestion().trueFalse(false,"correct",1);
            new Assignment().nextButtonInQuestionClick();
            Thread.sleep(72000); //wait
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");


            new LoginUsingLTI().ltiLogin("5");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Assignment().updateTimeValueOfTimedAssignment(5);

            new LoginUsingLTI().ltiLogin("5_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");

            CustomAssert.assertTrue(currentAssignments.timedAssignmentHelpInfo_icon.isDisplayed(), "verify timedAssignmentHelpInfo_icon", "timedAssignmentHelpInfo_icon is displaying", "timedAssignmentHelpInfo_icon is not displaying");
            currentAssignments.timedAssignmentHelpInfo_icon.click();
            Thread.sleep(2000);
            updateTime = ReadTestData.readDataByTagName("", "updateTime", dataIndex);

            int actualTime=Integer.parseInt(updateTime);
            actual=assignments.timeAssignmentHelp_msg.getText().trim();
            expected="This is a timed assignment that must be completed in one continuous session. Your time limit for this assignment is "+(actualTime-1)+" minutes.";
            CustomAssert.assertEquals(actual, expected, "Verify timeAssignmentHelp_msg", "timeAssignmentHelp_msg is  " + expected + "", "timeAssignmentHelp_msg is not" + expected + "");

            actual =currentAssignments.timedAssignmentMinute.get(0).getText().trim();
            expected=(actualTime-1)+" minutes";
            CustomAssert.assertEquals(actual, expected, "Verify Changed time limit value", "Changed time limit value should be displayed after Timed Assignment label.", "Changed time limit value is not displayed after Timed Assignment label.");

            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName()); //click on the timed assignments
            actual =assignments.timeAssignment_header.getText().trim();
            expected="Timed Assignment";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment header", "time assignment header is  " + expected + "", "time assignment header is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(0).getText().trim();
            expected="Once you begin, you will have "+(actualTime-1)+" minutes to complete this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(1).getText().trim();
            expected="If you log out, or navigate away from this assignment, the timer will continue to run.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(2).getText().trim();
            expected="Would you like to start the assignment now?";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            Thread.sleep(1000);

            actual =assignments.question_index.getText().trim();
            expected="Q 2:";
            CustomAssert.assertEquals(actual, expected, "Verify question index", "User has navigated to next question of last attempted question","User has not navigated to next question of last attempted question");

            String timeLeft=assignments.timer.getAttribute("timetaken").trim();
            System.out.println("timeLeft:"+timeLeft);
            actual =assignments.timer.getAttribute("timetaken").trim();
            if(!(actual.equals("120")||actual.equals("119")||actual.equals("118")||actual.equals("117")||actual.equals("116"))) {
                CustomAssert.fail("Verify extented time", "Remaining is getting start with old  time limit");
            }

            new AttemptQuestion().trueFalse(false, "correct", 1);
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.finish_button); //click on the finish button
            Thread.sleep(2000);
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getGoToNextLinkOnPopUp());
            actual = performanceSummaryReport.chartTitle.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            String questionNo = performanceSummaryReport.performanceChart_questionNo.getText().trim();
            CustomAssert.assertEquals(questionNo, "2\nQuestions", "Verify Question Number", "Attempted Number of Question should be  " + questionNo + "", "Attempted Number of Question not displaying correctly");

            new LoginUsingLTI().ltiLogin("5_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            CustomAssert.assertTrue(currentAssignments.timedAssignmentHelpInfo_icon.isDisplayed(), "verify timedAssignmentHelpInfo_icon", "timedAssignmentHelpInfo_icon is displaying", "timedAssignmentHelpInfo_icon is not displaying");
            currentAssignments.timedAssignmentHelpInfo_icon.click();
            Thread.sleep(2000);
            actual=assignments.timeAssignmentHelp_msg.getText().trim();
            expected="This is a timed assignment that must be completed in one continuous session. Your time limit for this assignment is "+timeDuration+" minutes.";
            CustomAssert.assertEquals(actual, expected, "Verify timeAssignmentHelp_msg", "timeAssignmentHelp_msg is  " + expected + "", "timeAssignmentHelp_msg is not" + expected + "");

            actual =currentAssignments.timedAssignmentMinute.get(0).getText().trim();
            expected=timeDuration+" minutes";
            CustomAssert.assertEquals(actual, expected, "Verify Changed time limit value", "Changed time limit value should be displayed after Timed Assignment label.", "Changed time limit value is not displayed after Timed Assignment label.");

        } catch (Exception e) {
            Assert.fail("Exception in TC timeLimitOptionEnableAndTimeLimitExhausted of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void timeLimitOptionEnableAndTimeLimitExhaustedPolicyThree(){
        WebDriver driver= Driver.getWebDriver();

        try {
            String dataIndex="6";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            String timeDuration = ReadTestData.readDataByTagName("", "timeDuration", dataIndex);
            String updateTime = ReadTestData.readDataByTagName("", "updateTime", dataIndex);

            new Assignment().create(6);
            new Assignment().addQuestions(6,"essay","");

            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");

            new LoginUsingLTI().ltiLogin("6_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("6_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("6");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, true, "1", "", "Release as they are being graded", "", "", "","6");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(6);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");


            new LoginUsingLTI().ltiLogin("6_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            new AttemptQuestion().trueFalse(false,"correct",1);
            new Assignment().nextButtonInQuestionClick();
            Thread.sleep(72000); //wait
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");


            new LoginUsingLTI().ltiLogin("6");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Assignment().updateTimeValueOfTimedAssignment(6);

            new LoginUsingLTI().ltiLogin("6_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");

            CustomAssert.assertTrue(currentAssignments.timedAssignmentHelpInfo_icon.isDisplayed(), "verify timedAssignmentHelpInfo_icon", "timedAssignmentHelpInfo_icon is displaying", "timedAssignmentHelpInfo_icon is not displaying");
            currentAssignments.timedAssignmentHelpInfo_icon.click();
            Thread.sleep(2000);
            updateTime = ReadTestData.readDataByTagName("", "updateTime", dataIndex);

            int actualTime=Integer.parseInt(updateTime);
            actual=assignments.timeAssignmentHelp_msg.getText().trim();
            expected="This is a timed assignment that must be completed in one continuous session. Your time limit for this assignment is "+(actualTime-1)+" minutes.";
            CustomAssert.assertEquals(actual, expected, "Verify timeAssignmentHelp_msg", "timeAssignmentHelp_msg is  " + expected + "", "timeAssignmentHelp_msg is not" + expected + "");

            actual =currentAssignments.timedAssignmentMinute.get(0).getText().trim();
            expected=(actualTime-1)+" minutes";
            CustomAssert.assertEquals(actual, expected, "Verify Changed time limit value", "Changed time limit value should be displayed after Timed Assignment label.", "Changed time limit value is not displayed after Timed Assignment label.");

            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName()); //click on the timed assignments
            actual =assignments.timeAssignment_header.getText().trim();
            expected="Timed Assignment";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment header", "time assignment header is  " + expected + "", "time assignment header is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(0).getText().trim();
            expected="Once you begin, you will have "+(actualTime-1)+" minutes to complete this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(1).getText().trim();
            expected="If you log out, or navigate away from this assignment, the timer will continue to run.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            actual =assignments.timeAssignmentNotification.get(2).getText().trim();
            expected="Would you like to start the assignment now?";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            Thread.sleep(1000);

            actual =assignments.question_index.getText().trim();
            expected="Q 2:";
            CustomAssert.assertEquals(actual, expected, "Verify question index", "User has navigated to next question of last attempted question","User has not navigated to next question of last attempted question");

            String timeLeft=assignments.timer.getAttribute("timetaken").trim();
            System.out.println("timeLeft:"+timeLeft);
            actual =assignments.timer.getAttribute("timetaken").trim();
            if(!(actual.equals("120")||actual.equals("119")||actual.equals("118")||actual.equals("117")||actual.equals("116"))){
                CustomAssert.fail("Verify extented time","Remaining is getting start with old  time limit");
            }

            new AttemptQuestion().essay(false, "correct", 1);
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.finish_button); //click on the finish button
            Thread.sleep(2000);
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getGoToNextLinkOnPopUp());
            actual = performanceSummaryReport.chartTitle.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            String questionNo = performanceSummaryReport.performanceChart_questionNo.getText().trim();
            CustomAssert.assertEquals(questionNo, "2\nQuestions", "Verify Question Number", "Attempted Number of Question should be  " + questionNo + "", "Attempted Number of Question not displaying correctly");

            new LoginUsingLTI().ltiLogin("6_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            CustomAssert.assertTrue(currentAssignments.timedAssignmentHelpInfo_icon.isDisplayed(), "verify timedAssignmentHelpInfo_icon", "timedAssignmentHelpInfo_icon is displaying", "timedAssignmentHelpInfo_icon is not displaying");
            currentAssignments.timedAssignmentHelpInfo_icon.click();
            Thread.sleep(2000);
            actual=assignments.timeAssignmentHelp_msg.getText().trim();
            expected="This is a timed assignment that must be completed in one continuous session. Your time limit for this assignment is "+timeDuration+" minutes.";
            CustomAssert.assertEquals(actual, expected, "Verify timeAssignmentHelp_msg", "timeAssignmentHelp_msg is  " + expected + "", "timeAssignmentHelp_msg is not" + expected + "");

            actual =currentAssignments.timedAssignmentMinute.get(0).getText().trim();
            expected=timeDuration+" minutes";
            CustomAssert.assertEquals(actual, expected, "Verify Changed time limit value", "Changed time limit value should be displayed after Timed Assignment label.", "Changed time limit value is not displayed after Timed Assignment label.");

        } catch (Exception e) {
            Assert.fail("Exception in TC timeLimitOptionEnableAndTimeLimitExhaustedPolicyThree of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 7,enabled = true)
    public void timeLimitAndExtensionDueDateOptionEnableAndTimeLimitExhausted(){
        WebDriver driver= Driver.getWebDriver();

        try {
            String dataIndex="7";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            String timeDuration = ReadTestData.readDataByTagName("", "timeDuration", dataIndex);
            String updateTime = ReadTestData.readDataByTagName("", "updateTime", dataIndex);

            new Assignment().create(7);
            new Assignment().addQuestions(7,"truefalse","");

            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");

            new LoginUsingLTI().ltiLogin("7_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("7_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("7");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, true, "1", "", "Auto-release on assignment submission", "", "", "","7");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(7);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");


            new LoginUsingLTI().ltiLogin("7_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            new AttemptQuestion().trueFalse(false,"correct",1);
            new Assignment().nextButtonInQuestionClick();
            Thread.sleep(180000); //wait till due date expiry

            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            actual =assignments.timeElapsed_message.getText().trim();
            expected="The due date for this assignment has expired.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");


            new LoginUsingLTI().ltiLogin("7");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Assignment().extendDueDateWithDefaultClassSection("7");

            new LoginUsingLTI().ltiLogin("7_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");


            new LoginUsingLTI().ltiLogin("7");
            new Assignment().updateTimeValueOfTimedAssignment(7);

            new LoginUsingLTI().ltiLogin("7_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());

            updateTime = ReadTestData.readDataByTagName("", "updateTime", dataIndex);

            int actualTime=Integer.parseInt(updateTime);
            actual =assignments.timeAssignmentNotification.get(0).getText().trim();
            expected="Once you begin, you will have "+(actualTime-4)+" minutes to complete this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            new LoginUsingLTI().ltiLogin("7_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            CustomAssert.assertTrue(currentAssignments.timedAssignmentHelpInfo_icon.isDisplayed(), "verify timedAssignmentHelpInfo_icon", "timedAssignmentHelpInfo_icon is displaying", "timedAssignmentHelpInfo_icon is not displaying");
            currentAssignments.timedAssignmentHelpInfo_icon.click();
            Thread.sleep(2000);
            actual=assignments.timeAssignmentHelp_msg.getText().trim();
            expected="This is a timed assignment that must be completed in one continuous session. Your time limit for this assignment is "+timeDuration+" minutes.";
            CustomAssert.assertEquals(actual, expected, "Verify timeAssignmentHelp_msg", "timeAssignmentHelp_msg is  " + expected + "", "timeAssignmentHelp_msg is not" + expected + "");

            actual =currentAssignments.timedAssignmentMinute.get(0).getText().trim();
            expected=timeDuration+" minutes";
            CustomAssert.assertEquals(actual, expected, "Verify Changed time limit value", "Changed time limit value should be displayed after Timed Assignment label.", "Changed time limit value is not displayed after Timed Assignment label.");

        } catch (Exception e) {
            Assert.fail("Exception in TC timeLimitAndExtensionDueDateOptionEnableAndTimeLimitExhausted of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 7,enabled = true)
    public void timeLimitAndExtensionDueDateOptionEnableAndTimeLimitExhaustedPolicyTwo(){
        WebDriver driver= Driver.getWebDriver();

        try {
            String dataIndex="8";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            String timeDuration = ReadTestData.readDataByTagName("", "timeDuration", dataIndex);
            String updateTime = ReadTestData.readDataByTagName("", "updateTime", dataIndex);

            new Assignment().create(8);
            new Assignment().addQuestions(8,"truefalse","");

            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");

            new LoginUsingLTI().ltiLogin("8_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("8_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("8");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, true, "1", "", "Auto-release on assignment submission", "", "", "","8");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(8);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");


            new LoginUsingLTI().ltiLogin("8_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            new AttemptQuestion().trueFalse(false,"correct",1);
            new Assignment().nextButtonInQuestionClick();
            Thread.sleep(180000); //wait till due date expiry

            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            actual =assignments.timeElapsed_message.getText().trim();
            expected="The due date for this assignment has expired.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");


            new LoginUsingLTI().ltiLogin("8");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Assignment().extendDueDateWithDefaultClassSection("8");

            new LoginUsingLTI().ltiLogin("8_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");


            new LoginUsingLTI().ltiLogin("8");
            new Assignment().updateTimeValueOfTimedAssignment(8);

            new LoginUsingLTI().ltiLogin("8_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());

            updateTime = ReadTestData.readDataByTagName("", "updateTime", dataIndex);

            int actualTime=Integer.parseInt(updateTime);
            actual =assignments.timeAssignmentNotification.get(0).getText().trim();
            expected="Once you begin, you will have "+(actualTime-4)+" minutes to complete this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            new LoginUsingLTI().ltiLogin("8_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            CustomAssert.assertTrue(currentAssignments.timedAssignmentHelpInfo_icon.isDisplayed(), "verify timedAssignmentHelpInfo_icon", "timedAssignmentHelpInfo_icon is displaying", "timedAssignmentHelpInfo_icon is not displaying");
            currentAssignments.timedAssignmentHelpInfo_icon.click();
            Thread.sleep(2000);
            actual=assignments.timeAssignmentHelp_msg.getText().trim();
            expected="This is a timed assignment that must be completed in one continuous session. Your time limit for this assignment is "+timeDuration+" minutes.";
            CustomAssert.assertEquals(actual, expected, "Verify timeAssignmentHelp_msg", "timeAssignmentHelp_msg is  " + expected + "", "timeAssignmentHelp_msg is not" + expected + "");

            actual =currentAssignments.timedAssignmentMinute.get(0).getText().trim();
            expected=timeDuration+" minutes";
            CustomAssert.assertEquals(actual, expected, "Verify Changed time limit value", "Changed time limit value should be displayed after Timed Assignment label.", "Changed time limit value is not displayed after Timed Assignment label.");

        } catch (Exception e) {
            Assert.fail("Exception in TC timeLimitAndExtensionDueDateOptionEnableAndTimeLimitExhaustedPolicyTwo of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 8,enabled = true)
    public void updateDifferentTimeLimitForDifferentStudent(){
        WebDriver driver= Driver.getWebDriver();

        try {
            String dataIndex="9";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            String updateTime = ReadTestData.readDataByTagName("", "updateTime", dataIndex);
            new Assignment().create(9);

            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");
            new LoginUsingLTI().ltiLogin("9_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("9_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("9_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("9");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, true, "1", "", "Auto-release on assignment submission", "", "", "","9");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(9);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("9");
            String firstTimeLimit=new Assignment().updateTimeValueOfTimedAssignment(9);
            String secondTimeLimit=new Assignment().updateTimeValueOfTimedAssignment(10);
            String thirdTimeLimit=new Assignment().updateTimeValueOfTimedAssignment(11);

            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            String actualFirstTimeLimit=assignments.assignedTimeLimit.get(0).getAttribute("data-time-limit").trim();

            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(1));
            String actualSecondTimeLimit=assignments.assignedTimeLimit.get(1).getAttribute("data-time-limit").trim();

            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(2));
            String actualThirdTimeLimit=assignments.assignedTimeLimit.get(2).getAttribute("data-time-limit").trim();

            CustomAssert.assertEquals(actualFirstTimeLimit,firstTimeLimit,"Verify updated time value","updated time limit value is correct for first student","updated time limit value is not correct for first student");
            CustomAssert.assertEquals(actualSecondTimeLimit,secondTimeLimit,"Verify updated time value","updated time limit value is correct for second student","updated time limit value is not correct for second student");
            CustomAssert.assertEquals(actualThirdTimeLimit,thirdTimeLimit,"Verify updated time value","updated time limit value is correct for third student","updated time limit value is not correct for third student");

            new LoginUsingLTI().ltiLogin("9_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            Thread.sleep(5000);
            WebDriverUtil.clickOnElementUsingJavascript(assignments.policyName);//click on the policy name
            new ScrollElement().scrollToElementUsingCoordinates(policies.timeLimit_policyPage);
            actual =policies.timeLimit_policyPage.getText().trim();
            expected="The total time limit for this assignment is "+updateTime+" minute(s).";
            CustomAssert.assertEquals(actual, expected, "verify the time limit entry on the policy page.", "time limit entry on the policy page is  " + expected + "", "time limit entry on the policy page not " + expected + "");

            new LoginUsingLTI().ltiLogin("9_2");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            Thread.sleep(5000);
            updateTime = ReadTestData.readDataByTagName("", "updateTime", "10");

            WebDriverUtil.clickOnElementUsingJavascript(assignments.policyName);//click on the policy name
            new ScrollElement().scrollToElementUsingCoordinates(policies.timeLimit_policyPage);
            actual =policies.timeLimit_policyPage.getText().trim();
            expected="The total time limit for this assignment is "+updateTime+" minute(s).";
            CustomAssert.assertEquals(actual, expected, "verify the time limit entry on the policy page.", "time limit entry on the policy page is  " + expected + "", "time limit entry on the policy page not " + expected + "");


            new LoginUsingLTI().ltiLogin("9_3");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            Thread.sleep(5000);

            updateTime = ReadTestData.readDataByTagName("", "updateTime", "11");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.policyName);//click on the policy name
            new ScrollElement().scrollToElementUsingCoordinates(policies.timeLimit_policyPage);
            actual =policies.timeLimit_policyPage.getText().trim();
            expected="The total time limit for this assignment is "+updateTime+" minute(s).";
            CustomAssert.assertEquals(actual, expected, "verify the time limit entry on the policy page.", "time limit entry on the policy page is  " + expected + "", "time limit entry on the policy page not " + expected + "");


        } catch (Exception e) {
            Assert.fail("Exception in TC updateDifferentTimeLimitForDifferentStudent of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 9,enabled = true)
    public void dueDateLessThanTimeLimit(){
        WebDriver driver= Driver.getWebDriver();

        try {

            String dataIndex="13";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            new Assignment().create(13);
            new Assignment().addQuestions(13,"truefalse","");

            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");

            new LoginUsingLTI().ltiLogin("13_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("13");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "","13");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(13);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("13_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();
            Thread.sleep(120000); //wait till due date expiry
            WebDriverUtil.clickOnElementUsingJavascript(assignments.closeDueDate_button);
            actual =assignments.timeElapsed_message.getText().trim();
            expected="The due date for this assignment has expired.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");
            CustomAssert.assertEquals(assignments.status_inProgress.getText().trim(),"In Progress","Validating 'Your Status:' Value","'Your Status:' Value is displayed as 'In Progress' in Assignments Page","'Your Status:' Value is not displayed as 'In Progress' in Assignments Page");


        } catch (Exception e) {
            Assert.fail("Exception in TC dueDateLessThanTimeLimit of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 10,enabled = true)
    public void timeLimitLessThanDueDate(){
        WebDriver driver= Driver.getWebDriver();

        try {

            String dataIndex="14";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            new Assignment().create(14);
            new Assignment().addQuestions(14,"truefalse","");

            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");
            new LoginUsingLTI().ltiLogin("14_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("14");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, true, "1", "", "Auto-release on assignment submission", "", "", "","14");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(14);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("14_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();
            Thread.sleep(120000); //wait till due date expiry
            //new Assignment().submitButtonInQuestionClick();
            //WebDriverUtil.clickOnElementUsingJavascript(assignments.closeDueDate_button);
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");
            CustomAssert.assertEquals(assignments.status_inProgress.getText().trim(),"In Progress","Validating 'Your Status:' Value","'Your Status:' Value is displayed as 'In Progress' in Assignments Page","'Your Status:' Value is not displayed as 'In Progress' in Assignments Page");

            new LoginUsingLTI().ltiLogin("14");
            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            currentAssignments.getViewGrade_link().click(); //click on the view response link
            boolean unAttemptedQuestion = (boolean) ((JavascriptExecutor) driver).executeScript("return document.getElementsByClassName('idb-gradebook-content-coloumn-not-attempted')[0].hasAttribute('points');");
            CustomAssert.assertFalse(unAttemptedQuestion,"auto-submitted","Unattempted question should not get auto-submitted","Unattempted question is getting auto-submitted");

        } catch (Exception e) {
            Assert.fail("Exception in TC timeLimitLessThanDueDate of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 11,enabled = true)
    public void performanceSummaryOfPolicyOne(){
        WebDriver driver= Driver.getWebDriver();

        try {

            String dataIndex="15";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            new Assignment().create(15);

            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");
            new LoginUsingLTI().ltiLogin("15_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("15");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "","15");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(15);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("15_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            new AttemptQuestion().trueFalse(false, "correct", 1);
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.finish_button); //click on the finish button
            Thread.sleep(2000);
            actual = performanceSummaryReport.chartTitle.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            String questionNo = performanceSummaryReport.performanceChart_questionNo.getText().trim();
            CustomAssert.assertEquals(questionNo, "1\nQuestions", "Verify Question Number", "Attempted Number of Question should be  " + questionNo + "", "Attempted Number of Question not displaying correctly");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            String score=assignments.getScore().getText();
            expected = "Score (2/2)";
            CustomAssert.assertEquals(score, expected, "Verify score", "Assignment is getting Graded", "Assignment is not getting Graded");

            new LoginUsingLTI().ltiLogin("15");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Class Assignments");  //navigate to Current Assignments
            new Assignment().verifyClassAssignmentStatus(15, "Available for Students");

            currentAssignments.getViewGrade_link().click(); //click on the view response student link
            Thread.sleep(3000);
            new Assignment().verifyClassAssignmentStatus(15, "Available for Students");

            Assert.assertEquals(new Assignment().statusBoxCount(15, "Graded"),1,"Student status \"Graded\" count is not 1");

        } catch (Exception e) {
            Assert.fail("Exception in TC performanceSummaryOfPolicyOne of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 11,enabled = true)
    public void performanceSummaryOfPolicyTwo(){
        WebDriver driver= Driver.getWebDriver();

        try {

            String dataIndex="16";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            new Assignment().create(16);

            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");
            new LoginUsingLTI().ltiLogin("16_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("16");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description118", "2", null, false, "1", "", "Auto-release on due date", "", "", "","16");//policy 2
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(16);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("16_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            new AttemptQuestion().trueFalse(false, "correct", 1);
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.finish_button); //click on the finish button
            Thread.sleep(2000);
            actual = performanceSummaryReport.chartTitle.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            String questionNo = performanceSummaryReport.performanceChart_questionNo.getText().trim();
            CustomAssert.assertEquals(questionNo, "1\nQuestions", "Verify Question Number", "Attempted Number of Question should be  " + questionNo + "", "Attempted Number of Question not displaying correctly");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().verifyClassAssignmentStatus(16, "Submitted");

            new LoginUsingLTI().ltiLogin("16");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Class Assignments");  //navigate to Current Assignments

            int graded = new Assignment().statusBoxCount(16, "Graded");
            Assert.assertEquals(graded, 1, "Student status \"Graded\" count is not 1");

            currentAssignments.getViewGrade_link().click(); //click on the view response student link
            graded = new Assignment().statusBoxCount(16, "Graded");
            Thread.sleep(3000);
            Assert.assertEquals(graded, 1, "Student status \"Graded\" count is not 1");


        } catch (Exception e) {
            Assert.fail("Exception in TC performanceSummaryOfPolicyTwo of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 12,enabled = true)
    public void performanceSummaryOfPolicyThree(){
        WebDriver driver= Driver.getWebDriver();

        try {

            String dataIndex="17";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            new Assignment().create(17);

            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");
            new LoginUsingLTI().ltiLogin("17_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("17");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "", "17");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(17);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("17_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            new AttemptQuestion().essay(false, "correct", 1);
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.finish_button); //click on the finish button
            Thread.sleep(2000);

            actual = performanceSummaryReport.chartTitle.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            String questionNo = performanceSummaryReport.performanceChart_questionNo.getText().trim();
            CustomAssert.assertEquals(questionNo, "1\nQuestions", "Verify Question Number", "Attempted Number of Question should be  " + questionNo + "", "Attempted Number of Question not displaying correctly");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().verifyClassAssignmentStatus(17, "Submitted");

            new LoginUsingLTI().ltiLogin("17");
            new Assignment().provideGradeToStudentForMultipleQuestions(17);
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");

            new Navigator().NavigateTo("Class Assignments");  //navigate to Current Assignments
            int graded = new Assignment().statusBoxCount(17, "Graded");
            Assert.assertEquals(graded, 1, "Student status \"Graded\" count is not 1");

            currentAssignments.getViewGrade_link().click(); //click on the view response student link
            assessmentResponses.refresh_button.click();
            graded = new Assignment().statusBoxCount(17, "Graded");
            Thread.sleep(3000);
            Assert.assertEquals(graded, 1, "Student status \"Graded\" count is not 1");

            new LoginUsingLTI().ltiLogin("17_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            String score=assignments.getScore().getText();
            expected = "Score (0.7/2)";
            CustomAssert.assertEquals(score, expected, "Verify score", "Assignment is getting Graded", "Assignment is not getting Graded");


        } catch (Exception e) {
            Assert.fail("Exception in TC performanceSummaryOfPolicyThree of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 14,enabled = true)
    public void performanceSummaryOfPolicyFour(){
        WebDriver driver= Driver.getWebDriver();

        try {

            String dataIndex="18";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            new Assignment().create(18);

            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");
            new LoginUsingLTI().ltiLogin("18_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("18");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description21", "2", null, false, "1", "", "", "", "", "", "18");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(18);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("18_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            new AttemptQuestion().trueFalse(false, "correct", 1);
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.finish_button); //click on the finish button
            Thread.sleep(2000);

            actual = performanceSummaryReport.chartTitle.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            String questionNo = performanceSummaryReport.performanceChart_questionNo.getText().trim();
            CustomAssert.assertEquals(questionNo, "1\nQuestions", "Verify Question Number", "Attempted Number of Question should be  " + questionNo + "", "Attempted Number of Question not displaying correctly");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().verifyClassAssignmentStatus(18, "Submitted");

            new LoginUsingLTI().ltiLogin("18");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Class Assignments");  //navigate to Current Assignments
            int graded = new Assignment().statusBoxCount(18, "Graded");
            Assert.assertEquals(graded, 1, "Student status \"Graded\" count is not 1");

            currentAssignments.getViewGrade_link().click(); //click on the view response student link
            assessmentResponses.refresh_button.click();
            graded = new Assignment().statusBoxCount(18, "Graded");
            Thread.sleep(3000);
            Assert.assertEquals(graded, 1, "Student status \"Graded\" count is not 1");



        } catch (Exception e) {
            Assert.fail("Exception in TC performanceSummaryOfPolicyOne of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 15,enabled = false)
    public void multipleNumberOfAttempt(){
        WebDriver driver= Driver.getWebDriver();

        try {

            String dataIndex="19";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            new Assignment().create(19);

            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");
            new LoginUsingLTI().ltiLogin("19_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");

            new LoginUsingLTI().ltiLogin("19");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description21", "2", null, true, "2", "", "", "", "", "", "19");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(19);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("19_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            new AttemptQuestion().trueFalse(false, "incorrect", 1);
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.finish_button); //click on the finish button
            Thread.sleep(112000);
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getReAttemptLinkOnPopUp());
            WebDriverUtil.clickOnElementUsingJavascript(assignments.closeDueDate_button);

            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");

            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");


        } catch (Exception e) {
            Assert.fail("Exception in TC multipleNumberOfAttempt of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 16,enabled = true)
    public void updatePolicyWithTimeLimitEnable(){
        WebDriver driver= Driver.getWebDriver();

        try {

            String dataIndex="20";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            new Assignment().create(20);

            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");
            new LoginUsingLTI().ltiLogin("20_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("20_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("20");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description21", "2", null, false, "1", "", "", "", "", "", "20");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(20);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");
            Thread.sleep(120000);

            new LoginUsingLTI().ltiLogin("20_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            actual =assignments.timeElapsed_message.getText().trim();
            expected="The due date for this assignment has expired.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");


            new LoginUsingLTI().ltiLogin("20");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Class Assignments");

            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            Thread.sleep(2000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.assignmentPolicy_Link.click();
            new Click().clickbylinkText("Create a new Assignment Policy");
            new ScrollElement().scrollToElementUsingCoordinates(policies.timeLimit_disable);
            boolean disable=policies.timeLimit_disable.isSelected();
            System.out.println(disable);
            CustomAssert.assertTrue(disable, "verify disable option", "Disable should be selected for assignment policies by default", "By default Disable is not selected for assignment policies ");
            new AssignmentPolicy().enterPolicyName("updated policy");
            new AssignmentPolicy().enterPolicyDescription("updated policy description");
            policies.score_textBox.sendKeys("1");
            WebDriverUtil.clickOnElementUsingJavascript(policies.getAssignmentPolicySaveButton());
            Thread.sleep(5000);
            driver.findElement(By.id("due-date")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']"));
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            new UIElement().waitAndFindElement(By.linkText("9"));
            driver.findElement(By.linkText("9")).click();
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getAssign());
            //WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.yesLinkAfterAssign); //click on the yes link

            new LoginUsingLTI().ltiLogin("20_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            new AttemptQuestion().trueFalse(false, "correct", 1);
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.finish_button); //click on the finish button
            WebDriverUtil.clickOnElementUsingJavascript(assignments.goToNextLinkOnPopUp);


            Thread.sleep(2000);
            actual = performanceSummaryReport.chartTitle.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            String questionNo = performanceSummaryReport.performanceChart_questionNo.getText().trim();
            CustomAssert.assertEquals(questionNo, "1\nQuestions", "Verify Question Number", "Attempted Number of Question should be  " + questionNo + "", "Attempted Number of Question not displaying correctly");

            new LoginUsingLTI().ltiLogin("20_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            new AttemptQuestion().trueFalse(false, "correct", 1);
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.finish_button); //click on the finish button
            WebDriverUtil.clickOnElementUsingJavascript(assignments.goToNextLinkOnPopUp);


            Thread.sleep(2000);
            actual = performanceSummaryReport.chartTitle.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            questionNo = performanceSummaryReport.performanceChart_questionNo.getText().trim();
            CustomAssert.assertEquals(questionNo, "1\nQuestions", "Verify Question Number", "Attempted Number of Question should be  " + questionNo + "", "Attempted Number of Question not displaying correctly");


        } catch (Exception e) {
            Assert.fail("Exception in TC updatePolicyWithTimeLimitEnable of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 17,enabled = true)
    public void updatePolicyWithTimeLimitDisableAndDueDateEnable(){
        WebDriver driver= Driver.getWebDriver();

        try {


            String dataIndex="21";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            String updateTime = ReadTestData.readDataByTagName("", "updateTime", dataIndex);

            new Assignment().create(21);

            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");
            new LoginUsingLTI().ltiLogin("21_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("21_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("21");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description21", "2", null, false, "1", "", "", "", "", "", "21");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(21);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");
            Thread.sleep(120000);

            new LoginUsingLTI().ltiLogin("21_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            actual =assignments.timeElapsed_message.getText().trim();
            expected="The due date for this assignment has expired.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");



            new LoginUsingLTI().ltiLogin("21");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Class Assignments");

            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            Thread.sleep(2100);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.assignmentPolicy_Link.click();
            new Click().clickbylinkText("Create a new Assignment Policy");
            new ScrollElement().scrollToElementUsingCoordinates(policies.timeLimit_disable);
            new AssignmentPolicy().enterPolicyName("updated policy");
            new AssignmentPolicy().enterPolicyDescription("updated policy description");
            policies.score_textBox.sendKeys("1");
            WebDriverUtil.clickOnElementUsingJavascript(policies.timeLimit_enable);
            policies.timeDuration_textBox.sendKeys(updateTime);
            WebDriverUtil.clickOnElementUsingJavascript(policies.getAssignmentPolicySaveButton());
            Thread.sleep(50000);
            driver.findElement(By.id("due-date")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']"));
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            new UIElement().waitAndFindElement(By.linkText("9"));
            driver.findElement(By.linkText("9")).click();
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getAssign());
           // WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.yesLinkAfterAssign);


            new LoginUsingLTI().ltiLogin("21_2");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link

            new AttemptQuestion().trueFalse(false, "correct", 1);
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.finish_button); //click on the finish button
            WebDriverUtil.clickOnElementUsingJavascript(assignments.goToNextLinkOnPopUp);

            Thread.sleep(2000);
            actual = performanceSummaryReport.chartTitle.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            String questionNo = performanceSummaryReport.performanceChart_questionNo.getText().trim();
            CustomAssert.assertEquals(questionNo, "1\nQuestions", "Verify Question Number", "Attempted Number of Question should be  " + questionNo + "", "Attempted Number of Question not displaying correctly");


        } catch (Exception e) {
            Assert.fail("Exception in TC updatePolicyWithTimeLimitDisableAndDueDateEnable of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 18,enabled = true)
    public void attemptNotApplicableAssignment(){
        WebDriver driver= Driver.getWebDriver();

        try {

            String dataIndex="22";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);

            new Assignment().create(22);

            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");
            new LoginUsingLTI().ltiLogin("22_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("22");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description22", "2", null, false, "1", "", "", "", "", "", "22");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(22);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("22");
            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on the view response link
            Thread.sleep(4000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.userName_checkBox);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.notApplicable_button);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.notApplicable_yes);

            new LoginUsingLTI().ltiLogin("22_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");

            CustomAssert.assertTrue(assignments.notApplicable_icon.isDisplayed(), "verify not applicable label", "Not applicable icon is  present", "Not applicable icon is not present");
            new Assignment().assignAssignmentFromAssignmentTab(0,assessmentName);
            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            new AttemptQuestion().trueFalse(false, "correct", 1);
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.finish_button); //click on the finish button

            Thread.sleep(2000);
            actual = performanceSummaryReport.chartTitle.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            String questionNo = performanceSummaryReport.performanceChart_questionNo.getText().trim();
            CustomAssert.assertEquals(questionNo, "1\nQuestions", "Verify Question Number", "Attempted Number of Question should be  " + questionNo + "", "Attempted Number of Question not displaying correctly");

        } catch (Exception e) {
            Assert.fail("Exception in TC attemptNotApplicableAssignment of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 19,enabled = true)
    public void timeLimitEnableOfMPQQuestion(){
        WebDriver driver= Driver.getWebDriver();

        try {

            String dataIndex="23";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);

            new Assignment().create(23);

            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");
            new LoginUsingLTI().ltiLogin("23_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("23");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description23", "2", null, false, "1", "", "", "", "", "", "23");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(23);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("23_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            preview.trueFalseAnswerLabel.get(0).click();//click on option 'A'
            preview.submitPart_link.click();
            WebDriverUtil.clickOnElementUsingJavascript(preview.multipleSelection.get(0));//click on option 'A'
            new ScrollElement().scrollToElementUsingCoordinates(preview.lastSubmitPart_link);
            WebDriverUtil.clickOnElementUsingJavascript(preview.lastSubmitPart_link);
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.finish_button); //click on the finish button

            Thread.sleep(8000);
            String s1=performanceTab.attemptedTime.get(0).getText().trim();
            String s2=performanceTab.attemptedTime.get(1).getText().trim();
            String s3=s1.substring(0,s1.length()-1);
            String s4=s2.substring(0,s2.length()-1);
            String timeTaken=String.valueOf(Integer.parseInt(s3) + Integer.parseInt(s4));
            System.out.println("timeTaken:"+timeTaken);
            new LoginUsingLTI().ltiLogin("23");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on the view response link
            new MouseHover().mouserhoverbywebelement(assessmentResponses.studentName.get(0));
            String actualTimeTaken=assignmentResponsesPage.timeSpentByStudent.getText().trim();
            System.out.println(actualTimeTaken);
            //String str="Time spent : 0 min(s) 6 sec(s)";
            int size=actualTimeTaken.indexOf(")");
            String exactTime=actualTimeTaken.substring(size + 1, actualTimeTaken.indexOf("sec(s)")).trim();
            CustomAssert.assertEquals(exactTime,timeTaken.trim(),"Verify time taken by student to attempt answer","Time spent in Performance Summary and Assignments Response page is equal.","Time spent in Performance Summary and Assignments Response page is not equal.");

        } catch (Exception e) {
            Assert.fail("Exception in TC timeLimitEnableOfMPQQuestion of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 20,enabled = true)
    public void verifyUpdatedTime(){
        WebDriver driver= Driver.getWebDriver();

        try {

            String dataIndex="24";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            String updateTime = ReadTestData.readDataByTagName("", "updateTime", dataIndex);

            new Assignment().create(24);

            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");
            new LoginUsingLTI().ltiLogin("24_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("24");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description24", "2", null, false, "1", "", "", "", "", "", "24");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(24);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");

            new LoginUsingLTI().ltiLogin("24_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link

            Thread.sleep(120000); //wait
            actual =assignments.timeElapsed_message.getText().trim();
            expected="You have used up all your available time for this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time elapsed", "time elapsed is  " + expected + "", "time elapsed is not " + expected + "");

            new LoginUsingLTI().ltiLogin("25");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Assignment().updateTimeValueOfTimedAssignment(25);

            new LoginUsingLTI().ltiLogin("24_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());

            String timeDuration = ReadTestData.readDataByTagName("", "timeDuration", "24");
            updateTime = ReadTestData.readDataByTagName("", "updateTime", "25");

            int actualTime=Integer.parseInt(timeDuration);
            int updateTimes=Integer.parseInt(updateTime);

            actual =assignments.timeAssignmentNotification.get(0).getText().trim();
            expected="Once you begin, you will have "+(updateTimes-actualTime)+" minutes to complete this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            new LoginUsingLTI().ltiLogin("26");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Assignment().updateTimeValueOfTimedAssignment(26);

            new LoginUsingLTI().ltiLogin("24_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());

            timeDuration = ReadTestData.readDataByTagName("", "timeDuration", "24");
            updateTime = ReadTestData.readDataByTagName("", "updateTime", "26");

            actualTime=Integer.parseInt(timeDuration);
            updateTimes=Integer.parseInt(updateTime);

            actual =assignments.timeAssignmentNotification.get(0).getText().trim();
            expected="Once you begin, you will have "+(updateTimes-actualTime)+" minutes to complete this assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify time assignment notification", "time assignment notification is  " + expected + "", "time assignment notification is not " + expected + "");

            WebDriverUtil.clickOnElementUsingJavascript(assignments.timeAssignment_yesLink); //click on the yes link
            new AttemptQuestion().trueFalse(false, "correct", 1);
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.finish_button); //click on the finish button
           // WebDriverUtil.clickOnElementUsingJavascript(assignments.goToNextLinkOnPopUp);

            Thread.sleep(2000);
            actual = performanceSummaryReport.chartTitle.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            String questionNo = performanceSummaryReport.performanceChart_questionNo.getText().trim();
            CustomAssert.assertEquals(questionNo, "1\nQuestions", "Verify Question Number", "Attempted Number of Question should be  " + questionNo + "", "Attempted Number of Question not displaying correctly");

        } catch (Exception e) {
            Assert.fail("Exception in TC verifyUpdatedTime of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }

    @Test(priority = 21,enabled = true)
    public void assignAssignmentFromDifferentChapter(){
        WebDriver driver= Driver.getWebDriver();

        try {

             String dataIndex="27";
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            String updateTime = ReadTestData.readDataByTagName("", "updateTime", dataIndex);
            String customAssignmentName = ReadTestData.readDataByTagName("", "customAssignmentName", dataIndex);

            ReportUtil.log("Create an assessment", "Assessment created successfully", "Pass");
            new LoginUsingLTI().ltiLogin("27_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("27");
            ReportUtil.log("Login as instructor", "instructor logged successfully", "info");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description27", "2", null, false, "1", "", "", "", "", "", "27");
            ReportUtil.log("Navigated to policy", "Created policy with release option one", "Pass");

            new Navigator().NavigateTo("My Assignments");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            new SearchQuestionInCustomCourseAssignemnt().selectQuestionFromDifferentChapter(1, dataIndex);
            new SearchQuestionInCustomCourseAssignemnt().selectQuestionFromDifferentChapter(2,dataIndex);
            new SearchQuestionInCustomCourseAssignemnt().selectQuestionFromDifferentChapter(5, dataIndex);
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(6000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(27);//assign assignment
            new LoginUsingLTI().ltiLogin("27_1");
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(4); //click on the 5th chapter
            WebDriverUtil.clickOnElementUsingJavascript(lessonPage.getForAssignmentName());
        } catch (Exception e) {
            Assert.fail("Exception in TC assignAssignmentFromDifferentChapter of class TimeLimitOptionEnableAndDueDateNotElapsed ",e);
        }
    }
}
