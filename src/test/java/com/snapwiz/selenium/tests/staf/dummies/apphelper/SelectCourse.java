package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Config;
import com.snapwiz.selenium.tests.staf.dummies.Driver;

public class SelectCourse
{
	public void selectcourse()
	{
		try
		{
			Driver.driver.findElement(By.cssSelector("img[title='"+Config.course+"']")).click();//.select course
		}
		catch(Exception e)
		{
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in testcase selectcourse in class SelectCourse",e);
		}
	}
	public void selectchapter(String chaptername)
	{
		try
		{
			Driver.driver.findElement(By.cssSelector("div[title='"+chaptername+"']")).click();//select chapter name	
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in testcase selectchapter in class SelectCourse",e);
		}
	}
	public void selectassignment(String assignmentname)
	{
		try
		{
			Driver.driver.findElement(By.cssSelector("div[title='"+assignmentname+"']")).click();//hover assignment name
		}
		catch(Exception e)
		{
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in testcase selectassignment in class SelectCourse",e);
		}
	}
	
	public void selectChapterByIndex(int zeroBasedIndex)
	{
		try
		{
			List<WebElement> chapters = Driver.driver.findElements(By.cssSelector("div[class='course-chapter-label node']"));
			chapters.get(zeroBasedIndex).click();
		}
		catch(Exception e)
		{
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in testcase selectChapterByIndex in class SelectCourse",e);
		}
		
	}
	
	public void selectAssessmentByIndex(int zeroBasedIndex)
	{
		try
		{
			List<WebElement> assessment = Driver.driver.findElements(By.className("collection-assessment-name"));
			assessment.get(zeroBasedIndex).click();
		}
		catch(Exception e)
		{
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in testcase selectAssessmentByIndex in class SelectCourse",e);
		}
		
	}

}
