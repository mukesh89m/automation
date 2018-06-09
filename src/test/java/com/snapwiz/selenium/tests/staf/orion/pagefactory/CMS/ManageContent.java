package com.snapwiz.selenium.tests.staf.orion.pagefactory.CMS;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/*
 * Created by shashank on 03-02-2015.
 */
public class ManageContent {
    @FindBy(xpath = "//a[text()='Jump To Q#']")  //Question dropdown under Chapter
    public WebElement jumpToQuestion;// publish value in select status

    @FindBy(xpath="//div[contains(@class,'redactor_box')]/div")
    public WebElement fetchQuestionDescription;  //Path to fetch Question description

    @FindBy(xpath="//a[contains(@selectedid,'80')]")
    public WebElement questionStatus;  //Question status as publish

    @FindBy(xpath="//span[@id='save-question-disabled']")
    public WebElement disabledSaveButton; //Save button disabled

    @FindBy(xpath="//span[@id='save-question-disabled']")
    public WebElement saveButton; //Save button disabled

    @FindBy(xpath= "//div[contains(@class,'overview')]/li/a")
    public List<WebElement> totalNumberOfQuestions;

    @FindBy(xpath= "//div[@class='cms-assessment-checkbox-row']/i")
    public List<WebElement> topicList;

    @FindBy(xpath= "//div[@class='cms-assessments-dialog-heading']/i")
    public WebElement cancelOnCopyAssesssment;

    @FindBy (xpath  ="//li[@id='manage-content-tab']")
    public WebElement manageContent;

    @FindBy (xpath  ="//div[contains(text(),'ASSOCIATED CONTENT')]")
    public WebElement textAssociatedContent;

    @FindBy (xpath  ="//div[@class='message-wrapper no-lessons']/span")
    public WebElement textUnderLessontabFirstLine;//First line under Lesson tab

    @FindBy (xpath  ="//div[@class='message-wrapper no-lessons']/span[2]")
    public WebElement textUnderLessontabSecondLine;//Second line under Lesson tab

    @FindBy (xpath  ="//div[@class='message-wrapper no-questions']/span")
    public WebElement textUnderQuestiontabFirstLine;//First line under Lesson tab

    @FindBy (xpath  ="//div[@class='message-wrapper no-questions']/span[2]")
    public WebElement textUnderQuestiontabSecondLine;//Second line under Lesson tab

    @FindBy (xpath  ="//div[contains(@class,'expand-chapter-tree expand')]")
    public WebElement expandChapter;

    @FindBy (xpath  ="//div[text()='Introduction']")
    public WebElement sectionUnderChapter;

    @FindBy(css="span[class='publish-chapter-lesson-icon publish-chapter-lesson']")
    public WebElement publishIcon;

    @FindBy (xpath = "//input[@status='10' and @class='question-publish-status-check']/following-sibling::span")
    public WebElement getDraftQuestionCount;

    @FindBy (xpath = "//input[@status='20' and @class='question-publish-status-check']/following-sibling::span")
    public WebElement getDraftPendingImagesQuestionCount;

    @FindBy (xpath = "//input[@status='30' and @class='question-publish-status-check']/following-sibling::span")
    public WebElement getAccuracyCheckQuestionCount;

    @FindBy (xpath = "//input[@status='40' and @class='question-publish-status-check']/following-sibling::span")
    public WebElement getQAQuestionCount;

    @FindBy (xpath = "//input[@status='50' and @class='question-publish-status-check']/following-sibling::span")
    public WebElement getNeedRevisionQuestionCount;

    @FindBy (xpath = "//input[@status='60' and @class='question-publish-status-check']/following-sibling::span")
    public WebElement getApproveQuestionCount;

    @FindBy (xpath = "//input[@status='70' and @class='question-publish-status-check']/following-sibling::span")
    public WebElement getReadyToPublishQuestionCount;

    @FindBy (xpath = "//input[@status='80' and @class='question-publish-status-check']/following-sibling::span")
    public WebElement getPublishQuestionCount;

    @FindBy (css = "div.create-practice")
    public WebElement getCreatePractice;

    @FindBy (className = "create-regular-assessment-popup-item")
    public WebElement createRegularAssessment;

    @FindBy (css = "div.create-practice")
    public WebElement createPractice ;

    @FindBy (className = "create-file-assessment-popup-item")
    public WebElement createFileBasedAssessment;

    @FindBy(xpath = "//img[@id='content-search-icon']")
    public WebElement searchLink;
    @FindBy(xpath = "//span[@class='ls-delete-file-icon']")
    public List<WebElement> deleteUploadedFile;


    @FindBy(id = "qtn-type-true-false-img")
    public WebElement trueFalseQuestion;

    @FindBy(className = "content-label")
    public WebElement workBoard;

    @FindBy(className = "expand-question-content")
    public List<WebElement> quickPreview;

    @FindBy(id = "assessmentName")
    public WebElement assessmentname;

    @FindBy(id = "questionSetName")
    public  WebElement questionSetName;

    @FindBy(id = "preview-the-image-quiz")
    public WebElement preview_Button;

    @FindBy(id = "qtn-writeboard-type-new")
    public WebElement writeBoardQuestion;

    @FindBy(id = "question-writeboard-content-label")
    public WebElement workBoardPreviewText;

    @FindBy(id = "question-type-title")
    public WebElement question_Type;

    @FindBy(id = "search-filter-link")
    public WebElement searchFilterLink;

    @FindBy(xpath = "//a[.='All Question Type']")
    public List<WebElement> allQuestionDropDownArrow;

    @FindBy(xpath = "//a[.='Workboard']")
    public List<WebElement> workBoardQuestionType;

    @FindBy(className = "cms-conetent-search-go-btn")
    public WebElement go_Button;

    @FindBy(className = "cms-content-review-question-type")
    public WebElement questionType;

    @FindBy(id = "content-search-field")
    public WebElement searchField;

    @FindBy(xpath = "//div[@id='cms-review-question-content']")
    public List<WebElement> reviewQuestion;// when user search Questions displayed

    @FindBy(xpath = "//div[@class='expand-question-content']")
    public List<WebElement> quickReviewExpand;//To check status and Question type

    @FindBy(id = "question-check-box-div")
    public List<WebElement> checkBox;

    @FindBy(xpath= "//img[@title='Launch review']")
    public WebElement lunchReviewButton;

    @FindBy(xpath= "//div[@id='buttonPaneHeader-choice1']")
    public WebElement textEditor;

    @FindBy(xpath= "//div[@id='question-edit']/div/div[1]")
    public WebElement questionTextBox;

    @FindBy(id = "question-raw-content")
    public List<WebElement> questionTextBox_orion;

    @FindBy(xpath= "//div[@class='redactor_box']/div/inline")
    public WebElement questionContent;

    @FindBy(css= "a[class='re-icon re-fontfamily redactor-btn-image']")
    public List<WebElement> fontFamilyIcon;

    @FindBy(css= "div[class='redactor_dropdown redactor_dropdown_box_fontfamily']")
    public List<WebElement> fontFamilyList;

    @FindBy(css = "a[class='redactor_dropdown_s6 opensans-default-font-active']")
    public List<WebElement> openSansFont;

    @FindBy(css = "a[class=' redactor_dropdown_s5']")
    public List<WebElement> monoSpaceFont;

    @FindBy(css = "a[class='redactor_dropdown_s5 active']")
    public List<WebElement> activeMonoSpaceFont;

    @FindBy(css= "span.true-false-answer-label")
    public WebElement turFalseAnswer;

    @FindBy(id= "saveQuestionDetails1")
    public WebElement save_Button;

    @FindBy(xpath = "//label[contains(@class,'control-label')]/inline")
    public WebElement question_Text;

    @FindBy(id = "questionOptions")
    public WebElement newLink;

    @FindBy(id = "copyQuestion")
    public WebElement duplicateLink;

    @FindBy(xpath = "//div[contains(@class,'q-label-center')]/strong")
    public WebElement questionLabel;

    @FindBy(id = "addQuestion")
    public WebElement newLinkOption;

    @FindBy(className = "create-question-header-sectn")
    public  WebElement createQuestionText;

    //Langauge palette

    @FindBy(css = "a[class='re-icon re-language redactor-btn-image']")
    public WebElement langaugePalette;

    @FindBy(id = "languages")
    public WebElement langaugePalette_icon;

    @FindBy(css = "a[class='re-icon re-language redactor-btn-image']")
    public List<WebElement> langaugePalettes;

    @FindBy(xpath = "//div[@class='redactor_dropdown redactor_dropdown_box_language']/a")
    public List<WebElement> langaugePalette_option;

    @FindBy(xpath = "//a[text()='Spanish']")
    public WebElement langaugePalette_spanish;

    @FindBy(xpath = "//a[text()='Spanish']")
    public List<WebElement> langaugePalette_spanishs;


    @FindBy(xpath = "//a[text()='Italian']")
    public WebElement langaugePalette_Italian;

    @FindBy(xpath = "//a[text()='Italian']")
    public List<WebElement> langaugePalette_Italians;

    @FindBy(xpath = "//a[text()='French']")
    public WebElement langaugePalette_French;

    @FindBy(xpath = "//a[text()='French']")
    public List<WebElement> langaugePalette_Frenchs;

    @FindBy(id="language-palette-outer-wrapper")
    public WebElement langaugePalette_popup;

    @FindBy(id="languagePaletteBox")
    public WebElement langaugePalette_textBox;

    @FindBy(xpath = "//*[@id='languagePaletteBox']/following-sibling::div")
    public WebElement langaugePalette_textBoxContent;

    @FindBy(className = "palette-close-icon")
    public WebElement langaugePalette_closeIcon;

    @FindBy(id = "cancelLanguage")
    public WebElement langaugePalette_cancelButton;

    @FindBy(id="save-language-text")
    public WebElement langaugePalette_saveButton;

    @FindBy(xpath="//div[contains(@class,'language-icon-containers')]/input")
    public List<WebElement> langaugePalette_allinput;

    @FindBy(css="div[class='buttonPaneHeader language-title']>div")
    public WebElement langaugePalette_header;

    @FindBy(id="question-raw-content")
    public WebElement questionContent_area;

    @FindBy(id = "content-solution")
    public WebElement contentSolution;

    @FindBy(id = "content-hint")
    public WebElement contentHint;

    @FindBy(xpath = "//div[@class='language-palette-draft-drop-down-wrapper']//ul/li/a")
    public List<WebElement> selectLangaugePalete_TextEntry;

    @FindBy(className = "spanish-popup")
    public WebElement spanishPalette_icon;

    @FindBy(className = "visible_redactor_input")
    public WebElement textEntryQuestionContent;

    @FindBy(css = "span[class='btn sty-green save-language-text accept_answer text_entry_accept_answer']")
    public WebElement accept_answer;

    @FindBy(xpath = "//*[starts-with(@id, 'popupEditor_')]")
    public WebElement multipleChoice1;

    @FindBy(css = "a[class='redactor_dropdown_spanish showOptionsPointer multiple_selection_answerchoice']")
    public List<WebElement> multipleChoice_spanish;

    @FindBy(xpath = "//a[@class='redactor_dropdown_spanish showOptionsPointer multiple_selection_answerchoice']//following-sibling::a[1]")
    public List<WebElement> multipleChoice_italian;

    @FindBy(xpath = "//a[@class='redactor_dropdown_spanish showOptionsPointer multiple_selection_answerchoice']//following-sibling::a[2]")
    public List<WebElement> multipleChoice_fench;

    @FindBy(xpath = "//div[@id='cms-question-revision-checkbox']/input")
    public List<WebElement> questionRevision_chechkbok;

    @FindBy(id="cms-question-revision-get-diff")
    public WebElement getDiff_link;

    @FindBy(xpath = "//*[@id='diffoutput']//ol[2]/span[1]/following-sibling::ins[1]")
    public WebElement diffValue;

    @FindBy(xpath = ".//*[@id='question-raw-content-preview']/label")
    public List<WebElement> QuestionContents; //Questions contents in search page

    @FindBy(xpath = "//div[@id = 'question-check-box-div']//label")
    public List<WebElement> checkboxes; //checkboxes in search page

    @FindBy(className = "edit-question-content")
    public List<WebElement> edit_Link; //edit link on the search page of question

    @FindBy(className = "question-set-dropdown-title")
    public WebElement questionSet_title;

    @FindBy(className = "assessment-name-label-wrapper")
    public WebElement assessment_title;

    @FindBy(xpath = "//div[@id='cms-content-search-filter-difficulty-dropDown-section-browse-tab']//div/a")
    public WebElement difficultyLevel;

    @FindBy(xpath = "//a[@title='Hard']")
    public WebElement difficultyLevel_hard;

    @FindBy(className = "preview-question-content")
    public List<WebElement> preview_link;

    @FindBy(xpath = "//input[@id='cms-file-assessment-popup-title']")
    public WebElement fileBasedAssessmentName;

    @FindBy(id = "question-prompt-raw-content")
    public WebElement questionPrompt;

    @FindBy(xpath = "//a[@id='uploadFile']")
    public WebElement uploadButton;

    @FindBy(xpath = "//span[@class='cms-file-assessment-popup-save']")
    public WebElement saveButtonFileBased;

    @FindBy(xpath = "//*[starts-with(@class, 'course-chapter-label')]")
    public List<WebElement> chapterName;

    @FindBy(id = "questionRevisions")
    public WebElement revisionLink;

    @FindBy(id = "cms-question-revision-new-version-link")
    public WebElement createNewVersion;

    @FindBy(xpath = "//a[text()='Draft']")
    public WebElement draftLink;

    @FindBy(xpath = "//a[text()='Publish']")
    public WebElement publishLink;

    @FindBy(css = "a[title='Select Language Palette']")
    public WebElement languageDropDown;

    @FindBy(css = "a[rel='italian']")
    public WebElement italianLanguage;

    @FindBy(css = "div[class='search-question-rawcontent search-question-content']")
    public List<WebElement> questionContentsInSearchPage;

    @FindBy(xpath = "//*[@id='search-chapter-blk']/div")
    public List<WebElement> chapterNameInSearchPage;

    @FindBy(id="header-change-course")
    public WebElement change_link;

    @FindBy(className = "control-label")
    public WebElement questionContent_preview;

    @FindBy(className = "question-edit-link")
    public WebElement previewEdit_link; //edit link in review page

    @FindBy(className = "cms-assessment-footer-copy")
    public WebElement copy_button;

    @FindBy(className = "cms-assessments-dialog-close")
    public WebElement close_icon; //close icon for bulk operation

    @FindBy(xpath = "//div[@class='question-set-dropdown-wrapper']//div/a[2]")
    public WebElement questionSet_dropdown;

    @FindBy(xpath = "//div[@class='question-set-dropdown-wrapper']//li/a")
    public List<WebElement> questionSet_dropdownList;

    @FindBy(className = "confirmation-message-block")
    public WebElement confirmationMsg;

    //Langauge Pelette for Orion


    @FindBy(xpath=".//*[@id='languages']")
    public WebElement langaugePalette_orion;

    @FindBy(className = "language-title")
    public WebElement spanish_header;

    @FindBy(className = "cancel-language-dialog")
    public WebElement cancel_button_orion;

    @FindBy(className = "save-language-text")
    public WebElement save_button_orion;

    @FindBy(className = "language-palette-button")
    public List<WebElement> langauge_input;

    @FindBy(className = "qtn-label")
    public  List<WebElement> questionLabel_orion;

    @FindBy(id="enable_spanish_palette")
    public WebElement enableSpanish_checkbox;

    @FindBy(className = "show-language-palette-dialog")
    public List<WebElement> languagePalette_icon;

    @FindBy(id="done-button")
    public WebElement accept_answer_TextEntry;

    @FindBy(xpath = ".//*[@id='right-alt-container-1']/div")
    public WebElement alterNativeSpanish_icon;

    @FindBy(className = "left-container")
    public List<WebElement> alterNative_answer;



}
