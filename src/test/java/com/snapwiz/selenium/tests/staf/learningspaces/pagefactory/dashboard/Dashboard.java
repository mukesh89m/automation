package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard;

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


    @FindBy(css = "div[class='ls-class-section-name ellipsis']")
    public WebElement defaultSectionName;

    @FindBy(xpath = "//div[@class='overview']/li[2]/a")
    public List<WebElement> classSectionDropDown;

    @FindBy(css = "div[class='sbHolder sbHolderDisabled']")
    public WebElement disabledClassSection;


    @FindBy(xpath = "//div[@class='number']")
    public List<WebElement> dashboardPerformance;

    @FindBy(xpath = "//span[@class='seconds']")
    public WebElement dashboard_AvgTime;

    @FindBy(xpath = "//span[@class='ls-assignment-progress-count']")
    public List<WebElement> submittedStatus;

    @FindBy(className = "ls-assignment-progress-text")
    public  List<WebElement> availableForStudent;

    @FindBy(id = "logout")
    public WebElement logOut;

    @FindBy(className = "ls-dashboard-container")
    public WebElement dashBoard;

    @FindBy(xpath = "//div[normalize-space(@class)='box avg-time-spent']//div[contains(@class,'number')]")
    public WebElement timeSpentTile;

    @FindBy(xpath = "//div[normalize-space(@class)='box avg-practice-questions']//div[@class='number']/p")
    public WebElement totalNumberOfQuestionAttempted;

    @FindBy(xpath = "//div[normalize-space(@class)='box avg-practice-performance']//div[@class='number']")
    public WebElement questionPerformanceInPercentage;

    @FindBy(xpath = "//div[normalize-space(@class)='box class-participation-score']//div[@class='number']")
    public WebElement participationRatingInPercentage;

    @FindBy(className = "ls-upcoming-no-data-value")
    public WebElement upcomingValue;

    @FindBy(xpath = "//*[@class='ls-site-nav-drop-down']/a")
    public WebElement toc_tree;

    @FindBy(css = "a[class='ls-chapter-navigation-icon ls-toc-sprite ls-study-plan']")
    public WebElement toc_drop;

    @FindBy(className = "upcoming-assignment-timestamp")
    public WebElement upcomingTimeStamp;
    @FindBy(xpath = "//div[@class='news news3']//b[text()='ORION  Ch 3: Biological Macromolecules']")
    public WebElement post_inDashboard;

    @FindBy(xpath = "//div[@class='middle']//p")
    public WebElement courseStream_entry;

    @FindBy(id="highcharts-0")
    public WebElement recently_graded;

    @FindBy(css = "span[class='ls-header__title-text']")
    public WebElement CourseNameOnHeader;

    @FindBy(className = "study-course-link")
    public WebElement DashboardStudyButton;

    @FindBy(className = "course-details-container")
    public WebElement courseDetailsContainer;

    @FindBy(className = "content-stream")
    public WebElement classActivityContentStream;


    @FindBy(xpath = ".//*[@id='course-stream']/div[2]/div/div[3]/p")
    public WebElement courseStreamContentStream;

    @FindBy(xpath = "//*[@id='course-details']/div[1]/div[2]")
    public WebElement course_title;

    @FindBy(xpath = "//*[@id='course-stream']//p[text()='img.png']")
    public WebElement fileUpload_entry;

    @FindBy(css = "div[class='news news3']")
    public List<WebElement> entry_courseStreamSection;

    @FindBy(className = "ls-upcoming-text")
    public  WebElement upcoming_Text;

    @FindBy(xpath  = "//div[@class='ls-dashboard-assignment-type']")
    public WebElement upcoming_assignment;

    @FindBy(xpath = "//div[contains(@class,'box class-participation-score')]/div[3]")
    public WebElement averageParticipationRating;

    @FindBy(className = "ls-dashboard-upcoming-assignment")
    public List<WebElement> upComingAssignment;

    @FindBy(css = "a[class='activity-row activity-assignment']")
    public WebElement recentActivitiesText;

    @FindBy(css = "div[class='ls-assignment-in-progress ls-ins-in-progress']")
    public WebElement availableForStudentsCount;

    @FindBy(className = "ls-site-nav-options")
    public WebElement mainNavigatorMenu;

    @FindBy(className = "ls-message-description")
    public WebElement perspectiveText;

}
