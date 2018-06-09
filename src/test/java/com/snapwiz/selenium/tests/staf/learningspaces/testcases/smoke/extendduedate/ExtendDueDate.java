package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.extendduedate;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by priyanka on 02-03-2016.
 */
public class ExtendDueDate extends Driver {

    CurrentAssignments currentAssignments;
    AssignmentResponsesPage assignmentResponsesPage;
    LessonPage lessonPage;
    MyQuestionBank myQuestionBank;

    @BeforeMethod
    public void inItElement() {
        WebDriver driver=Driver.getWebDriver();
         currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
         assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
         lessonPage= PageFactory.initElements(driver, LessonPage.class);
         myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);

    }


    @Test(priority = 1, enabled = true)
    public  void  instructorExtendDueDateForStudentOneWithPolicyFour(){
        try {

            ReportUtil.log("Description","Test case validates creation of assignment,instructor assign assignment to class section with policy4,instructor extends due date for student1,assignment submitted by students and status verification","info");
            WebDriver driver=Driver.getWebDriver();
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "1");

            new Assignment().create(1);
            ReportUtil.log("Create assignment","Assignment created successfully with true false question","pass");

            new LoginUsingLTI().ltiLogin("1_1");//log in as student1
            ReportUtil.log("Student1 login","Student1 logged in successfully","pass");

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            ReportUtil.log("Instructor login","Instructor logged in successfully","pass");

            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description21", "2", null, false, "1", "", "Release explicitly by the instructor", "", "", "","1");//policy 4
            ReportUtil.log("Create policy four","Instructor created policy successfully","pass");

            new Assignment().assignAssignmentWithDueDate(1);
            ReportUtil.log("Assign assignment","Instructor assigned assignment to class section with due date","pass");

            Thread.sleep(12000);
            new Assignment().extendedDueDate("1_1");//extend the due date for student
            ReportUtil.log("Extend the due date for student1","Instructor Extended the due date for student1","pass");
            Thread.sleep(2000);
            new Navigator().NavigateTo("Class Assignments");//click on my question bank
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(),true,"Due date has been extended to <month> <date>, <Year> format message is not appearing in that particular assignment's due date section.");

            new LoginUsingLTI().ltiLogin("1_2");//log in as student2
            ReportUtil.log("Student2 login","Student logged in successfully","pass");

            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(1);//submit assignment
            ReportUtil.log("Submit assignment","Student2 submitted the assignment successfully","pass");

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            ReportUtil.log("Instructor login","Instructor logged in successfully","pass");
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            boolean elementFound = false;
            try{
                driver.findElement(By.cssSelector("div[title='Release Grade for All']"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"Release Grade for All button is enabled");

            new LoginUsingLTI().ltiLogin("1_1");//log in as student1
            ReportUtil.log("Student1 login","Student1 logged in successfully","pass");
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has not been extended");// verify whether due date has been extended
            new Assignment().submitAssignmentAsStudent(1);//submit assignment
            ReportUtil.log("Submit assignment","Student1 submitted the assignment successfully","pass");

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            ReportUtil.log("Instructor login","Instructor logged in successfully","pass");
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            Assert.assertEquals(assignmentResponsesPage.getReleaseGradeForAll().isEnabled(), true, "Release grades for all button is not enabled");// verify Release grades for all button

        }
        catch (Exception e) {
            Assert.fail("Exception in TC instructorExtendDueDateForStudentOneWithPolicyFour of class AutoSubmitReleaseGradeTriggerEntireClassSection", e);

        }
    }

    @Test(priority = 2, enabled = true)
    public  void  instructorExtendDueDateForStudentOneWithPolicyThree(){
        try {
            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("Description","Test case validates creation of manual graded assessment,instructor assign assignment to class section with policy3,instructor extends due date for student1,assignment submitted by students and status verification","info");

            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "2");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(2));

            new Assignment().create(2);
            new Assignment().addQuestions(2,"essay","");
            new Assignment().addQuestions(2,"writeboard","");
            ReportUtil.log("Create assignment","Assignment created successfully with manual graded questions","pass");

            new LoginUsingLTI().ltiLogin("2_1");//log in as student1
            ReportUtil.log("Student1 login","Student1 logged in successfully","pass");

            new LoginUsingLTI().ltiLogin("2");//log in as instructor
            ReportUtil.log("Instructor login","Instructor logged in successfully","pass");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "","1");//till save policy
            ReportUtil.log("Create policy three","Instructor created policy successfully","pass");

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(3);
            new TopicOpen().clickOnStaticAssessmentArrow(assessmentname);
            lessonPage.assignThis_link.click(); //click on the assign this link
            new Assignment().assignAssignmentWithDueDate(2);
            ReportUtil.log("Assign assignment","Instructor assigned assignment to class section with due date","pass");
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("2");//log in as instructor
            ReportUtil.log("Instructor login","Instructor logged in successfully","pass");

            new Assignment().extendedDueDate("2_1");//extend the due date for whole class
            Thread.sleep(2000);
            ReportUtil.log("Extend the due date for student1","Instructor Extended the due date for student1","pass");
            new Navigator().NavigateTo("Class Assignments");//click on my question bank
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(),true,"Due date has been extended to <month> <date>, <Year> format message is not appearing in that particular assignment's due date section.");

            new LoginUsingLTI().ltiLogin("2_2");//log in as student2
            ReportUtil.log("Student2 login","Student2 logged in successfully","pass");

            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(2);//submit assignment
            ReportUtil.log("Submit assignment","Student2 submitted the assignment successfully","pass");

            new LoginUsingLTI().ltiLogin("2");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            boolean elementFound = false;
            try{
                driver.findElement(By.cssSelector("div[title='Release Grade for All']"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,true,"Release Grade for All button is enabled");

            new LoginUsingLTI().ltiLogin("2_1");//log in as student1
            ReportUtil.log("Student1 login","Student1 logged in successfully","pass");
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has not been extended");// verify whether due date has been extended
            new Assignment().submitAssignmentAsStudent(2);//submit assignment

            new LoginUsingLTI().ltiLogin("2");//log in as instructor
            ReportUtil.log("Instructor login","Instructor logged in successfully","pass");
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            Assert.assertEquals(assignmentResponsesPage.getReleaseGradeForAll().isEnabled(), true, "Release grades for all button is not enabled");// verify Release grades for all button

        }
        catch (Exception e) {
            Assert.fail("Exception in TC instructorExtendDueDateForStudentOneWithPolicyThree of class AutoSubmitReleaseGradeTriggerEntireClassSection", e);

        }
    }

    @Test(priority = 3, enabled = true)
    public  void  instructorExtendDueDateForStudentOneWithPolicyTwo(){
        try {
            ReportUtil.log("Description","Test case validates creation of assignment,instructor assign assignment to class section with policy2,instructor extends due date for student1,assignment submitted by students and status verification","info");
            WebDriver driver=Driver.getWebDriver();
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "3");

            new Assignment().create(3);
            new LoginUsingLTI().ltiLogin("3_1");//log in as student1
            ReportUtil.log("Student1 login","Student1 logged in successfully","pass");

            new LoginUsingLTI().ltiLogin("3_3");//log in as student3
            ReportUtil.log("Student3 login","Student3 logged in successfully","pass");

            new LoginUsingLTI().ltiLogin("3");//log in as instructor
            ReportUtil.log("Instructor login","Instructor logged in successfully","pass");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on due date", "", "", "","1");//till save policy*//*
            ReportUtil.log("Create policy two","Instructor created policy successfully","pass");

            new Assignment().assignAssignmentWithDueDate(3);
            ReportUtil.log("Assign assignment","Instructor assigned assignment to class section with due date","pass");
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("3");//log in as instructor
            ReportUtil.log("Instructor login","Instructor logged in successfully","pass");
            new Assignment().extendedDueDate("3_1");//extend the due date for whole class
            Thread.sleep(2000);
            ReportUtil.log("Extend the due date for student1","Instructor Extended the due date for student1","pass");
            new Navigator().NavigateTo("Class Assignments");//click on my question bank
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(),true,"Due date has been extended to <month> <date>, <Year> format message is not appearing in that particular assignment's due date section.");

            new LoginUsingLTI().ltiLogin("3_2");//log in as student2
            ReportUtil.log("Student2 login","Student2 logged in successfully","pass");
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(3);//submit assignment
            ReportUtil.log("Submit assignment","Student2 submitted the assignment successfully","pass");

            new LoginUsingLTI().ltiLogin("3");//log in as instructor
            ReportUtil.log("Instructor login","Instructor logged in successfully","pass");
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            boolean elementFound = false;
            try{
                driver.findElement(By.cssSelector("div[title='Release Grade for All']"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,true,"Release Grade for All button is enabled");

            new LoginUsingLTI().ltiLogin("3_1");//log in as student1
            ReportUtil.log("Student1 login","Student1 logged in successfully","pass");
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has not been extended");// verify whether due date has been extended
            new Assignment().submitAssignmentAsStudent(3);//submit assignment

            new LoginUsingLTI().ltiLogin("3");//log in as instructor
            ReportUtil.log("Instructor login","Instructor logged in successfully","pass");
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            Assert.assertEquals(assignmentResponsesPage.getReleaseGradeForAll().isEnabled(), true, "Release grades for all button is not enabled");// verify Release grades for all button
            Thread.sleep(300000);

            new LoginUsingLTI().ltiLogin("3");//log in as instructor
            ReportUtil.log("Instructor login","Instructor logged in successfully","pass");
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            new Assignment().verifyClassAssignmentStatus(3,"Graded");
        }
        catch (Exception e) {
            Assert.fail("Exception in TC instructorExtendDueDateForStudentOneWithPolicyTwo of class AutoSubmitReleaseGradeTriggerEntireClassSection", e);

        }
    }


    @Test(priority = 4, enabled = true)
    public  void  instructorExtendDueDateForStudentOneWithPolicyOne(){
        try {
            ReportUtil.log("Description","Test case validates creation of custom assignment,instructor assign assignment to class section with policy1,instructor extends due date for student1,assignment submitted by students and status verification","info");
            WebDriver driver=Driver.getWebDriver();
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "4");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "4");
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "4");

            new Assignment().create(4);
            ReportUtil.log("Create assignment","Assignment created successfully with true false question","pass");

            new LoginUsingLTI().ltiLogin("4_1");//log in as student1
            ReportUtil.log("Student1 login","Student1 logged in successfully","pass");

            new LoginUsingLTI().ltiLogin("4_2");//log in as student2
            ReportUtil.log("Student2 login","Student2 logged in successfully","pass");

            new LoginUsingLTI().ltiLogin("4");//log in as instructor
            ReportUtil.log("Instructor login","Instructor logged in successfully","pass");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "","1");//till save policy*//*
            ReportUtil.log("Create policy one","Instructor created policy successfully","pass");
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("4");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(4);//assign assignment
            ReportUtil.log("Assign assignment","Instructor assigned assignment to class section with due date","pass");
            new Assignment().extendedDueDate("4_1");//extend the due date for whole class
            Thread.sleep(2000);
            ReportUtil.log("Extend the due date for student1","Instructor Extended the due date for student1","pass");
            new Navigator().NavigateTo("Class Assignments");//click on my question bank
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(),true,"Due date has been extended to <month> <date>, <Year> format message is not appearing in that particular assignment's due date section.");

            new LoginUsingLTI().ltiLogin("4_2");//log in as student2
            ReportUtil.log("Student2 login","Student2 logged in successfully","pass");
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(4);//submit assignment
            ReportUtil.log("Submit assignment","Student2 submitted the assignment successfully","pass");

            new LoginUsingLTI().ltiLogin("4");//log in as instructor
            ReportUtil.log("Instructor login","Instructor logged in successfully","pass");
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            boolean elementFound = false;
            try{
                driver.findElement(By.cssSelector("div[title='Release Grade for All']"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,true,"Release Grade for All button is enabled");


            new LoginUsingLTI().ltiLogin("4_1");//log in as student1
            ReportUtil.log("Student1 login","Student1 logged in successfully","pass");
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has not been extended");// verify whether due date has been extended
            new Assignment().submitAssignmentAsStudent(4);//submit assignment

            new LoginUsingLTI().ltiLogin("4");//log in as instructor
            ReportUtil.log("Instructor login","Instructor logged in successfully","pass");

            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            boolean elementFound1 = false;
            try{
                driver.findElement(By.cssSelector("div[title='Release Grade for All']"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,true,"Release Grade for All button is enabled");


        }
        catch (Exception e) {
            Assert.fail("Exception in TC instructorExtendDueDateForStudentOneWithPolicyOne of class AutoSubmitReleaseGradeTriggerEntireClassSection", e);

        }
    }


}



