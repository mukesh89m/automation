package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import lombok.experimental.FieldDefaults;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by root on 12/29/14.
 */
public class NumberLineQuestionCreation {
    @FindBy(css = "span[class='as-editor-question-type']")
    WebElement questionTitle;// Question title
    public WebElement getQuestionTitle(){return questionTitle;}

    @FindBy(id = "question-raw-content")
    WebElement textBox_QuestionField;//Question edit box to type the question or to get the default value
    public WebElement getTextBox_QuestionField(){return textBox_QuestionField;}

    @FindBy(id = "add-new-numberLine-answer-choice")
    WebElement link_addNewAnswerChoice;//link "Add new answer choice"
    public WebElement getLink_addNewAnswerChoice(){return link_addNewAnswerChoice;}

    @FindBy(id = "addNewAnnotation")
    WebElement link_addAnnotation;//link "Add Annotation"
    public WebElement getLink_addAnnotation(){return link_addAnnotation;}

    @FindBys({@FindBy(className= "answer")})
    List<WebElement> list_enterAnswerChoice;//"Enter answer choices" text boxes
    public List<WebElement> getList_enterAnswerChoice(){return  list_enterAnswerChoice;}

    @FindBy(id = "ans_ch_1")
    WebElement answerChoice_1st;//1st answer choice
    public WebElement getAnswerChoice_1st(){return answerChoice_1st;}

    @FindBys(@FindBy(xpath = "//li[starts-with(@class,'answer-choice-hover numberLine-label-answer-choice-hover')]"))
    List<WebElement> list_answerChoiceToDragOrDelete;//Outer are of answer choice to get delete or drag button
    public List<WebElement> getList_answerChoiceToDragOrDelete(){return list_answerChoiceToDragOrDelete;}

    @FindBy(id = "numberLineAnsBox_0")
    WebElement numberLine;// Number Line to identify the line
    public WebElement getNumberLine(){return numberLine;}

    @FindBy(id ="isShuffleAnswerChoice")
    WebElement checkbox_shuffleAnswerChoices;//"Shuffle answer choices" check box
    public WebElement getCheckbox_shuffleAnswerChoices(){return checkbox_shuffleAnswerChoices;}

    @FindBy(id ="isShowNumbersforMajorTicks")
    WebElement checkbox_majorTicks;//"Show numbers for major ticks" check box
    public WebElement getCheckbox_majorTicks(){return checkbox_majorTicks;}

    @FindBy(css="label[class='writeboardchkbox as-writeboard-checkbox-unchecked']")
    WebElement checkbox_scratchpad;//Scratchpad check box
    public WebElement getCheckbox_scratchpad(){return checkbox_scratchpad;}

    @FindBy(id ="startValue")
    WebElement textbox_axisStartValue;//"Axis start Value" text box to enter value or to get the default value
    public WebElement getTextbox_axisStartValue(){return  textbox_axisStartValue;}

    @FindBy(id="endValue")
    WebElement textbox_endValue;//"End Value" text box to enter value or to get the default value
    public  WebElement getTextbox_endValue(){return textbox_endValue;}

    @FindBy(id="majorTicks")
    WebElement textbox_majorTicks;//"Major Ticks" text box to enter value or to get the default value
    public  WebElement getTextbox_majorTicks(){return textbox_majorTicks;}

    @FindBy(id="minorTicks")
    WebElement textbox_minorTicks;//"Major Ticks" text box to enter value or to get the default value
    public  WebElement getTextbox_minorTicks(){return textbox_minorTicks;}

    @FindBy(id="answer_choice_edit")
    WebElement answerChoice_text;//Text answer choice to create text answer
    public  WebElement getAnswerchoice_text(){return answerChoice_text;}

    @FindBy(id="answer_math_edit")
    WebElement answerChoice_mathML;//MAthML answer choice to create MAthML answer
    public  WebElement getAnswerChoice_mathML(){return answerChoice_mathML;}

    @FindBy(id="answer_image_edit")
    WebElement answerChoice_image;//Image answer choice to create image answer
    public WebElement getAnswerChoice_image(){return answerChoice_image;}

    @FindBy(id="delete-numline-answer_choice")
    WebElement delete_answerChoice;//Delete icon to delete the answer choice
    public WebElement getDelete_answerChoice(){return delete_answerChoice;}

    @FindBy(id="ans-drag-btn")
    WebElement drag_answerChoice;//Drag button to drag the answer choice
    public WebElement getDrag_answerChoice(){return drag_answerChoice;}

    @FindBys(@FindBy(id = "ans-drag-btn"))
    List<WebElement> list_dragAnswerChoice;//List of drag button
    public List<WebElement> getList_dragAnswerChoice(){return list_dragAnswerChoice;}

    @FindBys({@FindBy(css="div[class ='num-line-box ui-droppable']")})
    List<WebElement> list_dropClass;//Numberline drop area list to drop the answer choice
    public List<WebElement> getList_dropClass(){return list_dropClass;}

    @FindBys({@FindBy(id="drop-num-line-answer")})
    List<WebElement> list_droppedAnswerChoice;//dropped answer choice into numberline
    public List<WebElement> getList_droppedAnswerChoice(){return list_droppedAnswerChoice;}

    @FindBy(css="div[class='dnd-numberLine-answer-choice-container']")
    WebElement leftPanel;//Left panel
    public WebElement getLeftPanel(){return leftPanel;}

    @FindBy(className = "number-line-attributes")
    WebElement numberLine_attribute;//Numberline attribute
    public  WebElement getNumberLine_attribute(){return numberLine_attribute;}

    @FindBy(className = "remove-numline-choice")
    WebElement remove_droppedObject;//close button to remove the dropped object
    public WebElement getRemove_droppedObject(){return remove_droppedObject;}

    @FindBys({@FindBy(className = "graph-tick-text")})
    List<WebElement> ListnumberLine_number;//Numbers on number line
    public List<WebElement> getListNumberLine_number(){return ListnumberLine_number;}

    @FindBy(xpath = "//label[text()='Major tick should lies between 0 to 21']")
    WebElement error_message_majorTicks;//Error message when major ticks value is greater than 21
    public WebElement getError_message_majorTicks(){return error_message_majorTicks;}

    @FindBys({@FindBy(id="answer_choice_edit")})
    List<WebElement> list_annotationText;//List of Textbox in annotation to enter text
    public List<WebElement> getList_annotationText(){return list_annotationText;}

    @FindBys({@FindBy(id = "answer_math_edit")})
    List<WebElement> list_annotationMathML;//List of MathML in annotation to enter MathML
    public List<WebElement> getList_annotationMathML(){return list_annotationMathML;}

    @FindBys({@FindBy(id="answer_image_edit")})
    List<WebElement> list_annotationImage;//List of image in annotation to upload image
    public List<WebElement> getList_annotationImage(){return list_annotationImage;}

    @FindBy(id="annotationContent")
    WebElement annotationContent;//Annotation Content to perform drag and delete operation
    public WebElement getAnnotationContent(){return annotationContent;}

    @FindBy(id="dragAnnotation")
    WebElement drag_annotation;//Drag button to drag the annotation
    public WebElement getDrag_annotation(){return drag_annotation;}

    @FindBy(id="num-line-section")
    WebElement drop_annotation;//Drop annotation area to drop the annotation
    public WebElement getDrop_annotation(){return drop_annotation;}

    @FindBys({@FindBy(id="dragged-ans-choice")})
    List<WebElement> list_droppedAnnotation;//Dropped annotation to get the text
    public List<WebElement> getList_droppedAnnotation(){return list_droppedAnnotation;}

    @FindBy(id = "edit-answer-math-dialog")
    WebElement mathML_popup;//MathML pop up
    public WebElement getMathML_popup(){return mathML_popup;}

    @FindBy(id="widget-createimage_start_queue")
    WebElement image_popup;//Image pop up
    public WebElement getImage_popup(){return image_popup;}

    @FindBy(xpath = "//div[@id = 'annotationContent']/span/img")
    WebElement imageAnnotation;//Added image annotation
    public WebElement getImageAnnotation(){return imageAnnotation;}

    @FindBy(xpath = "//div[@id='dragged-ans-choice']/img")
    WebElement image_droppedAnnotation;//Dropped image annotation
    public WebElement getImage_droppedAnnotation(){return image_droppedAnnotation;}

    @FindBy(id="content-solution")
    WebElement textbox_solution;//Solution text box
    public WebElement getTextbox_solution(){return textbox_solution;}

    @FindBy(id="content-hint")
    WebElement textbox_hint;//Hint text box
    public WebElement getTextbox_hint(){return textbox_hint;}

    @FindBy(id="difficulty-level-drop-down")
    WebElement difficulty_level;//Difficulty level drop down
    public WebElement getDifficulty_level(){return difficulty_level;}

    @FindBys({@FindBy(xpath = "//select[@id='difficulty-level-drop-down']/option")})
    List<WebElement> list_difficultyLevel;//Difficulty Level list
    public List<WebElement> getList_difficultyLevel(){return list_difficultyLevel;}

    @FindBy(className = "as-footer-learning-objectives")
    WebElement learningObjective;//Learning objective
    public WebElement getLearningObjective(){return learningObjective;}

    @FindBy(id="saveQuestionDetails1")
    WebElement save_button;//Save button
    public WebElement getSave_button(){return save_button;}

    @FindBy(id="footer-notification-text")
    WebElement error_savingQuestion;//Error message while saving the question
    public WebElement getError_savingQuestion(){return error_savingQuestion;}

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

    @FindBy(css = "div[class='lsm-createAssignment-done selected']")
    WebElement button_review;// 'Review' Button
    public WebElement getButton_review() {return button_review;}

    @FindBy(css = "div[class='lsm-createAssignment-Question']")
    WebElement createAssignmentQuestionName;//Question Text in the Questions List Page
    public WebElement getCreateAssignmentQuestionName(){return createAssignmentQuestionName;}

    @FindBy(xpath = "//span[text() = 'OWNER:']/following-sibling::span[@class='as-questionType']")
    WebElement labelValue_Ownwer;// Value for Owner 'You'
    public WebElement getLabelValue_Ownwer() {
        return labelValue_Ownwer;
    }

    @FindBy(className = "learning-objective-close-btn")
    WebElement closeButton_learningObjective;//Learning objective close button
    public WebElement getCloseButton_learningObjective(){return closeButton_learningObjective;}

    @FindBys({@FindBy(className = "major-tick")})
    List<WebElement> majorTicks;//Major ticks
    public List<WebElement> getMajorTicks(){return majorTicks;}

    @FindBys({@FindBy(className = "minor-tick")})
    List<WebElement> minorTicks;//Minor ticks
    public List<WebElement> getMinorTicks(){return minorTicks;}











}




