package com.snapwiz.selenium.tests.staf.orion.pagefactory.CMS;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Mukesh on 1/12/15.
 */
public class Preview {

    @FindBy(className = "true-false-student-answer-select")
    public List<WebElement> trueFalseAnswerLabel;

    @FindBy(className = "qtn-label")
    public List<WebElement> trueFalseAnswer_label;

    @FindBy(id="question-reveview-submit")
    public WebElement checkAnswer_button; //

    @FindBy(css="div.evaluate-question-answer-notification")
    public WebElement quesAnsNotificationMsg;

    @FindBy(css="div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")
    public WebElement wrongNotificationMsg;

    @FindBy(css="div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")
    public WebElement trueNotificationMsg;

    @FindBy(css="div[class='evaluate-question-answer-notification evaluate-question-answer-notification-partial-correct']")
    public WebElement partialcorrectNotificationMsg;

    @FindBy(xpath = "//td[@classname='qtn-label'][contains(text(),'C')]")
    public WebElement trueFalseAnswerC; //answer label c

    @FindBy(xpath = "//td[@classname='qtn-label'][contains(text(),'D')]")
    public WebElement trueFalseAnswerD; //answer label D

    @FindBy(css="div[class='multiple-select-choice-icon-preview multiple-select-choice-icon-deselect']")
    public WebElement deselect_multipleSelection;

    @FindBy(xpath = "//span[@id='footer-notification-text']/label")
    public WebElement notificationMsg; //notification message when u select the lsCourse

    @FindBy(xpath = "//a[starts-with(@id,'sbToggle_')]")
    public WebElement comboBox_toggle;

    @FindBy(xpath = "//ul[starts-with(@id,'sbOptions_')]/li/a")
    public List<WebElement> comboBox_option;

    @FindBy(css = "span[class='input-tag-wrapper question-element-wrapper']>input")
    public WebElement mapleNumeric_textBox;

    @FindBy(css = "span[class='input-tag-wrapper question-element-wrapper cde']>input")
    public WebElement textEntry_textBox;

    @FindBy(css = "span[class='input-tag-wrapper question-element-wrapper']>input")
    public WebElement textEntry_textBox1;

    @FindBy(className="question-raw-content")
    public WebElement mapleNumericContent; //content area of maple numeric

    @FindBy(id="cms-question-preview-footer-hint")
    public WebElement hint_button;

    @FindBy(css = "div[class='cms-question-preview-question-hint-content']")
    public WebElement hintContent;

    @FindBy(css = "input[class='visible_redactor_input div-preview-right-answer']")
    public WebElement mapleNumeric_correctTextBox;//correct answer textBox for maple numeric question

    @FindBy(css = "div[class='single-select-choice-icon-preview single-select-choice-icon-deselect']")
    public List<WebElement> selectMultipleChoice;

    @FindBy(className = "qtn-label")
    public List<WebElement> select_multipleChoice;

    @FindBy(css = "span[class='associated-content-details-label display-write-board']")
    public WebElement workBoard;

    @FindBy(id = "content-search-review-btn")
    public WebElement launchReview;

    @FindBy(id = "show-your-work-label")
    public WebElement plusWorkBoard;

    @FindBy(className = "close-iframe-question-content")
    public WebElement crossIcon;

    @FindBy(id = "main-controls")
    public WebElement toolControl;

    @FindBy(xpath = "//iframe[starts-with(@id,'whiteBoard_iframe_kedukWBSTUDENT')]")
    public WebElement frame;

    @FindBy(css = "canvas[width='754']")
    public WebElement textEntryPopUp;

    @FindBy(xpath = "(//div[@class='true-false-student-answer-select'])[1]")
    public WebElement answerChoice;

    @FindBy(id = "cms-question-preview-footer-solution")
    public WebElement solution_Button;

    @FindBy(css = "div[class='cms-question-preview-question-solution-content']")
    public WebElement solutionContent;

    @FindBy(css="div[class='multiple-select-choice-icon-preview multiple-select-choice-icon-deselect']")
    public List<WebElement> multipleSelection;

    @FindBy(xpath = "(//select[@class='question-raw-content-dropdown'])[1]/option[2]")
    public WebElement textEntryDropDown;

    @FindBy(xpath = "(//select[@class='question-raw-content-dropdown numeric-unit-preview-selectbox'])[1]/option[2]")
    public WebElement numericEntryDropDown;

    @FindBy(id = "answer_math_edit")
    public WebElement expressionEvaluator;

    @FindBy(id = "html-editor-non-draggable")
    public WebElement essayTextBox;

    @FindBy(xpath = ".//*[@id='cms-question-preview-question-hint-wrapper']/div[2]/img")
    public WebElement hintImage;

    @FindBy(xpath = ".//*[@id='cms-question-preview-question-solution-wrapper']/div[2]/img")
    public WebElement solutionImage;

    @FindBy(xpath = "//span[@title='Diagnostic - Ch 2: The Chemical Foundation of Life']")
    public WebElement previewTab_diagnostic_2ndChapter;//Diagnostic Preview tab for 2nd chapter

    @FindBy(className = "close_tab")
    public List<WebElement> list_closeTab;//Close button on tab

    @FindBy(xpath = "//span[@title='Personalized Practice - Ch 2: The Chemical Foundation of Life']")
    public WebElement previewTab_adaptivePractice_2ndChapter;//Adaptive Practice preview tab for 2nd chapter

    @FindBy(css = "input[class='visible_redactor_input bg-color-white']")
    public WebElement textEntryField;

    @FindBy(id = "question-label")
    public WebElement questionLabel;

    @FindBy(css = "[id^='sbToggle_']")
    public WebElement selectUnits_dropdown;

    @FindBy(xpath = "//span[@class='select-box-tag-wrapper question-element-wrapper']//ul/li/a")
    public List<WebElement> select_dropdown;

    @FindBy(css = "[id^='sbOptions_']>li>a")
    public List<WebElement> selectUnits;

    @FindBy(xpath = ".//*[@class='sbOptions']/li/a[.=\"feet\"]")
    public WebElement selectOptionUnitFeet;

    @FindBy(xpath = "//div[text()='You got it wrong.']")
    public WebElement wrong_message;

    @FindBy(xpath = "//div[text()='You got it right.']")
    public WebElement right_message;


    @FindBy(id="languages")
    public WebElement langaugePalette;

    @FindBy(xpath = "//div[@class='redactor_dropdown redactor_dropdown_box_language']/a")
    public List<WebElement> langaugePalette_option;

    @FindBy(xpath = "//a[text()='Spanish']")
    public WebElement langaugePalette_spanish;

    @FindBy(xpath="//div[contains(@class,'language-icon-containers')]/input")
    public List<WebElement> langaugePalette_allinput;

    @FindBy(id="language-palette-outer-wrapper")
    public WebElement langaugePalette_popup;

    @FindBy(id="languagePaletteBox")
    public WebElement langaugePalette_textBox;


    @FindBy(id = "cancelLanguage")
    public WebElement langaugePalette_cancelButton;

    @FindBy(id="save-language-text")
    public WebElement langaugePalette_saveButton;

    @FindBy(id="cms-question-preview-header-ass-name")
    public WebElement questionPreview_header;





}
