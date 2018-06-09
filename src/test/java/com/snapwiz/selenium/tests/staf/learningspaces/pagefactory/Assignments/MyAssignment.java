package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Dharaneesh T Gowda on 16-06-2016.
 */
public class MyAssignment{

    @FindBy(css = "div[class='ls-assessment-item-sectn-right ls-my-resource-item-section']")
    public WebElement dwSection;

    @FindBy(css = "span[class='learning-activity discussion-icon']")
    public WebElement dwIcon;

    @FindBy(css = "div[class='resource-title ls-learning-activity-rwl']")
    public WebElement dwAssignmentName;

    @FindBy(className = "resource-icon")
    public WebElement dwDescriptionIcon;

    @FindBy(xpath = "//div[@class='bookmark-assign-this-wrapper']//span")
    public WebElement actionLabel;

    @FindBy(css = "span[class='resource-assign-class action-links']")
    public WebElement assignThis;

    @FindBy(css = "div[class='ls-assessment-item-sectn-wrapper resource-separator']")
    public WebElement assignmentEntry;





}
