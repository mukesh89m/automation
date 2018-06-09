package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by Dharaneesh T Gowda on 20-01-2015.
 */
public class TrueFalseQuestionCreation {
    @FindBy(id = "question-raw-content")
    WebElement textField_EnterQuestionText; //Text Field 'Enter Question Text'
    public WebElement getTextField_EnterQuestionText(){
        return textField_EnterQuestionText;
    }

    @FindBy(xpath = "//span[@class='true-false-answer-label' and text() = 'A']")
    WebElement button_True; //Button 'True'
    public WebElement getButton_True(){
        return button_True;
    }
    @FindBy(xpath = "//span[@class='true-false-student-answer-label' and text() = 'A']")
    WebElement button_True_StudentSide; //Button 'True' in student view
    public WebElement getButton_True_StudentSide(){
        return button_True_StudentSide;
    }
    @FindBy(xpath = "//span[@class='true-false-answer-label' and text() = 'B']")
    WebElement button_False; //Button 'False'
    public WebElement getButton_False(){
        return button_False;
    }

    @FindBy(className = "lsm-createAssignment-input-name") //class name for the Assessment Name
            WebElement assessmentTextBoxField;
    public WebElement getAssessmentTextBoxField(){
        return assessmentTextBoxField;
    }


    @FindBy(xpath = "//div[normalize-space(@class) ='true-false-answer-select true-false-answer-clicked']//span[@class='true-false-answer-label' and text() = 'B']")
    WebElement button_FalseClicked; //Button 'False' after it is clicked
    public WebElement getButton_FalseClicked(){
        return button_FalseClicked;
    }

    @FindBy(xpath = "//div[normalize-space(@class) ='true-false-answer-select true-false-answer-clicked']//span[@class='true-false-answer-label' and text() = 'A']")
    WebElement button_TrueClicked; //Button 'True' after it is clicked
    public WebElement getButton_TrueClicked(){
        return button_TrueClicked;
    }

    @FindBy(id="as-take-next-question")
    WebElement button_submit;//Submit button
    public WebElement getButton_submit(){return button_submit;}

    @FindBy(className = "as-question-teacher-preview-button")
    WebElement button_Preview; // Button 'Preview' to view the question
    public WebElement getButton_Preview(){
        return button_Preview;
    }

    @FindBy(id="question-review-submit-column")
    WebElement preview_submit;//Submit button on Preview page
    public WebElement getPreview_submit(){return preview_submit;}

    @FindBy(id="saveQuestionDetails1")
    WebElement save_button;//Save button
    public WebElement getSave_button(){return save_button;}

    @FindBy(className = "points-input-edit-icon")
    WebElement point_editor;//Point editor to edit the point
    public WebElement getPoint_editor(){return point_editor;}

    @FindBy(id = "points-input-tag")
    WebElement point_textbox;//Point text box to enter the point
    public WebElement getPoint_textbox(){return point_textbox;}

    @FindBy(css = ".as-assignmentReview-headerRight div.createQuestion-review-button")
    List<WebElement> button_review;// 'Review' Button
    public WebElement getButton_review() {
        int size=button_review.size();
        return button_review.get(size-1);
    }

    @FindBy(id="footer-notification-text")
    WebElement error_savingQuestion;//Error message while saving the question
    public WebElement getError_savingQuestion(){return error_savingQuestion;}

    @FindBy(css = "span[class^='save-points tick']")
    public WebElement button_savePoint;// Save button to save point




}
