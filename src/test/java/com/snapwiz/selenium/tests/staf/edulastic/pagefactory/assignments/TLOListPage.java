package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by root on 12/29/14.
 */
public class TLOListPage {

    @FindBy(css = "span[data-skillidentifier = '1.OA.A.1']")
    WebElement button_create;// 'Create' button to create new assessment for different Question Types in particular TLO & ELO
    public WebElement getButton_create() {
        return button_create;
    }

    @FindBy(css = "span[class='lsm-create-btn lsm-elo-create-btn']")
    WebElement button_createForAll;//Crete button to go to different question type
    public WebElement getButton_createForAll(){return button_createForAll;}

    @FindBy(css = "span[data-skillidentifier = '2.OA.A.1']")
    WebElement button_createGrade2;// 'Create' button to create new assessment for different Question Types in particular TLO & ELO for grade 2
    public WebElement getButtonGrade2_create() {
        return button_createGrade2;
    }

    @FindBy(xpath = "//span[@class='as-create-asignmentHeader-close']")
    WebElement button_close;//Close button
    public WebElement getButton_close(){return button_close;}

    @FindBy(css = "div[class='as-modal-yes-btn yes-delete add-question-page']")
    WebElement buttonYes_popup;//Yes button to close the pop up
    public WebElement getButtonYes_popup(){return buttonYes_popup;}

    @FindBy(xpath="(//span[@class='lsm-select-btn lsm-select-elo-questions-btn'])[1]")
    WebElement button_select;//1st Select button
    public WebElement getButton_select(){return button_select;}

    @FindBy(className = "as-grey-checkbox")
    WebElement checkbox;//Check box to select the existing question to add in new assessment
    public WebElement getCheckbox(){return checkbox;}

}
