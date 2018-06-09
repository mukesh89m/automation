package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT20.R2010;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyActivity.MyActivity;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R1714.AssigningMPQAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R1714.AssigningMPQAssignmentAsMentor;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 5/14/2015.
 */
public class QuestionContentStatusViewForQAVendor extends Driver {
    @Test(priority = 1, enabled = true)
    public void visualIndicatorFunctionality() {
        try {
            //tc row no 9
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            new Assignment().create(9);
            new DirectLogin().loginAsQAVendorRole("10");//Log in as QA Vendor instructor role
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new TOCShow().chaptertree();//Click on toc
            Assert.assertEquals(lessonPage.chapterContainer.get(0).isDisplayed(), true, "E-text book is not opened");
            new TopicOpen().chapterOpen(1);//click on 2nd chapter
            boolean visualIndicator = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_10']/parent::div/span[1]")).isDisplayed();
            Assert.assertEquals(visualIndicator, true, "Visual indicator for assignment is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case visualIndicatorFunctionality in class QuestionContentStatusViewForQAVendor", e);
        }
    }


    @Test(priority = 2, enabled = true)
    public void visualIndicatorFunctionalityForPublishedStatus() {
        try {
            //tc row no 14
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            new Assignment().create(14);
            new DirectLogin().loginAsQAVendorRole("15");//Log in as QA Vendor instructor role
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new TOCShow().chaptertree();//Click on toc
            Assert.assertEquals(lessonPage.chapterContainer.get(0).isDisplayed(), true, "E-text book is not opened");
            new TopicOpen().chapterOpen(1);//click on 2nd chapter
            boolean visualIndicator = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_14']/parent::div/span[1]")).isDisplayed();
            Assert.assertEquals(visualIndicator, true, "Visual indicator for assignment is not displaying");
            Actions act = new Actions(driver);
            WebElement element = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_14']/parent::div/span[1]"));
            act.moveToElement(element).build().perform();
            String status = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_14']/parent::div/span[1]")).getAttribute("item-status");
            Assert.assertEquals(status, "Published", "Published text is not displaying as a tool tip");

        } catch (Exception e) {
            Assert.fail("Exception in test case visualIndicatorFunctionalityForPublishedStatus in class QuestionContentStatusViewForQAVendor", e);
        }
    }


    @Test(priority = 3, enabled = true)
    public void visualIndicatorFunctionalityForPublishedStatusWithDeactivatedQuestion() {
        try {
            //tc row no 14
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "19");
            String QuestionNo = ReadTestData.readDataByTagName("", "QuestionNo", "19");
            new Assignment().create(19);
            new Assignment().addQuestions(19, "writeboard", "");
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectcourse();//select a course
            Thread.sleep(2000);
            driver.findElements(By.cssSelector("div[class='course-chapter-label node']")).get(1).click();//select 3rd chapter
            new AssigningMPQAssignmentAsMentor().selectInvisiblebottomAssignment(assignmentname);
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Jump To Q#']")));
            driver.findElement(By.xpath("//a[@title='Jump To Q#']")).click();
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.linkText(QuestionNo)));
            driver.findElement(By.linkText(QuestionNo)).click();
            Thread.sleep(2000);
            new Click().clickByid("questionOptions"); //click on question option
            Thread.sleep(2000);
            new Click().clickByid("questionRevisions"); // click on revisions
            Thread.sleep(2000);
            new Click().clickByid("cms-question-revision-deactivate-button");//click on the deactivate button

            new DirectLogin().loginAsQAVendorRole("20");//Log in as QA Vendor instructor role
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new TOCShow().chaptertree();//Click on toc
            Assert.assertEquals(lessonPage.chapterContainer.get(0).isDisplayed(), true, "E-text book is not opened");
            new TopicOpen().chapterOpen(1);//click on 2nd chapter
            boolean visualIndicator = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_19']/parent::div/span[1]")).isDisplayed();
            Assert.assertEquals(visualIndicator, true, "Visual indicator for assignment is not displaying");
            Actions act = new Actions(driver);
            WebElement element = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_19']/parent::div/span[1]"));
            act.moveToElement(element).build().perform();
            String status = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_19']/parent::div/span[1]")).getAttribute("item-status");
            Assert.assertEquals(status, "Published", "Published text is not displaying as a tool tip");
            driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_19']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            String str = driver.findElement(By.className("tryit-preview-dropdown-container")).getText();
            if (!str.equals("Question (1 of 1)"))
                Assert.fail("Deactivated question is displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test case visualIndicatorFunctionalityForPublishedStatusWithDeactivatedQuestion in class QuestionContentStatusViewForQAVendor", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void visualIndicatorFunctionalityForQAStatus() {
        try {
            //tc row no 25
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            new DirectLogin().loginAsQAVendorRole("25");//Log in as QA Vendor instructor role
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new TOCShow().chaptertree();//Click on toc
            Assert.assertEquals(lessonPage.chapterContainer.get(0).isDisplayed(), true, "E-text book is not opened");
            new TopicOpen().chapterOpen(1);//click on 2nd chapter
            boolean visualIndicator = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_10']/parent::div/span[1]")).isDisplayed();
            Assert.assertEquals(visualIndicator, true, "Visual indicator for assignment is not displaying");
            Actions act = new Actions(driver);
            WebElement element = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_10']/parent::div/span[1]"));
            act.moveToElement(element).build().perform();
            String status = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_10']/parent::div/span[1]")).getAttribute("item-status");
            Assert.assertEquals(status, "QA", "Published text is not displaying as a tool tip");

        } catch (Exception e) {
            Assert.fail("Exception in test case visualIndicatorFunctionalityForQAStatus in class QuestionContentStatusViewForQAVendor", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void visualIndicatorFunctionalityAsStudent() {
        try {
            //tc row no 30
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            new DirectLogin().loginAsQAVendorRole("30");//Log in as QA Vendor student role
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new TOCShow().chaptertree();//Click on toc
            Assert.assertEquals(lessonPage.chapterContainer.get(0).isDisplayed(), true, "E-text book is not opened");
            new TopicOpen().chapterOpen(1);//click on 2nd chapter
            boolean visualIndicator = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_10']/parent::div/span[1]")).isDisplayed();
            Assert.assertEquals(visualIndicator, true, "Visual indicator for assignment is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case visualIndicatorFunctionalityAsStudent in class QuestionContentStatusViewForQAVendor", e);
        }
    }

    @Test(priority = 7, enabled = true)
    public void visualIndicatorFunctionalityForPublishedStatusAsStudent() {
        try {
            //tc row no 35
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            new DirectLogin().loginAsQAVendorRole("35");//Log in as QA Vendor student role
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new TOCShow().chaptertree();//Click on toc
            Assert.assertEquals(lessonPage.chapterContainer.get(0).isDisplayed(), true, "E-text book is not opened");
            new TopicOpen().chapterOpen(1);//click on 2nd chapter
            boolean visualIndicator = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_14']/parent::div/span[1]")).isDisplayed();
            Assert.assertEquals(visualIndicator, true, "Visual indicator for assignment is not displaying");
            Actions act = new Actions(driver);
            WebElement element = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_14']/parent::div/span[1]"));
            act.moveToElement(element).build().perform();
            String status = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_14']/parent::div/span[1]")).getAttribute("item-status");
            Assert.assertEquals(status, "Published", "Published text is not displaying as a tool tip");

        } catch (Exception e) {
            Assert.fail("Exception in test case visualIndicatorFunctionalityForPublishedStatusAsStudent in class QuestionContentStatusViewForQAVendor", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void visualIndicatorFunctionalityForQAStatusAsStudent() {
        try {
            //tc row no 40
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            new DirectLogin().loginAsQAVendorRole("40");//Log in as QA Vendor student role
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new TOCShow().chaptertree();//Click on toc
            Assert.assertEquals(lessonPage.chapterContainer.get(0).isDisplayed(), true, "E-text book is not opened");
            new TopicOpen().chapterOpen(1);//click on 2nd chapter
            boolean visualIndicator = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_10']/parent::div/span[1]")).isDisplayed();
            Assert.assertEquals(visualIndicator, true, "Visual indicator for assignment is not displaying");
            Actions act = new Actions(driver);
            WebElement element = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_10']/parent::div/span[1]"));
            act.moveToElement(element).build().perform();
            String status = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_10']/parent::div/span[1]")).getAttribute("item-status");
            Assert.assertEquals(status, "QA", "Published text is not displaying as a tool tip");

        } catch (Exception e) {
            Assert.fail("Exception in test case visualIndicatorFunctionalityForQAStatusAsStudent in class QuestionContentStatusViewForQAVendor", e);
        }
    }


    @Test(priority = 9, enabled = true)
    public void visualIndicatorFunctionalityForPublishedStatusOfAssessment() {
        try {
            //tc row no 45
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Assignment().create(44);
            new DirectLogin().loginAsQAVendorRole("45");//Log in as QA Vendor instructor role
            new Assignment().assignToStudent(45);

            new DirectLogin().loginAsQAVendorRole("45_1");//Log in as QA Vendor student role
            new Assignment().submitAssignmentAsStudent(45);
            new ClickOnquestionCard().clickonquestioncard(0);
            assignments.getDiscussionTab().click();
            assignments.getNewButton().click();
            assignments.getEditBox().sendKeys("This is discussion");
            assignments.getSubmit().click();

            new DirectLogin().loginAsQAVendorRole("45");//Log in as QA Vendor instructor role
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new TOCShow().chaptertree();//Click on toc
            Assert.assertEquals(lessonPage.chapterContainer.get(0).isDisplayed(), true, "E-text book is not opened");
            new TopicOpen().chapterOpen(1);//click on 2nd chapter
            String parentWindow = driver.getWindowHandle();
            driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_19']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);
            boolean elementFound = false;
            try {
                driver.findElement(By.className("ls-question-status-indicator-wrapper"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound, false, "published visual indicator is displaying");
            driver.switchTo().window(parentWindow);
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.getJumpOut().click();//click on jumpout
            Thread.sleep(9000);
            String discussion = driver.findElement(By.xpath("//div[@class='ls-right-user-post-body']")).getText();
            if (!discussion.contains("This is discussion")) {
                Assert.fail("Instructor should not get navigated to deactivated lesson page");
            }

            try {
                driver.findElement(By.className("ls-question-status-indicator-wrapper"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound, false, "published visual indicator is displaying");

            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.tryIt_Link.get(0).click();//click on try it
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(5000);
            try {
                driver.findElement(By.className("ls-question-status-indicator-wrapper"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound, false, "published visual indicator is displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case visualIndicatorFunctionalityForPublishedStatusOfAssessment in class QuestionContentStatusViewForQAVendor", e);
        }
    }


    @Test(priority = 10, enabled = true)
    public void visualIndicatorFunctionalityForQAStatusOfAssessment() {
        try {
            //tc row no 62
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            new Assignment().create(12);
            new DirectLogin().loginAsQAVendorRole("62");//Log in as QA Vendor instructor role
            new Assignment().assignToStudent(62);//Assign tit to class sections

            new DirectLogin().loginAsQAVendorRole("62_1");//Log in as QA Vendor student role
            new Assignment().submitAssignmentAsStudent(62);
            new ClickOnquestionCard().clickonquestioncard(0);
            assignments.getDiscussionTab().click();
            assignments.getNewButton().click();
            assignments.getEditBox().sendKeys("This is discussion");
            assignments.getSubmit().click();

            new DirectLogin().loginAsQAVendorRole("62");//Log in as QA Vendor instructor role
            new TOCShow().chaptertree();//Click on toc
            Assert.assertEquals(lessonPage.chapterContainer.get(0).isDisplayed(), true, "E-text book is not opened");
            new TopicOpen().chapterOpen(1);//click on 2nd chapter
            boolean visualIndicator = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_10']/parent::div/span[1]")).isDisplayed();
            Assert.assertEquals(visualIndicator, true, "Visual indicator for assignment is not displaying");
            String parentWindow = driver.getWindowHandle();
            driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_10']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(driver.findElement(By.className("ls-question-status-indicator-wrapper")).isDisplayed(), true, "Yellow colour visual indicator is not displaying");
            driver.switchTo().window(parentWindow);
            new TopicOpen().chapterOpen(1);//click on 2nd chapter
            driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_10']")).click();//click on assignment
            Assert.assertEquals(lessonPage.getDiagnosticTestTitle().getText(), "(P2) IT20_R2010_Assessment_10", "Assessment is not opened");
            Assert.assertEquals(lessonPage.getAssignmentsTab().getText(), "Assignments", "Assignment tab is not selected by default");

            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.getJumpOut().click();//click on jumpout
            Thread.sleep(10000);
            String discussion = driver.findElement(By.xpath("//div[@class='ls-right-user-post-body']")).getText();
            if (!discussion.contains("This is discussion")) {
                Assert.fail("Instructor should not get navigated to deactivated lesson page");
            }
            Assert.assertEquals(driver.findElement(By.className("ls-question-status-indicator-wrapper")).isDisplayed(), true, "Yellow colour visual indicator is not displaying");

            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.tryIt_Link.get(0).click();//click on try it
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(driver.findElement(By.className("ls-question-status-indicator-wrapper")).isDisplayed(), true, "Yellow colour visual indicator is not displaying");
            driver.close();//close preview window

        } catch (Exception e) {
            Assert.fail("Exception in test case visualIndicatorFunctionalityForPublishedStatusOfAssessment in class QuestionContentStatusViewForQAVendor", e);
        }
    }


    @Test(priority = 11, enabled = true)
    public void visualIndicatorFunctionalityForReadyToPublishStatus() {
        try {
            //tc row no 80
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new Assignment().create(80);
            new DirectLogin().loginAsQAVendorRole("81");//Log in as QA Vendor instructor role
            new TOCShow().chaptertree();//Click on toc
            Assert.assertEquals(lessonPage.chapterContainer.get(0).isDisplayed(), true, "E-text book is not opened");
            new TopicOpen().chapterOpen(1);//click on 2nd chapter
            /*boolean visualIndicator=driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_81']/parent::div/span[1]")).isDisplayed();
            Assert.assertEquals(visualIndicator, true, "Visual indicator for assignment is not displaying");*/
            String parentWindow = driver.getWindowHandle();
            driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_81']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(driver.findElement(By.className("ls-question-status-indicator-wrapper")).isDisplayed(), true, "Yellow colour visual indicator is not displaying");
            driver.switchTo().window(parentWindow);
            new TopicOpen().chapterOpen(1);//click on 2nd chapter
            driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_81']")).click();//click on assignment
            Assert.assertEquals(lessonPage.getDiagnosticTestTitle().getText(), "(P2) IT20_R2010_Assessment_81", "Assessment is not opened");
            Assert.assertEquals(lessonPage.getAssignmentsTab().getText(), "Assignments", "Assignment tab is not selected by default");

            new Assignment().create(82);
            for (int i = 0; i < 12; i++) {
                new Assignment().addQuestions(82, "truefalse", "");
            }

            new DirectLogin().loginAsQAVendorRole("81");//Log in as QA Vendor instructor role
            new Assignment().assignToStudent(81);//Assign tit to class sections

            new DirectLogin().loginAsQAVendorRole("81_1");//Log in as QA Vendor student role
            new Assignment().submitAssignmentAsStudent(81);
            new ClickOnquestionCard().clickonquestioncard(0);
            assignments.getDiscussionTab().click();
            assignments.getNewButton().click();
            assignments.getEditBox().sendKeys("This is discussion");
            assignments.getSubmit().click();

            new DirectLogin().loginAsQAVendorRole("81");//Log in as QA Vendor instructor role
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.getJumpOut().click();//click on jumpout
            Thread.sleep(10000);
            String discussion = driver.findElement(By.xpath("//div[@class='ls-right-user-post-body']")).getText();
            if (!discussion.contains("This is discussion")) {
                Assert.fail("Instructor should not get navigated to deactivated lesson page");
            }
            Assert.assertEquals(driver.findElement(By.className("ls-question-status-indicator-wrapper")).isDisplayed(), true, "Yellow colour visual indicator is not displaying");



          /*  new RunScheduledJobs().runScheduledJobsForDashboard();
            new DirectLogin().loginAsQAVendorRole("81");//Log in as QA Vendor instructor role
            new Navigator().NavigateTo("Proficiency Report");*/

            new DirectLogin().loginAsQAVendorRole("81");//Log in as QA Vendor instructor role
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.tryIt_Link.get(0).click();//click on try it
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(driver.findElement(By.className("ls-question-status-indicator-wrapper")).isDisplayed(), true, "Yellow colour visual indicator is not displaying");
            driver.close();//close preview window

        } catch (Exception e) {
            Assert.fail("Exception in test case visualIndicatorFunctionalityForReadyToPublishStatus in class QuestionContentStatusViewForQAVendor", e);
        }
    }


    @Test(priority = 12, enabled = true)
    public void visualIndicatorFunctionalityForPublishStatusWhileAttemptingQuestion() {
        try {
            //tc row no 98
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
            MyActivity myActivity = PageFactory.initElements(driver, MyActivity.class);

            new Assignment().create(98);
            for (int i = 0; i < 2; i++) {
                new Assignment().addQuestions(98, "truefalse", "");
            }

            new DirectLogin().loginAsQAVendorRole("99");//Log in as QA Vendor instructor role
            new Assignment().assignToStudent(99);//Assign tit to class sections

            new DirectLogin().loginAsQAVendorRole("99_1");//Log in as QA Vendor student role
            new Assignment().submitAssignmentAsStudent(99);
            new ClickOnquestionCard().clickonquestioncard(0);//click on 1st question card
            assignments.getDiscussionTab().click();
            assignments.getNewButton().click();
            assignments.getEditBox().sendKeys("This is discussion");
            assignments.getSubmit().click();
            Assert.assertEquals(assignmentResponsesPage.question_Number.getText(), "Q 1:", "Selected question is not opened");
            boolean elementFound = false;
            try {
                driver.findElement(By.className("ls-question-status-indicator-wrapper"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound, false, "published visual indicator is displaying");

            new Navigator().NavigateTo("Proficiency Report");
            proficiencyReport.QuestionCart.get(0).click();//click on 1st question card
            try {
                driver.findElement(By.className("ls-question-status-indicator-wrapper"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound, false, "published visual indicator is displaying");

            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.getJumpOut().click();//click on jumpout
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("al-diag-test-chapter-label")));
            String discussion = driver.findElement(By.xpath("//div[@class='ls-right-user-post-body']")).getText();
            if (!discussion.contains("This is discussion")) {
                Assert.fail("Instructor should not get navigated to deactivated lesson page");
            }

            try {
                driver.findElement(By.className("ls-question-status-indicator-wrapper"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound, false, "published visual indicator is displaying");

            new Navigator().NavigateTo("Proficiency Report");
            proficiencyReport.QuestionCart.get(2).click();//click on 1st question card
            Assert.assertEquals(driver.findElement(By.xpath("//span[@title='About']")).isDisplayed(), true, "About tab is not highlighted");

            try {
                driver.findElement(By.className("ls-question-status-indicator-wrapper"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound, false, "published visual indicator is displaying");
            new Navigator().NavigateTo("My Activity");
            myActivity.assessmentLink.click();//click on assessment
            Thread.sleep(5000);
            Assert.assertEquals(driver.findElement(By.xpath("//span[@title='Performance']")).isDisplayed(), true, "performance tab is not highlighted");
            new ClickOnquestionCard().clickonquestioncard(0);//click on 1st question card
            try {
                driver.findElement(By.className("ls-question-status-indicator-wrapper"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound, false, "published visual indicator is displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test case visualIndicatorFunctionalityForPublishStatusWhileAttemptingQuestion in class QuestionContentStatusViewForQAVendor", e);
        }
    }

    @Test(priority = 13, enabled = true)
    public void visualIndicatorFunctionalityForQAStatusWhileAttemptingQuestion() {
        try {
            //tc row no 124
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(124));
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            WebDriverWait wait = new WebDriverWait(driver, 200);
            new Assignment().createChapter(124, 1);//create a chapter
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectcourse();//select a course
            Thread.sleep(2000);
            courseOutline.courseOutline.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@title,'Add New Chapter')]")));
          //  Actions actions = new Actions(driver);

            //publish chapter
            List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label node course-chapter-label-unpublished')]"));
            for (WebElement chapters : allChapters) {
                if (chapters.getText().contains(chapterName)) {
                    Locatable hoverItem = (Locatable) chapters;
                    Mouse mouse = ((HasInputDevices) driver).getMouse();
                    mouse.mouseMove(hoverItem.getCoordinates());
                }
            }
           // actions.moveToElement(driver.findElement(By.xpath("//div[contains(text(),'" + chapterName + "')]"))).build().perform();
            Thread.sleep(500);
            courseOutline.editButtonAtChapter.click();
            Thread.sleep(500);
            courseOutline.checkBoxToPublishChapter.click();//click on publish
            courseOutline.saveButton.click();//click on save
            Thread.sleep(5000);
            courseOutline.saveMyChanges.click();
            Thread.sleep(2000);
            new Assignment().create(124);//Create an assignment
            new DirectLogin().loginAsQAVendorRole("124");//Log in as QA Vendor student role
            new TOCShow().chaptertree();//click on toc
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");//click on particular chapter
            lessonPage.orionAdaptive_link.get(1).click();//click on orionAdaptive
            boolean visualIndicatorDisplayed = isDisplayed(124, lessonPage.visualIndicator_Diagnostic);
            Assert.assertEquals(visualIndicatorDisplayed, true, "Yellow colour visual indicator is not displaying");
            startTest(1);
            lessonPage.beginDiagnostic.get(1).click();//click on begin
            Assert.assertEquals(driver.findElement(By.className("ls-question-status-indicator-wrapper")).isDisplayed(), true, "Yellow colour visual indicator is not displaying");
            new DiagnosticTest().attemptAllCorrect(2, false, false);


        } catch (Exception e) {
            Assert.fail("Exception in test case visualIndicatorFunctionalityForQAStatusWhileAttemptingQuestion in class QuestionContentStatusViewForQAVendor", e);
        }
    }

    public boolean isDisplayed(int dataIndex, List<WebElement> we) {
        boolean elementFound = false;
        try {
            for (WebElement c : we) {
                if (c.isDisplayed()) {
                    elementFound = true;
                    break;
                }
            }

        } catch (Exception e) {
            Assert.fail("Exception in method 'isDisplayed' in class 'StudyPlanEnhancement'", e);

        }
        return elementFound;
    }


    public void startTest(int confidenceLevelIndex) {
        try {
            List<WebElement> conf = driver.findElements(By.id(Integer.toString(confidenceLevelIndex)));
            for (WebElement c : conf) {
                if (c.isDisplayed()) {
                    c.click();
                    break;
                }
            }
        } catch (Exception e) {
            Assert.fail("Exception in app helper startTest in class DiagnosticTest", e);
        }

    }

    @Test(priority = 14, enabled = true)
    public void visualIndicatorFunctionalityForQAStatusPersonalizedPractice() {
        try {
            //tc row no 131
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(124));
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            new Assignment().create(131);//Create an assignment
            new DirectLogin().loginAsQAVendorRole("131");//Log in as QA Vendor student role
            new TOCShow().chaptertree();//click on toc
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");//click on particular chapter
            lessonPage.orionAdaptive_link.get(1).click();//click on orionAdaptive
            startTest(1);
            lessonPage.beginDiagnostic.get(1).click();//click on begin
            new DiagnosticTest().attemptAllCorrect(2, false, false);
            new TOCShow().chaptertree();//click on toc
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");//click on particular chapter
            lessonPage.orionAdaptive_link.get(1).click();//click on orionAdaptive
            boolean visualIndicatorDisplayed = isDisplayed(131, lessonPage.visualIndicator_Practice);
            Assert.assertEquals(visualIndicatorDisplayed, true, "Yellow colour visual indicator is not displaying");
            new PracticeTest().startTest();
            Assert.assertEquals(driver.findElement(By.className("ls-question-status-indicator-wrapper")).isDisplayed(), true, "Yellow colour visual indicator is not displaying");
            new PracticeTest().AttemptCorrectAnswer(2, "131");
            new PracticeTest().quitThePracticeTest();

        } catch (Exception e) {
            Assert.fail("Exception in test case visualIndicatorFunctionalityForQAStatusPersonalizedPractice in class QuestionContentStatusViewForQAVendor", e);
        }
    }


    @Test(priority = 15, enabled = true)
    public void visualIndicatorFunctionalityAsStudentStaticPractice() {
        try {
            //tc row no 138
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            new Assignment().create(138);
            new DirectLogin().loginAsQAVendorRole("138");//Log in as QA Vendor student role
            new TOCShow().chaptertree();//Click on toc
            Assert.assertEquals(lessonPage.chapterContainer.get(0).isDisplayed(), true, "E-text book is not opened");
            new TopicOpen().chapterOpen(1);//click on 2nd chapter
            boolean visualIndicator = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_138']/parent::div/span[1]")).isDisplayed();
            Assert.assertEquals(visualIndicator, true, "Visual indicator for assignment is not displaying");
            driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_138']")).click();
            Assert.assertEquals(driver.findElement(By.className("ls-question-status-indicator-wrapper")).isDisplayed(), true, "Yellow colour visual indicator is not displaying");
            new SelectAnswerAndSubmit().staticanswersubmit("A");

        } catch (Exception e) {
            Assert.fail("Exception in test case visualIndicatorFunctionalityAsStudent in class QuestionContentStatusViewForQAVendor", e);
        }
    }


    @Test(priority = 16, enabled = true)
    public void visualIndicatorFunctionalityAsStudentStaticPracticeStudent() {
        try {
            //tc row no 145
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new DirectLogin().loginAsQAVendorRole("145");//Log in as QA Vendor student role
            new TOCShow().chaptertree();//Click on toc
            Assert.assertEquals(lessonPage.chapterContainer.get(0).isDisplayed(), true, "E-text book is not opened");
            new TopicOpen().chapterOpen(1);//click on 2nd chapter
            boolean visualIndicator = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_138']/parent::div/span[1]")).isDisplayed();
            Assert.assertEquals(visualIndicator, true, "Visual indicator for assignment is not displaying");
            driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_138']")).click();
            Assert.assertEquals(driver.findElement(By.className("ls-question-status-indicator-wrapper")).isDisplayed(), true, "Yellow colour visual indicator is not displaying");
            new SelectAnswerAndSubmit().staticanswersubmit("A");

            new Navigator().navigateToTab("Discussion");
            String discussionText = new RandomString().randomstring(6);
            new Discussion().postDiscussion(discussionText);//pots a discussion
            new TOCShow().chaptertree();//Click on toc
            new TopicOpen().chapterOpen(1);//click on 2nd chapter
            driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_138']")).click();
            assignments.questionCard.get(0).click();//click on 1st question card
            Assert.assertEquals(assignments.statusBox.isDisplayed(), true, "Yellow colour visual indicator is not displaying");

            new Navigator().NavigateTo("Proficiency Report");
            assignments.questionCard.get(0).click();//click on 1st question card
            Assert.assertEquals(assignments.statusBox.isDisplayed(), true, "Yellow colour visual indicator is not displaying");

            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.getJumpOut().click();//click on jumpout
            Thread.sleep(10000);
            String discussion = driver.findElement(By.xpath("//div[@class='ls-right-user-post-body']")).getText();
            if (!discussion.contains(discussionText)) {
                Assert.fail("Instructor should not get navigated to deactivated lesson page");
            }
            Assert.assertEquals(driver.findElement(By.className("ls-question-status-indicator-wrapper")).isDisplayed(), true, "Yellow colour visual indicator is not displaying");


            new Navigator().NavigateTo("Proficiency Report");
            assignments.questionCard.get(0).click();//click on 1st question card
            Thread.sleep(6000);
            Assert.assertEquals(driver.findElement(By.xpath("//span[@title='About']")).isDisplayed(), true, "About tab is not highlighted");
            Assert.assertEquals(driver.findElement(By.className("ls-question-status-indicator-wrapper")).isDisplayed(), true, "Yellow colour visual indicator is not displaying");

            new Navigator().NavigateTo("My Activity");
            driver.findElement(By.xpath("//a[@resourcename='IT20_R2010_Assessment_138']")).click();
            Thread.sleep(5000);
            Assert.assertEquals(driver.findElement(By.xpath("//span[@title='Performance']")).isDisplayed(), true, "performance tab is not highlighted");
            assignments.questionCard.get(0).click();//click on 1st question card
            Thread.sleep(2000);
            Assert.assertEquals(driver.findElement(By.className("ls-question-status-indicator-wrapper")).isDisplayed(), true, "Yellow colour visual indicator is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test case visualIndicatorFunctionalityAsStudent in class QuestionContentStatusViewForQAVendor", e);
        }
    }


    @Test(priority = 17, enabled = true)
    public void visualIndicatorFunctionalityAsStudentStaticPracticeReadyToPublish() {
        try {

            //tc row no 171
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            new Assignment().create(171);
            new DirectLogin().loginAsQAVendorRole("171");//Log in as QA Vendor student role
            new TOCShow().chaptertree();//Click on toc
            Assert.assertEquals(lessonPage.chapterContainer.get(0).isDisplayed(), true, "E-text book is not opened");
            new TopicOpen().chapterOpen(1);//click on 2nd chapter
            boolean visualIndicator = driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_171']/parent::div/span[1]")).isDisplayed();
            Assert.assertEquals(visualIndicator, true, "Visual indicator for assignment is not displaying");
            driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_171']")).click();
            Assert.assertEquals(driver.findElement(By.className("ls-question-status-indicator-wrapper")).isDisplayed(), true, "Yellow colour visual indicator is not displaying");
            new SelectAnswerAndSubmit().staticanswersubmit("A");

            new Navigator().navigateToTab("Discussion");
            String discussionText = new RandomString().randomstring(6);
            new Discussion().postDiscussion(discussionText);//pots a discussion
            new TOCShow().chaptertree();//Click on toc
            new TopicOpen().chapterOpen(1);//click on 2nd chapter
            driver.findElement(By.xpath("//a[@title='IT20_R2010_Assessment_171']")).click();
            assignments.questionCard.get(0).click();//click on 1st question card
            Assert.assertEquals(assignments.statusBox.isDisplayed(), true, "Yellow colour visual indicator is not displaying");

            new Navigator().NavigateTo("Proficiency Report");
            assignments.questionCard.get(0).click();//click on 1st question card
            Assert.assertEquals(assignments.statusBox.isDisplayed(), true, "Yellow colour visual indicator is not displaying");

            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.getJumpOut().click();//click on jumpout
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("al-diag-test-chapter-label")));
            String discussion = driver.findElement(By.xpath("//div[@class='ls-right-user-post-body']")).getText();
            if (!discussion.contains(discussionText)) {
                Assert.fail("Instructor should not get navigated to deactivated lesson page");
            }
            Assert.assertEquals(driver.findElement(By.className("ls-question-status-indicator-wrapper")).isDisplayed(), true, "Yellow colour visual indicator is not displaying");


            new Navigator().NavigateTo("Proficiency Report");
            assignments.questionCard.get(0).click();//click on 1st question card
            Thread.sleep(6000);
            Assert.assertEquals(driver.findElement(By.xpath("//span[@title='About']")).isDisplayed(), true, "About tab is not highlighted");
            Assert.assertEquals(driver.findElement(By.className("ls-question-status-indicator-wrapper")).isDisplayed(), true, "Yellow colour visual indicator is not displaying");

            new Navigator().NavigateTo("My Activity");
            driver.findElement(By.xpath("//a[@resourcename='IT20_R2010_Assessment_171']")).click();
            Thread.sleep(5000);
            Assert.assertEquals(driver.findElement(By.xpath("//span[@title='Performance']")).isDisplayed(), true, "performance tab is not highlighted");
            assignments.questionCard.get(0).click();//click on 1st question card
            Thread.sleep(2000);
            Assert.assertEquals(driver.findElement(By.className("ls-question-status-indicator-wrapper")).isDisplayed(), true, "Yellow colour visual indicator is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case visualIndicatorFunctionalityAsStudentStaticPracticeReadyToPublish in class QuestionContentStatusViewForQAVendor", e);
        }
    }



}