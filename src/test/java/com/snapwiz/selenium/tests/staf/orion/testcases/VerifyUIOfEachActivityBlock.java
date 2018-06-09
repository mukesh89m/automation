package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.AddDiscussionInQuestions;
import com.snapwiz.selenium.tests.staf.orion.apphelper.AddNotesInQuestions;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class VerifyUIOfEachActivityBlock {
	@Test(priority = 1, enabled = false)
	public void verifyUIForDiscussionPage()
	{
		try
		{
			Driver.startDriver();	
			String chapter1= ReadTestData.readDataByTagName("chapternames", "chapter1", "0");
			new LoginUsingLTI().ltiLogin("1714"); //  Logging in as student to orion
			new DiagnosticTest().startTest(0,3);//select 1st chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(4, 5, "correct", false, false, true);
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
			List<WebElement> allDiscussion = Driver.driver.findElements(By.className("my-journal-activity-event-action"));
			if(!allDiscussion.get(0).getText().equals("Started a Discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The discussion post doesn't have a header saying 'Started a discussion'.");
			}
			List<WebElement> allTimings = Driver.driver.findElements(By.className("my-journal-activity-event-date"));
			if(allTimings.get(0).getText() == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The discussion post doesn't have the time of posting the discussion.");
			}
			List<WebElement> allChapters = Driver.driver.findElements(By.className("my-journal-activity-event-title"));
			if(!allChapters.get(0).getText().equals(chapter1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Chapter name is absent in the discussion posted.");
			}
			//check for question
			String question = Driver.driver.findElement(By.className("question-display-label")).getText();
			//if(!questionText.trim().contains(question))
			if(question == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The question for which the discussion has been posted is absent in the timline.");
			}
			//check for discussion topic
			String discussionTopic = Driver.driver.findElement(By.cssSelector("p[class='my-journal-media-body activity-text']")).getText();
			if(!discussionTopic.equals("Q: This is a discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The discussion topic is absent in the timline.");
			}
			//check for profile image
			List<WebElement> allimg = Driver.driver.findElements(By.className("my-journal-media-img"));
			if(!allimg.get(1).getAttribute("src").contains("default-profile-image.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The default profile pics is absent near the discussion topic.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyUIForDiscussionPage in class VerifyUIOfEachActivityBlock ",e);
		}
	}
	@Test(priority = 2, enabled = false)
	public void verifyUIForNote()
	{
		try
		{
			Driver.startDriver();	
			String chapter1= ReadTestData.readDataByTagName("chapternames", "chapter1", "0");
			new LoginUsingLTI().ltiLogin("1717"); //  Logging in as student to orion
			new DiagnosticTest().startTest(0,3);//select 1st chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(4, 5, "correct", false, false, true);
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
			if(!allNotes.get(0).getText().equals("Posted a Note"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The Note post doesn't have a header saying 'Posted a Note'.");
			}
			List<WebElement> allTimings = Driver.driver.findElements(By.className("my-journal-activity-event-date"));
			if(allTimings.get(0).getText() == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The Note post doesn't have the time of posting the note.");
			}
			List<WebElement> allChapters = Driver.driver.findElements(By.className("my-journal-activity-event-title"));
			if(!allChapters.get(0).getText().equals(chapter1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Chapter name is absent in the note posted.");
			}
			//check for question
			String question = Driver.driver.findElement(By.className("question-display-label")).getText();
			if(question == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The question for which the note has been posted is absent in the timline.");
			}
			//check for note
			String note = Driver.driver.findElement(By.cssSelector("p[class='my-journal-media-body activity-text']")).getText();
			if(!note.equals("This is a note."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The note is absent in the timline.");
			}
			//check for profile image
			List<WebElement> allimg = Driver.driver.findElements(By.className("my-journal-media-img"));
			if(!allimg.get(1).getAttribute("src").contains("default-profile-image.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The default profile pics is absent near the discussion topic.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyUIForNote in class VerifyUIOfEachActivityBlock ",e);
		}
	}
	@Test(priority = 3, enabled = true)
	public void verifyUIOnCompletionOfPracticeTest()
	{
		try
		{
			Driver.startDriver();	
			String chapter1= ReadTestData.readDataByTagName("chapternames", "chapter1", "0");
			new LoginUsingLTI().ltiLogin("1718"); //  Logging in as student to orion
			new DiagnosticTest().startTest(0,3);//select 1st chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(4, 5, "correct", false, false, true);
			//come to dashboard
			new Navigator().orionDashboard();
			//take a practice test
			new PracticeTest().startTest();
			for(int i = 0; i< 4; i++)
				new PracticeTest().attemptQuestion("correct", 4, false, false);
			new PracticeTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentAllActivity();
			List<WebElement> allHeaders = Driver.driver.findElements(By.className("my-journal-activity-event-action"));
			if(!allHeaders.get(0).getText().equals("Completed a Practice Session"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After finishing the practice test it doesn't have a header saying 'Completed a Practice Session'.");
			}
			List<WebElement> allTimings = Driver.driver.findElements(By.className("my-journal-activity-event-date"));
			if(allTimings.get(0).getText() == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After finishing the practice test it doesn't have the time of finishing the note.");
			}
			List<WebElement> allChapters = Driver.driver.findElements(By.className("my-journal-activity-event-title"));
			if(!allChapters.get(0).getText().equals(chapter1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Chapter name is absent for which the practice test has been concluded.");
			}
			//check for profile image
			List<WebElement> allimg = Driver.driver.findElements(By.className("my-journal-media-img"));
			if(!allimg.get(1).getAttribute("src").contains("default-profile-image.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The default profile pics is absent near the discussion topic.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyUIOnCompletionOfPracticeTest in class VerifyUIOfEachActivityBlock ",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
