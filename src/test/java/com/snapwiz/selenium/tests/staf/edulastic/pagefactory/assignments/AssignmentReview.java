package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**fv
 * Created by Dharaneesh T Gowda on 07-01-2015.
 */
public class AssignmentReview {

    @FindBy(className = "as-question-preview-edit-button") //Edit Button
    WebElement button_Edit;
    public WebElement getButton_Edit()
    {
      return  button_Edit;
    }

    @FindBy(className = "control-label") //Question text label
            WebElement label_QuestionText;
    public WebElement getLabel_QuestionText()
    {
        return  label_QuestionText;
    }


    @FindBys(@FindBy(className = "as-status-label"))
    List<WebElement> list_assessmentstatus;//List of Assessment status
    public List<WebElement> getList_assessmentstatus(){return list_assessmentstatus;}


    @FindBys(@FindBy(xpath = "//span[@class='as-assignment-due-date ls-assignment-due-date']"))
    List<WebElement> list_duedate;//List of due dates
    public List<WebElement> getList_duedate(){return list_duedate;}

    @FindBy(className = "as-question-preview-back-button")
    WebElement back_arrow;//Arrow to go back
    public WebElement getBack_arrow(){return back_arrow;}


   //Classification question Type
    @FindBys(@FindBy(css = "div[class='dnd-preview-answer answer']"))
    List<WebElement> list_answerChoice;//List of answer choice
    public List<WebElement> getList_answerChoice(){return list_answerChoice;}


    //Matching Tables question type
    @FindBys(@FindBy(xpath = "//div[@class='matrix-equation-area matrix-wrap']"))
    List<WebElement> list_editbox_answerChoice;//Answer choice edit box
    public List<WebElement> getList_editbox_answerChoice(){return list_editbox_answerChoice;}

    @FindBy(className = "as-question-preview-duplicate-button")
    WebElement button_duplicate;//duplicate button
    public WebElement getButton_duplicate(){return button_duplicate;}

    @FindBy(className = "as-question-preview-delete-button")
    WebElement button_delete;//delete button
    public WebElement getButton_delete(){return button_delete;}

    @FindBy(css = "div[class='as-modal-yes-btn yes-delete yes-delete-question']")
    WebElement button_yesDelete;//yes button after clicking on Delete
    public WebElement getButton_yesDelete(){return button_yesDelete;}

    @FindBy(xpath = "//span[@class='as-question-id']/descendant::span")
    WebElement questionId;//Question id
    public WebElement getQuestionId(){return questionId;}

    @FindBy(xpath = "//span[@class='as-question-owner']/descendant::span")
    WebElement owner;//Owner
    public WebElement getOwner(){return owner;}

    @FindBy(id = "assessments-use-button")
    public WebElement nextButton;

    @FindBy(xpath = "//input[@checked='checked']")
    public List<WebElement> checkBoxQuestion;

    @FindBy(css = "span.lsm-content-sprite.lsm-createAssignment-dragIcon")
    public List<WebElement> dragIcon;

    @FindBy(id = "assessments-save-later-button")
    public WebElement saveInDrafts;

    @FindBy(id="assessments-back-button")
    public WebElement addMore;

    @FindBy(id = "show-preview")
    public WebElement button_viewAsStudent;//View as student button

    @FindBy(xpath = "//label[@class='as-grey-checkbox']")
    public List<WebElement> cross_icon;//Cross icons to remove questions

    @FindBy(className = "as-question-preview-edit-button")
    public WebElement editButton;

    @FindBy(xpath = "//span[@title='Add to District Library']")
    public List<WebElement> addToDistrictLibrary;

    @FindBy(id = "assessments-use-button")
    public WebElement btn_AssignOrPublish;




}
