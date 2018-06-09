package com.snapwiz.selenium.tests.staf.dummies.uihelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
//import com.snapwiz.selenium.tests.staf.dummies.apphelper.Screenshot;

public class Click {
	public void clickByclassname(String classname)
	{
		try
		{
			Thread.sleep(2000);
			WebElement we=Driver.driver.findElement(By.className(classname));
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",we);
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			//new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in apphelper clickByclassname in class Click",e);
		}
	}
	
	public void clickBycssselector(String selector)
	{
		try
		{
			Thread.sleep(2000);
			WebElement we=Driver.driver.findElement(By.cssSelector(selector));
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",we);
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			//new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in apphelper clickBycssselector in class Click",e);
		}
	}

	public void clickByid(String id)
	{
		try
		{
			WebElement we=Driver.driver.findElement(By.id(id));
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",we);
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			//new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in apphelper clickByid in class Click",e);
		}
	}
	
	public void clickbylist(String classname,int index)
	{
		try
		{
			List<WebElement> alllist=Driver.driver.findElements(By.className(classname));
			Thread.sleep(2000);
			alllist.get(index).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			//new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in apphelper clickbylist in class Click",e);
		}
	}
	
	public void clickbylistid(String id,int index)
	{
		try
		{
			List<WebElement> alllist=Driver.driver.findElements(By.id(id));
			Thread.sleep(2000);
			alllist.get(index).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			//new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in apphelper clickbylistid in class Click",e);
		}
	}
	
	public void clickbylistcssselector(String selector,int index)
	{
		try
		{
			List<WebElement> alllist=Driver.driver.findElements(By.cssSelector(selector));
			alllist.get(index).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			//new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in apphelper clickbylistcssselector in class Click",e);
		}
	}
	
	public void clickongraphbar()
	{
		try
		{
			Driver.driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			//new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in apphelper clickongraphbar in class Click",e);
		}
	}

}
