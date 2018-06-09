package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.preview;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by Mukesh on 12/19/14.
 */
public class PreviewPage {

    //For true false question
    @FindBy(css = "div[class='center header-title']")
    WebElement previewTitle;
    public WebElement getPreviewPageTitle() {
        return previewTitle;
    }

    @FindBy(id = "question-edit")
    WebElement questionText;
    public WebElement getQuestionText() {
        return questionText;
    }

    @FindBy(css = "span[class='true-false-student-answer-label']")
    List<WebElement> answerOption;
    public List<WebElement> getAnswerOption() {
        return answerOption;
    }

    @FindBy(css = "span[class='btn sty-blue submit-button pull-right']")
    WebElement submitButton;
    public WebElement getSubmitButton() {
        return submitButton;
    }

    @FindBy(css = "div[class='true-false-student-tick true-false-student-right']") //correct tick mark
    WebElement correctTickMark;
    public WebElement getCorrectTickMark() {
        return correctTickMark;
    }

    @FindBy(xpath = "(//div[@id='cms-question-preview-question-hint-wrapper']/div)[1]")
    WebElement hintLabel;
    public WebElement getHintLabel() {
        return hintLabel;
    }

    @FindBy(className = "cms-question-preview-question-hint-content")
    WebElement hintContent;
    public WebElement getHintContent() {
        return hintContent;
    }

    @FindBy(xpath = "//div[@id='cms-question-preview-question-solution-wrapper']/div[1]")
    WebElement solutionLabel;

    public WebElement getSolutionLabel() {
        return solutionLabel;
    }

    @FindBy(className = "cms-question-preview-question-solution-content")
    WebElement solutionLabelContent;

    public WebElement getSolutionLabelContent() {
        return solutionLabelContent;
    }

    @FindBy(id = "points-input-tag")
    WebElement points;
    public WebElement getPoints() {
        return points;
    }

    //For multiple selection question

    @FindBy(css = "div[class='multiple-select-choice-icon-preview multiple-select-choice-icon-deselect']")
    List<WebElement> multipleSelectionAnswerOption;
    public List<WebElement> getMultipleSelectionAnswerOption() {
        return multipleSelectionAnswerOption;
    }

    @FindBy(className = "true-tick") //correct tick mark for multiple selection
    WebElement multipleSelectionCorrectTickMark;
    public WebElement getMultipleSelectionCorrectTickMark() {
        return multipleSelectionCorrectTickMark;
    }

    //For multiple choice
    @FindBy(css = "div[class='single-select-choice-icon-preview single-select-choice-icon-deselect']")
    List<WebElement> multipleChoiceAnswerOption;
    public List<WebElement> getMultipleChoiceAnswerOption() {
     return multipleChoiceAnswerOption;
    }

    //For text entry
    @FindBy(xpath = "//input[starts-with(@id,'text_')]")
    WebElement inputTextEntry;
    public WebElement getInputTextEntry() {
        return inputTextEntry;
    }

    @FindBy(css = "input[class='visible_redactor_input div-preview-wrong-answer']")
    WebElement wrongTickMark;

    public WebElement getWrongTickMark() {
        return wrongTickMark;
    }

    //Priyanka das
    //for draganddrop

    @FindBy(id = "dragged-ans-choice")
    WebElement blankSpace;

    public WebElement getBlankSpace() {
        return blankSpace;
    }

    @FindBy(xpath = "//div[@class='student-dnd-answer-area ui-droppable']")
    WebElement answerchoice;

    public WebElement getAnswerchoice() {
        return answerchoice;
    }

    @FindBy(id = "question-review-submit")
    WebElement get_submit;
    public WebElement getGet_submit() {
        return get_submit;
    }

    @FindBy(xpath = "(//div[@class='dnd-preview-answer'])[1]")
    WebElement get_answerchoice1;

    public WebElement getGet_answerchoice1() {
        return get_answerchoice1;
    }

    @FindBy(xpath = "(//div[@class='dnd-preview-answer'])[2]")
    WebElement get_answerchoice2;

    public WebElement getGet_answerchoice2() {
        return get_answerchoice2;
    }

    @FindBy(xpath = "(//div[@class='dnd-preview-answer'])[3]")
    WebElement get_answerchoice3;

    public WebElement getGet_answerchoice3() {
        return get_answerchoice3;
    }

    @FindBy(css = "div[class='dnd-previewDropArea drop-container background-white ui-droppable wrong-answer']")
    WebElement wrongAnswer;
    public WebElement getWrongAnswer() {
        return wrongAnswer;
    }

    //for essay type question
    @FindBy(css = "div[id^='html-editor-non-draggable']")
    WebElement textinputfield;
    public WebElement getTextinputfield() {
        return textinputfield;

    }

    //for label an imagetext
    @FindBy(id = "answer")
    WebElement answerBox;

    public WebElement getAnswerBox() {
        return answerBox;
    }

    @FindBy(className = "wrong-answer")
    WebElement answer;

    public WebElement getAnswer() {
        return answer;
    }

    //for label an image drop down
    @FindBy(id = "answer-value")
    WebElement dropDown_labelAnImageDropDown;
    public WebElement getDropDown_labelAnImageDropDown() {
        return dropDown_labelAnImageDropDown;
    }

    @FindBy(id = "stud-view-label-drpdwn-answerText")
    WebElement imageDropDownAnswer;
    public WebElement getImageDropDownAnswer() {
        return imageDropDownAnswer;
    }

    //for resequence
    @FindBy(css = "div[class='resequence-answer-normalText label-to-control']")
    WebElement answerOptionResequence;

    public WebElement getAnswerOptionResequence() {
        return answerOptionResequence;

    }

    @FindBy(xpath = "(//div[@class='resequence-answer-normalText label-to-control'])[1]")
    WebElement answerOptionResequence1;
    public WebElement getAnswerOptionResequence1() {
        return answerOptionResequence1;
    }

    @FindBy(xpath = "(//div[@class='resequence-answer-normalText label-to-control'])[2]")
    WebElement answerOptionResequence2;
    public WebElement getAnswerOptionResequence2() {
        return answerOptionResequence2;
    }

    @FindBy(css = "div[class='student-resequence-answer-choice hover-border wrongAnswerChoice']")
    WebElement wrongAnswerResequence;
    public WebElement getWrongAnswerResequence() {
        return wrongAnswerResequence;
    }


    //for cloze formula
    @FindBy(css = "div[class='match-answer cloze-formula-answer-draggable cursor-pointer ui-draggable']")
    WebElement answerChoice;
    public WebElement getAnswerChoice() {
        return answerChoice;
    }

    @FindBy(id = "ans_ch_1")
    WebElement answerChoice1_clozeFormula;
    public WebElement getAnswerChoice1_clozeFormula() {
        return answerChoice1_clozeFormula;
    }

    @FindBy(id = "ans_ch_2")
    WebElement answerChoice2_clozeFormula;
    public WebElement getAnswerChoice2_clozeFormula() {
        return answerChoice2_clozeFormula;
    }

    @FindBy(id = "ans_ch_3")
    WebElement answerChoice3_clozeFormula;
    public WebElement getAnswerChoice3_clozeFormula() {
        return answerChoice3_clozeFormula;
    }

    @FindBy(id = "ans_ch_4")
    WebElement answerChoice4_clozeFormula;
    public WebElement getAnswerChoice4_clozeFormula() {
        return answerChoice4_clozeFormula;
    }

    @FindBy(id = "ans_ch_5")
    WebElement answerChoice5_clozeFormula;
    public WebElement getAnswerChoice5_clozeFormula() {
        return answerChoice5_clozeFormula;
    }


    @FindBy(xpath = "(//div[@class='dnd-match-rhs box cloze-formula-rhs ui-droppable'])[1]")
    WebElement blankSpace1;
    public WebElement getBlankSpace1() {
        return blankSpace1;
    }

    @FindBy(xpath = "(//div[@class='dnd-match-rhs box cloze-formula-rhs ui-droppable'])[2]")
    WebElement blankSpace2;
    public WebElement getBlankSpace2() {
        return blankSpace2;
    }

    @FindBy(xpath = "(//div[@class='dnd-match-rhs box cloze-formula-rhs ui-droppable'])[3]")
    WebElement blankSpace3;
    public WebElement getBlankSpace3() {
        return blankSpace3;
    }

    @FindBy(xpath = "(//div[@class='dnd-match-rhs box cloze-formula-rhs ui-droppable'])[4]")
    WebElement blankSpace4;
    public WebElement getBlankSpace4() {
        return blankSpace4;
    }

    @FindBy(xpath = "(//*[@id='matching-question-tbl']//tr/td[3])[5]/div")
    WebElement blankSpace5;
    public WebElement getBlankSpace5() {
        return blankSpace5;
    }

    @FindBy(xpath = "//img[@id='eval-img']")
    WebElement wrongAnswerClozeFormula;
    public WebElement getWrongAnswerClozeFormula() {
        return wrongAnswerClozeFormula;
    }


    //for Numeric Entry With Units
    @FindBy(className = "numeric_entry_student_input")
    WebElement textField_QuestionInputNumericEntry;//Question text field to provide right or wrong answer
    public WebElement getTextField_QuestionInputNumericEntry() {
        return textField_QuestionInputNumericEntry;
    }


    @FindBy(className = "numeric_entry_student_title numeric_entry_student_wrong")
    WebElement redBorderColor_NumericEntry;//Red border color if answer choice is wrong
    public WebElement getRedBorderColor_NumericEntry() {
        return redBorderColor_NumericEntry;
    }


    //For Advanced Numeric

    @FindBy(css = "input[class='visible_redactor_input bg-color-white']")
    WebElement textField_AdvancedNumeric;//Question Text Field in Advanced Numeric Preview Page
    public WebElement getTextField_AdvancedNumeric() {
        return textField_AdvancedNumeric;
    }


    @FindBy(css = "button[title = 'Square root']")
    WebElement button_SquareRoot_expressionEvaluator;// Square root button in Expression Evaluator Correct answer edit field window
    public WebElement getButton_SquareRoot_expressionEvaluator() {
        return button_SquareRoot_expressionEvaluator;
    }



    //For expression evaluator
    @FindBy(id = "answer_math_edit")
    WebElement answeringSpace_ExpressionEvaluatorPreview;// Answering space button in Expression Evaluator preview page
    public WebElement getAnsweringSpace_ExpressionEvaluatorPreview() {
        return answeringSpace_ExpressionEvaluatorPreview;
    }


    @FindBy(className = "wrs_focusElement")
    WebElement textArea_CorrectAnswerEditWindow_expressionEvaluator;// Correct Answer Edit Window  in Expression Evaluator in Edit Page
    public WebElement getTextArea_CorrectAnswerEditWindow_expressionEvaluator() {
        return textArea_CorrectAnswerEditWindow_expressionEvaluator;
    }


    @FindBy(id = "wiris-answer-container-save-choice1")
    WebElement button_Save_expressionEvaluator;// Save button in expression evaluator's Correct answer edit field window
    public WebElement getButton_Save_expressionEvaluator() {
        return button_Save_expressionEvaluator;
    }

    @FindBy(id = "footer-notification-text")
    WebElement notificationText; //Notification text 'Saved' after the question is saved
    public WebElement getNotificationText(){
        return notificationText;
    }

    @FindBy(className = "control-label")
    WebElement questionTitle; //question Title
    public WebElement getQuestionTitle(){
        return questionTitle;
    }

    //For Number Line

    @FindBy(id = "question-raw-content-preview")
    WebElement question_text;//Question text
    public WebElement getQuestion_text(){return question_text;}

    @FindBys({@FindBy(css = "li[class='numberLine-label-answer-choice-hover numberLine-preview-answer-choice-hover ui-draggable']")})
    List<WebElement> list_answerChoice;//List of answer choice
    public List<WebElement> getList_answerChoice(){return list_answerChoice;}

    @FindBy(xpath = "//*[contains(text(),'Point')]")
    WebElement questionPoint_previewPage;//Question point on Preview page
    public WebElement getQuestionPoint_previewPage(){return questionPoint_previewPage;}

    @FindBy(css = "div[class = 'tab-link-preview tab-link tab-title current']") //Tab 'Header1 Title'
    WebElement tab_FirstHeader1Title;
    public WebElement getTab_FirstHeader1Title()
    {
        return  tab_FirstHeader1Title;
    }

    //Generic

    @FindBy(id = "as-question-preview-wrapper")
    WebElement preview_page;//entire preview page
    public WebElement getPreview_page(){return preview_page;}


    //MatchingTables
    @FindBy(xpath = "(//div[@class='matrix-equation-area matrix-wrap'])[2]/img")
    WebElement image_answerchoice;//Uploaded image as answer choice
    public WebElement getImage_answerchoice(){return image_answerchoice;}

    @FindBys(@FindBy(xpath = "//div[@class='matrix-box-select-wrapper']/input/following-sibling::label"))
    List<WebElement> list_radiobuttonorcheckbox;//Radio button or checkbox on preview page and question creation page
    public List<WebElement> getList_radiobuttonorcheckbox(){return list_radiobuttonorcheckbox;}

    @FindBy(xpath = "//*[@fill='#73B966']")
    public List<WebElement> performanceBar_green;//Performance bar green

    @FindBy(xpath = "//div[@class='ibox-content text-center']/div")
    public List<WebElement> scoreBox;//Score box

    @FindBy(xpath = "//*[@class='fa fa-times-circle close-btn-white']")
    public WebElement close;//Close

    @FindBy(className= "close")
    public WebElement close_previewQuestion;

    @FindBy(id = "as-take-next-question")
    public WebElement button_next;//Next button

    @FindBy(className = "question-raw-content-dropdown")
    public WebElement textDropDown;//Text drop down

    //For Multipart
    @FindBy(xpath = "//div[@class='multi-select-choice-icon-preview multi-select-choice-icon-deselect']")
    public List<WebElement> option_multipleSelection_multipart;//Multiple selection option


    //For Match the following
    @FindBy(css = "div[class='answer-con match formula-to-control']")
    public List<WebElement> mathMlEditBox;//Math ml edit box

    @FindBy(css = "div[class='answer-con match label-to-control']")
    public List<WebElement> text_editBox;//Text edit box

    @FindBy(css = "div[class='stud-dnd-match-rhs ui-droppable']")
    public List<WebElement> editBox_droppable;//Droppable edit box


    //For classification
    @FindBy(css = "div.dnd-preview-answer.answer")
    public List<WebElement> answerChoiceToDrop;//answer choice to drop

    @FindBy(css = "div[class='classification-div-content']")
    public List<WebElement> classToDrop;//Class to drop answer choice


    //For cloze matrix
    @FindBy(xpath = "//*[@id='matrix-question-table-preview']/descendant::div[1]/input")
    public List<WebElement> input_clozeMatrix;//Cloze matrix input box


    //For fraction editor
    @FindBy(css = "div.rect-column")
    public List<WebElement> column_fractionEditor;//Fraction editor column


    //For sentence response

    @FindBy(css = "span[class='sentence-response-selectiontext selected-text-correct-answer']")
    WebElement text_selection; // Highlighted text
    public WebElement getText_selection(){
        return text_selection;
    }

    @FindBy(xpath = "//span[contains(@class,'sentence-response-selectiontext')]")
    public List<WebElement> textToSelect;//Texts to be selected



    @FindBy(className = "student-report-continue-button")
    public WebElement button_continue;//Click on continue button

    @FindBy(id = "view-user-question-performance-score-box")
    public WebElement scoreObtained; //Score obtained for individula question

    @FindBy(xpath = "//div[@id='next-question-performance-view']")
    public WebElement nextArrow;//Next arrow

    @FindBy(id = "question-points")
    public WebElement questionPoint;//Question point

    @FindBy(xpath = "//div[@class='view-user-question-performance-score']")
    public WebElement totalScoreObtained;//Tota score obtained

}
