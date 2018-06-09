package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by root on 12/18/14.
 */
public class AssessmentQuestionPage {

    @FindBy(xpath = "//label[text() = 'ID: ']//following-sibling::span")
    WebElement labelValue_QuestionID;//Question ID to get the ID of a question
    public WebElement getLabelValue_QuestionID() {
        return labelValue_QuestionID;
    }

    @FindBy(xpath = "//label[text() = 'Owner: ']/following-sibling::span[@class='ellipsis']")
     WebElement labelValue_Owner;// Owner 'You'
     public WebElement getLabelValue_Owner() {
     return labelValue_Owner;
     }

    @FindBy(className = "as-question-preview-edit-button")
    WebElement button_Edit;// 'Edit Button'
    public WebElement getButton_Edit() {
        return button_Edit;
    }

    @FindBy(className = "as-question-preview-delete-button")
    WebElement button_Delete;// 'Delete' Button
    public WebElement getButton_Delete() {
        return button_Delete;
    }

    @FindBy(className = "control-label")
    WebElement label_questionName;//Question Name to get the question's text
    public WebElement getLabel_questionName() {
        return label_questionName;
    }

    @FindBy(className = "as-question-teacher-preview-button")
    WebElement button_Preview;// 'Preview' Button
    public WebElement getButton_Preview() {
        return button_Preview;
    }

    @FindBy(className = "as-shuffle-ans-choices-checkbox-unchecked")
    WebElement checkBox_shuffleAnswerChoices;// Checkbox_Unchecked 'Shuffle Answer Choices'
    public WebElement getCheckBox_shuffleAnswerChoices() {
        return checkBox_shuffleAnswerChoices;
    }

    @FindBy(className = "as-shuffle-ans-choices-checkbox-checked")
    WebElement checkBoxChecked_shuffleAnswerChoices;// Checkbox_Checked 'Shuffle Answer Choices'
    public WebElement getCheckBoxChecked_shuffleAnswerChoices() {
        return checkBoxChecked_shuffleAnswerChoices;
    }

    @FindBy(xpath = "(//div[@class='ellipsis'])[1]")
    WebElement label_tlo;// Tlo Text to get the whole tlo text
    public WebElement getLabel_tlo() {
        return label_tlo;
    }

    @FindBy(xpath = "(//div[@class='ellipsis'])[2]")
    WebElement label_elo;// ELO Text to get the whole elo text
    public WebElement getLabel_elo() {
        return label_elo;
    }

    @FindBy(css="div[class='as-modal-yes-btn yes-delete yes-delete-question']")
    WebElement yesButton_Delete; // Yes button to delete question
    public WebElement getYesButton_Delete(){ return yesButton_Delete; }

    @FindBy(id="sentence-selection-raw-content-div-heighlight")
    WebElement textArea_Passage; // Text Area Passage
    public WebElement getTextArea_Passage(){ return textArea_Passage; }
    @FindBy(id="view-user-question-performance-score-box")
    WebElement value_QuestionPerformanceScore; // Value for Question performance score
    public WebElement getValue_QuestionPerformanceScore(){ return value_QuestionPerformanceScore; }

}
