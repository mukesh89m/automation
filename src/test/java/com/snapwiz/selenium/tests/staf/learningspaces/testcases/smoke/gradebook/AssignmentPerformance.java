package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.gradebook;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyActivity.MyActivity;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.Filter;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.TocSearch;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 6/29/16.
 */
public class AssignmentPerformance extends Driver {


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
    Gradebook gradebook;
    Filter filter;
    TocSearch tocSearch;
    String actual = "";
    String expected = "";
    int actualSize = 0;
    int expectedSize = 0;

    @BeforeMethod
    public void initElement() {
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
        gradebook = PageFactory.initElements(driver, Gradebook.class);
        tocSearch= PageFactory.initElements(driver,TocSearch.class);
        lessonPage= PageFactory.initElements(driver, LessonPage.class);
        filter=PageFactory.initElements(driver,Filter.class);
    }

    @Test(priority = 1, enabled = true)
    public void instructorCanViewTheTextOnStudentPerformanceTab() {
        WebDriver driver = Driver.getWebDriver();
        try {

            ReportUtil.log("Description", "Test case validates instructor can see the text on student performance tab", "info");
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", "1");
            new Assignment().create(1);
            new Assignment().addQuestions(1, "multiplechoice", "");
            new Assignment().addQuestions(1, "multipleselection", "");
            new Assignment().addQuestions(1, "essay", "");


            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            new Assignment().assignAssignmentWithDueDate(1);
            for (int i = 2; i < 13; i++) {
                assignCustomAssignmentFromMyQuestionBank(1, i);
            }

            new Assignment().createFileBasedAssessmentAtInstructorSide(2);
            new Assignment().assignFileBasedAssignmentFromMyQuestionBank(2);

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            new Assignment().createDiscussionAssignment(3);
            new Assignment().assignDiscussionAssignmentToGroup(3);

            new LoginUsingLTI().ltiLogin("1_1");//log in as student
            new LoginUsingLTI().ltiLogin("1_2");//log in as student
            new LoginUsingLTI().ltiLogin("1_3");//log in as student
            new LoginUsingLTI().ltiLogin("1_4");//log in as student
            new LoginUsingLTI().ltiLogin("1_5");//log in as student

            new LoginUsingLTI().ltiLogin("1_1");//log in as student
            clickOnTheParticularAssignment("FileBasedAssignment_AssignmentPerformance_2");
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("1_2");//log in as student
            clickOnTheParticularAssignment("FileBasedAssignment_AssignmentPerformance_2");
            Thread.sleep(10000);
            assignmentTab.finishButton.click();//click on finish assignment

            new LoginUsingLTI().ltiLogin("1_3");//log in as student
            clickOnTheParticularAssignment("FileBasedAssignment_AssignmentPerformance_2");
            Thread.sleep(10000);
            assignmentTab.finishButton.click();//click on finish assignment

            new LoginUsingLTI().ltiLogin("1_1");//log in as student
            clickOnTheParticularAssignment("AssignmentPerformance_1");
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            new AttemptQuestion().multipleChoice(false, "incorrect", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            new AttemptQuestion().multipleSelection(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            new AttemptQuestion().essay(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("1_2");//log in as student
            clickOnTheParticularAssignment("AssignmentPerformance_1");
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next

            new LoginUsingLTI().ltiLogin("1_4");//log in as student
            clickOnTheParticularAssignment("AssignmentPerformance_1");
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            new AttemptQuestion().multipleChoice(false, "incorrect", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            new AttemptQuestion().multipleSelection(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            new AttemptQuestion().essay(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();


            new LoginUsingLTI().ltiLogin("1_2");//log in as student
            clickOnTheParticularAssignment("2GradableAssignment");
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            new AttemptQuestion().multipleChoice(false, "incorrect", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            new AttemptQuestion().multipleSelection(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            new AttemptQuestion().essay(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("1_3");//log in as student
            clickOnTheParticularAssignment("2GradableAssignment");
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next

            new LoginUsingLTI().ltiLogin("1_5");//log in as student
            clickOnTheParticularAssignment("2GradableAssignment");
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            new AttemptQuestion().multipleChoice(false, "incorrect", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            new AttemptQuestion().multipleSelection(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            new AttemptQuestion().essay(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();


            new LoginUsingLTI().ltiLogin("1_3");//log in as student
            clickOnTheParticularAssignment("3GradableAssignment");
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            new AttemptQuestion().multipleChoice(false, "incorrect", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            new AttemptQuestion().multipleSelection(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            new AttemptQuestion().essay(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("1_5");//log in as student
            clickOnTheParticularAssignment("3GradableAssignment");
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next

            new LoginUsingLTI().ltiLogin("1_2");//log in as student
            clickOnTheParticularAssignment("3GradableAssignment");
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            new AttemptQuestion().multipleChoice(false, "incorrect", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            new AttemptQuestion().multipleSelection(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            new AttemptQuestion().essay(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();


            new LoginUsingLTI().ltiLogin("1_1");//log in as student to create discussion assignment
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            Thread.sleep(5000);
            WebDriverUtil.scrollIntoView(assignments.resourceName, false);
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));


            new LoginUsingLTI().ltiLogin("1_4");//log in as student
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            Thread.sleep(5000);
            WebDriverUtil.scrollIntoView(assignments.resourceName, false);
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));


          /*  new LoginUsingLTI().ltiLogin("1_3");//log in as student
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            Thread.sleep(5000);
            WebDriverUtil.scrollIntoView(assignments.resourceName, false);
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);*/


            Thread.sleep(180000);
            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            clickOnParticularVSRP("5GradableAssignment");
            assessmentResponses.resumeGrading_button.click();//click on yes
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            Thread.sleep(1000);

            new Assignment().enterGradeOnParticularQuestion(0, 0, "0.5");
            new Assignment().enterGradeOnParticularQuestion(0, 1, "1.0");
            new Assignment().enterGradeOnParticularQuestion(0, 2, "0.5");
            new Assignment().enterGradeOnParticularQuestion(0, 3, "1.0");

            new Assignment().enterGradeOnParticularQuestion(1, 0, "0.5");
            new Assignment().enterGradeOnParticularQuestion(1, 1, "1.0");
            new Assignment().enterGradeOnParticularQuestion(1, 2, "0.5");
            new Assignment().enterGradeOnParticularQuestion(1, 3, "0.7");

            new Assignment().enterGradeOnParticularQuestion(2, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(2, 1, "1.0");
            new Assignment().enterGradeOnParticularQuestion(2, 2, "0.5");
            new Assignment().enterGradeOnParticularQuestion(2, 3, "1.0");

            new Assignment().enterGradeOnParticularQuestion(3, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(3, 1, "1.0");
            new Assignment().enterGradeOnParticularQuestion(3, 2, "1.0");
            new Assignment().enterGradeOnParticularQuestion(3, 3, "1.0");

            new Assignment().enterGradeOnParticularQuestion(4, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(4, 1, "1.0");
            new Assignment().enterGradeOnParticularQuestion(4, 2, "1.0");
            new Assignment().enterGradeOnParticularQuestion(4, 3, "1.0");

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            Thread.sleep(1000);
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            clickOnParticularVSRP("4GradableAssignment");
            assessmentResponses.resumeGrading_button.click();//click on yes
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            Thread.sleep(1000);

            new Assignment().enterGradeOnParticularQuestion(0, 0, "0.5");
            new Assignment().enterGradeOnParticularQuestion(0, 1, "1.0");
            new Assignment().enterGradeOnParticularQuestion(0, 2, "1.0");
            new Assignment().enterGradeOnParticularQuestion(0, 3, "1.0");

            new Assignment().enterGradeOnParticularQuestion(2, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(2, 1, "1.0");
            new Assignment().enterGradeOnParticularQuestion(2, 2, "1.0");
            new Assignment().enterGradeOnParticularQuestion(2, 3, "1.0");

            new Assignment().enterGradeOnParticularQuestion(3, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(3, 1, "1.0");
            new Assignment().enterGradeOnParticularQuestion(3, 2, "1.0");
            new Assignment().enterGradeOnParticularQuestion(3, 3, "1.0");

            new Assignment().enterGradeOnParticularQuestion(4, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(4, 1, "1.0");
            new Assignment().enterGradeOnParticularQuestion(4, 2, "1.0");
            new Assignment().enterGradeOnParticularQuestion(4, 3, "1.0");

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            clickOnParticularVSRP("2GradableAssignment");
            assessmentResponses.resumeGrading_button.click();//click on yes
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            Thread.sleep(1000);
            new Assignment().enterGradeOnParticularQuestion(3, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(3, 1, "1.0");
            new Assignment().enterGradeOnParticularQuestion(3, 2, "1.0");
            new Assignment().enterGradeOnParticularQuestion(3, 3, "1.0");

            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            clickOnParticularVSRP("3GradableAssignment");
            assessmentResponses.resumeGrading_button.click();//click on yes
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            Thread.sleep(1000);
            new Assignment().enterGradeOnParticularQuestion(3, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(3, 1, "1.0");
            new Assignment().enterGradeOnParticularQuestion(3, 2, "1.0");
            new Assignment().enterGradeOnParticularQuestion(3, 3, "1.0");

            new Assignment().enterGradeOnParticularQuestion(4, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(4, 1, "1.0");
            new Assignment().enterGradeOnParticularQuestion(4, 2, "1.0");
            new Assignment().enterGradeOnParticularQuestion(4, 3, "1.0");

            new Assignment().enterGradeOnParticularQuestion(3,0,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,1,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,2,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,3,"1.0");


            new Assignment().enterGradeOnParticularQuestion(4,0,"1.0");
            new Assignment().enterGradeOnParticularQuestion(4,1,"1.0");
            new Assignment().enterGradeOnParticularQuestion(4,2,"1.0");
            new Assignment().enterGradeOnParticularQuestion(4,3,"1.0");

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            clickOnParticularVSRP("AssignmentPerformance_1");
            assessmentResponses.resumeGrading_button.click();//click on yes
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            Thread.sleep(1000);
            new Assignment().enterGradeOnParticularQuestion(3, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(3, 1, "1.0");
            new Assignment().enterGradeOnParticularQuestion(3, 2, "1.0");
            new Assignment().enterGradeOnParticularQuestion(3, 3, "1.0");
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            clickOnParticularVSRP("FileBasedAssignment_AssignmentPerformance_2");
            assessmentResponses.resumeGrading_button.click();//click on yes
            Thread.sleep(1000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            Thread.sleep(1000);
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.allActivity_link);
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.discussionAssignment_link);
            currentAssignments.getViewGrade_link().click();
            assessmentResponses.resumeGrading_button.click();//click on yes
            Thread.sleep(1000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            Thread.sleep(1000);
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            clickOnParticularVSRP("6GradableAssignment");
            assessmentResponses.resumeGrading_button.click();//click on yes
            Thread.sleep(1000);

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            Thread.sleep(1000);
            new Assignment().enterGradeOnParticularQuestion(3,0,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,1,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,2,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,3,"1.0");

            Thread.sleep(1000);
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            clickOnParticularVSRP("7GradableAssignment");
            assessmentResponses.resumeGrading_button.click();//click on yes
            Thread.sleep(1000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            Thread.sleep(1000);
            new Assignment().enterGradeOnParticularQuestion(3,0,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,1,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,2,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,3,"1.0");

            Thread.sleep(1000);
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            clickOnParticularVSRP("8GradableAssignment");
            assessmentResponses.resumeGrading_button.click();//click on yes
            Thread.sleep(1000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            Thread.sleep(1000);
            new Assignment().enterGradeOnParticularQuestion(3,0,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,1,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,2,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,3,"1.0");

            new Assignment().enterGradeOnParticularQuestion(4,0,"1.0");
            new Assignment().enterGradeOnParticularQuestion(4,1,"1.0");
            new Assignment().enterGradeOnParticularQuestion(4,2,"1.0");
            new Assignment().enterGradeOnParticularQuestion(4,3,"1.0");

            Thread.sleep(1000);
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            clickOnParticularVSRP("9GradableAssignment");
            assessmentResponses.resumeGrading_button.click();//click on yes
            Thread.sleep(1000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            Thread.sleep(1000);
            new Assignment().enterGradeOnParticularQuestion(3,0,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,1,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,2,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,3,"1.0");

            new Assignment().enterGradeOnParticularQuestion(4,0,"1.0");
            new Assignment().enterGradeOnParticularQuestion(4,1,"1.0");
            new Assignment().enterGradeOnParticularQuestion(4,2,"1.0");
            new Assignment().enterGradeOnParticularQuestion(4,3,"1.0");

            Thread.sleep(1000);
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            clickOnParticularVSRP("10GradableAssignment");
            assessmentResponses.resumeGrading_button.click();//click on yes
            Thread.sleep(1000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            Thread.sleep(1000);
            new Assignment().enterGradeOnParticularQuestion(3,0,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,1,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,2,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,3,"1.0");

            new Assignment().enterGradeOnParticularQuestion(4,0,"1.0");
            new Assignment().enterGradeOnParticularQuestion(4,1,"1.0");
            new Assignment().enterGradeOnParticularQuestion(4,2,"1.0");
            new Assignment().enterGradeOnParticularQuestion(4,3,"1.0");

            Thread.sleep(1000);
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            clickOnParticularVSRP("11GradableAssignment");
            assessmentResponses.resumeGrading_button.click();//click on yes
            Thread.sleep(1000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            Thread.sleep(1000);
            new Assignment().enterGradeOnParticularQuestion(3,0,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,1,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,2,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,3,"1.0");

            new Assignment().enterGradeOnParticularQuestion(4,0,"1.0");
            new Assignment().enterGradeOnParticularQuestion(4,1,"1.0");
            new Assignment().enterGradeOnParticularQuestion(4,2,"1.0");
            new Assignment().enterGradeOnParticularQuestion(4,3,"1.0");

            Thread.sleep(1000);
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            clickOnParticularVSRP("12GradableAssignment");
            assessmentResponses.resumeGrading_button.click();//click on yes
            Thread.sleep(1000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            Thread.sleep(1000);
            new Assignment().enterGradeOnParticularQuestion(3,0,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,1,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,2,"1.0");
            new Assignment().enterGradeOnParticularQuestion(3,3,"1.0");

            new Assignment().enterGradeOnParticularQuestion(4,0,"1.0");
            new Assignment().enterGradeOnParticularQuestion(4,1,"1.0");
            new Assignment().enterGradeOnParticularQuestion(4,2,"1.0");
            new Assignment().enterGradeOnParticularQuestion(4,3,"1.0");

            Thread.sleep(1000);
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorCanViewTheTextOnStudentPerformanceTab in class StudentPerformance", e);
        }
    }


    public void assignCustomAssignmentFromMyQuestionBank(int dataIndex, int x) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
            String policyDescription = ReadTestData.readDataByTagName("", "policyDescription", Integer.toString(dataIndex));
            String scorePerQuestion = ReadTestData.readDataByTagName("", "scorePerQuestion", Integer.toString(dataIndex));
            String ordering = ReadTestData.readDataByTagName("", "ordering", Integer.toString(dataIndex));
            String immediateFeedBack = ReadTestData.readDataByTagName("", "immediateFeedBack", Integer.toString(dataIndex));
            String numberOfAttempt = ReadTestData.readDataByTagName("", "numberOfAttempt", Integer.toString(dataIndex));
            String showAnswerAtAttemptNumber = ReadTestData.readDataByTagName("", "showAnswerAtAttemptNumber", Integer.toString(dataIndex));
            String gradeReleaseOption = ReadTestData.readDataByTagName("", "gradeReleaseOption", Integer.toString(dataIndex));
            String showHintsAtAttemptNumber = ReadTestData.readDataByTagName("", "showHintsAtAttemptNumber", Integer.toString(dataIndex));
            String showReadingContentLinkAtAttemptNumber = ReadTestData.readDataByTagName("", "showReadingContentLinkAtAttemptNumber", Integer.toString(dataIndex));
            String showSolutionAtAttemptNumber = ReadTestData.readDataByTagName("", "showSolutionAtAttemptNumber", Integer.toString(dataIndex));
            String policyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String gradeBookWeighting = ReadTestData.readDataByTagName("", "gradeBookWeighting", Integer.toString(dataIndex));

            if (policyName == null) {
                System.out.println("Inside Policy Name");
                policyName = "Policy1";
            }

            if (policyDescription == null) {
                policyDescription = "Policy Description text";
            }
            if (scorePerQuestion == null) {
                scorePerQuestion = "1";
            }

            if (immediateFeedBack == null) {
                immediateFeedBack = "false";
            }

            if (numberOfAttempt == null) {
                numberOfAttempt = "1";
            }
            if (showAnswerAtAttemptNumber == null) {
                showAnswerAtAttemptNumber = "";
            }
            if (gradeReleaseOption == null) {
                gradeReleaseOption = "Release explicitly by the instructor";
            }
            if (showHintsAtAttemptNumber == null) {
                showHintsAtAttemptNumber = "";
            }
            if (showReadingContentLinkAtAttemptNumber == null) {
                showReadingContentLinkAtAttemptNumber = "";
            }
            if (showSolutionAtAttemptNumber == null) {
                showSolutionAtAttemptNumber = "";
            }

            new Navigator().NavigateTo("My Question Bank");
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[class='assign-this']")).click();
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("ir-ls-assign-this-edit-link")));

            driver.findElement(By.className("ir-ls-assign-this-header-edit-text")).clear();
            Thread.sleep(2000);
            driver.findElement(By.className("ir-ls-assign-this-header-edit-text")).sendKeys(x + "GradableAssignment");
            new UIElement().waitAndFindElement(By.id("due-time"));

            DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
            Calendar now = Calendar.getInstance();
            //add minutes to current date using Calendar.add method
            now.add(Calendar.MINUTE, Integer.parseInt(duetime));
            String calenderFormat = dateFormat.format(now.getTime());
            System.out.println("calenderFormat:" + calenderFormat);

            driver.findElement(By.id("due-time")).click();//click on dur time
            driver.findElement(By.id("due-time")).sendKeys(calenderFormat);//click on dur time

            driver.findElement(By.id("due-date")).click();//click on due date
            Thread.sleep(5000);
            List<WebElement> defaultsDate = driver.findElements(By.cssSelector("a[class='ui-state-default ui-state-highlight ui-state-hover']"));
            for (WebElement defaultDate : defaultsDate) {
                if (defaultDate.isDisplayed()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", defaultDate);
                    break;
                }
            }


            if (gradeable.equals("false")) {
                new UIElement().waitAndFindElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']")));
            }
            if (gradeable.equals("true") && assignmentpolicy != null) {

                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                new UIElement().waitAndFindElement(By.linkText(assignmentpolicy));
                driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
            }
            if (gradeable.equals("true")) {
                if (policyName.equals("Policy1")) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                    new Click().clickbyxpath("//a[@rel='addNewAssignmentPolicy']");
                    new AssignmentPolicy().createAssignmentPolicyWhileAssigning(policyName, policyDescription, scorePerQuestion, ordering, Boolean.parseBoolean(immediateFeedBack), numberOfAttempt, showAnswerAtAttemptNumber, gradeReleaseOption, showHintsAtAttemptNumber, showReadingContentLinkAtAttemptNumber, showSolutionAtAttemptNumber);
                }

            }
            if (gradeBookWeighting != null) {
                if (gradeBookWeighting.equals("true")) {
                    new Click().clickbylinkText("No Assignment Category");
                    new Click().clickbylinkText("Practice");
                }
            }
            if (gradeBookWeighting != null && gradeBookWeighting.equals("No Assignment Category")) {
                new Click().clickbylinkText("Uncategorized");
                new Click().clickbylinkText("No Assignment Category");
            }
            if (accessibleafter != null) {
                new UIElement().waitAndFindElement(By.id("accessible-date"));
                driver.findElement(By.id("accessible-date")).click();
                driver.findElement(By.linkText(accessibleafter)).click();
            }
            new UIElement().waitAndFindElement(By.id("additional-notes"));
            ((JavascriptExecutor) driver).executeScript("document.getElementById('additional-notes').value='additionalnote'");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Assign']")));
            new UIElement().waitTillInvisibleElement(By.xpath("//span[text()='Assign']"));
        } catch (Exception e) {
            Assert.fail("Exception in  assignFileBasedAssignmentFromMyQuestionBank in AppHelper class Assignment", e);
        }
    }

    public  void clickOnTheParticularAssignment(String assignmentName) {
        try {
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            List<WebElement> assessment = currentAssignments.getList_assignmentName();
            for (WebElement ele : assessment) {
                if (ele.getText().equals(assignmentName)) {
                    WebDriverUtil.clickOnElementUsingJavascript(ele);
                    break;
                }
            }
        } catch (Exception e) {
            Assert.fail("Exception in method clickOnTheParticularAssignment in class AssignmentPerformance", e);
        }

    }

    public  void clickOnParticularVSRP(String assignmentName) {
        try {
            new Navigator().NavigateTo("Class Assignments");
            int index = 0;
            List<WebElement> assessment = currentAssignments.getList_assignmentName();
            for (WebElement ele : assessment) {
                if (ele.getText().trim().equals(assignmentName)) {
                    break;
                }
                index++;
            }
            System.out.println("Index:" + index);
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.viewStudentResponses.get(index));
        } catch (Exception e) {
            Assert.fail("Exception in Method clickOnParticularVSRP of class AssignmentPerformance", e);
        }

    }
    public  void clickOnTheAssignmentLink(String assignmentName) {

        for (WebElement ele:gradebook.classPerformanceByAssignment_link){
            if(ele.getAttribute("title").equals(assignmentName)){
                WebDriverUtil.clickOnElementUsingJavascript(ele);
                break;
            }
        }
    }

    public static void navigateToGradeBookPage() {
        new Navigator().NavigateTo("Gradebook");
    }

    public void notApplicable(int index) {
        try {
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.student_checkBox.get(index));
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.menuIcon);
            assignmentResponsesPage.notApplicable.click();
            assignmentResponsesPage.yes_NotApplicable.click();
        } catch (Exception e) {
            Assert.fail("Exception in method notApplicable in class AssignmentPerformance", e);
        }

    }

    @Test(priority = 2, enabled = true)
    public void viewEnhancedGradeBookPage() {
        try {
            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            navigateToGradeBookPage();
            actual = gradebook.basicView.getText().trim();
            expected = "(Go to Basic View)";
            CustomAssert.assertEquals(actual, expected, "Verify (Go to Enhanced View) link", "By Default Enhanced View is displaying", "By Default Enhanced View is not displaying");

            actual = gradebook.Export_to_CSV_link.getAttribute("title").trim();
            expected = "Export to CSV";
            CustomAssert.assertEquals(actual, expected, "Verify Export to CSV link", "Export to CSV link is displaying", "Export to CSV link is not displaying");

            actual = gradebook.transferred_gradebook_button.getAttribute("title").trim();
            expected = "Transferred Gradebook";
            CustomAssert.assertEquals(actual, expected, "Verify Transferred Gradebook button", "Transferred Gradebook button is displaying", "Transferred Gradebook button not is displaying");

            WebDriverUtil.clickOnElementUsingJavascript(gradebook.getGradebookWeighting());
            gradebook.getEnterGradebookWeighting().get(0).sendKeys("10");
            WebDriverUtil.clickOnElementUsingJavascript(gradebook.getgradebookWeightingSaveButton());

            actual = gradebook.notification_message.getText().trim();
            expected = "*Total Must be equal to 100";
            CustomAssert.assertEquals(actual, expected, "Verify Total Must be equal to 100 message", "Total Must be equal to 100 message is displaying", "Total Must be equal to 100 message not is displaying");

            gradebook.getEnterGradebookWeighting().get(0).clear();
            new KeysSend().sendKeyBoardKeys("^");
            WebDriverUtil.clickOnElementUsingJavascript(gradebook.getgradebookWeightingSaveButton());
            Thread.sleep(4000);
            CustomAssert.assertEquals(new BooleanValue().presenceOfElement(1, gradebook.gradebook_popup), false, "verify  \"Gradebook Weighting\" pop up", "\"Gradebook Weighting\" pop up should be closed .", "\"Gradebook Weighting\" pop up should is not getting closed");
            WebDriverUtil.clickOnElementUsingJavascript(gradebook.getGradebookWeighting());
            driver.findElement(By.cssSelector("body")).click();
            CustomAssert.assertEquals(new BooleanValue().presenceOfElement(1, gradebook.gradebook_popup), false, "verify  \"Gradebook Weighting\" pop up", "\"Gradebook Weighting\" pop up should be closed .", "\"Gradebook Weighting\" pop up should is not getting closed");


        } catch (Exception e) {
            Assert.fail("Exception in TC viewEnhancedGradeBookPage of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void assignmentPerformanceDetailsForEmptyClass() {
        try {
            new LoginUsingLTI().ltiLogin("4");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "info");
            navigateToGradeBookPage();
            actual = gradebook.getNoGradeBookMessage().getText().trim();
            expected = "It seems that grades have not been released for any gradable assignment yet.\n" +
                    "Please check back after grades are released for at least one gradable assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify Default message", "By Default Enhanced View is displaying", "By Default Enhanced View is not displaying");

            gradebook.studentPerformanceTab.click();
            Thread.sleep(5000);
            actual = gradebook.gradeBookMessage.get(1).getText().trim();
            expected = "It seems that grades have not been released for any gradable assignment yet.\nPlease check back after grades are released for at least one gradable assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify message in student performance tab", "Message is as per expected", "Message is not as per expected");

        } catch (Exception e) {
            Assert.fail("Exception in TC assignmentPerformanceDetailsForEmptyClass of class  AssignmentPerformance", e);
        }
    }


    @Test(priority = 4, enabled = true)
    public void assignmentEntryInBasicView() {
        try {

            new Assignment().create(5);
            new Assignment().addQuestions(5, "multiplechoice", "");

            new LoginUsingLTI().ltiLogin("5_1");//log in as student
            ReportUtil.log("Login as student", "student logged successfully", "pass");

            new LoginUsingLTI().ltiLogin("5");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Assignment().assignAssignmentWithDueDate(5);
            navigateToGradeBookPage();
            gradebook.basicView.click();
            actual = gradebook.getAssignmentName().getText().trim();
            expected = "AssignmentPerformance_5";
            CustomAssert.assertEquals(actual, expected, "Verify Assignment Entry", "Assignment Name is displaying", "Assignment Name is not displaying");
            gradebook.getAssignmentName().click();//click on assignment

        } catch (Exception e) {
            Assert.fail("Exception in TC assignmentEntryInBasicView of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void assignmentEntryInBasicViewForFewStudent() {
        try {

            new Assignment().create(6);
            new LoginUsingLTI().ltiLogin("6_1");//log in as student
            ReportUtil.log("Login as student1", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("6_2");//log in as student
            ReportUtil.log("Login as student2", "student logged successfully", "pass");

            new LoginUsingLTI().ltiLogin("6");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Assignment().assignAssignmentWithDueDate(6);

            new LoginUsingLTI().ltiLogin("6_1");//log in as student
            ReportUtil.log("Login as student1", "student logged successfully", "pass");
            new Assignment().submitAssignmentAsStudent(6);

            new LoginUsingLTI().ltiLogin("6");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();
            actual = gradebook.getNoGradeBookMessage().getText().trim();
            expected = "It seems that grades have not been released for any gradable assignment yet.\n" +
                    "Please check back after grades are released for at least one gradable assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify Default message", "By Default Enhanced View is displaying", "By Default Enhanced View is not displaying");

            gradebook.studentPerformanceTab.click();
            Thread.sleep(5000);
            actual = gradebook.gradeBookMessage.get(1).getText().trim();
            expected = "It seems that grades have not been released for any gradable assignment yet.\nPlease check back after grades are released for at least one gradable assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify message in student performance tab", "Message is as per expected", "Message is not as per expected");

        } catch (Exception e) {
            Assert.fail("Exception in TC assignmentEntryInBasicViewForFewStudent of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void verifyAssignmentPerformanceTabView() {
        try {


            new LoginUsingLTI().ltiLogin("1");//log in as existing instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();

            actual = gradebook.tabHeader.get(0).getText().trim();
            expected = "Class Performance on Assignments";
            CustomAssert.assertEquals(actual, expected, "Verify Class Performance on Assignments header", "Class Performance on Assignments header is " + expected + "", "Class Performance on Assignments header is not " + expected + "");
            WebDriverUtil.clickOnElementUsingJavascript(gradebook.gradeRangeSelector.get(0));//click on 0%-59% grade range selector
            String align = gradebook.average_grade_label.getCssValue("text-align");
            CustomAssert.assertEquals(align, "left", "Verify Y-Axis of “Average Grade (%)”", "Alignment “Average Grade (%) is correct", "Alignment “Average Grade (%) is not correct");
            Thread.sleep(5000);
            for (WebElement ele : gradebook.assignment_barEntry) {
                if (ele.isDisplayed()) {
                    if (!(ele.getAttribute("height").equals("0"))) {
                        actualSize++;
                    }
                }
            }

            CustomAssert.assertEquals(actualSize, 4, "Verify gradable Assignment entry”", "All the gradable assignments for whom grades are released at the class section level is displaying", "All the gradable assignments for whom grades are released at the class section level is not displaying");
            new MouseHover().mouserhoverbywebelement(gradebook.classPerformanceByAssignment_link.get(0));

            actual = gradebook.classPerformanceByAssignment_link.get(0).getAttribute("title").trim();
            expected = "discussionAssignment_AssignmentPerformance_3";
            CustomAssert.assertEquals(actual, expected, "Verify Class Performance on Assignments Name on mouse hover", "On hover ,the complete assignment name is displaying on tooltip.", "On hover ,the complete assignment name is not displaying on tooltip.");


            align = gradebook.average_timeSpent_label.getCssValue("text-align");
            CustomAssert.assertEquals(align, "left", "Verify Y-Axis of “Avg. Time Spent (min)”", "Alignment “Avg. Time Spent (min) is correct", "Alignment “Avg. Time Spent (min) is not correct");

            actual = gradebook.classPerformanceByAssignment_link.get(4).getAttribute("title").trim();
            expected = "discussionAssignment_AssignmentPerformance_3";
            CustomAssert.assertEquals(actual, expected, "Verify Class Performance on Assignments Name on mouse hover", "On hover ,the complete assignment name is displaying on tooltip.", "On hover ,the complete assignment name is not displaying on tooltip.");


        } catch (Exception e) {
            Assert.fail("Exception in TC verifyAssignmentPerformanceTabView of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 7, enabled = true)
    public void verifyAverageGrade() {
        try {


            new LoginUsingLTI().ltiLogin("1");//log in as existing instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();

            gradebook.gradeRangeSelector.get(1).click(); //click on the second range
            new MouseHover().mouserhoverbywebelement(gradebook.assignment_barEntry.get(0));
/*
            # The "Average grade ” for one assignment should be calculated as =
            (sum of total time spent on that assignment across all students who are part of that assignment) / (number of students).
             i.e. The values of all the students who did not start the assignment should be ignored for this calculation.
*/
            /*(50%+50%+100%+100%)/4*/
            Thread.sleep(2000);
            actual = gradebook.tooltip_value.get(0).getText().trim();
            expected = "75%";
            CustomAssert.assertEquals(actual, expected, "Verify average grade value", "average grade value is " + expected + "", "average grade value is not " + expected + "");

            gradebook.gradeRangeSelector.get(2).click(); //click on the third range
            actual = gradebook.assignment_barEntry.get(0).getAttribute("fill").trim();
            expected = "#6bb45f"; //yellow
            CustomAssert.assertEquals(actual, expected, "Verify (80-100) range color", "(80-100) range color is " + expected + "", "(80-100) range color is not " + expected + "");


            gradebook.gradeRangeSelector.get(1).click(); //click on the second range
            actual = gradebook.assignment_barEntry.get(1).getAttribute("fill").trim();
            expected = "rgb(126, 180, 212)";//blue
            CustomAssert.assertEquals(actual, expected, "Verify average time spent (60-79) range color", "average time spent (60-79) range  color is " + expected + "", "average time spent (60-79) range color is not " + expected + "");

            String discussionAssignment = ReadTestData.readDataByTagName("", "discussionAssignment", "3");

            gradebook.gradeRangeSelector.get(0).click(); //click on the first range
            actual = Assignment.gradeBookStatusBoxCount(3, "AverageGrade", discussionAssignment);
            expected = "0%";
            CustomAssert.assertEquals(actual, expected, "Verify average grade value of discussion Assignment", "average grade value is " + expected + " of discussion Assignment", "average grade value is not " + expected + "of discussion Assignment");

            String fileBasedAssignment = ReadTestData.readDataByTagName("", "assessmentname", "2");
            actual = Assignment.gradeBookStatusBoxCount(3, "AverageGrade", fileBasedAssignment);
            expected = "0%";
            CustomAssert.assertEquals(actual, expected, "Verify average grade value of fileBasedAssignment", "average grade value is " + expected + " of fileBasedAssignment", "average grade value is not " + expected + "of fileBasedAssignment");


            gradebook.gradeRangeSelector.get(0).click(); //click on the first range
            actual = Assignment.gradeBookStatusBoxCount(3, "AverageTimeSpent", discussionAssignment);
            expected = "Not Available";
            CustomAssert.assertEquals(actual, expected, "Verify AverageTimeSpent value of discussion Assignment", "AverageTimeSpent value is " + expected + " of discussion Assignment", "AverageTimeSpent value is not " + expected + "of discussion Assignment");

            actual = Assignment.gradeBookStatusBoxCount(3, "AverageTimeSpent", fileBasedAssignment);
            expected = "Not Available";
            CustomAssert.assertEquals(actual, expected, "Verify AverageTimeSpent value of fileBasedAssignment", "AverageTimeSpent value is " + expected + " of fileBasedAssignment", "AverageTimeSpent value is not " + expected + "of fileBasedAssignment");

        } catch (Exception e) {
            Assert.fail("Exception in TC verifyAverageGrade of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void verifyBarGraphValueOnMouseHover() {
        try {

            new LoginUsingLTI().ltiLogin("1");//log in as existing instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();

            gradebook.gradeRangeSelector.get(0).click(); //click on the first range
            new MouseHover().mouserhoverbywebelement(gradebook.assignment_barEntry.get(2));//Mouse hover on the average time spent
            actual = gradebook.gradeBox_color.get(3).getAttribute("stroke").trim();
            expected = "rgb(205,71,15)";//red
            CustomAssert.assertEquals(actual, expected, "Verify average grade (0-59) range color", "Average grade (0-59) range  color is " + expected + "", "Average grade  (0-59) range color is not " + expected + "");

            Thread.sleep(1000);
            actual = gradebook.dotBox.get(0).getText().trim();
             /*(50%+25%+100%+50%)/4*=56%*/
            DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
            Calendar now = Calendar.getInstance();
            String calenderFormat = dateFormat.format(now.getTime());
            System.out.println("calenderFormat:" + calenderFormat);
            //"Due Date: "+calenderFormat+", 2016";
            expected = "2GradableAssignment\n" +
                    "Average Grade: 56%\n" +
                    "Due Date: " + calenderFormat;
            CustomAssert.assertEquals(actual, expected, "Verify Bar Graph Value OnMouseHover of (0%-59%)", " Bar Graph Value OnMouseHover of (0%-59%) is " + expected + "", " Bar Graph Value OnMouseHover of (0%-59%) is not " + expected + "");

            gradebook.gradeRangeSelector.get(2).click(); //click on the third range
            actual = gradebook.assignment_barEntry.get(0).getAttribute("fill").trim();
            expected = "#6bb45f"; //green
            CustomAssert.assertEquals(actual, expected, "Verify (80-100) range color", "(80-100) range color is " + expected + "", "(80-100) range color is not " + expected + "");

            new MouseHover().mouserhoverbywebelement(gradebook.assignment_barEntry.get(7));
            Thread.sleep(1000);
            actual = gradebook.dotBox.get(0).getText().trim();
             /*(75%+68%+88%+100%+100%)/5*=86%*/
            expected = "5GradableAssignment\n" +
                    "Average Grade: 86%\n" +
                    "Due Date: " + calenderFormat;
            CustomAssert.assertEquals(actual, expected, "Verify Bar Graph Value OnMouseHover of (80%-100%)", " Bar Graph Value OnMouseHover of (80%-100%) is " + expected + "", " Bar Graph Value OnMouseHover of (80%-100%) is not " + expected + "");

            gradebook.gradeRangeSelector.get(1).click(); //click on the second range
            new MouseHover().mouserhoverbywebelement(gradebook.assignment_barEntry.get(0));
            actual = gradebook.gradeBox_color.get(3).getAttribute("stroke").trim();
            expected = "rgb(235,187,61)";//yellow
            CustomAssert.assertEquals(actual, expected, "Verify average grade (60-79) range color", "Average grade (60-79) range  color is " + expected + "", "Average grade  (60-79) range color is not " + expected + "");
            Thread.sleep(1000);
            actual = gradebook.dotBox.get(0).getText().trim();
             /*(50%+50%+100%+100%)/4*=75%*/
            expected = "3GradableAssignment\n" +
                    "Average Grade: 75%\n" +
                    "Due Date: " + calenderFormat;
            CustomAssert.assertEquals(actual, expected, "Verify Bar Graph Value OnMouseHover of (60%-79%)", " Bar Graph Value OnMouseHover of (60%-79%) is " + expected + "", " Bar Graph Value OnMouseHover of (60%-79%) is not " + expected + "");

            gradebook.gradeRangeSelector.get(1).click(); //click on the second range
            new MouseHover().mouserhoverbywebelement(gradebook.assignment_barEntry.get(1));//Mouse hover on the average time spent
            actual = gradebook.gradeBox_color.get(7).getAttribute("stroke").trim();
            expected = "rgb(126, 180, 212)";//blue
            CustomAssert.assertEquals(actual, expected, "Verify average time spent (60-79) range color", "average time spent (60-79) range  color is " + expected + "", "average time spent (60-79) range color is not " + expected + "");

            Thread.sleep(1000);
            actual = gradebook.dotBox.get(1).getText().trim();
            expected = "3GradableAssignment\n" +
                    "Average Time Spent: 1 min(s)\n" +
                    "Due Date: " + calenderFormat;
            CustomAssert.assertEquals(actual, expected, "Verify average time spent Value OnMouseHover of (60%-79%)", "Average time spent Value OnMouseHover of (60%-79%) is " + expected + "", "Average time spent Value OnMouseHover of (60%-79%) is not " + expected + "");

        } catch (Exception e) {
            Assert.fail("Exception in TC verifyBarGraphValueOnMouseHover of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void gradeBookWeightAgeSymbol() {
        try {

            new LoginUsingLTI().ltiLogin("7_1");//log in as student
            ReportUtil.log("Login as student", "student logged successfully", "pass");

            new LoginUsingLTI().ltiLogin("7");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Navigator().NavigateTo("My Assignments");
            new Navigator().navigateToTab("Question Banks");
            WebDriverUtil.clickOnElementUsingJavascript(questionBank.getAddToMyQuestionBank());
            navigateToGradeBookPage();
            WebDriverUtil.clickOnElementUsingJavascript(gradebook.getGradebookWeighting());
            gradebook.getEnterGradebookWeighting().get(0).clear();
            gradebook.getEnterGradebookWeighting().get(0).sendKeys("50");
            Thread.sleep(1000);
            gradebook.getEnterGradebookWeighting().get(1).clear();
            gradebook.getEnterGradebookWeighting().get(1).sendKeys("50");
            Thread.sleep(1000);
            gradebook.getEnterGradebookWeighting().get(5).clear();
            gradebook.getEnterGradebookWeighting().get(5).sendKeys("0");
            Thread.sleep(2000);
            WebDriverUtil.clickOnElementUsingJavascript(gradebook.getgradebookWeightingSaveButton());

            for (int i = 1; i <= 1; i++) {
                assignCustomAssignmentFromMyQuestionBank(7, i);
            }
            new LoginUsingLTI().ltiLogin("7");//log in as instructor
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            assessmentResponses.resumeGrading_button.click();//click on yes
            Thread.sleep(120000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            navigateToGradeBookPage();
            gradebook.weightage.get(0).click();
            actual = gradebook.weightage_value.get(0).getText().trim();
            expected = "Practice: 50% weightage";
            CustomAssert.assertEquals(actual, expected, "verify Weightage", "Weightage vaule is " + expected + "", "Weightage vaule is not" + expected + "");


            new LoginUsingLTI().ltiLogin("7");//log in as instructor
            navigateToGradeBookPage();
            WebDriverUtil.clickOnElementUsingJavascript(gradebook.getGradebookWeighting());
            gradebook.getEnterGradebookWeighting().get(0).clear();
            gradebook.getEnterGradebookWeighting().get(0).sendKeys("0");
            Thread.sleep(1000);
            gradebook.getEnterGradebookWeighting().get(1).clear();
            gradebook.getEnterGradebookWeighting().get(1).sendKeys("0");
            Thread.sleep(1000);
            gradebook.getEnterGradebookWeighting().get(5).clear();
            gradebook.getEnterGradebookWeighting().get(5).sendKeys("100");
            WebDriverUtil.clickOnElementUsingJavascript(gradebook.getgradebookWeightingSaveButton());
            for (int i = 2; i <= 2; i++) {
                assignCustomAssignmentFromMyQuestionBank(9, i);
            }
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            assessmentResponses.resumeGrading_button.click();//click on yes
            Thread.sleep(120000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            navigateToGradeBookPage();
            gradebook.weightage.get(0).click();
            actual = gradebook.weightage_value.get(0).getText().trim();
            expected = "Uncategorized: 100% weightage";
            CustomAssert.assertEquals(actual, expected, "verify Weightage", " Uncategorized Weightage vaule is " + expected + "", "UncategorizedWeightage vaule is not" + expected + "");


            new LoginUsingLTI().ltiLogin("7");//log in as instructor
            for (int i = 3; i <= 3; i++) {
                assignCustomAssignmentFromMyQuestionBank(8, i);
            }
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            assessmentResponses.resumeGrading_button.click();//click on yes
            Thread.sleep(120000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            navigateToGradeBookPage();
            Assert.assertFalse(new BooleanValue().presenceOfElement(8, gradebook.gradeBookNoAssignmentCategory));

        } catch (Exception e) {
            Assert.fail("Exception in TC gradeBookWeightAgeSymbol of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void extendDueDateColumnVerification() {
        try {

            new LoginUsingLTI().ltiLogin("10_1");//log in as student
            ReportUtil.log("Login as student2", "student logged successfully", "pass");

            new LoginUsingLTI().ltiLogin("10");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Navigator().NavigateTo("My Assignments");
            new Navigator().navigateToTab("Question Banks");
            WebDriverUtil.clickOnElementUsingJavascript(questionBank.getAddToMyQuestionBank());
            for (int i = 1; i <= 1; i++) {
                assignCustomAssignmentFromMyQuestionBank(10, i);
            }
            new Assignment().extentDueTimeFromAssignmentResponsePage("10");
            Thread.sleep(120000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            assessmentResponses.resumeGrading_button.click();//click on yes
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all

            navigateToGradeBookPage();
            DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
            Calendar now = Calendar.getInstance();
            String calenderFormat = dateFormat.format(now.getTime());
            System.out.println("calenderFormat:" + calenderFormat);
            actual = gradebook.gradeBook_statusHeader.get(1).getText().trim();
            expected = "Due Date";
            CustomAssert.assertEquals(actual, expected, "Verify Due Date column position", "Due Date column position " + expected + "", "Due Date column position is not " + expected + "");

            actual = Assignment.gradeBookStatusBoxCount(10, "DueDate", "1GradableAssignment");
            expected = calenderFormat+"\n"+"Ext. Date: "+calenderFormat;
            CustomAssert.assertEquals(actual, expected, "Verify extended due date", "extended due date is " + expected + "", "extended due date is not " + expected + "");

            new MouseHover().mouserhoverbywebelement(gradebook.dueDate_sorting);
            actual =gradebook.dueDate_sorting.getAttribute("title").trim();
            expected = "Descending";
            CustomAssert.assertEquals(actual, expected, "Verify hover on visual indicator", "Tool tip 'Descending' should be displayed", "Tool tip 'Descending' is not dispalying");

            new LoginUsingLTI().ltiLogin("10");//log in as instructor
            for (int i = 2; i <= 2; i++) {
                assignCustomAssignmentFromMyQuestionBank(11, i);
            }
            new Assignment().extentDueTimeFromAssignmentResponsePage("12");
            Thread.sleep(60000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            assessmentResponses.resumeGrading_button.click();//click on yes
            Thread.sleep(5000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            navigateToGradeBookPage();
            String latestAssignmentName=gradebook.assignmentName_label.getText().trim();
            if(latestAssignmentName.equals("2GradableAssignment")){
                if(!(driver.findElement(By.xpath("/*//*[text()='"+latestAssignmentName+"']/../following-sibling::td[1]/span")).isDisplayed())){
                    CustomAssert.fail("Verify latest entry due date","Latest assignment due date entry is not in top");
                }
            }

            new LoginUsingLTI().ltiLogin("10");//log in as instructor
            int i=0;
            for (i = 4; i <= 4; i++) {
                assignCustomAssignmentFromMyQuestionBank(11, i);
            }
            new Assignment().extentDueTimeFromAssignmentResponsePage("13");
            new Assignment().extentDueTimeFromAssignmentResponsePage("14");

            Thread.sleep(60000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            assessmentResponses.resumeGrading_button.click();//click on yes
            Thread.sleep(5000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            navigateToGradeBookPage();
            latestAssignmentName=gradebook.assignmentName_label.getText().trim();
            if(latestAssignmentName.equals(i+"GradableAssignment")){
                if(!(driver.findElement(By.xpath("//*[text()='"+latestAssignmentName+"']/../following-sibling::td[1]/span")).isDisplayed())){
                    CustomAssert.fail("Verify latest entry due date","Latest assignment due date entry is not in top");
                }
            }else {
                CustomAssert.fail("Verify latest entry due date","Latest assignment due date entry is not in top");

            }
        } catch (Exception e) {
            Assert.fail("Exception in TC extendDueDateColumnVerification of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 11, enabled = true)
    public void didNotStartColumn() {
        try {

            new LoginUsingLTI().ltiLogin("1");//log in as existing instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();

           /* a- Total number of students who did not start or open the assignment.
              b- Total number of all the students to which that assignment was assigned in that class-section.
            */
            actual=GradeBook.didNotStartPercentageCalculation(11, 0);
            expected="60%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            gradebook.assignmentPerformanceHelp_icon.get(0).click(); //click on the did not start help icon
            actual = gradebook.weightage_value.get(0).getText().trim();
            expected = "This shows all the students who did not open the assignment.";
            CustomAssert.assertEquals(actual, expected, "verify help message of Did not start", "Help message of Did not start is " + expected + "", "Help message of Did not start is not" + expected + "");

            new MouseHover().mouserhoverbywebelement(gradebook.assignmentPerformanceSorting_icon.get(0));
            actual = gradebook.assignmentPerformanceSorting_icon.get(0).getAttribute("title").trim();
            expected = "Sort";
            CustomAssert.assertEquals(actual, expected, "verify Sorting message of Did not start", "Sorting message of Did not start is " + expected + "", "Sorting message of Did not start is not" + expected + "");

            gradebook.assignmentPerformanceSorting_icon.get(0).click();
            GradeBook.sortValueInAscendingOrder(11,"DidNotStart");
            gradebook.ascending.click();
            GradeBook.sortValueInDescendingOrder(11,"DidNotStart");


        } catch (Exception e) {
            Assert.fail("Exception in TC didNotStartColumn of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 12, enabled = true)
    public void didNotFinishColumn() {
        try {

            new LoginUsingLTI().ltiLogin("1");//log in as existing instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();

           /* a-  Total number of students who did not FINISH the assignment.
              b-  Total number of all the students to which that assignment was assigned in that class-section.
            */
            actual=GradeBook.didNotFinishPercentageCalculation(11, 0);
            expected="0%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "Did Not Finish value is " + expected + "", "Did Not Finish value is not " + expected + "");

            gradebook.assignmentPerformanceHelp_icon.get(1).click(); //click on the did not finish help icon
            actual = gradebook.weightage_value.get(0).getText().trim();
            expected = "This shows the students who started but did not finish the assignment.";
            CustomAssert.assertEquals(actual, expected, "verify help message of Did Not Finish", "Help message of Did Not Finish is " + expected + "", "Help message of Did Not Finish is not" + expected + "");

            new MouseHover().mouserhoverbywebelement(gradebook.assignmentPerformanceSorting_icon.get(1));
            actual = gradebook.assignmentPerformanceSorting_icon.get(1).getAttribute("title").trim();
            expected = "Sort";
            CustomAssert.assertEquals(actual, expected, "verify sorting message of Did Not Finish", "Sorting message of Did Not Finish is " + expected + "", "Sorting message of Did Not Finish is not" + expected + "");

            gradebook.assignmentPerformanceSorting_icon.get(1).click();
            GradeBook.sortValueInAscendingOrder(11,"DidNotFinish");
            gradebook.ascending.click();
            GradeBook.sortValueInDescendingOrder(11,"DidNotFinish");


        } catch (Exception e) {
            Assert.fail("Exception in TC didNotFinishColumn of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 13, enabled = true)
    public void finishedColumnVerification() {
        try {

            new LoginUsingLTI().ltiLogin("1");//log in as existing instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();

           /* a-  Total number of students who finished the assignment within due date.
              b-  Total number of all the students to which that assignment was assigned in that class-section.
            */
            actual=GradeBook.finishedPercentageCalculation(11, 0);
            expected="40%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "Finished value is " + expected + "", "Finished value is not " + expected + "");

            gradebook.assignmentPerformanceHelp_icon.get(2).click(); //click on the finished help icon
            actual = gradebook.weightage_value.get(0).getText().trim();
            expected = "This shows the students who finished the assignment.";
            CustomAssert.assertEquals(actual, expected, "verify help message of Finished", "Help message of Finished is " + expected + "", "Help message of Finished is not" + expected + "");

            new MouseHover().mouserhoverbywebelement(gradebook.assignmentPerformanceSorting_icon.get(2));
            actual = gradebook.assignmentPerformanceSorting_icon.get(2).getAttribute("title").trim();
            expected = "Sort";
            CustomAssert.assertEquals(actual, expected, "verify sorting message of Finished", "Sorting message of Finished is " + expected + "", "Sorting message of Finished is not" + expected + "");

            gradebook.assignmentPerformanceSorting_icon.get(2).click();
            GradeBook.sortValueInAscendingOrder(11,"Finished");
            gradebook.ascending.click();
            GradeBook.sortValueInDescendingOrder(11,"Finished");


        } catch (Exception e) {
            Assert.fail("Exception in TC finishedColumnVerification of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 13, enabled = true)
    public void averageTimeSpent() {
        try {

            new LoginUsingLTI().ltiLogin("1");//log in as existing instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();

            actual=GradeBook.gradeBookStatusBoxCount(12,"AverageTimeSpent","2GradableAssignment");
            expected="1 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify Average Time Spent value", "Average Time Spent value is " + expected + "", "Average Time Spent value is not " + expected + "");

            gradebook.assignmentPerformanceHelp_icon.get(3).click(); //click on the finished help icon
            actual = gradebook.weightage_value.get(0).getText().trim();
            expected = "This shows the average time spent on each assignment across all the students assigned.";
            CustomAssert.assertEquals(actual, expected, "verify help message of Average Time Spent", "Help message of Average Time Spent is " + expected + "", "Help message of Average Time Spent is not" + expected + "");

            new MouseHover().mouserhoverbywebelement(gradebook.assignmentPerformanceSorting_icon.get(3));
            actual = gradebook.assignmentPerformanceSorting_icon.get(3).getAttribute("title").trim();
            expected = "Sort";
            CustomAssert.assertEquals(actual, expected, "verify sorting message of Average Time Spent", "Sorting message of Average Time Spent is " + expected + "", "Sorting message of Average Time Spent is not" + expected + "");

            gradebook.assignmentPerformanceSorting_icon.get(3).click();

            List<String> averageTimeSpent = new ArrayList<String>();
            averageTimeSpent.add("Not Available");
            averageTimeSpent.add("Not Available");
            averageTimeSpent.add("0 min(s)");
            averageTimeSpent.add("0 min(s)");
            averageTimeSpent.add("0 min(s)");
            averageTimeSpent.add("0 min(s)");
            averageTimeSpent.add("0 min(s)");
            averageTimeSpent.add("0 min(s)");
            averageTimeSpent.add("0 min(s)");
            averageTimeSpent.add("0 min(s)");
            averageTimeSpent.add("0 min(s)");
            averageTimeSpent.add("1 min(s)");
            averageTimeSpent.add("1 min(s)");
            averageTimeSpent.add("1 min(s)");

            List<WebElement> averageTimeSpent_values = gradebook.averageTimeSpent_values;
            boolean mismatch=false;
            System.out.println(averageTimeSpent_values.size());
            for (int i = 0; i <= averageTimeSpent_values.size()-1; i++) {
                WebDriverUtil.scrollIntoView(averageTimeSpent_values.get(i), false);
                System.out.println(i+":"+averageTimeSpent_values.get(i).getText().trim());
                if (!(averageTimeSpent.get(i).equals(averageTimeSpent_values.get(i).getText().trim()))) {
                    System.out.println(i+"fail");
                    mismatch=true;
                    break;
                }
            }
            if(mismatch==true){
                CustomAssert.fail("verify order", "Values are not in ascending order");
            }
            else {
                ReportUtil.log("verify order", "Values are in ascending order", "pass");
            }

            gradebook.ascending.click();
            List<String> descendingAverageTimeSpent = new ArrayList<String>();
            descendingAverageTimeSpent.add("1 min(s)");
            descendingAverageTimeSpent.add("1 min(s)");
            descendingAverageTimeSpent.add("1 min(s)");
            descendingAverageTimeSpent.add("0 min(s)");
            descendingAverageTimeSpent.add("0 min(s)");
            descendingAverageTimeSpent.add("0 min(s)");
            descendingAverageTimeSpent.add("0 min(s)");
            descendingAverageTimeSpent.add("0 min(s)");
            descendingAverageTimeSpent.add("0 min(s)");
            descendingAverageTimeSpent.add("0 min(s)");
            descendingAverageTimeSpent.add("0 min(s)");
            descendingAverageTimeSpent.add("0 min(s)");


            List<WebElement> descendingAverageTimeSpent_values = gradebook.averageTimeSpent_values;
            mismatch=false;
            for (int i = 0; i < descendingAverageTimeSpent.size()-1; i++) {
                WebDriverUtil.scrollIntoView(descendingAverageTimeSpent_values.get(i), false);
                if (!(descendingAverageTimeSpent.get(i).equals(descendingAverageTimeSpent_values.get(i).getText().trim()))) {
                    mismatch=true;
                    break;
                }
            }
            if(mismatch==true){
                CustomAssert.fail("verify order", "Values are not in descending order");
            }
            else {
                ReportUtil.log("verify order", "Values are in descending order", "pass");
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC averageTimeSpent of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 14, enabled = true)
    public void averageTimeSpentOfFBAAndDWAssignment() {
        try {

            new LoginUsingLTI().ltiLogin("1");//log in as existing instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();

            actual=GradeBook.gradeBookStatusBoxCount(12,"AverageTimeSpent","discussionAssignment_AssignmentPerformance_3");
            expected="Not Available";
            CustomAssert.assertEquals(actual, expected, "Verify Average Time Spent value of DW assignment", "Average Time Spent value of DW assignment is " + expected + "", "Average Time Spent value of DW assignment is not " + expected + "");

            actual=GradeBook.gradeBookStatusBoxCount(12,"AverageTimeSpent","FileBasedAssignment_AssignmentPerformance_2");
            expected="Not Available";
            CustomAssert.assertEquals(actual, expected, "Verify Average Time Spent value FBA", "Average Time Spent value of FBA is " + expected + "", "Average Time Spent value of FBA is not " + expected + "");


        } catch (Exception e) {
            Assert.fail("Exception in TC averageTimeSpentOfFBAAndDWAssignment of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 15, enabled = true)
    public void averageGrade() {
        try {

            new LoginUsingLTI().ltiLogin("1");//log in as existing instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();

            gradebook.gradeRangeSelector.get(0).click(); //click on the first range
            actual = gradebook.grade_list.get(3).getText().trim();
            expected = "56%";
            CustomAssert.assertEquals(actual, expected, "Verify average grade value", "average grade value is " + expected + "", "average grade value is not " + expected + "");

            gradebook.assignmentPerformanceHelp_icon.get(4).click(); //click on the finished help icon
            actual = gradebook.weightage_value.get(0).getText().trim();
            expected = "This shows the average grade on each assignment across all the students assigned.";
            CustomAssert.assertEquals(actual, expected, "verify help message of Average grade", "Help message of Average grade is " + expected + "", "Help message of Average grade is not" + expected + "");

            new MouseHover().mouserhoverbywebelement(gradebook.assignmentPerformanceSorting_icon.get(4));
            actual = gradebook.assignmentPerformanceSorting_icon.get(4).getAttribute("title").trim();
            expected = "Sort";
            CustomAssert.assertEquals(actual, expected, "verify sorting message of Average grade", "Sorting message of Average grade is " + expected + "", "Sorting message of Average grade is not" + expected + "");

            gradebook.assignmentPerformanceSorting_icon.get(4).click();

            List<String> averageGrade = new ArrayList<String>();
            averageGrade.add("0%");
            averageGrade.add("0%");
            averageGrade.add("56%");
            averageGrade.add("58%");
            List<WebElement> averageGrade_values = gradebook.grade_list;
            boolean mismatch=false;
            for (int i = 0; i <averageGrade.size(); i++) {
                System.out.println(i+":a"+averageGrade.get(i));
                System.out.println(i+":b"+averageGrade_values.get(i+1).getText().trim());
                if (!(averageGrade.get(i).equals(averageGrade_values.get(i+1).getText().trim()))) {
                    System.out.println(i+"fail");
                    mismatch=true;
                    break;
                }
            }
            if(mismatch==true){
                CustomAssert.fail("verify order", "Values are not in ascending order");
            }
            else {
                ReportUtil.log("verify order", "Values are in ascending order", "pass");
            }

            gradebook.ascending.click();
            List<String> descendingAverageGrade = new ArrayList<String>();
            descendingAverageGrade.add("58%");
            descendingAverageGrade.add("56%");
            descendingAverageGrade.add("0%");
            descendingAverageGrade.add("0%");


            List<WebElement> descendingAverageGrade_values = gradebook.grade_list;
            mismatch=false;
            for (int i = 0; i < averageGrade.size(); i++) {
                WebDriverUtil.scrollIntoView(descendingAverageGrade_values.get(i), false);
                if (!(descendingAverageGrade.get(i).equals(descendingAverageGrade_values.get(i+1).getText().trim()))) {
                    mismatch=true;
                    break;
                }
            }


            if(mismatch==true){
                CustomAssert.fail("verify order", "Values are not in descending order");
            }
            else {
                ReportUtil.log("verify order", "Values are in descending order", "pass");
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC averageGrade of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 16, enabled = true)
    public void verifyTinyScroll() {
        try {

            new LoginUsingLTI().ltiLogin("1");//log in as existing instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();

            new MouseHover().mouserhoverbywebelement(gradebook.grade_list.get(2));
            new MouseHover().mouserhoverbywebelement(gradebook.tiny_scroll);
           /* Thread.sleep(1000);
            String tinyWidth=gradebook.tiny_scroll.getAttribute("style").trim();
            Map<String,Object> convertJSONStringTOMap = ReadJSONData.convertJSONStringTOMap(tinyWidth);
            Thread.sleep(5000);
            String width=convertJSONStringTOMap.get("width").toString();
         */

        } catch (Exception e) {
            Assert.fail("Exception in TC verifyTinyScroll of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 16, enabled = true)
    public void removeStudentVerification() {
        try {

            String assignmentName =  ReadTestData.readDataByTagName("", "assessmentname", "15");


            new Assignment().create(15);
            new LoginUsingLTI().ltiLogin("15_1");//log in as student
            ReportUtil.log("Login as student1", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("15_2");//log in as student
            ReportUtil.log("Login as student2", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("15_3");//log in as student
            ReportUtil.log("Login as student3", "student logged successfully", "pass");


            new LoginUsingLTI().ltiLogin("15");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Assignment().assignAssignmentWithDueDate(15);

            new LoginUsingLTI().ltiLogin("15_1");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("15_2");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            Thread.sleep(60000);
            new LoginUsingLTI().ltiLogin("15");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            try {
                assessmentResponses.resumeGrading_button.click();//click on yes
                WebDriverUtil.clickOnElementUsingJavascript(assessmentResponses.resumeGrading_button);
                WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            } catch (Exception e) {

            }
            Thread.sleep(3000);
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            navigateToGradeBookPage();
            driver.navigate().refresh();
            navigateToGradeBookPage();
            new LoginUsingLTI().ltiLogin("16");//log in as instructor to remove student
            new LoginUsingLTI().ltiLogin("15");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();
            actual=GradeBook.didNotStartPercentageCalculation(11, 0);
            expected="50%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.didNotFinishPercentageCalculation(11, 0);
            expected="50%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.finishedPercentageCalculation(11, 0);
            expected="0%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "Finished value is " + expected + "", "Finished value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1,"AverageTimeSpent",assignmentName);
            expected = "0 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify Average Time Spent value", "Average Time Spent value is " + expected + "", "Average Time Spent value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1, "AverageGrade", assignmentName);
            expected = "0%";
            CustomAssert.assertEquals(actual, expected, "Verify average grade value", "average grade value is " + expected + "", "average grade value is not " + expected + "");

            actual =gradebook.assignment_barEntry.get(0).getAttribute("height").trim();
            expected = "0";
            CustomAssert.assertEquals(actual,expected,"Verify Bar graph length in Avg Time Spent and Average grade","Bar graph length of Avg Time Spent and Average grade should not displayed","Bar graph length in Avg Time Spent and Average grade is displaying");

            clickOnTheAssignmentLink(assignmentName);
            List<String> student = new ArrayList<String>();
            List<String> actualStudent = new ArrayList<String>();
            actualStudent.add("b, month");
            actualStudent.add("c, month");
            for (WebElement we : gradebook.drillDown_studentName) {
                student.add(we.getText());
            }

            if (!student.equals(actualStudent)) {
                CustomAssert.fail("Verify removed student name", "Removed student name is still displaying");
            }

        } catch (Exception e) {
            Assert.fail("Exception in TC removeStudentVerification of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 17, enabled = true)
    public void moveStudentToDifferentClassSection() {
        try {

            String assignmentName =  ReadTestData.readDataByTagName("", "assessmentname", "17");

            new Assignment().create(17);
            new LoginUsingLTI().ltiLogin("17_1");//log in as student
            ReportUtil.log("Login as student1", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("17_2");//log in as student
            ReportUtil.log("Login as student2", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("17_3");//log in as student
            ReportUtil.log("Login as student3", "student logged successfully", "pass");

            new LoginUsingLTI().ltiLogin("17");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Assignment().assignAssignmentWithDueDate(17);


            new LoginUsingLTI().ltiLogin("17_1");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("17_2");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            Thread.sleep(60000);
            new LoginUsingLTI().ltiLogin("17");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            Thread.sleep(4000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            try {
                 WebDriverUtil.clickOnElementUsingJavascript(assessmentResponses.resumeGrading_button);//click on yes
                 WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            } catch (Exception e) {

            }
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            navigateToGradeBookPage();
            driver.navigate().refresh();
            navigateToGradeBookPage();
            new LoginUsingLTI().ltiLogin("17_4");//log in as student to move different class section
            ReportUtil.log("Login as student4", "student moved to other class section successfully", "pass");

            new LoginUsingLTI().ltiLogin("17");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();
            actual=GradeBook.didNotStartPercentageCalculation(11, 0);
            expected="50%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.didNotFinishPercentageCalculation(11, 0);
            expected="50%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.finishedPercentageCalculation(11, 0);
            expected="0%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "Finished value is " + expected + "", "Finished value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1,"AverageTimeSpent",assignmentName);
            expected = "0 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify Average Time Spent value", "Average Time Spent value is " + expected + "", "Average Time Spent value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1, "AverageGrade", assignmentName);
            expected = "0%";
            CustomAssert.assertEquals(actual, expected, "Verify average grade value", "average grade value is " + expected + "", "average grade value is not " + expected + "");

            actual =gradebook.assignment_barEntry.get(0).getAttribute("height").trim();
            expected = "0";
            CustomAssert.assertEquals(actual,expected,"Verify Bar graph length in Avg Time Spent and Average grade","Bar graph length of Avg Time Spent and Average grade should not displayed","Bar graph length in Avg Time Spent and Average grade is displaying");


            clickOnTheAssignmentLink(assignmentName);
            List<String> student = new ArrayList<String>();
            List<String> actualStudent = new ArrayList<String>();
            actualStudent.add("b, month");
            actualStudent.add("c, month");
            for (WebElement we : gradebook.drillDown_studentName) {
                student.add(we.getText());
            }

            if (!student.equals(actualStudent)) {
                CustomAssert.fail("Verify removed student name", "Removed student name is still displaying");
            }

        } catch (Exception e) {
            Assert.fail("Exception in TC moveStudentToDifferentClassSection of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 18,enabled = true)
    public void assignAssignmentToSpecificGroup(){
        WebDriver driver=Driver.getWebDriver();
        try {

            String assignmentName =  ReadTestData.readDataByTagName("", "assessmentname", "261");

            new Assignment().create(261);
            new LoginUsingLTI().ltiLogin("261_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("261_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("261_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");

            new LoginUsingLTI().ltiLogin("261");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName=new AddGroup().addGroup(261); //add group
            System.out.println("groupName:" + groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1, 0, 3);//add first three student to group*//**//**//**//*

            new LoginUsingLTI().ltiLogin("261");
            new Assignment().assignAssignmentToGroup(261);

            new LoginUsingLTI().ltiLogin("261_1");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getAssessmentName());
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "correct", 1);
            Thread.sleep(60000);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("261_2");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getAssessmentName());
            new LoginUsingLTI().ltiLogin("261");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);

            try {
                assessmentResponses.resumeGrading_button.click();//click on yes
                WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);

            } catch (Exception e) {
            }
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            navigateToGradeBookPage();
            driver.navigate().refresh();
            navigateToGradeBookPage();
            new LoginUsingLTI().ltiLogin("261");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();
            actual=GradeBook.didNotStartPercentageCalculation(11, 0);
            expected="33%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.didNotFinishPercentageCalculation(11, 0);
            expected="33%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.finishedPercentageCalculation(11, 0);
            expected="33%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "Finished value is " + expected + "", "Finished value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1,"AverageTimeSpent",assignmentName);
            expected = "0 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify Average Time Spent value", "Average Time Spent value is " + expected + "", "Average Time Spent value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1, "AverageGrade", assignmentName);
            expected = "50%";
            CustomAssert.assertEquals(actual, expected, "Verify average grade value", "average grade value is " + expected + "", "average grade value is not " + expected + "");

            actual =gradebook.assignment_barEntry.get(0).getAttribute("height").trim();
            expected = "69";
            CustomAssert.assertEquals(actual,expected,"Verify Bar graph length in Avg Time Spent and Average grade","Bar graph length of Avg Time Spent and Average grade should not displayed","Bar graph length in Avg Time Spent and Average grade is displaying");

            new LoginUsingLTI().ltiLogin("261");//log in as instructor for alter grade
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getViewGrade_link().click(); //click on the view response student link
            new Assignment().enterGradeOnParticularQuestion(1, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(2, 0, "1.0");
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);

            navigateToGradeBookPage();
            actual =GradeBook.gradeBookStatusBoxCount(1, "AverageGrade", assignmentName);
            expected = "100%";
            CustomAssert.assertEquals(actual, expected, "Verify average grade value after alter grade", "average grade value is " + expected + "", "average grade value is not " + expected + "");

            actual =gradebook.assignment_barEntry.get(0).getAttribute("height").trim();
            expected = "140";
            CustomAssert.assertEquals(actual, expected, "Verify Bar graph length in Avg Time Spent and Average grade after alter grade", "Bar graph length of Avg Time Spent and Average grade should not displayed", "Bar graph length in Avg Time Spent and Average grade is displaying");

            new MouseHover().mouserhoverbywebelement(gradebook.assignment_barEntry.get(0));
            DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
            Calendar now = Calendar.getInstance();
            String calenderFormat = dateFormat.format(now.getTime());
            Thread.sleep(1000);
            actual = gradebook.dotBox.get(0).getText().trim();
            expected = assignmentName+"\n" + "Average Grade: 100%\n" + "Due Date: " + calenderFormat;
            CustomAssert.assertEquals(actual, expected, "Verify Bar Graph Value", " Bar Graph Value OnMouseHover  is " + expected + "", " Bar Graph Value OnMouseHover  is not " + expected + "");

            clickOnTheAssignmentLink(assignmentName);

            List<String> student = new ArrayList<String>();
            List<String> actualStudent = new ArrayList<String>();
            actualStudent.add("a, month");
            actualStudent.add("b, month");
            actualStudent.add("c, month");
            List<WebElement> studentName = gradebook.drillDown_studentName;
            for (WebElement we : studentName) {
                student.add(we.getText());
            }

            if (!student.equals(actualStudent)) {
                CustomAssert.fail("Verify student name", "student name is not in ascending order");
            }


            List<String> student1 = new ArrayList<String>();
            List<String> actualCompletionLevel = new ArrayList<String>();
            actualCompletionLevel.add("Finished");
            actualCompletionLevel.add("Did Not Finish");
            actualCompletionLevel.add("Did Not Start");
            List<WebElement> completionLevel = gradebook.drillDown_completionLevel;
            for (WebElement we : completionLevel) {
                student1.add(we.getText());
            }

            if (!student1.equals(actualCompletionLevel)) {
                CustomAssert.fail("Verify completionLevel", "completionLevel is not in ascending order");
            }

            List<String> student2 = new ArrayList<String>();
            List<String> actualTimeSpent = new ArrayList<String>();
            actualTimeSpent.add("0 min(s)");
            actualTimeSpent.add("0 min(s)");
            actualTimeSpent.add("0 min(s)");
            List<WebElement> timeSpent = gradebook.drillDown_timeSpent;
            for (WebElement we : timeSpent) {
                student2.add(we.getText());
            }

            if (!student2.equals(actualTimeSpent)) {
                CustomAssert.fail("Verify TimeSpent", "TimeSpent is not in ascending order");
            }

            actual =gradebook.drillDown_grade.get(0).getText().trim();
            expected = "100%\n1 / 1";
            CustomAssert.assertEquals(actual, expected, "Verify  grade", "Grade is displaying correctly", "Grade is not displaying correctly");

            actual =gradebook.assignment_barEntry.get(0).getAttribute("height").trim();
            expected = "140";
            CustomAssert.assertEquals(actual, expected, "Verify Bar graph length of Average grade after alter grade", "Bar graph length of Avg Time Spent and Average grade should not displayed", "Bar graph length in Avg Time Spent and Average grade is displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC assignAssignmentToSpecificGroup of class AssignmentPerformance",e);
        }
    }

    @Test(priority = 19,enabled = true)
    public void notApplicableImpact(){
        WebDriver driver=Driver.getWebDriver();
        try {


            String assignmentName =  ReadTestData.readDataByTagName("", "assessmentname", "19");

            new Assignment().create(19);
            new LoginUsingLTI().ltiLogin("19_1");//log in as student
            ReportUtil.log("Login as student1", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("19_2");//log in as student
            ReportUtil.log("Login as student2", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("19_3");//log in as student
            ReportUtil.log("Login as student3", "student logged successfully", "pass");

            new LoginUsingLTI().ltiLogin("19");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Assignment().assignAssignmentWithDueDate(19);


            new LoginUsingLTI().ltiLogin("19_1");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("19_2");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("19_3");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();


            new LoginUsingLTI().ltiLogin("19");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            new Assignment().enterGradeOnParticularQuestion(0, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(1, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(2, 0, "1.0");

            notApplicable(1); //NA student
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);
            try {
                assessmentResponses.resumeGrading_button.click();//click on yes
                WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);

              } catch (Exception e) {
            }
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all*/
            navigateToGradeBookPage();
            driver.navigate().refresh();
            navigateToGradeBookPage();

            actual=GradeBook.didNotStartPercentageCalculation(11, 0);
            expected="0%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.didNotFinishPercentageCalculation(11, 0);
            expected="0%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.finishedPercentageCalculation(11, 0);
            expected="100%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "Finished value is " + expected + "", "Finished value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1,"AverageTimeSpent",assignmentName);
            expected = "0 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify Average Time Spent value", "Average Time Spent value is " + expected + "", "Average Time Spent value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1, "AverageGrade", assignmentName);
            expected = "100%";
            CustomAssert.assertEquals(actual, expected, "Verify average grade value", "average grade value is " + expected + "", "average grade value is not " + expected + "");

            actual =gradebook.assignment_barEntry.get(0).getAttribute("height").trim();
            expected = "140";
            CustomAssert.assertEquals(actual,expected,"Verify Bar graph length in Avg Time Spent and Average grade","Bar graph length of Avg Time Spent and Average grade should not displayed","Bar graph length in Avg Time Spent and Average grade is displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC notApplicableImpact of class AssignmentPerformance",e);
        }
    }

    @Test(priority = 20,enabled = true)
    public void alterGradeForNotStartedStudent(){
        WebDriver driver=Driver.getWebDriver();
        try {


            String assignmentName =  ReadTestData.readDataByTagName("", "assessmentname", "21");
            new Assignment().create(21);
            new LoginUsingLTI().ltiLogin("21_1");//log in as student
            ReportUtil.log("Login as student1", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("21_2");//log in as student
            ReportUtil.log("Login as student2", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("21_3");//log in as student
            ReportUtil.log("Login as student3", "student logged successfully", "pass");

            new LoginUsingLTI().ltiLogin("21");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Assignment().assignAssignmentWithDueDate(21);


            new LoginUsingLTI().ltiLogin("21_1");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("21_2");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();
            Thread.sleep(60000); //wait for due to be expired


            new LoginUsingLTI().ltiLogin("21");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            new Assignment().enterGradeOnParticularQuestion(0, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(1, 0, "1.0");
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);

            try {
                assessmentResponses.resumeGrading_button.click();//click on yes
                WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);
            } catch (Exception e) {
            }

            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all*/
            navigateToGradeBookPage();
            driver.navigate().refresh();

            actual=GradeBook.didNotStartPercentageCalculation(11, 0);
            expected="33%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.didNotFinishPercentageCalculation(11, 0);
            expected="0%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.finishedPercentageCalculation(11, 0);
            expected="66%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "Finished value is " + expected + "", "Finished value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1,"AverageTimeSpent",assignmentName);
            expected = "0 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify Average Time Spent value", "Average Time Spent value is " + expected + "", "Average Time Spent value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1, "AverageGrade", assignmentName);
            expected = "100%";
            CustomAssert.assertEquals(actual, expected, "Verify average grade value", "average grade value is " + expected + "", "average grade value is not " + expected + "");

            actual =gradebook.assignment_barEntry.get(0).getAttribute("height").trim();
            expected = "140";
            CustomAssert.assertEquals(actual,expected,"Verify Bar graph length in Avg Time Spent and Average grade","Bar graph length of Avg Time Spent and Average grade should not displayed","Bar graph length in Avg Time Spent and Average grade is displaying");

            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            new Assignment().enterGradeOnParticularQuestion(2, 0, "0.6");
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);
            navigateToGradeBookPage();

            actual=GradeBook.didNotStartPercentageCalculation(11, 0);
            expected="33%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.didNotFinishPercentageCalculation(11, 0);
            expected="0%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.finishedPercentageCalculation(11, 0);
            expected="66%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "Finished value is " + expected + "", "Finished value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1,"AverageTimeSpent",assignmentName);
            expected = "0 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify Average Time Spent value", "Average Time Spent value is " + expected + "", "Average Time Spent value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1, "AverageGrade", assignmentName);
            expected = "87%";
            CustomAssert.assertEquals(actual, expected, "Verify average grade value", "average grade value is " + expected + "", "average grade value is not " + expected + "");

            actual =gradebook.assignment_barEntry.get(0).getAttribute("height").trim();
            expected = "121";
            CustomAssert.assertEquals(actual,expected,"Verify Bar graph length in Avg Time Spent and Average grade","Bar graph length of Avg Time Spent and Average grade should not displayed","Bar graph length in Avg Time Spent and Average grade is displaying");

            //Drill Down
            clickOnTheAssignmentLink(assignmentName);
            actual = gradebook.drillDown_grade.get(2).getText().trim();
            expected = "60%\n0.6 / 1";
            CustomAssert.assertEquals(actual, expected, "Verify grade", "For NA student grade is 'Not Applicable'", "For NA student grade is not 'Not Applicable'");

            actual =gradebook.assignment_barEntry.get(2).getAttribute("height").trim();
            expected = "83";
            CustomAssert.assertEquals(actual,expected,"Verify Bar graph length in Avg Time Spent and Average grade","Bar graph length of Avg Time Spent and Average grade should not displayed","Bar graph length in Avg Time Spent and Average grade is displaying");

            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            new Assignment().enterGradeOnParticularQuestion(2, 0, "0.0");
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);
            navigateToGradeBookPage();

            actual=GradeBook.didNotStartPercentageCalculation(11, 0);
            expected="33%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.didNotFinishPercentageCalculation(11, 0);
            expected="0%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.finishedPercentageCalculation(11, 0);
            expected="66%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "Finished value is " + expected + "", "Finished value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1,"AverageTimeSpent",assignmentName);
            expected = "0 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify Average Time Spent value", "Average Time Spent value is " + expected + "", "Average Time Spent value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1, "AverageGrade", assignmentName);
            expected = "100%";
            CustomAssert.assertEquals(actual, expected, "Verify average grade value", "average grade value is " + expected + "", "average grade value is not " + expected + "");

            actual =gradebook.assignment_barEntry.get(0).getAttribute("height").trim();
            expected = "140";
            CustomAssert.assertEquals(actual,expected,"Verify Bar graph length in Avg Time Spent and Average grade","Bar graph length of Avg Time Spent and Average grade should not displayed","Bar graph length in Avg Time Spent and Average grade is displaying");

            clickOnTheAssignmentLink(assignmentName);
            actual = gradebook.drillDown_grade.get(2).getText().trim();
            expected = "0%\n0 / 1";
            CustomAssert.assertEquals(actual, expected, "Verify grade", "For NA student grade is 'Not Applicable'", "For NA student grade is not 'Not Applicable'");


        } catch (Exception e) {
            Assert.fail("Exception in TC alterGradeForNotStartedStudent of class AssignmentPerformance",e);
        }
    }

    @Test(priority = 21,enabled = true)
    public void assignmentPerformanceForPolicyOne(){
        WebDriver driver=Driver.getWebDriver();
        try {


            String assignmentName =  ReadTestData.readDataByTagName("", "assessmentname", "22");
            String assignmentPolicyName =  ReadTestData.readDataByTagName("", "assignmentpolicyname", "22");

            new Assignment().create(22);
            new LoginUsingLTI().ltiLogin("22_1");//log in as student
            ReportUtil.log("Login as student1", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("22_2");//log in as student
            ReportUtil.log("Login as student2", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("22_3");//log in as student
            ReportUtil.log("Login as student3", "student logged successfully", "pass");

            new LoginUsingLTI().ltiLogin("22");//log in as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "", "22");
            ReportUtil.log("Navigated to policy", "Created policy with release option 1", "Pass");

            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Assignment().assignAssignmentWithDueDate(22);


            new LoginUsingLTI().ltiLogin("22_1");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "correct", 1);

            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("22_2");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();
            Thread.sleep(120000); //wait for due to be expired

            new LoginUsingLTI().ltiLogin("22");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);

            try {
                assessmentResponses.resumeGrading_button.click();//click on yes
                WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);
            } catch (Exception e) {
            }
            navigateToGradeBookPage();
            actual=GradeBook.didNotStartPercentageCalculation(11, 0);
            expected="33%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.didNotFinishPercentageCalculation(11, 0);
            expected="0%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.finishedPercentageCalculation(11, 0);
            expected="66%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "Finished value is " + expected + "", "Finished value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1,"AverageTimeSpent",assignmentName);
            expected = "0 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify Average Time Spent value", "Average Time Spent value is " + expected + "", "Average Time Spent value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1, "AverageGrade", assignmentName);
            expected = "100%";
            CustomAssert.assertEquals(actual, expected, "Verify average grade value", "average grade value is " + expected + "", "average grade value is not " + expected + "");

            actual =gradebook.assignment_barEntry.get(0).getAttribute("height").trim();
            expected = "140";
            CustomAssert.assertEquals(actual,expected,"Verify Bar graph length in Avg Time Spent and Average grade","Bar graph length of Avg Time Spent and Average grade should not displayed","Bar graph length in Avg Time Spent and Average grade is displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC assignmentPerformanceForPolicyOne of class AssignmentPerformance",e);
        }
    }

    @Test(priority = 22,enabled = true)
    public void assignmentPerformanceForPolicyTwo(){
        WebDriver driver=Driver.getWebDriver();
        try {


            String assignmentName =  ReadTestData.readDataByTagName("", "assessmentname", "23");
            String assignmentPolicyName =  ReadTestData.readDataByTagName("", "assignmentpolicyname", "23");

            new Assignment().create(23);
            new LoginUsingLTI().ltiLogin("23_1");//log in as student
            ReportUtil.log("Login as student1", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("23_2");//log in as student
            ReportUtil.log("Login as student2", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("23_3");//log in as student
            ReportUtil.log("Login as student3", "student logged successfully", "pass");

            new LoginUsingLTI().ltiLogin("23");//log in as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, false, "1", "", "Auto-release on due date", "", "", "", "23");
            ReportUtil.log("Navigated to policy", "Created policy with release option 1", "Pass");

            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Assignment().assignAssignmentWithDueDate(23);

            new LoginUsingLTI().ltiLogin("23_1");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "correct", 1);
            Thread.sleep(60000);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("23_2");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();
            Thread.sleep(60000); //wait for due to be expired

            new LoginUsingLTI().ltiLogin("23");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            Thread.sleep(5000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);
            try {
                assessmentResponses.resumeGrading_button.click();//click on yes
                Thread.sleep(4000);
                WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);

            } catch (Exception e) {
            }
            navigateToGradeBookPage();
            actual=GradeBook.didNotStartPercentageCalculation(11, 0);
            expected="33%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.didNotFinishPercentageCalculation(11, 0);
            expected="0%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.finishedPercentageCalculation(11, 0);
            expected="66%"; //67%
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "Finished value is " + expected + "", "Finished value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1,"AverageTimeSpent",assignmentName);
            expected = "1 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify Average Time Spent value", "Average Time Spent value is " + expected + "", "Average Time Spent value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1, "AverageGrade", assignmentName);
            expected = "100%";
            CustomAssert.assertEquals(actual, expected, "Verify average grade value", "average grade value is " + expected + "", "average grade value is not " + expected + "");

            actual =gradebook.assignment_barEntry.get(0).getAttribute("height").trim();
            expected = "140";
            CustomAssert.assertEquals(actual,expected,"Verify Bar graph length of Average grade","Bar graph length of Average grade should not displayed","Bar graph length of Average grade is displaying");

            actual =gradebook.assignment_barEntry.get(1).getAttribute("height").trim();
            expected = "35";
            CustomAssert.assertEquals(actual,expected,"Verify Bar graph length in Avg Time Spent ","Bar graph length of Avg Time Spent should not displayed","Bar graph length in Avg Time Spent  is displaying");

            clickOnTheAssignmentLink(assignmentName);
            Thread.sleep(1000);
            actual = gradebook.getAverage_grade_label.get(0).getText().trim();
            expected = "Average\n100%";
            CustomAssert.assertEquals(actual, expected, "Verify Bar Graph Value OnMouseHover of (0%-59%)", " Bar Graph Value OnMouseHover of (0%-59%) is " + expected + "", " Bar Graph Value OnMouseHover of (0%-59%) is not " + expected + "");

            new MouseHover().mouserhoverbywebelement(gradebook.assignment_barEntry.get(3));//Mouse hover on the average time spent
            Thread.sleep(1000);
            actual = gradebook.dotBox.get(1).getText().trim();
            expected = "a, month\n"+"Time Spent: 1 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify average time spent Value OnMouseHover of (60%-79%)", "Average time spent Value OnMouseHover of (60%-79%) is " + expected + "", "Average time spent Value OnMouseHover of (60%-79%) is not " + expected + "");

            actual = gradebook.getAverage_grade_label.get(1).getText().trim();
            expected = "Average\n1 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify Bar Graph Value OnMouseHover of (0%-59%)", " Bar Graph Value OnMouseHover of (0%-59%) is " + expected + "", " Bar Graph Value OnMouseHover of (0%-59%) is not " + expected + "");

        } catch (Exception e) {
            Assert.fail("Exception in TC assignmentPerformanceForPolicyTwo of class AssignmentPerformance",e);
        }
    }

    @Test(priority = 23,enabled = true)
    public void assignmentPerformanceForPolicyThree(){
        WebDriver driver=Driver.getWebDriver();
        try {


            String assignmentName =  ReadTestData.readDataByTagName("", "assessmentname", "24");
            String assignmentPolicyName =  ReadTestData.readDataByTagName("", "assignmentpolicyname", "24");

            new Assignment().create(24);
            new Assignment().addQuestions(24, "essay", "");

            new LoginUsingLTI().ltiLogin("24_1");//log in as student
            ReportUtil.log("Login as student1", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("24_2");//log in as student
            ReportUtil.log("Login as student2", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("24_3");//log in as student
            ReportUtil.log("Login as student3", "student logged successfully", "pass");

            new LoginUsingLTI().ltiLogin("24");//log in as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "","24");
            ReportUtil.log("Navigated to policy", "Created policy with release option 1", "Pass");

            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Assignment().assignAssignmentWithDueDate(24);

            new LoginUsingLTI().ltiLogin("24_1");//log in as student
            new Assignment().submitAssignmentAsStudent(24); //submit assignment
            ReportUtil.log("submit Assignment", "student1 submitted assignment", "pass");

            new LoginUsingLTI().ltiLogin("24_2");//log in as student
            new Assignment().submitAssignmentAsStudent(24); //submit assignment
            ReportUtil.log("submit Assignment", "student2 submitted assignment", "pass");

            new LoginUsingLTI().ltiLogin("24");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            Thread.sleep(3000);
            new Assignment().enterGradeOnParticularQuestion(0, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(0, 1, "0.5");

            new Assignment().enterGradeOnParticularQuestion(1, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(1, 1, "0.6");

            new Assignment().enterGradeOnParticularQuestion(1, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(1, 1, "0.5");
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);
            ReportUtil.log("Provide grade", "Instructor provided grade successfully", "pass");

            try {
                assessmentResponses.resumeGrading_button.click();//click on yes
                WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);

            } catch (Exception e) {
            }
            navigateToGradeBookPage();
            actual=GradeBook.didNotStartPercentageCalculation(11, 0);
            expected="33%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.didNotFinishPercentageCalculation(11, 0);
            expected="0%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.finishedPercentageCalculation(11, 0);
            expected="66%"; //67%
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "Finished value is " + expected + "", "Finished value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1,"AverageTimeSpent",assignmentName);
            expected = "1 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify Average Time Spent value", "Average Time Spent value is " + expected + "", "Average Time Spent value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1, "AverageGrade", assignmentName);
            expected = "38%";
            CustomAssert.assertEquals(actual, expected, "Verify average grade value", "average grade value is " + expected + "", "average grade value is not " + expected + "");

            actual =gradebook.assignment_barEntry.get(0).getAttribute("height").trim();
            expected = "53";
            CustomAssert.assertEquals(actual,expected,"Verify Bar graph length of Average grade","Bar graph length of Average grade should not displayed","Bar graph length of Average grade is displaying");

            actual =gradebook.assignment_barEntry.get(1).getAttribute("height").trim();
            expected = "35";
            CustomAssert.assertEquals(actual,expected,"Verify Bar graph length in Avg Time Spent ","Bar graph length of Avg Time Spent should not displayed","Bar graph length in Avg Time Spent  is displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC assignmentPerformanceForPolicyTwo of class AssignmentPerformance",e);
        }
    }

    @Test(priority = 24,enabled = true)
    public  void verifyScoreForAdaptivePracticeWithThreshold(){
        try {

            String assignmentName="ORION Ch 1: The Study of Life";
            new LoginUsingLTI().ltiLogin("25_1");//log in as student
            ReportUtil.log("Login as student1", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("25_2");//log in as student
            ReportUtil.log("Login as student2", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("25_3");//log in as student
            ReportUtil.log("Login as student3", "student logged successfully", "pass");

            new LoginUsingLTI().ltiLogin("25");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            WebDriverUtil.clickOnElementUsingJavascript(dashBoard.toc_drop); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(25);
            Thread.sleep(6000);
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            Thread.sleep(4000);
            try {
                WebDriverUtil.clickOnElementUsingJavascript(assessmentResponses.resumeGrading_button);//click on yes
            } catch (Exception e) {
            }

            new Assignment().enterGradeOnParticularQuestion(0,0,"20");
            new Assignment().enterGradeOnParticularQuestion(1,0,"30");
            new Assignment().enterGradeOnParticularQuestion(2,0,"40");
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);

            navigateToGradeBookPage();
            actual=GradeBook.didNotStartPercentageCalculation(11, 0);
            expected="100%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.didNotFinishPercentageCalculation(11, 0);
            expected="0%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.finishedPercentageCalculation(11, 0);
            expected="0%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "Finished value is " + expected + "", "Finished value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1,"AverageTimeSpent",assignmentName);
            expected = "0 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify Average Time Spent value", "Average Time Spent value is " + expected + "", "Average Time Spent value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1, "AverageGrade", assignmentName);
            expected = "30%";
            CustomAssert.assertEquals(actual, expected, "Verify average grade value", "average grade value is " + expected + "", "average grade value is not " + expected + "");

            actual =gradebook.assignment_barEntry.get(0).getAttribute("height").trim();
            expected = "42";
            CustomAssert.assertEquals(actual,expected,"Verify Bar graph length of Average grade","Bar graph length of Average grade should not displayed","Bar graph length of Average grade is displaying");

            clickOnTheAssignmentLink(assignmentName);
            Thread.sleep(1000);
            actual = gradebook.getAverage_grade_label.get(0).getText().trim();
            expected = "Average\n30%";
            CustomAssert.assertEquals(actual, expected, "Verify Bar Graph Value OnMouseHover of (0%-59%)", " Bar Graph Value OnMouseHover of (0%-59%) is " + expected + "", " Bar Graph Value OnMouseHover of (0%-59%) is not " + expected + "");


            actual = gradebook.getAverage_grade_label.get(1).getText().trim();
            expected = "Average\n0 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify Bar Graph Value OnMouseHover of (0%-59%)", " Bar Graph Value OnMouseHover of (0%-59%) is " + expected + "", " Bar Graph Value OnMouseHover of (0%-59%) is not " + expected + "");

            List<String> student = new ArrayList<String>();
            List<String> actualStudent = new ArrayList<String>();
            actualStudent.add("a, month");
            actualStudent.add("b, month");
            actualStudent.add("c, month");
            List<WebElement> studentName = gradebook.drillDown_studentName;
            for (WebElement we : studentName) {
                student.add(we.getText());
            }

            if (!student.equals(actualStudent)) {
                CustomAssert.fail("Verify student name", "student name is not in ascending order");
            }


            List<String> student1 = new ArrayList<String>();
            List<String> actualCompletionLevel = new ArrayList<String>();
            actualCompletionLevel.add("Did Not Start");
            actualCompletionLevel.add("Did Not Start");
            actualCompletionLevel.add("Did Not Start");
            List<WebElement> completionLevel = gradebook.drillDown_completionLevel;
            for (WebElement we : completionLevel) {
                student1.add(we.getText());
            }

            if (!student1.equals(actualCompletionLevel)) {
                CustomAssert.fail("Verify completionLevel", "completionLevel is not in ascending order");
            }

            List<String> student2 = new ArrayList<String>();
            List<String> actualTimeSpent = new ArrayList<String>();
            actualTimeSpent.add("0 min(s)");
            actualTimeSpent.add("0 min(s)");
            actualTimeSpent.add("0 min(s)");
            List<WebElement> timeSpent = gradebook.drillDown_timeSpent;
            for (WebElement we : timeSpent) {
                student2.add(we.getText());
            }

            if (!student2.equals(actualTimeSpent)) {
                CustomAssert.fail("Verify TimeSpent", "TimeSpent is not in ascending order");
            }

            actual =gradebook.drillDown_grade.get(0).getText().trim();
            expected = "20%\n20 / 100";
            CustomAssert.assertEquals(actual, expected, "Verify  grade", "Grade is displaying correctly", "Grade is not displaying correctly");


        } catch (Exception e) {
            Assert.fail("Exception in TC verifyScoreForAdaptivePracticeWithThreshold of class  AssignmentPerformance", e);
        }

    }

    @Test(priority = 24,enabled = true,successPercentage = 5)
    public  void verifyScoreForAdaptivePracticeWithoutThreshold(){
        try {

            String assignmentName="ORION Ch 1: The Study of Life";
            new LoginUsingLTI().ltiLogin("26_1");//log in as student
            ReportUtil.log("Login as student1", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("26_2");//log in as student
            ReportUtil.log("Login as student2", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("26_3");//log in as student
            ReportUtil.log("Login as student3", "student logged successfully", "pass");

            new LoginUsingLTI().ltiLogin("26");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            WebDriverUtil.clickOnElementUsingJavascript(dashBoard.toc_drop); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(26);
            Thread.sleep(60000);
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            Thread.sleep(4000);
            try {
                WebDriverUtil.clickOnElementUsingJavascript(assessmentResponses.resumeGrading_button);//click on yes
            } catch (Exception e) {
            }

            new Assignment().enterGradeOnParticularQuestion(0,0,"20");
            new Assignment().enterGradeOnParticularQuestion(1,0,"30");
            new Assignment().enterGradeOnParticularQuestion(2,0,"40");
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);

            navigateToGradeBookPage();
            actual=GradeBook.didNotStartPercentageCalculation(11, 0);
            expected="100%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.didNotFinishPercentageCalculation(11, 0);
            expected="0%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.finishedPercentageCalculation(11, 0);
            expected="0%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "Finished value is " + expected + "", "Finished value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1,"AverageTimeSpent",assignmentName);
            expected = "0 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify Average Time Spent value", "Average Time Spent value is " + expected + "", "Average Time Spent value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1, "AverageGrade", assignmentName);
            expected = "30%";
            CustomAssert.assertEquals(actual, expected, "Verify average grade value", "average grade value is " + expected + "", "average grade value is not " + expected + "");

            actual =gradebook.assignment_barEntry.get(0).getAttribute("height").trim();
            expected = "42";
            CustomAssert.assertEquals(actual,expected,"Verify Bar graph length of Average grade","Bar graph length of Average grade should not displayed","Bar graph length of Average grade is displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC verifyScoreForAdaptivePracticeWithoutThreshold of class  AssignmentPerformance", e);
        }

    }

    @Test(priority = 25,enabled = true)
    public  void discussionWidget(){
        try {

            String assignmentName="What is the importance of phosphorylation in the positive regulation of the cell cycle and how might this affect the protein activity?";

            new LoginUsingLTI().ltiLogin("27_1");//log in as student
            ReportUtil.log("Login as student1", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("27_2");//log in as student
            ReportUtil.log("Login as student2", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("27_3");//log in as student
            ReportUtil.log("Login as student3", "student logged successfully", "pass");

            new LoginUsingLTI().ltiLogin("27");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(27);
            ReportUtil.log("Login as student", "Login as student for flowForGradableDWAssignment method", "info");

            new LoginUsingLTI().ltiLogin("27_1");//log in as student
            ReportUtil.log("Login as student1", "student logged successfully", "pass");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);//click on the resources name
            String perspectives = new RandomString().randomstring(4);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspectives); //add perspective to DW assignment
            ReportUtil.log("Add perspective","Perspective added successfully","pass");

            new LoginUsingLTI().ltiLogin("27_2");//log in as student
            ReportUtil.log("Login as student2", "student logged successfully", "pass");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);//click on the resources name
            perspectives = new RandomString().randomstring(3);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspectives); //add perspective to DW assignment
            ReportUtil.log("Add perspective","Perspective added successfully","pass");

            new LoginUsingLTI().ltiLogin("27_3");//log in as student
            ReportUtil.log("Login as student3", "student logged successfully", "pass");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);//click on the resources name
            perspectives = new RandomString().randomstring(2);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspectives); //add perspective to DW assignment
            ReportUtil.log("Add perspective","Perspective added successfully","pass");

            new LoginUsingLTI().ltiLogin("27");//log in as instructor
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            new Assignment().enterGradeOnParticularQuestion(0,0,"1.0");
            new Assignment().enterGradeOnParticularQuestion(1,0,"1.8");
            new Assignment().enterGradeOnParticularQuestion(2,0,"1.5");
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);
            navigateToGradeBookPage();
            driver.navigate().refresh();

            navigateToGradeBookPage();

            actual=GradeBook.didNotStartPercentageCalculation(11, 0);
            expected="0%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.didNotFinishPercentageCalculation(11, 0);
            expected="0%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.finishedPercentageCalculation(11, 0);
            expected="100%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "Finished value is " + expected + "", "Finished value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1,"AverageTimeSpent",assignmentName);
            expected = "Not Available";
            CustomAssert.assertEquals(actual, expected, "Verify Average Time Spent value", "Average Time Spent value is " + expected + "", "Average Time Spent value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1, "AverageGrade", assignmentName);
            expected = "72%";
            CustomAssert.assertEquals(actual, expected, "Verify average grade value", "average grade value is " + expected + "", "average grade value is not " + expected + "");

            actual =gradebook.assignment_barEntry.get(0).getAttribute("height").trim();
            expected = "100";
            CustomAssert.assertEquals(actual,expected,"Verify Bar graph length of Average grade","Bar graph length of Average grade should not displayed","Bar graph length of Average grade is displaying");


            clickOnTheAssignmentLink(assignmentName);
            List<String> student1 = new ArrayList<String>();
            List<String> actualCompletionLevel = new ArrayList<String>();
            actualCompletionLevel.add("Finished");
            actualCompletionLevel.add("Finished");
            actualCompletionLevel.add("Finished");
            List<WebElement> completionLevel = gradebook.drillDown_completionLevel;
            for (WebElement we : completionLevel) {
                student1.add(we.getText());
            }

            if (!student1.equals(actualCompletionLevel)) {
                CustomAssert.fail("Verify completionLevel", "completionLevel is not in ascending order");
            }

            List<String> student2 = new ArrayList<String>();
            List<String> actualTimeSpent = new ArrayList<String>();
            actualTimeSpent.add("Not Available");
            actualTimeSpent.add("Not Available");
            actualTimeSpent.add("Not Available");
            List<WebElement> timeSpent = gradebook.drillDown_timeSpent;
            for (WebElement we : timeSpent) {
                student2.add(we.getText());
            }

            if (!student2.equals(actualTimeSpent)) {
                CustomAssert.fail("Verify TimeSpent", "TimeSpent is not in ascending order");
            }

            actual =gradebook.drillDown_grade.get(0).getText().trim();
            expected = "50%\n1 / 2";
            CustomAssert.assertEquals(actual, expected, "Verify  grade", "Grade is displaying correctly", "Grade is not displaying correctly");

            actual = gradebook.getAverage_grade_label.get(1).getText().trim();
            expected = "Average\n" + "Not Available";
            CustomAssert.assertEquals(actual, expected, "Verify grade", "For NA student grade is 'Not Applicable'", "For NA student grade is not 'Not Applicable'");


        } catch (Exception e) {
            Assert.fail("Exception in TC discussionWidget of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 26, enabled = true)
    public void addStudentToClassSection() {
        try {

            String assignmentName =  ReadTestData.readDataByTagName("", "assessmentname", "28");
            new Assignment().create(28);
            new LoginUsingLTI().ltiLogin("28_1");//log in as student
            ReportUtil.log("Login as student1", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("28_2");//log in as student
            ReportUtil.log("Login as student2", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("28_3");//log in as student
            ReportUtil.log("Login as student3", "student logged successfully", "pass");

            new LoginUsingLTI().ltiLogin("28");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Assignment().assignAssignmentWithDueDate(28);

            new LoginUsingLTI().ltiLogin("28_1");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("28_2");//log in as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("28_3");//log in as student3
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("28");//log in as instructor
            new LoginUsingLTI().ltiLogin("28_4");//log in as add student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "correct", 1);


            new LoginUsingLTI().ltiLogin("28");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);
            try {
                WebDriverUtil.clickOnElementUsingJavascript(assessmentResponses.resumeGrading_button);//click on yes
                WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);
            } catch (Exception e) {

            }
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all

            navigateToGradeBookPage();
            actual=GradeBook.didNotStartPercentageCalculation(11, 0);
            expected="0%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.didNotFinishPercentageCalculation(11, 0);
            expected="25%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "DidNotStart value is " + expected + "", "DidNotStart value is not " + expected + "");

            actual=GradeBook.finishedPercentageCalculation(11, 0);
            expected="75%";
            CustomAssert.assertEquals(actual, expected, "Verify (a/b)% value", "Finished value is " + expected + "", "Finished value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1,"AverageTimeSpent",assignmentName);
            expected = "0 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify Average Time Spent value", "Average Time Spent value is " + expected + "", "Average Time Spent value is not " + expected + "");

            actual =GradeBook.gradeBookStatusBoxCount(1, "AverageGrade", assignmentName);
            expected = "75%";
            CustomAssert.assertEquals(actual, expected, "Verify average grade value", "average grade value is " + expected + "", "average grade value is not " + expected + "");

            actual =gradebook.assignment_barEntry.get(0).getAttribute("height").trim();
            expected = "104";
            CustomAssert.assertEquals(actual,expected,"Verify Bar graph length in Avg Time Spent and Average grade","Bar graph length of Avg Time Spent and Average grade should not displayed","Bar graph length in Avg Time Spent and Average grade is displaying");


            clickOnTheAssignmentLink(assignmentName);

            List<String> student = new ArrayList<String>();
            List<String> actualStudent = new ArrayList<String>();
            actualStudent.add("a, month");
            actualStudent.add("b, month");
            actualStudent.add("c, month");
            actualStudent.add("d, month");
            List<WebElement> studentName = gradebook.drillDown_studentName;
            for (WebElement we : studentName) {
                student.add(we.getText());
            }

            if (!student.equals(actualStudent)) {
                CustomAssert.fail("Verify student name", "student name is not in ascending order");
            }


            List<String> student1 = new ArrayList<String>();
            List<String> actualCompletionLevel = new ArrayList<String>();
            actualCompletionLevel.add("Finished");
            actualCompletionLevel.add("Finished");
            actualCompletionLevel.add("Finished");
            actualCompletionLevel.add("Did Not Finish");
            List<WebElement> completionLevel = gradebook.drillDown_completionLevel;
            for (WebElement we : completionLevel) {
                student1.add(we.getText());
            }

            if (!student1.equals(actualCompletionLevel)) {
                CustomAssert.fail("Verify completionLevel", "completionLevel is not in ascending order");
            }

            List<String> student2 = new ArrayList<String>();
            List<String> actualTimeSpent = new ArrayList<String>();
            actualTimeSpent.add("0 min(s)");
            actualTimeSpent.add("0 min(s)");
            actualTimeSpent.add("0 min(s)");
            actualTimeSpent.add("0 min(s)");
            List<WebElement> timeSpent = gradebook.drillDown_timeSpent;
            for (WebElement we : timeSpent) {
                student2.add(we.getText());
            }

            if (!student2.equals(actualTimeSpent)) {
                CustomAssert.fail("Verify TimeSpent", "TimeSpent is not in ascending order");
            }

            actual =gradebook.drillDown_grade.get(3).getText().trim();
            expected = "0%\n0 / 1";
            CustomAssert.assertEquals(actual, expected, "Verify  grade", "Grade is displaying correctly", "Grade is not displaying correctly");

            actual =gradebook.perGradeAssignmentStudent_label.get(3).getText().trim();
            expected = "d, m";
            CustomAssert.assertEquals(actual, expected,"Verify Added student Entry for Per Grade Assignment","Newly student is displaying","Newly student is not displaying");

            actual =gradebook.perTimeSpentStudent_label.get(3).getText().trim();
            expected = "d, m";
            CustomAssert.assertEquals(actual, expected,"Verify Added student Entry for Per Time Spent","Newly student is displaying","Newly student is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC addStudentToClassSection of class  AssignmentPerformance", e);
        }
    }

}

