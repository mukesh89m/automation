package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;


public class PerfomancePageVerification extends Driver
{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PerfomancePageVerification");
	
	public boolean performanceTabBarGraph()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			String PerfomancepagePresent = driver.findElement(By.cssSelector("span[title='Performance']")).getText();
			//WebElement WE = driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/div[2]/div[3]/div"));
			WebElement WE = driver.findElement(By.cssSelector("div[class='al-diagnostic-test-sidebar-content-wrapper']"));
			String textInPerformanceTab = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",WE);
			int firstDiv = textInPerformanceTab.indexOf("Performance in Last 10 Qs");
			
			int secondDiv = textInPerformanceTab.indexOf("About this Question");
			
			int thirdDiv = textInPerformanceTab.indexOf("Question Difficulty");
			
			int fourthDiv = textInPerformanceTab.indexOf("Difficulty");
			
			int fifthDiv = textInPerformanceTab.indexOf("Students got it correct");
			
			int sixthDiv = textInPerformanceTab.indexOf("Question Objectives");
			
			if(firstDiv<secondDiv && secondDiv<thirdDiv && thirdDiv<fourthDiv && fourthDiv<fifthDiv && fifthDiv<sixthDiv && PerfomancepagePresent.equals("Performance"))
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
			
			logger.log(Level.SEVERE,"Exception in apphelper PerfomancePageVerification",e);
			Assert.fail("Exception in apphelper PerfomancePageVerification",e);
			return false;
		}
		
		
	}
	public boolean checkforTextAlongBarGraph()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			WebElement WE2 = driver.findElement(By.cssSelector("div[class='al-content-performance-question-bar-wrapper']"));
			String difficultyLevelInGraph = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",WE2);
			String textAlongXaxis=driver.findElement(By.cssSelector("div[class='al-content-performance-question-bar-bottom']")).getCssValue("background-image");
			if(difficultyLevelInGraph.contains("Easy") && difficultyLevelInGraph.contains("Hard") && textAlongXaxis.contains("question-label.png"))
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
			
			logger.log(Level.SEVERE,"Exception in apphelper PerfomancePageVerification",e);
			Assert.fail("Exception in apphelper PerfomancePageVerification",e);
			return false;
		}
		
	}
	public boolean checkBarAreComingForAnswers()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			new UIElement().waitAndFindElement(By.cssSelector("div[class='al-content-performance-question-bar-content']"));
			WebElement WE = driver.findElement(By.cssSelector("div[class='al-content-performance-question-bar-content']"));
			String checkColorForAnswer = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",WE);
			
			String barcolor;
			if(Config.browser.equals("ie"))				
				    barcolor = "rgb(204, 204, 204)";				
			else
				barcolor = "#6eac3d";	
			if(checkColorForAnswer.contains("pattern-red.png") || checkColorForAnswer.contains(barcolor))			
				return true;			
			else			
				return false;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.log(Level.SEVERE,"Exception in PerfomancePageVerification",e);
			Assert.fail("Exception in PerfomancePageVerification",e);
			return false;
		}
		
	}
	public boolean checkTenBarsAreComingForAnswers()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			for(int i = 0; i<20; i++)
			{
                new UIElement().waitAndFindElement(By.cssSelector("input[type='button']"));
				driver.findElement(By.cssSelector("input[type='button']")).click();
			}
			WebElement WE = driver.findElement(By.cssSelector("div[class='al-content-performance-question-bars']"));
			
			String tenBars = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",WE);
			int result = 0;
			  for (int i = 0, n = tenBars.length(); i < n; i++) {
			    if (tenBars.charAt(i) == '<' && tenBars.charAt(i+1) == 'd' && tenBars.charAt(i+2) == 'i' && tenBars.charAt(i+3) == 'v') {
			      result++;
			    }}
			System.out.println(result);
			if(result ==10)
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
			e.printStackTrace();
			logger.log(Level.SEVERE,"Exception in PerfomancePageVerification",e);
			Assert.fail("Exception in PerfomancePageVerification",e);
			return false;
		}
		
	}
	public boolean checkTenBarsAreComingForAnswersForDiagnostic()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			for(int i = 0; i<12; i++)
			{
                new UIElement().waitAndFindElement(By.cssSelector("input[type='button']"));
				driver.findElement(By.cssSelector("input[type='button']")).click();
			}
			
			WebElement WE = driver.findElement(By.cssSelector("div[class='al-content-performance-question-bars']"));
			String tenBars = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",WE);
			
			int result = 0;
			  for (int i = 0, n = tenBars.length(); i < n; i++) {
			    if (tenBars.charAt(i) == '<' && tenBars.charAt(i+1) == 'd' && tenBars.charAt(i+2) == 'i' && tenBars.charAt(i+3) == 'v') {
			      result++;
			    }}
			System.out.println(result);
			if(result ==10)
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
			e.printStackTrace();
			logger.log(Level.SEVERE,"Exception in PerfomancePageVerification",e);
			Assert.fail("Exception in PerfomancePageVerification",e);
			return false;
		}
		
	}
	public boolean performanceTabBarGraphForStatic()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			String PerfomancepagePresent = driver.findElement(By.cssSelector("span[title='Performance']")).getText();
		//	WebElement WE = driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/div[2]/div[3]/div"));
		//	String textInPerformanceTab = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",WE);
			WebElement WE = driver.findElement(By.className("al-diagnostic-test-sidebar-content-wrapper"));
			   String textInPerformanceTab = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",WE);
			
			int firstDiv = textInPerformanceTab.indexOf("Points : 1");
			
			int secondDiv = textInPerformanceTab.indexOf("al-content-performance-question-bar");
			
			int thirdDiv = textInPerformanceTab.indexOf("Students got it correct");
			
			
			if(firstDiv<secondDiv && secondDiv<thirdDiv && PerfomancepagePresent.equals("Performance"))
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
			
			logger.log(Level.SEVERE,"Exception in TestCase performanceTabBarGraphForStatic in class PerfomancePageVerification",e);
			Assert.fail("Exception in TestCase performanceTabBarGraphForStatic in class PerfomancePageVerification",e);
			return false;
		}		
	}
	public boolean checkTenBarsAreComingForAnswersForStaic()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			for(int i = 0; i<20; i++)
			{
                new UIElement().waitAndFindElement(By.cssSelector("input[type='button']"));
				driver.findElement(By.cssSelector("input[type='button']")).click();
			}
			WebElement WE = driver.findElement(By.cssSelector("div[class='al-content-performance-question-bars static-content-performance-question-bars']"));
			
			String tenBars = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",WE);
			
			int result = 0;
			  for (int i = 0, n = tenBars.length(); i < n; i++) {
			    if (tenBars.charAt(i) == '<' && tenBars.charAt(i+1) == 'd' && tenBars.charAt(i+2) == 'i' && tenBars.charAt(i+3) == 'v') {
			      result++;
			    }}
			System.out.println(result);
			if(result ==10)
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
			e.printStackTrace();
			logger.log(Level.SEVERE,"Exception in PerfomancePageVerification",e);
			Assert.fail("Exception in PerfomancePageVerification",e);
			return false;
		}
	}
	public boolean checkforTextAlongBarGraphForStatic()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			WebElement WE2 = driver.findElement(By.cssSelector("div[class='al-content-performance-question-bar-wrapper']"));
			String textAlongYaxis = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",WE2);
			
			String textAlongXaxis=driver.findElement(By.cssSelector("div[class='al-content-performance-question-bar-bottom']")).getCssValue("background-image");
			
			if(textAlongYaxis.contains("Points") && textAlongXaxis.contains("question-label.png"))
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
			
			logger.log(Level.SEVERE,"Exception in apphelper PerfomancePageVerification",e);
			Assert.fail("Exception in apphelper PerfomancePageVerification",e);
			return false;
		}
		
	}
}
