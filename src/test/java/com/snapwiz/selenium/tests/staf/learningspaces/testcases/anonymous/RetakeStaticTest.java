package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AttemptTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandFirstChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelecteTextBook;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class RetakeStaticTest extends Driver
{
	/*
	 * 1293-1297
	 */
	  private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.RetakeStaticTest");
	  @Test
	  public void retakestatictest()
	  {
		  try
		  {
			 driver.manage().deleteAllCookies();
			 new LoginUsingLTI().ltiLogin("1293");
			 new SelecteTextBook().etextselector();
			 new ExpandFirstChapter().expandFirstChapter();
			 new TopicOpen().topicOpen("Static - Chemical change - 1");
			 new AttemptTest().StaticTest();
			 Thread.sleep(5000);
			 driver.findElement(By.className("static-assessment-retake")).click();
			 Thread.sleep(5000);
			
			 boolean timer=driver.findElement(By.id("assessmentTimer")).isDisplayed();
			 boolean optionvalue=driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div/div[2]/div[2]/div/div/label")).isSelected();
			 if(timer==true && optionvalue==false)
			 {
				 logger.log(Level.INFO,"After retake option is selected or not Return on Test ");
				 new AttemptTest().StaticTest();
				 driver.findElement(By.className("show-toc")).click();
				 Thread.sleep(5000);
				 new TopicOpen().topicOpen("Static - Chemical change - 1");
				 Thread.sleep(5000);
				 boolean performancepage=driver.findElement(By.className("al-performance-chart-title")).isDisplayed();
				 if(performancepage==true)
				 {
					 logger.log(Level.INFO,"After click on static test from TOc its shown performance summary page ");
					 driver.findElement(By.className("ls-static-practice-test-continue-button")).click();
					 Thread.sleep(5000);
					 boolean glossaryvalue=driver.findElement(By.className("ls-search-bar")).isDisplayed();
					 if(glossaryvalue==true)
					 {
						 logger.log(Level.INFO,"TOC should get opened after click on continue Button");
					 }
					 else
					 {
						 logger.log(Level.INFO,"TOC should Not get opened after click on continue Button");
						 Assert.fail("TOC should Not get opened after click on continue Button");
					 }
					 
				 }
				 else
				 {
					 
					 logger.log(Level.INFO,"After click on static test from TOc its NOT shown performance summary page ");
					 Assert.fail("After click on static test from TOc its NOT shown performance summary page ");
					 
				 }
			 }
			 else
			 {
				 logger.log(Level.INFO,"After retake option is selected or not Return on Test");
				 Assert.fail("After retake option is selected or not Return on Test");
				 
			 }
				
		  }
		  catch(Exception e)
		    {
					  logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper",e);				 
					  Assert.fail("Exception in LoginUsingLTI Application Helper",e);
					  
			}
		}


}
