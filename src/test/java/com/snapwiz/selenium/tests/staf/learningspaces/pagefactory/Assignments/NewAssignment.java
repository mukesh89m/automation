package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by priyanka on 1/7/2015.
 */
public class NewAssignment {
    @FindBy(xpath = "//div[contains(text(),'Use Pre-created Assignment')]")
    WebElement usePreCreatedAssignment;
    public WebElement getUsePreCreatedAssignment(){return usePreCreatedAssignment;}

    @FindBy(xpath = "//div[contains(text(),'Create Custom Assignment')]")
    WebElement CreatedCustomAssignment_link;

    @FindBy(xpath = "//div[contains(text(),'Create File based Assignment')]")
    WebElement CreatedFileBasedAssignment_link;


    @FindBy(xpath = "(//span[text()='New Assignment'])[2]")
    WebElement newAssignment;
    public WebElement getNewAssignment(){return newAssignment;}

    @FindBy(css = "span[title='Find Questions']")
    WebElement findQuestion;
    public WebElement getFindQuestion(){return findQuestion;}

    @FindBy(id = "ls-ins-assignment-name")
    public  WebElement assessmentNameTextBox;

    @FindBy(id = "ls-ins-edit-assignment")
    public  WebElement assessmentName_TextBox;

    @FindBy(css = "i[class='ls-ins-custom-image ls-ins-edit-assignment-icon']")
    public WebElement pencil_Icon;

    @FindBy(xpath = "//inline[@class='redactor_placeholder']")
    public WebElement addQuestionTextBox;

    @FindBy(id = "ls-ins-save-assigment-btn")
    public WebElement saveForLater_Button;

    @FindBy(id = "question-prompt-raw-content")
    public WebElement addQuestion_TextBox;

    @FindBy(css = "a[class='re-icon re-link']")
    public WebElement linkIconTextEditor;

    @FindBy(className = "redactor_dropdown_link")
    public WebElement insertLink;

    @FindBy(id = "redactor_link_url")
    public WebElement popUpUrl;

    @FindBy(id = "redactor_link_url_text")
    public WebElement textBox;

    @FindBy(id = "redactor_link_blank")
    public WebElement checkBox;

    @FindBy(id = "redactor_insert_link_btn")
    public WebElement insertButton;

    @FindBy(xpath = "//a[@href='https://www.google.co.in/']")
    public WebElement google;

    @FindBy(xpath = "(//span[@class='redactor-link-tooltip']/a)[1]")
    public WebElement googleLink;

    @FindBy(id = "uploadFile")
    public WebElement uploadFileButton;

    @FindBy(className = "ls-uploaded-file")
    public List<WebElement> fieUpload;

    @FindBy(css = "span[class='ls-fileicon-img ls-resource-doctypes ls-resource-image']")
    public List<WebElement> imageIcon;

    @FindBy(css = "span[class='ls-fileicon-img ls-resource-doctypes ls-resource-image']")
    public List<WebElement> imageIconList;

    @FindBy(className = "ls-delete-file-icon")
    public WebElement deleteIcon;

    @FindBy(className = "notification-text-span")
    public WebElement deleteNotificationMessage;

    @FindBy(className = "delete-file-from-file-assessment")
    public WebElement yesOnDeleteMessage;

    @FindBy(className = "ir-ls-assign-dialog-header")
    public WebElement popUP;


    @FindBy(className = "donot-delete-file-from-file-assessment")
    public WebElement noOnDeleteMessage;

    @FindBy(xpath = "//a[@title='Close']")
    public WebElement closeIcon;

    @FindBy(className = "ls-notification-text-span")
    public WebElement notification_message;

    @FindBy(id = "ls-assign-now-assigment-btn")
    public WebElement assignNowButton;

    @FindBy(css = "div[class='ir-ls-assign-dialog-assign-to-label ir-ls-assign-dialog-label']")
    public List<WebElement> assignField;

    @FindBy(id = "assign-cancel")
    public WebElement cancelPopUp;

    @FindBy(css = "span[class='btn sty-green submit-assign assign-custom-assignment']")
    public WebElement assignPopUp;

    @FindBy(css = "span[class='btn sty-green submit-assign']")
    public WebElement button_assign;

    @FindBy(className = "ir-ls-assign-dialog-gradable-label-check")
    public WebElement gradableCheckBox;

    @FindBy(className = "total-points-help-icon")
    public WebElement helpIconOFPoints;

    @FindBy(className = "assign-this-help-tooltip-wrapper")
    public  WebElement helpMessage;

    @FindBy(className = "tab_title")
    public List<WebElement> tabTittle;

    @FindBy(id = "ls-ins-assignment-desc")
    public WebElement descriptionField;

    @FindBy(id = "ls-ins-enter-assignment-desc")
    public WebElement descriptionTextArea;

    @FindBy(id = "ls-ins-your-question-delete-icon")
    public WebElement delete_Icon;

    @FindBy(id = "ls-ins-your-question-label")
    public WebElement questionLabel;

    @FindBy(id = "showExpendQuestionIcon")
    public List<WebElement> expandIcon;

    @FindBy(className = "question-grading-data-checked")
    public WebElement manualGradingCheckBox;

    @FindBy(xpath = "//div[@id='ls-ins-your-question-scoring-data']/input")
    public List<WebElement> pointCheckBox;

    @FindBy(xpath = "//div[@class='ls-ins-your-question-type']/span[2]")
    public WebElement questionType;

    @FindBy(xpath = "(//span[@class='custom-expand-all-icon'])[2]")
    public WebElement expandAllIcon;

    @FindBy(className = "custom-collapse-all-icon")
    public WebElement collapseAllIcon;

    @FindBy(className = "associated-content-details-label")
    public WebElement difficultyLevel;

    @FindBy(xpath = "//span[contains(@title,'Selected Questions')]")
    public WebElement selectedQuestion;

    @FindBy(xpath = "//span[contains(@class,'ls-ins-search-text')]")
    public WebElement searchButton;

    @FindBy(className = "ls-ins-browse-text")
    public WebElement browseButton;

    @FindBy(className = "ls-ins-search-no-result-found")
    public WebElement noQuestionFound;

    @FindBy(xpath = "//input[contains(@class,'ls-ins-search-question-content')]")
    public WebElement searchTextArea;

    @FindBy(className = "view-browse-options-drop-down")
    public WebElement browseDropDown;

    @FindBy(className = "checked")
    public WebElement checkCheckedBox;

    @FindBy(className = "ls-search-question-text")
    public List<WebElement> searchText;

    @FindBy(xpath= "//div[@class='ls-your-questions-outer-wrapper ui-sortable']/div")
    public List<WebElement> questionBar;

    @FindBy(id= "ls-ins-your-question-data")
    public List<WebElement> secondQuestionBar;

    @FindBy(id = "close-new-assignment")
    public WebElement close_Icon;

    @FindBy(xpath = "//span[contains(@class,'close-new-assignment-tab')]")
    public WebElement yesOnPopUp;

    @FindBy(className = "cms-content-question-review")
    public List<WebElement> question_Card;

    @FindBy(className = "control-label")
    public List<WebElement> questionPreview;

    @FindBy(css = "label[class='question-grading-data-checked checked']")
    public WebElement manualGradingCheckedCheckBox;

    @FindBy(css = "div.ls-inst-dashboard-assignment-popup-button.ls--create-custom-assignment-view")
    public WebElement createCustomAssignment;

    @FindBy(className = "ir-ls-assign-dialog-header-wrapper")
    public WebElement assignThis_popUp; //assignthis pop in custom assignmnet

    @FindBy(xpath = "//div[@class='ir-ls-assign-dialog-content-wrapper']/div[3]/div[1]")
    public WebElement assignmentReference_label;

    @FindBy(xpath = "(//div[@class='ir-ls-assign-dialog-assign-to-label ir-ls-assign-dialog-label'])[3]")
    public WebElement assignmentReference_label3; //static


    @FindBy(xpath = "(//div[@class='ir-ls-assign-dialog-assign-to-label ir-ls-assign-dialog-label'])[4]")
    public WebElement assignmentReference_label4; //adaptive

    @FindBy(xpath = "(//div[@class='ir-ls-assign-dialog-assign-to-label ir-ls-assign-dialog-label'])[5]")
    public WebElement assignmentReference_label5; //update custom assignment

    @FindBy(xpath = "//div[@class='ir-ls-assign-dialog-content-wrapper']/div[5]/div[1]")
    public WebElement assignmentReference_label1;

    @FindBy(xpath = "//div[contains(text(),'Assignment Reference:')]")
    public WebElement assignmentReference_label2;//file based assignment

    @FindBy(css = "div[class='ir-ls-grading-policy-filter-drop-down-wrapper ir-ls-assign-dialog-field scrollbar-wrapper']")
    public WebElement assignmentReference_dropDown;

    @FindBy(xpath = "//div[@class='ir-ls-assign-dialog-content-wrapper']/div[5]//span")
    public WebElement assignmentReference_description;

    @FindBy(xpath = "//div[@class='ir-ls-assign-dialog-content-wrapper']/div[6]//span")
    public WebElement assignmentReference_description1;

    @FindBy(xpath = "//div[@class='ir-ls-assign-dialog-content-wrapper']/div[7]//span")
    public WebElement assignmentReference_description2; // file based assignment

    @FindBy(xpath = "(//div[@class='ir-ls-assign-dialog-assign-to-label ir-ls-assign-dialog-label'])[4]")
    public WebElement assignmentReference_description3; // file based assignment


    @FindBy(xpath = "(//div[@class='ir-ls-assign-dialog-assign-to-label ir-ls-assign-dialog-label'])[5]")
    public WebElement assignmentReference_description4; // file adaptive

    @FindBy(xpath = "(//div[@class='ir-ls-assign-dialog-assign-to-label ir-ls-assign-dialog-label'])[6]")
    public WebElement assignmentReference_description5; // update custom assignment


    @FindBy(xpath = "//a[@title='Select an Assignment Policy']")
    public WebElement assignmentPolicy_dropdown;


    @FindBy(xpath = "//a[@title='ByDefault Policy Name']")
    public WebElement assignmentPolicydropdown;


    @FindBy(xpath = "//a[@title='Create a new Assignment Policy']")
    public WebElement createAssignmentPolicy;

    @FindBy(css = "div.ls-inst-dashboard-assignment-popup-button.ls--custom-file-assignment-view")
    public WebElement createFileBasedAssignment;


    @FindBy(css = "span.assign-different-dates")
    public WebElement assignDifferentDates;

    @FindBy(css = "span.assign-different-dates")
    public List<WebElement> assignDifferentDatesList;

    @FindBy(css = "div.assign-all-lessons")
    public WebElement assignAllLessons;

    @FindBy(css ="div.share-with-wrap")
    public WebElement shareWith;

    @FindBy(xpath = "//a[@class='closebutton']")
    public WebElement removeClassSection;

    @FindBy(className="maininput")
    public List<WebElement> classSection;

    @FindBy(xpath ="//ul[@id='share-with_feed']/li/em")
    public WebElement shareWithClass;

    @FindBy(css = "input.add-row-button")
    public WebElement addRow;

    @FindBy(css = "span.ir-ls-assign-classwise-remove")
    public List<WebElement> removeClass;

    @FindBy(css="span.additional-desc-help-icon.dates-label-help-icon")
    public WebElement helpIconAssignDifferentDates;

    @FindBy(css ="div.share-with-wrap")
    public List<WebElement> assignTo;

    @FindBy (css="input.input-filed.accessible-date-classwise.hasDatepicker")
    public List<WebElement> datePickerAssignDifferentDates;

    @FindBy(css = "input#accessible-time")
    public List<WebElement> timePickerAssignDifferentDates;


    @FindBy(css="input.due-date-classwise")
    public List<WebElement> dueDateClassWise;
    @FindBy(css="input#due-time")
    public List<WebElement> dueTimeClassWise;


    @FindBy(id="accessible-date")
    public WebElement datePicker;

    @FindBy(id="due-date")
    public WebElement dueDate;
    @FindBy(id="due-time")
    public WebElement dueTime;

    @FindBy(className = "ls-ins-browse-icon ")
    public WebElement browse_link;

    @FindBy(className = "ls-ins-advanced-filters")
    public WebElement advancedFilter_link;

    @FindBy(xpath = "//div[@id='ls-ins-question-advanced-filters-list']/div")
    public List<WebElement> advancedFilter_list;

    @FindBy(xpath = "//div[@id='ls-ins-question-advanced-filters-list']//label")
    public List<WebElement> advancedFilter_checkbox;

    @FindBy(xpath = "//label[@class='ui-dropdownchecklist-text']")
    public List<WebElement> allQuestionType_Filter;

    @FindBy(xpath = "//div[@class='ui-dropdownchecklist-item ui-state-default']/input")
    public List<WebElement> allQuestionType_checkbox;

    @FindBy(xpath = "//span[@class='ui-dropdownchecklist-done']")
    public WebElement done_button;

    @FindBy(xpath = "//span[@class='ui-dropdownchecklist-done deactivate']")
    public WebElement done_deactivate;

    @FindBy(css = ".ui-dropdownchecklist-text")
    public WebElement allQuestionType_dropdown;

    @FindBy(id="ls-ins-view-browse-options-dropdown")
    public WebElement browse_dropdown;

    @FindBy(className = "ls-ins-browse-go")
    public WebElement go_button;

    @FindBy(css = "i[class='ls-ins-questionfilters-tooltip']")
    public WebElement multipleQuestions_elipses;

    @FindBy(xpath = "//div[@class='help-data-container']/br")
    public List<WebElement> question_Container;

    @FindBy(xpath = "//div[@class='ls-ins-ans-options']/div[1]/span")
    public  List<WebElement> chapterNumber;

    @FindBy(css = "input[class='ls-ins-search-question-content ls-ins-search-question-content-selected']")
    public WebElement searchQuestion_textbox; //custom search

    @FindBy(className = "ls-ins-view-filters")
    public WebElement filter;

    @FindBy(xpath = "//div[@class='browse-left-filters']//li/a")
    public List<WebElement> chapterName; //all chapter name in all chapter dropdown from custom assignment

    @FindBy(xpath = "//a[@title='Ch 2: The Chemical Foundation of Life']")
    public List<WebElement> secondChapterName;

    @FindBy(className = "ls-instructor-only-assignment-check")
    public WebElement checkboxForInstructorAssignment;

    @FindBy(xpath = "//div[@class='ls-search-question-text']/inline")
    public List<WebElement> search_Text;

    @FindBy(xpath= "//div[@id='ls-ins-your-question-data']/inline")
    public List<WebElement> answerBar;

    @FindBy(xpath = "//input[@type='checkbox']//following-sibling::label")
    public List<WebElement> check_Box;

    @FindBy(css = "span[title = 'New Assignment']")
    public WebElement tab_newAssignment;

    @FindBy(className = "question-available-on-etextbook")
    public List<WebElement> questionAvailableOntextbook;

    @FindBy(className = "ls-ins-browse-go")
    public List<WebElement> goButton;

    @FindBy(css = "div[class='tab active']")
    public WebElement activeTab;

    @FindBy(className= "ls-ins-search-icon")
    public WebElement searchIcon;

    @FindBy(className= "search-browse-separator")
    public WebElement searchBrowseSeparator;

    @FindBy(className= "custom-expand-all-text")
    public WebElement expandAllQuestionLink;

    @FindBy(xpath = "//span[@class='custom-expand-all-icon']")
    public List<WebElement> expandAll_Icon;

    @FindBy(id= "ir-ls-assign-dialog")
    public List<WebElement> assignPopUpCustomPage;

    @FindBy(className= "ls-ins-view-browse-options-position")
    public WebElement browsePopUp;

    @FindBy(linkText = "All Chapters")
    public WebElement allChapters;

    @FindBy(linkText = "All Objectives")
    public WebElement allObjectives;

    @FindBy(linkText = "All Sections")
    public WebElement allSections;

    @FindBy(linkText = "All Difficulty Levels")
    public WebElement allDifficultyLevels;

    @FindBy(id = "ls-ins-edit-assignment")
    public  List<WebElement> assessmentNameTextField;

    @FindBy(partialLinkText = "Ch 1:")
    public WebElement subChapter;

    @FindBy(partialLinkText = "1.1")
    public WebElement subSection;

    @FindBy(className = "ls-ins-search-filter-count")
    public WebElement filterCount;

    @FindBy(linkText = "Easy")
    public WebElement difficulty_Level;

    @FindBy(xpath = "//span[@title='All Question types']")
    public WebElement allQuestionTypes;

    @FindBy(className = "expendQuestion")
    public WebElement expandQuestion;

    @FindBy(className = "ls-ins-question-wrapper")
    public List<WebElement> questionWrapperContent;

    @FindBy(id = "cms-question-preview-question-content")
    public WebElement cmsQuestionPreview;

    @FindBy(className = "manual-grading-help-icon")
    public WebElement manualGradingHelpIcon;

    @FindBy(className = "manual-grading-help-tooltip-wrapper")
    public WebElement manualGradingHelpMessage;

    @FindBy(id = "ls-ins-your-question-scoring-data")
    public List<WebElement> scoreBox;

    @FindBy(xpath = "//span[@class='expendQuestion']")
    public List<WebElement> expand_Question;

    @FindBy(xpath = "//input[@type='checkbox']")
    public List<WebElement> checkBoxInput;

    public By browse_PopUp = By.className("ls-ins-view-browse-options-position");

    @FindBy(xpath = "//div[@class='browse-left-filters']/div")
    public List<WebElement> leftFilter;

    @FindBy(xpath = "//div[@class='browse-right-filters']/div")
    public List<WebElement> rightFilter;

    @FindBy(xpath = "//div[@class='ls-ins-browse-filter-checkbox']//span[2]")
    public WebElement showQuestionFromEtextbook;

     @FindBy(css = "div[class='ls-ins-search-bar ui-sortable-handle']")
    public List<WebElement> questionWrapperContentSelectTab;

    @FindBy(className = "mpq-grading-data-disable")
    public WebElement manualGradeDisableCheckBox;

    @FindBy(className = "ls-inst-dashboard-assignment-title")
    public WebElement assignment_title;

    @FindBy(className = "ls-inst-dashboard-assignment-header")
    public WebElement assignment_header;

    @FindBy(className = "ls-inst-dashboard-assignment-mode")
    public List<WebElement> assignment_mode;

    @FindBy(xpath = "//div[contains(@class,'discussion-assignment-view')]")
    public WebElement discussionAssignment;

    @FindBy(id="assignment-widget-content")
    public WebElement questionContent;

    @FindBy(id = "assignment-widget-content")
    public  WebElement assessmentDescriptionTextBox;

    @FindBy(id = "my-resource-search-textarea")
    public  WebElement searchAssignableItemsTextField;

    @FindBy(xpath ="//div[@id='ls-ins-all-chapters']//a[1]" )
    public WebElement allChapter_link;

    @FindBy(xpath ="//div[@id='ls-ins-all-chapters']//li/a" )
    public List<WebElement> chapterName_list;

    @FindBy(css = "div[class='ls-resource-doctypes ls-resource-image']")
    public WebElement dwImageIcon;

    @FindBy(className = "ls-dialog-txt")
    public WebElement discussionText;

}
