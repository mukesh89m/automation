package com.snapwiz.selenium.tests.staf.microlearning.apphelper;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.SignupPage;
import com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions.CucumberDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions.SignUp.random;

/**
 * Created by mukesh on 22/8/16.
 */
public class LoginHelper {

    /**
     * This function will login to application with random email and password
     * @param email,password
     * @author Mukesh
     */
    public static void loginWithCredentials(String email, String password){
        SignupPage signup= PageFactory.initElements(CucumberDriver.getWebDriver(),SignupPage.class);

        try {

            WebDriverUtil.launchURL();
            signup.login_button.click();
            signup.email_textbox.sendKeys(random+email);
            signup.password_textbox.sendKeys(password+random);
            Thread.sleep(3000);
            WebDriverUtil.clickOnElementUsingJavascript(signup.logIn);
            WebDriverUtil.waitTillVisibilityOfElement(signup.username_icon,60);
        } catch (Throwable e) {
            Assert.fail("Exception in method loginWithValidCredentials of class Login",e);
        }
    }

    /**
     * This function will login to application with registered email and password
     * @author Mukesh
     */
    public static void loginWithRegisteredUser(){
        SignupPage signup= PageFactory.initElements(CucumberDriver.getWebDriver(),SignupPage.class);

        try {
            WebDriverUtil.launchURL();
            signup.login_button.click();
            signup.email_textbox.sendKeys("ml@snapwiz.com");
            signup.password_textbox.sendKeys("snapwiz");
            Thread.sleep(3000);
            WebDriverUtil.clickOnElementUsingJavascript(signup.logIn);
            WebDriverUtil.waitTillVisibilityOfElement(signup.username_icon,60);
        } catch (Throwable e) {
            Assert.fail("Exception in method loginWithRegisteredUser of class Login"+e);
        }
    }

    public static void loginWithValidCredentials(){
        SignupPage signup= PageFactory.initElements(CucumberDriver.getWebDriver(),SignupPage.class);
        try {
            WebDriverUtil.launchURL();
            signup.login_button.click();
            signup.email_textbox.sendKeys(SignUpHelper.email);
            signup.password_textbox.sendKeys(SignUpHelper.password);
            Thread.sleep(3000);
            WebDriverUtil.clickOnElementUsingJavascript(signup.logIn);
            WebDriverUtil.waitTillVisibilityOfElement(signup.username_icon,60);
        } catch (Throwable e) {
            Assert.fail("Exception in method loginWithValidCredentials of class Login",e);
        }
    }

}
