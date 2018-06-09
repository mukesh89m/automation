package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.CommentOnPost;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;

public class InsRecentCommentOnTopAndOrderdByTime extends Driver {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.InsRecentCommentOnTopAndOrderdByTime");
	/*
	 * 62
	 */
	@Test
	public void recentCommentOnTopAndOrderdByTime()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("62_R24");
			new Navigator().NavigateTo("Course Stream");
			String texttopost1=new RandomString().randomstring(6);
			String texttopost2=new RandomString().randomstring(6);
			String comment1=new RandomString().randomstring(6);
			String comment2=new RandomString().randomstring(6);
			//First Post
			new PostMessage().postmessage(texttopost1);
			new PostMessageValidate().postMessageValidateForInstructor(texttopost1);
			
			//Second Post
			new PostMessage().postmessage(texttopost2);
			new PostMessageValidate().postMessageValidateForInstructor(texttopost2);
			new CommentOnPost().commentOnPost(comment1, 0);
			new CommentOnPost().commentOnPost(comment2, 1);
			driver.navigate().refresh();
			new Navigator().NavigateTo("Course Stream");
			String text = driver.findElement(By.className("ls-link-span")).getText();
            System.out.println(text);
            if(!text.trim().equals(texttopost1))
			{
				logger.log(Level.INFO,"Testcase InsRecentCommentOnTopAndOrderdByTime Pass");
				Assert.fail("The ordering of the post is not based on the age of the latest comment");
			}
		
			
		}
		catch (Exception e)
		 {
			  Assert.fail("Exception  in testcase recentCommentOnTopAndOrderdByTime in class InsRecentCommentOnTopAndOrderdByTime",e);
	 
		  }
	}

}
