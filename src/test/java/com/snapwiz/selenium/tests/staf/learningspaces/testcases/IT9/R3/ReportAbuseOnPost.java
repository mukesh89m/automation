package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;

public class ReportAbuseOnPost extends Driver{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.ReportAbuseOnPost");
	/*
	 * 379-382
	 */
	@Test(priority=1,enabled=true)
	public void reportabuse()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("379");
			new Navigator().NavigateTo("Course Stream");
			String random = new RandomString().randomstring(5);
			new PostMessage().postmessage(random);
			if(!new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post NOT posted successfully");
			
			new LoginUsingLTI().ltiLogin("380");
			new Navigator().NavigateTo("Course Stream");
			//Reporting post as abuse by user 380
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-post-report-abuse")));
			
			new LoginUsingLTI().ltiLogin("381");
			new Navigator().NavigateTo("Course Stream");
			//Reporting post as abuse by user 381
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-post-report-abuse")));
			
			new LoginUsingLTI().ltiLogin("382");
			new Navigator().NavigateTo("Course Stream");
			//Reporting post as abuse by user 382
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-post-report-abuse")));
			//Validating post when logged in as user 382
			if(!new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post NOT found after 3 users reported it as an abuse by user 382");	
			
			new LoginUsingLTI().ltiLogin("381"); //Logging in as user 381
			new Navigator().NavigateTo("Course Stream");
			if(!new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post NOT found after 3 users reported it as an abuse by user 381");
			
			new LoginUsingLTI().ltiLogin("380"); //Logging in as user 380
			new Navigator().NavigateTo("Course Stream");
			if(!new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post NOT found after 3 users reported it as an abuse by user 380");
			
			new LoginUsingLTI().ltiLogin("379"); //Logging in as user 380
			new Navigator().NavigateTo("Course Stream");
			if(!new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post NOT found after 3 users reported it as an abuse by user 379");
		}
		catch (Exception e)
		 {
			  logger.log(Level.SEVERE,"Exception  in testcase reportabuse in class ReportAbuseOnPost",e);			  
			  Assert.fail("Exception  in testcase reportabuse in class ReportAbuseOnPost",e);
		  }
	}
	@Test(priority=2,enabled=true)
	public void Users4AbusePostdeleted()
	{
		try
		{

			String random = new RandomString().randomstring(5);
			new LoginUsingLTI().ltiLogin("379");
			new Navigator().NavigateTo("Course Stream");
			new PostMessage().postmessage(random);	
			if(!new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post NOT posted successfully");
			
			new LoginUsingLTI().ltiLogin("380"); //Logging in with user 380	for reporting the post by user 379 as abuse		
			new Navigator().NavigateTo("Course Stream");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-post-report-abuse")));
			
			new LoginUsingLTI().ltiLogin("381"); //Logging in with user 381 for reporting the post by user 379 as abuse
			new Navigator().NavigateTo("Course Stream");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-post-report-abuse")));
			
			new LoginUsingLTI().ltiLogin("382"); //Logging in with user 382 for reporting the post by user 379 as abuse
			new Navigator().NavigateTo("Course Stream");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-post-report-abuse")));
			
			new LoginUsingLTI().ltiLogin("383"); //Logging in with user 383 for reporting the post by user 379 as abuse
			new Navigator().NavigateTo("Course Stream");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-post-report-abuse")));
			
			//Validating if post is deleted for each of the users
			
			new LoginUsingLTI().ltiLogin("384"); //Logging in with user 384 for checking if the post reported by 4 users as abusive is deleted or not
			new Navigator().NavigateTo("Course Stream");
			if(new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post found even after 4 users reported it as an abuse by user 384");
			
			new LoginUsingLTI().ltiLogin("383"); //Logging in with user 383 for checking if the post reported by 4 users as abusive is deleted or not
			new Navigator().NavigateTo("Course Stream");
			if(new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post found even after 4 users reported it as an abuse by user 383");
			
			new LoginUsingLTI().ltiLogin("382"); //Logging in with user 382 for checking if the post reported by 4 users as abusive is deleted or not
			new Navigator().NavigateTo("Course Stream");
			if(new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post found even after 4 users reported it as an abuse by user 382");
			
			new LoginUsingLTI().ltiLogin("381"); //Logging in with user 381 for checking if the post reported by 4 users as abusive is deleted or not
			new Navigator().NavigateTo("Course Stream");
			if(new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post found even after 4 users reported it as an abuse by user 381");
			
			new LoginUsingLTI().ltiLogin("380"); //Logging in with user 380 for checking if the post reported by 4 users as abusive is deleted or not
			new Navigator().NavigateTo("Course Stream");
			if(new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post found even after 4 users reported it as an abuse by user 380");
			
			new LoginUsingLTI().ltiLogin("379"); //Logging in with user 379 for checking if the post reported by 4 users as abusive is deleted or not
			new Navigator().NavigateTo("Course Stream");
			if(new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post found even after 4 users reported it as an abuse by user 379");
			
		}
		catch (Exception e)
		 {
			  logger.log(Level.SEVERE,"Exception  in testcase Users4AbusePostdeleted in class ReportAbuseOnPost",e);			  
			  Assert.fail("Exception  in testcase Users4AbusePostdeleted in class ReportAbuseOnPost",e);
		  }		
	}

}
