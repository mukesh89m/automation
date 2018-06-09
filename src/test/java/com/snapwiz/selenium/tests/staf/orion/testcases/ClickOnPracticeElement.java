package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class ClickOnPracticeElement {
@Test
	public void clickOnPracticeElement()
	{
		try
		{
			Driver.startDriver();	
			new LoginUsingLTI().ltiLogin("1738"); //  Logging in as student to orion
			new DiagnosticTest().startTest(0,4);//select 1st chapter for test
			//new DiagnosticTest().DiagonesticTestQuitBetween(4, 5, "correct", false, false, true);
			new DiagnosticTest().attemptAllCorrect(4, false, false);
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			for(int i = 0; i<10 ; i++)
				new PracticeTest().attemptQuestion("correct", 4, false, false);
			new PracticeTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentAllActivity();
			//list all headers
			List<WebElement> allHeaders = Driver.driver.findElements(By.cssSelector("div[class='my-journal-media-body practice-test-activity-subtitle journal-activity-ellipsis']"));
			if(!allHeaders.get(0).getText().contains("Attempted 10 questions for chapter"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After attempting a number of questions it doesn't display the number questions attempted in All Activity page.");
			}
			//click on practice element
			allHeaders.get(0).click();
			Thread.sleep(2000);
			String question1 = Driver.driver.findElement(By.className("al-diag-test-question-raw-content")).getText();
			if(question1 == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking the practice element from the 'All Activity' page it doesn't go to that question which was last attempted.");
			}
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			 //attempt 5 more questions
			for(int i = 0; i<5 ; i++)
					new PracticeTest().attemptQuestion("correct", 4, false, false);
			new PracticeTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentAllActivity();
			//list all headers
			List<WebElement> allHeaders1 = Driver.driver.findElements(By.cssSelector("div[class='my-journal-media-body practice-test-activity-subtitle journal-activity-ellipsis']"));
			if(!allHeaders1.get(0).getText().contains("Attempted 5 questions for chapter"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After attempting a number of questions it doesn't display the number questions attempted in All Activity page.");
			}
			new Navigator().orionDashboard();
			//start TLO level practice test
			new PracticeTest().startTLOLevelPracticeTest(0);
			 //attempt 5 more questions
			for(int i = 0; i<5 ; i++)
					new PracticeTest().attemptQuestion("correct", 4, false, false);
			new PracticeTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentAllActivity();
			//list all headers
			List<WebElement> allHeaders2 = Driver.driver.findElements(By.cssSelector("div[class='my-journal-media-body practice-test-activity-subtitle journal-activity-ellipsis']"));
			if(!allHeaders2.get(0).getText().contains("Attempted 5 questions for objective"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After attempting a number of questions from practice at TLO level it doesn't display the number questions attempted in All Activity page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase clickOnPracticeElement in class ClickOnPracticeElement ",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}

}
