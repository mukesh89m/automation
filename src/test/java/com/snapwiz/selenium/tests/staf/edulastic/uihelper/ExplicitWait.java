package com.snapwiz.selenium.tests.staf.edulastic.uihelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class ExplicitWait extends Driver
{

	public void explicitWaitbyid(String id)
	{
		try
		{
            WebDriver driver=Driver.getWebDriver();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
		}
		catch(Exception e)
		{
			Assert.fail("exception in apphelper ExplicitWait in method explicitWaitbyid",e);
		}
	}

    public void explicitWaitById(String id,String textInElement, int waitingTime)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            WebDriverWait wait = new WebDriverWait(driver, waitingTime);
            wait.until(ExpectedConditions.textToBePresentInElementValue(driver.findElement(By.id(id)),textInElement));

        }
        catch(Exception e)
        {
            Assert.fail("exception in apphelper ExplicitWait method explicitWaitByXpath",e);
        }
    }


    public void explicitWaitbyxpath(String xpath)
{
    try
    {
        WebDriver driver=Driver.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, 120);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }
    catch(Exception e)
    {
        Assert.fail("exception in apphelper ExplicitWait in method explicitWaitbyxpath",e);
    }
}




    public void explicitWaitByClass(String classname)
	{
		try
		{
            WebDriver driver=Driver.getWebDriver();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.className(classname)));
		}
		catch(Exception e)
		{
			Assert.fail("exception in apphelper ExplicitWait method explicitWaitByCssSelector",e);
		}
	}



    public void explicitWaitByClass(String className, int waitTime)
{
    try
    {
        WebDriver driver=Driver.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.elementToBeClickable(By.className(className)));
    }
    catch(Exception e)
    {
        Assert.fail("exception in UI helper ExplicitWait in method explicitWaitByCssSelector",e);
    }
}



    public void explicitWaitByElement(WebElement element, int waitTime)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
        catch(Exception e)
        {
            Assert.fail("exception in UI helper ExplicitWait in method explicitWaitByCssSelector",e);
        }
    }



    public void explicitWaitById(String id, int waitTime)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
        }
        catch(Exception e)
        {
            Assert.fail("exception in UI helper ExplicitWait in method explicitWaitById",e);
        }
    }






    public void explicitWaitByLinkText(String linkText, int waitTime)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));
        }
        catch(Exception e)
        {
            Assert.fail("exception in UI helper ExplicitWait in method explicitWaitByCssSelector",e);
        }
    }



	
	public void explicitWaitByCssSelector(String cssselector)
	{
		try
		{
            WebDriver driver=Driver.getWebDriver();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssselector)));
		}
		catch(Exception e)
		{
			Assert.fail("exception in apphelper ExplicitWait method explicitWaitByCssSelector",e);
		}
	}

    public void explicitWaitByCssSelector(String cssselector, int waitTime)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssselector)));
        }
        catch(Exception e)
        {
            Assert.fail("exception in UI helper ExplicitWait in method explicitWaitByCssSelector",e);
        }
    }

}
