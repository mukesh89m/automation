package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;

public class Openstudylink 
{
	public void Openstudylinkindashboard(int index)
	{
		try
		{
			List<WebElement> allstudy = Driver.driver.findElements(By.cssSelector("div[title='Study']"));
			System.out.println(allstudy.size());
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();", allstudy.get(index));
		}
		catch(Exception e)
		{
			Assert.fail("Exception in class Openstudylink",e);
		}
	}
	
	public void Openpracticelinkindashboard(int index)
	{
		try
		{
			List<WebElement> selectpractice = Driver.driver.findElements(By.cssSelector("span[title='Practice']"));
			System.out.println(selectpractice.size());
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();", selectpractice.get(index));
		}
		catch(Exception e)
		{
			Assert.fail("Exception in class Openpractcelink",e);
		}
	}

}
