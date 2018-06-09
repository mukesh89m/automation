package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.DiscussionWidget;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Mukesh on 7/2/15.
 */
public class DiscussionWidget {

    @FindBy(className = "ir-ls-assign-dialog-dw-gradable-label-check ")
    public WebElement gradable_checkbox;
}
