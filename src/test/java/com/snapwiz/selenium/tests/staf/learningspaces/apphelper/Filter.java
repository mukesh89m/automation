package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class Filter extends Driver {

	public void filterApply (String filter, String subFilter)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			driver.findElement(By.linkText(filter)).click(); //Applying filter
			Thread.sleep(2000);
			driver.findElement(By.linkText(subFilter)).click();
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in application helper filterApply in class Filter",e);
		}
	}

}
