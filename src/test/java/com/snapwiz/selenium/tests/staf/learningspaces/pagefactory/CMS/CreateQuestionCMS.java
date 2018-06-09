package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by root on 18/12/14.
 */
public class CreateQuestionCMS {

    @FindBy(linkText = "Adaptive Component Diagnostic")
    public WebElement dropDown_AssignmentType;
    public WebElement expand_AssignmentTypeDropDown() { return dropDown_AssignmentType; }

    @FindBy(linkText = "Static Practice")
    public WebElement dropDown_staticPracticeValue;
    public WebElement select_staticPracticeAssignmentType() {return dropDown_staticPracticeValue; }

    @FindBy(id = "qtn-type-true-false-img")
    public WebElement button_trueFalseQuestionType;
    public WebElement getButton_trueFalseQuestion() {return button_trueFalseQuestionType; }

    @FindBy(className = "create-regular-assessment-popup-item")
    public WebElement link_CreateRegularAssessment;
    public WebElement getLink_CreateRegularAssessment() {return link_CreateRegularAssessment; }

    //multi part question section
    @FindBy(className = "cms-multipart-add-question-part")
    public WebElement AddNewQuestionPart;

    @FindBy(className = "cms-multipart-add-stem")
    public WebElement AddQuestionStem;



}
