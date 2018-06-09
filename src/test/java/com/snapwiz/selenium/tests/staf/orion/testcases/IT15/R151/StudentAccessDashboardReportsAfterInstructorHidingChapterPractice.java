package com.snapwiz.selenium.tests.staf.orion.testcases.IT15.R151;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by Sumit on 7/16/2014.
 */
public class StudentAccessDashboardReportsAfterInstructorHidingChapterPractice {
    @Test
    public void studentAccessDashboardReportsAfterInstructorHidingChapterPractice()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("67");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            Driver.driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("div.tloToggle.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();//click on OFF for TLO
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("div.chapter-practice.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();    //click OFF Chapter practice
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("67_1");        //login as student
            new SelectChapterForTest().selectchapterfortest(0, 4); //Start the Diagnostic test
            new DiagnosticTest().attemptAllCorrect(2, false, false);
            new Navigator().orionDashboard();
            //TC row no. 67
            String practice = Driver.driver.findElement(By.cssSelector("span[title='Practice']")).getCssValue("background-image");
            if(!practice.contains("block-icon.png"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail(" \"Practice\" button is not disabled for the chapter practice after finishing diag test after instructor OFF the Chapter Practice.");
            }
            new Navigator().NavigateToStudentReport();
            Driver.driver.findElement(By.id("al-productivity-report")).click();    //click on productivity report
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("al-terminal-objective-title")).click();  //click on Chapter name
            Thread.sleep(2000);
            //TC row no. 69
            int size = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'al-link btn-disable al-tlo-start-practice-button al-tlo-start-practice-button-')]")).size();
            if(size != 1)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"Practice\" button is not disabled in Productivity Report for the chapter practice after instructor OFF the Chapter Practice..");
            }
            Driver.driver.findElement(By.className("highcharts-markers")).click();  //click on dot
            Thread.sleep(2000);
            //TC row no. 73
            int size1 = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'al-link btn-disable al-tlo-start-practice-button al-tlo-start-practice-button-')]")).size();
            if(size1 != 1)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"Practice\" button is not disabled in Productivity Report for the chapter practice after instructor OFF the Chapter Practice..");
            }
            Driver.driver.findElement(By.id("al-metacognitive-report")).click();	//click on metacognitive Report
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("al-terminal-objective-title")).click();  //click on Chapter name
            Thread.sleep(2000);
            //TC row no. 75
            int size2 = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'al-link btn-disable al-tlo-start-practice-button al-tlo-start-practice-button-')]")).size();
            if(size2 != 1)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"Practice\" button is not disabled in Metacognitive Report for the chapter practice after instructor OFF the Chapter Practice..");
            }
            Driver.driver.findElement(By.id("al-most-challenging-activity")).click(); //click on most challenging activity
            Thread.sleep(2000);
            Driver.driver.findElement(By.id("al-most-challenging-activities-chapters")).click();    //click on View by Chapter
            Thread.sleep(2000);
            //TC row no. 81
            int size3 = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'al-link   btn-disable  al-chapter-start-practice-button al-chapter-start-practice-button-')]")).size();
            if(size3 != 1)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"Practice\" button is not disabled in most challenging activity for the chapter practice after instructor OFF the Chapter Practice..");
            }

        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase studentAccessDashboardReportsAfterInstructorHidingChapterPractice in class StudentAccessDashboardReportsAfterInstructorHidingChapterPractice.", e);
        }
    }
    @AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
