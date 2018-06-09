package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.signup;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.MyAssessments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.classpage.ClassPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.StudentSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.TeacherSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.logging.Level;

/**
 * Created by Pragya on 07-12-2015.
 */
public class SignUp extends Driver {

    static String zipcode;

    @Test(priority = 1, enabled = true)
    public void teacherAndStudentSignUpNewSchool(){
        WebDriver driver=Driver.getWebDriver();
        try{
            String appendChar = new RandomString().randominteger(3);

            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            TeacherSignUpPage teacherSignUpPage = PageFactory.initElements(driver,TeacherSignUpPage.class);
            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver,StudentSignUpPage.class);
            ClassPage classPage = PageFactory.initElements(driver,ClassPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            MyAssessments myAssessments = PageFactory.initElements(driver,MyAssessments.class);
            ManageClass manageClass = PageFactory.initElements(driver,ManageClass.class);

            String currentMethodName = new Exception().getStackTrace()[0].getMethodName();

            driver.get(Config.baseURL);//Open the application
            String signInUrl = driver.getCurrentUrl();//Get the url of sign in page

            //Expected - Sign in url should contain login
            if(!signInUrl.contains("/#login")){
                Assert.fail("Sign In Url is not displayed as expected");
            }
            loginPage.getButton_signUpItsFree().click();//Click on sign up its free button
            Thread.sleep(2000);

            String signUpUrl = driver.getCurrentUrl();//Get the url of sign up page

            //Expected - Sign up url should contain '/#register/close/teacher'
            if(!signUpUrl.contains("/#register/close/teacher")){
                Assert.fail("Sign Up Url is not displayed as expected");
            }

            //Expected - Sign in and sign up url should be different
            if(signInUrl.equals(signUpUrl)){
                Assert.fail("Both 'sign in' and 'sign up' url is same");
            }

            teacherSignUpPage.getButton_signUp().click();//Click on sign up as teacher button without entering any field

            //Expected - Error message should appear on each field
            Assert.assertEquals(teacherSignUpPage.getErrorMessage_name().getText(),"Please provide your full name.","Error message is not appearing on name as expected");
            Assert.assertEquals(teacherSignUpPage.getErrorMessage_email().getText(),"Please provide a valid Email id.","Error message is not appearing on email as expected");
            Assert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Please provide a valid password.","Error message is not appearing on password as expected");

            studentSignUpPage.getTextBox_name().sendKeys(currentMethodName+appendChar);//enter first name
            teacherSignUpPage.getButton_signUp().click();//Click on sign up as teacher button

            //Error message should appear only on email and password field
            Assert.assertEquals(teacherSignUpPage.getErrorMessage_email().getText(),"Please provide a valid Email id.","Error message is not appearing on email as expected");
            Assert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Please provide a valid password.","Error message is not appearing on password as expected");

            //Error message should not appear on name field
            boolean errorOnNameFound = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_name());
            Assert.assertEquals(errorOnNameFound,false,"Error message is displayed on name even after entering the value");

            studentSignUpPage.getTextBox_email().sendKeys(currentMethodName+appendChar);//Enter email id in wrong format
            teacherSignUpPage.getButton_signUp().click();//Click on sign up as teacher button

            //Error message should appear again only on email and password field
            Assert.assertEquals(teacherSignUpPage.getErrorMessage_email().getText(),"Please provide a valid Email id.","Error message is not appearing on email as expected");
            Assert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Please provide a valid password.","Error message is not appearing on password as expected");

            //Error message should not appear on name field
            boolean errorOnNameFound1 = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_name());
            Assert.assertEquals(errorOnNameFound1,false,"Error message is displayed on name even after entering the value");

            studentSignUpPage.getTextBox_email().clear();
            studentSignUpPage.getTextBox_email().sendKeys(currentMethodName+appendChar+"@snapwiz.com");//Enter email id in correct format
            teacherSignUpPage.getButton_signUp().click();//Click on sign up as teacher button

            //Error message should appear only on password field
            Assert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Please provide a valid password.","Error message is not appearing on password as expected");

            //Error message should not appear on name and email field
            boolean errorOnNameFound2 = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_name());
            Assert.assertEquals(errorOnNameFound2,false,"Error message is displayed on name even after entering the value");

            boolean errorOnEmailFound = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_email());
            Assert.assertEquals(errorOnEmailFound,false,"Error message is displayed on email even after entering the value");

            studentSignUpPage.getTextBox_password().sendKeys("sna");//Enter password(only 3 characters)
            teacherSignUpPage.getButton_signUp().click();//click on sign up as teacher

            //Error message should appear only on password field
            Assert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Password is too short - must be at least 5 characters.","Error message is not displayed on password field as expected");

            studentSignUpPage.getTextBox_password().clear();
            studentSignUpPage.getTextBox_password().sendKeys("snapwiz");//Enter valid password
            teacherSignUpPage.getButton_signUp().click();//click on sign up as teacher

            //Error message should appear only on retype password field
            Assert.assertEquals(teacherSignUpPage.getErrorMessage_retypePassword().getText(),"Please retype the password.","Error message is not displayed on retype password field as expected");

            //Error message should not appear on any other field except retype password field
            boolean errorOnNameFound3 = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_name());
            Assert.assertEquals(errorOnNameFound3,false,"Error message is displayed on name even after entering the value");

            boolean errorOnEmailFound1 = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_email());
            Assert.assertEquals(errorOnEmailFound1,false,"Error message is displayed on email even after entering the value");

            boolean errorOnPassword = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_email());
            Assert.assertEquals(errorOnPassword,false,"Error message is displayed on password even after entering the value");

            //Expected - Teacher SignUp through app creating new school
            new com.snapwiz.selenium.tests.staf.edulastic.apphelpers.SignUp().teacher(appendChar,1);//Register teacher1
            schoolPage.getButton_add().click();//Click on add button
            schoolPage.getButton_addSchool().click();//Click on 'Add school button'

            //Expected - Error should appear if school name and zip is not entered
            Assert.assertEquals(schoolPage.getError_msg_schoolName().getText(),"Please provide a valid school name.","Error message is not appearing correctly if school is not entered");
            Assert.assertEquals(schoolPage.getError_msg_zip().getText(),"Please provide a valid zip code.","Error message is not displayed correctly if zip is not entered");
            schoolPage.getEditbox_schoolName().click();
            schoolPage.getEditbox_schoolName().clear();
            driver.switchTo().activeElement().sendKeys("School");//Enter the school name

            String str = new RandomString().randomstring(6);
            schoolPage.editBox_zipCode.sendKeys(str);//Enter zip code as string
            schoolPage.getButton_addSchool().click();//Click on 'Add school button'

            //Expected - Error should appear on zip code
            Assert.assertEquals(schoolPage.getError_msg_zip().getText(),"Please provide a valid zip code.","Error message is not appearing correctly if zip is not entered");

            //Error message should not appear on school name
            boolean errorOnSchoolFound = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_name());
            Assert.assertEquals(errorOnSchoolFound,false,"Error message is displayed on school even after entering the value");

            schoolPage.getClose().click();//Click on close button
            zipcode=new School().createWithOnlyName(appendChar,1);//Create a school
            System.out.println("zipCode "+zipcode);

            //By default class name should appear on class page
            Assert.assertEquals(classPage.nameOfClass.getAttribute("placeholder"),currentMethodName+"ins"+appendChar+"'s "+"class","By default class name is not displayed as expected");

            classPage.finishButton.click();//Click on finish button

            //Error message should appear on all the field except class name
            Assert.assertEquals(classPage.errorMsg_subject.getText(),"Subject is not selected","Error message is not displayed as expected on subject");

            new Classes().createClass(appendChar, 1);//create class

            new Navigator().navigateTo("assignment");//Navigate to assignment

            //Expected - Assignment should not appear
            Assert.assertTrue(assignments.message_noAssignment.getText().contains("No Assignments Available For Your Class"), "Assignments are appearing for newly signed up instructor");

            new Navigator().navigateTo("myAssessments");//Navigate to my assessments

            //No assessments should appear under published and draft
            Assert.assertTrue(assignments.message_noAssignment.getText().contains("Assessment not available"),"Assessments are appearing for newly signed up instructor under published");

            myAssessments.draft.click();//Click on draft

            Assert.assertTrue(assignments.message_noAssignment.getText().contains("Drafts not available"),"Assessments are appearing under draft for newly signed up instructor");

            new Navigator().navigateTo("manageclass");//Navigate to manage class

            //Expected - Verify class name as created
            Assert.assertEquals(manageClass.getClassNameStudent().get(0).getAttribute("title"),currentMethodName+"class"+appendChar,"class name is not same as created");

            //Expected - Verify active and archived tab
            Assert.assertEquals(manageClass.tab_activeAndArchived.get(0).getText(),"Active (2)","Active tab is not displayed as expected");
            Assert.assertEquals(manageClass.tab_activeAndArchived.get(1).getText(),"Archived (0)","Archive tab is not displayed as expected");

            String url = manageClass.getUrl().getText();
            String classCodeInstructor = manageClass.getclassCode().get(0).getText();
            new Navigator().logout();//log out

            driver.get(String.valueOf(url));//open the url copied in manage class

            //Expected - Class code field should be disabled
            Assert.assertEquals(studentSignUpPage.getTextBox_classCode().getAttribute("disabled"),"true","Class code field is not disabled as expected");

            studentSignUpPage.getButton_signUp().click();//Click on sign up as student button

            //Expected - Error message should appear on each field
            Assert.assertEquals(teacherSignUpPage.getErrorMessage_name().getText(),"Please provide your full name.","Error message is not appearing on name as expected");
            Assert.assertEquals(teacherSignUpPage.getErrorMessage_email().getText(),"Please provide valid Username or Email id.","Error message is not appearing on email as expected");
            Assert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Please provide a valid password.","Error message is not appearing on password as expected");

            studentSignUpPage.getTextBox_name().sendKeys(currentMethodName+appendChar);//enter first name
            studentSignUpPage.getButton_signUp().click();//Click on sign up as student button

            //Error message should appear only on email and password field
            Assert.assertEquals(teacherSignUpPage.getErrorMessage_email().getText(),"Please provide valid Username or Email id.","Error message is not appearing on email as expected");
            Assert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Please provide a valid password.","Error message is not appearing on password as expected");

            //Error message should not appear on name field
            boolean errorOnNameFoundStudentSide = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_name());
            Assert.assertEquals(errorOnNameFoundStudentSide,false,"Error message is displayed on name even after entering the value");

            studentSignUpPage.getTextBox_email().sendKeys(currentMethodName+appendChar);//Enter email id in wrong format
            studentSignUpPage.getButton_signUp().click();//Click on sign up as student button

            //Error message should appear only on password field
            Assert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Please provide a valid password.","Error message is not appearing on password as expected");

            //Error message should not appear on name and email field
            boolean errorOnNameFound1Studentside = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_name());
            Assert.assertEquals(errorOnNameFound1Studentside,false,"Error message is displayed on name even after entering the value");

            boolean errorOnEmailFoundStudentside = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_email());
            Assert.assertEquals(errorOnEmailFoundStudentside,false,"Error message is displayed on email even after entering the value");

            studentSignUpPage.getTextBox_email().clear();
            studentSignUpPage.getTextBox_email().sendKeys(currentMethodName+appendChar+"@snapwiz.com");//Enter email id in correct format
            studentSignUpPage.getButton_signUp().click();//Click on sign up as student button

            //Error message should appear only on password field
            Assert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Please provide a valid password.","Error message is not appearing on password as expected");

            //Error message should not appear on name and email field
            boolean errorOnNameFound2StudentSide = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_name());
            Assert.assertEquals(errorOnNameFound2StudentSide,false,"Error message is displayed on name even after entering the value");

            boolean errorOnEmailFoundStudentSide = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_email());
            Assert.assertEquals(errorOnEmailFoundStudentSide,false,"Error message is displayed on email even after entering the value");

            studentSignUpPage.getTextBox_password().sendKeys("sna");//Enter password(only 3 characters)
            studentSignUpPage.getButton_signUp().click();//Click on sign up as student button

            //Error message should appear only on password field
            Assert.assertEquals(teacherSignUpPage.getErrorMessage_password().getText(),"Password is too short - must be at least 5 characters.","Error message is not displayed on password field as expected");

            studentSignUpPage.getTextBox_password().clear();
            studentSignUpPage.getTextBox_password().sendKeys("snapwiz");//Enter valid password
            studentSignUpPage.getButton_signUp().click();//Click on sign up as student button

            //Error message should appear only on retype password field
            Assert.assertEquals(teacherSignUpPage.getErrorMessage_retypePassword().getText(),"Please retype the password.","Error message is not displayed on retype password field as expected");

            //Error message should not appear on any other field except retype password field
            boolean errorOnNameFound3StudentSide = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_name());
            Assert.assertEquals(errorOnNameFound3StudentSide,false,"Error message is displayed on name even after entering the value");

            boolean errorOnEmailFound1StudentSide = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_email());
            Assert.assertEquals(errorOnEmailFound1StudentSide,false,"Error message is displayed on email even after entering the value");

            boolean errorOnPasswordStudentSide = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_email());
            Assert.assertEquals(errorOnPasswordStudentSide,false,"Error message is displayed on password even after entering the value");

           //Student sign up using url
            new com.snapwiz.selenium.tests.staf.edulastic.apphelpers.SignUp().studetnSignUpUsingUrl(1,url,appendChar);

            new Navigator().navigateTo("assignment");//Navigate to assignment page

            //Assignment should not appear
            Assert.assertTrue(assignments.message_noAssignment.getText().contains("No Assignments Available For You"),"Assignments are appearing");

            new Navigator().navigateTo("manageclass");//Navigate to amnage class

            //Expected - Verify active and archived tab
            Assert.assertEquals(manageClass.tab_activeAndArchived.get(0).getText(),"Active (1)","Active tab is not displayed as expected");
            Assert.assertEquals(manageClass.tab_activeAndArchived.get(1).getText(),"Archived (0)","Archive tab is not displayed as expected");

            //Expected - Verify class name of student and class code
            Assert.assertEquals(manageClass.getClassNameStudent().get(0).getAttribute("title"),currentMethodName+"class"+appendChar,"Student class name is not correct");

            String classCodeStudent = manageClass.getclassCode().get(0).getText();
            if(!classCodeInstructor.equals(classCodeStudent))
            {
                Assert.fail("Class code in not correct in student side");
            }

        }catch (Exception e){
            Assert.fail("Exception in testcase 'teacherAndStudentSignUpNewSchool' in class 'SignUp'",e);

        }

    }


    @Test(priority = 2, enabled = false)
    public void teacherAndStudentSignUpExistingSchool(){
        WebDriver driver=Driver.getWebDriver();
        try{

            String appendChar = new RandomString().randominteger(3);

            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            TeacherSignUpPage teacherSignUpPage = PageFactory.initElements(driver,TeacherSignUpPage.class);
            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver,StudentSignUpPage.class);
            ClassPage classPage = PageFactory.initElements(driver,ClassPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            MyAssessments myAssessments = PageFactory.initElements(driver,MyAssessments.class);
            ManageClass manageClass = PageFactory.initElements(driver,ManageClass.class);

            String currentMethodName = new Exception().getStackTrace()[0].getMethodName();

            //Expected - Teacher SignUp through app using existing school
            new com.snapwiz.selenium.tests.staf.edulastic.apphelpers.SignUp().teacher(appendChar,1);//Register teacher1

            new School().enterAndSelectSchool(zipcode,1,false);
            schoolPage.getContinueButton().click();//Click on continue button

            //By default class name should appear on class page
            Assert.assertEquals(classPage.nameOfClass.getAttribute("placeholder"),currentMethodName+"ins"+appendChar+"'s "+"class","By default class name is not displayed as expected");

            classPage.finishButton.click();//Click on finish button

            //Error message should appear on all the field except class name
            Assert.assertEquals(classPage.errorMsg_standard.getText(),"Subject Area is not selected","Error message is not displayed as expected on subject area");
            Assert.assertEquals(classPage.errorMsg_subject.getText(),"Subject is not selected","Error message is not displayed as expected on subject");
            Assert.assertEquals(classPage.errorMsg_subjectGrade.getText(),"Grade is not selected","Error message is not displayed as expected on subject grade");

            new Classes().createClass(appendChar, 1);//create class

            new Navigator().navigateTo("assignment");//Navigate to assignment

            //Expected - Assignment should not appear
            Assert.assertTrue(assignments.message_noAssignment.getText().contains("No Assignments Available For Your Class"), "Assignments are appearing for newly signed up instructor");

            new Navigator().navigateTo("myAssessments");//Navigate to my assessments

            //No assessments should appear under published and draft
            Assert.assertTrue(assignments.message_noAssignment.getText().contains("Assessment not available"),"Assessments are appearing for newly signed up instructor under published");

            myAssessments.draft.click();//Click on draft

            Assert.assertTrue(assignments.message_noAssignment.getText().contains("Drafts not available"),"Assessments are appearing under draft for newly signed up instructor");

            new Navigator().navigateTo("manageclass");//Navigate to manage class

            //Expected - Verify class name as created
            Assert.assertEquals(manageClass.getClassNameStudent().get(0).getAttribute("title"),currentMethodName+"class"+appendChar,"class name is not same as created");

            //Expected - Verify active and archived tab
            Assert.assertEquals(manageClass.tab_activeAndArchived.get(0).getText(),"Active (2)","Active tab is not displayed as expected");
            Assert.assertEquals(manageClass.tab_activeAndArchived.get(1).getText(),"Archived (0)","Archive tab is not displayed as expected");

            String url = manageClass.getUrl().getText();
            String classCodeInstructor = manageClass.getclassCode().get(0).getText();
            new Navigator().logout();//log out

            driver.get(String.valueOf(url));//open the url copied in manage class

            //Expected - Class code field should be disabled
            Assert.assertEquals(studentSignUpPage.getTextBox_classCode().getAttribute("disabled"),"true","Class code field is not disabled as expected");

            //Student sign up using url
            new com.snapwiz.selenium.tests.staf.edulastic.apphelpers.SignUp().studetnSignUpUsingUrl(1,url,appendChar);

            new Navigator().navigateTo("assignment");//Navigate to assignment page

            //Assignment should not appear
            Assert.assertTrue(assignments.message_noAssignment.getText().contains("No Assignments Available For You"),"Assignments are appearing");

            new Navigator().navigateTo("manageclass");//Navigate to amnage class

            //Expected - Verify active and archived tab
            Assert.assertEquals(manageClass.tab_activeAndArchived.get(0).getText(),"Active (1)","Active tab is not displayed as expected");
            Assert.assertEquals(manageClass.tab_activeAndArchived.get(1).getText(),"Archived (0)","Archive tab is not displayed as expected");

            //Expected - Verify class name of student and class code
            Assert.assertEquals(manageClass.getClassNameStudent().get(0).getAttribute("title"),currentMethodName+"class"+appendChar,"Student class name is not correct");

            String classCodeStudent = manageClass.getclassCode().get(0).getText();
            if(!classCodeInstructor.equals(classCodeStudent))
            {
                Assert.fail("Class code in not correct in student side");
            }

        }catch (Exception e){
            Assert.fail("Exception in testcase 'teacherAndStudentSignUpExistingSchool' in class 'SignUp'",e);

        }

    }
}
