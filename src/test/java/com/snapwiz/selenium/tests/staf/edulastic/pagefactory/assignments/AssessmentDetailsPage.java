package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by pragya on 21-07-2015.
 */
public class AssessmentDetailsPage {

    @FindBy(css = "div.as-card-header")
    WebElement assessment_name;//Assessment name
    public WebElement getAssessment_name(){return assessment_name;}

    @FindBy(css = "div.lsm-createAssignment-Question")
    public List<WebElement> questionName;

    @FindBy(css = ".as-assignment-edit.btn.btn-rounded.btn-block.btn-invisible")
    public WebElement editAssesssment;

    @FindBy(xpath = "//*[starts-with(@class,'ed-icon icon-group')]/following-sibling::span")
    public WebElement usageCountOfAssignment;

    @FindBy(id="js-assessment-ratings")
    public WebElement tabNotRated;

    @FindBy(xpath = "//span[@data-toggle='modal']")
    public WebElement beFirstToRateThisAssessment;

    @FindBy(css = "div.star-ratings  >input")
    public List<WebElement> rating;

    @FindBy(xpath = "//button[text()='Save']")
    public WebElement buttonSave;

    @FindBy(css = "div[class='unrated unrated-message']>div:nth-of-type(1)")
    public WebElement message_notRated;//Not rated message

    @FindBy(css = "div #avg-quality >i")
    public List<WebElement> avgQualityRating;

    @FindBy(css = "div #avg-accuracy >i")
    public List<WebElement> avgAccuracyRating;

    @FindBy(css = "div #avg-alignment >i")
    public List<WebElement> avgStandardsAlignmentRating;

    @FindBy(css = "div #avg-creativity >i")
    public List<WebElement> avgCreativityRating;

    @FindBy(css = "div.as-questions-block")
    public List<WebElement> numberOfQuestion;


    @FindBy(xpath = "//i[@class='ed-icon icon-edit-assesment']/following-sibling::span")
    public WebElement editAssessment;

    @FindBy(id = "show-preview")
    public WebElement preview;//Preview button

    @FindBy(css = "div.as-assignment-questions-modal-msg-wrapper")
    public List<WebElement> confirmation_popUp;//Confirmation pop up

    @FindBy(xpath = "//div[@class='as-modal-yes-btn yes-delete ']/span")
    public WebElement YesButton_confirmationPopUp;//Confirmation pop up yes button

    @FindBy(css = "span[class='as-modal-no-btn no-delete']")
    public WebElement NoButton_confirmationPopUp;//Confirmation pop up No button

    @FindBy(css = "div.share-link-url")
    public List<WebElement> deepLinkUrl;//Deep link url

    @FindBy(id = "myTabContent-details")
    public WebElement assessmentDetails;//Assessment details

    @FindBy(className = "as-questionDetails-clickArrow")
    public List<WebElement> clickArrowIcon;



}
