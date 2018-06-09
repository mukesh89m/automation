package com.snapwiz.selenium.tests.staf.orion.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.RunScheduledJobs;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.uihelper.LearnMoreReport;


public class ReportsFlowInstructor {

	@BeforeClass
	public void preSetUp()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("527_setup");
			new DiagnosticTest().startTest(0, 4);
			new DiagnosticTest().attemptAllCorrect(3, false, false);
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(2, "correct", 3, false, false);
			new PracticeTest().quitTestAndGoToDashboard();
			new RunScheduledJobs().runScheduledJobs();
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in preSetUp for verifying the reports on Instructor side in ReportsFlow",e);
		}
	}
	
	@Test(priority = 1,enabled = true)
	public void instructorLoginFirstTimeNotification()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("527");
			Thread.sleep(3000);
			int notification = Driver.driver.findElements(By.className("al-notification-message-body")).size();
			if(notification == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Notification message not coming if instructor is logged in the first time");
			}
			else
			{
			String notificationtext = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
			if(!notificationtext.contains("Welcome to ORION! This is the Instructor Dashboard, where you can see a summary of all your class activity in ORION. For more information on what is displayed on the Instructor Dashboard, please see the help page for this report."))
				Assert.fail("Notification message coming on instructor first time login is not as expected");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase instructorLoginFirstTime in class ReportsFlow",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void myReportsNotification()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("528");
			Thread.sleep(3000);
			new Navigator().NavigateToInstructorReport();
			new Navigator().NavigateToReport("Performance Report");
			int notification = Driver.driver.findElements(By.className("al-notification-message-body")).size();
			if(notification == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Notification message not coming if instructor is logged in the first time");
			}
			else
			{
			String notificationtext = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
			System.out.println(notificationtext);
			if(!notificationtext.contains("The graph at the top shows your class Performance on each chapter in ORION. Performance represents the number of correct answers out of the number of attempted questions. The top graph breaks down each chapter by correct, incorrect, partially correct, and skipped answers. The table at the bottom shows individual student Proficiency in ORION compared to the class average Proficiency in ORION."))
				Assert.fail("Notification message coming after instructor navigates to Performance Report first time is not as expected");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase myReportsNotification in class ReportsFlow",e);
		}
	}
	
	@Test(priority = 3,enabled = true)
	public void performanceReport()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("529");
			Thread.sleep(3000);
			new Navigator().NavigateToInstructorReport();
			new Navigator().NavigateToReport("Performance Report");
			Driver.driver.findElement(By.className("coursePerformanceByChapters-xAxisLabel")).click(); //Clicking on chapter link below the graph
			Thread.sleep(3000);
			if(!Driver.driver.findElement(By.id("ir-performance-report-title")).getText().equalsIgnoreCase("Class Performance by Objectives"))
				Assert.fail("Instructor not landed on class performance report by objective");
			
			Driver.driver.findElement(By.className("chapterPerformanceByObjectives-xAxisLabel")).click(); //Clicking on TLO link below the graph
			Thread.sleep(3000);
			System.out.println(Driver.driver.findElement(By.id("ir-performance-report-title")).getText());
			if(!Driver.driver.findElement(By.id("ir-performance-report-title")).getText().equalsIgnoreCase("Class Performance By Questions"))
				Assert.fail("Instructor not landed on class performance report by Questions");
			
			/*Driver.driver.findElement(By.className("chapterPerformanceByQuestions-xAxisLabel")).click(); //Clicking on First Question link 'Q1' below the graph
			Thread.sleep(3000);
			if(!Driver.driver.findElement(By.className("cms-content-question-review-question-no-label")).getText().contains("Q1"))
				Assert.fail("First Question not displayed after clicking on First Question link in the performance report by Questions");*/
			
			new Navigator().orionDashboard(); //navigating to Orion Dashboard by clicking on the star icon
			new Navigator().NavigateToInstructorReport();
			if(!Driver.driver.findElement(By.id("ir-performance-report-title")).getText().trim().contains("Class Performance by Chapters"))
				Assert.fail("Instructor not landed to class performance report by chapter report after navigated again to the reports page");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase clickOnLinkBelowGraph in class ReportsFlow",e);
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void productivityReport()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("530");
			Thread.sleep(3000);
			new Navigator().NavigateToInstructorReport();
			new Navigator().NavigateToReport("Productivity Report");
			new LearnMoreReport().learnMoreLinkClick("productivity-report-students");
			new LearnMoreReport().closeLearnMorePage();
			Driver.driver.findElement(By.className("highcharts-markers")).click(); //clicking on student dot in productivity report graph
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("highcharts-markers")).click(); //clicking on chapter dot in productivity report graph
			new Navigator().orionDashboard(); //navigating to Orion Dashboard by clicking on the star icon
			new Navigator().NavigateToInstructorReport();
			if(!Driver.driver.findElement(By.id("ir-productivity-report-title-one")).getText().trim().contains("Students"))
				Assert.fail("Instructor not landed to student level productivity report after navigated again to the reports page");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase productivityReport in class ReportsFlow",e);
		}
	}
	
	@Test(priority = 5, enabled = true)
	public void metoCognitiveReport()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("533");
			Thread.sleep(3000);
			new Navigator().NavigateToInstructorReport();
			new Navigator().NavigateToReport("Metacognitive Report");
			if(!Driver.driver.findElement(By.className("al-reports-description")).getText().contains("The Metacognitive Report compares student Proficiency in ORION to their level of confidence. Metacognition is the \"awareness and understanding of one's own thought process."))
				Assert.fail("The metacognitive report description is not as expected");
			
			if(!Driver.driver.findElement(By.className("al-reports-sub-description")).getText().contains("Hover over or click on a point on the graph to see more details about a student."))
				Assert.fail("The metacognitive report sub description is not as expected");

			new LearnMoreReport().learnMoreLinkClick("metacognitive-report-students");
			new LearnMoreReport().closeLearnMorePage();
			Driver.driver.findElement(By.cssSelector("g.highcharts-markers > path")).click();
			//Driver.driver.findElement(By.className("highcharts-markers")).click(); //clicking on student dot in metacognitive report graph
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("g.highcharts-markers > path")).click(); //clicking on chapter dot in metacognitive report graph
			new Navigator().orionDashboard(); //navigating to Orion Dashboard by clicking on the star icon
			new Navigator().NavigateToInstructorReport();
			
			if(!Driver.driver.findElement(By.id("ir-metacognitive-report-title")).getText().trim().contains("Metacognitive Report"))
				Assert.fail("Instructor not landed to Metacognitive report (Student) after navigated again to the reports page");
			
			if(!Driver.driver.findElement(By.id("ir-metacognitive-report-title-one")).getText().trim().contains("Students"))
				Assert.fail("Instructor not landed to Metacognitive report (Student) after navigated again to the reports page");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase metoCognitiveReport in class ReportsFlow",e);
		}
	}
	
	
	@Test(priority= 6, enabled = true)
	public void mostChallengingActivityReport()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("536");
			Thread.sleep(3000);
			new Navigator().NavigateToInstructorReport();
			if(!Driver.driver.findElement(By.className("al-reports-description")).getText().contains("The Most Challenging Activities report in ORION sorts objectives and chapters by Proficiency, from lowest to highest."))
				Assert.fail("The Most Challenging Report Description is Incorrect");
			new LearnMoreReport().learnMoreLinkClick("most-challenging-activity");
			new LearnMoreReport().closeLearnMorePage();
			new Navigator().orionDashboard();
			new Navigator().NavigateToInstructorReport();
			if(!Driver.driver.findElement(By.id("ir-most-challenging-activities-report-title")).getText().trim().contains("Most Challenging Activities"))
				Assert.fail("Instructor not landed to Most Challenging Activities report after navigated again to the reports page");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase mostChallengingActivityReport in class ReportsFlow",e);
		}
	}
	
	@AfterMethod
	public void TearDown()throws Exception
	{
		Driver.driver.quit();
	}
}

