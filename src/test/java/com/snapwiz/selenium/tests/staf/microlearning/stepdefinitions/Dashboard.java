package com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions;

import com.snapwiz.selenium.tests.staf.microlearning.apphelper.LogoutHelper;
import com.snapwiz.selenium.tests.staf.microlearning.apphelper.NavigatorHelper;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.SignupPage;
import cucumber.api.java.en.Given;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by root on 8/29/16.
 */
public class Dashboard {
    SignupPage signupPage = PageFactory.initElements(CucumberDriver.getWebDriver(), SignupPage.class);
    @Given("^User is able to navigate to \"([^\"]*)\" page$")
    public void User_is_able_to_navigate_page(String pageName) throws Throwable {
        new NavigatorHelper().navigateTo(pageName);
    }

    @Given("^I am logged out from dashboard$")
    public void I_am_logged_out_from_dashboard() throws Throwable {
        new LogoutHelper().logoutUser();
        if (!signupPage.login_button.isDisplayed()) {
            Assert.fail("User is not logged out");
        }
    }
}
