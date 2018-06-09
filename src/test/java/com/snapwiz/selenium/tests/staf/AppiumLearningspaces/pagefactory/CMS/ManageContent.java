package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.CMS;

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
    public WebElement quickPreview;

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



}
