package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.CMS;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by root on 18/12/14.
 */
public class NewQuestionDataEntry {

    public static final String commonParams = "//div[@id ='commonparameters']//div[starts-with(@class,'parameter')]";

    @FindBy(xpath = "//div[@id='score']/h4")
    public WebElement label_ScoreText;
    public WebElement getLabel_ScoreText() {return label_ScoreText; }

    @FindBy(xpath = "//input[@id='questionScore']")
    public WebElement value_QuestionScore;
    public WebElement get_QuestionScore() {return value_QuestionScore; }

    @FindBy(id = "assessmentName")
    public WebElement assessmentName;
    public WebElement textField_assessmentName() {return assessmentName; }

    @FindBy(id = "questionSetName")
    public WebElement questionSetName;
    public WebElement textField_questionSetName() {return questionSetName; }

    @FindBy(id = "question-raw-content")
    public WebElement questionText;
    public WebElement textField_questionText() {return questionText; }

    @FindBy(className = "true-false-answer-label")
    public WebElement trueFalseAnswerLabel;
    public WebElement button_trueFalseAnswerLabel() {return trueFalseAnswerLabel;}

    @FindBy(id = "content-solution")
    public WebElement solutionText;
    public WebElement textField_SolutionText() {return solutionText;}

    @FindBy(id="question-type-title")
    public WebElement questionType;
    public WebElement getQuestionType(){return questionType;}

    @FindBy(xpath = "//a[@title='QA']")
    public WebElement questionStatus;

    @FindBy(id="preview-the-image-quiz")
    public WebElement preview_link;

 /*   @FindBy(id="isShuffleAnswerChoice")
    public WebElement shuffle_checkbox; //shufle checkBox for multiple selection*/

    @FindBy(id="shuffle-answer-choices-checkbox")
    public WebElement shuffle_checkbox; //shufle checkBox for multiple selection

    @FindBy(xpath = "//span[@id='saveQuestionDetails1']")
    public WebElement save_button;

    @FindBy(css = "div[class='multiple-select-choice-icon multiple-select-choice-icon-select']")
    public WebElement selected_multipleSelection; //selected multiple selection option

    @FindBy(id="hint")
    public WebElement hint_textBox; //hint textBox

    @FindBy(xpath = "//a[text()='Jump To Q#']")
    public WebElement jumpToQuestionSelector; //jump To Question Selector besides question type

    @FindBy(className = "qtn-label-selected")
    public WebElement selected_multipleChoice; ////selected multiple Choice option

    @FindBy(id="shuffle-answer-choices-checkbox")
    public WebElement multipleChoiceShuffle_checkbox; //shufle checkBox for multiple choice

    @FindBys(@FindBy(xpath = "(//div[@class='overview'])[2]/li/following-sibling::li/a"))
    List<WebElement> list_questionsCreated_dropdown;//List of questions created in dropdown
    public List<WebElement> getList_questionsCreated_dropdown(){return list_questionsCreated_dropdown;}

    @FindBys(@FindBy(className = "wistia_poster"))
    List<WebElement> list_videoInQuestion;//Added video in question
    public List<WebElement> getList_videoInQuestion(){return list_videoInQuestion;}

    @FindBy(className = "question-set-name")
    WebElement setName_question;//Question set name
    public WebElement getSetName_question(){return setName_question;}

    @FindBy(xpath = "//div[contains(@id,'wistia_bigPlayButton')]")
    WebElement playVideo;//Play button to play the video
    public WebElement getPlayVideo(){return playVideo;}












}
