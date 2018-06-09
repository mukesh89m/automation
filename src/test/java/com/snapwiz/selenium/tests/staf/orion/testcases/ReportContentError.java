package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.ReportContentIssue;
import com.snapwiz.selenium.tests.staf.orion.apphelper.RunScheduledJobs;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Notification;

public class ReportContentError {
	
	@Test(priority = 1, enabled = true)
	public void contentErrorLinkVerify()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("652_1"); //login as student
			new DiagnosticTest().startTest(0, 3);
			Driver.driver.findElement(By.id("content-error-view-section")).click();
			
			new DirectLogin().CMSLogin(); //login as content editor
			Driver.driver.findElement(By.cssSelector("img[alt=\""+Config.course+"\"]")).click();
			Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click(); //clicking on first chapter
			Driver.driver.findElement(By.className("collection-assessment-name")).click(); 
			Driver.driver.findElement(By.cssSelector("div[title='Report Content Errors']")).click();	
			
			new DirectLogin().CMSLogin(); //login as content editor
			
			Driver.driver.findElement(By.cssSelector("img[alt=\""+Config.course+"\"]")).click();
			Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click(); //clicking on first chapter
			Driver.driver.findElement(By.className("collection-assessment-name")).click(); 
			Driver.driver.findElement(By.cssSelector("div[title='Report Content Errors']")).click();	
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase contentErrorLinkVerify in class ReportContentError",e);
		}
	}

	@Test(priority = 2, enabled = true)
	public void resolveButtonVerify()
	{
		try
		{
			Driver.startDriver();
			DBConnect.st.executeQuery("truncate t_content_error");
			new LoginUsingLTI().ltiLogin("657"); //login as student
			new DiagnosticTest().startTest(0, 3);
			//for(int i=0;i<5;i++)
			new DiagnosticTest().attemptCorrect(0);
			new DiagnosticTest().quitTestAndGoToDashboard();		
			new RunScheduledJobs().runScheduledJobs();
			new LoginUsingLTI().ltiLogin("657_1");
			new ReportContentIssue().reportcontentissue(0, 0, 1);
			new DirectLogin().CMSLogin(); //login as content editor
			Driver.driver.findElement(By.cssSelector("img[alt=\""+Config.course+"\"]")).click();
			Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click(); //clicking on first chapter
			Driver.driver.findElement(By.className("collection-assessment-name")).click(); 
			Driver.driver.findElement(By.cssSelector("div[title='Report Content Errors']")).click();	
			if(Driver.driver.findElement(By.cssSelector("span[title='Resolved It']")).isDisplayed() == false)
				Assert.fail("Resolve button not present on the top-right of the comment area.");
			Driver.driver.findElement(By.cssSelector("span[title='Resolved It']")).click();
			String status = "";
			ResultSet rst = DBConnect.st.executeQuery("select error_status from t_content_error where comments like 'This is reported Issue'");
			while(rst.next())
				status = rst.getString("error_status");
			if(!status.equals("Closed"))
				Assert.fail("Status of the content error reported not set as closed in DB even after reloving it");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase resolveButtonVerify in class ReportContentError",e);
		}
		
	}
	
	@Test(priority = 3, enabled = true)	
	public void listOfIssuesReported()
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin(); //login as content editor
			Driver.driver.findElement(By.cssSelector("img[alt=\""+Config.course+"\"]")).click();
			Driver.driver.findElement(By.id("deliver-course")).click();
			Thread.sleep(2000);			
			Driver.driver.findElement(By.id("refresh-publish-content")).click();
			Thread.sleep(10000);
			Driver.driver.findElement(By.id("deliver-course")).click();
			Thread.sleep(2000);	
			Driver.driver.findElement(By.className("review-potential-text")).click();
			Thread.sleep(2000);
			String heading = Driver.driver.findElement(By.id("reported-content-issue-col-names")).getText();
			heading = heading.replaceAll("\\n", " ");
			if(!heading.equals("Id Content Type User Type Reported By Reported Date Comments Status Action"))
				Assert.fail("The heading of content errors reported table is not as expected");
			Driver.driver.findElement(By.cssSelector("span[title='Fix It']")).click();
			Driver.driver.findElement(By.cssSelector("div[title='Report Content Errors']")).click();
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase listOfIssuesReported in class ReportContentError",e);
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void reportErrorInPracticeTest()
	{
		try
		{
			String diagtesterror = "Content Issue in Diagnostic Test 671";
			String practesterror = "Content Issue in Practice Test 671";
			String tlopractesterror = "Content Issue in TLO Level Practice Test 671";
			String questioncardcontenterror = "Content Issue From Question Card 671";
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("671");
			new DiagnosticTest().startTest(0, 4);
			new ReportContentIssue().reportIssueOnQuestion(diagtesterror);
			new DiagnosticTest().attemptCorrect(4);
			new DiagnosticTest().quitTestAndGoToDashboard();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().submitOnlyQuestion(); //Click on submit button for the question but don't click on Next button
			new ReportContentIssue().reportIssueOnQuestion(practesterror);
			new PracticeTest().quitTestAndGoToDashboard();
			new Navigator().orionDashboard();
			new PracticeTest().startTLOLevelPracticeTest(0);
			new PracticeTest().submitOnlyQuestion(); //Click on submit button for the question but don't click on Next button
			new ReportContentIssue().reportIssueOnQuestion(tlopractesterror);
			new Navigator().NavigateToStudentReport();
			new Navigator().NavigateToReport("Performance Report");
			Driver.driver.findElement(By.className("question-card-question-content")).click();
			Thread.sleep(3000);
			new ReportContentIssue().reportIssueOnQuestion(questioncardcontenterror);
			Driver.driver.findElement(By.cssSelector("div[class='add-content-error show-content-issues-dialog']")).click();//click on report content issue
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("text-area-content-issue")).click();//click on report content issue text area
			Thread.sleep(1000);
			Driver.driver.findElement(By.id("text-area-content-issue")).sendKeys("Dummy text");//report text
			Driver.driver.findElement(By.id("send-content-issue-btn")).click();//click on report content issue send button
			Thread.sleep(2000);
			String notification = new Notification().getNotificationMessage();
			if(!notification.contains("Are you sure there is a problem with this question? You may want to start a Discussion on this question with your classmates before reporting a problem."))
				Assert.fail("The notification message while reporting content error is not as expected");
				
			Thread.sleep(3000);
			List<String> comments= new ArrayList<String>();
			List<String> error_status= new ArrayList<String>();
			ResultSet rst = DBConnect.st.executeQuery("SELECT comments,error_status FROM  t_content_error where comments like '%671%'");
			while(rst.next())
			{
				comments.add(rst.getString("comments"));
				error_status.add(rst.getString("error_status"));
			}
			if(!comments.get(0).equals(diagtesterror)) Assert.fail("Issue reported in Diag Test Question not found in Database");
			if(!comments.get(1).equals(practesterror)) Assert.fail("Issue reported in Practice Test Question not found in Database");
			if(!comments.get(2).equals(tlopractesterror)) Assert.fail("Issue reported in TLO level Practice Test Question not found in Database");
			if(!comments.get(3).equals(questioncardcontenterror)) Assert.fail("Issue reported from question card not found in Database");
			if(!error_status.get(0).equals("Open"))    Assert.fail("Status of Issue reported in Diag Test Question not Open in DB");
			if(!error_status.get(1).equals("Open"))    Assert.fail("Status of Issue reported in Practice Test Question not Open in DB");
			if(!error_status.get(2).equals("Open"))    Assert.fail("Status of Issue reported in TLO Level Practice Test Question not Open in DB");
			if(!error_status.get(3).equals("Open"))    Assert.fail("Status of Issue reported from questio card not Open in DB");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase reportErrorInPracticeTest in class ReportContentError",e);
		}
	}
	
	@Test(priority = 5, enabled = true)
	public void instructorReportContentError()
	{
		try
		{
			String contenterror = "Content Issue on question from Instructor 680";
			Driver.startDriver();
			new RunScheduledJobs().runScheduledJobs();
			new LoginUsingLTI().ltiLogin("680");			
			new Navigator().NavigateToInstructorReport();
			new Navigator().NavigateToReport("Performance Report");
			Driver.driver.findElement(By.className("coursePerformanceByChapters-xAxisLabel")).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.className("chapterPerformanceByObjectives-xAxisLabel")).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.className("chapterPerformanceByQuestions-xAxisLabel")).click();
			new ReportContentIssue().reportIssueOnQuestion(contenterror);
			Thread.sleep(3000);
			List<String> comments= new ArrayList<String>();
			List<String> error_status= new ArrayList<String>();
			ResultSet rst = DBConnect.st.executeQuery("SELECT comments,error_status FROM  t_content_error where comments like '%680%'");
			while(rst.next())
			{
				comments.add(rst.getString("comments"));
				error_status.add(rst.getString("error_status"));
			}
			if(!comments.get(0).equals(contenterror)) Assert.fail("Issue reported by Instructor not found in Database");
			if(!error_status.get(0).equals("Open"))    Assert.fail("Status of Issue reported by Instructor not Open in DB");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase reportErrorInPracticeTest in class ReportContentError",e);
		}
	}
	
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
