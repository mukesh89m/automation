package com.snapwiz.selenium.tests.staf.orion.pagefactory.CMS;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by root on 18/12/14.
 */
public class CourseOutline {

    @FindBy(xpath = "//img[@title = 'Create Practice']")
    public WebElement createPracticeLink;
    public WebElement get_CreatePracticeLink() {return createPracticeLink; }


    @FindBy(xpath = "//a[@title='Course Details']")
    public WebElement courseDetails;
    public WebElement getCourseDetails() {return courseDetails; }

    @FindBy(linkText = "Course Settings")
    public WebElement courseAssignmentPolicyLink;
    public WebElement getCourseAssignmentPolicyLink() {return courseAssignmentPolicyLink; }

    @FindBy(linkText = "Course Assignment Policy")
    public WebElement courseAssignmentPolicyLink_LS;

    @FindBy(id = "setting-drop-down")
    public WebElement settingIcon;//setting icon
    public WebElement getSettingIcon() {return settingIcon; }

    @FindBy(id = "dialog-close")
    public WebElement closePopUp;//click on close icon of pop-up
    public WebElement getClosePopUp() {return closePopUp; }

    @FindBy(className = "course-assignment-policy-header-text")
    public WebElement courseAssignmentPolicyHeader;//course Assignment Policy header
    public WebElement getCourseAssignmentPolicyHeader() {return courseAssignmentPolicyHeader; }

    @FindBy(id = "helpclicker")
    public WebElement helpIcon;//course Assignment Policy help icon
    public WebElement getHelpIcon() {return helpIcon; }

    @FindBy(className = "help-data-policy-header")
    public WebElement helpText;//course Assignment Policy help text
    public WebElement getHelpText() {return helpText; }

    @FindBy(xpath="//li[contains(text(),'Course Outline')]")
    public WebElement courseOutline;   //Course outline tab

    @FindBy(xpath="//img[@title='Edit']")
    public WebElement editButtonAtChapter;//Edit Button when user mouse hover to the Chapter

    @FindBy(xpath = "//div[@class='cms-course-tree-edit-publish-content-wrapper cms-course-tree-edit-line-wrapper']/label")
    public WebElement checkBoxToPublishChapter;  //ChaeckBox to publish chapter

    @FindBy(xpath= "//span[contains(@id,'cms-course-tree-edit-save-btn')]")
    public WebElement saveButton;

    @FindBy(xpath= "//span[text()='Yes, Save my Changes']")
    public WebElement saveMyChanges;

   @FindBy(css = "span[title='Add Learning Objective']")
    public WebElement addLearningObjective;

    @FindBy(xpath = "//div[@class='course-assignment-policy-header-text']/../div[2]")
    public WebElement helpIcon_position;

    @FindBy(xpath = "//div[@class='course-assignment-policy-header-text']/../div[2]")
    public WebElement helpIcon_positionAfterPolicy;

    @FindBy(className = "course-assignment-policy-body-text")
    public List<WebElement> gradingPolicy_Text; //Language grading policy

    @FindBy(id="help")
    public WebElement helpIcon_afterPolicy;// help icon after Language grading policy

    @FindBy(css = "div[class='help-data-policy show-help-msg']")
    public WebElement helpText_afterPolicy;

    @FindBy(xpath = "//input[@value='enable']/following-sibling::span[text()='Enable']")
    public WebElement enable_radioButton_Text;

    @FindBy(xpath = "//input[@value='disable']/following-sibling::span[text()='Disable']")
    public List<WebElement> disable_radioButton_Text;

    @FindBy(id="cancel-button")
    public WebElement cancel_button;

    @FindBy(id="course-assignment-policy-save")
    public WebElement save_button;

    @FindBy(css = "input[class='courseAssignmentPolicy-radio-btm enable']")
    public WebElement enable_radioButton;

    @FindBy(css = "input[class='courseAssignmentPolicy-radio-btm disable']")
    public WebElement disable_radioButton;

    @FindBy(xpath = " //input[@checked='checked']")
    public List<WebElement> checkedRadioButton;

    @FindBy(id="palette-help")
    public WebElement helpIcon_LanguagePalette;// help icon after Language grading policy

    @FindBy(css = "div[class='help-data-policy-palette show-helppalette-msg']")
    public WebElement helpText_LanguagePalette;

    @FindBy(css = "input[class='courseLanguagePalettePolicy-radio-btm enable']")
    public WebElement enable_radioButtonForLanguagePalette;
}
