package com.snapwiz.selenium.tests.staf.microlearning.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by murthi on 08-09-2016.
 */
public class RichTextPage {
    @FindBy(css = ".fr-box")
    public WebElement froala_editor;

    @FindBy(css = ".fr-element.fr-view")
    public WebElement froala_editor_text_box;

    @FindBy(css = "button[mode='create']")
    public WebElement addToSlide_button;

    @FindBy(css=".fr-command.fr-btn[data-cmd=\"bold\"]")
    public WebElement froala_editor_btnBold;

    @FindBy(css=".fr-command.fr-btn[data-cmd=\"italic\"]")
    public WebElement froala_editor_btnItalic;

    @FindBy(css = ".lesson-preview-body")
    public WebElement textSlidePreview;


}
