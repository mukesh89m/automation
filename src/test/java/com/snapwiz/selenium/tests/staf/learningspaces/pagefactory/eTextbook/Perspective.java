package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by mukesh on 26/2/16.
 */
public class Perspective {
    @FindBy(className = "ls-post-like-link")
    public List<WebElement> perspective_postLike;

    @FindBy(className = "js-discussion-comment-like")
    public List<WebElement> perspective_commentLike;

    @FindBy(css = "a[class='ls-content-post__footer-perspective-link js-toggle-comments']")
    public WebElement perspective_link;

    @FindBy(className = "js-comment-like")
    public List<WebElement> commentLike;

    @FindBy(className = "ls-perspctive-comments-posted")
    public List<WebElement> perspective_commentText;

    @FindBy(className = "ls-comment-entry")
    public WebElement commentEntry;

    @FindBy(css="div[id='idb-gradeBook-title']")
    public List<WebElement> gradeBook_title;

    @FindBy(css="div[class='instructor-dw-response-student-details group-name-title']")
    public WebElement groupName;

    @FindBy(className = "perspective-word-count-wrapper")
    public WebElement perspective_word_count;

    @FindBy(className = "instructor-dw-comments-count-wrapper")
    public WebElement perspective_dwComment_count;

    @FindBy(css = "li[class='ls-stream-post-comment  '] div[class='ls-comment-entry']")
    public List<WebElement> lsCommentEntry;

    @FindBy(css = "li[class*='other-perspectives']")
    public List<WebElement> hide_perspective;

    @FindBy(xpath = "//li[@class='ls-stream-post-comment  ']//a[@title='Comments']")
    public List<WebElement> allComments;

    @FindBy(css = "time[class='ls-time-stamp ls-already-formatted']")
    public List<WebElement> time_stamp;

    @FindBy(id="view-other-perspective-link")
    public WebElement view_other_perspective_link;

    @FindBy(className = "ls-content-post-comment-count")
    public WebElement commentPost_count;

    @FindBy(className = "view-group-discussion-performance-save-btn")
    public WebElement save_button;

    @FindBy(className = "yes-text-link-group-grade")
    public WebElement yes_link;

    @FindBy(css = "span[class='continue-to-view-ca-tab ls-donot-delete-assignment']")
    public WebElement no_link;

    @FindBy(id="view-user-question-performance-score-box")
    public WebElement score_box;

}
