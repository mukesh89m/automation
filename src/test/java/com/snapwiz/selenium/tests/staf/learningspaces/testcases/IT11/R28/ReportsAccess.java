package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R28;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;

public class ReportsAccess extends Driver {

	@Test(priority = 1, enabled=true)
	public void reportsAccessLSandAdaptive()
	{
		try
		{	
			new LoginUsingLTI().ltiLogin("2");
			
			new Navigator().NavigateTo("Proficiency Report");	
			Thread.sleep(1000);

			String text = driver.findElement(By.className("no-data-notification-message-block")).getText();
			System.out.println("Output"+ text);
			if(!text.contains("Unfortunately, a Proficiency Report could not be generated for you at this time since there is not enough data for the system to analyze. Please continue progressing in your course and check back at a later time."))
				Assert.fail("No Message is found-1");
			
			
			new Navigator().NavigateTo("Metacognitive Report");
			Thread.sleep(1000);
			
			String text1 = driver.findElement(By.className("no-data-notification-message-block")).getText();
			System.out.println("Output"+ text1);
			if(!text1.contains("Unfortunately, a Metacongnitive Report could not be generated for you at this time since there is not enough data for the system to analyze. Please continue progressing in your course and check back at a later time."))
				Assert.fail("No Message is found-2");
			
			new Navigator().NavigateTo("Productivity Report");
			Thread.sleep(1000);
			
			String text2 = driver.findElement(By.className("no-data-notification-message-block")).getText();
			System.out.println("Output"+ text2);
			if(!text2.contains("Unfortunately, a Productivity Report could not be generated for you at this time since there is not enough data for the system to analyze. Please continue progressing in your course and check back at a later time."))
				Assert.fail("No Message is found-3");
			
			new Navigator().NavigateTo("Most Challenging Activities Report");	
			
			String text3 = driver.findElement(By.className("no-data-notification-message-block")).getText();
		
			if(!text3.contains("Unfortunately, a Most Challenging Activities Report could not be generated for you at this time since there is not enough data for the system to analyze. Please continue progressing in your course and check back at a later time."))
				Assert.fail("No Message is found-4");
			
			driver.findElement(By.id("ls-most-challenging-activities-tlo")).click();
	}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase reportsAccessLSandAdaptive in class ReportsAccess",e);		}
	}
	
	@Test(priority = 2, enabled=true)
	public void reportsAccessNonAdaptive()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("5");
			new Navigator().NavigateTo("Performance Report");
			String text = driver.findElement(By.className("no-data-notification-message-block")).getText();

			if(!text.contains("Unfortunately, a Performance Report could not be generated for you at this time since there is not enough data for the system to analyze. Please continue progressing in your course and check back at a later time."))
				Assert.fail("In performance report page the appropriate message is not there.");
			
			new Navigator().NavigateTo("Most Challenging Chapters Report");	
			
			String text3 = driver.findElement(By.className("no-data-notification-message-block")).getText();
		
			if(!text3.contains("Unfortunately, a Most Challenging Chapters Report could not be generated for you at this time since there is not enough data for the system to analyze. Please continue progressing in your course and check back at a later time."))
                Assert.fail("In Most Challenging Chapters Report page the appropriate message is not there.");
			driver.findElement(By.id("ls-most-challenging-activities-tlo")).click();
		}
		catch (Exception e)
		{
	    	new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase reportsAccessNonAdaptive in class ReportsAccess",e);
		}
	}

}
