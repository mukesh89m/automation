package com.snapwiz.selenium.tests.staf.orion.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;

public class CheckWileyPluslink 
{
	public void CheckWileyPluslinkdashboardanddiagqspage() throws InterruptedException
	{
		boolean returnwileyplus = Driver.driver.findElement(By.id("al-header-return-url")).isDisplayed();
		if(returnwileyplus==false)
		{
			Assert.fail("On dashboard WileyPlus button is not present.");
		}
		new DiagnosticTest().startTest(0, 2);
		int timer= Driver.driver.findElements(By.cssSelector("span[id='cntdwn']")).size();
		while (timer!=0)
		{
			boolean returnwileyplusdiag = Driver.driver.findElement(By.id("al-header-return-url")).isDisplayed();
			if(returnwileyplusdiag==false)
			{
				Assert.fail("On diag test quetsion page WileyPlus button is not present.");
			}
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button")));
			
			Thread.sleep(2000);
			timer =	Driver.driver.findElements(By.cssSelector("span[id='cntdwn']")).size();
			System.out.println(timer);
		}
  }

}
