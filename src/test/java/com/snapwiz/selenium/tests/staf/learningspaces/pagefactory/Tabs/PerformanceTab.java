package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Mukesh on 3/4/15.
 */
public class PerformanceTab {

    @FindBy(xpath = "//div[@class='question-card-instructor-feedback']")
    public List<WebElement> visualIndicator_Icon; //visual Indicator for graded/non-graded Question

    @FindBy(className = "feedback-content")
    public WebElement teacher_feedback;

    @FindBy(className = "marks-awarded")
    public WebElement teacher_mark;

    @FindBy(id="white-board-feedBack-link-text")
    public WebElement teacherFeedback_link;

    @FindBy(className = "static-assessment-retake")
    public  WebElement reTake_button;

    @FindBy(id = "cms-question-preview-sidebar-toggle-icon")
    public  WebElement tab_performance;

    @FindBy(css=".question-card-time-taken")
    public List<WebElement> attemptedTime;


}
