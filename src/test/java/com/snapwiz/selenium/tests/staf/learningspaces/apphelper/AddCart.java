package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class AddCart extends Driver {
	public void widgetaddtocart()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			//driver.findElement(By.className("assign-options")).click();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.className("assign-options")));
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.className("add-assignment-cart-text")));
			//driver.findElement(By.className("add-assignment-cart-text")).click();//click on assign cart
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("exception in app helper add cart",e);
		}
	}
	
	public void editcart()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			new Click().clickByclassname("assignment-cart-wrapper");//click on assign cart
			Thread.sleep(2000);
			new MouseHover().mouserhover("ir-ls-assign-dialog-header-wrapper");//mouse hover to edit the assignment cart
			new Click().clickByclassname("ir-ls-assign-this-edit-link");
			driver.findElement(By.className("ir-ls-assign-this-header-edit-text")).clear();
			String ranstring=new RandomString().randomstring(3);
			driver.findElement(By.className("ir-ls-assign-this-header-edit-text")).sendKeys(ranstring);//change the name of assignment cart
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("exception in app helper add cart",e);
		}	
	}

}
