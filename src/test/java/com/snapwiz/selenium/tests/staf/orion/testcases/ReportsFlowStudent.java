package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Opacity;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Reports;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.uihelper.LearnMoreReport;

public class ReportsFlowStudent {
	
	@Test(priority = 1, enabled = true)
	public void productivityReport()
	{
		try
		{
			Driver.startDriver();
			//DBConnect.dropMongo();			
			new LoginUsingLTI().ltiLogin("541");
			new DiagnosticTest().startTest(0, 4);
			new DiagnosticTest().attemptAllCorrect(3, false, false);
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(1, "correct", 3, false, false);
			new PracticeTest().quitTestAndGoToDashboard();
			Thread.sleep(3000);
			
			new Navigator().NavigateToStudentReport();
			new Navigator().NavigateToReport("Productivity Report");
			
			//Validating Productivity Report report description
			if(!Driver.driver.findElement(By.className("al-reports-description")).getText().contains("The Productivity Report compares the Time Spent to your overall Proficiency in ORION."))
				Assert.fail("The student side productivity report description is not as expected");
			List<WebElement> subdesc = Driver.driver.findElements(By.className("al-reports-sub-description"));
			if(!subdesc.get(0).getText().contains("Hover over a point in the graph to see chapter details. You can then click on Study or Practice for that chapter."))
				Assert.fail("The student side productivity report sub description at point no 1 is not as expected");
			if(!subdesc.get(1).getText().contains("Click on a point in the graph to see your Productivity results by chapter objectives. You can then click on a point to Study or Practice specific objectives."))
				Assert.fail("The student side productivity report sub description at point no 2 is not as expected");
			new LearnMoreReport().learnMoreLinkClick("productivity-report-chapters");
			new LearnMoreReport().closeLearnMorePage();			
			Driver.driver.findElement(By.cssSelector("g.highcharts-markers > path")).click(); //clicking on chapter marker on the report graph
			new Navigator().orionDashboard();
			new Navigator().NavigateToStudentReport();
			System.out.println(Driver.driver.findElement(By.className("al-student-report-title-section")).getText());
			if(!Driver.driver.findElement(By.className("al-student-report-title-section")).getText().contains("Productivity Report ( Chapters )"))
				Assert.fail("Student not landed on chapter level productivity report.");
			
			//Performance Report
			
			new Navigator().NavigateToReport("Performance Report");
			Driver.driver.findElement(By.cssSelector("g.highcharts-tracker > g > rect")).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("g.highcharts-tracker > g > rect")).click();
			new Navigator().orionDashboard();
			new Navigator().NavigateToStudentReport();
			if(!Driver.driver.findElement(By.className("al-performance-chart-title")).getText().equalsIgnoreCase("Course Performance Summary"))
				Assert.fail("Not landed to Course Performance page on again opening navigating to My Reports");
			
			//Metacoginitive Report
			Driver.driver.findElement(By.linkText("Performance Report")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText("Metacognitive Report")).click();
			
			if(!Driver.driver.findElement(By.className("al-reports-description")).getText().contains("The Metacognitive Report compares your Proficiency in ORION to your confidence level. Metacognition is the awareness and understanding of your own thought process."))
				Assert.fail("The student side Metacognitive report description is not as expected");
			List<WebElement> subdesc_meta = Driver.driver.findElements(By.className("al-reports-sub-description"));
			if(!subdesc_meta.get(0).getText().contains("Hover over a point in the graph to see chapter details. You can decide to Study or Practice on that chapter."))
				Assert.fail("The student side Metacognitive report sub description at point no 1 is not as expected");
			if(!subdesc_meta.get(1).getText().contains("Click on a point in the graph to see your Proficiency results by chapter objectives. You can then click on a point to Study or Practice specific objectives."))
				Assert.fail("The student side Metacognitive report sub description at point no 2 is not as expected");
			
			
			new LearnMoreReport().learnMoreLinkClick("metacognitive-report-chapters");
			new LearnMoreReport().closeLearnMorePage();
			
			Driver.driver.findElement(By.cssSelector("g.highcharts-markers > path")).click();
			
			new Navigator().orionDashboard();
			new Navigator().NavigateToStudentReport();
			System.out.println(Driver.driver.findElement(By.className("al-student-report-title-section")).getText());
			if(!Driver.driver.findElement(By.className("al-student-report-title-section")).getText().contains("Metacognitive Report (Chapters)"))
				Assert.fail("Student not landed on chapter level metacognitive report.");
			
			//Most Challenging Activity
			new Navigator().NavigateToReport("Most Challenging Activities");
			
			if(!Driver.driver.findElement(By.className("al-reports-description")).getText().contains("The Most Challenging Activities report in ORION sorts objectives and chapters by Proficiency,starting with the lowest."))
			Assert.fail("The description of Most Challenging Activities report is not as expected");
			
			new LearnMoreReport().learnMoreLinkClick("most-challenging-activity");
			new LearnMoreReport().closeLearnMorePage();
			
			new Navigator().orionDashboard();
			new Navigator().NavigateToStudentReport();
			if(!Driver.driver.findElement(By.className("al-report-header-title")).getText().contains("Most Challenging Activities"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Not landed to Most Challenging Activities reports after again navigating to it.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase productivityReport in class ReportsFlowStudent",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void metacognitiveECF2Variation()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("868");
			new DiagnosticTest().startTest(0, 3);
			new DiagnosticTest().DiagonesticTestQuitBetween(0, 3, "correct", false, false, true);
			new Navigator().NavigateToStudentReport();
			new Navigator().NavigateToReport("Metacognitive Report");
			Thread.sleep(3000);
			Driver.driver.findElement(By.className("al-terminal-objective-title")).click();
			Thread.sleep(3000);
			List<Double> values = new Opacity().opacities();
			System.out.println(values);
			for(Double val : values)
			{
			if(!(val > 0.25))
				Assert.fail("The opacity is not greater than 0.25 after attempting 3 questions");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase metacognitiveECFVariation in class ReportsFlowStudent",e);
		}
	}

	@Test(priority = 3, enabled = true)
	public void metacognitiveECF5Variation()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("869");
			new DiagnosticTest().startTest(0, 3);
			new DiagnosticTest().DiagonesticTestQuitBetween(0, 5, "correct", false, false, true);
			new Navigator().NavigateToStudentReport();
			new Navigator().NavigateToReport("Metacognitive Report");
			Thread.sleep(3000);
			Driver.driver.findElement(By.className("al-terminal-objective-title")).click();
			Thread.sleep(3000);
			List<Double> values = new Opacity().opacities();
			System.out.println(values);
			for(Double val : values)
			{
			if(!(val > 0.40))
				Assert.fail("The opacity is not greater than 0.25 after attempting 3 questions");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase metacognitiveECFVariation in class ReportsFlowStudent",e);
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void metacognitiveThirdQuadrantAllIncorrectConfidence1()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("873");
			new DiagnosticTest().startTest(0, 1);
			new DiagnosticTest().DiagonesticTestQuitBetween(1, 1, "incorrect", false, false, true);
			new Navigator().orionDashboard();
			new PracticeTest().startTLOLevelPracticeTest(1);
			for(int i =0; i<15;i++)
			new PracticeTest().attemptQuestion("incorrect", 1, false, false);
			new PracticeTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentReport();
			new Navigator().NavigateToReport("Metacognitive Report");
			Thread.sleep(3000);
			int quadrant = new Reports().quadrantCheck(0);
			if(quadrant != 3) Assert.fail("Marker for chapter not plotted in third quadrant");
			Driver.driver.findElement(By.className("al-terminal-objective-title")).click();
			Thread.sleep(3000);			
			quadrant = new Reports().quadrantCheck(0);
			if(quadrant != 3) Assert.fail("Marker for TLO not plotted in third quadrant");			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase metacognitiveThirdQuadrantAllIncorrectConfidence1 in class ReportsFlowStudent",e);
		}
	}
	
	@Test(priority = 5, enabled = true)
	public void metacognitiveSecondQuadrantAllCorrectConfidence1()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("874");
			new DiagnosticTest().startTest(0, 1);
			new DiagnosticTest().DiagonesticTestQuitBetween(1, 1, "correct", false, false, true);
			new Navigator().orionDashboard();
			new PracticeTest().startTLOLevelPracticeTest(0);
			for(int i =0; i<35;i++)
			new PracticeTest().attemptQuestion("correct", 1, false, false);
			new PracticeTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentReport();
			new Navigator().NavigateToReport("Metacognitive Report");
			Thread.sleep(3000);
			int quadrant = new Reports().quadrantCheck(0);
			if(quadrant != 2) Assert.fail("Marker for chapter not plotted in second quadrant");
			Driver.driver.findElement(By.className("al-terminal-objective-title")).click();
			Thread.sleep(3000);			
			quadrant = new Reports().quadrantCheck(0);
			if(quadrant != 2) Assert.fail("Marker for TLO not plotted in second quadrant");			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase metacognitiveSecondQuadrantAllCorrectConfidence1 in class ReportsFlowStudent",e);
		}
	}
	
	@Test(priority = 6, enabled = true)
	public void metacognitiveThirdQuadrantAllIncorrectConfidence2()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("875");
			new DiagnosticTest().startTest(0, 2);
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 1, "incorrect", false, false, true);
			new Navigator().orionDashboard();
			new PracticeTest().startTLOLevelPracticeTest(0);
			for(int i =0; i<35;i++)
			new PracticeTest().attemptQuestion("incorrect", 2, false, false);
			new PracticeTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentReport();
			new Navigator().NavigateToReport("Metacognitive Report");
			Thread.sleep(3000);
			int quadrant = new Reports().quadrantCheck(0);
			if(quadrant != 3) Assert.fail("Marker for chapter not plotted in third quadrant");
			Driver.driver.findElement(By.className("al-terminal-objective-title")).click();
			Thread.sleep(3000);			
			quadrant = new Reports().quadrantCheck(0);
			if(quadrant != 3) Assert.fail("Marker for TLO not plotted in third quadrant");			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase metacognitiveThirdQuadrantAllIncorrectConfidence2 in class ReportsFlowStudent",e);
		}
	}
	
	@Test(priority = 7, enabled = true)
	public void metacognitiveSecondQuadrantAllCorrectConfidence2()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("876");
			new DiagnosticTest().startTest(0, 2);
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 1, "correct", false, false, true);
			new Navigator().orionDashboard();
			new PracticeTest().startTLOLevelPracticeTest(0);
			for(int i =0; i<35;i++)
			new PracticeTest().attemptQuestion("correct", 2, false, false);
			new PracticeTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentReport();
			new Navigator().NavigateToReport("Metacognitive Report");
			Thread.sleep(3000);
			int quadrant = new Reports().quadrantCheck(0);
			if(quadrant != 2) Assert.fail("Marker for chapter not plotted in second quadrant");
			Driver.driver.findElement(By.className("al-terminal-objective-title")).click();
			Thread.sleep(3000);			
			quadrant = new Reports().quadrantCheck(0);
			if(quadrant != 2) Assert.fail("Marker for TLO not plotted in second quadrant");			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase metacognitiveSecondQuadrantAllCorrectConfidence2 in class ReportsFlowStudent",e);
		}
	}
	
	@Test(priority = 8, enabled = true)
	public void metacognitiveFourthQuadrantAllInCorrectConfidence3()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("877");
			new DiagnosticTest().startTest(0, 3);
			new DiagnosticTest().DiagonesticTestQuitBetween(3, 1, "incorrect", false, false, true);
			new Navigator().orionDashboard();
			new PracticeTest().startTLOLevelPracticeTest(0);
			for(int i =0; i<35;i++)
			new PracticeTest().attemptQuestion("incorrect", 3, false, false);
			new PracticeTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentReport();
			new Navigator().NavigateToReport("Metacognitive Report");
			Thread.sleep(3000);
			int quadrant = new Reports().quadrantCheck(0);
			if(quadrant != 4) Assert.fail("Marker for chapter not plotted in fourth quadrant");
			Driver.driver.findElement(By.className("al-terminal-objective-title")).click();
			Thread.sleep(3000);			
			quadrant = new Reports().quadrantCheck(0);
			if(quadrant != 4) Assert.fail("Marker for TLO not plotted in fourth quadrant");			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase metacognitiveFourthQuadrantAllInCorrectConfidence3 in class ReportsFlowStudent",e);
		}
	}
	
	@Test(priority = 9, enabled = true)
	public void metacognitiveFirstQuadrantAllCorrectConfidence3()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("878");
			new DiagnosticTest().startTest(0, 3);
			new DiagnosticTest().DiagonesticTestQuitBetween(3, 1, "correct", false, false, true);
			new Navigator().orionDashboard();
			new PracticeTest().startTLOLevelPracticeTest(0);
			for(int i =0; i<35;i++)
			new PracticeTest().attemptQuestion("correct", 3, false, false);
			new PracticeTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentReport();
			new Navigator().NavigateToReport("Metacognitive Report");
			Thread.sleep(3000);
			int quadrant = new Reports().quadrantCheck(0);
			if(quadrant != 1) Assert.fail("Marker for chapter not plotted in first quadrant");
			Driver.driver.findElement(By.className("al-terminal-objective-title")).click();
			Thread.sleep(3000);			
			quadrant = new Reports().quadrantCheck(0);
			if(quadrant != 1) Assert.fail("Marker for TLO not plotted in first quadrant");			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase metacognitiveFourthQuadrantAllInCorrectConfidence3 in class ReportsFlowStudent",e);
		}
	}
	
	@Test(priority = 10, enabled = true)
	public void metacognitiveFourthQuadrantAllInCorrectConfidence4()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("879");
			new DiagnosticTest().startTest(0, 4);
			new DiagnosticTest().DiagonesticTestQuitBetween(4, 1, "incorrect", false, false, true);
			new Navigator().orionDashboard();
			new PracticeTest().startTLOLevelPracticeTest(0);
			for(int i =0; i<35;i++)
			new PracticeTest().attemptQuestion("incorrect",4, false, false);
			new PracticeTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentReport();
			new Navigator().NavigateToReport("Metacognitive Report");
			Thread.sleep(3000);
			int quadrant = new Reports().quadrantCheck(0);
			if(quadrant != 4) Assert.fail("Marker for chapter not plotted in fourth quadrant");
			Driver.driver.findElement(By.className("al-terminal-objective-title")).click();
			Thread.sleep(3000);			
			quadrant = new Reports().quadrantCheck(0);
			if(quadrant != 4) Assert.fail("Marker for TLO not plotted in fourth quadrant");			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase metacognitiveFourthQuadrantAllInCorrectConfidence3 in class ReportsFlowStudent",e);
		}
	}
	
	@Test(priority = 11, enabled = true)
	public void metacognitiveFirstQuadrantAllCorrectConfidence4()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("880");
			new DiagnosticTest().startTest(0, 4);
			new DiagnosticTest().DiagonesticTestQuitBetween(4, 1, "correct", false, false, true);
			new Navigator().orionDashboard();
			new PracticeTest().startTLOLevelPracticeTest(0);
			for(int i =0; i<35;i++)
			new PracticeTest().attemptQuestion("correct",4, false, false);
			new PracticeTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentReport();
			new Navigator().NavigateToReport("Metacognitive Report");
			Thread.sleep(3000);
			int quadrant = new Reports().quadrantCheck(0);
			if(quadrant != 1) Assert.fail("Marker for chapter not plotted in fourth quadrant");
			Driver.driver.findElement(By.className("al-terminal-objective-title")).click();
			Thread.sleep(3000);			
			quadrant = new Reports().quadrantCheck(0);
			if(quadrant != 1) Assert.fail("Marker for TLO not plotted in fourth quadrant");			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase metacognitiveFourthQuadrantAllInCorrectConfidence3 in class ReportsFlowStudent",e);
		}
	}
	
	@AfterMethod
	public void TearDown()throws Exception
	{
		new Screenshot().captureScreenshotFromTestCase();
		Driver.driver.quit();
	}

}
