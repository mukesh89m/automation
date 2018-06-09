package com.snapwiz.selenium.tests.staf.learningspaces.uihelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class TabClose extends Driver{

	public void tabClose(int tabIndex)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			driver.findElements(By.className("close_tab")).get(tabIndex).click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper TabClose",e);
		}
	}
}
