package com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions;

import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.LoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.junit.*;

/**
 * Created by navin on 9/2/2016.
 */
public class Login {

    LoginPage login = PageFactory.initElements(CucumberDriver.getWebDriver(), LoginPage.class);
    public static String random = StringUtil.generateRandomString(5, StringUtil.Mode.ALPHA);


    @And("^I should see 'Sign Up' button in the header$")
    public void I_should_see_Sign_Up_button_in_the_header() throws Throwable {
        Assert.assertEquals("There is a problem with Login page Sign Up Button","Sign Up",login.login_page_sign_up_button.getText() );
    }

    @And("^I should see 'Login to your account' label in sign up page$")
    public void I_should_see_Login_to_your_account_label_in_sign_up_page() throws Throwable {
        Assert.assertEquals("Login page label is not correct","Login to your account",login.login_page_label.getText());
    }

    @And("^I should see 'Email' label in login page$")
    public void I_should_see_Email_label_in_login_page() throws Throwable {
        login.login_email_textbox.sendKeys("naveen@snapwiz.com");
        Assert.assertEquals("Email label is not correct","Email",login.login_fields_labels.get(0).getText());
    }

    @And("^I should see email text box with placeholder text as 'Email' in Login page$")
    public void I_should_see_email_text_box_with_placeholder_text_as_Enter_your_Email_in_Login_page() throws Throwable {
        Assert.assertEquals("Place holder text for email text box is not corect","Email",login.login_email_textbox.getAttribute("placeholder"));
    }

    @And("^I should see 'Password' label in Login page$")
    public void I_should_see_Password_label_in_Login_page() throws Throwable {
        login.login_password_textbox.sendKeys("snapwiz");
        Assert.assertEquals("password field label is not correct","Password",login.login_fields_labels.get(1).getText());
    }

    @And("^I should see password text box with placeholder text as 'Password' in Login page$")
    public void I_should_see_password_text_box_with_placeholder_text_as_Enter_your_Password_in_Login_page() throws Throwable {
        Assert.assertEquals("Password placeholder text is not correct","Password",login.login_password_textbox.getAttribute("placeholder"));
    }

    @And("^I should see remember me check box in login page$")
    public void I_should_see_remember_me_check_box_in_login_page() throws Throwable {
        Assert.assertEquals("Remember me check box is not there on Login page",true,login.login_page_remember_me_checkbox.isDisplayed());

    }


    @And("^I should see 'Remember me' label in Login page$")
    public void I_should_see_Remember_me_label_in_Login_page() throws Throwable {
        Assert.assertEquals("Remember me label on login page is not correct","Remember me",login.login_page_remember_me_label.getText());
    }


    @And("^I should see 'Login with your Google Account' label$")
    public void I_should_see_Login_with_your_Google_Account_label() throws Throwable {
        Assert.assertEquals("Login page Login with Google label is not correct","Login to your account",login.login_with_google_label.getText());
    }

    @And("^I should see the Login button in Login page$")
    public void I_should_see_the_Login_button_in_Login_page() throws Throwable {
        Assert.assertEquals("Login button Label is not correct","Login",login.login_page_login_button.getText());
    }

    @And("^I should see 'Login with Google' link$")
    public void I_should_see_Login_with_Google_link() throws Throwable {
        Assert.assertEquals("There is problem with Google login link on the Login page","Login with Google",login.login_page_google_login_link.getText());
    }

    @And("^I should see the Footer label 'Don't have an account\\?'$")
    public void I_should_see_the_Footer_label_Don_t_have_an_account_() throws Throwable {
        Assert.assertEquals("There is a problem with Login page footer label","Don't have an account?",login.loginpage_footer_label.getText().trim());
    }

    @And("^I should see 'Sign Up' link in Login page footer$")
    public void I_should_see_Sign_Up_link_in_Login_page_footer() throws Throwable {
        Assert.assertEquals("There is a problem with Sin Ip link on Login page footer","Sign Up",login.loginpage_footer_signup_link.getText());

    }

    @Then("^I should see the logo on the header page in login page$")
    public void I_should_see_the_logo_on_the_header_page_in_login_page() throws Throwable {
        Assert.assertEquals("Logo on login page is not displayed",true,login.login_page_logo.isDisplayed());
    }

    @When("^I enter valid \"([^\"]*)\" emil in email field$")
    public void I_enter_valid_emil_in_email_field(String arg1) throws Throwable {
        login.login_email_textbox.sendKeys(arg1);
    }

    @And("^I enter valid \"([^\"]*)\" password in password field$")
    public void I_enter_valid_password_in_password_field(String arg1) throws Throwable {
        login.login_password_textbox.sendKeys(arg1);
    }

    @When("^I click on Login button on login page$")
    public void I_click_on_Login_button_on_login_page() throws Throwable {
        login.login_page_login_button.click();
    }

    @When("^I enter \"([^\"]*)\" in email field on login page$")
    public void I_enter_in_email_field_on_login_page(String arg1) throws Throwable {
        login.login_email_textbox.sendKeys(arg1);
    }

    @And("^I enter \"([^\"]*)\" in password field on login page$")
    public void I_enter_in_password_field_on_login_page(String arg1) throws Throwable {
        login.login_password_textbox.sendKeys(arg1);
    }

    @Then("^I should see the Validation  as \"([^\"]*)\" for the \"([^\"]*)\"$")
    public void I_should_see_the_Validation_as_for_the(String validationMessage, String arg2) throws Throwable {
        if(arg2.equalsIgnoreCase("Empty Email")){
            Assert.assertEquals("There is a problem with Login page Name validation",validationMessage,login.login_page_email_error_message.getText() );
        }
        else if(arg2.equalsIgnoreCase("Empty Password")){
            Assert.assertEquals("There is a problem with Login page Password validation",validationMessage,login.login_page_password_error_message.getText());
        }

        else if(arg2.equalsIgnoreCase("Wrong Email")){
            Assert.assertEquals("There is a problem with Login page Name validation",validationMessage,login.login_page_email_error_message.getText() );
        }
        else if(arg2.equalsIgnoreCase("Wrong Password")){

            Assert.assertEquals("There is a problem with Login page Password validation",validationMessage,login.login_page_password_error_messag1e1.getText().trim());
        }
        else if(arg2.equalsIgnoreCase("Email Not Registered")){

            Assert.assertEquals("There is a problem with Login page Password validation",validationMessage,login.login_page_password_error_messag1e1.getText().trim());
        }


        else {
            Assert.fail("Invalid Field!!!");
        }
    }

    @When("^I click on Login with Google link in the Login page$")
    public void I_click_on_Login_with_Google_link_in_the_Login_page() throws Throwable {
        login.login_with_google_link.click();
    }


    @And("^I enter email \"([^\"]*)\" in email$")
    public void I_enter_email_in_email(String arg1) throws Throwable {
        login.gmail_email_textbox.sendKeys(arg1);
    }


    @When("^I enter the password \"([^\"]*)\" in gmail page$")
    public void I_enter_the_password_in_gmail_page(String arg1) throws Throwable {
        login.gmail_password_textbox.sendKeys(arg1);

    }


}
