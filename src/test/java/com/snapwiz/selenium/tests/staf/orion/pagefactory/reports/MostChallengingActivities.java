package com.snapwiz.selenium.tests.staf.orion.pagefactory.reports;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by priyanka on 30-11-2015.
 */
public class MostChallengingActivities {

    @FindBy(id = "al-most-challenging-activities-report-title")
    public WebElement mostChallengingActivityTitle;

    @FindBy(id = "ir-most-challenging-activities-report-title")
    public WebElement mostChallengingActivityTitle_instructor;

    @FindBy(id = "idb-most-challenging-activities-chapters")
    public WebElement viewByChapter_instructor;

    @FindBy(id = "al-most-challenging-activities-chapters-tab")
    public WebElement viewByChapter;


    @FindBy(id = "al-most-challenging-activities-tlo-tab")
    public WebElement viewByObjectives;

    @FindBy(id = "idb-most-challenging-activities-tlo")
    public WebElement viewByObjectives_instructor;

    @FindBy(className = "no_most_challenging_data_message")
    public List<WebElement> noMostChallengingActivityData;

    @FindBy(xpath = "//a[@title='Most Challenging Activities']")
    public WebElement mostChallengingActivityDropDown;

    @FindBy(className = "al-preformance-text")
    public List<WebElement> viewByChapterHover;

    @FindBy(xpath = "//a[@title='Practice']")
    public List<WebElement> hoverAndClickOnPractice;

    @FindBys(@FindBy(className = "al-proficiency-percentage"))
    List<WebElement> studChapProficiency;// Most Challenging Activities By Chapter Proficiency
    public List<WebElement> getStudChapProficiency(){return studChapProficiency;}

    @FindBys(@FindBy(className = "al-preformance-text"))
    public List<WebElement> studChapPerformance;//performance report

    @FindBy(className = "idb-preformance-text")
    public List<WebElement> instructorChapterPerformance;//performance report

    @FindBy(id="ls-most-challenging-activities-tlo")
    WebElement studViewObjective_Tab;//View By Objective Tab
    public WebElement getStudViewObjective_Tab(){return studViewObjective_Tab;}

    @FindBy(xpath = "(//div[@class='al-content-body-first'])[2]")
    public WebElement objectiveName_viewByObjective;

    @FindBy(className = "al-terminal-objective-title")
    public  WebElement chapterName_viewByChapter;//in student side

    @FindBy(xpath = "//div[@class='al-chapter-performance-content-header-row']/div")
    public List<WebElement> chapterPerformance_Header;

    @FindBy(id = "ir-most-challenging-activities-report-title")
    public WebElement mostChallengingReportTitle;
}
