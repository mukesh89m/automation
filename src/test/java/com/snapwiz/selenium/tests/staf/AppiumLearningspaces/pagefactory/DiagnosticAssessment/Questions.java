package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.DiagnosticAssessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by Dharaneesh T Gowda on 29-01-2015.
 */
public class Questions {
    @FindBy(xpath = "//img[@alt = 'BIO Principles, First Edition WileyPLUS Learning Space Course']")
    WebElement icon_BioCourse;// 'Bio Principles' Course
    public WebElement getIcon_BioCourse(){return icon_BioCourse;}

    @FindBy(xpath = "//div[@id = 'questionOptions']//span[@class='question-set-add-text']")
    WebElement link_New;// link 'New'
    public WebElement getLink_New(){return link_New;}


    @FindBy(id = "questionRevisions")
    WebElement link_Revisions;// link 'Revisions'
    public WebElement getLink_Revisions(){return link_Revisions;}

    @FindBy(id = "cms-question-revision-new-version-link")
    WebElement link_createNewVersion;// link 'Create New Version'
    public WebElement getLink_createNewVersion(){
        return link_createNewVersion;
    }

    @FindBy(xpath = "//a[@title = 'Draft']")
    WebElement comboBox_Draft;// Combo box 'Draft'
    public WebElement getComboBox_Draft(){
        return comboBox_Draft;
    }

    @FindBy(xpath = "//a[@title = 'Publish']")
    WebElement link_ReadyToPublish;// link 'Publish'
    public WebElement getLink_ReadyToPublish(){
        return link_ReadyToPublish;
    }

    @FindBy(id = "saveQuestionDetails1")
    WebElement button_Save;// Button 'Save'
    public WebElement getButton_Save(){
        return button_Save;
    }

    @FindBy(xpath = "//div[contains(@title,'Ch 1: ')]")
    WebElement link_Ch1;// link 'Ch 1 : '
    public WebElement getLink_Ch1(){
        return link_Ch1;
    }




    @FindBy(xpath = "//div[contains(@title,'Ch 2: ')]")
    WebElement link_Ch2;// link 'Ch 2: '
    public WebElement getLink_Ch2(){
        return link_Ch2;
    }

    @FindBy(xpath = "//div[contains(@title,'Ch 3: ')]")
    WebElement link_Ch3;// link 'Ch 3: '
    public WebElement getLink_Ch3(){
        return link_Ch3;
    }

    @FindBy(xpath = "//div[contains(@title,'Diagnostic Test - The Chemical Foundation of Life')]")
    WebElement link_diagnosticTest;// link 'Diagnostic Test'
    public WebElement getLink_diagnosticTest(){
        return link_diagnosticTest;
    }

    @FindBy(className = "cms-deactivated-question")
    WebElement msg_Notification;// Notification message
    public WebElement getMsg_Notification(){
        return msg_Notification;
    }

    @FindBy(css = "span[title = 'Discussion']")
    WebElement tab_Discussion;// Tab 'Discussion'
    public WebElement getTab_Discussion(){
        return tab_Discussion;
    }


    @FindBy(className = "control-label")
    WebElement label_QuestionText;// Tab 'Discussion'
    public WebElement getLabel_QuestionText(){
        return label_QuestionText;
    }

    @FindBy(xpath = "//div[@class='redactor_box']//div")
    WebElement label_NewVersionQuestionText;// New Version Question text label
    public WebElement getLabel_NewVersionQuestionText(){
        return label_NewVersionQuestionText;
    }

    @FindBys({@FindBy(css = "div[class = 'question-card-question-content']")})
    List<WebElement> label_QuestionCardElementsList;
    public List<WebElement> getLabel_QuestionCardElementsList() {
        return label_QuestionCardElementsList;
    }








    @FindBy(id = "show-your-work-label")
    public WebElement plusWorkBoard;

    @FindBy(className = "close-iframe-question-content")
    public WebElement crossIcon;

    @FindBy(id = "main-controls")
    public WebElement toolControl;

    @FindBy(xpath = "//iframe[starts-with(@id,'whiteBoard_iframe_kedukWBSTUDENT')]")
    public WebElement frame;

    @FindBy(className = "toggle-content")
    public WebElement expandIcon;

    @FindBy(css = "canvas[height='545']")
    public WebElement textEntryPopUp;

    @FindBy(className = "true-false-student-answer-label")
    public WebElement trueFalseAnswerOption;

    @FindBy(className = "confidence-level-almost")
    public WebElement confidenceLevelAlmost;

    @FindBy(className = "al-diag-test-submit-button")
    public WebElement finish_Button;

    @FindBy(css = "div[class='report-sidebar-question-card-sectn question-card-green']")
    public  List<WebElement> questionCard;

    @FindBy(css = "div[class='report-sidebar-question-card-sectn question-card-grey']")
    public WebElement questionCardSkipped;

    @FindBy(className = "al-quit-diag-test-icon")
    public WebElement quit_Icon;

    @FindBy(className = "ls-practice-test-view-report")
    public WebElement viewReportLink;

    @FindBy(id = "pencil-btn")
    public WebElement pencilButton;

    @FindBy(id = "white-board-feedBack-link-text")
    public WebElement viewTeacherFeedback;

    @FindBy(id = "question-mc-raw-content")
    WebElement label_questionText;// Question Text
    public WebElement getLabel_questionText(){
        return label_questionText;
    }

    @FindBy(className = "question-label")
    public WebElement questionLabel;//Question label in diagnostic test

    @FindBy(xpath = "//div[contains(@class,'report-sidebar-question-card-sectn')]")
    public List<WebElement> question_card;

    @FindBy(className = "whiteboard-feedback-teacher")
    public WebElement viewTeacherFeedbackButton;

    @FindBy(id = "white-board-link")
    public WebElement whiteboardLink;

    @FindBy(id="teacher-btn")
    public WebElement teacherFeedback;


    @FindBy(xpath = "//iframe[starts-with(@id,'whiteBoard_iframe_kedukWBTEACHER')]")
    public WebElement frameInstructor;

    @FindBy(className = "tab_title")
    public List<WebElement> tab;


}
