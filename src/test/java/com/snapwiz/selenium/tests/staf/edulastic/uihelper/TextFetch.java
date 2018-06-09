package com.snapwiz.selenium.tests.staf.edulastic.uihelper;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class TextFetch extends Driver
{

	public String textfetchbyid(String id)
	{
		WebDriver driver=Driver.getWebDriver();
		String text=null;
		try
		{
			text=driver.findElement(By.id(id)).getText();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in  apphelper textfetchbyid ",e);
		}
		return text;
	}
	
	public String textfetchbyclass(String classname)
	{
		String text=null;
		WebDriver driver=Driver.getWebDriver();
		try
		{
			text=driver.findElement(By.className(classname)).getText();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in  apphelper textfetchbyclass ",e);
		}
		return text;
	}
	
	public String textfetchbycssselector(String cssselector)
	{
		String text=null;
		WebDriver driver=Driver.getWebDriver();
		try
		{
			text=driver.findElement(By.cssSelector(cssselector)).getText();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in  apphelper textfetchbycssselector ",e);
		}
		return text;
	}
	
	public String textfetchbylistid(String id,int index)
	{
		String text=null;
		WebDriver driver=Driver.getWebDriver();
		try
		{
			List<WebElement> alllist=driver.findElements(By.id(id));
			text=alllist.get(index).getText();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in  apphelper textfetchbylistid ",e);
		}
		return text;
	}
	
	public String textfetchbylistcssselector(String cssselector,int index)
	{
		String text=null;
		WebDriver driver=Driver.getWebDriver();
		try
		{
			List<WebElement> alllist=driver.findElements(By.cssSelector(cssselector));
			text=alllist.get(index).getText();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in  apphelper textfetchbylistcssselector ",e);
		}
		return text;
	}
	
	public String textfetchbylistclass(String classname,int index)
	{
		String text=null;
		WebDriver driver=Driver.getWebDriver();
		try
		{
			List<WebElement> alllist=driver.findElements(By.className(classname));
			text=alllist.get(index).getText();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in  apphelper textfetchbylistclass ",e);
		}
		return text;
	}

    public String textFetchByXpath(String xPath)
    {
        String text=null;
		WebDriver driver=Driver.getWebDriver();
        try
        {
            text=driver.findElement(By.xpath(xPath)).getText();
        }
        catch (Exception e)
        {
            Assert.fail("Exception in  apphelper textFetchByXpath ",e);
        }
        return text;
    }

    public String textfetchbylistXpath(String xpath,int index)
    {
        String text=null;
		WebDriver driver=Driver.getWebDriver();
        try
        {
            List<WebElement> alllist=driver.findElements(By.xpath(xpath));
            text=alllist.get(index).getText();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper textfetchbylistXpath ",e);
        }
        return text;
    }
}
