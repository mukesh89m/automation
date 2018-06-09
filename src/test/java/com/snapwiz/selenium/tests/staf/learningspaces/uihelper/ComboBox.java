package com.snapwiz.selenium.tests.staf.learningspaces.uihelper;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class ComboBox extends Driver{

	public void selectValue(int index,String value)
	{
        WebDriver driver=Driver.getWebDriver();
		try
		{
		List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
		 Thread.sleep(2000);
		 allElements.get(index).click();
 		Thread.sleep(2000);
	    driver.findElement(By.linkText(value)).click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in UI helper ComboBox",e);
		}
	}
	
	public void selectValuebycssselector(String value,String cssselector)
	{
        WebDriver driver=Driver.getWebDriver();
		try
		{
			new Click().clickBycssselector(cssselector);
			driver.findElement(By.linkText(value)).click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in UI helper selectValuebycssselector",e);
		}
	}

    public void selectValueByScrollToView(String valueToExpandDropDown, String value)
    {
        WebDriver driver=Driver.getWebDriver();
        try {

            new Click().clickbylinkText(valueToExpandDropDown);
            WebElement element=driver.findElement(By.xpath("//a[text()='"+value+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(500);
            new Click().clickbylinkText(value);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in UI helper selectValueByScrollToView",e);
        }
    }

    public void selectValueByScrollToViewByXpath(String xpath, String value)
    {
        WebDriver driver=Driver.getWebDriver();
        try {

            new Click().clickbyxpath(xpath);
            WebElement element=driver.findElement(By.xpath("//a[text()='"+value+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(500);
            new Click().clickbylinkText(value);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in UI helper selectValueByScrollToView",e);
        }
    }
}
