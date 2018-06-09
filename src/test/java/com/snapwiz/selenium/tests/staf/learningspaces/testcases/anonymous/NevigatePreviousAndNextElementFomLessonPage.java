package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;


public class NevigatePreviousAndNextElementFomLessonPage extends Driver{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.TOCHide");
	/*
	 * 911-923
	 */
	@Test(priority=1,enabled=true)
	public void validateShortTopicNavigationLinks()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("911");
			new Navigator().NavigateTo("eTextbook");
			String defaulttopictitle = ReadTestData.readDataByTagName("tocdata", "defaulttopictitle", "1");
			new TopicOpen().topicOpen(defaulttopictitle);
			String topictitle = (driver.findElement(By.cssSelector("div[class='tab active']")).getText());
			if(!topictitle.equals(defaulttopictitle)) Assert.fail("The student is not naviagted to the lesson expected with TOC opened");
			//Validating Page 2 link as Next link or not
			Actions action = new Actions(driver);
	        WebElement we = driver.findElement(By.className("ls-lesson-navigation"));
	        action.moveToElement(we).build().perform();
			System.out.println(driver.findElement(By.cssSelector("a[class='ls-next']")).getText());
			if(!driver.findElement(By.cssSelector("a[class='ls-next']")).getText().equals("Page 2"))
				Assert.fail("Link to navigate to Page 2 not available");
			driver.findElement(By.linkText("Page 2")).click();
			
			Thread.sleep(10000);
			
			//Validating Page 1 link as Previous link or not
			action = new Actions(driver);
		     we = driver.findElement(By.className("ls-lesson-navigation"));
		     action.moveToElement(we).build().perform();
			 if(!driver.findElement(By.cssSelector("a[class='ls-prev']")).getText().equals("Page 1"))
				 Assert.fail("Link to navigate to page 1 not found after navigating to page 2 of the topic");
			
			
		//	driver.findElement(By.cssSelector("a[class='ls-next']")).click();
			
			//Opening a short topic and checking if the topic is opened or not
			 new TOCShow().tocShow();
			String topicopened = ReadTestData.readDataByTagName("NevigatePreviousAndNextElementFomLessonPage", "topicopened", "911");
			//driver.findElement(By.cssSelector("a[title='1.3: Science understanding: Time for some changes']")).click();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='"+topicopened+"']")));
			Thread.sleep(10000);
			//new TopicOpen().topicOpen(topicopened);			
			String topictitleopened = (driver.findElement(By.cssSelector("div[class='tab active']")).getText());
			if(!topictitleopened.equals(topicopened)) Assert.fail("The topic opened is not as expected");
			
		/*	 */
			
			//Validating the next and previous chapter links
			int nextpagelink=driver.findElements(By.cssSelector("a[class='ls-next']")).size();
			int previouspagelink=driver.findElements(By.cssSelector("a[class='ls-prev']")).size();
			if(nextpagelink!=1 || previouspagelink!=1)
			{
				Assert.fail("Previous or Next lesson link not available");
			}
			new TOCShow().tocShow();
			new TopicOpen().topicOpen("1.1: Overview");
			
		}
		
		catch (Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in TestCase NevigatePreviousAndNextElementFomLessonPage",e);			 
			  Assert.fail("Exception in TestCase NevigatePreviousAndNextElementFomLessonPage",e);
			  
		}
	}
	@Test(priority=2,enabled=true)
	public void validateLongTopicNavigationLinks()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("911");
			new Navigator().NavigateTo("eTextbook");
			
			//Opening a long topic and checking if the topic is opened or not
			String topicopened = ReadTestData.readDataByTagName("NevigatePreviousAndNextElementFomLessonPage", "longtopic", "911");
			new TopicOpen().topicOpen(topicopened);			
			String topictitleopened = (driver.findElement(By.cssSelector("div[class='tab active']")).getText());
			if(!topictitleopened.equals(topicopened)) Assert.fail("The topic opened is not as expected");
			Thread.sleep(5000);
			new TOCShow().tocHide();
		/*	boolean  nextpagelinkstatus=driver.findElement(By.cssSelector("a[class='ls-next']")).isDisplayed();
			boolean previouspagelinkstatus=driver.findElement(By.cssSelector("a[class='ls-prev']")).isDisplayed();
			if(nextpagelinkstatus==true || previouspagelinkstatus==true)
				Assert.fail("Navigation links present in case of long lesson without mouse overing the footer.");*/
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.className("ls-lesson-navigation"));
			action.moveToElement(we).build().perform();
			int  nextpagelink=driver.findElements(By.cssSelector("a[class='ls-next']")).size();
			int previouspagelink=driver.findElements(By.cssSelector("a[class='ls-prev']")).size();
			if(nextpagelink==1 && previouspagelink==1)
			{			
				driver.findElement(By.cssSelector("a[class='ls-next']")).click();
				Thread.sleep(3000);
				topicopened = ReadTestData.readDataByTagName("NevigatePreviousAndNextElementFomLessonPage", "topicopenedafternextnavigation", "911");		
				topictitleopened = (driver.findElement(By.cssSelector("div[class='tab active']")).getText());
				if(!topictitleopened.equals(topicopened)) Assert.fail("The topic opened is not as expected");
			}
			else
			{
				logger.log(Level.INFO,"Previous and next page link not available");
				Assert.fail("Previous and next page link not available");
			}							
				
			new TOCShow().tocShow();
			new TopicOpen().topicOpen("1.1: Overview");
				
		}
		catch (Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in LoginUSingLTI Application Helper",e);			 
			  Assert.fail("Error in TestCase nevigatepreviousandnextelementfomlessonpageforlongchapter in class NevigatePreviousAndNextElementFomLessonPage");
			  
		}
	}
	
	@Test(priority = 3,enabled = true)
	public void navigateFromFirstSectionOfFirstChapter()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("916");
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().topicOpen("1.1: Overview");
			Actions action = new Actions(driver);
	        WebElement we = driver.findElement(By.className("ls-lesson-navigation"));
	        action.moveToElement(we).build().perform();
	        System.out.println(driver.findElement(By.cssSelector("a[class='ls-prev']")).isDisplayed());
			//driver.findElement(By.cssSelector("a[class='ls-prev']")).click();
			boolean  nextpagelink=driver.findElement(By.cssSelector("a[class='ls-next']")).isDisplayed();
			boolean previouspagelink=driver.findElement(By.cssSelector("a[class='ls-prev']")).isDisplayed();
			if(previouspagelink ==true) Assert.fail("Link to navigate to previous section is available in case of first section of first chapter");
			if(nextpagelink == false ) Assert.fail("Link to navigate to next section is not available");
			
			 
	         we = driver.findElement(By.className("ls-lesson-navigation"));
	        action.moveToElement(we).build().perform();
			
			if(!driver.findElement(By.cssSelector("a[class='ls-next']")).getText().equals("Page 2"))
				Assert.fail("Link to navigate to Page 2 not available");
			Thread.sleep(3000);
			//clicking on page 2
			action = new Actions(driver);
	        we = driver.findElement(By.className("ls-lesson-navigation"));
	        action.moveToElement(we).build().perform();
			driver.findElement(By.linkText("Page 2")).click();
			Thread.sleep(3000);
			
			action = new Actions(driver);
	        we = driver.findElement(By.className("ls-lesson-navigation"));
	        action.moveToElement(we).build().perform();
			  nextpagelink=driver.findElement(By.cssSelector("a[class='ls-next']")).isDisplayed();
			 previouspagelink=driver.findElement(By.cssSelector("a[class='ls-prev']")).isDisplayed();
			 if(previouspagelink == false) Assert.fail("Link to navigate to page 1 is not available after moving to page 2 of the topic");
			 
			 action = new Actions(driver);
		     we = driver.findElement(By.className("ls-lesson-navigation"));
		     action.moveToElement(we).build().perform();
			 if(!driver.findElement(By.cssSelector("a[class='ls-prev']")).getText().equals("Page 1"))
				 Assert.fail("Link to navigate to page 1 not found after navigating to page 2 of the topic");
			 
			 if(nextpagelink == false) Assert.fail("Link to navigate to next section is not available after moving to last section of the chapter");
			 
			 action = new Actions(driver);
		     we = driver.findElement(By.className("ls-lesson-navigation"));
		     action.moveToElement(we).build().perform();
			 driver.findElement(By.cssSelector("a[class='ls-prev']")).click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase navigateFromFirstSectionOfFirstChapter in class NevigatePreviousAndNextElementFomLessonPage",e);
		}
	}


}
