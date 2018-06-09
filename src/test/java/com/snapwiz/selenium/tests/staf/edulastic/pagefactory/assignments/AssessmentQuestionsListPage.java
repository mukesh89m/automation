package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by root on 12/19/14.
 */
public class AssessmentQuestionsListPage {
    @FindBy(xpath = "//span[contains(@class,'as-terminal-objectives')]")// Standard TLO Text
    WebElement labelValue_StandardTlo;//
    public WebElement getLabelValue_StandardTlo() {
        return labelValue_StandardTlo;
    }

    @FindBy(className = "as-questionDetails-clickArrow") //xpath for arrow link
    WebElement arrowLink;
    public WebElement getArrowLink()
    {
        return  arrowLink;
    }

    @FindBy(className = "question-type-display-wrapper") //Question details
            WebElement questionDetailsDisplay;
    public WebElement getQuestionDetailsDisplay() {
        return questionDetailsDisplay;
    }

    @FindBy(xpath = "//div[starts-with(@class,'question-type-display-wrapper')]/span[1]")
    WebElement questionType;//Question type
    public WebElement getQuestionType(){
        return questionType;
    }


    @FindBy(id = "assessments-save-later-button")
    WebElement button_saveForLater;// 'Save for later' button
    public WebElement getButton_saveForLater() {
        return button_saveForLater;
    }

    @FindBy(xpath = "//div[@class='assessment-name ellipsis']")
    WebElement assessmentName;// Assessment Name
    public WebElement getAssessmentName() {return assessmentName;}

    @FindBy(xpath = "//div[contains(@class,'lsm-createAssignment-Question')]/div[2]")
    WebElement createAssignmentQuestionName;//Question Text in the Questions List Page
    public WebElement getCreateAssignmentQuestionName() {
        return createAssignmentQuestionName;
    }


    @FindBy(xpath = "//span[text() = 'ID:']//following-sibling::span[@class='as-questionType']")
    WebElement labelValue_QuestionID;// Question 'ID' to get the ID of a Question
    public WebElement getLabelValue_QuestionID() {
        return labelValue_QuestionID;
    }

    @FindBy(xpath = "//span[text() = 'OWNER:']/following-sibling::span[@class='as-questionType']")
    WebElement labelValue_Ownwer;// Value for Owner 'You'
    public WebElement getLabelValue_Ownwer() {
        return labelValue_Ownwer;
    }

    @FindBy(id="assessments-back-button")
    WebElement button_addMore;//Add more button
    public WebElement getButton_addMore(){return button_addMore;}

    @FindBys({@FindBy(css = "span[class^='ed-icon icon-dotted-icon']")})
    List<WebElement> list_dragQuestion;//List of drag area
    public List<WebElement> getList_dragQuestion(){return list_dragQuestion;}

    @FindBy(css = "div[class^='question-display-label']+div")
    List<WebElement> list_questionBody;//List of question body
    public List<WebElement> getList_questionBody(){return list_questionBody;}

    @FindBy(id="assessments-use-button")
    WebElement button_next;//Next button
    public WebElement getButton_next(){return button_next;}

    @FindBy(css = "input[class^='as-questionPoints']")
    WebElement point;//Point
    public WebElement getPoint(){return point;}

    @FindBy(css = "label[class^='ed-icon icon-close-icon']")
    public List<WebElement> removeQuestion;//Cross icon to remove question


}
