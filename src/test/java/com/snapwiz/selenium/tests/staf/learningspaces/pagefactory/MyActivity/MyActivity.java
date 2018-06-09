package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyActivity;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/*
 * Created by sumit on 5/1/15.
 */
public class MyActivity {

    @FindBy(className = "no-activities-msg-wrapper")
    WebElement noActivityMessage;//no Activity exist in My Activity page
    public WebElement getNoActivityMessage() {
        return noActivityMessage;
    }

    @FindBy(xpath = "(//a[@class='subevent-link'])[1]")
    WebElement discussion_Link;
    public WebElement getDiscussion_Link(){return discussion_Link;}


    @FindBy(css = "span[class ='discus-icon item-icon']")
    WebElement discussionLinkIcon;
    public WebElement getDiscussionLinkIcon(){return discussionLinkIcon;}

    @FindBy(className = "assessment-title")
    public WebElement assessmentLink;

    @FindBy(xpath = "//div[contains(@class,'act-header stamp')]/p")
    public WebElement myActivityTitle;

    @FindBy(css="p[class='subevents-subtitle']>a")
    public WebElement assignment_card;

    @FindBy(xpath = "//a[@resourcename='ORION - Ch 2: The Chemical Foundation of Life']")
    WebElement link_completedPractieAssignment;
    public WebElement getLink_completedPractieAssignment(){return link_completedPractieAssignment;}

    @FindBy(css = "p[class='card-title']")
    public List<WebElement> cardTitle;

    @FindBy(css = "a[asstype='ORION Adaptive practice']")
    public List<WebElement> assessment_title;

    @FindBy(css = "a[class='subevent-link']")
    public List<WebElement> noteLink;


}
