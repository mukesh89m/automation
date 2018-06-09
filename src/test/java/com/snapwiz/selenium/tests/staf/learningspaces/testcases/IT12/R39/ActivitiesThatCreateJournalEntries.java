package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R39;


import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.HighlightText;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Bookmark;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.MyJournal;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Discussion;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentSocialElement;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ResourseCreate;

public class ActivitiesThatCreateJournalEntries extends Driver {
	
	@Test(priority = 1, enabled = true)
	public void cardUIForChronologicalView()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("76");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().bookmark("lesson");//bookmark the lesson
			new Navigator().NavigateTo("My Journal");//go to My Journal
            String lessonName1 = driver.findElement(By.cssSelector("div[class='journal-subject-title ellipsis']")).getText();
			boolean isPresent = new MyJournal().myJournalEntryOnBookmarking("lesson", 0);
			if(isPresent == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The header name is not in proper format in chronological view.");
			}
			//check date of creation
			String submissionDate = driver.findElement(By.className("journal-card-submission-time")).getText();
			if(submissionDate == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Date of creation of the journal entry is not displayed as part of header.");
			}
			
			String lessonName = driver.findElement(By.cssSelector("div[class='journal-subject-title ellipsis']")).getText();
			if(!lessonName.equals(lessonName1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Name of the lesson is not displayed below header when the journal entry is associated to a lesson");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new HighlightText().highlightText("yellow");//highlight text
			new Navigator().NavigateTo("My Journal");//go to My Journal
			//check for highlighted text
			String highlightedText = driver.findElement(By.className("journal-subject-description")).getText();
			if(highlightedText == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After highlighting the text from a lesson it doesn't appear in My Journal.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String note = new RandomString().randomstring(5);
			new Discussion().postNote(note);//post note
			new Navigator().NavigateTo("My Journal");//go to My Journal
			String noteContext = driver.findElement(By.className("journal-card-bottom-description")).getText();
			if(!noteContext.equals(note))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Actual Notes is not displayed below context");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String discussion = new RandomString().randomstring(5);
			new Discussion().postDiscussion(discussion);//post discussion
			new Navigator().NavigateTo("My Journal");//go to My Journal
			String discussionContext = driver.findElement(By.className("journal-card-bottom-description")).getText();
			if(!discussionContext.equals(discussion))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Actual discussion is not displayed below context");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase cardUIForChronologicalView in class ActivitiesThatCreateJournalEntries",e);
		}
	}

	@Test(priority = 2, enabled = true)
	public void verifyJumpOutIconForLesson()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("88");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String lessonName1 = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			new Bookmark().bookmark("lesson");//bookmark the lesson
			new Navigator().NavigateTo("My Journal");//go to My Journal
			String bookmarkIcon = driver.findElement(By.cssSelector("span[class='posted-icon card-icons']")).getCssValue("width");
			if(!bookmarkIcon.equals("17px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarkin the lesson Journal entry dont have the the specific bookmarked icon.");
			}
			
			new Navigator().clickOnJumpOutIcon();//click on jump out icon
			String lessonName = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!lessonName.equals(lessonName1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On click of jumpout,student doesn't navigate to corressponding lesson.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			new Bookmark().unbookmarkFromMyJournal();//unbookmark from my journal
			boolean isRemoved = new Bookmark().isRemovedFromMyJournal();
			if(isRemoved == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Clicking on yellow star from My Journal page the entry is not removed.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyJumpOutIconForLesson in class ActivitiesThatCreateJournalEntries",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void verifyJumpOutIconForDiscussion()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("94");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String discussion = new RandomString().randomstring(5);
			new Discussion().postDiscussion(discussion);//post discussion
			new LoginUsingLTI().ltiLogin("94_1");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String lessonName1 = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			new Bookmark().bookmark("discussion");//bookmark the discussion
			new Navigator().NavigateTo("My Journal");//go to My Journal
			String discussionOnMyJournal = driver.findElement(By.cssSelector("div[class='journal-card-bottom-description']")).getText();
			if(!discussionOnMyJournal.equals(discussion))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the discussion Journal entry don't have the specific discussion text.");
			}
			String bookmarkIcon = driver.findElement(By.cssSelector("span[class='posted-icon card-icons']")).getCssValue("width");
			if(!bookmarkIcon.equals("17px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the discussion Journal entry dont have the specific bookmarked icon.");
			}
			String journalText = driver.findElement(By.className("journal-card-title")).getText();
			if(!journalText.contains("Bookmarked a discussion on"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a discussion the Journal entry with name 'Bookmarked a discussion' is not available.");
			}
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			String color = new Bookmark().colorOfStarInCourseStream();
			if(!color.equals("yellow") )
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a discussion the Discussion entry at course stream do not appear bookmarked.");
			}
			new Navigator().NavigateTo("My Journal");
			String commentlink = driver.findElement(By.id("ls-right-post-comment-link")).getText();
            String likelink = driver.findElement(By.className("right-post-like")).getText();
            System.out.println(commentlink);
			if(!likelink.contains("(0)") )
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In course stream the bookmarked discussion does not have 'Like' social elements.");
			}
			if(!commentlink.contains("(0) Comments") )
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In course stream the bookmarked discussion does not have 'Comments' social elements.");
			}
			String age = driver.findElement(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']")).getText();
			if(age == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In course stream the bookmarked discussion does not have age in social elements.");
			}
			new Navigator().clickOnJumpOutIcon();//click on jump out icon
			String lessonName = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!lessonName.equals(lessonName1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On click of jumpout icon from My Journal, student doesn't navigate to corressponding lesson.");
			}
            Thread.sleep(5000);
            if(driver.findElements(By.className("swhelp-button-cancel")).size() > 0)
                driver.findElement(By.className("swhelp-button-cancel")).click();
            Thread.sleep(6000);
			String discussionOpen = driver.findElement(By.className("edit-content-body")).getText();
			if(!discussionOpen.equals(discussion))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On click of jumpout icon from My Journal, the corresponding discussion box is not expanded.");
			}
			new Navigator().NavigateTo("My Journal");
			new Bookmark().unbookmarkFromMyJournal();//unbookmark from my journal
			boolean isRemoved = new Bookmark().isRemovedFromMyJournal();
			if(isRemoved == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For a discussion, Clicking on yellow star from My Journal page the entry is not removed.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyJumpOutIconForDiscussion in class ActivitiesThatCreateJournalEntries",e);
		}
	}
	@Test(priority = 4, enabled = true)
	public void verifyJumpOutIconForWidget()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("106");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().bookmark("widget");//bookmark the discussion
			new Navigator().NavigateTo("My Journal");//go to My Journal
			String bookmarkIcon = driver.findElement(By.cssSelector("span[class='posted-icon card-icons']")).getCssValue("width");
			if(!bookmarkIcon.equals("17px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the widget Journal entry dont have the the specific bookmarked icon.");
			}
			String journalText = driver.findElement(By.className("journal-card-title")).getText();
			if(!journalText.contains("Bookmarked a media on"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a media the Journal entry with name 'Bookmarked a media' is not available.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String lessonName1 = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			new AssignmentSocialElement().clickonlike(0);//hit like in image widget
			String descOfImg = driver.findElement(By.cssSelector("div[class='widget-description image-widget-description']")).getText();
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			String action = driver.findElement(By.cssSelector("span[class='ls-stream-post__action']")).getText();
			if(!action.contains("liked this"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("entry is not added to course stream after liking the widget.");
			}
            String age = driver.findElement(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']")).getText();
            if(age == null)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In course stream the bookmarked discussion does not have age in social elements.");
            }
            String likecound_cs = driver.findElement(By.className("ls-post-like-count")).getText();
            if(!likecound_cs.contains("1") )
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In Course Stream the bookmarked discussion does not have 'Like' social elements.");
            }
            driver.findElement(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']")).click();//clicking on comment link of image widget in Course Stream page
            new Navigator().NavigateTo("My Journal");//go to My Journal
			String likecound = driver.findElement(By.className("ls-right-post-like-count")).getText();
			if(!likecound.contains("1") )
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In My Notes the bookmarked discussion does not have 'Like' social elements.");
			}
			driver.findElement(By.cssSelector("i[class='ls-icon-img ls--comments-icon']")).click(); //clicking on comment link of image widget in my notes page
			String descInMyJournal = driver.findElement(By.cssSelector("div[class='widget-description image-widget-description']")).getText();
			if(!descInMyJournal.equals(descOfImg))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The media asset's name and description available in the journal entry is not as same as displayed over lesson.");
			}
			
			new Navigator().clickOnJumpOutIcon();//click on jump out icon
			String lessonName = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!lessonName.equals(lessonName1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On click of jumpout icon from My Journal, student doesn't navigate to corressponding lesson.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			new Bookmark().unbookmarkFromMyJournal();//unbookmark from my journal
			boolean isRemoved = new Bookmark().isRemovedFromMyJournal();
			if(isRemoved == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For a discussion, Clicking on yellow star from My Journal page the entry is not removed.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyJumpOutIconForWidget in class ActivitiesThatCreateJournalEntries",e);
		}
	}
	@Test(priority = 5, enabled = true)
	public void verifyMyJournalForResource()
	{
		try
		{
            new ResourseCreate().resourseCreate(115, 0);
			new LoginUsingLTI().ltiLogin("115");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Navigator().openFirstResourceFromResourceTab(0);
			Thread.sleep(2000);
			new AssignmentSocialElement().clickonlike(1);//like the resource
			String resourceDesc = driver.findElement(By.className("resource-description")).getText();
			new Bookmark().bookmark("resource");//bookmark the resource
			new Navigator().NavigateTo("My Journal");//go to My Journal
			String bookmarkIcon = driver.findElement(By.cssSelector("span[class='posted-icon card-icons']")).getCssValue("width");
			if(!bookmarkIcon.equals("14px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the widget Journal entry dont have the the specific bookmarked icon.");
			}
			String journalText = driver.findElement(By.className("journal-card-title")).getText();
			if(!journalText.contains("Bookmarked a resource on"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a media the Journal entry with name 'Bookmarked a resource' is not available.");
			}
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			String action = driver.findElement(By.cssSelector("span[class='ls-post-like-link']")).getText();
			if(!action.contains("Unlike"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("entry is not added to course stream after liking the resource.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
            String commentlink = driver.findElement(By.id("ls-right-post-comment-link")).getText();
            String likelink = driver.findElement(By.className("right-post-like")).getText();
            System.out.println(commentlink); System.out.println(likelink);
            if(!likelink.contains("(1)") )
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In course stream the bookmarked discussion does not have 'Like' social elements.");
            }
            if(!commentlink.contains("(0) Comments") )
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In course stream the bookmarked discussion does not have 'Comments' social elements.");
            }

			String age = driver.findElement(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']")).getText();
			if(age == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In course stream the bookmarked discussion does not have age in social elements.");
			}
			String descInMyJournal = driver.findElement(By.cssSelector("div[class='journal-resource-description']")).getText();
			if(!descInMyJournal.equals(resourceDesc))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The resource's name and description available in the journal entry is not as same as displayed over eTextbook.");
			}
			new Bookmark().unbookmarkFromMyJournal();//unbookmark from my journal
			boolean isRemoved = new Bookmark().isRemovedFromMyJournal();
			if(isRemoved == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For a discussion, Clicking on yellow star from My Journal page the entry is not removed.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyMyJournalForResource in class ActivitiesThatCreateJournalEntries",e);
		}
	}
	@Test(priority = 6, enabled = true)
	public void verifyJumpOutIconForHighlightedText()
	{
		try
		{
			String color = ReadTestData.readDataByTagName("", "color", "130");
			new LoginUsingLTI().ltiLogin("130");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String lessonName1 = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			new HighlightText().highlightText("yellow");//highlight text
			new Navigator().NavigateTo("My Journal");//go to My Journal
			String journalText = driver.findElement(By.className("journal-card-title")).getText();
			if(!journalText.contains("Highlighted a text on"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After highlighting a text the Journal entry with name 'Highlighted a text on' is not available.");
			}
			//check for highlighted text
			String highlightedText = driver.findElement(By.className("journal-subject-description")).getText();
			if(highlightedText == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After highlighting the text from a lesson it doesn't appear in My Journal.");
			}
			
			//validate the color match
			new HighlightText().validateHighlight(color);
			new Navigator().clickOnJumpOutIcon();
			String lessonName = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!lessonName.equals(lessonName1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On click of jumpout icon for Highlighted text from My Journal, student doesn't navigate to corressponding lesson.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			new Bookmark().unbookmarkFromMyJournal();//unbookmark from my journal
			boolean isRemoved = new Bookmark().isRemovedFromMyJournal();
			if(isRemoved == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For a discussion, Clicking on yellow star from My Journal page the entry is not removed.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyJumpOutIconForHighlightedText in class ActivitiesThatCreateJournalEntries",e);
		}
	}
	
	@Test(priority = 7, enabled = true)
	public void expandCollapseIconForImage()
	{
        try {
            new LoginUsingLTI().ltiLogin("110");
            new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Bookmark().bookmark("widget");//bookmark the image widget
            new Navigator().NavigateTo("My Journal");//go to My Journal
            String expandImage = driver.findElement(By.cssSelector("div[class='toggle-widget-size toggle-widget-size-expand']")).getCssValue("background-image");
            if (!expandImage.contains("expand.png")) {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In my journal on mouse hovering over a Image widget the expand symbol is not shown.");
            }
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div.toggle-widget-size.toggle-widget-size-expand")));//click to expand
            Thread.sleep(2000);
            String collapseImage = driver.findElement(By.cssSelector("div[class='toggle-widget-size toggle-widget-size-collapse']")).getCssValue("background-image");
            if (!collapseImage.contains("collapse.png")) {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In my journal if we expand a image then the collapse symbol is absent in expanded image.");
            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div.toggle-widget-size.toggle-widget-size-collapse"))); //click to collapse
        } catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase expandCollapseIconForImage in class ActivitiesThatCreateJournalEntries",e);
		}
}

}
