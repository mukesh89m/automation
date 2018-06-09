package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.signupsignin;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Edulastic.Login;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google.SignIn;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.SignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.StudentSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shashank on 17-07-2015.
 */
public class SignInWithGoogleAdmin extends Driver{
    String baseUrl = "http://idc-dev1.snapwiz.net/";
    String googleUrl = "https://www.google.co.in/";
    String password = "snapwiz2015";
    String email_student = "snaplogicautomationstudent";
    @Test(priority = 1, enabled = true)
    public void signInWithGoogleAdmin(){
        try{
            
            String randNumber=new RandomString().randominteger(4);
            ManageClass manageClass= PageFactory.initElements(driver, ManageClass.class);
            //Execute sql query to remove user from database
            int rst =  DBConnect.st.executeUpdate("update t_user set email = '" + email_student + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '" + email_student + "@gmail.com';");

            String appendChar="149";

            SignIn signIn = PageFactory.initElements(driver, SignIn.class);
            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            /*Steps : 1.Create a google ID
              2.Sign in as a teacher in Edulastic using 'With Google' without sign up*/
            //Expected : Teacher should not be able to login if he has not signed up
            SignUpPage signUpPage=PageFactory.initElements(driver,SignUpPage.class);
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            driver.get(Config.launchURL);
            loginPage.getButton_signUpItsFree().click();
            signUpPage.getTab_admin().click();
            signUpPage.signUpWithGoogle.click();
            Thread.sleep(2000);
            if(driver.findElements(By.id("account-chooser-add-account")).size()!=0){
                driver.findElement(By.id("account-chooser-add-account")).click();
                Thread.sleep(5000);
            }
            signIn.getTextField_email().sendKeys(email_student);// Type Email
            signIn.buttonNext.get(0).click();
            signIn.getTextField_password().sendKeys(password);//Type Password
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            Thread.sleep(5000);
            Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"),"SignUp for admin with google is working");


        }catch(Exception e){
            Assert.fail("Exception in the testcase 'checkEdulasticSignInWithoutSignUpForTeacher' in the class 'WithGoogle'", e);
        }
    }

    public void googleSignIn_Edulastic(String email,String password,String url){
        try{
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            SignIn signIn = PageFactory.initElements(driver, SignIn.class);
            SignUpPage signUpPage=PageFactory.initElements(driver,SignUpPage.class);

            driver.get(url);
            signUpPage.signUpWithGoogle.click();//Click on button 'With Google'
            Thread.sleep(2000);
            if(driver.findElements(By.id("account-chooser-add-account")).size()!=0){
                driver.findElement(By.id("account-chooser-add-account")).click();
                Thread.sleep(5000);
            }
            signIn.getTextField_email().sendKeys(email);// Type Email
            signIn.buttonNext.get(0).click();
            signIn.getTextField_password().sendKeys(password);//Type Password
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            Thread.sleep(5000);
        }catch(Exception e){
            Assert.fail("Exception in the method 'googleSignIn_Edulastic' in the class 'WithGoogle'", e);
        }
    }
}
