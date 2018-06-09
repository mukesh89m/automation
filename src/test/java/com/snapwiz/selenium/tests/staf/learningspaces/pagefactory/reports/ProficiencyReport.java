package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by Mukesh on 12/31/14.
 */
public class ProficiencyReport {

    @FindBy(css="g.highcharts-series.highcharts-tracker > rect")
    WebElement barChart;// barChart of Class Proficiency by Chapters
    public WebElement getBarChart(){return barChart;}

    @FindBy(className = "al-proficiency-percentage")
    WebElement studProficiency;// Class Proficiency Report By Students
    public WebElement getStudProficiency(){return studProficiency;}

    @FindBy(className = "idb-perf-report-tcqs")
    List<WebElement> tloChapterQuestionPerformance;
    public  List<WebElement> getTloChapterQuestionPerformance(){return tloChapterQuestionPerformance;}

    @FindBy(className = "idb-perf-report-tqs")
    List<WebElement> tloQuestionPerformance;
    public   List<WebElement> getTloQuestionPerformance(){return tloQuestionPerformance;}

    @FindBy(className = "coursePerformanceByChapters-xAxisLabel")
    WebElement chapNumber; //chapter number below the bar chart
    public WebElement getChapNumber(){return chapNumber;}

    @FindBy(className = "chapterPerformanceByObjectives-xAxisLabel")
    WebElement tloNumber; //chapter number below the bar chart
    public WebElement getTloNumber(){return tloNumber;}

    @FindBy(css = "tspan")
    WebElement courseProficiency; //Course Proficiency in student side
    public WebElement getCourseProficiency(){return courseProficiency;}

    @FindBy(css = "tspan")
    public  List<WebElement> courseProficiencys; //Course Proficiency in student side

    @FindBy(id = "ir-performance-report-title")
    WebElement proficiencyReportTitle;
    public WebElement getProficiencyReportTitle(){return proficiencyReportTitle;}

    @FindBy(className = "coursePerformanceByChapters-xAxisLabel")
    WebElement XaxixLabel;
    public WebElement getXaxixLabel(){return XaxixLabel;}

    @FindBy(className = "chapterPerformanceByObjectives-xAxisLabel")
    WebElement xaxixLabelByChapter;
    public WebElement getxXaxixLabelByChapter(){return xaxixLabelByChapter;}

    @FindBy(css = "rect[width='35']")
    WebElement width;
    public WebElement getWidth(){return width;}

    @FindBys(@FindBy(css = "div[class='report-sidebar-question-card-sectn question-card-yellow']"))
    List<WebElement> questionCart;
    public List<WebElement> getQuestionCart(){return questionCart;}

    @FindBy(id = "reportTitle")
    WebElement proficiencyReportTitleStudentSide;
    public WebElement getProficiencyReportTitleStudentSide(){return proficiencyReportTitleStudentSide;}

    @FindBy(className = "no-data-notification-message-block")
    public WebElement notificationBlock;

    @FindBy(css = "div[class='no-data-notification-message-block ls-ins-proficiency-report-graphs-data-no-found']")
    public WebElement notification_Block;


    @FindBy(className = "al-diagtest-percentage-score")
    WebElement labelValue_StudentGotItCorrect;
    public WebElement getLabelValue_StudentGotItCorrect(){return labelValue_StudentGotItCorrect;}

    @FindBys({@FindBy(css = "rect[width='30']")})
    List<WebElement> BarChartElementsList;//Bar chart Elements List
    public List<WebElement> getBarChartElementsList() {
        return BarChartElementsList;
    }

    @FindBy(css = "rect[width='30']")
    public List<WebElement> all_barChar;// barChart of Class Proficiency by Chapters


    @FindBy(css = "div[class='report-sidebar-question-card-sectn question-card-green']")
    public List<WebElement> QuestionCart;

    @FindBy(className = "al-terminal-objective-title")
    public List<WebElement> studentName;

    @FindBy(id = "al-cource-proficiency-summary")
    public WebElement proficiencySummaryValue;

    @FindBy(xpath = "//g[contains(@class,'highcharts-series highcharts-tracker')]/rect")
    WebElement questionPerformance; //question performance
    public WebElement getQuestionPerformance() {
        return questionPerformance;
    }

    @FindBy(css = "g[class='highcharts-series highcharts-tracker']:nth-of-type(1)>rect")
    public List<WebElement> proficiencyBar;

    @FindBy(className = "al-performance-value")
    public List<WebElement> objectivePerformance;

    @FindBy(xpath = "//div[@class='idb-prod-report-content-body-fourth']/span[1]")
     public List<WebElement> performanceByQuestion;
}
