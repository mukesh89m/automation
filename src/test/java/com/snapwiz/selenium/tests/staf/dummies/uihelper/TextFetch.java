package com.snapwiz.selenium.tests.staf.dummies.uihelper;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;

public class TextFetch
{
	public String textfetchbyid(String id)
	{
		String text=null;
		try
		{
			text=Driver.driver.findElement(By.id(id)).getText();
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
		try
		{
			text=Driver.driver.findElement(By.className(classname)).getText();
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
		try
		{
			text=Driver.driver.findElement(By.cssSelector(cssselector)).getText();
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
		try
		{
			List<WebElement> alllist=Driver.driver.findElements(By.id(id));
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
		try
		{
			List<WebElement> alllist=Driver.driver.findElements(By.cssSelector(cssselector));
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
		try
		{
			List<WebElement> alllist=Driver.driver.findElements(By.className(classname));
			text=alllist.get(index).getText();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in  apphelper textfetchbylistclass ",e);
		}
		return text;
	}

}
