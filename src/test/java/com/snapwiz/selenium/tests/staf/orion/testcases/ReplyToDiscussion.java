package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.AddDiscussionInQuestions;
import com.snapwiz.selenium.tests.staf.orion.apphelper.AddNotesInQuestions;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class ReplyToDiscussion {
	@Test
	public void replyToDiscussion()
	{
		try
		{
			Driver.startDriver();
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			new Assignment().createChapter(1723, Integer.parseInt(tloids.get(0)));
			//create diag test
			new Assignment().createPracticeAtChapterLevel(1723, "Adaptive Component Diagnostic", true, false, true, Integer.parseInt(tloids.get(0)));
			//create adaptive test
			new Assignment().createPracticeAtChapterLevel(1723, "Adaptive Component Practice", true, false, true, Integer.parseInt(tloids.get(0)));
			new LoginUsingLTI().ltiLogin("1723");
			new DiagnosticTest().openLastDiagnosticTest();
			new DiagnosticTest().attemptAllCorrect(4, false, false)	;
			new Navigator().orionDashboard();
			new PracticeTest().openLastPracticeTest();
			//select answer
			Driver.driver.findElement(By.className("qtn-label")).click();
			Thread.sleep(2000);
			//click submit
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		    Thread.sleep(2000);
		    //click submit
		    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		    Thread.sleep(2000);
		    new AddDiscussionInQuestions().AddDiscussion("Discussion Text.");
			Driver.driver.findElement(By.cssSelector("span[id='al-user-discussion-question-replay']")).click();
			Thread.sleep(2000);
			Driver.driver.switchTo().activeElement().sendKeys("This is a reply to a discussion.");//reply on discussion
			Actions ac=new Actions(Driver.driver);
			ac.sendKeys(Keys.RETURN).build().perform();
			Thread.sleep(2000);
			new Navigator().NavigateToStudentAllActivity();
			//list all headers
			List<WebElement> allHeaders = Driver.driver.findElements(By.className("my-journal-activity-event-action"));
			if(!allHeaders.get(0).getText().equals("Replied to a Discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After replying a to a discussion, in all activity there is no header saying 'Replied to a Discussion'.");
			}
			new Navigator().orionDashboard();
			new PracticeTest().openLastPracticeTest();
			//select answer
			Driver.driver.findElement(By.className("qtn-label")).click();
			//click submit
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		    Thread.sleep(2000);
		    //click submit
		    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		    Thread.sleep(2000);
		    //click on discussion icon
		    Driver.driver.findElement(By.className("al-question-notes-discussion-text")).click();
			Thread.sleep(1000);
		    //reply multiple times
		    for(int i = 0; i < 2; i++)
		    {
		    Driver.driver.findElement(By.cssSelector("span[id='al-user-discussion-question-replay']")).click();
			Thread.sleep(2000);
			Driver.driver.switchTo().activeElement().sendKeys("This is a reply to a discussion.");//reply on discussion
			ac.sendKeys(Keys.RETURN).build().perform();
			Thread.sleep(2000);
		    }
		    new Navigator().NavigateToStudentAllActivity();
		    //list all headers
			List<WebElement> allHeaders1 = Driver.driver.findElements(By.className("my-journal-activity-event-action"));
			if(!allHeaders1.get(0).getText().equals("Replied to a Discussion") || !allHeaders1.get(1).getText().equals("Replied to a Discussion") || !allHeaders1.get(3).getText().equals("Replied to a Discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After replying a to a discussion mutiple times, in all activity are no multiple header saying 'Replied to a Discussion'.");
			}
			
			//click on discussion in All ACtivity
			List<WebElement> allDiscussions = Driver.driver.findElements(By.className("activity-parent-text"));
			allDiscussions.get(1).click();
			Thread.sleep(2000);
			String question = Driver.driver.findElement(By.className("al-diag-test-question-raw-content")).getText();
			if(question == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking the discussion from the 'All Activity' page it doesn't go to that question.");
			}
			new Navigator().NavigateToStudentAllActivity();
			//click on reply of the discussion element in All ACtivity
			List<WebElement> allReply = Driver.driver.findElements(By.cssSelector("p[class='my-journal-media-body activity-text']"));
			allReply.get(1).click();
			Thread.sleep(2000);
			String question1 = Driver.driver.findElement(By.className("al-diag-test-question-raw-content")).getText();
			if(question1 == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking the reply to a particular discussion from the 'All Activity' page it doesn't go to that question.");
			}
			
			//add note to the question
			new AddNotesInQuestions().addnotes("This is a note.");
			new Navigator().NavigateToStudentAllActivity();
			List<WebElement> allHeaders2 = Driver.driver.findElements(By.className("my-journal-activity-event-action"));
			allHeaders2.get(0).click();
			Thread.sleep(2000);
			String question2 = Driver.driver.findElement(By.className("al-diag-test-question-raw-content")).getText();
			if(question2 == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking the note element from the 'All Activity' page it doesn't go to that question for which note was added.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase replyToDiscussion in class ReplyToDiscussion ",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
