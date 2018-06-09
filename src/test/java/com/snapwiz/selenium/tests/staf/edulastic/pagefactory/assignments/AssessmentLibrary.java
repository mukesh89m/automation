package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by pragya on 27-05-2015.
 */
public class AssessmentLibrary {

    @FindBys(@FindBy(xpath = "//*[@id='ownrSelectionList']/div/div/label"))
    List<WebElement> list_labels_owner;//List of labels under owner section
    public List<WebElement> getList_labels_owner(){return list_labels_owner;}

    @FindBy(xpath = "//*[contains(text(),'Sharing Level')]")
    WebElement label_owner;//Owner label
    public WebElement getLabel_owner(){return label_owner;}

    @FindBy(id = "select2-gradeSelection-container")
    WebElement selectbox_grade;//Grade select box
    public WebElement getSelectbox_grade(){return selectbox_grade;}

    @FindBy(id = "select2-subjectSelection-container")
    WebElement selectbox_subject;// Subject select box
    public WebElement getSelectbox_subject(){return selectbox_subject;}

    @FindBy(xpath = "//div[@id='avlblStandardsList']/../h4")
    WebElement label_chooseStandard;//Choose standard label
    public WebElement getLabel_chooseStandard(){return label_chooseStandard;}

    @FindBy(xpath = "//div[contains(@class,'font-semi-bold')]")
    List<WebElement> list_assessment;//Assessments
    public List<WebElement> getList_assessment(){return list_assessment;}

    @FindBy(xpath = "//div[@class='col-xs-4 more-btn']/a")
    WebElement more;//More button
    public WebElement getMore(){return more;}

    @FindBy(xpath = "//div[@class='col-xs-4 more-btn']/a")
    List<WebElement> list_more;//List of more button
    public List<WebElement> getList_more(){return list_more;}


    @FindBy(xpath = "//a[starts-with(@class,'as-assignment-customize btn btn-rounded btn-block')]/descendant::span")
    List<WebElement> list_customize;//List of customize link
    public List<WebElement> getList_createNewVersion(){return list_customize;}

    @FindBy(css = "a[class='show-preview sp-link']")
    WebElement preview;//Preview link
    public WebElement getPreview(){return preview;}

    @FindBy(css = "//i[@class='ed-icon icon-preview']")
    List<WebElement> list_preview;//List of preview button
    public List<WebElement> getList_preview(){return list_preview;}

    @FindBy(xpath = "//div[starts-with(@class,'iradio_square-green')]")
    List<WebElement> radioButtons_owner;//Owner radio buttons
    public List<WebElement> getRadioButtons_owner(){return radioButtons_owner;}

    @FindBy(xpath = "//input[@value='0']/parent::div")
    public WebElement sharingLevelPrivate;

    @FindBy(xpath = "//input[@value='1']/parent::div")
    public WebElement sharingLevelSchool;

    @FindBy(xpath = "//input[@value='2']/parent::div")
    public WebElement sharingLevelDistrict;

    @FindBy(xpath = "//input[@value='3']/parent::div")
    public WebElement sharingLevelPublic;

    @FindBy(className = "toast-message")
    public WebElement errorMessage;

    @FindBy(xpath = "//ul[@id='select2-gradeSelection-results']/li")
    public List<WebElement> list_grades;// List of grades

    @FindBy(id = "search-fld")
    public WebElement textBox_search;//Search text box

    @FindBy(css = "ul[class='select2-selection__rendered']>li:nth-of-type(1)>span")
    public WebElement removeGrade;//Remove icon to remove grade








}
