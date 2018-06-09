package com.snapwiz.selenium.tests.staf.orion.uihelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class ComboBox extends Driver {

	public void selectValue(int index,String value)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
		List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
		 Thread.sleep(3000);
		 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allElements.get(index));
		// allElements.get(index).click();
		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText(value)));
	    //driver.findElement(By.linkText(value)).click();
		Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in UI helper ComboBox",e);
		}
	}
	
	public void selectValuewithtitle(int index,String value)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
		List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
		 Thread.sleep(3000);
		allElements.get(index).click(); 
		Thread.sleep(2000);
	    driver.findElement(By.cssSelector("a[title='"+value+"']")).click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in UI helper ComboBox",e);
		}
	}
	
}
