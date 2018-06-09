package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.w3c.dom.ls.LSInput;

import java.util.List;

/**
 * Created by root on 12/19/14.
 */
public class QuestionEditPage {
    @FindBy(id = "question-raw-content")
    WebElement textBox_QuestionEditField;// Questions Name in True False Question's Edit Field
    public WebElement getTextBox_QuestionEditField() {
        return textBox_QuestionEditField;
    }


    @FindBy(className = "text-drop-val")
    WebElement textBox_AnswerEditField;//Answer Choice 1 Text Field
    public WebElement getTextBox_AnswerEditField() {
        return textBox_AnswerEditField;
    }


    @FindBy(id = "ans1")
    WebElement textBox_Answer1EditField;// Answer choice 1 Edit Text Field
    public WebElement getTextBox_Answer1EditField() {
        return textBox_Answer1EditField;
    }


    @FindBy(css = "option[value = 'Answer4Answer1']")
    WebElement textBox_Answer1PostEditField;// Question Text Field in Text Dropdown Question after editing
    public WebElement getTextBox_Answer1PostEditField() {
        return textBox_Answer1PostEditField;
    }


    @FindBy(className = "swformula")
    WebElement textBox_formulaTypeAnswerPostEditField;// Question Text Field in Text Dropdown Question after editing
    public WebElement getTextBox_formulaTypeAnswerPostEditField() {
        return textBox_formulaTypeAnswerPostEditField;
    }

    @FindBy(xpath = "//div[@id = 'question-raw-content']//input[@value = '700']")
    WebElement textBox_QuestionPostEditField;// Numeric Entry Questions Edit Field after Editing
    public WebElement getTextBox_QuestionPostEditField() {
        return textBox_QuestionPostEditField;
    }

    @FindBy(className = "select-icon-text-drop-down")
    WebElement icon_Answer1SelectSymbol;// Questions Name in True False Question's Edit Field
    public WebElement getIcon_Answer1SelectSymbol() {
        return icon_Answer1SelectSymbol;
    }


    @FindBy(id = "question-mc-raw-content")
    WebElement textBox_MultipleChoiceQuestionEditField;// Questions Name in Multiple Choice Question's Edit Field
    public WebElement getTextBox_MultipleChoiceQuestionEditField() {
        return textBox_MultipleChoiceQuestionEditField;
    }



    @FindBy(id = "question-ms-raw-content")
    WebElement textBox_MultipleSelectionEditField;// Questions Name in Multiple Selection Question's Edit Field
    public WebElement getTextBox_MultipleSelectionEditField() {
        return textBox_MultipleSelectionEditField;
    }


    @FindBy(id = "saveQuestionDetails1")
    WebElement button_Save;// 'Save' Button to save the Question
    public WebElement getButton_Save() {
        return button_Save;
    }

    @FindBy(css = "span[class='done-button btn sty-green text-drop-accept accept_answer']")
    WebElement button_AcceptAnswer;// 'Accept Answer' Button to save the Question
    public WebElement getButton_AcceptAnswer()
    {
        return button_AcceptAnswer;
    }


     //For Text Entry Question

     @FindBy(css = "input[class='numeric_text_entry_input get-user-entry']")
    WebElement textBox_CorrectAnswerField;// Correct Answer Text Field in Text Entry Questions
    public WebElement getTextBox_CorrectAnswerField() {
        return textBox_CorrectAnswerField;
    }


    @FindBy(css = "span[class='btn sty-green save-spanish-text accept_answer text_entry_accept_answer']")
    WebElement button_AcceptAnswer_TextEntry;// 'Accept Answer' Button to accept the answer for text entry question
    public WebElement getButton_AcceptAnswer_TextEntry()
    {
        return button_AcceptAnswer_TextEntry;
    }


    @FindBy(xpath = "//div[@id = 'question-raw-content']//input")
    WebElement textBox_QuestionPostEditField_TextEntry;// Text Entry Questions Edit Field after Editing
    public WebElement getTextBox_QuestionPostEditField_TextEntry() {
        return textBox_QuestionPostEditField_TextEntry;
    }




    //For Numeric Entry with Units
    @FindBy(css = "input[class='numeric_unit_correct_answer numeric_text_entry numeric_text_entry_input']")
    WebElement textBox_CorrectAnswerEditField;//textbox 'Correct Answer Text Field Value' for Numeric Entry with Units
    public WebElement getTextBox_CorrectAnswerEditField() {
        return textBox_CorrectAnswerEditField;
    }


    @FindBy(css = "span[class='done-button btn sty-green tab_event_widget num_ent_unit_accept_answer']")
    WebElement button_AcceptAnswer_NumericEntry;// 'Accept Answer' Button to accept the answer for Numeric Entry with unitsQuestions
    public WebElement getButton_AcceptAnswer_NumericEntry()
    {
        return button_AcceptAnswer_NumericEntry;
    }



    //For Advanced Numeric Question Types
    @FindBy(css = "input[class='numeric_correct_text_entry_input num-entry-ans border-color-gray']")
    WebElement textField_CorrectAnswer_AdvancedNumeric;// Correct Answer Text Field in Advanced Numeric
    public WebElement getTextField_CorrectAnswer_AdvancedNumeric()
    {
        return textField_CorrectAnswer_AdvancedNumeric;
    }

    @FindBy(css = "span[class='done-button btn sty-green tab_event_widget accept_answer accept_answer_maple_numeric']")
    WebElement button_AcceptAnswer_AdvancedNumeric;// 'Accept Answer' Button to accept the answer for Advanced Numeric Questions
    public WebElement getButton_AcceptAnswer_AdvancedNumeric()
    {
        return button_AcceptAnswer_AdvancedNumeric;
    }


    @FindBy(id = "answer_math_edit")
    WebElement textField_CorrectAnswer_expressionEvaluator;// Correct Answer Edit Field in Expression Evaluator in Edit Page
    public WebElement getTextField_CorrectAnswer_expressionEvaluator()
    {
        return textField_CorrectAnswer_expressionEvaluator;
    }

    @FindBy(className = "wrs_focusElement")
    WebElement textArea_CorrectAnswerEditWindow_expressionEvaluator;// Correct Answer Edit Window  in Expression Evaluator in Edit Page
    public WebElement getTextArea_CorrectAnswerEditWindow_expressionEvaluator()
    {
        return textArea_CorrectAnswerEditWindow_expressionEvaluator;
    }


    @FindBy(css = "button[title = 'Square root']")
    WebElement button_SquareRoot_expressionEvaluator;// Square root button in expression evaluator's Correct answer edit field window
    public WebElement getButton_SquareRoot_expressionEvaluator()
    {
        return button_SquareRoot_expressionEvaluator;
    }


    @FindBy(id = "wiris-answer-container-save-choice1")
    WebElement button_Save_expressionEvaluator;// Save button in expression evaluator's Correct answer edit field window
    public WebElement getButton_Save_expressionEvaluator()
    {
        return button_Save_expressionEvaluator;
    }




    // For Expression Evaluator
    @FindBy(css = "span[class='done-button btn sty-green tab_event_widget accept_answer maple_symc_not_accept_answer']")
    WebElement button_AcceptAnswer_expressionEvaluator;// 'Accept Answer' Button to accept the answer for Expression Evaluator
    public WebElement getButton_AcceptAnswer_expressionEvaluator()
    {
        return button_AcceptAnswer_expressionEvaluator;
    }





    @FindBy(className = "as-question-editor-back")
    WebElement back_arrow;
    public WebElement getBack_arrow(){return back_arrow;}





}
