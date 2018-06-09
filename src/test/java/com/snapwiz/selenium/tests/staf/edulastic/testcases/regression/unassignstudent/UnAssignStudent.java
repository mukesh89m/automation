package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.unassignstudent;

import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.districtassessmentresponse.DistrictAssessmentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.KeysSend;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Priyanka on 09-11-2016.
 */
public class UnAssignStudent extends Driver {
    Assessments assessments;
    MyAssessments myAssessments;
    AssessmentDetailsPage assessmentDetailsPage;
    AssignmentReview assignmentReview;
    Assignments assignments;
    StudentResponse studentResponse;


    @BeforeMethod
    public void init(){
        WebDriver driver= Driver.getWebDriver();
        assessments = PageFactory.initElements(driver, Assessments.class);
        myAssessments = PageFactory.initElements(driver,MyAssessments.class);;
        assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);;
        assignmentReview = PageFactory.initElements(driver,AssignmentReview.class);
        assignments = PageFactory.initElements(driver,Assignments.class);
        studentResponse = PageFactory.initElements(driver,StudentResponse.class);

    }


    @Test(priority = 1,enabled = true)
    public void unAssignGradedStudent(){
        try{
            WebDriver driver = getWebDriver();
            int dataIndex = 3;

            ReportUtil.log("Description", "This test case will unassign student and verify notification message \"You will NOT be able to unassign selected student(s) as the assignment is already graded\" ", "info");

            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            DistrictAssessmentResponse districtAssessmentResponse = PageFactory.initElements(driver, DistrictAssessmentResponse.class);

            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar3 = "c" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            /*String appendChar1="aoCo";
            String appendChar2="bkdX";
            String appendChar3="cSaG";*/

            System.out.println("appendChar1:"+appendChar1);
            System.out.println("appendChar2:"+appendChar2);
            System.out.println("appendChar3:"+appendChar3);

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            System.out.println(methodName);

            //Teacher signup
            new SignUp().teacher(appendChar1,dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("987654963",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar1,dataIndex);//Create class
            System.out.println("classCode :" + classCode);
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar3,classCode,dataIndex);//Sign up as a student3
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);
            new Assignment().create(dataIndex, "truefalse");
            new Assignment().assignToStudent(dataIndex,appendChar1);//Assign to class
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1,dataIndex);//student 1 submitted
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().navigateTo("assignment");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar2,dataIndex);//student 2 submitted
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().navigateTo("assignment");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar3,dataIndex);//student 3 submitted
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().navigateTo("assignment");
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("scroll(250, 0)"); //x value '250' can be altered
            WebDriverUtil.clickOnElementUsingJavascript(districtAssessmentResponse.releaseGrade);//Click on release grade button

            studentResponse.studentCard_checkbox.get(0).click();//click on student responses checkbox
            Thread.sleep(4000);

            studentResponse.unAssignStudents.click();//click on un assign student button
            System.out.println(studentResponse.notificationMsg_redirect.getText().trim());
            CustomAssert.assertEquals(studentResponse.notificationMsg_redirect.getText().trim(), "You will NOT be able to unassign selected student(s) as the assignment is already graded", "Verify the notification message", "Notification is displayed as expected", "Notification is not displayed as expected");

            studentResponse.studentCard_checkbox.get(1).click();//click on student responses checkbox
            studentResponse.unAssignStudents.click();//click on un assign student button
            System.out.println(studentResponse.notificationMsg_redirect.getText().trim());
            CustomAssert.assertEquals(studentResponse.notificationMsg_redirect.getText().trim(), "You will NOT be able to unassign selected student(s) as the assignment is already graded", "Verify the notification message", "Notification is displayed as expected", "Notification is not displayed as expected");

            studentResponse.studentCard_checkbox.get(2).click();//click on student responses checkbox
            studentResponse.unAssignStudents.click();//click on un assign student button
            System.out.println(studentResponse.notificationMsg_redirect.getText().trim());
            CustomAssert.assertEquals(studentResponse.notificationMsg_redirect.getText().trim(), "You will NOT be able to unassign selected student(s) as the assignment is already graded", "Verify the notification message", "Notification is displayed as expected", "Notification is not displayed as expected");


        }catch(Exception e){
            Assert.fail("Exception in TC unAssignGradedStudent of class StudentInProgress", e);
        }
    }


    @Test(priority = 2,enabled = true)
    public void removeStudentFromAssignmentResponsePage(){
        try{
            WebDriver driver = getWebDriver();
            int dataIndex = 4;
            int dataIndex1 = 5;


            ReportUtil.log("Description", "This test case will add removed student back to the assignment and verify status \"NOT STARTED\"", "info");

            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            DistrictAssessmentResponse districtAssessmentResponse = PageFactory.initElements(driver, DistrictAssessmentResponse.class);
            Assignments assignments = PageFactory.initElements(Driver.getWebDriver(), Assignments.class);

            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar3 = "c" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

//            String appendChar1="aaMI";
//            String appendChar2="byAk";
//            String appendChar3="cKYW";

            System.out.println("appendChar1:"+appendChar1);
            System.out.println("appendChar2:"+appendChar2);
            System.out.println("appendChar3:"+appendChar3);

            String methodName = new Exception().getStackTrace()[0].getMethodName();

            //Teacher signup
            new SignUp().teacher(appendChar1,dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar1,dataIndex);//Create class
            System.out.println("classCode :" + classCode);
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar3,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);
            String classCode2 = new Classes().createNewClass(appendChar2,dataIndex1,"Grade 3","Mathematics","Math - Common Core");//Create a grade3 class
            System.out.println("classcode2:" +classCode2);
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1,classCode2,dataIndex1);//Sign up as a student2
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);
            new Assignment().create(5,"truefalse");//Create an assignment2
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Navigator().logout();//log out


            new Login().loginAsInstructor(appendChar1,dataIndex);
            new Assignment().assignToStudent(4,appendChar1);//Assign assignment2 to class1
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.dropdown_defaultClassName.click();//Click on class drop down
            assignments.classSection_textbox.sendKeys(methodName+"class"+appendChar2);
            new KeysSend().sendKeyBoardKeys("^");
            Thread.sleep(5000);
            new Navigator().navigateTo("myAssessments");//Navigate to my assessment page

            if(myAssessments.assignmentList.size()==0)
            {
                driver.navigate().refresh();
                Thread.sleep(8000);
                myAssessments.draft.click();//Click on Draft
                WebDriverUtil.waitForAjax(driver,60);
                if(myAssessments.assignmentList.size()==0){
                    driver.findElement(By.xpath("//input[@id='published']/..")).click(); //click on the publish
                    WebDriverUtil.waitForAjax(driver,60);
                }

            }
            driver.findElement(By.xpath("//input[@id='published']/..")).click(); //click on the publish
            WebDriverUtil.clickOnElementUsingJavascript(myAssessments.assignmentList.get(0));//click on Assignment
            new Click().clickByXpath("//i[@class='ed-icon icon-edit-assesment']/following-sibling::span");//Click on edit button
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getButton_saveForLater()); //click on the draft
            new Assignment().assignToStudent(5,appendChar2);//Assign assignment1 to class2
            new Navigator().logout();//log out


            new Login().loginAsStudent(appendChar1,dataIndex);//student 1 submitted
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().navigateTo("assignment");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar3,dataIndex);//student 1 submitted
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().navigateTo("assignment");
            new Navigator().logout();//log out*

            new Login().loginAsInstructor(appendChar1,dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to assignment page

            assignments.viewResponse.get(0).click();//Click on view response

            studentResponse.studentCard_checkbox.get(0).click();//click on student responses checkbox
            studentResponse.unAssignStudents.click();//click on un assign student button
            studentResponse.unAssign_TextBox.sendKeys("UNASSIGN");
            studentResponse.yesUnAssign.click();//click on yes


            CustomAssert.assertEquals(driver.findElement(By.className("section-count")).getText().trim(),"0","Verify assigned count","Assigned count is expected zero","Assigned count should be zero after unassign student");

            assignments.viewResponse.get(0).click();//Click on view response
            Thread.sleep(3000);
            System.out.println(studentResponse.notificationMsg_redirect.getText().trim());
            CustomAssert.assertEquals(studentResponse.notificationMsg_redirect.getText().trim(), "No response(s) from student(s) available yet", "Verify the notification message", "Notification is displayed as expected", "Notification is not displayed as expected");
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.dropdown_defaultClassName.click();//Click on class drop down
            assignments.classSection_textbox.sendKeys(methodName+"class"+appendChar1);
            new KeysSend().sendKeyBoardKeys("^");

            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            studentResponse.studentCard_checkbox.get(0).click();//click on student responses checkbox
            Thread.sleep(5000);
            studentResponse.unAssignStudents.click();//click on un assign student button
            studentResponse.unAssign_TextBox.sendKeys("UNASSIGN");
            studentResponse.yesUnAssign.click();//click on yes
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            studentResponse.studentCard_checkbox.get(0).click();//click on student responses checkbox
            studentResponse.button_redirect.click();

            driver.findElement(By.className("maininput")).sendKeys(methodName+"st"+appendChar1);
            Thread.sleep(3000);
            List<WebElement> suggestname=driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
            for (WebElement name : suggestname) {
                if (name.getText().trim().equals(methodName+"st"+appendChar1)) {
                    name.click();
                }
            }
            driver.findElement(By.id("assign-button")).click();  //click on the redirect button
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            Thread.sleep(3000);
            CustomAssert.assertEquals(driver.findElement(By.className("section-count")).getText().trim(),"2","Verify assigned count after redirect","Assigned count is expected 2","Assigned count should be 2 after rediect to  student");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.viewResponse.get(0));//Click on view response
            CustomAssert.assertEquals(assignments.notStarted_status.size(),2,"Verify NOT STARTED label count","NOT STARTED label count is expected","NOT STARTED label count is not expected");
            boolean notStarted=true;
            for (WebElement ele:assignments.notStarted_status){
                if(!ele.getText().trim().equals("NOT STARTED")){
                    notStarted=false;
                }
            }

            if(notStarted==false){
                CustomAssert.fail("Verify Status","Student is not addded with \"Not started\" status.");
            }

            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar1,dataIndex);//student 1 submitted
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().navigateTo("assignment");
            new Navigator().logout();//log out

        }catch(Exception e){
            Assert.fail("Exception in TC removeStudentFromAssignmentResponsePage of class StudentInProgress", e);
        }
    }


    @Test(priority = 3,enabled = true)
    public void daClassAssessmentFlow(){
        try{
            WebDriver driver = getWebDriver();
            int dataIndex = 6;

            ReportUtil.log("Description", "This test case will unassign student and verify notification message \"You will NOT be able to unassign selected student(s) as the assignment is already graded\" of DA Admin", "info");

            String appendChar = "a" + StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);
//                String appendChar = "aAo" ;

            System.out.println("appendChar:" + appendChar);
            String email = "ryanda@snapwiz.com";

            AssignmentSummary assignmentSummary = PageFactory.initElements(driver, AssignmentSummary.class);
            StudentResponse studentResponse = PageFactory.initElements(driver, StudentResponse.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);

            new SignUp().teacher(appendChar, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode = new Classes().createClass(appendChar, dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, dataIndex);//Sign up as a student
            new Navigator().logout();//log out

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            new Assignment().createByDA(dataIndex, "truefalse");// includes true false and essay

            new Assignment().assignByDA(dataIndex, classCode);
            new Navigator().navigateTo("dashboard");// Navigate to dashboard
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            Thread.sleep(5000);
            assignments.open.get(0).click();//Click on open
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, dataIndex);//Login as a student
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            Thread.sleep(1000);
            new Navigator().logout();


            new Login().loginAsInstructor(appendChar, dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            Thread.sleep(1000);

            studentResponse.studentCard_checkbox.get(0).click();//click on student responses checkbox
            studentResponse.unAssignStudents.click();//click on un assign student button
            studentResponse.unAssign_TextBox.sendKeys("UNASSIGN");
            studentResponse.yesUnAssign.click();//click on yes

            CustomAssert.assertEquals(driver.findElement(By.className("section-count")).getText().trim(),"0","Verify assigned count","Assigned count is expected zero","Assigned count should be zero after unassign student");

            assignments.viewResponse.get(0).click();//Click on view response
            Thread.sleep(3000);
            System.out.println(studentResponse.notificationMsg_redirect.getText().trim());
            CustomAssert.assertEquals(studentResponse.notificationMsg_redirect.getText().trim(), "No response(s) from student(s) available yet", "Verify the notification message", "Notification is displayed as expected", "Notification is not displayed as expected");
            new Navigator().logout();//log out


        }catch(Exception e){
            Assert.fail("Exception in TC daClassAssessmentFlow of class StudentInProgress", e);
        }
    }



    @Test(priority = 4,enabled = true)
    public void saClassAssessmentFlow(){
        try{
            WebDriver driver = getWebDriver();
            int dataIndex = 7;

            ReportUtil.log("Description", "This test case will unassign student and verify notification message \"You will NOT be able to unassign selected student(s) as the assignment is already graded\" of SA Admin", "info");

            String appendChar = "a" + StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);
//                String appendChar = "afG" ;

            System.out.println("appendChar:" + appendChar);
            String email = "testsa@snapwiz.com";

            AssignmentSummary assignmentSummary = PageFactory.initElements(driver, AssignmentSummary.class);
            StudentResponse studentResponse = PageFactory.initElements(driver, StudentResponse.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);

            new SignUp().teacher(appendChar, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("654321369", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode = new Classes().createClass(appendChar, dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, dataIndex);//Sign up as a student
            new Navigator().logout();//log out
            new Login().loginAsDA(dataIndex, email); //login as District Admin
            new Assignment().createByDA(dataIndex, "truefalse");// includes true false and essay

            new Assignment().assignByDA(dataIndex, classCode);
            new Navigator().navigateTo("dashboard");// Navigate to dashboard
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            Thread.sleep(5000);
            assignments.open.get(0).click();//Click on open
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, dataIndex);//Login as a student
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            Thread.sleep(1000);
            new Navigator().logout();


            new Login().loginAsInstructor(appendChar, dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            Thread.sleep(1000);

            studentResponse.studentCard_checkbox.get(0).click();//click on student responses checkbox
            studentResponse.unAssignStudents.click();//click on un assign student button
            studentResponse.unAssign_TextBox.sendKeys("UNASSIGN");
            studentResponse.yesUnAssign.click();//click on yes

            CustomAssert.assertEquals(driver.findElement(By.className("section-count")).getText().trim(),"0","Verify assigned count","Assigned count is expected zero","Assigned count should be zero after unassign student");

            assignments.viewResponse.get(0).click();//Click on view response
            Thread.sleep(3000);
            System.out.println(studentResponse.notificationMsg_redirect.getText().trim());
            CustomAssert.assertEquals(studentResponse.notificationMsg_redirect.getText().trim(), "No response(s) from student(s) available yet", "Verify the notification message", "Notification is displayed as expected", "Notification is not displayed as expected");
            new Navigator().logout();//log out


        }catch(Exception e){
            Assert.fail("Exception in TC saClassAssessmentFlow of class StudentInProgress", e);
        }
    }



}
