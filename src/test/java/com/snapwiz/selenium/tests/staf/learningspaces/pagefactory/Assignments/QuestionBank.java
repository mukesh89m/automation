package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by priyanka on 1/7/2015.
 */
public class QuestionBank {
    @FindBy(css = "span[title='Question Banks']")
    WebElement questionBankTitle;
    public WebElement getQuestionBankTitle(){return questionBankTitle; }

    @FindBy(className = "ls-preview")
    WebElement previewButton;
    public WebElement getPreviewButton(){return previewButton;}

    @FindBy(css = "span[title='Add to My Assignments']")
    WebElement addToMyQuestionBank;
    public WebElement getAddToMyQuestionBank(){return addToMyQuestionBank;}

    @FindBy(className = "assign-this")
    WebElement assignThisButtton;
    public WebElement getAssignThisButtton(){return assignThisButtton;}

    @FindBy(css = "span[title='Assign This']")
    WebElement assignThis;
    public WebElement getAssignThis(){return assignThis;}

    @FindBy(css = "span[title='Customize This']")
    WebElement customizeThis;
    public WebElement getCustomizeThis(){ return customizeThis;}

    @FindBys(@FindBy(className = "ls-notification-text-span"))
    List<WebElement> notificationMessage;
    public List<WebElement> getNotificationMessage(){return notificationMessage;}

    @FindBy(css = "span[title='Remove from My Question Bank']")
    WebElement removeFromMyQuestionBank;
    public WebElement getRemoveFromMyQuestionBank(){return removeFromMyQuestionBank;}

    @FindBy(className = "ir-ls-assign-dialog-header")
    WebElement popupHeader;
    public WebElement getPopupHeader(){return popupHeader;}

    @FindBy(xpath = "//img[@title='Close']")
    WebElement closeIconOnNavigationMessage;
    public WebElement getCloseIconOnNavigationMessage(){return closeIconOnNavigationMessage;}

    @FindBy(className = "ls-assignment-notify-count")
    WebElement myQuestionBankCount;
    public WebElement getMyQuestionBankCount(){
        return myQuestionBankCount;
    }

    @FindBy(xpath = "(//div[@class='resource-title'])[4]")
    WebElement secondAssessment;
    public WebElement getSecondAssessment(){
        return secondAssessment;
    }

    @FindBy(xpath = "//span[@class='ins-resource-search-sprite instructor-resource-search-img']")
    List<WebElement> searchButtonOnQuestionBank;//search button on Question banks tab.
    public List<WebElement> getSearchButtonOnQuestionBank(){
        return searchButtonOnQuestionBank;
    }

    @FindBy(className = "resource-search-box")
    List<WebElement> searchInputFieldOnQuestionBank;//search field on Question banks tab.
    public List<WebElement> getSearchInputFieldOnQuestionBank(){
        return searchInputFieldOnQuestionBank;
    }

    @FindBy(className = "coursePerformanceByChapters-xAxisLabel")
    WebElement performanceByChapters;
    public WebElement getPerformanceByChapters()
    {
        return performanceByChapters;}
    @FindBy(className = "chapterPerformanceByObjectives-xAxisLabel")
    WebElement performanceByObjectives;
    public WebElement getPerformanceByObjectives()
    {
        return performanceByObjectives;}

    @FindBy(className = "chapterPerformanceByQuestions-xAxisLabel")
    WebElement performanceByQuestions;
    public WebElement getPerformanceByQuestions()
    {
        return performanceByQuestions;}


    @FindBy(css = "span[class='action-links try-it']")
    public List<WebElement> tryItLink;

    @FindBy(className = "ls-try-it")
    public List<WebElement> tryItIcon;

    @FindBy(id = "cms-question-try-it-header-logo")
    public WebElement wileyLogo;


    @FindBy(css= "div[class='cms-question-preview-header-course-name try-it-wiley']")
    public WebElement courseName;

    @FindBy(xpath = "//div[@class='ls-uploaded-files-header']/span")
    public List<WebElement> language_Preview;

    @FindBy(className = "control-label")
    public WebElement questionContent;


    @FindBy(className = "ls-uploaded-files-header")
    public List<WebElement> languagePreview;

    @FindBy(css = "span[title='Question Banks']")
    public WebElement questionBankTab;

    @FindBy(xpath = ".//div[@id='ls-assessment-filter-questionset-drop-down-wrapper']/div/a")
    public WebElement questionBankDropdown;

    @FindBy(css = "a[title='Practice Sets Only']")
    public WebElement practiceSetsOnly;

    @FindBy(css = "i[class='assign-this resource-assign-this-image ls-assign-this-sprite']")
    public List<WebElement> assignThisIcon;

    @FindBy(className = "ls-actions")
    public List<WebElement> lsActions;

    @FindBy(className = "resource-title")
    public List<WebElement> assessmentName;

    @FindBy(css = "span[class='ls-preview-wrapper action-links']")
    public List<WebElement> preview;

    @FindBy(css = "span[class='ls-preview-wrapper action-links']")
    public  WebElement previewLink;

    @FindBy(css = "span[class='addThisToMyResources bookmark-label']")
    public List<WebElement> addToMyQuestionBanks;

    @FindBy(xpath = "//span[@class='assign-this']")
    public List<WebElement> assignThisLink;

    @FindBy(className = "customize-this")
    public List<WebElement> customizeThisLink;

    @FindBy(css = "span[class='tab_icon assignment-icon']")
    public WebElement assignmentIcon;

    @FindBy(id="customAssignment")
    public WebElement createCustomAssignment_link;

    @FindBy(css="div[class='ir-ls-assign-dialog-dw-gradable-label-check selected']")
    public List<WebElement> gradableCheckBoxList;

    @FindBy(className = "maininput")
    public  WebElement assignToTextBox;

    @FindBy(className = "no-results-message")
    public  WebElement noResultMessage;

    @FindBy(xpath= "//span[@class='ins-resource-search-sprite instructor-resource-search-img']")
    public WebElement searchIcon;

    @FindBy(css = "span[class='addThisToMyResources bookmark-label']")
    public WebElement addToMyAssignments;
}
