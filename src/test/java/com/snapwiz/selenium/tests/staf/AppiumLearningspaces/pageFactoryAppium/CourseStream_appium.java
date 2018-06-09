package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pageFactoryAppium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by snapwizmacmini2 on 11/3/15.
 */
public class CourseStream_appium {

    @FindBy(xpath = "//UIALink[@name='Post a discussion']")
    public WebElement post_discussion;

    @FindBy(xpath = "//UIALink[@name='Post a link']")
    public WebElement post_link;

    @FindBy(xpath = "//UIALink[@name='Post a file']")
    public WebElement post_file;

    @FindBy(xpath = "//UIAImage")
    public List<WebElement> courseSream_options ;

    @FindBy(xpath = "//UIAStaticText[@name='Share With:']")
    public WebElement shareWith;

    @FindBy(xpath = "//UIAStaticText[@name='posted a discussion']")
    public WebElement discussionPosted;

    @FindBy(xpath = "//UIAStaticText[@name='shared a link']")
    public WebElement sharedLink;

    @FindBy(xpath = "//UIAStaticText[@name='www.google.co.in']")
    public WebElement google_link;





}
