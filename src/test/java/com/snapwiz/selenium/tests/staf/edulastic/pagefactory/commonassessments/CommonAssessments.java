package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.commonassessments;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by pragyas on 18-08-2016.
 */
public class CommonAssessments {

    @FindBy(xpath = "//h3[contains(@class,'show-response-wrapper as-title twoline-ellipsis')]")
    public List<WebElement> assessmentName;//Assessment name

    @FindBy(css = "div.icheckbox_square-green")
    public List<WebElement> checkboxInStudentCard;

    @FindBy(css = "button[class^='btn release-feed btn-rounded ']")
    public WebElement releaseGrade;

    @FindBy(linkText = "Grade")
    public WebElement grade;

    @FindBy(partialLinkText = "View Responses")
    public WebElement viewResponse;

    @FindBy(xpath = "//span[@class='as-response']")
    public List<WebElement> response_link;


    @FindBy(partialLinkText = "View Responses")
    public List<WebElement> view_Response;

    @FindBy(css = "div[class^='status-label pull-lef']>span")
    public List<WebElement> grades_status;

    @FindBy(css = ".icheckbox_square-green")
    public List<WebElement> assignment_checkbox;

}
