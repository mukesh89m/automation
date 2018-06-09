package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.detailedResponse;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by shashank on 19-08-2015.
 */
public class DetailedResponse {

    @FindBy(id= "go-back")
    public WebElement detailedResponseBackArrow;

    @FindBy(className= "assessment_summary_nav_up")
    public WebElement navigateToTop;

    @FindBy(css= "div.question-no")
    public List<WebElement> questionLabelAtDetailedResponse;

    @FindBy(xpath= "//a[@id='go-back']/following-sibling::h3")
    public WebElement detailedResponseHeader;

    @FindBy(css= "textarea#assessment-feedback-box")
    public WebElement overallFeedback;

    @FindBy(id= "view-user-question-performance-feedback-box")
    public List<WebElement> teacherFeedbackOnEachQuestion;

    @FindBy(css = "a.dropdown-toggle.ellipsis")
    public WebElement studentListDropDown;

    @FindBy(xpath = "//div[@class='student-name ellipsis']/span[@class='user-name']")
    public List<WebElement> studentNameListDropDown;


    @FindBy(id = "view-user-question-performance-score-box")
    public List<WebElement> scorePerQuestion;

    @FindBy(css = "span[class='views-number block text-center p-xs js-total-score']")
    public WebElement totalScoreNumerator;

    @FindBy(css = "span[class='views-number block text-center p-xs']")
    public WebElement totalScoreDenominator;

    @FindBy(xpath = "//div[starts-with(@class,'true-false-student-answer-select')]")
    public List<WebElement> options_trueFalse;//True false options to identify if selected or not

    @FindBy(css = "li[class='disabled font-bold std-name']")
    public List<WebElement> list_studentname;// List of students name

    @FindBy(css = "img.assignment-icon")
    public WebElement assignmentImage;//Assignment image

    @FindBy(css = "span[class='user-initials gray-bubble']")
    public WebElement dropdown_questionNumber;//Question number circle in questions dropdown

    @FindBy(css = "span[class='select2-selection__arrow']")
    public List<WebElement> list_dropdown;//Students and Questions dropdown

    @FindBy(xpath = "//ul[@id='select2-sel-question-results']/li")
    public List<WebElement> dropdown_questions;//Questions in dropdown

    @FindBy(xpath = "//*[@class='highcharts-series highcharts-tracker']/*")
    public List<WebElement> bars_student;//Student bars

    @FindBy(xpath = "//*[@class='highcharts-tooltip']")
    public WebElement hoverOverDetails_studentBar;//Details after doing hover over on student bar

    @FindBy(css = "[class='highcharts-markers highcharts-tracker']>path")
    public List<WebElement> lineGraph_students;//Line graph for students

    @FindBy(css = "[class='highcharts-axis-labels']>text>tspan")
    public List<WebElement> studentsInitial_bar;//Students initial on bar

    @FindBy(xpath = "//ul[@class='list-inline assignment-all-questions-navigation-content']/li")
    public List<WebElement> studentsInitial_bubble;//Students initial on bubble

    @FindBy(xpath = "//*[@fill='#73B966']")
    public List<WebElement> studentBar_allCorrectAnswers;//Student bars when all the answers are correct(21 students)

    @FindBy(css = "div[class='as-assignment-next-chart next-chart']")
    public WebElement nextArrowUnderPerformanceSummary;//Side scroller under performance summary

    @FindBy(css = "div[class='as-assignment-prev-chart prev-chart']")
    public WebElement previousArrowUnderPerformanceSummary;//Previous arrow under performance summary

    @FindBy(className = "view-user-question-performance-feedback")
    public List<WebElement> label_TeacherFeedback;//Teacher Feedback label

    @FindBy(css = "h3[class='response-and-feedback-block-header']")
    public List<WebElement> list_labelTeacherFeedback;//Teacher Feedback labels

    @FindBy(xpath = "//li[starts-with(@class,'question-link-label')]")
    public List<WebElement> bubble_student;//Student bubble

    @FindBy(xpath = "//div[@class='question-performance-score col-xs-3 col-sm-3 col-md-2 col-lg-2 text-center']")
    public List<WebElement> scoreSection;//Score section

    @FindBy(xpath = "//li[starts-with(@class,'question-link-label correct')]/img")
    public List<WebElement> bubble_studentWithImage;//Student bubble with image

    @FindBy(css = "div.perfrmance-bar-chart-header-sectn")
    public WebElement performanceHeader;//Performance header

    @FindBy(xpath = "//div[starts-with(@id,'qId')]")
    public List<WebElement> cards_student;//Student's cards

    @FindBy(css = "span[id='view-user-question-performance-save-success-message']")
    public List<WebElement> message_feedbackOrGrade;//Feedback or grade success message

    @FindBy(css = "div.feedback-saved-message")
    public WebElement message_overallFeedback;//Over all feedback message after entering feedback

    @FindBy(xpath = "//li[@class='disabled font-bold std-name']/descendant::span[3]")
    public List<WebElement> list_studentName;//List of student names

    @FindBy(xpath = "//*[@height='68']")
    public List<WebElement> bars_3students;//Student bars with 3 students

    @FindBy(xpath = "//*[@height='46']")
    public List<WebElement> bars_1studentAttempted;//Student bars

    @FindBy(id = "breadcrumb")
    public WebElement breadCrumb;

    @FindBy(xpath = "//div[starts-with(@class,'multiple-select-choice-icon-preview multiple-select-choice')]")
    public List<WebElement> options_multiSelection;//True false options to identify if selected or not

    @FindBy(xpath = "//*[@height='23']")
    public List<WebElement> bars_studentPartiallyAttempted;//Student bars

    @FindBy(xpath = "//*[@height='34']")
    public List<WebElement> bars_studentPartiallyAttempted1;//Student bars

    @FindBy(className = "go-back-icon")
    public WebElement backIcon;

    @FindBy(css = "span[id='question-points']")
    public List<WebElement> totalScorePerQuestion;

    @FindBy(id ="saveQuestionDetails1")
    public List<WebElement> saveQuestion;//click on save button





}
