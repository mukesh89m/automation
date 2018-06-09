package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.CommentOnPost;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Logout;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;


/*
 * R3 1150
 */
public class HideReportPostComment {
	
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.HideReportPostComment");
	
	@Test(priority = 1, enabled = true)
	public void hidePostLinkVerify()
	{
		try
		{
			logger.log(Level.INFO,"Test Case hideReportPostComment under class HideReportPostComment Execution Started");
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			//Posting a comment
			String randomstring = new RandomString().randomstring(5);
			new PostMessage().postmessage(randomstring);
			Driver.driver.findElement(By.className("ls-dropdown__toggle")).click();
			Thread.sleep(3000);
			WebElement element = Driver.driver.findElement(By.className("ls-hide-post"));
			System.out.println(element.getText());
			String hidepostlinktext = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",element);
			if(!hidepostlinktext.contains("Remove Post"))
				 Assert.fail("Remove Post link not found when logged in as the owner of post");		
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in Test Case hidePostLinkVerify under class HideReportPostComment");
		}
	}

	
	@Test(priority = 2, enabled = true)
	public void abusePostLinkVerify()
	{
		try
		{
			logger.log(Level.INFO,"Test Case abusePostLinkVerify under class HideReportPostComment Execution Started");
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			Driver.driver.findElement(By.className("ls-dropdown__toggle")).click();
			Thread.sleep(3000);
			WebElement element2 = Driver.driver.findElement(By.className("ls-post-report-abuse"));
			System.out.println(element2.getText());
			String reportabuselinktext = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",element2);
			if(!reportabuselinktext.contains("Report Abuse"))
				Assert.fail("Report Abuse link not found when logged in as another user who is not the owner of the post");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in Test Case abusePostLinkVerify under class HideReportPostComment");
		}
	}
	@Test(priority = 3, enabled = true)
	public void hideAPost()
	{
		try
		{
			logger.log(Level.INFO,"Test Case hideAPost under class HideReportPostComment Execution Started");
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			//Posting a comment
			String randomstring = new RandomString().randomstring(5);
			new PostMessage().postmessage(randomstring);
			Thread.sleep(5000);
			//Clicking on Hide Post Link
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-hide-post")));
			Thread.sleep(2000);
			String text = Driver.driver.findElement(By.className("ls-link-span")).getText();
			System.out.println("postpresent: "+text);
			if(text.contains(randomstring))
				Assert.fail("The Post hidden by the user is visible");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in Test Case hideAPost under class HideReportPostComment");
		}
		
	}
	@Test(priority = 4,enabled = true)
	public void hideComment()
	{
		try
		{
			logger.log(Level.INFO,"Test Case hideComment under class HideReportPostComment Execution Started");
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			//Posting a Post
			String randomstring = new RandomString().randomstring(5);
			new PostMessage().postmessage(randomstring);
			//Posting a Comment on the Post
			new CommentOnPost().commentOnPost(new RandomString().randomstring(5), 0);
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-hide-comment")));
			WebElement we = Driver.driver.findElement(By.cssSelector("li[class='ls-stream-post-comment ls-media']"));
			System.out.println(we.getText());
			String hidecommentnotification = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",we);
			if(!hidecommentnotification.equals("This post has been removed."))
				 Assert.fail("After hiding a notification the message \"This post has been removed.\" doesnt appear.");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in Test Case hideComment under class HideReportPostComment");
		}
	}
	
	@Test(priority = 5,enabled = true)
	public void reportAbuseComment()
	{
		try
		{
			logger.log(Level.INFO,"Test Case hideComment under class HideReportPostComment Execution Started");
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");	//Posting a Post
			String randomstring = new RandomString().randomstring(5);
			new PostMessage().postmessage(randomstring);
			new CommentOnPost().commentOnPost(randomstring, 0);//Posting a Comment on the Post
			new Logout().logout();
			
			new UserCreate().CreateStudent("b", "");//create student
			new DirectLogin().studentLogin("b");
			new Navigator().NavigateTo("Course Stream");
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-post-report-abuse"))); //Report Abuse
			
			WebElement element2 = Driver.driver.findElement(By.className("ls-post-report-abuse"));
			String reportabuselinktext = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",element2);
			if(!reportabuselinktext.contains("Abuse Reported")) //checking if report abuse is convert to abuse reported
				Assert.fail("Abuse Reported text not found after reporting a comment as abuse");
		}
		catch(Exception e)
		{		
			Assert.fail("Exception in TestCase reportAbuseComment in class HideReportPostComment",e);
		}
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
