package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Mukesh on 12/31/14.
 */
public class ProductivityReport {

    /*Instructor side*/
    @FindBy(className = "al-proficiency-percentage")
    WebElement studProficiency;// Class Proficiency Report By Students
    public WebElement getStudProficiency(){return studProficiency;}

    @FindBy(css="g.highcharts-markers.highcharts-tracker > path")
    WebElement coloredMarker;// colored marker icon
    public WebElement getColoredMarker(){return coloredMarker;}

    @FindBy(xpath = "//div[@class='tooltip-label-subsection-wrapper']/span[2]")
    WebElement proficiency;// proficiency tooltip
    public WebElement getProficiency(){return  proficiency;}

    /*Student Side*/
    @FindBy(className = "ls-reports-proficiency-percentage")
    WebElement studProfPercentage;// proficiency percentage for student
    public WebElement getStudProfPercentage(){return studProfPercentage;}

    @FindBy(id = "ir-productivity-report-title")
    WebElement productivityReportTitle;
    public WebElement getProductivityReportTitle(){return productivityReportTitle;}

    @FindBy(id = "reportTitle")
    WebElement productivityReportPageTitle;//in student side
    public WebElement getProductivityReportPageTitle(){return productivityReportPageTitle;}

    @FindBy(className = "no-data-notification-message-block")
    public WebElement notificationBlock;

    @FindBy(id = "ir-productivity-report-graph-content")
    public WebElement notification_Block;

    @FindBy(css = "span[class='al-terminal-objective-title  student-report']")
    public List<WebElement> studentName;


    @FindBy(className = "tooltip-label")
    public WebElement studentNameOnToolTip;

    @FindBy(className = "al-proficiency-percentage")
    public List<WebElement> insProficiency;// Class Proficiency Report By Students


}
