package com.snapwiz.selenium.tests.staf.orion.testcases.IT15.R151;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by Sumit on 7/21/2014.
 */
public class StudentAttemptedQuestionsForChapterAndInstructorHidesThatChapter {

    @Test
    public void studentAttemptedQuestionsForChapterAndInstructorHidesThatChapter()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("111_1");        //login as student
            new SelectChapterForTest().selectchapterfortest(0, 4); //Start the Diagnostic test
            new DiagnosticTest().attemptAllCorrect(2, false, false);
            new Navigator().orionDashboard();
            new PracticeTest().startTest();//Start practice test
            new PracticeTest().practiceTestAttempt(2, 1, "skip", false, false);
            new Navigator().orionDashboard();
            new PracticeTest().openTLOLevelPracticeTestBasedOnIndex(1);
            new PracticeTest().practiceTestAttempt(2, 1, "skip", false, false);
            new Navigator().orionDashboard();
            new PracticeTest().openTLOLevelPracticeTestBasedOnIndex(2);
            new PracticeTest().practiceTestAttempt(2, 1, "skip", false, false);
            new Navigator().orionDashboard();

            new LoginUsingLTI().ltiLogin("111");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            Driver.driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("div.al-customize-course-disabled.al-chkbox")).click();    //click OFF button for chapter
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("111_1");        //login as student
            //TC row no. 111
            String chapter = Driver.driver.findElement(By.className("al-content-header-row")).getText();
            if(chapter.contains("Ch 1:"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After clicking on OFF for first chapter the student still can see the first chapter.");
            }
            //TC row no. 112
            String summaryBlock = Driver.driver.findElement(By.id("al-preformance-top-7-time-spent-content")).getCssValue("background-image");
            if(!summaryBlock.contains("time-spent-graph-chart.png"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After clicking on OFF for first chapter the student still can see It under \"Summary\" block in the right side.");
            }
            //TC row no. 113
            String leastProfCh = Driver.driver.findElement(By.className("al-recommended-focus-area-chart-section")).getCssValue("background-image");
            if(!leastProfCh.contains("recommended-focus-area-graph-chart.png"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After clicking on OFF for first chapter the student still can see It under \"Least Proficiency Chapter\" block in the right side.");
            }
            new Navigator().NavigateToStudentReport();
            Driver.driver.findElement(By.id("al-productivity-report")).click();		//click on productivity report
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("al-terminal-objective-title")).click();  //click on student name
            Thread.sleep(2000);
            boolean found = false;
            List<WebElement> allTlo1 = Driver.driver.findElements(By.className("al-terminal-objective-title"));
            for(WebElement l: allTlo1)
            {
                System.out.println("tlo: "+l.getText());
                if(l.getText().contains("1.1:"))
                {
                    found = true;
                    break;
                }
            }
            //TC row no. 114
            if(found == false)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Chapter which is OFF is not shown in the student side productivity report.");
            }
            //TC row no. 115
            int disabledTlo = Driver.driver.findElements(By.xpath("/*//*[starts-with(@class, 'al-link btn-disable al-tlo-start-practice-button al-tlo-start-practice-button-')]")).size();
            if(disabledTlo == 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Chapter which is OFF is not disabled in the student side productivity report.");
            }
            Driver.driver.findElement(By.id("al-metacognitive-report")).click();	//click on metacognitive Report
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("al-terminal-objective-title")).click();  //click on Chapter name
            Thread.sleep(2000);
            boolean found1 = false;
            List<WebElement> allTlo = Driver.driver.findElements(By.className("al-terminal-objective-title"));
            for(WebElement l: allTlo)
            {
                System.out.println("tlo: "+l.getText());
                if(l.getText().contains("1.1:"))
                {
                    found1 = true;
                    break;
                }
            }
            //TC row no. 117
            if(found1 == false)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Chapter and TLO which is OFF is not shown in the student side metacognitive report.");
            }
            //TC row no. 118
            int size3 = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'al-link btn-disable al-tlo-start-practice-button al-tlo-start-practice-button-')]")).size();
            System.out.println("size3: "+size3);
            if(size3 < 2)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"Practice\" button is not disabled for the TLO practice in metacognitive Report page.");
            }
            Driver.driver.findElement(By.id("al-most-challenging-activity")).click();	//click on most challenging activity
            Thread.sleep(2000);
            //TC row no. 120
            int size2 = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'al-link btn-disable  btn-disable  al-chapter-start-practice-button al-chapter-start-practice-button-')]")).size();
            System.out.println("size2: "+size2);
            if(size2 != 1)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"Practice\" button is not disabled for the chapter practice in most challenging activity page.");
            }
            new Navigator().NavigateToStudentAllActivity();
            Driver.driver.findElement(By.linkText("All Chapters")).click();         //click on All Chapters dropdown
            //TC row no. 122
            String ch = Driver.driver.findElement(By.partialLinkText("Ch 1:")).getText();
            System.out.println("ch: "+ch);
            if(ch.length()==0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In \"All Activity\" on selecting \"All Chapters\" dropdown the hidden chapter is not shown..");
            }
            Driver.driver.findElement(By.partialLinkText("Ch 1:")).click(); //click on first chapter
            Thread.sleep(2000);
            Driver.driver.findElement(By.linkText("All Objectives")).click();         //click on All Objectives dropdown
            Thread.sleep(2000);
            //TC row no. 123
            String objective = Driver.driver.findElement(By.partialLinkText("1.1")).getText();
            System.out.println("objective: "+objective);
            if(objective.length()==0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In \"All Activity\" on selecting \"All Objectives\" dropdown the hidden TLOs are not shown.");
            }
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase studentAttemptedQuestionsForChapterAndInstructorHidesThatChapter in class StudentAttemptedQuestionsForChapterAndInstructorHidesThatChapter.", e);
        }
    }
    @AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
