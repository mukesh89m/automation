package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.CourseProductivityReport;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class VerifyUIOfCourseProductivityReport {
@Test	
	public void verifyUIOfCourseProductivityReport()
	{
		try
		{
			Driver.startDriver();
			String chapter1= ReadTestData.readDataByTagName("chapternames", "chapter1", "0");
			new LoginUsingLTI().ltiLogin("885");
			new DiagnosticTest().startTest(0,3);//select 1st chapter for test
			new DiagnosticTest().attemptAllCorrect(4, false, false);		//attempt correctly
			new Navigator().NavigateToStudentReport();
			new Navigator().NavigateToReport("Productivity Report");
			String reportHeader = Driver.driver.findElement(By.className("al-student-report-title-section")).getText();
			if(!reportHeader.contains("Productivity Report ( Chapters )"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The report header does not have the text 'Productivity Report ( Chapters )'.");
			}
			//find the graph
			int graph = Driver.driver.findElements(By.id("al-productivity-report-img-block")).size();
			if(graph != 1)
				Assert.fail("The scattered plot is missing in the productivity report page.");
			//find text in table below the graph
			String tableHeader = Driver.driver.findElement(By.className("al-report-chapter-performance-content-header-row")).getText();
			if(!tableHeader.contains("Chapters"))
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
			//check for chapter icon in table header
			String chapterIcon = Driver.driver.findElement(By.cssSelector("img[title='Chapters']")).getAttribute("src");
			if(!chapterIcon.contains("chapters-icon.png"))
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
			//attempted chapter names in the table 
			String chapterNames = Driver.driver.findElement(By.cssSelector("span[class='al-terminal-objective-title']")).getText();
			if(!chapterNames.equals(chapter1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Chapters attempted by the student in the course is not listed.");
			}
			
			//check for time spent column in the table
			String time = Driver.driver.findElement(By.cssSelector("div[class='al-preformance-text']")).getText();
			if(!time.contains("min"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Time spent for each chapter is not present under the Time Spent column.");
			}
			
			//mouse hover over study and practice link
			WebElement studyLink = Driver.driver.findElement(By.cssSelector("g.highcharts-markers > path"));
			Locatable hoverItem = (Locatable) studyLink;
			Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
			mouse.mouseMove(hoverItem.getCoordinates());
			Thread.sleep(2000);
			//find the study and practice text
			String studyPracticeLink = Driver.driver.findElement(By.className("al-preformance-links")).getText();
			if(!studyPracticeLink.contains("Study"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The 'Study' link is absent on mouse hovering .");
			}
			
			if(!studyPracticeLink.contains("Practice"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The 'Practice' link is absent on mouse hovering .");
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
			
			//find the dots present on graph
			int dots = Driver.driver.findElements(By.cssSelector("g.highcharts-markers > path")).size();
			if(dots != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The colored dot is absent in the scattered plot in productivity report page.");
			}
			
			//list the color tag of the proficiency bar
			List<WebElement> allProficiencyBarColor = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'al-proficiency-content al-proficiency-')]"));
			
			if(!allProficiencyBarColor.get(0).getAttribute("class").contains("green"))
			 {
				 new Screenshot().captureScreenshotFromTestCase();
				 Assert.fail("The color of the dot in scattered plot is not according to the proficiency bar color.");
			 }
			
			
			//mouse hover on the dot
			new CourseProductivityReport().mouseHoverOnDot(1);
			
			//find the text after hovering over the dots
			String text = Driver.driver.findElement(By.className("tooltip-label")).getText();
			String text1 = Driver.driver.findElement(By.className("tooltip-label-subsection-wrapper")).getText();
			if(!text.equals(chapter1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On mouse hovering the dot the chapter name is not displayed in course productivity report page.");
			}
			
			if(!text1.contains("Proficiency:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On mouse hovering the dot the proficiency is not displayed in course productivity report page.");
			}
			
			if(!text1.contains("Time Spent:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On mouse hovering the dot the proficiency is not displayed in course productivity report page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyUIOfCourseProductivityReport in class VerifyUIOfCourseProductivityReport ",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
