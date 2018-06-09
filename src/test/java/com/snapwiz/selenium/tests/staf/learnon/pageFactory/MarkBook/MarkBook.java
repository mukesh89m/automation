package com.snapwiz.selenium.tests.staf.learnon.pageFactory.MarkBook;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by root on 10/27/15.
 */
public class MarkBook{
    @FindBy(className = "ins-gradebook-summary-header-title")
    WebElement gradeBookHeader;
    public WebElement getGradeBookHeader(){return gradeBookHeader;}

    @FindBy(css="div.ls-ins-gradebook-weighting")
    WebElement gradebookWeighting;
    public WebElement getGradebookWeighting(){return  gradebookWeighting;}

    @FindBy(css = "input.gradebook-weight-inputBox")
    List<WebElement> enterGradebookWeighting;
    public List<WebElement> getEnterGradebookWeighting(){return enterGradebookWeighting;}

    @FindBy(css="span.ls-ins-gradebook-weighting-save-button")
    WebElement gradebookWeightingSaveButton;
    public WebElement getgradebookWeightingSaveButton(){return  gradebookWeightingSaveButton;}



}
