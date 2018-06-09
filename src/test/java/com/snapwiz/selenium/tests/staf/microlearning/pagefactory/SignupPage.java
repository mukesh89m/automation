package com.snapwiz.selenium.tests.staf.microlearning.pagefactory;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by mukesh on 19/8/16.
 */
public class SignupPage {

    @FindBy(css = "button[class='btn btn-primary btn-md pull-right ml-signup-btn btn-green js-signup-btn']")
    public WebElement signUp_link;

    @FindBy(css = "button[class='btn btn-primary btn-blue btn-md pull-right ml-login-btn js-login-btn']")
    public WebElement login_button;

    @FindBy(css = "#first-name")
    public WebElement name_textbox;

    @FindBy(css = "#user-email")
    public WebElement email_textbox;

    @FindBy(css = "#user-password")
    public WebElement password_textbox;

    @FindBy(css = "#retype-password")
    public WebElement re_typePassword_textbox;

    @FindBy(css = "button[class='btn btn-primary btn-green btn-lg js-signup-action']")
    public WebElement signUp_button;

    @FindBy(css = ".text-center.dl-green.font-bold")
    public WebElement signUp_label;

    @FindBy(css = ".img-circle")
    public WebElement signUpProfile_thumbnail;

    @FindBy(css = "div[class='dl-errorMsg email-message dl-errorMsg-bottom']")
    public WebElement existingEmail_message;

    @FindBy(css = "div[class='dl-errorMsg first-name-message dl-errorMsg-bottom']")
    public WebElement name_errorMessage;


    @FindBy(css = "div[class='dl-errorMsg email-message']")
    public WebElement email_errorMessage;

    @FindBy(css = "div[class='dl-errorMsg password-message dl-errorMsg-bottom']")
    public WebElement password_errorMessage;

    @FindBy(css = "div[class='dl-errorMsg retype-password-message dl-errorMsg-bottom']")
    public WebElement reTypePassword_errorMessage;

    @FindBy(css = ".text-center.dl-green.font-bold")
    public WebElement login_header;

    @FindBy(css = "#logIn")
    public WebElement logIn;

    @FindBy(css = "div[class='navbar-username']")
    public WebElement username_icon;

    @FindBy(css = "a[href='/dl/logout']")
    public WebElement logout_link;

    @FindBy(className = "navbar-username")
    public WebElement userName;

    @FindBy(css = "span[class='ml-logo-img']")
    public WebElement signuppagelogo;

    @FindBy(css = ".control-label.floating-label")
    public List<WebElement> signup_fields_labels;


    @FindBy(css = "h4[class='font-bold']")
    public WebElement sign_up_with_google_label;

    @FindBy(css = "div[id='google-openid-regestration']")
    public WebElement sign_up_with_google_link_block;

    @FindBy(css = "span[class='media-middle']")
    public WebElement getSign_up_with_google_link_label;

    @FindBy(css = "div[class='col-xs-12 text-center dl-already-account']>span")
    public WebElement signUp_Page_Footer_Label;


    @FindBy(css = "div[class='col-xs-12 text-center dl-already-account']>a")
    public WebElement signUp_Page_Footer_Login_link;

    @FindBy(css = "div[class='col-xs-12 text-center dl-already-account']>span")
    public WebElement signup_page_footer_label;

    @FindBy(css = "a[href='#signup']")
    public WebElement footer_signUp_link;

    @FindBy(css = "a[href='#login']")
    public WebElement footer_login_link;

    @FindBy(css = ".dl-errorMsg.email-message>a")
    public WebElement loginLinkInErrorMessage;

    @FindBy(css = "large[class='dl-terms-and-condition m-t-md']>a")
    public List<WebElement> termAndCondition_link;

    @FindBy(css = "div>h2")
    public WebElement termAndCondition_header;

    @FindBy(css = "#google-openid-regestration")
    public WebElement signupWithGoogle_link;


}
