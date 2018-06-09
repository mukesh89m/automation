package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;

public class TOCShow {

	public void tocShow()
	{
		try
		{
			Driver.driver.findElement(By.cssSelector("span[class='show-toc navigator-sprites']")).click();//TOC open
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
             new WebDriverWait(Driver.driver,180).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("i.close-study-plan-icon.close-study-plan")));
            ((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("i.close-study-plan-icon.close-study-plan")));

            Thread.sleep(2000);
			//Driver.driver.findElement(By.cssSelector("i.close-study-plan-icon.close-study-plan")).click();//click on cross symbol
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
            Thread.sleep(10000);
			Driver.driver.findElement(By.cssSelector("a[class='ls-chapter-navigation-icon ls-toc-sprite ls-study-plan']")).click();
            new WebDriverWait(Driver.driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper chaptertree",e);
		}
	}
	
}
