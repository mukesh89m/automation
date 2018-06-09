package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R39;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentSocialElement;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Bookmark;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.CourseStream;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Discussion;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.MyJournal;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ResourseCreate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.HighlightText;

public class VerifyMyJournal extends Driver {
	@Test(priority = 1, enabled = true)
	public void UIofMyJournal()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("5");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().bookmark("lesson");//bookmark the lesson
			new Navigator().NavigateTo("My Journal");//go to My Journal
			//check for header
			String header = driver.findElement(By.className("my-journal-header")).getText();
			if(!header.contains("My Notes"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The hader 'My Notes' is absent in My journal.");
			}
			//check for timeline
			int timeline = driver.findElements(By.className("ls-myjournal-timeline-section")).size();
			if(timeline == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Time line/vertical line at left in 'My Journal' is absent.");
			}
			//check for journal entry
			int journalEntry = driver.findElements(By.cssSelector("div[class='my-journal-card bookmarked-lesson']")).size();
			if(journalEntry == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Journal entry cards in 'My Journal' is absent.");
			}
			/*//check for Chronological Sequence
			String chronological = driver.findElement(By.cssSelector("span[class='chronological-sequence-text']")).getText();
			if(!chronological.equals("Chronological Sequence"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail(" Chronological sequence button in 'My Journal' is absent.");
			}
			//check for Topical Sequence
			String topical = driver.findElement(By.cssSelector("span[class='topical-sequence-text']")).getText();
			if(!topical.equals("Topical Sequence"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail(" Topical Sequence button in 'My Journal' is absent.");
			}*/
			//check for filters
			String filters = driver.findElement(By.id("ls-my-journal-filter-wrapper")).getText();
			if(!filters.contains("All Chapters"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Chapter Filters in 'My Journal' is absent.");
			}
			if(!filters.contains("All Sections"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Sections Filters in 'My Journal' is absent.");
			}
			//check for month/week navigation option
			String dates = driver.findElement(By.id("accordion")).getText();
			if(!dates.contains("Now"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Right side month/week navigation option in 'My Journal' is absent.");
			}
			if(!dates.contains("Week"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Right side month/week navigation option in 'My Journal' is absent.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase UIofMyJournal in class VerifyMyJournal",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void bookmarkingOfAll()
	{
		try
		{
			new ResourseCreate().resourseCreate(9, 0);
			new LoginUsingLTI().ltiLogin("9");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().bookmark("lesson");//bookmark the lesson
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean entry = new MyJournal().myJournalEntryOnBookmarking("lesson", 0);
			if(entry == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The entry is not added to My Journal on bookmarking a lesson.");
			}
			//unbookamrk lesson 
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().unbookmark("lesson");//bookmark the lesson
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isEmpty = new Bookmark().isMyJournalEmpty();
			if(isEmpty == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The entry is not deleted from My Journal on unbookmarking a lesson.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
            new Navigator().openFirstResourceFromResourceTab(0);
			new Bookmark().bookmark("resource");
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean entry1 = new MyJournal().myJournalEntryOnBookmarking("resource", 0);
			if(entry1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The entry is not added to My Journal on bookmarking a resource.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
            new Navigator().openFirstResourceFromResourceTab(0);
			new Bookmark().unbookmark("resource");
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isEmpty2 = new Bookmark().isMyJournalEmpty();
			if(isEmpty2 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The entry is not deleted from My Journal on unbookmarking a resource.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			Thread.sleep(2000);
			new Bookmark().bookmark("widget");//bookmark image widget
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean entry2 = new MyJournal().myJournalEntryOnBookmarking("widget", 0);
			if(entry2 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The entry is not added to My Journal on bookmarking a widget.");
			}
			//unbookamrk image 
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TOCShow().tocHide();
			new Bookmark().unbookmark("widget");//bookmark image widget
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isEmpty1 = new Bookmark().isMyJournalEmpty();
			if(isEmpty1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The entry is not deleted from My Journal on unbookmarking a image widget.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookmarkingOfAll in class VerifyMyJournal",e);
		}
	}
	@Test(priority = 3, enabled = true)
	public void postNoteAndVerify()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("11");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String note = new RandomString().randomstring(5);
			new Discussion().postNote(note);//post note
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean entry = new MyJournal().myJournalEntryOnBookmarking("note", 0);
			if(entry == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The entry is not added to My Journal on adding a note.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Discussion().deleteNote();//delete note
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isEmpty = new Bookmark().isMyJournalEmpty();
			if(isEmpty == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The entry is not deleted from My Journal after deleting a note.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase postNoteAndVerify in class VerifyMyJournal",e);
		}
	}
	@Test(priority = 4, enabled = true)
	public void postDiscussionAndVerify()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("13");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String discussionText = new RandomString().randomstring(5);
			new Discussion().postDiscussion(discussionText);//post discussion
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean entry = new MyJournal().myJournalEntryOnBookmarking("discussion", 0);
			if(entry == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The entry is not added to My Journal on adding a discussion.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Discussion().deleteDiscussion();//delete discussion
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isEmpty = new Bookmark().isMyJournalEmpty();
			if(isEmpty == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The entry is not deleted from My Journal after deleting a discussion.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase postDiscussionAndVerify in class VerifyMyJournal",e);
		}
	}
	@Test(priority = 5, enabled = true)
	public void highlightTextAndVerify()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("15");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TOCShow().tocHide();
			new HighlightText().highlightText("yellow");
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean entry = new MyJournal().myJournalEntryOnHighlightingText();
			if(entry == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The entry is not added to My Journal on adding a discussion.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook

			new TOCShow().tocHide();//hide toc
			Thread.sleep(3000);
			new HighlightText().removeHighlight();//remove the highlight
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isEmpty = new Bookmark().isMyJournalEmpty();
			if(isEmpty == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The entry is not deleted from My Journal after removing the highlight from text.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase highlightTextAndVerify in class VerifyMyJournal",e);
		}
	}
	@Test(priority = 6, enabled = true)
	public void addDiscussionAndBookmarkWidget()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("17");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TOCShow().tocHide();
			String discussionText = new RandomString().randomstring(5);
			new Discussion().postDiscussion(discussionText);
			Thread.sleep(2000);
			new Bookmark().bookmark("widget");
			Thread.sleep(3000);
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean entry = new MyJournal().myJournalEntryOnBookmarking("widget", 0);
			if(entry == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("add Discussion AndBookmark Widget then the entry for image widget is not present.");
			}
			boolean entry1 = new MyJournal().myJournalEntryOnBookmarking("discussion", 1);
			if(entry1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("add Discussion AndBookmark Widget then the entry for discussion is not present.");
			}
			new Navigator().NavigateTo("Course Stream");//go to My Journal
			new CourseStream().hideFromCourseStream(0);//hide the 1st post
			Thread.sleep(2000);
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isEmpty = new Bookmark().isPresentInMyJournal("discussion");
			if(isEmpty == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After hiding the post for discussion from Course Stream the entries is not removed from My Journal.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase addDiscussionAndBookmarkWidget in class VerifyMyJournal",e);
		}
	}
	
	@Test(priority = 7, enabled = true)
	public void addDiscussionAndBookmarkResource()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("17_1");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String discussionText = new RandomString().randomstring(5);
			new Discussion().postDiscussion(discussionText);
			new Navigator().openFirstResourceFromResourceTab(0);
			new Bookmark().bookmark("resource");
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean entry = new MyJournal().myJournalEntryOnBookmarking("resource", 0);
			if(entry == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Bookmarked resource entry is not present.");
			}
			boolean entry1 = new MyJournal().myJournalEntryOnBookmarking("discussion", 1);
			if(entry1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Entry for discussion is not present.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Navigator().openFirstResourceFromResourceTab(0);
			new AssignmentSocialElement().clickonlike(1);//like is at 1st index not 0th index
			new Navigator().NavigateTo("Course Stream");//go to My Journal
			new CourseStream().hideFromCourseStream(0);//hide the 1st post
			Thread.sleep(2000);
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isPresent = new Bookmark().isPresentInMyJournal("resource");
			if(isPresent == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After hiding the post for discussion from Course Stream the entries is not removed from My Journal.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase addDiscussionAndBookmarkResource in class VerifyMyJournal",e);
		}
	}
	
	@Test(priority = 8, enabled = true)
	public void addDiscussionAndBookmarkResourceAndHideAsInstructor()
	{
		try
		{
            new ResourseCreate().resourseCreate(19,0);
			new LoginUsingLTI().ltiLogin("19");//login as student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String discussionText = new RandomString().randomstring(5);
			new Discussion().postDiscussion(discussionText);
			new Navigator().navigateToResourceTab();
			new Navigator().openResourceFromResourceTabFromCMS(19);
			new Bookmark().bookmark("resource");
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean entry = new MyJournal().myJournalEntryOnBookmarking("resource", 0);
			if(entry == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Entry for bookmarked resource is not present.");
			}
			boolean entry1 = new MyJournal().myJournalEntryOnBookmarking("discussion", 1);
			if(entry1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("add Discussion And Bookmark resource then the entry for discussion is not present.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Navigator().navigateToResourceTab();
			new Navigator().openResourceFromResourceTabFromCMS(19);
			new AssignmentSocialElement().clickonlike(1);//like is at 1st index not 0th index
			new LoginUsingLTI().ltiLogin("19_1");//login as instructor
			new Navigator().NavigateTo("Course Stream");//go to My Journal
			new CourseStream().hideFromCourseStream(0);//hide the 1st post
			Thread.sleep(2000);
			
			new LoginUsingLTI().ltiLogin("19");//login as student
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isPresent = new Bookmark().isPresentInMyJournal("resource");
			if(isPresent == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After hiding the post for resource by Instructor from Course Stream by instructor the entry is not removed from My Journal from student view.");
			}

			new LoginUsingLTI().ltiLogin("19_1");//login as instructor
			new Navigator().NavigateTo("Course Stream");//go to My Journal
			new CourseStream().hideFromCourseStream(0);//hide the 1st post
			Thread.sleep(2000);
			
			new LoginUsingLTI().ltiLogin("19");//login as student
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isdiscussionPresent = new Bookmark().isPresentInMyJournal("resource");
			if(isdiscussionPresent == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After hiding the post for discussion by Instructor from Course Stream by instructor the entry is not removed from My Journal from student view.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase addDiscussionAndBookmarkResourceAndHideAsInstructor in class VerifyMyJournal",e);
		}
	}
	@Test(priority = 9, enabled = true)
	public void addDiscussionAndBookmarkWidgetAndHideAsInstructor()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("19_2");//login as student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			String discussionText = new RandomString().randomstring(5);
			new Discussion().postDiscussion(discussionText);
			new Bookmark().bookmark("widget");
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean entry = new MyJournal().myJournalEntryOnBookmarking("widget", 0);
			if(entry == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After adding a Discussion And Bookmarking a widget then the entry for widget is not present.");
			}
			boolean entry1 = new MyJournal().myJournalEntryOnBookmarking("discussion", 1);
			if(entry1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After adding a Discussion And Bookmarking a widget then the entry for discussion is not present.");
			}
			//list all cards
			List<WebElement> allCards = driver.findElements(By.className("journal-card-title"));
			if(!allCards.get(0).getText().contains("Bookmarked a media on") || !allCards.get(1).getText().contains("Posted a discussion on"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The journal entries are not arranged in the order in which they were bookmarked and/or created.");
			}
			new LoginUsingLTI().ltiLogin("19_3");//login as instructor
			new Navigator().NavigateTo("Course Stream");//go to My Journal
			new CourseStream().hideFromCourseStream(0);//hide the widget
			
			new LoginUsingLTI().ltiLogin("19_2");//login as student
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isPresent = new Bookmark().isPresentInMyJournal("widget");
			if(isPresent == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After hiding the post for widget from Course Stream by instructor the entries are not removed from My Journal from student view.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase addDiscussionAndBookmarkWidgetAndHideAsInstructor in class VerifyMyJournal",e);
		}
	}

}
