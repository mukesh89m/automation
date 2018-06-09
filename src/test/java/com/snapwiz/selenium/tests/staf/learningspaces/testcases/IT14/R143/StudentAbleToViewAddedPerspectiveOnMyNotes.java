package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R143;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Widget;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class StudentAbleToViewAddedPerspectiveOnMyNotes extends Driver
{
	@Test
	public void studentAbleToViewAddedPerspectiveOnMyNotes()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("174");
			//174
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().openLessonWithDiscussionWidgetForStudent();//open chapter 10.3
			Thread.sleep(2000);
			new Widget().perspectiveAdd();//add perspective with DW
			//175
			new Navigator().NavigateTo("My Notes");
			String headerofDW=new TextFetch().textfetchbyclass("journal-card-title");//fethc header text
			if(!headerofDW.contains("Bookmarked a discussion"))
				Assert.fail("header od Dw not contains 'Bookmarked a discussion'");
			//176
			String persectivecount=new TextFetch().textfetchbyclass("ls-right-stream-post-comment-count");//count of perscpective
			if(!persectivecount.contains("1"))
				Assert.fail("perspective count not shown on my notes page");
			//176,177
			new MouseHover().mouserhover("my-journal-activity-details");
			new Click().clickByclassname("my-journal-media-popout");//click on jump-out icon
			//181,182
			new Navigator().NavigateTo("My Notes");
			new MouseHover().mouserhover("my-journal-activity-details");//hover on DW
			new Click().clickBycssselector("span[class='my-journal-card-bookmark bookmarked-card']");//remove bookmark form DW
			int discussionwigdet1=driver.findElements(By.className("my-journal-activity-details")).size();
			if(discussionwigdet1!=0)
				Assert.fail("after remove the bookmark of DW on my notes page its not deleted form my notes page");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in  testcase StudentAbleToViewAddedPerspectiveOnMyNotes in test method studentAbleToViewAddedPerspectiveOnMyNotes",e);
		}
	}

}
