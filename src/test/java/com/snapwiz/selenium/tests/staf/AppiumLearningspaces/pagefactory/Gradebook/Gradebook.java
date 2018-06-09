package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.Gradebook;

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

    @FindBy(xpath = "(//span[@class='ls-ins-gradebook-activity-midterm'])[138]")
    WebElement scrollDown;
    public WebElement getScrollDown(){return scrollDown;}

    @FindBy(xpath = "(//span[@class='ls-ins-gradebook-activity-midterm'])[140]")
    WebElement scrollHorizontall;
    public WebElement getScrollHorizontall(){return scrollHorizontall;}


    @FindBy(xpath = "(//span[@class='ls-ins-gradebook-activity-midterm'])[1]")
    WebElement assignment;
    public WebElement getAssignment(){return assignment;}

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
    public  WebElement getPostAMessage(){return  postAMessage;}

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



















}
