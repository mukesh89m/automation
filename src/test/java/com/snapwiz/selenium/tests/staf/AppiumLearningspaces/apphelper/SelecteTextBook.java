package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.logging.Logger;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;

public class SelecteTextBook {
	public static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.SelecteTextBook");


	public void etextselector()
	{
		try
		{
			 Thread.sleep(5000);
			 String streamvalue=Driver.driver.findElement(By.xpath("/html/body/header/div/div/div/ul/li/a")).getText();
		     if(streamvalue.trim().equals("Course Stream"))
		     {
		    	 try
		    	 {
			     Actions action = new Actions(Driver.driver);
			     WebElement we = Driver.driver.findElement(By.xpath("/html/body/header/div/div/div/ul/li/a"));
			     action.moveToElement(we).build().perform();
			     Driver.driver.findElement(By.cssSelector("a[class='ls--nav-text-book']")).click();
		    	 }
		    	 catch(Exception e)
		    	 {
		    		 Assert.fail("Exception in SelecteTextBook",e);
		    	 }
			
			     WebElement element = (new WebDriverWait(Driver.driver,2)).until(ExpectedConditions.presenceOfElementLocated(By.className("close-help-page")));
			     element.click();
			    	 
		     }
		     else
		     {		    	
		    	 Assert.fail("Failed opening eTextBook page");
		     }
			
		}
		catch(Exception e)
		{
			
			
		}
		
		
	    	 
	     
	}
}
