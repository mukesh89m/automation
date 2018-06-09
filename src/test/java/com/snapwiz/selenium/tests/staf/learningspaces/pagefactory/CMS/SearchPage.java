package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Snapwiz on 13/10/15.
 */
public class SearchPage{
    @FindBy(id = "content-search-icon")
    public WebElement icon_search;// Search Icon

    @FindBy(linkText = "Bulk Operations")
    public WebElement link_bulkOperations;

    @FindBy(linkText = "Copy Assessments")
    public WebElement link_copyAssessments;

    @FindBy(linkText = "Move Assessments")
    public WebElement link_moveAssessments;

    @FindBy(xpath = "//label[@for='use-lo-mapping']//i")
    public WebElement label_useLOMapping;

    @FindBy(id = "use-lo-mapping")
    public WebElement chechBox_useLOMapping;

    @FindBy(linkText = "Copy Assessments")
    public WebElement link_Biology;

    @FindBy(id = "tab-search")
    public WebElement tab_search;

    @FindBy(id = "tab-browse")
    public WebElement tab_browse;


    @FindBy(xpath = "//div[@class='cms-assessments-dialog-heading']/span")
    public WebElement label_bulkoperationCopy;

    @FindBy(xpath = "//div[@class='cms-bulk-operations-dialog-wrapper']//i")
    public WebElement icon_closeBulkOPeration;


    @FindBy(xpath = "//div[@class='cms-lo-mapping-dialog-wrapper']//i")
    public WebElement icon_close;

    @FindBy(partialLinkText = "Manage Mapping")
    public WebElement link_manageMapping;


    @FindBy(partialLinkText = "(Manage Mapping)")
    public WebElement link_manageMapping1;

    @FindBy(xpath = "//div[@class='cms-lo-mapping-dialog-wrapper']//span")
    public WebElement label_LoMappingTable;

    @FindBy(xpath = ".//*[@id='child-view']/div/div[1]/select")
    public WebElement tloOption1;

    @FindBy(xpath = ".//*[@id='child-view']/div/div[2]/select")
    public WebElement tloOption2;



    @FindBy(className = "lo-mapping-course-title")
    public WebElement label_sourceCourse;

    @FindBy(xpath = "(//div[@class='lo-mapping-course-title'])[2]")
    public WebElement label_destinationCourse;

    @FindBy(xpath = "(//div[@class='cms-lo-mapping-dialog-wrapper']//select)[3]/option")
    public WebElement label_SelectAnLearningObjective1;

    @FindBy(xpath = "(//div[@class='cms-lo-mapping-dialog-wrapper']//select)[4]/option")
    public WebElement label_SelectAnLearningObjective2;

    @FindBy(xpath = "(//div[@class='cms-lo-mapping-dialog-wrapper']//select)[5]/option")
    public WebElement label_SelectAnLearningObjective3;

    @FindBy(xpath = "(//div[@class='cms-lo-mapping-dialog-wrapper']//select)[6]/option")
    public WebElement label_SelectAnLearningObjective4;



    @FindBy(id = "lo-mapping-done")
    public WebElement button_done;

    @FindBy(linkText = "Cancel")
    public WebElement button_cancel;

    @FindBy(linkText = "Clear all")
    public WebElement link_clearAll;

    @FindBy(linkText = "+ Add row")
    public WebElement link_addRow;


    @FindBy(linkText = "- Remove")
    public WebElement link_remove;



    @FindBy(css = "select[data-type = 'source']")
    public WebElement comboBox_selectAnLearningObjective1;

    @FindBy(css = "select[data-type = 'target']")
    public WebElement comboBox_selectAnLearningObjective2;



    /*@FindBy(xpath = "(//select[@class='select-box-mapping'])[1]")
    public WebElement comboBox_selectAnLearningObjective1;

    @FindBy(xpath = "(//select[@class='select-box-mapping'])[2]")
    public WebElement comboBox_selectAnLearningObjective2;*/



    @FindBy(className = "confirmation-message-block")
    public WebElement successMessage;

    @FindBy(id = "cms-notification-message-body-title")
    public WebElement successmessage;

    @FindBy(id = "lo-mapping-clear-all")
    public WebElement clearAllMessage;

    @FindBy(linkText = "No")
    public WebElement link_no;

    @FindBy(linkText = "Yes")
    public WebElement link_yes;

    @FindBy(id = "content-search-field")
    public WebElement searchField;

    @FindBy(id = "search-question-contents-icon")
    public WebElement button_go;

    @FindBy(xpath = "//div[@id = 'question-check-box-div']//label")
    public WebElement checkBox;

    @FindBy(xpath = "//div[@id = 'content-search-copy-btn']//img")
    public WebElement button_copy;

    @FindBy(xpath = "//div[@id = 'content-search-move-btn']//img")
    public WebElement button_move;

    @FindBy(className = "content-search-select-title")
    public WebElement label_copySelectedQuestiosTo;

    @FindBy(linkText = "Select a course")
    public WebElement link_selectACourse;

    @FindBy(partialLinkText = "Geography: Realms, Regions, and Concepts")
    public WebElement link_geography;

    @FindBy(xpath = "(.//*[@id='contentSearchSelectChapterSlider']//span)[2]")
    public WebElement label_useLoMapping;

    @FindBy(id = "lo-mapping-check-box")
    public WebElement chechBox_useLOMapping2;

    @FindBy(id = "search-filter-link")
    public WebElement searchFiltersLink;


    @FindBy(xpath = "//a[@title='All Chapters (Includes Course Level)']")
    public WebElement allChapters_DropDown;

    @FindBy(xpath = "//a[@title='All Difficulty Level']")
    public WebElement difficultyLevel_DropDown;

    @FindBy(xpath = "//a[@title='All Status']")
    public WebElement allStatus_DropDown;

    @FindBy(xpath = "//a[@title='All Questions']")
    public List<WebElement> allQuestion_DropDown;

    @FindBy(xpath = "//a[@title='All Questions']//..//ul//li")
    public List<WebElement> allQuestionOptions;

    @FindBy(xpath = "//a[@title='All Objectives']")
    public WebElement allObjectives_DropDown;

    @FindBy(xpath = "//a[@title='All Objectives']//..//ul//li")
    public List<WebElement> allObjectivesOptions;

    @FindBy(xpath = "//a[starts-with(@title,'All Bloom')]")
    public WebElement taxonomy_DropDown;

    @FindBy(xpath = "//a[@title='All Question Type']")
    public WebElement allQuestionType_DropDown;

    @FindBy(xpath = "//*[@id='cms-content-search-filter-question-type-dropDown-section-search-tab']//a[@class='sbSelector']")
    public WebElement questionFilterDropdown;

    @FindBy(xpath ="//span[text()='GO']")//click on Go Button
    public WebElement go_Button;

    @FindBy(className = "cms-result-media-icon")
    public WebElement mediaIcon;

    public By questionText=By.id("content-search-results-block");

    @FindBy(className = "expand-question-content")
    public WebElement quickPreview;

    @FindBy(className = "collapse-question-content")
    public WebElement collapseQuickPreview;


    @FindBy(xpath = "//span[@class='cms-content-review-question-type']/span")
    public WebElement questionType;

    @FindBy(className = "associated-content-details")
    public WebElement status;

    @FindBy(className = "qtn-content-difficulty")
    public List<WebElement> difficultyLevel;

    @FindBy(xpath = "//embed[contains(@name,'wistia_')]")
    public WebElement video;

    @FindBy(xpath = "//div[@id='search-chapter-blk']/div")
    public WebElement chapterName;

    @FindBy(className = "preview-question-content")
    public WebElement fullPreview;

    @FindBy(id = "content-search-review-btn")
    public WebElement launchReview;

    @FindBy(className = "edit-question-content")
    public WebElement editIcon;

    @FindBy(id = "tab-browse")
    public WebElement browseTab;

    @FindBy(xpath = "//a[@title='Select Content Type']")
    public WebElement content_DropDown;

    @FindBy(xpath = "//a[@title='Select an option']")
    public WebElement option_DropDown;

    @FindBy(xpath = "//a[@title='Select Objectives']")
    public WebElement objectives_DropDown;

    @FindBy(xpath = "//a[@title='Select Difficulty Level']")
    public WebElement difficulty_DropDown;

    @FindBy(xpath = "//a[starts-with(@title,'Select Bloom')]")
    public WebElement taxonomyDropDown;

    @FindBy(xpath = "//a[@title='Select Status']")
    public WebElement statusDropDown;

    @FindBy(xpath = "//a[@title='Select Question Type']")
    public WebElement questionDropDown;

    @FindBy(xpath = "//a[@title='Select Objectives']//..//ul//..//li/a")
    public List<WebElement> objectiveOption;

    @FindBy(xpath = "//label[@for='name']/img")
    public WebElement image;

    @FindBy(xpath = "(//div[contains(@class,'sw-wistia-video-wrapper sw-media-content')])[2]")
    public WebElement uploadImage;

    @FindBy(xpath = "//img[contains(@id,'wistia_')]")
    public WebElement videoMedia;

    @FindBy(xpath = "//label[@for='title']/img")
    public WebElement imageMedia;

    @FindBy(linkText = "Select an option")
    public WebElement link_selectOption;

    @FindBy(xpath = ".//*[@id='copy-ref-check-box-div']//label")
    public WebElement checkBox_copyAsReferenceOnly;

    @FindBy(id = "lo-mapping-check-box")
    public WebElement checkBox_useLOMapping;

   /* @FindBy(xpath = "//div[@class='assessment-custom-radio-btn']//input")
    public WebElement assessmentCustomRadioButton;*/

    @FindBy(css = "input[type = 'radio']")
    public WebElement assessmentCustomRadioButton;

    @FindBy(id = "add-assesments-in-contentSearch")
    public WebElement copyButtonDone;

    @FindBy(id = "select-node")
    public WebElement copyQuestionNotification;

    @FindBy(xpath = "//div[@class='redactor_box']/div")
    public WebElement question_Text;

    @FindBy(id = "saveQuestionDetails1")
    public WebElement save_Button;
}
