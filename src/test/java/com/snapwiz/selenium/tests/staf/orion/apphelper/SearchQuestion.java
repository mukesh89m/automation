package com.snapwiz.selenium.tests.staf.orion.apphelper;

import org.openqa.selenium.By;
import org.testng.Assert;
import com.snapwiz.selenium.tests.staf.orion.Driver;

public class SearchQuestion
{
	public void searchquestion(String question)
	{
		try 
		{
			Driver.driver.findElement(By.id("content-search-icon")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("content-search-field")).click();
			Driver.driver.findElement(By.id("content-search-field")).sendKeys(question);
			Driver.driver.findElement(By.id("search-question-contents-icon")).click();
			Thread.sleep(2000);
			
		} 
		catch (Exception e) {
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in app helper searchquestion ",e);
		}
	}
		
}
