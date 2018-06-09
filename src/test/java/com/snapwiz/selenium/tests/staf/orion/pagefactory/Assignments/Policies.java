package com.snapwiz.selenium.tests.staf.orion.pagefactory.Assignments;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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


}
