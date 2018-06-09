package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.RunScheduledJobs;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class MentorShowMostChallengingChapter
{
	@Test(priority=1,enabled=true)
	public void mentorShowMostChallengingChapterattempt7chapter()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1048");//Login as student
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, true);//attempt diagnostic test1
			new Navigator().orionDashboard();//navigate to dashboard
			Thread.sleep(2000);
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, true);//attempt diagnostic test2
			new Navigator().orionDashboard();//navigate to dashboard
			Thread.sleep(2000);
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, true);//attempt diagnostic test3
			new Navigator().orionDashboard();//navigate to dashboard
			Thread.sleep(2000);
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, true);//attempt diagnostic test4
			new Navigator().orionDashboard();//navigate to dashboard
			Thread.sleep(2000);
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, true);//attempt diagnostic test5
			new Navigator().orionDashboard();//navigate to dashboard
			Thread.sleep(2000);
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, true);//attempt diagnostic test6
			new Navigator().orionDashboard();//navigate to dashboard
			Thread.sleep(2000);
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, true);//attempt diagnostic test7
			new Navigator().orionDashboard();//navigate to dashboard
			Thread.sleep(2000);
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, true);//attempt diagnostic test8
			new Navigator().orionDashboard();//navigate to dashboard
			new RunScheduledJobs().runScheduledJobs();
			new LoginUsingLTI().ltiLogin("10481");
			List<WebElement> leastproficenychapter=Driver.driver.findElements(By.className("idb-terminal-objective-title"));
			int noofchapterdisplay=leastproficenychapter.size();
			
			if(noofchapterdisplay<7)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("less than 6 chapter shown");
			}
			boolean link=Driver.driver.findElement(By.id("idb-class-performance-seleted")).isDisplayed();
			if(link==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("by default weeked7 chapter not selected");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase instructorShowMostChallengingChapter in class InstructorShowMostChallengingChapter",e);
		}
		
	}
	
	@Test(priority=2,enabled=true)
	public void mentorShowMostChallengingChapterattempt6chapter()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1048");//Login as student
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, true);//attempt diagnostic test1
			new Navigator().orionDashboard();//navigate to dashboard
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, true);//attempt diagnostic test2
			new Navigator().orionDashboard();//navigate to dashboard
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, true);//attempt diagnostic test3
			new Navigator().orionDashboard();//navigate to dashboard
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, true);//attempt diagnostic test4
			new Navigator().orionDashboard();//navigate to dashboard
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, true);//attempt diagnostic test5
			new Navigator().orionDashboard();//navigate to dashboard
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, true);//attempt diagnostic test6
			new Navigator().orionDashboard();//navigate to dashboard
			new RunScheduledJobs().runScheduledJobs();
			new LoginUsingLTI().ltiLogin("10481");
			List<WebElement> leastproficenychapter=Driver.driver.findElements(By.className("idb-terminal-objective-title"));
			int noofchapterdisplay=leastproficenychapter.size();
			System.out.println(noofchapterdisplay);
			if(noofchapterdisplay>7)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("less than 7 chapter shown");
			}
			boolean link=Driver.driver.findElement(By.id("idb-class-performance-seleted")).isDisplayed();
			if(link==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("by default weeked7 chapter not selected");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase instructorShowMostChallengingChapter in class InstructorShowMostChallengingChapter",e);
		}
		
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}

}
