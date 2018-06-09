package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import javax.xml.ws.WebServiceContext;
import java.util.List;

/**
 * Created by priyanka on 2/18/2015.
 */
public class TocSearch {
    @FindBy(css = "input[class='toc-sprite search-course-stream ui-autocomplete-input']")
    WebElement tocSearch;//search field
    public WebElement getTocSearch(){return tocSearch;}

    @FindBy(className = "toc-overlay")
    WebElement tocScreen;
    public WebElement getTocScreen(){return tocScreen;}

    @FindBy(className = "search-image-resource-filter-wrapper")
    WebElement imageIcon;//image icon
    public WebElement getImageIcon(){return imageIcon;}

    @FindBy(className = "search-audio-resource-filter-wrapper")
    WebElement videoIcon;//video icon
    public WebElement getVideoIcon(){return videoIcon;}

    @FindBy(className = "search-quiz-resource-filter-wrapper")
    WebElement quizIcon;//quiz icon
    public WebElement getQuizIcon(){return quizIcon;}

    @FindBy(className = "search-lesson-resource-filter-wrapper")
    WebElement lessonIcon;//lesson icon
    public WebElement getLessonIcon(){return lessonIcon;}

    @FindBy(className = "search-discussion-resource-filter-wrapper")
    WebElement discussionIcon;//discussion icon
    public WebElement getDiscussionIcon(){return discussionIcon;}

    @FindBy(className = "search-animation-resource-filter-wrapper")
    WebElement animationIcon;//animation ion
    public WebElement getAnimationIcon(){return animationIcon;}

    @FindBy(css = "i[class='close-search-results search-close']")
    WebElement done_Button;//done button
    public WebElement getDone_Button(){return done_Button;}

    @FindBy(css = "div[class='ls-search-bar focus-ls-search-bar']")
    WebElement searchField;
    public WebElement getSearchField(){return searchField;}

    @FindBy(className = "search-icon")
    WebElement search_Icon;
    public WebElement getSearch_Icon(){return search_Icon;}

    @FindBy(xpath = "(//i[contains(@class,'search-data-icon search-data-img-icon')])[1]")
    WebElement image;
    public WebElement getImage(){return image;}

    @FindBy(xpath = "(//i[contains(@class,'search-data-icon search-data-lesson-icon')])[1]")
    WebElement lesson;
    public WebElement getLesson(){return lesson;}

    @FindBy(xpath = "(//i[contains(@class,'search-data-icon search-data-animation-icon')])[1]")
    WebElement animation;
    public WebElement getAnimation(){return animation;}

    @FindBy(xpath = "(//i[contains(@class,'search-data-icon search-data-discussion-icon')])[1]")
    WebElement discussion;
    public WebElement getDiscussion(){return discussion;}

    @FindBy(className = "widget-close")
    WebElement imageClose_Icon;
    public WebElement getImageClose_Icon(){return imageClose_Icon;}

    @FindBy(id = "ch01-sec1-1")
    WebElement lessonText;
    public WebElement getLessonText(){return  lessonText;}

    @FindBy(id = "ch02-sec2-1")
    WebElement animationText;
    public WebElement getAnimationText(){return animationText;}

    @FindBy(css = "a[class='ls-content-post__footer-perspective-link js-toggle-comments']")
    WebElement perspective;
    public  WebElement getPerspective(){return perspective;}

    @FindBy(className="search-list-chapter-level")
    WebElement chapterLevel;
    public WebElement getChapterLevel(){return chapterLevel;}


    @FindBy(className="search-list-course-level")
    WebElement courseLevel;
    public WebElement getCourseLevel(){return courseLevel;}


    @FindBy(xpath = "(//span[@class='ls-result-view-title'])[1]")
    WebElement glossaryHeading;
    public WebElement getGlossaryHeading(){return glossaryHeading;}

    @FindBy(xpath = "//li[contains(@class,'search-item-wrapper')]//div/span[contains(text(),'Animation 2.1: Atoms and Elements')]")
    WebElement animationText1;
    public WebElement getAnimationText1(){return animationText1;}

    @FindBy(xpath = "(//li[@class='search-item-wrapper']//div[@class='desc-box']//div[2]//i[@class='search-data-icon search-data-animation-icon'])[1]")
    WebElement animation1;
    public WebElement getAnimation1(){return animation1;}

    @FindBy(xpath = "(//li[@class='search-item-wrapper']//div[@class='desc-box']//div[2]//div[@class='ellipsis-box'])[1]//a[@class='toc-auto-suggest-label']")
    WebElement animationtext2;
    public  WebElement getAnimationtext2(){return animationtext2;}

    @FindBy(xpath = "(//a[@class='toc-auto-suggest-label'])[1]")
    WebElement highlightedText;
    public WebElement getHighlightedText(){return highlightedText;}

    @FindBy(xpath = "(//a[@class='toc-auto-suggest-label'])[1]/em")
    WebElement highlightedText1;
    public WebElement getHighlightedText1(){return highlightedText1;}

    @FindBy(css = "div[class='search-image-resource-filter-wrapper selected']")
    WebElement imageSelected;
    public WebElement getImageSelected(){return imageSelected;}

    @FindBy(css = "div[class='search-lesson-resource-filter-wrapper selected']")
    WebElement lessonSelected;
    public WebElement getLessonSelected(){return lessonSelected;}

    @FindBy(css = "div[class='search-discussion-resource-filter-wrapper selected']")
    WebElement discussionSelected;
    public WebElement getDiscussionSelected(){return discussionSelected;}

    @FindBy(css = "div[class='search-animation-resource-filter-wrapper selected']")
    WebElement animationSelected;
    public WebElement getAnimationSelected(){return animationSelected;}

    @FindBy(className = "resource-description")
    WebElement description;
    public WebElement getDescription(){return description;}

    @FindBy(xpath = "(//li[@class='ui-menu-item'])[1]")
    WebElement dropDownItem;
    public WebElement getDropDownItem(){return dropDownItem;}

    @FindBy(css = "ul[class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']")
    WebElement autoCompleteBox;
    public  WebElement getAutoCompleteBox(){return autoCompleteBox;}

    //ul[class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ui-corner-all']

    @FindBy(css = "li[class='search-item-wrapper selected']")
    WebElement searchResult;
    public WebElement getSearchResult(){return searchResult;}

    @FindBy(className = "glo-term")
    WebElement glossaryText;
    public WebElement getGlossaryText(){return glossaryText;}

    @FindBy(xpath = "(//div[@class='search-list-chapter-name'])[1]")
    WebElement chapterHeading;
    public WebElement getChapterHeading(){return chapterHeading;}

    @FindBy(className = "search-resource-filter-wrapper")
    WebElement mediaElement;
    public WebElement getMediaElement(){return mediaElement;}

    @FindBy(css = "span[class='toc-icon inprogress']")
    public WebElement assignment_icon;

    @FindBy(xpath = "//a[@title='1A.2 Concept Check']")
    public WebElement assignmentName;

    @FindBy(xpath = "//a[@title='2.1 Concept Check']")
    public WebElement assignmentNameAdaptive;

    @FindBy(css="span[class='ls-toc-due-date']")
    public WebElement tocDueDate;


    @FindBy(css="span[class='ls-toc-due-date ls-adaptive-toc-due-date']")
    public WebElement adaptiveTocDueDate;

    @FindBy(className = "student-name-label")
    public WebElement studentTOC;

    @FindBy(css = "i[class='study-plan-back-btn']")
    public WebElement backButton;

    @FindBy(xpath = "//a[@title='1A.2 Concept Check']/preceding-sibling::span")
    public WebElement assessment_icon;

    @FindBy(xpath = "//a[@title='2.1 Concept Check']/preceding-sibling::span")
    public WebElement assessment_icon_Adaptive;


    @FindBy(xpath = "//a[@title='1A.2 Concept Check']/preceding-sibling::span/i[contains(@class,'s s--assessment ls-toc-sprite')]")
    public List<WebElement> studentAssessment_icon;

    @FindBy(xpath = "//a[@title='2.1 Concept Check']/preceding-sibling::span/i[contains(@class,'s s--assessment')]")
    public List<WebElement> studentAssessment_icon1; //assignment icon for biology

    @FindBy(xpath = "//div[@class='toc-assignment-ellipsis']")
    public WebElement assignmentName1;

    @FindBy(css= "div.hide-toc")
    public WebElement hideTOC;

    @FindBy(xpath = "//a[@title='1A.8 Concept Check']")
    public WebElement assignment_Name;

    @FindBy(xpath = "//span[@class='toc-icon inprogress']")
    public List<WebElement> assignmentInProgress_icon;


    @FindBy(xpath = "//a[@title='1A.2 Concept Check']/div")
    public WebElement assignmentname;

    @FindBy(className = "ls-result-view-title")
    public List<WebElement> tocSearchList;

    @FindBy(css = "div[class='ir-ls-assign-dialog-gradable-label-check']")
    public WebElement gradable_checkbox;

    @FindBy(css = "div[class='ir-ls-assign-dialog-gradable-threshold-label-check selected']")
    public WebElement gradableTreshold_checkbox;

    @FindBy(css = "div[class='ir-ls-assign-dialog-gradable-label-check selected']")
    public WebElement selectedGradable_checkbox;

    @FindBy(xpath = "//input[@id='gradable-threshold-range-one']")
    public WebElement threshold_val_one;

    @FindBy(xpath = "//input[@id='gradable-threshold-range-two']")
    public WebElement threshold_val_two;

    @FindBy(xpath = "//input[@id='gradable-threshold-range-three']")
    public WebElement threshold_val_three;

    @FindBy(xpath = "//input[@id='gradable-threshold-range-four']")
    public WebElement threshold_val_four;

    @FindBy(xpath = "//input[@id='gradable-threshold-range-five']")
    public WebElement threshold_val_five;

    @FindBy(className = "gradable-ranges-error-message")
    public WebElement rangeErrorMessage;

    @FindBy(css = "div[class='ir-ls-assign-dialog-gradable-threshold-label-check selected']")
    public WebElement selectedThresholdRange;

    @FindBy(css = "div[class='threshold-gradient-bar ir-ls-assign-dialog-content-item']")
    public List<WebElement> thermometer;

    @FindBy(css = "span[class='short-label-help-icon gradable-check-help']")
    public WebElement gradableCheckbox_help;

    @FindBy(css = "span[class='short-label-help-icon gradable-threshold-check-help']")
    public WebElement gradableThresholdCheckbox_help;

    @FindBy(css = "div[class='assign-this-help-tooltip-wrapper']")
    public WebElement gradableThresholdCheckbox_helpText;

    @FindBy(css = "div[class='threshold-container first-threshold-container']")
    public WebElement totalMarks1;

    @FindBy(css = "div[class='threshold-container second-threshold-container']")
    public WebElement totalMarks2;

    @FindBy(css = "div[class='threshold-container third-threshold-container']")
    public WebElement totalMarks3;

    @FindBy(css = "div[class='threshold-container fourth-threshold-container']")
    public WebElement totalMarks4;

    @FindBy(css = "div[class='threshold-container fifth-threshold-container']")
    public WebElement totalMarks5;

    @FindBy(xpath = ".//ul[@class='assignment-card']//a")
    public List<WebElement> postChapterAssignments;




}
