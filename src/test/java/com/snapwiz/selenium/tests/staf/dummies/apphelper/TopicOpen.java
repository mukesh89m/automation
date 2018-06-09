package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.dummies.apphelper.ExpandFirstChapter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;

public class TopicOpen 
{

	public void topicOpen(String topicName)
	{
		try
		{
			Thread.sleep(3000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[title='"+topicName+"']")));
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper TopicOpen",e);
		}
	}
	public void lessonOpen(int chapterIndex, int lessonIndex)
	{
		try
		{
            Thread.sleep(10000);
			Driver.driver.findElements(By.className("chapter-heading-label")).get(chapterIndex).click();
			Thread.sleep(3000);
			Driver.driver.findElements(By.cssSelector("a[data-type='lesson']")).get(lessonIndex).click();
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper lessonOpen",e);
		}
	}
	public void TOCOpen()
	{
		try
		{
			/*int headervalue=Driver.driver.findElements(By.id("showHeader")).size();			
			if(headervalue>=1)
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.cssSelector("span[id='showHeader']")));*/
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("div[class='ls-chapter-tree']")).click();//TOC open
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper TOCOpen",e);
		}
	}
	
	public void topicopeninnewtab(String topicName,int topicnumber)
	{
		try
		{
			int index=0;
			
		
			Actions action = new Actions(Driver.driver);
			WebElement we= Driver.driver.findElement(By.cssSelector("a[title='"+topicName+"']"));
			action.moveToElement(we).build().perform();
			//Thread.sleep(3000);
			List<WebElement> topicopeninnewtab = Driver.driver.findElements(By.cssSelector("div[class='pointer-arrow chapter-badge toc-sprite']"));
           //((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", By.cssSelector("div[class='pointer-arrow chapter-badge toc-sprite']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", topicopeninnewtab.get(topicnumber));
		//	topicopeninnewtab.get(topicnumber).click();
			//Driver.driver.findElement(By.cssSelector("div[class='pointer-arrow chapter-badge toc-sprite']")).click(); 
			 
		//	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", By.cssSelector("div[class='pointer-arrow chapter-badge toc-sprite']"));
		//	Driver.driver.findElement(By.cssSelector("div[class='pointer-arrow chapter-badge toc-sprite']")).click();
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method topicopeninnewtab App helper TOCOpen",e);
		}
	
	}
	
	public void ResourcesOpenInNewtab(int resourseopen,int resnumber)
	{
		try
		{
			Driver.driver.findElement(By.cssSelector("span[title='Resources']")).click();//click on resources tab
			Thread.sleep(3000);
			Actions action = new Actions(Driver.driver);
			List<WebElement> we= (List<WebElement>) Driver.driver.findElements(By.cssSelector("li[class='resource-content-posts-list']"));
			action.moveToElement(we.get(resourseopen)).build().perform();
			//Driver.driver.findElement(By.cssSelector("a[class='toc-sprite folder-forward']")).click();
			List<WebElement> resoursenewtab = Driver.driver.findElements(By.cssSelector("a[class='toc-sprite folder-forward']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", resoursenewtab.get(resnumber));
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method ResourcesOpenInNewtab App helper TOCOpen",e);
		}
	}
	public void resourcesopeninexistingtab(int resourcesno)
	{
		try
		{
			Actions action = new Actions(Driver.driver);
			List<WebElement> we= (List<WebElement>) Driver.driver.findElements(By.cssSelector("li[class='resource-content-posts-list']"));
			action.moveToElement(we.get(resourcesno)).build().perform();
			int index=0;
			List<WebElement> allexitrestab=Driver.driver.findElements(By.cssSelector("a[class='toc-sprite folder-cycle']"));
			for(WebElement element:allexitrestab)
			{
				if(index==resourcesno)
				{
					element.click();
					break;
				}
				index++;
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method resourcesopeninexistingtab App helper TOCOpen",e);
		}
	}
	
	public void AssignmentOpen(int assignmentopen)
	{
		try
		{
			Driver.driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on resources tab
			Thread.sleep(3000);
			Actions action = new Actions(Driver.driver);
			List<WebElement> we= (List<WebElement>) Driver.driver.findElements(By.cssSelector("li[class='assignment-content-posts-list']"));
			action.moveToElement(we.get(assignmentopen)).build().perform();
			//Driver.driver.findElement(By.cssSelector("a[class='toc-sprite folder-forward']")).click();
			List<WebElement> resoursenewtab = Driver.driver.findElements(By.cssSelector("a[class='toc-sprite folder-forward']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", resoursenewtab.get(assignmentopen));
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method ResourcesOpenInNewtab App helper TOCOpen",e);
		}
	}
	
	public void adativetestopeninnewtab(String topicName,int testnumbernumber)
	{
		try
		{

			Actions action = new Actions(Driver.driver);
			WebElement we= Driver.driver.findElement(By.cssSelector("a[title='"+topicName+"']"));
			action.moveToElement(we).build().perform();
			List<WebElement> adaptiveopeninnewtab = Driver.driver.findElements(By.cssSelector("div[class='pointer-arrow toc-adaptive-icon chapter-badge']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", adaptiveopeninnewtab.get(testnumbernumber));
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method adativetestopeninnewtab App helper TOCOpen",e);
		}
	}
	
	public void topicOpenLast(String topicToOpen)
	{
		try
		{
			new ExpandFirstChapter().expandFirstChapter();
			List<WebElement> allChNames = Driver.driver.findElements(By.cssSelector("a[title='"+topicToOpen+"']"));
			int numberOfChapters = allChNames.size();
			allChNames.get(numberOfChapters-1).click();
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method topicOpenLast App helper TOCOpen",e);
		}
	}

}
