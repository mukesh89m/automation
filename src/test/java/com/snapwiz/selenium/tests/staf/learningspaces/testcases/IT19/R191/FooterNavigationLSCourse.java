package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R191;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shashank on 23-02-2015.
 */
public class FooterNavigationLSCourse extends Driver{


    @Test(priority = 1)
    public void footerNavigationUnderChapter() {
        try {

            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            //Student login
            new LoginUsingLTI().ltiLogin("127");
            new Navigator().NavigateTo("e-Textbook");
            WebDriverWait wait = new WebDriverWait(driver, 200);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            //open Photosynthesis as a chapter
            new TopicOpen().chapterOpen(0);
            Thread.sleep(3000);
            //open introduction
            driver.findElement(By.xpath("//a[contains(@title,'Chapter Introduction')]")).click();
            Thread.sleep(5000);
            //check footers
            Assert.assertEquals(lessonPage.getNextChapterFooter().size(), 1, "Error");
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().size(), 0, "Error");
            lessonPage.getNextChapterFooter().get(0).click();
            Thread.sleep(2000);
            Assert.assertTrue(lessonPage.getPreviousChapterFooter().get(0).getAttribute("title").contains("Chapter Introduction"), "Previous Assignment is not displaying as expected");
            Assert.assertTrue(lessonPage.getNextChapterFooter().get(0).getAttribute("title").contains("0.2 Concept Check"), "Next Section  is not displaying as expected");
            lessonPage.getPreviousChapterFooter().get(0).click();
            Thread.sleep(3000);
            lessonPage.getNextChapterFooter().get(0).click();
            Thread.sleep(3000);
            lessonPage.getNextChapterFooter().get(0).click();
            new AttemptTest().StaticTestAttemptAllTypeWithConfidence(2,false,false);


            Assert.assertTrue(lessonPage.getPreviousChapterFooter().get(1).getAttribute("title").contains("0.1: A World on Maps"), "Previous Assignment is not displaying as expected");
            Assert.assertTrue(lessonPage.getNextChapterFooter().get(1).getAttribute("title").contains("0.2: Geography’s Perspective"), "Next Section  is not displaying as expected");
            lessonPage.getPreviousChapterFooter().get(1).click();
            Thread.sleep(2000);
            //click on assessment tab
            driver.findElement(By.cssSelector("span[@class=tab_icon assessment-icon]")).click();
            Thread.sleep(2000);
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(3000);
              new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(0);
            Thread.sleep(3000);
            new Click().clickbyxpath("//a[@title='0.12 Concept Check']");
            Thread.sleep(2000);
            new AttemptTest().StaticTestAttemptAllTypeWithConfidence(2,false,false);
            Assert.assertTrue(lessonPage.getPreviousChapterFooter().get(1).getAttribute("title").contains("0.11: Realms and Regions: The Structure of this Book"), "Previous Assignment is not displaying as expected");
            Assert.assertTrue(lessonPage.getNextChapterFooter().get(1).getAttribute("title").contains("Next Chapter"), "Next Section  is not displaying as expected");
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(2000);
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='selected-chapter-name']")).getText().contains("Ch 1A: The European Realm"),"Not able locate element on screen");
            new Click().clickbyxpath("//a[@title='1A.1: Defining the Realm']");
            lessonPage.getPreviousChapterFooter().get(2).click();
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='selected-chapter-name']")).getText().contains("Ch G: Introduction: World Regional Geography: Global Perspectives"),"Not able locate element on screen");
            int chapterCount=lessonPage.getChapterCount().size();
            lessonPage.getChapterCount().get(chapterCount).click();
            new Click().clickbyxpath("//a[@title='12.10 Concept Check']");
            Thread.sleep(2000);
            new AttemptTest().StaticTestAttemptAllTypeWithConfidence(2,false,false);
            Assert.assertEquals(lessonPage.getNextChapterFooter().size(), 4, "Error");
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().size(), 3, "Error");



        } catch (Exception e) {
            Assert.fail("Exception in testcase footerNavigationUnderChapter in class FooterNavigationLSCourse", e);
        }
    }
    @Test(priority = 2)
    public void notificationOnSwitchingAssessment () {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 200);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            //Student login
            new LoginUsingLTI().ltiLogin("145");
            new Navigator().NavigateTo("e-Textbook");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            //open Photosynthesis as a chapter
            new TopicOpen().chapterOpen(0);
            Thread.sleep(3000);
            new Click().clickbyxpath("//a[@title='0.2 Concept Check']");
            Thread.sleep(5000);
            lessonPage.getToc().click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[contains(text(),'0.2: Geography’s Perspective')]")).click();
            Thread.sleep(8000);
            //  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//a[@class='ls-prev' and @title='8.1 Concept Check']")));

            lessonPage.getNextChapterFooter().get(2).click();
            Thread.sleep(3000);
            Assert.assertTrue(lessonPage.getNotificationMessageOnclickOfPreviousFooter().getText().contains("You have chosen to open the assessment in the existing tab.This will lead to the existing assessment being quit and continue later. Are you sure you want to continue?"), "Displayed notification message is not correct");
            Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'active')]/span[2]")).getText().contains("P5B.2"), "The current tab is not Open");
            lessonPage.getNoLink().click();
            Thread.sleep(2000);
            new AttemptTest().StaticTestAttemptAllTypeWithConfidence(2,false,false);
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
            new LoginUsingLTI().ltiLogin("145");
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(0);
            Thread.sleep(1000);
            new Click().clickbyxpath("//span[@title='0.1: A World on Maps']");
            Thread.sleep(3000);
            new Navigator().NavigateTo("Dashboard");
            boolean elementPresent=false;
            for(WebElement element:lessonPage.getChapterName_Dashboard())
            {
                if(element.getText().contains("0.1: A World on Maps"))
                    elementPresent=true;
            }
            new Navigator().NavigateTo("My Activity");
            boolean elementPresentOnMyActivity=false;
            for(WebElement element:lessonPage.getChapterOnMyActivity())
            {
                if(element.getText().contains("0.1: A World on Maps"))
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
            new Click().clickbyxpath("//span[@title='0.1: A World on Maps']");
            Thread.sleep(3000);
            lessonPage.getNextChapterFooter().get(0).click();
            Thread.sleep(3000);
            new AttemptTest().StaticTestAttemptAllTypeWithConfidence(2,false,false);
            new Navigator().NavigateTo("Dashboard");
            elementPresent=false;
            for(WebElement element:lessonPage.getChapterName_Dashboard())
            {
                if(element.getText().contains("0.1: A World on Maps"))
                    elementPresent=true;
            }
            new Navigator().NavigateTo("My Activity");
            elementPresentOnMyActivity=false;
            for(WebElement element:lessonPage.getChapterOnMyActivity())
            {
                if(element.getText().contains("0.1: A World on Maps"))
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
            new Click().clickbyxpath("//span[@title='0.1: A World on Maps']");
            Thread.sleep(3000);
            lessonPage.getNextChapterFooter().get(0).click();
            Thread.sleep(3000);
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(2000);
            new Navigator().NavigateTo("Dashboard");
            elementPresent=false;
            for(WebElement element:lessonPage.getChapterName_Dashboard())
            {
                if(element.getText().contains("0.2: Geography’s Perspective"))
                    elementPresent=true;
            }
            new Navigator().NavigateTo("My Activity");
            elementPresentOnMyActivity=false;
            for(WebElement element:lessonPage.getChapterOnMyActivity())
            {
                if(element.getText().contains("0.2: Geography’s Perspective"))
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
            new TopicOpen().chapterOpen(0);
            Thread.sleep(1000);
            new Click().clickbyxpath("//span[@title='0.1: A World on Maps']");
            Thread.sleep(3000);
            lessonPage.getNextChapterFooter().get(0).click();
            Thread.sleep(3000);
            new AttemptTest().StaticTestAttemptAllTypeWithConfidence(2,false,false);
            new Navigator().NavigateTo("Dashboard");
            lessonPage.getChapterName_Dashboard().get(0).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ls-dashboard-activities-chaptername']")));
            //open introduction
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(7);
            Thread.sleep(1000);
            new Click().clickbyxpath("//span[@title='Chapter Introduction']");
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
