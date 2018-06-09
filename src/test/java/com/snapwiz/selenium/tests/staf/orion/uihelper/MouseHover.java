package com.snapwiz.selenium.tests.staf.orion.uihelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


import java.util.List;

public class MouseHover 
{
    public WebDriver driver= Driver.getWebDriver();

    public  void mouserhover(String classname)
	{
		try{
            Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.className(classname));
			action.moveToElement(we).build().perform();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method mouserhover",e);
		}
	}
	
	public  void mouserhoverbyid(String id)
	{
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
	
	public  void mouserhoverbywebelement(WebElement we)
	{
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

	public  void mouserHoverByClassList(String className,int index)
	{
		try
		{
			Actions action = new Actions(driver);
			WebElement we = driver.findElements(By.className(className)).get(index);
			action.moveToElement(we).build().perform();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method mouserhoverbywebelement",e);
		}
	}


    public  void mouserHoverByXpathList(WebElement we)
    {
        try
        {
            Actions action = new Actions(driver);
            //WebElement we = driver.findElements(By.xpath(className)).get(index);
            action.moveToElement(we).build().perform();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method mouserhoverbywebelement",e);
        }
    } 
    
    
}
