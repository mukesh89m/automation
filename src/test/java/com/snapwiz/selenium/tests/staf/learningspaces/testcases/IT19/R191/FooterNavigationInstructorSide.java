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
public class FooterNavigationInstructorSide extends Driver {

    @Test(priority = 1)
    public void footerNavigationUnderChapter() {
        try {

            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            //Student login
            new LoginUsingLTI().ltiLogin("88");
            new Navigator().NavigateTo("e-Textbook");
            WebDriverWait wait = new WebDriverWait(driver, 200);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            //open Photosynthesis as a chapter
            new TopicOpen().chapterOpen(0);
            Thread.sleep(3000);
            new Click().clickbyxpath("//a[contains(@title,'Diagnostic')]");
            Assert.assertEquals(lessonPage.getNextChapterFooter().size(), 2, "Error");
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().size(), 1, "Error");
            Thread.sleep(2000);
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.getNextChapterFooter().size(), 2, "Error");
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().size(), 2, "Error");
            new Navigator().NavigateTo("e-Textbook");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            //open Metabolism as a chapter
            new TopicOpen().chapterOpen(5);
            Thread.sleep(3000);
            new Click().clickbyxpath("//a[contains(@title,'Diagnostic')]");
            Thread.sleep(3000);
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(3000);
            Assert.assertEquals(lessonPage.getNextChapterFooter().get(1).getText().contains("Introduction"), "Not able locate element on screen");
            lessonPage.getPreviousChapterFooter().get(1).click();
            Thread.sleep(2000);
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(2000);
            lessonPage.getNextChapterFooter().get(1).click();
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().get(1).getText().contains("Personalized Practice - Ch 6: Metabolism"),"Not able locate element on screen");
            Assert.assertEquals(lessonPage.getNextChapterFooter().get(1).getText().contains("6.1: Energy and Metabolism"), "Not able locate element on screen");
            lessonPage.getPreviousChapterFooter().get(1).click();
            Thread.sleep(2000);
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(2000);
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().get(1).getText().contains("Introduction"),"Not able locate element on screen");
            Assert.assertEquals(lessonPage.getNextChapterFooter().get(1).getText().contains("6.1: Describe anabolic and catabolic pathways in metabolism - Practice"), "Not able locate element on screen");
            lessonPage.getPreviousChapterFooter().get(1).click();
            Thread.sleep(2000);
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(2000);
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().get(1).getText().contains("6.1: Energy and Metabolism"),"Not able locate element on screen");
            Assert.assertEquals(lessonPage.getNextChapterFooter().get(1).getText().contains("6.1 Concept Check"), "Not able locate element on screen");
            lessonPage.getPreviousChapterFooter().get(1).click();
            Thread.sleep(2000);
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(2000);
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().get(1).getText().contains("6.1: Describe anabolic and catabolic pathways in metabolism - Practice"),"Not able locate element on screen");
            Assert.assertEquals(lessonPage.getNextChapterFooter().get(1).getText().contains("6.2: Potential, Kinetic, Free, and Activation Energy"),"Not able locate element on screen");
            lessonPage.getPreviousChapterFooter().get(1).click();
            Thread.sleep(2000);
            new Navigator().NavigateTo("e-Textbook");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            //open Metabolism as a chapter
            new TopicOpen().chapterOpen(5);
            Thread.sleep(3000);
            new Click().clickbyxpath("//a[contains(@title,'Chapter Summary')]");
            Thread.sleep(3000);
            lessonPage.getNextChapterFooter().get(0).click();
            Thread.sleep(3000);
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().get(1).getText().contains("Chapter Summary"), "Not able locate element on screen");
            Assert.assertEquals(lessonPage.getNextChapterFooter().get(1).getText().contains("Next Chapter"),"Not able locate element on screen");
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(3000);
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='selected-chapter-name']")).getText().contains("Ch 7: Cellular Respiration"),"Not able locate element on screen");
            new Click().clickbyxpath("//a[contains(@title,'Diagnostic')]");

            Assert.assertEquals(lessonPage.getPreviousChapterFooter().get(1).getText().contains("Previous Chapter"),"Not able locate element on screen");
            Assert.assertEquals(lessonPage.getNextChapterFooter().get(1).getText().contains("Personalized Practice - Ch 7: Cellular Respiration"),"Not able locate element on screen");
            lessonPage.getPreviousChapterFooter().get(1).click();
            Thread.sleep(3000);
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='selected-chapter-name']")).getText().contains("Ch 6: Metabolism"),"Not able locate element on screen");
            int chapterCount=lessonPage.getChapterCount().size();
            lessonPage.getChapterCount().get(chapterCount).click();
            Thread.sleep(3000);
            new Click().clickbyxpath("//a[contains(@title,'Chapter Summary')]");
            Thread.sleep(3000);
            Assert.assertEquals(lessonPage.getNextChapterFooter().size(), 0, "Error");
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().size(), 1, "Error");

                    }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase footerNavigationUnderChapter in class FooterNavigationInstructorSide", e);
        }
    }

    @Test(priority = 2)
    public void entryOverDashboardAndMyActivity () {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 200);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            //Student login
            new LoginUsingLTI().ltiLogin("88");
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
            new Navigator().NavigateTo("Dashboard");
            lessonPage.getChapterName_Dashboard().get(0).click();
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.getNextChapterFooter().size(), 1, "Error");
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().size(), 1, "Error");
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
        }
        catch (Exception e) {
            Assert.fail("Exception in testcase notificationOnSwitchingAssessment in class FooterNavigation", e);
        }
    }
}

