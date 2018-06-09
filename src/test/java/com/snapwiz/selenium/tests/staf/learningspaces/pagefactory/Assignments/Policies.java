package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by priyanka on 1/7/2015.
 */
public class Policies {
    @FindBy(className = "tab_title")
    WebElement assignmentPolicies;
    public WebElement getAssignmentPolicies(){return assignmentPolicies; }

    @FindBy(className = "ls-save-policy-btn")
    WebElement assignmentPolicySaveButton;
    public WebElement getAssignmentPolicySaveButton(){return assignmentPolicySaveButton; }

    @FindBy(id="newAssignmentPolicy-link")
    public WebElement policy_link;

    @FindBy(css = "a[class='copy-policy']")
    public  WebElement copyPolicy_link;

    @FindBy(css = "a[class='update-policy']")
    public  WebElement updatePolicy_link;

    @FindBy(css = "a[class='delete-policy']")
    public  WebElement deletePolicy_link;

    @FindBy(css = "a[class='view-policy']")
    public  WebElement viewPolicy_link;

    @FindBy(xpath = "//a[text()='None']")
    public WebElement none;

    @FindBy(className = "policy-msg")
    public WebElement policy_Message;

    @FindBy(className = "ls-save-policy-btn")
    public WebElement save_button;

    @FindBy(css = "div[class='btn btn--primary continue-assignment-dialog-btn']")
    public WebElement continue_button;

    @FindBy(className = "gradeReleaseOptions")
    public List<WebElement> gradeReleaseOptions;

    @FindBy(className = "assignment-policy-heading")
    public List<WebElement> policyName_assignmentPolicies;

    @FindBy(className = "immediateFeedback")
    public List<WebElement> immediateFeedback;

    @FindBy(xpath = "//input[starts-with(@name,'orderingcopy-tabId')]")
    public List<WebElement> order_option;

    @FindBy(xpath = "//*[starts-with(@name,'allowCollaborationview-tabId')]")
    public List<WebElement> viewAllowCollaboration;

    @FindBy(xpath = "//*[starts-with(@name,'allowCollaborationupdate-tabId')]")
    public List<WebElement> updateAllowCollaboration;

    @FindBy(xpath = "//*[starts-with(@name,'allowCollaborationcopy-tabId')]")
    public List<WebElement> allowCollaboration;

    @FindBy(id="ls-ins-assignment-policy-name")
    public WebElement policyName;

    @FindBy(xpath = "//div[@class='policy-notification-text-span']/a")
    public List<WebElement> policyNotification;

    @FindBy(css="input[class='timedAssignment'][value='true']")
    public WebElement timeLimit_enable;

    @FindBy(css="input[class='timedAssignment'][value='false']")
    public WebElement timeLimit_disable;

    @FindBy(css = "#timedassignmentduration")
    public WebElement timeDuration_textBox;

    @FindBy(css = "div[class='timed-assignment ls-assignment-policy-row']>div")
    public WebElement timeLimit_policyPage;

    @FindBy(css = "#score")
    public WebElement score_textBox;

    @FindBy(xpath = "(//div[@class='ls-assignment-policy-question'])[last()]")
    public WebElement timeLimit;

    @FindBy(className = "timedAssignment")
    public List<WebElement> radioButton;

    @FindBy(id = "timed-assignment-icon")
    public WebElement helpIcon_Time;

    @FindBy(className = "policy-help-data-container")
    public WebElement helpMessage;

    @FindBy(id = "timedassignmentduration")
    public WebElement minuteTextBox;

    @FindBy(className = "policy-after-attempt-number")
    public  List<WebElement> minuteText;

    @FindBy(id = "timed-assignment-policy-duration-error")
    public WebElement durationError;

    @FindBy(xpath = "//a[@title='Select an Assignment Policy']")
    public WebElement selectPolicy;

    @FindBy(xpath = "//a[@rel='addNewAssignmentPolicy']")
    public WebElement addNewPolicy;

    @FindBy(css = "i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon")
    public WebElement editIcon_PolicyName;

    @FindBy(xpath = "//div[@class='ls-save-policy-btn']")
    public List<WebElement> useThisPolicy;



}