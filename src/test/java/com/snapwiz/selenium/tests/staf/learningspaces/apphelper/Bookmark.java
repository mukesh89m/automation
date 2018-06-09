package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;

public class Bookmark extends Driver {
	
	public boolean isBookmarked(String type)
	{
		boolean result = false;
		WebDriver driver=Driver.getWebDriver();
		try
		{
			if(type.equalsIgnoreCase("lesson"))
			{
			String bookmark = driver.findElement((By.xpath("//*[starts-with(@class, 'lesson-bookmark-sprite-img')]"))).getCssValue("background-position");
			
			if(bookmark.contains("-32px -33px"))
				result = false;
			else
				result = true;
			}
			else if (type.equalsIgnoreCase("widget")) {

				String bookmark = driver.findElement((By.xpath("//*[starts-with(@class, 'ls-bookmark-widget ls-widget-')]"))).getCssValue("background-image");
				if(bookmark.contains("lsWidgetUnbookmarked.png"))
					result = false;
				else
					result = true;
			}
			else if(type.equalsIgnoreCase("resource")){
				String bookmark = driver.findElement((By.xpath("//*[starts-with(@class, 'favourite-resource')]"))).getAttribute("title");
				if(bookmark.contains("Bookmark"))
					result = false;
				else
					result = true;
			}
			
			else if(type.equalsIgnoreCase("discussion")){
				String position1 = driver.findElement(By.cssSelector("i[class='ls-right-section-sprites ls--right-star-icon']")).getCssValue("background-position");
				if(position1.equals("-77px -24px"))
					result = true;
				else 
					result = false;
			}
			else if(type.equalsIgnoreCase("note")){
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[data-id='fav']"))); //opening star tab
                Thread.sleep(5000);
                int index=0;
                /*List<WebElement> boxes = driver.findElements(By.className("ls-right-user-content"));
                for(WebElement box:boxes)
                {
                    if(box.getText().contains("You posted a note"))
                    {
                        index++;
                    }
                    else
                        break;
                }*/
                //List<WebElement> staricons = driver.findElements(By.cssSelector("a[class='sidebar-favourite active']"));
                String bookmark = driver.findElement(By.xpath("//*[starts-with(@class, 'sidebar-favourite')]")).getAttribute("title");
                System.out.println("bookmark: "+bookmark);
                if(bookmark.equals("Remove bookmark"))
					result = true;
				else 
					result = false;
			}
			else if(type.equalsIgnoreCase("discussion-widget")){
				String bookmark = driver.findElement((By.xpath("//*[starts-with(@class, 'ls-discussion-widget-bkmarking ls-discussion-widget-')]"))).getAttribute("title");
				if(bookmark.equalsIgnoreCase("Remove bookmark"))
					result = true;
				else 
					result = false;
			}
			else if (type.equalsIgnoreCase("flash-widget")) {

				String bookmark = driver.findElement((By.xpath("//*[starts-with(@class, 'ls-bookmark-widget ls-widget-')]"))).getCssValue("background-image");
				if(bookmark.contains("lsWidgetUnbookmarked.png"))
					result = false;
				else
					result = true;
			}
			else if (type.equalsIgnoreCase("audio-widget")) {

				String bookmark = driver.findElement((By.xpath("//*[starts-with(@class, 'ls-bookmark-widget ls-widget-')]"))).getCssValue("background-image");
				if(bookmark.contains("lsWidgetUnbookmarked.png"))
					result = false;
				else
					result = true;
			}
			else if (type.equalsIgnoreCase("video-widget")) {

				String bookmark = driver.findElement((By.xpath("//*[starts-with(@class, 'ls-bookmark-widget ls-widget-')]"))).getCssValue("background-image");
				if(bookmark.contains("lsWidgetUnbookmarked.png"))
					result = false;
				else
					result = true;
			}
		}
		
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Bookmark in method isBookmarked.", e);
		}
		return result;
	}
	
	public void isPresent(String type)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			if(type.equalsIgnoreCase("lesson"))
			{
			int bookmark = driver.findElements(By.cssSelector("a.lesson-bookmark-sprite-img.bookmark-lesson")).size();
			if(bookmark == 0)
				{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The bookmark symbol for lesson is absent.");
				}			
			}

			if(type.equalsIgnoreCase("widget"))
			{
			int bookmark = driver.findElements(By.cssSelector("div[title='Add to My Notes']")).size();
			if(bookmark == 0)
				{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The bookmark symbol is absent for widget.");
				}			
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Bookmark in method isPresent.", e);
		}
	}

	public void bookmark(String type)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			if(type.equalsIgnoreCase("lesson"))
			{
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a.lesson-bookmark-sprite-img.bookmark-lesson")));
				Thread.sleep(2000);
			}
			//pass "widget" when its a image type widget
			else if(type.equalsIgnoreCase("widget"))
			{
				//mouse hover
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='ls-bookmark-widget ls-widget-unbookmarked']")));
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("resource"))
			{

				/*List<WebElement> all = driver.findElements(By.cssSelector("a[title='Bookmark']"));
				for(WebElement l: all)
				{
					System.out.println("==>"+l.getAttribute("titile"));
				}
				all.get(1).click();*/
				//driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon-resource")).click();
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='favourite-resource']")));
				//driver.findElement(By.cssSelector("a[class='favourite-resource']")).click();
				//((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Bookmark']")));
				//.click();
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("discussion"))
			{
				//click on grey star 
				driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();
				Thread.sleep(2000);
				//click on star to in pop up
				driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("note"))
			{
				//click on grey star 
				driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();
				Thread.sleep(2000);
				//click on star to in pop up
				driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("discussion-widget"))
			{
				//click on star icon
				driver.findElement(By.cssSelector("span.ls-discussion-widget-bkmarking")).click();
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("flash-widget"))
			{
				//mouse hover
				WebElement menuitem = driver.findElement(By.cssSelector("div[class='flash-widget widget-content']"));
				Locatable hoverItem = (Locatable) menuitem;
				Mouse mouse = ((HasInputDevices) driver).getMouse();
				mouse.mouseMove(hoverItem.getCoordinates());
				driver.findElement(By.cssSelector("div[title='Bookmark']")).click();
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("audio-widget"))
			{
				//mouse hover
				WebElement menuitem = driver.findElement(By.cssSelector("div[class='widget audio-widget']"));
				Locatable hoverItem = (Locatable) menuitem;
				Mouse mouse = ((HasInputDevices) driver).getMouse();
				mouse.mouseMove(hoverItem.getCoordinates());
				driver.findElement(By.cssSelector("div[title='Bookmark']")).click();
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("video-widget"))
			{
				//mouse hover
				WebElement menuitem = driver.findElement(By.cssSelector("div[class='wistia_embed widget-content']"));
				Locatable hoverItem = (Locatable) menuitem;
				Mouse mouse = ((HasInputDevices) driver).getMouse();
				mouse.mouseMove(hoverItem.getCoordinates());
				driver.findElement(By.cssSelector("div[title='Bookmark']")).click();
				Thread.sleep(2000);
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Bookmark in method bookmark.", e);
		}
	}
	public void unbookmark(String type)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			if(type.equalsIgnoreCase("lesson"))
			{
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a.lesson-bookmark-sprite-img.unbookmark-lesson")));
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("widget"))
			{
				//mouse hover
				WebElement menuitem = driver.findElement(By.cssSelector("img[class='image-main widget-content']"));
				Locatable hoverItem = (Locatable) menuitem;
				Mouse mouse = ((HasInputDevices) driver).getMouse();
				mouse.mouseMove(hoverItem.getCoordinates());
				//click
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a.ls-bookmark-widget.ls-widget-bookmarked")));
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("resource"))
			{
				driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon-resource")).click();
				Thread.sleep(2000);
			}
			//pass "discussion" when discussion is posted from student or instructor side
			else if(type.equalsIgnoreCase("discussion"))
			{
				//click on Discussion star icon
				driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();
				Thread.sleep(2000);
				//click on Discussion star icon in pop up to remove bookmark
				driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();
			    Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("note"))
			{
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[data-id='fav']"))); //opening star tab
                int index=0;
                List<WebElement> boxes = driver.findElements(By.className("ls-right-user-content"));
                for(WebElement box:boxes)
                {
                    if(!box.getText().contains("You posted a note"))
                    {
                        index++;
                    }
                    else
                        break;
                }
                driver.findElements(By.className("ls-right-user-post-body")).get(index).click();
			    Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@class='favourite-annotation active']/i")).click(); //clicking on star icon to un-bookmark note
                Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("discussion-widget"))
			{
				driver.findElement(By.cssSelector("span.ls-discussion-widget-bkmarking")).click();
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("flash-widget"))
			{
				//mouse hover
				WebElement menuitem = driver.findElement(By.cssSelector("div[class='flash-widget widget-content']"));
				Locatable hoverItem = (Locatable) menuitem;
				Mouse mouse = ((HasInputDevices) driver).getMouse();
				mouse.mouseMove(hoverItem.getCoordinates());
				driver.findElement(By.cssSelector("div.ls-bookmark-widget.ls-widget-bookmarked")).click();
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("audio-widget"))
			{
				//mouse hover
				WebElement menuitem = driver.findElement(By.cssSelector("div[class='widget audio-widget']"));
				Locatable hoverItem = (Locatable) menuitem;
				Mouse mouse = ((HasInputDevices) driver).getMouse();
				mouse.mouseMove(hoverItem.getCoordinates());
				driver.findElement(By.cssSelector("div.ls-bookmark-widget.ls-widget-bookmarked")).click();
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("video-widget"))
			{
				//mouse hover
				WebElement menuitem = driver.findElement(By.cssSelector("div[class='wistia_embed widget-content']"));
				Locatable hoverItem = (Locatable) menuitem;
				Mouse mouse = ((HasInputDevices) driver).getMouse();
				mouse.mouseMove(hoverItem.getCoordinates());
				driver.findElement(By.cssSelector("div.ls-bookmark-widget.ls-widget-bookmarked")).click();
				Thread.sleep(2000);
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Bookmark in method unbookmark", e);
		}
	}
	
	public boolean isPresentInMyJournal(String type)
	{
		boolean result= false;
		WebDriver driver=Driver.getWebDriver();
		try
		{
			int noactivity = driver.findElements(By.className("no-activity-msg-desc")).size();
			if(noactivity == 1)
				return false;
			if(type.equalsIgnoreCase("lesson"))
			{
			String journalText = driver.findElement(By.className("journal-card-title")).getText();
			if(!journalText.contains("Bookmarked a lesson on"))
				{
					result = false;
				}
			else
				result = true;
			}
			else if(type.equalsIgnoreCase("widget"))
			{
			String journalText = driver.findElement(By.className("journal-card-title")).getText();
			if(!journalText.contains("Bookmarked a media on"))
				{
				result = false;
				}
			else
				result = true;
			}
			else if(type.equalsIgnoreCase("resource"))
			{
				String journalText = driver.findElement(By.className("journal-card-title")).getText();
				if(!journalText.contains("Bookmarked a resource on"))
					
					result = false;
					
				else
					result = true;
			}
			else if(type.equalsIgnoreCase("discussion"))
			{
				String journalText = driver.findElement(By.className("journal-card-title")).getText();
				if(journalText.contains("Posted a discussion on") || journalText.contains("Bookmarked a discussion on"))
					
					result = true;
					
				else
					result = false;
			}
			else if(type.equalsIgnoreCase("note"))
			{
				String journalText = driver.findElement(By.className("journal-card-title")).getText();
				if(journalText.contains("Posted a note on"))
					
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
	public boolean isMyJournalEmpty()
	{
		boolean result= false;
		WebDriver driver=Driver.getWebDriver();
		try
		{
			String errorMessage = driver.findElement(By.cssSelector("p[class='no-activity-msg-desc']")).getText();
			if(!errorMessage.equals("It seems you have not done any activity yet."))
				result = false;
			else
				result = true;
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Bookmark in method isMyJournalEmpty", e);
		}
		return result;
		
	}
	public String colorOfStarInCourseStream()
	{
		String color = "";
		WebDriver driver=Driver.getWebDriver();
		try
		{
			String position = driver.findElement(By.cssSelector("i[class='ls-icon-img ls--star-icon']")).getCssValue("background-position");
            System.out.println("position: "+position);
            if(position.equals("-94px -38px"))
				color = "yellow";
			else
				color = "grey";
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Bookmark in method colorOfStarInCourseStream", e);
		}
		return color;
	}
	public void bookmarkFromCourseStream()
	{
		try{
			//bookmark from Course Stream
			WebDriver driver=Driver.getWebDriver();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("i.ls-icon-img.ls--star-icon")));

			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Bookmark in method bookmarkFromCourseStream", e);
		}
	}
	public void unbookmarkFromCourseStream()
	{
		try{
			//unbookmark from Course Stream
			WebDriver driver=Driver.getWebDriver();
			driver.findElement(By.cssSelector("i.ls-icon-img.ls--star-icon")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Bookmark in method unbookmarkFromCourseStream", e);
		}
	}
	public boolean isPresentInCourseStream(String type)
	{
		boolean result = false;
		WebDriver driver=Driver.getWebDriver();
		try
		{
			if(type.equalsIgnoreCase("discussion"))
			{
				String courseStreamText = driver.findElement(By.cssSelector("span[class='ls-stream-post__action']")).getText();
				if(!courseStreamText.contains("posted a discussion"))
					
					result = false;
					
				else
					result = true;
			}
			if(type.equalsIgnoreCase("perspective"))
			{
				String courseStreamText = driver.findElement(By.cssSelector("span[class='ls-stream-post__action']")).getText();
				if(!courseStreamText.contains("posted a perspective"))
					
					result = false;
					
				else
					result = true;
			}
			if(type.equalsIgnoreCase("widget"))
			{
				String courseStreamText = driver.findElement(By.cssSelector("span[class='ls-stream-post__action']")).getText();
				if(courseStreamText.contains("bookmarked this") || courseStreamText.contains("liked this"))
					
					result = true;
					
				else
					result = false;
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Bookmark in method isPresentInCourseStream", e);
		}
		return result;
	}
	public boolean isBookmarkedInMyJournal()
	{
		boolean result = false;
		WebDriver driver=Driver.getWebDriver();
		try{
			String position = driver.findElement(By.cssSelector("span[class='my-journal-card-bookmark-icon general-sprites']")).getCssValue("background-position");
			if(position.equals("-96px -38px"))
				result = true;
			else
				result = false;
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Bookmark in method isBookmarkedInMyJournal", e);
		}
		return result;
	}
	/*public void unBookmarkDiscussion()
	{
		try
		{
			//click on Discussion star icon
			driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();
			Thread.sleep(2000);
			//click on Discussion star icon in pop up to remove bookmark
			driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();
		    Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method unBookmarkDiscussionk", e);
		}
	}*/
	public void unbookmarkFromMyJournal()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			new MouseHover().mouserhover("my-journal-activity-details");
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.my-journal-card-bookmark-icon.general-sprites")));
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase unbookmarkFromMyJournal in class Bookmark",e);
		}
	}
	public void unbookmarkNoteFromMyJournal()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{			
			new MouseHover().mouserhover("my-journal-activity-details");
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.my-journal-card-bookmark-icon.general-sprites")));
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase unbookmarkFromMyJournal in class Bookmark",e);
		}
	}
	public boolean isRemovedFromMyJournal()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			//check whether entry is removed from My Journal
			int anyPost = driver.findElements(By.className("journal-card-title")).size();
			if(anyPost == 0)
				return true;
			else
				return false;
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase isRemovedFromMyJournal in class Bookmark",e);
		}
		return false;
	}
	
}
