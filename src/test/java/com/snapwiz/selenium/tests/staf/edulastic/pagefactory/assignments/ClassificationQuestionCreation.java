package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by pragya on 13-01-2015.
 */
public class ClassificationQuestionCreation {
    @FindBy(css = "span[class='as-editor-question-type']")
    WebElement questionTitle;// Question title
    public WebElement getQuestionTitle(){return questionTitle;}

    @FindBy(id = "question-raw-content")
    WebElement textBox_QuestionField;//Question edit box to type the question or to get the default value
    public WebElement getTextBox_QuestionField(){return textBox_QuestionField;}

    @FindBys({@FindBy(className ="classification-name")})
    List<WebElement> list_classNames;//List of 'Enter Class Name'
    public List<WebElement> getList_classNames(){return list_classNames;}

    @FindBys({@FindBy(css = "li[class='classification ui-draggable']")})
    List<WebElement> list_classArea;//List of class area
    public List<WebElement> getList_classArea(){return list_classArea;}

    @FindBy(id = "add-new-classification")
    WebElement link_addNewClass;//"Add new class" link
    public WebElement getLink_addNewClass(){return link_addNewClass;}

    @FindBys({@FindBy(className= "answer")})
    List<WebElement> list_enterAnswerChoice;//"Enter answer choices" text boxes
    public List<WebElement> getList_enterAnswerChoice(){return  list_enterAnswerChoice;}

    @FindBys(@FindBy(xpath = "//ul[@id='answer_choices']/li"))
    List<WebElement> list_answerChoiceEditBox;// List of answer choice edit box to find enabled or disabled edit box
    public List<WebElement> getList_answerChoiceEditBox(){return list_answerChoiceEditBox;}

    @FindBy(id = "add-new-dnd-answer-choice")
    WebElement link_addNewAnswerChoice;// "Add new answer choice" link
    public WebElement getLink_addNewAnswerChoice(){return link_addNewAnswerChoice;}

    @FindBy(id="delete_answer_choice")
    WebElement delete_answerChoice;//Delete icon to delete the answer choice
    public WebElement getDelete_answerChoice(){return delete_answerChoice;}

    @FindBys(@FindBy(id="ans-drag-btn"))
    List<WebElement> list_drag_answerChoice;//List of Drag button to drag the answer choice
    public List<WebElement> getList_drag_answerChoice(){return list_drag_answerChoice;}

    @FindBy(id="ans-drag-btn")
    WebElement drag_answerChoice;//Drag button to drag the answer choice
    public WebElement getDrag_answerChoice(){return drag_answerChoice;}

    @FindBy(css = "span[class='remove-classification pull-right']")
    List<WebElement> list_deleteClass;//List of delete button to delete the class
    public List<WebElement> getList_deleteClass(){return list_deleteClass;}

    @FindBy(css = "span[class='drag-classification pull-right']")
    WebElement dragClass;// Drag button to drag the class
    public WebElement getDragClass(){return dragClass;}

    @FindBys(@FindBy(css = "span[class='drag-classification pull-right']"))
    List<WebElement> list_dragClass;// Drag button to drag the class
    public List<WebElement> getList_dragClass(){return list_dragClass;}

    @FindBy(css = "label[class='shufflechkbox as-shuffle-ans-choices-checkbox-unchecked']")
    WebElement checkbox_shuffleAnswerChoices;//"Shuffle answer choices" check box
    public WebElement getCheckbox_shuffleAnswerChoices(){return checkbox_shuffleAnswerChoices;}

    @FindBy(css="label[class='writeboardchkbox as-writeboard-checkbox-unchecked']")
    WebElement checkbox_scratchpad;//Scratchpad check box
    public WebElement getCheckbox_scratchpad(){return checkbox_scratchpad;}

    @FindBy(id="content-solution")
    WebElement textbox_solution;//Solution text box
    public WebElement getTextbox_solution(){return textbox_solution;}

    @FindBy(id="content-hint")
    WebElement textbox_hint;//Hint text box
    public WebElement getTextbox_hint(){return textbox_hint;}

    @FindBy(id="uploadbackgroundImage")
    WebElement link_uploadABackgroundImage;//Upload a background image link(optional)
    public WebElement getLink_uploadABackgroundImage(){return link_uploadABackgroundImage;}

    @FindBy(id="widget-createimage_upload_list")
    WebElement popUp_upload;//Upload a background image popup
    public WebElement getPopUp_upload(){return popUp_upload;}

    @FindBy(id="draggable")
    WebElement backgroundImage_uploaded;
    public WebElement getBackgroundImage_uploaded(){return backgroundImage_uploaded;}

    @FindBy(id="answer_choice_edit")
    WebElement answerChoice_text;//Text answer choice to create text answer
    public  WebElement getAnswerchoice_text(){return answerChoice_text;}

    @FindBy(id="answer_math_edit")
    WebElement answerChoice_mathML;//MAthML answer choice to create MAthML answer
    public  WebElement getAnswerChoice_mathML(){return answerChoice_mathML;}

    @FindBy(id="answer_image_edit")
    WebElement answerChoice_image;//Image answer choice to create image answer
    public WebElement getAnswerChoice_image(){return answerChoice_image;}

    @FindBy(xpath="//div[starts-with(@class,'classification-container ui-droppable')]")
    WebElement drop_class;//Area to drop the class
    public WebElement getDrop_class(){return drop_class;}

    @FindBy(css = "div[class = 'classification-container ui-droppable']")
    WebElement dropClass;//Area to drop the class
    public WebElement getDropClass(){return dropClass;}

    @FindBy(className = "classification-div-header")
    WebElement dropped_classHeader;//Dropped class header to get the dropped class text
    public WebElement getDropped_classHeader(){return dropped_classHeader;}

    @FindBys({@FindBy(className = "classification-div-header")})
    List<WebElement> list_dropped_classHeader;//Dropped class header to get the dropped class text
    public List<WebElement> getListDropped_classHeader(){return list_dropped_classHeader;}

    @FindBys(@FindBy(css="div[class='class-col-answer']"))
    List<WebElement> list_dropped_answerChoice;//Dropped answer choice to get the dropped answer choice text
    public List<WebElement> getListDropped_answerChoice(){return list_dropped_answerChoice;}

    @FindBys(@FindBy(css="div[class='classification-div-content']"))
    List<WebElement> list_dropped_classArea;//Dropped class area to drop the answer choice
    public List<WebElement> getListDropped_classArea(){return list_dropped_classArea;}

    @FindBy(css="span[class='remove-answer-choice pull-right']")
    WebElement delete_droppedAnswerChoice;//Delete button to delete the dropped answer choice
    public WebElement getDelete_droppedAnswerChoice(){return delete_droppedAnswerChoice;}

    @FindBys(@FindBy(css="span[class='remove-answer-choice pull-right']"))
    List<WebElement> list_delete_droppedAnswerChoice;//List of Delete button to delete the dropped answer choice
    public List<WebElement> getListDelete_droppedAnswerChoice(){return list_delete_droppedAnswerChoice;}

    @FindBy(id="saveQuestionDetails1")
    WebElement button_save;//Save button
    public WebElement getButton_save(){return button_save;}

    @FindBy(id="footer-notification-text")
    WebElement message_text;//Message beside the question type
    public WebElement getMessage_text(){return message_text;}

    @FindBys({@FindBy(xpath = "//select[@id='difficulty-level-drop-down']/option")})
    List<WebElement> list_difficultyLevel;//Difficulty Level list
    public List<WebElement> getList_difficultyLevel(){return list_difficultyLevel;}

    @FindBy(id="difficulty-level-drop-down")
    WebElement difficulty_level;//Difficulty level drop down
    public WebElement getDifficulty_level(){return difficulty_level;}

    @FindBy(className = "as-footer-learning-objectives")
    WebElement learningObjective;//Learning objective
    public WebElement getLearningObjective(){return learningObjective;}

    @FindBy(className = "as-question-teacher-preview-button")
    WebElement button_preview;//Preview button
    public WebElement getButton_preview(){return button_preview;}

    @FindBy(id="question-reveview-submit")
    WebElement preview_submit;//Submit button on Preview page
    public WebElement getPreview_submit(){return preview_submit;}

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

    @FindBy(css = "div[class='lsm-createAssignment-done selected']")
    WebElement button_review;// 'Review' Button
    public WebElement getButton_review() {return button_review;}

    @FindBys(@FindBy(css = "span[class='remove-classification-dropzone pull-right']"))
    List<WebElement> removeButton;//Remove button to remove class
    public List<WebElement> getRemoveButton(){return removeButton;}

















}
