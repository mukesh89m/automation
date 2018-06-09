package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by mukesh on 25/2/16.
 */
public class DiscussionTab {
    @FindBy(className = "ls-right-user-post-body")
    public List<WebElement> discussionEntry_lessonPage;

    @FindBy(css = "div[class='prow editdiscussion-wrapper']")
    public WebElement editDiscussion_wrapper;

    @FindBy(css = "div[class='editnote-wrapper prow']")
    public WebElement editNote_wrapper;

    @FindBy(css = "i[class='ls-right-section-sprites ls--right-star-icon']")
    public List<WebElement> bookmark_icon;

    @FindBy(className ="no-post-message" )
    public WebElement emptyPost;

    @FindBy(xpath = "//span[@title='Discussion']")
    public WebElement discussion_Tab;

    @FindBy(css = "i[class='ls-icon-img ls--like-icon like']")
    public WebElement like_Link;


}
