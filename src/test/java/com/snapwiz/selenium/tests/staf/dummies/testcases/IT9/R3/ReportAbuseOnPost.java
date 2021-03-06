package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Logout;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.RandomString;

public class ReportAbuseOnPost {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.ReportAbuseOnPost");
	/*
	 * 379-382
	 */
	@Test(priority=1,enabled=true)
	public void reportabuse()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("aa", "");//create student
            new UserCreate().CreateStudent("bb", "");//create student
            new UserCreate().CreateStudent("cc", "");//create student
            new UserCreate().CreateStudent("dd", "");//create student

            new DirectLogin().studentLogin("aa");
			new Navigator().NavigateTo("Course Stream");
			String random = new RandomString().randomstring(5);
			new PostMessage().postmessage(random);
			if(!new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post NOT posted successfully");
			new Logout().logout();
			Thread.sleep(2000);
			new DirectLogin().studentLogin("bb");
			new Navigator().NavigateTo("Course Stream");
			//Reporting post as abuse by user 380
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-post-report-abuse")));						
			new Logout().logout();
			new DirectLogin().studentLogin("cc");

			new Navigator().NavigateTo("Course Stream");
			//Reporting post as abuse by user 381
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-post-report-abuse")));	
			new Logout().logout();
			new DirectLogin().studentLogin("dd");

			new Navigator().NavigateTo("Course Stream");
			//Reporting post as abuse by user 382
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-post-report-abuse")));
			//Validating post when logged in as user 382
			if(!new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post NOT found after 3 users reported it as an abuse by user 382");	
			new Logout().logout();
			new DirectLogin().studentLogin("cc");

			new Navigator().NavigateTo("Course Stream");
			if(!new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post NOT found after 3 users reported it as an abuse by user 381");
			new Logout().logout();
			new DirectLogin().studentLogin("bb");
			new Navigator().NavigateTo("Course Stream");
			if(!new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post NOT found after 3 users reported it as an abuse by user 380");
			new Logout().logout();
			new DirectLogin().studentLogin("aa");
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
	public void UsersForAbusePostdeleted()
	{
		try
		{
			Driver.startDriver();
			String random = new RandomString().randomstring(5);
			new UserCreate().CreateStudent("a", "");//create student
            new UserCreate().CreateStudent("b", "");//create student
            new UserCreate().CreateStudent("c", "");//create student
            new UserCreate().CreateStudent("d", "");//create student
            new UserCreate().CreateStudent("e", "");//create student
            new UserCreate().CreateStudent("f", "");//create student
            new UserCreate().CreateStudent("g", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			new PostMessage().postmessage(random);	
			if(!new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post NOT posted successfully");
			new Logout().logout();
			new DirectLogin().studentLogin("b"); //Logging in with user 380	for reporting the post by user 379 as abuse
			new Navigator().NavigateTo("Course Stream");
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-post-report-abuse")));
			new Logout().logout();
			new DirectLogin().studentLogin("c"); //Logging in with user 381 for reporting the post by user 379 as abuse
			new Navigator().NavigateTo("Course Stream");
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-post-report-abuse")));
			new Logout().logout();
			new DirectLogin().studentLogin("d"); //Logging in with user 382 for reporting the post by user 379 as abuse
			new Navigator().NavigateTo("Course Stream");
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-post-report-abuse")));
			new Logout().logout();
			new DirectLogin().studentLogin("f"); //Logging in with user 383 for reporting the post by user 379 as abuse
			new Navigator().NavigateTo("Course Stream");
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-post-report-abuse")));
			
			//Validating if post is deleted for each of the users
			new Logout().logout();
			new DirectLogin().studentLogin("g");//Logging in with user 384 for checking if the post reported by 4 users as abusive is deleted or not
			new Navigator().NavigateTo("Course Stream");
			if(new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post found even after 4 users reported it as an abuse by user 384");
			
			new Logout().logout();
			new DirectLogin().studentLogin("f"); //Logging in with user 383 for checking if the post reported by 4 users as abusive is deleted or not
			new Navigator().NavigateTo("Course Stream");
			if(new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post found even after 4 users reported it as an abuse by user 383");
			
			new Logout().logout();
			new DirectLogin().studentLogin("d"); //Logging in with user 382 for checking if the post reported by 4 users as abusive is deleted or not
			new Navigator().NavigateTo("Course Stream");
			if(new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post found even after 4 users reported it as an abuse by user 382");
			
			new Logout().logout();
			new DirectLogin().studentLogin("c"); //Logging in with user 381 for checking if the post reported by 4 users as abusive is deleted or not
			new Navigator().NavigateTo("Course Stream");
			if(new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post found even after 4 users reported it as an abuse by user 381");
			
			new Logout().logout();
			new DirectLogin().studentLogin("b");//Logging in with user 380 for checking if the post reported by 4 users as abusive is deleted or not
			new Navigator().NavigateTo("Course Stream");
			if(new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post found even after 4 users reported it as an abuse by user 380");
			
			new Logout().logout();
			new DirectLogin().studentLogin("a"); //Logging in with user 379 for checking if the post reported by 4 users as abusive is deleted or not
			new Navigator().NavigateTo("Course Stream");
			if(new PostMessageValidate().postMessageValidate(random)) Assert.fail("Post found even after 4 users reported it as an abuse by user 379");
			
		}
		catch (Exception e)
		 {
			  logger.log(Level.SEVERE,"Exception  in testcase UsersforAbusePostdeleted in class ReportAbuseOnPost",e);			  
			  Assert.fail("Exception  in testcase Users4AbusePostdeleted in class ReportAbuseOnPost",e);
		  }		
	}
	@AfterMethod
	  public void tearDown() throws Exception 
	  {
	     Driver.driver.quit();
	  }	
}
