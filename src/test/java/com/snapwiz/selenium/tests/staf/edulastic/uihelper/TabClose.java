package com.snapwiz.selenium.tests.staf.edulastic.uihelper;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverFactory;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class TabClose extends Driver {

	public void tabClose(int tabIndex)
	{
		WebDriver driver=null;
		try
		{
			driver= Driver.getWebDriver();
			driver.findElements(By.className("close_tab")).get(tabIndex).click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper TabClose",e);
		}
	}
}
