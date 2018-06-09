package com.snapwiz.selenium.tests.staf.dummies.uihelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;

import java.util.List;

public class MouseHover 
{
	public static void mouserhover(String classname)
	{
		try
		{
			Actions action = new Actions(Driver.driver);
			WebElement we = Driver.driver.findElement(By.className(classname));
			action.moveToElement(we).build().perform();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method mouserhover",e);
		}
	}
	
	public static void mouserhoverbyid(String id)
	{
		try
		{
			Actions action = new Actions(Driver.driver);
			WebElement we = Driver.driver.findElement(By.id(id));
			action.moveToElement(we).build().perform();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method mouserhoverbyid",e);
		}
	}
	
	public static void mouserhoverbywebelement(WebElement we)
	{
		try
		{
			Actions action = new Actions(Driver.driver);
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
            Actions action = new Actions(Driver.driver);
            WebElement webElement = we.get(index);
            action.moveToElement(webElement).build().perform();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method mouserhover",e);
        }
    }

}
