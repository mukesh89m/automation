package com.snapwiz.selenium.tests.staf.microlearning.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by murthi on 08-09-2016.
 */
public class AuthorNewLessonPage {

    @FindBy(css=".dl-slides-active .slide-type-text")
    public WebElement active_slide_type;

    @FindBy(id = "dl-rich-text-preview")
    public WebElement rich_text_preview;

    @FindBy(css=".js-slide-edit")
    public WebElement edit_slide;

    @FindBy(id="js-close-delete-confirm-popup")
    public WebElement btnNoCancel;

    @FindBy(css = ".dl-slides-box.ui-state-default")
    public List<WebElement> allSlide_Type;

    @FindBy(id="js-delete-slide-confirmed")
    public WebElement btnYesDelete;

    @FindBy(id="slide-container")
    public WebElement slide_container;

    @FindBy(id="slide-container")
    public List<WebElement> slide_containerList;
}
