package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.RandomString;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.MouseHover;

public class AddCart extends  Driver{
	public void widgetaddtocart()
	{
		try
		{
			//Driver.driver.findElement(By.className("assign-options")).click();
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.className("assign-options")));
            Thread.sleep(2000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.className("add-assignment-cart-text")));
			//Driver.driver.findElement(By.className("add-assignment-cart-text")).click();//click on assign cart
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("exception in app helper add cart",e);
		}
	}
	
	public void editcart()
	{
		try
		{
			new Click().clickByclassname("assignment-cart-wrapper");//click on assign cart
			Thread.sleep(2000);
			MouseHover.mouserhover("ir-ls-assign-dialog-header-wrapper");//mouse hover to edit the assignment cart
			new Click().clickByclassname("ir-ls-assign-this-edit-link");
			Driver.driver.findElement(By.className("ir-ls-assign-this-header-edit-text")).clear();
			String ranstring=new RandomString().randomstring(3);
			Driver.driver.findElement(By.className("ir-ls-assign-this-header-edit-text")).sendKeys(ranstring);//change the name of assignment cart
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("exception in app helper add cart",e);
		}	
	}

}
