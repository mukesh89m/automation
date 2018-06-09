package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by pragya on 15-07-2015.
 */
public class TeacherSignUpPage {

    @FindBy(xpath= "//button[@type='submit' and @mode='teacher']")
    WebElement button_signUp;//Sign up button
    public WebElement getButton_signUp(){return button_signUp;}

    @FindBy(className = "sign-with-classCode")
    WebElement link_signIn_errorMsg;//Sign in button on email error message for existing user
    public WebElement getLink_signIn_errorMsg(){return link_signIn_errorMsg;}

    @FindBy(css = "div[class='as-errorMsg first-name-message']")
    WebElement errorMessage_name;//Error message on name
    public WebElement getErrorMessage_name(){return errorMessage_name;}

    @FindBy(xpath = "//div[starts-with(@class,'as-errorMsg email-message')]")
    WebElement errorMessage_email;//Error message on email
    public WebElement getErrorMessage_email(){return errorMessage_email;}

    @FindBy(css = "div[class='as-errorMsg password-message']")
    WebElement errorMessage_password;//Error message on password
    public WebElement getErrorMessage_password(){return errorMessage_password;}

    @FindBy(css = "div[class='as-errorMsg retype-password-message']")
    WebElement errorMessage_retypePassword;//Error message on retype password
    public WebElement getErrorMessage_retypePassword(){return errorMessage_retypePassword;}

}
