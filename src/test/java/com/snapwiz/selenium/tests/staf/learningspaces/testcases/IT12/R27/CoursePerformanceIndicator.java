package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R27;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;

public class CoursePerformanceIndicator extends Driver {
	
	@Test(priority = 1, enabled = true)
	public void coursePerformanceIndicator()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("118");//login as instructor
			int coursePerfTile = driver.findElements(By.cssSelector("section[id='statistics']")).size();
			if(coursePerfTile != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The course performance indicators at left bottom side of the dashboard is absent.");
			}
			String text1 = driver.findElement(By.cssSelector("div[class='box class-participation-score']")).getText();
			if(!text1.contains("AVG CLASS PARTICIPATION"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The course performance indicators doesnt have 'AVG CLASS PARTICIPATION' tile.");
			}
			String text2 = driver.findElement(By.cssSelector("div[class='box avg-time-spent']")).getText();
			if(!text2.contains("AVG TIME SPENT"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The course performance indicators doesnt have 'AVG TIME SPENT' tile.");
			}
			String text3 = driver.findElement(By.cssSelector("div[class='box avg-practice-performance']")).getText();
			if(!text3.contains("AVG QUESTION PERFORMANCE"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The course performance indicators doesnt have 'AVG QUESTION PERFORMANCE' tile.");
			}
			String text4 = driver.findElement(By.cssSelector("div[class='box avg-practice-questions']")).getText();
			if(!text4.contains("AVG QUESTIONS ATTEMPTED"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The course performance indicators doesnt have 'AVG PRACTICE ATTEMPTED' tile.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase coursePerformanceIndicator in class CoursePerformanceIndicator.",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void clickOnAvgTimeSpentTileForLSPlusAdaptiveCourse()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("134");//login as instructor
			driver.findElement(By.cssSelector("div.box.avg-time-spent > div.percent")).click();//click On Avg Time Spent Tile
			Thread.sleep(2000);
			String url = driver.getCurrentUrl();
			if(!url.contains("instProdRepo"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Avg Time Spent tile the instructor doesn't navigate to Productivty Report Page for LS+Adaptive Course type.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase clickOnAvgTimeSpentTileForLSPlusAdaptiveCourse in class CoursePerformanceIndicator.",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void clickOnAvgTimeSpentTileForLSCourse()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("135");//login as instructor
			driver.findElement(By.cssSelector("div.box.avg-time-spent > div.percent")).click();//click On Avg Time Spent Tile
			Thread.sleep(2000);
			String url = driver.getCurrentUrl();
			if(!url.contains("instPerfRepo"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Avg Time Spent tile the instructor doesn't navigate to Performance Report Page For LS Course type.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase clickOnAvgTimeSpentTileForLSCourse in class CoursePerformanceIndicator.",e);
		}
	}

}
