package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.ReadTestData;

public class ExpandCollapseChapter {
	
	String chapter1 = ReadTestData.readDataByTagName("tocdata", "chapter1","1");
	String chapter2 = ReadTestData.readDataByTagName("tocdata", "chapter2","1");

	public void collapseChapter(int chapterNumber)
	{
		try
		{
			
			if(chapterNumber == 1)
			{
				 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[title='"+chapter1+"']")));
			
			}
			if(chapterNumber == 2)
			{
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[title='"+chapter2+"']")));

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
			if(chapterNumber == 1)
			{
				 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[title='"+chapter1+"']")));
			}
			if(chapterNumber == 2)
			{
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[title='"+chapter2+"']")));
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
			int headervalue=Driver.driver.findElements(By.cssSelector("a[class='ls-chapter-navigation-icon ls-toc-sprite ls-study-plan']")).size();	
			System.out.println(headervalue);
			if(headervalue>=1)
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.cssSelector("a[class='ls-chapter-navigation-icon ls-toc-sprite ls-study-plan']")));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[title='"+chapterName+"']")));
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper expand in class ExpandCollapseChapter",e);
		}
	}
	
}
