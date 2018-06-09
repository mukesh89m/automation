package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by shashank on 19-08-2015.
 */
public class AssignmentSummary {

    @FindBy(css = "g.highcharts-series.highcharts-tracker > rect")
    public List <WebElement> barOnAssignmentSummary;

    @FindBy(xpath = "//div[starts-with(@class,'teacher-feedback')]")
    public WebElement teacherFeedbackStudentSide;

    @FindBy(id = "next-question-performance-view")
    public WebElement nextArrowInPerformance;

    @FindBy(css = "rect[height='117']")
    public List<WebElement> bar_performanceByQuestions;//Performance by questions bar

    @FindBy(xpath = "//*[@stroke='#FFFFFF']")
    public List<WebElement> bar_question;//Question bars

    @FindBy(id = "view-user-question-performance-feedback-box")
    public WebElement editbox_teacherFeedback;//Teacher feedback editbox

    @FindBy(css = "tspan")
    public List<WebElement> percentage; //Percentage on assignment summary

    @FindBy(css = "span[class='student-report-continue-button']")
    public WebElement button_continue;// Continue button

    @FindBy(css = "rect[fill='#73B966']")
    public List<WebElement> correctAnswer;

    @FindBy(xpath = "//div[@class='view-user-question-performance-score']")
    public WebElement studentScore;

    @FindBy(xpath = "//div[@id='question-points']")
    public WebElement totalScore;

    @FindBy(css = "div[class='as-assignment-next-chart next-chart']")
    public WebElement nextArrowInPerformanceBar;

}
