package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.eTextbook;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Mukesh on 1/9/15.
 */
public class Discussion {
    @FindBy(css="div[class='ls-comment-entry  ls-feedback-comment']")
    WebElement insFeedback;//instructor feedback in student side in discussion tab
    public WebElement getInsFeedback(){return insFeedback;}

    @FindBy(partialLinkText="Perspectives")
    WebElement perspectives;//Perspectives
    public WebElement getPerspectives(){return perspectives;}

    @FindBy(className="navigate-to-jump-out-icon")
    WebElement link_enterSubmission;//link 'Enter Submission'
    public WebElement getLink_enterSubmission(){return link_enterSubmission;}

    @FindBy(xpath="//span[text()='Discussion']")
    WebElement tab_discussion;//Tab 'Discussion'
    public WebElement getTab_discussion(){return tab_discussion;}

}
