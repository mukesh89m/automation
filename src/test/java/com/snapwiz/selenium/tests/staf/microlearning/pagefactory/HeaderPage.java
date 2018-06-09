package com.snapwiz.selenium.tests.staf.microlearning.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by murthi on 24-08-2016.
 */
public class HeaderPage {

    //home page header
    @FindBy(id = "ml-nav-search")
    public WebElement SearchBox;

    @FindBy(id = "ml-nav-search-btn")
    public WebElement SearchButton;

    @FindBy(className = "ml-logo-img")
    public WebElement microLessonLogo;

    //Add slide header
    @FindBy(css = ".js-add-to-slide")
    public WebElement btnAddToSlide;






}
