package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Edulastic;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Dharaneesh T Gowda on 27-01-2015.
 */
public class Login {
    @FindBy(name = "teacher")
    WebElement button_Teacher;//Button 'Teacher' in 'sign up' popup
    public WebElement getButton_Teacher() {
        return button_Teacher;
    }

    @FindBy(name = "student")
    WebElement button_Student;//Button 'Student' in 'sign up' popup
    public WebElement getButton_Student() {
        return button_Student;
    }

    @FindBy(className = "as-registration-google-signin-text")
    WebElement button_ConnectWithGoogle;//Button 'Connect With Google'
    public WebElement getButton_ConnectWithGoogle() {
        return button_ConnectWithGoogle;
    }

    @FindBy(id = "class-code-google")
    WebElement textField_EnterClassCode;//Text Field 'Enter Class Code'
    public WebElement getTextField_EnterClassCode() {
        return textField_EnterClassCode;
    }


    @FindBy(id = "account-chooser-add-account")
    WebElement link_AddAccount;//Link 'Add Account'
    public WebElement geLink_AddAccount() {
        return link_AddAccount;
    }
}
