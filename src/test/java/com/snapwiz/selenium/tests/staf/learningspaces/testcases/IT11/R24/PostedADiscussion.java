package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R24;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class PostedADiscussion extends Driver {
	
	@Test(priority = 1, enabled = true)
	public void postedADiscussion()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("87_1");  	//login as instructor
			new LoginUsingLTI().ltiLogin("87");  	//login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			driver.findElement(By.linkText("Post")).click();//click on Post Link
			Thread.sleep(2000);
			//TC row no. 87
			WebElement t=driver.findElement(By.id("iframe-user-text-input-div"));
			driver.switchTo().frame(t) ;
			String defaultText = driver.findElement(By.xpath("html/body/font")).getText();
			if(!defaultText.contains("Share your knowledge or seek answers..."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Text box dont have default: \"Share your knowledge or seek answers...\"");
			}
			driver.switchTo().defaultContent();
			driver.findElement(By.id("post-submit-button")).click();//click on Submit
			Thread.sleep(2000);
			//TC row no. 88
			int notice = driver.findElements(By.className("notification-message-wrapper")).size();
			if(notice != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Leave the textbox of post blank and click on Submit then error message is displayed.");
			}
			String randomtext = new RandomString().randomstring(2);
			new PostMessage().postmessage(randomtext);
			//TC row no. 89, 90
			String title = new TextFetch().textfetchbyclass("ls-stream-post__head");
			if(!title.contains("posted a discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Title \"Posted a Discussion\" for a post is not added in course stream");
			}
			new LoginUsingLTI().ltiLogin("87_1");  	//login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			//TC row no. 91
			String title1 = new TextFetch().textfetchbyclass("ls-stream-post__head");
			if(!title1.contains("posted a discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For another instructor in same class section the Title \"Posted a Discussion\" for a post is not added in course stream");
			}
			new LoginUsingLTI().ltiLogin("92");  	//login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			//TC row no. 92
			int size = driver.findElements(By.className("ls-stream-post__head")).size();
			if(size != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For another instructor in disfferent class section the Title \"Posted a Discussion\" for a post is added in course stream");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase postedADiscussion in class PostedADiscussion", e);
		}
	}

}
