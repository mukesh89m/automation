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

public class StudentGetNotificationWhenQuitDiagonesticTest 
{
	@Test
	public void studentGetNotificationWhenQuitDiagonesticTest()
	{
		try 
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("750");//login as student
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			Driver.driver.findElement(By.className("al-quit-diag-test-icon")).click();//click on quit icon  of diagonestic test
			Thread.sleep(2000);
			boolean continuelink=Driver.driver.findElement(By.className("al-diag-test-continue-later")).isDisplayed();
			if(continuelink==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("continue later link not present");
			}
			boolean quit=Driver.driver.findElement(By.className("al-quit-diag-test")).isDisplayed();
			if(quit==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("quit link not present");
			}
			boolean cross=Driver.driver.findElement(By.id("close-al-notification-dialog")).isDisplayed();
			if(cross==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("cross link not present");
			}
			Driver.driver.findElement(By.className("al-diag-test-continue-later")).click();//click on continue later link
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Continue']")).click();//click on continue button from dashboard
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("al-quit-diag-test-icon")).click();//clcik on quit diagonestic test
			Thread.sleep(2000);
			boolean continuelink1=Driver.driver.findElement(By.className("al-diag-test-continue-later")).isDisplayed();
			if(continuelink1==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("continue later1 link not present");
			}
			boolean quit1=Driver.driver.findElement(By.className("al-quit-diag-test")).isDisplayed();
			if(quit1==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("quit link1 not present");
			}
			
			Driver.driver.findElement(By.className("al-diag-test-continue-later")).click();//click on continue later link
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Continue']")).click();//click on continue button from dashboard
			Thread.sleep(2000);
			new DiagnosticTest().attemptAllCorrect(2,false,false);
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			Driver.driver.findElement(By.className("al-quit-diag-test-icon")).click();//clcik on quit diagonestic test
			Thread.sleep(2000);
			String continuelink2=Driver.driver.findElement(By.className("al-diag-test-continue-later")).getText();
			System.out.println(continuelink2);
			if(continuelink2.equals("Continue Later"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("continue later2 link  present");
			}
			String quit2=Driver.driver.findElement(By.className("al-diag-test-quit-block")).getText();
			System.out.println(continuelink2);
			if(quit2.equals("quit"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("quit link2  present");
			}
		}
		catch (Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase studentGetNotificationWhenQuitDiagonesticTest in class studentGetNotificationWhenQuitDiagonesticTest ",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}

	

}
