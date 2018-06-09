package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by pragya on 16-07-2015.
 */
public class SignUpPage {

    @FindBy(css = "span.as-show-teacher-signup.as-signup-option.green-bg")
    WebElement tab_teacher;//Teacher tab on sign up page
    public WebElement getTab_teacher(){return tab_teacher;}

    @FindBy(xpath = "//span[@mode='student']")
    WebElement tab_student;//Student tab on sign up page
    public WebElement getTab_student(){return tab_student;}

    @FindBy(xpath = "//span[@mode='admin']")
    WebElement tab_admin;//Admin tab on sign up page
    public WebElement getTab_admin(){return tab_admin;}

    @FindBy(css = "span[class='btn btn-blue center-block as-goto-signin-button']")
    WebElement button_signIn; //Sign in button
    public WebElement getButton_signIn(){return button_signIn;}

    @FindBy(id = "google-openid-regestration")
    public WebElement signUpWithGoogle;

    @FindBy(css = "span[class='navbar-text']")
    public WebElement alreadyHaveAnAccount;
}
