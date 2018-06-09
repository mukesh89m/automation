package com.snapwiz.selenium.tests.staf.learningspaces.testcases.sanityPack.ls;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.PerformanceSummary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by priyanka on 16-11-2015.
 */
public class AssignmentWithPolicyLsAdaptive extends Driver {

    @Test(priority=1,enabled = true)
    public void assignmentWithPolicyOneLsAdaptive()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //"1. Login as Instructor 2. Navigate to question bank page. 3.assign an Gradable assignment with Policy1"
            //4.Login to student and complete the assignment

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "1");

            new Assignment().create(1);
            new Assignment().addQuestions(1,"multiplechoice","");
            new Assignment().addQuestions(1,"multipleselection","");
            new Assignment().addQuestions(1,"textentry","");
            new Assignment().addQuestions(1,"textdropdown","");
            new Assignment().addQuestions(1,"numericentrywithunits","");
            new Assignment().addQuestions(1,"advancednumeric","");
            new Assignment().addQuestions(1,"expressionevaluator","");
            new Assignment().addQuestions(1,"essay","");
            new Assignment().addQuestions(1,"audio","");
            new Assignment().addQuestions(1,"multipart","");
            new Assignment().addQuestions(1,"draganddrop","");
            new Assignment().addQuestions(1,"matchthefollowing","");
            new Assignment().addQuestions(1,"labelAnImageText","");
            new Assignment().addQuestions(1,"labelAnImageDropdown","");

            new LoginUsingLTI().ltiLogin("1");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//till save policy

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(1);//assign to student

            new LoginUsingLTI().ltiLogin("1_1");//login as instructor
            new Assignment().submitAssignmentAsStudent(1);

            new LoginUsingLTI().ltiLogin("1");//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            Assert.assertEquals(currentAssignments.gradedStatus.getText(),"Graded","grade is not released after student submitted the assignment");
            currentAssignments.getViewGrade_link().click();//click on view grades link
            boolean elementFound = false;
            try{
                driver.findElement(By.cssSelector("div[title='Release Grade for All']"));
                elementFound = true;
            }
            catch (Exception e){
                elementFound = false;
            }
            Assert.assertEquals(elementFound,false,"grade is not released after student submitted the assignment");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase assignmentWithPolicyOneLsAdaptive of class AssignmentWithPolicyLsAdaptive" + e);
        }
    }


    @Test(priority=2,enabled = true)
    public void assignmentWithPolicyThreeLsAdaptive()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //"1. Login as Instructor 2. Navigate to question bank page. 3.assign an Gradable assignment with Policy3"
            //4.Login to student and complete the assignment

            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "4");

            new LoginUsingLTI().ltiLogin("4");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");//till save policy

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(4);//assign to student

            new LoginUsingLTI().ltiLogin("4_1");//login as instructor
            new Assignment().submitAssignmentAsStudent(4);

            new LoginUsingLTI().ltiLogin("4");//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            new Assignment().releaseGrades(4, "Release Grade for All");
            Thread.sleep(1000);
            Assert.assertEquals(assignmentResponsesPage.gradeBook_Box.getAttribute("title"), "Grade Released", "grade is not released after instructor released the grade");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase assignmentWithPolicyThreeLsAdaptive of class AssignmentWithPolicyLsAdaptive" + e);
        }
    }

    @Test(priority=3,enabled = true)
    public void assignmentWithPolicyFourLsAdaptive()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //"1. Login as Instructor 2. Navigate to question bank page. 3.assign an Gradable assignment with Policy4"
            //4.Login to student and complete the assignment

            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "5");

            new LoginUsingLTI().ltiLogin("5");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description21", "2", null, false, "1", "", "Release explicitly by the instructor", "", "", "");//policy 4

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(5);//assign to student

            new LoginUsingLTI().ltiLogin("5_1");//login as instructor
            new Assignment().submitAssignmentAsStudent(5);

            new LoginUsingLTI().ltiLogin("5");//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            new Assignment().releaseGrades(5, "Release Grade for All");
            Thread.sleep(1000);
            Assert.assertEquals(assignmentResponsesPage.gradeBook_Box.getAttribute("title"), "Grade Released", "grade is not released after instructor released the grade");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase assignmentWithPolicyFourLsAdaptive of class AssignmentWithPolicyLsAdaptive" + e);
        }
    }

    @Test(priority=4,enabled = true)
    public void diagnosticAssessment()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //"1. Login as Instructor 2.  Go to any chapter and start attempting Diagnostic assessment. "
            //3. Complete the assessment


            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "7");

            new LoginUsingLTI().ltiLogin("7");//login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            new DiagnosticTest().startTest(1); //start Diagnostic test
            new DiagnosticTest().attemptAllCorrect(2, false, false); //attempt all correct question
            Assert.assertEquals(performanceSummary.title_performanceSummary.getText(),"Performance Summary","User is not landing  to Performance summary page.");
            new QuestionCard().clickOnCard("7",0);
            Assert.assertEquals(performanceSummary.questionContent.isDisplayed(),true,"Question content is not available");


            new LoginUsingLTI().ltiLogin("7");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to eTextbook
            new PracticeTest().startTest(); //start practice test
            for(int i = 0; i < 10; i++) {
                new PracticeTest().AttemptCorrectAnswer(0,"7");
            }
            new PracticeTest().quitThePracticeTest();
            Assert.assertEquals(performanceSummary.title_performanceSummary.getText(),"Performance Summary","User is not landing  to Performance summary page.");
            if(performanceSummary.questionCart.size()!=10){
                Assert.fail("All questionccards is not seen on right side.");
            }
            new QuestionCard().clickOnCard("7",0);
            Assert.assertEquals(performanceSummary.questionContent.isDisplayed(),true,"Question content is not available");

            new Assignment().create(7);
            new Assignment().addQuestions(7,"all","");

            new LoginUsingLTI().ltiLogin("7");//login as student
            new Navigator().NavigateTo("e-Textbook");
            new SelectCourse().selectInvisibleAssignment(assessmentname);
            new AttemptTest().StaticTest();// continue after quiting test
            Assert.assertEquals(performanceSummary.title_performanceSummary.getText(),"Performance Summary","User is not landing  to Performance summary page.");
            new QuestionCard().clickOnCard("7",0);
            Assert.assertEquals(performanceSummary.questionContent.isDisplayed(),true,"Question content is not available");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase diagnosticAssessment of class AssignmentWithPolicyLsAdaptive" + e);
        }
    }


    @Test(priority=5,enabled = true)
    public void diagnosticAssessmentForLs()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //"1. Login as Instructor 2. Go to any chapter and start attempting Static assessment. "
            //3. Complete the assessment


            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "17");

            new Assignment().create(17);
            new Assignment().addQuestions(17,"all","");

            new LoginUsingLTI().ltiLogin("17");//login as student
            new Navigator().NavigateTo("e-Textbook");
            new SelectCourse().selectInvisibleAssignment(assessmentname);
            new AttemptTest().StaticTest();// continue after quiting test
            Assert.assertEquals(performanceSummary.title_performanceSummary.getText(),"Performance Summary","User is not landing  to Performance summary page.");
            new QuestionCard().clickOnCard("17",0);
            Assert.assertEquals(performanceSummary.questionContent.isDisplayed(),true,"Question content is not available");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase diagnosticAssessmentForLs of class AssignmentWithPolicyLsAdaptive" + e);
        }
    }

    @Test(priority=6,enabled = true)
    public void nonGradeAbleAssignmentLsAdaptive()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //"1. Login as Instructor 2. Assign an Assessment through Questionbank page."
            //4.Login to student and complete the assignment

            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);

            new LoginUsingLTI().ltiLogin("21");//login as instructor
            new Assignment().assignToStudent(21);//assign to student

            new LoginUsingLTI().ltiLogin("21_1");//login as student
            new Assignment().submitAssignmentAsStudent(21);
            new QuestionCard().clickOnCard("21",0);
            Assert.assertEquals(performanceSummary.question_Content.isDisplayed(),true,"Question content is not available");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase assignmentWithPolicyOneLsAdaptive of class AssignmentWithPolicyLsAdaptive" + e);
        }
    }

}

