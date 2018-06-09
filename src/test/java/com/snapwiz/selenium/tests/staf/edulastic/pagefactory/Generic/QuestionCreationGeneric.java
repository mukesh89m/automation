package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Generic;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Dharaneesh T Gowda on 31-12-2014.
 */
public class QuestionCreationGeneric {
    @FindBy(id = "saveQuestionDetails1")
    WebElement button_Save; // 'Save' button to save the question
    public WebElement getButton_Save(){
        return button_Save;
    }


    @FindBy(id="question-reveview-submit")
    WebElement submitButton;// 'Submit' button to submit the question
    public WebElement getSubmitButton()
    {
        return submitButton;
    }



}
