package com.snapwiz.selenium.tests.staf.edulastic.uihelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class TextSend extends Driver {
	public void textsendbyclass(String text,String classname)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
            driver.findElement(By.className(classname)).clear();
			driver.findElement(By.className(classname)).sendKeys(text);
		}
		catch(Exception e)
		{
			Assert.fail("exception in app helper method textsendbyclass",e);
		}
	}
	
	public void textsendbyid(String text,String id)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
            driver.findElement(By.id(id)).clear();
			driver.findElement(By.id(id)).sendKeys(text);
		}
		catch(Exception e)
		{
			Assert.fail("exceptionm in app helper method textsendbyid",e);
		}
	}
	public void textsendbycssSelector(String text,String cssselector)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
            driver.findElement(By.cssSelector(cssselector)).clear();
			driver.findElement(By.cssSelector(cssselector)).sendKeys(text);
		}
		catch(Exception e)
		{
			Assert.fail("exception in app helper method textsendbycssSelector",e);
		}
	}

    public void textsendbyxpath(String text,String xpath)
    {
        try
        {
			WebDriver driver=Driver.getWebDriver();
            driver.findElement(By.xpath(xpath)).clear();
            driver.findElement(By.cssSelector(xpath)).sendKeys(text);
        }
        catch(Exception e)
        {
            Assert.fail("exception in app helper method textsendbycssSelector",e);
        }
    }
	
	public void textsendbyclasslist(String text,String classname,int index)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			List<WebElement> we=driver.findElements(By.className(classname));
            we.get(index).clear();
			we.get(index).sendKeys(text);
		}
		catch(Exception e)
		{
			Assert.fail("exceptionm in app heleper method textsendbyclass",e);
		}
	}

    public void textsendbycsslist(String text,String cssselector,int index)
    {
        try
        {
			WebDriver driver=Driver.getWebDriver();
            List<WebElement> we=driver.findElements(By.cssSelector(cssselector));
            we.get(index).clear();
            we.get(index).sendKeys(text);
        }
        catch(Exception e)
        {
            Assert.fail("exceptionm in app heleper method textsendbyclass",e);
        }
    }

    public void textsendbyxpathlist(String text,String xpath,int index)
    {
        try
        {
			WebDriver driver=Driver.getWebDriver();
            List<WebElement> we=driver.findElements(By.xpath(xpath));
            we.get(index).clear();
            we.get(index).sendKeys(text);
        }
        catch(Exception e)
        {
            Assert.fail("exceptionm in app heleper method textsendbyxpathlist",e);
        }
    }

}
