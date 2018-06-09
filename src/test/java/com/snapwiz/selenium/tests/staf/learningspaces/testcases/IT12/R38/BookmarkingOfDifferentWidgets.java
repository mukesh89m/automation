package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R38;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentSocialElement;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Bookmark;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;

public class BookmarkingOfDifferentWidgets extends Driver {
	//before running this manually add a discussion widget, flash widget , audio widget and video widget in different lesson in 1st chapter 
	@Test(priority = 1, enabled = true)
	//cant proceed for a bug, already raised,  Bug id = NA
	public void bookmarkingOfDiscussionWidget()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("52");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			String chapter1 = driver.findElement(By.className("selected-chapter-name")).getText();
			System.out.println("chapter1: "+chapter1);
			//go to lesson having discussion widget
			List<WebElement> allNames = driver.findElements(By.cssSelector("a[title='"+chapter1+"']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allNames.get(0));
			Thread.sleep(3000);
			new Bookmark().bookmark("discussion-widget");
			//check for book mark symbol
			boolean bookmarked = new Bookmark().isBookmarked("discussion-widget");
			if(bookmarked == false )
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The bookmark symbol doesn't turn yellow after clicking it.");
			}
			//go to My Journal
			new Navigator().NavigateTo("My Journal");
			//check for the image
			boolean isPresent = new Bookmark().isPresentInMyJournal("widget");
			if(isPresent == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarikng a discussion widget the discussion is not displayed in My Journal Page.");
			}
			
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			//goto another lesson and come back
			driver.findElement(By.cssSelector("a.ls-next")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.ls-prev")).click();
			Thread.sleep(2000);
			new Bookmark().unbookmark("discussion-widget");// unbookmark
			//find the book mark symbol
			boolean isBookmarked0 = new Bookmark().isBookmarked("discussion-widget");
			if(isBookmarked0 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After navigating to some other lesson and again coming back to the lesson and unbookmark is does not trun grey.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isPresent1 = new Bookmark().isMyJournalEmpty();
			if(isPresent1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking discussion widget it is still present in My Journal.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			//goto another lesson and come back
			driver.findElement(By.cssSelector("a.ls-next")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.ls-prev")).click();
			Thread.sleep(2000);
			
			//check whether bookmarked
			boolean isBookmarked = new Bookmark().isBookmarked("discussion-widget");
			if(isBookmarked == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking if we navigate to some other lesson and come back the * icon for the discussion widget is yellow.");
			}
			new Bookmark().bookmark("discussion-widget");//bookmark the widget
			new Navigator().NavigateTo("My Journal");//go to My Journal
			new Bookmark().unbookmarkFromMyJournal();
			boolean isRemoved = new Bookmark().isRemovedFromMyJournal();
			if(isRemoved == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking the discussion widget from My Journal it is still visible in My Journal.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			boolean isBookmarked1 = new Bookmark().isBookmarked("widget");
			if(isBookmarked1 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking the discussion widget from My Journal, in lesson page the * icon is still yellow for the discussion widget.");
			}
			//like on the widget;
			driver.findElement(By.cssSelector("span.ls-right-post-like-link")).click();
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			boolean isPresent3 = new Bookmark().isPresentInCourseStream("widget");
			if(isPresent3 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After liking the discussion widget from lesson page, the entry is not added to Course Stream.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new Bookmark().bookmark("widget");//bookmark the widget
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			boolean isPresent2 = new Bookmark().isPresentInCourseStream("widget");
			if(isPresent2 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the discussion widget from lesson page, the entry is not added to Course Stream.");
			}
			String color = new Bookmark().colorOfStarInCourseStream();
			if(!color.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the discussion widget from lesson page, the * icon in Course Stream is not yellow.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TOCShow().tocHide();
			new Bookmark().unbookmark("widget");
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			String color1 = new Bookmark().colorOfStarInCourseStream();
			if(color1.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking the discussion widget from lesson page, the * icon in Course Stream is not grey.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new AssignmentSocialElement().addComment("Commented text");//add a comment
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			new Bookmark().bookmarkFromCourseStream();
			String color2 = new Bookmark().colorOfStarInCourseStream();
			if(!color2.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the discussion widget from Course Stream, the * icon in Course Stream doesn't turn yellow.");
			}
			//Name of Course Stream should not update
			String courseStreamText = driver.findElement(By.cssSelector("span[class='ls-stream-post__action']")).getText();
			if(courseStreamText.contains("bookmarked this"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the discussion widget from Course Stream, the Course Stream name gets updated.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			boolean isBookmarked2 = new Bookmark().isBookmarked("widget");
			if(isBookmarked2 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the discussion widget from Course Stream, the * icon in lesson page is not yellow.");
			}
			
			new Navigator().NavigateTo("My Journal");//go to My Journal
			//check for the image
			boolean isPresent4 = new Bookmark().isPresentInMyJournal("widget");
			if(isPresent4 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a discussion widget from Course stream the entry is not created in My Journal.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookmarkingOfDiscussionWidget in class BookmarkingOfDifferentWidgets",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void bookMarkDiscussionWidgetAndLike()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("52_1");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			String chapter1 = driver.findElement(By.className("selected-chapter-name")).getText();
			//go to lesson having discussion widget
			List<WebElement> allNames = driver.findElements(By.cssSelector("a[title='"+chapter1+"']"));
			for(WebElement l: allNames)
			{
				System.out.println("-->"+l.getText());
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allNames.get(0));
			Thread.sleep(3000);
			new Bookmark().bookmark("discussion-widget");
			driver.findElement(By.cssSelector("span.ls-discussion-like-link")).click();//like the discussion
			Thread.sleep(3000);
			new Navigator().NavigateTo("Course Stream");
			boolean isPresent = new Bookmark().isPresentInCourseStream("widget");
			if(isPresent == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking widget and then hiting like from lesson, the entry is not added to Course Stream .");
			}
			String color = new Bookmark().colorOfStarInCourseStream();
			if(!color.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking widget and then hiting like from lesson, the * icon color in Course Stream is not yellow.");
			}
			new Bookmark().unbookmarkFromCourseStream();
			String color1 = new Bookmark().colorOfStarInCourseStream();
			if(color1.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking widget from course stream, the * icon color in Course Stream is not grey.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isRemoved = new Bookmark().isMyJournalEmpty();
			if(isRemoved == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking widget from course stream, the * icon color in Course Stream is not grey.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookMarkDiscussionWidgetAndLike in class BookmarkingOfDifferentWidgets",e);
		}
	}
	@Test(priority = 3, enabled = true)
	public void bookmarkingOfFlashWidget()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("52_2");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			String chapter1 = driver.findElement(By.className("selected-chapter-name")).getText();
			//go to lesson having discussion widget
			List<WebElement> allNames = driver.findElements(By.cssSelector("a[title='"+chapter1+"']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allNames.get(1));
			Thread.sleep(3000);
			new Bookmark().bookmark("flash-widget");
			//check for book mark symbol
			boolean bookmarked = new Bookmark().isBookmarked("flash-widget");
			if(bookmarked == false )
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The bookmark symbol for flash widget doesn't turn yellow after clicking it.");
			}
			//go to My Journal
			new Navigator().NavigateTo("My Journal");
			//check for the image
			boolean isPresent = new Bookmark().isPresentInMyJournal("widget");
			if(isPresent == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarikng a flash widget the flash is not displayed in My Journal Page.");
			}
			
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			//goto another lesson and come back
			driver.findElement(By.cssSelector("a.ls-next")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.ls-prev")).click();
			Thread.sleep(2000);
			new Bookmark().unbookmark("flash-widget");// unbookmark
			//find the book mark symbol
			boolean isBookmarked0 = new Bookmark().isBookmarked("flash-widget");
			if(isBookmarked0 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After navigating to some other lesson and again coming back to the lesson and unbookmark the flash widget is does not trun grey.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isPresent1 = new Bookmark().isMyJournalEmpty();
			if(isPresent1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking it is still present in My Journal.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			//goto another lesson and come back
			driver.findElement(By.cssSelector("a.ls-next")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.ls-prev")).click();
			Thread.sleep(2000);
			
			//check whether bookmarked
			boolean isBookmarked = new Bookmark().isBookmarked("flash-widget");
			if(isBookmarked == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking if we navigate to some other lesson and come back the * icon for the flash widget is yellow.");
			}
			new Bookmark().bookmark("flash-widget");//bookmark the widget
			new Navigator().NavigateTo("My Journal");//go to My Journal
			new Bookmark().unbookmarkFromMyJournal();
			boolean isRemoved = new Bookmark().isRemovedFromMyJournal();
			if(isRemoved == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking the flash widget from My Journal it is still visible in My Journal.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			boolean isBookmarked1 = new Bookmark().isBookmarked("flash-widget");
			if(isBookmarked1 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking the flash widget from My Journal, in lesson page the * icon is still yellow for the widget.");
			}
			//like on the widget;
			driver.findElement(By.cssSelector("span.ls-right-post-like-link")).click();
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			boolean isPresent3 = new Bookmark().isPresentInCourseStream("widget");
			if(isPresent3 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After liking the flash widget from lesson page, the entry is not added to Course Stream.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().bookmark("flash-widget");//bookmark the widget
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			boolean isPresent2 = new Bookmark().isPresentInCourseStream("widget");
			if(isPresent2 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the flash widget from lesson page, the entry is not added to Course Stream.");
			}
			String color = new Bookmark().colorOfStarInCourseStream();
			if(!color.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the flash widget from lesson page, the * icon in Course Stream is not yellow.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().unbookmark("flash-widget");
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			String color1 = new Bookmark().colorOfStarInCourseStream();
			if(color1.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking the flash widget from lesson page, the * icon in Course Stream is not grey.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new AssignmentSocialElement().addComment("Commented text");//add a comment
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			new Bookmark().bookmarkFromCourseStream();
			String color2 = new Bookmark().colorOfStarInCourseStream();
			if(!color2.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the flash widget from Course Stream, the * icon in Course Stream doesn't turn yellow.");
			}
			//Name of Course Stream should not update
			String courseStreamText = driver.findElement(By.cssSelector("span[class='ls-stream-post__action']")).getText();
			if(courseStreamText.contains("bookmarked this"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the flash widget from Course Stream, the Course Stream name gets updated.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			boolean isBookmarked2 = new Bookmark().isBookmarked("flash-widget");
			if(isBookmarked2 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the flash widget from Course Stream, the * icon in lesson page is not yellow.");
			}
			
			new Navigator().NavigateTo("My Journal");//go to My Journal
			//check for the image
			boolean isPresent4 = new Bookmark().isPresentInMyJournal("widget");
			if(isPresent4 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a flash widget from Course stream the entry is not created in My Journal.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookmarkingOfFlashWidget in class BookmarkingOfDifferentWidgets",e);
		}
	}
	@Test(priority = 4, enabled = true)
	public void bookMarkFlashWidgetAndLike()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("52_3");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			String chapter1 = driver.findElement(By.className("selected-chapter-name")).getText();
			//go to lesson having discussion widget
			List<WebElement> allNames = driver.findElements(By.cssSelector("a[title='"+chapter1+"']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allNames.get(1));
			Thread.sleep(3000);
			new Bookmark().bookmark("flash-widget");
			new AssignmentSocialElement().clickonlike(0);
			Thread.sleep(3000);
			new Navigator().NavigateTo("Course Stream");
			boolean isPresent = new Bookmark().isPresentInCourseStream("widget");
			if(isPresent == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking flash widget and then hiting like from lesson, the entry is not added to Course Stream .");
			}
			String color = new Bookmark().colorOfStarInCourseStream();
			if(!color.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking flash widget and then hiting like from lesson, the * icon color in Course Stream is not yellow.");
			}
			new Bookmark().unbookmarkFromCourseStream();
			String color1 = new Bookmark().colorOfStarInCourseStream();
			if(color1.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking flash widget from course stream, the * icon color in Course Stream is not grey.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isRemoved = new Bookmark().isMyJournalEmpty();
			if(isRemoved == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking flash widget from course stream, the * icon color in Course Stream is not grey.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookMarkImageWidgetAndLike in class BookMarkVerify",e);
		}
	}
	
	
	@Test(priority = 5, enabled = true)
	public void bookmarkingOfAudioWidget()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("52_4");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			String chapter1 = driver.findElement(By.className("selected-chapter-name")).getText();
			//go to lesson having discussion widget
			List<WebElement> allNames = driver.findElements(By.cssSelector("a[title='"+chapter1+"']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allNames.get(2));
			Thread.sleep(3000);
			new Bookmark().bookmark("audio-widget");
			//check for book mark symbol
			boolean bookmarked = new Bookmark().isBookmarked("audio-widget");
			if(bookmarked == false )
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The bookmark symbol for audio widget doesn't turn yellow after clicking it.");
			}
			//go to My Journal
			new Navigator().NavigateTo("My Journal");
			//check for the image
			boolean isPresent = new Bookmark().isPresentInMyJournal("widget");
			if(isPresent == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarikng a audio widget the audio is not displayed in My Journal Page.");
			}
			
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			//goto another lesson and come back
			driver.findElement(By.cssSelector("a.ls-next")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.ls-prev")).click();
			Thread.sleep(2000);
			new Bookmark().unbookmark("audio-widget");// unbookmark
			//find the book mark symbol
			boolean isBookmarked0 = new Bookmark().isBookmarked("audio-widget");
			if(isBookmarked0 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After navigating to some other lesson and again coming back to the lesson and unbookmark is does not trun grey.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isPresent1 = new Bookmark().isMyJournalEmpty();
			if(isPresent1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking it is still present in My Journal.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			//goto another lesson and come back
			driver.findElement(By.cssSelector("a.ls-next")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.ls-prev")).click();
			Thread.sleep(2000);
			
			//check whether bookmarked
			boolean isBookmarked = new Bookmark().isBookmarked("audio-widget");
			if(isBookmarked == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking if we navigate to some other lesson and come back the * icon for the audio widget is yellow.");
			}
			new Bookmark().bookmark("audio-widget");//bookmark the widget
			new Navigator().NavigateTo("My Journal");//go to My Journal
			new Bookmark().unbookmarkFromMyJournal();
			boolean isRemoved = new Bookmark().isRemovedFromMyJournal();
			if(isRemoved == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking the audio widget from My Journal it is still visible in My Journal.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			boolean isBookmarked1 = new Bookmark().isBookmarked("audio-widget");
			if(isBookmarked1 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking the audio widget from My Journal, in lesson page the * icon is still yellow for the widget.");
			}
			//like on the widget;
			driver.findElement(By.cssSelector("span.ls-right-post-like-link")).click();
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			boolean isPresent3 = new Bookmark().isPresentInCourseStream("widget");
			if(isPresent3 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After liking the audio widget from lesson page, the entry is not added to Course Stream.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().bookmark("audio-widget");//bookmark the widget
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			boolean isPresent2 = new Bookmark().isPresentInCourseStream("widget");
			if(isPresent2 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the audio widget from lesson page, the entry is not added to Course Stream.");
			}
			String color = new Bookmark().colorOfStarInCourseStream();
			if(!color.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the audio widget from lesson page, the * icon in Course Stream is not yellow.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().unbookmark("audio-widget");
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			String color1 = new Bookmark().colorOfStarInCourseStream();
			if(color1.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking the audio widget from lesson page, the * icon in Course Stream is not grey.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new AssignmentSocialElement().addComment("Commented text");//add a comment
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			new Bookmark().bookmarkFromCourseStream();
			String color2 = new Bookmark().colorOfStarInCourseStream();
			if(!color2.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the audio widget from Course Stream, the * icon in Course Stream doesn't turn yellow.");
			}
			//Name of Course Stream should not update
			String courseStreamText = driver.findElement(By.cssSelector("span[class='ls-stream-post__action']")).getText();
			if(courseStreamText.contains("bookmarked this"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the audio widget from Course Stream, the Course Stream name gets updated.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			boolean isBookmarked2 = new Bookmark().isBookmarked("audio-widget");
			if(isBookmarked2 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the audio widget from Course Stream, the * icon in lesson page is not yellow.");
			}
			
			new Navigator().NavigateTo("My Journal");//go to My Journal
			//check for the image
			boolean isPresent4 = new Bookmark().isPresentInMyJournal("widget");
			if(isPresent4 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a audio widget from Course stream the entry is not created in My Journal.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookmarkingOfAudioWidget in class BookmarkingOfDifferentWidgets",e);
		}
	}
	@Test(priority = 6, enabled = true)
	public void bookMarkAudioWidgetAndLike()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("52_5");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			String chapter1 = driver.findElement(By.className("selected-chapter-name")).getText();
			//go to lesson having discussion widget
			List<WebElement> allNames = driver.findElements(By.cssSelector("a[title='"+chapter1+"']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allNames.get(2));
			Thread.sleep(3000);
			new Bookmark().bookmark("audio-widget");
			new AssignmentSocialElement().clickonlike(0);
			Thread.sleep(3000);
			new Navigator().NavigateTo("Course Stream");
			boolean isPresent = new Bookmark().isPresentInCourseStream("widget");
			if(isPresent == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking audio widget and then hiting like from lesson, the entry is not added to Course Stream .");
			}
			String color = new Bookmark().colorOfStarInCourseStream();
			if(!color.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking audio widget and then hiting like from lesson, the * icon color in Course Stream is not yellow.");
			}
			new Bookmark().unbookmarkFromCourseStream();
			String color1 = new Bookmark().colorOfStarInCourseStream();
			if(color1.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking audio widget from course stream, the * icon color in Course Stream is not grey.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isRemoved = new Bookmark().isMyJournalEmpty();
			if(isRemoved == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking audio widget from course stream, the * icon color in Course Stream is not grey.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookMarkAudioWidgetAndLike in class BookmarkingOfDifferentWidgets",e);
		}
	}
	
	
	@Test(priority = 7, enabled = true)
	public void bookmarkingOfVideoWidget()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("52_6");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			String chapter1 = driver.findElement(By.className("selected-chapter-name")).getText();
			//go to lesson having discussion widget
			List<WebElement> allNames = driver.findElements(By.cssSelector("a[title='"+chapter1+"']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allNames.get(3));
			Thread.sleep(3000);
			new Bookmark().bookmark("video-widget");
			//check for book mark symbol
			boolean bookmarked = new Bookmark().isBookmarked("video-widget");
			if(bookmarked == false )
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The bookmark symbol for video widget doesn't turn yellow after clicking it.");
			}
			//go to My Journal
			new Navigator().NavigateTo("My Journal");
			//check for the image
			boolean isPresent = new Bookmark().isPresentInMyJournal("widget");
			if(isPresent == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarikng a video widget the video is not displayed in My Journal Page.");
			}
			
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			//goto another lesson and come back
			driver.findElement(By.cssSelector("a.ls-next")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.ls-prev")).click();
			Thread.sleep(2000);
			new Bookmark().unbookmark("video-widget");// unbookmark
			//find the book mark symbol
			boolean isBookmarked0 = new Bookmark().isBookmarked("flash-widget");
			if(isBookmarked0 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After navigating to some other lesson and again coming back to the lesson and unbookmark is does not trun grey.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isPresent1 = new Bookmark().isMyJournalEmpty();
			if(isPresent1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking it is still present in My Journal.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			//goto another lesson and come back
			driver.findElement(By.cssSelector("a.ls-next")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.ls-prev")).click();
			Thread.sleep(2000);
			
			//check whether bookmarked
			boolean isBookmarked = new Bookmark().isBookmarked("video-widget");
			if(isBookmarked == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking if we navigate to some other lesson and come back the * icon for the video widget is yellow.");
			}
			new Bookmark().bookmark("video-widget");//bookmark the widget
			new Navigator().NavigateTo("My Journal");//go to My Journal
			new Bookmark().unbookmarkFromMyJournal();
			boolean isRemoved = new Bookmark().isRemovedFromMyJournal();
			if(isRemoved == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking the video widget from My Journal it is still visible in My Journal.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			boolean isBookmarked1 = new Bookmark().isBookmarked("video-widget");
			if(isBookmarked1 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking the video widget from My Journal, in lesson page the * icon is still yellow for the widget.");
			}
			//like on the widget;
			driver.findElement(By.cssSelector("span.ls-right-post-like-link")).click();
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			boolean isPresent3 = new Bookmark().isPresentInCourseStream("widget");
			if(isPresent3 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After liking the video widget from lesson page, the entry is not added to Course Stream.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().bookmark("video-widget");//bookmark the widget
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			boolean isPresent2 = new Bookmark().isPresentInCourseStream("widget");
			if(isPresent2 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the video widget from lesson page, the entry is not added to Course Stream.");
			}
			String color = new Bookmark().colorOfStarInCourseStream();
			if(!color.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the video widget from lesson page, the * icon in Course Stream is not yellow.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().unbookmark("video-widget");
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			String color1 = new Bookmark().colorOfStarInCourseStream();
			if(color1.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking the video widget from lesson page, the * icon in Course Stream is not grey.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new AssignmentSocialElement().addComment("Commented text");//add a comment
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			new Bookmark().bookmarkFromCourseStream();
			String color2 = new Bookmark().colorOfStarInCourseStream();
			if(!color2.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the widget from Course Stream, the * icon in Course Stream doesn't turn yellow.");
			}
			//Name of Course Stream should not update
			String courseStreamText = driver.findElement(By.cssSelector("span[class='ls-stream-post__action']")).getText();
			if(courseStreamText.contains("bookmarked this"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the video widget from Course Stream, the Course Stream name gets updated.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			boolean isBookmarked2 = new Bookmark().isBookmarked("video-widget");
			if(isBookmarked2 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the video widget from Course Stream, the * icon in lesson page is not yellow.");
			}
			
			new Navigator().NavigateTo("My Journal");//go to My Journal
			//check for the image
			boolean isPresent4 = new Bookmark().isPresentInMyJournal("widget");
			if(isPresent4 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a video widget from Course stream the entry is not created in My Journal.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookmarkingOfVideoWidget in class BookmarkingOfDifferentWidgets",e);
		}
	}
	@Test(priority = 8, enabled = true)
	public void bookMarkVideoWidgetAndLike()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("52_7");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			String chapter1 = driver.findElement(By.className("selected-chapter-name")).getText();
			//go to lesson having discussion widget
			List<WebElement> allNames = driver.findElements(By.cssSelector("a[title='"+chapter1+"']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allNames.get(3));
			Thread.sleep(3000);
			new Bookmark().bookmark("video-widget");
			new AssignmentSocialElement().clickonlike(0);
			Thread.sleep(3000);
			new Navigator().NavigateTo("Course Stream");
			boolean isPresent = new Bookmark().isPresentInCourseStream("widget");
			if(isPresent == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking video widget and then hiting like from lesson, the entry is not added to Course Stream .");
			}
			String color = new Bookmark().colorOfStarInCourseStream();
			if(!color.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking video widget and then hiting like from lesson, the * icon color in Course Stream is not yellow.");
			}
			new Bookmark().unbookmarkFromCourseStream();
			String color1 = new Bookmark().colorOfStarInCourseStream();
			if(color1.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking video widget from course stream, the * icon color in Course Stream is not grey.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isRemoved = new Bookmark().isMyJournalEmpty();
			if(isRemoved == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking video widget from course stream, the * icon color in Course Stream is not grey.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookMarkVideoWidgetAndLike in class BookmarkingOfDifferentWidgets",e);
		}
		
	}

}
