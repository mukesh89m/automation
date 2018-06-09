package com.snapwiz.selenium.tests.staf.learnon.pageFactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by Mukesh on 8/17/15.
 */
public class QuestionBank {
        @FindBy(css = "span[title='Question Banks']")
        WebElement questionBankTitle;
        public WebElement getQuestionBankTitle(){return questionBankTitle; }

        @FindBy(className = "ls-preview")
        WebElement previewButton;
        public WebElement getPreviewButton(){return previewButton;}

        @FindBy(css = "span[title='Add to My Question Bank']")
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


    }
