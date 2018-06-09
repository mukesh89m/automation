package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R38;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Bookmark;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Discussion;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;

public class BookmarkingOfPostedDiscussion extends Driver{
	@Test(priority = 1, enabled = true)
	public void bookmarkingOfPostedDiscussion()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("87");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String discussionText = new RandomString().randomstring(6);
			new Discussion().postDiscussion(discussionText);
			String text = driver.findElement(By.className("ls-right-user-post-body")).getText();
			//check discussion is posted or not
			if(!text.equals(discussionText))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Discussion is not posted.");
			}
			//check it is bookmarked by default
			boolean isBookmarked1 = new Bookmark().isBookmarked("discussion");
			if(isBookmarked1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The discussion that has bees posted is not bookmarked bydefault.");
			}
			
			new Navigator().NavigateTo("Course Stream"); // go to Course Stream
			boolean isPresent1 = new Bookmark().isPresentInCourseStream("discussion");
			if(isPresent1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The discussion that has been posted is not appearing in Course Stream.");
			}
			//check the star color
			String color = new Bookmark().colorOfStarInCourseStream();
			if(!color.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The star icon for the discussion that has been posted is not appearing yellow in Course Stream.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			//check the discussion in My Journal
			boolean isPresent = new Bookmark().isPresentInMyJournal("discussion");
			if(isPresent == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The discussion that has been posted is not appearing in My Journal.");
			}
			//check star color in My Journal
			boolean isBookmarked = new Bookmark().isBookmarkedInMyJournal();
			if(isBookmarked == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The star icon for the discussion that has been posted is not appearing yellow in My Journal.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			//navigate to some other lesson come to the same lesson again
			//click on next lesson 
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-next")));
			Thread.sleep(3000);
			//click on previous lesson 
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-prev")));
			Thread.sleep(3000);
			boolean isBookmarked2 = new Bookmark().isBookmarked("discussion");
			if(isBookmarked2 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to any other lesson and coming back to the previous lesson the * icon of discussion doesn't remain yellow.");
			}
			new Bookmark().unbookmark("discussion");//unbookmark discussion
			boolean isBookmarked3 = new Bookmark().isBookmarked("discussion");
			if(isBookmarked3 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark for discussion the * icon remains yellow.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			//check the discussion in My Journal
			boolean isPresent2 = new Bookmark().isMyJournalEmpty();
			if(isPresent2 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark for discussion the discussion is not removed from My Journal.");
			}
			new Navigator().NavigateTo("Course Stream"); // go to Course Stream
			String color1 = new Bookmark().colorOfStarInCourseStream();
			if(color1.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark for discussion the * icon for the discussion doesnt apprear grey in Course Stream.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookmarkingOfPostedDiscussion in class BookmarkingOfPostedDiscussion",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void postDicussionAndBookmarkInCourseStream()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("100");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String discussionText = new RandomString().randomstring(6);
			new Discussion().postDiscussion(discussionText);
			new Navigator().NavigateTo("Course Stream");// go to Course Stream
			new Bookmark().unbookmarkFromCourseStream();//unbookmark discussion
			String color = new Bookmark().colorOfStarInCourseStream();
			if(color.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("* icon for a recently added discussion doesnt apprear grey after removing the bookmark in Course Stream.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			//check the discussion in My Journal
			boolean isEmpty = new Bookmark().isMyJournalEmpty();
			if(isEmpty == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark for discussion from the Course Stream the discussion is not removed from My Journal.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to eTextbook
			new TOCShow().tocHide();
			//check for * color in lesson
			boolean isBookmarked = new Bookmark().isBookmarked("discussion");
			if(isBookmarked == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark for discussion from the Course Stream the * icon for the discussion is not grey in Lesson page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase postDicussionAndBookmarkInCourseStream in class BookmarkingOfPostedDiscussion",e);
		}
	}
	@Test(priority = 3, enabled = true)
	public void postDicussionAndBookmarkInMyJournal()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("104");
			new Navigator().NavigateTo("eTextbook");//navigate to eTextbook
			new TOCShow().tocHide();
			String discussionText = new RandomString().randomstring(6);
			new Discussion().postDiscussion(discussionText);
			new Navigator().NavigateTo("My Journal");//go to My Journal
			new Bookmark().unbookmarkFromMyJournal();
			//check whether entry is removed from My Journal
			int anyPost = driver.findElements(By.className("journal-card-title")).size();
			if(anyPost > 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark of discussion from my journal page it doesn't get removed from my journal.");
			}
			new Navigator().NavigateTo("Course Stream");// go to Course Stream
			String color = new Bookmark().colorOfStarInCourseStream();
			if(color.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course Stream * icon for a discussion doesnt apprear grey after removing the bookmark from My Journal.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to eTextbook
			new TOCShow().tocHide();
			boolean isBookmarked = new Bookmark().isBookmarked("discussion");
			if(isBookmarked == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking a discussion from My Journal page, it still appears bookmarked in lesson page.");
			}
			
			new Bookmark().bookmark("discussion"); //bookmark the same discussion
			new Navigator().NavigateTo("Course Stream");// go to Course Stream
			String color1 = new Bookmark().colorOfStarInCourseStream();
			if(!color1.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a discussion in lesson page the * icon color doesn't change to yellow in Course Stream.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isBookmarked1 = new Bookmark().isBookmarkedInMyJournal();
			if(isBookmarked1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a discussion in lesson page the * icon color doesn't change to yellow in My Journal.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase postDicussionAndBookmarkInMyJournal in class BookmarkingOfPostedDiscussion",e);
		}
	}
	@Test(priority = 4, enabled = true)
	public void discussionPostedByAnotherStudent()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("111");
			new Navigator().NavigateTo("eTextbook");//navigate to eTextbook
			new TOCShow().tocHide();
			String discussionText = new RandomString().randomstring(6);
			new Discussion().postDiscussion(discussionText);//post discussion
			new LoginUsingLTI().ltiLogin("111_1");
			new Navigator().NavigateTo("eTextbook");//navigate to eTextbook
			new TOCShow().tocHide();
			boolean isBookmarked = new Bookmark().isBookmarked("discussion");
			if(isBookmarked == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Discussion posted by another student also appears yellow.");
			}
			new Bookmark().bookmark("discussion");//bookmark the discussion
			Thread.sleep(3000);
			boolean isBookmarked1=driver.findElement(By.cssSelector("i[class='ls-right-section-sprites ls--right-star-icon']")).isDisplayed();
			//boolean isBookmarked1 = new Bookmark().isBookmarked("discussion");
			if(isBookmarked1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a discussion which has been posted by some other student it doesn't turn to yellow.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isPresent = new Bookmark().isPresentInMyJournal("discussion");
			if(isPresent == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a discussion which has been posted by some other student, The discussion is absent in My Journal.");
			}
			new Navigator().NavigateTo("Course Stream");// go to Course Stream
			String color = new Bookmark().colorOfStarInCourseStream();
			if(!color.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a discussion which has been posted by some other student, the Star icon for that particular discussion course stream entry is not yellow.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			new Bookmark().unbookmarkFromMyJournal();// un bookmark from my journal
			boolean isRemoved = new Bookmark().isRemovedFromMyJournal();
			if(isRemoved == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark of discussion from my journal page it doesn't get removed from my journal.");
			}
			new Navigator().NavigateTo("Course Stream");// go to Course Stream
			String color1 = new Bookmark().colorOfStarInCourseStream();
			if(color1.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark of discussion from my journal page the * color doesnt turn to to grey in Course stream.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to eTextbook
			new TOCShow().tocHide();
			boolean isBookmarked2 = new Bookmark().isBookmarked("discussion");
			if(isBookmarked2 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark of discussion from my journal page the * color doesnt turn to to grey in eTextbook.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase discussionPostedByAnotherStudent in class BookmarkingOfPostedDiscussion",e);
		}
	}

	
}
