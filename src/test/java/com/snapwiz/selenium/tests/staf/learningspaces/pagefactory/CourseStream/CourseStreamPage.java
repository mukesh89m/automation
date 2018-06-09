package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream;

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

    @FindBy(xpath = "//*[@class='audio-content-recorder-icon']")
    public WebElement audioIconInResources;

    @FindBy(xpath = "(//div[@class='ls-right-user-content'])[1]/div[3]")
    public WebElement discussionBody;

    @FindBy(className = "ls-stream-assignment-title")
    public WebElement assessmentName;

    @FindBy(className = "ls-stream-assignment-title")
    public List<WebElement> assignmentName;

    @FindBy(className= "ls-file-assignment-icon")
    public WebElement assignmentIcon;

    @FindBy(xpath = "//a[text()='img.png']")
    public WebElement resourceLink;

    @FindBy(css = "span[class='ls-lesson-title ellipsis']")
    public WebElement assignmentLink;

    @FindBy(css = "div[class='ls-stream-assignment-title-wrapper']")
    public WebElement assessmentNameLink;

    @FindBy(xpath = "//label[@id='question-content-label']/inline")
    public WebElement questionText;

    @FindBy(xpath = "//a[contains(@class,'btn btn--primary btn--large btn--submit')]")
    public WebElement finishAssignment;

    @FindBy(className = "ls-shareImg")
    public WebElement textEditor;

    @FindBy(xpath = "//a[@title='Comments']")
    public List<WebElement> commentLinkIn_CSPage; //comment link in course stream page

    @FindBy(xpath = "//a[@title='Like']")
    public List<WebElement> likeLinkIn_CSPage; //Like link in course stream page

    @FindBy(css = "a[class='re-icon re-language redactor-btn-image']")
    public List<WebElement> languagePaletteIcon_CSPage; //Language Palette icon in course stream page

    @FindBy(xpath = "//div[@name='comment']")
    public List<WebElement> commentBox;

    @FindBy(xpath = "//div[starts-with(@class,'post-comment')]")
    public List<WebElement> post_Link;

    @FindBy(className = "ls-stream-post__comment-text")
    public List<WebElement> postComment_content;

    @FindBy(xpath = "//img[@id='languages']")
    public List<WebElement> languages;

    @FindBy(xpath = "//ul[contains(@class,'ls-supportive-languages-container')]/li")
    public List<WebElement> languageOptions;

    @FindBy(id="sw-language-palette-wrapper")
    public WebElement langaugePalette_popup;

    @FindBy(className = "palette-close-icon")
    public WebElement langaugePalette_closeIcon;

    @FindBy(className = "cancel-language-dialog")
    public WebElement langaugePalette_cancelButton;

    @FindBy(id="save-language-text")
    public WebElement langaugePalette_saveButton;

    @FindBy(css="div[class='language-palette-title palette-header']")
    public WebElement langaugePalette_header;

    @FindBy(id = "iframe-user-text-input-div")
    public WebElement frame;

    @FindBy(id = "post-submit-cancel-button")
    public WebElement cancelPost;

    @FindBy(xpath = "//img[@id='forecolor']")
    public List<WebElement> textColour;

    @FindBy(id = "post-submit-button")
    public  WebElement submitButton;

    @FindBy(className = "ls-link-span")
    public WebElement postText;

    @FindBy(xpath = "//a[@aria-label='Post a link']")
    public WebElement link_link;

    @FindBy(id = "iframe-user-link-input-div")
    public WebElement frame_Link;

    @FindBy(xpath = "//a[@aria-label='Post a file']")
    public WebElement file_link;

    @FindBy(id = "iframe-user-file-input-div")
    public WebElement frame_File;

    @FindBy(className = "ls-stream-post__body")
    public WebElement courseStream_content;

    @FindBy(xpath = "//div[starts-with(@id,'ls-comments-add-text-')]")
    public WebElement commentContentIn_editComment;

    @FindBy(className = "ls-post-tab")
    public WebElement tab_post;

    @FindBy(className = "item-text")
    public WebElement sharedName;

    @FindBy(css = "a[title='Bookmark']")
    public List<WebElement> bookMark;

    @FindBy(css = "a[title='Remove bookmark']")
    public List<WebElement> unbookMark;

    @FindBy(className = "share-to-ls-body")
    public WebElement postTextBox;

    @FindBy(className = "ls-stream-share__title")
    public List<WebElement> discussionEntry_courseStreamPage;

    @FindBy(css="div[class='ls-media__body media_file_link']")
    public List<WebElement> fileUpload_entry;

    @FindBy(className = "ls-stream-post__username")
    public List<WebElement> posted_UserName;

    @FindBy(className = "ls-dropdown__toggle")
    public WebElement toggle;

    @FindBy(className = "ls-hide-post")
    public WebElement hide_post;

    @FindBy(css = "a[class='js-favourite active']")
    public WebElement activeTab;

    @FindBy(xpath = "//span[text()='shared a file with']")
    public WebElement sharedAFile;

    @FindBy(css = "a[class='js-post-like']")
    public WebElement post_like;

    @FindBy(className = "ls-post-like-count")
    public WebElement like_count;

    @FindBy(className = "social-policy-configuration-text")
    public WebElement socialPolicyConfiguration_text;

    @FindBy(className = "maininput")
    public WebElement classSectionSuggestion_textbox;

    @FindBy(xpath = ".//*[@id='share-with_feed']/li[1]")
    public WebElement selectClassSectionSuggestion;

    @FindBy(className = "ls-post-recipients-list")
    public List<WebElement> visualIndicatorIcon;

    @FindBy(css = "div[class='ls-shared-group-users']>span")
    public List<WebElement> group_users;

    @FindBy(css = ".ls-category-title.ellipsis")
    public List<WebElement> group_Name;

    @FindBy(className = "ls-link-span")
    public List<WebElement> postedText;

    @FindBy(className = "no-results-message")
    public  WebElement noResultMessage;

    @FindBy(className = "ls-shared-class-section")
    public List<WebElement> classSection;

    @FindBy(className = "ls-stream-learning-activity-title")
    public WebElement discussionAssignmentName;

    @FindBy(css = "span[class='ls-lesson-title ellipsis']")
    public WebElement discussionAssignmentDescription;

    @FindBy(css = "i[class='ls-icon-img ls--calendar-icon']")
    public WebElement calenderIcon;

    @FindBy(css = "div[class='ls-stream-widget-perspective ls-stream-ellipsis']")
    public WebElement perspectiveComment;

}
