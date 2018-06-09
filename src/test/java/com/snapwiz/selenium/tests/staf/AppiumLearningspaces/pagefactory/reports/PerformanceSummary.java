package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.reports;

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

}
