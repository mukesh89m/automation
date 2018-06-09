package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class CourseStream extends Driver{

	public void hideFromCourseStream(int zerobasedindex)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			//hover on element
			List<WebElement> allElement = driver.findElements(By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']"));
			WebElement element = allElement.get(zerobasedindex); 
			Locatable hoverItem = (Locatable) element;
			Mouse mouse = ((HasInputDevices) driver).getMouse();
			mouse.mouseMove(hoverItem.getCoordinates());
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-dropdown__toggle")));//click on toggle icon
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();",  driver.findElement(By.className("ls-hide-post")));//click on Hide post
		    Thread.sleep(5000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper hideFromCourseStream in class CourseStream",e);
		}
	}
	public void addCommentInCourseStream(String comment, int zerobasedindex)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			//driver.findElement(By.linkText("Comments")).click();
			List<WebElement> allComments = driver.findElements(By.partialLinkText("Comments"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allComments.get(zerobasedindex));
			Thread.sleep(2000);
			driver.switchTo().activeElement().sendKeys(comment);//comment
			driver.switchTo().activeElement().sendKeys(Keys.RETURN);//press enter key after write comment
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
		WebDriver driver=Driver.getWebDriver();
		try
		{
			int noactivity = driver.findElements(By.className("no-activity-msg-desc")).size();
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
		WebDriver driver=Driver.getWebDriver();
        try
        {
            List<WebElement> allElements = driver.findElements(By.cssSelector("span[class='ls-lesson-title ellipsis']"));//list of all DW
            allElements.get(index).click();
            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in aphhelper Bookmark in method openFromCourseStream", e);
        }
    }
}
