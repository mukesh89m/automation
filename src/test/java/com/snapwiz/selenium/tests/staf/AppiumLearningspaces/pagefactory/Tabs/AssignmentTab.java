package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.Tabs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Mukesh on 1/20/15.
 */
public class AssignmentTab {

    @FindBy(className = "ls-right-user-head")
    public WebElement userName; //username of assignment tab in right top corner

    @FindBy(className = "ls-assignment-show-assign-this-block")
    public WebElement rightArrow;

    @FindBy(css = "span[class='ls-right-tab-hover-sprite folder-cycle-bg']")
    public  WebElement open_button;

    @FindBy(css = "div[class='ls-stream-learing-activity-level ls-assignment-learning-activity ellipsis']")
    public List<WebElement> lessonName_AssignmentTab;

    @FindBy(className = "assessmentStatus")
    public WebElement status_AssignmentTab;

    @FindBy(css = "span[class='toc-icon completed']")
    public WebElement selectSecondLesson;

    @FindBy(id = "performance-chart-label-id")
    public WebElement questionCount; //question count in performance summary


    @FindBy(xpath = "//a[@title='Open']")
    public WebElement getOpen_button; //Open link after click on Right arrow

    @FindBy(xpath = "//div[@class='tab active']/span[@class='close_tab']")
    public WebElement cancelMark; //Cancel 'X' on top of tab

    @FindBy(xpath = "//span[@title='Assignments']")
    public WebElement assignmentsTab; //Assignment tab under Introduction

    @FindBy(xpath = "//a[@title='New tab']")
    public WebElement newTabUnderArrow; //New tab under Assignments tab>arrow
    @FindBy(xpath = "//a[@title='Assign this']")
    public WebElement assignThis; //Assign this under Assignments tab>arrow

    @FindBy(xpath = "//div[@class='share-with-wrap']")
    public WebElement AssignTO; //Assign to

    @FindBy(xpath = "//input[@class='input-filed']")
    public WebElement shor; //Assign to

    @FindBy(xpath = "//div[contains(@class,'ir-ls-assign-dialog-gradable-label-check')]")
    public WebElement   gradable; //Checkbox gradeble

    @FindBy(xpath = "//a[text()='Choose your grading policy']")
    public WebElement   gradingPolicy; //Checkbox gradeble
    @FindBy(id = "grading-policy")
    public WebElement gradingPolicyDescription;
    @FindBy(id = "accessible-date")
    public WebElement accessibleDate;
    @FindBy(id = "due-date")
    public WebElement dueDate;
    @FindBy(id = "additional-notes")
    public WebElement  additionalNotes;
    @FindBy(css = "span[class='btn sty-green submit-assign']")
    public WebElement  AssignButton;
    @FindBy(id = "assign-cancel")
    public WebElement  cancel;
    @FindBy(xpath = "//input[@id='total-points']")
    public WebElement totalPoints;

    @FindBy(xpath="//div[@class='assign-this-help-tooltip-wrapper']")
    public WebElement helpIconTextTotalPoints;

    @FindBy(xpath = "//div[contains(@class,'ir-ls-assign-dialog-total-points-content')]/span")
    public WebElement helpIconTotalPoints;

    @FindBy(xpath = "//div[text()='Use Pre-created Assignment']")
    public WebElement usePrecreatedAssignmentButton;

    @FindBy(xpath= "//div[@subtype='AUTHOR_FILE_ASSESSMENT']")
    public List<WebElement> assignmentList;

    @FindBy(xpath="//span[starts-with(@class,'ls-preview-wrapper')]")
    public WebElement previewLink;

    @FindBy(xpath= "//span[@class='tab_title']")
    public List<WebElement> tabs;

    @FindBy(xpath = "//span[@title='Add to My Question Bank']")
    public WebElement addToMyQuestionBank;

    @FindBy(css = "i[class='ls-icon-img ls--like-icon']")
    public WebElement likeIcon;

    @FindBy(css = "i[class='ls-icon-img ls--comments-icon']")
    public WebElement commentIcon;

    @FindBy(css = "i[class='ls-file-assignment-icon']")
    public WebElement assignmentIcon;

    @FindBy(id= "close-assignment")
    public WebElement closeAssignment;

    @FindBy(className = "resource-title")
    public WebElement resourceName;

    @FindBy(css = "a[class='ls-file-delete-file-name ellipsis']")
    public WebElement uploadedResource;

    @FindBy(css = "a[class='btn btn--primary btn--large btn--submit long-text-button']")
    public WebElement finishButton;

    @FindBy(id = "uploadFile")
    public WebElement uploadFileLink;

    @FindBy(className = "static-text")
    public WebElement optionalField;

    @FindBy(className = "ls-assignment-grading-title")
    public WebElement gradingPolicyField;

    @FindBy(className = "ls-uploaded-files-header")
    public WebElement description;

    @FindBy(className = "student-response-title")
    public WebElement responseTag;

    @FindBy(css = "a[class='btn btn--primary btn--large btn--continue']")
    public WebElement continueButton;

    @FindBy(className = "feedback-content")
    public WebElement teacherFeedBack;

    @FindBy(className = "marks-awarded")
    public WebElement score;

    @FindBy(css = "span[class='ls-right-tab-hover-sprite folder-forward-bg']")
    public WebElement newTab_Button;

    @FindBy(className = "ls_assessment_link")
    public WebElement assessmentName;

    @FindBy(className = "question-association-skill-id")
    public WebElement associateTLO;

    @FindBy(xpath = "//div[@class='dateImg question-no-label']/input")
    public WebElement numberOfQuestions;

    @FindBy(css="div.ir-ls-assign-dialog-header")
    public WebElement assignmentName;

    @FindBy(css="div.ir-ls-assign-this-edit-link")
    public WebElement editAssignmentName;

    @FindBy(xpath = "//*[@class='ir-ls-assign-this-header-edit-text']")
    public WebElement inputAssignmentName;

    @FindBy(xpath="//span[@class='short-label-help-icon question-no-msg']")
    public WebElement numberOfQuestionHelpLabel;

    @FindBy(xpath="//span[@class='short-label-help-icon gradable-check-help']")
    public WebElement gradableHelpLabel;



    @FindBy(xpath="//div[@class='assign-this-help-tooltip-wrapper']")
    public WebElement getNumberOfQuestionHelpText;


    @FindBy(css="div.question-no-error-msg")
    public WebElement getErrorMessageForTotalNumberOfQuestions;

    @FindBy(className = "back__btn")
    public WebElement icon_performancePage;



    @FindBy(css = "span.assign-this")
    public WebElement assignThisUnderResources;

    @FindBy(css = "span.assign-this")
    public List<WebElement> assignThisUnderResourcesList;




}
