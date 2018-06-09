package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Mukesh on 3/11/15.
 */
public class CreateCourse {
    @FindBy(xpath = "//div[@id='add-new-course']/a/img")
    public WebElement createCourse_link;

    @FindBy(id="admin-heading")
    public WebElement createCourse_title;

    @FindBy(id="courseName")
    public WebElement course_name;

    @FindBy(id="author")
    public WebElement author_name;

    @FindBy(id="courseDesc")
    public WebElement course_description;

    @FindBy(xpath = "//div[@class='course-type-dropdown-sectn']/div/a")
    public WebElement courseType_dropdown;

    @FindBy(partialLinkText = "Learning Space + Adaptive Component")
    public WebElement courseType_lsadp;

    @FindBy(id="publisher")
    public WebElement publisherr;

    @FindBy(xpath = "//img[@title='Step 2: Course Outline']")
    public  WebElement CourseOutline_link;

    @FindBy(xpath = "//div[@title='Step 2: Course Outline']")
    public WebElement courseOutline_title;

    @FindBy(id="add-contents-img")
    public WebElement addContent_img;

    @FindBy(xpath = "//a[@id='add-content']/img")
    public WebElement addContent_link;

    @FindBy(xpath = "//a[@id='finish']")
    public  WebElement finish_button;

    @FindBy(id="manage-content-tab")
    public WebElement manageContent_Tab;

    @FindBy(xpath = "//img[@title='Project Management 9e']")
    public  WebElement lsCourse;

    @FindBy(xpath = "//img[@title='Contemporary Business, 16e']")
    public  WebElement lsadpCourse;

    @FindBy(xpath = "//img[@title='Contemporary Business 15e']")
    public  WebElement adaptiveCourse;

    @FindBy(id="header-change-course")
    public WebElement changeCourse_link;

    @FindBy(xpath = "//span[@class='border-two']/img")
    public WebElement orionCourse;

    @FindBy(css="div[class='course-chapter-label node']")
    public WebElement chapterName;




}
