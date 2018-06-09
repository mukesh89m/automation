package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1511;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class EntryForBookmarkingWidgetUnderFavTab extends Driver
{
	@Test
	public void entryForBookmarkingWidgetUnderFavTab()
	{
		try {

			new LoginUsingLTI().ltiLogin("39");
			String  discussiontext=new RandomString().randomstring(3);
			new Navigator().NavigateTo("e-Textbook");
            new TOCShow().tocHide();
			new UIElement().waitAndFindElement(By.id("sec-intro"));
			//39-44
			new Click().clickBycssselector("a[class='ls-bookmark-widget ls-widget-unbookmarked']");//bookmark image widget
			new Navigator().navigateToTab("Fav");
			String bookmarkedWidgetTextTag=new TextFetch().textfetchbyclass("stream-content-posts-list");
			if(!bookmarkedWidgetTextTag.contains("bookmarked a media"))
				Assert.fail("widget not shown with  'You bookmarked a media' text under fav tab");
			//45
			//MouseHover.mouserhover("stream-content-posts-list");//mouse hover on bookmark widget
			//46
			new Click().clickbyxpath("//a[@class='ls-post-show-assign-this-block']");
			new Click().clickBycssselector("span[class='ls-right-tab-hover-sprite folder-cycle-bg']");//click on open button
            //driver.findElement(By.xpath("/*//*[@id='stream-content-posts-scrollbar']/div[2]/div/ul/li/div[4]/a/span")).click();//click on open button
			//47
			new Click().clickBycssselector("div[class='toggle-widget-size toggle-widget-size-collapse']");//click on collapse  for widget
			new Click().clickBycssselector("a[class='ls-bookmark-widget ls-widget-bookmarked']");//unbookmark image widget
			//48-51
			new Click().clickBycssselector("a[class='lesson-bookmark-sprite-img bookmark-lesson']");//bookmark a lesson
			String bookmarkedLessonTextTag=new TextFetch().textfetchbyclass("stream-content-posts-list");//fetch string under fav tab
			if(!bookmarkedLessonTextTag.contains("bookmarked a lesson"))
				Assert.fail("bookmark a lesson text not tag under fav tab");

			boolean topofLesson=new BooleanValue().booleanbyid("sec-intro");//validate the top portion of lesson
			if(topofLesson==false)
				Assert.fail("after click on open button lesson not scroll up to top");
			new Click().clickBycssselector("a[class='lesson-bookmark-sprite-img unbookmark-lesson']");//unbookmark the lesson
			new Discussion().addDiscussioninLessonText(discussiontext);
			Thread.sleep(2000);
			String DiscussionPostWithLessonText=new TextFetch().textfetchbylistclass("stream-content-posts-list",1);
			if(!DiscussionPostWithLessonText.contains("posted a discussion"))
				Assert.fail("posted discussion not shown with 'posted a discussion' text under fav tab");
			//59-61
			new Navigator().NavigateTo("My Notes");//navigate to my notes
			new Click().clickByclassname("my-journal-media-popout");//click on jump out icon
			Thread.sleep(3000);
			//62-64
			new Click().clickbylistcssselector("i[class='ls-right-section-sprites ls--right-star-icon']", 0);//unbookmark the discussion
			new Navigator().navigateToTab("Fav");//navigater to favrouite tab
			boolean addDiscussionUnderfavTab=new BooleanValue().booleanbyclass("ls-right-user-content");//verify discussion present under fab tab or not
			if(addDiscussionUnderfavTab==true)
				Assert.fail("after unbookmark the discussion add with text  the discussion not remove form  fab tab");
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			Thread.sleep(2000);
			new Click().clickBycssselector("span[class='ls-discussion-widget-bkmarking ls-discussion-widget-bkmark']");//bookmark the discuusion widget
			new Navigator().navigateToTab("Fav");//navigater to favrouite tab
			String DiscussionWidgetBookmark=new TextFetch().textfetchbyclass("stream-content-posts-list");//fetch text under fav tab
			if(!DiscussionWidgetBookmark.contains("bookmarked a discussion"))
				Assert.fail("discussion widget not shown after bookmark with text 'bookmarked a discussion'  under fav tab");

			new Click().clickBycssselector("span[class='ls-discussion-widget-bkmarking ls-discussion-widget-unbkmark']");//unbookmark discussion widget
            Thread.sleep(4000);
			boolean addDiscussionWidgetUnderfavTab = driver.findElement(By.className("ls-right-user-content")).isDisplayed();
			System.out.println("addDiscussionWidgetUnderfavTab:"+addDiscussionWidgetUnderfavTab);
			if(addDiscussionWidgetUnderfavTab==true)
				Assert.fail("after unbookmark the discussion widget add with text  the discussion not remove form  fab tab");
			

		}
		catch(Exception e)
		{
			Assert.fail("Exception in testclass StudentAbleToViewMyFavoutiteEleMentOneTextbook in testmethod studentAbleToViewMyFavoutiteEleMentOneTextbook",e);
		}
	}

}
