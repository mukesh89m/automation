package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.signupsignin;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.forgotpassword.ForgotPasswordPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.AdminSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.SignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.StudentSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.TeacherSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.ExplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by pragya on 17-07-2015.
 */
public class ForgotPasswordForAdmin extends Driver {

    @Test(priority = 1,enabled = true)
    public void forgotPasswordForAdmin(){
       try{
           String appendChar = "a";
           String email = "snaplogicautomationforgotpass@gmail.com";
           String emailPassword = "snapwiz2015";
           String accessURL = "Reset Password";

           StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver,StudentSignUpPage.class);
           TeacherSignUpPage teacherSignUpPage = PageFactory.initElements(driver,TeacherSignUpPage.class);
           LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
           ForgotPasswordPage forgotPasswordPage = PageFactory.initElements(driver,ForgotPasswordPage.class);
           AdminSignUpPage adminSignUpPage = PageFactory.initElements(driver,AdminSignUpPage.class);
           SignUpPage signUpPage = PageFactory.initElements(driver,SignUpPage.class);

           String randNumber=new RandomString().randominteger(4);

           //Execute sql query to remove user from database
           int rst =  DBConnect.st.executeUpdate("update t_user set email = '" + email + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '"+email+"'");

           driver.get(Config.baseURL);
           loginPage.getButton_signUpItsFree().click();//Click on sign up its free button and navigate to teacher tab
           signUpPage.getTab_admin().click();//Click on admin tab
           studentSignUpPage.getTextBox_name().sendKeys("ForgotPasswordAdmin");//Enter name
           studentSignUpPage.getTextBox_email().sendKeys(email);//Enter email id
           studentSignUpPage.getTextBox_password().sendKeys("snapwiz");//Enter password
           studentSignUpPage.getTextBox_retypePassword().sendKeys("snapwiz");//Enter retype password
           adminSignUpPage.getButton_signUp().click();//Click on sign up button
           new School().createWithOnlyName(appendChar,1);//Create school
           new Classes().createClass(appendChar,1);//Create class
           new Navigator().logout();//log out

           driver.get(Config.baseURL);
           loginPage.getLink_forgotPassword().click();//Click on forgot password link
           forgotPasswordPage.textBox_forgotUserNameOrEmail.click();
           driver.switchTo().activeElement().sendKeys("forgotPassword@gmail.com");//Enter invalid email id
           forgotPasswordPage.button_sendLink.click();//Click on send link button
           new ExplicitWait().explicitWaitByCssSelector("div[class='notification-msg forgot-password ibox-content']");

           //Expected - Error message should appear
           Assert.assertEquals(forgotPasswordPage.msg_email.getText(), "The username/email address you have entered is not correct.", "Error message is not displayed as expected");

           forgotPasswordPage.button_tryAgain.click();//Click on try again button
           forgotPasswordPage.textBox_forgotUserNameOrEmail.clear();
           driver.switchTo().activeElement().sendKeys(email);//Enter correct email id
           forgotPasswordPage.button_sendLink.click();//Click on send link button
           new ExplicitWait().explicitWaitByCssSelector("div[class='notification-msg forgot-password ibox-content']");

           //Expected - Message should appear 'You've got mail!'
           Assert.assertEquals(forgotPasswordPage.msg_email.getText().replaceAll("[\\t\\n\\r]"," "),"You've got mail! We sent you an email with instructions on how to reset your password.");

           new Gmail().login(email,emailPassword);//Login to gmail with given username and password
           new Gmail().openEmail(email,accessURL,1);

           String windowHandleBefore = driver.getWindowHandle();
           for(String window : driver.getWindowHandles())
           {
               driver.switchTo().window(window);
           }
           Thread.sleep(5000);
           forgotPasswordPage.textBox_resetNewPassword.sendKeys("snapw");//Reset the password
           Thread.sleep(2000);
           forgotPasswordPage.textBox_resetConfirmPassword.sendKeys("snapw");//Confirm password
           forgotPasswordPage.button_resetPassword.click();//Click on reset password button

           driver.close();//Close the current window
           driver.switchTo().window(windowHandleBefore);//Get the control of main window
           Thread.sleep(3000);
           driver.get(Config.baseURL);
           Thread.sleep(3000);
           driver.get(Config.baseURL);
           Thread.sleep(3000);
           new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("navbar-username")));
           new Navigator().logout();//log out

           driver.get(Config.baseURL);//Navigate to app
           loginPage.getTextBox_username().sendKeys(email);//Enter the email id
           loginPage.getTextBox_login_password().sendKeys("snapwiz");//Enter the old password
           loginPage.getButton_signIn().click();//Click on sign in button
           new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.className("notification-msg")));

           //Expected - Error message should appear
           Assert.assertEquals(loginPage.getErrorMsg().getText(),"Email or Password do not match","Error message is not appearing correctly when the password entered is incorrect");

           loginPage.getTextBox_login_password().clear();
           loginPage.getTextBox_login_password().sendKeys("snapw");//Enter the new reset password
           loginPage.getButton_signIn().click();//Click on sign in button
           Thread.sleep(4000);
           //Expected - User should be able to navigate to application
           String url = driver.getCurrentUrl();
           if(!url.contains("dashboard")){
               Assert.fail("User is not navigated to dashboard");
           }

       }catch (Exception e){
           Assert.fail("Exception in testcase 'forgotPasswordForAdmin' in class 'ForgotPasswordForAdmin'", e);

       }
   }
}
