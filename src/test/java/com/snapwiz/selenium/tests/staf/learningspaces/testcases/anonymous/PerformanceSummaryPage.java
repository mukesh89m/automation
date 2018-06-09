package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AttemptTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectAnswerAndSubmit;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;


public class PerformanceSummaryPage extends Driver
{
	
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.PerformanceSummaryPage");


		@Test(priority = 1, enabled=true)		
	public void performanceSummaryPageForDiagnosticTest()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1252");
			new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);
			
			String questionNumber = driver.findElement(By.cssSelector("div[class='al-diag-chapter-details']")).getText();
			
			if(questionNumber.trim().contains("1 of"))
			{
				logger.log(Level.INFO,"First question is delivered");
			}
			else
			{
				logger.log(Level.INFO,"First question is not delivered");
				Assert.fail("First question is not delivered");
			}
			
			new AttemptTest().Diagonestictest();
			Thread.sleep(3000);
			String PerfomancepagePresent = driver.findElement(By.cssSelector("div[class='al-performance-chart-title']")).getText();
			
			if(PerfomancepagePresent.equals("Performance Summary"))
			{
				logger.log(Level.INFO,"After clicking on submit perfomance page is opened");
			}
			else
			{
				logger.log(Level.INFO,"After clicking on submit perfomance page is not opened");
				Assert.fail("After clicking on submit perfomance page is not opened");
			}
			new TabClose().tabClose(1);
			new TOCShow().tocShow();
			new DiagnosticTest().startTest(2);
			String PerfomancepagePresent1 = driver.findElement(By.cssSelector("div[class='al-performance-chart-title']")).getText();
			
			if(PerfomancepagePresent1.equals("Performance Summary"))
			{
				logger.log(Level.INFO,"After completeing the whole assesment once, agin we clicked on that particular test , it landed on perfomance page directly");
			}
			else
			{
				logger.log(Level.INFO,"After completeing the whole assesment once, again we clicked on that particular test , it doesnt land on perfomance page");
				Assert.fail("After completeing the whole assesment once, again we clicked on that particular test , it doesnt land on perfomance page");
			}
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in PerformanceSummaryPage",e);
		}
	}

@Test(priority = 2, enabled=true)
public void startDiagnosticTestAndQuit()
{
	try
	{
		new LoginUsingLTI().ltiLogin("1255");
		new Navigator().NavigateTo("eTextbook");
		new DiagnosticTest().startTest(2);
		new SelectAnswerAndSubmit().daigonestianswersubmit("A");
		driver.findElement(By.cssSelector("div[class='al-quit-diag-test-icon']")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("span[class='al-quit-diag-test al-view-diag-practice-test-report']")).click();
		Thread.sleep(5000);
		String PerfomancepagePresent = driver.findElement(By.cssSelector("div[class='al-performance-chart-title']")).getText();
		
		if(PerfomancepagePresent.equals("Performance Summary"))
		{
			logger.log(Level.INFO,"After quiting diagnostic test perfomance page is opened");
		}
		else
		{
			logger.log(Level.INFO,"After quiting diagnostic test perfomance page is not opened");
			Assert.fail("After quiting diagnostic test perfomance page is not opened");
		}
		
	}
	catch(Exception e)
	{
		Assert.fail("Exception in PerformanceSummaryPage",e);
	}
}

@Test(priority = 3, enabled=true)
public void performanceSummaryPageForAdaptive()
{
	try
	{
		String firstQuestionNumber = ReadTestData.readDataByTagName("PerformanceSummaryPage", "firstQuestionNumber", "1259");
		String secondQuestionNumber = ReadTestData.readDataByTagName("PerformanceSummaryPage", "secondQuestionNumber", "1259");
		String adaptivetest = ReadTestData.readDataByTagName("tocdata", "adaptivetest", "1");
		new LoginUsingLTI().ltiLogin("1259");
		new Navigator().NavigateTo("eTextbook");
		new DiagnosticTest().startTest(2);
		new AttemptTest().Diagonestictest();
		new TOCShow().tocShow();
		new TopicOpen().topicOpen(adaptivetest);
		String questionNumber = driver.findElement(By.cssSelector("div[class='al-diag-test-question-label']")).getText();
		
		if(questionNumber.equals(firstQuestionNumber))
		{
			logger.log(Level.INFO,"First question is delivered");
		}
		else
		{
			logger.log(Level.INFO,"First question is not delivered");
			Assert.fail("First question is not delivered");
		}
		for(int i =0; i<2;i++)
		{
		driver.findElement(By.cssSelector("input[type='button']")).click();
		Thread.sleep(5000);
		}
		driver.findElement(By.cssSelector("div[class='al-quit-diag-test-icon']")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("span[class='ls-practice-test-view-report']")).click();
		Thread.sleep(3000);
		String PerfomancepagePresent = driver.findElement(By.cssSelector("div[class='al-performance-chart-title']")).getText();
		
		if(PerfomancepagePresent.equals("Performance Summary"))
		{
			logger.log(Level.INFO,"After quiting adaptive test perfomance page is opened");
		}
		else
		{
			logger.log(Level.INFO,"After quiting adaptive test perfomance page is not opened");
			Assert.fail("After quiting adaptive test perfomance page is not opened");
		}
		new TOCShow().tocShow();
		new TopicOpen().topicOpen(adaptivetest);
		Thread.sleep(3000);
		String nextQuestionNumber = driver.findElement(By.cssSelector("div[class='al-diag-test-question-label']")).getText();
		
		if(nextQuestionNumber.equals(secondQuestionNumber))
			{
				logger.log(Level.INFO,"After clicking on uncompleted practice it shows the next question");
			}
		
		else
			{
				logger.log(Level.INFO,"After clicking on uncompleted practice it does not show the next question");
				Assert.fail("After clicking on uncompleted practice it does not show the next question");
			}
	}
	catch(Exception e)
	{
		Assert.fail("Exception in performanceSummaryPageForAdaptive in class PerformanceSummaryPage",e);
	}
}
@Test(priority = 4, enabled=true)
public void performanceSummaryPageForStaticTest()
{
	try
	{
		new LoginUsingLTI().ltiLogin("1256");
		String statictest = ReadTestData.readDataByTagName("tocdata", "statictest", "1");
		new Navigator().NavigateTo("eTextbook");
		new TopicOpen().topicOpen(statictest);
		
		String questionNumber = driver.findElement(By.cssSelector("div[class='al-diag-chapter-details']")).getText();
		
		if(questionNumber.trim().contains("1 of"))
		{
			logger.log(Level.INFO,"First question is delivered");
		}
		else
		{
			logger.log(Level.INFO,"First question is not delivered");
			Assert.fail("First question is not delivered");
		}
		
		int testend=driver.findElements(By.className("al-diag-test-timer")).size();
		
		while(testend==1)
		{
			Thread.sleep(3000);
			testend=driver.findElements(By.className("al-diag-test-timer")).size();
			if(testend == 0)
			break;
			driver.findElements(By.className("al-diag-test-timer"));
			driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
			Thread.sleep(3000);
		}
		Thread.sleep(3000);
		String PerfomancepagePresent = driver.findElement(By.cssSelector("div[class='al-performance-chart-title']")).getText();
		
		if(PerfomancepagePresent.equals("Performance Summary"))
		{
			logger.log(Level.INFO,"After submitting last question perfomance page is opened");
		}
		else
		{
			logger.log(Level.INFO,"After submitting last question perfomance page is not opened");
			Assert.fail("After submitting last question perfomance page is not opened");
		}
		Thread.sleep(5000);
		new TabClose().tabClose(1);
		new TOCShow().tocShow();
		new TopicOpen().topicOpen(statictest);
		String PerfomancepagePresent1 = driver.findElement(By.cssSelector("div[class='al-performance-chart-title']")).getText();
		
		if(PerfomancepagePresent1.equals("Performance Summary"))
		{
			logger.log(Level.INFO,"After completeing the whole assesment once, agin we clicked on that particular test , it landed on perfomance page directly");
		}
		else
		{
			logger.log(Level.INFO,"After completeing the whole assesment once, again we clicked on that particular test , it doesnt land on perfomance page");
			Assert.fail("After completeing the whole assesment once, again we clicked on that particular test , it doesnt land on perfomance page");
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		logger.log(Level.SEVERE,"Exception in PerformanceSummaryPage",e);
		Assert.fail("Exception in PerformanceSummaryPage",e);
	}
}

}
