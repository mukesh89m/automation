package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Mukesh on 1/12/15.
 */
public class Preview {

    @FindBy(className = "true-false-student-answer-label")
    public List<WebElement> trueFalseAnswerLabel;

    @FindBy(className = "qtn-label")
    public List<WebElement> trueFalseAnswer_label;

    @FindBy(id="question-reveview-submit")
    public WebElement checkAnswer_button; //

    @FindBy(css="div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")
    public WebElement wrongNotificationMsg;


    @FindBy(css="div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")
    public WebElement trueNotificationMsg;

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

    @FindBy(css = "div[class='cms-question-preview-question-solution-content']>img")
    public WebElement solutionImageContent;

    @FindBy(xpath="//span[@class='choice-value']")
    public List<WebElement> multipleSelection;

    @FindBy(xpath = "(//select[@class='question-raw-content-dropdown'])[1]/option[2]")
    public WebElement textEntryDropDown;

    @FindBy(className = "numeric_entry_student_input")
    public WebElement numericEntryTextBox;

    @FindBy(className = "numeric_entry_student_input")
    public List<WebElement> numericEntryTextBoxs;

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

    @FindBy(xpath = "//span[@title='ORION  Ch 2: The Chemical Foundation of Life']")
    public WebElement previewTab_adaptivePractice_2ndChapter;//Adaptive Practice preview tab for 2nd chapter

    @FindBy(xpath = "//input[contains(@class,'visible_redactor_input')]")
    public WebElement textEntryField;

    @FindBy(xpath = "//input[contains(@class,'visible_redactor_input')]")
    public List<WebElement> textEntryFields;

    @FindBy(css = "a[class='re-icon re-matheditor redactor-btn-image']")
    public WebElement mathEditor;


    @FindBy(css = "a[class='re-icon re-language redactor-btn-image']")
    public WebElement language;

    @FindBy(id = "wiris-container-cancelchoice1")
    public WebElement cancelButton;

    @FindBy(xpath = "((//select[@class='question-raw-content-dropdown'])[1]/option)")
    public List<WebElement> textEntry_DropDown;


    @FindBy(xpath = "(//*[@id='answer_math_edit'])[1]")
    public WebElement answerEdit;

    @FindBy(xpath = ".//*[@id='wirisAnswerEditorContainer']//input[@class='wrs_focusElement']")
    public WebElement wrsAnswerEdit;

    @FindBy(xpath = ".//*[@id='wiris-answer-container-save-choice1']/span")
    public WebElement editAnswerMathDialog_Save;

    @FindBy(xpath = ".//*[@id='wiris-answer-container-delete-choice1']/span")
    public WebElement editAnswerMathDialog_Delete;

    @FindBy(xpath = ".//*[@id='wirisAnswerEditorContainer']//button[@title='Square root']")
    public WebElement editAnswerMathDialog_ToolBar_SquareRoot;

    @FindBy(id="cms-question-preview-header-logo")
    public WebElement previewPage_HeaderLogo;

    @FindBy(css = "label[class='control-label']>img")
    public  WebElement preview_image;

    @FindBy(xpath = "//label[text()='You got it wrong.']")
    public WebElement gotItWrong;

    @FindBy(xpath = "//label[text()='You got it right.']")
    public WebElement gotItRight;

    @FindBy(xpath = "//label[text()='You got it partially correct.']")
    public WebElement gotItPartiallyCorrect;

    @FindBy(css = "a[class='jp-play']")
    public WebElement playButton;

    @FindBy(css = "div[class='jp-current-time']")
    public WebElement playTime;

    @FindBy(xpath = "//embed[contains(@name,'wistia_')]")
    public WebElement video;

    @FindBy(css = "select[class='question-raw-content-dropdown numeric-unit-preview-selectbox']")
    public WebElement numericDropDown;

    @FindBy(className = "question-raw-content-dropdown")
    public WebElement textDropDown;

    @FindBy(css = "div[class='dnd-preview-draggable-answer ui-draggable ui-draggable-handle']")
    public List<WebElement> preview_draggable_answer;

    @FindBy(id="dragged-ans-choice")
    public WebElement preview_answer_choice;

    @FindBy(css = "div[class='match-answer match-answer-draggable cursor-pointer ui-draggable ui-draggable-handle']")
    public List<WebElement> matchTheFollowing_preview_draggable_answer;

    @FindBy(css = "div[class='stud-dnd-match-rhs ui-droppable']")
    public List<WebElement> matchTheFollowing_preview_draggable_choice;

    @FindBy(id="answer")
    public WebElement labelAnImageText_answer;

    @FindBy(css="label[for='name']")
    public WebElement preview_questionName;

    @FindBy(css = "div[id='question-raw-content-preview']>label")
    public WebElement audioPreview_questionName;

    @FindBy(xpath = "//*[@id='answer-value']")
    public List<WebElement> labelAnImageDropDown_answer;

    @FindBy(xpath = "//*[@id='ans-val']")
    public List<WebElement> labelAnImageDropDown_CorrectAnswer;

    @FindBy(className = "part-question-check-answer")
    public List<WebElement> checkAnswerButton_MPQ;

    @FindBy(xpath = "//div[starts-with(@class,'part-evaluated-result part-question-check-answer-evaluated-result-')]")
    public WebElement mpq_notification;

    @FindBy(id="cms-question-preview-header-ass-name")
    public WebElement questionPreviewHeader;

    @FindBy(className="cms-question-preview-sidebar-title-sectn")
    public WebElement pointAvailable_label;

    @FindBy(xpath="//div[@class='cms-question-preview-header-course-name']/span")
    public WebElement previewCourseName;

    @FindBy(xpath = "//div[@class='cms-diagtest-skills-evaluted-ul']/span")
    public WebElement preview_tlos;

    @FindBy(xpath = "//div[@class='cms-diagtest-score-view']/div")
    public List<WebElement> preview_score;

    @FindBy(css = "span[class='ellipsis associated-learning-objective-text']")
    public WebElement learning_objective_text;

    @FindBy(id="learing-objectives-span")
    public WebElement learing_objectives_link;

    @FindBy(css = "div[class='cms-question-preview-sidebar-title']>div")
    public WebElement performance_label;

    @FindBy(className = "question-preview-sidebar-total-points")
    public WebElement total_point;

    @FindBy(className = "part-question-solution")
    public  WebElement multipart_solution;

    @FindBy(className = "part-question-hint")
    public  WebElement multipart_hint;

    @FindBy(css = "div[class='multi-part-question-solution-content']")
    public WebElement multipart_solutionContent;

    @FindBy(css = "div[class='multi-part-question-hint-content']")
    public WebElement multipart_hintContent;

   @FindBy(css = "button[class='next-question-part']")
    public WebElement submitPart_link;

    @FindBy(css = "button[class='next-question-part']")
    public WebElement lastSubmitPart_link;

}
