package com.snapwiz.selenium.tests.staf.orion.testcases.IT14.R1412;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class OrionGradebookSync {

	@Test(priority = 1, enabled = true) 
	public void orionGradebookSync()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("3");//login as student
			int redOutline = Driver.driver.findElements(By.cssSelector("div[class='al-content-body-wrapper-disabled lti-chapter-red-outline']")).size();
			if(redOutline == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In dashboard the chapter is not highlighted.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase orionGradebookSync in class OrionGradebookSync.",e);
		}
	}
	@Test(priority = 2, enabled = true) 
	public void orionGradebookSyncForMultipleChapters()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("4");//login as student
			int redOutline = Driver.driver.findElements(By.cssSelector("div[class='al-content-body-wrapper-disabled lti-chapter-red-outline']")).size();
			if(redOutline != 3)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In dashboard the chapter are not highlighted.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase orionGradebookSyncForMultipleChapters in class OrionGradebookSync.",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
