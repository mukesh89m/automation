package com.snapwiz.selenium.tests.staf.orion.uihelper;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class LearnMoreReport {
	
	public void learnMoreLinkClick(String reportName)
	{
		try
		{
			Driver.driver.findElement(By.className("al-reports-know-more")).click(); //clicking on learn more link in reports
			Thread.sleep(3000);
			System.out.println(Driver.driver.findElement(By.className("al-helpDialogContentWrapper")).getAttribute("style"));
			if(!Driver.driver.findElement(By.className("al-helpDialogContentWrapper")).getAttribute("style").contains(reportName))
			{
				new Screenshot().captureScreenshotFromAppHelper();
				Assert.fail("The help page opened is not of the report "+reportName);
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception while clicking on Learn More link of reports",e);
		}
	}

	public void closeLearnMorePage()
	{
		try
		{
			Driver.driver.findElement(By.id("al-helpDialog-close-icon")).click(); //clicking on close button of learn more page in reports
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception while closing Learn More page of reports",e);
		}
	}
}
