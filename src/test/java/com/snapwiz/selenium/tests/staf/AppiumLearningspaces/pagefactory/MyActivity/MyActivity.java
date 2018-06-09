package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.MyActivity;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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


    /*@FindBy(xpath = "//a[contains(@resourcename,'Personalized Practice')]")
    WebElement link_CompletedAPersonalizedPracticeAssessmentCard;
    public WebElement getLink_CompletedAPersonalizedPracticeAssessmentCard(){return link_CompletedAPersonalizedPracticeAssessmentCard;}*/



    @FindBy(css = "a[resourcename = 'Personalized Practice - Ch 2: The Chemical Foundation of Life']")
    WebElement link_CompletedAPersonalizedPracticeAssessmentCard;
    public WebElement getLink_CompletedAPersonalizedPracticeAssessmentCard(){return link_CompletedAPersonalizedPracticeAssessmentCard;}

    @FindBy(xpath = "//a[@resourcename='ORION - Ch 2: The Chemical Foundation of Life']")
    WebElement link_completedPractieAssignment;
    public WebElement getLink_completedPractieAssignment(){return link_completedPractieAssignment;}


}
