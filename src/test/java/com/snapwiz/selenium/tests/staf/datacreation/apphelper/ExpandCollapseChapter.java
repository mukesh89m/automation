package com.snapwiz.selenium.tests.staf.datacreation.apphelper;

import com.snapwiz.selenium.tests.staf.datacreation.Driver;
import com.snapwiz.selenium.tests.staf.datacreation.ReadTestData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class ExpandCollapseChapter extends Driver {
	
	String chapter1 = "";
	String chapter2 = "";

	public void collapseChapter(int chapterNumber)
	{
		try
		{
			
			if(chapterNumber == 1)
			{
				 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[title='"+chapter1+"']")));
			
			}
			if(chapterNumber == 2)
			{
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[title='"+chapter2+"']")));

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
				 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[title='The Study of Life']")));
			}
			else if(chapterNumber == 2)
			{
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[title='The Chemical Foundation of Life']")));
			}

            else if(chapterNumber == 3) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[title='Así es mi familia']")));
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
			int headervalue=driver.findElements(By.cssSelector("a[class='ls-chapter-navigation-icon ls-toc-sprite ls-study-plan']")).size();
			System.out.println(headervalue);
			if(headervalue>=1)
				((JavascriptExecutor) driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.cssSelector("a[class='ls-chapter-navigation-icon ls-toc-sprite ls-study-plan']")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[title='"+chapterName+"']")));
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper expand in class ExpandCollapseChapter",e);
		}
	}
	
}
