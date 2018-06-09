package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.CommentOnPost;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;

public class RecentCommentOnTopAndOrderdByTime {
	/*
	 * 62
	 */
	@Test
	public void recentCommentOnTopAndOrderdByTime()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			String texttopost1=new RandomString().randomstring(6);
			String texttopost2=new RandomString().randomstring(6);
			String comment1=new RandomString().randomstring(6);
			String comment2=new RandomString().randomstring(6);
			//First Post
			new PostMessage().postmessage(texttopost1);
			new PostMessageValidate().postMessageValidate(texttopost1);
			
			Thread.sleep(2000);
			//Second Post
			new PostMessage().postmessage(texttopost2);
			new PostMessageValidate().postMessageValidate(texttopost2);
			Thread.sleep(5000);
			Boolean commentdone = new CommentOnPost().commentOnPost(comment1, 0);
			if(commentdone == false)
				Assert.fail("Comment on first post failed");
			JavascriptExecutor jse = (JavascriptExecutor)Driver.driver;
			jse.executeScript("window.scrollBy(0,200)", "");
			commentdone = new CommentOnPost().commentOnPost(comment2, 1);
			if(commentdone == false)
				Assert.fail("Comment on second post failed");
			Driver.driver.navigate().refresh();
			Thread.sleep(3000);
			String text=Driver.driver.findElement(By.className("ls-stream-share__title")).getText(); //Fetching text of first post
			
			if(!text.trim().equals(texttopost1))
			{
				Assert.fail("The ordering of the post is not based on the age of the latest comment");
			}
		}
		catch (Exception e)
		 {
			  Assert.fail("Exception  in testcase recentCommentOnTopAndOrderdByTime in class RecentCommentOnTopAndOrderdByTime",e);
	 
		  }
	}
	@AfterMethod
	  public void tearDown() throws Exception {
	    Driver.driver.quit();
	  }

}
