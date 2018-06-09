package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.managedistrict;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Priyanka on 14-12-2016.
 */
public class ClassEnrollment {

    @FindBy(id = "classenrollment")
    public WebElement classEnrollmentTab;

    @FindBy(xpath = "//span[@title=' Select a column']")
    public WebElement selectAColumn;

    @FindBy(xpath = "//span[@title=' Select a value']")
    public WebElement selectAValue;

    @FindBy(xpath = "//input[@placeholder='Enter text']")
    public WebElement inputText;

    @FindBy(xpath = "//tbody//tr//td[@username]")
    public List<WebElement> userName;

    @FindBy(xpath = "//*[@id='class-enrollment-table']/tbody//ins")
    public List<WebElement> checkBox;

    @FindBy(xpath = "//span[@title='Actions']")
    public List<WebElement> actionDropDown;

    @FindBy(xpath = "//li[text()='Move user(s)']")
    public WebElement moveUser;

    @FindBy(id = "class-code")
    public WebElement classCode;

    @FindBy(id = "class-name")
    public WebElement className;

    @FindBy(id = "school-name")
    public WebElement schoolName;

    @FindBy(id = "teacher-name")
    public WebElement teacherName;

    @FindBy(id = "submit-move-users-form")
    public WebElement move;

    @FindBy(id = "delete-confm-txt")
    public  WebElement moveTextBox;

    @FindBy(xpath = "//span[text()='Yes, Move >']")
    public WebElement yesMove;

    @FindBy(xpath = "//li[text()='Add New User']")
    public WebElement addNewUser;

    @FindBy(id = "user-name-email")
    public WebElement userEmail;

    @FindBy(id = "add-user-name")
    public WebElement user_Name;

    @FindBy(id = "add-user-password")
    public WebElement passWord;

    @FindBy(id = "add-user-retype-password")
    public WebElement confirmPassWord;

    @FindBy(xpath = "//button[text()='Yes, Create >']")
    public WebElement yesCreate;


}
