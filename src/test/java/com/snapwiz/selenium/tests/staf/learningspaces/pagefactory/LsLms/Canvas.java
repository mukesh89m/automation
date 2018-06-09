package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.LsLms;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by rashmi on 05-02-2016.
 */
public class Canvas {
    @FindBy(xpath = ".//*[@id='my-canvas']/a")
    public WebElement MyProfileCanvasText;

    @FindBy(className = "nav-ls-canvas")
    public WebElement MyProfileCanvasThumbnail;

    @FindBy(className = "ls---black-board-go-back-to-my-canvas-text")
    public WebElement MainNavigatorGoBackToMyCanvasText;

    @FindBy(className = "ls---black-board-go-back-to-my-canvas")
    public WebElement MainNavigatorCanvasThumbnail;

    @FindBy(xpath = "//span[@data-tab-name='tools']")
    public WebElement ToolsLink;

    @FindBy(xpath = "//span[@data-tab-name='assignments']")
    public WebElement AssignmentsLink;

    @FindBy(xpath = "//span[@data-tab-name='readings']")
    public WebElement ReadingsLink;

    @FindBy(xpath = "//span[@data-tab-name='instructor-tools']")
    public WebElement InstructorTools;

    @FindBy(xpath = "//div[@title='WileyPLUS Learning Space Homepage']")
    public WebElement HomepageTile;

    @FindBy(xpath = "//div[@title='WileyPLUS Learning Space e-Textbook']")
    public WebElement eTextbookTile;

    @FindBy(xpath = "//div[@title='WileyPLUS Learning Space Gradebook']")
    public List<WebElement> GradebookTile;


    @FindBy(xpath = "//div[@title='WileyPLUS Learning Space Gradebook']")
    public List<WebElement> CourseStreamTile;

    @FindBy(xpath = "//div[@title='WileyPLUS Learning Space Reports']")
    public WebElement ReportsTile;

    @FindBy(xpath = "//div[@title='WileyPLUS Learning Space Technical Support']")
    public WebElement SupportTile;

    @FindBy(xpath = "//div[@title='WileyPLUS Learning Space Grade Refresh']")
    public WebElement GradeRefresh;

    @FindBy(xpath = "//div[@title='WileyPLUS Learning Space My Notes']")
    public WebElement NotesTile;

    @FindBy(xpath = "//span[@title='View the Learning Space dashboard for your WileyPLUS Learning Space Class Section.']")
    public WebElement DescHomepageTile;

    @FindBy(xpath = "//span[@title='View the digital textbook in your WileyPLUS class section.']")
    public WebElement DesceTextbookTile;

    @FindBy(xpath = "//span[@title='View all scores and attempt details for assignments in your WileyPLUS class section.']")
    public WebElement DescGradeBook;

    @FindBy(xpath = "//span[@title='View all the social collaboration available in your WileyPLUS class section.']")
    public WebElement DescCourseStream;

    @FindBy(xpath = "//span[@title='View all the reports available in your WileyPLUS class section.']")
    public WebElement DescReports;

    @FindBy(xpath = "//span[@title='View all scores and attempt details for assignments in your WileyPLUS class section.The same would be communicated back to LMS in real time.']")
    public WebElement DescGradeRefresh;

    @FindBy(xpath = "//span[@title='View the notes area in your WileyPLUS class section for students only.']")
    public WebElement DescNotes;

    @FindBy(css = "a[class='preview-clone-btn tool-preview-btn']")
    public WebElement HomepagePreview;

    @FindBy(css = "a[class='preview-clone-btn tool-add-btn']")
    public WebElement AddButtonAnyTile;

    @FindBy(css="span[class='ls-create-assignment tool-preview-btn']")
    public WebElement AssignWPLSButton;

    @FindBy (xpath = "//span[contains(text(),\"Refresh Assignments\")]")
    public WebElement RefreshAssignmentsButton;

    @FindBy(xpath = "//div[@class='ls-external-tool-list-title']")
    public List<WebElement> AssignmentTile;

    @FindBy(xpath = "//span[@title='View this assignment in your WileyPLUS class section.']")
    public WebElement  AssignmentDescription;

    @FindBy(className = "cms-content-question-review-list-label")
    public WebElement AssignmentPreviewScreen;

    @FindBy(className = "ls-external-tool-list-title")
    public WebElement AssignmentTitleText;

    @FindBy(xpath = "//div[@class='external-tool-box']")
    public List<WebElement> ChapterTile;

    @FindBy(xpath = "//span[@title='Full digital textbook and helpful media resources.']")
    public WebElement ChapterDescription;

    @FindBy(xpath = "//div[@title='WileyPLUS Learning Space Assignments']")
    public WebElement CurrentAssignmentsTileText;

    @FindBy(xpath = "//div[@title='WileyPLUS Instructor Resources']")
    public WebElement ResourcesTileText;
}
