package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by durgapathi on 22/12/15.
 */
public class QuestionPage {

    @FindBy(css = "div[class='ls-student-allassignment-drop-down-wrapper scrollbar-wrapper']")
    public WebElement questionPage;

    @FindBy(css = "li[class='question-link-label s--check-enable not-attempted-question current-question-view']")
    public List<WebElement> currentQuestion;

    @FindBy(css = "li[class='question-link-label s--check-enable not-attempted-question']")
    public List<WebElement> notAttemptedQuestions;

    @FindBy(css = "li[class='question-link-label s--check-enable attempted-question']")
    public List<WebElement> attemptedQuestions;

    @FindBy(className = "resource-title")
    public WebElement assignmentName;

    @FindBy(id = "show-question-detials")
    public WebElement questionDetails;

    @FindBy(xpath = ".//ul[@class='ls-question-navigator-list']/li[@page='1']")
    public List<WebElement> questionsInPage1;

    @FindBy(xpath = ".//ul[@class='ls-question-navigator-list']/li[@page='2']")
    public List<WebElement> questionsInPage2;

    @FindBy(css = "div[class='prev-question-page ls-question-player-navigator']")
    public WebElement prePageNavigator;

    @FindBy(css = "div[class='next-question-page ls-question-player-navigator']")
    public WebElement nextPageNavigator;

    @FindBy(xpath = ".//ul[@class='ls-question-navigator-list']/li")
    public List<WebElement> allQuestions;

    @FindBy(className = "toggle-content")
    public WebElement expandButton;

    @FindBy(className = "ls-question-navigator")
    public WebElement questionNavigator;

    @FindBy(className = "true-false-student-answer-label")
    public List<WebElement> trueFalseChoices;

    @FindBy(css = "a[class='btn btn--primary btn--large btn--next']")
    public WebElement submitAnswer;

    @FindBy(className = "next-or-submit-link")
    public WebElement nextQuestion;

    @FindBy(css = "span[class='discussion-icon card-icons']")
    public WebElement discussionAssignmentDescriptionIcon;

    @FindBy(id = "timer-label")
    public List<WebElement> timeRemaining;

    @FindBy(id = "timedAssignmentTimer")
    public WebElement timedAssignmentTimer;

    @FindBy(css = "span[class='timevalue count-down-timer-warning']")
    public WebElement timeWarning;


}
