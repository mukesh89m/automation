package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class VerifyCourseProductivityReport {
	@Test(priority=1,enabled = true)
	public void verifyCourseProductivityReportWhenNoChapterAttempted()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("881");
			new Navigator().NavigateToStudentReport();
			new Navigator().NavigateToReport("Productivity Report");
			String noData = Driver.driver.findElement(By.cssSelector("div[class='no-data-notification-message']")).getText();
			if(!noData.equals("No Data Available"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Blank Course-level Productivity Report is not displayed.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyCourseProductivityReportWhenNoChapterAttempted in class VerifyCourseProductivityReport ",e);
		}
	}
	
	@Test(priority = 2,enabled = true)
	public void verifyCourseProductivityReportWhenOneChapterAttempted()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("882");
			new DiagnosticTest().startTest(0,3);//select 1st chapter for test
			new DiagnosticTest().attemptAllCorrect(4, false, false);		//attempt correctly
			new Navigator().NavigateToStudentReport();
			new Navigator().NavigateToReport("Productivity Report");
			//list all dots
			List<WebElement> allDots = Driver.driver.findElements(By.cssSelector("g.highcharts-markers > path"));
			if(allDots.size() != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("When one chapter has been attempted, the productivity report doesnot show the single dot in the scattered plot.");
			}
			
			List<WebElement> allChapterNames = Driver.driver.findElements(By.cssSelector("span[class='al-terminal-objective-title']"));
			if(allChapterNames.size() != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("When one chapter has been attempted, the productivity report doesnot show the single chapter name in the bottom part.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyCourseProductivityReportWhenOneChapterAttempted in class VerifyCourseProductivityReport ",e);
		}
	}
	
	
	@Test(priority = 3, enabled = true)
	public void verifyCourseProductivityReportWhenSevenChapterAttempted()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("883");
			new DiagnosticTest().startTest(0,3);//select 1st chapter for test
			new DiagnosticTest().attemptAllCorrect(4, false, false);		//attempt correctly
			
			new Navigator().orionDashboard();
			
			new DiagnosticTest().startTest(1,3);//select 2nd chapter for test
			new DiagnosticTest().attemptAllCorrect(4, false, false);		//attempt correctly
			
			new Navigator().orionDashboard();
			
			new DiagnosticTest().startTest(2,3);//select 3rd chapter for test
			new DiagnosticTest().attemptAllCorrect(4, false, false);		//attempt correctly
			
			new Navigator().orionDashboard();
			
			new DiagnosticTest().startTest(3,3);//select 4th chapter for test
			new DiagnosticTest().attemptAllCorrect(4, false, false);		//attempt correctly
			
			new Navigator().orionDashboard();
			
			new DiagnosticTest().startTest(4,3);//select 5th chapter for test
			new DiagnosticTest().attemptAllCorrect(4, false, false);		//attempt correctly
			
			new Navigator().orionDashboard();
			
			new DiagnosticTest().startTest(5,3);//select 6th chapter for test
			new DiagnosticTest().attemptAllCorrect(4, false, false);		//attempt correctly
			
			new Navigator().orionDashboard();
			
			new DiagnosticTest().startTest(6,3);//select 7th chapter for test
			new DiagnosticTest().attemptAllCorrect(4, false, false);		//attempt correctly
			
			new Navigator().NavigateToStudentReport();
			new Navigator().NavigateToReport("Productivity Report");
			//list all dots
			List<WebElement> allDots = Driver.driver.findElements(By.cssSelector("g.highcharts-markers > path"));
			if(allDots.size() != 7)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("When one chapter has been attempted, the productivity report doesnot show the single dot in the scattered plot.");
			}
			
			List<WebElement> allChapterNames = Driver.driver.findElements(By.cssSelector("span[class='al-terminal-objective-title']"));
			if(allChapterNames.size() != 7)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("When one chapter has been attempted, the productivity report doesnot show the single chapter name in the bottom part.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyCourseProductivityReportWhenOneChapterAttempted in class VerifyCourseProductivityReport ",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
