package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Screenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.MouseHover;

public class Bookmark {
	
	public boolean isBookmarked(String type)
	{
		boolean result = false;
		try
		{
			if(type.equalsIgnoreCase("lesson"))
			{
			String bookmark = Driver.driver.findElement((By.xpath("//*[starts-with(@class, 'lesson-bookmark-sprite-img')]"))).getCssValue("background-position");
			
			if(bookmark.contains("-32px -33px"))
				result = false;
			else
				result = true;
			}
			else if (type.equalsIgnoreCase("widget")) {

				String bookmark = Driver.driver.findElement((By.xpath("//*[starts-with(@class, 'ls-bookmark-widget ls-widget-')]"))).getCssValue("background-image");
				if(bookmark.contains("lsWidgetUnbookmarked.png"))
					result = false;
				else
					result = true;
			}
			else if(type.equalsIgnoreCase("resource")){
				String bookmark = Driver.driver.findElement((By.xpath("//*[starts-with(@class, 'favourite-resource')]"))).getAttribute("title");
				if(bookmark.contains("Bookmark"))
					result = false;
				else
					result = true;
			}
			
			else if(type.equalsIgnoreCase("discussion")){
				String position1 = Driver.driver.findElement(By.cssSelector("i[class='ls-right-section-sprites ls--right-star-icon']")).getCssValue("background-position");
				if(position1.equals("-77px -24px"))
					result = true;
				else 
					result = false;
			}
			else if(type.equalsIgnoreCase("note")){
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[data-id='fav']"))); //opening star tab
                Thread.sleep(5000);
                int index=0;
                /*List<WebElement> boxes = Driver.driver.findElements(By.className("ls-right-user-content"));
                for(WebElement box:boxes)
                {
                    if(box.getText().contains("You posted a note"))
                    {
                        index++;
                    }
                    else
                        break;
                }*/
                //List<WebElement> staricons = Driver.driver.findElements(By.cssSelector("a[class='sidebar-favourite active']"));
                String bookmark = Driver.driver.findElement(By.xpath("//*[starts-with(@class, 'sidebar-favourite')]")).getAttribute("title");
                System.out.println("bookmark: "+bookmark);
                if(bookmark.equals("Remove bookmark"))
					result = true;
				else 
					result = false;
			}
			else if(type.equalsIgnoreCase("discussion-widget")){
				String bookmark = Driver.driver.findElement((By.xpath("//*[starts-with(@class, 'ls-discussion-widget-bkmarking ls-discussion-widget-')]"))).getAttribute("title");
				if(bookmark.equals("Remove bookmark"))
					result = true;
				else 
					result = false;
			}
			else if (type.equalsIgnoreCase("flash-widget")) {

				String bookmark = Driver.driver.findElement((By.xpath("//*[starts-with(@class, 'ls-bookmark-widget ls-widget-')]"))).getCssValue("background-image");
				if(bookmark.contains("lsWidgetUnbookmarked.png"))
					result = false;
				else
					result = true;
			}
			else if (type.equalsIgnoreCase("audio-widget")) {

				String bookmark = Driver.driver.findElement((By.xpath("//*[starts-with(@class, 'ls-bookmark-widget ls-widget-')]"))).getCssValue("background-image");
				if(bookmark.contains("lsWidgetUnbookmarked.png"))
					result = false;
				else
					result = true;
			}
			else if (type.equalsIgnoreCase("video-widget")) {

				String bookmark = Driver.driver.findElement((By.xpath("//*[starts-with(@class, 'ls-bookmark-widget ls-widget-')]"))).getCssValue("background-image");
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
		try
		{
			if(type.equalsIgnoreCase("lesson"))
			{
			int bookmark = Driver.driver.findElements(By.cssSelector("a.lesson-bookmark-sprite-img.bookmark-lesson")).size();
			if(bookmark == 0)
				{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The bookmark symbol for lesson is absent.");
				}			
			}

			if(type.equalsIgnoreCase("widget"))
			{
			int bookmark = Driver.driver.findElements(By.cssSelector("div[title='Add to My Notes']")).size();
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
		try
		{
			if(type.equalsIgnoreCase("lesson"))
			{
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a.lesson-bookmark-sprite-img.bookmark-lesson")));
				Thread.sleep(2000);
			}
			//pass "widget" when its a image type widget
			else if(type.equalsIgnoreCase("widget"))
			{
				//mouse hover
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='ls-bookmark-widget ls-widget-unbookmarked']")));
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("resource"))
			{

				/*List<WebElement> all = Driver.driver.findElements(By.cssSelector("a[title='Bookmark']"));
				for(WebElement l: all)
				{
					System.out.println("==>"+l.getAttribute("titile"));
				}
				all.get(1).click();*/
				//Driver.driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon-resource")).click();
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='favourite-resource']")));
				//Driver.driver.findElement(By.cssSelector("a[class='favourite-resource']")).click();
				//((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[title='Bookmark']")));
				//.click();
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("discussion"))
			{
				//click on grey star 
				Driver.driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();
				Thread.sleep(2000);
				//click on star to in pop up
				Driver.driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("note"))
			{
				//click on grey star 
				Driver.driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();
				Thread.sleep(2000);
				//click on star to in pop up
				Driver.driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("discussion-widget"))
			{
				//click on star icon
				Driver.driver.findElement(By.cssSelector("span.ls-discussion-widget-bkmarking")).click();
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("flash-widget"))
			{
				//mouse hover
				WebElement menuitem = Driver.driver.findElement(By.cssSelector("div[class='flash-widget widget-content']")); 
				Locatable hoverItem = (Locatable) menuitem;
				Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
				mouse.mouseMove(hoverItem.getCoordinates());
				Driver.driver.findElement(By.cssSelector("div[title='Bookmark']")).click();
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("audio-widget"))
			{
				//mouse hover
				WebElement menuitem = Driver.driver.findElement(By.cssSelector("div[class='widget audio-widget']")); 
				Locatable hoverItem = (Locatable) menuitem;
				Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
				mouse.mouseMove(hoverItem.getCoordinates());
				Driver.driver.findElement(By.cssSelector("div[title='Bookmark']")).click();
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("video-widget"))
			{
				//mouse hover
				WebElement menuitem = Driver.driver.findElement(By.cssSelector("div[class='wistia_embed widget-content']")); 
				Locatable hoverItem = (Locatable) menuitem;
				Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
				mouse.mouseMove(hoverItem.getCoordinates());
				Driver.driver.findElement(By.cssSelector("div[title='Bookmark']")).click();
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
		try
		{
			if(type.equalsIgnoreCase("lesson"))
			{
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a.lesson-bookmark-sprite-img.unbookmark-lesson")));
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("widget"))
			{
				//mouse hover
				WebElement menuitem = Driver.driver.findElement(By.cssSelector("img[class='image-main widget-content']")); 
				Locatable hoverItem = (Locatable) menuitem;
				Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
				mouse.mouseMove(hoverItem.getCoordinates());
				//click
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a.ls-bookmark-widget.ls-widget-bookmarked")));
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("resource"))
			{
				Driver.driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon-resource")).click();
				Thread.sleep(2000);
			}
			//pass "discussion" when discussion is posted from student or instructor side
			else if(type.equalsIgnoreCase("discussion"))
			{
				//click on Discussion star icon
				Driver.driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();
				Thread.sleep(2000);
				//click on Discussion star icon in pop up to remove bookmark
				Driver.driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();
			    Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("note"))
			{
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[data-id='fav']"))); //opening star tab
                int index=0;
                List<WebElement> boxes = Driver.driver.findElements(By.className("ls-right-user-content"));
                for(WebElement box:boxes)
                {
                    if(!box.getText().contains("You posted a note"))
                    {
                        index++;
                    }
                    else
                        break;
                }
                Driver.driver.findElements(By.className("ls-right-user-post-body")).get(index).click();
			    Thread.sleep(2000);
                Driver.driver.findElement(By.xpath("//*[@class='favourite-annotation active']/i")).click(); //clicking on star icon to un-bookmark note
                Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("discussion-widget"))
			{
				Driver.driver.findElement(By.cssSelector("span.ls-discussion-widget-bkmarking")).click();
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("flash-widget"))
			{
				//mouse hover
				WebElement menuitem = Driver.driver.findElement(By.cssSelector("div[class='flash-widget widget-content']")); 
				Locatable hoverItem = (Locatable) menuitem;
				Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
				mouse.mouseMove(hoverItem.getCoordinates());
				Driver.driver.findElement(By.cssSelector("div.ls-bookmark-widget.ls-widget-bookmarked")).click();
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("audio-widget"))
			{
				//mouse hover
				WebElement menuitem = Driver.driver.findElement(By.cssSelector("div[class='widget audio-widget']")); 
				Locatable hoverItem = (Locatable) menuitem;
				Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
				mouse.mouseMove(hoverItem.getCoordinates());
				Driver.driver.findElement(By.cssSelector("div.ls-bookmark-widget.ls-widget-bookmarked")).click();
				Thread.sleep(2000);
			}
			else if(type.equalsIgnoreCase("video-widget"))
			{
				//mouse hover
				WebElement menuitem = Driver.driver.findElement(By.cssSelector("div[class='wistia_embed widget-content']")); 
				Locatable hoverItem = (Locatable) menuitem;
				Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
				mouse.mouseMove(hoverItem.getCoordinates());
				Driver.driver.findElement(By.cssSelector("div.ls-bookmark-widget.ls-widget-bookmarked")).click();
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
		try
		{
			int noactivity = Driver.driver.findElements(By.className("no-activity-msg-desc")).size();
			if(noactivity == 1)
				return false;
			if(type.equalsIgnoreCase("lesson"))
			{
			String journalText = Driver.driver.findElement(By.className("journal-card-title")).getText();
			if(!journalText.contains("Bookmarked a lesson on"))
				{
					result = false;
				}
			else
				result = true;
			}
			else if(type.equalsIgnoreCase("widget"))
			{
			String journalText = Driver.driver.findElement(By.className("journal-card-title")).getText();
			if(!journalText.contains("Bookmarked a media on"))
				{
				result = false;
				}
			else
				result = true;
			}
			else if(type.equalsIgnoreCase("resource"))
			{
				String journalText = Driver.driver.findElement(By.className("journal-card-title")).getText();
				if(!journalText.contains("Bookmarked a resource on"))
					
					result = false;
					
				else
					result = true;
			}
			else if(type.equalsIgnoreCase("discussion"))
			{
				String journalText = Driver.driver.findElement(By.className("journal-card-title")).getText();
				if(journalText.contains("Posted a discussion on") || journalText.contains("Bookmarked a discussion on"))
					
					result = true;
					
				else
					result = false;
			}
			else if(type.equalsIgnoreCase("note"))
			{
				String journalText = Driver.driver.findElement(By.className("journal-card-title")).getText();
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
		try
		{
			String errorMessage = Driver.driver.findElement(By.cssSelector("p[class='no-activity-msg-desc']")).getText();
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
		try
		{
			String position = Driver.driver.findElement(By.cssSelector("i[class='ls-icon-img ls--star-icon']")).getCssValue("background-position");
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
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("i.ls-icon-img.ls--star-icon")));

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
			Driver.driver.findElement(By.cssSelector("i.ls-icon-img.ls--star-icon")).click();
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
		try
		{
			if(type.equalsIgnoreCase("discussion"))
			{
				String courseStreamText = Driver.driver.findElement(By.cssSelector("span[class='ls-stream-post__action']")).getText();
				if(!courseStreamText.contains("posted a discussion"))
					
					result = false;
					
				else
					result = true;
			}
			if(type.equalsIgnoreCase("perspective"))
			{
				String courseStreamText = Driver.driver.findElement(By.cssSelector("span[class='ls-stream-post__action']")).getText();
				if(!courseStreamText.contains("posted a perspective"))
					
					result = false;
					
				else
					result = true;
			}
			if(type.equalsIgnoreCase("widget"))
			{
				String courseStreamText = Driver.driver.findElement(By.cssSelector("span[class='ls-stream-post__action']")).getText();
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
		try{
			String position = Driver.driver.findElement(By.cssSelector("span[class='my-journal-card-bookmark-icon general-sprites']")).getCssValue("background-position");
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
			Driver.driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();
			Thread.sleep(2000);
			//click on Discussion star icon in pop up to remove bookmark
			Driver.driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();
		    Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method unBookmarkDiscussionk", e);
		}
	}*/
	public void unbookmarkFromMyJournal()
	{
		try
		{
			MouseHover.mouserhover("my-journal-activity-details");
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("span.my-journal-card-bookmark-icon.general-sprites")));
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
		try
		{			
			MouseHover.mouserhover("my-journal-activity-details");
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("span.my-journal-card-bookmark-icon.general-sprites")));
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
		try
		{
			//check whether entry is removed from My Journal
			int anyPost = Driver.driver.findElements(By.className("journal-card-title")).size();
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
