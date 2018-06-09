package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by shashank on 28-01-2015.
 */
public class Lesson
{
    @FindBy(xpath = "//div[@class='cms-assessment-publish-questions-title']/span")
    public WebElement publishQuestion_title; // publish question title after clicking on publish icon
    @FindBy(xpath = "//div[@id='cms-chapter-question-publish-tab']")
    public WebElement questionTabUnderPublish;
    @FindBy(xpath = "//div[@id='cms-chapter-lesson-publish-tab']")
    public WebElement lessonTabUnderPublish;

    @FindBy(xpath ="//input[@status='10' and @class='question-publish-status-check']/following-sibling::span")
    public WebElement textAfterCheckboxUnderQuestiontab;
    @FindBy(xpath ="//input[@status='10' and @class='lesson-publish-status-check']/following-sibling::span")
    public WebElement checkBoxDraftStatusLesson;
    @FindBy(xpath ="//input[@status='20' and @class='lesson-publish-status-check']/following-sibling::span")
    public WebElement checkBoxDraftPendingImagesStatusLesson;
    @FindBy(xpath ="//input[@status='30'and @class='lesson-publish-status-check']/following-sibling::span")
    public WebElement checkBoxAccuracyCheckStatusLesson;
    @FindBy(xpath ="//input[@status='40'and @class='lesson-publish-status-check']/following-sibling::span")
    public WebElement checkBoxQAStatusLesson;
    @FindBy(xpath ="//input[@status='50'and @class='lesson-publish-status-check']/following-sibling::span")
    public WebElement checkBoxRevisionStatusLesson;
    @FindBy(xpath ="//input[@status='60'and @class='lesson-publish-status-check']/following-sibling::span")
    public WebElement checkBoxApproveStatusLesson;
    @FindBy(xpath ="//input[@status='70'and @class='lesson-publish-status-check']/following-sibling::span")
    public WebElement checkBoxReadyToPublishStatusLesson;
    @FindBy(xpath ="//input[@status='80'and @class='lesson-publish-status-check']/following-sibling::span")
    public WebElement checkBoxPublishStatusLesson;
    @FindBy(xpath ="//input[@status='10' and @class='question-publish-status-check']/following-sibling::span")
    public WebElement checkBoxDraftStatusQuestion;
    @FindBy(xpath ="//input[@status='20' and @class='question-publish-status-check']/following-sibling::span")
    public WebElement checkBoxDraftPendingImagesStatusQuestion;
    @FindBy(xpath ="//input[@status='30'and @class='question-publish-status-check']/following-sibling::span")
    public WebElement checkBoxAccuracyCheckStatusQuestion;
    @FindBy(xpath ="//input[@status='40'and @class='question-publish-status-check']/following-sibling::span")
    public WebElement checkBoxQAStatusQuestion;
    @FindBy(xpath ="//input[@status='50'and @class='question-publish-status-check']/following-sibling::span")
    public WebElement checkBoxRevisionStatusQuestion;
    @FindBy(xpath ="//input[@status='60'and @class='question-publish-status-check']/following-sibling::span")
    public WebElement checkBoxApproveStatusQuestion;
    @FindBy(xpath ="//input[@status='70'and @class='question-publish-status-check']/following-sibling::span")
    public WebElement checkBoxReadyToPublishStatusQuestion;
    @FindBy(xpath ="//input[@status='80'and @class='question-publish-status-check']/following-sibling::span")
    public WebElement checkBoxPublishStatusQuestion;

}
