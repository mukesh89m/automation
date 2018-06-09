package com.snapwiz.selenium.tests.staf.learningspaces.testcases.sanity.completeflow;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyActivity.MyActivity;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyNotes.MyNote;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.DiscussionTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.ResourceTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Perspective;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by mukesh on 25/1_1/16.
 */
public class SanityTest extends Driver {

    String actual = "";
    String expected = "";
    String height = "";
    int recentlyGraded = 0;

    LessonPage lessonPage;
    DiscussionTab discussionTab;
    CourseStreamPage courseStreamPage;
    Perspective perspective;
    Assignments assignments;
    Dashboard dashboard;
    ProficiencyReport proficiencyReport;
    AssignmentResponsesPage assignmentResponsesPage;
    CurrentAssignments currentAssignments;
    PerformanceSummaryReport performanceSummaryReport;
    ResourceTab resourceTab;
    MyNote myNote;
    MyActivity myActivity;

    @BeforeMethod
    public void initializeWebElement() {
        WebDriver driver=Driver.getWebDriver();
        lessonPage = PageFactory.initElements(driver, LessonPage.class);
        discussionTab = PageFactory.initElements(driver, DiscussionTab.class);
        courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
        perspective = PageFactory.initElements(driver, Perspective.class);
        assignments = PageFactory.initElements(driver, Assignments.class);
        dashboard = PageFactory.initElements(driver, Dashboard.class);
        proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
        assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
        currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
        performanceSummaryReport = PageFactory.initElements(driver, PerformanceSummaryReport.class);
        resourceTab = PageFactory.initElements(driver, ResourceTab.class);
        myNote = PageFactory.initElements(driver, MyNote.class);
        myActivity = PageFactory.initElements(driver, MyActivity.class);


    }

    @Test(priority = 1, enabled = true)
    public void highlightTextAndRemove() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Login as student", "Login as student for highlightTextAndRemove method", "info");
            new LoginUsingLTI().ltiLogin("101");
            new Navigator().NavigateTo("eTextbook");
            WebDriverUtil.waitTillVisibilityOfElement(lessonPage.getCrossIcon(), 50);
            new TOCShow().tocHide();
            new Discussion().highlightingText();
            List<WebElement> highLightOption = lessonPage.highlightOption;
            if (highLightOption.get(0).getText().trim().equals("Highlight")) {
                if (highLightOption.get(1).getText().trim().equals("Add Note")) {
                    if (!highLightOption.get(2).getText().trim().equals("Add Discussion")) {
                        CustomAssert.fail("Verify text highlight", "Student is not able to highlight text");
                    }
                }
            }
            WebDriverUtil.clickOnElementUsingJavascript(highLightOption.get(0)); //click on the highlight link
            CustomAssert.assertEquals(lessonPage.highlightText.isDisplayed(), true, "Verify text highlight", "Student highlighted text successfully", "Student not highlighted text successfully");

        } catch (Exception e) {
            Assert.fail("Exception in TC highlightTextAndRemove of class SanityTest", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void addDiscussionAfterHighlightText() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Login as student", "Login as student for addDiscussionAfterHighlightText method", "info");
            new LoginUsingLTI().ltiLogin("2");
            new Navigator().NavigateTo("eTextbook");
            WebDriverUtil.waitTillVisibilityOfElement(lessonPage.getCrossIcon(), 50);
            new TOCShow().tocHide();
            String randomText = new RandomString().randomstring(4);
            new Discussion().addDiscussionAfterHighlightingText(randomText);//add Discussion after highlight text
            String discussionText = discussionTab.discussionEntry_lessonPage.get(0).getText().trim();
            CustomAssert.assertEquals(discussionText, randomText, "Verify discussion Entry", "After highlighting text ,discussion is added in discussion tab", "After highlighting text ,discussion is not added in discussion tab");

            new Navigator().navigateToTab("Fav"); //Navigate to star tab
            Thread.sleep(5000);
            WebDriverUtil.waitTillVisibilityOfElement(discussionTab.discussionEntry_lessonPage.get(1),60);
            String starText = discussionTab.discussionEntry_lessonPage.get(1).getText().trim();
            CustomAssert.assertEquals(starText, randomText, "Verify star tab Entry", "After highlighting text ,discussion is added in star tab", "After highlighting text ,discussion is not added in star tab");

            new Navigator().NavigateTo("Course Stream");
            String discussionTextCourseStreamPage = courseStreamPage.discussionEntry_courseStreamPage.get(2).getText().trim();
            CustomAssert.assertEquals(discussionTextCourseStreamPage, randomText, "Verify Course stream entry", "Course stream entry is  getting added for discussion", "Course stream entry is not getting added for discussion");


            new Navigator().NavigateTo("Course Stream");
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.getJumpOut());//click on the jump out icon
            WebDriverUtil.waitTillVisibilityOfElement(discussionTab.editDiscussion_wrapper, 50);
            try {
                CustomAssert.assertEquals(discussionTab.editDiscussion_wrapper.isDisplayed(), true, "Verify highlighted discussion.", "student landed on that highlighted discussion.", "student is not landing on that highlighted discussion.");
            } catch (Exception e) {
                CustomAssert.assertEquals(lessonPage.highlightText.isDisplayed(), true, "Verify text highlight", "Student highlighted text successfully", "student is not landing on that highlighted discussion.");
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC addDiscussionAfterHighlightText of class SanityTest", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void addNoteAfterHighlightText() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Login as student", "Login as student for addNoteAfterHighlightText method", "info");
            new LoginUsingLTI().ltiLogin("3");
            new Navigator().NavigateTo("eTextbook");
            WebDriverUtil.waitTillVisibilityOfElement(lessonPage.getCrossIcon(), 50);
            new TOCShow().tocHide();
            String randomText = new RandomString().randomstring(4);
            new Discussion().addNoteAfterHighlightingText(randomText, "yellow"); //add note after highlight text

            new Navigator().navigateToTab("Fav"); //Navigate to star tab
            Thread.sleep(5000);
            String starText = discussionTab.discussionEntry_lessonPage.get(0).getText().trim();
            CustomAssert.assertEquals(starText, randomText, "Verify star tab Entry", "After highlighting text ,note is added in star tab", "After highlighting text ,note is not added in star tab");

            new Navigator().NavigateTo("My Notes"); //navigate to my Notes
            new Navigator().clickOnJumpOutIcon();//click on jump out icon
            WebDriverUtil.waitTillVisibilityOfElement(discussionTab.editNote_wrapper, 50);
            try {
                CustomAssert.assertEquals(discussionTab.editNote_wrapper.isDisplayed(), true, "Verify highlighted note.", "student landed on that highlighted note.", "student is not landing on that highlighted note.");
            } catch (Exception e) {
                CustomAssert.assertEquals(lessonPage.highlightText.isDisplayed(), true, "Verify text highlight", "Student highlighted text successfully", "student is not landing on that highlighted note.");
            }
        } catch (Exception e) {
            Assert.fail("Exception in TC addNoteAfterHighlightText of class SanityTest", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void addDiscussionFromDiscussionTab() {
        WebDriver driver=Driver.getWebDriver();
        try {

            ReportUtil.log("Login as student", "Login as student for addDiscussionFromDiscussionTab method", "info");
            new LoginUsingLTI().ltiLogin("4"); //login as student
            new Navigator().NavigateTo("eTextbook");
            WebDriverUtil.waitTillVisibilityOfElement(lessonPage.getCrossIcon(), 50);
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Discussion");
            String randomText = new RandomString().randomstring(6);
            new Discussion().postDiscussion(randomText);//post a discussion
            String discussionText = discussionTab.discussionEntry_lessonPage.get(0).getText().trim();
            CustomAssert.assertEquals(discussionText, randomText, "Verify discussion Entry", "discussion is added in discussion tab", "discussion is not added in discussion tab");

            new Navigator().navigateToTab("Fav"); //Navigate to star tab
            Thread.sleep(5000);
            String starText = discussionTab.discussionEntry_lessonPage.get(1).getText().trim();
            CustomAssert.assertEquals(starText, randomText, "Verify star tab Entry", "note is added in star tab", "note is not added in star tab");

            new Navigator().navigateToTab("Discussion");
            discussionTab.bookmark_icon.get(0).click(); //click on the bookmark icon
            Thread.sleep(2000);
            String editedText = new RandomString().randomstring(6);
            new Discussion().editDiscussion(editedText); //update text and verify
            ReportUtil.log("update Discussion", " Discussion updated in discussion tab successfully", "pass");

            new Navigator().navigateToTab("Fav"); //Navigate to star tab
            Thread.sleep(5000);
            String updatedStarText = discussionTab.discussionEntry_lessonPage.get(1).getText().trim();
            CustomAssert.assertEquals(updatedStarText, editedText, "Verify star tab Entry", "note is updated in star tab", "note is not updated in star tab");

            new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            WebDriverUtil.waitTillVisibilityOfElement(lessonPage.getCrossIcon(), 50);
            new TOCShow().tocHide();
            WebDriverUtil.waitTillVisibilityOfElement(discussionTab.discussionEntry_lessonPage.get(0), 50);
            new Discussion().deleteDiscussion();//delete discussion
            String deleteText = discussionTab.emptyPost.getText().trim();
            CustomAssert.assertEquals(deleteText, "Start a discussion with your class.", "Verify discussion deleted", "discussion is deleted in discussion tab", "discussion is not deleted in discussion tab");

            new Navigator().navigateToTab("Fav"); //Navigate to star tab
            boolean starEntry = WebDriverUtil.isElementPresent(By.cssSelector("div[id='tab-fav'] .star-content ul[class='stream-content-posts'] .ls-right-user-content"));
            CustomAssert.assertEquals(starEntry, false, "Verify discussion deleted from star tab", "discussion is deleted from star tab", "discussion is not deleted from star tab");

            new Navigator().NavigateTo("My Journal");//go to My Journal
            boolean isEmptyNote = new Bookmark().isMyJournalEmpty();
            if(isEmptyNote == false) {
                CustomAssert.fail("Verify discussion deleted  ","The entry is not deleted from My Journal after deleting a Discussion.");
            }

        } catch (Exception e) {
            Assert.fail("Exception in TC addDiscussionFromDiscussionTab of class SanityTest", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void addNoteFromStarTab() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Login as student", "Login as student for addNoteFromStarTab method", "info");
            new LoginUsingLTI().ltiLogin("5"); //login as student
            new Navigator().NavigateTo("eTextbook");
            WebDriverUtil.waitTillVisibilityOfElement(lessonPage.getCrossIcon(), 50);
            new TOCShow().tocHide();
            String randomText = new RandomString().randomstring(6);
            new Navigator().navigateToTab("Fav"); //Navigate to star tab
            new Discussion().postNote(randomText);//post a note
            Thread.sleep(5000);
            WebDriverUtil.waitTillVisibilityOfElement(discussionTab.discussionEntry_lessonPage.get(0), 50);
            String starText = discussionTab.discussionEntry_lessonPage.get(0).getText().trim();
            CustomAssert.assertEquals(starText, randomText, "Verify star tab Entry", "note is added in star tab", "note is not added in star tab");

            discussionTab.bookmark_icon.get(0).click(); //click on the bookmark icon
            String editedText = new RandomString().randomstring(6);
            new Discussion().editNote(editedText); //edit note
            ReportUtil.log("update Note", " Note updated in Note tab successfully", "pass");

            new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            WebDriverUtil.waitTillVisibilityOfElement(lessonPage.getCrossIcon(), 50);
            new TOCShow().tocHide();
            new Discussion().deleteNote();
            String textEntry = discussionTab.discussionEntry_lessonPage.get(0).getText().trim();
            CustomAssert.assertNotEquals(textEntry, editedText, "Verify note deleted from star tab", "note is deleted in star tab", "note is not deleted in star tab");


            new Navigator().NavigateTo("My Journal");//go to My Journal
            boolean isEmptyNote = new Bookmark().isMyJournalEmpty();
            if(isEmptyNote == false) {
                CustomAssert.fail("Verify note deleted","The entry is not deleted from My Journal after deleting a note.");
            }
        } catch (Exception e) {
            Assert.fail("Exception in TC addNoteFromStarTab of class SanityTest", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void expandCollapseForWidget() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Login as student", "Login as student for expandCollapseForWidget method", "info");
            new LoginUsingLTI().ltiLogin("5"); //login as student
            new Navigator().NavigateTo("eTextbook");//navigate to eTextbook
            WebDriverUtil.waitTillVisibilityOfElement(lessonPage.getCrossIcon(), 50);
            new TOCShow().tocHide();
            WebDriverUtil.clickOnElementUsingJavascript( lessonPage.expandImage);//click on the widget
            String widgetClose = lessonPage.widgetClose_icon.getText();
            CustomAssert.assertEquals(widgetClose, "X", "Verify widget expand mode", "widget is getting expand successfully", "widget is not in expand mode");
            WebDriverUtil.clickOnElementUsingJavascript(lessonPage.widgetClose_icon);
            CustomAssert.assertEquals(new BooleanValue().presenceOfElement(5, lessonPage.widgetClose), false, "Verify collapse the widget", "widget is getting collapse successfully", "widget is not in collapse mode");

        } catch (Exception e) {
            Assert.fail("Exception in TC expandCollapseForWidget of class SanityTest", e);
        }
    }

    @Test(priority = 7, enabled = true)
    public void perspectiveDWLikeComment() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Login as student", "Login as student for perspectiveDWLikeComment method", "info");
            new LoginUsingLTI().ltiLogin("5"); //login as student
            new Navigator().NavigateTo("eTextbook");
            new TopicOpen().openLessonWithDiscussionWidget();
            new Widget().perspectiveAdd();
            Thread.sleep(2000);
            ReportUtil.log("Add perspective", "Perspective added successfully", "pass");
            WebDriverUtil.clickOnElementUsingJavascript(perspective.perspective_postLike.get(0)); //click on like for the perspective
            String likeCount = perspective.perspective_commentLike.get(0).getText().trim();
            CustomAssert.assertEquals(likeCount, "(1)Unlike", "Verify like for added perspective", "student liked perspective successfully", "Student is not able to like on perspective.");
            Thread.sleep(3000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", perspective.perspective_link); //clicking on perspective link
            Thread.sleep(3000);
            String randomText = new RandomString().randomstring(10);
            new DiscussionWidget().commentOnPerspective(randomText, 0);    //comment on perspective
            String commentText = perspective.perspective_commentText.get(0).getText().trim();
            CustomAssert.assertEquals(commentText, randomText, "Verify perspctive-comments-posted", "Student is  able to add the Comment on perspective.", "Student is not able to add the Comment on perspective.");

            WebDriverUtil.clickOnElementUsingJavascript(perspective.commentLike.get(0));//click on Like for Comment
            String commentLike = perspective.commentLike.get(0).getText().trim();
            CustomAssert.assertEquals(commentLike, "(1)", "Verify perspctive-comments-like", "Student is  able to like the Comment on perspective.", "Student is not able to like the Comment on perspective.");


        } catch (Exception e) {
            Assert.fail("Exception in TC perspectiveDWLikeComment of class SanityTest ", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void postDiscussionLinkFileForClass() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Login as student", "Login as student for postDiscussionLinkFileForClass method", "info");
            new LoginUsingLTI().ltiLogin("2");//login as student
            new Navigator().NavigateTo("Course Stream");
            boolean shared = new PostMessage().postMessageAndShare("Hi this is New Post", "san", "studentnametag", "2", "true");
            CustomAssert.assertEquals(shared, true, "Verify Posts are shared", "student shared a Post successfully", "Student is not able to shared a Posts.");
            new PostMessage().postlink("www.google.com");
            List<WebElement> allSharedElements = courseStreamPage.discussionEntry_courseStreamPage;
            CustomAssert.assertTrue(allSharedElements.get(0).getText().contains("www.google.com"), "Verify Links are  Shared", "student shared a Links successfully", "Student is not able to shared a Link.");

            //Course stream -Student navigate to the course stream and Add a file
            new FileUpload().fileUpload("2", true);
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.xpath("//span[text()='shared a file with']")), 600);
            String fileUpload = courseStreamPage.fileUpload_entry.get(0).getText().trim();
            CustomAssert.assertTrue(fileUpload.contains("img.png"), "Verify file are Shared", "student shared a File successfully", "Student is not able to shared a File.");

        } catch (Exception e) {
            Assert.fail("Exception in TC postDiscussionLinkFileForClass of class SanityTest ", e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void addPostLinkFileForSpecificStudent() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Login as student1", "Login as student1 for addPostLinkFileForSpecificStudent method", "info");
            new LoginUsingLTI().ltiLogin("6"); //login as student1
            ReportUtil.log("Login as student2", "Login as student2 for addPostLinkFileForSpecificStudent method", "info");
            new LoginUsingLTI().ltiLogin("7");//login as student2
            ReportUtil.log("Login as student3", "Login as student3 for addPostLinkFileForSpecificStudent method", "info");

            new LoginUsingLTI().ltiLogin("8");//login as student3
            new Navigator().NavigateTo("Course Stream");
            String randomText = new RandomString().randomstring(3);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "8");
            boolean shared = new PostMessage().postMessageAndShare(randomText, shareWithInitialString, "studentnametag", "8", "");
            CustomAssert.assertEquals(shared, true, "Verify Posts are shared", "student shared a Post successfully", "Student is not able to shared a Posts.");


            new PostMessage().postLinkAndShare("www.yahoo.com", shareWithInitialString, "studentnametag", "8");
            List<WebElement> allSharedElements = courseStreamPage.discussionEntry_courseStreamPage;
            CustomAssert.assertTrue(allSharedElements.get(0).getText().contains("www.yahoo.com"), "Verify Links are  Shared", "student shared a Links successfully", "Student is not able to shared a Link.");

            new FileUpload().fileUploadAndShare(shareWithInitialString, "studentnametag", "", "8");
            WebDriverUtil.waitTillVisibilityOfElement(courseStreamPage.sharedAFile, 500);
            String fileUpload = courseStreamPage.fileUpload_entry.get(0).getText().trim();
            CustomAssert.assertTrue(fileUpload.contains("img.png"), "Verify file are Shared", "student shared a File successfully", "Student is not able to shared a File.");


            ReportUtil.log("Login as student2", "Login as student2 for addPostLinkFileForSpecificStudent method", "info");
            new LoginUsingLTI().ltiLogin("7"); //login as student2
            new Navigator().NavigateTo("Course Stream");

            List<WebElement> allSharedElements1 = courseStreamPage.discussionEntry_courseStreamPage;

            CustomAssert.assertTrue(allSharedElements1.get(1).getText().contains("www.yahoo.com"), "Verify Links are  Shared", "student shared a Links successfully", "Student is not able to shared a Link.");
            CustomAssert.assertEquals(allSharedElements1.get(2).getText().trim(), randomText, "Verify Posts are shared", "student shared a Post successfully", "Student is not able to shared a Posts.");

            String fileUpload1 = courseStreamPage.fileUpload_entry.get(0).getText().trim();
            CustomAssert.assertTrue(fileUpload1.contains("img.png"), "Verify file are Shared", "student shared a File successfully", "Student is not able to shared a File.");


            ReportUtil.log("Login as student1", "Login as student2 for addPostLinkFileForSpecificStudent method", "info");
            new LoginUsingLTI().ltiLogin("6");//login as student1
            new Navigator().NavigateTo("Course Stream");
            CustomAssert.assertEquals(courseStreamPage.posted_UserName.size(), 0, "Verify Post,file and link entry of student 1", "Post,file and link entry should not be displayed for student 1", "Post,file and link entry is displaying for student 1 also");

            ReportUtil.log("Login as student2", "Login as student2 for addPostLinkFileForSpecificStudent method", "info");
            new LoginUsingLTI().ltiLogin("8"); //login as student3
            new Navigator().NavigateTo("Course Stream");
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.toggle);
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.hide_post);
            Thread.sleep(5000);
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.toggle);
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.hide_post);
            Thread.sleep(5000);
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.toggle);
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.hide_post);

            ReportUtil.log("Login as student2", "Login as student3 for addPostLinkFileForSpecificStudent method", "info");
            new LoginUsingLTI().ltiLogin("7"); //login as student3
            new Navigator().NavigateTo("Course Stream");
            CustomAssert.assertEquals(courseStreamPage.posted_UserName.size(), 0, "Verify Post,file and link entry of student 2", "post and link is  removed from course stream of student2", "post and link is  not removed from course stream of student2");

            new LoginUsingLTI().ltiLogin("8"); //login as student3
            new Navigator().NavigateTo("Course Stream");
            CustomAssert.assertEquals(courseStreamPage.posted_UserName.size(), 0, "Verify Post,file and link entry of student 3", "post and link is  removed from course stream of student3", "post and link is  not removed from course stream of student3");

        } catch (Exception e) {
            Assert.fail("Exception in the testcases addPostLinkFileForSpecificStudent of class SanityTest" + e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void assignAssignmentToStudentAndViewAndReleaseFeedback() {
        WebDriver driver=Driver.getWebDriver();
        try {

            new Assignment().create(9); //create assignment
            ReportUtil.log("create assessment", "Assessment created successfully", "info");

            ReportUtil.log("Login as instructor", "Login as instructor for assignAssignmentToStudentAndViewAndReleaseFeedback method", "info");
            new LoginUsingLTI().ltiLogin("9");//login as instructor

            new Assignment().assignToStudent(9); //assign to the student
            ReportUtil.log("Assign Assignment", "Instructor assigned assignment to complete class section", "pass");

            ReportUtil.log("Login as student", "Login as student for assignAssignmentToStudentAndViewAndReleaseFeedback method", "info");
            new LoginUsingLTI().ltiLogin("10"); //login as student
            new Assignment().submitAssignmentAsStudent(9); //submit assignment
            ReportUtil.log("submit Assignment", "student submitted assignment", "pass");

            ReportUtil.log("Login as instructor", "Login as instructor for assignAssignmentToStudentAndViewAndReleaseFeedback method", "info");
            new LoginUsingLTI().ltiLogin("9");//login as instructor
            new Assignment().provideFeedback(9);
            ReportUtil.log("Provide feedback", "Instructor provided feedback successfully", "pass");

            ReportUtil.log("Login as student", "Login as student for assignAssignmentToStudentAndViewAndReleaseFeedback method", "info");
            new LoginUsingLTI().ltiLogin("10");//login as student
            new Assignment().openAssignmentFromCourseStream("9"); //open course stream
            new QuestionCard().clickOnCard("9", 0);//click on the question card
            String feedbackContent = new TextFetch().textfetchbyclass("feedback-content");
            Assert.assertEquals(feedbackContent, "This is a FeedbackText", "This is a FeedbackText text is present");

            ReportUtil.log("Login as instructor", "Login as instructor for assignAssignmentToStudentAndViewAndReleaseFeedback method", "info");
            new LoginUsingLTI().ltiLogin("9");//login as instructor
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "9");
            new Assignment().clickViewResponse(assessmentName);
            new Assignment().releaseGrades(9, "Release Feedback for All");//click on the release feedback

            new LoginUsingLTI().ltiLogin("10");//login as student
            new Navigator().NavigateTo("Assignments");
            new Assignment().verifyClassAssignmentStatus(9, "Reviewed");

        } catch (Exception e) {
            Assert.fail("Exception in TC assignAssignmentToStudentAndViewAndReleaseFeedback of class SanityTest" + e);
        }
    }

    @Test(priority = 11, enabled = true)
    public void assignAssignmentToStudentAndViewAndReleaseGrade() {
        WebDriver driver=Driver.getWebDriver();
        try {

            new Assignment().create(11); //create assignment
            new Assignment().addQuestions(11, "truefalse", "");
            ReportUtil.log("create assessment", "Assessment created successfully", "info");

            ReportUtil.log("Login as instructor", "Login as instructor for assignAssignmentToStudentAndViewAndReleaseGrade method", "info");
            new LoginUsingLTI().ltiLogin("11");//login as instructor
            new Assignment().assignToStudent(11); //assign to the student
            ReportUtil.log("Assign Assignment", "Instructor assigned assignment to complete class section", "pass");

            ReportUtil.log("Login as student", "Login as student for assignAssignmentToStudentAndViewAndReleaseGrade method", "info");
            new LoginUsingLTI().ltiLogin("12"); //login as student
            new Assignment().submitAssignmentAsStudent(11); //submit assignment
            ReportUtil.log("submit Assignment", "student submitted assignment", "pass");

            ReportUtil.log("Login as instructor", "Login as instructor for assignAssignmentToStudentAndViewAndReleaseGrade method", "info");
            new LoginUsingLTI().ltiLogin("11");//login as instructor
            new Assignment().provideGradeToStudentForMultipleQuestions(11);
            ReportUtil.log("Provide grade", "Instructor provided grade successfully", "pass");
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "11");
            new Assignment().clickViewResponse(assessmentName);
            new Assignment().releaseGrades(11, "Release Grade for All");//click on the release Grade
            ReportUtil.log("Release grade", "Instructor released grade successfully", "pass");

            ReportUtil.log("Login as student", "Login as student for assignAssignmentToStudentAndViewAndReleaseGrade method", "info");
            new LoginUsingLTI().ltiLogin("12");//login as student
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            String score = assignments.getScore().getText();
            CustomAssert.assertEquals(score, "Score (1.4/2)", "verify student grade", "student is able to see grade", "student is not able to see grade");

            new Navigator().NavigateTo("Dashboard");
            actual = dashboard.getQuestionAttempted().getText().trim();
            expected = "2";
            CustomAssert.assertEquals(actual, expected, "Verify Question Attempted Number in dashboard", "Question Attempted Number is " + expected + "", "Question Attempted Number is not " + expected + "");

            actual = dashboard.questionPerformance.getText().trim();
            expected = "70\n%";
            CustomAssert.assertEquals(actual, expected, "Verify Question performance in dashboard", "Question performance is displaying as " + expected + "", "Question performance is not displaying as " + expected + "");

            String overallScore2 = proficiencyReport.courseProficiencys.get(1).getText().trim();
            String overallPercentage1 = proficiencyReport.courseProficiencys.get(2).getText().trim();

            actual = overallScore2 + overallPercentage1;
            expected = "70%";
            CustomAssert.assertEquals(actual, expected, "Verify overall score in dashboard", "overall score is displaying as " + expected + "", "overall score is not displaying as " + expected + "");

            height = dashboard.getGradedBarChart().getAttribute("height");
            recentlyGraded = Integer.parseInt(height);
            CustomAssert.assertTrue(recentlyGraded > 80, "Verify Recently Graded graph", "Recently Graded graph is generated", "Recently Graded graph is not generated");

            ReportUtil.log("Login as instructor", "Login as instructor for assignAssignmentToStudentAndViewAndReleaseGrade method", "info");
            new LoginUsingLTI().ltiLogin("11");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            new Assignment().verifyClassAssignmentStatus(11, "Graded");
            CustomAssert.assertEquals(assignments.assignmentPerformanceGraph.size(), 1, "Verify Assignment performance graph", "Assignment performance graph is visible", "Assignment performance graph is not visible");
            String assessmentName1 = ReadTestData.readDataByTagName("", "assessmentname", "11");

            new Assignment().clickViewResponse(assessmentName1);
            String respnsepageTitle = assignmentResponsesPage.getPageTitle().getText();
            CustomAssert.assertEquals(respnsepageTitle, "Assignment Responses", "Verify student response page", "Instructor is  navigated to Assignment Response page", "Instructor is not navigated to Assignment Response page");
            new Assignment().verifyClassAssignmentStatus(11, "Graded");

        } catch (Exception e) {
            Assert.fail("Exception in TC assignAssignmentToStudentAndViewAndReleaseGrade of class SanityTest",e);
        }
    }

    @Test(priority = 12, enabled = true)
    public void flowForGradableDWAssignment() {
        WebDriver driver=Driver.getWebDriver();
        try
        {

            ReportUtil.log("Login as instructor", "Login as instructor for flowForGradableDWAssignment method", "info");
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "13"); //click on the view student response
            new LoginUsingLTI().ltiLogin("13"); //login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(13);
            ReportUtil.log("Login as student", "Login as student for flowForGradableDWAssignment method", "info");

            new LoginUsingLTI().ltiLogin("14");//login a student
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);//click on the resources name
            String perspectives = new RandomString().randomstring(4);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspectives); //add perspective to DW assignemnt
            ReportUtil.log("Add perspective","Perspective added successfully","pass");

            new Navigator().NavigateTo("Assignments");    //navigate to Assignment
            new Assignment().verifyClassAssignmentStatus(14, "Submitted");

            ReportUtil.log("Login as instructor", "Login as instructor for flowForGradableDWAssignment method", "info");
            new LoginUsingLTI().ltiLogin("13"); //login as instructor
            new Navigator().NavigateTo("Summary");
            new Assignment().clickViewResponse(assessmentName);

            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link());
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", assignmentResponsesPage.getViewResponseLink());

            String perspectiveEntry = perspective.commentEntry.getText().trim();
            CustomAssert.assertEquals(perspectives,perspectiveEntry,"Verify student perspective","Perspective added by student is visible","Perspective added by student is not visible");

            new Navigator().NavigateTo("Summary");
            new Assignment().clickViewResponse(assessmentName);
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on the release Grade
            Thread.sleep(4000);
            List<WebElement> status=currentAssignments.class_assignmentStatus;
            CustomAssert.assertEquals(status.get(1).getAttribute("title").trim(),"Graded","Verify instructor class status in assignment response page","class status is graded","class status is not graded assignment response page");

            new Navigator().NavigateTo("Summary");
            new Assignment().clickViewResponse(assessmentName);
            new Navigator().NavigateTo("Current Assignments");
            CustomAssert.assertEquals(status.get(0).getAttribute("title").trim(),"Graded","Verify instructor class status in current assignment page","class status is graded","class status is not graded current assignment page");

            new LoginUsingLTI().ltiLogin("14");//login as student
            new Navigator().NavigateTo("Assignments");
            String score = assignments.getScore().getText();
            CustomAssert.assertEquals(score, "Score (0/2)", "verify student grade", "student is able to see grade", "student is not able to see grade");

        } catch (Exception e) {
            Assert.fail("Exception in TC flowForNonGradableDWAssignment of class SanityTest", e);
        }
    }

    @Test(priority = 13, enabled = true)
    public void assignLesson() {
        WebDriver driver=Driver.getWebDriver();
        try
        {

            ReportUtil.log("Login as instructor", "Login as instructor for assignLesson method", "info");
            new LoginUsingLTI().ltiLogin("15"); //login as instructor
            new Navigator().NavigateTo("e-Textbook");
            WebDriverUtil.clickOnElementUsingJavascript(lessonPage.getAllLessonAssign());//click on bulk assign icon
            WebDriverUtil.clickOnElementUsingJavascript(lessonPage.assign_button); //click on the assign button
            new AssignLesson().assignLessonWithDefaultClassSection("15");
            ReportUtil.log("Assign Lesson", "Lesson assigned with default class section successfully", "pass");

            ReportUtil.log("Login as student", "Login as student for assignLesson method", "info");
            new LoginUsingLTI().ltiLogin("16");		//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.studentLessonAssignment.get(0).click();//open lesson assignment

            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.studentLessonAssignment.get(1));

            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.studentLessonAssignment.get(2));

            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.studentLessonAssignment.get(3));
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            new Assignment().verifyClassAssignmentStatus(16, "Submitted");


        } catch (Exception e) {
            Assert.fail("Exception in TC assignLesson of class SanityTest",e);
        }
    }

    @Test(priority = 14, enabled = true)
    public void assignImageWidget() {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            ReportUtil.log("Login as instructor", "Login as instructor for assignLesson method", "info");
            new LoginUsingLTI().ltiLogin("17"); //login as instructor
            new Navigator().NavigateTo("e-Textbook");
            WebDriverUtil.waitTillVisibilityOfElement(lessonPage.getCrossIcon(), 50);
            new TOCShow().tocHide();//hide the TOC
            new AssignLesson().assignImageWidget("17");
            ReportUtil.log("Assign image widget", "image widget assigned with default class section successfully", "pass");


            ReportUtil.log("Login as student", "Login as student for assignLesson method", "info");
            new LoginUsingLTI().ltiLogin("18");		//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.studentLessonAssignment.get(0));
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            new Assignment().verifyClassAssignmentStatus(17, "Submitted");


        } catch (Exception e) {
            Assert.fail("Exception in TC assignImageWidget of class SanityTest",e);
        }
    }

    @Test(priority = 15,enabled = true)
    public void gradableAssignmentWithPolicyOne(){
        try {

            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", "20");
            String assignmentName = ReadTestData.readDataByTagName("", "assessmentname", "20");

            new Assignment().create(20);
            for(int i =0 ; i<2 ; i++)
                new Assignment().addQuestions(20, "qtn-type-true-false-img", assignmentName);
            ReportUtil.log("Create Assessment", "Assessment created successfully", "pass");

            ReportUtil.log("Login as student1", "Login as student1 for gradableAssignmentWithPolicyOne method", "info");
            new LoginUsingLTI().ltiLogin("20_1");	//login as a student1
            ReportUtil.log("Login as student2", "Login as student2 for gradableAssignmentWithPolicyOne method", "info");
            new LoginUsingLTI().ltiLogin("20_2");	//login as a student2
            ReportUtil.log("Login as student3", "Login as student3 for gradableAssignmentWithPolicyOne method", "info");
            new LoginUsingLTI().ltiLogin("20_3");	//login as a student3

            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyOne method", "pass");
            new LoginUsingLTI().ltiLogin("20");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//policy one
            ReportUtil.log("Create Policy", "policy one created successfully", "pass");
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(20);//assign to student
            ReportUtil.log("Assign Assignment", "Instructor Assigned assignment with policy one", "pass");

            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            CustomAssert.assertEquals(currentAssignments.getAssessmentName().getText().trim(),assignmentName,"Verify assignment name","Assignment name is displaying in Assignment page","Assignment name is not displaying in Assignment page");
            new Assignment().verifyClassAssignmentStatus(20,"Available for Students");

            ReportUtil.log("Login as student1", "Login as student1 for gradableAssignmentWithPolicyOne method", "info");
            new LoginUsingLTI().ltiLogin("20_1");	//login as a student1
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().checkStatusCountInStudentAssignmentPage(20);
            new Assignment().verifyClassAssignmentStatus(20, "Not Started");
            new Assignment().submitAssignmentAsStudent(20); //submit assignment
            ReportUtil.log("Submit Assignment", "Student1 submitted assignment", "pass");

            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyOne method", "pass");
            new LoginUsingLTI().ltiLogin("20");//login as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click(); //click on the view response student link
            CustomAssert.assertEquals(assignmentResponsesPage.grade_Box.getText(), "Release Grade for All", "Verify Release Grade for All Text","Release Grade for All link is displaying","Release Grade for All link is not displaying ");


            ReportUtil.log("Login as student2", "Login as student2 for gradableAssignmentWithPolicyOne method", "info");
            new LoginUsingLTI().ltiLogin("20_2");	//login as a student2
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().checkStatusCountInStudentAssignmentPage(20);
            new Assignment().verifyClassAssignmentStatus(20, "Not Started");
            new Assignment().submitAssignmentAsStudent(20); //submit assignment
            ReportUtil.log("Submit Assignment", "Student2 submitted assignment", "pass");

            ReportUtil.log("Login as student3", "Login as student3 for gradableAssignmentWithPolicyOne method", "info");
            new LoginUsingLTI().ltiLogin("20_3");	//login as a student3
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().checkStatusCountInStudentAssignmentPage(20);
            new Assignment().verifyClassAssignmentStatus(20, "Not Started");
            new Assignment().submitAssignmentAsStudent(20); //submit assignment
            ReportUtil.log("Submit Assignment", "Student3 submitted assignment", "pass");

            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyOne method", "pass");
            new LoginUsingLTI().ltiLogin("20");//login as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            new Assignment().verifyClassAssignmentStatus(20, "Available for Students");

            currentAssignments.getViewGrade_link().click(); //click on the view response student link

            ReportUtil.log("Login as student3", "Login as student3 for gradableAssignmentWithPolicyOne method", "info");
            new LoginUsingLTI().ltiLogin("20_3");	//login as a student3
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            String score=assignments.getScore().getText();
            CustomAssert.assertEquals(score, "Score (6/6)", "verify student grade", "student is able to see grade", "student is not able to see grade");

            ReportUtil.log("Login as student2", "Login as student3 for gradableAssignmentWithPolicyOne method", "info");
            new LoginUsingLTI().ltiLogin("20_2");	//login as a student3e
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            String score1=assignments.getScore().getText();
            CustomAssert.assertEquals(score1, "Score (6/6)", "verify student grade", "student is able to see grade", "student is not able to see grade");

        } catch (Exception e) {
            Assert.fail("Exception in TC gradableAssignmentWithPolicyOne of class SanityTest ", e);
        }

    }


    @Test(priority = 16,enabled = true)
    public void gradableAssignmentWithPolicyFour(){
        try {

            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", "21");
            String assignmentName = ReadTestData.readDataByTagName("", "assessmentname", "21");

            new Assignment().create(21);
            ReportUtil.log("create assessment", "Assessment created successfully", "info");

            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyFour method", "info");
            new LoginUsingLTI().ltiLogin("21");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description21", "2", null, false, "1", "", "", "", "", "", "21");
            ReportUtil.log("Create Policy", "policy Four created successfully", "pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(21);//assign to student
            ReportUtil.log("Assign Assignment", "Instructor assigned assignment to complete class section", "pass");

            ReportUtil.log("Login as student", "Login as student for gradableAssignmentWithPolicyFour method", "info");
            new LoginUsingLTI().ltiLogin("21_1");//login as student
            new Assignment().submitAssignmentAsStudent(21); //submit assignment
            ReportUtil.log("submit Assignment", "student submitted assignment", "pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().verifyClassAssignmentStatus(21, "Submitted");

            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyFour method", "info");
            new LoginUsingLTI().ltiLogin("21");//login as instructor
            new Assignment().clickViewResponse(assignmentName);
            new Assignment().releaseGrades(21, "Release Grade for All");//click on the release Grade
            ReportUtil.log("Release Grade", "Grade Released", "pass");

            ReportUtil.log("Login as student", "Login as student for gradableAssignmentWithPolicyFour method", "info");
            new LoginUsingLTI().ltiLogin("21_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            String score=assignments.getScore().getText();
            CustomAssert.assertEquals(score, "Score (2/2)", "verify student grade", "student is able to see grade", "student is not able to see grade");

            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyFour method", "info");
            new LoginUsingLTI().ltiLogin("21");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().verifyClassAssignmentStatus(21, "Graded");

        } catch (Exception e) {

            Assert.fail("Exception in TC gradableAssignmentWithPolicyFour of class SanityTest ", e);
        }

    }


    @Test(priority = 17,enabled = true)
    public void gradableAssignmentWithPolicyTwo(){
        try {

            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", "22");
            String assignmentName = ReadTestData.readDataByTagName("", "assessmentname", "22");

            new Assignment().create(22);
            ReportUtil.log("create assessment", "Assessment created successfully", "info");
            new LoginUsingLTI().ltiLogin("22_1");	//login as a student1
            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyTwo method", "info");
            new LoginUsingLTI().ltiLogin("22");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description118", "2", null, false, "1", "", "Auto-release on due date", "", "", "","22");//policy 2
            ReportUtil.log("Create Policy", "policy two created successfully", "pass");

            new Assignment().assignAssignmentWithDueDate(22);
            ReportUtil.log("Assign Assignment", "Instructor assigned assignment with due date to complete class section", "pass");

            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            Assert.assertEquals(currentAssignments.getAssessmentName().getText().trim(),assignmentName);
            new Assignment().verifyClassAssignmentStatus(22,"Available for Students");

            ReportUtil.log("Login as student", "Login as student for gradableAssignmentWithPolicyTwo method", "info");
            new LoginUsingLTI().ltiLogin("22_1");	//login as a student1
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().checkStatusCountInStudentAssignmentPage(22);
            new Assignment().verifyClassAssignmentStatus(22, "Not Started");

            Thread.sleep(180000); //wait for 3 minute till due date is expired

            ReportUtil.log("Login as student", "Login as student for gradableAssignmentWithPolicyTwo method", "info");
            new LoginUsingLTI().ltiLogin("22_1");	//login as a student1
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Navigator().NavigateTo("Assignments");//navigate to Assignments

            String score=assignments.getScore().getText();
            CustomAssert.assertEquals(score, "Score (0/2)", "verify student grade", "student is able to see grade", "student is not able to see grade");

            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyTwo method", "info");
            new LoginUsingLTI().ltiLogin("22");//login as instructor
            try {
                new Navigator().NavigateTo("Assignments");//navigate to Assignments
                new Assignment().verifyClassAssignmentStatus(22, "Graded");
            } catch (Exception e) {
                new Navigator().NavigateTo("Assignments");//navigate to Assignments
                new Assignment().verifyClassAssignmentStatus(22, "Graded");
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC gradableAssignmentWithPolicyTwo of class SanityTest ", e);
        }
    }

    @Test(priority = 18,enabled = true)
    public void gradableAssignmentWithPolicyThree(){
        try {

            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", "23");
            new Assignment().create(23);
            new Assignment().addQuestions(23, "essay", "");
            ReportUtil.log("create assessment", "Assessment created with manual graded question successfully", "info");

            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyTwo method", "info");
            new LoginUsingLTI().ltiLogin("23");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "", "23");
            ReportUtil.log("Create Policy", "policy three created successfully", "pass");
            new Assignment().assignToStudent(23);//assign to student
            ReportUtil.log("Assign Assignment", "Instructor assigned assignment with due date to complete class section", "pass");

            ReportUtil.log("Login as student", "Login as student for gradableAssignmentWithPolicyFour method", "info");
            new LoginUsingLTI().ltiLogin("23_1");//login as student
            new Assignment().submitAssignmentAsStudent(23); //submit assignment
            ReportUtil.log("submit Assignment", "student submitted assignment", "pass");

            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyTwo method", "info");
            new LoginUsingLTI().ltiLogin("23");//login as instructor
            new Assignment().provideGradeToStudentForMultipleQuestions(23);
            ReportUtil.log("Provide grade", "Instructor provided grade successfully", "pass");

            ReportUtil.log("Login as student", "Login as student for gradableAssignmentWithPolicyFour method", "info");
            new LoginUsingLTI().ltiLogin("23_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            String score=assignments.getScore().getText();
            CustomAssert.assertEquals(score, "Score (1.4/4)", "verify student grade", "student is able to see grade", "student is not able to see grade");

            new LoginUsingLTI().ltiLogin("23");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to assignment

            int notStarted;
            int inProgress;
            int submitted;
            int graded;

            notStarted = new Assignment().statusBoxCount(23, "Not Started");
            Assert.assertEquals(notStarted,0,"Student status \"Not Started\" count is not 0");

            inProgress = new Assignment().statusBoxCount(23, "In Progress");
            Assert.assertEquals(inProgress,0,"Student status \"In Progress\" count is not 0");

            submitted = new Assignment().statusBoxCount(23, "Submitted");
            Assert.assertEquals(submitted,0,"Student status \"Submitted\" count is not 0");

            graded = new Assignment().statusBoxCount(23, "Graded");
            Assert.assertEquals(graded, 1, "Student status \"Graded\" count is not 1");

            new Assignment().verifyClassAssignmentStatus(23, "Needs Grading");


        } catch (Exception e) {
            Assert.fail("Exception in TC gradableAssignmentWithPolicyThree of class SanityTest ", e);
        }
    }

    @Test(priority=19,enabled = true)
    public void collaborationSupportWithEnable()
    {
        try {

            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicy", "24");
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "24");
            new Assignment().create(24);
            new Assignment().addQuestions(24, "qtn-type-true-false-img", assessmentName);
            ReportUtil.log("create assessment", "Assessment created  successfully", "info");

            ReportUtil.log("Login as instructor", "Login as instructor for collaborationSupportWithEnable method", "info");
            new LoginUsingLTI().ltiLogin("24");//login as instructor
            new Navigator().NavigateTo("Policies");//go to Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description21", "2", null, false, "1", "", "", "", "", "", "21");
            ReportUtil.log("Create Policy", "policy Four created successfully", "pass");
            new Assignment().assignToStudent(24);  //Assigning assignment
            ReportUtil.log("Assign Assignment", "Instructor assigned assignment to complete class section", "pass");

            ReportUtil.log("Login as student", "Login as student for gradableAssignmentWithPolicyFour method", "info");
            new LoginUsingLTI().ltiLogin("24_1"); //login as student 1
            new Assignment().openAssignmentFromCourseStream("24");//open the assignment
            new SelectAnswerAndSubmit().staticAssignment("A");//submit 1st question
            Thread.sleep(3000);
            new SelectAnswerAndSubmit().staticAssignment("A");//submit 2nd question
            new QuestionCard().clickOnCard("24", 1);//click on the question card
            discussionTab.discussion_Tab.click();//click on the discussion tab
            ReportUtil.log("Verify for Discussion Tab","Discussion Tab is displaying","pass");


        } catch (Exception e) {
            Assert.fail("Exception in TC collaborationSupportWithEnable of class SanityTest",e);
        }
    }


    @Test(priority=20,enabled = true)
    public void collaborationSupportWithDisable()
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            String assignmentPolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", "25");
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "2");

            new Assignment().create(25);
            new Assignment().addQuestions(25, "qtn-type-true-false-img", assessmentName);
            ReportUtil.log("create assessment", "Assessment created  successfully", "pass");

            ReportUtil.log("Login as instructor", "Login as instructor for collaborationSupportWithDisable method", "info");
            new LoginUsingLTI().ltiLogin("25");//login as instructor
            new Navigator().NavigateTo("Policies");//go to Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicy, "Policy description25", "1", "", false, "1", "", "", "", "", "");
            ReportUtil.log("Create Policy", "policy Four created successfully", "pass");

            new AssignmentPolicy().updateAllowCollaboration("25", false);
            ReportUtil.log("Updated AllowCollaboration", "AllowCollaboration is disabled successfully", "pass");

            new Assignment().assignToStudent(25);  //Assigning assignment
            ReportUtil.log("Assign Assignment", "Instructor assigned assignment to complete class section", "pass");

            ReportUtil.log("Login as student", "Login as student for collaborationSupportWithDisable method", "info");
            new LoginUsingLTI().ltiLogin("25_1"); //login as student 1
            new Assignment().openAssignmentFromCourseStream("25");//open the assignment

            new SelectAnswerAndSubmit().staticAssignment("A");//submit 1st question
            String qNo = driver.findElement(By.className("current-question-index")).getText();
            if(!qNo.contains("2"))
                CustomAssert.fail("Verify Second Question No","After submitting the 1st question with policy \"Allow Collaboration\" Disabled  the student does not navigate to second question");

            //Click on question dropdown to navigate the previous question..Discussion and Star tab should not appear
            new StaticAssignmentSubmit().navigateFromQuestionDropdown("1");//navigate question 1
            boolean isTabPresent = false;
            List<WebElement> allTabs = driver.findElements(By.className("tabs"));
            for(WebElement tab: allTabs)
            {
                Thread.sleep(2000);
                if(tab.getText().equals("Discussion")) {
                    isTabPresent = true;
                    break;
                }
            }
            if(isTabPresent == true)
                CustomAssert.fail("Verify for Discussion Tab ","The discussion tab for attempted question appears for assignment with policy \"Allow Collaboration\" Disabled.");

        }
        catch (Exception e) {
            Assert.fail("Exception in TC collaborationSupportWithDisable of class SanityTest",e);
        }
    }

    @Test(priority=21,enabled = true)
    public void roboNotificationOfDiagnosticTest()
    {
        try {

            ReportUtil.log("Login as student", "Login as student for roboNotificationOfDiagnosticTest method", "info");
            new LoginUsingLTI().ltiLogin("26");//login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            new Navigator().NavigateToOrion();//navigate to orion tab
            WebDriverUtil.clickOnElementUsingJavascript(lessonPage.beginDiagnostic.get(0));//Click on Begin Diagnostic button
            expected="Please say how confident you feel about this chapter. You will get more accurate recommendations!";
            actual=lessonPage.roboNotificationMessage.getText().trim();
            CustomAssert.assertEquals(actual, expected, "Verify for Robo notification", "Robo notification is displayed as expected", "Robo notification is not displayed as expected");
            new DiagnosticTest().startTest(2); //start Diagnostic test

        }
        catch (Exception e) {
            Assert.fail("Exception in TC roboNotificationOfDiagnosticTest of class SanityTest",e);
        }
    }


    @Test(priority=22,enabled = true)
    public void summaryReportOfDiagnosticAndPracticeTest()
    {
        try {
            ReportUtil.log("Login as student", "Login as student for summaryReportOfDiagnosticAndPracticeTest method", "info");
            new LoginUsingLTI().ltiLogin("27");//login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            new DiagnosticTest().startTest(2); //start Diagnostic test
            new DiagnosticTest().attemptAllCorrect(2, false, false); //attempt all correct question
            ReportUtil.log("Attempt Diagnostic test", "student completed diagnostic test", "pass");

            actual = performanceSummaryReport.title_performanceSummary.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            String questionNo = performanceSummaryReport.studentPerformance_questionNo.getText().trim();
            CustomAssert.assertEquals(questionNo, "20\nQuestions", "Verify Question Number", "Attempted Number of Question is displaying in Diagnostic test Performance Summary page ", "Attempted Number of Question not displaying correctly");

            new Navigator().NavigateTo("e-Textbook"); //navigate to eTextbook
            new PracticeTest().startTest();
            for(int i = 0; i < 13; i++) {
                new PracticeTest().AttemptCorrectAnswer(0,"27");
            }
            new PracticeTest().quitThePracticeTest();
            actual = performanceSummaryReport.title_performanceSummary.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            String practiceQuestionNo = performanceSummaryReport.studentPerformance_questionNo.getText().trim();
            CustomAssert.assertEquals(practiceQuestionNo, "13\nQuestions", "Verify Question Number Performance Summary Page", "Attempted Number of Question is displaying in Performance Summary page ", "Attempted Number of Question not displaying correctly");


        }
        catch (Exception e) {
            Assert.fail("Exception in TC summaryReportOfDiagnosticAndPracticeTest of class SanityTest",e);
        }
    }

    @Test(priority=23,enabled = true)
    public void quitAndContinueDiagnosticAndPracticeTest()
    {
        try {

            ReportUtil.log("Login as student", "Login as student for quitAndContinueDiagnosticAndPracticeTest method", "info");
            new LoginUsingLTI().ltiLogin("28");//login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            new DiagnosticTest().startTest(2); //start Diagnostic test
            new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, false);//Continue Later

            new Navigator().NavigateTo("e-Textbook");//navigate to eTextbook
            new DiagnosticTest().continueDiagnosticTest();
            Thread.sleep(2000);
            new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, true);
            ReportUtil.log("Attempt Diagnostic test", "student completed diagnostic test", "pass");

            actual = performanceSummaryReport.title_performanceSummary.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            String questionNo = performanceSummaryReport.studentPerformance_questionNo.getText().trim();
            CustomAssert.assertEquals(questionNo, "4\nQuestions", "Verify Question Number", "Attempted Number of Question is displaying in Diagnostic test Performance Summary page ", "Attempted Number of Question not displaying correctly");


            new Navigator().NavigateTo("e-Textbook"); //navigate to eTextbook
            new PracticeTest().startTest();
            for(int i = 0; i < 13; i++) {
                new PracticeTest().AttemptCorrectAnswer(0,"28");
            }
            ReportUtil.log("start practice Test", "practice test attempted successfully", "pass");
            new PracticeTest().quitThePracticeTest();
            actual = performanceSummaryReport.title_performanceSummary.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            String practiceQuestionNo = performanceSummaryReport.studentPerformance_questionNo.getText().trim();
            CustomAssert.assertEquals(practiceQuestionNo, "13\nQuestions", "Verify Question Number Performance Summary Page", "Attempted Number of Question is displaying in Performance Summary page ", "Attempted Number of Question not displaying correctly");

            Thread.sleep(4000);
            new QuestionCard().clickOnCard("28",1);
            ReportUtil.log("Click on Question card", "student clicked on question card successfully", "pass");
            new Navigator().navigateToTab("Discussion");
            String randomText=new RandomString().randomstring(3);
            new Discussion().postDiscussion(randomText);
            new Navigator().NavigateTo("Course Stream");
            String discussionTextCourseStreamPage = courseStreamPage.discussionEntry_courseStreamPage.get(1).getText().trim();
            CustomAssert.assertEquals(discussionTextCourseStreamPage, randomText, "Verify Course stream entry", "Course stream entry is  getting added for discussion", "Course stream entry is not getting added for discussion");

        }
        catch (Exception e) {
            Assert.fail("Exception in TC quitAndContinueDiagnosticAndPracticeTest of class SanityTest",e);
        }
    }

    @Test(priority=24,enabled = true)
    public void staticTestQuitContinue()
    {
        try {
            new LoginUsingLTI().ltiLogin("49");//login as student
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(0);
            new SelectCourse().selectInvisibleAssignment("1.2 Concept Check");
            new SelectAnswerAndSubmit().staticanswersubmit("A");
            lessonPage.quitPractice_Test.click();//Quit static Test
            String introduction=lessonPage.introduction_Text.getText().trim();
            CustomAssert.assertEquals("INTRODUCTION",introduction,"Verify lesson page","student navigated to Introduction page","after quiting the test student is not navigating to Intorduction page");

            new Navigator().NavigateTo("e-Textbook");
            new SelectCourse().selectInvisibleAssignment("1.2 Concept Check");
            new AttemptTest().StaticTest();
            actual = performanceSummaryReport.title_performanceSummary.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            String practiceQuestionNo = performanceSummaryReport.studentPerformance_questionNo.getText().trim();
            CustomAssert.assertEquals(practiceQuestionNo, "3\nQuestions", "Verify Question Number Performance Summary Page", "Attempted Number of Question is displaying in Performance Summary page ", "Attempted Number of Question not displaying correctly");

        } catch (Exception e) {
            Assert.fail("Exception in TC staticTestQuitContinue of class SanityTest ",e);
        }
    }


    @Test(priority = 25, enabled = true)
    public void generateAllReportForStudentAndInstructor() {
        try {
            WebDriver driver=Driver.getWebDriver();
            String actual = "";
            String expected = "";
            int actual_int = 0;
            int expected_int = 0;
            String height = "";
            int recentlyGraded = 0;
            String mostProficiencyByChapter = "";
            String mostPerformanceByChapter = "";
            String mostObjectiveProficiency = "";
            String mostPerformanceByObjective = "";

            Dashboard dashboard;
            Assignments assignments;
            ProficiencyReport proficiencyReport;
            MostChallengingReport mostChallengingReport;
            PerformanceSummaryReport performanceSummaryReport;
            CurrentAssignments currentAssignments;
            ProductivityReport productivityReport;
            productivityReport = PageFactory.initElements(driver, ProductivityReport.class);

            dashboard = PageFactory.initElements(driver, Dashboard.class);
            performanceSummaryReport = PageFactory.initElements(driver, PerformanceSummaryReport.class);
            currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            EngagementReport engagementReport;
            engagementReport = PageFactory.initElements(driver, EngagementReport.class);
            Gradebook gradebook;
            gradebook = PageFactory.initElements(driver, Gradebook.class);

            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", "1");

            ReportUtil.log("Create Question", "Create 10 questions of different type", "info");
            new Assignment().create(1);
            for (int i = 0; i < 4; i++)
                new Assignment().addQuestions(1, "truefalse", "");

            for (int i = 5; i < 6; i++)
                new Assignment().addQuestions(1, "multiplechoice", "");

            for (int i = 7; i < 9; i++)
                new Assignment().addQuestions(1, "multipleselection", "");

            for (int i = 10; i < 12; i++)
                new Assignment().addQuestions(1, "textentry", "");


            ReportUtil.log("Student Login", "Login as student one", "pass");
            new LoginUsingLTI().ltiLogin("1_1");//login as student 1

            ReportUtil.log("Student Login", "Login as student two", "pass");
            new LoginUsingLTI().ltiLogin("1_2");//login as student 2

            ReportUtil.log("Student Login", "Login as student three", "pass");
            new LoginUsingLTI().ltiLogin("1_3");//login as student 3


            ReportUtil.log("Instructor Login", "Login as instructor", "pass");
            new LoginUsingLTI().ltiLogin("1");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, false, "1", "", "Auto-release on due date", "", "", "", "1");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(1);

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            ReportUtil.log("Student Login", "Login as student one", "info");
            new LoginUsingLTI().ltiLogin("1_1");//login as student 1
            new Assignment().submitAssignmentAsStudent(1); //submit assignment

            actual = performanceSummaryReport.chartTitle.getText().trim();
            expected = "Performance Summary";
            CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

            String questionNo = performanceSummaryReport.performanceChart_questionNo.getText().trim();
            CustomAssert.assertEquals(questionNo, "10\nQuestions", "Verify Question Number", "Attempted Number of Question should be  " + questionNo + "", "Attempted Number of Question not displaying correctly");

            ReportUtil.log("Student Dashboard", "Navigate to student dashboard", "info");
            new Navigator().NavigateTo("Dashboard");
            actual = dashboard.getQuestionAttempted().getText().trim();
            expected = "10";
            CustomAssert.assertEquals(actual, expected, "Verify Question Attempted Number in dashboard", "Question Attempted Number is " + expected + "", "Question Attempted Number is not " + expected + "");

            new LoginUsingLTI().ltiLogin("1");//login as instructor
            new Navigator().NavigateTo("Gradebook"); //navigate to Gradebook
            Thread.sleep(4000);
            new Click().clickByclassname("ls-gradebook-basic-view-link");

            List<WebElement> grade = gradebook.getOverAllScore();
            int count = 0;
            for (int i = 1; i <= grade.size() - 1; i++) {
                if (!grade.get(i).getText().equals("NA")) {
                    count++;
                    break;
                }
            }

            if (count > 0) {
                CustomAssert.fail("Verify Assignment grade of each student", "Assignment grade of each student is not \"NA\"");
            }

            List<WebElement> weight = gradebook.gradebookWeight;
            if (weight.get(4).getText().trim().equals("Grades Not Released") && weight.get(9).getText().trim().equals("Grades Not Released") && weight.get(14).getText().trim().equals("Grades Not Released")) {
                ReportUtil.log("Verify assignment grade of each student", "Assignment grade of each student is displaying correct", "pass");
            } else {
                CustomAssert.fail("Verify assignment grade of each student", "Assignment grade of each student is not expected");

            }


            ReportUtil.log("Student Login", "Login as student two", "info");
            new LoginUsingLTI().ltiLogin("1_2");//login as student 2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            for (int i = 0; i < 5; i++) {
                new AttemptQuestion().trueFalse(false, "correct", 1);
                new Assignment().nextButtonInQuestionClick();
            }

            new AttemptQuestion().multipleChoice(false, "incorrect", 1);
            new Assignment().nextButtonInQuestionClick();

            ReportUtil.log("Student Dashboard", "Navigate to student dashboard", "info");
            new Navigator().NavigateTo("Dashboard");
            actual = dashboard.getQuestionAttempted().getText().trim();
            expected = "0";
            CustomAssert.assertEquals(actual, expected, "Verify Question Attempted Number in dashboard", "Question Attempted Number is " + expected + "", "Question Attempted Number is not " + expected + "");

            ReportUtil.log("Student Login", "Login as student three", "info");
            new LoginUsingLTI().ltiLogin("1_3");//login as student 3

            stopWatch.stop();
            System.out.println("time:" + stopWatch);

            Thread.sleep(300000); //wait for 3 minute for due to expiry

            new RunScheduledJobs().runScheduledJob("AutoSubmitAssignmentJob");
            new RunScheduledJobs().runScheduledJob("ClassSectionActivityJob");
            new RunScheduledJobs().runScheduledJob("ClassSectionPerformanceJob");

            driver = new ReInitDriver().startDriver("firefox");
            assignments = PageFactory.initElements(driver, Assignments.class);
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
            mostChallengingReport = PageFactory.initElements(driver, MostChallengingReport.class);
            productivityReport = PageFactory.initElements(driver, ProductivityReport.class);
            ReportUtil.log("Student Login", "Login as student three", "info");

            new LoginUsingLTI().ltiLogin("1_3");//login as student 3
            actual = dashboard.getQuestionAttempted().getText().trim();
            expected = "0";
            CustomAssert.assertEquals(actual, expected, "Verify Question Attempted Number in dashboard", "Question Attempted Number is " + expected + "", "Question Attempted Number is not " + expected + "");

            actual = dashboard.questionPerformance.getText().trim();
            expected = "0\n%";
            CustomAssert.assertEquals(actual, expected, "Verify Question performance in dashboard", "Question performance is displaying as " + expected + "", "Question performance is not displaying as " + expected + "");

            String overallScore1 = proficiencyReport.courseProficiencys.get(1).getText().trim();
            String overallPercentage = proficiencyReport.courseProficiencys.get(2).getText().trim();

            actual = overallScore1 + overallPercentage;
            expected = "0%";
            CustomAssert.assertEquals(actual, expected, "Verify overall score in dashboard", "overall score is displaying as " + expected + "", "overall score is not displaying as " + expected + "");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            String score = assignments.getScore().getText();
            CustomAssert.assertEquals(score, "Score (0/20)", "Verify student status in Assignment page", "student status is displaying as " + score + "", "student status is not displaying as " + score + "");

            Assert.assertEquals(assignments.completed_count.get(0).getText().trim(), "1 Graded");
            Assert.assertEquals(assignments.completed_count.get(1).getText().trim(), "0 Reviewed");


            new LoginUsingLTI().ltiLogin("1_2");//login as student 2
            actual = dashboard.getQuestionAttempted().getText().trim();
            expected = "6";
            CustomAssert.assertEquals(actual, expected, "Verify Question Attempted Number in dashboard", "Question Attempted Number is " + expected + "", "Question Attempted Number is not " + expected + "");

            actual = dashboard.questionPerformance.getText().trim();
            expected = "50\n%";
            CustomAssert.assertEquals(actual, expected, "Verify Question performance in dashboard", "Question performance is displaying as " + expected + "", "Question performance is not displaying as " + expected + "");

            String overallScore2 = proficiencyReport.courseProficiencys.get(1).getText().trim();
            String overallPercentage1 = proficiencyReport.courseProficiencys.get(2).getText().trim();

            actual = overallScore2 + overallPercentage1;
            expected = "50%";
            CustomAssert.assertEquals(actual, expected, "Verify overall score in dashboard", "overall score is displaying as " + expected + "", "overall score is not displaying as " + expected + "");

            height = dashboard.getGradedBarChart().getAttribute("height");
            recentlyGraded = Integer.parseInt(height);
            CustomAssert.assertTrue(recentlyGraded > 0, "Verify Recently Graded graph", "Recently Graded graph is generated", "Recently Graded graph is not generated");

            new LoginUsingLTI().ltiLogin("1_2");//login as student 2
            ReportUtil.log("Most Challenging Activities Report", "Navigate to Most Challenging Activities Report", "info");
            new Navigator().NavigateTo("Most Challenging Activities Report"); //navigate to Most Challenging Activities Report
            mostProficiencyByChapter = mostChallengingReport.getStudChapProficiency().get(0).getText().trim();
            System.out.println("Most Challenging proficiency By Chapter:" + mostProficiencyByChapter);
            CustomAssert.assertTrue(mostProficiencyByChapter.equals("95%"), "Verify Most Challenging proficiency By Chapter", "Most Challenging proficiency By Chapter is displaying correctly", "Most Challenging proficiency By Chapter is empty.");

            mostPerformanceByChapter = mostChallengingReport.getStudChapPerformance().get(0).getText().trim();
            System.out.println("Most Challenging performance By Chapter:" + mostPerformanceByChapter);
            CustomAssert.assertTrue(mostPerformanceByChapter.equals("10/20"), "Verify Most Challenging performance By Chapter", "Most Challenging performance By Chapter is displaying correctly", "Most Challenging performance By Chapter is empty.");

            mostChallengingReport.getStudViewObjective_Tab().click();//click on the view Objective tab

            mostObjectiveProficiency = mostChallengingReport.getStudChapProficiency().get(1).getText().trim();
            CustomAssert.assertTrue(mostObjectiveProficiency.equals("95%"), "Verify Most Challenging proficiency By Objective", "Most Challenging proficiency By Objective is displaying correctly", "Most Challenging proficiency By Objective is empty.");

            mostPerformanceByObjective = mostChallengingReport.getStudChapPerformance().get(1).getText().trim();
            CustomAssert.assertTrue(mostPerformanceByObjective.equals("10/20"), "Verify Most Challenging performance By Objective", "Most Challenging proficiency By performance is displaying correctly", "Most Challenging performance By Objective is empty.");


            ReportUtil.log("Proficiency Report", "Navigate to Proficiency Report", "info");
            new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report
            new TopicOpen().filterChapterInProficiencyReport(1, 1);

            String chapterProficiency = proficiencyReport.courseProficiencys.get(0).getText().trim();
            String chapterProficiencyPercentage = proficiencyReport.courseProficiencys.get(1).getText().trim();

            actual = chapterProficiency + chapterProficiencyPercentage;
            if (actual.equals("95%")) {
                expected = "95%";
                CustomAssert.assertEquals(actual, expected, "Verify Chapter Proficiency Summary", "Chapter Proficiency Summary is displaying as " + expected + "", "Chapter Proficiency Summary is not displaying as " + expected + "");
            } else {
                expected = "96%";
                CustomAssert.assertEquals(actual, expected, "Verify Chapter Proficiency Summary", "Chapter Proficiency Summary is displaying as " + expected + "", "Chapter Proficiency Summary is not displaying as " + expected + "");

            }
            String chapterProficiencyBarChart = proficiencyReport.getBarChart().getAttribute("height");
            int chapterProficiencyBarChartHeight = Integer.parseInt(chapterProficiencyBarChart);
            CustomAssert.assertTrue(chapterProficiencyBarChartHeight > 130, "Verify Chapter Proficiency By Objective ", "Chapter Proficiency By Objective graph is generated", "Chapter Proficiency By Objective graph is not generated");

            new Navigator().NavigateTo("Productivity Report"); //Navigate to Productivity Report
            ReportUtil.log("Productivity Report", "Navigated to Productivity Report", "Pass");
            Thread.sleep(2000);
            if (productivityReport.getStudProfPercentage().getText().equals("96%") || productivityReport.getStudProfPercentage().getText().equals("95%")) {
                ReportUtil.log("Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");
            } else {
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            if (productivityReport.getStudProfPercentage().getText().equals("96%") || productivityReport.getStudProfPercentage().getText().equals("95%")) {
                ReportUtil.log("Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");
            } else {
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }

            new Navigator().NavigateTo("Metacognitive Report"); //Navigate to Metacognitive Report
            ReportUtil.log("Metacognitive Report", "Navigated to Metacognitive Report", "Pass");

            if (productivityReport.getStudProfPercentage().getText().equals("96%") || productivityReport.getStudProfPercentage().getText().equals("95%")) {
                ReportUtil.log("Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");
            } else {
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            if (productivityReport.getStudProfPercentage().getText().equals("96%") || productivityReport.getStudProfPercentage().getText().equals("95%")) {
                ReportUtil.log("Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");
            } else {
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }


            new LoginUsingLTI().ltiLogin("1_1");//login as student 1
            actual = dashboard.getQuestionAttempted().getText().trim();
            expected = "10";
            CustomAssert.assertEquals(actual, expected, "Verify Question Attempted Number in dashboard", "Question Attempted Number is " + expected + "", "Question Attempted Number is not " + expected + "");

            actual = dashboard.questionPerformance.getText().trim();
            expected = "100\n%";
            CustomAssert.assertEquals(actual, expected, "Verify Question performance in dashboard", "Question performance is displaying as " + expected + "", "Question performance is not displaying as " + expected + "");


            String overallScore3 = proficiencyReport.courseProficiencys.get(1).getText().trim();
            String overallPercentage3 = proficiencyReport.courseProficiencys.get(2).getText().trim();


            actual = overallScore3 + overallPercentage3;
            expected = "100%";
            CustomAssert.assertEquals(actual, expected, "Verify overall score in dashboard", "overall score is displaying as " + expected + "", "overall score is not displaying as " + expected + "");

            height = dashboard.getGradedBarChart().getAttribute("height");
            recentlyGraded = Integer.parseInt(height);
            CustomAssert.assertTrue(recentlyGraded > 120, "Verify Recently Graded graph", "Recently Graded graph is generated", "Recently Graded graph is not generated");

            ReportUtil.log("Most Challenging Activities Report", "Navigate to Most Challenging Activities Report", "info");
            new Navigator().NavigateTo("Most Challenging Activities Report"); //navigate to Most Challenging Activities Report
            mostProficiencyByChapter = mostChallengingReport.getStudChapProficiency().get(0).getText().trim();
            System.out.println("Most Challenging proficiency By Chapter:" + mostProficiencyByChapter);
            CustomAssert.assertTrue(mostProficiencyByChapter.equals("99%") || mostProficiencyByChapter.equals("100%"), "Verify Most Challenging proficiency By Chapter", "Most Challenging proficiency By Chapter is displaying correctly", "Most Challenging proficiency By Chapter is empty.");

            mostPerformanceByChapter = mostChallengingReport.getStudChapPerformance().get(0).getText().trim();
            System.out.println("Most Challenging performance By Chapter:" + mostPerformanceByChapter);
            CustomAssert.assertTrue(mostPerformanceByChapter.equals("20/20"), "Verify Most Challenging performance By Chapter", "Most Challenging performance By Chapter is displaying correctly", "Most Challenging performance By Chapter is empty.");

            mostChallengingReport.getStudViewObjective_Tab().click();//click on the view Objective tab

            mostObjectiveProficiency = mostChallengingReport.getStudChapProficiency().get(1).getText().trim();
            CustomAssert.assertTrue(mostObjectiveProficiency.equals("99%"), "Verify Most Challenging proficiency By Objective", "Most Challenging proficiency By Objective is displaying correctly", "Most Challenging proficiency By Objective is empty.");

            mostPerformanceByObjective = mostChallengingReport.getStudChapPerformance().get(1).getText().trim();
            CustomAssert.assertTrue(mostPerformanceByObjective.equals("20/20"), "Verify Most Challenging performance By Objective", "Most Challenging proficiency By performance is displaying correctly", "Most Challenging performance By Objective is empty.");


            ReportUtil.log("Proficiency Report", "Navigate to Proficiency Report", "info");
            new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report
            new TopicOpen().filterChapterInProficiencyReport(1, 1);

            String chapterProficiency1 = proficiencyReport.courseProficiencys.get(0).getText().trim();
            String chapterProficiencyPercentage1 = proficiencyReport.courseProficiencys.get(1).getText().trim();

            actual = chapterProficiency1 + chapterProficiencyPercentage1;
            if (actual.equals("100%")) {
                expected = "100%";
                CustomAssert.assertEquals(actual, expected, "Verify Chapter Proficiency Summary", "Chapter Proficiency Summary is displaying as " + expected + "", "Chapter Proficiency Summary is not displaying as " + expected + "");
            } else {
                expected = "99%";
                CustomAssert.assertEquals(actual, expected, "Verify Chapter Proficiency Summary", "Chapter Proficiency Summary is displaying as " + expected + "", "Chapter Proficiency Summary is not displaying as " + expected + "");

            }

            String chapterProficiencyBarChart1 = proficiencyReport.getBarChart().getAttribute("height");
            int chapterProficiencyBarChartHeight1 = Integer.parseInt(chapterProficiencyBarChart1);
            CustomAssert.assertTrue(chapterProficiencyBarChartHeight1 > 130, "Verify Chapter Proficiency By Objective ", "Chapter Proficiency By Objective graph is generated", "Chapter Proficiency By Objective graph is not generated");

            new Navigator().NavigateTo("Productivity Report"); //Navigate to Productivity Report
            ReportUtil.log("Productivity Report", "Navigated to Productivity Report", "Pass");

            if (productivityReport.getStudProfPercentage().getText().equals("100%") || productivityReport.getStudProfPercentage().getText().equals("99%")) {
                ReportUtil.log("Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");
            } else {
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            if (productivityReport.getStudProfPercentage().getText().equals("100%") || productivityReport.getStudProfPercentage().getText().equals("99%")) {
                ReportUtil.log("Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");
            } else {
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }

            new Navigator().NavigateTo("Metacognitive Report"); //Navigate to Metacognitive Report
            ReportUtil.log("Metacognitive Report", "Navigated to Metacognitive Report", "Pass");

            if (productivityReport.getStudProfPercentage().getText().equals("100%") || productivityReport.getStudProfPercentage().getText().equals("99%")) {
                ReportUtil.log("Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");
            } else {
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            if (productivityReport.getStudProfPercentage().getText().equals("100%") || productivityReport.getStudProfPercentage().getText().equals("99%")) {
                ReportUtil.log("Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");
            } else {
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }


            ReportUtil.log("Instructor Login", "Login as login as instructor", "info");
            new LoginUsingLTI().ltiLogin("1");//login as instructor

            actual = dashboard.getQuestionAttempted().getText().trim();
            expected = "6";
            CustomAssert.assertEquals(actual, expected, "Verify Question Attempted Number in dashboard", "Question Attempted Number is " + expected + "", "Question Attempted Number is not " + expected + "");

               /* actual = dashboard.getAvgQuestionPerformance().get(1).getText().trim();
                expected = "50%";
                CustomAssert.assertEquals(actual, expected, "Verify Question performance in dashboard", "Question performance is displaying as " + expected + "", "Question performance is not displaying as " + expected + "");
*/
            String overallScore4 = proficiencyReport.courseProficiencys.get(1).getText().trim();
            String overallPercentage4 = proficiencyReport.courseProficiencys.get(2).getText().trim();

            actual = overallScore4 + overallPercentage4;
            expected = "50%";
            CustomAssert.assertEquals(actual, expected, "Verify overall score in dashboard", "overall score is displaying as " + expected + "", "overall score is not displaying as " + expected + "");

            height = dashboard.getGradedBarChart().getAttribute("height");
            recentlyGraded = Integer.parseInt(height);
            CustomAssert.assertTrue(recentlyGraded > 60, "Verify Recently Graded graph", "Recently Graded graph is generated", "Recently Graded graph is not generated");


            new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report
            String proficiencyBar = proficiencyReport.proficiencyBar.get(0).getAttribute("height");
            CustomAssert.assertTrue(Integer.parseInt(proficiencyBar) > 220, "Verify Class proficiency by chapter performance bar", "Class proficiency by chapter performance bar has generated", "Class proficiency by chapter performance bar has not generated");

            String avgPerformanceStudent1Num = proficiencyReport.getTloChapterQuestionPerformance().get(0).getText();
            String avgPerformanceStudent1Den = proficiencyReport.getTloQuestionPerformance().get(0).getText();
            String avgPerformanceStud1 = avgPerformanceStudent1Num + "/" + avgPerformanceStudent1Den;
            CustomAssert.assertEquals(avgPerformanceStud1, "10/20", "Verify Class proficiency by chapter performance", "Class proficiency by chapter performance for student1 is available.", "Class proficiency by chapter performance for student1 is not available.");

            String avgPerformanceStudent2Num = proficiencyReport.getTloChapterQuestionPerformance().get(1).getText();
            String avgPerformanceStudent2Den = proficiencyReport.getTloQuestionPerformance().get(1).getText();
            String avgPerformanceStud2 = avgPerformanceStudent2Num + "/" + avgPerformanceStudent2Den;
            CustomAssert.assertEquals(avgPerformanceStud2, "20/20", "Verify Class proficiency by chapter performance", "Class proficiency by chapter performance for student2 is available.", "Class proficiency by chapter performance for student2 is not available.");

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", proficiencyReport.getChapNumber());
            Thread.sleep(3000);

            String proficiencyBarByObjectives = proficiencyReport.proficiencyBar.get(0).getAttribute("height");
            CustomAssert.assertTrue(Integer.parseInt(proficiencyBarByObjectives) > 250, "Verify Class Proficiency by Chapters graph", "Class Proficiency by Chapters is generated correctly", "Class Proficiency by Chapters is not generated correctly");


            List<WebElement> tloProficiency = mostChallengingReport.getChapProficiency();
            if ((tloProficiency.get(0).getText().trim().equals("96%") || tloProficiency.get(0).getText().trim().equals("95%")) && (tloProficiency.get(1).getText().trim().equals("100%") || tloProficiency.get(1).getText().trim().equals("99%"))) {
                ReportUtil.log("Verify Class Proficiency By Students section", "Class Proficiency By Students proficiency is displaying correct", "pass");
            } else {
                CustomAssert.fail("Verify Class Proficiency By Students section ", "Class Proficiency By Students proficiency is not expected");

            }


            String avgChapterPerformanceStudent1Num = proficiencyReport.getTloChapterQuestionPerformance().get(0).getText();
            String avgChapterPerformanceStud1 = avgChapterPerformanceStudent1Num + "/" + "20";
            CustomAssert.assertEquals(avgChapterPerformanceStud1, "10/20", "Verify Class proficiency by chapter performance", "Class proficiency by chapter performance for student1 is available.", "Class proficiency by chapter performance for student1 is not available.");

            String avgChapterPerformanceStudent2Num = proficiencyReport.getTloChapterQuestionPerformance().get(1).getText();
            String avgChapterPerformanceStud2 = avgChapterPerformanceStudent2Num + "/" + "20";
            CustomAssert.assertEquals(avgChapterPerformanceStud2, "20/20", "Verify Class proficiency by chapter performance", "Class proficiency by chapter performance for student2 is available.", "Class proficiency by chapter performance for student2 is not available.");

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", proficiencyReport.getTloNumber()); //click on the tlo number
            Thread.sleep(3000);
            List<WebElement> objectivePerformanceProficiency = mostChallengingReport.getChapProficiency();
            if (objectivePerformanceProficiency.get(0).getText().trim().equals("95%") && objectivePerformanceProficiency.get(1).getText().trim().equals("99%")) {
                ReportUtil.log("Verify Objective Performance By Students Proficiency", "Objective Performance By Students Proficiency is displaying correct", "pass");
            } else {
                CustomAssert.fail("Verify Objective Performance By Students Proficiency section ", "Objective Performance By Students Proficiency is not expected");

            }
            List<WebElement> objectivePerformance = proficiencyReport.objectivePerformance;
            String value1 = ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent", objectivePerformance.get(0)).toString();
            String value2 = ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent", objectivePerformance.get(1)).toString();
            if (value1.equals("10  / 20") && value2.equals("20  / 20")) {
                ReportUtil.log("Verify Objective Performance By Students Proficiency", "Objective Performance By Students Proficiency is displaying correct", "pass");
            } else {
                CustomAssert.fail("Verify Objective Performance By Students Proficiency section ", "Objective Performance By Students Proficiency is not expected");

            }

            int ClassPerformanceByQuestions = proficiencyReport.proficiencyBar.size();
            System.out.println(ClassPerformanceByQuestions);
            CustomAssert.assertTrue(ClassPerformanceByQuestions == 10, "Verify Class Performance by Questions graph", "Class Performance by Questions bar chart is generated correctly", "Class Performance by Questions bar chart is not generated correctly");


            ReportUtil.log("Most Challenging Activities Report", "Navigate to Most Challenging Activities Report", "info");
            new Navigator().NavigateTo("Most Challenging Activities Report"); //navigate to Most Challenging Activities Report
            mostProficiencyByChapter = mostChallengingReport.getChapProficiency().get(0).getText().trim();
            System.out.println("Most Challenging proficiency By Chapter:" + mostProficiencyByChapter);
            CustomAssert.assertTrue(mostProficiencyByChapter.equals("98%") || mostProficiencyByChapter.equals("97%"), "Verify Most Challenging proficiency By Chapter", "Most Challenging proficiency By Chapter is displaying correctly", "Most Challenging proficiency By Chapter is empty.");

            mostPerformanceByChapter = mostChallengingReport.getChapPerformance().get(0).getText().trim();
            System.out.println("Most Challenging performance By Chapter:" + mostPerformanceByChapter);
            CustomAssert.assertTrue(mostPerformanceByChapter.equals("5/10"), "Verify Most Challenging performance By Chapter", "Most Challenging performance By Chapter is displaying correctly", "Most Challenging performance By Chapter is empty.");

            mostChallengingReport.getViewByObjective_Tab().click();//click on the view Objective tab
            Thread.sleep(2000);

            mostObjectiveProficiency = mostChallengingReport.getChapProficiency().get(1).getText().trim();
            CustomAssert.assertTrue(mostObjectiveProficiency.equals("97%"), "Verify Most Challenging proficiency By Objective", "Most Challenging proficiency By Objective is displaying correctly", "Most Challenging proficiency By Objective is empty.");

            mostPerformanceByObjective = mostChallengingReport.getChapPerformance().get(1).getText().trim();
            CustomAssert.assertTrue(mostPerformanceByObjective.equals("5/10"), "Verify Most Challenging performance By Objective", "Most Challenging proficiency By performance is displaying correctly", "Most Challenging performance By Objective is empty.");


            gradebook = PageFactory.initElements(driver, Gradebook.class);
            engagementReport = PageFactory.initElements(driver, EngagementReport.class);


            ReportUtil.log("Engagement Report", "Navigate to Engagement Report", "info");
            new Navigator().NavigateTo("Engagement Report");
            actual = engagementReport.questionPerformance_Count.getText().trim();
            expected = "30"; //No of Question
            CustomAssert.assertEquals(actual, expected, "Verify Question Number of Question Performance Summary", "Question Number of Question Performance Summary  is " + expected + "", "Question Number of Question Performance Summary is not " + expected + "");
            int sum = 0;
            for (int i = 2; i < 5; i++) {
                Thread.sleep(1000);
                System.out.println(i+":"+engagementReport.questionProficiency.get(i).getText());
                System.out.println(engagementReport.questionProficiency.get(i).getText().trim().substring(0, engagementReport.questionProficiency.get(i).getText().indexOf("%")));
                sum += Integer.parseInt(engagementReport.questionProficiency.get(i).getText().trim().substring(0, engagementReport.questionProficiency.get(i).getText().indexOf("%")));

            }
            actual_int = sum;
            expected_int = 100;
            CustomAssert.assertEquals(actual_int, expected_int, "Verify Question performance in Engagement Report", "Question performance is displaying as " + expected_int + "", "Question performance is not displaying as " + expected_int + "");

            List<WebElement> grades = engagementReport.studTotalGrades;
            for (int i = 0; i <grades.size(); i++) {
                System.out.println(grades.get(i).getText().trim());
            }
            if (grades.get(0).getText().trim().equals("50.0%") && grades.get(1).getText().trim().equals("100.0%") && grades.get(2).getText().trim().equals("0.0%")) {
                ReportUtil.log("Verify Total grade", "Total grade is displaying correct", "pass");
            } else {
                CustomAssert.fail("Total grade", "Total grade is displaying correct is not expected");

            }

            ReportUtil.log("Gradebook", "Navigate to Gradebook", "info");
            new Navigator().NavigateTo("Gradebook"); //navigate to Gradebook
            Thread.sleep(4000);
            new Click().clickByclassname("ls-gradebook-basic-view-link");

            List<WebElement> weight1 = gradebook.gradebookWeight;
            for (int i = 0; i <weight1.size() ; i++) {
                System.out.println(i+":"+weight1.get(i).getText());
            }
            if (weight1.get(4).getText().trim().equals("10/20") && weight1.get(9).getText().trim().equals("20/20") && weight1.get(14).getText().trim().equals("0/20")) {
                ReportUtil.log("Verify assignment grade of each student", "Assignment grade of each student is displaying correct", "pass");
            } else {
                CustomAssert.fail("Verify assignment grade of each student", "Assignment grade of each student is not expected");

            }
            List<WebElement> overallScore = gradebook.getOverAllScore();
            for (int i = 0; i <overallScore.size() ; i++) {
                System.out.println(i+":"+overallScore.get(i).getText());
            }
            if (overallScore.get(1).getText().trim().equals("50%") && overallScore.get(2).getText().trim().equals("100%") && overallScore.get(3).getText().trim().equals("0%")) {
                ReportUtil.log("Verify overall score of each student", "overall score of each student is displaying correct", "pass");
            } else {
                CustomAssert.fail("overall score grade of each student", "overall score of each student is not expected");

            }


            new Navigator().NavigateTo("Productivity Report"); //Navigate to Productivity Report
            ReportUtil.log("Productivity Report", "Navigated to Productivity Report", "Pass");

            List<WebElement> profReport4 = productivityReport.insProficiency;
            if (profReport4.get(0).getText().trim().equals("95%") && profReport4.get(1).getText().trim().equals("99%")) {
                ReportUtil.log("Verify Proficiency", "Proficiency for each student is displaying correct", "pass");
            } else {
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            if (productivityReport.getStudProficiency().getText().equals("99%") || productivityReport.getStudProficiency().getText().equals("100%")) {
                ReportUtil.log("Verify Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");
            } else {
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }
            new Navigator().NavigateTo("Metacognitive Report"); //Navigate to Metacognitive Report
            ReportUtil.log("Metacognitive Report", "Navigated to Metacognitive Report", "Pass");

            List<WebElement> profReport5 = productivityReport.insProficiency;
            if (profReport5.get(0).getText().trim().equals("95%") && profReport5.get(1).getText().trim().equals("99%")) {
                ReportUtil.log("Verify Proficiency", "Proficiency for each student is displaying correct", "pass");
            } else {
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            if (productivityReport.getStudProficiency().getText().equals("99%") || productivityReport.getStudProficiency().getText().equals("100%")) {
                ReportUtil.log("Verify Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");
            } else {
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }

        } catch (Exception e) {
            Assert.fail("Exception in TC generateAllReportForStudentAndInstructor of class SanityTest", e);
        }
    }




    @Test(priority=26,enabled = true)
    public void courseStreamSocialActivity()
    {
        try {

            new LoginUsingLTI().ltiLogin("40");
            ReportUtil.log("Login as Student", "Student logged in successfully", "Pass");

            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "Navigated to Course Stream successfully", "Pass");

            new PostMessage().postmessage("Course stream post");
            ReportUtil.log("Post message", "Posted a message successfully", "Pass");
            commentLikeBookMark();

            new PostMessage().postlink("www.google.com");
            ReportUtil.log("Post Link", "Posted a link successfully", "Pass");
            commentLikeBookMark();

            new FileUpload().fileUpload("40", true);
            ReportUtil.log("Upload a file", "Uploaded a file successfully", "Pass");
            WebDriverUtil.waitTillVisibilityOfElement(courseStreamPage.sharedAFile, 200);

            new LoginUsingLTI().ltiLogin("40");
            new Navigator().NavigateTo("Course Stream");
            commentLikeBookMark();

        } catch (Exception e) {
            Assert.fail("Exception in test case courseStreamSocialActivity of class SanityTest " + e);
        }
    }


    @Test(priority=27,enabled = true)
    public void eTextbookSocialActivity()
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            new LoginUsingLTI().ltiLogin("41");
            ReportUtil.log("Login as Student", "Student logged in successfully", "Pass");

            new Navigator().NavigateTo("e-Textbook");
            ReportUtil.log("Navigate to e-Textbook", "Navigated to e-Textbook successfully", "Pass");

            new TOCShow().tocHide();
            String comment = new RandomString().randomstring(6);
            new Navigator().navigateToTab("Discussion");
            ReportUtil.log("Navigate to Discussion tab", "Navigated to Discussion tab successfully", "Pass");

            String discussionText = new RandomString().randomstring(6);
            new Discussion().postDiscussion(discussionText);//post a discussion
            ReportUtil.log("Post Discussion", "Posted a Discussion successfully", "Pass");

            new Discussion().commentOnDiscussion(0, comment);//post need to be created first
            ReportUtil.log("Comment on Discussion", "Comment on Discussion successfully", "Pass");

            discussionTab.like_Link.click();//click on like
            discussionTab.bookmark_icon.get(0).click();//click on bookmark

            new ResourseCreate().resourseCreate(41, 0);
            ReportUtil.log("Create Resource", "Resource created successfully", "Pass");

            new LoginUsingLTI().ltiLogin("41");
            ReportUtil.log("Login as Student", "Student logged in successfully", "Pass");

            new Navigator().NavigateTo("eTextbook");
            ReportUtil.log("Navigate to e-Textbook", "Navigated to e-Textbook successfully", "Pass");

            new TOCShow().tocHide();
            new Navigator().navigateToResourceTab();
            ReportUtil.log("Navigate to Resource tab", "Navigated to Resource tab successfully", "Pass");

            new Navigator().openResourceFromResourceTab(41);

            new Navigator().NavigateTo("eTextbook");
            ReportUtil.log("Navigate to e-Textbook", "Navigated to e-Textbook successfully", "Pass");

            new TOCShow().tocHide();
            new Navigator().navigateToResourceTab();
            ReportUtil.log("Navigate to Resource tab", "Navigated to Resource tab successfully", "Pass");

            new Navigator().openResourceFromResourceTab(41);
            new CommentOnPost().commentOnPost("new post", 1);
            Thread.sleep(3000);
            for (int i = 0; i<5 ; i++) {
                try {
                    WebDriverUtil.clickOnElementUsingJavascript(resourceTab.bookmark_icon);
                    break;
                } catch (Exception e) {
                }
            }

            WebElement element = resourceTab.removeBookmark_icon;
            String bookMark = element.getAttribute("title");
            if(!bookMark.equals("Remove bookmark")) {
                CustomAssert.fail("Verify bookmark","Bookmark on the first page failed");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case eTextbookSocialActivity of class SanityTest "+e);
        }
    }


    @Test(priority=28,enabled = true)
    public void widgetSocialActivity()
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            new LoginUsingLTI().ltiLogin("42");//login as student
            ReportUtil.log("Login as Student", "Student logged in successfully", "Pass");

            new Navigator().NavigateTo("e-Textbook");//navigate to eTextbook
            ReportUtil.log("Navigate to e-Textbook", "Navigated to e-Textbook successfully", "Pass");

            new TOCShow().tocHide();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",lessonPage.widget);//click on the widget
            String widgetComment = new RandomString().randomstring(6);
            lessonPage.widgetComment_text.get(0).sendKeys(widgetComment+ Keys.RETURN);
            String expectedComment=lessonPage.comment.getText();
            if(!expectedComment.contains(widgetComment))
                CustomAssert.fail("Verify comment", "comment are not  posted");
            new AssignmentSocialElement().clickonlike(0);
            String like= lessonPage.unlike_Link.getAttribute("title");
            CustomAssert.assertEquals("Unlike",like,"Verify like","Like posted successfully","Like post failed");

        } catch (Exception e) {
            Assert.fail("Exception in test case widgetSocialActivity of class SanityTest "+e);
        }
    }

    @Test(priority=29,enabled = true)
    public void AssignmentEntrySocialActivity()
    {
        try {

            new Assignment().create(30); //create assignment
            ReportUtil.log("create assessment", "Assessment created with all question type successfully", "pass");
            ReportUtil.log("Login as instructor", "Login as instructor for AssignmentEntrySocialActivity method", "pass");
            new LoginUsingLTI().ltiLogin("30");//login as instructor
            new Assignment().assignToStudent(30); //assign to the student
            ReportUtil.log("Assign Assignment", "Instructor assigned assignment to complete class section", "pass");
            String comment = new RandomString().randomstring(6);
            boolean commentDone = new CommentOnPost().commentOnPost(comment, 0);
            if(!commentDone) {
                CustomAssert.fail("Verify Comment", "Comment on assignment failed");
            }

            new AssignmentSocialElement().clickonlike(0);
            int likeCountOfCurrentPost = new AssignmentSocialElement().countoflikecoursestream(0);
            if(likeCountOfCurrentPost != 1) {
                CustomAssert.fail("Verify Like", "Like on the assignment failed");
            }

            ReportUtil.log("Login as Student", "Student logged in successfully", "Pass");
            new LoginUsingLTI().ltiLogin("30_1");//login as student
            new Navigator().NavigateTo("Course Stream");
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.commentLinkIn_CSPage.get(0));
            String csComment = new RandomString().randomstring(6);
            courseStreamPage.commentBox.get(0).sendKeys(csComment + Keys.RETURN);
            Thread.sleep(3000);
            actual=courseStreamPage.postComment_content.get(1).getText().trim();
            if(!actual.contains(csComment)) {
                CustomAssert.fail("Verify Comment", "Comment on course stream failed");
            }
            new AssignmentSocialElement().clickonlike(0);//click on like
            String studentLike= courseStreamPage.like_count.getText();
            CustomAssert.assertEquals("2",studentLike,"Verify Student like","student able to like post","student is not able to like post");


        } catch (Exception e) {
            Assert.fail("Exception in TC AssignmentEntrySocialActivity of class SanityTest ",e);
        }
    }

    @Test(priority=30,enabled = true)
    public void myNotesAndActivity()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            ReportUtil.log("Login as student", "Login as student for myNotesAndActivity method", "info");
            new LoginUsingLTI().ltiLogin("31"); //login as student
            new Navigator().NavigateTo("eTextbook");
            WebDriverUtil.waitTillVisibilityOfElement(lessonPage.getCrossIcon(), 50);
            new TOCShow().tocHide();
            String randomText = new RandomString().randomstring(6);
            new Navigator().navigateToTab("Fav"); //Navigate to star tab
            new Discussion().postNote(randomText);//post a note
            Thread.sleep(5000);
            String starText = discussionTab.discussionEntry_lessonPage.get(0).getText().trim();
            CustomAssert.assertEquals(starText, randomText, "Verify star tab Entry", "note is added in star tab", "note is not added in star tab");

            new Navigator().NavigateTo("My Notes");
            ReportUtil.log("Navigate to My Notes", "Navigated to My Notes successfully", "Pass");
            String noteDescription=myNote.note_description.getText().trim();
            CustomAssert.assertEquals(noteDescription, randomText, "Verify note entry", "note is added in MyNotes page", "note is not added in MyNotes page");

            new Navigator().clickOnJumpOutIcon();//click on jump out icon
            try {
                WebDriverUtil.waitTillVisibilityOfElement(discussionTab.editNote_wrapper, 50);
                CustomAssert.assertEquals(discussionTab.editNote_wrapper.isDisplayed(), true, "Verify  note.", "student landed on that  note.", "student is not landing on that  note.");
                driver.findElement(By.xpath("/html/body")).click();
            } catch (Exception e) {

            }
            Thread.sleep(5000);
            String updatedStarText = discussionTab.discussionEntry_lessonPage.get(0).getText().trim();
            CustomAssert.assertEquals(updatedStarText, randomText, "Verify star tab Entry", "note is updated in star tab", "note is not updated in star tab");

            new Navigator().NavigateTo("My Activity");
            ReportUtil.log("Navigate to My Activity", "Navigated to My Activity successfully", "Pass");
            myActivity.noteLink.get(1).click();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in TC myNotesAndActivity of class SanityTest ",e);
        }
    }

    @Test(priority=31,enabled = true)
    public void studentToStudentSocialPolicy()
    {
        try
        {
            ReportUtil.log("Login as instructor", "Login as instructor for studentToStudentSocialPolicy method", "pass");
            new LoginUsingLTI().ltiLogin("32");//login as instructor
            new SetSocialPolicy().setSocialPolicy("32", "two");
            ReportUtil.log("set social policy", " Student to Student” level communication only", "pass");

            ReportUtil.log("Login as student", "Login as student for studentToStudentSocialPolicy method", "info");
            new LoginUsingLTI().ltiLogin("32_1");//login as student
            new Navigator().NavigateTo("Course Stream");
            String socialPolicyConfigurationText=courseStreamPage.socialPolicyConfiguration_text.getText().trim();//validate warning message
            String expected="Any discussions for this class section are not monitored by your instructor.";
            CustomAssert.assertEquals(socialPolicyConfigurationText, expected,"verify social-policy-configuration text", "social-policy-configuration-text warning is visible","social-policy-configuration-text warning is not visible");

            new Navigator().NavigateTo("Assignments");
            ReportUtil.log("Navigate to Assignments", "Navigated to Assignments successfully", "Pass");
            new MouseHover().mouserhover("social-policy-configuration");//Assignment Page

            String socialPolicyConfigurationTooltip=assignments.socialPolicyConfigurationWrapper_tooltip.getText().trim();//validate warning message
            expected="Any discussions for this class section are not monitored by your instructor.";
            CustomAssert.assertEquals(socialPolicyConfigurationTooltip, expected,"verify social-policy-configuration text", "social-policy-configuration-text warning is visible assignment page","social-policy-configuration-text warning is not visible assignment page");

            new Navigator().NavigateTo("e-Textbook");
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Discussion");
            new Click().clickBycssselector("a[class='ls-toc-btn ls-right-new-btn']");
            new MouseHover().mouserhover("social-policy-configuration");//Discussion tab
            String discussionTooltip=assignments.socialPolicyConfigurationWrapper_tooltip.getText().trim();//validate warning message
            expected="Any discussions for this class section are not monitored by your instructor.";
            CustomAssert.assertEquals(discussionTooltip, expected, "verify social-policy-configuration text", "social-policy-configuration-text warning is visible in Discussion", "social-policy-configuration-text warning is not visible assignment page");

            String postMessageOfStud = new RandomString().randomstring(3);
            new Navigator().NavigateTo("Course Stream"); //Navigate to course stream
            new PostMessage().postmessage(postMessageOfStud);
            new Navigator().NavigateTo("Course Stream");
            String discussionTextCourseStreamPage = courseStreamPage.postText.getText().trim();
            CustomAssert.assertEquals(discussionTextCourseStreamPage, postMessageOfStud, "Verify Course stream entry", "Student one is able to see  post", "Student one is not able to see  post");

            ReportUtil.log("Login as instructor", "Login as instructor for studentToStudentSocialPolicy method", "pass");
            new LoginUsingLTI().ltiLogin("32");
            new Navigator().NavigateTo("Course Stream");
            discussionTextCourseStreamPage = courseStreamPage.postText.getText().trim();
            CustomAssert.assertEquals(discussionTextCourseStreamPage, postMessageOfStud, "Verify Course stream entry", "Instructor is able to see student post", "Instructor is not able to see student post");
        }
        catch(Exception e) {
            Assert.fail("Exception in TC studentToStudentSocialPolicy of class SanityTest ",e);
        }
    }

    @Test(priority = 32,enabled = true)
    public void studentToInstructorSocialPolicy()
    {
        try {

            ReportUtil.log("Login as instructor", "Login as instructor for studentToInstructorSocialPolicy method", "pass");
            new LoginUsingLTI().ltiLogin("33");//login as instructor
            new SetSocialPolicy().setSocialPolicy("33", "three");//set policy level3
            ReportUtil.log("set social policy", "Student to Instructor level communication only", "pass");
            new Navigator().NavigateTo("Course Stream");
            String instructorPost = new RandomString().randomstring(3);
            new PostMessage().postmessage(instructorPost);
            ReportUtil.log("Post Message", "Student post message to instructor successfully", "pass");

            ReportUtil.log("Login as student2", "Login as student2 for studentToInstructorSocialPolicy method", "info");
            new LoginUsingLTI().ltiLogin("33_2");//login as student2
            new Navigator().NavigateTo("Course Stream");
            String discussionTextCourseStreamPage = courseStreamPage.postText.getText().trim();
            CustomAssert.assertEquals(discussionTextCourseStreamPage, instructorPost, "Verify Course stream entry", "Student one is able to see  post", "Student one is not able to see student post");

            new LoginUsingLTI().ltiLogin("33_1");//login as student1
            new Navigator().NavigateTo("Course Stream");
            discussionTextCourseStreamPage = courseStreamPage.postText.getText().trim();
            CustomAssert.assertEquals(discussionTextCourseStreamPage, instructorPost, "Verify Course stream entry", "Student one is able to see  post", "Student one is not able to see student post");

            ReportUtil.log("Login as instructor", "Login as instructor for studentToInstructorSocialPolicy method", "pass");
            new LoginUsingLTI().ltiLogin("33");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            discussionTextCourseStreamPage = courseStreamPage.postText.getText().trim();
            CustomAssert.assertEquals(discussionTextCourseStreamPage, instructorPost, "Verify Course stream entry", "Instructor is able to see student post", "Instructor is not able to see student post");

        } catch (Exception e) {
            Assert.fail("Exception in TC studentToInstructorSocialPolicy of class SanityTest ",e);
        }
    }

    @Test(priority = 33, enabled = true)
    public void postDiscussionWithLanguagePallete() {
        try {
            ReportUtil.log("Login as student", "Login as student for postDiscussionLinkFileForClass method", "info");
            new LoginUsingLTI().ltiLogin("50");//login as student
            new Navigator().NavigateTo("Course Stream");
            new PostMessage().postMessageWithLangaugePallete(50);
            String discussionTextCourseStreamPage = courseStreamPage.postText.getText().trim();
            CustomAssert.assertEquals(discussionTextCourseStreamPage, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "Verify Course stream entry", "Discussion posted with spanish-language", "Discussion not posted with spanish-language");

            new PostMessage().postMessageWithLangaugePallete(51);
            discussionTextCourseStreamPage = courseStreamPage.postText.getText().trim();
            CustomAssert.assertEquals(discussionTextCourseStreamPage, "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "Verify Course stream entry", "Discussion posted with italian-language", "Discussion not posted with italian-language");

            new PostMessage().postMessageWithLangaugePallete(52);
            discussionTextCourseStreamPage = courseStreamPage.postText.getText().trim();
            CustomAssert.assertEquals(discussionTextCourseStreamPage, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "Verify Course stream entry", "Discussion posted with french-language", "Discussion not posted with french-language");


        } catch (Exception e) {
            Assert.fail("Exception in TC postDiscussionWithLanguagePallete of class SanityTest ", e);
        }
    }

    public  void commentLikeBookMark()
    {
        String comment = new RandomString().randomstring(6);
        boolean commentDone = new CommentOnPost().commentOnPost(comment, 0);
        if(!commentDone) {
            CustomAssert.fail("Verify Comment", "Comment on first post failed");
        }

        new AssignmentSocialElement().clickonlike(0);
        int likeCountOfCurrentPost = new AssignmentSocialElement().countoflikecoursestream(0);
        if(likeCountOfCurrentPost != 1) {
            CustomAssert.fail("Verify Like", "Like on the first post failed");
        }

        new Bookmark().bookmarkFromCourseStream();
        WebElement element = courseStreamPage.activeTab;
        String bookMark = element.getAttribute("title");
        if(!bookMark.equals("Remove bookmark")) {
            CustomAssert.fail("Verify BookMark", "Bookmark on the first post failed");
        }
    }

    public static boolean isClickable(WebElement element) {
        WebDriver driver = Driver.getWebDriver();
        try {
            element.click();
            return true;
        } catch(Exception e){
            return false;
        }

    }

}
