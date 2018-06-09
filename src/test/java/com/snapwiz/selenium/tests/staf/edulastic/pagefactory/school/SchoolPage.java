package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/*
 * Created by sumit on 22/1/15.
 */
public class SchoolPage {

    @FindBy(css = "div[class='as-search-blue-btn as-search-next btn btn-blue pull-right m-t m-b']")
    WebElement continueButton; // 'Save Button' button
    public WebElement getContinueButton(){
        return continueButton;
    }

    @FindBy(css = "a[class='as-add-link m-l m-r-lg']")
    WebElement button_add;//Add button to add new school
    public WebElement getButton_add(){return button_add;}

    @FindBy(id="school-name")
    WebElement editbox_schoolName;//Edit box to enter School name
    public WebElement getEditbox_schoolName(){return editbox_schoolName;}

    @FindBy(css = "div[class='as-errorMsg school-name-message']")
    WebElement error_msg_schoolName;//Error message when school name is not entered
    public WebElement getError_msg_schoolName(){return error_msg_schoolName;}

    @FindBy(css = "div[class='as-errorMsg zip-code-message']")
    WebElement error_msg_zip;//Error message when zip is not entered
    public WebElement getError_msg_zip(){return error_msg_zip;}

    @FindBy(css = "i[class='fa fa-times close-popup pointer pull-right m-t-xs']")
    WebElement close; //Close button to close the add new school pop up
    public WebElement getClose(){return close;}

    @FindBy(css = "div[class='as-search-blue-btn as-add-save-btn btn btn-blue pull-right']")
    WebElement button_addSchool;// Add school button
    public WebElement getButton_addSchool(){return button_addSchool;}

    @FindBy(id = "zip-code")
    public WebElement editBox_zipCode;// Zip code edit box

}
