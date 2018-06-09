package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R191;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by shashank on 18-02-2015.
 */
public class FooterNavigation  extends Driver{

    @Test(priority = 1)
    public void footerNavigationUnderChapter() {
        try {

            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            //Student login
            new LoginUsingLTI().ltiLogin("11");
            new Navigator().NavigateTo("e-Textbook");
            WebDriverWait wait = new WebDriverWait(driver, 200);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            //open Photosynthesis as a chapter
            new TopicOpen().chapterOpen(7);
            //open introduction
            new SelectCourse().selectInvisibleAssignment("Introduction");
            //check footers
            Assert.assertTrue(lessonPage.getNextChapterFooter().get(0).getAttribute("title").contains("8.1: Overview of Photosynthesis"), "Next Section  is not displaying as expected");
            //click on previous footer
            lessonPage.getPreviousChapterFooter().get(0).click();
            Thread.sleep(2000);
            new Navigator().NavigateTo("e-Textbook");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            //open Photosynthesis as a chapter
            new TopicOpen().chapterOpen(7);
            //start diagnostic test with confidence level
            new DiagnosticTest().startTestByName("Photosynthesis", 2);
            //check footer should not be displayed on the screen
            Assert.assertTrue(lessonPage.getIntroductionTab().isDisplayed(), "Introduction tab is not displaying on the screen");
            Assert.assertTrue(!lessonPage.getNextChapterFooter().get(0).isDisplayed(), "Introduction tab is not displaying on the screen");
            //quit test after attempting 5 question and continue later
            new DiagnosticTest().DiagonesticTestQuitBetween(2, 5, "correct", false, false, false);
            new TopicOpen().chapterOpen(7);
            new DiagnosticTest().continueDiagnosticTest();
            new DiagnosticTest().attemptAllCorrect(2, false, false);
            new TopicOpen().chapterOpen(7);
            new PracticeTest().startTest();
            for (int i = 0; i < 3; i++) {
                (new PracticeTest()).AttemptCorrectAnswer(2,"11");
            }
            new PracticeTest().quitThePracticeTest();
            Thread.sleep(3000);

            Assert.assertTrue(lessonPage.getNextChapterFooter().get(1).getAttribute("title").contains("Introduction"), "Next Section  is not displaying as expected");
            lessonPage.getPreviousChapterFooter().get(1).click();
            Thread.sleep(5000);
            lessonPage.getNextChapterFooter().get(1).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='submit-practice-question-button']")));

            for (int i = 0; i < 4; i++) {
                (new PracticeTest()).AttemptCorrectAnswer(2,"11");
            }
            new PracticeTest().quitThePracticeTest();
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(3600);
            Assert.assertTrue(lessonPage.getNextChapterFooter().get(0).getAttribute("title").contains("8.1: Overview of Photosynthesis"), "Next Section  is not displaying as expected");
            int sizeBeforeClick = lessonPage.getTabsOpenCount().size();
            lessonPage.getNextChapterFooter().get(0).click();
            Thread.sleep(3000);
            Assert.assertTrue(lessonPage.getPreviousChapterFooter().get(1).getAttribute("title").contains("Introduction"), "Previous Assignment is not displaying as expected");
            Assert.assertTrue(lessonPage.getNextChapterFooter().get(1).getAttribute("title").contains("8.1: Give an overview of the process of photosynthesis - Practice"), "Next Section  is not displaying as expected");
            int sizeAfterClick = lessonPage.getTabsOpenCount().size();
            if (sizeBeforeClick != sizeAfterClick)
                Assert.fail("lesson is not being get opened in same tab");
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(3000);
            for (int i = 0; i < 4; i++) {
                (new PracticeTest()).AttemptCorrectAnswer(2,"11");
            }
            new PracticeTest().quitThePracticeTest();
            Thread.sleep(2000);
            Assert.assertTrue(lessonPage.getPreviousChapterFooter().get(1).getAttribute("title").contains("8.1: Overview of Photosynthesis"), "Previous Assignment is not displaying as expected");
            Assert.assertTrue(lessonPage.getNextChapterFooter().get(1).getAttribute("title").contains("8.1 Concept Check"), "Next Section  is not displaying as expected");
            lessonPage.getPreviousChapterFooter().get(1).click();
            Thread.sleep(3000);
            Assert.assertTrue(lessonPage.getPreviousChapterFooter().get(0).getAttribute("title").contains("Introduction"), "Previous Assignment is not displaying as expected");
            Assert.assertTrue(lessonPage.getNextChapterFooter().get(0).getAttribute("title").contains("8.1: Give an overview of the process of photosynthesis - Practice"), "Next Section  is not displaying as expected");
            Thread.sleep(3000);
            lessonPage.getNextChapterFooter().get(0).click();
            Thread.sleep(3000);
            new PracticeTest().quitThePracticeTest();
            Thread.sleep(3000);
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(3000);
            new AttemptTest().StaticTestWithConfidence(3);
            Assert.assertTrue(lessonPage.getPreviousChapterFooter().get(1).getAttribute("title").contains("8.1: Give an overview of the process of photosynthesis - Practice"), "Previous Assignment is not displaying as expected");
            Assert.assertTrue(lessonPage.getNextChapterFooter().get(1).getAttribute("title").contains("8.2: The Light-Dependent Reactions of Photosynthesis"), "Next Section  is not displaying as expected");
            lessonPage.getPreviousChapterFooter().get(1).click();
            Thread.sleep(3000);
            for (int i = 0; i < 4; i++) {
                (new PracticeTest()).AttemptCorrectAnswer(2,"11");
            }
            new PracticeTest().quitThePracticeTest();
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(3000);
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(3000);
            new Navigator().NavigateTo("e-Textbook");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            //open Photosynthesis as a chapter
            new TopicOpen().chapterOpen(7);
            Thread.sleep(3000);
            driver.findElement(By.xpath("//a[contains(@title,'Analyze the products of the light-dependent')]")).click();
            Thread.sleep(3000);
            for (int i = 0; i < 4; i++) {
                (new PracticeTest()).AttemptCorrectAnswer(2,"11");
            }
            new PracticeTest().quitThePracticeTest();
            Assert.assertTrue(lessonPage.getPreviousChapterFooter().get(1).getAttribute("title").contains("Chapter Summary"), "Previous Assignment is not displaying as expected");
            Assert.assertTrue(lessonPage.getNextChapterFooter().get(1).getAttribute("title").contains("Next Chapter"), "Next Section  is not displaying as expected");
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(3000);

            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='selected-chapter-name']")).getText().contains("Ch 9: Cell Communication"), "Next Section  is not displaying as expected");
            Thread.sleep(3000);
            new DiagnosticTest().attemptAllCorrect(2, false, false);
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(4000);
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='selected-chapter-name']")).getText().contains("Ch 8: Photosynthesis"), "Next Section  is not displaying as expected");
            int totalChapter = lessonPage.getChapterCount().size();
            lessonPage.getChapterCount().get(totalChapter - 1).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//a[text(),'Chapter Summary']")).click();
            Thread.sleep(3000);
            Assert.assertTrue(lessonPage.getNextChapterFooter().size() == 0, "Next chapter is present on the screen");
            new LoginUsingLTI().ltiLogin("54");
            new Navigator().NavigateTo("e-Textbook");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            //open Photosynthesis as a chapter
            new TopicOpen().chapterOpen(7);
            Thread.sleep(3000);
            driver.findElement(By.xpath("//a[@title='8.1: Overview of Photosynthesis']")).click();
            Thread.sleep(3000);
            lessonPage.getNextChapterFooter().get(0).click();
            Thread.sleep(2000);
           // Assert.assertTrue(lessonPage.getNotificationMessageOnclickOfPreviousFooter().getText().contains("Your diagnostic test is not yet complete. Please finish it before attempting the practice test."), "Displayed notification message is not correct");
            new Navigator().NavigateTo("e-Textbook");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            //open Photosynthesis as a chapter
            new TopicOpen().chapterOpen(7);
            Thread.sleep(3000);
            driver.findElement(By.xpath("//a[@title='8.1 Concept Check']")).click();
            Thread.sleep(3000);
            //??need to attempt static test
        } catch (Exception e) {
            Assert.fail("Exception in testcase footerNavigationUnderChapter in class FooterNavigation", e);
        }
    }
    @Test(priority = 2)
    public void notificationOnSwitchingAssessment () {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 200);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            //Student login
            new LoginUsingLTI().ltiLogin("54");
            new Navigator().NavigateTo("e-Textbook");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            //open Photosynthesis as a chapter
            new TopicOpen().chapterOpen(7);
            Thread.sleep(3000);
            new DiagnosticTest().startTestByName("Photosynthesis", 2);
            Thread.sleep(5000);
            lessonPage.getToc().click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[contains(text(),'The Light-Dependent Reactions of Photosynthesis')]")).click();
            Thread.sleep(8000);
          //  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//a[@class='ls-prev' and @title='8.1 Concept Check']")));

            lessonPage.getPreviousChapterFooter().get(1).click();
            Thread.sleep(3000);
            Assert.assertTrue(lessonPage.getNotificationMessageOnclickOfPreviousFooter().getText().contains("You have chosen to open the assessment in the existing tab.This will lead to the existing assessment being quit and continue later. Are you sure you want to continue?"), "Displayed notification message is not correct");
            Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'active')]/span[2]")).getText().contains("Diagnostic - Ch 8: Photosynthesis"), "The current tab is not Open");
            lessonPage.getNoLink().click();
            Thread.sleep(2000);
            new DiagnosticTest().attemptAllCorrect(2, false, false);
            new Navigator().NavigateTo("e-Textbook");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            //open Photosynthesis as a chapter
            new TopicOpen().chapterOpen(7);
            Thread.sleep(3000);
            //open introduction
            lessonPage.getIntroductionUnderLesson().get(1).click();
            Thread.sleep(5000);
            lessonPage.getToc().click();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(8);
            Thread.sleep(1000);
            new DiagnosticTest().startTestByName("Cell Communication", 2);
            Thread.sleep(2000);
            new Click().clickbyxpath("//span[@title='Introduction']");
            Thread.sleep(2000);
            lessonPage.getPreviousChapterFooter().get(0).click();
            Thread.sleep(2000);
            Assert.assertTrue(lessonPage.getNotificationMessageOnclickOfPreviousFooter().getText().contains("You have chosen to open the assessment in the existing tab.This will lead to the existing assessment being quit and continue later. Are you sure you want to continue?"), "Displayed notification message is not correct");
            new Navigator().NavigateTo("e-Textbook");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            new TopicOpen().chapterOpen(8);
            Thread.sleep(1000);
            new DiagnosticTest().startTestByName("Cell Communication", 2);
            new DiagnosticTest().attemptAllCorrect(2, false, false);
            Thread.sleep(1000);
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(1000);
            lessonPage.getToc().click();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(7);
            Thread.sleep(1000);
            new Click().clickbyxpath("//span[@title='Introduction']");
            Thread.sleep(3000);
            lessonPage.getPreviousChapterFooter().get(1).click();
            Thread.sleep(3000);
            Assert.assertTrue(lessonPage.getNotificationMessageOnclickOfPreviousFooter().getText().contains("You have chosen to open the assessment in the existing tab.This will lead to the existing assessment being quit and continue later. Are you sure you want to continue?"), "Displayed notification message is not correct");
            lessonPage.getToc().click();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(7);
            Thread.sleep(1000);
            new Click().clickbyxpath("//span[@title='Introduction']");
            Thread.sleep(3000);
            driver.findElement(By.xpath("//a[contains(text(),'The Light-Dependent Reactions of Photosynthesis')]")).click();
            Thread.sleep(2000);
            lessonPage.getPreviousChapterFooter().get(1).click();
            Thread.sleep(3000);
            Assert.assertTrue(lessonPage.getNotificationMessageOnclickOfPreviousFooter().getText().contains("You have chosen to open the assessment in the existing tab.This will lead to the existing assessment being quit and continue later. Are you sure you want to continue?"), "Displayed notification message is not correct");
            Thread.sleep(2000);
            lessonPage.getYesLink().click();
            Thread.sleep(2000);
            lessonPage.getToc().click();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(7);
            Thread.sleep(1000);
            new Click().clickbyxpath("//span[@title='Introduction']");
            Thread.sleep(3000);
            lessonPage.getPreviousChapterFooter().get(1).click();
            Thread.sleep(3000);
            Assert.assertTrue(lessonPage.getNotificationMessageOnclickOfPreviousFooter().getText().contains("You have chosen to open the assessment in the existing tab.This will lead to the existing assessment being quit and continue later. Are you sure you want to continue?"), "Displayed notification message is not correct");
            lessonPage.getYesLink().click();
            Thread.sleep(2000);
            new Navigator().NavigateTo("e-Textbook");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            new TopicOpen().chapterOpen(7);
            Thread.sleep(1000);
            new Click().clickbyxpath("//a[@title='8.1 Concept Check']");
            lessonPage.getToc().click();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(7);
            Thread.sleep(1000);
            new Click().clickbyxpath("//a[@title='Chapter Summary']");
            Thread.sleep(3000);
            lessonPage.getPreviousChapterFooter().get(1).click();
            Thread.sleep(3000);
            Assert.assertTrue(lessonPage.getNotificationMessageOnclickOfPreviousFooter().getText().contains("You have chosen to open the assessment in the existing tab.This will lead to the existing assessment being quit and continue later. Are you sure you want to continue?"), "Displayed notification message is not correct");
            Thread.sleep(2000);
            lessonPage.getYesLink().click();
            Thread.sleep(2000);

        }
        catch (Exception e) {
            Assert.fail("Exception in testcase notificationOnSwitchingAssessment in class FooterNavigation", e);
        }
    }
    @Test(priority = 2)
    public void entryOverDashboardAndMyActivity () {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 200);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            //Student login
            new LoginUsingLTI().ltiLogin("70");
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(0);
            Thread.sleep(1000);
            new Click().clickbyxpath("//span[@title='Introduction']");
            Thread.sleep(3000);
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(3000);
            new Navigator().NavigateTo("Dashboard");
            boolean elementPresent=false;
            for(WebElement element:lessonPage.getChapterName_Dashboard())
            {
                if(element.getText().contains("1.1 Concept Check"))
                elementPresent=true;
            }
            new Navigator().NavigateTo("My Activity");
            boolean elementPresentOnMyActivity=false;
            for(WebElement element:lessonPage.getChapterOnMyActivity())
            {
                if(element.getText().contains("1.1 Concept Check"))
                    elementPresentOnMyActivity=true;
            }
            if(!elementPresentOnMyActivity)
            {
                Assert.fail("Accessed lesson is not displaying at My Activity");
            }
            if(!elementPresent)
            {
                Assert.fail("Accessed lesson is not displaying at dashBoard");
            }

            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(0);
            Thread.sleep(1000);
            new Click().clickbyxpath("//a[@title='1.2: Themes and Concepts of Biology']");
            Thread.sleep(2000);
            lessonPage.getPreviousChapterFooter().get(0).click();
            Thread.sleep(1000);
            new AttemptTest().StaticTestAttemptAllTypeWithConfidence(2,false,false);
            new Navigator().NavigateTo("Dashboard");
            elementPresent=false;
            for(WebElement element:lessonPage.getChapterName_Dashboard())
            {
                if(element.getText().contains("1.1 Concept Check"))
                    elementPresent=true;
            }
            new Navigator().NavigateTo("My Activity");
            elementPresentOnMyActivity=false;
            for(WebElement element:lessonPage.getChapterOnMyActivity())
            {
                if(element.getText().contains("1.1 Concept Check"))
                    elementPresentOnMyActivity=true;
            }
            if(!elementPresentOnMyActivity)
            {
                Assert.fail("Accessed lesson is not displaying at My Activity");
            }
            if(!elementPresent)
            {
                Assert.fail("Accessed lesson is not displaying at dashBoard");
            }
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(0);
            Thread.sleep(1000);
            new Click().clickbyxpath("//a[@title='1.1 Concept Check']");
            Thread.sleep(2000);
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(2000);
            new Navigator().NavigateTo("Dashboard");
            elementPresent=false;
            for(WebElement element:lessonPage.getChapterName_Dashboard())
            {
                if(element.getText().contains("1.1 Concept Check"))
                    elementPresent=true;
            }
            new Navigator().NavigateTo("My Activity");
            elementPresentOnMyActivity=false;
            for(WebElement element:lessonPage.getChapterOnMyActivity())
            {
                if(element.getText().contains("1.1 Concept Check"))
                    elementPresentOnMyActivity=true;
            }
            if(!elementPresentOnMyActivity)
            {
                Assert.fail("Accessed lesson is not displaying at My Activity");
            }
            if(!elementPresent)
            {
                Assert.fail("Accessed lesson is not displaying at dashBoard");
            }
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(0);
            Thread.sleep(1000);
            new Click().clickbyxpath("//a[@title='1.1 Concept Check']");
            Thread.sleep(2000);
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(2000);
            lessonPage.getNextChapterFooter().get(0).click();
            Thread.sleep(2000);

            new Navigator().NavigateTo("Dashboard");
            elementPresent=false;
            for(WebElement element:lessonPage.getChapterName_Dashboard())
            {
                if(element.getText().contains(" 1.2: Describe the properties and levels of organization of living things - Practice"))
                    elementPresent=true;
            }
            new Navigator().NavigateTo("My Activity");
            elementPresentOnMyActivity=false;
            for(WebElement element:lessonPage.getChapterOnMyActivity())
            {
                if(element.getText().contains(" 1.2: Describe the properties and levels of organization of living things - Practice"))
                    elementPresentOnMyActivity=true;
            }
            if(!elementPresentOnMyActivity)
            {
                Assert.fail("Accessed lesson is not displaying at My Activity");
            }
            if(!elementPresent)
            {
                Assert.fail("Accessed lesson is not displaying at dashBoard");
            }
        }
        catch (Exception e) {
            Assert.fail("Exception in testcase entryOverDashboardAndMyActivity in class FooterNavigation", e);
        }
    }
    @Test(priority = 3)
    public void availabilityOfNavigationFooter () {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 200);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            CourseStreamPage courseStreamPage=PageFactory.initElements(driver,CourseStreamPage.class);
            //Student login
            new LoginUsingLTI().ltiLogin("78");
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(7);
            Thread.sleep(1000);
            new DiagnosticTest().startTestByName("Photosynthesis", 2);
            //check footer should not be displayed on the screen
            new DiagnosticTest().DiagonesticTestQuitBetween(2,2, "correct", false, false, false);
            Thread.sleep(4000);
            Assert.assertEquals(lessonPage.getNextChapterFooter().size(), 2, "Error");
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().size(), 2, "Error");
            new Navigator().NavigateTo("Dashboard");
            lessonPage.getChapterName_Dashboard().get(0).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ls-dashboard-activities-chaptername']")));
            //open introduction
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(7);
            Thread.sleep(1000);
            new Click().clickbyxpath("//span[@title='Introduction']");
            Thread.sleep(3000);
            new Navigator().navigateToTab("Discussion");
            String discussionText = new RandomString().randomstring(6);
            new Discussion().postDiscussion(discussionText);//post a discussion
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.getJumpOut().click();
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.getNextChapterFooter().size(), 1, "Error");
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().size(), 1, "Error");
            new Discussion().postNote("This is note");
            new Navigator().NavigateTo("My Notes");
            Thread.sleep(2000);
            new Click().clickbyxpath("//span[@class='my-journal-media-popout-icon card-icons']");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.getNextChapterFooter().size(), 1, "Error");
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().size(), 1, "Error");
            new Navigator().NavigateTo("My Activity");
            Thread.sleep(2000);
            new Click().clickbyxpath("//a[@class='subevent-link']");
            Thread.sleep(2000);
            new Click().clickbyxpath("//a[@class='subevent-link']");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.getNextChapterFooter().size(), 1, "Error");
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().size(), 1, "Error");

            //Submit a static test
            //Click on retake
            //Submit the retake
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(0);
            Thread.sleep(1000);
            new Click().clickbyxpath("//a[@title='1.1 Concept Check']");
            Thread.sleep(3000);
            new AttemptTest().StaticTestAttemptAllTypeWithConfidence(2,false,false);
            //retake static test
            new Click().clickbyxpath("//div[@class='static-assessment-retake-button']");
            new AttemptTest().StaticTestAttemptAllTypeWithConfidence(2,false,false);
            Assert.assertEquals(lessonPage.getNextChapterFooter().size(), 1, "Error");
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().size(), 1, "Error");
            new Click().clickbylinkText("Show All");
            Thread.sleep(2000);
            new Click().clickbylinkText("Correct");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.getNextChapterFooter().size(), 1, "Error");
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().size(), 1, "Error");
        }
        catch (Exception e) {
            Assert.fail("Exception in testcase notificationOnSwitchingAssessment in class FooterNavigation", e);
        }
    }

}
