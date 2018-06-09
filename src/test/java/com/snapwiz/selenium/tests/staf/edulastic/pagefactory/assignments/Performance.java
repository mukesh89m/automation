package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Dharaneesh T Gowda on 13-01-2015.
 */

public class Performance {
    @FindBy(className = "student-report-continue-button")
    WebElement button_Continue; // 'Continue' button to continue the performance result
    public WebElement getButton_Continue(){
        return button_Continue;
    }

    @FindBy(id = "view-user-question-performance-feedback-box")
    WebElement textArea_EnterFeedback; // Text Area 'Enter Feedback' to enter the feedback by instructor
    public WebElement getTextArea_EnterFeedback(){
        return textArea_EnterFeedback;
    }

    @FindBy(className = "view-user-question-performance-score")
    WebElement questionPerformanceScore; // Question Performance Score eg. 0.3/1
    public WebElement getQuestionPerformanceScore(){
        return questionPerformanceScore;
    }

    @FindBy(className = "view-user-question-performance-save-btn")
    WebElement button_Save; // 'Save' button to save the performance result
    public WebElement getButton_Save(){
        return button_Save;
    }

    @FindBy(css = "g[class='highcharts-legend']+text>tspan")
    WebElement performancePercentage; // bar graph to open the question
    public WebElement getPerformancePercentage(){
        return performancePercentage;
    }

    @FindBy(id = "next-question-performance-view")
    WebElement button_next;//Next button to go to next question in student's side question performance view
    public WebElement getButton_next(){return button_next;}

    @FindBy(className = "user-performance-back-btn")
    WebElement backarrow_assessmentResponse;//Back button to navigate to assessment response page
    public WebElement getBackarrow_assessmentResponse(){return backarrow_assessmentResponse;}

    @FindBy(css = "g[class='highcharts-axis-labels']>text")
    public List<WebElement> questionPoint;//Question point son x axis

    @FindBy(id = "question-points")
    public WebElement totalScore;//Total score for the assignment assigned

    @FindBy(css = "h3[class='lsm-assignment-header-title ellipsis']")
    public WebElement assignmentName;

    @FindBy(xpath = "//h3[text()='Performance Summary']")
    public WebElement performanceSummary;

    @FindBy(className = "assignments-back-link")
    public WebElement backArrow;




}
