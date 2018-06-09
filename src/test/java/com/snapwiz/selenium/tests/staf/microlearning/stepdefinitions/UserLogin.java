package com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.microlearning.apphelper.LoginHelper;
import com.snapwiz.selenium.tests.staf.microlearning.apphelper.SignUpHelper;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.SignupPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

import static com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions.SignUp.random;


/**
 * Created by mukesh on 22/8/16.
 */
public class UserLogin {

    SignupPage signup= PageFactory.initElements(CucumberDriver.getWebDriver(),SignupPage.class);

    @Given("^user loggedIn with valid email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public  void user_loggedIn_with_valid_email_and_password(String email, String password) throws Throwable {
        LoginHelper.loginWithCredentials(email, password);
    }

    @When("^click on the Logout button$")
    public void click_on_the_Logout_button() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(signup.username_icon);
        Thread.sleep(5000);
        WebDriverUtil.clickOnElementUsingJavascript(signup.logout_link);
    }

    @Then("^user should be able to LogOut Successfully$")
    public void user_should_be_able_to_LogOut_Successfully() throws Throwable {
        Assert.assertEquals("Sign Up link is not present in the application page","Sign Up",signup.signUp_link.getText().trim());
        Assert.assertEquals("Login link is not present in the application page","Login",signup.login_button.getText().trim());
    }


    @When("^Click on the Login button$")
    public void click_on_the_Login_button() throws Throwable {
     signup.login_button.click();
    }


    @Given("^I am on loggedIn page$")
    public void iAmOnLoggedInPage() throws Throwable {
        LoginHelper.loginWithRegisteredUser();

    }

    @Then("^I should see the Login page$")
    public void iShouldSeeTheLoginPage() throws Throwable {
        Assert.assertEquals("Login to your account header is not present in the login page","Login to your account",signup.login_header.getText().trim());

    }


    @Given("^I am logged in with valid credentials$")
    public void I_am_logged_in_with_valid_credentials() throws Throwable {
        new LoginHelper().loginWithValidCredentials();
        if (!(signup.userName.getText().trim().equals(SignUpHelper.userName))) {
            Assert.fail("User is not registered with the Registered User Name 'UserName" + random + "'");
        }
    }

    @And("^I enters existing email \"([^\"]*)\" and password \"([^\"]*)\" in login page$")
    public void iEntersExistingEmailAndPasswordInLoginPage(String email, String password) throws Throwable {
        signup.email_textbox.sendKeys(email);
        signup.password_textbox.sendKeys(password);
    }

    @And("^I click on the Login button in login page$")
    public void iClickOnTheLoginButtonInLoginPage() throws Throwable {
        signup.logIn.click();
    }

    @Given("^I am on Login page$")
    public void iAmOnLoginPage() throws Throwable {
        WebDriverUtil.launchURL();
        WebDriverUtil.clickOnElementUsingJavascript(signup.login_button);

    }

    @When("^I click on Sign Up button on Login page$")
    public void iClickOnSignUpButtonOnLoginPage() throws Throwable {
       WebDriverUtil.clickOnElementUsingJavascript(signup.signUp_link);
    }

    @When("^I click on Sign Up link text on login page footer$")
    public void iClickOnSignUpLinkTextOnLoginPageFooter() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(signup.footer_signUp_link);

    }

    @When("^I click on Login button on Default Explore page$")
    public void iClickOnLoginButtonOnDefaultExplorePage() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(signup.login_button);

    }

    @When("^I click on Login button on Sign Up page in the header$")
    public void iClickOnLoginButtonOnSignUpPageInTheHeader() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(signup.login_button);

    }

    @When("^I click on Login link text on Sign Up page in the footer$")
    public void iClickOnLoginLinkTextOnSignUpPageInTheFooter() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(signup.footer_login_link);

    }

    @When("^I click on profile thumnail$")
    public void iClickOnProfileThumnail() throws Throwable {
        signup.signUpProfile_thumbnail.click();
    }
}
