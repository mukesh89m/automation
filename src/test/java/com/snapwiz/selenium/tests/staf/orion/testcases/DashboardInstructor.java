package com.snapwiz.selenium.tests.staf.orion.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.AddDiscussionInQuestions;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.RunScheduledJobs;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Notification;

public class DashboardInstructor {
	
	@Test(priority = 1, enabled = true)
	public void discussionOnDashboard()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("972_student");
			new DiagnosticTest().startTest(0, 2);
			new DiagnosticTest().attemptCorrect(0);
			new DiagnosticTest().quitTestAndGoToDashboard();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();//Start practice test
			new PracticeTest().submitOnlyQuestion();
			new AddDiscussionInQuestions().AddDiscussion("This is a discussion added by user student 972");
			new PracticeTest().quitTestAndGoToDashboard();
			new RunScheduledJobs().runScheduledJobs();
			new LoginUsingLTI().ltiLogin("972_instructor");
			Driver.driver.findElement(By.cssSelector("div[title='Discussions']")).click();
			Thread.sleep(3000);
			if(!Driver.driver.findElement(By.className("instructor-question-discussion-text")).getText().equalsIgnoreCase("Discussions"))
				Assert.fail("Discussion page not opened after clicking on Discussion box in Instructor Dashboard");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase discussionOnDashboard in class DashboardInstructor",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void notificationIfNoDiscussion()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("973");
			Driver.driver.findElement(By.cssSelector("div[title='Discussions']")).click();
			String notification = new Notification().getNotificationMessage();
			if(!notification.contains("No discussions found for class section studtitle973"))
				Assert.fail("Notification message not coming as expected after clicking on discussion box in discussion with no discussions");
				
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase notificationIfNoDiscussion in class DashboardInstructor",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void notificationsGreaterThanOne()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("974_student");
			new DiagnosticTest().startTest(0, 2);
			new DiagnosticTest().attemptCorrect(0);
			new DiagnosticTest().quitTestAndGoToDashboard();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();//Start practice test
			new PracticeTest().submitOnlyQuestion();
			new AddDiscussionInQuestions().AddDiscussion("This is first discussion added by user student 974");
			new AddDiscussionInQuestions().AddDiscussion("This is second discussion added by user student 974");
			new PracticeTest().quitTestAndGoToDashboard();
			new RunScheduledJobs().runScheduledJobs();
			new LoginUsingLTI().ltiLogin("974_instructor");
			if(!Driver.driver.findElement(By.className("instructor-discussion-notification")).getText().equals("2"))
				Assert.fail("The count of New Discussions Since Last 12 hours is not 2");
			Driver.driver.findElement(By.cssSelector("div[title='Discussions']")).click();
			int discussioncount = Driver.driver.findElements(By.id("instructor-discussion-content-block")).size();
			if(discussioncount != 2) Assert.fail("No. of discussion displayed not equal to 2 in discussion page");
				
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase notificationIfNoDiscussion in class DashboardInstructor",e);
		}
	}
	

	
	@AfterMethod
	public void tearDown() throws Exception
	{
		//new Screenshot().captureScreenshotFromTestCase();
		Driver.driver.quit();
	}
}
