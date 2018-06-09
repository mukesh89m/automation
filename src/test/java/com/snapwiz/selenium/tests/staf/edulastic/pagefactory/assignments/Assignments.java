package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by snapwiz on 18-Dec-14.
 */
public class Assignments {

    @FindBy(xpath= "//button[starts-with(@class,'btn btn-rounded btn-default lsm-createAssignment-done')]")
    WebElement button_review;// 'Review' Button
    public WebElement getButton_review() {
        return button_review;
    }

    @FindBy(id = "assessments-save-later-button")
    WebElement button_saveForLater;// 'Save for later' button
    public WebElement getButton_saveForLater() {
        return button_saveForLater;
    }

    @FindBy(className = "as-assignment-draftTxt")
    WebElement button_viewDraftStatus;// 'View draft status' button
    public WebElement getButton_ViewDraftStatus() {
        return button_viewDraftStatus;
    }

    @FindBy(xpath = "//span[text() = 'ID:']//following-sibling::span[@class='as-questionType']")
    WebElement labelValue_QuestionID;// Question 'ID' to get the ID of a Question
    public WebElement getLabelValue_QuestionID() {
        return labelValue_QuestionID;
    }

    @FindBy(css = ".as-title")
    WebElement assessmentName;// Assessment Name
    public WebElement getAssessmentName() {
        return assessmentName;
    }

    @FindBy(xpath = "//*[starts-with(@class,'as-title')]")
    List<WebElement> assessmentNamelist;// Assessment Name
    public List<WebElement> getAssessmentNameList() {
        return assessmentNamelist;
    }

    @FindBys(@FindBy(css = "div[class='as-label ellipsis']"))
    List<WebElement> list_assessmentName;//List of Assessment Name
    public List<WebElement> getList_assessmentName() { return list_assessmentName;}

    @FindBy(css = "span[class='as-questionDetails-clickArrow']")
    WebElement rightArrow;// Right Arrow in the right hand side of each question in Question List Page
    public WebElement getRightArrow() {
        return rightArrow;
    }

    @FindBy(css = "div.lsm-createAssignment-Question")
    WebElement createAssignmentQuestionName;//Question Text in the Questions List Page
    public WebElement getCreateAssignmentQuestionName(){return createAssignmentQuestionName;}

    @FindBy(xpath = "//span[text() = 'OWNER:']/following-sibling::span[@class='as-questionType']")
    WebElement labelValue_Ownwer;// Value for Owner 'You'
    public WebElement getLabelValue_Ownwer() {
        return labelValue_Ownwer;
    }

    @FindBy(className = "as-question-preview-edit-button")
    WebElement editLink;//Edit link
    public WebElement getEditLink(){  return editLink;}

    @FindBy(css = "div.as-assignment-flow-link-title")
    WebElement useExistingAssessmentLink;//use Existing Assessment Link
    public WebElement getUseExistingAssessmentLink(){  return useExistingAssessmentLink;}

    @FindBys(@FindBy(xpath = "//div[@class='as-assignment-flow-link-title']"))
    List<WebElement> links_useExistingAndCreateAssessment;//Link after clicking on new assessment
    public List<WebElement> getLinks_useExistingAndCreateAssessment(){return links_useExistingAndCreateAssessment;}

    @FindBy(css = "div[class='font-semi-bold space-15 assign-title']")
    List<WebElement> assessmentsNames;//use Existing Assessment Link
    public List<WebElement> getAllAssessmentsNames(){  return assessmentsNames;}

    @FindBy(className = "as-print")
    WebElement button_Print;//Edit link
    public WebElement getButton_Print(){  return button_Print;}

    @FindBy(css= "a[class='btn btn-blue btn-rounded']")
    WebElement button_newAssessment;//Create New Assignments button
    public WebElement getButton_newAssessment(){return button_newAssessment;}

    @FindBys(@FindBy(css= "a[class='btn btn-blue btn-rounded']"))
    List<WebElement> list_button_newAssessment;//Create New Assignments button
    public List<WebElement> getList_button_newAssessment(){return list_button_newAssessment;}

    @FindBy(id = "two")
    WebElement radioButton_public;//Radio button for Community
    public  WebElement getRadioButton_public(){return radioButton_public;}

    @FindBy(className = "as-question-preview-duplicate-button")
    WebElement duplicate_link;//Duplicate link
    public WebElement getDuplicate_link(){return duplicate_link;}

    @FindBy(className = "as-question-preview-delete-button")
    WebElement delete_link;//Delete link
    public WebElement getDelete_link(){return delete_link;}


    @FindBy(css = "span.as-delete")
    public List<WebElement> deleteAssessment;

    @FindBy(id = "search-assessment-with-val")
    WebElement search;//Search icon
    public WebElement getSearch(){return search;}

    @FindBy(css= "span.as-response")
    public List<WebElement> viewResponse;//view response

    @FindBy(css = "div.dropdown-toggle")
    public WebElement more;//more dropdown

    @FindBy(xpath = "//span[contains(@class,'view-user-question-performance-score views-number')]")
    public WebElement studentScore;

    @FindBy(xpath = "//span[contains(@class,'view-user-question-performance-score views-number')]")
    public List<WebElement> studentScoreList;

    @FindBy(xpath = "//span[@class='views-number block text-center']")
    public WebElement totalScore;

    @FindBy(xpath = "//span[@class='views-number block text-center']")
    public List<WebElement> totalScoreList;

    @FindBy(css = "div.as-noData-msgWrapper")
    public WebElement message_noAssignment;//No assignment message box

    @FindBy(id = "select2-as-header-classes-selectbox-container")
    public WebElement dropdown_defaultClassName;//default Class name

    @FindBy(xpath = "//ul[starts-with(@id,'select2-as-header-classes-selectbox-result')]/li")
    public List<WebElement> dropdown_classNames;//List of class names in dropdown

    @FindBy(xpath = "//span[@class='ed-icon icon-preview']")
    public WebElement preview;//Preview

    @FindBy(css = "div.col-xs-9")
    public WebElement assignment;

    @FindBy(css = "span.open-assignment-btn.action-btn")
    public List<WebElement> open; //Open button

    @FindBy(css = "div[class^='sp-link btn btn-invisible as-g-clsrm-share']")
    public WebElement googleClassroom;//Google class room

    @FindBy(css = ".as-title.as-label.twoline-ellipsis.m-b-xs")
    public List<WebElement> assignmentName;

    @FindBy(className = "due-date-section")
    public WebElement dueDate;

    @FindBy(css = "input#delete-confm-txt")
    public WebElement deleteTextBox;

    @FindBy(css = ".as-modal-yes-btn.delete-draft-assessment")
    public WebElement yesButtonOnDeletePopUp;

    @FindBy (css = "span[class*='canvas-icons small-icon']")
    public WebElement canvasIcon;

    @FindBy(css = ".sync-canvas-students")
    public WebElement shareButton;

    @FindBy(xpath = "//div[text()='Close']")
    public WebElement closeButton;

    @FindBy(className = "as-noData-title")
    public WebElement assessmentNotAvailable;

    @FindBy(xpath = "//div[@title='Close Assignment']")
    public WebElement closeAssignment;


    @FindBy(xpath = "//div[@title='Pause Assignment']")
    public WebElement pauseAssignment;

    @FindBy(css = ".as-modal-yes-btn.close-assignment")
    public WebElement yesCloseButton;

    @FindBy(css = ".as-modal-yes-btn.pause__resume-assignment")
    public WebElement yesPauseButton;

    @FindBy(css = ".section-count")
    public List<WebElement> sectionCount;

    @FindBy(css = "div[class*='share-with-canvas']")
    public WebElement shareWithCanvas;

    @FindBy(css = ".modal-header >h4")
    public List<WebElement> successPopUp;

    @FindBy(css = ".as-modal-yes-btn.delete-assignment")
    public WebElement yesDelete;

    @FindBy(className = "status-label")
    public WebElement assignmentStatus;
    @FindBy(css = ".select2-search__field")
    public WebElement classSection_textbox;

    @FindBy(css = "span[class='stu_status st-not-start']")
    public List<WebElement> notStarted_status;

    @FindBy(css = "li[class^='select2-results__option']")
    public List<WebElement> classSectionDrpDownOptions;

    @FindBy(xpath = "//span[@class='status-label mantis']")
    public WebElement assignment_Status;



}
