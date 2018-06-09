package com.snapwiz.selenium.tests.staf.orion.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;
import com.snapwiz.selenium.tests.staf.orion.uihelper.ComboBox;

public class CoursePerformanceAlongWithQuestionCardInPerformancepage 
{
	@Test
	public void coursePerformanceAlongWithQuestionCardInPerformancepage()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("421");//login as student
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().attemptAllCorrect(3, false, false);//attempt diagonestic  test
			new Navigator().orionDashboard();//navigate to dashboard
			Thread.sleep(2000);
			new PracticeTest().startTest();//Start practice test
			new PracticeTest().practiceTestAttempt(2, 6, "correct", false, false);
			new Navigator().NavigateToStudentReport();
			Driver.driver.findElement(By.id("al-performance-report")).click();//click on performance report link
			Thread.sleep(2000);
			int questioncard=Driver.driver.findElements(By.cssSelector("div[class='report-sidebar-question-card-sectn question-card-green']")).size();
			if(questioncard<1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("question card not shown");
			}
			boolean performance=Driver.driver.findElement(By.className("al-performance-chart-label")).isDisplayed();
			if(performance==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("performance report not shown");
			}
			new ComboBox().selectValue(4, "Productivity Report");
			boolean productivitychart=Driver.driver.findElement(By.className("al-productivity-chart-block")).isDisplayed();
			if(productivitychart==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("productivitychart report not shown");
			}
			Driver.driver.findElement(By.id("al-metacognitive-report")).click();//click on metacognitive report link
			Thread.sleep(2000);
			boolean metacognity=Driver.driver.findElement(By.id("al-metacognitive-report-display-block")).isDisplayed();
			if(metacognity==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("metacognity report not shown");
			}
			Driver.driver.findElement(By.id("al-most-challenging-activity")).click();//click on most-challenging-activity report link
			Thread.sleep(2000);
			boolean challengingactivty=Driver.driver.findElement(By.id("al-most-challenging-activities-tlo")).isDisplayed();
			if(challengingactivty==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("challengingactivty report not shown");
			}
			//login as same student with different section
			new LoginUsingLTI().ltiLogin("4211");
			new Navigator().NavigateToStudentReport();
			Driver.driver.findElement(By.id("al-performance-report")).click();//click on performance report link
			Thread.sleep(2000);
			int questioncard1=Driver.driver.findElements(By.cssSelector("div[class='report-sidebar-question-card-sectn question-card-green']")).size();
			if(questioncard1<1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("question card1 not shown");
			}
			boolean performance1=Driver.driver.findElement(By.className("al-performance-chart-label")).isDisplayed();
			if(performance1==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("performance report1 1not shown");
			}
			new ComboBox().selectValue(4, "Productivity Report");
			boolean productivitychart1=Driver.driver.findElement(By.className("al-productivity-chart-block")).isDisplayed();
			if(productivitychart1==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("productivitychart report1 not shown");
			}
			Driver.driver.findElement(By.id("al-metacognitive-report")).click();//click on metacognitive report link
			Thread.sleep(2000);
			boolean metacognity1=Driver.driver.findElement(By.id("al-metacognitive-report-display-block")).isDisplayed();
			if(metacognity1==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("metacognity report1 not shown");
			}
			Driver.driver.findElement(By.id("al-most-challenging-activity")).click();//click on most-challenging-activity report link
			Thread.sleep(2000);
			boolean challengingactivty1=Driver.driver.findElement(By.id("al-most-challenging-activities-tlo")).isDisplayed();
			if(challengingactivty1==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("challengingactivty report1 not shown");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase coursePerformanceAlongWithQuestionCardInPerformancepage in class CoursePerformanceAlongWithQuestionCardInPerformancepage",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
