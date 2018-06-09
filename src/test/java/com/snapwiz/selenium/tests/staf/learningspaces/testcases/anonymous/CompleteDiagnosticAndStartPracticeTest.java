package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;

public class CompleteDiagnosticAndStartPracticeTest extends Driver{
	
@Test
	public void completeDiagnosticAndStartPracticeTest()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("2475");
			new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);
			new DiagnosticTest().attemptAllCorrect(0, false, false);
            new Navigator().NavigateTo("eTextbook");
            new PracticeTest().startTest();//start the adaptive practice test
			//click on quit on the upper right corner of question page
			driver.findElement(By.cssSelector("div[class='al-quit-diag-test-icon']")).click();
			Thread.sleep(3000);
			String notification = driver.findElement(By.cssSelector("div[class='al-notification-message-body']")).getText();
			if(notification.contains("Continue Later") && notification.contains("Quit") )
				Assert.fail("The particular notification with 'Continue Later' and 'Quit' is displayed for Adaptive practice.");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in completeDiagnosticAndStartPracticeTest in class CompleteDiagnosticAndStartPracticeTest",e);
		}
	}
	@AfterMethod
	public void TearDown()throws Exception
	{
		driver.quit();
	}
}
