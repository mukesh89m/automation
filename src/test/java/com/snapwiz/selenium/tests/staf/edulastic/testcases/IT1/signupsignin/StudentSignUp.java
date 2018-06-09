package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.signupsignin;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.StudentDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.AdminSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.SignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.StudentSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.TeacherSignUpPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by pragya on 15-07-2015.
 */
public class StudentSignUp extends Driver{

    @Test(priority = 1,enabled = true)
    //Student signUp through app with code
    public void signUpWithCode(){
        try{
            String appendChar = "a";

            new SignUp().teacher(appendChar,1);//Sign up as an instructor
            new School().createWithOnlyName(appendChar,1);//Create a school
            String classCode = new Classes().createClass(appendChar,1);//Create a class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,1);//Sign up as a student using class code
            new Navigator().logout();//log out

        }catch (Exception e){
            Assert.fail("Exception in testcase 'signUpWithCode' in class 'StudentSignUp'",e);
        }
    }

    @Test(priority = 2,enabled = true)
    //Student signUp through app with url
    public void signUpWithUrl(){
        try{
            String appendCharacterBuild=System.getProperty("UCHAR");
            String appendChar = "a";
            if (appendCharacterBuild!=null)
                appendChar=appendChar+appendCharacterBuild;
            ManageClass manageClass = PageFactory.initElements(driver, ManageClass.class);
            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver, StudentSignUpPage.class);
            StudentDashboard studentDashboard = PageFactory.initElements(driver,StudentDashboard.class);

            new SignUp().teacher(appendChar,1);//Sign up as an instructor
            new School().createWithOnlyName(appendChar,1);//Create a school
            new Classes().createClass(appendChar,1);//Create a class
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            String url = manageClass.getUrl().getAttribute("title");//Get the url
            new Navigator().logout();//log out

            driver.get(url);//open the url copied from manage class
            String classCodeFieldDisabled = studentSignUpPage.getTextBox_classCode().getAttribute("disabled");

            //Expected - Class code should be disabled by default
            Assert.assertEquals(classCodeFieldDisabled,"true","Class code field is not disabled");

            studentSignUpPage.getTextBox_name().sendKeys("signUpWithUrl"+appendChar+"");//Enter the student name
            studentSignUpPage.getTextBox_email().sendKeys("signUpWithUrlst"+appendChar+""+"@snapwiz.com");//Enter the student email
            studentSignUpPage.getTextBox_password().sendKeys("snapwiz");//Enter the password
            studentSignUpPage.getButton_signUp().click();//Click on sign up button

            //Expected - Error message should be displayed
            Assert.assertEquals(studentSignUpPage.getErrorMessage_retypePassword().getText(),"Please retype the password.","Error message is not displayed correctly when retype password is not entered");

            studentSignUpPage.getTextBox_retypePassword().sendKeys("snapwiz1");//Enter incorrect retype password
            studentSignUpPage.getButton_signUp().click();//Click on sign up button

            //Expected - Error message should be displayed
            Assert.assertEquals(studentSignUpPage.getErrorMessage_retypePassword().getText(),"Retyped password do not match","Error message is not displayed correctly when retype password incorrect");

            studentSignUpPage.getTextBox_retypePassword().clear();
            studentSignUpPage.getTextBox_retypePassword().sendKeys("snapwiz");//Enter correct retype password
            studentSignUpPage.getButton_signUp().click();//Click on sign up button
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.className("getStarted")));

            //Expected - Student should be able to sign up
            Assert.assertEquals(studentDashboard.getGetStarted().getText(),"Get Started","Student is not landed on dashboard page");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'signUpWithUrl' in class 'StudentSignUp'",e);

        }
    }

    @Test(priority = 3,enabled = true)
    //Error message should appear if entered invalid class code
    public void signUpWithInvalidCode(){
        try {
            String appendChar = "a";
            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver, StudentSignUpPage.class);
            LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
            SignUpPage signUpPage = PageFactory.initElements(driver,SignUpPage.class);

            new SignUp().teacher(appendChar, 1);//Sign up as an instructor
            new School().createWithOnlyName(appendChar, 1);//Create a school
            new Classes().createClass(appendChar, 1);//Create a class
            new Navigator().logout();//log out

            driver.get(Config.baseURL);
            loginPage.getButton_signUpItsFree().click();//Click on sign up its free button
            signUpPage.getTab_student().click();//Click on student tab
            studentSignUpPage.getTextBox_classCode().click();
            driver.switchTo().activeElement().sendKeys("1457");//Enter invalid class code
            studentSignUpPage.getTextBox_name().sendKeys("Student");//Enter the student name
            studentSignUpPage.getTextBox_email().sendKeys("signUpWithInvalidCodest@snapwiz.com"+appendChar+"");//Enter the student email
            studentSignUpPage.getTextBox_password().sendKeys("snapwiz");//Enter the password
            studentSignUpPage.getTextBox_retypePassword().sendKeys("snapwiz");//Enter retype password
            studentSignUpPage.getButton_signUp().click();//Click on sign up button

            //Expected - Error message should appear on class code
            Assert.assertEquals(studentSignUpPage.getErrorMessage_classCode().getText(),"Please provide a valid class code.","Error message is not displayed as expected");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'signUpWithInvalidCode' in class 'StudentSignUp'",e);

        }
    }

    @Test(priority = 4,enabled = true)
    public void existingStudentSignUp(){
        try{
            String appendChar = "a";
            AdminSignUpPage StudentSignUpPage = PageFactory.initElements(driver,AdminSignUpPage.class);
            LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
            TeacherSignUpPage teacherSignUpPage = PageFactory.initElements(driver,TeacherSignUpPage.class);

            new SignUp().teacher(appendChar, 1);//Sign up as an instructor
            new School().createWithOnlyName(appendChar, 1);//Create a school
            String classCode =  new Classes().createClass(appendChar, 1);//Create a class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,1);//Sign up as a student
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,1);//Again sign up with the already registered student

            //Expected - Error message should appear on email field
            Assert.assertEquals(StudentSignUpPage.getErrorMessage_email().getText().replaceAll("[\\t\\n\\r]"," "),"Username/Email already exists (in your district). Please sign in to your account.","Error message is not displayed on email as expected");
            Thread.sleep(1000);
            teacherSignUpPage.getLink_signIn_errorMsg().click();//Click on sign in link on email error message

            //Expected - User should be navigated to sign in page
            Assert.assertEquals(loginPage.getSignIntoYourAccount().getText(),"Sign into your Account","User is not navigated to sign in page");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'existingStudentSignUp' in class 'StudentSignUp'",e);

        }
    }

    @Test(priority = 5,enabled = true)
    public void studentSignUpErrorMessage(){
        try{

            String appendChar = "a";

            LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
            AdminSignUpPage adminSignUpPage = PageFactory.initElements(driver,AdminSignUpPage.class);
            SignUpPage signUpPage = PageFactory.initElements(driver,SignUpPage.class);
            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver,StudentSignUpPage.class);

            driver.get(Config.baseURL);
            loginPage.getButton_signUpItsFree().click();//Click on sign up its free button
            signUpPage.getTab_student().click();//Click on student tab
            studentSignUpPage.getButton_signUp().click();//Click on sign up button

            //Expected - Error message should appear on each field
            Assert.assertEquals(studentSignUpPage.getErrorMessage_classCode().getText(),"Please provide a valid class code.","Error message is not dispalyed on class code field as expected");
            Assert.assertEquals(adminSignUpPage.getErrorMessage_name().getText(),"Please provide your full name.","Error message is not appearing on name as expected");
            Assert.assertEquals(adminSignUpPage.getErrorMessage_email().getText(),"Please provide valid Username or Email id.","Error message is not appearing on email as expected");
            Assert.assertEquals(adminSignUpPage.getErrorMessage_password().getText(),"Please provide a valid password.","Error message is not appearing on password as expected");

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String studentEmail = methodName+"admin"+appendChar+"@snapwiz.com";

            studentSignUpPage.getTextBox_classCode().clear();
            driver.switchTo().activeElement().sendKeys("14563");//Enter any class code
            studentSignUpPage.getTextBox_name().clear();
            driver.switchTo().activeElement().sendKeys("studentSignUpErrorMessage"+appendChar+"");//Enter student name
            studentSignUpPage.getTextBox_email().clear();
            driver.switchTo().activeElement().sendKeys(studentEmail);//Enter student email
            studentSignUpPage.getTextBox_password().clear();
            driver.switchTo().activeElement().sendKeys("snapwiz");//Enter password

            studentSignUpPage.getButton_signUp().click();//Click on sign up page

            //Expected - Error should appear on retype password field
            Assert.assertEquals(adminSignUpPage.getErrorMessage_retypePassword().getText(),"Please retype the password.","Error message is not displayed on retype password as expected");

            studentSignUpPage.getTextBox_retypePassword().clear();
            studentSignUpPage.getTextBox_retypePassword().sendKeys("snapwiz1");//Enter the wrong retype password
            studentSignUpPage.getButton_signUp().click();//Click on sign up button

            //Expected - Error should be displayed on retype password field
            Assert.assertEquals(adminSignUpPage.getErrorMessage_retypePassword().getText(),"Retyped password do not match","Error message is not displayed as expected");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'studentSignUpErrorMessage' in class 'StudentSignUp'",e);

        }
    }

    @Test(priority = 6,enabled = true)
    public void existingStudentSignUpWithCode(){
        try{
            String appendChar1 = "a";
            String appendChar2 = "b";
            String appendCharacterBuild1="";
            ManageClass manageClass = PageFactory.initElements(driver,ManageClass.class);
            String appendCharacterBuild=System.getProperty("UCHAR");
            if (appendCharacterBuild!=null) {
                appendCharacterBuild1 = appendChar1 + appendCharacterBuild;
            }
            else
                appendCharacterBuild1 = appendChar1;

            new SignUp().teacher(appendChar1,11);//Sign up as a teacher 1
            new School().createWithOnlyName(appendChar1,11);//Create a school
            String classCode1 = new Classes().createClass(appendChar1,1);//Create class

            new Navigator().logout();//log out

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String studentName = methodName+"st"+appendCharacterBuild1;
            String studentEmail = methodName+"st"+appendCharacterBuild1+"@snapwiz.com";
            new SignUp().studentDirectRegister(appendChar1,classCode1,11);//Sign up as a student with the class code of teacher 1
            Thread.sleep(2000);
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("navbar-username")));
            new Navigator().logout();//log out

            new SignUp().teacher(appendChar2,11);//Sign up as a teacher 2
            new School().createWithOnlyName(appendChar2,11);//Create a school in the same district of teacher 1
            String classCode2 = new Classes().createClass(appendChar2,11);//Create class
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar1,11);//Login as a student
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            manageClass.getNewClass().click();//Click on new class
            manageClass.classNameWebElement().sendKeys(classCode2);//Enter the class code of other instructor(2nd instructor)
            manageClass.buttonJoinClass().click();//Click on join class
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.className("notification-banner")));

            //Notification message should appear
            Assert.assertEquals(manageClass.notification_msg.getText(),"New class has been added","Message is not displayed after adding student to new class");

            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar2,11);//Login as an instructor 2
            new Navigator().navigateTo("manageclass");//Navigate to manage class

            //Added student should be displayed
            Assert.assertEquals(manageClass.getStudentNames().get(0).getText(),studentName,"Added student name is not displayed as expected");
            Assert.assertEquals(manageClass.students_userNames.get(0).getText(),studentEmail,"Added student email is not displayed as expected");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'existingStudentSignUpWithCode' in class 'StudentSignUp'",e);

        }

    }

}
