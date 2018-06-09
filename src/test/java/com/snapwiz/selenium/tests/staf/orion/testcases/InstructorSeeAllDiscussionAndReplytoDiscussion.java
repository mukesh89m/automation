package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.AddDiscussionInQuestions;
import com.snapwiz.selenium.tests.staf.orion.apphelper.ClickOnquestionCard;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.orion.apphelper.RunScheduledJobs;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Click;
import com.snapwiz.selenium.tests.staf.orion.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.orion.uihelper.PerformanceReport;

public class InstructorSeeAllDiscussionAndReplytoDiscussion
{
	@Test
	public void instructorSeeAllDiscussionAndReplytoDiscussion()
	{
		try
		{
			Driver.startDriver();
			String text1=new RandomString().randomstring(3);
			String text2=new RandomString().randomstring(3);
			String text3=new RandomString().randomstring(3);
			String text4=new RandomString().randomstring(3);
			new LoginUsingLTI().ltiLogin("980");//login as student
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 3, "correct", false, false, true);//attempt diagnostic test
			Thread.sleep(2000);
			new ClickOnquestionCard().clickOnQuestion(0);//click on question card
			new AddDiscussionInQuestions().AddDiscussion(text1);//add discussion in question which last attempted
			new RunScheduledJobs().runScheduledJobs();
			new LoginUsingLTI().ltiLogin("9801");//login as instructor
			new Click().clickByid("instructor-discussion-content-details");//click on discussion box
			Driver.driver.findElement(By.cssSelector("span[title='Reply']")).click();
			Thread.sleep(2000);
			Actions ac=new Actions(Driver.driver);
			Driver.driver.findElement(By.className("instructor-discussion-reply-text-section")).clear();
			Driver.driver.findElement(By.className("instructor-discussion-reply-text-section")).sendKeys("this is reply 1"+Keys.ENTER);
			ac.sendKeys(Keys.RETURN).build().perform();
			Thread.sleep(2000);
			boolean replyposted=Driver.driver.findElement(By.id("instructor-user-discussion-answer-content")).isDisplayed();
			if(replyposted==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("reply on discussion not posted");
			}
			new LoginUsingLTI().ltiLogin("980");
			new Navigator().NavigateToStudentReport();
			new PerformanceReport().performancereport();
			Thread.sleep(2000);
			new ClickOnquestionCard().clickOnQuestion(0);//click on question card
			new Click().clickByclassname("al-question-notes-discussion-text");
			Thread.sleep(2000);
			boolean replypresentforstudent=Driver.driver.findElement(By.id("al-user-discussion-answer-content")).isDisplayed();
			System.out.println(replypresentforstudent);
			new AddDiscussionInQuestions().AddDiscussion(text2);//
			new AddDiscussionInQuestions().AddDiscussion(text3);
			new AddDiscussionInQuestions().AddDiscussion(text4);
			new LoginUsingLTI().ltiLogin("9801");//login as instructor
			new Click().clickByid("instructor-discussion-content-details");//click on discussion box
			List<WebElement> allDiscussion=Driver.driver.findElements(By.id("instructor-discussion-content-text"));
			String distextattop=allDiscussion.get(0).getText();//fetch string at top
			String distextatbottom=allDiscussion.get(3).getText();//fetch string at bottom
			if(!distextattop.equals(text4))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("last discussion not at top");
			}

			if(!distextatbottom.equals(text1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("1st discussion not at bottom");
			}
			
			new ComboBox().selectValuewithtitle(0, "Questions without any responses");
			new ComboBox().selectValuewithtitle(0, "All Questions");
			
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase instructorSeeAllDiscussionAndReplytoDiscussion in class  instructorSeeAllDiscussionAndReplytoDiscussion",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}

}
