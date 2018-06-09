package com.snapwiz.selenium.tests.staf.orion.pagefactory.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by murthi on 23-03-2016.
 */
public class Header {

    @FindBy(css="[title=\"ORION Dashboard\"]")
    public WebElement DashboardIcon;

}
