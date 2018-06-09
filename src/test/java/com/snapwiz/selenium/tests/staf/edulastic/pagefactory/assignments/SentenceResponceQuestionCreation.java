package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

/**
 * Created by root on 12/29/14.
 */
public class SentenceResponceQuestionCreation {

    @FindBy(className = "as-editor-question-type")
    WebElement label_questionTitle; //Question Title 'Sentence Response question'
    public WebElement getLabel_questionTitle(){
        return label_questionTitle;
    }

    @FindBy(id = "question-raw-content")
    WebElement textField_EnterQuestionText; //Text Field 'Enter Question Text' to enter the question Name
    public WebElement getTextField_EnterQuestionText(){
        return textField_EnterQuestionText;
    }

    @FindBy(id = "sentence-selection-raw-content-text-area")
    WebElement textArea_EnterYourParagraphHere; //Text Area 'Enter Your Paragraph here' to enter the Paragraph(Multiple options or Lines)
    public WebElement getTextArea_EnterYourParagraphHere(){
        return textArea_EnterYourParagraphHere;
    }

    @FindBy(id = "radactor-toolbar-separate")
    WebElement editor_redactorToolbar; //redactor toolbar once it is clicked on question text text field.
    public WebElement getEditor_redactorToolbar(){
        return editor_redactorToolbar;
    }



    @FindBy(id = "tab-highlight-text")
    WebElement tab_highlightText; // 'Highlight Text' Tab
    public WebElement getTab_highlightText(){
        return tab_highlightText;
    }

    @FindBy(id = "tab-edit-text")
    WebElement tab_editText; // 'Edit Text' Tab
    public WebElement getTab_editText(){
        return tab_editText;
    }

    @FindBy(id = "sentence-selection-raw-content-text-area")
    WebElement textArea_highlightText; //Text Area 'Highlight text' to highlight the text
    public WebElement getTextArea_highlightText(){
        return textArea_highlightText;
    }


    @FindBy(xpath = "//div[@id='solution']/h4")
    WebElement label_Solution; //Label 'Solution'
    public WebElement getLabel_Solution(){
        return label_Solution;
    }

    @FindBy(xpath = "//div[@id='hint']/h4")
    WebElement label_Hint; //Label 'Hint'
    public WebElement getLabel_Hint(){
        return label_Hint;
    }


    @FindBy(id = "content-solution")
    WebElement textField_EnterSolutionText; //Text Field 'Enter Solution Text' to enter Solution Text
    public WebElement getTextField_EnterSolutionText(){
        return textField_EnterSolutionText;
    }

    @FindBy(id = "content-hint")
    WebElement textField_EnterHintText; //Text Field 'Enter Hint Text' to enter Hint Text
    public WebElement getTextField_EnterHintText(){
        return textField_EnterHintText;
    }

    @FindBy(id = "footer-notification-text")
    WebElement notificationText; //Notification text 'Saved' after the question is saved
     public WebElement getNotificationText(){
        return notificationText;
    }

    @FindBy(className = "as-assignment-modal-msg")
    WebElement edit_notificationtext; // Notification text after clicking on ok
    public  WebElement getEdit_notificationtext(){
        return edit_notificationtext;
    }

    @FindBy(id = "as-render-learing-objectives-link")
    WebElement dropDown_LearningObjectives; // Dropdown 'Learning objectives() to open the TLO List'
    public WebElement getDropDown_LearningObjectives(){
        return dropDown_LearningObjectives;
    }


    @FindBy(id = "difficulty-level-drop-down")
    WebElement dropDown_DifficultyLevel; // Dropdown 'Difficulty Level' to open 'Difficulty Level'
    public WebElement getDropDown_DifficultyLevel(){
        return dropDown_DifficultyLevel;
    }

    @FindBy(className = "as-question-teacher-preview-button")
    WebElement button_Preview; // Button 'Preview' to view the question
    public WebElement getButton_Preview(){
        return button_Preview;
    }

    @FindBy(css = "div[class='lsm-createAssignment-done selected']")
    WebElement button_review; // Button 'review' to view the question
    public WebElement getButton_review(){
        return button_review;
    }

    @FindBy(className = "report-points-container")
    WebElement label_Points_1Point0; // Label 'Points: 1.0'
    public WebElement getLabel_Points_1Point0(){
        return label_Points_1Point0;
    }

    @FindBy(className = "lsm-createAssignment-input-name")
    WebElement inputTextField_SampleAssessmentName; // Input text Field 'Sample Assessment Name' to enter the assessment name
    public WebElement getInputTextField_SampleAssessmentName(){
        return inputTextField_SampleAssessmentName;
    }

    @FindBy(className = "student-performance-back-btn")
    WebElement button_leftArrow; // Left Arrow (<) button to go back to the question's page
    public WebElement getButton_leftArrow(){
        return button_leftArrow;
    }

    @FindBy(className = "learing-objectives-content-list")
    WebElement label_LearningObjectivesContent; // TLO and ELo content Text
    public WebElement getLabel_LearningObjectivesContent(){
        return label_LearningObjectivesContent;
    }


    @FindBy(className = "lsm-assignment-name-tooltip")
    WebElement errMsg_PleaseRenameUrAssessment; // Error Message 'Please rename your assessment'
    public WebElement getErrMsg_PleaseRenameUrAssessment(){
        return errMsg_PleaseRenameUrAssessment;
    }

    @FindBy(css = "div[class='as-modal-yes-btn yes-delete yes-confirm']")
    WebElement button_Yes; // Button 'Yes'
    public WebElement getButton_Yes(){
        return button_Yes;
    }

    @FindBy(id = "mark-correct-answer-option")
    WebElement inputLink_MarkAsCorrectAnswer; // input Link 'Mark As Correct Answer' after mouse hover on highlight text
    public WebElement getInputLink_MarkAsCorrectAnswer(){
        return inputLink_MarkAsCorrectAnswer;
    }

    @FindBy(id = "remove-correct-answer-option")
    WebElement inputLink_removeCorrectAnswer; // input Link 'Remove Correct Answer' after mouse hover on highlight text
    public WebElement getInputLink_removeCorrectAnswer(){
        return inputLink_removeCorrectAnswer;
    }

    @FindBy(id = "sentence-response-clear-answer-choices")
    WebElement link_clearAllAnswerChoices; // input Link 'Clear All Answer Choices' after mouse hover on highlight text
    public WebElement getLink_clearAllAnswerChoices(){
        return link_clearAllAnswerChoices;
    }

    @FindBy(css = "span[class='sentence-response-selectiontext selected-text-correct-answer']")
    WebElement text_selection; // Highlighted text
    public WebElement getText_selection(){
        return text_selection;
    }



    @FindBy(css = "span[class = 'sentence-response-selectiontext selected-text-correct-answer selectedSentance']")
    WebElement highlightedYellowText; // Highlighted text with yellow color after clicking after mouse over
    public WebElement getHighlightedYellowText(){
        return highlightedYellowText;
    }


    @FindBy(id = "remove-selected-answer-option")
    WebElement inputLink_removeAnswerChoice; // input Link 'Remove Answer Choice' after mouse hover on highlight text
    public WebElement getInputLink_removeAnswerChoice(){
        return inputLink_removeAnswerChoice;
    }

    @FindBy(id = "allow-multiple-correct-ans-checkbox")
    WebElement checkBox_AllowMultipleCorrectAnswer; // Checkbox 'Allow multiple correct answer'
    public WebElement getCheckBox_AllowMultipleCorrectAnswer(){
        return checkBox_AllowMultipleCorrectAnswer;
    }

    @FindBy(id = "writeboard")
    WebElement checkBox_allowStudentToUseScratchPad; // Checkbox 'Allow student to use Scratchpad'
    public WebElement getCheckBox_allowStudentToUseScratchPad(){
        return checkBox_allowStudentToUseScratchPad;
    }

    @FindBy(className = "fieldrow")
    WebElement label_allowStudentToUseScratchPad; // Checkbox 'Allow student to use Scratchpad'
    public WebElement getLabel_allowStudentToUseScratchPad(){
        return label_allowStudentToUseScratchPad;
    }

    @FindBy(id = "show-your-work-label")
    WebElement linkText_YourWork; // Link 'your work'
    public WebElement getlinkText_YourWork(){
        return linkText_YourWork;
    }


    @FindBy(id = "main-controls")
    WebElement toolbar_WriteboardConfigurator; // 'Writeboard' toolbar
    public WebElement getToolbar_WriteboardConfigurator(){
        return toolbar_WriteboardConfigurator;
    }


    @FindBy(className = "sentence-response-selectiontext")
    WebElement highlightedRedText; // Highlighted text with yellow color after clicking after mouse over
    public WebElement getHighlightedRedText(){
        return highlightedRedText;
    }


    @FindBy(className = "response-and-feedback-block-header")
    WebElement label_TeacherFeedBack; //teacher Feedback
    public WebElement getLabel_TeacherFeedBack(){
        return label_TeacherFeedBack;
    }

    @FindBy(id = "view-user-question-performance-feedback-box")
    WebElement feedback_content;//Feedback content
    public WebElement getFeedback_content(){return feedback_content;}

}
