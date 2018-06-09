package com.snapwiz.selenium.tests.staf.dummies.apphelper;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;

public class TOCShow {

	public void tocShow()
	{
		try
		{
			Driver.driver.findElement(By.cssSelector("a[class='ls-chapter-navigation-icon ls-toc-sprite ls-study-plan']")).click();//TOC open
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper TOCShow",e);
		}
	}
	public void tocHide()
	{
		try
		{
			Driver.driver.findElement(By.cssSelector("i.close-study-plan-icon.close-study-plan")).click();//click on cross symbol
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper TOCShow",e);
		}
	}
	public void chaptertree()
	{
		try
		{
			WebElement we=Driver.driver.findElement(By.className("ls-chapter-tree"));
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click()",we);
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper chaptertree",e);
		}
	}
	
}
