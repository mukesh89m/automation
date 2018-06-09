package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by Dharaneesh T Gowda on 12-01-2015.
 */
public class AssessmentResponses {
    @FindBy(css = "div[astatus = 'COMPLETED']")
    WebElement gradeBookContentColumn; // grade book column content link
    public WebElement getGradeBookContentColumn(){
        return gradeBookContentColumn;
    }

    @FindBy(xpath = "//div[starts-with(@title,'Release Grade')]")
    WebElement button_ReleaseGradeForAll; // Button 'Release Grade For All'
    public WebElement getButton_ReleaseGradeForAll(){
        return button_ReleaseGradeForAll;
    }


    @FindBy(xpath = "//div[starts-with(@title,'Release Feedback')]")
    WebElement button_ReleaseFeedBackForAll; // Button 'Release Feedback For All'
    public WebElement getButton_ReleaseFeedBackForAll(){
        return button_ReleaseFeedBackForAll;
    }

    @FindBy(css = "div[title = ' Release Feedback']")
    WebElement button_ReleaseFeedbackForAll; // Button 'Release Grade For All'
    public WebElement getButton_ReleaseFeedbackForAll(){
        return button_ReleaseFeedbackForAll;
    }

    @FindBy(css="div[class='as-gradebook-content-coloumn as-gradebook-question-label as-gradebook-question-coloumn']")
    WebElement questionPresentInGrid;//Question present in grid
    public WebElement getQuestionPresentInGrid(){return questionPresentInGrid;}

    @FindBys(@FindBy(css="div[class='as-gradebook-content-coloumn as-gradebook-question-label as-gradebook-question-coloumn']"))
    List<WebElement> list_questionPresentInGrid;//Question present in grid
    public List<WebElement> getList_questionPresentInGrid(){return list_questionPresentInGrid;}

    @FindBy(id="question-points")
    WebElement questionpoint;//Question point on student's side
    public WebElement getQuestionpoint(){return questionpoint;}

    @FindBy(xpath = "//a[starts-with(@class,'response-unit')]")
    WebElement response_question;//Question response
    public WebElement getResponse_question(){return response_question;}


}
