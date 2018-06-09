package com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.GmailPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by mukesh on 1/9/16.
 */
public class Gmail {

    GmailPage gmailPage= PageFactory.initElements(CucumberDriver.getWebDriver(),GmailPage.class);
    @Then("^I should see the gmail login page$")
    public void iShouldSeeTheGmailLoginPage() throws Throwable {
        WebDriverUtil.switchToNewWindow();
        Assert.assertTrue("When i click on the signup with google link not able to navigate to gmail page",CucumberDriver.getWebDriver().getCurrentUrl().trim().contains("https://accounts.google.com"));
    }

    @When("^I enter email \"([^\"]*)\" in gmail page email field$")
    public void iEnterEmailInGmailPageEmailField(String email) throws Throwable {
       gmailPage.email_textbox.sendKeys(email);
    }

    @And("^I click on Next button$")
    public void iClickOnNextButton() throws Throwable {
      gmailPage.next_button.click();
    }

    @When("^I enter the password \"([^\"]*)\" in gmail page password field$")
    public void iEnterThePasswordInGmailPagePasswordField(String password) throws Throwable {
     gmailPage.password_textbox.sendKeys(password);
    }

    @And("^I click on Sign In button$")
    public void iClickOnSignInButton() throws Throwable {
      gmailPage.signIn_link.click();
        Thread.sleep(4000);
    }

    @Then("^I should see  page micro learning would like to: page$")
    public void iShouldSeePageMicroLearningWouldLikeToPage() throws Throwable {
        String src = gmailPage.microLearning_title.getAttribute("src");
        if (!src.contains("/driplrn/logo-1.png")) {
            Assert.fail("User is not able to navigate micro learning would like to:");
        }
    }

    @When("^I click on the Deny button$")
    public void iClickOnTheDenyButton() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(gmailPage.deny_link);
    }

    @When("^I click on the Allow button$")
    public void iClickOnTheAllowButton() throws Throwable {
       WebDriverUtil.clickOnElementUsingJavascript(gmailPage.access_link);
    }
}
