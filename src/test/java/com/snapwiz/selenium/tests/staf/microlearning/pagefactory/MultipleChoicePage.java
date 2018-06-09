package com.snapwiz.selenium.tests.staf.microlearning.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Priti on 22-09-2016.
 */
public class MultipleChoicePage {
   @FindBy(css = "#question-mc-raw-content")
    public WebElement multipleChoiceQuestionText;

    @FindBy(css = "div[id^='popupEditor_']")
    public List<WebElement> multipleChoiceAnswerOptionsText;

    @FindBy(css = ".single-select-choice-icon.single-select-choice-icon-deselect")
    public List<WebElement> multipleChoiceAnswerOptions;
}
