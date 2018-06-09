package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class RecentCommentOnTopAndOrderdByTime extends Driver{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.RecentCommentOnTopAndOrderdByTime");
	/*
	 * 62
	 */
	@Test(priority = 1,enabled = true)
	public void recentCommentOnTopAndOrderdByTime()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("62");
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
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,200)", "");
			commentdone = new CommentOnPost().commentOnPost(comment2, 1);
			if(commentdone == false)
				Assert.fail("Comment on second post failed");
			driver.navigate().refresh();
			Thread.sleep(3000);
			String text=driver.findElement(By.className("ls-stream-share__title")).getText(); //Fetching text of first post
			
			if(!text.trim().equals(texttopost1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				logger.log(Level.INFO,"Testcase RecentCommentOnTopAndOrderdByTime Pass");
				Assert.fail("The ordering of the post is not based on the age of the latest comment");
			}
		
			
		}
		catch (Exception e)
		 {
			  new Screenshot().captureScreenshotFromTestCase();
			  logger.log(Level.SEVERE,"Exception  in testcase recentCommentOnTopAndOrderdByTime in class RecentCommentOnTopAndOrderdByTime");			 
			  Assert.fail("Exception  in testcase recentCommentOnTopAndOrderdByTime in class RecentCommentOnTopAndOrderdByTime",e);
	 
		  }


	}

    @Test(priority = 2,enabled = true)
    public void recentLinkOnTopOrderedByTime()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("62");
            new Navigator().NavigateTo("Course Stream");
            new PostMessage().postlink("http://www.yahoo.com");
            new PostMessage().postlink("http://www.gmail.com");
            boolean commentdone = new CommentOnPost().commentOnPost(new RandomString().randomstring(10), 0);
            if(commentdone == false)
                Assert.fail("Comment on first post failed");
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("window.scrollBy(0,200)", "");
            commentdone = new CommentOnPost().commentOnPost(new RandomString().randomstring(10), 1);
            if(commentdone == false)
                Assert.fail("Comment on second post failed");
            driver.navigate().refresh();
            Thread.sleep(3000);
            List<WebElement> allSharedElements = driver.findElements(By.className("ls-stream-share__title"));
            if(!(allSharedElements.get(0).getText().contains("http://www.yahoo.com")))
                Assert.fail("Link on which the comment is posted recently not appearing on top");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase recentLinkOnTopOrderedByTime in class RecentCommentOnTopAndOrderdByTime",e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void recentFileUploadedOnTopOrderedByTime()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("62");
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("62",true);
            new FileUpload().fileUpload("62_nextfile",true);
            boolean commentdone = new CommentOnPost().commentOnPost(new RandomString().randomstring(10), 0);
            if(commentdone == false)
                Assert.fail("Comment on first post failed");
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("window.scrollBy(0,200)", "");
            commentdone = new CommentOnPost().commentOnPost(new RandomString().randomstring(10), 1);
            if(commentdone == false)
                Assert.fail("Comment on second post failed");
            driver.navigate().refresh();
            Thread.sleep(3000);
            if(!(driver.findElement(By.cssSelector("div[class='ls-media__body media_file_link']")).getText()).equals("img.png"))
                Assert.fail("The ordering of the files posted is not based on the age of the latest comment");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase recentFileUploadedOnTopOrderedByTime in class RecentCommentOnTopAndOrderdByTime",e);
        }
    }



}
