package com.snapwiz.selenium.tests.staf.learnon.pageFactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

    /**
     * Created by priyanka on 1/7/2015.
     */
    public class MyQuestionBank {
        @FindBy(className = "tab_title")
        WebElement myQuestionBankTitle;
        public WebElement getMyQuestionBankTitle(){return myQuestionBankTitle; }

        @FindBy(xpath = "//div[@class='resource-title']")
        WebElement assessment;
        public WebElement getAssessment(){return assessment;}

        @FindBy(id = "customAssignment")
        WebElement customAssignmentButton;//custom Assignment button
        public WebElement getCustomAssignmentButton(){return customAssignmentButton;}

        @FindBy(id = "ls-ins-save-assigment-btn")
        WebElement saveForLater;//save for later
        public WebElement getSaveForLater(){return saveForLater;}

        @FindBys(@FindBy(className = "ls-notification-text-span"))
        List<WebElement> notificationMessage;
        public List<WebElement> getNotificationMessage(){return notificationMessage;}

        @FindBy(className = "ls-ins-search-text")
        WebElement searchButton;
        public WebElement getSearchButton(){return searchButton;}

        @FindBy(id = "ls-ins-assignment-name")
        WebElement assignmentNameField;
        public WebElement getAssignmentNameField(){return assignmentNameField;}

        @FindBy(id = "ls-ins-edit-assignment")
        WebElement assignmentNameFieldAfterClick;
        public WebElement getAssignmentNameFieldAfterClick(){return assignmentNameFieldAfterClick;}

        @FindBy(css = "div[title='New Name - V1']")
        WebElement resourceTitle;
        public WebElement getResourceTitle(){return resourceTitle;}

        @FindBy(className = "ls-preview")
        WebElement previewButton;
        public WebElement getPreviewButton(){return previewButton;}

        @FindBys(@FindBy(className = "question-label"))
        List<WebElement> questionCount;
        public List<WebElement> getQuestionCount(){return questionCount;}

        @FindBy(className = "assign-this")
        WebElement assignThis;
        public WebElement getAssignThis(){return assignThis;}

        @FindBy(className = "assign-this")
        List<WebElement> assignThislist;
        public List<WebElement> getAssignThisList(){return assignThislist;}

        @FindBy(css = "span[class='btn sty-green submit-assign']")
        WebElement assignOnPopUp;
        public WebElement getAssignOnPopUp(){return assignOnPopUp;}

        @FindBy(xpath = "(//i[@class='ls-preview'])[2]")
        WebElement previewButtonOfOriginal;
        public WebElement getPreviewButtonOfOriginal(){return previewButtonOfOriginal;}

        @FindBy(xpath = "(//span[@title='Delete This'])[1]")
        WebElement deleteButtonOfOriginal;
        public WebElement getDeleteButtonOfOriginal(){return deleteButtonOfOriginal;}

        @FindBy(xpath = "//span[@title='Yes']")
        WebElement yesOnNotificationMessage;
        public WebElement getYesOnNotificationMessage(){return yesOnNotificationMessage;}

        @FindBy(className = "delete-this")
        WebElement deleteButton;
        public WebElement getDeleteButton(){return deleteButton;}

        @FindBy(className = "question-label-with-questionid")
        WebElement questionLabel;
        public WebElement getQuestionLabel(){return questionLabel;
        }

        @FindBy(css = "i[class='ls-ins-custom-image ls-ins-view-filter-down-arw']")
        WebElement filterArrow;
        public WebElement getFilterArrow(){return filterArrow;}

        @FindBy(xpath = "(//a[@class='sbToggle'])[15]")
        WebElement allQuestionTypeArrow;
        public  WebElement getAllQuestionTypeArrow(){return allQuestionTypeArrow;}

        @FindBy(xpath = "//a[.='True/False']")
        WebElement trueFalse;
        public WebElement getTrueFalse(){return trueFalse;}

        @FindBy(xpath = "//div[@class='ls-ins-browse-go']/span")
        WebElement go_Button;
        public WebElement getGo_Button(){return go_Button;}

        @FindBy(xpath = "(//a[@class='sbToggle'])[10]")
        WebElement allChapters;
        public WebElement getAllChapters(){return allChapters;}

        @FindBy(xpath = "(//a[text()='Ch 1: The Study of Life'])[3]")
        WebElement studyOfLife;
        public WebElement getStudyOfLife(){return studyOfLife;}

        @FindBy(xpath = "(//a[@title='Ch 1A: The European Realm'])[3]")
        WebElement europeanRegion;
        public WebElement getEuropeanRegion(){return  europeanRegion;}

        @FindBy(css = "a[class='ls-file-delete-file-name ellipsis']")
        public WebElement filename;

        @FindBy(className = "close_tab")
        public List<WebElement> closeTabIcon;

        @FindBy(css = "span[class='removeThisFromMyResources  bookmark-label']")
        public List<WebElement> removeFromMyQuestionBankLink;

        @FindBy(className = "customize-this")
        public WebElement customizeThis;

        @FindBy(className = "ir-ls-assign-dialog-header")
        public WebElement popUP;

        @FindBy(id = "assign-cancel")
        public WebElement cancelPopUp;

        @FindBy(css = "span[class='btn sty-green submit-assign']")
        public WebElement assignPopUp;

        @FindBy(className = "notification-text-span")
        public WebElement notification;

        @FindBy(className = "ls-donot-delete-assignment")
        public WebElement noLink;

        @FindBy(css = "span[class='close-current-assignment delete-custom-assignment']")
        public WebElement yesLink;

        @FindBy(xpath = "//div[@class='resource-title']")
        public List<WebElement> assessmentName;

        @FindBy(id="all-resource-search-textarea")
        public WebElement allResourse_textarea;

        @FindBy(className = "ir-ls-assign-dialog-gradable-label-check")
        public WebElement gradable_checkBox;

        @FindBy(xpath = "//a[@title='Select an Assignment Policy']")
        public WebElement selectPolicy;

        @FindBy(id="assignment-policy-icons")
        public  WebElement eyeIcon; //eye icon while selecting policy

        @FindBy(xpath = "//div[@reassign='true']/div/a")
        public WebElement selectPolicy_dropdown;

        @FindBy(id = "close-new-assignment")
        public WebElement closeTab;

        @FindBy(xpath = "//a[.='Multiple Selection']")
        public WebElement multipleSelection;

        @FindBy(xpath = "//a[.='Easy']")
        public WebElement easy_Link;

        @FindBy(xpath = "//a[.='Medium']")
        public WebElement medium_Link;

        @FindBy(xpath = "//a[.='Hard']")
        public WebElement hard_Link;

        @FindBy(xpath = "//div[@id='ls-ins-all-difficulty']/div/a[1]")
        public  WebElement allDifficultyLevelArrow;

        @FindBy(xpath = "(//a[text()='Ch 2: The Chemical Foundation of Life'])[2]")
        public WebElement chemicalFoundation;

        @FindBy(xpath = "//span[@title='Edit This']")
        public WebElement editThis;

        @FindBy(xpath = "//a[.='Workboard']")
        public List<WebElement> workBoard;

        @FindBy(xpath = "//a[@class='sbToggle']")
        public  List<WebElement> AllQuestionTypeArrow;

        @FindBy(id = "customAssignment")
        public List<WebElement> customAssignmentButton_list;//custom Assignment button



        @FindBy(xpath = "//span[@title='Share This']")
        public WebElement shareThis;

        @FindBy(className = "ls-try-it")
        public List<WebElement> tryItIcon;

        @FindBy(id = "ir-ls-assign-dialog")
        public WebElement shareWithPopUp;

        @FindBy(css = "span[class='short-label-help-icon share-this-label-help-icon']")
        public WebElement helpIcon;

        @FindBy(xpath = "//ul[@class='holder']")
        public WebElement shareWithTextBox;

        @FindBy(css = "span[class='btn sty-blue ls-share-this-button']")
        public WebElement shareButton;

        @FindBy(className = "assign-this-help-tooltip-wrapper")
        public WebElement helpMessage;


        @FindBy(id = "share-with_annoninput")
        public WebElement shareWith_TextBox;

        @FindBy(className = "maininput")
        public WebElement shareTextBox;

        @FindBy(xpath = "//ul[@id='share-with_feed']/div[2]/div/li")
        public List<WebElement> suggestionBox;

        @FindBy(className = "yes-share-this-assessment")
        public WebElement yesOnShareMessage;

        @FindBy(className = "item-text")
        public List<WebElement> sharedName;

        @FindBy(className = "closebutton")
        public WebElement closeIcon_instructorCard;

        @FindBy(css = "div[class='share-with-wrap ls-share-with-wrap']")
        public List<WebElement> shareFieldBlank;

        @FindBy(className = "customizeStudyPlanNo")
        public WebElement noOnShareMessage;

        @FindBy(className = "error-message-assign")
        public WebElement errorMessage;

        @FindBy(xpath = "//div[@class='ls-assessment-updated-date']/span")
        public List<WebElement> updatedBy;




    }


