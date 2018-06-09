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

public class BookmarkingOfPostedNotesForInstructor extends Driver{
	
	@Test
	public void bookmarkingOfPostedNotesForInstructor()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("134");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String discussionText = new RandomString().randomstring(6);
			new Discussion().postNote(discussionText);//post a note
			//check note is bookmarked by default
			boolean isBookmarked = new Bookmark().isBookmarked("note");
			if(isBookmarked == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The note that has been posted is not bookmarked by default at instructor site.");
			}
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
				Assert.fail("After going to any other lesson and coming back to the previous lesson the * icon of note doesn't remain yellow at instructor site.");
			}
			new Bookmark().unbookmark("note");//unbookmark note
			boolean isBookmarked3 = new Bookmark().isBookmarked("note");
			if(isBookmarked3 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Student not able to un-bookmark a note");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookmarkingOfPostedNotesForInstructor in class BookmarkingOfPostedNotesForInstructor",e);
		}
	}

}
