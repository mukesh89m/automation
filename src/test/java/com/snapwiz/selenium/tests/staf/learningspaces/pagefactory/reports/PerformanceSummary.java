package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by Dharaneesh T Gowda on 12-03-2015.
 */
public class PerformanceSummary {
    @FindBys(@FindBy(className = "question-card-question-content"))
    List<WebElement> questionCardList;
    public List<WebElement> getQuestionCardList(){return questionCardList;}

    @FindBy(className = "al-performance-chart-title")
    public WebElement title_performanceSummary;

    @FindBy(id = "question-raw-content-preview")
    public WebElement questionContent;

    @FindBy(xpath = "//div[contains(@class,'report-sidebar-question-card-sectn')]")
    public  List<WebElement> questionCart;

    @FindBy(id = "cms-question-preview-wrapper")
    public WebElement question_Content;

    @FindBy(className = "report-chart-title")
    public WebElement titlePerformanceSummary;

    @FindBy(id = "performance-bar-chart-wrapper")
    public WebElement performanceByQuestionWrapper;

}
