package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.AddDiscussionInQuestions;
import com.snapwiz.selenium.tests.staf.orion.apphelper.AddNotesInQuestions;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class StudentAbleToViewAllActivity {
	@Test
	public void studentAbleToViewAllActivity()
	{
		try
		{
			Driver.startDriver();	
			new LoginUsingLTI().ltiLogin("1677"); //  Logging in as student to orion
			new Navigator().NavigateToStudentAllActivity();
			String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
			if(!notice.contains("No Activity Found."))
			{
				
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("On clickin all activity Doesn't display alert message that no actvities are completed by the student for a fresh student.");
			}
			new DiagnosticTest().startTest(1,3);//select 1st chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(4, 5, "correct", false, false, true);
			new Navigator().NavigateToStudentAllActivity();
			String allActivity = Driver.driver.findElement(By.cssSelector("h1[class='my-journal-heading-title title-active']")).getText();
			if(!allActivity.equals("All Activity"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("It does not land on all activity page when we click on all activity.");
			}
			if(!allActivity.equals("All Activity"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The heading does not say 'All Activity' for all activity page.");
			}
			
			//verify the UI 
			List<WebElement> allTabs = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'my-journal-button my-journal-button-primary my-journal-tab-')]"));
			if(!allTabs.get(0).getText().equals("All Activity"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("All Activity tab is absent in all activity page.");
			}
			if(!allTabs.get(1).getText().equals("My Journal"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("My Journal tab is absent in all activity page.");
			}
			//list all filters
			List<WebElement> allFilters = Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
			if(!allFilters.get(0).getText().equals("All Chapters"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("All Chapters filter is absent in all activity page.");
			}
			if(!allFilters.get(1).getText().equals("All Objectives"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("All Objectives filter is absent in all activity page.");
			}
			if(!allFilters.get(2).getText().equals("All Activity Types"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("All Activity Types filter is absent in all activity page.");
			}
			
			String nowLabel = Driver.driver.findElement(By.cssSelector("div[class='my-journal-timeline-separator my-journal-timeline-separator--now']")).getText();
			if(!nowLabel.equals("Now"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Now label is absent in all activity page");
			}
			
			//click on my journal tab
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("my-journal-tab-button")));
			Thread.sleep(3000);
			String myJournal = Driver.driver.findElement(By.cssSelector("h1[class='my-journal-heading-title title-inactive']")).getText();
			if(!myJournal.equals("My Journal"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking My Journal tab the My journal doesn't open.");
			}
			//go to all activity tab
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("my-activity-tab-button")));
			Thread.sleep(3000);
			//click on star icon
			Driver.driver.findElement(By.cssSelector("i.my-journal-icon-background.my-journal-star-icon")).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("i.my-journal-icon-background.my-journal-star-icon")).click();
			Thread.sleep(3000);
			//come to dashboard
			new Navigator().orionDashboard();
			//take a practice test
			new PracticeTest().startTest();
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
			Thread.sleep(2000);
			new AddDiscussionInQuestions().AddDiscussion("This is a discussion");
			new Navigator().NavigateToStudentAllActivity();
			//click on my journal tab
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("my-journal-tab-button")));
			Thread.sleep(3000);
			List<WebElement> allDiscussion = Driver.driver.findElements(By.className("my-journal-activity-event-action"));
			if(!allDiscussion.get(1).getText().equals("Started a Discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The discussion posted by the student is not shown in my Journal tab of 'all activity'.");
			}
			//go to all activity tab
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("my-activity-tab-button")));
			Thread.sleep(3000);
			List<WebElement> allDiscussion1 = Driver.driver.findElements(By.className("my-journal-activity-event-action"));
			if(!allDiscussion1.get(1).getText().equals("Started a Discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The discussion posted by the student is not shown inall activity tab of 'all activity'.");
			}
			//come to dashboard
			new Navigator().orionDashboard();
			//take a practice test
			new PracticeTest().startTest();
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
			Thread.sleep(2000);
			new AddNotesInQuestions().addnotes("This is a note.");
			new Navigator().NavigateToStudentAllActivity();
			List<WebElement> allNotes = Driver.driver.findElements(By.className("my-journal-activity-event-action"));
			String note = allNotes.get(0).getText();
			if(!note.equals("Posted a Note"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The discussion posted by the student is not shown in my Journal tab of 'all activity'.");
			}
			//click on my journal tab
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("my-journal-tab-button")));
			Thread.sleep(3000);
			List<WebElement> allNotes1 = Driver.driver.findElements(By.className("my-journal-activity-event-action"));
			String note1 = allNotes1.get(1).getText();
			if(!note1.equals("Posted a Note"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The discussion posted by the student is not shown in my Journal tab of 'all activity'.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase studentAbleToViewAllActivity in class StudentAbleToViewAllActivity ",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
