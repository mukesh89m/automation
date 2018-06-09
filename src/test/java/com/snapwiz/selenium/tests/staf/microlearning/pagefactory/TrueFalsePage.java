package com.snapwiz.selenium.tests.staf.microlearning.pagefactory;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


/**
 * Created by Priti on 07-09-2016.
 */
public class TrueFalsePage {
    @FindBy(css = "span[class='icon-question']")
    public WebElement createQuestion_slide;

    @FindBy(id = "ml-slide-type-title")
    public WebElement questionHeader;


    @FindBy(css = "span[class='icon-trueorfalse']")
    public WebElement truefalseQuestion;

    @FindBy(css = "span[class='icon-multiple-choice']")
    public WebElement multipleChoiceQuestion;

    @FindBy(xpath = "//button[contains(text(),'Add To Slide')]")
    public  WebElement addToSlide_Button;

    @FindBy(css = "div[class='toast-message']")
    public WebElement warningMessage;

    @FindBy(xpath = "//div[@id='question-raw-content']")
    public WebElement trueFalseQuestionText;

    @FindBy(xpath = "//label[@class='true-false-content-text' and contains(text(),'True')]")
    public WebElement trueOption;

    @FindBy(xpath = "//div[@class='lesson-slider-wrapper']")
    public WebElement questionSlidePreview;

    @FindBy(xpath = "//div[@class='slide-preview-action']/button[1]")
    public WebElement editButton;

    @FindBy(xpath = "//label[@class='true-false-content-text' and contains(text(),'False')]")
    public WebElement falseOption;

    @FindBy(css = ".points-input-edit-icon")
    public WebElement pointIcon;

    @FindBy(css = "#points-input-tag")
    public WebElement pointBox;

    @FindBy(css = "span[title='Save Points']")
    public WebElement savePoint_Button;

    @FindBy(css = "#js-preview-lesson")
    public WebElement previewButton;

    @FindBy(css = "button[class=\"pull-right dl-btn btn-blue btn-rounded question-submit js-question-submit\"]")
    public WebElement submitQuestionButton;

    @FindBy(css = ".totalScore")
    public WebElement totalScore;

    @FindBy(css = "input[class =\"points-input error\"]")
    public WebElement errorPointBox;

    @FindBy(css = "#content-hint")
    public WebElement hintTextBox;

    @FindBy(css = "#content-solution")
    public  WebElement solutionTextBox;

    @FindBy(xpath = "//button[contains(text(),'Hint')]")
    public WebElement hintButton;

    @FindBy(css = ".cms-question-preview-question-hint-content")
    public WebElement hintText;

    @FindBy(css = ".cms-question-preview-question-solution-content")
    public WebElement solutionText;

    @FindBy(css= "#radactor-toolbar-separate")
    public WebElement textEditor;

    @FindBy(css = ".true-false-student-answer-select")
    public List<WebElement> trueFalseAnswerSelect;


}
