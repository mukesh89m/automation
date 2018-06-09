package com.snapwiz.selenium.tests.staf.orion.pagefactory.reports;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by durgapathi on 29/10/15.
 */
public class PerformanceReportInstructor {
    @FindBy(id = "ir-performance-report-by-chapters")
    public WebElement performanceReportByChapters;

    @FindBy(className = "al-notification-message-body")
    public WebElement notification;// notification

    @FindBy(className = "al-performance-chart-title")
        public WebElement label_chapterPerformanceSummary;//

    @FindBy(className = "al-notification-message-wrapper")
    public WebElement notificationPopUp;// notification POpUp

    @FindBy(className = "al-notification-message-header-text")
    public WebElement labelInformation;// labelInformation

    @FindBy(className = "al-notification-message-header-icon")
    public WebElement iconI;// iconI

    @FindBy(id = "close-al-notification-dialog")
    public WebElement closeIcon;// Close Icon

    @FindBy(xpath = ".//*[@id='al-notification-dialog']//img")
    public WebElement startIcon;// Star Icon in PopUp

    @FindBy(xpath = "(.//*[@id='al-notification-dialog']//div[@class='al-reports-know-more'])[1]")
    public WebElement helpLink;// Help Link In Popup

    @FindBy(id="al-performance-chart-label-id")
    public WebElement performanceChartLabel;

    @FindBy(css = "div[class='al-performance-chart-title']>span")
    public WebElement performanceSummaryTitle;

    @FindBy(xpath = "//div[@class='highcharts-container']")
    public WebElement highchartContainer;

    @FindBy(css = "div[class='question-card-question-no']")
    public List<WebElement> questionCards;

    @FindBy(xpath = "//div[@class='question-card-question-no']")
    public List<WebElement> questionCardContent;

    @FindBy(className = "al-diag-test-question-raw-content")
    public WebElement questionName;

    @FindBy(xpath = "//img[@alt='Navigates to ORION Dashboard']")
    public WebElement dashBoard_icon;

    @FindBy(xpath = "//a[@title='View class reports']")
    public WebElement viewClassReport;

    @FindBy(xpath = "//a[@title='Performance Report']")
    public WebElement performanceReport;

    @FindBy(xpath = "//img[@title='Reset Proficiency']")
    public WebElement resetProficiency_button;

    @FindBy(id = "idb-reset-proficiency-report-button")
    public WebElement resetProficiency_enable;

    @FindBy(id = "ir-performance-checkbox-select-all")
    public List<WebElement> checkBoxStudent;

    @FindBy(xpath= "//div[@class='instructor-reset-proficiency-header-label']")
    public WebElement resetProficiencyHeader;

    @FindBy(xpath= "//div[@class='Proficiency-message-indication']")
    public WebElement studentProficiencyReset;

    @FindBy(xpath= "//div[@class='student-name']")
    public List<WebElement> studentName;

    @FindBy(className= "proficiency-information-message")
    public WebElement proficiencyInformationMessage;

    @FindBy(id = "cancel-reset-proficiency")
    public WebElement cancelLink;

    @FindBy(id = "reset-proficiency-btn")
    public WebElement resetLink;

    @FindBy(className = "notification-text")
    public WebElement notificationMessage;

    @FindBy(css = "div[class='notification-message-yes-section notification-message-link']")
    public WebElement yesLink;

    @FindBy(css = "div[class='notification-message-no-  section notification-message-link']")
    public WebElement noLink;

    @FindBy(xpath = "//span[contains(@class,'al-terminal-objective-title')]")
    public List<WebElement> student_Name;

    @FindBy(xpath = "//div[@class='al-notification-message-body']")
    public WebElement notificationStudent;

    @FindBy(xpath = "//img[@title='Begin Diagnostic']")
    public WebElement beginDiagnostic;

    @FindBy(className = "coursePerformanceByChapters-xAxisLabel")
    public WebElement contributedChapterLink;

    @FindBy(xpath = "//div[@class='ir-performance-chart-title']")
    public WebElement reportTitle;

    @FindBy(id = "ir-performance-report-title")
    public WebElement classPerformanceByChaptersLabel;

    @FindBy(id = "ir-performance-report-course-back-arrow")
    public WebElement backArrow;

    @FindBy(id = "notification-performance-report-view")
    public WebElement notificationPerformanceReportView;


    @FindBy(className = "al-next-practice-question")
    public WebElement NextPracticeQuestion;


    @FindBy(xpath = "//div[contains(@class,'report-sidebar-question-card-sectn')]")
    public List<WebElement> questionCard;

    @FindBy(xpath = "//div[@class='al-performance-chart-title']")
    public WebElement coursePerformanceSummaryTitle;


}
