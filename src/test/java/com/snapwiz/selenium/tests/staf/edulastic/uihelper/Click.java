package com.snapwiz.selenium.tests.staf.edulastic.uihelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class Click extends Driver {
	public void clickByclassname(String classname)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
           WebDriverUtil.clickOnElementUsingJavascript ((new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(By.className(classname))));
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper clickByclassname in class Click",e);
		}
	}
	
	public void clickBycssselector(String selector)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			//WebDriverUtil.waitTillElementToBeClickable(driver.findElement(By.cssSelector(selector)),60);
			//driver.findElement(By.cssSelector(selector)).click();
            WebDriverUtil.clickOnElementUsingJavascript((new WebDriverWait(driver, 40)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selector))));
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper clickBycssselector in class Click",e);
		}
	}

    public void clickByXpath(String xpath)
    {
        try
        {
			WebDriver driver=Driver.getWebDriver();
			//WebDriverUtil.waitTillElementToBeClickable(driver.findElement(By.xpath(xpath)),60);
			//driver.findElement(By.xpath(xpath)).click();
           WebDriverUtil.clickOnElementUsingJavascript((new WebDriverWait(driver, 40)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))));
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper clickByXpath in class Click",e);
        }
    }

	public void clickByid(String id)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			//WebDriverUtil.waitTillElementToBeClickable(driver.findElement(By.id(id)),60);
			//driver.findElement(By.id(id)).click();
            WebDriverUtil.clickOnElementUsingJavascript((new WebDriverWait(driver, 40)).until(ExpectedConditions.presenceOfElementLocated(By.id(id))));
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper clickByid in class Click",e);
		}
	}
	
	public void clickbylist(String classname,int index)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			List<WebElement> alllist=driver.findElements(By.className(classname));
			Thread.sleep(2000);
			alllist.get(index).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper clickbylist in class Click",e);
		}
	}
	
	public void clickbylistid(String id,int index)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			List<WebElement> alllist=driver.findElements(By.id(id));
			Thread.sleep(2000);
			WebDriverUtil.clickOnElementUsingJavascript(alllist.get(index));
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper clickbylistid in class Click",e);
		}
	}
	
	public void clickbylistcssselector(String selector,int index)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			List<WebElement> alllist=driver.findElements(By.cssSelector(selector));
			alllist.get(index).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper clickbylistcssselector in class Click",e);
		}
	}
    public void clickByListClassName(String classname,int index)
    {
        try
        {
			WebDriver driver=Driver.getWebDriver();
            List<WebElement> alllist=driver.findElements(By.className(classname));
            alllist.get(index).click();
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper clickbylistcssselector in class Click",e);
        }
    }
	public void clickongraphbar()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper clickongraphbar in class Click",e);
		}
	}
	public void clickbylinkText(String linkText)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
            (new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText))).click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper clickbylinkText in class Click",e);
		}
	}
	
	public void clickbypartiallinkText(String partiallinkText)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
            (new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(partiallinkText))).click();

		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper clickbypartiallinkText in class Click",e);
		}
	}

    public void clickIfDisplayed(int dataIndex, List<WebElement> we)
    {
        try
        {
            for(WebElement c : we)
            {
                if(c.isDisplayed())
                {
                    c.click();
                    break;
                }
            }
        }catch (Exception e){
            Assert.fail("Exception in method 'clickIfDisplayed' in class 'Click'", e);

        }
    }

    public void clickByListXpath(List<WebElement> we,int index)
    {
        try
        {
            we.get(index).click();
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper clickbylistcssselector in class Click",e);
        }
    }
}


