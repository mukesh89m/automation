package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.preview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by pragyas on 16-03-2016.
 */
public class PrintPreview {

    @FindBy(id = "view-user-question-performance-score-box")
    public List<WebElement> marksObtained ; //Marks obtained by student

    @FindBy(id = "question-points")
    public List<WebElement> totalPoint;//Total point of a question

    @FindBy(xpath = "//div[@id='question-raw-content-preview']")
    public List<WebElement> questionNames;//Question names

    @FindBy(className = "cinnabar")
    public WebElement totalScore;//Points earned/Maximum points


}
