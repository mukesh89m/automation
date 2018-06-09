package com.snapwiz.selenium.tests.staf.microlearning.apphelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.SignupPage;
import com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions.CucumberDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by root on 8/29/16.
 */
public class SignUpHelper {
    static String random= StringUtil.generateRandomString(5, StringUtil.Mode.ALPHA);
    public static String userName = "UserName"+random;
    public static String email = "Email"+random+"@gmail.com";
    public static String password = "password"+random;
    public static String reTypePassword = "password"+random;
    public void signUpUser(){
       SignupPage signupPage= PageFactory.initElements(CucumberDriver.getWebDriver(), SignupPage.class);
        try{
           signupPage.signUp_link.click();
           signupPage.name_textbox.sendKeys(userName);
           signupPage.email_textbox.sendKeys(email);
           signupPage.password_textbox.sendKeys(password);
           signupPage.re_typePassword_textbox.sendKeys(reTypePassword);
           signupPage.signUp_button.click(); //click on the sign up button in sign up page
       }catch(Exception e){
           Assert.fail("Exception in the test method in the class SignUp in the apphelper 'registerUser'",e);
       }
   }
}
