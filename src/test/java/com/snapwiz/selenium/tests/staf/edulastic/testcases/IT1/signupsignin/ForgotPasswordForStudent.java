package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.signupsignin;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.forgotpassword.ForgotPasswordPage;
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
public class ForgotPasswordForStudent extends Driver {

    @Test(priority = 1,enabled = true)
    public void forgotPasswordForStudentWithEmail(){
        try{
            String email = "snaplogicautomationforgotpass@gmail.com";
            String emailPassword = "snapwiz2015";
            String accessURL = "Reset Password";
            String classCode = new SignUp().classCodeFromDB(0);

            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver,StudentSignUpPage.class);
            ForgotPasswordPage forgotPasswordPage = PageFactory.initElements(driver,ForgotPasswordPage.class);
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

            String randNumber=new RandomString().randominteger(4);

            //Execute sql query to remove user from database
            int rst =  DBConnect.st.executeUpdate("update t_user set email = '" + email + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '" + email + "'");

            driver.get(Config.baseURL);
            new Navigator().studentSignUp(); //Navigate to student registration page
            studentSignUpPage.getTextBox_classCode().sendKeys(classCode);//entering a valid class code
            studentSignUpPage.getTextBox_name().sendKeys("ForgotPasswordTeacher");//Enter name
            studentSignUpPage.getTextBox_email().sendKeys(email);//Enter email id
            studentSignUpPage.getTextBox_password().sendKeys("snapwiz");//Enter password
            studentSignUpPage.getTextBox_retypePassword().sendKeys("snapwiz");//Enter retype password
            studentSignUpPage.getButton_signUp().click();//Click on sign up button
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.className("navbar-username")));
            new Navigator().logout();//log out

            driver.get(Config.baseURL);
            loginPage.getLink_forgotPassword().click();//Click on forgot password link
            forgotPasswordPage.textBox_forgotUserNameOrEmail.click();
            driver.switchTo().activeElement().sendKeys("forgotPassword@gmail.com");//Enter invalid email id
            forgotPasswordPage.button_sendLink.click();//Click on send link button
            new ExplicitWait().explicitWaitByCssSelector("div[class='notification-msg forgot-password ibox-content']");

            //Expected - Error message should appear
            Assert.assertEquals(forgotPasswordPage.msg_email.getText(),"The username/email address you have entered is not correct.","Error message is not displayed as expected");

            forgotPasswordPage.button_tryAgain.click();//Click on try again button
            forgotPasswordPage.textBox_forgotUserNameOrEmail.clear();
            driver.switchTo().activeElement().sendKeys(email);//Enter correct email id
            forgotPasswordPage.button_sendLink.click();//Click on send link button
            new ExplicitWait().explicitWaitByCssSelector("div[class='notification-msg forgot-password ibox-content']");

            //Expected - Message should appear 'You've got mail!'
            Assert.assertEquals(forgotPasswordPage.msg_email.getText().replaceAll("[\\t\\n\\r]"," "),"You've got mail! We sent you an email with instructions on how to reset your password.");

            Thread.sleep(60000);
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
            driver.get(Config.baseURL);
            Thread.sleep(3000);
            driver.get(Config.baseURL);
            Thread.sleep(3000);
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.className("navbar-username")));
            new Navigator().logout();//log out

            driver.get(Config.baseURL);//Navigate to app
            loginPage.getTextBox_username().sendKeys(email);//Enter the email id
            loginPage.getTextBox_login_password().sendKeys("snapwiz");//Enter the old password
            loginPage.getButton_signIn().click();//Click on sign in button

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
            Assert.fail("Exception in testcase 'forgotPasswordForStudentWithEmail' in class ForgotPasswordForStudent", e);

        }
    }

    @Test(priority = 2,enabled = true)
    public void forgotPasswordForStudentWithUserName(){
        try{
            String appendCharacterBuild=System.getProperty("UCHAR");
            String appendChar = "a";
            if (appendCharacterBuild!=null){
                appendChar = appendChar+appendCharacterBuild;
            }else {
                appendChar = appendChar;
            }
            String emailPassword = "snapwiz2015";
            String accessURL = "here";
            String studentName = "ForgotPasswordStudent"+appendChar;
            String email = "snaplogicautomationforgotpass@gmail.com";
            StudentSignUpPage studentSignUpPage =PageFactory.initElements(driver,StudentSignUpPage.class);
            LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
            ForgotPasswordPage forgotPasswordPage = PageFactory.initElements(driver,ForgotPasswordPage.class);
            TeacherSignUpPage teacherSignUpPage = PageFactory.initElements(driver,TeacherSignUpPage.class);

            String randNumber=new RandomString().randominteger(4);

            //Execute sql query to remove user from database
            int rst =  DBConnect.st.executeUpdate("update t_user set email = '" + email + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '" + email + "'");

            driver.get(Config.baseURL);//Navigate to app
            loginPage.getButton_signUpItsFree().click();//Click on sign up its free button
            studentSignUpPage.getTextBox_name().sendKeys("ForgotPasswordForStudentWithUserName"+appendChar);//Enter the instructor name
            studentSignUpPage.getTextBox_email().sendKeys(email);//Enter the email id
            studentSignUpPage.getTextBox_password().sendKeys("snapwiz");
            studentSignUpPage.getTextBox_retypePassword().sendKeys("snapwiz");//Retype the password
            teacherSignUpPage.getButton_signUp().click();//Click on sign up page
            new School().createWithOnlyName(appendChar,1);//Create a school
            String classCode = new Classes().createClass(appendChar,1);//Create a class
            new Navigator().logout();//log out*/

            new Navigator().studentSignUp();//Navigate to student sign up page
            studentSignUpPage.getTextBox_classCode().sendKeys(classCode);//Enter the class code
            studentSignUpPage.getTextBox_name().sendKeys(studentName);//Enter the student name
            studentSignUpPage.getTextBox_email().sendKeys(studentName);//Enter the student username
            studentSignUpPage.getTextBox_password().sendKeys("snapwiz");//Enter the password
            studentSignUpPage.getTextBox_retypePassword().sendKeys("snapwiz");//Retype the password
            studentSignUpPage.getButton_signUp().click();//Register the student
            new Navigator().logout();//log out

            driver.get(Config.baseURL);//Navigate to app
            loginPage.getLink_forgotPassword().click();//Click on forgot password link

            forgotPasswordPage.textBox_forgotUserNameOrEmail.sendKeys(studentName+randNumber);//Enter the wrong name of student
            forgotPasswordPage.button_sendLink.click();//Click on send link button
            new ExplicitWait().explicitWaitByCssSelector("div[class='notification-msg forgot-password ibox-content']");

            //Expected - Error message should appear
            Assert.assertEquals(forgotPasswordPage.msg_email.getText(),"The username/email address you have entered is not correct.","Error message is not displayed as expected");

            forgotPasswordPage.button_tryAgain.click();//Click on try again button
            forgotPasswordPage.textBox_forgotUserNameOrEmail.clear();
            driver.switchTo().activeElement().sendKeys(studentName);//Enter correct name of student
            forgotPasswordPage.button_sendLink.click();//Click on send link button
            new ExplicitWait().explicitWaitByCssSelector("div[class='notification-msg forgot-password ibox-content']");

            //Expected - Message should appear 'You've got mail!'
           Assert.assertEquals(forgotPasswordPage.msg_email.getText().replaceAll("[\\t\\n\\r]"," "),"Mail has been sent to your Teacher! Since you do not have a valid email ID registered with us, we have sent an email to your teacher with instructions on how to reset your password.");

            Thread.sleep(50000);
            new Gmail().login(email,emailPassword);//Login to gmail with given username and password(instructor email id)
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
            driver.get(Config.baseURL);
            Thread.sleep(3000);
            driver.get(Config.baseURL);
            Thread.sleep(3000);
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("navbar-username")));
            new Navigator().logout();//log out

            driver.get(Config.baseURL);//Navigate to app
            loginPage.getTextBox_username().sendKeys(studentName);//Enter the username of student
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
            Assert.fail("Exception in testcase 'forgotPasswordForStudentWithUserName' in class ForgotPasswordForStudent", e);

        }
    }
}
