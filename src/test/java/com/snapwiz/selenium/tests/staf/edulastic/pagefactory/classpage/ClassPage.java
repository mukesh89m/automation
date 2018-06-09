package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.classpage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by shashank on 29-07-2015.
 */
public class ClassPage {

    @FindBy(id = "class-name")
    public WebElement nameOfClass;

    @FindBy(css = "select[class='form-control as-add-subjectArea-dropDown']")
    public WebElement selectSubjectArea;

    @FindBy(css= "select[class='form-control as-add-subject-dropDown']")
    public WebElement selectSubject;

    @FindBy(css = "select[class='form-control as-add-grade-dropDown']")
    public WebElement selectGrade;

    @FindBy(css= "div[class='as-search-blue-btn btn btn-blue as-add-save-btn']")
    public WebElement finishButton;

    @FindBy(css = "div[class='as-errorMsg subject-message']")
    public WebElement errorMsg_standard;//Error message on standard

    @FindBy(css = "div[class='as-errorMsg subject-message']")
    public WebElement errorMsg_subject;//Error message on subject

    @FindBy(css = "div[class='as-errorMsg grade-message']")
    public WebElement errorMsg_subjectGrade;//Error message on subject grade


}
