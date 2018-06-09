package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R161.PublishAdminAbleToDeleteAnnouncement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Murthi on 12/29/2016.
 */
public class AddStudentToAssignment {

    //Header elements
    @FindBy(css = "#assign-button")
    private WebElement btnAssign;

    //Left side elements
    @FindBy(css = ".lsm-createAssignment-input-name")
    private WebElement txtBoxAssessmentName;

    @FindBy(css = ".lsm-assignment-total-points")
    private WebElement lblTotalPoints;

    @FindBy(css = ".select2-search__field")
    private WebElement txtBoxTags;

    //Right side elements
    //Assign now section
    @FindBy(css = "#share-with_annoninput>input")
    private WebElement txtBoxAssignThisTo;

    @FindBy(css = "#share-with_feed>li")
    private List<WebElement> lstSuggestedStudent;

    @FindBy(css = "#assessment-open-date")
    private WebElement txtBoxStartDate;

    @FindBy(css = "#assessment-close-date")
    private WebElement txtBoxDueDate;

    @FindBy(css = "#start-later")
    private WebElement lstBoxAssessmentOpen;

    @FindBy(css = "#close-later")
    private WebElement lstBoxAssessmentClose;

    @FindBy(css = ".lsm-assignment-assignto-tooltip")
    private WebElement errMsgAssignTo;

    @FindBy(css = "#headingOne .p-r-md")
    private WebElement lblAdvancedOptions;

    public void clickAdvancedOptions(){
        lblAdvancedOptions.click();
    }

    public boolean isAssessmentNameEditable(){
        return txtBoxAssessmentName.isEnabled();
    }

    public boolean isTotalPointsEditable(){
        return Boolean.parseBoolean(WebDriverUtil.executeJavascript("return arguments[0].isContentEditable",lblTotalPoints));
    }

    public boolean isTagsEditable(){
        return txtBoxTags.isEnabled();
    }


    public boolean isNameExistInSuggestedList(String name){
        for(WebElement element:lstSuggestedStudent){
            if(element.getText().equals(name))
                return true;
        }
        return false;
    }

    public boolean selectNameFromAutoSuggestedList(String name){
        for(WebElement element:lstSuggestedStudent){
            if(element.getText().equals(name)){
                element.click();
                return true;
            }
        }
        return false;
    }

    public void clickOnAssignButton(){
        btnAssign.click();
    }

    public boolean isErrorMessageDisplayedForAssignTo(String message){
        return errMsgAssignTo.getText().trim().toLowerCase().equals(message.trim().toLowerCase());
    }

    public boolean isAssessmentStarDateEditable(){
        return txtBoxStartDate.isEnabled();
    }

    public boolean isAssessmentDueDateEditable(){
        return txtBoxDueDate.isEnabled();
    }

    public boolean isAssessmentOpenListBoxEditable(){
        return lstBoxAssessmentOpen.isEnabled();
    }

    public boolean isAssessmentCloseListBoxEditable(){
        return lstBoxAssessmentOpen.isEnabled();
    }

    public String getAssessmentName(){
        return txtBoxAssessmentName.getAttribute("value");
    }

    public String getTotalPoints(){
        return lblTotalPoints.getText();
    }

    public boolean isAssignThisToTextBoxEmpty(){
        return txtBoxAssignThisTo.getAttribute("value").trim().isEmpty();
    }

    public AddStudentToAssignment clickOnAssignThisTo()
    {
        txtBoxAssignThisTo.click();
        return this;
    }

    public List<WebElement> enterTextInAssignThisToTextBox(String text){
        txtBoxAssignThisTo.clear();
        txtBoxAssignThisTo.sendKeys(text);
        WebDriverUtil.waitForAjax(Driver.getWebDriver(),30);
        return lstSuggestedStudent;
    }




}
