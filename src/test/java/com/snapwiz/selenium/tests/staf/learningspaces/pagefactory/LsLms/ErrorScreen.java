package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.LsLms;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by rashmi on 02-02-2016.
 */
public class ErrorScreen {
    @FindBy(xpath = ".//*[@id='bb-sso-error-text']/p[1]")
    WebElement myNotesInstructorErrorScreen;

    public WebElement myNotesInstructorErrorScreen() {
        return myNotesInstructorErrorScreen;
    }
}