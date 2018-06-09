package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R39;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentSocialElement;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Bookmark;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.CourseStream;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.MyJournal;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;

public class ReflectionOfSocialActivitiesOnCourseStream extends Driver{
	
	@Test
	public void reflectionOfSocialActivitiesOnCourseStream()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("70");//login as student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().bookmark("widget");//bookmark the widget
			new AssignmentSocialElement().clickonlike(0);
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean entry1 = new MyJournal().myJournalEntryOnBookmarking("widget", 0);
			if(entry1 == false)
			{
				Assert.fail("The entry is not added to My Journal on bookmarking a resource.");
			}
			new LoginUsingLTI().ltiLogin("70_1");//login as student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().bookmark("widget");//bookmark the widget
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			int likeCount = new AssignmentSocialElement().countoflikecoursestream(0);
            System.out.println("likeCount: "+likeCount);
            driver.findElement(By.cssSelector("span[class='ls-post-like-link']")).click();//like the image widget
			Thread.sleep(2000);
			new Navigator().NavigateTo("My Journal");//go to My Journal
			int likeCount1 = new MyJournal().countoflikeinMyJournal(0);
            System.out.println("likeCount1: "+likeCount1);
            if(likeCount1 != likeCount+1)
			{
				Assert.fail("Hit Like in course stream doesn't reflect the like count over corresponding journal entry.");
			}
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			driver.findElement(By.cssSelector("span[class='ls-post-like-link']")).click();//unlike the image widget
			Thread.sleep(2000);
			new Navigator().NavigateTo("My Journal");//go to My Journal
			String like = driver.findElement(By.className("ls-right-post-like-count")).getText();
			if(!like.contains("1"))
			{
				Assert.fail("After Unliking from course stream, link count doesnt decrease from 2 to 1 in My Journal.");
			}
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			int commentCount = new AssignmentSocialElement().countofcomment(0);
			new CourseStream().addCommentInCourseStream("This is a comment", 0);
			new Navigator().NavigateTo("My Journal");//go to My Journal
			int commentCount1 = new MyJournal().countofcommmentinMyJournal(0);
			if(commentCount1 != commentCount+1)
			{
				Assert.fail("Comment in course stream doesn't reflect the commnet count over corresponding journal entry.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			int likeCount2 = new MyJournal().countoflikeinMyJournal(0);
            System.out.println("likeCount2: "+likeCount2);

            new AssignmentSocialElement().clickonlike(0);//like the widget in lesson page
			new Navigator().NavigateTo("My Journal");//go to My Journal
			int likeCount3 = new MyJournal().countoflikeinMyJournal(0);
            System.out.println("likeCount3: "+likeCount3);

            if(likeCount3 != likeCount2+1)
			{
				Assert.fail("Like the widget in lesson page doesn't reflect the like count over corresponding journal entry.");
			}

			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new AssignmentSocialElement().clickonlike(0);//unlike the widget in lesson page
			int likeCount4 = new MyJournal().countoflikeinMyJournal(0);//count of like in lesson page (using same element for like as that of Mu Journal)
            System.out.println("likeCount4: "+likeCount4);

            if(likeCount4 != likeCount3-1)
			{
				Assert.fail("UnLike the widget in lesson page doesn't reflect the like count over corresponding journal entry.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String stringcommentCount2 = driver.findElement(By.cssSelector("span[class='ls-right-stream-post-comment-count']")).getText();
			int commentCount2=Integer.parseInt(stringcommentCount2);
			new AssignmentSocialElement().addComment("This is a comment");
			new Navigator().NavigateTo("My Journal");//go to My Journal
			int commentCount3 = new MyJournal().countofcommmentinMyJournal(0);
			if(commentCount3 != commentCount2+1)
			{
				Assert.fail("Comment in widget in lesson page it doesn't reflect the comment count over corresponding My journal entry.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase reflectionOfSocialActivitiesOnCourseStream in class ReflectionOfSocialActivitiesOnCourseStream",e);
		}
	}

}
