package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;

public class QuitAssessment extends Driver
{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.apphelper.QuitAssessment");
	
	public boolean quitassessmentAndCheckRoboNotificationForDiagnostic()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			driver.findElement(By.cssSelector("div[class='al-quit-diag-test-icon']")).click();
			Thread.sleep(5000);
			String notification = driver.findElement(By.cssSelector("div[class='al-notification-message-body']")).getText();
			
			if(notification.contains("Continue Later") && notification.contains("Quit") )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in QuitAssessment",e);
			return false;
		}
	}
	
	
	public boolean clickingOnContinueLaterForDiagnostic()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			String defaulttopictitle = ReadTestData.readDataByTagName("tocdata", "defaulttopictitle", "1");
			driver.findElement(By.cssSelector("span[class='al-diag-test-continue-later']")).click();
			Thread.sleep(5000);
			
			int TOCsize = driver.findElements(By.linkText(defaulttopictitle)).size();
			if(TOCsize == 0)
			{
				return false;
			}
			else
			{
				return true;
			}
			
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in QuitAssessment",e);
			return false;
		}
	}
	
	public boolean quitTheAssesmentCompletlyForDiagnostic()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			new SelectAnswerAndSubmit().daigonestianswersubmit("A");
			/*for(int i = 0; i<3; i++)
			{
				driver.findElement(By.cssSelector("input[type='button']")).click();
				Thread.sleep(5000);
			}*/
			driver.findElement(By.cssSelector("div[class='al-quit-diag-test-icon']")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("span[class='al-quit-diag-test al-view-diag-practice-test-report']")).click();
			Thread.sleep(5000);
			String PerfomancepagePresent = driver.findElement(By.cssSelector("div[class='al-performance-chart-title']")).getText();
			
			if(PerfomancepagePresent.equals("Performance Summary"))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception in QuitAssessment",e);
			Assert.fail("Exception in QuitAssessment",e);
			return false;
		}
	}
	
	public boolean quitTheAssesmentCompletlyAndStartTheSameAssesmentAgainForDiagnostic()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			new TabClose().tabClose(1);
			Thread.sleep(5000);
			new TOCShow().tocShow();
			new DiagnosticTest().startTest(2);
			//String daigonestictest = ReadTestData.readDataByTagName("tocdata", "daigonestictest", "1");
			//new TopicOpen().topicOpen(daigonestictest);
			String PerfomancepagePresent1 = driver.findElement(By.cssSelector("div[class='al-performance-chart-title']")).getText();
			if(PerfomancepagePresent1.equals("Performance Summary"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception in QuitAssessment",e);
			Assert.fail("Exception in QuitAssessment",e);
			return false;
		}
	}
	public boolean quitAdaptiveAssessmentAndCheckRoboNotification()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			driver.findElement(By.cssSelector("div[class='al-quit-diag-test-icon']")).click();
			Thread.sleep(5000);
			WebElement WE = driver.findElement(By.cssSelector("div[class='al-notification-message-wrapper']"));
			String notification = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",WE);
			
			if(notification.contains("View Report") && notification.contains("Back to last lesson"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			
			logger.log(Level.SEVERE,"Exception in QuitAssessment",e);
			Assert.fail("Exception in QuitAssessment",e);
			return false;
		}
	}
	public boolean clickingOnBackToLastLessonForAdaptive()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			String defaulttopictitle = ReadTestData.readDataByTagName("tocdata", "defaulttopictitle", "1");
			driver.findElement(By.cssSelector("span[class='ls-adaptive-practice-test-close']")).click();
			Thread.sleep(3000);
			int practesticonontab = driver.findElements(By.cssSelector("span[class='tab_icon assessment-icon']")).size();
			Thread.sleep(3000);
			
			int tocopencheck = driver.findElements(By.linkText(defaulttopictitle)).size();
			
			if(tocopencheck == 1 && practesticonontab==0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			
			logger.log(Level.SEVERE,"Exception in QuitAssessment",e);
			Assert.fail("Exception in QuitAssessment",e);
			return false;
		}
	}
	public boolean clickingOnViewReportForAdaptive()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			
			for(int i = 0; i<4; i++)
			{
				driver.findElement(By.cssSelector("input[type='button']")).click();
				Thread.sleep(5000);
			}
			driver.findElement(By.cssSelector("div[class='al-quit-diag-test-icon']")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("span[class='ls-practice-test-view-report']")).click();
			Thread.sleep(5000);
			String PerfomancepagePresent = driver.findElement(By.cssSelector("div[class='al-performance-chart-title']")).getText();
			
			if(PerfomancepagePresent.equals("Performance Summary"))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception in QuitAssessment",e);
			Assert.fail("Exception in QuitAssessment",e);
			return false;
		}
	}
	public boolean quitTheAssesmentCompletlyAndStartTheSameAssesmentAgainForAdaptive()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			String adaptivetest = ReadTestData.readDataByTagName("tocdata", "adaptivetest", "1");
			new TabClose().tabClose(1);
			new TOCShow().tocShow();
			new TopicOpen().topicOpen(adaptivetest);
			
			String question = driver.findElement(By.className("al-diag-test-question-raw-content")).getText();
			//Checking if some question is present or not
			if(question.length() > 1)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception in QuitAssessment",e);
			Assert.fail("Exception in QuitAssessment",e);
			return false;
		}
	}
	
	public boolean quitAssesmentForStaticTest()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			driver.findElement(By.cssSelector("div[class='al-quit-diag-test-icon']")).click();
			Thread.sleep(3000);
			int size = driver.findElements(By.cssSelector("div[id='assessmentTimer']")).size();
			Thread.sleep(3000);
			
			WebElement WE = driver.findElement(By.cssSelector("div[class='chapter-heading chapter-selected']"));
			String text = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",WE);
			
			if(text.contains("Chapter 1") && size == 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception in QuitAssessment",e);
			Assert.fail("Exception in QuitAssessment",e);
			return false;
		}
	}
}
