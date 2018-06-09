package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by Dharaneesh T Gowda on 16-01-2015.
 */
public class PassageBasedQuestionCreation {
    @FindBy(id = "passage_title")
    WebElement textField_EnterInstructions;// Text Field 'Enter Instructions for the passage question...'
    public WebElement getTextField_EnterInstructions() {
        return textField_EnterInstructions;
    }


    @FindBy(className = "lsm-createAssignment-input-name")
    WebElement inputTextField_SampleAssessmentName; // Input text Field 'Sample Assessment Name' to enter the assessment name
    public WebElement getInputTextField_SampleAssessmentName(){
        return inputTextField_SampleAssessmentName;
    }



    /*@FindBy(css = "div[class = 'tab-title-text redactor_editor']")
    WebElement tab_EnterHeader;//  Tab 'Enter Header'
    public WebElement getTab_EnterHeader() {
        return tab_EnterHeader;
    }

*/


    @FindBys({@FindBy(xpath = "//div[@class='tab-title-text']")})
    List<WebElement> tab_EnterHeaderElementsList;
    public List<WebElement> getTab_EnterHeaderElementsList() {
        return tab_EnterHeaderElementsList;
    }


    @FindBy(css = "div[class='lsm-createAssignment-done selected']")
    WebElement button_review;// 'Review' Button
    public WebElement getButton_review() {return button_review;}

    /*@FindBy(xpath = "//div[contains(@id,'tab')]//inline[@class = 'redactor_placeholder']")
    WebElement textArea1Onwards_EnterTabContent;// Text Area 1 'Enter Tab Content'
    public WebElement getTextArea1Onwards_EnterTabContent() {
        return textArea1Onwards_EnterTabContent;
    }*/



    /*@FindBys({@FindBy(xpath = "//div[contains(@id,'tab')]//inline[@class = 'redactor_placeholder']")})
    List<WebElement> textArea_EnterTabContentElementsList;
    public List<WebElement> getTextArea_EnterTabContentElementsList() {
        return textArea_EnterTabContentElementsList;
    }*/

    @FindBys({@FindBy(xpath = "//div[contains(@id,'tab')]//inline[@class = 'redactor_placeholder']")})
    List<WebElement> textArea_EnterTabContentElementsList;
    public List<WebElement> getTextArea_EnterTabContentElementsList() {
        return textArea_EnterTabContentElementsList;
    }

    /*@FindBys({@FindBy(css = "div[class ='editable-question-content f43']")})
    List<WebElement> textArea_EnterTabContentElementsList;
    public List<WebElement> getTextArea_EnterTabContentElementsList() {
        return textArea_EnterTabContentElementsList;
    }*/



    @FindBy(css = "div[class = 'tab-link add-new-tab']")
    WebElement icon_PlusNewTab;// 1st '+' Symbol to add a new tab
    public WebElement getIcon_PlusNewTab() {
        return icon_PlusNewTab;
    }

    /*@FindBy(css = "div[class = 'tab-link current tab-title']")
    WebElement tab2_EnterHeader;// Second Tab 'Enter Header'
    public WebElement getTab2_EnterHeader() {
        return tab2_EnterHeader;
    }*/

    /*@FindBy(css = "div[class = 'tab-link add-new-tab']")
    WebElement icon_SecondPlusNewTab;// Second '+' Symbol to add a new tab
    public WebElement getIcon_SecondPlusNewTab() {
        return icon_SecondPlusNewTab;
    }*/

    @FindBy(id = "question-edit-passage-text")
    WebElement textArea1_EnterTabContent;// Text Area 1 'Enter Tab Content'
    public WebElement getTextArea1_EnterTabContent() {
        return textArea1_EnterTabContent;
    }

    /*@FindBy(xpath = "//div[contains(@id,'tab')]//inline[@class = 'redactor_placeholder']")
    WebElement textArea1Onwards_EnterTabContent;// Text Area 1 'Enter Tab Content'
    public WebElement getTextArea1Onwards_EnterTabContent() {
        return textArea1Onwards_EnterTabContent;
    }*/

    @FindBy(css = "div[class = 'editable-question-content f43 editable question-edit-passage-text redactor_editor']")
    WebElement textArea2_EnterTabContent;// Text Area 2 'Enter Tab Content'
    public WebElement getTextArea2_EnterTabContent() {
        return textArea2_EnterTabContent;
    }


    @FindBy(className = "remove-tab")
    WebElement icon_removeTab;// 'X' Symbol to remove a tab
    public WebElement getIcon_removeTab() {
        return icon_removeTab;
    }



    @FindBys({@FindBy(className = "remove-tab")})
    List<WebElement> icon_removeTabElementsList;
    public List<WebElement> getIcon_removeTabElementsList() {
        return icon_removeTabElementsList;
    }


    @FindBy(id = "footer-notification-text")
    WebElement notificationText; //Notification text 'Saved' after the question is saved
    public WebElement getNotificationText(){
        return notificationText;
    }

    @FindBy(className = "add-question-text")
    WebElement button_AddNewQuestionForThisPassage; //Button 'Add a New Question for this passage '
    public WebElement getButton_AddNewQuestionForThisPassage(){
        return button_AddNewQuestionForThisPassage;
    }


}


