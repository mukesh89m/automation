package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Resources;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by priyanka on 1/7/2015.
 */
public class MyResources {
    @FindBy(className = "tab_title")
    WebElement myResourceTitle;
    public WebElement getMyResourceTitle(){return myResourceTitle; }

    @FindBy(id="upload-resourse-button")
    public WebElement uploadResource_link;

    @FindBy(className = "ls-ins-uploadResource-dialogBox")
    public WebElement uploadFile_popup;

    @FindBy(className = "ins-useResource-grading-policy-txt")
    public WebElement assignmentReference_text;

    @FindBy(className = "ls-instructor-only-grading-policy-check")
    public WebElement assignmentReference_checkbok;

    @FindBy(className = "ins-resourceUpload-help-img-grading-policy")
    public WebElement assignmentReference_help;

    @FindBy(id="uploadFile")
    public WebElement uploadFile_link;

    @FindBy(className = "ins-dialogBox-Save")
    public WebElement save_button;

    @FindBy(className = "help-data-container")
    public WebElement help_text;

    @FindBy(className = "ls-notification-text-span")
    public WebElement notification_msg;

    @FindBy(className = "ls-grading-policy-icon")
    public WebElement assignmentReference_icon;

    @FindBy(xpath = "//div[@class='ls-assessment-item-sectn-title']/div/div/following-sibling::span")
    public WebElement assignmentReference_position;

    @FindBy(css = "div[class = 'post-comment resource-post-comment']")
    public WebElement button_post;

    @FindBy(css = "div[class = 'ls-right-section-status ls-right-section-resource']")
    public WebElement assignmentName;
}
