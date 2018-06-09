package com.snapwiz.selenium.tests.staf.orion.pagefactory.reports;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by priyanka on 30-11-2015.
 */
public class MetaCognitiveReport {

    @FindBy(xpath = "//a[@title='Metacognitive Report']")
    public WebElement metaCognitiveReport;

    @FindBy(linkText = "Metacognitive Report")
    public WebElement metacognitiveReportTitle;

    @FindBy(className = "no-data-notification-message")
    public WebElement noDataAvailableMessage;

    @FindBy(css="g.highcharts-markers.highcharts-tracker > path")
    public WebElement coloredMarker;

    @FindBy(id="al-chapter-start-practice")
    public WebElement praticeFromMetacognitive;

    @FindBy(xpath = "//*[@class='al-student-report-title-section']/div[1]")
    public WebElement report_title;

    @FindBy(xpath = "//div[@class='tooltip-label-subsection-wrapper']/span[1]")
    WebElement proficiency;// proficiency tooltip
    public WebElement getProficiency(){return  proficiency;}

    @FindBy(xpath = "//*[@id='al-report-content-body-row']")
    public WebElement reportContent;

    @FindBy(id="chapter-confidence-level-section")
    public WebElement confidence_report;

    @FindBy(id = "ir-metacognitive-report-title")
    WebElement metacognitiveReportPage;
    public WebElement getMetacognitiveReportPage(){return metacognitiveReportPage;}

}
