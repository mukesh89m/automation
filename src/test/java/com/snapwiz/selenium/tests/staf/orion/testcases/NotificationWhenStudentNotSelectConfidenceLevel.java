package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.NotificationText;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectAnswerAndSubmit;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;
import com.snapwiz.selenium.tests.staf.orion.uihelper.PerformanceReport;

public class NotificationWhenStudentNotSelectConfidenceLevel
{
	String notificationtext1="Please say how confident you feel about this chapter. You will get more accurate recommendations!";
	String notificationtext2="You will build your proficiency faster if you say how confident you feel about the question before answering.";
	@Test(priority=1,enabled=true)
	public void notifiaction1002()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("754");
			List<WebElement> allbegin=Driver.driver.findElements(By.cssSelector("img[title=\"Begin\"]"));			
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",allbegin.get(0));//click on test ,thats we want to start
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("al-continue-to-diagnostic")).click();//click on continue button to start test
			Thread.sleep(2000);
			String notification=Driver.driver.findElement(By.className("al-notification-message-body")).getAttribute("innerHTML");
			if(!notification.contains(notificationtext1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("notification not shown");
			}
			List<WebElement> allbegin1=Driver.driver.findElements(By.cssSelector("img[title=\"Begin\"]"));			
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",allbegin1.get(1));//click on test ,thats we want to start
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("al-continue-to-diagnostic")).click();//click on continue button to start test
			Thread.sleep(2000);
			String notification1=Driver.driver.findElement(By.className("al-notification-message-body")).getAttribute("innerHTML");
			if(!notification1.contains(notificationtext1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("notification1 not shown");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase notification1002",e);
		}
	}
	@Test(priority=2,enabled=true)
	public void notification1003()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("756");
			new SelectChapterForTest().selectchapterfortest(0, 2);
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 3, "correct", false, false, false);
			new Navigator().orionDashboard();
            Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("img[title=\"Continue\"]")).click();	
			int notification=Driver.driver.findElements(By.className("al-notification-message-body")).size();
			if(notification!=0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Notifiaction not shown when click on continue.");
			}			
			new LoginUsingLTI().ltiLogin("7561");
			new SelectChapterForTest().selectchapterfortest(0, 2);
			Driver.driver.findElement(By.cssSelector("img[title='Submit']")).click();//click on submit
			String notification1=new NotificationText().notificationtext();
			if(notification1.contains(notificationtext1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("chpater level notification is equal to  question page level notification.");
			}
			if(!notification1.contains(notificationtext2))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("for diagnostic test notifiaction not shown when not select confidence level in question page while answering");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase notification1003",e);
		}
	}
	@Test(priority=3,enabled=true)
	public void notification1003_1()
	{
		try
		{
			Driver.startDriver();
			
			new LoginUsingLTI().ltiLogin("760");
			new SelectChapterForTest().selectchapterfortest(0, 2);
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 3, "correct", false, false, true);
			new Navigator().orionDashboard();
			new SelectChapterForTest().selectprqacticetest(0);//select chapter level practice test
			Driver.driver.findElement(By.cssSelector("img[title='Submit']")).click();//click on submit
			String notification=new NotificationText().notificationtext();
			if(!notification.contains(notificationtext2))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("for practice test at chapter level notifiaction not shown when not select confidence level in question page while answering");
			}
			new LoginUsingLTI().ltiLogin("760");
			new SelectChapterForTest().selectchapterfortest(0, 2);
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 3, "correct", false, false, true);
			new Navigator().orionDashboard();
			new PracticeTest().startTLOLevelPracticeTest(0);//select TLO practice test
			Driver.driver.findElement(By.cssSelector("img[title='Submit']")).click();//click on submit
			String notification1=new NotificationText().notificationtext();
			if(!notification1.contains(notificationtext2))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("for practice test at TLO level notifiaction not shown when not select confidence level in question page while answering");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase notification1003_1",e);
		}
	}
	
	@Test(priority=4,enabled=true)
	public void notification1004()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("762");
			new SelectChapterForTest().selectchapterfortest(0, 2);
			Driver.driver.findElement(By.cssSelector("img[title='Submit']")).click();//click on submit
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Submit']")).click();//click on submit
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Submit']")).click();//click on submit
			Thread.sleep(1000);
			String notifiaction=new NotificationText().notificationtext();
			if(!notifiaction.contains("Did you know you can hide the information on the right by clicking on this icon"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				
				Assert.fail(" student not get the notification for the 3rd question in FIRST attempted diagnostic test in the course");
			}
			Driver.driver.findElement(By.cssSelector("img[title='Submit']")).click();//click on submit
			Thread.sleep(1000);
			int notification1=Driver.driver.findElements(By.className("al-notification-message-body")).size();
			if(notification1!=0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("for next question notification shown");
			}
			new Navigator().orionDashboard();
			new SelectChapterForTest().selectchapterfortest(0, 2);
			Driver.driver.findElement(By.cssSelector("img[title='Submit']")).click();//click on submit
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Submit']")).click();//click on submit
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Submit']")).click();//click on submit
			Thread.sleep(1000);
			int notification2=Driver.driver.findElements(By.className("al-notification-message-body")).size();
			if(notification2!=0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("for other chapter notification shown");
			}
			new Navigator().orionDashboard();
			Driver.driver.findElement(By.cssSelector("img[title='Continue']")).click();
			new DiagnosticTest().attemptAllCorrect(2, false, false);
			new Navigator().orionDashboard();
			new SelectChapterForTest().selectprqacticetest(0);
			new SelectAnswerAndSubmit().practiceTestAttempt(2, 2, "correct", false, false);
			int notification3=Driver.driver.findElements(By.className("al-notification-message-body")).size();
			if(notification3!=0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("for practice test notifiaction shown");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase notification1004",e);
		}
		
	}
	
	@Test(priority=5,enabled=true)
	public void notification1005()
	{
		try
		{
			String notificationtext="Did you know you can click on the graph to see more information about the chapter objectives?";
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("766");//login as student
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().attemptAllCorrect(2, false, false);//attempt diagnostic test
			new Navigator().NavigateToStudentReport();//navigate to student my report
			new PerformanceReport().performancereport();//go to performance report
			String notification=new NotificationText().notificationtext();//fetch notification
			if(!notification.contains(notificationtext))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("notification not shown after 1st time go to performance report");
			}
			new Navigator().NavigateToStudentReport();//navigate to student my report
			new PerformanceReport().performancereport();//go to performance report
			int notification2=Driver.driver.findElements(By.className("al-notification-message-body")).size();
			if(notification2!=0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("notification  shown after 2nd time go to performance report");
			}
			new LoginUsingLTI().ltiLogin("7661");//login as  new student
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("al-quit-diag-test-icon")).click();//click on cross 
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("al-quit-diag-test")).click();//click on quit
			Thread.sleep(2000);
			new Navigator().NavigateToStudentReport();//navigate to student my report
			Thread.sleep(2000);
			new PerformanceReport().performancereport();//navigate to performance report
			String notification3=new NotificationText().notificationtext();//fetch text of 
			if(notification3.contains(notificationtext))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("notification  shown after quit the diagnostic test without attempting and go to performance report is same as privious");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase notification1005",e);
		}	
	}
	
	@Test(priority=6,enabled=true)
	public void notification1006()
	{
		try
		{
			String notificationtext="This Activity Report shows you how you did on your Diagnostic or Practice  questions.  The graphs will help you learn more about how you are doing on the chapter objectives.";
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("769");//login as student
			new SelectChapterForTest().selectchapterfortest(13, 2);//select chapter for test
			new DiagnosticTest().attemptAllCorrect(2, false, false);//attempt diagnostic test
			String notification=new NotificationText().notificationtext();
			if(!notification.contains(notificationtext))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("notification not shown after completion the diagnostic test");
			}
			new Navigator().orionDashboard();
			new SelectChapterForTest().selectchapterfortest(0, 2);
			new DiagnosticTest().attemptAllCorrect(2, false, false);
			int notification1 =Driver.driver.findElements(By.className("al-notification-message-body")).size();
			if(notification1!=0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("notifiaction shown for 2nd time finish the diagnostic test");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase notification1006",e);
		}	
	}
	@Test(priority=7,enabled=true)
	public void notification1007()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("774");
			new SelectChapterForTest().selectchapterfortest(0, 2);
			Thread.sleep(2000);
			Driver.driver.findElement(By.xpath("//label[@id='al-diagtest-markForReview']")).click(); //clicking on mark for review checkbox
			String notification=new NotificationText().notificationtext();
			if(!notification.equals("You can see the questions that you mark for review by clicking on \"My Reports\" under your profile and navigating to the \"Performance Report\". Just look for the questions using the \"Status\" filter."))
				Assert.fail("Notification displaying the functionality of marking a question for reviewing not coming");
			//Submit the first question
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button"))); //clicking on submit button
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button"))); //clicking on submit button after notification to go to the next question
			Thread.sleep(2000);
			Driver.driver.findElement(By.xpath("//label[@id='al-diagtest-markForReview']")).click(); //clicking on mark for review for the second question
			Thread.sleep(1000);
			int notification1 =Driver.driver.findElements(By.className("al-notification-message-body")).size();
			if(notification1!=0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Notifiaction shown for 2nd time click of mark for review");
			}
			new DiagnosticTest().quitTestAndGoToDashboard();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			Driver.driver.findElement(By.xpath("//label[@id='al-diagtest-markForReview']")).click(); //clicking on mark for review for question in practice test
			int notification2 =Driver.driver.findElements(By.className("al-notification-message-body")).size();
			if(notification2!=0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Notifiaction for marking a question for review shown for question in practice test");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase notification1007",e);
		}	
		
	}
		
	@Test(priority=8,enabled=true)
	public void notificationInReportPageMarkForReview()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("777");
			new SelectChapterForTest().selectchapterfortest(0, 2);
			Thread.sleep(2000);
			//Submit the first question
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button"))); //clicking on submit button
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button"))); //clicking on submit button after notification to go to the next question
			Thread.sleep(2000);
			//Quit the test and go to performance report
			new DiagnosticTest().quitTestAndGoToDashboard();
			Driver.driver.findElement(By.className("question-card-question-content")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.xpath("//label[@id='al-diagtest-markForReview']")).click(); //clicking on mark for review checkbox
			String notification=new NotificationText().notificationtext();
			if(!notification.equals("You can see the questions that you mark for review by clicking on \"My Reports\" under your profile and navigating to the \"Performance Report\". Just look for the questions using the \"Status\" filter."))
				Assert.fail("Notification displaying the functionality of marking a question for reviewing not coming in Performance Report");
			Driver.driver.findElement(By.xpath("//label[@id='al-diagtest-markForReview']")).click(); //unchecking mark for review checkbox
			Thread.sleep(2000);
			Driver.driver.findElement(By.xpath("//label[@id='al-diagtest-markForReview']")).click(); //checking mark for review checkbox again
			Thread.sleep(2000);
			int notification1 =Driver.driver.findElements(By.className("al-notification-message-body")).size();
			if(notification1!=0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Notifiaction shown for 2nd time click of mark for review");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase notificationInReportPageMarkForReview",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
