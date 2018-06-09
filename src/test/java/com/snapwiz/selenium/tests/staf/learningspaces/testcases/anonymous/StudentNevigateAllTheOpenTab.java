package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandFirstChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelecteTextBook;

public class StudentNevigateAllTheOpenTab extends Driver
{
	public static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.StudentNevigateAllTheOpenTab");
	/*
	 * 1039-1049
	 */
	@Test
	public void nevigatealltab()
	{
		try
		{
			driver.manage().deleteAllCookies();
			new LoginUsingLTI().ltiLogin("1062");
			new SelecteTextBook().etextselector();
			new ExpandFirstChapter().expandFirstChapter();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("body")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("span[class='show-toc']")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("div[class='tab'][data-id='resources']")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("a[title='res do']")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("a[class='ls_assessment_link']")).click();
			Thread.sleep(5000);
			
			driver.findElement(By.cssSelector("body")).click();
			Thread.sleep(5000);
			
			driver.findElement(By.cssSelector("span[class='show-toc']")).click();
			Thread.sleep(5000);
			
			Actions action = new Actions(driver);
	        WebElement we = driver.findElement(By.xpath("//*[@id='lesson-NDY4Nzc0MzMz']"));
	        action.moveToElement(we).build().perform();
	        if(!driver.findElement(By.className("hiddentabs")).isDisplayed())
	        {
	        
	        	driver.findElement(By.xpath("/html/body/div[3]/div/div/ul[2]/div/div[2]/div/li/div/div[2]/ul[3]/li/div/div")).click();
	        	Thread.sleep(5000);
	        	driver.findElement(By.cssSelector("span[class='show-toc']")).click();
	        	Thread.sleep(5000);
	        	Actions action1 = new Actions(driver);
	        	WebElement we1 = driver.findElement(By.xpath("//*[@id='lesson-NDY5NjkxODM3']"));
	        	action1.moveToElement(we1).build().perform();
	        	driver.findElement(By.xpath("/html/body/div[3]/div/div/ul[2]/div/div[2]/div/li/div/div[2]/ul[2]/li/div/div")).click();
	        	
	        	driver.findElement(By.cssSelector("span[class='show-toc']")).click();
				Thread.sleep(5000);
	        	Actions action2 = new Actions(driver);
	        	WebElement we2 = driver.findElement(By.xpath("//*[@id='lesson-NDY5MTY3NTQ5']"));
	        	action2.moveToElement(we2).build().perform();
	        	driver.findElement(By.xpath("/html/body/div[3]/div/div/ul[2]/div/div[2]/div/li/div/div[2]/ul[5]/li/div/div")).click();
	        	boolean tabvalue=driver.findElement(By.className("hiddentabs")).isDisplayed();
	        	if(tabvalue==true)
	        	{
	        		driver.findElement(By.className("hiddentabs")).click();
	        		Thread.sleep(5000);
	        		boolean extratabvalue=driver.findElement(By.cssSelector("div[class='hiddentabs dropdownActive']")).isDisplayed();
	        		Thread.sleep(5000);
	        		boolean extratabvalue1=driver.findElement(By.cssSelector("div[class='hiddentabs dropdownActive']")).isEnabled();
	        		
	        		boolean dropdown=driver.findElement(By.cssSelector("div[class='hidden-tab-data']")).isDisplayed();
	        		if(extratabvalue==true && dropdown==true && extratabvalue1==true)
	        		{
	        			logger.log(Level.INFO,"When click on extra tab then its expand with dropdown");
	        			driver.findElement(By.cssSelector("div[class='hidden-tab-data']")).click();
	        			boolean dropdown1=driver.findElement(By.cssSelector("div[class='hidden-tab-data']")).isDisplayed();
	        			
	        			if(dropdown1==false)
	        			{
	        				logger.log(Level.INFO,"after selecting from drop down value droplist not shown");
	        				driver.findElement(By.className("hiddentabs")).click();
	    	        		Thread.sleep(5000);
	    	        		boolean dropdown2=driver.findElement(By.cssSelector("div[class='hidden-tab-data']")).isDisplayed();
	    	        		Thread.sleep(5000);
	    	        		driver.findElement(By.cssSelector("div[class='hiddentabs dropdownActive']")).click();
	    	        		Thread.sleep(5000);
	    	        		boolean dropdown3=driver.findElement(By.cssSelector("div[class='hidden-tab-data']")).isDisplayed();
	    	        		Thread.sleep(5000);
	    	        		if(dropdown2==true && dropdown3==false)
	    	        		{
	    	        			logger.log(Level.INFO,"Afetr double click on extra tab epand drop down is hidden");
	    	        		}
	    	        		else
	    	        		{
	    	        			logger.log(Level.INFO,"Afetr double click on extra tab epand drop down is Not hidden");
	    	        			Assert.fail("Afetr double click on extra tab epand drop down is Not hidden");
	    	        		}
	        				
	        			}
	        			else
	        			{
	        				logger.log(Level.INFO,"after selecting from drop down value droplist still shown");
		        			Assert.fail("after selecting from drop down value droplist still shown");
	        			}
	        			
	        		}
	        		else
	        		{
	        			logger.log(Level.INFO,"When click on extra tab then its not expand with dropdown");
	        			Assert.fail("When click on extra tab then its not expand with dropdown");
	        		}
	        		
	        	}
	        	else
	        	{
	        		logger.log(Level.INFO,"The arrow icon to view additional tabs should Not appear. ");
	        		Assert.fail("The arrow icon to view additional tabs should Not appear.");
	        	}
	        }
	        else
	        {
	        	logger.log(Level.INFO,"The arrow icon to view additional tabs should appear.");
	        	Assert.fail("The arrow icon to view additional tabs should appear.");
	        }
			
			
		}
		catch(Exception e)
		   {
				  logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper",e);
				  driver.quit();
				  Assert.fail();
				  
		   }
	}


}
