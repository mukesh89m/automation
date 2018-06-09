package com.snapwiz.selenium.tests.staf.orion.pagefactory.Settings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
   Created by root on 2/3/16.
 */
public class Setting {
    @FindBy(id = "instructor-settings-title")
    public WebElement label_settings;

    @FindBy(css = "div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")
    public WebElement button_orionTopicsON;

    @FindBy(className = "instructor-more-options-link")
    public WebElement link_moreOptions;

    @FindBy(className = "al-customize-course-toggle-block")
    public WebElement courseToggleBlock;

    @FindBy(css = "div.tloToggle.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")
    public WebElement tloOffButton;

    @FindBy(css = "div[class='chapter-practice al-chkbox-holder off-state']")
    public List<WebElement> chapterPracticeOFFButton;

    @FindBy(css = "div[class='diag-test al-chkbox-holder off-state']")
    public List<WebElement> diagnosticTestOFFButtonList;

    @FindBy(css = "span[class='al-question-help-img al-chapter-practice-help-img']")
    public WebElement chapterPracticeHelpIcon;

    @FindBy(className = "help-data-container")
    public WebElement chapterPracticeHelpIconContainer;

    @FindBy(className = "help-data-container")
    public List<WebElement> chapterPracticeHelpIconContainerList;

    @FindBy(css = "span[class='al-question-help-img al-diag-practice-help-img']")
    public WebElement diagnosticTestHelpIcon;

    @FindBy(css = "div[class='chapter-practice al-chkbox-holder on-state']")
    public WebElement chapterPracticeONOFFButton;

    @FindBy(css = "div[class='diag-test al-chkbox-holder on-state']")
    public WebElement diagnosticTestONOFFButton;

    @FindBy(className = "al-back-btn-icon")
    public WebElement orionBackIcon;

    @FindBy(className = "al-back-btn-icon")
    public List<WebElement> orionBackIconList;

    @FindBy(className = "instructor-settings-discussion-label")
    public List<WebElement> instructorSettingsDiscussionLabelList;

    @FindBy(className = "instructor-more-options-icon")
    public WebElement instructorMoreOptionsIcon;

    @FindBy(className = "al-customize-course-text")
    public WebElement allCustomizeCourseText;

    @FindBy(css = "div[class='chapterToggle al-chkbox-holder on-state']")
    public List<WebElement> chapterToggleONButtonList;

    @FindBy(css = "div[class = 'tloToggle al-chkbox-holder on-state']")
    public List<WebElement> ONButtonList;

    @FindBy(className = "al-question-help-img")
    public WebElement helpImage;

    @FindBy(css = "div[class='chapterToggle al-chkbox-holder off-state']")
    public List<WebElement> chapterOFFButton;

    @FindBy(className = "al-content-header-row")
    public WebElement chapterLabel;

    @FindBy(css = "div.al-customize-course-disabled.al-chkbox")
    public WebElement chapterOFFbutton;

    @FindBy(className = "al-terminal-objective-title")
    public List<WebElement> terminalObjectiveTitle;

    @FindBy(className = "al-terminal-objective-title")
    public WebElement terminalObjectiveTitles;

    @FindBy(css = "div.al-customize-course-enabled.al-chkbox1")
    public WebElement chapterONButton;

    @FindBy(css = "div[class='tloToggle al-chkbox-holder off-state']")
    public List<WebElement> chapterOFFState;

    @FindBy(css = "div.chapter-practice.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")
    public WebElement chapterPractice;

    @FindBy(css = "a[title='Practice']")
    public WebElement practiceLink;

    @FindBy(css = "div.tloToggle.al-chkbox-holder > div.al-customize-course-enabled.al-chkbox1")
    public WebElement tloButton;

    @FindBy(css = "div.diag-test.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")
    public WebElement diagnosticChapterOFFButton;

    @FindBy(className = "al-notification-message-body")
    public WebElement notificationMessage;

    @FindBy(linkText = "Yes")
    public WebElement yesLink;

    @FindBy(linkText = "No")
    public WebElement noLink;

    @FindBy(className = "al-content-body-wrapper-enabled")
    public List<WebElement> contentBodyWrapper;

    @FindBy(className = "al-proficiency-pin")
    public List<WebElement> proficiencyPin;

    @FindBy(css = "div.diag-test.al-chkbox-holder > div.al-customize-course-enabled.al-chkbox1")
    public WebElement oFFDiagnosticChapter;

    @FindBy(css = "span[class='al-terminal-objective-title  student-metacognitive-report']")
    public WebElement studentName;

    @FindBy(id = "al-performance-report")
    public WebElement performanceReport;

    @FindBy(className = "coursePerformanceByChaptersForStudent-xAxisLabel")
    public WebElement chapterNameLabel;

    @FindBy(xpath = "//span[contains(@class,'al-terminal-objective-title')]")
    public WebElement studentFullName;

}
