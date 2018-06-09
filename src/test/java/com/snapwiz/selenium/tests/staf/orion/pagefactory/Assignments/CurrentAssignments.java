package com.snapwiz.selenium.tests.staf.orion.pagefactory.Assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;


/*
 * Created by sumit on 29/12/14.
 */
public class CurrentAssignments {


    @FindBy(className = "ls-assignment-not-available")
    WebElement noAssignmentMessage;//no assignment exist in Current assignment page
    public WebElement getNoAssignmentMessage() {
        return noAssignmentMessage;
    }

    @FindBy(className = "assign-more")
    WebElement updateAssignment_button;//update Assignment button on Current assignment page
    public WebElement getUpdateAssignment_button() {
        return updateAssignment_button;
    }

    @FindBy(xpath = ".//*[@class='ls-assignment-assign-more']/span[3]")
    WebElement unAssignAssignment_button;//un-assign Assignment button on Current assignment page
    public WebElement getUnAssignAssignment_button() {
        return unAssignAssignment_button;
    }

    @FindBy(xpath = ".//*[@class='delete-notification']/span[2]")
    WebElement unAssignAssignment_popup;//un-assign Assignment pop-up text
    public WebElement getUnAssignAssignment_popup() {
        return unAssignAssignment_popup;
    }

    @FindBy(css = "span[class='cancel-delete-assignment']")
    WebElement noForUnAssign_button;//NO button on Un-assign assignment pop-up
    public WebElement getNoForUnAssign_button() {
        return noForUnAssign_button;
    }

    @FindBy(className = "delete-assignment")
    WebElement yesForUnAssign_button;//YES button on Un-assign assignment pop-up
    public WebElement getYesForUnAssign_button() {
        return yesForUnAssign_button;
    }

    @FindBy(css = "span[class='as-modal-sprite-img as-close-modal cancel-delete-assignment']")
    WebElement closeForUnAssign_icon;//close icon on Un-assign assignment pop-up
    public WebElement getCloseForUnAssign_icon() {
        return closeForUnAssign_icon;
    }

    @FindBy(css = "div[class='ls-inst-dashboard-assignment-popup-button ls--question-banks-view']")
    WebElement usePreCreatedAssignment_button;//Use Pre-created Assignment on Create New assignment pop-up
    public WebElement getUsePreCreatedAssignment_button() { return usePreCreatedAssignment_button; }


    @FindBy(className = "re-assign-text")
    WebElement reassign_button;//re assign button for updation of question assignment Current assignment page
    public WebElement getReassign_button() {
        return reassign_button;
    }

    @FindBy(className = "learning-activity-title")
    WebElement lessonAssignment;//lesson/image/cart assignment in Assignments page
    public WebElement getLessonAssignment() {
        return lessonAssignment;
    }

    @FindBy(css = "div[data-id='extend-duedate']")
    WebElement extendDueDateTab;//extend due date tab in assign pop-up
    public WebElement getExtendDueDateTab() {
        return extendDueDateTab;
    }

    @FindBy(id = "due-date")
    WebElement dueDateOnAssignPopUp;//due date field in assign pop-up
    public WebElement getDueDateOnAssignPopUp() {
        return dueDateOnAssignPopUp;
    }

    @FindBys({@FindBy(className = "ir-ls-assign-dialog-header")})
    List<WebElement> allAssignmentNameInList;
    public List<WebElement> getAssignmentNameOnAssignPopUpList() {
        return allAssignmentNameInList;
    }

    @FindBy(css="span[class='ls-assignment-name instructor-assessment-review']")
    List<WebElement> list_assignmentName; //list of assignment name in Assignment response tab
    public List<WebElement> getList_assignmentName(){return list_assignmentName;}

    @FindBys({@FindBy(className = "item-text")})
    List<WebElement> extendDueDateForField;//extend due date for field in extend due date tab in assign pop-up
    public List<WebElement> getExtendDueDateForField() {
        return extendDueDateForField;
    }

    @FindBy(id = "extended-due-date")
    WebElement newDueDateField;//new Due Date Field in extend due date tab in assign pop-up
    public WebElement getNewDueDateField() {
        return newDueDateField;
    }

    @FindBy(css = "span[class='ls-leaning-activity instructor-assessment-review']")
    WebElement DWassignmentName;//DW assignment name in Assignment page
    public WebElement getDWassignmentName() {
        return DWassignmentName;
    }

    @FindBys({@FindBy(css = "span[class='btn sty-green submit-assign']")})
    List<WebElement> updateButtonInReassign_button;//extend due date for field in extend due date tab in assign pop-up
    public List<WebElement> getUpdateButtonInReassign_button() {
        return updateButtonInReassign_button;
    }

    @FindBy(css = "span[class='ls-assignment-due-date ls-extended-due-date']")
    WebElement extendedDueDate;//extended due date field in Current Assignments page
    public WebElement getExtendedDueDate() {
        return extendedDueDate;
    }

    @FindBy(className = "ls-assignment-due-date")
    WebElement originalDueDate;//original/initial due date field in Current Assignments page
    public WebElement getOriginalDueDate() {
        return originalDueDate;
    }

    @FindBy(className = "ls-grade-book-assessment")
    WebElement viewGrade_link; //view grade link on the Current assignment page
    public WebElement getViewGrade_link(){return viewGrade_link;}

    @FindBy(id="idb-gradeBook-title")
    WebElement responsePageTitle; //response title in Assignment response tab
    public WebElement getResponsePageTitle(){return responsePageTitle;}

    @FindBy(css="span[class='ls-assignment-name instructor-assessment-review']")
    WebElement assignmentName; //assignment name in Assignment response tab
    public WebElement getAssignmentName(){return assignmentName;}

    @FindBy(css="span[class='idb-gradebook-assignment-username']")
    WebElement familyGivenName; // family GivenName in the  Assignment response tab
    public WebElement getFamilyGivenName(){return familyGivenName;}

    @FindBy(id="idb-grade-now-link")
    WebElement enterGrade_link; // enter grade link
    public WebElement getEnterGrade_link(){return  enterGrade_link;}


    @FindBy(css="input[class='idb-grade-points']")
    WebElement enterGrade_textBox; //enter grade textBox
    public WebElement getEnterGrade_textBox(){return  enterGrade_textBox;}

    @FindBy(css = "input[class='idb-grade-points invalid-value']")
    public WebElement clearGrade_textBox;

    @FindBy(xpath = "//div[starts-with(@id,'total_')]/span")
    WebElement totalMark; //total mark textBox Value
    public WebElement getTotalMark(){return totalMark;}

    @FindBy(xpath = "(//div[@class='idb-gradebook-content-coloumn'])[1]")
    WebElement gradeBookQuestionContent;
    public WebElement getGradeBookQuestionContent(){return  gradeBookQuestionContent;}

    @FindBy(className = "ls-view-response-link")
    WebElement viewResponseLink;
    public WebElement getViewResponseLink(){return viewResponseLink;}

    @FindBy(className = "control-label")
    WebElement questionText;//question Name
    public WebElement getQuestionText(){return  questionText;}

    @FindBy(id="view-user-question-performance-score-box")
    WebElement performanceScoreBox; //grade box
    public WebElement getPerformanceScoreBox(){return performanceScoreBox;}

    @FindBy(id="view-user-question-performance-feedback-box")
    WebElement feedBack_textBox; //feedback text area
    public  WebElement getFeedBack_textBox(){return feedBack_textBox;}

    @FindBy(className = "view-user-question-performance-save-btn")
    WebElement save_button;
    public WebElement getSave_button(){return  save_button;}

    @FindBy(id="view-user-question-performance-save-success-message")
    WebElement saveMessage;// successful save message
    public WebElement getSaveMessage(){return saveMessage;}

    @FindBy(id="next-question-performance-view")
    WebElement nextArrow;
    public WebElement getNextArrow(){return nextArrow;}

    @FindBy(id="prev-question-performance-view")
    WebElement prevArrow;
    public WebElement getPrevArrow(){return prevArrow;}

    @FindBy(css="span[class='ls-leaning-activity instructor-assessment-review']")
    WebElement dwAssignmentName;// Discussion widget assignment in assignment response page
    public WebElement getDwAssignmentName(){return dwAssignmentName;}

    @FindBy(className = "idb-gradebook-question-content")
    WebElement gradeMark;//textfield  for enter grade
    public WebElement getGradeMark(){return gradeMark;}

    @FindBy(className = "ls-comment-entry")
    WebElement dwComment; // comment field for dw assignment
    public WebElement getDwComment(){return dwComment;}

    @FindBy(className = "view-user-discussion-performance-save-btn")
    WebElement dwSave_button;
    public  WebElement getDwSave_button(){return dwSave_button;}

    @FindBy(className = "tab_title")
    WebElement currentAssignmentTitle;
    public WebElement getCurrentAssignmentTitle(){return currentAssignmentTitle; }

    @FindBy(xpath = "(//span[@class='tab_title'])[2]")
    WebElement createdAssignmentTitle;
    public WebElement getCreatedAssignmentTitle(){return createdAssignmentTitle; }

    @FindBy(xpath = "(//span[@class='close_tab'])[2]")
    WebElement closeButton;
    public WebElement getCloseButton(){return closeButton;}

    @FindBy(className = "assignment-name")
    WebElement createdAssignmentName;
    public WebElement getCreatedAssignmentName(){return createdAssignmentName;}

    @FindBy(className = "assignment-update-cancel")
    WebElement cancelButton;
    public WebElement getCancelButton(){return cancelButton;}

    @FindBys(@FindBy(id = "ls-ins-your-question-data"))
    List<WebElement> question;
    public List<WebElement> getQuestion(){return question;}

    @FindBy(id = "ls-ins-your-question-label")
    WebElement questionNo;
    public WebElement getQuestionNo(){return questionNo;}

    @FindBy(xpath = "(//span[@class='ls-ins-semibold-text'])[3]")
    WebElement questionType;
    public WebElement getQuestionType(){return questionType;}

    @FindBy(id = "ls-ins-your-question-delete-icon")
    WebElement deleteIcon;
    public WebElement getDeleteIcon(){return  deleteIcon;}

    @FindBy(id = "showExpendQuestionIcon")
    WebElement threeDots;
    public WebElement getThreeDots(){return threeDots;}

    @FindBy(className = "ls-assignment-item-author-name")
    WebElement authorNmae;
    public WebElement getAuthorNmae(){return authorNmae;}

    @FindBy(className = "expendQuestion")
    WebElement expendQuestion;
    public WebElement getExpendQuestion(){return expendQuestion;}

    @FindBy(className = "associated-content-details-label")
    WebElement difficultyLevel;
    public WebElement getDifficultyLevel(){return difficultyLevel;}

    @FindBy(className = "expendQuestionView")
    WebElement expandIcon;
    public WebElement getExpandIcon(){return expandIcon;}

    @FindBys(@FindBy(className = "ls-notification-text-span"))
    List<WebElement> notificationMessage;
    public List<WebElement> getNotificationMessage(){return notificationMessage;}

    @FindBy(xpath = "//img[@title='Close']")
    WebElement closeButtonOnNotificationMessage;
    public WebElement getCloseButtonOnNotificationMessage(){return closeButtonOnNotificationMessage;}

    @FindBy(className = "update-assignment-notification-close-btn-section")
    WebElement noOnNotificationMessage;
    public WebElement getNoOnNotificationMessage(){return noOnNotificationMessage;}

    @FindBy(className = "deleteAssignmentQuestion")
    WebElement yesOnNotificationMessage;
    public WebElement getYesOnNotificationMessage(){return yesOnNotificationMessage;}

    @FindBy(className = "ir-ls-assign-dialog-header")
    WebElement popUpTitle;
    public WebElement getPopUpTitle(){return popUpTitle;}

    @FindBy(id = "assign-cancel")
    WebElement cancelPopup;
    public WebElement getCancelPopup(){return cancelPopup;}

    @FindBy(css = "span[class='btn sty-green submit-assign']")
    WebElement assign;
    public WebElement getAssign(){return assign;}

    @FindBy(className = "delete-assigned-task")
    WebElement unAssignButtonOfVersionAssignment;
    public WebElement getUnAssignButtonOfVersionAssignment(){return unAssignButtonOfVersionAssignment;}

    @FindBy(css = "div[class='as-modal-yes-btn delete-button']")
    WebElement yesOnUnAssignPopUp;
    public WebElement getYesOnUnAssignPopUp(){return yesOnUnAssignPopUp;}

    @FindBys(@FindBy(xpath = "//div[starts-with(@class,'question-label')]"))
    List<WebElement> questionCount;
    public List<WebElement> getQuestionCount(){return questionCount;}

    @FindBy(xpath = "(//span[contains(@class,'ls-assignment-name instructor-assessment-review')])[1]")
    WebElement previewOfAssignmentOnCurrentAssignment;
    public WebElement getPreviewOfAssignmentOnCurrentAssignment(){return previewOfAssignmentOnCurrentAssignment;}

    @FindBy(className = "control-label")
    WebElement questionLabel;
    public WebElement getQuestionLabel(){return questionLabel;}

    @FindBy(xpath = "(//span[@id='ls-ins-your-question-delete-icon'])[2]")
    WebElement deleteIcontwo;
    public WebElement getDeleteIconTwo(){return  deleteIcontwo;}

    @FindBy(css = "span[class='ls-assignment-name instructor-assessment-review']")
    WebElement assessmentName;
    public WebElement getAssessmentName(){return assessmentName;}

    @FindBy(css = "span[title='Extend Due Date']")
    WebElement extendDueDate;
    public  WebElement getExtendDueDate(){return extendDueDate;}

    @FindBy(xpath = "(//span[contains(@class,'btn sty-green submit-assign')])[2]")
    WebElement update;
    public WebElement getUpdate(){return update;}

    @FindBy(partialLinkText = "All Activity")
    public WebElement allActivity_link;

    @FindBy(partialLinkText = "Learning Activity")
    public WebElement learningActivity_link;

    @FindBy(className = "ls-assignment-status-awaiting-submissions")
    public WebElement status; //class status

    @FindBy(css = "div[class='ls-resource-doctypes ls-default-resource']")
    public List<WebElement> lsIcon; //icon of learning activity assessment

    @FindBy(xpath = "//li[contains(@class,'ls-assignment-cart-item ellipsis')]/a")
    public List<WebElement> lesson; //lesson name

    @FindBy(xpath = "//div[@class='ls-right-section-status']/span[2]")
    public WebElement classStatus_assignmentTab; //class status in assignment tab

    @FindBy(xpath = "//*[@id='inst-existing-assignment-wrapper']/div[4]/div[2]/div[1]/div[2]/div[2]/ul/li/a")
    public List<WebElement> lessonName_particularClass;

    @FindBy(xpath = "//a[contains(@class,'sbToggle')]")
    public WebElement classSection_toggle;  //toggle of the class section

    @FindBy(xpath = "//div[@class='overview']/li[3]/a")
    public WebElement selectClassSection; //select other class section of same instructor

    @FindBy(xpath = "//*[@id='inst-existing-assignment-wrapper']/div[4]/div[2]/div[1]/div[2]/div[2]/ul/li/a")
    public List<WebElement> lessonName_mentorParticularClass;

    @FindBy(xpath = "//div[contains(@class,'ls-instructor-activity-cards ls-instructor-cards-first not-started')]/div")
    public List<WebElement> notStarted_box;

    @FindBy(className = "learning-activity-title")
    public List<WebElement>  studentLessonAssignment;//lesson/image/cart assignment in Assignments page

    @FindBy(className = "ls-assignment-status-in-red-grading-in-progress")
    public WebElement status_reviewInProgress;

    @FindBy(xpath = "//div[@class='ls-instructor-activity-cards ls-instructor-cards-third submitted']/div[1]")
    WebElement submittedBoxCount;
    public  WebElement getSubmittedBoxCount(){return submittedBoxCount;}

    @FindBy(id = "new-assignment-button")
    public WebElement newAssignment_Button;

    @FindBy(className = "ls-inst-db-assignment-close-dialog")
    public WebElement close_Icon;

    @FindBy(css = "div[class='ls-inst-dashboard-assignment-popup-button ls--create-custom-assignment-view']")
    public WebElement createCustomAssignmentButton;

    @FindBy(css = "div[class='ls-inst-dashboard-assignment-popup-button ls--question-banks-view']")
    public WebElement usePreCreatedAssignmentButton;

    @FindBy(css = "div[class='ls-inst-dashboard-assignment-popup-button ls--custom-file-assignment-view']")
    public WebElement createFileBasedAssignmentButton;

    @FindBy(className = "tab_title")
    public  List<WebElement> tab_Title;

    @FindBy(xpath = "//a[text()='img.png']")
    public WebElement resourceLink;

    @FindBy(xpath = "//div[@class='ls-assignment-name-block']/img")
    public WebElement imageIcon;

    @FindBy(className = "assign-more")
    public List<WebElement> updateAssignment_link;//update Assignment button on Current assignment page

    @FindBy(css = "a[class='btn btn--primary btn--large btn--submit']")
    public WebElement finish_button;

    @FindBy(xpath = "//span[@class='resource-title']/a")
    public WebElement uploadedResources;

    @FindBy(className = "display-question")
    WebElement lastQuestionInDropDown;
    public WebElement getLastQuestionInDropDown() {
        return lastQuestionInDropDown;
    }

    @FindBy(className = "question-label")
    WebElement questionLabelText;
    public WebElement getQuestionLabelText(){return questionLabelText;}

    @FindBy(id = "show-question-detials")
    public  WebElement questionCount_dropDown;

    @FindBy(css = "span[class='idb-gradebook-content-label-text idb-gradebook-username']")
    public List<WebElement> enterGrade_mouseOver; //mouse over on student name while entering grade

    @FindBy(css="span[class='ls-assignment-graded-quiz-icon assessment-block-icon']")
    public WebElement gradable_icon;

    @FindBy(className = "ls-ins-question-text")
    public List<WebElement> previewQuestion;

    @FindBy(className = "assignment-update-reassign")
    public WebElement reAssign_link;
    @FindBy(className = "try-it")
    public List<WebElement> tryIt_Link;

    @FindBy(xpath = "//a[@title='All Activity']/preceding-sibling::a")
    public WebElement allActivity_DropDown;

    @FindBy(xpath = "//a[@title='Question Assignment']")
    public WebElement questionAssignment;

    @FindBy(id = "cms-question-preview-header-logo")
    public WebElement wileyLogo;

    @FindBy(id = "cms-question-preview-header-course-name")
    public WebElement courseName;

    @FindBy(id = "cms-question-preview-header-ass-name")
    public WebElement assignmentNamePreviewPage;

    @FindBy(css = "i[class='s s--check-down-arrow']")
    public WebElement summaryDropDown;

    @FindBy(id = "cms-question-preview-footer-hint")
    public WebElement hint_Button;

    @FindBy(id = "cms-question-preview-footer-solution")
    public WebElement solution_Button;

    @FindBy(id = "question-reveview-submit")
    public WebElement checkAnswer_Button;

    @FindBy(css = "div[class='add-content-error show-content-issues-dialog preview-show-content-dialog']")
    public WebElement reportContentError_Link;

    @FindBy(className = "cms-question-preview-sidebar-title-sectn")
    public WebElement performanceTab;

    @FindBy(xpath = "//img[@src='../../../webresources/images/al/diag-test-sidebar-collapse-icon.png']")
    public WebElement performanceTabImage;

    @FindBy(id = "cms-question-preview-header-user-thumbnail")
    public WebElement userThumbNail;

    @FindBy(className = "footer-btn-text")
    public WebElement next_Button;

    @FindBy(id = "question-try-it-previous")
    public WebElement previous_Button;

    @FindBy(id = "question-try-it-next")
    public WebElement nextButton;

    @FindBy(xpath = "//*[@class='true-false-student-answer-select']")
    public List<WebElement> answerChoice;

    @FindBy(id = "footer-notification-text")
    public WebElement footerText;

    @FindBy(className = "choice-value")
    public List<WebElement> answerOption_MultipleChoice;

    @FindBy(className = "tryit-dropdown-listItem")
    public List<WebElement> dropDownQuestion;

    @FindBy(id = "question-try-it-finish")
    public WebElement finishButton; //finish button in question preview page

    @FindBy(className = "ls-help-assignment-question-mark")
    public WebElement helpIconOnSummaryDropDown;

    @FindBy(css = "div[class='true-false-student-answer-select true-false-student-answer-clicked']")
    public WebElement answerChoiceSelected;

    @FindBy(xpath = "//span[@data-localize='points']")
    public WebElement totalPoints;

    @FindBy(xpath = "//span[@data-localize='performance']")
    public WebElement performanceInQuestion;

    @FindBy(id = "cms-question-difficulty-img")
    public WebElement performanceGraph;

    @FindBy(className = "cms-difficulty-level-status-wrapper")
    public WebElement difficultyBar;

    @FindBy(className = "cms-difficulty-level-status")
    public WebElement difficultyLevelBar;

    @FindBy(className = "associated-content-details")
    public WebElement associateTLO;

    @FindBy(className = "ls-instructor-activity-cards ls-instructor-cards-first not-started")
    public WebElement notStartedCount;



    @FindBy(id = "cms-question-try-it-header-logo")
    public WebElement wiley_Logo;


    @FindBy(css = "div[class='cms-question-preview-header-course-name try-it-wiley']")
    public WebElement course_Name;

    @FindBy(css = "div[class='idb-gradebook-total-score']")
    public List <WebElement> totalScore;


    @FindBy(css = "div[class='idb-gradebook-total-questions-attempted-entry']")
    public List <WebElement> totalQuestionAttempted;


    @FindBy(css = "div[class='idb-gradebook-questions-status-column idb-gradebook-questions-correct-entry']")
    public List <WebElement> totalCorrectAnswer;
    @FindBy(css = "div[class='idb-gradebook-questions-status-column idb-gradebook-questions-partially-correct-entry']")
    public List <WebElement> totalPartiallyCorrectAnswer;
    @FindBy(css = "div[class='idb-gradebook-questions-status-column idb-gradebook-questions-incorrect-entry']")
    public List <WebElement> totalIncorrectAnswer;
    @FindBy(css = "div[class='idb-gradebook-questions-status-column idb-gradebook-questions-skipped-entry']")
    public List <WebElement> totalSkippedAnswer;


    @FindBy(css = "div[class='idb-gradebook-content-coloumn-complete idb-gradebook-content-perc-complete']")
    public List <WebElement> percentageCompleted;

    @FindBy(xpath = "//div[@class='ls-ins-your-question-type']/span[2]")
    public List<WebElement> question_Type;

    @FindBy(className = "ls-assignment-grading-title")
    public List<WebElement> assignmentReference;

    @FindBy(className = "ls-assignment-post-label")
    public WebElement noAssignmentExit;

    @FindBy(css = "div[class='ls-comment-entry  ls-feedback-comment']")
    public WebElement teacher_Feedback;

    @FindBy(id = "ls-toc-assignmnet-due-date")
    public WebElement OriginalDueDate;

    @FindBy(css = "div[class='ls-right-section-status ls-due-date-locale-formate']")
    public WebElement date_AssignmentsTab;



}
