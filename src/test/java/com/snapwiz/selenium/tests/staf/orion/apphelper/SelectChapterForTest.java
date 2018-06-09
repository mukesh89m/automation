package com.snapwiz.selenium.tests.staf.orion.apphelper;



import java.util.List;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class SelectChapterForTest extends Driver
{
	public void selectchapterfortest(int index,int confidencelevel)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			String confidence=Integer.toString(confidencelevel);
			List<WebElement> allbegin=driver.findElements(By.cssSelector("img[title=\"Begin Diagnostic\"]"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",allbegin.get(index));//click on test ,thats we want to start
			Thread.sleep(2000);
			WebElement we=driver.findElement(By.cssSelector("a[id='"+confidence+"']"));//click on confidence level
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
			Thread.sleep(2000);
			WebElement we1=driver.findElement(By.className("al-continue-to-diagnostic"));//click on continue button to start test
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",we1);
			/*for(WebElement allbutton:allbeing)
			{
				System.out.println(allbutton.getAttribute("src"));
				if(index==chpno)
				{
					((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",allbutton);
					Thread.sleep(2000);
					break;
				}
				chpno++;
			}*/
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper method SelectChapterForTest in class SelectChapterForTest",e);
		}
	}
	
	public void selectprqacticetest(int practicetestpostion)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{			
			List<WebElement> allbegin=driver.findElements(By.cssSelector("span[title=\"Practice\"]"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",allbegin.get(practicetestpostion));//click on test ,thats we want to start
			Thread.sleep(2000);			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper method SelectChapterForTest in class SelectChapterForTest",e);
		}
	}
	
	
}
