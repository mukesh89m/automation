package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R38;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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

import java.util.List;

public class BookmarkingOfPostedNotes extends Driver {

	@Test(priority = 1, enabled = true)
	public void bookmarkingOfPostedNotes()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("120");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String noteText = new RandomString().randomstring(10);
			new Discussion().postNote(noteText);//post a note
			//check note is posted or not
            boolean notepresent = false;
            List<WebElement> notes = driver.findElements(By.className("ls-right-user-post-body"));
            for(WebElement note : notes)
            {
                if(note.getText().equals(noteText)) {
                    notepresent = true;
                    break;
                }
            }
            if(notepresent == false)
                Assert.fail("The note has not been posted.");
			//check note is bookmarked by default
			boolean isBookmarked = new Bookmark().isBookmarked("note");
			if(isBookmarked == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The note that has been posted is not bookmarked by default.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			//check the discussion in My Journal
			boolean isPresent = new Bookmark().isPresentInMyJournal("note");
			if(isPresent == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The note that has been posted is not appearing in My Journal.");
			}
            //bookmark symbol for a note is absent in My Notes so commenting the part
			/*//check star color in My Journal
			boolean isBookmarked1 = new Bookmark().isBookmarkedInMyJournal();
			if(isBookmarked1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The star icon for the note that has been posted is not appearing yellow in My Journal.");
			}*/
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			//navigate to some other lesson come to the same lesson again
			//click on next lesson 
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-next")));
			Thread.sleep(3000);
			//click on previous lesson 
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-prev")));
			Thread.sleep(3000);
			boolean isBookmarked2 = new Bookmark().isBookmarked("note");
			if(isBookmarked2 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to any other lesson and coming back to the previous lesson the * icon of note doesn't remain yellow.");
			}
            //user is not allowed to unbookmark the note so commenting this part
			/*new Bookmark().unbookmark("note");//unbookmark note
			boolean isBookmarked3 = new Bookmark().isBookmarked("note");
			if(isBookmarked3 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark for note the * icon remains yellow.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			//check the discussion in My Journal
			boolean isPresent2 = new Bookmark().isMyJournalEmpty();
			if(isPresent2 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark for note, the note is not removed from My Journal.");
			}*/
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookmarkingOfPostedNotes in class BookmarkingOfPostedNotes",e);
		}
	}
    ////Bookmarking and unbookmarking of a note entry is removed from My Journal so Disabling the TC
	@Test(priority = 2, enabled = false)
	public void postNoteAndUnbookmarkFromMyJournal()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("129");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String discussionText = new RandomString().randomstring(6);
            new Discussion().postNote(discussionText);//post a note
            //Bookmarking and unbookmarking of a note entry is removed from My Journal
            new Navigator().NavigateTo("My Journal");//go to My Journal
            new Bookmark().unbookmarkNoteFromMyJournal();//unbookmark note from my journal
            boolean isRemoved = new Bookmark().isRemovedFromMyJournal();
            if(isRemoved == false)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After removing bookmark for note from the My Journal the note is not removed from My Journal.");
            }
            new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TOCShow().tocHide();
            boolean isPresent = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", discussionText);
            if(isPresent == true)
                Assert.fail("After deleting the note from my notes page its visible under Star tab of eTextBook");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			boolean isBookmarked = new Bookmark().isBookmarked("note");
			if(isBookmarked == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark for note from the My Journal the note remains bookmarked in lesson page.");
			}
			new Bookmark().bookmark("note"); 
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isBookmarked1 = new Bookmark().isBookmarkedInMyJournal();
			if(isBookmarked1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a note in lesson page the entry is not getting added to My Journal.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase postNoteAndUnbookmarkFromMyJournal in class BookmarkingOfPostedNotes",e);
		}
	}

}
