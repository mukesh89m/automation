package com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions;

import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.microlearning.apphelper.RobotHelper;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.SignupPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Th;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by murthi on 22-01-2016.
 */
public  class SignUp {


    SignupPage signup = PageFactory.initElements(CucumberDriver.getWebDriver(), SignupPage.class);
    public static String random = StringUtil.generateRandomString(5, StringUtil.Mode.ALPHA);


    @When("^User enter existing \"([^\"]*)\" and click on the SignUp button$")
    public void user_enter_existing_and_click_on_the_SignUp_button(String existingEmail) throws Throwable {
        signup.email_textbox.sendKeys(SignUp.random + existingEmail);
        signup.signUp_button.click();
    }

    @Then("^Email already exists\\. Please login  to your account message should be dispalyed$")
    public void email_already_exists_Please_login_to_your_account_message_should_be_dispalyed() throws Throwable {

        String errorMessage = "Email already exists. Please login to your account.";
        Assert.assertEquals("Email already exists. Please login  to your account message is not displaying", signup.existingEmail_message.getText().trim(), errorMessage.trim());

    }


    @Given("^I am on sign up page$")
    public void iAmOnSignUpPage() throws Throwable {
        WebDriverUtil.launchURL();
        WebDriverUtil.clickOnElementUsingJavascript(signup.signUp_link);

    }

    @When("^I click on SignUp button in ML home page$")
    public void iClickOnSignUpButtonInMLHomePage() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(signup.signUp_link); //click on the sign up link

    }

    @Then("^I should see sign up page$")
    public void iShouldSeeSignUpPage() throws Throwable {
        Assert.assertEquals("Sign Up label is not present in the signUp page", "Sign Up", signup.signUp_label.getText().trim());

    }

    @When("^I enter name as \"([^\"]*)\" in sign up page$")
    public void iEnterNameAsInSignUpPage(String name) throws Throwable {
        signup.name_textbox.sendKeys(name + random);
    }

    @When("^I enter email as \"([^\"]*)\" in sign up page$")
    public void iEnterEmailAsInSignUpPage(String email) throws Throwable {
        signup.email_textbox.sendKeys(random + email);

    }

    @When("^I enter password as \"([^\"]*)\" in sign up page$")
    public void iEnterPasswordAsInSignUpPage(String password) throws Throwable {
        signup.password_textbox.sendKeys(password + random);

    }

    @When("^I enter re-type password as \"([^\"]*)\" in sign up page$")
    public void iEnterReTypePasswordAsInSignUpPage(String reTypepassword) throws Throwable {
        signup.re_typePassword_textbox.sendKeys(reTypepassword + random);

    }

    @When("^I click on SignUp button in sign up page$")
    public void iClickOnSignUpButtonInSignUpPage() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(signup.signUp_button); //click on the sign up button in sign up page
        Thread.sleep(3000);
    }

    @Then("^I should see Profile thumbnail$")
    public void iShouldSeeProfileThumbnail() throws Throwable {
        String src = signup.signUpProfile_thumbnail.getAttribute("src");
        if (!src.contains("/superoutman/128.jpg")) {
            Assert.fail("User is not able to sign up with the given credentails");
        }
    }

    @Then("^I should see the Validation text for Name field as \"([^\"]*)\"$")
    public void iShouldSeeTheValidationTextForNameFieldAs(String name) throws Throwable {
        Assert.assertEquals(name + "error message is not displaying", name, signup.name_errorMessage.getText().trim());

    }

    @And("^I should see the Validation text for Email field as \"([^\"]*)\"$")
    public void iShouldSeeTheValidationTextForEmailFieldAs(String email) throws Throwable {
        Assert.assertEquals(email + "error message is not displaying", email, signup.existingEmail_message.getText().trim());

    }


    @And("^I should see the Validation text for Password field as \"([^\"]*)\"$")
    public void iShouldSeeTheValidationTextForPasswordFieldAs(String password) throws Throwable {
        Assert.assertEquals(password + "error message is not displaying", password, signup.password_errorMessage.getText().trim());

    }

    @When("^I enter \"([^\"]*)\" in username field in the sign up page$")
    public void iEnterInUsernameFieldInTheSignUpPage(String name) throws Throwable {
        signup.name_textbox.sendKeys(name);
        Thread.sleep(2000);
        // new RobotHelper().sendKeyBoardKeys("`");

    }

    @When("^I enter \"([^\"]*)\"  in email field in the sign up page$")
    public void iEnterInEmailFieldInTheSignUpPage(String email) throws Throwable {
        signup.email_textbox.sendKeys(email);

    }

    @When("^I enter \"([^\"]*)\" in password field in the sign up page$")
    public void iEnterInPasswordFieldInTheSignUpPage(String password) throws Throwable {
        signup.password_textbox.sendKeys(password);

    }

    @When("^I enter \"([^\"]*)\" in re-type password field in the sign up page$")
    public void iEnterInReTypePasswordFieldInTheSignUpPage(String reTypepassword) throws Throwable {
        signup.re_typePassword_textbox.sendKeys(reTypepassword);

    }

    @Then("^I should see the Validation  as \"([^\"]*)\"$")
    public void iShouldSeeTheValidationAs(String validationMessage) throws Throwable {
        if ("Please provide your full name".equalsIgnoreCase(validationMessage)) {
            Assert.assertEquals(validationMessage + "error message is not displaying", validationMessage, signup.name_errorMessage.getText().trim());
        } else if ("Please provide a valid Email id".equalsIgnoreCase(validationMessage)) {
            Assert.assertEquals(validationMessage + "error message is not displaying", validationMessage, signup.existingEmail_message.getText().trim());
        } else if ("Please provide a valid password".equalsIgnoreCase(validationMessage)) {
            Assert.assertEquals(validationMessage + "error message is not displaying", validationMessage, signup.password_errorMessage.getText().trim());
        } else if ("Please retype the password".equalsIgnoreCase(validationMessage)) {
            Assert.assertEquals(validationMessage + "error message is not displaying", validationMessage, signup.reTypePassword_errorMessage.getText().trim());
        } else {
            Assert.fail("Invalid input!!");
        }
    }


    @Then("^I should see logo  in the header$")
    public void I_should_see_logo_in_the_header() throws Throwable {
        //check for logo availability
        signup.signuppagelogo.isDisplayed();

    }

    @Then("^I should see the validation for email field as \"([^\"]*)\"$")
    public void iShouldSeeTheValidationForEmailFieldAs(String errorMessage) throws Throwable {
        WebDriverUtil.waitTillVisibilityOfElement(signup.existingEmail_message,30);
        Assert.assertEquals("Email already exists. Please login  to your account message is not displaying", signup.existingEmail_message.getText().trim(), errorMessage.trim());

    }

    @When("^I click on Login link in Validation popup text in the sign up page$")
    public void iClickOnLoginLinkInValidationPopupTextInTheSignUpPage() throws Throwable {
        Thread.sleep(3000);
        WebDriverUtil.clickOnElementUsingJavascript(signup.loginLinkInErrorMessage);

    }

    @When("^I click on Terms link in the sign up page$")
    public void iClickOnTermsLinkInTheSignUpPage() throws Throwable {
        signup.termAndCondition_link.get(0).click();
    }

    @Then("^I should see the terms page$")
    public void iShouldSeeTheTermsPage() throws Throwable {
        WebDriverUtil.switchToNewWindow();
        Assert.assertEquals("When i click on the term link not able to navigate to term page","http://drip-uat.snapwiz.net/webresources/static/terms-of-service.htm",CucumberDriver.getWebDriver().getCurrentUrl().trim());
        Assert.assertEquals("Terms of Service header is not displaying","Micro Lesson Terms of Service",signup.termAndCondition_header.getText().trim());

    }

    @Then("^I should see the Privacy Policy page$")
    public void iShouldSeeThePrivacyPolicyPage() throws Throwable {
        WebDriverUtil.switchToNewWindow();
        Assert.assertEquals("When i click on the term link not able to navigate to term page","http://drip-uat.snapwiz.net/webresources/static/privacy-policy.htm",CucumberDriver.getWebDriver().getCurrentUrl().trim());
        Assert.assertEquals("Privacy Policy header is not displaying", "Micro Lesson Privacy Policy", signup.termAndCondition_header.getText().trim());

    }

    @When("^I click on Privacy Policy link in the sign up page$")
    public void iClickOnPrivacyPolicyLinkInTheSignUpPage() throws Throwable {
        signup.termAndCondition_link.get(1).click();

    }

    @When("^I click on Sign-up with Google link in the sign up page$")
    public void iClickOnSignUpWithGoogleLinkInTheSignUpPage() throws Throwable {
        signup.signupWithGoogle_link.click();
    }



    @Then("^I should see the logo on the header page in Sign Up page$")
    public void I_should_see_the_logo_on_the_header_page_in_Sign_Up_page() throws Throwable {
        Assert.assertEquals("Logo is not displayed on Sign up ", signup.signuppagelogo.isDisplayed(), true);
    }

    @And("^I should see 'Login' button in the header$")
    public void I_should_see_Login_button_in_the_header() throws Throwable {
        Assert.assertEquals("Login button label is not proper", signup.login_button.getText(), "Login");
    }

    @Then("^I should see the Validation for \"([^\"]*)\" as \"([^\"]*)\"$")
    public void I_should_see_the_Validation_for_as(String arg1, String expectedMessgae) throws Throwable {
        if(arg1.equalsIgnoreCase("Invalid Name")){
            Assert.assertEquals("There is a problem with Login page Name validation",expectedMessgae,signup.name_errorMessage.getText() );
        }
        else if(arg1.equalsIgnoreCase("Invalid Email")){
            Assert.assertEquals("There is a problem with Login page email validation",expectedMessgae,signup.existingEmail_message.getText());
        }
        else if (arg1.equalsIgnoreCase("Invalid Password")){
            Assert.assertEquals("There is a problem with Login page Password validation",expectedMessgae,signup.password_errorMessage.getText());
        }
        else if(arg1.equalsIgnoreCase("Invalid retype password")){
            Assert.assertEquals("There is a problem with Login page re-type Password validation",expectedMessgae,signup.reTypePassword_errorMessage.getText());
        }

    }

    @And("^I should see 'Sign Up' label in sign up page$")
    public void I_should_see_Sign_Up_label_in_sign_up_page() throws Throwable {
        Assert.assertEquals("Sing Up page label is not correct", signup.signUp_label.getText(), "Sign Up");
    }

    @And("^I should see 'Full Name' label  in sign up page$")
    public void I_should_see_Full_Name_label_in_sign_up_page() throws Throwable {
        signup.name_textbox.sendKeys("Navin");
        Assert.assertEquals("Name label is not correct", signup.signup_fields_labels.get(0).getText(), "Full Name");

    }

    @And("^I should see name text box with placeholder text as 'Full Name'$")
    public void I_should_see_name_text_box_with_placeholder_text_as_Full_Name() throws Throwable {
        Assert.assertEquals("Name text box placeholder text is not correct", "Full Name", signup.name_textbox.getAttribute("placeholder"));
    }

    @And("^I should see 'Email' label in sign up page$")
    public void I_should_see_Email_label_in_sign_up_page() throws Throwable {
        signup.email_textbox.sendKeys("mark@snapwiz.com");
        Assert.assertEquals("Name label is not correct", signup.signup_fields_labels.get(1).getText(), "Email");
    }

    @And("^I should see email text box with placeholder text as 'Email'$")
    public void I_should_see_email_text_box_with_placeholder_text_as_Enter_your_Email_Address() throws Throwable {
        Assert.assertEquals("Email text box placeholder text is not correct", "Email", signup.email_textbox.getAttribute("placeholder"));
    }

    @And("^I should see 'Password' label in sign up page$")
    public void I_should_see_Password_label_in_sign_up_page() throws Throwable {
        signup.password_textbox.sendKeys("snapwiz");
        Assert.assertEquals("Password label is not correct", "Password", signup.signup_fields_labels.get(2).getText());
    }

    @And("^I should see password text box with placeholder text as 'Password'$")
    public void I_should_see_password_text_box_with_placeholder_text_as_Enter_your_Password() throws Throwable {
        Assert.assertEquals("Password text box placeholder text is not correct", "Password", signup.password_textbox.getAttribute("placeholder"));
    }

    @And("^I should see 'Re-type Password' label in sign up page$")
    public void I_should_see_Re_type_Password_label_in_sign_up_page() throws Throwable {
        signup.re_typePassword_textbox.sendKeys("snapwiz");
        Assert.assertEquals("Re-type Password label is not correct", "Re-type Password", signup.signup_fields_labels.get(3).getText());
    }

    @And("^I should see re-type password text box with placeholder text as 'Re-type your Password'$")
    public void I_should_see_re_type_password_text_box_with_placeholder_text_as_Re_type_your_Password() throws Throwable {
        Assert.assertEquals("Re-type Password text box placeholder text is not correct", signup.re_typePassword_textbox.getAttribute("placeholder"), "Re-type Password");
    }

    @And("^I should see Sign-up with your Google Account label$")
    public void I_should_see_Sign_up_with_your_Google_Account_label() throws Throwable {
        Assert.assertEquals("Sign up with google label is not correct", signup.sign_up_with_google_label.getText(), "Sign-up with your Google Account");

    }

    @And("^I should see Sign-up with Google link$")
    public void I_should_see_Sign_up_with_Google_link() throws Throwable {
        Assert.assertEquals("Signup with google buttons link is not correct", signup.sign_up_with_google_link_block.isDisplayed(), true);
        Assert.assertEquals("Sign up with google link label is not correct", signup.getSign_up_with_google_link_label.getText(), "Sign-up with Google");
    }


    @And("^I should see the Footer label as Already have an account\\? Login link$")
    public void I_should_see_the_Footer_label_as_Already_have_an_account_Login_link() throws Throwable {
        Assert.assertEquals("Footer label is is not correct", signup.signUp_Page_Footer_Label.getText(), "Already have an account?  ");
        Assert.assertEquals("Footer link is is not correct", signup.signUp_Page_Footer_Login_link.getText(), "Login");

    }


    @When("^I enter existing email \"([^\"]*)\" in the sign up page$")
    public void I_enter_existing_email_in_the_sign_up_page(String arg1) throws Throwable {
        signup.email_textbox.sendKeys(arg1);
        CucumberDriver.getWebDriver().findElement(By.cssSelector("body")).click();
    }


}

