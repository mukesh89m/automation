package com.snapwiz.selenium.tests.staf.microlearning.pagefactory;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Priti on 23-09-2016.
 */
public class MyLibraryPage {

    @FindBy(css = ".action-label")
    public List<WebElement> lessonPreviewButton;

    @FindBy(css = ".ml-lesson-img")
    public List<WebElement> lessonCard;

    @FindBy(css = ".ml-preview-btn-txt")
    public WebElement addToMyLibraryButton;

    @FindBy(css = ".fa.icon-angle-left")
    public List<WebElement> slideNavigationArrow;

    @FindBy(css = ".lesson-card-title.m-b-xxs.twoline-ellipsis-clamp")
    public List<WebElement> lessonTitleLibrary;

    @FindBy(css = "a[title ='Remove']")
    public List<WebElement> removeLink;

    @FindBy(css = "#js-removeLessonCard-popup")
    public List<WebElement> cancelButton;

    @FindBy(css = "#js-remove-lesson-card")
    public WebElement removeButton;

    @FindBy(css = "a[title ='Play']")
    public List<WebElement> playLink;

    @FindBy(css = ".lesson-preview-title")
    public WebElement headerLabel;

    @FindBy(css = ".lesson-preview-slide-breadcrumb")
    public WebElement slideCount;

    @FindBy(css = ".ytp-large-play-button.ytp-button")
    public WebElement playVideoButton;

    @FindBy(css = ".ytp-play-button.ytp-button")
    public  WebElement pauseVideoButton;

    @FindBy(css = ".lesson-preview-btn.slider-navigate-btn.js-slider-next")
    public WebElement nextSlideButton;

    @FindBy(css = ".question-attempt-score")
    public WebElement scoreCard;

    @FindBy(css = ".lesson-previous-btn.slider-navigate-btn.js-slider-prev.show")
    public WebElement previousButton;

    @FindBy(css = "#js-skip-slide-close")
    public List<WebElement> skipSlideCloseButton;

    @FindBy(css = "#js-skip-slide")
    public WebElement skipSlideYesButton;

}
