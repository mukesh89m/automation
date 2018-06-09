package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class CloseHelpPage extends Driver
{
	public void closehelppage()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			int helppage = driver.findElements(By.className("close-help-page")).size();
		    if(helppage == 1)
		    	 driver.findElement(By.className("close-help-page")).click();//close help page if present
		    Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("exception in closehelppage",e);
		}
	}
	
	public void closehelppageOrion()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			int helppage = driver.findElements(By.className("al-dialog-welcome-close-icon")).size();
		    if(helppage == 1)
		    	 driver.findElement(By.className("al-dialog-welcome-close-icon")).click();//close help page if present
		    Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("exception in closehelppage",e);
		}
	}
	
	

}
