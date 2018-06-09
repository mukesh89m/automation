package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R24;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class SharingPosts extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void sharingPostWithAInstructor()
	{
		try
		{
			String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "93_1");
			new LoginUsingLTI().ltiLogin("93_1");  	//login as instructor
			new LoginUsingLTI().ltiLogin("93_2");  	//login as instructor
			new LoginUsingLTI().ltiLogin("93");  	//login as instructor
			new Navigator().NavigateTo("Course Stream");
			String randomstring=new RandomString().randomstring(2);
			new PostMessage().postMessageAndShare(randomstring, shareWithInitialString, "studentnametag", "93_1","");
			String title = new TextFetch().textfetchbyclass("ls-stream-post__head");
			//TC row no. 93
			if(!title.contains("posted a discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Title \"Posted a Discussion\" for a post is not added in course stream when post is shared with a specific instructor.");
			}
			new LoginUsingLTI().ltiLogin("93_1");  	//login as instructor 
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			//TC row no. 94
			String title1 = new TextFetch().textfetchbyclass("ls-stream-post__head");
			if(!title1.contains("posted a discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Title \"Posted a Discussion\" for a post is not added in course stream for the instructor for which the post has been shared.");
			}
			new LoginUsingLTI().ltiLogin("93_2");  	//login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			//TC row no. 95
			int size = driver.findElements(By.className("ls-stream-post__head")).size();
			if(size != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The post is also displayed for other instructor to whom the post has not been shared.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase sharingPostWithAInstructor in class SharingPosts", e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void sharingPostWithAMentor()
	{
		try
		{
			String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "99_1");
			new LoginUsingLTI().ltiLogin("99_1");  	//login as mentor
			new LoginUsingLTI().ltiLogin("99_2");  	//login as mentor
			new LoginUsingLTI().ltiLogin("99");  	//login as mentor
			new Navigator().NavigateTo("Course Stream");
			String randomstring=new RandomString().randomstring(2);
			new PostMessage().postMessageAndShare(randomstring, shareWithInitialString, "studentnametag", "99_1","");
			String title = new TextFetch().textfetchbyclass("ls-stream-post__head");
			//TC row no. 99
			if(!title.contains("posted a discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Title \"Posted a Discussion\" for a post is not added in course stream when post is shared with a specific instructor.");
			}
			new LoginUsingLTI().ltiLogin("99_1");  	//login as mentor 
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			//TC row no. 100
			String title1 = new TextFetch().textfetchbyclass("ls-stream-post__head");
			if(!title1.contains("posted a discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Title \"Posted a Discussion\" for a post is not added in course stream for the mentor for which the post has been shared.");
			}
			new LoginUsingLTI().ltiLogin("99_2");  	//login as mentor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			//TC row no. 101
			int size = driver.findElements(By.className("ls-stream-post__head")).size();
			if(size != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The post is also displayed for other mentor to whom the post has not been shared.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase sharingPostWithAMentor in class SharingPosts", e);
		}
	}

}
