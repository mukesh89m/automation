package com.snapwiz.selenium.tests.staf.microlearning.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by mukesh on 24/8/16.
 */
public class LessonPage {


    @FindBy(css = "#create>a")  //create>a //#create-lesson
    public WebElement createLesson_link;

    @FindBy(css = ".modal-title")
    public WebElement lesson_title;

    @FindBy(css = "button[class='close modal-close']")
    public WebElement lesson_close_icon;

    @FindBy(css = "#LessonName")
    public WebElement lessonName_textbox;

    @FindBy(css = "div[class='dl-errorMsg lesson-creation-error dl-errorMsg-bottom']")
    public List<WebElement> lesson_creation_error_message;

    @FindBy(css = "#lessonDescription")
    public WebElement lessonDescription_textbox;

    @FindBy(css = "#beginner+ins")
    public WebElement beginner_radio_button;

    @FindBy(css = "#intermediate+ins")
    public WebElement intermediate_radio_button;

    @FindBy(css = "#expert+ins")
    public WebElement expert_radio_button;

    @FindBy(css = ".select2-search__field")
    public WebElement enterTag_textbox;

    @FindBy(css = "#select2-lesson-tags-results>li")
    public List<WebElement> tag_results;

    @FindBy(css = ".dl-btn.dl-btn-white.btn-rounded.m-r-xs.modal-close")
    public WebElement lesson_cancel_button;

    @FindBy(css = "#js-create-lesson")
    public WebElement lesson_save_button;

    @FindBy(css = "#lesson-image-label")
    public WebElement upload_image_link;

    @FindBy(css = "#pickfiles")
    public  WebElement pickFile_link;

    @FindBy(css = "#widget-createimage_start_queue")
    public WebElement upload_button;

    @FindBy(css = "#upload-img-error-panel")
    public WebElement uploadImage_message;

    public By lesson_popup=By.cssSelector(".modal-content");

    @FindBy(css = ".upload-error")
    public WebElement invalidFileError_message;

    @FindBy(css = "div[class='ml-navigation-section ellipsis']")
    public WebElement lessonName_edit_page;

    @FindBy(css = "#edit-lesson-details")
    public WebElement edit_lesson_link;

    @FindBy(css = "#lesson-image-label")
    public WebElement replaceImage_link;

    @FindBy(xpath = "//div[text()='Image uploaded successfully.']")
    public WebElement succussfulImage_link;

    @FindBy(css = "li[class='select2-selection__choice']")
    public WebElement selected_tag;

    @FindBy(xpath = "//textarea[@id='lessonDescription']")
    public WebElement lessonDescriptionTextBox;



}
