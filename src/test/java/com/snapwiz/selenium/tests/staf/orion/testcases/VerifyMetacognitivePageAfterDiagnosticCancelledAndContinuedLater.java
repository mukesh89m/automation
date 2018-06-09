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

public class VerifyMetacognitivePageAfterDiagnosticCancelledAndContinuedLater {
	@Test
	public void verifyMetacognitivePageAfterDiagnosticCancelledAndContinuedLater()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("849");//login as student
			new DiagnosticTest().startTest(0,3);//select 1st chapter for test
			for(int i=0;i<15;i++)
				new DiagnosticTest().attemptCorrect(4);		//attempt correctly
			Driver.driver.findElement(By.className("al-quit-diag-test-icon")).click();   //click close icon
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("al-diag-test-continue-later")).click();		//click on continue later
			Thread.sleep(2000);
			new Navigator().NavigateToStudentReport();
			new Navigator().NavigateToReport("Metacognitive Report");		//click on Metacognitive Report
			String noData = Driver.driver.findElement(By.cssSelector("div[class='no-data-notification-message']")).getText();
			if(!noData.equals("No Data Available"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The dot is coming if we cancel and continue later the diagnostic test.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyMetacognitivePageAfterDiagnosticCancelledAndContinuedLater in class VerifyMetacognitivePageAfterDiagnosticCancelledAndContinuedLater ",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
