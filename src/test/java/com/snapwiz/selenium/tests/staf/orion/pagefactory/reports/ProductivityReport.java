package com.snapwiz.selenium.tests.staf.orion.pagefactory.reports;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by priyanka on 30-11-2015.
 */
public class ProductivityReport {

    @FindBy(xpath = "//a[@title='Productivity Report']")
    public WebElement productivityReport;

    @FindBy(linkText = "Productivity Report")
    public WebElement productivityReportTitle;

    @FindBy(className = "no-data-notification-message")
    public WebElement noDataAvailableMessage;

    @FindBy(css="g.highcharts-markers.highcharts-tracker > path")
    public List<WebElement> coloredMarker;

    @FindBy(id="al-chapter-start-practice")
    public WebElement praticeFromProductivity;


    @FindBy(xpath = "//div[@class='tooltip-label-subsection-wrapper']/span[2]")
    WebElement proficiency;// proficiency tooltip
    public WebElement getProficiency(){return  proficiency;}

    /*Student Side*/
    @FindBy(className = "al-proficiency-percentage")
    WebElement studProfPercentage;// proficiency percentage for student
    public WebElement getStudProfPercentage(){return studProfPercentage;}


    @FindBy(id = "reportTitle")
    public  WebElement productivityReportPageTitle;//in student side

    @FindBy(className = "no-data-notification-message-block")
    public WebElement notificationBlock;

    @FindBy(id = "ir-productivity-report-graph-content")
    public WebElement notification_Block;

    @FindBy(css = "span[class='al-terminal-objective-title  student-report']")
    public List<WebElement> studentName;

    @FindBy(className = "tooltip-label-data")
    public WebElement ToolTip_proficiencyPercentage;

    @FindBy(xpath = "//*[@class='al-student-report-title-section']/span[2]")
    public WebElement report_title;

    @FindBy(id = "ir-productivity-report-title")
    WebElement productivityReportPageTitleText;
    public WebElement getProductivityReportPageTitleText(){return productivityReportPageTitleText;}

}
