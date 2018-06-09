package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R39;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class ChronologicalSequence extends Driver{
	@Test
	public void chronologicalSequence()
	{
		try
		{
            new ResourseCreate().resourseCreate(48, 0);
			new LoginUsingLTI().ltiLogin("48");//login as student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().bookmark("lesson");//bookmark the lesson
			new Navigator().NavigateTo("My Journal");//go to My Journal
			//check for timeline
			int timeline = driver.findElements(By.className("ls-myjournal-timeline-section")).size();
			if(timeline == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Time line/vertical line at left in 'My Journal' is absent.");
			}
			//check for Now marker
			String now = driver.findElement(By.className("myjournal-activity-time")).getText();
			if(!now.equals("Now"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Now marker is absent in time line.");
			}
			//check for arrow with dot
			String arrow = driver.findElement(By.cssSelector("span[class='card-arrow arr-right']")).getCssValue("height");
			if(!arrow.equals("15px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Journal entry card is not displayed with arrow pointing towards dot over timeline.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().bookmark("widget");//bookmark the widget
			String discussionText = new RandomString().randomstring(10);
			new Discussion().postDiscussion(discussionText);
			new Navigator().openResourceFromResourceTabFromCMS(48);
			new Bookmark().bookmark("resource");//bookmark the resource
			new Navigator().NavigateTo("My Journal");//go to My Journal
			List<WebElement> allLikes = driver.findElements(By.className("right-post-like"));
			if(!allLikes.get(0).getText().contains("(0)"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The journal entries for resource dont have Like option.");
			}
			if(!allLikes.get(1).getText().contains("(0)"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The journal entries for discussion dont have Like option.");
			}

			//list all comments
			List<WebElement> allComments = driver.findElements(By.id("ls-right-post-comment-link"));
			
			if(!allComments.get(0).getText().contains("Comments"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The journal entries for resource dont have comment option.");
			}
			if(!allComments.get(1).getText().contains("Comments"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The journal entries for discussion dont have comment option.");
			}

			//list all ages 
			List<WebElement> allAges = driver.findElements(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']"));
			
			if(allAges.get(0).getText() == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The journal entries for resource dont have the age.");
			}
			if(allAges.get(1).getText() == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The journal entries for discussion dont have age.");
			}

			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase chronologicalSequence in class ChronologicalSequence",e);
		}
	}

}
