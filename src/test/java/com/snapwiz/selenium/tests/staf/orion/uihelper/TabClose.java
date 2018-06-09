package com.snapwiz.selenium.tests.staf.orion.uihelper;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;

public class TabClose {

	public void tabClose(int tabIndex)
	{
		try
		{
			Driver.driver.findElements(By.className("close_tab")).get(tabIndex).click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper TabClose",e);
		}
	}
}
