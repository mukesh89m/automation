package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pageFactoryAppium;

import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Snapwiz on 14/08/15.
 */
public class RandomLoginPage_appium {
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[1]")
    public WebElement textField_userId;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[2]")
    public WebElement textField_courseName;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[3]")
    public WebElement textField_role;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[4]")
    public WebElement textField_courseId;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[5]")
    public WebElement textField_contextId;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[6]")
    public WebElement textField_contextTitle;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[7]")
    public WebElement textField_domainId;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    public WebElement button_submit;
}
