package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.CourseStream;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/*
 * Created by sumit on 2/1/15.
 */
public class CourseStreamPage {

    @FindBy(className = "course-stream-extended-due-date-wrapper")
    WebElement extendedDueDate; //extended due date field in CS
    public WebElement getExtendedDueDate(){ return extendedDueDate; }

    @FindBys(@FindBy(className = "ls-stream-post__action"))
    List<WebElement> postedItemType;
    public List<WebElement> getPostedItemType(){ return postedItemType; }

    @FindBy(css = "span[class='ls-jumpout-icon-link ls-jumpout-icon']")
    WebElement jumpOut;
    public WebElement getJumpOut(){return jumpOut;}

    @FindBy(className = "control-label")
    WebElement question;
    public WebElement getQuestion(){return question;}

    @FindBy(css = "div[class='ls-stream-post__head']")
    public  WebElement instructorIcon;

    @FindBy(css = "span[class='ls-lesson-title ellipsis assignment-cart-item']")
    public List<WebElement> lessonName;// lesson name in student side

    @FindBy(css = "i[class='share-to-ls-image ls--note-icon']")
    public WebElement post_button;
    public WebElement getPost_button(){return post_button;}

    @FindBy(xpath = "//div[@class='audio-content-recorder-icon']")
    public WebElement microphoneIcon;
    public WebElement getMicrophoneIcon(){return microphoneIcon;}

    @FindBy(xpath = "(//div[@class='ls-right-user-content'])[1]/div[3]")
    public WebElement discussionBody;

    @FindBy(className = "ls-stream-assignment-title")
    public WebElement assessmentName;

    @FindBy(className= "ls-file-assignment-icon")
    public WebElement assignmentIcon;

    @FindBy(xpath = "//a[text()='img.png']")
    public WebElement resourceLink;

    @FindBy(css = "span[class='ls-lesson-title ellipsis']")
    public WebElement assignmentLink;

    @FindBy(css = "div[class='ls-stream-assignment-title-wrapper']")
    public WebElement assessmentNameLink;


}
