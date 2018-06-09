package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.districtassessmentresponse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by pragyas on 18-08-2016.
 */
public class DistrictAssessmentResponse {

    @FindBy(xpath = "//div[@class='response-header']/h4")
    public List<WebElement> instructorName;//Instructor name

    @FindBy(xpath = "//div[@class='response-header']/h4/following-sibling::div/descendant::ins")
    public List<WebElement> checkbox_ins;// instructor check box

    @FindBy(css = "button[class='btn gradebook-top-btn inline m-t-xs release-feed']")
    public WebElement releaseGrade;//Release grades button

    @FindBy(css = "button[class^='btn release-feed btn-rounded ']")
    public WebElement releaseGrade_link;


    @FindBy(css = "div[class$='notification-body']")
    public WebElement notificationMsg;//Notification message

    @FindBy(css = "div[class='as-modal-yes-btn release-grades']")
    public WebElement button_yes;//yes button

    @FindBy(className = "toast-message")
    public WebElement notificationMsg_gradeReleased;//Notification message after grade release

    @FindBy(xpath = "//span[text()='In Progress']")
    public WebElement assignmentInProgressStatus;

    @FindBy(css = "#student-response-card")
    public List<WebElement> viewStudentResponse;

    @FindBy(css = ".as-student-response")
    public List<WebElement> studentResponseCard;





}
