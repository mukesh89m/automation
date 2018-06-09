package com.snapwiz.selenium.tests.staf.learnon.uihelper;

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

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
			new Click().clickBycssselector(cssselector);
			Driver.driver.findElement(By.linkText(value)).click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in UI helper selectValuebycssselector",e);
		}
	}
	
}
