package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by pragya on 22-01-2015.
 */
public class MatchingTablesQuestionCreation {

    @FindBy(css = "span[class='as-editor-question-type']")
    WebElement questionTitle;// Question title
    public WebElement getQuestionTitle(){return questionTitle;}

    @FindBy(id = "question-raw-content")
    WebElement textBox_QuestionField;//Question edit box to type the question or to get the default value
    public WebElement getTextBox_QuestionField(){return textBox_QuestionField;}

    @FindBys(@FindBy(xpath = "//div[starts-with(@class,'matrix-text-area matching-text')]"))
    List<WebElement> list_editbox_answerChoice;//Answer choice edit box
    public List<WebElement> getList_editbox_answerChoice(){return list_editbox_answerChoice;}

    @FindBy(xpath="(//img[@id='answer_choice_edit'])[1]")
    WebElement answerChoice_text;//Text answer choice to create text answer
    public  WebElement getAnswerchoice_text(){return answerChoice_text;}

    @FindBy(id="answer_math_edit")
    WebElement answerChoice_mathML;//MAthML answer choice to create MAthML answer
    public  WebElement getAnswerChoice_mathML(){return answerChoice_mathML;}

    @FindBy(id="answer_image_edit")
    WebElement answerChoice_image;//image answer choice to create MAthML answer
    public  WebElement getAnswerChoice_image(){return answerChoice_image;}

    @FindBy(xpath = "//input[@id='multiple-selection-check-btn']/following-sibling::label")
    WebElement checkBox_allowMultipleSelection;//Allow multiple selection checkbox
    public WebElement getCheckBox_allowMultipleSelection(){return checkBox_allowMultipleSelection;}

    @FindBys(@FindBy(xpath = "//div[@class='matrix-box-select-wrapper']/input/following-sibling::label"))
    List<WebElement> list_checkboxOrRadioButtonInTable;//List of checkbox or radio buttons present in table to check or uncheck
    public List<WebElement> getList_checkboxOrRadioButtonInTable(){return list_checkboxOrRadioButtonInTable;}

    @FindBys(@FindBy(xpath = "//div[@class='matrix-box-select-wrapper']/input"))
    List<WebElement> list_checkboxOrRadioButtonToVerify;//List of checkbox or radio buttons present in table to verify radio or checkbox
    public List<WebElement> getList_checkboxOrRadioButtonToVerify(){return list_checkboxOrRadioButtonToVerify;}

    @FindBys(@FindBy(xpath = "//table[@id='matrix-question-table']/thead/following-sibling::tbody/tr"))
    List<WebElement> list_rowsInTable;//List of rows in table
    public List<WebElement> getList_rowsInTable(){return list_rowsInTable;}

    @FindBys(@FindBy(xpath = "//tbody/tr[1]/td[@class='editable-cell']"))
    List<WebElement> list_columnsInTable;//List of columns in table
    public List<WebElement> getList_columnsInTable(){return list_columnsInTable;}

    @FindBy(id="add-new-matrix-row")
    WebElement link_addNewRow;//Add new row link
    public WebElement getLink_addNewRow(){return link_addNewRow;}

    @FindBy(id="add-new-matrix-column")
    WebElement link_addNewColumn;//Add new row link
    public WebElement getLink_addNewColumn(){return link_addNewColumn;}

    @FindBy(xpath = "(//div[@class='matrix-row-delete cursor-pointer']/img)[2]")
    WebElement button_delete;//Delete button to delete row or column
    public WebElement getButton_delete(){return button_delete;}

    @FindBy(css="label[class='writeboardchkbox as-writeboard-checkbox-unchecked']")
    WebElement checkbox_scratchpad;//Scratchpad check box
    public WebElement getCheckbox_scratchpad(){return checkbox_scratchpad;}

    @FindBy(id="saveQuestionDetails1")
    WebElement button_save;//Save button
    public WebElement getButton_save(){return button_save;}

    @FindBy(id="footer-notification-text")
    WebElement message_text;//Message beside the question type
    public WebElement getMessage_text(){return message_text;}

    @FindBy(className = "as-question-teacher-preview-button")
    WebElement button_preview;//Preview button
    public WebElement getButton_preview(){return button_preview;}

    @FindBy(id="question-reveview-submit")
    WebElement preview_submit;//Submit button on Preview page
    public WebElement getPreview_submit(){return preview_submit;}

    @FindBy(id="content-solution")
    WebElement textbox_solution;//Solution text box
    public WebElement getTextbox_solution(){return textbox_solution;}

    @FindBy(id="content-hint")
    WebElement textbox_hint;//Hint text box
    public WebElement getTextbox_hint(){return textbox_hint;}

    @FindBy(id="difficulty-level-drop-down")
    WebElement difficulty_level;//Difficulty level drop down
    public WebElement getDifficulty_level(){return difficulty_level;}

    @FindBy(className = "as-footer-learning-objectives")
    WebElement learningObjective;//Learning objective
    public WebElement getLearningObjective(){return learningObjective;}

    @FindBys(@FindBy(css="span[class='ellipsis associated-learning-objective-text']"))
    List<WebElement>list_tloElo_learningObjective;//Tlo and Elo
    public List<WebElement> getList_tloElo_learningObjective(){return list_tloElo_learningObjective;}

    @FindBy(id="link-add-learning-objectives")
    WebElement add_learningObjetive;//Add Learning Objective link
    public WebElement getAdd_learningObjetive(){return add_learningObjetive;}

    @FindBy(xpath = "//div[contains(@title,'1.OA.A.2')]/label")
    WebElement checkbox_elo1_OA_A_2;//Checkbox of 1.OA.A.2 ELO
    public WebElement getCheckbox_elo1_OA_A_2(){return checkbox_elo1_OA_A_2;}

    @FindBy(xpath = "//span[@title='Associate']")
    WebElement button_associate;//Associate button in learning objective
    public WebElement getButton_associate(){return button_associate;}

    @FindBy(className = "learning-objective-close-btn")
    WebElement closeButton_learningObjective;//Learning objective close button
    public WebElement getCloseButton_learningObjective(){return closeButton_learningObjective;}

    @FindBy(css = "button[class='btn btn-rounded btn-default lsm-createAssignment-done m-r-md selected btn-blue']")
    WebElement button_review;// 'Review' Button
    public WebElement getButton_review() {return button_review;}

    @FindBy(className = "as-question-preview-edit-button")
    WebElement editLink;//Edit link
    public WebElement getEditLink(){  return editLink;}

    @FindBys(@FindBy(css = "div[class='matrix-text-area matching-text']"))
    List<WebElement> list_editbox_answerChoice_editPage;//Answer choice edit box
    public List<WebElement> getList_editbox_answerChoice_editPage(){return list_editbox_answerChoice_editPage;}

    @FindBy(xpath = "(//div[@class='answer-matrix-con matrix'])/img")
    WebElement uploadedImage;//Uploaded image
    public WebElement getUploadedImage(){return uploadedImage;}

    @FindBy(xpath = "//td[@class='delete-cell matrix-box-bg-highlight']/div/img")
    WebElement deleteButton_2nd_row;//Delete button to delete row
    public WebElement getDeleteButton_2nd_row(){return deleteButton_2nd_row;}

    @FindBy(xpath = "//th[@class='delete-cell matrix-box-bg-highlight']/div/img")
    WebElement deleteButton_2nd_column;//Delete button to delete column
    public WebElement getDeleteButton_2nd_column(){return deleteButton_2nd_column;}

    @FindBys(@FindBy(xpath = "//input[starts-with(@id,'matrix-box-preview-btn')]/parent::div/parent::div"))
    List<WebElement> listofanswers_radiobutton;//Radio button with right and wrong answers
    public List<WebElement> getListofanswers_radiobutton(){return listofanswers_radiobutton;}

    @FindBy(xpath = "//div[@class='lsm-createAssignment-total-questions']")
    WebElement noofquestions;//Number of questions on review button
    public WebElement getNoofquestions(){return noofquestions;}

    @FindBy(css = "div[class='answer-matrix-con matrix label-to-control']")
    WebElement answerChoice_1st;//1st answer choice to edit
    public WebElement getAnswerChoice_1st(){return answerChoice_1st;}




}
