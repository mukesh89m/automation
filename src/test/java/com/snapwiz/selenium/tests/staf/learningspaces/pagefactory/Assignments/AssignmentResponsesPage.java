

package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by sumit on 31/12/14.
 */
public class AssignmentResponsesPage {

    @FindBys({@FindBy(css = "span[class='ls-assignment-due-date ls-extended-due-date']")})
    List<WebElement> extendedDueDate;//extended due date field in Assignment Response Page
    public List<WebElement> getExtendedDueDate() {
        return extendedDueDate;
    }

    @FindBys({@FindBy(className = "ls-assignment-due-date")})
    List<WebElement> originalDueDate;//original/initial due date field in Assignment Response Page

    public List<WebElement> getOriginalDueDate() {
        return originalDueDate;
    }

    @FindBys({@FindBy(className = "ls-extended-due-date-icon")})
    List<WebElement> visualIndicatorForDueDate;//visual indicator for due date in Assignment response page

    public List<WebElement> getVisualIndicatorForDueDate() {
        return visualIndicatorForDueDate;
    }

    @FindBy(css = "div[class='ls-assignment-date-block extended-due-date-popup']")
    WebElement visualIndicatorToolTip;//extended due date field in Current Assignments page

    public WebElement getVisualIndicatorToolTip() {
        return visualIndicatorToolTip;
    }

    @FindBy(className = "idb-gradebook-question-content")
    WebElement viewResponse_link; //view Response link

    public WebElement getViewResponse_link() {
        return viewResponse_link;
    }

    @FindBy(css = "span[class='ls-assignment-grading-title ls-assignment-total-points']")
    WebElement totalPoints;

    public WebElement getTotalPoints() {
        return totalPoints;
    }

    @FindBy(xpath = "(//span[contains(@class,'ls-assignment-name instructor-assessment-review')])[2]")
    WebElement previewOfAssignment;

    public WebElement getPreviewOfAssignment() {
        return previewOfAssignment;
    }

    @FindBy(css = "div[class='idb-gradebook-content-coloumn-total-score idb-gradebook-content-total']")
    WebElement grade;

    public WebElement getGrade() {
        return grade;
    }


    @FindBy(id = "idb-gradeBook-title")
    WebElement pageTitle; //page title

    public WebElement getPageTitle() {
        return pageTitle;
    }

    @FindBy(className = "tab_title")
    WebElement tabName; //tab name

    public WebElement getTabName() {
        return tabName;
    }

    @FindBy(css = "span[class='student-name-label']")
    WebElement studentNameOnTopRight; //student name in assessment response page

    public WebElement getStudentNameOnTopRight() {
        return studentNameOnTopRight;
    }

    @FindBy(css = "span[class='idb-gradebook-assignment-username']")
    WebElement studentName; //student name in assessment response page

    public WebElement getStudentName() {
        return studentName;
    }

    @FindBy(id = "student-profile-icon")
    WebElement studentProfileIcon; // student Profile Icon in assessment response page

    public WebElement getStudentProfileIcon() {
        return studentProfileIcon;
    }

    @FindBy(css = "div[class='idb-gradebook-content-coloumn idb-gradebook-content-total']")
    WebElement totalMarksColumn; //total Marks column

    public WebElement getTotalMarksColumn() {
        return totalMarksColumn;
    }

    @FindBy(css = "div[class='idb-gradebook-content-coloumn-total-score idb-gradebook-content-total']")
    WebElement totalMarks; //total Marks

    public WebElement getTotalMarks() {
        return totalMarks;
    }

    @FindBy(css = "div[class='idb-gradebook-content-coloumn-complete idb-gradebook-content-perc-complete']")
    WebElement percentComplete; //percentage complete

    public WebElement getPercentComplete() {
        return percentComplete;
    }

    @FindBy(css = "div[class='ls-view-response-link']")
    WebElement viewResponseLink; //view Response Link
    public WebElement getViewResponseLink() {
        return viewResponseLink;
    }

    @FindBy(css = "div[class='idb-gradebook-content-coloumn idb-gradebook-question-coloumn']")
    List<WebElement> questionLabels;//Q1, Q2 label

    public List<WebElement> getQuestionLabels() {
        return questionLabels;
    }

    @FindBy(css = "span[class='ins-assignment-button-sprite instructor-assignment-refresh']")
    WebElement refreshButton; //get refresh button
    public WebElement getRefreshButton() {
        return refreshButton;
    }

    @FindBy(id = "idb-toc-response-backbutton")
    WebElement backButton; //back button

    public WebElement getBackButton() {
        return backButton;
    }

    @FindBy(id = "idb-grade-now-link")
    WebElement enterGrade; //enter grade link

    public WebElement getEnterGrade() {
        return enterGrade;
    }

    @FindBy(css = "input[class='idb-grade-points']")
    List<WebElement> gradeBox;

    public List<WebElement> getGradeBox() {
        return gradeBox;
    }

    @FindBy(id = "assignment-name")
    WebElement assessmentName; //assessment Name in response page for a particular question

    public WebElement getAssessmentName() {
        return assessmentName;
    }

    @FindBy(id = "current-question-Index")
    WebElement questionIndex; //question Index in response page for a particular question

    public WebElement getQuestionIndex() {
        return questionIndex;
    }

    @FindBy(id = "student-details")
    WebElement studentLabelAndName; //student name in response page for a particular question

    public WebElement getStudentLabelAndName() {
        return studentLabelAndName;
    }

    @FindBy(id = "view-user-question-performance-score-box")
    WebElement score; //score in response page for a particular question

    public WebElement getScore() {
        return score;
    }

    @FindBy(className = "view-user-question-performance-save-btn")
    WebElement saveButton; //save button in response page for a particular question

    public WebElement getSaveButton() {
        return saveButton;
    }


    @FindBy(id = "writeboard-review-student-label")
    WebElement writeBoardLabel; //writeBoard Label in response page for a particular question

    public WebElement getWriteBoardLabel() {
        return writeBoardLabel;
    }

    @FindBy(id = "show-your-work-label")
    WebElement writeBoardFeedback; //writeBoard feedback in response page for a particular question

    public WebElement getWriteBoardFeedback() {
        return writeBoardFeedback;
    }

    @FindBy(id = "view-user-question-performance-feedback-box")
    WebElement feedbackTextArea; // feedbackTextArea in response page for a particular question

    public WebElement getFeedbackTextArea() {
        return feedbackTextArea;
    }

    @FindBy(id = "view-user-question-performance-save-success-message")
    WebElement savedSuccessfullyMessage; // saved Successfully Message in response page for a particular question
    public WebElement getSavedSuccessfullyMessage() {
        return savedSuccessfullyMessage;
    }

    @FindBy(id = "next-question-performance-view")
    WebElement nextArrow; // next Arrow in response page for a particular question

    public WebElement getNextArrow() {
        return nextArrow;
    }

    @FindBy(id = "prev-question-performance-view")
    WebElement previousArrow; // Previous Arrow in response page for a particular question

    public WebElement getPreviousArrow() {
        return previousArrow;
    }

    @FindBy(className = "idb-question-feedback-icon")
    WebElement commentIcon; // comment Icon after instructor gives feedback

    public WebElement getCommentIcon() {
        return commentIcon;
    }

    @FindBy(className = "idb-gradebook-question-deleted")
    WebElement crossIconOnDeletedQuestion;

    public WebElement getCrossIconOnDeletedQuestion() {
        return crossIconOnDeletedQuestion;
    }

    @FindBy(className = "idb-gradebook-content-column-deactivated-question")
    WebElement crossIcon;

    public WebElement getCrossIcon() {
        return crossIcon;
    }

    @FindBy(xpath = "(//div[@class='idb-question-count-wrapper'])/div[3]")
    WebElement deletedQuestion;

    public WebElement getDeletedQuestion() {
        return deletedQuestion;
    }

    @FindBy(xpath = "(//span[@class='idb-gradebook-content-column-deactivated-question'])[2]")
    WebElement secondQuestionCrossIcon;

    public WebElement getSecondQuestionCrossIcon() {
        return secondQuestionCrossIcon;
    }

    @FindBy(xpath = "(//div[@class='idb-gradebook-content-coloumn'])[7]")
    WebElement viewResponse;

    public WebElement getViewResponse() {
        return viewResponse;
    }

    @FindBy(id = "view-user-question-performance-feedback-box")
    WebElement feedbackBox;

    public WebElement getFeedbackBox() {
        return feedbackBox;
    }

    @FindBy(className = "ls-assignment-status-grades-released")
    WebElement reviewStatus;

    public WebElement getReviewStatus() {
        return reviewStatus;
    }

    @FindBy(xpath = "(//div[@class='ls-instructor-activity-cards-status'])[4]")
    WebElement assignmentStatus;

    public WebElement getAssignmentStatus() {
        return assignmentStatus;
    }

    @FindBy(xpath = "(//div[@class='idb-gradebook-content-coloumn'])[10]")
    WebElement viewResponsesforDeletedquestion;

    public WebElement getViewResponsesforDeletedquestion() {
        return viewResponsesforDeletedquestion;
    }

    @FindBy(id = "ls-assignment-reviewed-count")
    WebElement gradeBoxCount;

    public WebElement getGradeBoxCount() {
        return gradeBoxCount;
    }

    @FindBy(xpath = "//div[@id='question-raw-content-preview']/label")
    public List<WebElement> questionText_assignmentReviewPage;

    @FindBy(css = "span[class='ls-assignment-name instructor-assessment-review']")
    public List<WebElement> assessmentName_AssignmentResponse;


    @FindBy(xpath = "//div[@class='idb-gradebook-content-coloumn idb-gradebook-question-coloumn'][position()>5]/span")
    public WebElement deActivateQuestionPosition;


    @FindBy(xpath = "//div[@class='idb-gradebook-content-coloumn idb-gradebook-question-coloumn'][position()>5]")
    public WebElement olderVersionQuestionPosition;


    @FindBy(xpath = "//div[@class='idb-gradebook-content-coloumn idb-gradebook-question-coloumn'][position()>6]")
    public WebElement publishedQuestionPosition;

    @FindBy(xpath = "(//span[@class='idb-gradebook-assignment-username'])[position()>2]/ancestor::div[2]/div[@class='idb-question-score-wrapper']/div[last()]")
    public WebElement deActivatedQuestion_Text;// deActivatedQuestion_Text for 3rd and 4th student

    @FindBy(xpath = "(//span[@class='idb-gradebook-assignment-username'])[last()-2]/ancestor::div[2]/div[last()]/div[last()]/span")
    public WebElement tickMark_assayQuestion;

    @FindBy(xpath = "(//span[@class='idb-gradebook-assignment-username'])[position()<3]/ancestor::div[2]/div[@class='idb-question-score-wrapper']/div[last()]/span")
    public WebElement xIcon_deActivatedQuestion;

    @FindBy(xpath = "(//span[@class='idb-gradebook-assignment-username'])[position()>2]/ancestor::div[2]/div[2]/span")
    public WebElement totalMark; //total mark for student 3rd and 4th

    @FindBy(xpath = "//div[contains(@class,'ls-instructor-activity-cards ls-instructor-cards-fourth submitted')]/div")
    public List<WebElement> graded_box;

    @FindBy(xpath = "//div[@class='students-performance-report-content-rows']/div[position()<5]/div[6]/span")
    public List<WebElement> attemptedQuestion;//attemptQuestion for student 1 & 2

    @FindBy(xpath = "//div[@class='students-performance-report-content-rows']/div[position()>4]/div[6]/span")
    public List<WebElement> attemptedQuestion1;//attemptQuestion for student 3 & 4

    @FindBy(id = "idb-gradebook-backbutton")
    WebElement back_Button;

    public WebElement getBack_Button() {
        return back_Button;
    }

    @FindBy(css = "span[class='ls-file-delete-file-name ellipsis']")
    List<WebElement> uploadFileList;

    public List<WebElement> getUploadFileList() {
        return uploadFileList;
    }

    @FindBy(xpath = "//a[text()='Finish Assignment']")
    WebElement finishAssignment;

    public WebElement getFinishAssignment() {
        return finishAssignment;
    }

    @FindBy(xpath = "//div[@title='Release Feedback for All']")
    WebElement releaseFeedbackForAll;

    public WebElement getReleaseFeedbackForAll() {
        return releaseFeedbackForAll;
    }

    @FindBy(xpath = "//div[@title='Release Grade for All']")
    WebElement releaseGradeForAll;
    public WebElement getReleaseGradeForAll() {
        return releaseGradeForAll;
    }

    public By byReleaseGradeForAll=By.xpath("//div[@title='Release Grade for All']");

    @FindBy(id = "writeboard-review-student-label")
    public WebElement workBoardMessage;

    @FindBy(id = "show-your-work-label")
    public WebElement plusWorkBoard;

    @FindBy(className = "close-iframe-question-content")
    public WebElement crossIconOnWorkBoard;

    @FindBy(id = "main-controls")
    public WebElement toolControl;

    @FindBy(xpath = "//iframe[contains(@id,'whiteBoard_iframe_kedukWBTEACHER__whiteBoard_JSONData__false')]")
    public WebElement frame;

    @FindBy(css = "div[class='idb-gradebook-content-coloumn idb-gradebook-content-perc-complete']")
    public WebElement percentageColumn;

    @FindBy(css = "div[class='idb-gradeBook-feedback-section-release idb-gradeBook-release-feedback-section idb-gradeBook-feedback-released']")
    public WebElement feedback_Box;



    @FindBy(css = "div[class='idb-gradeBook-grades-section-release idb-gradeBook-release-grades-section idb-gradeBook-grades-released']")
    public WebElement gradeBook_Box;


    @FindBy(className = "user-response-question-prompt")
    public WebElement details;

    @FindBy(xpath = ".//*[@id='question-raw-content-preview']/label/div/ul/li/span/a")
    public WebElement resourceTitle;


    @FindBy(css = "div[class='idb-gradeBook-grades-section-release idb-gradeBook-release-grades-section idb-gradeBook-grades-released']")
    public WebElement grade_Box;

    @FindBy(id = "writeboard-review-student-label")
    public WebElement noSubmmitedWriteBoardFeedback; //writeBoard feedback in response page for a particular question

    @FindBy(id = "ls-gradebook-refresh-icon")
    public WebElement refresh_icon;


    @FindBy(id = "assignment-name")
    public WebElement label_assignmentResponsesPage;

    @FindBys({@FindBy(className = "idb-gradebook-content-coloumn")})
    List<WebElement> pointsNrEditFieldList;//

    public List<WebElement> getPointsNrEditFieldList() {
        return pointsNrEditFieldList;
    }

    @FindBys({@FindBy(className = "multi-part-question-feedback")})
    List<WebElement> textArea_FeedbackList;//

    public List<WebElement> getTextArea_FeedbackList() {
        return textArea_FeedbackList;
    }


    @FindBys({@FindBy(className = "idb-gradebook-content-coloumn")})
    List<WebElement> viewResponseList;//

    public List<WebElement> getViewResponseList() {
        return viewResponseList;
    }

    @FindBys({@FindBy(css = "span[class='idb-gradebook-content-label-text idb-gradebook-username']")})
    List<WebElement> link_UserName;//

    public List<WebElement> getLink_UserName() {
        return link_UserName;
    }


    @FindBy(className = "idb-question-feedback-icon")
    public WebElement icon_feedback;

    @FindBy(className = "question-label")
    public WebElement question_Number;

    @FindBy(id = "close-view-responses")
    public WebElement closeTab;

    @FindBy(id = "question-points")
    public WebElement pointsOnQuestion;

    @FindBy(id = "teacher-btn")
    public WebElement teacherFeedback;

    @FindBy(id = "close-discussion-widget-response")
    public WebElement closeIcon_dwResponsePage;

    @FindBy(className = "idb-question-manually-graded")
    public WebElement icon_tickMark;


    @FindBy(xpath = "//div[@data-localize='gradeReleased']")
    public WebElement releaseGrade;

    @FindBy(css = "div[data-localize='releaseGrade']")
    public WebElement releaseGrade_link;


    @FindBy(className = "ls-ins-feedback-text-icon")
    public WebElement T_Icon;

    @FindBy(css = "a[class='re-icon re-fontfamily redactor-btn-image']")
    public WebElement fontFamily;

    @FindBy(css = "a[class='redactor_dropdown_s6 opensans-default-font-active']")
    public List<WebElement> openSansFont;

    @FindBy(xpath = "//div[@id='view-user-question-performance-feedback-box']/p")
    public WebElement text_Feedback;

    @FindBy(css = "a[class=' redactor_dropdown_s5']")
    public List<WebElement> monoSpaceFont;

    @FindBy(xpath = "//div[@id='view-user-question-performance-feedback-box']//inline")
    public WebElement text_FeedbackAfterSelect;


    @FindBy(css = "a[class='re-icon re-language redactor-btn-image']")
    public WebElement languagePalette;


    @FindBy(xpath = "//div[contains(@class,'redactor_dropdown redactor_dropdown_box_language')]/a")
    public List<WebElement> languageOptions;

    @FindBy(xpath="//div[contains(@id,'language-palette')]")
    public WebElement langaugePalette_popup;


    @FindBy(xpath="//div[contains(@class,'palette-header')]")
    public WebElement langaugePalette_header;

    @FindBy(className = "view-user-discussion-performance-save-btn")
    public  WebElement submit;

    @FindBy(className = "ls-assignment-status-grades-released")
    public List<WebElement> review_Status ;

    @FindBy(css = "span[class='btn sty-green submit-assign tab-view']")
    public  WebElement assign_button; //assign button in update assignment page

    @FindBy(id = "assign-cancel")
    public  WebElement cancel_button; //cancel button in update assignment page

    @FindBy(className = "idb-gradebook-assignment-username")
    public List<WebElement> student_Name;

    @FindBy(xpath = "//div[@id='select-all-drop-down-wraper']/div//li/a")
    public List<WebElement> studentDropDown;

    @FindBy(css = "div[class='select-all-drop-down-wraper scrollbar-wraper']")
    public WebElement studentPostDropDown;

    @FindBy(xpath = "//div[@title='Post a message']")
    public WebElement postAMessage;

    @FindBy(id = "email-message-content")
    public WebElement messageTextField;

    @FindBy(id = "send-mail")
    public WebElement sendButton;

    @FindBy(css = "input[class^='update-time-value']")
    public List<WebElement> updateTime;

    @FindBy(css = "span[class^='update-time-button']")
    public List<WebElement> updateTime_button;

    @FindBy(css = ".idb-gradebook-preformance-links.ls-grade-now-link")
    public WebElement timeSpentCard;

    @FindBy(className = "update-time-error")
    public List<WebElement> updateTimeError;

    @FindBy(css = ".idb-gradebook-view-username.assigned-time-limit")
    public List<WebElement> assignedTimeLimit;

    @FindBy(css = "span[class='idb-gradebook-view-username']")
    public WebElement timeSpent;

    @FindBy(css = ".timer-icon.time-exhausted")
    public WebElement timeExhaustedIcon;

    @FindBy(css = "span[class^='idb-Reviewed-icon idb-Reviewed']>img")
    public  WebElement tickMark;

    @FindBy(css = "span[class^='idb-Reviewed-icon-checkbox ']")
    public WebElement userName_checkBox;

    @FindBy(css = "div[class='ls-not-applicable ls-enable-not-applicable']")
    public WebElement notApplicable_button;

    @FindBy(css = ".notApplicableYes")
    public WebElement notApplicable_yes;

    @FindBy(css = ".notApplicableNo")
    public WebElement notApplicable_No;

    @FindBy(css = "span[class='idb-gradebook-view-username']")
    public WebElement timeSpentByStudent;

    @FindBy(className = "extend-due-date-label")
    public WebElement extendDueDateLabel;

    public By byExtendDueDateLabel=By.className("extend-due-date-label");


    @FindBy(css=".holder>li:nth-of-type(1)>span")
    public WebElement extendDueDate_classSectionSuggestion;

    public By extendDueDate_emptyClassSection=By.cssSelector(".holder>li:nth-of-type(1)>span");

    @FindBy(className = "extend-due-date-assignment-name")
    public WebElement extendDueDate_AssignmentName;

    @FindBy(className = "update-extend-due-date")
    public WebElement updateExtendDueDate;

    @FindBy(className = "ls-assignment-date-block")
    public WebElement extendDueDateMessage;

    @FindBy(css = ".ir-ls-assign-dialog-assign-to-label.ir-ls-assign-dialog-label>span")
    public WebElement newDueDate_label;

    @FindBy(css = ".dateImg>#extended-due-date")
    public WebElement extended_dueDate;

    @FindBy(css = "#extended-due-time")
    public WebElement extended_dueTime;

    @FindBy(css = ".cancel-extend-due-date")
    public WebElement extended_dueTime_Cancel_button;

    @FindBy(css="#ui-datepicker-div")
    public WebElement datePicker;

    @FindBy(className = "extend-due-date-close-icon")
    public WebElement extendDueDateCloseIcon;

    @FindBy(css=".extend-due-date-dialog-header>span")
    public List<WebElement> extendDueDateDialogHeader;

    @FindBy(css=".extend-due-date-for-wrapper>span")
    public List<WebElement> extendDueDateFor_label;

    @FindBy(css = "span[class*='idb-Reviewed-icon-checkbox idb-Reviewed']>img")
    public List<WebElement> student_checkBox;

    @FindBy(css = ".gradebook-menu-icon")
    public WebElement menuIcon;

    @FindBy(css = ".closebutton")
    public WebElement closeIcon;

    @FindBy(css = ".gradebook-actions")
    public WebElement action_link;

    @FindBy(xpath = "//div[@title='Not Applicable']")
    public WebElement notApplicable;

    @FindBy(className = "notApplicableYes")
    public WebElement yes_NotApplicable;

    @FindBy(css = "div[class='ls-assignment-date-block']>span[class='ls-assignment-due-date']")
    public WebElement originalDueDates;

    @FindBy(css = "div[class='ls-assignment-date-block']>span[class='ls-assignement-not-submitted']")
    public WebElement originalDueDate_notSubmitted;

    @FindBy(css = "div[class='ls-assignment-date-block']>span[class='ls-assignment-due-date ls-extended-due-date']")
    public List<WebElement> extendedDueDates;

    @FindBy(className = "item-text")
    public List<WebElement> classSection_list;

    @FindBy(xpath = ".//*[@id='share-with_feed']/div[1]/div/div/div")
    public WebElement thumb;

    @FindBy(className = "error-message")
    public WebElement classSuggestion_errorMsg;

    @FindBy(className = "no-results-message")
    public WebElement classSuggestion_noResultMsg;

    @FindBy(className = "maininput")
    public WebElement classSuggestion_textbox;

    @FindBy(className = "idb-resume-grading-content-left")
    public WebElement resumeGrading_Text;



    @FindBy(css = "div[title = 'Grade Released']")
    public WebElement label_gradesReleased;

    @FindBy(css = "div[title = 'Grade Released']")
    public List<WebElement> label_gradesReleasedList;

    @FindBy(className = "extend-due-date-label")
    public List<WebElement> extendDueDateLabelList;

    @FindBy(css = "div[class='idb-gradeBook-grades-section-release idb-gradeBook-release-grades-section idb-gradeBook-grades-released']")
    public List<WebElement> gradeBook_BoxList;









}
