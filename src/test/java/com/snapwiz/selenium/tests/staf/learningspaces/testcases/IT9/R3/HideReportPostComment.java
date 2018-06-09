package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import org.testng.Assert;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;

import com.snapwiz.selenium.tests.staf.learningspaces.*;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;

/*
 * R3 1150
 */
public class HideReportPostComment extends Driver{
	
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.HideReportPostComment");
	
	@Test(priority=1,enabled=true)
	public void hidePostLinkVerify()
	{
		try
		{
			logger.log(Level.INFO,"Test Case hideReportPostComment under class HideReportPostComment Execution Started");

			new LoginUsingLTI().ltiLogin("375");
			new Navigator().NavigateTo("Course Stream");
			//Posting a comment
			String randomstring = new RandomString().randomstring(5);
			new PostMessage().postmessage(randomstring);

			//Mouse overing the arrow button under which Hide Post link is give for the post
			driver.findElement(By.className("ls-dropdown__toggle")).click();
			WebElement element = driver.findElement(By.className("ls-hide-post"));
			 System.out.println(element.getText());
			 // String hidepostlinktext = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",element);
			 if(!element.getText().equals("Remove Post"))
				 Assert.fail("Remove Post link not found when logged in as the owner of post");
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in Test Case hidePostLinkVerify under class HideReportPostComment");
		}
	}

	
	@Test(priority=2,enabled=true)
	public void abusePostLinkVerify()
	{
		try
		{
			logger.log(Level.INFO,"Test Case abusePostLinkVerify under class HideReportPostComment Execution Started");

			 new LoginUsingLTI().ltiLogin("376");
			 new Navigator().NavigateTo("Course Stream");
				//Mouse overing the arrow button under which Hide Post link is give for the post
					WebElement we2 = driver.findElement(By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']"));
					Locatable hoverItem = (Locatable) we2;
					Mouse mouse = ((HasInputDevices) driver).getMouse();
					mouse.mouseMove(hoverItem.getCoordinates());
					WebElement element2 = driver.findElement(By.className("ls-post-report-abuse"));
					 System.out.println(element2.getText());
					  String reportabuselinktext = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",element2);
					 if(!reportabuselinktext.contains("Report Abuse"))
						 Assert.fail("Report Abuse link not found when logged in as another user who is not the owner of the post");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in Test Case abusePostLinkVerify under class HideReportPostComment");
		}
	}
	@Test(priority=3,enabled=true)
	public void hideAPost()
	{
		try
		{
			logger.log(Level.INFO,"Test Case hideAPost under class HideReportPostComment Execution Started");

			new LoginUsingLTI().ltiLogin("375");
			new Navigator().NavigateTo("Course Stream");
			//Posting a comment
			String randomstring = new RandomString().randomstring(5);
			new PostMessage().postmessage(randomstring);
			Thread.sleep(5000);
			//Clicking on Hide Post Link
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-hide-post")));
			Thread.sleep(2000);
			Boolean postpresent = new TextValidate().IsTextPresent(randomstring);
			if(postpresent)
				Assert.fail("The Post hidden by the user is visible");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in Test Case hideAPost under class HideReportPostComment");
		}
		
	}
	@Test(priority=4,enabled=true)
	public void hideComment()
	{
		try
		{
			logger.log(Level.INFO,"Test Case hideComment under class HideReportPostComment Execution Started");

			new LoginUsingLTI().ltiLogin("375");
			new Navigator().NavigateTo("Course Stream");
			//Posting a Post
			String randomstring = new RandomString().randomstring(5);
			new PostMessage().postmessage(randomstring);
			//Posting a Comment on the Post
			new CommentOnPost().commentOnPost(randomstring, 1);	
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-hide-comment")));
			WebElement we = driver.findElement(By.cssSelector("li[class='ls-stream-post-comment ls-media']"));
			System.out.println(we.getText());
			 String hidecommentnotification = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",we);
			 if(!hidecommentnotification.equals("This post has been removed."))
				 Assert.fail();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in Test Case hideComment under class HideReportPostComment");
		}
	}
	
	@Test(priority=5,enabled=true)
	public void reportAbuseComment()
	{
		try
		{
			logger.log(Level.INFO,"Test Case hideComment under class HideReportPostComment Execution Started");

			new LoginUsingLTI().ltiLogin("375");
			new Navigator().NavigateTo("Course Stream");	
			//Posting a Post
			String randomstring = new RandomString().randomstring(5);
			new PostMessage().postmessage(randomstring);
			//Posting a Comment on the Post
			new CommentOnPost().commentOnPost(randomstring, 0);
			
			new LoginUsingLTI().ltiLogin("376");
			new Navigator().NavigateTo("Course Stream");
            driver.findElement(By.partialLinkText("Comments")).click();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-comment-report-abuse")));
			
			WebElement we2 = driver.findElement(By.cssSelector("li[class='ls-stream-post-comment ls-media']"));
			Locatable hoverItem = (Locatable) we2;
			Mouse mouse = ((HasInputDevices) driver).getMouse();
			mouse.mouseMove(hoverItem.getCoordinates());
			WebElement element2 = driver.findElement(By.className("ls-comment-report-abuse"));
			 System.out.println(element2.getText());
			  String reportabuselinktext = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",element2);
			 if(!reportabuselinktext.contains("Abuse Reported"))
				 Assert.fail("Abuse Reported text not found after reporting a comment as abuse");
			
			
		}
		catch(Exception e)
		{		
			Assert.fail("Exception in TestCase reportAbuseComment in class HideReportPostComment",e);
		}
		
	}
	

}
