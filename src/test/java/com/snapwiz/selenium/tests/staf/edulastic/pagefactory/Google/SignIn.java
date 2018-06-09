package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Dharaneesh T Gowda on 27-01-2015.
 */
public class SignIn {

    @FindBy(id = "Email")
    WebElement textField_email;//Text Field 'Email'
    public WebElement getTextField_email() {
        return textField_email;
    }

    @FindBy(id = "Passwd")
    WebElement textField_password;//Text Field 'Password'
    public WebElement getTextField_password() {
        return textField_password;
    }

    @FindBy(id = "signIn")
    WebElement button_SignIn;//Button 'Sign in'
    public WebElement getButton_SignIn() {
        return button_SignIn;
    }

    @FindBy(id="next")
    public List<WebElement> buttonNext;

    @FindBy(id="submit_approve_access")
    public List<WebElement > buttonAccept;




}
