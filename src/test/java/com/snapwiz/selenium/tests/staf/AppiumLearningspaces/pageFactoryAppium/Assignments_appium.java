package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pageFactoryAppium;

import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Snapwiz on 19/08/15.
 */
public class Assignments_appium {
    @FindBy(xpath = "//UIALink[4]")
    public WebElement link_allAssignemntsDownArrow;

    @FindBy(xpath = "//UIAStaticText[@name = 'Your instructor has not yet assigned you anything. Please check back at a later point of time.']")
    public WebElement label_instructorMessage;

    @FindBy(xpath = "//UIAButton[@name = 'About']")
    public WebElement button_about;

    @FindBy(xpath = "//UIAStaticText[@name = 'Mark for Review']")
    public WebElement label_markForReview;

    @FindBy(xpath = "//UIAStaticText[@name = 'Confidence']")
    public WebElement label_confidence;

/*
    @FindBy(xpath = "//UIALink[@name = 'Report Content Errors']")
    public WebElement icon_reportContentError;
*/
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[13]")
    public WebElement icon_reportContentError;

    @FindBy(xpath = "//UIATextField[@name = 'Enter content issue']")
    public WebElement textArea_enterContentIssue;

    @FindBy(xpath = "//UIAButton[@name = 'Send']")
    public WebElement button_send;

    @FindBy(xpath = "//UIAStaticText[@name = 'Click yes to report this issue']")
    public WebElement label_ClickYesToReportThisIssue;


    @FindBy(xpath = "//UIALink[@name = 'Next Question']")
    public WebElement button_nextQuestion;

    @FindBy(xpath = "//UIAStaticText[@name = 'Finish Assignment']")
    public WebElement button_finishAssignment;

    @FindBy(xpath = "//UIALink[@name = 'Previous Question']")
    public WebElement button_previousQuestion;

    @FindBy(xpath = "//UIAButton[@name = 'Performance']")
    public WebElement tab_performance;

    @FindBy(xpath = "//UIALink[@name = 'CONTINUE']")
    public WebElement button_continue;

    @FindBy(xpath = "//UIAStaticText[@name = 'All Assignments']")
    public WebElement label_allAssignemnts;

    @FindBy(xpath = "//UIAStaticText[@name = 'Question Practice']")
    public WebElement label_questionPractice;

    @FindBy(xpath = "//UIALink[@name = '+ Upload file(s)']")
    public WebElement link_uploadFiles;

    @FindBy(xpath = "//UIAStaticText[@name = 'Tap to enter response...']")
    public WebElement label_tapToEnterResponse;

    @FindBy(xpath = "//UIATextField[@name = 'edit']")
    public WebElement textField_edit;

    @FindBy(xpath = "//UIAStaticText[@name = 'Reviewed this Assignment.']")
    public WebElement label_reviewedThisAssignment;


    @FindBy(xpath = "//UIAStaticText[@name = 'This is Student Response']")
    public WebElement label_thisIsStudentResponse;

    @FindBy(xpath = "/UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[15]")
    public WebElement questionText; //Question Text in pie char performance summary page

    @FindBy(xpath = "//UIALink[contains(@name,'Question Card: Q')]")
    public List<WebElement> questioncard;

    @FindBy(xpath = "//UIALink[contains(@name,'Add new discussion')]")
    public WebElement newDiscussion_link;

    @FindBy(xpath = "//UIATextField[contains(@name,'Enter discussion text here')]")
    public WebElement newDiscussion_textArea;

    @FindBy(xpath = "//UIAButton[contains(@name,'Submit this discussion')]")
    public WebElement newDiscussion_submitButton;

    @FindBy(xpath = "//UIATextField[contains(@value,'Add a note')]")
    public WebElement addNote_link;

    @FindBy(xpath = "//UIATextField[contains(@name,'Enter note text here')]")
    public WebElement addNote_textArea;

    @FindBy(xpath = "//UIAButton[contains(@name,'Submit Note')]")
    public WebElement submitNote;

    @FindBy(xpath = "//UIALink[contains(@name,'posted a note')][1]")
    public WebElement postedNote;

    @FindBy(xpath = "//UIAStaticText[@name='Submitted'][1]")
    public WebElement submitted_status;

    @FindBy(xpath = "//UIAStaticText[@label='1']")
    public WebElement submitted_statusValue;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText")
    public List<WebElement> assignemnt_status;

    @FindBy(xpath = "//UIALink[@name='Submit Answer']")
    public WebElement submitAnswer_link;

    @FindBy(xpath = "//UIAStaticText[@name='Overall Score']")
    public WebElement overallScore;


    @FindBy(xpath = "//UIAStaticText[contains(@name,'Go to next question')][2]")
    public WebElement goToNextQuestionLink;

    @FindBy(xpath = "//UIAStaticText[contains(@name,'Finish assignment')][2]")
    public WebElement finishAssignment_roboLink;

    @FindBy(xpath = "//UIAStaticText[contains(@name,'You got it correct')]")
    public List<WebElement> gotItCorrect;

    @FindBy(xpath = "//UIAStaticText[contains(@name,'You got it incorrect')]")
    public List<WebElement> gotItInCorrect;

    @FindBy(xpath = "//UIAStaticText[contains(@name,'Reattempt')][2]")
    public WebElement Reattempt_roboLink;

    @FindBy(xpath = "//UIALink[@name = 'Finish Assignment']")
    public WebElement finishAssignment_button;

    @FindBy(xpath = "//UIATextField[@name='Enter your answer']")
    public List<WebElement> textEntry_textbox;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAElement[1]")
    public WebElement select_textDropdown;

    @FindBy(xpath = "//UIAStaticText[@name='Answer2'] ")
    public WebElement answer2;

    @FindBy(xpath = "//UIAStaticText[contains(@name,'Your instructor will provide feedback')]")
    public List<WebElement> ins_feedback;

    @FindBy(xpath = "//UIAStaticText[@label='This is a FeedbackText']")
    public WebElement stu_feedback;

    @FindBy(xpath = "//UIAStaticText[@label='Teacher Feedback']")
    public WebElement ins_feedbackLabel; //instructor feedback label in student side

    @FindBy(xpath = "//UIAStaticText[@name='Create new note']")
    public WebElement createNewNote_label;

    @FindBy(xpath = "//UIAStaticText[@name='Notes *']")
    public WebElement Notes_label;

    @FindBy(xpath = "//UIAStaticText[@name='Associate Notes to']")
    public WebElement associateNoteTo_label;

    @FindBy(xpath = "//UIATextField[contains(@value,'Add a note')]")
    public WebElement addNote_textbox;

    @FindBy(xpath = "//UIAStaticText[@name='Upload File']")
    public WebElement upload_link;

    @FindBy(xpath = "//UIAStaticText[@name='Cancel']")
    public WebElement cancel_button;

    @FindBy(xpath = "//UIAStaticText[@name= 'Save']")
    public WebElement save_button;

    @FindBy(xpath = "//UIAStaticText[@name= 'Please provide a note']")
    public WebElement emptyNote_message;

    @FindBy(xpath = "//UIAButton[@name='Choose Existing']")
    public WebElement choose_Exiting;

    @FindBy(xpath = "//UIATableCell[@name='Camera Roll']")
    public WebElement cameraRoll;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIACollectionView[1]/UIACollectionCell")
    public List<WebElement> photos;

    @FindBy(xpath = "//UIAStaticText[@name='deleteicon']")
    public WebElement deleteIcon;

    @FindBy(xpath = "//UIAStaticText[@name='image.jpg']")
    public WebElement imageIcon;

    @FindBy(xpath = "//UIAStaticText[contains(@name,'You should choose the course or a chapter')]")
    public WebElement help_msg;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText")
    public List<WebElement> expanded_course;

    @FindBy(xpath = "//UIAStaticText[contains(@name,'You have selected')]")
    public WebElement youHvSelected_label;

    @FindBy(xpath = "//UIAStaticText[@name='It seems you have not done any activity yet.']")
    public WebElement noActivityMsg;

    @FindBy(xpath = "//UIAStaticText[contains(@name,'You have to choose a node to which this')]")
    public WebElement notificationMsg;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText")
    public List<WebElement> noteEntry;


    @FindBy(xpath = "//UIALink[@name='image.jpg']")
    public WebElement imageLink;

    @FindBy(xpath = "//UIAStaticText[@name='Note']")
    public WebElement note_link;

    @FindBy(xpath = "//UIAStaticText[@name='Add Tags']")
    public List<WebElement> addTag;

    @FindBy(xpath = "//UIAStaticText[@name='Edit Tags']")
    public List<WebElement> editTags;

    @FindBy(className = "UIATextField")
    public List<WebElement> addTag_textbox;

    @FindBy(xpath = "//UIAStaticText[@name='Done']")
    public List<WebElement> done_button;

    @FindBy(xpath = "//UIALink")
    public List<WebElement> closeNotes;

    @FindBy(xpath = "//UIAStaticText[@name='custom add tag']")
    public WebElement customAddTag;

    @FindBy(xpath = "//UIAStaticText[@name='custom add tag']")
    public List<WebElement> customAddTags;

    @FindBy(xpath = "//UIATextField[contains(@value,'Search tag')]")
    public WebElement search_textbox;



}
