package com.snapwiz.selenium.tests.staf.edulastic.uihelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class ComboBox extends Driver {

	public void selectValue(int index,String value)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
			Thread.sleep(2000);
			allElements.get(index).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText(value)).click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in UI helper ComboBox",e);
		}
	}

	/*public void selectValuebycssselector(String value,String cssselector)
	{
		try
		{
			new com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click().clickBycssselector(cssselector);
			driver.findElement(By.linkText(value)).click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in UI helper selectValuebycssselector",e);
		}
	}*/
	
}
