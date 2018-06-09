package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports;

import org.openqa.selenium.WebDriver;
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

    @FindBy(css = "g[class='highcharts-series.highcharts-tracker'] > rect")
    public List<WebElement> QuestionBar;

    @FindBy(className = "report-chart-title")
    public WebElement chartTitle;//Performance Summary  page title

    @FindBy(id="performance-chart-label-id")
    public WebElement performanceChart_questionNo;

    @FindBy(css = "a[class='btn btn--primary btn--large btn--continue']")
    public WebElement continue_button;

    @FindBy(id="al-peformance-title-id")
    public  WebElement performanceReportTitle;

    @FindBy(className = "al-performance-chart-title")
    public WebElement title_performanceSummary;

    @FindBy(id="ls-preformance-static-assessment-total-count")
    public WebElement performanceStaticAssessmentTotalCount;

    @FindBy(id="al-performance-chart-label-id")
    public WebElement studentPerformance_questionNo;
}
