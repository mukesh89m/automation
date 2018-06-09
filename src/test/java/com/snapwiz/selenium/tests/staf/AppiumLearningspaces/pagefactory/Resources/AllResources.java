package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.Resources;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by priyanka on 1/7/2015.
 */
public class AllResources {
    @FindBy(css = "span[title='All Resources']")
    WebElement allResources;
    public WebElement getAllResources(){return allResources; }
}
