package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by pragya on 16-02-2015.
 */
public class Assign {

    @FindBy(xpath = "//input[@id='private']/parent::div")
    WebElement radioButton_private;//Private radio button
    public WebElement getRadioButton_private(){return radioButton_private;}

    @FindBy(xpath = "//input[@id='public']/parent::div")
    WebElement radioButton_public;//Public radio button
    public WebElement getRadioButton_public(){return radioButton_public;}

    @FindBy(xpath = "//input[@id='district']/parent::div")
    WebElement radioButton_district;//District radio button
    public WebElement getRadioButton_district(){return radioButton_district;}

    @FindBy(xpath = "//input[@id='school']/parent::div")
    WebElement radioButton_school;//School radio button
    public WebElement getRadioButton_school(){return radioButton_school;}

    @FindBy(xpath = "//div[@id='assign-button']")
    WebElement button_assign;//Assign button to assign the assessments
    public WebElement getButton_assign(){return button_assign;}

    @FindBy(xpath = "//input[@id='assign-now']/..")
    WebElement radio_button_rightNow ;//Right now radio button
    public WebElement getRadio_button_rightNow(){return radio_button_rightNow;}


    @FindBy(xpath = "//div[starts-with(@class,'share-link-url')]")
    WebElement assessmentShareLink;//Share link for assessment
    public WebElement getAssessmentShareLink(){return assessmentShareLink;}

    @FindBy(xpath = "//div[@class='m-t-xs total-point-wrapper']")
    public WebElement totalPoints;

    @FindBy(xpath = "//input[@id='assign-later']/parent::div")
    public WebElement saveAssessmentAndUseLater;

    @FindBy(css = "div[class^='span-to-input min-ques mq-value']")
    public WebElement minimumNumberOfQuestion;

    @FindBy(css = "a[class='l-none update-mq'] >i")
    public WebElement reduceMinimumNumberOfQuestion;


    @FindBy(xpath = "//button[text()='Apply Criteria']")
    public WebElement buttonApplyCriteria;

    @FindBy(xpath = ".//*[@id='headingTwo']/h4/a")
    public WebElement editMasteryCriteria;

    @FindBy(id = "select-mastery-criteria")
    public WebElement masteryCriteria;// Select Mastery criteria

    @FindBy(xpath = "//div[@class='modal-body']/table/thead/tr/th")
    public List<WebElement> editMasterCriteriaHeader;

    @FindBy(xpath = "//div[@class='modal-body']/table/tbody/tr/td")
    public List<WebElement> editMasterCriteriaBody;

    @FindBy(linkText = "Cancel")
    public WebElement cancel;

    @FindBy(css = ".fa.fa-minus")
    public List<WebElement> decreaseCount;

    @FindBy(css = ".fa.fa-plus")
    public List<WebElement> increaseCount;

    @FindBy(className = "close")
    public WebElement closeEditMasterCriteria;

    @FindBy(css = ".item-text")
    public List<WebElement> textBoxItems_assignTO;//Assign to text box items

    @FindBy(xpath = "//input[@class='maininput']")
    public WebElement textBox_assignTO;//Assign to text box

    @FindBy(id = "select-mastery-criteria")
    public WebElement dropdown_masteryCriteria;//dropdown mastery criteria

    @FindBy(css = "#go-back")
    public WebElement backArrow;//Back arrow to review assessment

    @FindBy(xpath = "//div[@id='assign-assessment-holder']/descendant::div[@id='assignNow']")
    public WebElement confirmationScreen;//Confirmation screen after clicking on redirect

    @FindBy(id = "view-assign-button")
    public WebElement button_viewAssignment;//View assignment button

    @FindBy(css = "input[class^='lsm-createAssignment-input-name']")
    public WebElement assignmentName;//Assignment name

    @FindBy(className = "no-results-message")
    public WebElement assignTo_noResultFound;//No result found message

    @FindBy(xpath = "//ul[@id='share-with_feed']/li")
    public List<WebElement> assignTo_studentName; // Student name dropdown in assign to text box

    @FindBy(className = "closebutton")
    public List<WebElement> assignTo_close;//close button in assign to field to remove studnet"

    @FindBy(id ="view-response-button" )
    public WebElement btnViewResponse;

}





