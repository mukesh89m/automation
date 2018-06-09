package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.MetacognitiveReport;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

@Test(priority=1,enabled = false)
public class CompareSelfRatingWithActualPerformanceAtAnyGivenTime {
	
	public void compareSelfRatingWithActualPerformanceAtAnyGivenTime()
	{
		try
		{
			Driver.startDriver();
			String chapter1= ReadTestData.readDataByTagName("chapternames", "chapter1", "0");
			new LoginUsingLTI().ltiLogin("846");//login as student
			new DiagnosticTest().startTest(0,3);//select 1st chapter for test
			for(int i=0;i<10;i++)
				new DiagnosticTest().attemptCorrect(4);		//attempt correctly
			new DiagnosticTest().quitTestAndGoToDashboard();
			new Navigator().orionDashboard();
			new DiagnosticTest().startTest(0,3);//select 2nd chapter for test
			for(int i=0;i<10;i++)
				new DiagnosticTest().attemptIncorrect(4);		//attempt incorrectly
			new DiagnosticTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentReport();//navigate to my report
		    new Navigator().NavigateToReport("Metacognitive Report");
			Thread.sleep(2000);
			//text on the graph
			String str = Driver.driver.findElement(By.className("highcharts-container")).getText();
			String textAlongAllAxis = str.replaceAll("[\n\r]", "");
			if(!textAlongAllAxis.contains("LeastConfident"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Text 'Least Confident' along -ve X-axis is missing.");
			}
			
			if(!textAlongAllAxis.contains("MostConfident"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Text 'Most Confident' along +ve X-axis is missing.");
			}
			
			if(!textAlongAllAxis.contains("Most Proficient"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Text 'Most Proficient' along +ve Y-axis is missing.");
			}
			
			if(!textAlongAllAxis.contains("Least Proficient"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Text 'Least Proficient' along -ve Y-axis is missing.");
			}
			//find the texts below the graph
			String textBelowGraph = Driver.driver.findElement(By.id("al-report-content-body")).getText();
			if(!textBelowGraph.contains("Chapters") )
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Text 'Chapters' below the graph is not available.");
			}
			if(!textBelowGraph.equals(chapter1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				//Assert.fail("The chapter name below the graph is not available.");
			}
			int confidenceLevel = Driver.driver.findElements(By.id("4")).size();
			if(!textBelowGraph.contains("Confidence"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Text 'Confidence' for confidence below the graph is not available.");
			}
			if(confidenceLevel == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("the smiley for confidence below the graph is not available.");
			}
			
			String proficiencyPercentage = Driver.driver.findElement(By.cssSelector("span[class='al-proficiency-percentage']")).getText();
			if(!proficiencyPercentage.contains("%"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Proficiency percentage is absent.");
			}
			
			
			//mouse hover on dot on the scattered plot
			new MetacognitiveReport().mouseHoverOnDot(1);
			//find the chapter name
			String chapter = Driver.driver.findElement(By.className("tooltip-label")).getText();
			if(!chapter.equals(chapter1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On mouse hovering on the dot the chapter name is not displayed.");
			}
			String text = Driver.driver.findElement(By.className("tooltip-label-subsection-wrapper")).getText();
			if(!text.contains("Proficiency:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On mouse hovering on the dot the proficiency level is not displayed.");
			}
			if(!text.contains("Confidence Level: I know it"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On mouse hovering on the dot the confidence level is not displayed.");
			}
			
			//list the colors of the dots
			 List<WebElement> allDots = Driver.driver.findElements(By.cssSelector("g.highcharts-markers > path"));
			
			 if(!allDots.get(0).getAttribute("fill").equals("url(#highcharts-pattern-0)"))
			 {
				 new Screenshot().captureScreenshotFromTestCase();
				 Assert.fail("The color of the chapter in the scattered plot having less proficiency is not displayed in red.");
			 }
			 
			 if(!allDots.get(1).getAttribute("fill").equals("#6bb45f"))
			 {
				 new Screenshot().captureScreenshotFromTestCase();
				 Assert.fail("The color of the chapter in the scattered plot having more proficiency is not displayed in green.");
			 }
			 
			 //list the color tag of the proficiency bar
			 List<WebElement> allProficiencyBarColor = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'al-proficiency-content al-proficiency-')]"));
			 
			 if(!allProficiencyBarColor.get(0).getAttribute("class").contains("red"))
			 {
				 new Screenshot().captureScreenshotFromTestCase();
				 Assert.fail("The color of the dot in scattered plot is not according to the proficiency bar color.");
			 }
			 
			 if(!allProficiencyBarColor.get(1).getAttribute("class").contains("green"))
			 {
				 new Screenshot().captureScreenshotFromTestCase();
				 Assert.fail("The color of the dot in scattered plot is not according to the proficiency bar color.");
			 }
			 
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase compareSelfRatingWithActualPerformanceAtAnyGivenTime in class CompareSelfRatingWithActualPerformanceAtAnyGivenTime ",e);
		}
	}
	@Test(priority = 2,enabled = true)
	public void attemptPracticeTestFromMetacognitiveReportPage()
	{
		try
		{
			Driver.startDriver();
			String chapter1= ReadTestData.readDataByTagName("chapternames", "chapter1", "0");
			new LoginUsingLTI().ltiLogin("848");//login as student
			new DiagnosticTest().startTest(0,3);//select 1st chapter for test
			new DiagnosticTest().attemptAllCorrect(4, false, false);		//attempt correctly
			new Navigator().NavigateToStudentReport();//navigate to my report
			new Navigator().NavigateToReport("Metacognitive Report");
			//mouse hover the dot 
			new MetacognitiveReport().mouseHoverOnDot(0);	
			String text = Driver.driver.findElement(By.className("tooltip-label-subsection-wrapper")).getText();
			String trimmedText = text.replaceAll("[\n\r]", "");
			String profPercent = trimmedText.substring(13, 15);
			int percent = Integer.parseInt(profPercent);
			Thread.sleep(2000);
			new MetacognitiveReport().openPracticeTestFromMetacognitiveReportPage(chapter1);		//open practice test
			for(int i = 0; i < 12; i++)
				new PracticeTest().attemptQuestion("incorrect", 4, false, false);
			new Navigator().NavigateToStudentReport();
			//mouse hover the dot 
			new MetacognitiveReport().mouseHoverOnDot(0);	
			String text1 = Driver.driver.findElement(By.className("tooltip-label-subsection-wrapper")).getText();
			String trimmedText1 = text1.replaceAll("[\n\r]", "");
			String profPercent1 = trimmedText1.substring(13, 15);
			int percent1 = Integer.parseInt(profPercent1);
			if(percent1 > percent)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("proficiency is not updated for the chapter when we attempt the practice test from metacognitive report page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase attemptPracticeTestFromMetacognitiveReportPage in class CompareSelfRatingWithActualPerformanceAtAnyGivenTime ",e);
		}
	}

	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}

}
