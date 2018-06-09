package com.snapwiz.selenium.tests.staf.microlearning.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by mukesh on 1/9/16.
 */
public class GmailPage {

    @FindBy(css = "#Email")
    public WebElement email_textbox;

    @FindBy(css = "#next")
    public WebElement next_button;

    @FindBy(css = "#Passwd")
    public WebElement password_textbox;

    @FindBy(css = "#signIn")
    public WebElement signIn_link;

    @FindBy(css = "img[title='Micro Learning']")
    public WebElement microLearning_title;

    @FindBy(css = "#submit_deny_access")
    public WebElement deny_link;

    @FindBy(css = "#submit_approve_access")
    public WebElement access_link;
}
