package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.signupsignin;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Classes;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Navigator;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.School;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.SignUp;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.AdminSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.TeacherSignUpPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by pragya on 15-07-2015.
 */
public class TeacherSignUp extends Driver{

    @Test(priority = 1, enabled = true)
    public void teacherSignUp(){
        try{

            String appendChar1 = "a4";
            String appendChar2 = "b4";

            LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);

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
            if(signInUrl==signUpUrl){
                Assert.fail("Both 'sign in' and 'sign up' url is same");
            }

            //Expected - Teacher SignUp through app creating new school
            new SignUp().teacher(appendChar1,1);//Sign Up as an instructor 1
            schoolPage.getButton_add().click();//Click on add button
            schoolPage.getButton_addSchool().click();//Click on 'Add school button'

            //Expected - Error should appear if school name is not entered
             Assert.assertEquals(schoolPage.getError_msg_schoolName().getText(),"Please provide a valid school name.","Error message is not appearing correctly if school is not entered");

            schoolPage.getEditbox_schoolName().click();
            schoolPage.getEditbox_schoolName().clear();
            driver.switchTo().activeElement().sendKeys("School");//Enter the school name
            schoolPage.getButton_addSchool().click();//Click on 'Add school button'

            //Expected - Error should appear if the zip code is not entered
            Assert.assertEquals(schoolPage.getError_msg_zip().getText(),"Please provide a valid zip code.","Error message is not appearing correctly if zip is not entered");

            schoolPage.getClose().click();//Click on close button
            String zipcode=new School().createWithOnlyName(appendChar1,1);//Create a school
            new Classes().createClass(appendChar1, 1);//create class
            new Navigator().logout();//log out

            //Expected - Teacher SignUp through app existing school
            new SignUp().teacher(appendChar2,1);//Sign up an an instructor 2
            new School().enterAndSelectSchool(zipcode, 1, false);//Select the same school created by instructor 1
            schoolPage.getContinueButton().click();//Click on continue button
            new Classes().createClass(appendChar2,1);//Create a class
            new Navigator().logout();//Log out

        }catch (Exception e){
            Assert.fail("Exception in testcase 'signUpThroughExistingSchool' in class 'TeacherSignUp'",e);

        }

    }

    @Test(priority = 2,enabled = true)
    public void existingTeacherSignUp(){
        try{
            String appendChar = "a";
            AdminSignUpPage adminSignUpPage = PageFactory.initElements(driver,AdminSignUpPage.class);
            TeacherSignUpPage teacherSignUpPage = PageFactory.initElements(driver,TeacherSignUpPage.class);
            LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);

            new SignUp().teacher(appendChar,1);//Sign up an an instructor
            new School().createWithOnlyName(appendChar,1);//Create a school
            new Classes().createClass(appendChar,1);//Create a class
            new Navigator().logout();//Log out

            new SignUp().teacher(appendChar,1);//Sign up with the same registered teacher

            //Expected - Error message should appear on email field
            Assert.assertEquals(adminSignUpPage.getErrorMessage_email().getText().replaceAll("[\\t\\n\\r]"," "),"Email already exists. Please sign in to your account.","Error message is not displayed on email as expected");

            teacherSignUpPage.getLink_signIn_errorMsg().click();//Click on signin link on email error message

            //Expected - User should be navigated to sign in page
            Assert.assertEquals(loginPage.getSignIntoYourAccount().getText(),"Sign into your Account","User is not navigated to sign in page");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'existingTeacherSignUp' in class 'TeacherSignUp'",e);

        }
    }

    @Test(priority = 3,enabled = true)
    public void teacherSignUpErrorMessage(){
        try{
            LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
            AdminSignUpPage adminSignUpPage = PageFactory.initElements(driver,AdminSignUpPage.class);
            TeacherSignUpPage teacherSignUpPage = PageFactory.initElements(driver,TeacherSignUpPage.class);

            driver.get(Config.baseURL);
            loginPage.getButton_signUpItsFree().click();//Click on sign up its free button
            teacherSignUpPage.getButton_signUp().click();//Click on sign up button

            //Expected - Error message should appear on each field
            Assert.assertEquals(adminSignUpPage.getErrorMessage_name().getText(),"Please provide your full name.","Error message is not appearing on name as expected");
            Assert.assertEquals(adminSignUpPage.getErrorMessage_email().getText(),"Please provide a valid Email id.","Error message is not appearing on email as expected");
            Assert.assertEquals(adminSignUpPage.getErrorMessage_password().getText(),"Please provide a valid password.","Error message is not appearing on password as expected");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'teacherSignUpErrorMessage' in class 'TeacherSignUp'",e);

        }
    }






}
