package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Dharaneesh T Gowda on 27-01-2015.
 */
public class GoogleAccounts {
    @FindBy(id = "account-chooser-add-account")
    WebElement link_AddAccount;//Link 'Add Account'
    public WebElement getLink_AddAccount() {
        return link_AddAccount;
    }
}
