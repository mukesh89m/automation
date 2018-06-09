package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.Tabs;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Mukesh on 1/27/15.
 */
public class DiagnosticTab {

    @FindBy(className = "next-chart")
    public WebElement nextChart; //click on the nextChart toogle

    @FindBy(xpath = "//div[text()='4.1']")
    public WebElement questionPerformanceBarContent;

    @FindBy(className = "cms-deactivated-question")
    public WebElement deActivatedMsg;

    @FindBy(css = "span[class='ls-jumpout-icon-link ls-jumpout-icon']")
    public WebElement jumpOut_icon;

    @FindBy(css = "div[class='add-content-error show-content-issues-dialog']")
    public WebElement contentIssue_icon;

    @FindBy(css = "span[class='discus-icon item-icon']")
    public List<WebElement> discussion_icon; //discussion icon in myActivity page

    @FindBy(css = "label[class='control-label']")
    public WebElement questionName;

    @FindBy(css = "span[id='ls-preformance-static-assessment-total-count']")
    public WebElement questionCount;

    @FindBy(xpath = "//div[@class='completed-subevents']//a")
    public List<WebElement> practiceQuestionTab;

    @FindBy(css = "div[class='question-card-question-content']")
    public WebElement questionCard;

    @FindBy(className = "question-card-question-no")
    public WebElement questionCardNumber;

    @FindBy(className = "al-diagtest-score-view")
    public WebElement studentGotCorrectPer; //student got it correct msg in about tab

    @FindBy(xpath = "//*[@id='al-performance-chart-label-id']/span")
    public WebElement questionCountUnderPerformace;

    @FindBy(xpath = "//div[@class='question-card-question-content']")
    public List<WebElement> questionCardList;

    @FindBy(xpath = "//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")
    public List<WebElement> searchETextBook;

    @FindBy(xpath = "//div[@id='question-raw-content-preview']/label")
    public List<WebElement> questionCardListInstructor;//Question text at instructor level

    @FindBy(xpath = "//div[@id='question-raw-content-preview']/label")
    public WebElement questionContext;//Question text at instructor level



}
