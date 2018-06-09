package com.snapwiz.selenium.tests.staf.orion.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class VerifyAllActivityLink {
	@Test
	public void verifyAllActivityLink()
	{
		try
		{
			Driver.startDriver();	
			new LoginUsingLTI().ltiLogin("1672"); //  Logging in as student to orion
			Driver.driver.findElement(By.cssSelector("div.al-user-profile-name")).click();
			Thread.sleep(2000);
			String myActivity = Driver.driver.findElement(By.id("ls-my-Activity")).getText();
			if(!myActivity.equals("All Activity"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("All Activity option is not available in profile dropdown.");
			}
			//click out side
			Driver.driver.findElement(By.className("al-body-content-wrapper")).click();
			Thread.sleep(2000);
			new Navigator().NavigateToStudentReport();
			Thread.sleep(2000);
			String reportName = Driver.driver.findElement(By.className("al-report-header-title")).getText();
			if(!reportName.equals("Most Challenging Activities"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Most Challenging Activities report is not displayed on clicking my reports.");
			}
			new Navigator().NavigateToReport("Performance Report");
			Thread.sleep(2000);
			String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
			if(!notice.contains("Not enough data is available to show the performance report."))
			{
				
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("Doesn't display alert message that no actvities are completed by the student.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in verifyAllActivityLink in class VerifyAllActivityLink",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
