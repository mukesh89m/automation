package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.reports;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by pragyas on 08-02-2016.
 */
public class MyReports {

    @FindBy(css = "div.card-content")
    public List<WebElement> standardAssessced;

    @FindBy(xpath = "//input[@id='mastery']/parent::div")
    public WebElement viewByMastery;

    @FindBy(xpath = "//input[@id='raw']/parent::div")
    public WebElement viewByScore;

    @FindBy(xpath = "//input[@id='percent']/parent::div")
    public WebElement viewByPercent;

    @FindBy(xpath = "//span[@data-proficiency='mastery' and @identifier='1.OA.A.1']")
    public List<WebElement> masteryByEachStudent;

    @FindBy(xpath = "//span[@data-proficiency='mastery' and @identifier='1.OA']/span/i")
    public List<WebElement> checkMarkOnEachStudentMastery;

    @FindBy(xpath = "//span[@data-proficiency='raw' and @identifier='1.OA.A.1']")
    public List<WebElement> scoreByEachStudent;

    @FindBy(xpath = "//span[@data-proficiency='percent' and @identifier='1.OA.A.1']")
    public List<WebElement> percentByEachStudent;

    @FindBy(xpath = "//span[@data-proficiency='mastery' and @identifier='2.OA']")
    public List<WebElement> masteryByEachStudentGrade2;

    @FindBy(xpath = "//span[@data-proficiency='mastery' and @identifier='2.OA']/span/i")
    public List<WebElement> checkMarkOnEachStudentMasteryGrade2;

    @FindBy(xpath = "//span[@data-proficiency='raw' and @identifier='2.OA']")
    public List<WebElement> scoreByEachStudentGrade2;

    @FindBy(xpath = "//span[@data-proficiency='percent' and @identifier='2.OA']")
    public List<WebElement> percentByEachStudentGrade2;


    @FindBy(className = "pull-left")
    public WebElement titleName;

    @FindBy(xpath = "//div[@class='card-content']/p")
    public List<WebElement> standards;

    @FindBy(xpath = "//input[@type='radio']/../..")
    public List<WebElement> labels_viewBy;

    @FindBy(xpath = "//span[@data-proficiency='mastery']")
    public List<WebElement> tabularValue_master;//tabular value view by mastery

    @FindBy(xpath = "//span[@data-proficiency='raw']")
    public List<WebElement> tabularValue_score;//tabular value view by score

    @FindBy(xpath = "//span[@data-proficiency='percent']")
    public List<WebElement> tabularValue_percent;//tabular value view by percent

    @FindBy(xpath = "//span[@class='select2-selection__arrow']")
    public List<WebElement> selectionBox;

    @FindBy(xpath = "//li[text()='Grade 2']")
    public WebElement grade2; //Grade 2

    @FindBy(css = "span[class='skillReportIns-table-sprite skillReportIns-expand-icon']")
    public List<WebElement> expandIcon;//Expand icon

    @FindBy(className = "as-noData-msgWrapper")
    public WebElement defaultReport;//Default report image

    @FindBy(css = "span[class=' skillReportIns-common-core-std-grade  skillReportIns-tlo-cell skillReportIns-greyGrade']")
    public List<WebElement> eloInsufficientInfo;

    @FindBy(css = ".skillReportIns-common-core-std-grade.skillReportIns-tlo-cell.skillReportIns-redGrade")
    public List<WebElement> eloNotMastered;

    @FindBy(css = "span[class^=' skillReportIns-common-core-std-grade  skillReportIns-tlo-cell skillReportIns-orangeGrade']")
    public List<WebElement> eloNearlyMastered;

    @FindBy(css = "div[data-name='assessment-report']")
    public WebElement singleAssessmentReport;

    @FindBy(css = ".col-sm-12>h4")
    public List<WebElement> averagePerformance;






}
