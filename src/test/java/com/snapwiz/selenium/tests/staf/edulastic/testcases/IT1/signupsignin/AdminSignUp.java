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
 * Created by pragya on 16-07-2015.
 */
public class AdminSignUp extends Driver{

    @Test(priority =1,enabled = true)
    public void adminSignUp(){
        try{
            String appendChar1 = "a";
            String appendChar2 = "b";

            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);

            //Expected - Admin SignUp through app creating new school
            new SignUp().administrator(appendChar1,1);//Sign up as an admin 1
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
           String zipcode= new School().createWithOnlyName(appendChar1,1);//Create a school
            new Classes().createClass(appendChar1, 1);//create class
            new Navigator().logout();//log out

            //Expected - Admin SignUp through app existing school
            new SignUp().administrator(appendChar2, 1);//Sign up an an admin 2
            new School().enterAndSelectSchool(zipcode, 1, false);//Select the same school created by instructor 1
            schoolPage.getContinueButton().click();//Click on continue button
            new Classes().createClass(appendChar2,1);//Create a class
            new Navigator().logout();//Log out

        }catch (Exception e){
            Assert.fail("Exception in testcase 'adminSignUp' in class 'adminSignUp'", e);

        }
    }

    @Test(priority = 2,enabled = true)
    public void adminSignUpErrorMessage(){
        try{
            String appendChar = "a";
            String appendCharacterBuild=System.getProperty("UCHAR");
            if(appendCharacterBuild!=null){
                appendChar= appendChar+appendCharacterBuild;
            }
            LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
            SignUpPage signUpPage = PageFactory.initElements(driver,SignUpPage.class);
            AdminSignUpPage adminSignUpPage = PageFactory.initElements(driver,AdminSignUpPage.class);
            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver, StudentSignUpPage.class);
            TeacherSignUpPage teacherSignUpPage = PageFactory.initElements(driver,TeacherSignUpPage.class);

            driver.get(Config.baseURL);
            loginPage.getButton_signUpItsFree().click();//Click on sign up its free button
            signUpPage.getTab_admin().click();//Click on admin tab
            adminSignUpPage.getButton_signUp().click();//Click on sign up button

            //Expected - Error message should appear on each field
            Assert.assertEquals(adminSignUpPage.getErrorMessage_name().getText(),"Please provide your full name.","Error message is not appearing on name as expected");
            Assert.assertEquals(adminSignUpPage.getErrorMessage_email().getText(),"Please provide a valid Email id.","Error message is not appearing on email as expected");
            Assert.assertEquals(adminSignUpPage.getErrorMessage_password().getText(),"Please provide a valid password.","Error message is not appearing on password as expected");

            studentSignUpPage.getTextBox_name().clear();
            driver.switchTo().activeElement().sendKeys("adminSignUpErrorMessageOnSignUp"+appendChar+"");//Enter the admin name
            studentSignUpPage.getTextBox_email().clear();

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String adminEmail = methodName+"admin"+appendChar+"@snapwiz.com";
            studentSignUpPage.getTextBox_email().sendKeys(adminEmail);//Enter the admin email
            studentSignUpPage.getTextBox_password().sendKeys("snapwiz");//Enter the password
            adminSignUpPage.getButton_signUp().click();//Click on sign up button

            //Expected - Error should appear on retype password field
            Assert.assertEquals(adminSignUpPage.getErrorMessage_retypePassword().getText(),"Please retype the password.","Error message is not displayed on retype password as expected");

            studentSignUpPage.getTextBox_retypePassword().clear();
            studentSignUpPage.getTextBox_retypePassword().sendKeys("snapwiz1");//Enter the wrong retype password
            adminSignUpPage.getButton_signUp().click();//Click on sign up button

            //Expected - Error should be displayed on retype password field
            Assert.assertEquals(adminSignUpPage.getErrorMessage_retypePassword().getText(),"Retyped password do not match","Error message is not displayed as expected");

            studentSignUpPage.getTextBox_retypePassword().clear();
            studentSignUpPage.getTextBox_retypePassword().sendKeys("snapwiz");//Enter the correct retype password
            adminSignUpPage.getButton_signUp().click();//Click on sign up button
            new School().createWithOnlyName(appendChar,1);//Create school
            new Classes().createClass(appendChar,1);//Create class
            new Navigator().logout();

            new SignUp().administrator(appendChar,1);//Again sign up with the existing admin email id
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[starts-with(@class,'as-errorMsg email-message')]")));

            //Expected - Error message should appear on email field
            Assert.assertEquals(adminSignUpPage.getErrorMessage_email().getText().replaceAll("[\\t\\n\\r]"," "),"Email already exists. Please sign in to your account.");

            teacherSignUpPage.getLink_signIn_errorMsg().click();//Click on sign in link on email error message

            //Expected - User should be navigated to sign in page
            Assert.assertEquals(loginPage.getSignIntoYourAccount().getText(),"Sign into your Account","User is not navigated to sign in page");


        }catch (Exception e){
            Assert.fail("Exception in testcase 'adminSignUpErrorMessage' in class 'adminSignUp'", e);

        }
    }
}
