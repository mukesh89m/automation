package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

public class ExpandCollapseChapter extends Driver {
	
	String chapter1 = ReadTestData.readDataByTagName("tocdata", "chapter1","1");
	String chapter2 = ReadTestData.readDataByTagName("tocdata", "chapter2","1");

	public void collapseChapter(int chapterNumber)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			if(chapterNumber == 1)
			{
				 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[title='"+chapter1+"']")));
			
			}
			if(chapterNumber == 2)
			{
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[title='"+chapter2+"']")));

			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper collapseChapter in class ExpandCollapseChapter",e);
		}
	}
	
	public void expandChapter(int chapterNumber)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			if(chapterNumber == 1)
			{
				 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[title='"+chapter1+"']")));
			}
			if(chapterNumber == 2)
			{
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[title='"+chapter2+"']")));
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper collapseChapter in class ExpandCollapseChapter",e);
		}
	}
	
	public void expand(String chapterName)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			int headervalue=driver.findElements(By.cssSelector("a[class='ls-chapter-navigation-icon ls-toc-sprite ls-study-plan']")).size();
			System.out.println(headervalue);
			if(headervalue>=1)
				((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.cssSelector("a[class='ls-chapter-navigation-icon ls-toc-sprite ls-study-plan']")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[title='"+chapterName+"']")));
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper expand in class ExpandCollapseChapter",e);
		}
	}
	
}
