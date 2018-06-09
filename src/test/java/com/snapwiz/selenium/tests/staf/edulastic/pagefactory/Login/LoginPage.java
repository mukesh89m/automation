package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login;

        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;

/**
 * Created by Dharaneesh T Gowda on 27-01-2015.
 */
public class LoginPage {

    @FindBy(id = "google-openid-login")// Standard TLO Text
    WebElement button_WithGoogle;//Button 'With Google'
    public WebElement getButton_WithGoogle() {
        return button_WithGoogle;
    }
    @FindBy(id = "google-openid-regestration")
    public WebElement signUpWithGoogle;

    @FindBy(linkText = "I am a Student")
            WebElement link_studentLink;//Link 'I am student' on login page
    public WebElement studentLink() {
        return link_studentLink;
    }

    @FindBy(css = "span[class='btn btn-primary center-block as-goto-signup-button']")
    WebElement button_signUpItsFree; //Sign up its free button
    public WebElement getButton_signUpItsFree(){return button_signUpItsFree;}

    @FindBy(css = "h2.text-center.edu-green.font-bold")
    WebElement signIntoYourAccount; //Sign in to your account on sign in page
    public WebElement getSignIntoYourAccount(){return signIntoYourAccount;}

    @FindBy(id = "login-email")
    WebElement textBox_username; //Username text box
    public WebElement getTextBox_username(){return textBox_username;}

    @FindBy(id = "login-password")
    WebElement textBox_login_password; //Text box to enter login password
    public WebElement getTextBox_login_password(){return textBox_login_password;}

    @FindBy(id = "signIn")
    WebElement button_signIn; //Sign in button
    public WebElement getButton_signIn(){return button_signIn;}

    @FindBy(linkText = "Forgot Password ?")
    WebElement link_forgotPassword; // Forgot password link
    public WebElement getLink_forgotPassword(){return  link_forgotPassword;}

    @FindBy(className = "notification-msg")
    WebElement errorMsg;//Error message when email or password is not correct
    public WebElement getErrorMsg(){return errorMsg;}

    @FindBy(id = "user-email")
    public WebElement userlogin;
}
