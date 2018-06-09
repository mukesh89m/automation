package com.snapwiz.selenium.tests.staf.orion.pagefactory.Dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by mukesh on 20/10/15.
 */
public class DashBoard {

    @FindBy(xpath = "//img[@title='ORION Dashboard']")
    public WebElement orionDashBoardIcon;

    @FindBy(xpath = "//*[contains(@id,'sbToggle')]")
    public List<WebElement> classSectionDropDown;

    @FindBy(linkText = "Biology's 101")
    WebElement className;
    public WebElement getClassName() {
        return className;
    }

    @FindBy(xpath = "//*[contains(@id,'sbOptions')]")
    public  WebElement classSectionName;

    @FindBy(xpath = "//a[contains(@id,'sbSelector')]")
    public List<WebElement> defaultclassname;

    @FindBy(className = "al-header-logo")
    WebElement headerLogo;
    public WebElement getHeaderLogo() {
        return headerLogo;
    }

    @FindBy(id = "al-preformance-top-7-time-spent-content")
    public WebElement mostTimeSpent;

    @FindBy(className = "al-recommended-focus-area-chart-section")
    public WebElement leastProficientChapter;

    @FindBy(id = "idb-student-activity-filter-type-selected")
    public WebElement lastSevenDaysActivity;

    @FindBy(xpath = "//div[@class='idb-student-activity-data-card']")
    public List<WebElement> tileDashBoard;

    @FindBy(css = "span[class='idb-student-activity-time-line idb-student-activity-filter']")
    public List<WebElement> lastFiveWeeksActivity;

    @FindBy(className = "al-proficiency-percentage")
    public List<WebElement> proficiencyPercentage;

    @FindBy(className = "idb-class-performance-all")
    public WebElement allLink;

    @FindBy(className = "idb-class-performance-weakest-7")
    public WebElement weakestSevenLink;

    @FindBy(css = "tspan[x='3']")
    public  List<WebElement> courseProficiencys; //Course Proficiency in student side

    @FindBy(className = "al-dashboard-header")
    public WebElement buildYourProficiency;

    @FindBy(xpath = "//img[@title='Begin Diagnostic']")
    public WebElement beginDiagnostic_Button;

    @FindBy(className = "idb-student-activity-data-card")
    public  List<WebElement> studentActivityCardList;

    @FindBy(id = "idb-student-activity-filter-type-selected")
    public  List<WebElement> last7DaysLinkList;

    @FindBy(css = "span[buckettype = 'month']")
    public  WebElement link_last5Weeks;

    @FindBy(css = "span[buckettype = 'all']")
    public  WebElement link_all;

    @FindBy(id = "instructor-discussion-content-text")
    public  WebElement instructorDiscussionText;

    @FindBy(className = "idb-chapter-performance-content-body-first")
    public  WebElement label_chapters;

    @FindBy(className = "idb-chapter-performance-content-body-second")
    public  WebElement label_proficiency;

    @FindBy(className = "idb-chapter-performance-content-body-third")
    public  WebElement label_performance;

    @FindBy(xpath = "//ul[@class='confidence-level']/li/a")
    public List<WebElement> confidenceLevel;

    @FindBy(className = "al-continue-to-diagnostic")
    public WebElement continue_Button;

    @FindBy(className = "al-notification-message-body")
    public WebElement error_Message;

    @FindBy(xpath = "//img[@title='Continue Diagnostic']")
    public WebElement continueDiagnostic;

    @FindBy(xpath = "//a[@title='Practice']")
    public List<WebElement> practice_Button;

    @FindBy(xpath = "//div[@class='al-content-body-first big-font']")
    public List<WebElement> content;

    @FindBy(xpath = "//a[@title='Study']")
    public List<WebElement> study_Button;


    @FindBy(className = "idb-terminal-objective-title")
    public  WebElement performanceChapterName;

    @FindBy(className = "al-proficiency-percentage")
    public  WebElement proficiencyPercentageValue;

    @FindBy(className = "idb-preformance-text")
    public  WebElement performanceValue;

    @FindBy(id = "idb-class-performance-seleted")
    public  List<WebElement> link_classPerformanceWeakest7;

    @FindBy(className = "idb-class-performance-all")
    public  WebElement link_classPerformanceAll;

    @FindBy(id = "idb-switch-role-to-student")
    public  WebElement link_viewAsStudent;

    @FindBy(xpath = "//div[@class='al-welcome-slider']//img")
    public  WebElement img_orionWelcomePage;




}
