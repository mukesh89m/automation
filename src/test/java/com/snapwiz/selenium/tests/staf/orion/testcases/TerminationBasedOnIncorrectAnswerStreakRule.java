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
import com.snapwiz.selenium.tests.staf.orion.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectAnswerAndSubmit;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class TerminationBasedOnIncorrectAnswerStreakRule {
	@Test(priority = 1, enabled = true)
	public void terminationBasedOnIncorrectAnswerStreakRule()
	{
		try
		{
			Driver.startDriver();	
			new LoginUsingLTI().ltiLogin("175"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diag test for 1st chapter
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);
			new Navigator().orionDashboard();
			WebElement menuitem = Driver.driver.findElement(By.className("al-preformance-text")); 
			Locatable hoverItem = (Locatable) menuitem;
			Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
			mouse.mouseMove(hoverItem.getCoordinates());
			Driver.driver.findElement(By.cssSelector("div[title='Practice']")).click();		//click on 1st TLO of 1st chapter
			Thread.sleep(3000);
			new SelectAnswerAndSubmit().practiceTestAttempt(2, 4, "incorrect", false, false);
			String remediation = Driver.driver.findElement(By.className("al-notification-message-wrapper")).getText();
			if(!remediation.contains("Looks like you might want to study this objective in WileyPLUS. Go to WileyPLUS"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After attempting four questions incorrectly the required modification is not coming.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in terminationBasedOnIncorrectAnswerStreakRule in class TerminationBasedOnIncorrectAnswerStreakRule",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void terminationBasedOnIncorrectAnswerExcudingLastAndFirstQuestion()
	{
		try
		{
			Driver.startDriver();	
			new LoginUsingLTI().ltiLogin("177"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diag test for 1st chapter
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);
			new Navigator().orionDashboard();
			WebElement menuitem = Driver.driver.findElement(By.className("al-preformance-text")); 
			Locatable hoverItem = (Locatable) menuitem;
			Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
			mouse.mouseMove(hoverItem.getCoordinates());
			Driver.driver.findElement(By.cssSelector("div[title='Practice']")).click();		//click on 1st TLO of 1st chapter
			Thread.sleep(3000);
			new SelectAnswerAndSubmit().practiceTestAttempt(2, 2, "correct", false, false);
			new SelectAnswerAndSubmit().practiceTestAttempt(2, 4, "incorrect", false, false);
			String remediation = Driver.driver.findElement(By.className("al-notification-message-wrapper")).getText();
			if(!remediation.contains("Looks like you might want to study this objective in WileyPLUS. Go to WileyPLUS"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After attempting four questions incorrectly the required modification is not coming.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in terminationBasedOnIncorrectAnswerExcudingLastAndFirstQuestion in class TerminationBasedOnIncorrectAnswerStreakRule",e);
		}
	}
	
	@Test(priority = 3, enabled = false)
	public void terminationBasedOnIncorrectAnswerForLastFourQuestions()
	{
		try
		{
			Driver.startDriver();
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids
			String practiceassessmentname= ReadTestData.readDataByTagName("TerminationBasedOnIncorrectAnswerStreakRule", "practiceassessmentname", "176");
			//create a chapter
			new Assignment().createChapter(176);
			//create diagnostic test
			new Assignment().createPracticeAtChapterLevel(176, "Adaptive Component Diagnostic", true, false, true);
			//create adaptive test
			new Assignment().createPracticeAtChapterLevel(176, "Adaptive Component Practice", true, false, true);
			for(int i = 0; i<7; i++)
			{
			new Assignment().addQuestions(176, "qtn-multiple-choice-img", practiceassessmentname, false, false,null, true,Integer.parseInt(tloids.get(0)));
			}
			new LoginUsingLTI().ltiLogin("176");
			new DiagnosticTest().openLastDiagnosticTest();
			
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);	
			new Navigator().orionDashboard();
			new PracticeTest().openLastPracticeTest();
			new SelectAnswerAndSubmit().practiceTestAttempt(2, 4, "correct", false, false);
			new SelectAnswerAndSubmit().practiceTestAttempt(2, 4, "incorrect", false, false);
			String remediation = Driver.driver.findElement(By.className("al-notification-message-wrapper")).getText();
			System.out.println("-->"+remediation);
			if(!remediation.contains("Looks like you might want to study this objective in WileyPLUS. Go to WileyPLUS"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After attempting last four questions incorrectly the required modification is not coming.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in terminationBasedOnIncorrectAnswerForLastFourQuestions in class TerminationBasedOnIncorrectAnswerStreakRule",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
