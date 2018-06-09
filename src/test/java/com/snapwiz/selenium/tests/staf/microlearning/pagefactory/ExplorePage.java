package com.snapwiz.selenium.tests.staf.microlearning.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by mukesh on 25/8/16.
 */
public class ExplorePage {
    @FindBy(css = ".ml-navigation-section.ellipsis")
    public WebElement lessonTitlePreviewPage;
}
