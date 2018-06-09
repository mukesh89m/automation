package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;

public class ShareALinkWithStudent {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.ShareALinkWithClassSection");
    /*
     * 236-238
     */	
	@Test(priority=1,enabled=true)
	public void PostALinkWithsharingAStudent()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new UserCreate().CreateStudent("b", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			if(!new PostMessage().postLinkAndShare("www.yahoo.com", "", "b", ""))	
				Assert.fail("Link not Shared with student");
			if(!new PostMessageValidate().validateLink("www.yahoo.com"))	
				Assert.fail("Link not posted");
			
			new DirectLogin().studentLogin("b");
			new Navigator().NavigateTo("Course Stream");
			boolean postedtoverify=new PostMessageValidate().validateLink("www.yahoo.com");
			if(postedtoverify==false)
				Assert.fail("Link shared by user 236 to user 237 not Present when logged in as user 237");
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception  in testcase PostALinkWithsharingAStudent in class ShareALinkWithStudent",e);
			Assert.fail("Exception  in testcase PostALinkWithsharingAStudent in class ShareALinkWithStudent",e);		      
		}
	}
	
	
	@Test(priority=2,enabled=true,dependsOnMethods={"PostALinkWithsharingAStudent"})
	public void VerifyTheLinkWithWhomLinkNotShare()
	{
		try
		{
			Driver.startDriver();		
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			boolean postedtoverify=new PostMessageValidate().validateLink("www.yahoo.com");
			if(postedtoverify==false)
				logger.log(Level.INFO,"linktext not posted on the student with whom link not share");	
		}
		catch(Exception e)
		{			 
			Assert.fail("Exception  in testcase VerifyTheLinkWithWhomLinkNotShare in class ShareALinkWithStudent",e);		      
		}		
	}
	@AfterMethod
	  public void tearDown() throws Exception 
	  {
	     Driver.driver.quit();    
	  }
	
}


