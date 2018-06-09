package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/*
 * Created by sumit on 5/1/15.
 */
public class Gradebook {

    @FindBy(className = "no-gradebook-summary-block")
    WebElement noGradeBookMessage;//no Gradebook exist in Gradebook page
    public WebElement getNoGradeBookMessage() {
        return noGradeBookMessage;
    }

    @FindBy(xpath = "//div[@class='student-assignment-score-cell']/span")
    public WebElement questionAttempt_Count;

    @FindBy(className = "ins-gradebook-summary-header-title")
    WebElement gradeBookHeader;
    public WebElement getGradeBookHeader(){return gradeBookHeader;}


    @FindBy(xpath = ".//*[@title='Gradebook']")
    WebElement buttonExportToCSV;
    public WebElement getButtonExportToCSV(){return buttonExportToCSV;}

    @FindBy(xpath = "(//span[@class='ls-ins-gradebook-activity-midterm'])[138]")
    WebElement scrollDown;
    public WebElement getScrollDown(){return scrollDown;}

    @FindBy(xpath = "(//span[@class='ls-ins-gradebook-activity-midterm'])[140]")
    WebElement scrollHorizontall;
    public WebElement getScrollHorizontall(){return scrollHorizontall;}


    @FindBy(xpath = "(//span[@class='ls-ins-gradebook-activity-midterm'])[1]")
    WebElement assignment;
    public WebElement getAssignment(){return assignment;}

    @FindBy(xpath = "//span[@class='ls-ins-gradebook-activity-midterm']")
    public List<WebElement> gradebookWeight;

    @FindBy(xpath = "(//span[@class='ellipsis'])[20]")
    WebElement leftContent;
    public WebElement getLeftContent(){return leftContent;}

    @FindBy(xpath = "//img[@title='Gradebook']")
    WebElement exportToCSV;
    public WebElement getExportToCSV(){return exportToCSV;}

    @FindBy(css = "div[class='ls-ins-gradebook-post-msg post-msg-disabled']")
    WebElement postAmessage;
    public WebElement getPostAmessage(){return postAmessage;}

    @FindBy(xpath = "//div[@class='ls-ins-gradebook-activity-students']/span[1]")
    WebElement nameHeader;
    public  WebElement getNameHeader(){return nameHeader;}

    @FindBy(xpath = "(//div[@class='ls-ins-gradebook-activity-secondCell']/span[1])[1]")
    WebElement overallScore;
    public  WebElement getOverallScore(){return  overallScore;}

    @FindBy(xpath = "//span[@class='ellipsis']")
    List<WebElement> leftContentCount;
    public List<WebElement> getLeftContentCount(){return leftContentCount;}

    @FindBy(xpath = "(//span[contains(@class,'ls-ins-gradebook-activity-midterm ellipsis')])[4]")
    WebElement assignmentName;
    public WebElement getAssignmentName(){return  assignmentName;}

    @FindBy(xpath = "//span[contains(@class,'ls-ins-gradebook-activity-midterm ellipsis')]")
    List<WebElement> allAssignmentName;
    public List<WebElement> getAllAssignmentName(){return allAssignmentName;}

    @FindBy(className = "ls-ins-gradebook-overall-score")
    List<WebElement> overAllScore;
    public List<WebElement> getOverAllScore(){return overAllScore;}

    @FindBy(css="div.ls-ins-gradebook-weighting")
    WebElement gradebookWeighting;
    public WebElement getGradebookWeighting(){return  gradebookWeighting;}

    @FindBy(css="span.ls-ins-gradebook-weighting-save-button")
    WebElement gradebookWeightingSaveButton;
    public WebElement getgradebookWeightingSaveButton(){return  gradebookWeightingSaveButton;}


    @FindBy(css = "input.gradebook-weight-inputBox")
    List<WebElement> enterGradebookWeighting;
    public List<WebElement> getEnterGradebookWeighting(){return enterGradebookWeighting;}

    @FindBy(xpath = "//a[@title='No Assignment Category']")
    List<WebElement> noAssignmentCategory;
    public List<WebElement> getNoAssignmentCategory(){return  noAssignmentCategory;}

    @FindBy(xpath = "//a[@title='Practice']")
    List<WebElement> practice;
    public List<WebElement> getPractice(){return  practice;}

    @FindBy(xpath = "//a[@title='Homework']")
    List<WebElement> homework;
    public List<WebElement> getHomework(){return  homework;}

    @FindBy(xpath = "//a[@title='Quiz']")
    List<WebElement> quiz;
    public List<WebElement> getQuiz(){return  quiz;}

    @FindBy(xpath = "//a[@title='Test']")
    List<WebElement> test;
    public List<WebElement> getTest(){return  test;}

    @FindBy(xpath = "//a[@title='Other']")
    List<WebElement> other;
    public List<WebElement> getOther(){return  other;}

    @FindBy(xpath = "//a[@title='Uncategorized']")
    List<WebElement> uncategorized;
    public List<WebElement> getUncategorized(){return  uncategorized;}

    @FindBy(css="span.assign-this")
    List<WebElement> assignThis;
    public List<WebElement> getAssignThis(){return assignThis;}

    @FindBy(xpath="//div[@title='Assign This']")
    List<WebElement> assignThisFromETextbook;
    public List<WebElement> getAssignThisFromETextbook(){return assignThisFromETextbook;}


    @FindBy(css="span.assign-now-text")//Assign now on assignment page
            WebElement assignNow;
    public WebElement getAssignNow(){return assignNow;}


    @FindBy(xpath = "//span[text()='Gradebook Weighting: *']")//Grading weighting label
            WebElement gradebookWeightingLabel;
    public WebElement getGradebookWeightingLabel(){return  gradebookWeightingLabel;}


    @FindBy(css = "div[class='ls-inst-dashboard-assignment-popup-button ls--create-custom-assignment-view']")//create custom assignment on New assignment
            WebElement createCustomAssignment;
    public WebElement getCreateCustomAssignment(){return  createCustomAssignment;}


    @FindBy(xpath= "//div[@class='ls-inner-arw']")//arrow on assessment in each chapter
            List<WebElement> arrowOnAssessment;
    public List<WebElement> getArrowOnAssessment(){return  arrowOnAssessment;}

    @FindBy(css= "span.assignment-weightage")//assignment weightage at gradebook page
            List<WebElement> assignmentWeightage;
    public List<WebElement> getAssignmentWeightage(){return  assignmentWeightage;}


    @FindBy(css= "span.assign-more")//assignment weightage at gradebook page
            List<WebElement> updateAssignment;
    public List<WebElement> getUpdateAssignment(){return  updateAssignment;}

    @FindBy(css= "div.assignment-update-reassign")//assignment weightage at gradebook page
            WebElement reassignAssessment;
    public WebElement getReassignAssessment(){return  reassignAssessment;}


    @FindBy(css= "div[class='export-gradebook-summary-csv student-performance-report-export-csv idb-export-csv-performance-report']")//Export to csv
            WebElement exportToCsv;
    public WebElement getExportToCsv(){return  exportToCsv;}

    @FindBy(css= "div[class='ls-ins-gradebook-post-msg post-msg-disabled']")//Post a message
            WebElement postAMessage;
    public WebElement getPostAMessage(){return  postAMessage;}

    @FindBy(css= "span.ls-ins-gradebook-weighting-cancel-button")//Cancel button at gradebook page
            WebElement cancelGradebookWeighting;
    public WebElement getCancelGradebookWeighting(){return  cancelGradebookWeighting;}

    @FindBy(css= "span.gradebook-weighting-total-box")//assignment weightage at gradebook page
            WebElement totalBoxGradebookWeghting;
    public WebElement getTotalBoxGradebookWeghting(){return  totalBoxGradebookWeghting;}

    @FindBy(css= "div.top-notification-Bar")//Notification on top on click of save button
            WebElement topNotificationMessage;
    public WebElement getTopNotificationMessage(){return  topNotificationMessage;}

    @FindBy(css= "div.help-data-container")//Help when user click on percentage weightage at assignment
            WebElement helpNotification;
    public WebElement getHelpNotification(){return  helpNotification;}

    @FindBy(css="div.ls-class-section-drop-down-wrapper scrollbar-wrapper")
    WebElement changeClassSection;
    public WebElement getChangeClassSection(){return  changeClassSection;}

    @FindBy(css="span.ls-overall-score-percent")
    WebElement overallScoreStudentSide;
    public WebElement getOverallScoreStudentSide(){return  overallScoreStudentSide;}

    @FindBy(xpath="//a[@title='All Assignments']")
    WebElement allAssignments;
    public WebElement getAllAssignments(){return  allAssignments;}

    @FindBy(xpath="//a[@title='Question Assignment']")
    WebElement questionAssignment ;
    public WebElement getQuestionAssignment(){return  questionAssignment ;}

    @FindBy(xpath="//a[@title='Discussion Assignment']")
    WebElement discussionAssignment;
    public WebElement getDiscussionAssignment(){return  discussionAssignment;}

    @FindBy(css="span.ls-assignment-item-author-name")
    WebElement authorName ;
    public WebElement getAuthorName(){return  authorName;}

    @FindBy(css="span.category-weightage")
    WebElement assignmentCategoryStudentSide ;
    public WebElement getAssignmentCategoryStudentSide(){return  assignmentCategoryStudentSide;}

    @FindBy(className = "ls-gradebook-basic-view-link")
    public  WebElement basicView;

    @FindBy(xpath = "//span[@title='Student Performance']")
    public WebElement studentPerformanceTab;

    @FindBy(className = "no-gradebook-summary-block")
    public List<WebElement> gradeBookMessage;

    @FindBy(xpath = "//div[@class='tab active']/span[@class='tab_title']")
    public WebElement activeTab;

    @FindBy(xpath = "//ui[@class='grade-range-selector']/li")
    public List<WebElement> gradeRangeSelector;


    @FindBy(css = "li[class='grade-range-btn btn-0-59 selected-grade-range-btn']")
    public WebElement zeroToFiftyNineSelectedGrade;

    @FindBy(xpath = "//span[@title='Assignment Performance']")
    public WebElement assignmentPerformanceTab;

    @FindBy(xpath = "//div[@class='tab-header-label']")
    public  List<WebElement> tabHeader;

    @FindBy(css="g.highcharts-markers.highcharts-tracker > path")
    public List<WebElement> coloredMarker;// colored marker icon

    @FindBy(xpath = "//div[@class='highcharts-tooltip']")
    public List<WebElement> dotBox;

    @FindBy(className = "enhanced-gradebook-back-button")
    public WebElement backButton;

    @FindBy(xpath ="(//table[@id='assignmentMetricsTable'])[2]//th")
    public List<WebElement> tableHeaders;

    @FindBy(xpath = "//td[@class='gbe-sp-student-name']/div")
    public List<WebElement> studentsName;

    @FindBy(xpath ="//span[@title='Ascending']")
    public WebElement ascending;

    @FindBy(xpath ="//td[@class='gbe-sp-completion-level']")
    public List<WebElement> finishedPercentage;

    @FindBy(css = "div[class*='gdb-dd-help-icon']")
    public List<WebElement> helpIcon;

    @FindBy(xpath = "//span[@class='sorting']")
    public List<WebElement> sortIcon;

    @FindBy(xpath = "//td[@class='gbe-sp-time-spent']")
    public List<WebElement> avgTimeSpent;

    @FindBy(xpath = "//td[@class='gbe-sp-grade']")
    public  List<WebElement> overAllScorePercentage;

    @FindBy(xpath = "//div[@class='user-thumbnail']/img")
    public  WebElement userThumbnail;

    @FindBy(xpath = "//*[text()='Grade (%)']")
    public WebElement gradeLabel;

    @FindBy(css = "img[title='Export to CSV']")
    public WebElement Export_to_CSV_link;

    @FindBy(className = "transferred-gradebook-btn")
    public WebElement transferred_gradebook_button;

    @FindBy(className = "gradebook-weighting-total-message")
    public WebElement notification_message;

    public By gradebook_popup=By.className("ls-ins-gradebook-weighting-popup");

    @FindBy(xpath = "//*[text()='Avg. Time Spent (min)']")
    public WebElement average_timeSpent_label;

    @FindBy(xpath = "//*[text()='Average Grade (%)']")
    public WebElement average_grade_label;

    @FindBy(css = "g[class='highcharts-series highcharts-tracker']>rect")
    public List<WebElement> assignment_barEntry;

    @FindBy(className = "gbClassPerformanceByAssignment-xAxisLabel")
    public List<WebElement> classPerformanceByAssignment_link;

    @FindBy(className = "gb-tooltip-value")
    public List<WebElement> tooltip_value;

    @FindBy(className = "gbStudentAssignmentsPerformance-xAxisLabel")
    public List<WebElement> studentAssignmentPerformance_link;

    @FindBy(xpath = "//*[text()='Time Spent (min)']")
    public WebElement timeSpent_label;

    @FindBy(xpath = "//div[@class='next-chart']")
    public List<WebElement> nextArrow;

    @FindBy(css = "g[class='highcharts-tooltip']>rect")
    public List<WebElement> gradeBox_color;

    @FindBy(className = "enhanced-assignment-weightage")
    public List<WebElement> weightage;

    @FindBy(className = "help-data-container")
    public List<WebElement> weightage_value;

    public By gradeBookNoAssignmentCategory=By.xpath("(//td[@class='assignment-name'])[1]/div[@class='enhanced-assignment-weightage']");

    @FindBy(xpath = "//td[@class='not-start']/span[@class='bottom-text']")
    public List<WebElement> didNotStart_values;

    @FindBy(xpath = "//td[@class='not-finish']/span[@class='bottom-text']")
    public List<WebElement> notFinish_values;

    @FindBy(xpath = "//td[@class='finished']/span[@class='bottom-text']")
    public List<WebElement> finished_values;

    @FindBy(xpath = "//th[@class='due-date']/span")
    public WebElement dueDate_sorting;

    @FindBy(xpath = "//*[@id='assignmentMetricsTable']//th")
    public List<WebElement> gradeBook_statusHeader;

    @FindBy(css = "div[class='assignment-label ellipsis gbClassPerformanceByAssignment-xAxisLabel']")
    public WebElement assignmentName_label;

    @FindBy(css = "div[class='ls-ins-gradebook-activity-table-icons ls-ins-gradebook-help-img enhanced-gradebook-help-icon']+span")
    public List<WebElement> assignmentPerformanceSorting_icon;

    @FindBy(css = "div[class='ls-ins-gradebook-activity-table-icons ls-ins-gradebook-help-img enhanced-gradebook-help-icon']")
    public List<WebElement> assignmentPerformanceHelp_icon;

    @FindBy(xpath = "//td[@class='time-spent']")
    public List<WebElement> averageTimeSpent_values;

    @FindBy(css = ".grade")
    public List<WebElement> grade_list;

    @FindBy(xpath = "(//div[@class='track'])[4]/div")
    public WebElement tiny_scroll;

    @FindBy(css = "div.assignment-label.ellipsis.gbStudentAssignmentsPerformance-xAxisLabel")
    public List<WebElement> assignment_Name;

    @FindBy(xpath = "//div[@id='tab-student-performance-tab']//th/div")
    public List<WebElement> helpIconStudentMetrics;

    @FindBy(xpath = "//td[@class='spd-completion-level']")
    public List<WebElement> completionLevel;

    @FindBy(xpath = "//td[@class='spd-avg-grade']")
    public List<WebElement> averageGrade;

    @FindBy(xpath = "//td[@class='spd-avg-time-spent']")
    public  List<WebElement> averageTimeSpent;

    @FindBy(xpath = "//td[@class='spd-time-spent']")
    public  List<WebElement> timeSpent;

    @FindBy(xpath = "//div[@class='gradebook-student-performance-view']/div[@class='next-chart']")
    public WebElement nextChartIcon;

    @FindBy(xpath = "//div[@class='prev-chart']")
    public WebElement previousChartIcon;

    @FindBy(id = "idb-gradebook-backbutton")
    public  WebElement goBackButton;

    @FindBy(xpath = "//td[@class='spd-grade']")
    public  List<WebElement> studentGrade;

    @FindBy(className = "highcharts-tooltip")
    public List<WebElement> student_PerformanceBox;

    @FindBy(xpath = "//td[@class='gbe-sp-completion-level']/span[@class='bottom-text']")
    public List<WebElement> assignmentFinished;

  /*  @FindBy(css = "div[class='tab active']>span:nth-child(2)")
    public WebElement active_tab;*/

    @FindBy(css = "span[class='tab-header-assignment-label ellipsis']")
    public WebElement tab_header_assignment_label;

    @FindBy(className = "chart-average-label")
    public List<WebElement> getAverage_grade_label;

    @FindBy(xpath = "(//table[@id='assignmentMetricsTable'])//th")
    public List<WebElement> drillDown_AssignmentMetrics;

    @FindBy(xpath = "//td[@class='gbe-dd-student-name']/div")
    public List<WebElement>drillDown_studentName;

    @FindBy(css ="span[class='sorting sorting_desc']")
    public WebElement desc_sorting;

    @FindBy(css = "#assignmentMetricsTable div")
    public List<WebElement> drillDown_helpIcon;

    @FindBy(css = "#assignmentMetricsTable span")
    public List<WebElement> drillDown_sortIcon;

    @FindBy(xpath = "//td[@class='gbe-dd-completion-level']")
    public List<WebElement> drillDown_completionLevel;

    @FindBy(xpath = "//td[@class='gbe-dd-time-spent']")
    public List<WebElement> drillDown_timeSpent;

    @FindBy(xpath = "//td[@class='gbe-dd-grade']")
    public List<WebElement> drillDown_grade;

    @FindBy(css = "#cs-grade-per-assignment-chart .gb-summary-xAxisLabel")
    public List<WebElement> perGradeAssignmentStudent_label;

    @FindBy(css = "#student-assignment-time-spent-chart .gb-summary-xAxisLabel")
    public List<WebElement> perTimeSpentStudent_label;

}
