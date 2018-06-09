package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by pragya on 15-07-2015.
 */
public class StudentDashboard {

    @FindBy(className = "getStarted")
    WebElement getStarted; // Get started
    public WebElement getGetStarted(){return getStarted;}

    @FindBy(className = "toast-message")
    public WebElement notificationMessage;

    @FindBy(className = "caret")
    public WebElement dropdown_userName;//User name dropdown

    @FindBy(css = "span[class='as-manage-blue-btn as-add-newClass-btn join-class-button text-right p-r-md']")
    public WebElement button_joinAClass;//Join a class button

    @FindBy(xpath = "//div[@class='pull-left']")
    public WebElement heading_dashboard;//Dashboard

    @FindBy(css = "h2[class='dashboard-header-label']")
    public WebElement subHeading_MyClasses;//Sub heading as My Classes

    @FindBy(xpath = "//span[contains(@class,'as-manage-blue-btn as-launch-class-btn pull-right')]")
    public List<WebElement> button_launchClass; //Launch button

    @FindBy(className = "navbar-username")
    public WebElement userName; //User name

    @FindBy(xpath = "//div[@class='manageClass-codeSection']/span")
    public List<WebElement> name_classes;//Classes name

    @FindBy(xpath = "//div[@class='manageClass-codeSection f13']/span[1]")
    public List<WebElement> list_classCodes; // Class codes

    @FindBy(css = ".as-title.twoline-ellipsis.m-b-xs")
    public List<WebElement> list_assignmentsName; //List of assignments name

    @FindBy(xpath = "//div[@class='manageClass-codeSection f13']/span[2]")
    public List<WebElement> list_instructorName; // list of instructor name

    @FindBy(xpath = "//div[@class='col-xs-5']")
    public List<WebElement> activeAssignment;//Active Assignment

    @FindBy(xpath = "//span[starts-with(@class,'status-label')]")
    public List<WebElement> status_assignments; //Assignment status

    @FindBy(xpath = "//div[@class='col-sm-8 top-space']")
    public List<WebElement> dueDate_assignment;//List of assignment due date

    @FindBy(css = ".as-grade.m-b-xs")
    public List<WebElement> tag_gradable;// GRADABLE tag


}
