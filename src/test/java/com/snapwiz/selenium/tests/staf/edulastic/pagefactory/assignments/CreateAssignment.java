package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by root on 12/29/14.
 */
public class CreateAssignment {
    @FindBy(id = "create-assessment-with-val")
    WebElement button_create;// 'Create' button to create a new assessment
    public WebElement getButton_create() {
        return button_create;
    }

    @FindBy(id = "new-assessment-name")
    WebElement assessment_editbox;//Edit box to enter the assignment name
    public WebElement getAssessment_editbox(){return assessment_editbox;}

    @FindBy(xpath = "//ul[@class='nav-tabs nav']/li/a")
    List<WebElement> tabs_differentQuestionType;//Different item type tabs to create different type of questions
    public List<WebElement> getTabs_differentQuestionType(){return tabs_differentQuestionType;}

    @FindBy(css = "input#new-assessment-name")
    public WebElement assignmentName;

    @FindBy(xpath = "//button[contains(@class,'btn btn-rounded btn-default lsm-createAssignment')]")
    public WebElement button_review;//Review button

}
