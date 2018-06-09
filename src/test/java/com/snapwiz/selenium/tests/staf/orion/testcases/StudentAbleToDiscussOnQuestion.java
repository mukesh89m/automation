package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.AddDiscussionInQuestions;
import com.snapwiz.selenium.tests.staf.orion.apphelper.ClickOnquestionCard;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class StudentAbleToDiscussOnQuestion 
{

	@Test(priority =1,enabled = true)
	public void studentabletodiscussonquestion()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("728");//login as student
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(3, 6, "correct", false, false, true);//attempt diagnostic test
			new Navigator().orionDashboard();
			new PracticeTest().startTLOLevelPracticeTest(0);//select practice test of TLO 1
			if(Driver.driver.findElements(By.className("qtn-label")).size() > 0)
			{
				Driver.driver.findElement(By.className("qtn-label")).click();//select answer option
			}
			Driver.driver.findElement(By.cssSelector("img[title='Submit']")).click();//click on submit question
			int submitbutton=Driver.driver.findElements(By.cssSelector("img[title='Submit']")).size();
			if(submitbutton>=1)
				Driver.driver.findElement(By.cssSelector("img[title='Submit']")).click();//click on submit button if present
			Thread.sleep(2000);
			String ranstring=new RandomString().randomstring(3);//generate random string
			new AddDiscussionInQuestions().AddDiscussion(ranstring);//add discussion in question
			String username=Driver.driver.findElement(By.id("al-discussion-user-name")).getText();//verify username 
			if(!username.contains(Config.givenname))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("uesr name not shown after post the discussion.");
			}
			String time=Driver.driver.findElement(By.id("al-user-discussion-question-time")).getText();//verify timing
			if(!time.contains("ago"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("time not shown after post");
			}
			Driver.driver.findElement(By.id("al-close-question-discussion-wrapper")).click();//click on cross of discussion area
			Thread.sleep(2000);
			boolean discussionarea=Driver.driver.findElement(By.className("al-display-user-discussion-wrapper")).isDisplayed();//verify about discussion area close or not
			if(discussionarea==true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("text area not close. after click on cross ");
			}
			String ranstring1=new RandomString().randomstring(3);//generate random string
			String ranstring2=new RandomString().randomstring(3);//generate random string
			String ranstring3=new RandomString().randomstring(3);//generate random string
			new AddDiscussionInQuestions().AddDiscussion(ranstring1);//add discussion in question
			new AddDiscussionInQuestions().AddDiscussion(ranstring2);//add discussion in question
			new AddDiscussionInQuestions().AddDiscussion(ranstring3);//add discussion in question
			List<WebElement> alldiscussion=Driver.driver.findElements(By.id("al-user-discussion-question-content"));
			String discussiontext=alldiscussion.get(0).getText();
			System.out.println(discussiontext);
			if(!discussiontext.contains(ranstring3))
			{
				Assert.fail("latest discussion not at top");
			}
			new Navigator().NavigateToStudentReport();
			Thread.sleep(3000);			
			int performancereport=Driver.driver.findElements(By.id("al-performance-report")).size();
			if(performancereport>=1)
			{
				Driver.driver.findElement(By.id("al-performance-report")).click();//click on performance report if available
			}
			Thread.sleep(2000);
			new ClickOnquestionCard().clickOnQuestion(0);
			boolean discussionshownloginagain=Driver.driver.findElement(By.id("al-question-discussion-count")).isDisplayed();//verify question discussion count
			if(discussionshownloginagain==false)
			{
				Assert.fail("disscussion not shown after login again");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase studentabletodiscussonquestion in class studentabletodiscussonquestion",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void anonymousUserDiscussionPosted()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("741");
			new DiagnosticTest().startTest(0, 4);
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 4, "correct", false, false, true);
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().submitOnlyQuestion();
			String randomstring=new RandomString().randomstring(3);//generate random string
			new AddDiscussionInQuestions().AddDiscussion(randomstring);//add discussion in question
			Thread.sleep(2000);
			if(!Driver.driver.findElement(By.id("al-discussion-user-name")).getText().trim().equals("Anonymous"))
				Assert.fail("The discussion not posted by the name anonymous");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase anonymousUserDiscussionPosted in class studentabletodiscussonquestion",e);
		}
		
	}
	
	@Test(priority = 3, enabled = true)
	public void diagnosticTestDiscussion()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("748");
			new DiagnosticTest().startTest(0, 4);
			int discussionicon = Driver.driver.findElements(By.id("al-question-discussion-icon-section")).size();
			if(discussionicon > 0) Assert.fail("The discussion option present in diagnostic test question");
			new DiagnosticTest().attemptCorrect(0);
			new DiagnosticTest().quitTestAndGoToDashboard();
			Driver.driver.findElement(By.className("question-card-question-details")).click();
			String randomstring=new RandomString().randomstring(3);//generate random string
			new AddDiscussionInQuestions().AddDiscussion(randomstring);//add discussion in question
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase diagnosticTestDiscussion in class studentabletodiscussonquestion",e);
		}		
	}
	
	@AfterMethod
	public void Teardown()
	{
		Driver.driver.quit();
	}
}
