package com.snapwiz.selenium.tests.staf.orion.pagefactory.reports;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Mukesh on 3/4/15.
 */
public class PerformanceSummaryReport {
    @FindBy(className = "performance-summary-titleText")
    public WebElement performanceSummaryTitle;

    @FindBy(css="g[class='highcharts-series highcharts-tracker']>path")
    public WebElement report;

    @FindBy(css="g[class='highcharts-series highcharts-tracker']>path")
    public List<WebElement> list_report;

    @FindBy(css = "g.highcharts-series.highcharts-tracker > rect")
    public List<WebElement> QuestionBar;

    @FindBy(className = "report-chart-title")
    public WebElement chartTitle;//Performance Summary  page title

    @FindBy(id="performance-chart-label-id")
    public WebElement performanceChart_questionNo;

    @FindBy(css = "a[class='btn btn--primary btn--large btn--continue']")
    public WebElement continue_button;

    @FindBy(className = "al-performance-chart-title")
    public WebElement title_performanceSummary;

    @FindBy(id="ls-preformance-static-assessment-total-count")
    public WebElement performanceStaticAssessmentTotalCount;

    @FindBy(id = "al-peformance-title-id")
    public WebElement performance_title;

    @FindBy(id="idb-prod-report-performance-title")
    public List<WebElement> performanceEntry_classPerformance;

    @FindBy(id="idb-prod-report-header-performance-title")
    public WebElement performanceEntry_objectivePerformance;


    @FindBy(className = "al-proficiency-percentage")
    public List<WebElement> proficiency_percentage;

    @FindBy(className = "ir-course-performance-heading")
    public WebElement classPerformanceByStudent_label;

    @FindBy(id="ir-performance-report-title")
    public WebElement classPerformanceByChapter_label;

    @FindBy(id="ir-performance-title")
    public WebElement classPerformance_subHeading;

    @FindBy(className = "coursePerformanceByChapters-xAxisLabel")
    public WebElement chapNumber; //chapter number below the bar chart

    @FindBy(className = "chapterPerformanceByObjectives-xAxisLabel")
    public WebElement tloNumber; //tlo number below the bar chart

    @FindBy(className = "chapterPerformanceByQuestions-xAxisLabel")
    public WebElement questionNumber; //question number below the bar chart
}
