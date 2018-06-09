package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;

public class LessonPageScroll extends Driver {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.TOCHide");
	/*
	 * 907-909
	 */
	@Test
	public void lessonpagescroll()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("907");
			new Navigator().NavigateTo("eTextbook");
			String topictitle = (driver.findElement(By.cssSelector("div[class='tab active']")).getText());
			String defaulttopictitle = ReadTestData.readDataByTagName("tocdata", "defaulttopictitle", "1");
			if(!topictitle.equals(defaulttopictitle)) Assert.fail("The student is not naviagted to the lesson expected with TOC opened");
			new TOCShow().tocHide();
			//boolean textvisible = driver.findElement(By.id("widget-content-widget-13585-16")).isDisplayed();
			//System.out.println(textvisible);
			//Validating the stream tab before scrolling the page down
			int streamtab = driver.findElements(By.cssSelector("span[title='Stream']")).size();
			if(streamtab != 1)
				Assert.fail("Stream Tab not present in eTextBook");
			driver.findElement(By.cssSelector("span[title='Stream']")).click();
			JavascriptExecutor jsx = (JavascriptExecutor)driver;
			jsx.executeScript("window.scrollBy(0,600)", "");
			
			if(!driver.findElement(By.cssSelector("span[title='Stream']")).isDisplayed())
				Assert.fail("Stream tab not displayed after the page is scrolled down");				
			driver.findElement(By.cssSelector("span[title='Stream']")).click();
				/*
				boolean streamdisplay=driver.findElement(By.cssSelector("span[title='Stream']")).isDisplayed();
				boolean resourcesdispaly=driver.findElement(By.cssSelector("span[title='Resources']")).isDisplayed();
				*/			
			
		}
		catch (Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in TestCase LessonPageScroll",e);			 
			  Assert.fail("Exception in TestCase LessonPageScroll",e);
			  
		}
	}

}
