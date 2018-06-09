package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Dharaneesh T Gowda on 27-01-2015.
 */
public class PrivacyPolicies {
    @FindBy(id = "submit_approve_access")
    WebElement button_Accept;//Button 'Accept'
    public WebElement getButton_Accept() {
        return button_Accept;
    }
}
