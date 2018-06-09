package com.snapwiz.selenium.tests.staf.edulastic.testcases.smoke.assignmentflow;

import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssignmentSummary;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.commonassessments.CommonAssessments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import org.testng.annotations.Test;
public class AssignmentFlow extends Driver
{

    @Test(priority = 1 , enabled = true)
    public void gradableAssignmentFlowByInstructor(){
        WebDriver driver=Driver.getWebDriver();
        try{
            ReportUtil.log("Description","This test case validates the creation of gradable assessment and assign to class,submitted by students and report verification","info");

            AssignmentSummary assignmentSummary = PageFactory.initElements(driver,AssignmentSummary.class);
            InstructorDashboard instructorDashboard = PageFactory.initElements(driver,InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);

            int dataIndex = 14;

            String appendChar = new RandomString().randominteger(3);
            System.out.println("appendchar "+appendChar);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("08822",dataIndex,false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            //Create an assessment with 10 different types of questions
            new Assignment().create(dataIndex, "passage");
            new Assignment().addQuestion(dataIndex, "multipart");
            new Assignment().addQuestion(dataIndex,"graphplotter");
            new Assignment().addQuestion(dataIndex, "multipleselection");
            new Assignment().addQuestion(dataIndex,"expressionevaluator");
            new Assignment().addQuestion(dataIndex,"labelanimagedropdown");
            new Assignment().addQuestion(dataIndex,"graphing");
            new Assignment().addQuestion(dataIndex,"classification");
            new Assignment().addQuestion(dataIndex,"clozematrix");
            new Assignment().addQuestion(dataIndex,"sentenceresponse");

            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Assignment().submitAssignmentWithMixResponse(dataIndex,8,15);

            //Verify the percentage of student
            CustomAssert.assertEquals(assignmentSummary.percentage.get(0).getText(),"56","Verify the percentage of student at student side after submitting te assignment","Percentage is displayed as expected","Percentage is not displayed as expected");
            //click on continue
            assignmentSummary.button_continue.click();//Click on continue button
            Thread.sleep(3000);

            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            Thread.sleep(4000);

            //Verify performance score at instructor side
            CustomAssert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(), "Performance: 56 %", "Verify performance score at instructor side", "Performance percentage is displayed as expected", "Performance percentage is not displayed as expected");

            //Verify score and percentage on student card
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(0).getText(),"9/16","Verify score of student at instructor side","Score is displayed as expected","Score is not displayed as expected");
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(1).getText(),"56.25%","Verify percentage of student at instructor side","Percentage is displayed as expected","Percentage is not displayed as expected");


        }catch (Exception e ){
            Assert.fail("Exception in testcase 'gradableAssignmentFlowByInstructor' in class 'AssignmentFlow'", e);
        }
    }

    @Test(priority =2, enabled = true)
    public void gradableAssignmentFlowByDAAdmin()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Description","This test case consist of use of existing assessment by DA and shared at district level,same assessment used by instructor in the same district and assigned to class,attempted by student,released grade by DA,view performance at instructor and student side","info");

            String appendChar = new RandomString().randominteger(3);
            System.out.println("appendchar "+appendChar);

            int dataIndex = 15;
            String email = "G1FRUSD@edulastic.com";

            AssignmentSummary assignmentSummary = PageFactory.initElements(driver,AssignmentSummary.class);
            InstructorDashboard instructorDashboard = PageFactory.initElements(driver, InstructorDashboard.class);
            CommonAssessments commonAssessments= PageFactory.initElements(driver, CommonAssessments.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            //Use the assessment with 10 different types of questions created in above test cases
            new Assignment().useExistingAssignment(16,appendChar);
            new Navigator().logout();//log out

            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("08822",dataIndex,false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Assignment().useExistingAssignment(dataIndex,appendChar);//Use existing assignment and assign to class
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Assignment().submitAssignmentWithMixResponse(dataIndex,10,15);//Submit assignment
            assignmentSummary.button_continue.click();//Click on continue button
            Thread.sleep(3000);
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as an instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            Thread.sleep(1000);
            Assert.assertEquals(driver.findElements(By.xpath("//button[contains(@class,'btn gradebook-left-btn release-feed')]")).size(),0,"Release grade should be disable");
            new Navigator().logout();

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            commonAssessments.viewResponse.click();
            Thread.sleep(1000);
            commonAssessments.checkboxInStudentCard.get(0).click();
            Thread.sleep(2000);
            commonAssessments.releaseGrade.click();//Click on release grade
            new Navigator().logout();

            new Login().loginAsStudent(appendChar,dataIndex);
            new Navigator().navigateTo("assignment");
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'view-user-question-performance-score views-number')]")));
            CustomAssert.assertEquals(assignments.studentScore.getText(),"11.00","Verify student score at student side","Student score matches","Student score not matches");
            CustomAssert.assertEquals(assignments.totalScore.getText(),"16","Verify total score at student side","Total score matches","Total score not matches");
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar, dataIndex);
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            Thread.sleep(4000);

            //Verify performance score at instructor side
            CustomAssert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(),"Performance: 69 %","Verify performance score at instructor side","Performance percentage is displayed as expected","Performance percentage is not displayed as expected");
            //Verify score and percentage on student card
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(0).getText(),"11/16","Verify score of student at instructor side","Score is displayed as expected","Score is not displayed as expected");
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(1).getText(),"68.75%","Verify percentage of student at instructor side","Percentage is displayed as expected","Percentage is not displayed as expected");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in 'AssignmentFlow' in 'gradableAssignmentFlowByDAAdmin' method",e);
        }

    }

}