package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/*
 * Created by Dharaneesh T Gowda on 27-01-2015.
 */
public class HomePage {
    @FindBy(xpath = "//a[contains(@title,'Account ')]")
    WebElement icon_userWindow;//Link 'User window to get an option 'Sign out''
    public WebElement getIcon_userWindow() {
        return icon_userWindow;
    }

    @FindBy(linkText = "Sign out")
    WebElement button_SignOut;//Button 'Sign out'
    public WebElement getButton_SignOut() {
        return button_SignOut;
    }

    @FindBy(linkText = "Sign in")
    WebElement button_SignIn;//Button 'Sign in'
    public WebElement getButton_SignIn() {
        return button_SignIn;
    }

}
