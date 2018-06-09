package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Profile;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by priyanka on 2/18/2015.
 */
public class Profile {
    @FindBy(xpath = "//*[@id='instructor-settings-link']/a")
    WebElement settings;//profile setting
    public WebElement getSettings(){return settings;}

    @FindBy(className = "settings-bg-img")
    WebElement setting_Title;//for setting title
    public WebElement getSetting_Title(){return setting_Title;}

    @FindBy(xpath = "//div[@class='audio-settings-wrapper']/div[1]")
    WebElement audio_Title;//for audio title
    public WebElement getAudio_Title(){return audio_Title;}

    @FindBy(xpath = "(//div[@class='settings-content-heading'])[2]")
    WebElement audioPosition;//audio title position
    public WebElement getAudioPosition(){return audioPosition;}

    @FindBy(xpath= "//span[contains(@class,'settings-help-icon audio-settings')]")
    WebElement helpicon;//help icon
    public WebElement getHelpicon(){return helpicon;}

    @FindBy(className = "help-data-container")
    WebElement help_Text;//help text
    public WebElement getHelp_Text(){return help_Text;}

    @FindBy(xpath = "//input[@id='disable']")
    WebElement disable_Radiobutton;//disable radio button
    public WebElement getDisable_Radiobutton(){return disable_Radiobutton;}

    @FindBy(id = "settings-cancel")
    WebElement cancel_Button;//cancel button
    public WebElement getCancel_Button(){return cancel_Button;}

    @FindBy(id = "settings-save")
    WebElement save_Button;//save button
    public WebElement getSave_Button(){return save_Button;}

    @FindBy(xpath = "//label[@for='audioEnable']")
    WebElement enable_RadioButton;//enable radio button
    public WebElement getEnable_RadioButton(){return enable_RadioButton;}

    @FindBy(xpath = "//label[@for='disable']")
    WebElement disableRadioButton;//disable radio button
    public WebElement getDisableRadioButton(){return disableRadioButton;}

    @FindBy(className = "notification-text-span")
    List<WebElement> notificationMessage;
    public List<WebElement> getNotificationMessage(){return notificationMessage;}

    @FindBys(@FindBy(xpath = "//a[@title='Close']"))
    WebElement closeIcon;
    public WebElement getCloseIcon(){return closeIcon;}

    @FindBy(className = "settings-content-heading")
    public List<WebElement> heading;

    @FindBy(className = "ls-notification-text-span")
    public List<WebElement> notification_Message;

   @FindBy(xpath ="//label[@for='studyPlanEnable']")
    public WebElement customButton;

    @FindBy(id = "settings-customize-link")
    public WebElement customizeNow_Link;

    @FindBy(css ="span[class='settings-help-icon customize-settings']")
    public WebElement helpIcon_CustomizeSetting;

    @FindBy(id = "top-notification-Bar")
    public WebElement topNotificationBar;

    @FindBy(className = "customizeStudyPlanNo")
    public WebElement noLinkOnNotificationMessage;

    @FindBy(className = "customizeStudyPlanYes")
    public WebElement yesLinkOnNotificationMessage;

    @FindBy(className = "customize-study-plan-text")
    public WebElement customizeStudyPlanTitle;

    @FindBy(css = "label[class='customizeChapterLevel settingsOnOff settings-on']")
    public List<WebElement> chapterLabelButton;

    @FindBy(css = "label[class='customizeTopicLevel settingsOnOff settings-on']")
    public List<WebElement> topicLabelButton;

    @FindBy(className = "customize-back-arrow")
    public WebElement customizeBackArrow;

    @FindBy(css = "label[class='customizeChapterLevel settingsOnOff']")
    public List<WebElement> chapterLabelButtonOff;

    @FindBy(className = "customize-chapter-name")
    public List<WebElement> chapterName;

    @FindBy(css = "span[class='settings-help-icon customize-plan-head']")
    public WebElement helpIconCustomizePage;

    @FindBy(className = "enabled-state")
    public List<WebElement> cannotBeDisabledLink;

    @FindBy(css = "i[class='settings-help-icon customize-plan-diagnostic']")
    public List<WebElement> helpIconDiagnostic;

    @FindBy(css = "i[class='settings-help-icon customize-plan-practice']")
    public List<WebElement> helpIconPractice;


    @FindBy(css = "i[class='settings-help-icon customize-plan-post']")
    public List<WebElement> helpIconPost;

    @FindBy(className = "changes-saved-status-box")
    public WebElement changeSavedStatusBox;


    @FindBy(css = "label[class='customizeTopicLevel settingsOnOff']")
    public List<WebElement> topicLabelButtonOff;

    @FindBy(className = "ls-user-nav__username")
    public WebElement classSection_name;

    @FindBy(id="audioEnable")
    public WebElement enable_audio;








}
