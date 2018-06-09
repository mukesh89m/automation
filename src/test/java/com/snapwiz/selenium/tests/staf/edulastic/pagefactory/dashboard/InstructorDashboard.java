package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by root on 18/12/14.
 */
public class InstructorDashboard {
    @FindBy(className = "as-response")
    WebElement button_ViewResponses; // 'View responses' button to view the responses
    public WebElement getButton_ViewResponses(){
        return button_ViewResponses;
    }

    @FindBy(css = "div[title = ' Release Grade']")
    WebElement button_releaseGradeForAll; // 'release Grade for all' button to release the grade
    public WebElement getButton_releaseGradeForAll(){
        return button_releaseGradeForAll;
    }

    @FindBy(css = "span[class = 'ellipsis recent-assignment-name inline']")
    WebElement link_activeAsssessment; // 'Active assessment names' link
    public WebElement getLink_activeAsssessment(){
        return link_activeAsssessment;
    }

    @FindBy(css = "div[class = 'as-user-label ellipsis']")
    WebElement label_userName; // User Name Value
    public WebElement getLabel_userName(){
        return label_userName;
    }

    @FindBy(id = "assignment")
    WebElement assessment;//Assessments
    public WebElement getAssessment(){return assessment;}

    @FindBy(css= "a.live-navigator")
    WebElement viewLiveResult;//Assessments
    public WebElement getViewLiveResult(){return viewLiveResult;}

    @FindBy(xpath= "//span[text()='View Responses']")
    WebElement viewResponse;//Assessments
    public WebElement viewResponseonDashboard(){return viewResponse;}

    @FindBy(id= "wrapper")
    public WebElement defaultVideo;//Default video

    @FindBy(xpath = "//iframe[@scrolling='no']")
    public WebElement videoFrame;//Video frame

    @FindBy(css = "div[class='ibox-content text-center']")
    public List<WebElement> standardMasteredAndAssessed;//standard Mastered And Assessed Count

    @FindBy(id = "breadcrumb")
    public WebElement label_dashboard;//Dashboard label

    @FindBy(xpath = "//span[@class='btn-label']/preceding-sibling::i[contains(@class,'ed-icon icon-preview')]")
    public WebElement preview;//Preview button




}
