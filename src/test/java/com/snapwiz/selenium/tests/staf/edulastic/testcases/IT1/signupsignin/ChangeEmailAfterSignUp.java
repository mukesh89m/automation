package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.signupsignin;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.forgotpassword.ForgotPasswordPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.profile.Profile;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.StudentSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.TeacherSignUpPage;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shashank on 21-07-2015.
 */
public class ChangeEmailAfterSignUp extends Driver{

    @Test(priority = 1,enabled = true)
            public void changeEmailAfterSignUp()
    {
        try
        {
            String appendChar = "a2";

            String email = "snaplogicautomationforgotpass@gmail.com";
            String emailPassword = "snapwiz2015";
            String accessURL = "Reset Password";
            Profile profile=PageFactory.initElements(driver,Profile.class);

            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver, StudentSignUpPage.class);
            TeacherSignUpPage teacherSignUpPage = PageFactory.initElements(driver,TeacherSignUpPage.class);
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            ForgotPasswordPage forgotPasswordPage = PageFactory.initElements(driver,ForgotPasswordPage.class);

            String randNumber=new RandomString().randominteger(4);

            //Execute sql query to remove user from database
            int rst =  DBConnect.st.executeUpdate("update t_user set email = '" + email + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '"+email+"'");

            driver.get(Config.baseURL);//Navigate to app
            loginPage.getButton_signUpItsFree().click();//Click on sign up its free button and navigate to teacher tab
            studentSignUpPage.getTextBox_name().sendKeys("ForgotPasswordTeacher");//Enter name
            studentSignUpPage.getTextBox_email().sendKeys(email);//Enter email id
            studentSignUpPage.getTextBox_password().sendKeys("snapwiz");//Enter password
            studentSignUpPage.getTextBox_retypePassword().sendKeys("snapwiz");//Enter retype password
            teacherSignUpPage.getButton_signUp().click();//Click on sign up button
            new School().createWithOnlyName(appendChar, 1);//Create school
            new Classes().createClass(appendChar, 1);//Create class
            new Navigator().logout();//log out
            new SignUp().teacher(appendChar, 1);
            new School().createWithOnlyName(appendChar, 1);//Create a school
            new Classes().createClass(appendChar, 1);
            new Navigator().profile();
            profile.editProfile.click();
            profile.email.clear();
            profile.email.sendKeys(email);
            profile.buttonSave.click();
            profile.buttonSaveList.get(1).click();
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(profile.notificationMessageOnSave));
            Assert.assertEquals(profile.notificationMessageOnSave.getText(),"Username/Email already exists.","Not displaying error message for duplicate email");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in ChangeEmailAfterSignUp in method changeEmailAfterSignUp",e);
        }
    }
    {


    }


}
