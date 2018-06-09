package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by shashank on 30-01-2015.
 */
public class Summary {
    @FindBy(xpath = "//a[@title='Publish']")
     public WebElement publish_dropdown;// publish value in select status

    @FindBy(xpath = "//div[@id='cms-review-question-content']")
    public WebElement reviewQuestion;// when user search Questions displayed

    @FindBy(xpath = "//div[@class='expand-question-content']")
    public WebElement quickReviewExpand;//To check status and Question type


    @FindBy(xpath = "//div[@class='search-content-question-details']")
    public WebElement chepterNameOnSummary;//To check status and Question type



    @FindBy(xpath = "//*[text()='Questions']/preceding-sibling::*")
    public WebElement fetchQuestionCount;//To check status and Question type

    @FindBy(xpath = "//*[text()='Publish']/preceding-sibling::*[1]")
    public WebElement fetchPublishCount;//To check status and Question type

    @FindBy(xpath = "//*[text()='Ready To Publish']/preceding-sibling::*[1]")
    public WebElement fetchReadyToPublishCount;//To check status and Question type

    @FindBy(xpath = "//*[text()='Draft']/preceding-sibling::*[1]")
    public WebElement fetchDraftsCount;//To check status and Question type

    @FindBy(xpath = "//*[text()='QA']/preceding-sibling::*[1]")
    public WebElement fetchQACount;//To check status and Question type

    @FindBy(xpath = "//*[text()='Needs Revision']/preceding-sibling::*[1]")
    public WebElement fetchNeedRevisionCount;//To check status and Question type

    @FindBy(xpath = "//*[text()='Approve']/preceding-sibling::*[1]")
    public WebElement fetchApproveCount;//To check status and Question type

    @FindBy(xpath = "//*[text()='Draft - Pending Images']/preceding-sibling::*[1]")
    public WebElement fetchDraftPendingImagesCount;//To check status and Question type

    @FindBy(xpath = "//*[text()='Accuracy Check']/preceding-sibling::*[1]")
    public WebElement fetchAccuracyCheckCount;//To check status and Question type

    @FindBy(xpath = "//li[contains(text(),'Summary')]")
    public WebElement summary;//Summary tab

    @FindBy(xpath = "//a[text()='Difficulty Level Count']")
    public WebElement difficultyLevelCountDropdown ;//Difficulty Level Count Dropdown

    @FindBy(linkText = "Bloom's Taxonomy Count")
    public WebElement bloomsTaxonomyCountDropdown ;//Bloom's Taxonomy Count Dropdown

    @FindBy(xpath = "//a[text()='Question Status Count']")
    public WebElement questionStatusCountDropdown ;//Difficulty Level Count Dropdown

    @FindBy(xpath = "//div[@id='refresh-publish-content']")
    public WebElement refreshSummaryCount;

    @FindBy(xpath = "//div[text()='Ch1']")
    public WebElement chapterUnderGraph;//barGraph

    @FindBy(xpath="//span[text()='Show questions']")
    public WebElement showQuestions;

    @FindBy(xpath="//a[contains(text(),'Edit Question')]")
    public WebElement editQuestion;

    @FindBy(xpath="//div[contains(@id,'questionOptions')]")
    public WebElement newVersion;

    @FindBy (xpath="//div[contains(text(),'Revisions')]")
    public WebElement revision;

    @FindBy (id = "cms-question-revision-new-version-link")
    public WebElement createNewVersion;

    @FindBy (xpath = "//div[contains(@id,'question-raw-content-preview')]/label")
    public WebElement getQuestionText;

    @FindBy(xpath = "//div[contains(@id,'question-editor-btn-nav-third-coloumn')]/span")
    public WebElement saveButton;

    @FindBy (xpath="//div[@id='cms-question-revision-deactivate-button']")
    public WebElement deactivateButton;


    @FindBy(xpath = "//a[text()='Question Type Count']")
    public WebElement questionTypeCountDropdown ;

    @FindBy(className = "cms-QuestionsType-name")
    public List<WebElement> question_Type;

}
