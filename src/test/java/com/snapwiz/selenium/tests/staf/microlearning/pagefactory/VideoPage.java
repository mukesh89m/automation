package com.snapwiz.selenium.tests.staf.microlearning.pagefactory;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by mukesh on 6/9/16.
 */
public class VideoPage {

    @FindBy(css = "#ml-slide-type-title")
    public WebElement slide_title;

    @FindBy(css = "#ml-video-search")
    public WebElement search_textbox;

    @FindBy(css = "#ml-nav-search-btn")
    public List<WebElement> search_button;

    @FindBy(css = "button[mode='create']")
    public WebElement addToSlide_button;

    @FindBy(css = "button[mode='update']")
    public WebElement updateSlide_button;

    @FindBy(css = "div[attr-type='video']")
    public WebElement video_link;

    @FindBy(css = "#load-more-results")
    public WebElement loadMoreResult;

    @FindBy(css = "#searched-videos-list-view>div")
    public List<WebElement> videoList;

    @FindBy(css = ".icon-angle-left")
    public List<WebElement> left_arrow;

    @FindBy(css = ".icon-add-new-slide+div")
    public WebElement newSlide_label;

    @FindBy(css = "img[class='slide-thumb img-responsive video-thumb']")
    public WebElement uploadedVideo_slide;

    @FindBy(css = "button[class='btn btn-blue btn-rounded block js-slide-edit']")
    public WebElement editSlide_link;

    @FindBy(css = "button[class='btn btn-blue btn-rounded block js-slide-delete']")
    public WebElement deleteSlide_link;

    @FindBy(css = ".lesson-preview-expand")
    public WebElement videoExpand_icon;

    @FindBy(css = "button[class='ytp-large-play-button ytp-button']")
    public WebElement play_button;

    @FindBy(css = "div[class='ytp-thumbnail-overlay-image']")
    public WebElement addedVideo_newsildePage;

    @FindBy(xpath = "//iframe[contains(@src,'https://www.youtube.com/embed')]")
    public WebElement youtube_frame;

    public By byAddedSlide_editPage=By.cssSelector("div[class='ytp-thumbnail-overlay-image']");
    public By byAddedVideo_newsildePage=By.cssSelector("div[class='ytp-thumbnail-overlay ytp-cued-thumbnail-overlay']");
    public By byUploadedVideo_slide =By.cssSelector("img[class='slide-thumb img-responsive video-thumb']");

    @FindBy(xpath = "//button[text()=' Cancel ']")
    public WebElement cancel_button;

    @FindBy(css = "span[class='lesson-preview-expand exit-full-screen']")
    public WebElement exitFullScreen_icon;

    @FindBy(css="#slide-container>div>span>img")
    public List<WebElement> slide_container;

    @FindBy(css = ".ytp-title-playlist-icon+span")
    public WebElement addedVideoTitle;

    @FindBy(css = "#js-delete-slide-confirmed")
    public WebElement yes_Delete_link;

    @FindBy(css = "#js-close-delete-confirm-popup")
    public WebElement no_cancel_link;

    @FindBy(css = "div[class='video-card-title twoline-ellipsis-clamp']")
    public WebElement videoName;

    @FindBy(className = "ytp-title-text")
    public WebElement videoSlideText;




}
