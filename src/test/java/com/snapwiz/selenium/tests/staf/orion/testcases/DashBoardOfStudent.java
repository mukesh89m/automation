package com.snapwiz.selenium.tests.staf.orion.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class DashBoardOfStudent 
{
	/*
	 * @brajesh
	 * student dash board after attempting the test
	 */
	@Test
	public void dashboard()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("419");//Login as student
			new SelectChapterForTest().selectchapterfortest(0, 2);
			new DiagnosticTest().attemptAllCorrect(2, false, false);
			new Navigator().orionDashboard();
			Thread.sleep(2000);
			int proficiency=Driver.driver.findElements(By.className("al-content-body-second")).size();//proficiency of TLO
			if(proficiency<1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("proficiency grapgh not shown");				
			}
			int performance=Driver.driver.findElements(By.className("al-content-body-third")).size();//Performance of TLO
			if(performance<1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("performance grapgh not shown");				
			}
			boolean mosttimespent=Driver.driver.findElement(By.className("highcharts-container")).isDisplayed();//Most time spent
			if(mosttimespent==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("mosttimespent time spent not shown");				
			}
			boolean leastproficiency=Driver.driver.findElement(By.className("al-recommended-focus-area-chart-section")).isDisplayed();//least proficiency chapter 
			if(leastproficiency==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("leastproficiency graph not shown");				
			}
			new LoginUsingLTI().ltiLogin("4191");//login as same student of different section
			int proficiency1=Driver.driver.findElements(By.className("al-content-body-second")).size();//proficiency of TLO
			if(proficiency1<1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("proficiency grapgh not shown1");				
			}
			int performance1=Driver.driver.findElements(By.className("al-content-body-third")).size();//Performance of TLO
			if(performance1<1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("performance grapgh not shown1");				
			}
			boolean mosttimespent1=Driver.driver.findElement(By.className("highcharts-container")).isDisplayed();//Most time spent
			if(mosttimespent1==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("mosttimespent time spent not shown1");				
			}
			boolean leastproficiency1=Driver.driver.findElement(By.className("al-recommended-focus-area-chart-section")).isDisplayed();//least proficiency chapter 
			if(leastproficiency1==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("leastproficiency graph not shown1");				
			}
			
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase DashBoard in class DashBoardOfStudent",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
	
}
