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
 * Created by Sumit on 7/16/2014.
 */
public class StudentAccessDashboardReportsAfterInstructorHidingAllTLOforChapter {

    @Test
    public void studentAccessDashboardReportsAfterInstructorHidingAllTLOforChapter()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("63_1");        //login as student
            new SelectChapterForTest().selectchapterfortest(0, 4); //Start the Diagnostic test
            new DiagnosticTest().attemptAllCorrect(2, false, false);
            new Navigator().orionDashboard();

            new LoginUsingLTI().ltiLogin("63");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            Driver.driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            new Settings().offTLOSOfChapter(1,0);

            new LoginUsingLTI().ltiLogin("63_1");        //login as student
            //TC row no. 63
            String chapter = Driver.driver.findElement(By.className("al-content-header-row")).getText();
            if(chapter.contains("Ch 1:"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After hiding 1st chapter(was hidden after hiding all TLOs of 1st chapter) the student still can see the first chapter.");
            }
            new Navigator().NavigateToStudentReport();	//navigate to My report
            Driver.driver.findElement(By.id("al-performance-report")).click(); //click on Performance Report
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("a[title='Show All Chapters']")).click();    //click Show All Chapters dropdown
            Thread.sleep(2000);
            //TC row no. 64
            int ch1 = Driver.driver.findElements(By.partialLinkText("Ch 1 :")).size();
            if(ch1 != 1)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In Performance report the hidden chapter(was hidden after hiding all TLOs of 1st chapter) is not shown in Chapter filter.");
            }
            Driver.driver.findElement(By.partialLinkText("Ch 1 :")).click();	//select chapter 1
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("a[title='Show All Objectives']")).click();    //click Show All Objectives dropdown
            Thread.sleep(2000);
            //TC row no. 65
            int ch2 = Driver.driver.findElements(By.partialLinkText("1.1")).size();
            if(ch2 == 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In Performance report the hidden TLO is not shown in Show All Objectives filter.");
            }
            new Navigator().NavigateToStudentAllActivity();

        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase studentAccessDashboardReportsAfterInstructorHidingAllTLOforChapter in class StudentAccessDashboardReportsAfterInstructorHidingAllTLOforChapter.", e);
        }
    }
    @AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
