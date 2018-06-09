package com.snapwiz.selenium.tests.staf.learnon.uihelper;

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

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
	
	public static void mouserHoverByClassList(String classname,int index)
	{
		try
		{
			Actions action = new Actions(Driver.driver);
			List<WebElement> we = Driver.driver.findElements(By.className(classname));
			action.moveToElement(we.get(index)).build().perform();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method mouserHoverByClassList",e);
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
	
	public static void mouserHoverByIdList(String id,int index)
	{
		try
		{
			Actions action = new Actions(Driver.driver);
			List<WebElement> we = Driver.driver.findElements(By.id(id));
			action.moveToElement(we.get(index)).build().perform();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method mouserHoverByIdList",e);
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

}
