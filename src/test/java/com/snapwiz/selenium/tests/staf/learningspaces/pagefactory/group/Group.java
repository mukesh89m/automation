package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.group;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by priyanka on 13-05-2016.
 */
public class Group {

    @FindBy(xpath = "//h1[@id='customize-study-group-text']/span/span")
    public WebElement groupHeader;

    @FindBy(xpath = "//span[@class='text-inside-card-group']")
    public WebElement createNewGroup;

    @FindBy(id = "selected-study-group-container")
    public WebElement groupName;

    @FindBy(id="enter-study-group-name")
    public WebElement groupName_textBox;

    @FindBy(id="study-group-create-btn-done")
    public WebElement done_button;

    @FindBy(css="div[class='ls-notification-text-span']")
    public WebElement notificationMessage;

    public By group_Name=By.id("group-text-name-ellipsis");

    @FindBy(xpath = "//tr[@class='student-details-row odd'][1]//td[2]")
    public WebElement studentName;

    @FindBy(className = "student-checkbox-select")
    public List<WebElement> studentCheckBox;

    @FindBy(className = "thumb")
    public List<WebElement> tinyScroll;

    @FindBy(id = "cancel-create-group")
    public  WebElement cancelGroup;

    @FindBy(className = "viewport")
    public List<WebElement> groupBox;

    @FindBy(className = "group-icon")
    public WebElement groupIcon;

    @FindBy(id = "total-count")
    public WebElement studentCount;

    @FindBy(className = "vertical-dots-icon")
    public WebElement dotIcon;

    @FindBy(className = "edit-group-name")
    public WebElement editGroupName;

    @FindBy(className = "delete-group")
    public WebElement deleteGroup;

    @FindBy(xpath = "//a[@title='Close']")
    public WebElement closeIcon;

    @FindBy(css="div[class='ls-notification-text-span']")
    public List<WebElement> notification_Message;

    @FindBy(css = "div[class='ins-student-field-cancel showCancel']")
    public List<WebElement> crossIconOnStudent;

    @FindBy(xpath = "//div[starts-with(@class,'ins-created-group-container')]")
    public List<WebElement> group_container;

    @FindBy(xpath = "//div[@class='ins-student-field-cancel showCancel']/preceding-sibling::div")
    public List<WebElement> selectedStudentName;


    @FindBy(xpath = "//th[@id='class-list-full-name-header']")
    public WebElement name_Header;

    @FindBy(xpath = "//td[contains(@class,'student-name')]")
    public List<WebElement> student_Name;


    @FindBy(id = "group-text-name-edit")
    public WebElement editTextBox;


    @FindBy(className = "delete-entry-confirm")
    public WebElement delete;


    @FindBy(className = "delete-card")
    public  WebElement yesOnDelete;

    @FindBy(xpath = "//div[contains(@class,'ins-created-group-container highlight')]//div[@class='vertical-dots-icon']")
    public WebElement dotIconHighlightGroup;

    @FindBy(className = "delete-entire-group-inner-wrapper")
    public WebElement deleteMessage;

    @FindBy(className = "case-sensitive-text")
    public  WebElement noCaseSensitive;

    @FindBy(css = "span[class='cancel-delete-card close-btn-action']")
    public WebElement noOnDeletePopUp;

    @FindBy(css = "span[class='cancel-to-delete-card close-btn-action']")
    public WebElement closeIconOnDelete;

    @FindBy(className = "delete-entire-group-inner-wrapper")
    public List<WebElement> deletePopUp;

    @FindBy(css = "input[class='delete-entry-confirm red-border-warning']")
    public WebElement redColourWarning;

    @FindBy(css = "label[class='student-checkbox-select checked']")
    public List<WebElement> studentCheckedBox;

    @FindBy(id = "default-text-created-card")
    public WebElement defaultTextOnGroupCard;

    @FindBy(css = "span[data-localize='score']")
    public WebElement scoreSorting_tooltip;

    @FindBy(css = "td[class='student-overall-score']")
    public List<WebElement> group_score;

    @FindBy(css = "td[class='student-overall-score sorting_1']")
    public List<WebElement> groupScore_ascendingOrder;



}
