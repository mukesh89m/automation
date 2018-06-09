package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;

public class Filter {

	public void filterApply (String filter, String subFilter)
	{
		try
		{
			Driver.driver.findElement(By.linkText(filter)).click(); //Applying filter
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(subFilter)).click();
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in application helper filterApply in class Filter",e);
		}
	}

}
