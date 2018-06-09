package com.snapwiz.selenium.tests.staf.edulastic.uihelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

import java.util.List;

public class MouseHover extends Driver
{
	public void mouserhover(String classname)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.className(classname));
			action.moveToElement(we).build().perform();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method mouserhover",e);
		}
	}
	
	public void mouserhoverbyid(String id)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.id(id));
			action.moveToElement(we).build().perform();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method mouserhoverbyid",e);
		}
	}
	
	public void mouserhoverbywebelement(WebElement we)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			Actions action = new Actions(driver);
			action.moveToElement(we).build().perform();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method mouserhoverbywebelement",e);
		}
	}

    public void mouserhoverList(List<WebElement> we,int index)
    {
        try
        {
			WebDriver driver=Driver.getWebDriver();
            Actions action = new Actions(driver);
            WebElement webElement = we.get(index);
            action.moveToElement(webElement).build().perform();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method mouserhover",e);
        }
    }

}
