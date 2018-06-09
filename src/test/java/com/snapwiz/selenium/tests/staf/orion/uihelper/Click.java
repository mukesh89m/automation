package com.snapwiz.selenium.tests.staf.orion.uihelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class Click extends Driver{
	
	public void clickByclassname(String classname)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			driver.findElement(By.className(classname)).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase clickByclassname in class Click",e);
		}
	}
	public void clickBycssselector(String cssSelector)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			driver.findElement(By.cssSelector(cssSelector)).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in Method clickBycssselector in class Click",e);
		}
	}
	public void clickByxpath(String xpath)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			driver.findElement(By.xpath(xpath)).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in Method clickBycssselector in class Click",e);
		}
	}
	public void clickbylinkText(String linkText)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			driver.findElement(By.linkText(linkText)).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in Method clickbylinkText in class Click",e);
		}
	}
	public void clickByid(String id)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			driver.findElement(By.id(id)).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase clickByid in class Click",e);
		}
	}
	
	public void clickongraphbar()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			//new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in apphelper clickongraphbar in class Click",e);
		}
	}

}
