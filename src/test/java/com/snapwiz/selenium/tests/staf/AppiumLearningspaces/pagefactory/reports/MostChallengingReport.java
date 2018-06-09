package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.reports;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by Mukesh on 12/31/14.
 */
public class MostChallengingReport {


    @FindBy(id="ir-most-challenging-activities-report-title")
     WebElement title;// Most Challenging Activities title
     public WebElement getTitle(){return  title;}

    @FindBys(@FindBy(className = "al-proficiency-percentage"))
    List<WebElement> chapProficiency;// Most Challenging Activities By Chapter Proficiency
    public List<WebElement> getChapProficiency(){return chapProficiency;}

    @FindBys({@FindBy(className = "idb-preformance-text")})
    List<WebElement> chapPerformance;
    public List<WebElement> getChapPerformance(){
        return chapPerformance;
    }


    @FindBy(id="idb-most-challenging-activities-tlo")
    WebElement viewByObjective_Tab; //View By Objective Tab
    public WebElement getViewByObjective_Tab(){return  viewByObjective_Tab;}

    /*Student side*/
    @FindBys(@FindBy(className = "ls-proficiency-percentage"))
    List<WebElement> studChapProficiency;// Most Challenging Activities By Chapter Proficiency
    public List<WebElement> getStudChapProficiency(){return studChapProficiency;}

   @FindBys(@FindBy(className = "ls-preformance-report"))
    List<WebElement> studChapPerformance;//performance report
    public List<WebElement> getStudChapPerformance(){return studChapPerformance;}

    @FindBy(id="ls-most-challenging-activities-tlo")
    WebElement studViewObjective_Tab;//View By Objective Tab
    public WebElement getStudViewObjective_Tab(){return studViewObjective_Tab;}

    @FindBy(id = "ir-most-challenging-activities-report-title")
    WebElement mostChallengingReportTitle;
    public WebElement getMostChallengingReportTitle(){return mostChallengingReportTitle;}

    @FindBy(id = "reportTitle")
    WebElement mostChallengingReportPageTitle;//in student side
    public WebElement getMostChallengingReportPageTitle(){return mostChallengingReportPageTitle;}

    @FindBy (xpath  ="//div[@class='ls-report-row-right-arrow']")
    public WebElement navigateToPractice;


    @FindBy(className = "no-data-notification-message-block")
    public WebElement notificationBlock;

    @FindBy(id = "idb-most-challenging-activities-tab-content-view")
    public WebElement notification_Block;
}
