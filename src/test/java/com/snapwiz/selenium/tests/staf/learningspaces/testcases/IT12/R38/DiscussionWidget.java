package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R38;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class DiscussionWidget extends Driver {
@Test(priority = 1, enabled = true)
	public void discussionWidget()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("135");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new Widget().perspectiveAdd();
			boolean isBookmarked = new Bookmark().isBookmarked("discussion-widget");
			if(isBookmarked == false)
			{
				Assert.fail("After adding perspective to a discussion widget the widget doesnt get bookmarked.");
			}
			new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
			//check the perspective in Course Stream
			boolean isPresent = new Bookmark().isPresentInCourseStream("perspective");
			if(isPresent == false)
			{
				Assert.fail("After adding perspective to a discussion widget the entry is added in Course Stream.");
			}
			//check color of star icon in Course Stream
			String color = new Bookmark().colorOfStarInCourseStream();
			System.out.println("colcor:"+color);
			if(!color.equals("yellow"))
			{
				Assert.fail("After adding perspective to a discussion widget the entry is added in Course Stream is not Grey in color.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.ls-discussion-like-link")));//hit like for the discussion
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
			//check color of star icon in Course Stream
			String color1 = new Bookmark().colorOfStarInCourseStream();
			System.out.println("colcor:"+color);
			if(color1.equals("grey"))
			{
				Assert.fail("After hiting like in discussion widget then in Course Stream the * icon is Grey in color.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase discussionWidget in class DiscussionWidget",e);
		}
	}
@Test(priority = 2, enabled = true)
public void discussionWidgetForInstructor()
{
	try
	{
		new LoginUsingLTI().ltiLogin("141");
		new Navigator().NavigateTo("eTextbook");//navigate to etextbook
        new TopicOpen().openLessonWithDiscussionWidget();
		new Widget().perspectiveAdd();
		boolean isBookmarked = new Bookmark().isBookmarked("discussion-widget");
		if(isBookmarked == false)
		{
			Assert.fail("After adding perspective to a discussion widget the widget doesnt get bookmarked.");
		}
		new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
		//check the perspective in Course Stream
		boolean isPresent = new Bookmark().isPresentInCourseStream("perspective");
		if(isPresent == false)
		{
			Assert.fail("After adding perspective to a discussion widget the entry is added in Course Stream.");
		}
		//check color of star icon in Course Stream
		String color = new Bookmark().colorOfStarInCourseStream();
		System.out.println("colour:"+color);
		if(!color.equals("yellow"))
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("After adding perspective to a discussion widget the entry is added in Course Stream is not yellow in color.");
		}
		new Navigator().NavigateTo("eTextbook");//navigate to etextbook
		new TOCShow().tocHide();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.ls-discussion-like-link")));//hit like for the discussion
		Thread.sleep(2000);
		//driver.findElement(By.cssSelector("span.ls-discussion-like-link")).click();
		new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
		//check color of star icon in Course Stream
		String color1 = new Bookmark().colorOfStarInCourseStream();
		if(color1.equals("grey"))
		{
			Assert.fail("After hiting like in discussion widget then in Course Stream the * icon is Grey in color.");
		}
	}
	catch(Exception e)
	{
		Assert.fail("Exception in testcase discussionWidgetForInstructor in class DiscussionWidget",e);
	}
}
}
