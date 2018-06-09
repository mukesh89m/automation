package com.snapwiz.selenium.tests.staf.orion.apphelper;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;



public class FetchValue
{
	public String fetchvaluebyclassname(String classname,String attribute,String attributename)
	{
		String value=null;
		try
		{
			if(attribute.equals("attribute"))
			{
				value=Driver.driver.findElement(By.className(classname)).getAttribute(attributename);
			}
			else
			{
				value=Driver.driver.findElement(By.className(classname)).getText();
			}
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method fetchvaluebyclassname",e);
		}
		return value;
	}
	
	public String fetchvaluebyId(String id)
	{
		String value=null;
		try
		{
			value=Driver.driver.findElement(By.id(id)).getText();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method fetchvaluebyId",e);
		}
		return value;
	}
	
	public String fetchvaluebycssselector(String css)
	{
		String value=null;
		try
		{
			value=Driver.driver.findElement(By.cssSelector(css)).getText();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method fetchvaluebycssselector",e);
		}
		return value;
	}
	
	public int fetchlistvaluebyclassname(String classname)
	{
		int value=0;
		try
		{
			value=Driver.driver.findElements(By.className(classname)).size();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method fetchlistvaluebyclassname",e);
		}
		return value;
	}
	
	
	
	public int fetchlistvaluebyId(String id)
	{
		int value=0;
		try
		{
			value=Driver.driver.findElements(By.className(id)).size();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method fetchlistvaluebyId",e);
		}
		return value;
	}
	
	public int fetchlistvaluebycssselector(String css)
	{
		int value=0;
		try
		{
			value=Driver.driver.findElements(By.id(css)).size();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method fetchvlistaluebycssselector",e);
		}
		return value;
	}


}
