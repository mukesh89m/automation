package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.reports;

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

}
