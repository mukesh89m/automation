package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.cleversync;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by shashank on 23-07-2015.
 */
public class CleverSync {

    @FindBy(id = "clever-oauth-login")
    public WebElement signInWithClever;

    @FindBy(linkText = "Log in with Clever")
    public WebElement logInWithClever;

    @FindBy(name= "username")
    public WebElement  username;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement logInIntoClever;

    @FindBy(css= "div.header-menu-user-name")
    public WebElement cleverHeaderMenu;

    @FindBy(linkText= "Log Out")
    public WebElement cleverLogout;

    @FindBy(css = "div.message")
    public WebElement errorMessage;


}
