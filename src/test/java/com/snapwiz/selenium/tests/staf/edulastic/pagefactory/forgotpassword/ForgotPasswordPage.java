package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.forgotpassword;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by pragya on 17-07-2015.
 */
public class ForgotPasswordPage {

    @FindBy(id = "forgot-email")
    public WebElement textBox_forgotUserNameOrEmail; //Text box to enter email id or user name

    @FindBy(css = "div[class='notification-msg forgot-password ibox-content']")
    public WebElement msg_email ; //Error message

    @FindBy(css = "button[class='input-lg sendLink btn btn-blue btn-block']")
    public WebElement button_sendLink ; //Send link button

    @FindBy(id = "try-again")
    public WebElement button_tryAgain ; //Send link button

    @FindBy(id = "new-password")
    public WebElement textBox_resetNewPassword; //Text box for reset new password

    @FindBy(id = "confirm_password")
    public WebElement textBox_resetConfirmPassword; //Text box reset confirm password

    @FindBy(id = "js-reset-password")
    public WebElement button_resetPassword; //Reset password button

}
