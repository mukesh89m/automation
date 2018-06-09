package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by shashank on 11-08-2015.
 */
public class StudentResponse {

    @FindBy(css = "div#gradebook-performance-chart")
    public WebElement performanceChart;

    @FindBy(css = "div#gradebook-mastery-chart")
    public WebElement masteryChart;

    @FindBy(css = "div.response-card")
    public List<WebElement> studentResponseCard;

    @FindBy(xpath = "//span[@class='inline m-t-xs']/span")
    public WebElement assessmentName;

    @FindBy(css = "span#go-back")
    public WebElement backButton;

    @FindBy(xpath = "//span[@class='sub-text pull-right m-t-xs']")
    public WebElement assignmentPolicy;

    @FindBy(css = "g.highcharts-axis-labels > text > tspan")
    public List<WebElement> labelsInChart;

    @FindBy(css = "rect[height='28']")
    public List<WebElement> barUnderPerformance;

    @FindBy(css = "rect[height='26']")
    public List<WebElement> barForWrongUnderPerformance;

    @FindBy(css = "rect[fill='#6bb45f']")
    public List<WebElement> barTwoCorrectUnderPerformance;

    @FindBy(css = "rect[height='108']")
    public List<WebElement> barTwoCorrectUnderPerformanceWithOneStudent;

    @FindBy(xpath =  "//div[starts-with(@class,'performance-title')]")
    public List<WebElement> performanceMasteryPercentage;

    @FindBy(xpath = "//*[contains(text(),'Mastery')]")
    public WebElement mastery;//Mastery

    @FindBy(css = "div.graph-scroll.graph-bottom.js-scroll-performance-right>img")
    public WebElement scroll_bottomMastery;//Bottom scroll on mastery

    @FindBy(css = "div.graph-scroll.graph-right.js-scroll-performance-right>img")
    public WebElement scroll_RightPerformance;//Bottom scroll on performance

    @FindBy(css = "div.graph-scroll.graph-left.js-scroll-performance-left>img")
    public WebElement scroll_LeftPerformance;//Up scroll on performance

    @FindBy(xpath = "//div[@class='row']/div[contains(@class,'col-xs-12 response-footer')]")
    public List<WebElement> checkCardDisabled;

    @FindBy(xpath = "//div[@class='row']/div[@class='col-xs-12 response-footer']/a")
    public List<WebElement> viewDetailedResponse;

    @FindBy(css= ".list-inline.assignment-all-questions-navigation-content > li")
    public List<WebElement> questionListAtDetailedViewResponse;

    @FindBy(css= ".highcharts-series.highcharts-tracker > rect")
    public List<WebElement> barGraphInDetailedResponse;

    @FindBy(css="div.col-xs-7.mastery-sections")
    public List<WebElement> performanceAtStudentCard;

    @FindBy(css="div.col-xs-12.mastery")
    public List<WebElement> masteryAtStudentCard;

    @FindBy(css = ".response-header>h4")
    public List<WebElement> lstStudentCard;

    @FindBy(css=".responses-list >li>a")
    public List<WebElement> responseListAtStudentCard;

    @FindBy(css = "rect[height='648']")
    public List<WebElement> bars_mastery;//Mastery bar

    @FindBy(xpath = "//*[@fill='#9a9fa0']")
    public List<WebElement> barsMastery_greyColor;//Mastery bars in grey color

    @FindBy(xpath = "//button[starts-with(@class,'btn gradebook-top-btn')]")
    public WebElement releaseGrade;

    @FindBy(css = "button[class='btn gradebook-left-btn as-live-print-wrapper']")
    public WebElement printResponse;

    @FindBy(css = "button[class='btn gradebook-middle-btn as-live-reassign-wrapper']")
    public WebElement button_redirect;

    @FindBy(css = "button[class='btn gradebook-right-btn as-gradeBook-download-csv']")
    public WebElement downloadGrade;

    @FindBy(css = ".iCheck-helper")
    public List<WebElement> checkboxInStudentCard;

    @FindBy(xpath = "//div[@class='response-card-select-box checkbox i-checks']//ins")
    public List<WebElement> studentCard_checkbox;

    @FindBy(xpath = "//*[@height='75']")
    public List<WebElement> barAllCorrectAnswer;//bar when all the answers are correct

    @FindBy(xpath = "//*[@height='78']")
    public List<WebElement> barAllCorrectAnswer_11Students;//bar when all the answers are correct

    @FindBy(css = "rect[height='80']")
    public List<WebElement> barOneCorrectUnderPerformance;

    @FindBy(xpath = "//div[@class='graph-scroll graph-right js-scroll-performance-right']/img")
    public WebElement nextArrow_performanceQuestions;//Next arrow to navigate to next questions under performance graph

    @FindBy(css = "rect[height='27']")
    public List<WebElement> bar3QuestionsAttemptedOutOf5;//Question bar under performance


    @FindBy(css = "rect[fill='#6bb45f']")
    public List<WebElement> greenBarUnderPerformance;

    @FindBy(css = "div[class='col-xs-6 text-left']")
    public List<WebElement> scoreAndPercentageOnStudentCard;//Score and percentage on student card

    @FindBy(css = "rect[height='68']")
    public List<WebElement> essayBar;

    @FindBy(css = "li.active")
    public List<WebElement> activePage;//Page name

    @FindBy(css = "div.notification-banner")
    public WebElement notificationMsg_redirect;//Notification message on redirect

    @FindBy(css = ".sub-text.m-t-xs")
    public WebElement explicitlyByDistrictAdmin;

    @FindBy(css = ".views-number.block.text-center.p-xs.js-total-score")
    public WebElement total_score;

    @FindBy(css = ".btn.as-gradeBook-add-students")
    public WebElement btnAddStudents;

    @FindBy(css = "button[class$='remove-student']")
    public WebElement unAssignStudents;

    @FindBy(css = ".writtenConfirmation-notification")
    public WebElement unAssignPopUp;

    @FindBy(css = ".as-modal-sprite-img.as-close-modal.cancel-writtenConfirmation")
    public WebElement closeIconOnUnAssignPopUp;

    @FindBy(id = "phrase-confm-txt")
    public WebElement unAssign_TextBox;

    @FindBy(css = ".as-modal-yes-btn.confirm-writtenConfirmation")
    public WebElement yesUnAssign;

    @FindBy(css = ".block")
    public List<WebElement> submittedStatus;

    @FindBy(xpath = "//*[text()='NOT STARTED']")
    public WebElement notstarted;

    @FindBy(xpath = "//li[@id='express-grader-tab']/a")
    public WebElement expressGraderTab;

    @FindBy(id = "go-back")
    public WebElement goBack;

    @FindBy(xpath = "//li[@id='live-class-tab']/a")
    public WebElement liveClassBoardTab;

    @FindBy(css = ".cell-content")
    public List<WebElement> cell_content;

    @FindBy(css = "div[class='dataTables_scrollBody']")
    public WebElement dataTables_scroll;

    @FindBy(css = "div[class='checkbox i-checks express-grader-show-all-class-section'] ins")
    public WebElement showAllClassSection_checkbok;

    @FindBy(css = "table[id='express-grader-table'] td[class='sorting_1']+td>span")
    public List<WebElement> classSectionData;

    @FindBy(css = "#express-response-modal-close")
    public WebElement express_response_close;

    @FindBy(css = "div[class^='inline student-row std-row-']>span")
    public WebElement label_student_name;


    @FindBy(xpath = "//li[@id='live-class-tab']")
    public WebElement liveClass_Tab;

    @FindBy(xpath = "//span[text()='SHOW ALL CLASS SECTIONS']")
    public WebElement allClassSection;

    @FindBy(xpath = "//th[text()='Questions & Standards']")
    public WebElement questionStandards;

    @FindBy(xpath = "//tr[@class='question-performance-row']/th")
    public List<WebElement> questionPerformanceRow;

    @FindBy(xpath = "//span[text()='SHOW ALL CLASS SECTIONS']/../div//ins[@class='iCheck-helper']")
    public WebElement allClassSectionCheckBox;

    @FindBy(xpath = "//table[@id='express-grader-table']//td[@class='sorting_1']//span[text()]")
    public List<WebElement> student_Name;

    @FindBy(xpath = "//td[@data-dt-column='2']")
    public List<WebElement> scores;

    @FindBy(xpath = "//td[@data-dt-column='2']/span[@class='pull-left']")
    public List<WebElement> percentageScore;

    @FindBy(css = "th[class='js-sort text-center sorting question-label-row']")
    public List<WebElement> questionBox;

    @FindBy(css = "th[class='js-sort text-center sorting question-label-row'] >div")
    public List<WebElement> standards;

    @FindBy(css = "th[class='js-sort text-center sorting question-label-row'] >div >div")
    public List<WebElement> tlos;


}
