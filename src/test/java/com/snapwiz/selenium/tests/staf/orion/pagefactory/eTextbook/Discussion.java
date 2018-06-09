package com.snapwiz.selenium.tests.staf.orion.pagefactory.eTextbook;

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

    @FindBy(css = "a[class='ls-toc-btn ls-right-new-btn']")
    public WebElement newDiscussion_button;

    @FindBy(xpath = "//div[starts-with(@id,'editdiscussion-text')]")
    public WebElement discussion_text;

    @FindBy(css = "div[class='ls-comments-add-text lesson-discussion-comment redactor_editor redactor-editor-focused']")
    public WebElement StudentDiscussion_text;

    @FindBy(css = "button[class='editdiscussion-button editdiscussion-submit']")
    public WebElement submit_button;

    @FindBy(css = "div[class='post-comment lesson-discussion-comment']")
    public WebElement post_link;

    @FindBy(css = "div[class='ls-post-comment-redactor-toolbar active-toolbar']+i")
    public  WebElement audio_icon;

    @FindBy(className = "al-diag-test-question-label")
    public WebElement questionLabel;







}
