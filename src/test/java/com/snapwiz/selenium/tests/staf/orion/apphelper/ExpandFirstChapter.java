package com.snapwiz.selenium.tests.staf.orion.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;

public class ExpandFirstChapter {
	
	public void expandFirstChapter()
	{
		try
		{	
			String firstchapterexpandname = ReadTestData.readDataByTagName("tocdata", "firstchapterexpandname", "1");
			int firstchapter=Driver.driver.findElements(By.cssSelector("div[class='chapter-heading chapter-selected']")).size();
			if(firstchapter<1)
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[title='"+firstchapterexpandname+"']")));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in app helper ExpandFirstChapter",e);
		}
	}

}
