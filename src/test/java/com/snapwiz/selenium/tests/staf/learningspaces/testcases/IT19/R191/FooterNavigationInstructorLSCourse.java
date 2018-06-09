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
public class FooterNavigationInstructorLSCourse extends Driver{
    @Test(priority = 1)
    public void footerNavigationUnderChapter() {
        try {

            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            //Student login
            new LoginUsingLTI().ltiLogin("163");
            new Navigator().NavigateTo("e-Textbook");
            WebDriverWait wait = new WebDriverWait(driver, 200);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            //open Photosynthesis as a chapter
            new TopicOpen().chapterOpen(0);
            Thread.sleep(3000);
            new Click().clickbyxpath("//a[contains(@title,'Chapter Introduction')]");
            Assert.assertEquals(lessonPage.getNextChapterFooter().size(), 1, "Error");
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().size(), 0, "Error");
            lessonPage.getNextChapterFooter().get(0).click();
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().get(0).getText().contains("Chapter Introduction"), "Not able locate element on screen");
            Assert.assertEquals(lessonPage.getNextChapterFooter().get(0).getText().contains("0.2 Concept Check"),"Not able locate element on screen");            new Navigator().NavigateTo("e-Textbook");
            //open Metabolism as a chapter
            lessonPage.getPreviousChapterFooter().get(0).click();
            Thread.sleep(3000);
            lessonPage.getNextChapterFooter().get(0).click();
            Thread.sleep(2000);
            lessonPage.getNextChapterFooter().get(0).click();
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().get(0).getText().contains("0.1: A World on Maps"), "Not able locate element on screen");
            Assert.assertEquals(lessonPage.getNextChapterFooter().get(0).getText().contains("0.2: Geography’s Perspective"),"Not able locate element on screen");            new Navigator().NavigateTo("e-Textbook");
            lessonPage.getPreviousChapterFooter().get(0).click();
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("span[@class=tab_icon assessment-icon]")).click();
            Thread.sleep(2000);
            lessonPage.getNextChapterFooter().get(1).click();
            new Navigator().NavigateTo("e-Textbook");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            //open Metabolism as a chapter
            new TopicOpen().chapterOpen(5);
            Thread.sleep(3000);
            new Click().clickbyxpath("//a[contains(@title,'0.12 Concept Check')]");
            Thread.sleep(3000);
            lessonPage.getNextChapterFooter().get(0).click();
            Thread.sleep(3000);
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().get(1).getText().contains("0.11: Realms and Regions: The Structure of this Book"), "Not able locate element on screen");
            Assert.assertEquals(lessonPage.getNextChapterFooter().get(1).getText().contains("Next Chapter"),"Not able locate element on screen");
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='selected-chapter-name']")).getText().contains("Ch 1A: The European Realm"),"Not able locate element on screen");
            new Click().clickbyxpath("//a[@title='1A.1: Defining the Realm']");
            lessonPage.getPreviousChapterFooter().get(2).click();
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='selected-chapter-name']")).getText().contains("Ch G: Introduction: World Regional Geography: Global Perspectives"),"Not able locate element on screen");
            int chapterCount=lessonPage.getChapterCount().size();
            lessonPage.getChapterCount().get(chapterCount).click();
            new Click().clickbyxpath("//a[@title='12.10 Concept Check']");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.getNextChapterFooter().size(), 3, "Error");
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().size(), 2, "Error");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase footerNavigationUnderChapter in class FooterNavigationInstructorLSCourse", e);
        }
    }

    @Test(priority = 2)
    public void entryOverDashboardAndMyActivity () {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 200);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            //Student login
            new LoginUsingLTI().ltiLogin("163");
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(0);
            Thread.sleep(1000);
            new Click().clickbyxpath("//span[@title='Chapter Introduction']");
            Thread.sleep(3000);
            lessonPage.getNextChapterFooter().get(1).click();
            Thread.sleep(3000);
            new Navigator().NavigateTo("Dashboard");
            boolean elementPresent=false;
            for(WebElement element:lessonPage.getChapterName_Dashboard())
            {
                if(element.getText().contains("0.1: A World on Maps"))
                    elementPresent=true;
            }
            if(!elementPresent)
            {
                Assert.fail("Accessed lesson is not displaying at dashBoard");
            }

            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(0);
            Thread.sleep(1000);
            new Click().clickbyxpath("//a[@title='0.2 Concept Check']");
            Thread.sleep(2000);
            new Navigator().NavigateTo("Dashboard");
            elementPresent=false;
            for(WebElement element:lessonPage.getChapterName_Dashboard())
            {
                if(element.getText().contains("1.1 Concept Check"))
                    elementPresent=true;
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
