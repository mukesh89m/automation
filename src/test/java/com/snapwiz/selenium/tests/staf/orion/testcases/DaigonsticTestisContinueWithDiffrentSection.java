package com.snapwiz.selenium.tests.staf.orion.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class DaigonsticTestisContinueWithDiffrentSection
{
	@Test
	public void daigonsticTestisContinueWithDiffrentSection()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("418");
			new SelectChapterForTest().selectchapterfortest(0, 2);
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 6, "correct", false, false, false);
			new LoginUsingLTI().ltiLogin("4181");
			Driver.driver.findElement(By.cssSelector("img[title='Continue']")).click();
			String Qno=Driver.driver.findElement(By.className("al-diag-test-question-label")).getText();
			if(!Qno.equals("Q 1.7:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("after login again by student by other section diagonestic test not started where its left previously");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase daigonsticTestisContinueWithDiffrentSection in class DaigonsticTestisContinueWithDiffrentSection",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}

}
