package com.snapwiz.selenium.tests.staf.learnon.testcases.IT17.R58;

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.*;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by Sumit on 9/18/2014.
 */
public class DiscussionAddedOverLesson {

    @Test(priority = 1, enabled = true)
    public void discussionAddedOverLessonByStudent()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("10");//login as student
            new Navigator().NavigateTo("Course");//go to Course
            new TOCShow().tocHide();//close the TOC
            String discussion = new RandomString().randomstring(10);
            new Discussion().postDiscussion(discussion);//post a discussion
            new Navigator().NavigateTo("Dashboard");//go to Dashboard
            String text;
            text = new TextFetch().textfetchbycssselector("div[class='middle ']");
            if(!text.contains("T 1"))
                Assert.fail("The word \"Ch\" is not replaced by \"T\" for a posted discussion in Course Stream tile.");
            new Navigator().NavigateTo("Course Stream");//go to Course Stream
            text = new TextFetch().textfetchbycssselector("p[class='ls-stream-share__title']");
            if(!text.contains("T 1"))
                Assert.fail("The word \"Ch\" is not replaced by \"T\" for a posted discussion in Course Stream.");

            new Navigator().NavigateTo("My Activity");//go to My Activity
            text = new TextFetch().textfetchbycssselector("p[class='card-title']");
            if(!text.contains("T 1"))
                Assert.fail("The word \"Ch\" is not replaced by \"T\" for a posted discussion in My Activity.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC discussionAddedOverLesson in class DiscussionAddedOverLesson", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void discussionAddedOverLessonByInstructor()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("12");//login as student
            new Navigator().NavigateTo("Course");//go to Course
            new TOCShow().tocHide();//close the TOC
            String discussion = new RandomString().randomstring(10);
            new Discussion().postDiscussion(discussion);//post a discussion
            new Navigator().NavigateTo("Dashboard");//go to Dashboard
            String text;
            text = new TextFetch().textfetchbycssselector("div[class='middle instructor-middle-align']");
            if(!text.contains("T 1"))
                Assert.fail("The word \"Ch\" is not replaced by \"T\" for a posted discussion in Course Stream tile.");
            new Navigator().NavigateTo("Course Stream");//go to Course Stream
            text = new TextFetch().textfetchbycssselector("p[class='ls-stream-share__title']");
            if(!text.contains("T 1"))
                Assert.fail("The word \"Ch\" is not replaced by \"T\" for a posted discussion in Course Stream.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC discussionAddedOverLesson in class DiscussionAddedOverLesson", e);
        }
    }

    //diagnostic assessments will not be there in LearnOn courses
    @Test(priority = 3, enabled = false)
    public void studentSubmitAssessment()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("16");//login as student
            new Navigator().NavigateTo("Course");//go to Course
            new DiagnosticTest().startTest(4);
            new DiagnosticTest().attemptAllCorrect(0, false, false);
            String text;
            List<WebElement> allFilter;
            text = new TextFetch().textfetchbyid("report-side-bar-chapter-label");

            if(!text.contains("T 1"))
                Assert.fail("The word \"Ch\" is not replaced by \"T\" in diagnostic test report page.");

            text = new TextFetch().textfetchbyid("al-peformance-title-id");
            if(!text.contains("T 1"))
                Assert.fail("The word \"Ch\" is not replaced by \"T\" in diagnostic test report page header.");

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "14");
            new Assignment().create(14);
            new Assignment().addQuestions(14, "multiplechoice", assessmentname);
            new Assignment().addQuestions(14, "multipleselection", assessmentname);

            new LoginUsingLTI().ltiLogin("14"); //log in as instructor
            new Assignment().assignToStudent(14);

            new LoginUsingLTI().ltiLogin("16");//log in as student
            new Assignment().submitAssignmentAsStudent(14);
            new Navigator().NavigateTo("Proficiency Report");//go to Proficiency Report
            text = new TextFetch().textfetchbyid("report-sidebar-chapter-dropDown-wrapper");
            if(!text.contains("Show All Topics"))
                Assert.fail("The word \"Show All Chapters\" is not replaced by \"Show All Topics\" in diagnostic test report page.");

            text = new TextFetch().textfetchbyclass("ls-proficiency-report-barchart-block");
            if(!text.contains("Course Proficiency by Topic"))
                Assert.fail("In proficiency report page the \"Course Proficiency by Topic\" label is not displayed above the graph.");

            text = new TextFetch().textfetchbyid("al-course-proficiency-by-chapter").trim();
            if(!text.contains("Topics"))
                Assert.fail("\"Chapter\" label is not removed with \"Topics\" from the X-Axis of Proficiency Report graph of student side.");

            new Navigator().NavigateTo("My Notes");//go to My Notes

            //add a note
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("add-note-button")));
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("textarea[class='ins-uploadResource-textbox']")).click();
            Driver.driver.findElement(By.cssSelector("textarea[class='ins-uploadResource-textbox']")).clear();
            Driver.driver.findElement(By.cssSelector("textarea[class='ins-uploadResource-textbox']")).sendKeys("noteText");
            new Click().clickBycssselector("div[class='course-chapter-label node']");//click to associate
            new Click().clickBycssselector("span[class='ins-dialogBox-Save ins-resource-save-btn']");//click save
            Thread.sleep(2000);
            text = new TextFetch().textfetchbycssselector("div[class='journal-subject-title ellipsis']");
            if(text.contains("Ch 1: The Study of Life"))
                Assert.fail("The word \"Ch\" is not replaced by \"T\" for note entry in My Notes page.");

            text = Driver.driver.findElement(By.id("myJournal-chapter-dropDown-wrapper")).getText();
            Assert.assertEquals(text, "All Topics", "\"All Chapter\" is displayed instead of \"All Topics\" in My Notes.");

            new Navigator().NavigateTo("My Activity");//go to My Activity
            allFilter = Driver.driver.findElements(By.xpath("/*//*[starts-with(@id, 'sbSelector_')]"));
            Assert.assertEquals(allFilter.get(0).getText() , "All Topics", "\"All Chapter\" is displayed instead of \"All Topics\" in My Activity page.");
            Assert.assertEquals(allFilter.get(1).getText() , "All Sub Topics", "\"All Sub Topics\" is not displayed in My Activity page.");

            new LoginUsingLTI().ltiLogin("14"); //log in as instructor
            new Navigator().NavigateTo("Most Challenging Activities Report");//go to Most Challenging Activities
            text = new TextFetch().textfetchbyclass("idb-terminal-objective-title");
            if(!text.contains("T 1"))
                Assert.fail("The word \"Ch\" is not replaced by \"T\" in Most Challenging Activities report page.");

            new Navigator().NavigateTo("Proficiency Report");//go to Proficiency Report
            text = new TextFetch().textfetchbyid("ir-performance-report-title").trim();
            if(!text.contains("Class Proficiency by Topics"))
                Assert.fail("Class Proficiency by Topics header is absent in Proficiency Report page.");

            text = new TextFetch().textfetchbyid("ir-course-performance-report-bar-content").trim();
            if(!text.contains("Topics"))
                Assert.fail("\"Chapter\" label is not removed with \"Topics\" from the X-Axis of Proficiency Report graph from instructor side.");

            new Navigator().NavigateTo("Question Banks");//go to Question Banks

            allFilter = Driver.driver.findElements(By.xpath("/*//*[starts-with(@id, 'sbSelector_')]"));
            Assert.assertEquals(allFilter.get(4).getText() , "All Topics", "\"All Chapter\" is displayed instead of \"All Topics\" in Question Bank of instructor side.");
            Assert.assertEquals(allFilter.get(5).getText() , "All Sub Topics", "\"All Sub Topics\" is not displayed in Question Bank of instructor side.");
            Assert.assertEquals(allFilter.get(6).getText() , "All Learning Objectives", "\"All Learning Objectives\" is not displayed in Question Bank of instructor side.");

            new Click().clickBycssselector("span[title='My Library']");//go to My Library
            allFilter = Driver.driver.findElements(By.xpath("/*//*[starts-with(@id, 'sbSelector_')]"));
            Assert.assertEquals(allFilter.get(1).getText() , "All Topics", "\"All Chapter\" is displayed instead of \"All Topics\" in My Library of instructor side.");
            Assert.assertEquals(allFilter.get(2).getText() , "All Sub Topics", "\"All Sub Topics\" is not displayed in My Library of instructor side.");
            Assert.assertEquals(allFilter.get(3).getText() , "All Learning Objectives", "\"All Learning Objectives\" is not displayed in My Library of instructor side.");

            new Navigator().NavigateTo("Resources");//go to Resources
            allFilter = Driver.driver.findElements(By.xpath("/*//*[starts-with(@id, 'sbSelector_')]"));
            Assert.assertEquals(allFilter.get(4).getText() , "All Topics", "\"All Chapter\" is displayed instead of \"All Topics\" in All Resources of instructor side.");
            Assert.assertEquals(allFilter.get(5).getText() , "All Sub Topics", "\"All Sub Topics\" is not displayed in All Resources of instructor side.");

            new Click().clickBycssselector("span[title='My Library']");//go to My Library
            allFilter = Driver.driver.findElements(By.xpath("/*//*[starts-with(@id, 'sbSelector_')]"));
            Assert.assertEquals(allFilter.get(2).getText() , "All Topics", "\"All Chapter\" is displayed instead of \"All Topics\" in My Library of instructor side.");
            Assert.assertEquals(allFilter.get(3).getText() , "All Sub Topics", "\"All Sub Topics\" is not displayed in My Library of instructor side.");

            new Navigator().NavigateTo("Assignments");//go to Assignments
            allFilter = Driver.driver.findElements(By.xpath("/*//*[starts-with(@id, 'sbSelector_')]"));
            Assert.assertEquals(allFilter.get(1).getText() , "All Topics", "\"All Chapter\" is displayed instead of \"All Topics\" in Assignments page of instructor side.");
            Assert.assertEquals(allFilter.get(2).getText() , "All Sub Topics", "\"All Sub Topics\" is not displayed in in Assignments page of instructor side.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC studentSubmitAssessment in class DiscussionAddedOverLesson", e);
        }
    }
    @AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
