package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiscussionValidate;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.RunScheduledJobs;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class DiscussionStartedAndRepliedShowUpInMyActivityPage
{
	@Test
	public void discussionStartedAndRepliedShowUpInMyActivityPage()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("436");
			new SelectChapterForTest().selectchapterfortest(0, 2);
			new DiagnosticTest().attemptAllCorrect(2, false, false);
			new Navigator().orionDashboard();
			Thread.sleep(2000);
			new PracticeTest().startTest();//Start practice test
			new PracticeTest().practiceTestAttempt(2, 7, "correct", false, false);
			new Navigator().NavigateToStudentReport();
			Driver.driver.findElement(By.id("al-performance-report")).click();//click on performance report link
			Thread.sleep(2000);
			List<WebElement> questioncard=Driver.driver.findElements(By.cssSelector("div[class='report-sidebar-question-card-sectn question-card-green']"));//ftech question card
			questioncard.get(0).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("al-question-discussion-icon-section")).click();//click on discussion icon
			Driver.driver.findElement(By.className("al-question-discussion-input-section")).click();//click on discussion text entry area
			Driver.driver.findElement(By.className("al-question-discussion-input-section")).sendKeys("this is discussion 1"+Keys.ENTER);
			boolean discussionadded=Driver.driver.findElement(By.id("al-user-discussion-question-content")).isDisplayed();//check discussion added or not
			if(discussionadded==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("discussion not shown after add");
			}
			new Navigator().NavigateToStudentAllActivity();
			List<WebElement> addeddiscussion=Driver.driver.findElements(By.className("my-journal-activity-event-action"));//click on added notes
			String discussion=addeddiscussion.get(0).getText();
			if(!discussion.equals("Started a Discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'add a discussion ' not shownin my activity page");
			}
			addeddiscussion.get(0).click();
			boolean discussionadded1=Driver.driver.findElement(By.id("al-user-discussion-question-content")).isDisplayed();//check discussion added or not
			if(discussionadded1==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("discussion1 not shown after add");
			}
			new Navigator().NavigateToStudentAllActivity();
			Driver.driver.findElement(By.id("my-journal-tab-button")).click();//click on Journel tab
			Thread.sleep(2000);	
			List<WebElement> addedDisjonral=Driver.driver.findElements(By.className("my-journal-activity-event-action"));//click on added notes
			addedDisjonral.get(0).click();
			Thread.sleep(2000);
			boolean discussionadded2=Driver.driver.findElement(By.id("al-user-discussion-question-content")).isDisplayed();//check discussion added or not
			if(discussionadded2==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("discussion2 not shown after add");
			}
			Driver.driver.findElement(By.id("al-user-discussion-question-replay")).click();
			Driver.driver.findElement(By.className("al-discussion-reply-text-section")).click();
			Driver.driver.findElement(By.className("al-discussion-reply-text-section")).sendKeys("this is reply1"+Keys.ENTER);
			new Navigator().NavigateToStudentAllActivity();
			List<WebElement> replyddiscussion=Driver.driver.findElements(By.className("my-journal-activity-event-action"));//click on reply discussion
			String discussion1=replyddiscussion.get(0).getText();
			if(!discussion1.equals("Replied to a Discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'Replied to a Discussion ' not shownin my activity page");
			}
			replyddiscussion.get(0).click();
			boolean replytext=Driver.driver.findElement(By.id("al-user-discussion-answer-content")).isDisplayed();//check discussion added or not
			if(replytext==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("replytext not shown after add");
			}
			new Navigator().NavigateToStudentAllActivity();
			Driver.driver.findElement(By.id("my-journal-tab-button")).click();//click on Journel tab
			Thread.sleep(2000);	
			List<WebElement> replyddiscussion1=Driver.driver.findElements(By.className("my-journal-activity-event-action"));//click on reply discussion
			replyddiscussion1.get(0).click();
			boolean replytext1=Driver.driver.findElement(By.id("al-user-discussion-answer-content")).isDisplayed();//check discussion added or not
			if(replytext1==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("replytext1 not shown after add");
			}
			new RunScheduledJobs().runScheduledJobs();
			new LoginUsingLTI().ltiLogin("4361"); //logging in same student in a different class and section
			new Navigator().NavigateToStudentAllActivity();
			new DiscussionValidate().discussionvalidate();
			new Navigator().NavigateToStudentAllActivity();
			Driver.driver.findElement(By.id("my-journal-tab-button")).click();//click on Journel tab
			Thread.sleep(2000);
			new DiscussionValidate().discussionvalidate();//verify discussion added with reply
			new Navigator().NavigateToStudentReport();
			Driver.driver.findElement(By.linkText("Performance Report")).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.linkText("Productivity Report")).click();
			new DiscussionValidate().validateperformanceProductivitychallenge();//validate performance,productivity,challenging
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase discussionStartedAndRepliedShowUpInMyActivityPage in class DiscussionStartedAndRepliedShowUpInMyActivityPage",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
	
	
}
