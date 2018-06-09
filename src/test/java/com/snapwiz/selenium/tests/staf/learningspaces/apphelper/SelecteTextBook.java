package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.logging.Logger;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class SelecteTextBook extends Driver{
	public static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.SelecteTextBook");


	public void etextselector()
	{
		try
		{
			 Thread.sleep(5000);
			WebDriver driver=Driver.getWebDriver();
			 String streamvalue=driver.findElement(By.xpath("/html/body/header/div/div/div/ul/li/a")).getText();
		     if(streamvalue.trim().equals("Course Stream"))
		     {
		    	 try
		    	 {
			     Actions action = new Actions(driver);
			     WebElement we = driver.findElement(By.xpath("/html/body/header/div/div/div/ul/li/a"));
			     action.moveToElement(we).build().perform();
			     driver.findElement(By.cssSelector("a[class='ls--nav-text-book']")).click();
		    	 }
		    	 catch(Exception e)
		    	 {
		    		 Assert.fail("Exception in SelecteTextBook",e);
		    	 }
			
			     WebElement element = (new WebDriverWait(driver,2)).until(ExpectedConditions.presenceOfElementLocated(By.className("close-help-page")));
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
