package com.snapwiz.selenium.tests.staf.microlearning.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by naveen on 9/2/2016.
 */
public class LoginPage {

    @FindBy(css = "button[class='btn btn-primary btn-blue btn-md pull-right ml-login-btn js-login-btn']")
    public WebElement home_login_button;

    @FindBy(css = "span[class='ml-logo-img']")
    public WebElement login_page_logo;


    @FindBy(css = "div[class='col-xs-12 col-md-12']>h2")
    public WebElement login_page_label;

    @FindBy(css = "label[class='control-label floating-label']")
    public List<WebElement> login_fields_labels;

    @FindBy(css = "#user-email")
    public WebElement login_email_textbox;

    @FindBy(css = "#user-password")
    public WebElement login_password_textbox;

    @FindBy(css = "div[class='icheckbox_square-green checked']")
    public WebElement login_page_remember_me_checkbox;

    @FindBy(css = "label[class='control-label']>span")
    public WebElement login_page_remember_me_label;

    @FindBy(css = "button[id='logIn']")
    public WebElement login_page_login_button;

    @FindBy(css = "div[class='col-xs-12 col-md-12']>h2")
    public WebElement login_with_google_label;

    @FindBy(css = "div[class='dl-registration-title-right btn-transition btn dl-google-signin-btn btn-outline center-block m-b-md']>div>span")
    public WebElement login_page_google_login_link;

    @FindBy(css = "div[class='col-xs-12 col-md-12 text-center dl-no-account']>span")
    public WebElement loginpage_footer_label;

    @FindBy(css = "div[class='col-xs-12 col-md-12 text-center dl-no-account']>a")
    public WebElement loginpage_footer_signup_link;

    @FindBy(css = "button[class='btn btn-primary btn-md pull-right ml-signup-btn btn-green js-signup-btn']")
    public WebElement login_page_sign_up_button;

    @FindBy(css = "div[class='dl-errorMsg login-email-message dl-errorMsg-bottom']")
    public WebElement login_page_email_error_message;

    @FindBy(css = "div[class='dl-errorMsg login-password-message dl-errorMsg-bottom']")
    public WebElement login_page_password_error_message;

    @FindBy(css = "div[class='notification-msg']")
    public WebElement login_page_password_error_messag1e1;

    @FindBy(css = ".close")
    public WebElement validationMessageCloseIcon;

    @FindBy(css = "div[class='dl-registration-title-right btn-transition btn dl-google-signin-btn btn-outline center-block m-b-md']")
    public WebElement login_with_google_link;

    @FindBy(css = "input[id='Email']")
    public WebElement gmail_email_textbox;

    @FindBy(css = "input[id='next']")
    public WebElement gmail_page_next_button;

    @FindBy(css = "input[id='Passwd']")
    public WebElement gmail_password_textbox;

    @FindBy(css = "input[id='signIn']")
    public WebElement gamail_sign_in_page;

    @FindBy(css = "button[id='submit_deny_access']")
    public WebElement google_deny_button;

    @FindBy(css = "button[id='submit_approve_access']")
    public WebElement google_allow_button;


    @FindBy(css = "img[title='Micro Learning']")
    public WebElement google_intermediate_pge;






}
