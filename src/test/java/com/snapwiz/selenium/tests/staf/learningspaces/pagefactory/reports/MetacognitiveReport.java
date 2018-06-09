package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by root on 12/31/14.
 */
public class MetacognitiveReport {

    /*Instructor side*/
    @FindBy(className = "al-proficiency-percentage")
    WebElement studProficiency;// Class Proficiency Report By Students
    public WebElement getStudProficiency(){return studProficiency;}

    @FindBy(css="g.highcharts-markers.highcharts-tracker > path")
    WebElement coloredMarker;// colored marker icon
    public WebElement getColoredMarker(){return coloredMarker;}

    @FindBy(xpath = "//div[@class='tooltip-label-subsection-wrapper']/span[1]")
    WebElement proficiency;// proficiency tooltip
    public WebElement getProficiency(){return  proficiency;}

    /*Student Side*/
    @FindBy(className = "ls-reports-proficiency-percentage")
    WebElement studProfPercentage;// proficiency percentage for student
    public WebElement getStudProfPercentage(){return studProfPercentage;}

    @FindBy(id = "ir-metacognitive-report-title")
    WebElement metacognitiveReportPage;
    public WebElement getMetacognitiveReportPage(){return metacognitiveReportPage;}

    @FindBy(id = "reportTitle")
    WebElement metacognitiveReportPageTitle;//in student side
    public WebElement getMetacognitiveReportPageTitle(){return metacognitiveReportPageTitle;}

    @FindBy(xpath ="//div[text()='Practice']")
    public WebElement practicePageButton;

    @FindBy(className = "no-data-notification-message-block")
    public WebElement notificationBlock;

    @FindBy(className = "no-data-notification-message")
    public WebElement notification_Block;

    @FindBy(css = "span[class='al-terminal-objective-title  student-metacognitive-report']")
    public List<WebElement> studentName;

    @FindBy(className = "tooltip-label")
    public WebElement studentNameOnToolTip;

    @FindBy(className = "ls-report-row-right-arrow")
    public WebElement proficiencyArrow;

    @FindBy(xpath = "//div[@class='ls-reports-preformance-links']/div")
    public List<WebElement> reportPerformance_link;

    @FindBy(xpath = "//div[@class='ls-preformance-links']/div")
    public List<WebElement> productivityReportPerformance_link;

    @FindBy(xpath = "//div[@class='ls-preformance-options']/div")
    public List<WebElement> mostReportPerformance_link;


}
