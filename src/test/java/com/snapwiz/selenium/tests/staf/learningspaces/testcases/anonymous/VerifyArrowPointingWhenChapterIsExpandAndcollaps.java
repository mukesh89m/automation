package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;
import org.openqa.selenium.By;

import junit.framework.TestCase;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelecteTextBook;

public class VerifyArrowPointingWhenChapterIsExpandAndcollaps extends Driver{
	public static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.VerifyArrowPointingWhenChapterIsExpandAndcollaps");

	/*
	 * 570-574
	 */
	@Test
	public void ArrowPointing()
	{
		try
		{
			driver.manage().deleteAllCookies();
			new LoginUsingLTI().ltiLogin("570");
			new SelecteTextBook().etextselector();			
			new SelecteTextBook().etextselector();
			Thread.sleep(15000);
		
			
			Thread.sleep(10000);
			String str=driver.findElement(By.cssSelector("div[class='overview']")).getText();
			if(str.contains("Lesson 1: Introducing Office"))
			{
				String colorvalue=driver.findElement(By.cssSelector("div[class='chapter-heading chapter-selected'][title='Lesson 1: Introducing Office']")).getCssValue("background-color");
				if(colorvalue.equals("rgba(3, 128, 183, 1)"))		
				{
					logger.log(Level.INFO,"Selected chapter COLORE change from unselected chapter");
					

					String imgurlselectedchapter=driver.findElement(By.xpath("/html/body/div[3]/div/div/ul[2]/div/div[2]/div/li/div/div")).getCssValue("background-image");
					String imgurlunselectedchapter=driver.findElement(By.xpath("/html/body/div[3]/div/div/ul[2]/div/div[2]/div/li[3]/div/div")).getCssValue("background-image");
					Thread.sleep(5000);
					if(imgurlselectedchapter.equals(imgurlunselectedchapter))
			        {
						logger.log(Level.INFO,"arrow Image are not changing");
						driver.quit();
						TestCase.fail("Arrow images are  not changing");					
					}
					else
					{
						logger.log(Level.INFO,"Arrow images chageed after select the chapter");
						driver.quit();
					}
				}
			}
			else
			{
				logger.log(Level.INFO,"TOC NOT Shown");
				driver.quit();
				TestCase.fail("Toc not shown");
			}
			
			
		}
		catch (Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in LoginUSingLTI Application Helper",e);			 
			  driver.quit();
			  TestCase.fail("Error in LTI Login Or Element not found");
		}
	}
}
