package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.FetchValue;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.MouseHover;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.RunScheduledJobs;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class InstructorShowMostChallengingLearningObjective
{
	@Test(priority=1,enabled=true)
	public void instructorShowMostChallengingLearningObjective()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("9382");//login as student
			new SelectChapterForTest().selectchapterfortest(0, 2);
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 6, "correct", false, false, true);
			new RunScheduledJobs().runScheduledJobs();
			new LoginUsingLTI().ltiLogin("938");//login as instructor
			new Navigator().NavigateToInstructorReport();
			boolean performanceofchapter=Driver.driver.findElement(By.className("idb-chapter-performance-and-proficiency-block")).isDisplayed();
			if(performanceofchapter==false)
			{
				Assert.fail("performance of chapter not shown");
			}
			boolean viewbychapter=Driver.driver.findElement(By.id("idb-most-challenging-activities-chapters")).isDisplayed();
			if(viewbychapter==false)
			{
				Assert.fail("view by chapter tab not shown");
			}
			boolean viewbyobjective=Driver.driver.findElement(By.id("idb-most-challenging-activities-tlo")).isDisplayed();
			if(viewbyobjective==false)
			{
				Assert.fail("view by objective tab not shown");
			}
			boolean pin=Driver.driver.findElement(By.className("idb-proficiency-pin")).isDisplayed();
			if(pin==false)
			{
				Assert.fail("pin not display");
			}
			Driver.driver.findElement(By.id("idb-most-challenging-activities-tlo")).click();
			Thread.sleep(2000);
			List<WebElement> allchapteerprof=Driver.driver.findElements(By.className("idb-terminal-objective-title"));
			int chpterdisplay=allchapteerprof.size();
			if(chpterdisplay==0)
			{
				Assert.fail("TLO not shown with proficency");
			}
			new LoginUsingLTI().ltiLogin("9381");//login as student
			new Navigator().NavigateToInstructorReport();
			int performancereportforotherclass=Driver.driver.findElements(By.className("idb-chapter-performance-and-proficiency-block")).size();
			if(performancereportforotherclass!=0)
			{
				Assert.fail("for other class the performance report shown ."); 
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase instructorShowMostChallengingLearningObjective in class InstructorShowMostChallengingLearningObjective",e);
		}
	}
	@Test(priority=2,enabled=true)
	public void pinofchapter()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("947");//login as student 1
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for diag test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 3, "correct", false, false,true);
			new RunScheduledJobs().runScheduledJobs();
			new LoginUsingLTI().ltiLogin("9472");//login as instructor
			new Navigator().NavigateToInstructorReport();
			int pincount=new FetchValue().fetchlistvaluebyclassname("idb-proficiency-pin");
			new LoginUsingLTI().ltiLogin("9471");//login as student 2
			new SelectChapterForTest().selectchapterfortest(1, 2);
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 3, "correct", false, false,true);
			new RunScheduledJobs().runScheduledJobs();
			new LoginUsingLTI().ltiLogin("9472");//login as instructor
			new Navigator().NavigateToInstructorReport();
			int pincount1=new FetchValue().fetchlistvaluebyclassname("idb-proficiency-pin");//fetch number of pin
			if(pincount1<pincount)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("pin count not increase after two student attempt two different chapter diag test");
			}
			MouseHover.mouserhover("idb-proficiency-pin");
			String  hovertext=new FetchValue().fetchvaluebyclassname("idb-proficiency-pin", "attribute","tooltip");//fetch hover  text
			if(!hovertext.contains("Class Average Starting Point"))
			{
				Assert.fail("hover text not shown");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase pinofchapter in class InstructorShowMostChallengingLearningObjective",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
