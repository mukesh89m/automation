package com.snapwiz.selenium.tests.staf.orion.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.FetchValue;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.RunScheduledJobs;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class MentorShowPerformanceReport
{
	@Test
	public void mentorShowPerformanceReport()
	{
		try
		{
			Driver.startDriver();
			String totalnumberofquestion=ReadTestData.readDataByTagName("", "totalnoofquestion", "1079");
			new LoginUsingLTI().ltiLogin("1079");//login as student 
			new SelectChapterForTest().selectchapterfortest(0, 2);
			new DiagnosticTest().attemptAllCorrect(2, false, false);//attempt all question correctly
			new RunScheduledJobs().runScheduledJobs();
			new LoginUsingLTI().ltiLogin("10791");//login as instructor
			new Navigator().NavigateToInstructorReport();
			int chaptershown=new FetchValue().fetchlistvaluebyclassname("idb-chapter-performance-content-body-wrapper");//fetch number of chapter shown
			if(chaptershown>2)
			{
				Assert.fail("more than one chapter shown");
			}
			String performance=new FetchValue().fetchvaluebyclassname("idb-preformance-text", "attribute", "title");//check deviser present or not
			if(!performance.contains("/"+totalnumberofquestion))
			{
				Assert.fail("total question and correct question count not shown");
			}
			Driver.driver.findElement(By.cssSelector("a[id='idb-most-challenging-activities-tlo']")).click();//click on view by objectives
			Thread.sleep(2000);
			int tloshown=new FetchValue().fetchlistvaluebyclassname("idb-terminal-objective-title");//fetch all tlo name
			if(tloshown<2)
			{
				Assert.fail("tlo not shown which student attempt");
			}
			
			String performancetlo=new FetchValue().fetchvaluebyclassname("idb-preformance-text", "attribute", "title");//check deviser present or not
			System.out.println(performancetlo);
			if(!performancetlo.contains("/"+totalnumberofquestion))
			{
				Assert.fail("total question and correct question count  of tlo not shown");
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
