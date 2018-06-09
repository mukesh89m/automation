package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

public class ExpandFirstChapter extends Driver{
	
	public void expandFirstChapter()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			String firstchapterexpandname = ReadTestData.readDataByTagName("tocdata", "firstchapterexpandname", "1");
			int firstchapter=driver.findElements(By.cssSelector("div[class='chapter-heading chapter-selected']")).size();
			if(firstchapter<1)
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[title='"+firstchapterexpandname+"']")));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in app helper ExpandFirstChapter",e);
		}
	}

}
