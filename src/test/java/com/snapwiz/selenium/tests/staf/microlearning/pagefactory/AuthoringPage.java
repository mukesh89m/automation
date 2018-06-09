package com.snapwiz.selenium.tests.staf.microlearning.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by mukesh on 25/8/16.
 */
public class AuthoringPage {

    @FindBy(css = "li[data-status='draft']")
    public WebElement draft_link;

    @FindBy(css = ".navbar-username")
    public WebElement userName;

    @FindBy(css = ".ml-author-name.ellipsis")
    public List<WebElement> authorNames;

    @FindBy(css = "li[data-status='published']")
    public WebElement published_link;

    @FindBy(css = "div[class='lesson-card-title-box']>div")
    public List<WebElement> allLessonCard_title;

    @FindBy(css = "div[class='icon-add dl-lesson-add']")
    public WebElement createNew_link;

    @FindBy(css = "a[title='Edit']")
    public List<WebElement> edit_link;

    @FindBy(css = "div[class='ml-lesson-img']")
    public List<WebElement> getAllLessonCard_img;

    @FindBy(css = "#authoring>a")
    public WebElement main_Navigator_authoring_link;

    @FindBy(css = ".navbar-minimalize.minimalize-styl-2.btn.btn-primary.m-t-md.m-l-lg.btn-hollow-green")
    public WebElement main_Navigator_icon;

    @FindBy(css = ".ml-navigation-section.ellipsis")
    public WebElement authoring_Page_label;

    @FindBy(css = "#ml-nav-search")
    public WebElement authoring_page_searchBox;

    @FindBy(css = "#ml-nav-search-btn")
    public WebElement authoring_page_searchIcon;

    @FindBy(css = ".icon-library")
    public WebElement myLibraryIcon;

    @FindBy(css = ".nav-label")
    public List<WebElement> mainNavigatorMenuLabels;

    @FindBy(css = ".icon-explore")
    public WebElement exploreIcon;

    @FindBy(css = ".icon-authoring")
    public WebElement authoringIcon;

    @FindBy(xpath = "//li[@data-status='published']")
    public WebElement publishedLabel;

    @FindBy(xpath = "//li[@data-status='draft']")
    public WebElement draftLabel;

    @FindBy(xpath = "//li[@Class='dl-lesson-published lesson-status white-bg selected']")
    public WebElement publishedStatus_selected;

    @FindBy(xpath = "//li[@class='dl-lesson-published lesson-status selected white-bg']")
    public WebElement publishedStatus_selected_bydefault;


    @FindBy(xpath = "//li[@Class='dl-lesson-draft lesson-status white-bg selected']")
    public WebElement draftStatus_selected;

    @FindBy(xpath = "//li[@Class='dl-lesson-published lesson-status white-bg']")
    public WebElement publishedStatus_Not_selected;

    @FindBy(xpath = "//li[@Class='dl-lesson-draft lesson-status white-bg']")
    public WebElement draftStatus_Not_selected;

    @FindBy(xpath = "//li[@data-status='published']/span[1]")
    public WebElement published_tick;

    @FindBy(xpath = "//li[@data-status='draft']/span[1]")
    public WebElement draft_tick;

    @FindBy(css = ".lesson-published-count")
    public WebElement published_count;

    @FindBy(css = ".lesson-draft-count")
    public WebElement draft_count;

    @FindBy(xpath = "//div[@class='ml-lesson-card white-bg dl-create-lesson-btn text-center font-noraml dl-create-lesson']")
    public WebElement createLessonCard;

    @FindBy(xpath = "//div[@class='dl-create-lesson-txt']")
    public WebElement createLessonCardTitle;

    @FindBy(xpath = "//div[@class='icon-add dl-lesson-add']")
    public WebElement createLessonCardPlusSymbol;

    @FindBy(xpath = "//div[@class='dl-create-lesson-sub-txt']")
    public WebElement createLessonCardCreateNewText;

    @FindBy(xpath = "//div[@class='lesson-card-title m-b-xxs twoline-ellipsis-clamp']")
    public List<WebElement> lessonTitles;

    @FindBy(xpath = "//div[@class='lesson-card-status-Published lesson-card-status pull-right']")
    public List<WebElement> publishedLabel1;

    @FindBy(css = ".lesson-card-status-Draft.lesson-card-status.pull-right")
    public List<WebElement> draftLabel1;

    @FindBy(css = ".icon-draft")
    public List<WebElement> draftIcons;

    @FindBy(xpath = "//div[@class='ml-author-title font-bold']")
    public List<WebElement> AuthorLabels;

    @FindBy(xpath = "//div[@class='ml-lesson-img']")
    public List<WebElement> lessonThumbnails;

    @FindBy(css = ".btn.btn-invisible.dl-btn-white.ml-lesson-card-hover-btn.lesson-card-action-btn.js-lesson-card-action-btn")
    public List<WebElement> edit_Buttons;

    @FindBy(css = ".btn.btn-invisible.dl-btn-white.ml-lesson-card-hover-btn.js-lesson-preview")
    public List<WebElement> preview_Buttons;

    @FindBy(css = ".card-options.pull-right")
    public List<WebElement> three_vertical_dots;

    @FindBy(css = ".lesson-published-count")
    public WebElement publishStatusLessonCount;

    @FindBy(css = ".lesson-draft-count")
    public WebElement draftStatusLessonCount;

    @FindBy(css = ".ml-filter-count")
    public List<WebElement> filetrcounts;

    @FindBy(xpath = "//button[@id='exit']")
    public WebElement exitButton_Edit_Lesson;

    @FindBy(css = "#publish-lesson")
    public WebElement publishButton;

    @FindBy(xpath = "//div[@class='card-options-popup delete-lesson show']")
    public WebElement lessonDeleteOption;

    @FindBy(xpath = "//h4[@class='modal-title']")
    public WebElement deletePopUpTitle;

    @FindBy(css = "#js-close-delete-popup")
    public List<WebElement> deletePopUpCloseIcon;

    @FindBy(css = ".delete-confirmation-message1")
    public WebElement deletePopupMsg1;

    @FindBy(css = ".delete-confirmation-message2")
    public WebElement deletePopupMsg2;

    @FindBy(css = "#js-delete-confirmed")
    public WebElement yesDeleteButton;

    @FindBy(css = "#lesson-image-preview>img")
    public WebElement lessonThumbnail;

    @FindBy(css = "#edit-lesson-details")
    public WebElement editDetailsButton;

    @FindBy(css = "#exit")
    public WebElement editButtonEditLessonPage;

    @FindBy(css = "#publish-lesson")
    public WebElement publishButtonEditLessonPage;

    @FindBy(css = "#js-preview-lesson")
    public WebElement previewButtonEditLessonPage;

    @FindBy(css = ".ml-navigation-section.ellipsis")
    public WebElement previewPageLessonName;

    @FindBy(css = ".lesson-preview-title")
    public WebElement previewLabelOfPreviewPage;

    @FindBy(xpath = "//div[@class='ml-prev-title']")
    public WebElement previewLevel;

    @FindBy(css = "div[class='ibox-content ml-preview-box']")
    public WebElement slidePreview;

    @FindBy(css = ".col-sm-1.col-md-1.ml-status-free.pull-right")
    public WebElement freeLabel;

    @FindBy(css = "button[class='btn btn-sm btn-rounded btn-green ml-preview-publish published pull-right']")
    public WebElement slidePreviewPublishedButton;

    @FindBy(css = ".btn.btn-sm.btn-rounded.btn-green.ml-preview-publish.published.pull-right")
    public WebElement slidePreviewPublishButton;

    @FindBy(css = ".lesson-preview-expand")
    public WebElement previewExpandButton;

    @FindBy(css = ".lesson-preview-slide-breadcrumb")
    public WebElement slideNumberOutOfTotal;

    @FindBy(css = ".badge.ml-lesson-size")
    public List<WebElement> totalSlides;

    @FindBy(css = ".lesson-preview-btn.slider-navigate-btn.js-slider-next.disabled")
    public WebElement slidePreviewButtonDisabled;

    @FindBy(css = ".lesson-preview-btn.slider-navigate-btn.js-slider-next")
    public WebElement slidePreviewButtonEnabled;

    @FindBy(css = ".font-bold")
    public List<WebElement> labelBelowSlidePreview;

    @FindBy(css = "span[class='pull-right']>span")
    public List<WebElement> authorAuthorName;

    @FindBy(xpath = "(//div[@class='ml-lesson-tags'])[1]/span")
    public List<WebElement> lessonTags;

    @FindBy(xpath = "(//div[@class='ml-lesson-tags'])/span")
    public List<WebElement> lessonTagsInPreview;

    @FindBy (css = ".m-t-sm.preview-footer-description")
    public WebElement previewLessonDescription;

    @FindBy(css = ".publish-date-wrapper")
    public WebElement publishedLabelWithDate;

    @FindBy(css = "div[class='lesson-card-title m-b-xxs twoline-ellipsis-clamp']")
    public WebElement lessonName;

    @FindBy(css = "div[class='ml-navigate-back']")
    public WebElement headerBackIcon;

    @FindBy(css = ".dropdown-menu.animated.fadeInRight.m-t-xs.m-r-sm>li>a")
    public WebElement logoutOption;

    @FindBy(css = "#js-create-lesson")
    public WebElement saveButtonLessonDetails;

    @FindBy(css = ".select2-selection__choice") //.ml-lesson-tags
    public List<WebElement> lessonTagsBeforeCopy;

    @FindBy(css = "#lessonDescription")
    public WebElement lessonDescriptionBeforeCopy;

    @FindBy(css = "#LessonName")
    public WebElement lessonNameOnCopy;

    @FindBy(css = ".dl-slides-box.ui-state-default")
    public List<WebElement> slidesOnEditDetailsPage;

    @FindBy(xpath = "(//div[@class='ml-lesson-tags'])[1]/span")
    public List<WebElement> originalLessonTags;



}
