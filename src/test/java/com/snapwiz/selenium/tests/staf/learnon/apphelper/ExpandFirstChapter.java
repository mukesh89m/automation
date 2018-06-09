package com.snapwiz.selenium.tests.staf.learnon.apphelper;

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

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
