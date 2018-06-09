package com.snapwiz.selenium.tests.staf.learningspaces.testcases.regression.assignment;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.CustomAssert;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by mukesh on 11/1/16.
 */
public class PolicyWithAssignmentFlow  extends Driver {

    @Test(priority = 1,enabled = true)
    public void gradableAssignmentWithPolicyOne(){
        WebDriver driver=Driver.getWebDriver();
        try {

            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", "115");
            String assignmentName = ReadTestData.readDataByTagName("", "assessmentname", "115");
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);

            int notStarted;
            int inProgress;
            int submitted;
            int graded;


            new Assignment().create(115);
            for(int i =0 ; i<2 ; i++)
                new Assignment().addQuestions(115, "qtn-type-true-false-img", assignmentName);


            new LoginUsingLTI().ltiLogin("115_1");	//login as a student1
            new LoginUsingLTI().ltiLogin("115_2");	//login as a student2
            new LoginUsingLTI().ltiLogin("115_3");	//login as a student3

            new LoginUsingLTI().ltiLogin("115");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//policy one

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(115);//assign to student

            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            Assert.assertEquals(currentAssignments.getAssessmentName().getText().trim(),assignmentName);
            new Assignment().verifyClassAssignmentStatus(115,"Available for Students");

            notStarted = new Assignment().statusBoxCount(115, "Not Started");

            Assert.assertEquals(notStarted,3,"Student status \"Not Started\" count is not 3");
            inProgress = new Assignment().statusBoxCount(115, "In Progress");
            Assert.assertEquals(inProgress,0,"Student status \"In Progress\" count is not 0");

            submitted = new Assignment().statusBoxCount(115, "Submitted");
            Assert.assertEquals(submitted,0,"Student status \"Submitted\" count is not 0");

            graded = new Assignment().statusBoxCount(115, "Graded");
            Assert.assertEquals(graded,0,"Student status \"Graded\" count is not 0");

            new LoginUsingLTI().ltiLogin("115_1");	//login as a student1
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().statusBoxCheckInStudentAssignmentPage(115, "1", "Not Started");
            new Assignment().verifyClassAssignmentStatus(115, "Not Started");
            new Assignment().submitAssignmentAsStudent(115); //submit assignment

            new LoginUsingLTI().ltiLogin("115");//login as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click(); //click on the view response student link
            Assert.assertEquals(assignmentResponsesPage.grade_Box.getText(), "Release Grade for All", "Release Grade for All link is not displaying ");

            notStarted = new Assignment().statusBoxCount(115, "Not Started");
            Assert.assertEquals(notStarted,2,"Student status \"Not Started\" count is not 2");

            inProgress = new Assignment().statusBoxCount(115, "In Progress");
            Assert.assertEquals(inProgress,0,"Student status \"In Progress\" count is not 0");

            submitted = new Assignment().statusBoxCount(115, "Submitted");
            Assert.assertEquals(submitted,0,"Student status \"Submitted\" count is not 0");

            graded = new Assignment().statusBoxCount(115, "Graded");
            Assert.assertEquals(graded,1,"Student status \"Graded\" count is not 1");

            new LoginUsingLTI().ltiLogin("115_2");	//login as a student2
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().statusBoxCheckInStudentAssignmentPage(115, "1", "Not Started");
            new Assignment().verifyClassAssignmentStatus(115, "Not Started");
            new Assignment().submitAssignmentAsStudent(115); //submit assignment

            new LoginUsingLTI().ltiLogin("115_3");	//login as a student3
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().statusBoxCheckInStudentAssignmentPage(115, "1", "Not Started");
            new Assignment().verifyClassAssignmentStatus(115, "Not Started");
            new Assignment().submitAssignmentAsStudent(115); //submit assignment

            new LoginUsingLTI().ltiLogin("115");//login as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            new Assignment().verifyClassAssignmentStatus(115, "Graded");

            currentAssignments.getViewGrade_link().click(); //click on the view response student link
            Assert.assertEquals(assignmentResponsesPage.grade_Box.getText(), "Grade Released", "Release Grade for All link still displaying  after all student submmited assignment having policy 1");

            notStarted = new Assignment().statusBoxCount(115, "Not Started");
            Assert.assertEquals(notStarted,0,"Student status \"Not Started\" count is not 0");

            inProgress = new Assignment().statusBoxCount(115, "In Progress");
            Assert.assertEquals(inProgress,0,"Student status \"In Progress\" count is not 0");

            submitted = new Assignment().statusBoxCount(115, "Submitted");
            Assert.assertEquals(submitted,0,"Student status \"Submitted\" count is not 0");

            graded = new Assignment().statusBoxCount(115, "Graded");
            Assert.assertEquals(graded,3,"Student status \"Graded\" count is not 3");

            new LoginUsingLTI().ltiLogin("115_3");	//login as a student3
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            String score=assignments.getScore().getText();
            Assert.assertEquals(score,"Score (6/6)","score of student is not 10/100 according to given ranges ");

            new LoginUsingLTI().ltiLogin("115_2");	//login as a student3e
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            String score1=assignments.getScore().getText();
            Assert.assertEquals(score1,"Score (6/6)","score of student is not 10/100 according to given ranges ");


        } catch (Exception e) {
            Assert.fail("Exception in TC gradableAssignmentWithPolicyOne of class PolicyWithAssignmentFlow ", e);
        }

    }


    @Test(priority = 2,enabled = true)
    public void gradableAssignmentWithPolicyFour(){
        WebDriver driver=Driver.getWebDriver();
        try {

            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", "116");
            String assignmentName = ReadTestData.readDataByTagName("", "assessmentname", "116");
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);

            int notStarted;
            int inProgress;
            int submitted;
            int graded;

            new LoginUsingLTI().ltiLogin("116_1");	//login as a student1
            new LoginUsingLTI().ltiLogin("116_2");	//login as a student2
            new LoginUsingLTI().ltiLogin("116_3");	//login as a student3


            new LoginUsingLTI().ltiLogin("116");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description116", "2", null, false, "1", "", "", "", "", "");//policy 4

            new LoginUsingLTI().ltiLogin("116");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(116);//assign to student

            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            Assert.assertEquals(currentAssignments.getAssessmentName().getText().trim(),assignmentName);

            new Assignment().verifyClassAssignmentStatus(116,"Available for Students");

            notStarted = new Assignment().statusBoxCount(116, "Not Started");
            Assert.assertEquals(notStarted,3,"Student status \"Not Started\" count is not 3");

            inProgress = new Assignment().statusBoxCount(116, "In Progress");
            Assert.assertEquals(inProgress,0,"Student status \"In Progress\" count is not 0");

            submitted = new Assignment().statusBoxCount(116, "Submitted");
            Assert.assertEquals(submitted,0,"Student status \"Submitted\" count is not 0");

            graded = new Assignment().statusBoxCount(116, "Graded");
            Assert.assertEquals(graded,0,"Student status \"Graded\" count is not 0");

            new LoginUsingLTI().ltiLogin("116_1");	//login as a student1
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().statusBoxCheckInStudentAssignmentPage(116, "1", "Not Started");
            new Assignment().verifyClassAssignmentStatus(116, "Not Started");
            new Assignment().submitAssignmentAsStudent(116); //submit assignment


            new LoginUsingLTI().ltiLogin("116");//login as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click(); //click on the view response student link
            new Assignment().verifyClassAssignmentStatus(116, "Available for Students");
            Assert.assertEquals(new BooleanValue().presenceOfElement(116, By.cssSelector("div[class='idb-gradeBook-grades-section-release idb-gradeBook-release-grades-section idb-gradeBook-grades-released']")), false);

            notStarted = new Assignment().statusBoxCount(116, "Not Started");
            Assert.assertEquals(notStarted,2,"Student status \"Not Started\" count is not 2");

            inProgress = new Assignment().statusBoxCount(116, "In Progress");
            Assert.assertEquals(inProgress,0,"Student status \"In Progress\" count is not 0");

            submitted = new Assignment().statusBoxCount(116, "Submitted");
            Assert.assertEquals(submitted,0,"Student status \"Submitted\" count is not 0");

            graded = new Assignment().statusBoxCount(116, "Graded");
            Assert.assertEquals(graded,1,"Student status \"Graded\" count is not 1");

            new LoginUsingLTI().ltiLogin("116_2");	//login as a student2
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            new Assignment().statusBoxCheckInStudentAssignmentPage(116,"1","Not Started");
            new Assignment().verifyClassAssignmentStatus(116, "Not Started");

            currentAssignments.getAssessmentName().click();//click on assignment
            new AttemptQuestion().trueFalse(false, "correct", 355);
            assignments.getNextQuestion().click();

            new Navigator().NavigateTo("Assignments");//navigate to assignment
            new Assignment().statusBoxCheckInStudentAssignmentPage(116,"1","In Progress");
            new Assignment().verifyClassAssignmentStatus(116, "In Progress");

            new LoginUsingLTI().ltiLogin("116");//login as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click(); //click on the view response student link

            notStarted = new Assignment().statusBoxCount(116, "Not Started");
            Assert.assertEquals(notStarted,1,"Student status \"Not Started\" count is not 0");

            inProgress = new Assignment().statusBoxCount(116, "In Progress");
            Assert.assertEquals(inProgress,1,"Student status \"In Progress\" count is not 0");

            submitted = new Assignment().statusBoxCount(116, "Submitted");
            Assert.assertEquals(submitted,0,"Student status \"Submitted\" count is not 0");

            graded = new Assignment().statusBoxCount(116, "Graded");
            Assert.assertEquals(graded,1,"Student status \"Graded\" count is not 1");

            new LoginUsingLTI().ltiLogin("116_2");	//login as a student2
            new Assignment().submitAssignmentAsStudent(116); //submit assignment

            new LoginUsingLTI().ltiLogin("116_3");	//login as a student3
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().statusBoxCheckInStudentAssignmentPage(116,"1","Not Started");
            new Assignment().verifyClassAssignmentStatus(116, "Not Started");
            new Assignment().submitAssignmentAsStudent(116); //submit assignment

            new LoginUsingLTI().ltiLogin("116");//login as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            new Assignment().verifyClassAssignmentStatus(116, "Needs Grading");

            currentAssignments.getViewGrade_link().click(); //click on the view response student link
            assignmentResponsesPage.getReleaseGradeForAll().click(); //click on the release grade button

            notStarted = new Assignment().statusBoxCount(116, "Not Started");
            Assert.assertEquals(notStarted,0,"Student status \"Not Started\" count is not 0");

            inProgress = new Assignment().statusBoxCount(116, "In Progress");
            Assert.assertEquals(inProgress,0,"Student status \"In Progress\" count is not 0");

            submitted = new Assignment().statusBoxCount(116, "Submitted");
            Assert.assertEquals(submitted,0,"Student status \"Submitted\" count is not 0");

            graded = new Assignment().statusBoxCount(116, "Graded");
            Assert.assertEquals(graded,3,"Student status \"Graded\" count is not 3");

            new LoginUsingLTI().ltiLogin("116_3");	//login as a student3
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            String score=assignments.getScore().getText();
            Assert.assertEquals(score,"Score (6/6)","score of student is not 10/100 according to given ranges ");
            Assert.assertEquals(assignments.completed_count.get(0).getText().trim(), "1 Graded");
            Assert.assertEquals(assignments.completed_count.get(1).getText().trim(), "0 Reviewed");

            new LoginUsingLTI().ltiLogin("116_2");	//login as a student3
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            String score1=assignments.getScore().getText();
            Assert.assertEquals(score1,"Score (6/6)","score of student is not 10/100 according to given ranges ");
            Assert.assertEquals(assignments.completed_count.get(0).getText().trim(), "1 Graded");
            Assert.assertEquals(assignments.completed_count.get(1).getText().trim(), "0 Reviewed");

            new LoginUsingLTI().ltiLogin("118");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            new Assignment().verifyClassAssignmentStatus(118, "Graded");


        } catch (Exception e) {

            Assert.fail("Exception in TC gradableAssignmentWithPolicyFour of class PolicyWithAssignmentFlow ", e);
        }

    }


    @Test(priority = 3,enabled = true)
    public void gradableAssignmentWithPolicyTwo(){
        WebDriver driver=Driver.getWebDriver();
        try {

            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", "118");
            String assignmentName = ReadTestData.readDataByTagName("", "assessmentname", "118");
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);

            int notStarted;
            int inProgress;
            int submitted;
            int graded;


            new LoginUsingLTI().ltiLogin("118_1");	//login as a student1
            new LoginUsingLTI().ltiLogin("118_2");	//login as a student2
            new LoginUsingLTI().ltiLogin("118_3");	//login as a student3

            new LoginUsingLTI().ltiLogin("118");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description118", "2", null, false, "1", "", "Auto-release on due date", "", "", "");//policy 2


            new LoginUsingLTI().ltiLogin("118");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(118);//assign to student
            StopWatch stopWatch=new StopWatch();
            stopWatch.start();

            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            Assert.assertEquals(currentAssignments.getAssessmentName().getText().trim(),assignmentName);
            new Assignment().verifyClassAssignmentStatus(118,"Available for Students");

            notStarted = new Assignment().statusBoxCount(118, "Not Started");
            Assert.assertEquals(notStarted,3,"Student status \"Not Started\" count is not 3");

            inProgress = new Assignment().statusBoxCount(118, "In Progress");
            Assert.assertEquals(inProgress,0,"Student status \"In Progress\" count is not 0");

            submitted = new Assignment().statusBoxCount(118, "Submitted");
            Assert.assertEquals(submitted,0,"Student status \"Submitted\" count is not 0");

            graded = new Assignment().statusBoxCount(118, "Graded");
            Assert.assertEquals(graded,0,"Student status \"Graded\" count is not 0");


            new LoginUsingLTI().ltiLogin("118_1");	//login as a student1
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().statusBoxCheckInStudentAssignmentPage(118,"1","Not Started");
            new Assignment().verifyClassAssignmentStatus(118, "Not Started");
            new Assignment().submitAssignmentAsStudent(118); //submit assignment

            new LoginUsingLTI().ltiLogin("118");//login as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click(); //click on the view response student link
            new Assignment().verifyClassAssignmentStatus(118, "Available for Students");


            notStarted = new Assignment().statusBoxCount(118, "Not Started");
            Assert.assertEquals(notStarted,2,"Student status \"Not Started\" count is not 2");

            inProgress = new Assignment().statusBoxCount(118, "In Progress");
            Assert.assertEquals(inProgress,0,"Student status \"In Progress\" count is not 0");

            submitted = new Assignment().statusBoxCount(118, "Submitted");
            Assert.assertEquals(submitted,0,"Student status \"Submitted\" count is not 0");

            graded = new Assignment().statusBoxCount(118, "Graded");
            Assert.assertEquals(graded,1,"Student status \"Graded\" count is not 1");

            new LoginUsingLTI().ltiLogin("118_2");	//login as a student2
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            new Assignment().statusBoxCheckInStudentAssignmentPage(118,"1","Not Started");
            new Assignment().verifyClassAssignmentStatus(118, "Not Started");

            currentAssignments.getAssessmentName().click();//click on assignment
            new AttemptQuestion().trueFalse(false, "correct", 355);
            assignments.getNextQuestion().click();

            new Navigator().NavigateTo("Assignments");//navigate to assignment
            new Assignment().statusBoxCheckInStudentAssignmentPage(118,"1","In Progress");
            new Assignment().verifyClassAssignmentStatus(118, "In Progress");

            new LoginUsingLTI().ltiLogin("118");//login as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click(); //click on the view response student link

            notStarted = new Assignment().statusBoxCount(118, "Not Started");
            Assert.assertEquals(notStarted,1,"Student status \"Not Started\" count is not 0");

            inProgress = new Assignment().statusBoxCount(118, "In Progress");
            Assert.assertEquals(inProgress,1,"Student status \"In Progress\" count is not 0");

            submitted = new Assignment().statusBoxCount(118, "Submitted");
            Assert.assertEquals(submitted,0,"Student status \"Submitted\" count is not 0");

            graded = new Assignment().statusBoxCount(118, "Graded");
            Assert.assertEquals(graded,1,"Student status \"Graded\" count is not 1");

            stopWatch.stop();
            System.out.println("spending time:"+stopWatch);

            new LoginUsingLTI().ltiLogin("118");//login as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            new Assignment().verifyClassAssignmentStatus(118, "Available for Students");

            Thread.sleep(240000); //wait for 4 minute till due date is expired


            notStarted = new Assignment().statusBoxCount(118, "Not Started");
            Assert.assertEquals(notStarted,0,"Student status \"Not Started\" count is not 0");

            inProgress = new Assignment().statusBoxCount(118, "In Progress");
            Assert.assertEquals(inProgress,0,"Student status \"In Progress\" count is not 0");

            submitted = new Assignment().statusBoxCount(118, "Submitted");
            Assert.assertEquals(submitted,0,"Student status \"Submitted\" count is not 0");

            graded = new Assignment().statusBoxCount(118, "Graded");
            Assert.assertEquals(graded,3,"Student status \"Graded\" count is not 3");

            new LoginUsingLTI().ltiLogin("118_3");	//login as a student3
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            String score=assignments.getScore().getText();
            Assert.assertEquals(score,"Score (0/6)","score of student is not 10/100 according to given ranges ");

            Assert.assertEquals(assignments.completed_count.get(0).getText().trim(), "1 Graded");
            Assert.assertEquals(assignments.completed_count.get(1).getText().trim(), "0 Reviewed");


            new LoginUsingLTI().ltiLogin("118_2");	//login as a student3
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            String score1=assignments.getScore().getText();
            Assert.assertEquals(score1,"Score (2/6)","score of student is not 10/100 according to given ranges ");

            Assert.assertEquals(assignments.completed_count.get(0).getText().trim(), "1 Graded");
            Assert.assertEquals(assignments.completed_count.get(1).getText().trim(), "0 Reviewed");

            new LoginUsingLTI().ltiLogin("118_1");	//login as a student3
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            String score2=assignments.getScore().getText();
            Assert.assertEquals(score2,"Score (6/6)","score of student is not 10/100 according to given ranges ");

            Assert.assertEquals(assignments.completed_count.get(0).getText().trim(), "1 Graded");
            Assert.assertEquals(assignments.completed_count.get(1).getText().trim(), "0 Reviewed");

            new LoginUsingLTI().ltiLogin("118");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            new Assignment().verifyClassAssignmentStatus(118, "Graded");


        } catch (Exception e) {
            Assert.fail("Exception in TC gradableAssignmentWithPolicyTwo of class PolicyWithAssignmentFlow ", e);
        }

    }

    @Test(priority = 4,enabled = true)
    public void gradableAssignmentWithPolicyThree(){
        try {
            WebDriver driver=Driver.getWebDriver();
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", "119");
            String assignmentName = ReadTestData.readDataByTagName("", "assessmentname", "119");
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);

            int notStarted;
            int inProgress;
            int submitted;
            int graded;
            new Assignment().create(119);
            new Assignment().addQuestions(119, "essay", "");
            new Assignment().addQuestions(119, "writeboard", "");

            new LoginUsingLTI().ltiLogin("119_1");	//login as a student1
            new LoginUsingLTI().ltiLogin("119_2");	//login as a student2
            new LoginUsingLTI().ltiLogin("119_3");	//login as a student3

            new LoginUsingLTI().ltiLogin("119");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");//policy 3

            new LoginUsingLTI().ltiLogin("119");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(119);//assign to student

            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            Assert.assertEquals(currentAssignments.getAssessmentName().getText().trim(), assignmentName);
            new Assignment().verifyClassAssignmentStatus(119, "Available for Students");

            notStarted = new Assignment().statusBoxCount(119, "Not Started");
            Assert.assertEquals(notStarted, 3, "Student status \"Not Started\" count is not 3");

            inProgress = new Assignment().statusBoxCount(119, "In Progress");
            Assert.assertEquals(inProgress,0,"Student status \"In Progress\" count is not 0");

            submitted = new Assignment().statusBoxCount(119, "Submitted");
            Assert.assertEquals(submitted,0,"Student status \"Submitted\" count is not 0");

            graded = new Assignment().statusBoxCount(119, "Graded");
            Assert.assertEquals(graded,0,"Student status \"Graded\" count is not 0");


            new LoginUsingLTI().ltiLogin("119_1");	//login as a student1
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().statusBoxCheckInStudentAssignmentPage(119, "1", "Not Started");
            new Assignment().verifyClassAssignmentStatus(119, "Not Started");
            new Assignment().submitAssignmentAsStudent(119); //submit assignment

            new LoginUsingLTI().ltiLogin("119");//login as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click(); //click on the view response student link
            new Assignment().verifyClassAssignmentStatus(119, "Available for Students");

            notStarted = new Assignment().statusBoxCount(119, "Not Started");
            Assert.assertEquals(notStarted,2,"Student status \"Not Started\" count is not 2");

            inProgress = new Assignment().statusBoxCount(119, "In Progress");
            Assert.assertEquals(inProgress,0,"Student status \"In Progress\" count is not 0");

            submitted = new Assignment().statusBoxCount(119, "Submitted");
            Assert.assertEquals(submitted,1,"Student status \"Submitted\" count is not 1");

            graded = new Assignment().statusBoxCount(119, "Graded");
            Assert.assertEquals(graded,0,"Student status \"Graded\" count is not 0");

            new LoginUsingLTI().ltiLogin("119_2");	//login as a student2
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            new Assignment().statusBoxCheckInStudentAssignmentPage(119, "1", "Not Started");
            new Assignment().verifyClassAssignmentStatus(119, "Not Started");

            currentAssignments.getAssessmentName().click();//click on assignment
            new AttemptQuestion().trueFalse(false, "correct", 355);
            assignments.getNextQuestion().click();

            new Navigator().NavigateTo("Assignments");//navigate to assignment
            new Assignment().statusBoxCheckInStudentAssignmentPage(119, "1", "In Progress");
            new Assignment().verifyClassAssignmentStatus(119, "In Progress");

            new LoginUsingLTI().ltiLogin("119");//login as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click(); //click on the view response student link

            notStarted = new Assignment().statusBoxCount(119, "Not Started");
            Assert.assertEquals(notStarted,1,"Student status \"Not Started\" count is not 1");

            inProgress = new Assignment().statusBoxCount(119, "In Progress");
            Assert.assertEquals(inProgress,1,"Student status \"In Progress\" count is not 1");

            submitted = new Assignment().statusBoxCount(119, "Submitted");
            Assert.assertEquals(submitted,1,"Student status \"Submitted\" count is not 1");

            graded = new Assignment().statusBoxCount(119, "Graded");
            Assert.assertEquals(graded,0,"Student status \"Graded\" count is not 0");


            new LoginUsingLTI().ltiLogin("119_2");	//login as a student2
            new Assignment().submitAssignmentAsStudent(119); //submit assignment


            new LoginUsingLTI().ltiLogin("119_3");	//login as a student3
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().statusBoxCheckInStudentAssignmentPage(119, "1", "Not Started");
            new Assignment().verifyClassAssignmentStatus(119, "Not Started");
            new Assignment().submitAssignmentAsStudent(119); //submit assignment

            new LoginUsingLTI().ltiLogin("119");//login as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            new Assignment().verifyClassAssignmentStatus(119, "Needs Grading");

            currentAssignments.getViewGrade_link().click(); //click on the view response student link

            notStarted = new Assignment().statusBoxCount(119, "Not Started");
            Assert.assertEquals(notStarted,0,"Student status \"Not Started\" count is not 0");

            inProgress = new Assignment().statusBoxCount(119, "In Progress");
            Assert.assertEquals(inProgress,0,"Student status \"In Progress\" count is not 0");

            submitted = new Assignment().statusBoxCount(119, "Submitted");
            Assert.assertEquals(submitted, 3, "Student status \"Submitted\" count is not 3");

            graded = new Assignment().statusBoxCount(119, "Graded");
            Assert.assertEquals(graded,0,"Student status \"Graded\" count is not 0");


            new LoginUsingLTI().ltiLogin("119");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            new Assignment().verifyClassAssignmentStatus(119, "Needs Grading");
            new Assignment().provideGradeToStudentForMultipleQuestions(119);

            notStarted = new Assignment().statusBoxCount(119, "Not Started");
            Assert.assertEquals(notStarted,0,"Student status \"Not Started\" count is not 0");

            inProgress = new Assignment().statusBoxCount(119, "In Progress");
            Assert.assertEquals(inProgress,0,"Student status \"In Progress\" count is not 0");

            submitted = new Assignment().statusBoxCount(119, "Submitted");
            Assert.assertEquals(submitted, 2, "Student status \"Submitted\" count is not 3");

            graded = new Assignment().statusBoxCount(119, "Graded");
            Assert.assertEquals(graded,1,"Student status \"Graded\" count is not 0");

            new LoginUsingLTI().ltiLogin("119_1");	//login as a student1
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            String score1=assignments.getScore().getText();
            Assert.assertEquals(score1, "Score (2.1/6)", "score of student is not 10/100 according to given ranges ");

            Assert.assertEquals(assignments.completed_count.get(0).getText().trim(), "1 Graded");
            Assert.assertEquals(assignments.completed_count.get(1).getText().trim(), "0 Reviewed");


            new LoginUsingLTI().ltiLogin("119");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            new Assignment().verifyClassAssignmentStatus(119, "Needs Grading");
            currentAssignments.getViewGrade_link().click(); //click on the view response student link

            new Assignment().enterGradeOnParticularQuestion(0,1,"0.8");
            new Assignment().enterGradeOnParticularQuestion(0,2,"0.7");

            new Assignment().enterGradeOnParticularQuestion(2,1,"0.8");
            new Assignment().enterGradeOnParticularQuestion(2,2,"0.7");

            new Assignment().verifyClassAssignmentStatus(119, "Graded");
            new Assignment().provideGradeToStudentForMultipleQuestions(119);

            notStarted = new Assignment().statusBoxCount(119, "Not Started");
            Assert.assertEquals(notStarted,0,"Student status \"Not Started\" count is not 0");

            inProgress = new Assignment().statusBoxCount(119, "In Progress");
            Assert.assertEquals(inProgress,0,"Student status \"In Progress\" count is not 0");

            submitted = new Assignment().statusBoxCount(119, "Submitted");
            Assert.assertEquals(submitted, 0, "Student status \"Submitted\" count is not 0");

            graded = new Assignment().statusBoxCount(119, "Graded");
            Assert.assertEquals(graded,3,"Student status \"Graded\" count is not 3");


            new LoginUsingLTI().ltiLogin("119_3");	//login as a student3
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            String score=assignments.getScore().getText();
            Assert.assertEquals(score, "Score (3.5/6)", "score of student is not 10/100 according to given ranges ");

            Assert.assertEquals(assignments.completed_count.get(0).getText().trim(), "1 Graded");
            Assert.assertEquals(assignments.completed_count.get(1).getText().trim(), "0 Reviewed");


            new LoginUsingLTI().ltiLogin("119_2");	//login as a student3
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            String score3=assignments.getScore().getText();
            Assert.assertEquals(score3, "Score (3.5/6)", "score of student is not 10/100 according to given ranges ");

            Assert.assertEquals(assignments.completed_count.get(0).getText().trim(), "1 Graded");
            Assert.assertEquals(assignments.completed_count.get(1).getText().trim(), "0 Reviewed");

            new LoginUsingLTI().ltiLogin("119");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            new Assignment().verifyClassAssignmentStatus(119, "Graded");



        } catch (Exception e) {
            Assert.fail("Exception in TC gradableAssignmentWithPolicyThree of class PolicyWithAssignmentFlow ", e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void createPolicyWhileAssigningAssignment(){
        try {
            WebDriver driver=Driver.getWebDriver();
            String assignmentName = ReadTestData.readDataByTagName("", "assessmentname", "120");
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            int notStarted;
            int inProgress;
            int submitted;
            int graded;

            new LoginUsingLTI().ltiLogin("120_1");	//login as a student1

            new LoginUsingLTI().ltiLogin("120");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(120);//assign to student

            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            Assert.assertEquals(currentAssignments.getAssessmentName().getText().trim(), assignmentName);
            new Assignment().verifyClassAssignmentStatus(120, "Available for Students");

            notStarted = new Assignment().statusBoxCount(120, "Not Started");
            Assert.assertEquals(notStarted, 1, "Student status \"Not Started\" count is not 3");

            inProgress = new Assignment().statusBoxCount(120, "In Progress");
            Assert.assertEquals(inProgress,0,"Student status \"In Progress\" count is not 0");

            submitted = new Assignment().statusBoxCount(120, "Submitted");
            Assert.assertEquals(submitted,0,"Student status \"Submitted\" count is not 0");

            graded = new Assignment().statusBoxCount(120, "Graded");
            Assert.assertEquals(graded,0,"Student status \"Graded\" count is not 0");


            new LoginUsingLTI().ltiLogin("120_1");	//login as a student1
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().statusBoxCheckInStudentAssignmentPage(120, "1", "Not Started");
            new Assignment().verifyClassAssignmentStatus(120, "Not Started");
            new Assignment().submitAssignmentAsStudent(120); //submit assignment

            new LoginUsingLTI().ltiLogin("120");//login as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click(); //click on the view response student link
            new Assignment().verifyClassAssignmentStatus(120, "Needs Grading");

            notStarted = new Assignment().statusBoxCount(120, "Not Started");
            Assert.assertEquals(notStarted,0,"Student status \"Not Started\" count is not 0");

            inProgress = new Assignment().statusBoxCount(120, "In Progress");
            Assert.assertEquals(inProgress,0,"Student status \"In Progress\" count is not 0");

            submitted = new Assignment().statusBoxCount(120, "Submitted");
            Assert.assertEquals(submitted,0,"Student status \"Submitted\" count is not 0");

            graded = new Assignment().statusBoxCount(120, "Graded");
            Assert.assertEquals(graded,1,"Student status \"Graded\" count is not 1");



        } catch (Exception e) {
            Assert.fail("Exception in TC createPolicyWhileAssigningAssignment of class PolicyWithAssignmentFlow ", e);
        }
    }

}
