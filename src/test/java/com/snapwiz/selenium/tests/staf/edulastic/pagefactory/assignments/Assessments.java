package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by root on 12/29/14.
 */
public class Assessments {

    @FindBy(css = "a[class='btn btn-blue btn-rounded']")
    WebElement button_newAssessment;// 'New Assessment' button to create a new assessment
    public WebElement getButton_newAssessment() {
        return button_newAssessment;
    }

    @FindBy(css = "h4.as-title ")
    WebElement assignment;// 1st Assignment to view by student
    public WebElement getAssignment(){return  assignment;}

    @FindBy(className = "numberLine-dnd-answer-area")
    WebElement answerChoice_leftPanel;//Answer choice area to drop the answer
    public WebElement getAnswerChoice_leftPanel(){return answerChoice_leftPanel;}

    @FindBy(xpath = "//div[@id='numberLineAnsBox_0']//div[@class='swformula']")
    WebElement droppedAnswerChoice_0thIndex;//Dropped answer choice on 0th index
    public WebElement getDroppedAnswerChoice_0thIndex(){return droppedAnswerChoice_0thIndex;}

    @FindBy(xpath = "//div[@id='numberLineAnsBox_2']//div[@class='swformula']")
    WebElement droppedAnswerChoice_2ndIndex;//Dropped answer choice on 2nd index
    public WebElement getDroppedAnswerChoice_2ndIndex(){return droppedAnswerChoice_2ndIndex;}

    @FindBy(xpath = "//div[@id='numberLineAnsBox_1']//div[@class='swformula']")
    WebElement droppedAnswerChoice_1stIndex;//Dropped answer choice on 1st index
    public WebElement getDroppedAnswerChoice_1stIndex(){return droppedAnswerChoice_1stIndex;}

    @FindBy(className = "student-report-continue-button")
    WebElement button_continue;//Continue button
    public WebElement getButton_continue(){return button_continue;}

    @FindBy(id="as-take-next-question")
    WebElement button_submit;//Submit button
    public WebElement getButton_submit(){return button_submit;}

    @FindBy(id="view-user-question-performance-score-box")
    WebElement score;//Score of student
    public WebElement getScore(){return  score;}

    @FindBy(className = "correct-answer-link")
    WebElement link_showCorrectAnswer;//Show correct answer link
    public WebElement getLink_showCorrectAnswer(){return link_showCorrectAnswer;}

    @FindBy(className = "your-response-link")
    WebElement link_showYourAnswer;//Show you answer link
    public  WebElement getLink_showYourAnswer(){return  link_showYourAnswer;}

    @FindBy(css="span[id='show-your-work-label']")
    WebElement showOrHideYourWork;//Show or Hide your work label
    public WebElement getShowOrHideYourWork(){return showOrHideYourWork;}

    @FindBy(className = "question-performance-score")
    WebElement scoreBoard;
    public WebElement getScoreBoard(){return scoreBoard;}

    @FindBy(css = "span[class='btn sty-blue submit-button']")
    WebElement final_submit;//Submit button before continue button
    public WebElement getFinal_submit(){return final_submit;}


    @FindBy(className = "as-response")
    WebElement view_grades;//View responses or View grades
    public  WebElement getView_grades(){return view_grades;}

    @FindBy(className = "as-question-manually-graded")
    WebElement grade_nonGradable;//grade of student when the assignment is non gradable(1st assessment)
    public  WebElement getGrade_nonGradable(){return grade_nonGradable;}

    @FindBy(className = "idb-gradebook-question-content")
    WebElement grade_gradable;//grade of student when the assignment is non gradable(1st assessment)
    public WebElement getGrade_gradable(){return grade_gradable;}

    @FindBys(@FindBy(className = "idb-gradebook-question-content"))
    List<WebElement> list_grade_gradable;//grade of student when the assignment is non gradable(1st assessment)
    public List<WebElement> getList_grade_gradable(){return list_grade_gradable;}

    @FindBys(@FindBy(xpath = "//div[@class='dnd-preview-answer answer']"))
    List<WebElement> list_answerChoice;//List of answer choice
    public List<WebElement> getList_answerChoice(){return list_answerChoice;}

    @FindBys(@FindBy(xpath = "//div[@class='dnd-ans-choice-div']/div"))
    List<WebElement> list_answerChoiceDisabled;//List of answer choice to identify enabled or disabled answer choice
    public List<WebElement> getList_answerChoiceDisabled(){return list_answerChoiceDisabled;}

    @FindBy(className = "as-print")
    WebElement button_print;//'print' button
    public WebElement getButton_print(){return button_print;}

    @FindBy(xpath = "(//div[@class='classification-div-header'])[2]/../div[2]")
    WebElement answers_class2;//Answers present in class2
    public WebElement getAnswers_class2(){return answers_class2;}

    @FindBy(xpath = "(//div[@class='classification-div-header'])[1]/../div[2]")
    WebElement answers_class1;//Answers present in class1
    public WebElement getAnswers_class1(){return answers_class1;}

    @FindBy(css = "div[class='class-col-answer wrong-answer dnd-previewDropArea']")
    WebElement wrongAnswer_class2;//Wrong answer present in class2
    public WebElement getWrongAnswer_class2(){return wrongAnswer_class2;}

    @FindBy(css="div[class='class-col-answer correct-answer dnd-previewDropArea']")
    WebElement correctAnswer_class2;//Correct answer present in class2
    public WebElement getCorrectAnswer_class2(){return correctAnswer_class2;}

    @FindBy(className = "control-label")
    WebElement question_text;//Question text
    public WebElement getQuestion_text(){return question_text;}

    @FindBy(className = "quit-assignment")
    WebElement button_exit;//Exit button while attempting the question in student's side
    public WebElement getButton_exit(){return button_exit;}

    @FindBy(id="question-points")
    WebElement questionpoint;//Question point on student's side
    public WebElement getQuestionpoint(){return questionpoint;}

    @FindBy(className = "as-question-preview-back-button")
    WebElement backarrow_assessmentResponse;//Back button to navigate to assessment response page
    public WebElement getBackarrow_assessmentResponse(){return backarrow_assessmentResponse;}

    @FindBy(id = "next-question-performance-view")
    WebElement button_next;//Next button to go to next question in student's side question performance view
    public WebElement getButton_next(){return button_next;}

    @FindBy(id = "view-user-question-performance-feedback-box")
    WebElement feedback_content;//Feedback given by instructor
    public WebElement getFeedback_content(){return feedback_content;}

    @FindBy(className = "view-user-question-performance-save-btn")
    WebElement button_save;//Save button on instructor side after opening the attempted question by student
    public WebElement getButton_save(){return button_save;}

    @FindBy(className = "user-performance-back-btn")
    WebElement button_back;//Back button on instructor side after opening the attempted question by student
    public WebElement getButton_back(){return button_back;}

    @FindBy(xpath = "(//*[@fill='#73B966'])[2]")
    WebElement barGraph_question;//Question wise bar graph
    public WebElement getBarGraph_question(){return barGraph_question;}

    @FindBy(css = "a[class^='btn btn-blue btn-rounded']")
    public WebElement button_createCommonAssessment;//Create common assessment button



}




