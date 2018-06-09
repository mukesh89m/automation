package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.TextFetch;

public class CourseStream {

	public void hideFromCourseStream(int zerobasedindex)
	{
		try
		{
			//hover on element
			List<WebElement> allElement = Driver.driver.findElements(By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']"));
			WebElement element = allElement.get(zerobasedindex); 
			Locatable hoverItem = (Locatable) element;
			Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
			mouse.mouseMove(hoverItem.getCoordinates());
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-dropdown__toggle")));//click on toggle icon
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.className("ls-hide-post")));//click on Hide post
		    Thread.sleep(5000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper hideFromCourseStream in class CourseStream",e);
		}
	}
	public void addCommentInCourseStream(String comment, int zerobasedindex)
	{
		try
		{
			//driver.findElement(By.linkText("Comments")).click();
			List<WebElement> allComments = Driver.driver.findElements(By.partialLinkText("Comments"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allComments.get(zerobasedindex));
			Thread.sleep(2000);
			Driver.driver.switchTo().activeElement().sendKeys(comment);//comment 
			Driver.driver.switchTo().activeElement().sendKeys(Keys.RETURN);//press enter key after write comment
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper addCommentInCourseStream in class CourseStream",e);
		}
	}
	public boolean isPresentCourseStream(String type)
	{
		boolean result= false;
		try
		{
			int noactivity = Driver.driver.findElements(By.className("no-activity-msg-desc")).size();
			if(noactivity == 1)
				return false;
			String text = new TextFetch().textfetchbyclass("ls-media__body");
			if(type.equalsIgnoreCase("discussion"))
			{
				
				if(text.contains("posted a discussion"))
					
					result = true;
					
				else
					result = false;
			}
			else if(type.equalsIgnoreCase("note"))
			{
				
				if(text.contains("posted a note"))
					
					result = true;
					
				else
					result = false;
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Bookmark in method isPresentInMyJournal", e);
		}
		return result;
		
	}
    //open a DW from Course Stream
    public void openDWFromCourseStream(int index)
    {
        try
        {
            List<WebElement> allElements = Driver.driver.findElements(By.cssSelector("span[class='ls-lesson-title ellipsis']"));//list of all DW
            allElements.get(index).click();
            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in aphhelper Bookmark in method openFromCourseStream", e);
        }
    }
}
