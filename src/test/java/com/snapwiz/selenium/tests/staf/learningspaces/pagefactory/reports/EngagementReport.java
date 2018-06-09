package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by root on 12/31/14.
 */
public class EngagementReport {
    @FindBy(css="g[class='highcharts-data-labels highcharts-tracker']")
    WebElement questionPerSummary; //Question summary page percentage
    public WebElement getQuestionPerSummary(){return questionPerSummary;}

    @FindBy(xpath = "//div[@class='students-report-content-body-six']/span")
    WebElement studTotalGrade; //student total grade
    public WebElement getStudTotalGrade(){return studTotalGrade;}

    @FindBy(xpath = "//div[@class='students-report-content-body-six']/span")
    public List<WebElement> studTotalGrades; //student total grade

    @FindBy(xpath = "(//span[@class='ls-ins-gradebook-overall-score'])[2]")
    WebElement overallScore; //grade book overall score
    public WebElement getOverallScore(){return overallScore;}

    @FindBy(xpath = ".//*[@class='row-assigned-status']/div[1]/span[1]")
    WebElement notStartedCount; //not started count
    public WebElement getNotStartedCount(){return notStartedCount;}

    @FindBy(className = "student-engagement-report-text")
    WebElement engagementReportTitle;
    public WebElement getEngagementReportTitle(){return engagementReportTitle;}

    @FindBy(css = "div.students-performance-report-content-row.student-performance-content-odd-row > div.students-report-content-body-third > div.row-non-assigned-status > div.fLeft")
    WebElement readingCount;
    public WebElement getReadingCount(){return readingCount;}

    @FindBy(xpath = "(//div[@class='students-report-content-body-five']/span)[3]")
    WebElement questionAttempt;
    public WebElement getQuestionAttempt(){return  questionAttempt;}

    @FindBy(xpath = "//div[@class='students-report-content-body-five']/span")
    WebElement questionAttempted;
    public WebElement getQuestionAttempted(){return questionAttempted;}

    @FindBy(className = "er-questions-count")
    public WebElement questionPerformance_Count;

    @FindBy(xpath = "(//div[@class='row-non-assigned-status']/div[2]/span)[2]")
    public WebElement assessment;

    @FindBy(className = "students-report-name-title")
    public List<WebElement> studentName;

    @FindBy(xpath = ".//*[@id='idb-body-content']/div/div/div[5]/div[2]/div[2]/div[2]/div/div[1]")
    public WebElement notStarted_label;

    @FindBy(css = "div.students-performance-report-content-row.student-performance-content-odd-row > div.students-report-content-body-third > div.row-non-assigned-status > div.fLeft")
    public List<WebElement> assessmentBox;

    @FindBy(css = "tspan")
    public  List<WebElement> questionProficiency; //Course Proficiency in student side

}
