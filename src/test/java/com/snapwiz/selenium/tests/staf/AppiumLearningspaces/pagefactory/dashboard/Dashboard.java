package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.dashboard;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by Mukesh on 1/5/15.
 */
public class Dashboard {
    @FindBy(id = "donut-chart")
    WebElement avgPerformance;// Average performance chart on the dashboard
    public WebElement getAvgPerformance(){return avgPerformance;}

    @FindBy(css="g.highcharts-series.highcharts-tracker > rect")
    WebElement gradedBarChart; //recently graded barChart on dashboard
    public WebElement getGradedBarChart(){return gradedBarChart;}

    @FindBys(@FindBy(className = "number"))
    List<WebElement> avgQuestionPerformance;
    public List<WebElement> getAvgQuestionPerformance(){return avgQuestionPerformance;}

    @FindBys(@FindBy(xpath = ".//*[@class='news news3']/div[2]/p[contains(text(),'posted')]"))
    List<WebElement> entryInCourseStreamTile;//it will list all the posted entries in CS tile
    public List<WebElement> getEntryInCourseStreamTile(){return entryInCourseStreamTile;}

    @FindBy(css = "img[title='WileyPLUS Learning Space']")
    WebElement title;//title on dashboard page
    public WebElement getTitle(){return title;}

    @FindBy(css = "a[class='navigator ls-toc-sprite']")
    WebElement mainNavigator;//main navigator icon in the leftcorner
    public WebElement getMainNavigator(){return mainNavigator; }

    @FindBy(className = "ls--assignments")
    WebElement assignments;//for assignment
    public WebElement getAssignments(){ return assignments; }

    @FindBy(className = "ls--new-assignment")
    WebElement newAssignments;//for new assignment
    public WebElement getNewAssignments(){ return newAssignments; }

    @FindBy(className = "ls-inst-db-assignment-close-dialog")
    WebElement crossIcon;//cross icon after clicking on new Assignment
    public WebElement getCrossIcon(){ return crossIcon; }

    @FindBy(className = "ls-dashboard-view-all")
    WebElement viewAllLink;
    public WebElement getViewAllLink(){
        return viewAllLink;
    }

    @FindBy(className = "ls-dashboard-new-assignment")
    WebElement newAssignmentButton;
    public WebElement getNewAssignmentButton(){
        return  newAssignmentButton;
    }

    @FindBy(css = "a[class='navigator ls-toc-sprite selected']")
    WebElement mainNavigatorAfterSelected;//main navigator icon in the leftcorner
    public WebElement getMainNavigatorAfterSelected(){return mainNavigatorAfterSelected; }

    @FindBy(className = "ls-user-nav__username")
    WebElement userName;//user name in top -right corner
    public WebElement getUserName(){ return userName; }

    @FindBy(id = "myBlackBoard")
    WebElement myBlackBoard;//my black board in profile dropdown
    public WebElement getMyBlackBoard(){ return myBlackBoard; }

    @FindBy(className = "nav-ls-black-board")
    WebElement myBlackBoardIcon;
    public WebElement getMyBlackBoardIcon(){ return myBlackBoardIcon; }


    @FindBy(css = "span[class='ls---black-board-go-back-to-my-blackboard-text']")
    WebElement goToMyBlackBoard;//go back to my black board in main navigator
    public WebElement getGoToMyBlackBoard(){ return goToMyBlackBoard; }

    @FindBy(xpath = "(//div[@class='number'])[3]")
    WebElement questionAttempted;
    public WebElement getQuestionAttempted(){return questionAttempted;}

    @FindBy(xpath = "//div[contains(@class,'box avg-practice-performance')]/div[3]")
    public  WebElement questionPerformance; //question performance on the student dashboard

    @FindBy(css = "i[class='ls-icon-img ls--arrow-down-icon']")
    WebElement profileDropDown;
    public WebElement getProfileDropDown(){return profileDropDown;}

    @FindBy(className = "ls-dashboard-activities-chaptername")
    public List<WebElement> assessmentLink;

    @FindBy(xpath = "//div[@class='middle']/p")
    public WebElement assessment;

    @FindBy(className= "ls-file-assignment-icon")
    public WebElement assignmentIcon;

    @FindBy(css = "div[class='number number2']")
    public WebElement timeSpent;

    @FindBy(className = "rank")
    public WebElement overallScore;

    @FindBy(xpath = "//div[@class='box class-participation-score']/div[3]/p[2]")
    public WebElement percentageSymbol;

    @FindBy(css = "div[class='box class-participation-score']")
    public WebElement participationRating_tile;

    @FindBy(id="participationRating")
    public WebElement participationRating_help;

    @FindBy(className = "ls-dashboard-help-data-container")
    public WebElement participationRating_helpText;

    @FindBy(xpath = "//div[@class='percent']/p[2]")
    public List<WebElement> percent_value;

    @FindBy(xpath = "//div[@class='box class-participation-score']/div[2]/p[2]")
    public WebElement contribution_perStudent;

    @FindBy(xpath = "//div[@class='percent']/p[text()='Total class contributions:']/following::p[1]")
    public WebElement contribution_value;

    @FindBy(xpath = "//ul[@class='sub-nav show-sub-nav']/li/a")
    public List<WebElement> subMenu;

    @FindBy(className = "middle")
    public WebElement postedAssignmentText;

    @FindBy(className = "ls-chapter-tree")
    public WebElement mainNavigatorList;

    @FindBy(className = "ls--course-stream")
    public WebElement menu_courseStream;

    @FindBy(xpath = "//div[contains(@class,'news news2')]/div[3]")
    public WebElement assignment_Link;
}
