package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.CourseProductivityReport;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class ClickOnTheColoredDot {
	@Test
	public void clickOnTheColoredDot()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("893");
			new DiagnosticTest().startTest(0,3);//select 1st chapter for test
			new DiagnosticTest().attemptAllCorrect(4, false, false);		//attempt correctly
			new Navigator().NavigateToStudentReport();
			new Navigator().NavigateToReport("Productivity Report");
			new CourseProductivityReport().clickOnDot(1);
			String reportHeader = Driver.driver.findElement(By.className("al-student-report-title-section")).getText();
			if(!reportHeader.contains("Productivity Report ( Objectives )"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The report header does not show the text 'Productivity Report ( Objectives )'.");
			}
			
			//find the graph
			int graph = Driver.driver.findElements(By.id("al-productivity-report-for-chapters")).size();
			if(graph != 1)
				Assert.fail("The scattered plot is missing in the productivity report page for chapter level.");
			//find text in table below the graph
			String tableHeader = Driver.driver.findElement(By.className("al-report-chapter-performance-content-header-row")).getText();
			if(!tableHeader.contains("Objectives"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The table in the bottom of scattered plot does not have the 'Chapters' column.");
			}
			
			if(!tableHeader.contains("Proficiency"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The table in the bottom of scattered plot does not have the 'Proficiency' column.");
			}
			
			if(!tableHeader.contains("Time Spent"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The table in the bottom of scattered plot does not have the 'Time Spent' column.");
			}
			
			//check for back arrrow 
			String backArrow = Driver.driver.findElement(By.cssSelector("img[title='back']")).getAttribute("src");
			if(!backArrow.contains("back-arrow.png"))
			{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("Back-arrow button is not there title Productivity Report(Objectives).");
			}
			//find the text present along the axis of the graph
			String str = Driver.driver.findElement(By.className("highcharts-container")).getText();
			if(!str.contains("High"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Text 'High' along Y-axis is missing.");
			}
			
			if(!str.contains("Low"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Text 'Low' along Y-axis is missing.");
			}
			
			if(!str.contains("Least"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Text 'Least' along X-axis is missing.");
			}
			
			if(!str.contains("Most"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Text 'Most' along X-axis is missing.");
			}
			
			//list all the dots
			List<WebElement> allDots = Driver.driver.findElements(By.cssSelector("g.highcharts-markers > path"));
			
			if(allDots.size() <= 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The dots for all the TLOs are not coming.");
			}
			//list the color tag of the proficiency bar
			List<WebElement> allProficiencyBarColor = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'al-proficiency-content al-proficiency-')]"));
			if(!allProficiencyBarColor.get(0).getAttribute("class").contains("green")  || !allProficiencyBarColor.get(1).getAttribute("class").contains("green")  || !allProficiencyBarColor.get(2).getAttribute("class").contains("green") || !allProficiencyBarColor.get(3).getAttribute("class").contains("green")  || !allProficiencyBarColor.get(4).getAttribute("class").contains("green"))
			{
				 new Screenshot().captureScreenshotFromTestCase();
				 Assert.fail("The color of the dot in scattered plot is not according to the proficiency bar color.");
			}
			
			//mouse hover the dots in chapter level report
			new CourseProductivityReport().mouseHoverOnDot(1);
			String tloName = Driver.driver.findElement(By.className("tooltip-label")).getText();
			if(tloName == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				 Assert.fail("The tlo Name is not shown on mouse hovering the dot.");
			}
			
			String text = Driver.driver.findElement(By.className("tooltip-label-subsection-wrapper")).getText();
			if(!text.contains("Proficiency:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The Proficiency is not shown on mouse hovering the dot.");
			}
			if(!text.contains("Time Spent:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The Time Spent is not shown on mouse hovering the dot.");
			}
			
			
			//check for chapter icon in table header
			String objectiveIcon = Driver.driver.findElement(By.cssSelector("img[title='Objectives']")).getAttribute("src");
			if(!objectiveIcon.contains("objectives-icon.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Chapter icon is missing in the table below the graph.");
			}
			//check for Proficiency icon in table header
			String proficiencyIcon = Driver.driver.findElement(By.cssSelector("img[title='Proficiency']")).getAttribute("src");
			if(!proficiencyIcon.contains("proficiency-icon.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Proficiency icon is missing in the table below the graph.");
			}
			
			//check for Time Spent icon in table header
			String timeSpent = Driver.driver.findElement(By.cssSelector("img[title='Time Spent']")).getAttribute("src");
			if(!timeSpent.contains("time-icon.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("time Spent icon is missing in the table below the graph.");
			}
			
			//click on practice link of TLO
			List<WebElement> practicebuttons = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'al-link  al-tlo-start-practice-button al-tlo-start-practice-button-')]"));
	  		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",practicebuttons.get(0)); 
	  		Thread.sleep(2000);
	  		
			String practiceTestName = Driver.driver.findElement(By.className("al-diag-test-title")).getText();
			if(!practiceTestName.contains("Practice"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking practice link from the TLO the practice test are not opening.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase clickOnTheColoredDot in class ClickOnTheColoredDot ",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
