package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by pragya on 02-04-2015.
 */
public class TakeAssignment {

        @FindBy(xpath = "//li[starts-with(@class,'question-link-label')]")
        public List<WebElement> question_number;//List of Question number while attempting the question

        @FindBy(id = "as-take-next-question")
        public WebElement button_next;//Next button while attempting question

        @FindBy(className = "true-false-student-content-text")
        public List<WebElement> trueFalse;

        @FindBy(css = "span[class='btn sty-blue save submit-button']")
        public WebElement finishButton;

        @FindBy(xpath = "//span[contains(@class,'btn sty-blue submit')]")
        public WebElement submitButton;

        @FindBy(css = "ul[class='assignment-all-questions-navigation-content list-inline m-sm'] > li")
        public List<WebElement> navigateToQuestion;

        @FindBy(className = "choice-value")
        public List<WebElement> multipleSelectionAnswerList;

        @FindBy(className = "redactor_editor")
        public WebElement answerEssayText;

        @FindBy(css = "div.true-false-student-answer-select")
        public List<WebElement> trueFalseOptions; //True False options

        @FindBy(css = "div.quit-assignment-clock-icon")
        public WebElement closeIcon; //Close icon

        @FindBy(xpath = "//div[starts-with(@class,'assessmentSummary-content')]/following-sibling::h3")
        public List<WebElement> labels_performance;//Performance summary ans performance by question labels

        @FindBy(className = "control-label")
        public WebElement questiontext;//Question name


    }

