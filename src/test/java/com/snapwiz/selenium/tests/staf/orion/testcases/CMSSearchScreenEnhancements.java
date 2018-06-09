package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectCourse;


public class CMSSearchScreenEnhancements
{
	@Test
	public void cmsSearchScreenEnhancements()
	{
		
		try 
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();
			new SelectCourse().selectcourse();
            Thread.sleep(2000);
			Driver.driver.findElement(By.id("content-search-icon")).click();
			Thread.sleep(2000);
			boolean serachtab=Driver.driver.findElement(By.id("tab-search")).isDisplayed();
			if(serachtab==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("serach tab not shown.");
			}
			boolean browsetab=Driver.driver.findElement(By.id("tab-browse")).isDisplayed();
			if(browsetab==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("browse tab not shown.");
			}
			boolean searchfield=Driver.driver.findElement(By.id("content-search-field")).isDisplayed();
			if(searchfield==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("searchfield not shown.");
			}
			boolean gosearch=Driver.driver.findElement(By.id("search-question-contents-icon")).isDisplayed();
			if(gosearch==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("gosearch button not shown.");
			}
			boolean filtersearch=Driver.driver.findElement(By.id("search-filter-link")).isDisplayed();
			if(filtersearch==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("filtersearch link not shown.");
			}
			Driver.driver.findElement(By.id("search-filter-link")).click();
			Thread.sleep(2000);
			List<WebElement> allElements = Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
			
			
		}
		catch (Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("exception in test case cmsSearchScreenEnhancements",e);
		}
	}
    @AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }

}
