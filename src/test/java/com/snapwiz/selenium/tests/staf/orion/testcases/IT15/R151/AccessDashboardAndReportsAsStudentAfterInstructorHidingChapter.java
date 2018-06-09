package com.snapwiz.selenium.tests.staf.orion.testcases.IT15.R151;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

/*
 * Created by Sumit on 7/15/2014.
 */
public class AccessDashboardAndReportsAsStudentAfterInstructorHidingChapter {
	
	@Test(priority = 1, enabled = true )
	public void accessDashboardAndReportsAsStudentAfterInstructorHidingChapter()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("46_1");        //login as student
            new SelectChapterForTest().selectchapterfortest(0, 4); //Start the Diagnostic test
            new DiagnosticTest().attemptAllCorrect(2, false, false);
			new Navigator().orionDashboard();
			
            new LoginUsingLTI().ltiLogin("46");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            Driver.driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("div.al-customize-course-disabled.al-chkbox")).click();    //click OFF button for chapter
            Thread.sleep(2000);
            
            new LoginUsingLTI().ltiLogin("46_1");        //login as student
            //TC row no. 46
            String chapter = Driver.driver.findElement(By.className("al-content-header-row")).getText();
            if(chapter.contains("Ch 1:"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After clicking on OFF for first chapter the student still can see the first chapter.");
            }
            new Navigator().NavigateToStudentReport();	//navigate to My report
            Driver.driver.findElement(By.id("al-performance-report")).click(); //click on Performance Report
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("a[title='Show All Chapters']")).click();    //click Show All Chapters dropdown
            Thread.sleep(2000);
            //TC row no. 47
            int ch1 = Driver.driver.findElements(By.partialLinkText("Ch 1 :")).size();
            if(ch1 != 1)
            {
            	new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In Performance report the hidden chapter is not shown in Chapter filter.");
            }
            new Navigator().NavigateToStudentAllActivity();
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase accessDashboardAndReportsAsStudentAfterInstructorHidingChapter in class AccessDashboardAndReportsAsStudentAfterInstructorHidingChapter.", e);
		}
	}
	@Test(priority = 2, enabled = true )
	public void accessDashboardAndReportsAsStudentAfterInstructorHidingTLO()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("48_1");        //login as student
            new SelectChapterForTest().selectchapterfortest(0, 4); //Start the Diagnostic test
            new DiagnosticTest().attemptAllCorrect(2, false, false);
			new Navigator().orionDashboard();
			
            new LoginUsingLTI().ltiLogin("48");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            Driver.driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("div.tloToggle.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();//click on OFF for TLO
            Thread.sleep(2000);
            
            new LoginUsingLTI().ltiLogin("48_1");        //login as student
            new Navigator().NavigateToStudentReport();	//navigate to My report
            Driver.driver.findElement(By.id("al-performance-report")).click(); //click on Performance Report 
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("a[title='Show All Chapters']")).click();    //click Show All Chapters dropdown
            Thread.sleep(2000);
            Driver.driver.findElement(By.partialLinkText("Ch 1 :")).click();	//select chapter 1
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("a[title='Show All Objectives']")).click();    //click Show All Objectives dropdown
            Thread.sleep(2000);
            //TC row no. 47, 51
            int ch1 = Driver.driver.findElements(By.partialLinkText("1.1")).size();
            if(ch1 == 0)
            {
            	new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In Performance report the hidden TLO is not shown in Show All Objectives filter.");
            }
            
            Driver.driver.findElement(By.cssSelector("a[title='Performance Report']")).click();		//click on Performance Report 
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("a[title='Productivity Report']")).click();	//click on Productivity Report
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("al-terminal-objective-title")).click();  //click on Chapter name
            Thread.sleep(2000);
            //TC row no. 52
            int size = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'al-link btn-disable al-tlo-start-practice-button al-tlo-start-practice-button-')]")).size();
            if(size == 0)
            {
            	new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"Practice\" button is not disabled for the TLO practice Productivity Report.");	
            }
            Driver.driver.findElement(By.id("al-metacognitive-report")).click();	//click on metacognitive Report
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("al-terminal-objective-title")).click();  //click on Chapter name
            Thread.sleep(2000);
            //TC row no. 56
            int size1 = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'al-link btn-disable al-tlo-start-practice-button al-tlo-start-practice-button-')]")).size();
            if(size1 != 1)
            {
            	new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"Practice\" button is not disabled for the TLO practice in metacognitive Report page.");	
            }
            Driver.driver.findElement(By.id("al-most-challenging-activity")).click();	//click on most challenging activity
            Thread.sleep(2000);
            //TC row no. 60
            int size2 = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'al-link btn-disable al-tlo-start-practice-button al-tlo-start-practice-button-')]")).size();
            if(size2 != 1)
            {
            	new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"Practice\" button is not disabled for the TLO practice in most challenging activity page.");	
            }
            new Navigator().NavigateToStudentAllActivity();
            Driver.driver.findElement(By.cssSelector("a[title='All Chapters']")).click();    //click All Chapters dropdown
            Thread.sleep(2000);
            Driver.driver.findElement(By.partialLinkText("Ch 1")).click();	//select chapter 1
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("a[title='All Objectives']")).click();    //click All Objectives dropdown
            Thread.sleep(2000);
            //TC row no. 62
            String ch = Driver.driver.findElement(By.partialLinkText("1.1")).getText();
            if(ch.length() == 0)
            {
            	new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("The hidden TLO is not shown in All Objectives filter in All Activity page.");
            }
            
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase accessDashboardAndReportsAsStudentAfterInstructorHidingTLO in class AccessDashboardAndReportsAsStudentAfterInstructorHidingChapter.", e);
		}
	}
	@AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
