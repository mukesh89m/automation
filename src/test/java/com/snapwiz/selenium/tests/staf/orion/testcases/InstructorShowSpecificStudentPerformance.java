package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.RunScheduledJobs;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;
import com.snapwiz.selenium.tests.staf.orion.uihelper.PerformanceReport;

public class InstructorShowSpecificStudentPerformance 
{
	@Test(priority=1,enabled=true)
	public void instructorShowSpecificStudentPerformance()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("999");//login as student
			new SelectChapterForTest().selectchapterfortest(0, 2);
			new DiagnosticTest().attemptAllCorrect(2, false, false);
			new RunScheduledJobs().runScheduledJobs();
			new LoginUsingLTI().ltiLogin("9991");//login as instructor
			new Navigator().NavigateToInstructorReport();
			new PerformanceReport().performancereport();
			List<WebElement> studentsname=Driver.driver.findElements(By.className("al-terminal-objective-title"));
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",studentsname.get(0));//click on student name
			Thread.sleep(2000);
			String headingtext=Driver.driver.findElement(By.className("ir-course-performance-heading")).getText();//fetch text from spage section2
			System.out.println(headingtext);
			if(!headingtext.contains("Course Performance"))
			{
				Assert.fail("name of report not change");
			}
			if(!headingtext.contains(Config.givenname))
			{
				Assert.fail("student family name not shown");
			}
			if(!headingtext.contains("All"))
			{
				Assert.fail("all link not shown. ");
			}
			int studentrowcount=Driver.driver.findElements(By.id("al-report-content-body-row")).size();
			if(studentrowcount!=1)
			{
				Assert.fail("more than 1 student row shown.");
			}
			String additionalinfo=Driver.driver.findElement(By.className("idb-perf-report-additional-info-section-outer-wrapper")).getText();
			System.out.println(additionalinfo);
			if(!additionalinfo.contains("Course Proficiency"))
			{
				Assert.fail("course proficiency not shown ");
			}
			if(!additionalinfo.contains("Course Proficiency"))
			{
				Assert.fail("Course Proficiency not shown" );
			}
			if(!additionalinfo.contains("Login Count"))
			{
				Assert.fail("Login count not shown");
			}
			if(!additionalinfo.contains("Participation Score"))
			{
				Assert.fail("participation score not shown");
			}
			boolean graph=Driver.driver.findElement(By.className("highcharts-tracker")).isDisplayed();
			System.out.println(graph);
			
			
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in class InstructorShowSpecificStudentPerformance in testcase instructorShowSpecificStudentPerformance",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void instructorShowStudentPerformanceinspecificchapter()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1000");
			new Navigator().NavigateToInstructorReport();
			new PerformanceReport().performancereport();
			Driver.driver.findElement(By.className("coursePerformanceByChapters-xAxisLabel")).click();
			Thread.sleep(2000);
			boolean classbyobj=Driver.driver.findElement(By.id("ir-performance-report-title")).isDisplayed();
			if(classbyobj==false)
			{
				Assert.fail("class performance by objective not shown");
			}
			boolean classbystudent=Driver.driver.findElement(By.className("ir-course-performance-heading")).isDisplayed();
			if(classbystudent==false)
			{
				Assert.fail("chapter performance by objective not shown");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in class InstructorShowSpecificStudentPerformance in testcase instructorShowStudentPerformanceinspecificchapter",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}

}
