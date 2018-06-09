package com.snapwiz.selenium.tests.staf.learnon.pageFactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Mukesh on 8/13/15.
 */
public class NewAssignment {
    @FindBy(xpath = "//div[contains(text(),'Use Pre-created Assignment')]")
    WebElement usePreCreatedAssignment;
    public WebElement getUsePreCreatedAssignment(){return usePreCreatedAssignment;}

    @FindBy(xpath = "(//span[text()='New Assignment'])[2]")
    WebElement newAssignment;
    public WebElement getNewAssignment(){return newAssignment;}

    @FindBy(css = "span[title='Find Questions']")
    WebElement findQuestion;
    public WebElement getFindQuestion(){return findQuestion;}

    @FindBy(id = "redactor_link_url_text")
    public WebElement textBox;

    @FindBy(id = "redactor_link_blank")
    public WebElement checkBox;

    @FindBy(xpath = "//a[@href='https://www.google.co.in/']")
    public WebElement google;

    @FindBy(xpath = "//a[@title='Close']")
    public WebElement closeIcon;

    @FindBy(id = "ls-assign-now-assigment-btn")
    public WebElement assignNowButton;

    @FindBy(className = "ir-ls-assign-dialog-gradable-label-check")
    public WebElement gradableCheckBox;

    @FindBy(id = "ls-ins-assignment-desc")
    public WebElement descriptionField;

    @FindBy(id = "ls-ins-your-question-label")
    public WebElement questionLabel;

    @FindBy(css = "div.ls-inst-dashboard-assignment-popup-button.ls--create-custom-assignment-view")
    public WebElement createCustomAssignment;

    @FindBy(className = "ir-ls-assign-dialog-header-wrapper")
    public WebElement assignThis_popUp; //assignthis pop in custom assignmnet

    @FindBy(xpath = "//div[@class='ir-ls-assign-dialog-content-wrapper']/div[4]/div[1]")
    public WebElement assignmentReference_label;

    @FindBy(xpath = "(//div[@class='ir-ls-assign-dialog-assign-to-label ir-ls-assign-dialog-label'])[3]")
    public WebElement assignmentReference_label3; //static


    @FindBy(xpath = "(//div[@class='ir-ls-assign-dialog-assign-to-label ir-ls-assign-dialog-label'])[4]")
    public WebElement assignmentReference_label4; //adaptive

    @FindBy(xpath = "(//div[@class='ir-ls-assign-dialog-assign-to-label ir-ls-assign-dialog-label'])[5]")
    public WebElement assignmentReference_label5; //update custom assignment

    @FindBy(xpath = "//div[@class='ir-ls-assign-dialog-content-wrapper']/div[5]/div[1]")
    public WebElement assignmentReference_label1;


    @FindBy(xpath = "//div[@class='ir-ls-assign-dialog-content-wrapper']/div[6]/div[1]")
    public WebElement assignmentReference_label2;//file based assignment

    @FindBy(css = "div[class='ir-ls-grading-policy-filter-drop-down-wrapper ir-ls-assign-dialog-field scrollbar-wrapper']")
    public WebElement assignmentReference_dropDown;

    @FindBy(xpath = "//div[@class='ir-ls-assign-dialog-content-wrapper']/div[5]//span")
    public WebElement assignmentReference_description;

    @FindBy(xpath = "//div[@class='ir-ls-assign-dialog-content-wrapper']/div[6]//span")
    public WebElement assignmentReference_description1;

    @FindBy(xpath = "//div[@class='ir-ls-assign-dialog-content-wrapper']/div[7]//span")
    public WebElement assignmentReference_description2; // file based assignment

    @FindBy(xpath = "(//div[@class='ir-ls-assign-dialog-assign-to-label ir-ls-assign-dialog-label'])[4]")
    public WebElement assignmentReference_description3; // file based assignment


    @FindBy(xpath = "(//div[@class='ir-ls-assign-dialog-assign-to-label ir-ls-assign-dialog-label'])[5]")
    public WebElement assignmentReference_description4; // file adaptive

    @FindBy(xpath = "(//div[@class='ir-ls-assign-dialog-assign-to-label ir-ls-assign-dialog-label'])[6]")
    public WebElement assignmentReference_description5; // update custom assignment


    @FindBy(xpath = "//a[@title='Select an Assignment Policy']")
    public WebElement assignmentPolicy_dropdown;

    @FindBy(xpath = "//a[@title='Create a new Assignment Policy']")
    public WebElement createAssignmentPolicy;

    @FindBy(css = "div.ls-inst-dashboard-assignment-popup-button.ls--custom-file-assignment-view")
    public WebElement createFileBasedAssignment;

    @FindBy(className="maininput")
    public List<WebElement> classSection;

    @FindBy(xpath ="//ul[@id='share-with_feed']/li/em")
    public WebElement shareWithClass;

    @FindBy(id="due-date")
    public WebElement dueDate;

}
