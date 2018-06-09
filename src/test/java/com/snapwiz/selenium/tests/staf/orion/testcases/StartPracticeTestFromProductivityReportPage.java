package com.snapwiz.selenium.tests.staf.orion.testcases;

import org.openqa.selenium.By;
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

public class StartPracticeTestFromProductivityReportPage {
@Test
	public void startPracticeTestFromProductivityReportPage()
	{
		try
		{
			Driver.startDriver();
			String chapter1= ReadTestData.readDataByTagName("chapternames", "chapter1", "0");
			new LoginUsingLTI().ltiLogin("890");
			new DiagnosticTest().startTest(0,3);//select 1st chapter for test
			new DiagnosticTest().attemptAllCorrect(4, false, false);		//attempt correctly
			new Navigator().NavigateToStudentReport();
			new Navigator().NavigateToReport("Productivity Report");
			new CourseProductivityReport().openPracticeTestFromCourseProductivityReportPage(chapter1);
			String assessmentTitle = Driver.driver.findElement(By.className("al-diag-test-title")).getText();
			if(!assessmentTitle.contains("Practice"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Unable to start practice test from productivity report page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase startPracticeTestFromProductivityReportPage in class StartPracticeTestFromProductivityReportPage ",e);
		}
	}
@AfterMethod
	public void tearDown() throws Exception
		{
			Driver.driver.quit();
		}
}
