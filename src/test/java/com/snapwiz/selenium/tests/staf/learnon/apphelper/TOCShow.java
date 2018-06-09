package com.snapwiz.selenium.tests.staf.learnon.apphelper;

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.Click;
import org.openqa.selenium.By;
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
			new Click().clickBycssselector("i.close-study-plan-icon.close-study-plan");//click on cross symbol
			//Driver.driver.findElement(By.cssSelector("i.close-study-plan-icon.close-study-plan")).click();
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
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper chaptertree",e);
		}
	}
	
}
