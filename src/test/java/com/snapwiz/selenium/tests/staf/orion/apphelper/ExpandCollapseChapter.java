package com.snapwiz.selenium.tests.staf.orion.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;

public class ExpandCollapseChapter {

	public void collapseChapter(int chapterNumber)
	{
		try
		{
			if(chapterNumber == 1)
			{
				 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[title=' Cell Reproduction']")));
			//	 Driver.driver.findElement(By.cssSelector("div[title='Chemical change']")).click();
		//	String chapterstatus = Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div/ul[2]/div/div[2]/div/li/div/div")).getAttribute("class");
		//	if(!chapterstatus.equals("chapter-heading")) //collapsing chapter 1
		//		 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("chapter-heading-label")));
			}
			if(chapterNumber == 2)
			{
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[title='Transferring and transforming energy']")));

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
				 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[title=' Cell Reproduction']")));
		//	String chapterstatus = Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div/ul[2]/div/div[2]/div/li/div/div")).getAttribute("class");
		//	if(chapterstatus.equals("chapter-heading")) //Expanding chapter 1
		//		 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("chapter-heading-label")));
			}
			if(chapterNumber == 2)
			{
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[title='Transferring and transforming energy']")));
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper collapseChapter in class ExpandCollapseChapter",e);
		}
	}
	
}
