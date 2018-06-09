package com.snapwiz.selenium.tests.staf.learningspaces.uihelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class MouseHover extends Driver
{

	public  void mouserhover(String classname)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.className(classname));
			action.moveToElement(we).build().perform();
            Thread.sleep(5000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method mouserhover",e);
		}
	}
	
	public  void mouserHoverByClassList(String classname,int index)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			Actions action = new Actions(driver);
			List<WebElement> we = driver.findElements(By.className(classname));
			action.moveToElement(we.get(index)).build().perform();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method mouserHoverByClassList",e);
		}
	}

    public  void mouserHoverByXpathList(String xpath,int index)
    {
		WebDriver driver=Driver.getWebDriver();
        try
        {
            Actions action = new Actions(driver);
            List<WebElement> we = driver.findElements(By.xpath(xpath));
            action.moveToElement(we.get(index)).build().perform();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method mouserHoverByXpathList",e);
        }
    }


	
	public  void mouserhoverbyid(String id)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.id(id));
			action.moveToElement(we).build().perform();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method mouserhoverbyid",e);
		}
	}

    public  void mouserhoverbyCss(String cssSelector)
    {
		WebDriver driver=Driver.getWebDriver();
        try
        {
            Actions action = new Actions(driver);
            WebElement we = driver.findElement(By.cssSelector(cssSelector));
            action.moveToElement(we).build().perform();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method mouserhoverbyCss",e);
        }
    }


    public  void mouserhoverbyXpath(String xpath)
    {
		WebDriver driver=Driver.getWebDriver();
        try
        {
            Actions action = new Actions(driver);
            WebElement we = driver.findElement(By.xpath(xpath));
            action.moveToElement(we).build().perform();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method mouserhoverbyXpath",e);
        }
    }
	
	public  void mouserHoverByIdList(String id,int index)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			Actions action = new Actions(driver);
			List<WebElement> we = driver.findElements(By.id(id));
			action.moveToElement(we.get(index)).build().perform();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method mouserHoverByIdList",e);
		}
	}
	
	public  void mouserhoverbywebelement(WebElement we)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			Actions action = new Actions(driver);
			action.moveToElement(we).build().perform();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method mouserhoverbywebelement",e);
		}
	}

}
