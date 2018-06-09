package com.snapwiz.selenium.tests.staf.datacreation.apphelper;

import com.snapwiz.selenium.tests.staf.datacreation.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
            (new WebDriverWait(Driver.driver, 3))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-chapter-navigation-icon ls-toc-sprite ls-study-plan']"))).click();
            Thread.sleep(15000);
		}
		catch(Exception e)
		{
			//Assert.fail("Exception in App helper chaptertree",e);
		}
	}
	
}
