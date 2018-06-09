package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelperAppium;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class Click extends Driver{
	public void clickBy(By by)
	{
		try
		{
			Thread.sleep(2000);
			WebElement we=driver.findElement(by);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			//new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in apphelper clickBy in class Click",e);
		}
	}



    public void clickbylist(By by,int index)
    {
        try
        {
            List<WebElement> alllist=driver.findElements(by);
            Thread.sleep(2000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", alllist.get(index));
            //alllist.get(index).click();
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            //new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in apphelper clickbylist in class Click",e);
        }
    }



    public void clickByElement(WebElement element)
    {
        try
        {
            Thread.sleep(2000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",element);
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            //new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in apphelper clickByclassname in class Click",e);
        }
    }

    public void clickByElement(List<WebElement> elements,int index)
    {
        try{
            Thread.sleep(2000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", elements.get(index));
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            //new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in apphelper clickByclassname in class Click",e);
        }
    }

}
