package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.QuitAssessment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectAnswerAndSubmit;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class QuitingDiagnosticTest extends Driver {
	
	
	@Test
	public void quitingDiagnosticTest()
	{
		try
		{
			String diagnostictest = ReadTestData.readDataByTagName("tocdata", "daigonestictest", "1");
			new LoginUsingLTI().ltiLogin("1116");
			new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);
			
			if (!new QuitAssessment().quitassessmentAndCheckRoboNotificationForDiagnostic())
				Assert.fail("Notification is not showing as Warning on the Robo icon to the user with 'Continue Later' and 'Quit' links.");
			//check for close button on the robo-notifiaction
			int closeButtonSize = driver.findElements(By.className("close-practice-test-notification")).size();
			if(closeButtonSize == 0)
				Assert.fail("Close button is not present in the robonotifiaction.");
			
			//click on the close button of the robo-notifiaction
			driver.findElement(By.className("close-practice-test-notification")).click();
			Thread.sleep(3000);
			int closeButtonSize1 = driver.findElements(By.className("close-practice-test-notification")).size();
			if(closeButtonSize1 != 0)
				Assert.fail("Notification is still visible after clicking on the close button of notification.");
			
			//again click to quit the test
			driver.findElement(By.cssSelector("div[class='al-quit-diag-test-icon']")).click();
			Thread.sleep(3000);
			if (!new QuitAssessment().clickingOnContinueLaterForDiagnostic())
				Assert.fail("TOC doesn't open after clicking 'Continue Later' in the robo notification &  the assement tab doesnt get closed");
			
			new TopicOpen().topicOpen(diagnostictest);
			Thread.sleep(3000);
			new SelectAnswerAndSubmit().daigonestianswersubmit("A"); //attempt a single question in the test
			//click on close button on the upper corner of the question page
			driver.findElement(By.cssSelector("div[class='al-quit-diag-test-icon']")).click();
			Thread.sleep(3000);
			if (!new QuitAssessment().quitassessmentAndCheckRoboNotificationForDiagnostic())
				Assert.fail("Notification is not showing as Warning on the Robo icon to the user with 'Continue Later' and 'Quit' links when we click to quit on any other question not the first question.");
			
			if(!new QuitAssessment().quitTheAssesmentCompletlyForDiagnostic())
				Assert.fail("After quiting  test perfomance page is not opened");
			Thread.sleep(3000);
			if(!new QuitAssessment().quitTheAssesmentCompletlyAndStartTheSameAssesmentAgainForDiagnostic())
				Assert.fail("After completeing the whole assesment once, again we clicked on that particular test , it doesnt land on perfomance page");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in QuitingDiagnosticTest",e);
		}
	
	}

}
