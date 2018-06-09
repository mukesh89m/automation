package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by mukesh on 3/3/16.
 */
public class ResourceTab {

    @FindBy(css = "i[class='ls-right-section-sprites ls--right-star-icon-resource']")
    public WebElement bookmark_icon;

    @FindBy(css = "a[class='favourite-resource active']")
    public WebElement removeBookmark_icon;
}
