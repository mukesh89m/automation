package com.snapwiz.selenium.tests.staf.dummies.uihelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;

public class ComboBox {

	public void selectValue(int index,String value)
	{
		try
		{
		List<WebElement> allElements = Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
		 Thread.sleep(2000);
		allElements.get(index).click();  
		Thread.sleep(2000);
	    Driver.driver.findElement(By.linkText(value)).click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in UI helper ComboBox",e);
		}
	}
	
	public void selectValuebycssselector(String value,String cssselector)
	{
		try
		{
			new com.snapwiz.selenium.tests.staf.dummies.uihelper.Click().clickBycssselector(cssselector);
			Driver.driver.findElement(By.linkText(value)).click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in UI helper selectValuebycssselector",e);
		}
	}
	
}
