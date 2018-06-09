package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by root on 10/20/15.
 */
public class MyAssessments {

    @FindBy(xpath = "//input[@id='draft']/..")
    public WebElement draft;
    //@FindBy(xpath = "//input[@id='draft']/following-sibling::ins/span")


    @FindBy(xpath = "//div[contains(@class,'font-semi-bold')]")
    public List<WebElement> names_assessment;//list of Assessment name

    @FindBy(css = "a[class='btn btn-blue btn-rounded pull-right row']")
    public WebElement button_creteNewAssessment;//Create new assessment button

    @FindBy(xpath = "//div[contains(@class,'card-thumbnail')]")
    public List<WebElement> cards_assessments;//Assessment cards

    @FindBy(xpath = "//i[@class='ed-icon icon-preview']")
    public List<WebElement> preview;//Preview on assessment card


    @FindBy(xpath = "//a[contains(@class,'assessment-name-padding twoline-ellipsis')]")
    public List<WebElement> assignmentList;

    @FindBy(xpath = "//input[@id='published']/following-sibling::ins")
    public WebElement published;

    @FindBy(xpath = "//div[contains(@class,'card-thumbnail')]/descendant::i[@class='ed-icon icon-assignment-icon']")
    public List<WebElement> assign;//Assign on assessment card

    @FindBy(className = "as-noData-title")
    public WebElement assessmentNotAvailable;


}
