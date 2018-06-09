package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;

public class CloseHelpPage 
{
	public void closehelppage()
	{
		try
		{
			int helppage = Driver.driver.findElements(By.className("close-help-page")).size(); 
		    if(helppage == 1)
		    	 Driver.driver.findElement(By.className("close-help-page")).click();//close help page if present
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
			int helppage = Driver.driver.findElements(By.className("al-dialog-welcome-close-icon")).size(); 
		    if(helppage == 1)
		    	 Driver.driver.findElement(By.className("al-dialog-welcome-close-icon")).click();//close help page if present
		    Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("exception in closehelppage",e);
		}
	}
	
	

}
