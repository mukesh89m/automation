package com.snapwiz.selenium.tests.staf.microlearning.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by mukesh on 25/8/16.
 */
public class SideMenuLink {

    @FindBy(css = "#explorer>a")
    public WebElement explorer_link;

    @FindBy(css = "#authoring>a")
    public WebElement authoring_link;

    @FindBy(css = ".select-menu>a")
    public WebElement myLibrary_link;


}
