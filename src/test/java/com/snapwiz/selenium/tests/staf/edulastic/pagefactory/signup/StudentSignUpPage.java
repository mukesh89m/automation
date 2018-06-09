package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by root on 3/5/15.
 */
public class StudentSignUpPage {

    @FindBy(id="class-code")
    WebElement textBox_classCode; //Class code text box
    public WebElement getTextBox_classCode() { return textBox_classCode; }

    @FindBy(id = "first-name")
    WebElement textBox_name; //Name text box
    public WebElement getTextBox_name(){return textBox_name;}

    @FindBy(id="user-email")
    WebElement textBox_email; //Email text box
    public WebElement getTextBox_email(){return textBox_email;}

    @FindBy(id = "user-password")
    WebElement textBox_password; //Password text box
    public WebElement getTextBox_password(){return textBox_password;}

    @FindBy(id = "retype-password")
    WebElement textBox_retypePassword; //Retype password text box
    public WebElement getTextBox_retypePassword(){return  textBox_retypePassword;}

    @FindBy(css = "button[class='col-xs-12 btn btn-primary btn-lg as-signup-button col-xs-12 col-sm-6 pull-right']")
    WebElement button_signUp; //Sign Up button
    public WebElement getButton_signUp(){return button_signUp;}

    @FindBy(css = "div[class='as-errorMsg retype-password-message']")
    WebElement errorMessage_retypePassword; //Retype password  error message
    public WebElement getErrorMessage_retypePassword(){return errorMessage_retypePassword;}

    @FindBy(css = "div[class='as-errorMsg class-code-message']")
    WebElement errorMessage_classCode; //Error message on class code
    public WebElement getErrorMessage_classCode(){return errorMessage_classCode;}
}
