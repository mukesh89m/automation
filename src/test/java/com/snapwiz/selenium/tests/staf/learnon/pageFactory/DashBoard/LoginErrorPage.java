package com.snapwiz.selenium.tests.staf.learnon.pageFactory.DashBoard;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by root on 12/28/15.
 */
public class LoginErrorPage {
    @FindBy(xpath = "//div[@class='al-error-body-content']/div[3]")
    public WebElement loginErrorMessage;

    @FindBy(xpath = "//img[@id = 'al-error-header-return-url']")
    public WebElement returnToJackPlusArrowIcon;

    @FindBy(id = "al-error-header-return-url")
    public WebElement link_clickHere;

    @FindBy(className = "al-learnon-header-logo")
    public WebElement icon_jarcarandaLearnOn;

    @FindBy(id = "al-return-to-dashboard-title")
    public WebElement label_returnToJackPlus;






}
