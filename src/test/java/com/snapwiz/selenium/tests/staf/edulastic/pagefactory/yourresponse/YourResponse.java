package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.yourresponse;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by pragya on 13-10-2015.
 */
public class YourResponse {
    @FindBy(id = "next-question-performance-view")
    public WebElement arrow_next;//Next arrow to navigate to next question

    @FindBy(id = "prev-question-performance-view")
    public WebElement arrow_previous;//Back arrow to navigate to previous question

    @FindBy(id="view-user-question-performance-score-box")
    public List<WebElement> scoreByQuestion;

    @FindBy(className = "student-performance-back-btn")
    public WebElement backArrowYourResponse;

    @FindBy(css = "span[id='question-points']")
    public WebElement totalScorePerQuestion;

}
