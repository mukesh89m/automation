package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.signupwitherrormessage;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.MyAssessments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.classpage.ClassPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.reports.MyReports;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.SignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.StudentSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.TeacherSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Pragya on 07-12-2015.
 */
public class SignUp extends Driver {
    static String zipcode;

    @Test(priority = 1, enabled = true)
    public void teacherSignUpNewSchoolAndStudentSignUpUsingUrl(){
        try{

            ReportUtil.log("Description","This test case validates the instructor sign up using new school and student sign up using url","info");
            String appendChar = "a";
            WebDriver driver=Driver.getWebDriver();
            String appendCharacterBuild="";
            if (System.getProperty("UCHAR")!=null)
                appendCharacterBuild=System.getProperty("UCHAR");

            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            TeacherSignUpPage teacherSignUpPage = PageFactory.initElements(driver,TeacherSignUpPage.class);
            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver,StudentSignUpPage.class);
            ClassPage classPage = PageFactory.initElements(driver,ClassPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            MyAssessments myAssessments = PageFactory.initElements(driver,MyAssessments.class);
            ManageClass manageClass = PageFactory.initElements(driver,ManageClass.class);
            InstructorDashboard instructorDashboard = PageFactory.initElements(driver,InstructorDashboard.class);
            MyReports myReports = PageFactory.initElements(driver,MyReports.class);
            SignUpPage signUpPage = PageFactory.initElements(driver,SignUpPage.class);

            String currentMethodName = new Exception().getStackTrace()[0].getMethodName();

            //All the error messages validations on sign up page are commented as it was taking long time(done in API Testing)
            new OpenMixPanel().openMixpanelAndAddCookies();
            Config.readconfiguration();
            driver.get(Config.baseURL);//Open the application
            String signInUrl = driver.getCurrentUrl();//Get the url of sign in page

            //Expected - Sign in url should contain login
            if(!signInUrl.contains("/#login")){
                CustomAssert.fail("Verify sign in url","Sign In Url is not displayed as expected");
            }
            loginPage.getButton_signUpItsFree().click();//Click on sign up its free button
            Thread.sleep(2000);

            String signUpUrl = driver.getCurrentUrl();//Get the url of sign up page

            //Expected - Sign in and sign up url should be different
            if(signInUrl.equals(signUpUrl)){
                CustomAssert.fail("Compare both sign in and sign up url","Both 'sign in' and 'sign up' url is same");
            }

            signUpPage.getTab_teacher().click();//Click on I'm a Teacher

            String teacherSignUpUrl = driver.getCurrentUrl();//Get the url of sign up page

            //Expected - Sign up url should contain '/#register/close/teacher'
            if(!teacherSignUpUrl.contains("/#register/close/teacher")){
                CustomAssert.fail("Verify sign up url","Sign Up Url is not displayed as expected");
            }


            teacherSignUpPage.getButton_signUp().click();//Click on sign up as teacher button without entering any field

            //Expected - Error message should appear on each field
            CustomAssert.assertEquals(teacherSignUpPage.getErrorMessage_name().getText(),"Please provide your full name.","Check for error message on name field","Error message is appearing on name as expected","Error message is not appearing on name as expected");
            CustomAssert.assertEquals(teacherSignUpPage.getErrorMessage_email().getText(),"Please provide a valid Email id.","Check for error message on email field","Error message is appearing on email as expected","Error message is not appearing on email as expected");
            CustomAssert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Please provide a valid password.","Check for error message on password field","Error message is appearing on password as expected","Error message is not appearing on password as expected");

            studentSignUpPage.getTextBox_name().sendKeys(currentMethodName+appendChar);//enter first name
            teacherSignUpPage.getButton_signUp().click();//Click on sign up as teacher button

            //Error message should appear only on email and password field
            CustomAssert.assertEquals(teacherSignUpPage.getErrorMessage_email().getText(),"Please provide a valid Email id.","Check for error message on mail","Error message is appearing on email as expected","Error message is not appearing on email as expected");
            CustomAssert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Please provide a valid password.","Check forerror message on password","Error message is appearing on password as expected","Error message is not appearing on password as expected");

            //Error message should not appear on name field
            boolean errorOnNameFound = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_name());
            CustomAssert.assertEquals(errorOnNameFound,false,"Check for error message on name field","Error message is not displayed on name after entering the value","Error message is displayed on name even after entering the value");

            studentSignUpPage.getTextBox_email().sendKeys(currentMethodName+appendChar);//Enter email id in wrong format
            teacherSignUpPage.getButton_signUp().click();//Click on sign up as teacher button

            //Error message should appear again only on email and password field
            CustomAssert.assertEquals(teacherSignUpPage.getErrorMessage_email().getText(),"Please provide a valid Email id.","Check for error message on email id","Error message is appearing on email as expected","Error message is not appearing on email as expected");
            CustomAssert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Please provide a valid password.","Check for error message on password","Error message is appearing on password as expected","Error message is not appearing on password as expected");

            //Error message should not appear on name field
            boolean errorOnNameFound1 = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_name());
            CustomAssert.assertEquals(errorOnNameFound1,false,"Check for error message on name field","Error message is not displayed on name after entering the value","Error message is displayed on name even after entering the value");

            studentSignUpPage.getTextBox_email().clear();
            studentSignUpPage.getTextBox_email().sendKeys(currentMethodName+appendChar+"@snapwiz.com");//Enter email id in correct format
            teacherSignUpPage.getButton_signUp().click();//Click on sign up as teacher button

            //Error message should appear only on password field
            CustomAssert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Please provide a valid password.","Check for error message on password","Error message is appearing on password as expected","Error message is not appearing on password as expected");

            //Error message should not appear on name and email field
            boolean errorOnNameFound2 = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_name());
            CustomAssert.assertEquals(errorOnNameFound2,false,"Check for error message on name field","Error message is not displayed on name after entering the value","Error message is displayed on name even after entering the value");

            boolean errorOnEmailFound = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_email());
            CustomAssert.assertEquals(errorOnEmailFound,false,"Check for error message on email field","Error message is not displayed on email after entering the value","Error message is displayed on email even after entering the value");

            studentSignUpPage.getTextBox_password().sendKeys("sna");//Enter password(only 3 characters)
            teacherSignUpPage.getButton_signUp().click();//click on sign up as teacher

            //Error message should appear only on password field
            CustomAssert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Password is too short - must be at least 5 characters.","Check for error message on password field","Error message is displayed on password field as expected","Error message is not displayed on password field as expected");

            studentSignUpPage.getTextBox_password().clear();
            studentSignUpPage.getTextBox_password().sendKeys("snapwiz");//Enter valid password
            teacherSignUpPage.getButton_signUp().click();//click on sign up as teacher

            //Error message should appear only on retype password field
            CustomAssert.assertEquals(teacherSignUpPage.getErrorMessage_retypePassword().getText(),"Please retype the password.","Check for error message on retype password field","Error message is displayed on retype password field as expected","Error message is not displayed on retype password field as expected");

            //Error message should not appear on any other field except retype password field
            boolean errorOnNameFound3 = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_name());
            CustomAssert.assertEquals(errorOnNameFound3,false,"Check for error message on name","Error message is not displayed on name after entering the value","Error message is displayed on name even after entering the value");

            boolean errorOnEmailFound1 = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_email());
            CustomAssert.assertEquals(errorOnEmailFound1,false,"Check for error message on email field","Error message is not displayed on email after entering the value","Error message is displayed on email even after entering the value");

            boolean errorOnPassword = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_email());
            CustomAssert.assertEquals(errorOnPassword,false,"Check for error message on password field","Error message is not displayed on password after entering the value","Error message is displayed on password even after entering the value");

            //Expected - Teacher SignUp through app creating new school
            new com.snapwiz.selenium.tests.staf.edulastic.apphelpers.SignUp().teacher(appendChar,1);//Register teacher1

            //All the error messages validations on school page are commented as it was taking long time(done in API Testing)
            schoolPage.getButton_add().click();//Click on add button
            schoolPage.getButton_addSchool().click();//Click on 'Add school button'

            //Expected - Error should appear if school name and zip is not entered
            CustomAssert.assertEquals(schoolPage.getError_msg_schoolName().getText(),"Please provide a valid school name.","Check for error message on school name field","Error message is appearing correctly if school is not entered","Error message is not appearing correctly if school is not entered");
            CustomAssert.assertEquals(schoolPage.getError_msg_zip().getText(),"Please provide a valid zip code.","Check for error message on zip code field","Error message is displayed correctly if zip is not entered","Error message is not displayed correctly if zip is not entered");
            schoolPage.getEditbox_schoolName().click();
            schoolPage.getEditbox_schoolName().clear();
            driver.switchTo().activeElement().sendKeys("School");//Enter the school name

            String str = new RandomString().randomstring(6);
            schoolPage.editBox_zipCode.sendKeys(str);//Enter zip code as string
            schoolPage.getButton_addSchool().click();//Click on 'Add school button'

            //Expected - Error should appear on zip code
            CustomAssert.assertEquals(schoolPage.getError_msg_zip().getText(),"Please provide a valid zip code.","Check for error message on zip code field","Error message is appearing correctly if zip is not entered","Error message is not appearing correctly if zip is not entered");

            //Error message should not appear on school name
            boolean errorOnSchoolFound = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_name());
            CustomAssert.assertEquals(errorOnSchoolFound,false,"Check for error message on school name field","Error message is not displayed on school after entering the value","Error message is displayed on school even after entering the value");
            schoolPage.getClose().click();//Click on close button

            zipcode=new School().createWithOnlyName(appendChar,1);//Create a school

            //By default class name should appear on class page
            CustomAssert.assertEquals(classPage.nameOfClass.getAttribute("placeholder"),currentMethodName+"ins"+appendChar+appendCharacterBuild+"'s "+"class","Verify default class name","Default class name is displayed as expected","Default class name is not displayed as expected");

            //All the error messages validations on class page are commented as it was taking long time(done in API Testing)
            classPage.finishButton.click();//Click on finish button

            //Error message should appear on all the field except class name
            CustomAssert.assertEquals(classPage.errorMsg_standard.getText(),"Subject Area is not selected","Verify error message on subject area","Error message is displayed as expected on subject area","Error message is not displayed as expected on subject area");
            CustomAssert.assertEquals(classPage.errorMsg_subject.getText(),"Subject is not selected","Verify error message on subject","Error message is displayed as expected on subject","Error message is not displayed as expected on subject");
            CustomAssert.assertEquals(classPage.errorMsg_subjectGrade.getText(),"Grade is not selected","Verify error message on subject greade","Error message is displayed as expected on subject grade","Error message is not displayed as expected on subject grade");

            new Classes().createClass(appendChar, 1);//create class

            //Default video verification is done in API Teting
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            Thread.sleep(3000);
            driver.switchTo().frame(instructorDashboard.videoFrame);
            new WebDriverWait(driver,160).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("wrapper")));
            //Verify the default video
            CustomAssert.assertTrue(instructorDashboard.defaultVideo.getText().contains("This video is not authorized to be embedded here."),"Verify the default video","Default video is displayed as expected","Default video is not displayed as expected");
            driver.switchTo().defaultContent();

            new Navigator().navigateTo("assignment");//Navigate to assignment
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.as-noData-msgWrapper")));

            //Expected - Assignment should not appear
            CustomAssert.assertTrue(assignments.message_noAssignment.getText().contains("No Assignments Available For Your Class"),"Verify default image of assignment","Assignments are not appearing for newly signed up instructor", "Assignments are appearing for newly signed up instructor");

            new Navigator().navigateTo("myAssessments");//Navigate to my assessments

            //No assessments should appear under published and draft
            CustomAssert.assertTrue(assignments.message_noAssignment.getText().contains("Assessment not available"),"Verify default image under published tab","Assessments are not appearing for newly signed up instructor under published tab","Assessments are appearing for newly signed up instructor under published tab");

            myAssessments.draft.click();//Click on draft

            CustomAssert.assertTrue(assignments.message_noAssignment.getText().contains("Drafts not available"),"Verify default image under draft tab","Assessments are not appearing under draft for newly signed up instructor","Assessments are appearing under draft for newly signed up instructor");

            new Navigator().navigateTo("myReports");//Navigate to my reports

            //Verify the default reports and value of Standard mastered and assessed
            CustomAssert.assertTrue(myReports.defaultReport.getText().contains("Skill Report Not Available"),"Verify default skill report","Default skill report is displayed as expected","Default skill report is not displayed as expected");
            CustomAssert.assertEquals(myReports.standards.get(0).getText(),"0%","Verify the value of Standard Mastered","Standard mastered value is displayed as expected","Standard mastered value is not displayed as expected");
            CustomAssert.assertEquals(myReports.standards.get(2).getText(),"0%","Verify the value of Standard Assesssed","Standard Assesssed value is displayed as expected","Standard Assesssed value is not displayed as expected");

            new Navigator().navigateTo("manageclass");//Navigate to manage class

            //Expected - Verify active and archived tab
            CustomAssert.assertEquals(manageClass.tab_activeAndArchived.get(0).getText(),"Active (2)","Verify Active tab at instructor side","Active tab is displayed as expected","Active tab is not displayed as expected");
            CustomAssert.assertEquals(manageClass.tab_activeAndArchived.get(1).getText(),"Archived (0)","Verify Archived tab at instructor side","Archive tab is displayed as expected","Archive tab is not displayed as expected");

            String url = manageClass.getUrl().getText();
            String classCodeInstructor = manageClass.getclassCode().get(0).getText();
            new Navigator().logout();//log out

            driver.get(String.valueOf(url));//open the url copied in manage class

            //Expected - Class code field should be disabled
            CustomAssert.assertEquals(studentSignUpPage.getTextBox_classCode().getAttribute("disabled"),"true","Verify the class code field","Class code field is disabled as expected","Class code field is not disabled as expected");

            //Student sign up using url
            new com.snapwiz.selenium.tests.staf.edulastic.apphelpers.SignUp().studetnSignUpUsingUrl(1,url,appendChar);//Sign up student using url

            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.as-noData-msgWrapper")));

            //Assignment should not appear
            CustomAssert.assertTrue(assignments.message_noAssignment.getText().contains("No Assignments Available For You"),"Verify the assignment at student side","Assignments are not appearing as expected","Assignments are appearing");

            new Navigator().navigateTo("manageclass");//Navigate to manage class

            //Expected - Verify active and archived tab
            CustomAssert.assertEquals(manageClass.tab_activeAndArchived.get(0).getText(),"Active (1)","Verify Active tab","Active tab is displayed as expected","Active tab is not displayed as expected");
            CustomAssert.assertEquals(manageClass.tab_activeAndArchived.get(1).getText(),"Archived (0)","Verify Archived tab","Archive tab is displayed as expected","Archive tab is not displayed as expected");

            //Expected - Verify class name of student and class code
            CustomAssert.assertEquals(manageClass.getClassNameStudent().get(0).getAttribute("title"),currentMethodName+"class"+appendChar+appendCharacterBuild,"Verify class name at student side","Student class name is correct","Student class name is not correct");

            String classCodeStudent = manageClass.getclassCode().get(0).getText();
            if(!classCodeInstructor.equals(classCodeStudent))
            {
                CustomAssert.fail("Verify class code at student side","Class code in not same in student side as copied at instructor side");
            }

        }catch (Exception e){
            Assert.fail("Exception in testcase 'teacherSignUpNewSchoolAndStudentSignUpUsingUrl' in class 'SignUp'", e);

        }

    }


    @Test(priority = 2, enabled = true)
    public void teacherSignUpUsingExistingSchoolAndStudentSignUpUsingClassCode(){
        try{
            ReportUtil.log("Description","This test case validates the instructor sign up using existing school and student sign up using class code","info");

            String appendChar = "a";
            WebDriver driver=Driver.getWebDriver();
            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            TeacherSignUpPage teacherSignUpPage = PageFactory.initElements(driver,TeacherSignUpPage.class);
            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver,StudentSignUpPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);

            String currentMethodName = new Exception().getStackTrace()[0].getMethodName();

            //Expected - Teacher SignUp through app using existing school
            new com.snapwiz.selenium.tests.staf.edulastic.apphelpers.SignUp().teacher(appendChar,1);//Register teacher1

            new School().enterAndSelectSchool(zipcode,1,false);//Select existing school
            schoolPage.getContinueButton().click();//Click on continue button

            String classCode = new Classes().createClass(appendChar, 1);//create class
            new Navigator().logout();//log out


            //All the error messages are verified in API Testing
            //Student Registration using class Code
            driver.get(Config.baseURL);
            new Navigator().studentSignUp();//Navigate to student registration page
            studentSignUpPage.getButton_signUp().click();//Click on sign up as student button

            //Expected - Error message should appear on each field
            CustomAssert.assertEquals(studentSignUpPage.getErrorMessage_classCode().getText(),"Please provide a valid class code.","Verify error message on class code","Error message is appearing on class code","Error message is not appearing on class code");
            CustomAssert.assertEquals(teacherSignUpPage.getErrorMessage_name().getText(),"Please provide your full name.","Verify the error message on name field","Error message is appearing on name as expected","Error message is not appearing on name as expected");
            CustomAssert.assertEquals(teacherSignUpPage.getErrorMessage_email().getText(),"Please provide valid Username or Email id.","Verify the error message on mail field","Error message is appearing on email as expected","Error message is not appearing on email as expected");
            CustomAssert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Please provide a valid password.","Verify the error message on password field","Error message is appearing on password as expected","Error message is not appearing on password as expected");

            studentSignUpPage.getTextBox_classCode().sendKeys(classCode);//Enter class code
            studentSignUpPage.getButton_signUp().click();//Click on sign up as student button

            //Error message should not appear on class code field
            boolean errorOnClassCodeFoundStudentSide = new BooleanValue().presenceOfElementByWebElement(1,studentSignUpPage.getErrorMessage_classCode());
            CustomAssert.assertEquals(errorOnClassCodeFoundStudentSide,false,"Verify error message on name field","Error message is not displayed on name after entering the value","Error message is displayed on name even after entering the value");

            studentSignUpPage.getTextBox_name().sendKeys(currentMethodName+appendChar);//enter first name
            studentSignUpPage.getButton_signUp().click();//Click on sign up as student button

            //Error message should appear only on email and password field
            CustomAssert.assertEquals(teacherSignUpPage.getErrorMessage_email().getText(),"Please provide valid Username or Email id.","Verify the error message on email","Error message is appearing on email as expected","Error message is not appearing on email as expected");
            CustomAssert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Please provide a valid password.","Verify the error message on password","Error message is appearing on password as expected","Error message is not appearing on password as expected");

            //Error message should not appear on name field
            boolean errorOnNameFoundStudentSide = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_name());
            CustomAssert.assertEquals(errorOnNameFoundStudentSide,false,"Verify error message on name field","Error message is not displayed on name after entering the value","Error message is displayed on name even after entering the value");

            studentSignUpPage.getTextBox_email().sendKeys(currentMethodName+appendChar);//Enter email id in wrong format
            studentSignUpPage.getButton_signUp().click();//Click on sign up as student button

            //Error message should appear only on password field
            CustomAssert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Please provide a valid password.","Verify error message on password field","Error message is appearing on password as expected","Error message is not appearing on password as expected");

            //Error message should not appear on name and email field
            boolean errorOnNameFound1Studentside = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_name());
            CustomAssert.assertEquals(errorOnNameFound1Studentside,false,"Verify error message on name field at student side","Error message is not displayed on name after entering the value","Error message is displayed on name even after entering the value");

            boolean errorOnEmailFoundStudentside = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_email());
            CustomAssert.assertEquals(errorOnEmailFoundStudentside,false,"Verify error message on email field at student side","Error message is not displayed on email even after entering the value","Error message is displayed on email even after entering the value");

            studentSignUpPage.getTextBox_email().clear();
            studentSignUpPage.getTextBox_email().sendKeys(currentMethodName+appendChar+"@snapwiz.com");//Enter email id in correct format
            studentSignUpPage.getButton_signUp().click();//Click on sign up as student button

            //Error message should appear only on password field
            CustomAssert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Please provide a valid password.","Verify error message on password","Error message is appearing on password as expected","Error message is not appearing on password as expected");

            //Error message should not appear on name and email field
            boolean errorOnNameFound2StudentSide = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_name());
            CustomAssert.assertEquals(errorOnNameFound2StudentSide,false,"Verify error message on name field at student's side","Error message is not displayed on name after entering the value","Error message is displayed on name even after entering the value");

            boolean errorOnEmailFoundStudentSide = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_email());
            CustomAssert.assertEquals(errorOnEmailFoundStudentSide,false,"Verify error message on email field at student side","Error message is not  displayed on email after entering the value","Error message is displayed on email even after entering the value");

            studentSignUpPage.getTextBox_password().sendKeys("sna");//Enter password(only 3 characters)
            studentSignUpPage.getButton_signUp().click();//Click on sign up as student button

            //Error message should appear only on password field
            CustomAssert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Password is too short - must be at least 5 characters.","Verify error message on password field","Error message is displayed on password field as expected","Error message is not displayed on password field as expected");

            studentSignUpPage.getTextBox_password().clear();
            studentSignUpPage.getTextBox_password().sendKeys("snapwiz");//Enter valid password
            studentSignUpPage.getButton_signUp().click();//Click on sign up as student button

            //Error message should appear only on retype password field
            CustomAssert.assertEquals(teacherSignUpPage.getErrorMessage_retypePassword().getText(),"Please retype the password.","Verify error message on retype password field","Error message is displayed on retype password field as expected","Error message is not displayed on retype password field as expected");

            //Error message should not appear on any other field except retype password field
            boolean errorOnNameFound3StudentSide = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_name());
            CustomAssert.assertEquals(errorOnNameFound3StudentSide,false,"Verify error messsage on student side","Error message is not displayed on name after entering the value","Error message is displayed on name even after entering the value");

            boolean errorOnEmailFound1StudentSide = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_email());
            CustomAssert.assertEquals(errorOnEmailFound1StudentSide,false,"Verify error message on email at student side","Error message is not displayed on email after entering the value","Error message is displayed on email even after entering the value");

            boolean errorOnPasswordStudentSide = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_email());
            CustomAssert.assertEquals(errorOnPasswordStudentSide, false, "Verify error message on password at student side", "Error message is not displayed on password after entering the value", "Error message is displayed on password even after entering the value");

            new com.snapwiz.selenium.tests.staf.edulastic.apphelpers.SignUp().studentDirectRegister(appendChar,classCode,1);

            new Navigator().navigateTo("assignment");//Navigate to assignment page

            //Assignment should not appear
            CustomAssert.assertTrue(assignments.message_noAssignment.getText().contains("No Assignments Available For You"),"Verify the assignment at student side","Assignments are not appearing as expected","Assignments are appearing");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'teacherSignUpUsingExistingSchoolAndStudentSignUpUsingClassCode' in class 'SignUp'",e);

        }
    }



}
