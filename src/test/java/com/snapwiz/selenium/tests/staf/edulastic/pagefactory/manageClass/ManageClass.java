package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by shashank on 27-02-2015.
 */
public class ManageClass {

 @FindBy(css= "div[class='as-manage-blue-btn as-add-newClass-btn']")// New class
         WebElement newClass;
 public WebElement getNewClass() {
  return newClass;
 }

 @FindBy(css= "div.as-manageClass-headerDropArr")// Expand class
         WebElement expandClass;
 public WebElement getExpandClass() {
  return expandClass;
 }

 @FindBy(css= "span.as-manageClass-codeValue")// get class code
         List<WebElement> classCode;
 public List<WebElement> getclassCode() {
  return classCode;
 }

 @FindBy(css="span.as-manage-class-visit")
 WebElement visitClass;
 public WebElement getVisitClass(){
  return visitClass;
 }

 @FindBy(css="span.as-manage-class-visit")
 public List<WebElement> listVisitClass;

 @FindBy(css="span.as-manageClass-gradeValue")
 WebElement grade;
 public WebElement getGrade(){
  return grade;
 }

 @FindBy(xpath="//span[contains(@class,'as-manageClass-subjectArea')]")
 WebElement subjectArea;
 public WebElement getSubjectArea(){
  return subjectArea;
 }

 @FindBy(xpath="//table[@class='table dataTable table-bordered student-table']/tbody/tr/td[2]")
 List<WebElement> studentNames;
 public List<WebElement> getStudentNames(){
  return studentNames;
 }

 @FindBy(xpath="//table[@class='table dataTable table-bordered student-table']/tbody/tr/td[3]")
 List<WebElement> studentUserNames;
 public List<WebElement> getStudentUserNames(){
  return studentUserNames;
 }
 @FindBy(css="div.as-manage-class-settings")
 WebElement settings;
 public WebElement getSettings(){
  return settings;
 }

 @FindBy(className="edit-class")
 WebElement editClass;
 public WebElement getEditClass(){
  return editClass;
 }

 @FindBy(css="input[class='as-blue-btn as-add-save-btn']")
 WebElement saveTheClass;
 public WebElement getSaveTheClass(){
  return saveTheClass;
 }

 @FindBy(css="span.as-remove-student")
 WebElement deleteStudent;
 public WebElement getDeleteStudent(){
  return deleteStudent;
 }

 @FindBy(css="span[class='delete-draft-assessment delete-class-student']")
 WebElement yesDelete;
 public WebElement getYesDelete(){
  return yesDelete;
 }

 @FindBy(css="div[class='as-modal-cancel-btn cancel-delete-assignment']")
 WebElement noCancel;
 public WebElement getNoCancel(){
  return noCancel;
 }

 @FindBy(css="div.notification-body > div")
 WebElement notificationMessage;
 public WebElement getNotificationMessage(){
  return notificationMessage;
 }

 @FindBy(id="delete-confm-txt")
 WebElement deleteConfirmText;
 public WebElement getDeleteConfirmText(){
  return deleteConfirmText;
 }

 @FindBy(xpath="//a[@class='as-add-back-btn pull-right m-r-sm btn btn-transperent']")
 WebElement cancelButton;
 public WebElement getCancelButton(){
  return cancelButton;
 }

 @FindBy(css = "textarea[class='as-manage-class-description class-description-input']")
 WebElement descriptionTexarea;
 public WebElement descriptionTexareaWebElement(){
  return descriptionTexarea;
 }

 @FindBy(css = "input[class='as-manage-class-name class-name-input']")
 WebElement className;
 public WebElement classNameWebElement(){
  return className;
 }

 @FindBy(css="div.notification-msg")
 WebElement errorMessageAtLogin;
 public WebElement getErrorMessageAtLogin(){
  return errorMessageAtLogin;
 }

 @FindBy(css="span.as-share")
 WebElement share;//Embed inside more
 public WebElement getShare(){
  return share;
 }

 @FindBy(css="div.as-share-link-block")
 WebElement shareUrl;
 public WebElement getShareUrl(){
  return shareUrl;
 }

 @FindBy(xpath="//div[@class='as-status-label']/span")
 List<WebElement> assignmentStatus;
 public List<WebElement> getAssignmentStatus(){
  return assignmentStatus;
 }

 @FindBy(css="span.as-gradebook-assignment-username")
 List<WebElement> studentListViewResponse;
 public List<WebElement> getStudentListViewResponse(){
  return studentListViewResponse;
 }

 @FindBy(css="span.as-response")
 WebElement viewResponse;
 public WebElement getViewResponse(){
  return viewResponse;
 }

 @FindBy(css="input[class='lsm-createAssignment-owner-checked owner']")
 WebElement ownerMe;
 public WebElement getOwnerMe (){
  return ownerMe;
 }

 @FindBy(css="input[class='lsm-createAssignment-owner-checked district']")
 WebElement districtUsers;
 public WebElement getDistrictUsers(){
  return districtUsers;
 }

 @FindBy(css="span[class='lsm-select-btn lsm-select-tlo-questions-btn']")
 WebElement selectButton;
 public WebElement selectButtonWebElement(){
  return selectButton;
 }

 @FindBy(css="input[class='lsm-useExistingAssignment-owner-checked owner']")
 WebElement ownerMeUsingExisting;
 public WebElement getOwnerMeUsingExisting (){
  return ownerMeUsingExisting;
 }

 @FindBy(css="input[class='lsm-useExistingAssignment-owner-checked district']")
 WebElement ownerDistrictUsingExisting;
 public WebElement getOwnerDistrictUsingExisting (){
  return ownerDistrictUsingExisting;
 }

 @FindBy(css = "span.lsm-auto-assign-btn")
 WebElement autoSelect;
 public WebElement autoSelectWebElement (){
  return autoSelect;
 }

 @FindBy(css = "span.as-questionDetails-clickArrow")
 WebElement nextArrow;
 public WebElement nextArrowWebElement(){return nextArrow;}

 @FindBy(css = "span.as-question-preview-back-button")
 WebElement backArrow;
 public WebElement backArrowWebElement(){return backArrow;}

 @FindBy(xpath = "//a[@class='as-question-preview-duplicate-button']")
 List<WebElement> duplicateLink;
 public List<WebElement> duplicateLinkWebElement(){return duplicateLink;}


 @FindBy(className="as-customize-button")
 WebElement customizeButton;
 public WebElement customizeButtonWebElement(){return customizeButton;}

 @FindBy(id = "assessments-back-link")
 WebElement backLink;
 public WebElement getBackLink(){return backLink;}

 @FindBy(xpath = "//div[@class='as-question-tlos-wrapper']/div")
 WebElement tloText;
 public WebElement getTloText(){return  tloText;}

 @FindBy(xpath = "//div[@id='next-question-performance-view']")
 WebElement nextArrowPerformanceView;
 public WebElement getNextArrowPerformanceView(){return  nextArrowPerformanceView;}

 @FindBy(className = "as-manage-class-content")
 List<WebElement> classCountStudent;
 public List<WebElement> getClassCountStudent(){return  classCountStudent;}

 @FindBy(css= "span[class='as-manage-class-subject as-manage-class-title ellipsis']")
 List<WebElement> classNameStudent;
 public List<WebElement> getClassNameStudent(){return  classNameStudent;}

 @FindBy(css = "span.getStarted")
 WebElement getStarted;
 public WebElement started(){return getStarted;}//getStated button

 @FindBy(xpath="(//input[starts-with(@class,'as-manage-class-name class-name-input')])[1]")
 WebElement enterClassCode;
 public WebElement enterClassCodeWebElement(){return enterClassCode;}//Input field Enter Class code at student side

 @FindBy(css="input[class='as-blue-btn as-join-class-btn']")
 WebElement joinClass;
 public WebElement buttonJoinClass(){return joinClass;}//join class button at student level

 @FindBy(css = "div[class='as-errorMsg class-name-message']")
 WebElement classCodeErrorMessage;
 public WebElement getClassCodeErrorMessage(){return classCodeErrorMessage;}//join class button at student level

 @FindBy(css="input#save-btn")
 WebElement saveButton;
 public WebElement getSaveButton(){return saveButton;}//save button

 @FindBy(css="span.select2-selection.select2-selection--single")
 WebElement createdBy;
 public WebElement getCreatedBy(){return createdBy;}//Assessment created by

 @FindBy(xpath="//div[starts-with(@class,'as-manageClass-codeValue as-manageClass-url ellipsis share-link-label')]")
 WebElement url;
 public WebElement getUrl(){return url;}

 @FindBy(xpath = "//div[@class='dropdown-toggle']")
 WebElement more;
 public WebElement getMore(){return more;}

 @FindBy(css = "span.select2-selection__rendered")
 WebElement changeClassDropDown;
 public WebElement getChangeClassDropDown(){return changeClassDropDown;}

 @FindBy(css = "input.select2-search__field")
 WebElement changeClassSearchField;
 public WebElement getChangeClassSearchField(){return changeClassSearchField;}

 @FindBy(xpath = "//*[@id='select2-as-header-classes-selectbox-results']/li")
 List<WebElement> classNameList;
 public List<WebElement> getClassNameList(){return classNameList;}


 @FindBy(partialLinkText="Show me my assessment")
 public  WebElement showMeMyAssessment;

 @FindBy(className = "notification-banner")
 public WebElement notification_msg;//Notification message after adding student to new class

 @FindBy(css = ".check-student +ins")
 public List<WebElement> checkBoxSelectStudent;


 @FindBy(css = "span[class='ed-icon icon-delete']")
 public WebElement deleteStudents;

 @FindBy(css = ".iCheck-helper >span")
 public List<WebElement> tab_activeAndArchived;//Active and archived tab

 @FindBy(css = "#breadcrumb > li")
 public WebElement label_manageClass;//Manage class label

 @FindBy(xpath = "//table[@class='table dataTable table-bordered student-table']/tbody/tr/td[3]")
 public List<WebElement> students_userNames; //Student's user names

 @FindBy(xpath = "//span[contains(@class,'ed-icon icon-pencil edit-classname')]")
 public WebElement editIcon_class;//Class edit icon

 @FindBy(xpath = "//span[contains(@class,'ed-icon icon-pencil edit-end-date')]")
 public WebElement editIcon_endDate; //End date edit icon

 @FindBy(xpath = "//span[contains(@class,'ed-icon icon-pencil edit-description')]")
 public WebElement editIcon_description;//Description edit icon

 @FindBy(xpath = "//span[starts-with(@class,'ui-icon ui-icon-circle-triangle')]")
 public List<WebElement> calender_arrows;//Arrows on calender

 @FindBy(xpath = "//a[contains(@class,'ui-state-default')]")
 public List<WebElement> dates_calender;//Calender dates

 @FindBy(css = "span[class='as-manageClass-endDate ellipsis']")
 public WebElement endDate;//End date

 @FindBy(css = ".as-manageClass-descriptionContent")
 public WebElement descriptionContent;//Description content

 @FindBy(css = "a[class^='btn change-password']")
 public WebElement changePassword;//Change password

 @FindBy(xpath = "//input[starts-with(@id,'reset-password')]")
 public List<WebElement> textBox_resetPassword;//Reset password text box

 @FindBy(css = "span[class='delete-draft-assessment reset-student-password']")
 public WebElement button_reset;//Reset button


 @FindBy(css = ".btn.google-sync.skip-deactive")
 public List<WebElement> syncWithGoogleClass;//Sync with google classroom

 @FindBy(xpath = "//a[contains(@class,'btn sync-with-canvas')]")
 public WebElement syncWithCanvas;//Sync with canvas

 @FindBy(css = ".btn.roster-upload.skip-deactive.hide-after-canvas-sync")
 public WebElement uploadClassRoster;//Upload class roster

 @FindBy(id = "google-code-input")
 public WebElement textBox_googleClassCode;//Google class code input text box

 @FindBy(css = ".as-modal-yes-btn.pull-right.text-center.sync-students")
 public WebElement sync;//sync button

 @FindBy(className = "as-manageClass-studentCount ")
 public WebElement currentStudentCount;

 @FindBy(css = "a[class='btn btn-blue btn-rounded']")
 public WebElement doneAfterSync;//Done button after sync

 @FindBy(css = "div[class='col-xs-12 row label-row']")
 public List<WebElement> classDetails;//Class details

 @FindBy(id = "change-class-room")
 public WebElement changeClassRoom;//Change class room

 @FindBy(xpath = "//table[@class='table table-bordered']/descendant::tbody/tr")
 public List<WebElement> studentsToBeSynced;//Number of students to be synced

 @FindBy(xpath = "(//div[@class='as-modal-yes-btn pull-right text-center sync-students'])[2]")
 public WebElement yesWhileSync;

 @FindBy(css = "div[class='modal-content gray-bg google-class-room']")
 public WebElement syncPopUp;//Sync pop up

 @FindBy(id = "roster-upload-download-pop-up-close")
 public WebElement cancelSync;// Cancel on sync pop up

 @FindBy(css = ".table.dataTable.table-bordered.student-table>tbody>tr")
 public List<WebElement> studentPresentInTable;

 @FindBy(css = ".select2-selection__arrow")
 public List<WebElement> courseDropDown;

 @FindBy(xpath = "//ul[@id='select2-courseListCombo-results']/li")
 public List<WebElement> selectCourse;

 @FindBy(xpath = "//ul[@id='select2-sectionListCombo-results']/li")
 public List<WebElement> selectSection;

 @FindBy(xpath = "//div[text()='Sync']")
 public WebElement syncButton;

 @FindBy(xpath = "//div[text()='Close']")
 public WebElement closeButton;

 @FindBy(xpath = "//a[text()='Change Canvas Course']")
 public WebElement changeCanvasCourse;

 @FindBy(css = "span.select2-selection__rendered")
 public List<WebElement> changeCourseDropDown;

 @FindBy(css = "a[class*='cancel-google-classroom']")
 public WebElement cancelSyncPopUp;

 @FindBy(xpath = "//div[text()='Yes']")
 public  WebElement yesOnSyncPopUp;

 @FindBy(css = ".modal-dialog.manageclass-content")
 public WebElement selectCanvasCoursePopUp;

 @FindBy(id = "canvas-popup-success-message")
 public WebElement syncSuccessMessage;

 @FindBy(css = "span[class='ed-icon icon-arrow-up']")
 public WebElement uploadClassRoaster;

 @FindBy(css = "a[class='btn add-user skip-deactive show']")
 public WebElement addStudent;

 @FindBy(css = "a[class^='btn delete-students']")
 public WebElement deleteSelectedStudent;

 @FindBy(css = "input#delete-confm-txt")
 public WebElement deleteTextBox;

 @FindBy(css = "span[class='delete-draft-assessment delete-class-student']")
 public WebElement yesButtonOnDeletePopUp;

 @FindBy(xpath = "//tbody//td")
 public List<WebElement> studentTable;

 @FindBy(css = ".as-manage-class-content")
 public List<WebElement> manageClassContent;

 @FindBy(css = ".as-manage-class-subject.as-manage-class-title.ellipsis")
 public List<WebElement> classTitle;


 @FindBy(id = "user-name-email")
 public WebElement userNameEmail;

 @FindBy(id = "add-user-name")
 public WebElement userName;

 @FindBy(id = "add-user-password")
 public WebElement userPassword;

 @FindBy(id = "add-user-retype-password")
 public WebElement retypePassword;

 @FindBy(id = "submit-add-user-to-class")
 public WebElement yesCreate;

 @FindBy(css = ".btn.add-user.skip-deactive")
 public WebElement add_Student;

 @FindBy(xpath = "//div[text()='Join Class']")
 public WebElement join_Class;

 @FindBy(xpath = "//div[contains(@class,'as-new-class-content')]//input[@type='text']")
 public WebElement classCodeTextBox;

 @FindBy(xpath = "//div[contains(@class,'as-new-class-content')]//input[@type='submit']")
 public WebElement join_ClassButton;

 @FindBy(css = "div[class='as-errorMsg class-name-message as-errorMsg-bottom']")
 public WebElement alreadyEnrolledErrorMessage;

 @FindBy(css = "i[class='fa fa-ellipsis-v']")
 public List<WebElement> moreLink;

 @FindBy(css = "span[class='fa fa-plus m-r-sm']")
 public List<WebElement> addStudentLink;

}
