package com.snapwiz.selenium.tests.staf.orion.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.uihelper.LearnMoreReport;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Notification;

public class InstructorHelpPages {
	
	@Test(priority =1 , enabled = true)
	public void helpPages()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("477");
			String notification = new Notification().getNotificationMessage();
			if(notification.equals("") || notification == null)
				Assert.fail("Notification not coming after instructor logged in first time");
			Driver.driver.findElement(By.className("al-reports-know-more")).click();
			Thread.sleep(2000);
			//Dashboard Help Page
			String page = Driver.driver.findElement(By.className("al-helpDialogContentWrapper")).getAttribute("style");
			if(!page.contains("instructor/dashboard.png")) Assert.fail("The dashboard help page not opened");
			//Opening second page of Help
			Driver.driver.findElement(By.id("2")).click();
			Thread.sleep(2000);
			 page = Driver.driver.findElement(By.className("al-helpDialogContentWrapper")).getAttribute("style");
			 System.out.println(page);
				if(!page.contains("instructor/performance-report-chapter.png")) Assert.fail("The performance report by chapter help page not opened");
				/*Driver.driver.findElement(By.id("3")).click();
				Thread.sleep(2000);				
				Driver.driver.findElement(By.id("4")).click();
				Thread.sleep(2000);*/
				Driver.driver.findElement(By.id("5")).click();
				Thread.sleep(2000);
				Driver.driver.findElement(By.id("6")).click();
				Thread.sleep(2000);
				Driver.driver.findElement(By.id("7")).click();
				Thread.sleep(2000);
				Driver.driver.findElement(By.id("8")).click();
				Thread.sleep(2000);
				Driver.driver.findElement(By.id("9")).click();
				Thread.sleep(2000);
				Driver.driver.findElement(By.id("10")).click();
				Thread.sleep(2000);
				Driver.driver.findElement(By.id("11")).click();
				Thread.sleep(2000);
				Driver.driver.findElement(By.id("12")).click();
				Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Close']")).click();
			new Navigator().NavigateToInstructorReport();
			new LearnMoreReport().learnMoreLinkClick("instructor/most-challenging-activity.png");
			new LearnMoreReport().closeLearnMorePage();
			new Navigator().NavigateToReport("Proficiency Report"); //opening Metacognitive Report
			new LearnMoreReport().learnMoreLinkClick("instructor/metacognitive-report-students.png");
			new LearnMoreReport().closeLearnMorePage();
			new Navigator().NavigateToReport("Most Challenging Activities"); //opening Most Challenging Report
			new LearnMoreReport().learnMoreLinkClick("instructor/most-challenging-activity.png");
			new LearnMoreReport().closeLearnMorePage();
			new Navigator().NavigateToReport("Productivity Report"); //opening  Productivity Report
			new LearnMoreReport().learnMoreLinkClick("instructor/productivity-report-students.png");
			new LearnMoreReport().closeLearnMorePage();		
			new Navigator().NavigateToReport("Performance Report"); //opening  Productivity Report
			Driver.driver.findElement(By.className("al-reports-know-more")).click();
			if(!Driver.driver.findElement(By.className("al-helpDialogContentWrapper")).getAttribute("style").contains("instructor/performance-report-chapter.png"))
			{
				new Screenshot().captureScreenshotFromAppHelper();
				Assert.fail("The help page opened is not of the report "+"instructor/performance-report-chapter.png");
			}
			new LearnMoreReport().closeLearnMorePage();		
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase InstructorHelpPages",e);
		}
	}

	@AfterMethod
	public void TearDown()throws Exception
	{
		new Screenshot().captureScreenshotFromTestCase();
		Driver.driver.quit();
	}
}
