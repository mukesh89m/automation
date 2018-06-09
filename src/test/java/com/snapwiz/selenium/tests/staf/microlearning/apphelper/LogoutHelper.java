package com.snapwiz.selenium.tests.staf.microlearning.apphelper;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.SignupPage;
import com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions.CucumberDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by root on 8/31/16.
 */
public class LogoutHelper {
    /**
     * This function will logout the user from dashboard
     * @author Dharaneesha
     */
    public void logoutUser() {
        SignupPage signupPage = PageFactory.initElements(CucumberDriver.getWebDriver(), SignupPage.class);
        try {
            signupPage.userName.click();
            signupPage.logout_link.click();
        } catch (Exception e) {
            Assert.fail("Exception in apphelper 'logoutUser' of class Logout", e);
        }
    }
}