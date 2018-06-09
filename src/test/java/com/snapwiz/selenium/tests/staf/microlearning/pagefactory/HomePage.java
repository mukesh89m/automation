package com.snapwiz.selenium.tests.staf.microlearning.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by root on 9/2/16.
 */
public class HomePage {

    @FindBy(className = "ml-lesson-card-holder-wrapper")
    public WebElement lessonCardHolder;

    @FindBy(css = "li[class='ml-lesson-filter-btn dl-filter-all js-lesson-filter-btn white-bg selected']")
    public WebElement all_button;

    @FindBy(css = "div[class='ml-search-msg m-t-md']")
    public WebElement searchResultText;

    @FindBy(css = "span[class='search-close js-search-close icon-cancel-icon']")
    public WebElement closeIcon;

    @FindBy(className = "ml-filter-count")
    public List<WebElement> filterCountList;

    @FindBy(className = "ml-lesson-card-holder-wrapper")
    public List<WebElement> lessonCardHolderList;

    @FindBy(css = "span[class='search-val ellipsis']")
    public WebElement searchKeyText;

    @FindBy(css = "div[class='lesson-card-title m-b-xxs twoline-ellipsis-clamp']")
    public WebElement lessonName;

    @FindBy(className = "ml-lesson-tags")
    public WebElement tagNames;

    @FindBy(css = "div[class='ml-author-name ellipsis']")
    public WebElement authorName;

    @FindBy(css = "div[class='m-t-sm preview-footer-description']")
    public WebElement lessonDescription;

    @FindBy(css = ".dl-card-block-hover-bg")
    public WebElement lessonThumbnail;


}
