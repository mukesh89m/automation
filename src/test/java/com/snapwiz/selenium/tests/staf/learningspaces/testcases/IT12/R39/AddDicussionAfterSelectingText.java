package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R39;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.HighlightText;

public class AddDicussionAfterSelectingText extends Driver{

	@Test(priority = 1, enabled = true)
	public void addDicussionAfterSelectingText()
	{
		try
		{
			String color = ReadTestData.readDataByTagName("", "color", "138");
			new LoginUsingLTI().ltiLogin("138");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String lessonName1 = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			String discussionText = new RandomString().randomstring(5);
			new Discussion().addDiscussionAfterHighlightingText(discussionText);
			new Navigator().NavigateTo("My Journal");//go to My Journal
			String discussionIcon = driver.findElement(By.cssSelector("span[class='posted-icon card-icons']")).getCssValue("height");
			if(!discussionIcon.equals("20px"))
			{
				Assert.fail("After posting discussion the Journal entry dont have the the specific discussion icon.");
			}
			boolean isPresent = new Bookmark().isPresentInMyJournal("discussion");
			if(isPresent == false)
			{
				Assert.fail("After posting a discussion by highlighting texts the Journal entry with name 'Posted a discussion' is not available.");
			}
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			boolean isPresent1 = new Bookmark().isPresentInCourseStream("discussion");
			if(isPresent1 == false)
			{
				Assert.fail("After posting a discussion by highlighting texts the entry with name 'Posted a discussion' is not available in Course Stream.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			//check for highlighted text
			String highlightedText = driver.findElement(By.className("journal-subject-description")).getText();
			if(highlightedText == null)
			{
				Assert.fail("After highlighting the text from a lesson it doesn't appear in My Journal.");
			}
			//validate the color match
			new HighlightText().validateHighlight(color);
            new Navigator().NavigateTo("Course Stream");//go to Course Stream
			//check for social elements
			String footer = driver.findElement(By.className("ls-stream-post__footer")).getText();
			if(!footer.contains("Like") )
			{
				Assert.fail("In course stream the posted discussion does not have 'Like' social elements.");
			}
			if(!footer.contains("Comments") )
			{
				Assert.fail("In course stream the posted discussion does not have 'Comments' social elements.");
			}
			String age = driver.findElement(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']")).getText();
			if(age == null)
			{
				Assert.fail("In course stream the posted discussion does not have age in social elements.");
			}
            new Click().clickBycssselector("span[class='ls-jumpout-icon-link ls-jumpout-icon']");//click on jump out icon
			String lessonName = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!lessonName.equals(lessonName1))
			{
				Assert.fail("On click of jumpout icon for a discussion posted by highlighting the text from My Journal, student doesn't navigate to corressponding lesson.");
			}
			String discussionOpen = driver.findElement(By.className("edit-content-body")).getText();
			if(!discussionOpen.equals(discussionText))
			{
				Assert.fail("On click of jumpout icon from My Journal, the corresponding discussion box is not expanded.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			new Bookmark().unbookmarkFromMyJournal();//unbookmark from my journal
			boolean isRemoved = new Bookmark().isRemovedFromMyJournal();
			if(isRemoved == false)
			{
				Assert.fail("For a discussion, Clicking on yellow star from My Journal page the entry is not removed.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase addDicussionAfterSelectingText in class AddDicussionAfterSelectingText",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void addNoteAfterSelectingText()
	{
		try
		{
			String color = ReadTestData.readDataByTagName("", "color", "155");
			new LoginUsingLTI().ltiLogin("155");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String lessonName1 = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			String noteText = new RandomString().randomstring(5);
			new Discussion().addNoteAfterHighlightingText(noteText, "yellow");
			new Navigator().NavigateTo("My Journal");//go to My Journal
			String noteIcon = driver.findElement(By.cssSelector("span[class='posted-icon card-icons']")).getCssValue("height");
			if(!noteIcon.equals("20px"))
			{
				Assert.fail("After posting note the Journal entry dont have the the specific note icon.");
			}
			boolean isPresent = new Bookmark().isPresentInMyJournal("note");
			if(isPresent == false)
			{
				Assert.fail("After posting a note by highlighting texts the Journal entry with name 'Posted a note' is not available.");
			}
			//check for highlighted text
			String highlightedText = driver.findElement(By.className("journal-subject-description")).getText();
			if(highlightedText == null)
			{
				Assert.fail("After adding note by highlighting the text from a lesson the highlighted text doesn't appear in My Journal.");
			}
			//validate the color match
			new HighlightText().validateHighlight(color);
			
			new Navigator().clickOnJumpOutIcon();//click on jump out icon
			String lessonName = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!lessonName.equals(lessonName1))
			{
				Assert.fail("On click of jumpout icon for a note posted by highlighting the text from My Journal, student doesn't navigate to corressponding lesson.");
			}
			driver.findElement(By.className("ls-user-posted-note-edit")).click();//click on edit
			Thread.sleep(2000);
			String noteOpen = driver.findElement(By.cssSelector("div[class='editnote-text edit-mode-note']")).getText();
			if(!noteOpen.equals(noteText))
			{
				Assert.fail("On click of jumpout icon from My Journal, the corresponding note box is not expanded.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
            new MyJournal().deleteNoteFromMyJournal("");//delete from My Journal
			boolean isRemoved = new Bookmark().isRemovedFromMyJournal();
			if(isRemoved == false)
			{
				Assert.fail("For a note, Clicking on delete icon from My Journal page the entry is not removed.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase addDicussionAfterSelectingText in class AddDicussionAfterSelectingText",e);
		}
	}

}
