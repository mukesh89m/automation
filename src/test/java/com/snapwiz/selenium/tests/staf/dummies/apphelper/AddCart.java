package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import com.snapwiz.selenium.tests.staf.dummies.apphelper.RandomString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.Click;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.MouseHover;

public class AddCart {
	public void widgetaddtocart()
	{
		try
		{
			Actions ac=new Actions(Driver.driver);
			WebElement we1=Driver.driver.findElement(By.cssSelector("img[class='image-main widget-content']"));//fetch image to hover
			ac.moveToElement(we1).build().perform();
			Driver.driver.findElement(By.className("add-assignment-cart-text")).click();//click on assign cart
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
