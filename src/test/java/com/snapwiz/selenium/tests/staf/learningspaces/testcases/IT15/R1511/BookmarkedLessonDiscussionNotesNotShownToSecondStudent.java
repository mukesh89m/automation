package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1511;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Discussion;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class BookmarkedLessonDiscussionNotesNotShownToSecondStudent extends Driver
{
	@Test
	public void bookmarkedLessonDiscussionNotesNotShownToSecondStudent()
	{
		try
		{

			String randomtext=new RandomString().randomstring(3);
			new LoginUsingLTI().ltiLogin("2031");//login as 1st student
			new LoginUsingLTI().ltiLogin("203");//login as 2nd student
			new Navigator().NavigateTo("e-Textbook");			
			new TopicOpen().lessonOpen(0, 0);//open 1st lesson of 1st chapter
			new Navigator().navigateToTab("Discussion");//navigate to discussion tab
			new Discussion().postDiscussion(randomtext);//204-204 post discussion			
			new Navigator().navigateToTab("Fav");//navigater to favrouite tab
			new Discussion().postNote(randomtext);//--205----post notes at 1st lesson of 1st chapter
			new Click().clickBycssselector("a[class='lesson-bookmark-sprite-img bookmark-lesson']");//208----bookmark lesson
			new Click().clickBycssselector("a[class='ls-bookmark-widget ls-widget-unbookmarked']");//207----bookmark image widget
			//login as 1st student
			new LoginUsingLTI().ltiLogin("2031");//login as 1st student
			new Navigator().NavigateTo("e-Textbook");			
			new TopicOpen().lessonOpen(0, 0);//open 1st lesson of 1st chapter
			new Navigator().navigateToTab("Discussion");//navigate to discussion tab
			int discussionForsecondStudent=driver.findElements(By.className("ls-right-user-content")).size();
			if(discussionForsecondStudent==0)
				Assert.fail("discussion shown for 1st student which is posted by 2nd student");//verify discussion shown on lesson under discussion tab
			new Navigator().navigateToTab("Fav");//navigater to favrouite tab
			//verify none of the bookkmarked item shown by the 1st  student under star tab which is bookmerked by 2nd student
			boolean NotesForsecondStudent=new BooleanValue().booleanbyclass("ls-right-user-content");
			if(NotesForsecondStudent==true)
				Assert.fail("Notes shown for 1st student which is posted by 2nd student");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase BookmarkedLessonDiscussionNotesNotShownToSecondStudent in method bookmarkedLessonDiscussionNotesNotShownToSecondStudent",e);
		}
	}		

}
