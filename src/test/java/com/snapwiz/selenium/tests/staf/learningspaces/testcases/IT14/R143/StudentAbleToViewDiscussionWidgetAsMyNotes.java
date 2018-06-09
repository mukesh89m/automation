package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R143;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class StudentAbleToViewDiscussionWidgetAsMyNotes extends Driver
{
	@Test
	public void studentAbleToViewDiscussionWidgetAsMyNotes()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("156");//login as student
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().openLessonWithDiscussionWidgetForStudent();//open chapter 10.3
			//156
			new Click().clickbylistcssselector("span[class='ls-discussion-widget-bkmarking ls-discussion-widget-bkmark']", 0);//bookmark discussion widget
			new Click().clickbylist("ls-discussion-like-link", 0);//like disscussion widget
			//157
			new Navigator().NavigateTo("Course Stream");//navigate to course stream page
			int widgetpresenceoncoursestream=driver.findElements(By.className("ls-stream-widget-chapter")).size();//verify disscussion widget on course stream page
			if(widgetpresenceoncoursestream!=1)
				Assert.fail("discussion widget  not display on course stream page");
			new Navigator().NavigateTo("My Notes");
			boolean discussionwidgetpresenceonmynotes=new BooleanValue().booleanbyclass("journal-subject-dw-description");
			if(discussionwidgetpresenceonmynotes==false)
				Assert.fail("bookmarked discussion widget not display on my notes page");
			//158
			String likecount=new TextFetch().textfetchbyclass("ls-right-post-like-count");//like count of discussion widget on my notes page
			if(!likecount.equals("1"))
				Assert.fail("like count not display on my notes page for discussison widget");
			//160,161
			new MouseHover().mouserhover("my-journal-activity-details");
			new Click().clickByclassname("my-journal-media-popout");//click on jump-out icon
			boolean textbookpage=new BooleanValue().booleanbyclass("favourite-lesson");
			if(textbookpage==false)
				Assert.fail("after click on jump out icon in my notes page its not redirect to etextbook page");
			new Navigator().NavigateTo("My Notes");
			//162,172,173
			new MouseHover().mouserhover("my-journal-activity-details");
			new Click().clickBycssselector("span[title='Remove Bookmark']");//romove bookmark form disscussion widget
			int discussionwigdet=driver.findElements(By.className("my-journal-activity-details")).size();
			if(discussionwigdet!=0)
				Assert.fail("after deselect the bookmark of DW its not deleted form my notes page");
			new Navigator().NavigateTo("Course Stream");
			//169-171
			new Click().clickByclassname("js-favourite");//bookmark discussion widget on course stream page
			new Navigator().NavigateTo("My Notes");
			int discussionwigdet1=driver.findElements(By.className("my-journal-activity-details")).size();
			if(discussionwigdet1==0)
				Assert.fail("after  bookmarked of DW in course stream page its not added to my notes page");
			new MouseHover().mouserhover("my-journal-activity-details");
			new Click().clickBycssselector("span[title='Remove Bookmark']");
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().openLessonWithDiscussionWidgetForStudent();//open chapter 10.3
			//164
			new Click().clickbylistcssselector("span[class='ls-discussion-widget-bkmarking ls-discussion-widget-bkmark']", 0);//bookmark discussion widget on etextbook
			new Navigator().NavigateTo("My Notes");
			int discussionwigdet2=driver.findElements(By.className("my-journal-activity-details")).size();
			if(discussionwigdet2==0)
				Assert.fail("after  bookmarked of DW in etextbook page its not added to my notes page");
			new Navigator().NavigateTo("Course Stream");
			new Click().clickBycssselector("i[class='ls-icon-img ls--star-icon']");//remove bookmark form discussion widget on course stream page
			new Navigator().NavigateTo("My Notes");
			int discussionwigdet3=driver.findElements(By.className("my-journal-activity-details")).size();
			if(discussionwigdet3!=0)
				Assert.fail("after deselect the bookmark of DW on course stream page its not deleted form my notes page");			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase class StudentAbleToViewDiscussionWidgetAsMyNotes in testcase method studentAbleToViewDiscussionWidgetAsMyNotes",e);
		}
	}


}
