package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R1431;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.CommentOnPost;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;

public class UserAbleToSeeVisualIndicatorForDirectCourseStreamEntries extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void userAbleToSeeVisualIndicatorForDirectCourseStreamEntries()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("2_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			String randomtext = new RandomString().randomstring(15);
			new PostMessage().postmessage(randomtext); //post a message
			
			new LoginUsingLTI().ltiLogin("2_2");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no .2
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In student side, visual indicator for posts which have been added/updated since my last visit is not displayed with course stream entries for post.");
			}
			driver.navigate().refresh();
			//TC row no .4
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In student side, visual indicator for posts which have been added/updated since my last visit is still displayed in course stream entries after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("2");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 5
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In instructor side, visual indicator for posts which have been added/updated since my last visit is not displayed with course stream entries for post.");
			}
			driver.navigate().refresh();
			//TC row no. 7
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In instructor side, visual indicator for posts which have been added/updated since my last visit is still displayed in course stream entries after we refresh the page.");
			}
			String randomtext1 = new RandomString().randomstring(15);
			new PostMessage().postmessage(randomtext1); //post a message
			
			new LoginUsingLTI().ltiLogin("2_2");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 8
			int size4 = driver.findElements(By.className("new-entry-icon")).size();
			if(size4 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In student side, visual indicator for instructor entered posts which have been added/updated since my last visit is not displayed with course stream entries for post.");
			}
			driver.navigate().refresh();
			//TC row no. 10
			int size5 = driver.findElements(By.className("new-entry-icon")).size();
			if(size5 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In student side, visual indicator for instructor entered posts which have been added/updated since my last visit is still displayed in course stream entries after we refresh the page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase userAbleToSeeVisualIndicatorForDirectCourseStreamEntries in class UserAbleToSeeVisualIndicatorForDirectCourseStreamEntries.",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void addingCommentToDirectCourseStreamEntries()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("11_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			String randomtext = new RandomString().randomstring(15);
			new PostMessage().postmessage(randomtext); //post a message
			String comment = new RandomString().randomstring(15);
			new CommentOnPost().commentOnPost(comment, 0);
			
			new LoginUsingLTI().ltiLogin("11_2");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 11
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In student side, visual indicator is not displayed with course stream entries for post which is posted with a comment.");
			}
			driver.navigate().refresh();
			//TC row no. 13
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In student side, visual indicator is still displayed in course stream entries after we refresh the page for post which is posted with a comment.");
			}

			new LoginUsingLTI().ltiLogin("11");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 14
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In instructor side, visual indicator is not displayed with course stream entries for post which is posted with a comment.");
			}
			driver.navigate().refresh();
			//TC row no. 16
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In instructor side, visual indicator is still displayed in course stream entries after we refresh the page for post which is posted with a comment.");
			}
			String comment1 = new RandomString().randomstring(15);
			new CommentOnPost().commentOnPost(comment1, 0);
			
			new LoginUsingLTI().ltiLogin("11_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 17
			int size4 = driver.findElements(By.className("new-entry-icon")).size();
			if(size4 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In student side, visual indicator for instructor entered posts which have been added/updated since my last visit is not displayed with course stream entries for post which is posted with a comment..");
			}
			driver.navigate().refresh();
			//TC row no. 19
			int size5 = driver.findElements(By.className("new-entry-icon")).size();
			if(size5 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In student side, visual indicator for instructor entered posts which have been added/updated since my last visit is still displayed in course stream entries after we refresh the page for post which is posted with a comment.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase addingCommentToDirectCourseStreamEntries in class UserAbleToSeeVisualIndicatorForDirectCourseStreamEntries.",e);
		}
	}
}
