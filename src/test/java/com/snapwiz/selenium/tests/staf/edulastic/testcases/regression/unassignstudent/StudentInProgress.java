package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.unassignstudent;

import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.commonassessments.CommonAssessments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.districtassessmentresponse.DistrictAssessmentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Priyanka on 09-11-2016.
 */
public class StudentInProgress extends Driver {
    Assessments assessments;
    MyAssessments myAssessments;
    AssessmentDetailsPage assessmentDetailsPage;
    AssignmentReview assignmentReview;
    Assignments assignments;
    StudentResponse studentResponse;
    CommonAssessments commonAssessments;
    DistrictAssessmentResponse districtAssessmentResponse;
    String actual = null;
    String expected = null;

    @BeforeMethod
    public void init(){
        WebDriver driver= Driver.getWebDriver();
        assessments = PageFactory.initElements(driver, Assessments.class);
        myAssessments = PageFactory.initElements(driver,MyAssessments.class);;
        assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);;
        assignmentReview = PageFactory.initElements(driver,AssignmentReview.class);
        assignments = PageFactory.initElements(driver,Assignments.class);
        studentResponse = PageFactory.initElements(driver,StudentResponse.class);
        commonAssessments = PageFactory.initElements(driver, CommonAssessments.class);
        districtAssessmentResponse = PageFactory.initElements(driver, DistrictAssessmentResponse.class);

    }

    @Test(priority = 1,enabled = true)
    public void studentInProgress(){
        try{
            ReportUtil.log("Description", "This test case validates instructor can un-assign a in-progress student", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 1;

            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar3 = "c" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            System.out.println("appendChar1:"+appendChar1);
            System.out.println("appendChar2:"+appendChar2);
            System.out.println("appendChar3:"+appendChar3);

           /* String appendChar1="asbt";
            String appendChar2="byeS";
            String appendChar3="cBgR";*/

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            System.out.println(methodName);
            String student3UserName = methodName+"st"+appendChar3;

            //Teacher signup
            new SignUp().teacher(appendChar1,dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369",dataIndex,false);
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

            new Login().loginAsStudent(appendChar1,dataIndex);//student 1 in progress
            new Assignment().openAssignment(dataIndex);
            new Navigator().navigateTo("assignment");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar2,dataIndex);//student 2 submitted
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().navigateTo("assignment");
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page

            CustomAssert.assertEquals(assignments.sectionCount.get(0).getText(),"3","Verify assigned count","Assigned count is as per expected","Assigned count is not as per expected");
            CustomAssert.assertEquals(assignments.sectionCount.get(1).getText(),"1","Verify submitted count","Submitted count is as per expected","Submitted count is not as per expected");
            CustomAssert.assertEquals(assignments.sectionCount.get(2).getText(),"0","Verify graded count","Graded count is as per expected","Graded count is not as per expected");

            assignments.viewResponse.get(0).click();//Click on view response
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[class='btn gradebook-left-btn as-live-print-wrapper']")));
            actual = studentResponse.unAssignStudents.getText();
            CustomAssert.assertEquals(actual,"Unassign Students","Verify un assign student button","Un assign student button is present next to download grades","Un assign student button is not present next to download grades");
            Thread.sleep(1000);
            studentResponse.checkboxInStudentCard.get(0).click();//click on student responses checkbox
            studentResponse.unAssignStudents.click();//click on un assign student button
            CustomAssert.assertTrue(studentResponse.unAssignPopUp.isDisplayed(),"Verify un assign pop up","Un assign pop up is displayed","Un assign pop up is not displayed");
            actual = studentResponse.unAssignPopUp.getText();
            expected ="Unassign\n" +
                    "You are about to unassign this assignment to selected student(s). Student’s responses will be deleted. Do you still want to proceed?\n" +
                    "This action can NOT be undone. If you are sure, please type UNASSIGN in the space given below to proceed.\n" +
                    "No,Cancel\n" +
                    "Yes, Unassign  ";
            CustomAssert.assertEquals(actual,expected,"Verify un assign pop up message","Un assign pop up notification message is as per expected","Un assign pop up is not displaying as per expected");
            studentResponse.closeIconOnUnAssignPopUp.click();//click on close icon
            Thread.sleep(2000);
            boolean syncPopUp = new BooleanValue().presenceOfElementByWebElement(dataIndex, studentResponse.unAssignPopUp);
            CustomAssert.assertEquals(syncPopUp, false, "Verify the un assign pop up", "un assign pop up should closed", "un assign pop up is not closed");
            Thread.sleep(1000);
            studentResponse.checkboxInStudentCard.get(0).click();//click on student responses checkbox to unckeck check box
            Thread.sleep(1000);
            studentResponse.checkboxInStudentCard.get(3).click();//click on not started student check box
            studentResponse.unAssignStudents.click();//click on un assign student button
            studentResponse.unAssign_TextBox.sendKeys("UNASSIGN");
            studentResponse.yesUnAssign.click();//click on yes
            Thread.sleep(4000);
            CustomAssert.assertEquals(studentResponse.submittedStatus.get(1).getText(),"1 out of 2 Submitted","Verify submit status","Submit status is as per expected","Submit status is not as per expected");
            boolean notStartedStudent = new BooleanValue().presenceOfElementByWebElement(dataIndex, studentResponse.notstarted);
            CustomAssert.assertEquals(notStartedStudent, false, "Verify the not started student", "un assign student is not present", "un assigned student is present");

            if( Properties.getPropertyValue("Browser").equals("FIREFOX")){
                driver.findElements(By.cssSelector("g.highcharts-series-group")).get(1).click();

            }
            if( Properties.getPropertyValue("Browser").equals("CHROME")) {
                WebElement BarQ2 = driver.findElements(By.cssSelector("rect[width='30']")).get(1);
                new Actions(driver).moveToElement(BarQ2).click(BarQ2).perform();
            }
            Thread.sleep(8000);
            List<WebElement> bar = driver.findElements(By.cssSelector("rect[width='20']"));
            for(WebElement bars :bar){
                new Actions(driver).moveToElement(bars).build().perform();
                WebElement studentnames = driver.findElement(By.cssSelector("g.highcharts-tooltip text tspan:nth-of-type(1)"));
                if(studentnames.getText().equals(student3UserName)){
                    CustomAssert.fail("Verify un assign student","Un assign student is still present");
                }

            }
            studentResponse.goBack.click();//click on go back
            Thread.sleep(2000);
            studentResponse.expressGraderTab.click();//click on express grader tab
            List<WebElement> students = driver.findElements(By.xpath("//div[contains(@class,'inline student-row std-row')]/span[2]"));
            for(int i =0;i<students.size()-1;i++){
                if(students.get(i).getText().equals(student3UserName)){
                    CustomAssert.fail("Verify un assign student","Un assign student is still present");
                }
            }

            studentResponse.liveClassBoardTab.click();//click on live class board
            Thread.sleep(2000);
            studentResponse.checkboxInStudentCard.get(0).click();//click on student responses checkbox
            studentResponse.unAssignStudents.click();//click on un assign student button
            studentResponse.unAssign_TextBox.sendKeys("UNASSIGN");
            studentResponse.yesUnAssign.click();//click on yes
            boolean syncPopUpAfterYes = new BooleanValue().presenceOfElementByWebElement(dataIndex, studentResponse.unAssignPopUp);
            CustomAssert.assertEquals(syncPopUpAfterYes, false, "Verify the un assign pop up", "un assign pop up should closed", "un assign pop up is not closed");
            actual = assignments.assignmentStatus.getText();
            CustomAssert.assertEquals(actual,"Awaiting Grades","Verify assignment status","Assignment status is awaiting grades","Assignment status is not awaiting grades");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1,dataIndex);//student 1
            new Navigator().navigateTo("assignment");
            actual = assignments.assessmentNotAvailable.getText().trim();
            CustomAssert.assertEquals(actual,"No Assignments Available For You","Verify deleted assessment","Assessment is deleted","Assessment is not deleted");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar2,dataIndex);//student 2
            new Navigator().navigateTo("assignment");
            actual = assignments.assessmentNotAvailable.getText().trim();
            CustomAssert.assertEquals(actual,"No Assignments Available For You","Verify deleted assessment","Assessment is deleted","Assessment is not deleted");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar3,dataIndex);//student 3
            new Navigator().navigateTo("assignment");
            actual = assignments.assessmentNotAvailable.getText().trim();
            CustomAssert.assertEquals(actual,"No Assignments Available For You","Verify deleted assessment","Assessment is deleted","Assessment is not deleted");
            new Navigator().logout();//log out


        }catch(Exception e){
            Assert.fail("Exception in TC studentInProgress of class StudentInProgress", e);
        }

    }

    @Test(priority = 2,enabled = true)
    public void unAssigningStudentWhenItIsAwaitingGrades(){
        try{
            ReportUtil.log("Description","This test case validates instructor can un-Assign Students when assignment status is AwaitingGrades", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 2;

            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar3 = "c" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            System.out.println("appendChar1:"+appendChar1);
            System.out.println("appendChar2:"+appendChar2);
            System.out.println("appendChar3:"+appendChar3);

            /*String appendChar1="asbt";
            String appendChar2="byeS";
            String appendChar3="cBgR";
*/
            String methodName = new Exception().getStackTrace()[0].getMethodName();
            System.out.println(methodName);
            String student3UserName = methodName+"st"+appendChar3;

            //Teacher signup
            new SignUp().teacher(appendChar1,dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369",dataIndex,false);
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

            new Login().loginAsStudent(appendChar1,dataIndex);//student 1 in progress
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

            CustomAssert.assertEquals(assignments.sectionCount.get(0).getText(),"3","Verify assigned count","Assigned count is as per expected","Assigned count is not as per expected");
            CustomAssert.assertEquals(assignments.sectionCount.get(1).getText(),"3","Verify submitted count","Submitted count is as per expected","Submitted count is not as per expected");
            CustomAssert.assertEquals(assignments.sectionCount.get(2).getText(),"0","Verify graded count","Graded count is as per expected","Graded count is not as per expected");

            assignments.viewResponse.get(0).click();//Click on view response
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[class='btn gradebook-left-btn as-live-print-wrapper']")));
            actual = studentResponse.unAssignStudents.getText();
            CustomAssert.assertEquals(actual,"Unassign Students","Verify un assign student button","Un assign student button is present next to download grades","Un assign student button is not present next to download grades");
            Thread.sleep(3000);
            studentResponse.checkboxInStudentCard.get(0).click();//click on student responses checkbox
            studentResponse.unAssignStudents.click();//click on un assign student button
            CustomAssert.assertTrue(studentResponse.unAssignPopUp.isDisplayed(),"Verify un assign pop up","Un assign pop up is displayed","Un assign pop up is not displayed");
            actual = studentResponse.unAssignPopUp.getText();
            expected ="Unassign\n" +
                    "You are about to unassign this assignment to selected student(s). Student’s responses will be deleted. Do you still want to proceed?\n" +
                    "This action can NOT be undone. If you are sure, please type UNASSIGN in the space given below to proceed.\n" +
                    "No,Cancel\n" +
                    "Yes, Unassign  ";
            CustomAssert.assertEquals(actual,expected,"Verify un assign pop up message","Un assign pop up notification message is as per expected","Un assign pop up is not displaying as per expected");
            studentResponse.closeIconOnUnAssignPopUp.click();//click on close icon
            Thread.sleep(2000);
            boolean syncPopUp = new BooleanValue().presenceOfElementByWebElement(dataIndex, studentResponse.unAssignPopUp);
            CustomAssert.assertEquals(syncPopUp, false, "Verify the un assign pop up", "un assign pop up should closed", "un assign pop up is not closed");
            studentResponse.checkboxInStudentCard.get(0).click();//click on student responses checkbox to unckeck check box
            studentResponse.checkboxInStudentCard.get(3).click();//click on not started student check box
            studentResponse.unAssignStudents.click();//click on un assign student button
            studentResponse.unAssign_TextBox.sendKeys("UNASSIGN");
            studentResponse.yesUnAssign.click();//click on yes
            Thread.sleep(2000);
            //CustomAssert.assertEquals(studentResponse.submittedStatus.get(1).getText(),"2 out of 2 Submitted","Verify submit status","Submit status is as per expected","Submit status is not as per expected");
            boolean notStartedStudent = new BooleanValue().presenceOfElementByWebElement(dataIndex, studentResponse.notstarted);
            CustomAssert.assertEquals(notStartedStudent, false, "Verify the not started student", "un assign student is not present", "un assigned student is present");

            if( Properties.getPropertyValue("Browser").equals("FIREFOX")){
                driver.findElements(By.cssSelector("g.highcharts-series-group")).get(1).click();

            }
            if( Properties.getPropertyValue("Browser").equals("CHROME")) {
                WebElement BarQ2 = driver.findElements(By.cssSelector("rect[width='30']")).get(1);
                new Actions(driver).moveToElement(BarQ2).click(BarQ2).perform();
            }
            Thread.sleep(8000);
            List<WebElement> bar = driver.findElements(By.cssSelector("rect[width='20']"));
            for(WebElement bars :bar){
                new Actions(driver).moveToElement(bars).build().perform();
                WebElement studentnames = driver.findElement(By.cssSelector("g.highcharts-tooltip text tspan:nth-of-type(1)"));
                if(studentnames.getText().equals(student3UserName)){
                    CustomAssert.fail("Verify un assign student","Un assign student is still present");
                }

            }
            studentResponse.goBack.click();//click on go back
            Thread.sleep(2000);
            studentResponse.expressGraderTab.click();//click on express grader tab
            List<WebElement> students = driver.findElements(By.xpath("//div[contains(@class,'inline student-row std-row')]/span[2]"));
            for(int i =0;i<students.size()-1;i++){
                if(students.get(i).getText().equals(student3UserName)){
                    CustomAssert.fail("Verify un assign student","Un assign student is still present");
                }
            }

            studentResponse.liveClassBoardTab.click();//click on live class board
            Thread.sleep(2000);
            studentResponse.checkboxInStudentCard.get(0).click();//click on student responses checkbox
            studentResponse.unAssignStudents.click();//click on un assign student button
            studentResponse.unAssign_TextBox.sendKeys("UNASSIGN");
            studentResponse.yesUnAssign.click();//click on yes
            boolean syncPopUpAfterYes = new BooleanValue().presenceOfElementByWebElement(dataIndex, studentResponse.unAssignPopUp);
            CustomAssert.assertEquals(syncPopUpAfterYes, false, "Verify the un assign pop up", "un assign pop up should closed", "un assign pop up is not closed");
            actual = assignments.assignmentStatus.getText();
            CustomAssert.assertEquals(actual,"Awaiting Grades","Verify assignment status","Assignment status is awaiting grades","Assignment status is not awaiting grades");
            new Navigator().logout();//log out

        }catch(Exception e){
            Assert.fail("Exception in TC unAssigningStudentWhenItIsAwaitingGrades of class StudentInProgress", e);
        }

    }


    @Test(priority = 3,enabled = true)
    public void DACommonAssignmentNotOpened(){
        try{
            ReportUtil.log("Description","This test case validates assignment status at da side after instructor un-assign a student", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 3;
            String email = "ryanda@snapwiz.com";
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            System.out.println(assessmentname);
            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar3 = "c" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            System.out.println("appendChar1:"+appendChar1);
            System.out.println("appendChar2:"+appendChar2);
            System.out.println("appendChar3:"+appendChar3);
/*

            String appendChar1="asbt";
            String appendChar2="byeS";
            String appendChar3="cBgR";
*/

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            System.out.println(methodName);

            //Teacher signup
            new SignUp().teacher(appendChar1, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode = new Classes().createClass(appendChar1, dataIndex);//Create class
            System.out.println(classCode);
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar3,classCode,dataIndex);//Sign up as a student3
            new Navigator().logout();//log out

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            new Assignment().createByDA(dataIndex, "truefalse");// includes true false and essay
            new Assignment().assignByDA(dataIndex, classCode);
            new Navigator().navigateTo("dashboard");// Navigate to dashboard
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            Thread.sleep(5000);
            assignments.open.get(0).click();//Click on open
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            CustomAssert.assertEquals(assignments.sectionCount.get(0).getText(),"3","Verify assigned count","Assigned count is as per expected","Assigned count is not as per expected");
            CustomAssert.assertEquals(assignments.sectionCount.get(1).getText(),"0","Verify submitted count","Submitted count is as per expected","Submitted count is not as per expected");
            CustomAssert.assertEquals(assignments.sectionCount.get(2).getText(),"0","Verify graded count","Graded count is as per expected","Graded count is not as per expected");

            assignments.viewResponse.get(0).click();//Click on view response
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".iCheck-helper")));
            Thread.sleep(1000);
            studentResponse.checkboxInStudentCard.get(1).click();//click on not started student check box
            studentResponse.unAssignStudents.click();//click on un assign student button
            studentResponse.unAssign_TextBox.sendKeys("UNASSIGN");
            studentResponse.yesUnAssign.click();//click on yes
            Thread.sleep(2000);
            CustomAssert.assertEquals(studentResponse.submittedStatus.get(1).getText(),"0 out of 2 Submitted","Verify submit status","Submit status is as per expected","Submit status is not as per expected");
            new Navigator().logout();//log out

           new Login().loginAsDA(dataIndex, email); //login as District Admin
            new Navigator().navigateTo("dashboard");//Navigate to common assessment
            int index =0;
            for (int i =0 ; i <commonAssessments.assessmentName.size();i++) {
                System.out.println(commonAssessments.assessmentName.get(i).getText()+ i);
                if (commonAssessments.assessmentName.get(i).getText().equals(assessmentname)) {
                    break;
                }
                index++;

            }
            commonAssessments.assessmentName.get(index).click();
            Thread.sleep(2000);
            districtAssessmentResponse.viewStudentResponse.get(0).click();
            Thread.sleep(1000);
            actual = districtAssessmentResponse.assignmentInProgressStatus.getText();
            CustomAssert.assertEquals(actual,"In Progress","Verify assignemnt status","Status is as per expected","Status is not as per expected");
            CustomAssert.assertEquals(districtAssessmentResponse.studentResponseCard.size(),2,"Verify student response card","Card size is 2","Card size is not 2");

        }catch(Exception e){
            Assert.fail("Exception in TC DACommonAssignmentNotOpened of class StudentInProgress", e);
        }

    }


    @Test(priority = 4,enabled = true)
    public void DACommonAssignmentGraded(){
        try{
            ReportUtil.log("Description","This test case validates assignment status at da side after instructor un-assign submitted student", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 4;
            String email = "ryanda@snapwiz.com";
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);

            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar3 = "c" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            System.out.println("appendChar1:"+appendChar1);
            System.out.println("appendChar2:"+appendChar2);
            System.out.println("appendChar3:"+appendChar3);

           /* String appendChar1="adCk";
            String appendChar2  ="bHCS";
            String appendChar3 = "cYgD";*/

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            System.out.println(methodName);
            String insemail = methodName + "ins" + appendChar1 + "@snapwiz.com";

            //Teacher signup
            new SignUp().teacher(appendChar1, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode = new Classes().createClass(appendChar1, dataIndex);//Create class
            System.out.println(classCode);
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar3,classCode,dataIndex);//Sign up as a student3
            new Navigator().logout();//log out

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            new Assignment().createByDA(dataIndex, "truefalse");// includes true false and essay
            new Assignment().assignByDA(dataIndex, classCode);
            new Navigator().navigateTo("dashboard");// Navigate to dashboard
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            Thread.sleep(5000);
            assignments.open.get(0).click();//Click on open
            new Navigator().logout();//log out


            new Login().loginAsStudent(appendChar1,dataIndex);//student 1 in progress
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

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            new Navigator().navigateTo("dashboard");//Navigate to common assessment

            int index =0;
            for (int i =0 ; i <commonAssessments.assessmentName.size();i++) {
                System.out.println(commonAssessments.assessmentName.get(i).getText()+ i);
                if (commonAssessments.assessmentName.get(i).getText().equals(assessmentname)) {
                    break;
                }
                index++;

            }
            commonAssessments.assessmentName.get(index).click();
            int grade=0;
            for (WebElement ele:commonAssessments.grades_status){
                if(ele.getText().trim().equals("Awaiting Grades")){
                    break;
                }
                grade++;
            }
            WebDriverUtil.clickOnElementUsingJavascript(commonAssessments.assignment_checkbox.get(grade+1));
            Thread.sleep(2000);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector(".btn.release-feed.btn-rounded.m-r-sm")));//Click on release grade button
            Thread.sleep(7000);
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".iCheck-helper")));
            Thread.sleep(1000);
            studentResponse.checkboxInStudentCard.get(1).click();//click on not started student check box
            studentResponse.unAssignStudents.click();//click on un assign student button
            actual = studentResponse.notificationMsg_redirect.getText();
            expected = "You will NOT be able to unassign selected student(s) as the assignment is already graded";
            CustomAssert.assertEquals(actual,expected,"Verify error message","Error message is as per expected","Error message is not as per expected");
            new Navigator().logout();//log out

        }catch(Exception e){
            Assert.fail("Exception in TC DACommonAssignmentGraded of class StudentInProgress", e);
        }

    }



    @Test(priority = 5,enabled = true)
    public void DACommonAssignmentGradeReleaseOnSubmission(){
        try{
            ReportUtil.log("Description", "This test case validates DA- Common assignment Grade release on submission Awaitng Grades ", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 5;
            String email = "ryanda@snapwiz.com";
            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar3 = "c" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            System.out.println("appendChar1:"+appendChar1);
            System.out.println("appendChar2:"+appendChar2);
            System.out.println("appendChar3:"+appendChar3);

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            System.out.println(methodName);

            //Teacher signup
            new SignUp().teacher(appendChar1, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode = new Classes().createClass(appendChar1, dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar3,classCode,dataIndex);//Sign up as a student3
            new Navigator().logout();//log out

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            new Assignment().createByDA(dataIndex, "truefalse");// includes true false and essay
            new Assignment().assignByDA(dataIndex, classCode);
            new Navigator().navigateTo("dashboard");// Navigate to dashboard
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            Thread.sleep(5000);
            assignments.open.get(0).click();//Click on open
            new Navigator().logout();//log out


            new Login().loginAsStudent(appendChar1,dataIndex);//student 1 in progress
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
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".iCheck-helper")));
            Thread.sleep(1000);
            studentResponse.checkboxInStudentCard.get(1).click();//click on not started student check box
            studentResponse.unAssignStudents.click();//click on un assign student button
            actual = studentResponse.notificationMsg_redirect.getText();
            expected = "You will NOT be able to unassign selected student(s) as the assignment is already graded";
            CustomAssert.assertEquals(actual,expected,"Verify error message","Error message is as per expected","Error message is not as per expected");
            new Navigator().logout();//log out


        }catch(Exception e){
            Assert.fail("Exception in TC DACommonAssignmentGradeReleaseOnSubmission of class StudentInProgress", e);
        }

    }

    @Test(priority = 6,enabled = true)
    public void DACommonAssignmentCloseAssignment(){
        try{
            ReportUtil.log("Description", "This test case validates DA Common assignment Grade release on submission", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 6;
            String email = "ryanda@snapwiz.com";
            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar3 = "c" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            System.out.println("appendChar1:"+appendChar1);
            System.out.println("appendChar2:"+appendChar2);
            System.out.println("appendChar3:"+appendChar3);

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            System.out.println(methodName);

            //Teacher signup
            new SignUp().teacher(appendChar1, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode = new Classes().createClass(appendChar1, dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar3,classCode,dataIndex);//Sign up as a student3
            new Navigator().logout();//log out

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            new Assignment().createByDA(dataIndex, "truefalse");// includes true false and essay
            new Assignment().assignByDA(dataIndex, classCode);
            new Navigator().navigateTo("dashboard");// Navigate to dashboard
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            Thread.sleep(5000);
            assignments.open.get(0).click();//Click on open
            new Navigator().logout();//log out

           new Login().loginAsStudent(appendChar1,dataIndex);//student 1 in progress
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

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            Thread.sleep(5000);
            assignments.closeAssignment.click();//click on close assignment
            assignments.deleteTextBox.sendKeys("CLOSE");
            assignments.yesCloseButton.click();//click on yes close
            Thread.sleep(2000);
            WebDriverUtil.clickOnElementUsingJavascript(assignments.viewResponse.get(0));//Click on view response
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".iCheck-helper")));
            Thread.sleep(1000);
            studentResponse.checkboxInStudentCard.get(1).click();//click on not started student check box
            studentResponse.unAssignStudents.click();//click on un assign student button
            actual = studentResponse.notificationMsg_redirect.getText();
            expected = "You will NOT be able to unassign selected student(s) as the assignment is already graded";
            CustomAssert.assertEquals(actual,expected,"Verify error message","Error message is as per expected","Error message is not as per expected");
            new Navigator().logout();//log out

            new Login().loginAsDA(dataIndex, email); //login as District Admin

        }catch(Exception e){
            Assert.fail("Exception in TC DACommonAssignmentCloseASsignment of class StudentInProgress", e);
        }
    }



}
