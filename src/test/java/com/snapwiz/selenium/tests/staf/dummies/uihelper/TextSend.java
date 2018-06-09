package com.snapwiz.selenium.tests.staf.dummies.uihelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;

public class TextSend {
	public void textsendbyclass(String text,String classname)
	{
		try
		{
            Driver.driver.findElement(By.className(classname)).clear();
			Driver.driver.findElement(By.className(classname)).sendKeys(text);
		}
		catch(Exception e)
		{
			Assert.fail("exceptionm in app heleper method textsendbyclass",e);
		}
	}
	
	public void textsendbyid(String text,String id)
	{
		try
		{
            Driver.driver.findElement(By.id(id)).clear();
			Driver.driver.findElement(By.id(id)).sendKeys(text);
		}
		catch(Exception e)
		{
			Assert.fail("exceptionm in app heleper method textsendbyid",e);
		}
	}
	public void textsendbycssSelector(String text,String cssselector)
	{
		try
		{
            Driver.driver.findElement(By.cssSelector(cssselector)).clear();
			Driver.driver.findElement(By.cssSelector(cssselector)).sendKeys(text);
		}
		catch(Exception e)
		{
			Assert.fail("exceptionm in app heleper method textsendbycssSelector",e);
		}
	}
	
	public void textsendbyclasslist(String text,String classname,int index)
	{
		try
		{
			List<WebElement> we=Driver.driver.findElements(By.className(classname));
            we.get(index).clear();
			we.get(index).sendKeys(text);
		}
		catch(Exception e)
		{
			Assert.fail("exceptionm in app heleper method textsendbyclass",e);
		}
	}
	
	

}
