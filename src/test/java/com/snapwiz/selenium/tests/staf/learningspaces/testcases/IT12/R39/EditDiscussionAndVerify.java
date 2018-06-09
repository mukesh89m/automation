package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R39;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Discussion;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;

public class EditDiscussionAndVerify extends Driver {

	@Test(priority=1, enabled = true)
	public void editDiscussionAndVerify()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("150");
			new Navigator().NavigateTo("eTextbook");//naviga    te to etextbook
			new TOCShow().tocHide();
			String discussionText = new RandomString().randomstring(5);
			new Discussion().addDiscussionAfterHighlightingText(discussionText);
			new Navigator().NavigateTo("My Journal");//go to My Journal
			new Navigator().clickOnJumpOutIcon();//click on jump out icon
			//click on Discussion star icon in lesson page to remove book mark
			driver.findElement(By.xpath("//a[@title='Remove Bookmark']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();//again bookmark
		    Thread.sleep(2000);
		    new Navigator().NavigateTo("My Journal");//go to My Journal
		    String journalText = driver.findElement(By.className("journal-card-title")).getText();
			if(!journalText.contains("Posted a discussion on"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After adding discussion we remove the bookmark and again bookmark the Journal doesnt say 'Posted a discussion'.");
			}
			new Navigator().clickOnJumpOutIcon();//click on jump out icon
			String editedText = new RandomString().randomstring(5);
			new Discussion().editDiscussion(editedText);
			new Navigator().NavigateTo("My Journal");//go to My Journal
			String editedDiscussion = driver.findElement(By.className("journal-card-bottom-description")).getText();
			if(!editedDiscussion.contains(editedText))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After editing the discussion the journal didn't get updated.");
			}
			String journalText1 = driver.findElement(By.className("journal-card-title")).getText();
			if(!journalText1.contains("Posted a discussion on"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After editing the discussion the journal entry doesnt say 'Posted a discussion'.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase editDiscussionAndVerify in class EditDiscussionAndVerify",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void editNoteAndVerify()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("165");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			Thread.sleep(10000);
			String noteText = new RandomString().randomstring(5);
			new Discussion().addNoteAfterHighlightingText(noteText, "yellow");
			new Navigator().NavigateTo("My Journal");//go to My Journal
			new Navigator().clickOnJumpOutIcon();//click on jump out icon
			Thread.sleep(5000);
			//click on Discussion star icon in lesson page to remove book mark
			driver.findElement(By.xpath("//a[@title='Remove Bookmark']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();//again bookmark
		    Thread.sleep(2000);
		    new Navigator().NavigateTo("My Journal");//go to My Journal
		    String journalText = driver.findElement(By.className("journal-card-title")).getText();
			if(!journalText.contains("Posted a note on"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After adding note we remove the bookmark and again bookmark the Journal doesnt say 'Posted a note'.");
			}
			new Navigator().clickOnJumpOutIcon();//click on jump out icon
            Thread.sleep(5000);
			String editedNote = new RandomString().randomstring(5);
			new Discussion().editNote(editedNote);
			new Navigator().NavigateTo("My Journal");//go to My Journal
			String editedNotetext = driver.findElement(By.className("journal-card-bottom-description")).getText();
			if(!editedNotetext.contains(editedNote))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After editing the not the journal didn't get updated.");
			}
			String journalText1 = driver.findElement(By.className("journal-card-title")).getText();
			if(!journalText1.contains("Posted a note on"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After editing the note the journal entry doesnt say 'Posted a note'.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase editNoteAndVerify in class EditDiscussionAndVerify",e);
		}
	}

	
}
