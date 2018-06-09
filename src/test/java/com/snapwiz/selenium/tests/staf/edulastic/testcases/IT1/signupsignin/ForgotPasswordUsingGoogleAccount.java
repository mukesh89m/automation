package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.signupsignin;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Edulastic.Login;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google.SignIn;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.forgotpassword.ForgotPasswordPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.ExplicitWait;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shashank on 21-07-2015.
 */
public class ForgotPasswordUsingGoogleAccount extends Driver{
    
    @Test(priority = 1,enabled = true )
    public void forgotPasswordForTeacherUsingGoogle()
    {
        String email = "snaplogic.automation22";
        String password="snapwiz2015";
        String appendChar="a";

        try
        {
            LoginPage loginPage=PageFactory.initElements(driver,LoginPage.class);
            ForgotPasswordPage forgotPasswordPage=PageFactory.initElements(driver,ForgotPasswordPage.class);
            String randNumber=new RandomString().randominteger(4);
            //Execute sql query to remove user from database
            int rst =  DBConnect.st.executeUpdate("update t_user set email = '" + email + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '" + email + "@gmail.com';");


            SignIn signIn = PageFactory.initElements(driver, SignIn.class);
            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            /*Steps : 1.Create a google ID
              2.Sign in as a teacher in Edulastic using 'With Google' without sign up*/
            //Expected : Teacher should not be able to login if he has not signed up
            Login login = PageFactory.initElements(driver, Login.class);
            new SignUp().googleSignIn_Edulastic(email,password);
            Thread.sleep(5000);
            if(signIn.buttonAccept.size()>0)
                signIn.buttonAccept.get(0).click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(login.getButton_Teacher()));
            login.getButton_Teacher().click();
            new School().createWithOnlyName(appendChar,1);//Create a school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().logout();
            driver.get(Config.baseURL);
            loginPage.getLink_forgotPassword().click();//Click on forgot password link
            forgotPasswordPage.textBox_forgotUserNameOrEmail.click();
            driver.switchTo().activeElement().sendKeys("forgotPassword@gmail.com");//Enter invalid email id
            forgotPasswordPage.button_sendLink.click();//Click on send link button
            new ExplicitWait().explicitWaitByCssSelector("div[class='notification-msg forgot-password ibox-content']");
            Assert.assertEquals(forgotPasswordPage.msg_email.getText(),"The username/email address you have entered is not correct.","Error message not displaying properly");
            forgotPasswordPage.button_tryAgain.click();//Click on try again button
            forgotPasswordPage.textBox_forgotUserNameOrEmail.clear();
            driver.switchTo().activeElement().sendKeys(email+"@gmail.com");//Enter correct email id
            forgotPasswordPage.button_sendLink.click();//Click on send link button
            new ExplicitWait().explicitWaitByCssSelector("div[class='notification-msg forgot-password ibox-content']");
            Assert.assertEquals(forgotPasswordPage.msg_email.getText(),"You are registered as a Google user in Edulastic. Edulastic can't reset this password!","Error message not displaying properly");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in ForgotPasswordUsingGoogleAccount in forgotPasswordForTeacherUsingGoogle method",e);
        }
    }

    @Test(priority = 2,enabled = true )
    public void forgotPasswordForTStudentUsingGoogle()
    {
        String baseUrl = "http://idc-dev1.snapwiz.net/";
        String email = "snaplogic.automation22";
        String password="snapwiz2015";
        String appendChar="a";

        try
        {
            String randNumber=new RandomString().randominteger(4);
            DBConnect.st.executeUpdate("update t_user set email = '" + email + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '" + email + "@gmail.com';");
            LoginPage loginPage=PageFactory.initElements(driver,LoginPage.class);
            ForgotPasswordPage forgotPasswordPage=PageFactory.initElements(driver,ForgotPasswordPage.class);
            String classCode=new SignUp().classCodeFromDB(0);
            //Execute sql query to remove user from database

            new SignUp().googleSignIn_EdulasticWithUrl(email, password, Config.launchURL+"regcd/"+classCode);
            new Navigator().navigateTo("manageclass");
            new Navigator().logout();
            driver.get(Config.baseURL);
            loginPage.getLink_forgotPassword().click();//Click on forgot password link
            forgotPasswordPage.textBox_forgotUserNameOrEmail.click();
            driver.switchTo().activeElement().sendKeys("forgotPassword@gmail.com");//Enter invalid email id
            forgotPasswordPage.button_sendLink.click();//Click on send link button
            new ExplicitWait().explicitWaitByCssSelector("div[class='notification-msg forgot-password ibox-content']");
            Assert.assertEquals(forgotPasswordPage.msg_email.getText(),"The username/email address you have entered is not correct.","Error message not displaying properly");
            forgotPasswordPage.button_tryAgain.click();//Click on try again button
            forgotPasswordPage.textBox_forgotUserNameOrEmail.clear();
            driver.switchTo().activeElement().sendKeys(email+"@gmail.com");//Enter correct email id
            forgotPasswordPage.button_sendLink.click();//Click on send link button
            new ExplicitWait().explicitWaitByCssSelector("div[class='notification-msg forgot-password ibox-content']");
            Assert.assertEquals(forgotPasswordPage.msg_email.getText(),"You are registered as a Google user in Edulastic. Edulastic can't reset this password!","Error message not displaying properly");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in ForgotPasswordUsingGoogleAccount in forgotPasswordForTeacherUsingGoogle method",e);
        }
    }
}
