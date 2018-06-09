package com.snapwiz.selenium.tests.staf.datacreation.uihelper;

import com.snapwiz.selenium.tests.staf.datacreation.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
//import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;

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
            ((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",alllist.get(index));
			//alllist.get(index).click();
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

    public void clickbylistxpath(String xpath,int index)
    {
        try
        {
            List<WebElement> alllist=Driver.driver.findElements(By.xpath(xpath));
            Thread.sleep(2000);
            alllist.get(index).click();
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            //new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in apphelper clickbylistxpath in class Click",e);
        }
    }

	public void clickbylistcssselector(String selector,int index)
	{
		try
		{
			List<WebElement> alllist=Driver.driver.findElements(By.cssSelector(selector));
			Thread.sleep(2000);
			alllist.get(index).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			//new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in apphelper clickbylistcssselector in class Click",e);
		}
	}
	
	public void clickbyxpath(String xpath)
	{
		try
		{
			WebElement we=Driver.driver.findElement(By.xpath(xpath));
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",we);
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			//new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in apphelper clickbyxpath in class Click",e);
		}
	}
	
	public void clickbylinkText(String linkText)
	{
		try
		{
			Driver.driver.findElement(By.linkText(linkText)).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper clickbylinkText in class Click",e);
		}
	}
	
	public void clickbypartiallinkText(String partiallinkText)
	{
		try
		{
			Driver.driver.findElement(By.partialLinkText(partiallinkText)).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper clickbypartiallinkText in class Click",e);
		}
	}

}
