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

public class StudentAbleToAccessVariousReport 
{

	@Test
	public void studentAbleToAccessVariousReport()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("843");//login as student
			new Navigator().NavigateToStudentReport();//navigate to my report
			String activitypagetext=Driver.driver.findElement(By.id("al-most-challenging-activities-report-title")).getText();//check most challenging activity page
			if(!activitypagetext.contains("Most Challenging Activities"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("its not a most challenging activity page");
			}
			String pagetext=Driver.driver.findElement(By.className("no_most_challenging_data_message")).getAttribute("innerHTML");
			System.out.println(pagetext);
			if(!pagetext.contains("No most challenging activities data"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("when student going to 1st time to my report its not shown 'No most challenging activities data '");
			}
			new Navigator().orionDashboard();
			new SelectChapterForTest().selectchapterfortest(0,2);//select chapter for diagonestic test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 14, "correct", false, false, true);//attempt diagonestic test
			new Navigator().NavigateToStudentReport();
			int proficiency=Driver.driver.findElements(By.className("al-proficiency-percentage")).size();
			if(proficiency<1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("after attempt daigonstic test and come to my report activity not shown in a most challenging activity page.");
			}
			
		}
		catch (Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase studentAbleToAccessVariousReport in class StudentAbleToAccessVariousReport ",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
