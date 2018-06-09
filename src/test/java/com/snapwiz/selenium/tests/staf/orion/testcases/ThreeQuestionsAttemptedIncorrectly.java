package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectAnswerAndSubmit;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class ThreeQuestionsAttemptedIncorrectly {
	@Test
	public void threeQuestionsAttemptedIncorrectly()
	{
		try
		{
			Driver.startDriver();	
			String tloid1= ReadTestData.readDataByTagName("tlos", "tlo1id", "0");
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids
			System.out.println("tlo: "+Integer.parseInt(tloids.get(0)));
			new Assignment().createChapter(245, Integer.parseInt(tloids.get(0)));
			//create diagnostic test
			new Assignment().createPracticeAtChapterLevel(245, "Adaptive Component Diagnostic", true, false, true);
			//create adaptive test
			new Assignment().createPracticeAtChapterLevel(245, "Adaptive Component Practice", true, false, true);
			new LoginUsingLTI().ltiLogin("245");
			new DiagnosticTest().openLastDiagnosticTest();
			
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);
			new LoginUsingLTI().ltiLogin("245");
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diag test for 1st chapter
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new SelectAnswerAndSubmit().practiceTestAttempt(2, 4, "incorrect", false, false);
			String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
			String trimmmedNotice = notice.replaceAll("[\n\r]", "");
			System.out.println("-->"+trimmmedNotice);
			if(!trimmmedNotice.contains("Looks like you might want to study this chapter in WileyPLUS. Go to WileyPLUS"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After attempting three questions incorrect notification is not coming.");
			}
			//click to close notification
		   Driver.driver.findElement(By.xpath("(//div[@id='close-al-notification-dialog'])[2]")).click();
		   Thread.sleep(3000);
		   //check notification is closed or not
		   int noticeSize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
		   if(noticeSize != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Notification is not closing when clicked on close icon.");
			}
		   //click on submit
		   ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		   Thread.sleep(2000);
		 //click on Next
		   ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
			Thread.sleep(2000);
			//find question is delivered or not
			String question = Driver.driver.findElement(By.className("al-diag-test-question-raw-content")).getText();
			if(question.length()==0 )
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After closing notification we are unable to progess with the test.");
			}
		  
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in threeQuestionsAttemptedIncorrectly in class ThreeQuestionsAttemptedIncorrectly",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
