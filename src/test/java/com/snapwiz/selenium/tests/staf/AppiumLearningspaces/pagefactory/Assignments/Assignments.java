package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.Assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/*
 * Created by sumit on 31/12/14.
 */
public class Assignments {

    @FindBy(css = "span[class='ls-assignment-due-date assignment-extended-due-date']")
    WebElement extendedDueDate;//extended due date field in Assignments page of student side
    public WebElement getExtendedDueDate() {
        return extendedDueDate;
    }

    @FindBy(className = "ls-assignement-not-submitted")
    WebElement originalDueDate;//original/initial due date field in Assignments page of student side
    public WebElement getOriginalDueDate() {
        return originalDueDate;
    }

    @FindBy(className = "ls-assignment-post-label")
    WebElement noAssignmentMessage;//no Assignment Message Assignments page of student side
    public WebElement getNoAssignmentMessage() {
        return noAssignmentMessage;
    }

    @FindBy(className = "ls-overall-score-percent")
    public WebElement overallScorePercentage; //overall Score Percentage

    public WebElement getOverallScorePercentage() {
        return overallScorePercentage;
    }

    @FindBys(@FindBy(className = "ls-assignment-not-due-date-title"))
    List<WebElement> dueDateMessage;

    public List<WebElement> getDueDateMessage() {
        return dueDateMessage;
    }

    @FindBy(css = "span[class='ls-assignment-name instructor-assessment-review']")
    public WebElement assignmentName;// assignment name

    public WebElement getAssignmentName() {
        return assignmentName;
    }

    @FindBy(css = "g[class='highcharts-data-labels highcharts-tracker']")
    WebElement performanceSummary; //Performance summary page percentage

    public WebElement getPerformanceSummary() {
        return performanceSummary;
    }

    @FindBy(xpath = "//g[contains(@class,'highcharts-series highcharts-tracker')]/rect")
    WebElement questionPerformance; //question performance

    public WebElement getQuestionPerformance() {
        return questionPerformance;
    }

    @FindBy(xpath = "//rect[contains(@fill,'white')]")
    WebElement questionBar;// question bar

    public WebElement getQuestionBar() {
        return questionBar;
    }

    @FindBy(css = "span[class='next-or-submit-link']")
    WebElement goToNextLinkOnPopUp;// go to next question link on pop-up

    public WebElement getGoToNextLinkOnPopUp() {
        return goToNextLinkOnPopUp;
    }

    @FindBy(css = "span[class='re-attempt-link']")
    WebElement reAttemptLinkOnPopUp;// reattempt link on pop-up

    public WebElement getReAttemptLinkOnPopUp() {
        return reAttemptLinkOnPopUp;
    }

    @FindBy(className = "al-notification-message-body")
    public WebElement notification;// notification

    public WebElement getNotification() {
        return notification;
    }

    @FindBys(@FindBy(css = "div[class='report-sidebar-question-card-sectn question-card-brown']"))
    List<WebElement> questionCart;

    public List<WebElement> getQuestionCart() {
        return questionCart;
    }


    public WebElement getDiscussionTab() {
        return discussionTab;
    }

    @FindBy(css = "a[class='ls-toc-btn ls-right-new-btn']")
    WebElement newButton;

    public WebElement getNewButton() {
        return newButton;
    }

    @FindBy(className = "editdiscussion-text")
    WebElement editBox;

    public WebElement getEditBox() {
        return editBox;
    }

    @FindBy(css = "button[class='editdiscussion-button editdiscussion-submit']")
    WebElement submit;

    public WebElement getSubmit() {
        return submit;
    }

    @FindBys(@FindBy(css = "rect[width='40']"))
    List<WebElement> width;

    public List<WebElement> getWidth() {
        return width;
    }


    @FindBy(className = "ls-assignment-status-grading-in-progress")
    public WebElement status_inProgress; //class status inProgress in student side

    @FindBy(xpath = "//div[contains(@class,'ls-la-title-wrapper ls-assignment-cart-item')]/i[contains(@class,'ls-la-assignment-status-icon ls-la-accessed')]")
    public WebElement green_tickMark; // correct tick mark for accessed lesson

    @FindBy(className = "ls-assignment-status-submitted")
    public WebElement status_submitted; //class status submitted in student side


    @FindBy(css = "a[class='btn btn--primary btn--large btn--next long-text-button']")
    WebElement nextQuestion;

    public WebElement getNextQuestion() {
        return nextQuestion;
    }

    @FindBy(className = "arrow-top")
    WebElement arrowDropDown;

    public WebElement getArrowDropDown() {
        return arrowDropDown;
    }

    @FindBys(@FindBy(xpath = "//div[@class='questions_navigation_index']//tr"))
    List<WebElement> questionCount;

    public List<WebElement> getQuestionCount() {
        return questionCount;
    }

    @FindBy(xpath = "//a[text()='Finish Assignment']")
    WebElement finishAssignment;

    public WebElement getFinishAssignment() {
        return finishAssignment;
    }

    @FindBy(className = "cms-deactivated-question")
    WebElement notificationMessage;

    public WebElement getNotificationMessage() {
        return notificationMessage;
    }

    @FindBy(xpath = "(//div[contains(@class,'report-sidebar-question-card-sectn question-card-brown')])[2]")
    WebElement secondquestioncart;

    public WebElement getSecondquestioncart() {
        return secondquestioncart;
    }

    @FindBy(className = "assignment-score")
    public WebElement score;

    public WebElement getScore() {
        return score;
    }

    @FindBy(className = "assignment-marks")
    WebElement marks;

    public WebElement getMarks() {
        return marks;
    }

    @FindBy(xpath = "(//div[contains(@class,'report-sidebar-question-card-sectn question-card-pink')])[2]")
    WebElement secondquestioncartt;

    public WebElement getSecondquestioncartt() {
        return secondquestioncartt;
    }

    @FindBy(xpath = "//div[@class='questions_navigation_index']//tr/td")
    public List<WebElement> questionLabel;

    @FindBy(xpath= "//a[contains(@class,'btn btn--primary btn--large')]")
    WebElement submitAssignment;

    public WebElement getSubmitAssignment() {
        return submitAssignment;
    }

    @FindBy(xpath = "//input[@type='button']")
    public List<WebElement> submitButton;

    @FindBy(xpath = "//input[@title='Finish']")
    public WebElement finish;

    @FindBy(css = "i[class='ls-icon-img ls--like-icon']")
    public WebElement likeIcon;

    @FindBy(css = "i[class='ls-icon-img ls--comments-icon']")
    public WebElement commentIcon;

    @FindBy(xpath = "//div[@class='ls-assignment-name-block']/img")
    public WebElement assessmentIcon;

    @FindBy(className = "ls-overall-score-text")
    public WebElement overAll_score;

    @FindBy(xpath = "(//span[@class='question-set-add-text'])[2]")
    public WebElement new_Link;

    @FindBy(xpath = "(//a[contains(@id,'sbSelector_')])[6]")
    public WebElement combBox_publish;

    @FindBy(xpath = "(//a[contains(@id,'sbSelector_')])[5]")
    public WebElement combBox_jumpToQ;

    @FindBy(id = "questionRevisions")
    public WebElement option_Revisions;


    @FindBy(className = "cms-question-revision-title-text")
    public WebElement label_QuestionAndRevisionHistory;


    @FindBy(id = "cms-question-revision-published-version")
    public WebElement label_PublishedV1;


    @FindBy(id = "cms-question-revision-new-version-link")
    public WebElement link_CreateNewVersion;

    @FindBy(id = "cms-question-revision-get-diff")
    public WebElement button_GetDiff;


    @FindBy(id = "cms-question-revision-time")
    public WebElement questionNewVersions_timeStamp;

    @FindBy(id = "cms-question-revision-user-name")
    public WebElement questionNewVersions_firstName;

    @FindBy(id = "cms-question-revision-version-and-action")
    public WebElement questionNewVersions_status;

    @FindBy(id = "cms-question-revision-preview-button")
    public WebElement button_Preview;

    @FindBy(linkText = "Publish")
    public WebElement link_publish;


    /*@FindBy(xpath = "//div[@id = 'cms-question-revision-checkbox']//input")
    public WebElement checkBox_TimeStamp;*/


    @FindBys(@FindBy(xpath = "//ol[contains(@class,'data')]"))
    List<WebElement> questionTextList_GetDiffPopup;

    public List<WebElement> getQuestionTextList_GetDiffPopup() {
        return questionTextList_GetDiffPopup;
    }


    @FindBys(@FindBy(xpath = "//div[@id = 'cms-question-revision-checkbox']//input"))
    List<WebElement> checkBox_TimeStampList;

    public List<WebElement> getCheckBox_TimeStampList() {
        return checkBox_TimeStampList;
    }

    @FindBy(className = "question-no")
    public WebElement questionText;


    @FindBys(@FindBy(xpath = "(//div[@class='overview'])[2]//a"))
    List<WebElement> questionsList_JumpToQ;

    public List<WebElement> getQuestionsList_JumpToQ() {
        return questionsList_JumpToQ;
    }

    @FindBy(id = "question-mp-raw-content-0")
    public WebElement questionsEditBox;

    @FindBy(id = "question-id-label")
    public WebElement label_questionID;

    @FindBy(id = "close-question-diff-dialog")
    public WebElement button_Close_GetDiffPopUp;





    @FindBy(xpath = "//span[contains(text(),'IT204_static Assessment_807')]")
    public WebElement assignment;


    @FindBy(className = "al-quit-diag-test-icon")
    public WebElement button_close;//Close button while attempting the test

    @FindBy(css = "div[class='report-sidebar-question-card-sectn question-card-grey']")
    public  List<WebElement> questionCard;

    @FindBy(className = "ls-question-status-indicator-wrapper")
    public  WebElement statusBox;

    @FindBy(className = "diagnostic-test-attempt-notification-title")
    public WebElement popUp_diagnosticTestHeader;//Diagnostic test pop up header

    @FindBy(className = "diagnostic-test-attempt-notification-message")
    public WebElement popUp_diagnosticTestBody;//Diagnostic test pop up body

    @FindBy(className = "diagnostic-test-attempt-notification-close")
    public WebElement popUp_diagnosticTestClose;//Close icon on Diagnostic Test pop up

    @FindBy(className = "diagnostic-test-attempt-notification-cancel")
    public WebElement popUp_diagnosticTestCancel;//Cancel button on Diagnostic Test pop up

    @FindBy(className = "diagnostic-test-attempt-text")
    public WebElement popUp_diagnosticTest_beginDiagnostic;//Begin Diagnostic button on Diagnostic Test pop up

    @FindBy(className = "ls-self-rating-message")
    public WebElement popUp_diagnosticTest_enterConfidenceLevel;//Enter confidence level on Diagnostic Test pop up

    @FindBy(xpath = "//a[starts-with(@class,'ls-confidence-level-img ls')]")
    public List<WebElement> popUp_diagnosticTest_confidenceLevelIcons;//Confidence level icons on Diagnostic Test pop up

    @FindBy(className = "ls-assessment-continue-btn")
    public WebElement popUp_diagnosticTest_arrow;//Arrow on Diagnostic Test pop up after clicking on begin diagnostic

    @FindBy(className = "al-notification-message-header")
    public WebElement popUp_error;//Error pop up

    @FindBy(css = "div.ls-static-practice-test-submit-button")
    public WebElement submitAnswer;

    @FindBy(css = "div.ls-static-practice-test-next-button")
    public WebElement nextButton;

    @FindBy(className = "ls-assessment-continue-btn")
    public List<WebElement> list_popUp_diagnosticTest_arrow;//List of arrows on Diagnostic Test pop up after clicking on begin diagnostic under assignment tab
























    @FindBy(css = "label[class = 'control-label redactor_editor']")
    public WebElement MPQquestionText;

    @FindBy(css = "button[class= 'next-question-part']")
    public WebElement button_gotoNextQuestionPart;

    @FindBy(css = "a[class= 'btn btn--primary btn--large btn--next long-text-button']")
    public WebElement button_NextQuestion;

    @FindBy(className = "ls-question-status-indicator-wrapper")
    public WebElement label_status;

    @FindBy(className = "al-diag-chapter-details")
    public WebElement questionsCount;

    @FindBy(className = "question-status-deactivated-text")
    public WebElement label_deactivated;

    @FindBy(css = "div[title='Report Content Errors']")
    public WebElement icon_reportContentError;


    @FindBy(id = "text-area-content-issue")
    public WebElement textArea_EnterContentIssue;

    @FindBy(id = "send-content-issue-btn")
    public WebElement buttonReportContent_Save;


    @FindBy(css = "div[class='add-content-error show-content-issues-dialog']")
    public WebElement dialog_ContentIssue;

    @FindBy(className = "content-issue-view-sectn-body")
    public WebElement message_ReportContentIssue;



    @FindBy(css = "input[title='Submit Answer']")
    public WebElement button_submitAnswer;


    @FindBy(id= "send-content-issue")
    public WebElement link_yes;


    @FindBy(className = "content-error-resolved-it-btn")
    public WebElement button_Resolved;

    @FindBy(id = "deliver-course")
    public WebElement link_summary;


    @FindBy(className = "reported-content-issue-cmnts")
    public WebElement label_comments;


    @FindBy(css = "span[class='fix-it fixIt-qtn-content-issue']")
    public WebElement button_fixit;

    @FindBy(className = "issues-count")
    public WebElement label_issuesCount;

    @FindBy(xpath = "//div[@class='reported-content-issue-qtn-section']//div[7]")
    public WebElement label_colStatus;



    @FindBy(className = "question-no")
    public WebElement questionWithLabel;

  /*  @FindBy(id = "cms-question-preview-question-content")
    public WebElement questionWithLabel;*/


    @FindBy(className = "question-label-with-questionid")
    public WebElement questionId;

    @FindBy(css = "div[class='question-toggle-arrow-icon']")
    public WebElement icon_QuestionToggleArrow;


    @FindBy(className = "ls-reviewed-text")
    public  WebElement assignmentFilter_review;

    @FindBy(className = "multi-part-question-points")
    public WebElement label_pointsAvailable;

    @FindBy(className = "multipart-total-attempt")
    public WebElement label_answered;

    @FindBy(className = "static-assessment-point-content-box")
    public WebElement label_totalPoints;

    @FindBy(id = "assessmentTimer")
    public WebElement timer_timeSpent;

    @FindBy(xpath = "(//div[@class='tabs']//div[@class= 'tab active'])[2]")
    public WebElement tab_performance;

    @FindBy(className= "al-content-box-title-header")
    public WebElement label_performanceInLast10Qs;

    @FindBy(css= "div[class='al-difficulty-level-block al-content-box-body']")
    public WebElement labelValue_studentsGotItCorrect;

    @FindBy(css= "h3[class='al-content-box-title al-content-box-performance-title']")
    public WebElement label_studyThisTopic;


    @FindBy(className= "question-association-skill-id")
    public WebElement link_questionAssociationSkillID;

    @FindBy(xpath= ".//*[@id='part-1']//div[@class='al-customize-checkbox-small static-assessment-mark-for-review']")
    public WebElement checkBox_MarkForReview;

    @FindBy(className= "part-question-hint")
    public WebElement button_hint;


    @FindBys(@FindBy(css = "div[class='question-toggle-arrow-icon collapse']"))
    public List<WebElement> icon_upperArrowList;

    @FindBys(@FindBy(className = "control-label"))
    public List<WebElement> questionTextList;


    @FindBy(className= "question-indicator")
    public WebElement icon_blueTriangle;

    @FindBy(className= "true-false-student-answer-label")
    public WebElement button_true;

    @FindBys(@FindBy(css = "div[class='question-toggle-arrow-icon expand']"))
    public List<WebElement> icon_downArrowList;


    @FindBy(xpath= "(//div[@class='multi-part-question-container'])[2]//button")
    public WebElement button_submitThisQuestionPart;

    @FindBy(xpath= "(//div[@class='preview-single-selection-answer-choices']/div)[1]//div[@class='true-tick']")
    public WebElement trueTickMark_mcOption1;


    @FindBy(xpath= "(//div[@class='preview-single-selection-answer-choices']/div)[4]//div[@class='false-tick']")
    public WebElement falseTickMark_mcOption4;

    /*@FindBy(className= "false-tick")
    public WebElement falseTickMark_mcOption4;*/
    //div[@class='preview-single-select-answer-choice']//span[text() = 'Option 4']//..//..//..//div

    @FindBy(className= "al-diag-chapter-details")
    public WebElement questionDropdown;

    @FindBy(className= "//tr[@qindex='1']//i[@class='s s--check-green']")
    public WebElement grayTickMark_attemptedColumn;


    @FindBy(className= "//tr[@qindex='1']//i[@class='s s--check-not-attempted']")
    public WebElement redBlankMark_attemptedColumn;


    @FindBy(css= "span[title = 'Add to My Notes']")
    public WebElement tab_addToMyNotes;

    @FindBys(@FindBy(className = "choice-value"))
    public List<WebElement> optionButtonsList_multipleChoice;

    @FindBy(xpath= "//div[@class='preview-single-select-answer-choice']//span[text() = 'Option 4']//..//..//..//div")
    public WebElement button_option4;

    @FindBy(css = "input[title = 'Next Question']")
    public WebElement nextQuestionButton;


    @FindBy(linkText = "Next Question")
    public WebElement button_nextQuestion;

    @FindBy(xpath = "(//p[@class='ls-stream-share__title'])[2]")
    public WebElement label_discussionText;

    @FindBy(className = "question-data-ellipsis")
    public WebElement label_questionStem;

    @FindBy(className = "question-indicator")
    public WebElement grey_questionIndicator;


    @FindBy(xpath = "(//div[@class='tab active']//span[@class='close_tab'])[2]")
    public WebElement icon_closeforEtextBook;


    @FindBy(xpath = "//div[@class='al-diag-test-hint-drop-down-wrapper']")
    public WebElement hint_button;

    @FindBys(@FindBy(className = "question-card-question-content"))
    public List<WebElement> questionListInQuestionCard;

    @FindBys(@FindBy(className = "question-card-difficulty-level"))
    public List<WebElement> label_points;

    @FindBy(className = "al-diagtest-markForReview")
    public WebElement checkBox_markForReview;



    @FindBy(xpath = "//span[@title='Discussion']")
    public WebElement discussionTab;


    @FindBy(className = "multipart-stud-performance-total-points")
    public WebElement totalPoints;

    @FindBy(css = "span[title = 'Performance']")
    public WebElement getTab_performance;


    @FindBy(linkText = "Show All")
    public WebElement dropDown_showAll;

    @FindBy(linkText = "Marked For Review")
    public WebElement link_markedForReview;

    @FindBy(id = "al-chapter-performance-view")
    public WebElement getIcon_performancePage;

    @FindBy(id = "teacher-btn")
    public WebElement icon_instructorFeedback; //instructor feedback icon

    @FindBy(id = "zwibbler")
    public WebElement workBoard_overlay; //workBoard Overlay

    @FindBy(xpath = "//div[@id = 'white-board-view-section-wrapper']//span")
    public WebElement icon_workBoardClose; //workBoard Close Icon

    @FindBy(xpath = "//div[@class='ls-static-practice-test-next-button']//input[@title='Finish']")
    public WebElement finish_Button;

    @FindBy(css="i[class='ls-arrow ls-arrow--left']")
    public List<WebElement> performancebar_leftarrow; //performance bar in workboard


    @FindBy(css="i[class='ls-arrow ls-arrow--right']")
    public List<WebElement> performancebar_rightarrow; //performance bar in workboard

    @FindBy(xpath = "//div[@class='ls-static-practice-test-next-button']//input[@title='Next Question']")
    public WebElement next_Button;

    @FindBy(className = "ls_resource_link")
    public WebElement resourceLink;

    @FindBy(linkText = "Assignments")
    public WebElement menu_Assignments;

    @FindBy(linkText = "Current Assignments")
    public WebElement subMenu_currentAssignments;

    @FindBy(css = "ul[class='sub-nav show-sub-nav']")
    public WebElement subMenu;

    @FindBy(className = "assignment-category")
    public WebElement percentageWeightage;


}


