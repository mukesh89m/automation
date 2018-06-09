package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.Tabs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by pragya on 25-05-2015.
 */
public class About {

    @FindBy(xpath = "//div[starts-with(@class,'static-assessment-')]")
    List<WebElement> texts;//Texts on top under about tab
    public List<WebElement> getTexts(){return texts;}

    @FindBy(className = "al-content-box-title")
    WebElement questionDifficulty;//Question Difficulty text
    public WebElement getQuestionDifficulty(){return questionDifficulty;}

    @FindBy(xpath = "//div[@class='al-difficulty-level-block al-content-box-body']")
    WebElement difficultyLevelBlock;//Difficulty Level block
    public WebElement getDifficultyLevelBlock(){return difficultyLevelBlock;}

    @FindBy(xpath = ".//*[@class='al-content-box-title al-content-box-performance-title']")
    WebElement studyThisTopic; //Study this topic text
    public WebElement getStudyThisTopic(){return studyThisTopic;}

}
