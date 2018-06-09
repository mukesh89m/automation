package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/**
 * Created by priyanka on 12/19/2014.
 */
public class MatchTheFollowingPreviewPage {
    @FindBy(css="div[class='stud-dnd-match-rhs ui-droppable']")
    WebElement blankSpace;
    public WebElement getBlankSpace(){
        return  blankSpace;
    }
    @FindBy(css="div[class='answer-con match formula-to-control']")
    WebElement answerOption;
    public WebElement getAnswerOption(){
        return answerOption;
    }
    @FindBy(id="question-reveview-submit")
    WebElement get_submit;
    public WebElement getGet_submit(){
        return get_submit;
    }
    @FindBy(xpath="(//div[contains(@class,'match-answer match-answer-draggable cursor-pointer ')])[1]")
    WebElement get_answerchoice1;
    public WebElement getGet_answerchoice1(){
        return get_answerchoice1;
    }
    @FindBy(xpath="(//div[contains(@class,'match-answer match-answer-draggable cursor-pointer ')])[2]")
    WebElement get_answerchoice2;
    public WebElement getGet_answerchoice2(){
        return get_answerchoice2;
    }
    @FindBy(xpath="(//div[contains(@class,'match-answer match-answer-draggable cursor-pointer ')])[3]")
    WebElement get_answerchoice3;
    public WebElement getGet_answerchoice3(){
        return get_answerchoice3;
    }
    @FindBy(xpath="(//div[contains(@class,'match-answer match-answer-draggable cursor-pointer ')])[4]")
    WebElement get_answerchoice4;
    public WebElement getGet_answerchoice4(){
        return get_answerchoice4;
    }
    @FindBy(xpath="(//div[contains(@class,'match-answer match-answer-draggable cursor-pointer ')])[5]")
    WebElement get_answerchoice5;
    public WebElement getGet_answerchoice5(){
        return get_answerchoice5;
    }

    @FindBy(xpath = "(//div[contains(@class,'stud-dnd-match-rhs ui-droppable')])[1]")
    WebElement get_blankspace1;
    public WebElement getGet_blankspace1(){
        return get_blankspace1;
    }
    @FindBy(xpath = "(//div[contains(@class,'stud-dnd-match-rhs ui-droppable')])[2]")
    WebElement get_blankspace2;
    public WebElement getGet_blankspace2(){
        return get_blankspace2;
    }
    @FindBy(xpath = "(//div[contains(@class,'stud-dnd-match-rhs ui-droppable')])[3]")
    WebElement get_blankspace3;
    public WebElement getGet_blankspace3(){
        return get_blankspace3;
    }
    @FindBy(xpath = "(//div[contains(@class,'stud-dnd-match-rhs ui-droppable')])[4]")
    WebElement get_blankspace4;
    public WebElement getGet_blankspace4(){
        return get_blankspace4;
    }
    @FindBy(xpath = "(//div[contains(@class,'stud-dnd-match-rhs ui-droppable')])[5]")
    WebElement get_blankspace5;
    public WebElement getGet_blankspace5(){
        return get_blankspace5;
    }

    @FindBy(css = "div[class='stud-dnd-match-rhs ui-droppable wrong-answer']")
    WebElement wronganswer;
    public WebElement getWronganswer(){
        return wronganswer;
    }
    @FindBy(css = "div[class='stud-dnd-match-rhs ui-droppable correct-answer']")
    WebElement correctanswer;
    public WebElement getCorrectanswer(){
        return correctanswer;
    }
}
