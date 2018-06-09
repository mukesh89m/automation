package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;
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

public class SharingAPostWithStudent {
	/*
	 * 212,213,124
	 */
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.SharingAPostWithStudent");
	
	@Test(priority=1,enabled=true)
	public void postAText()
	{
		//post a with share with student
		try
		{
			Driver.startDriver();		
			new UserCreate().CreateStudent("a", "");//create student
			new UserCreate().CreateStudent("b", "");//create student
			new DirectLogin().studentLogin("a");			
			new Navigator().NavigateTo("Course Stream");
			String randomstring=new RandomString().randomstring(20);
			boolean postdone = new PostMessage().postMessageAndShare(randomstring, "", "b", "","");
			if(postdone == false) Assert.fail("Post not Posted successfully");			
			boolean textpresent=new PostMessageValidate().postMessageValidate(randomstring);
			if(textpresent==false) Assert.fail("Post Text not found as expected"); 
		}
		catch(Exception e)
		{
			  logger.log(Level.SEVERE,"Exception  in testcase postaText in class SharingAPostWithStudent");
			  Assert.fail("Exception  in testcase postAText in class SharingAPostWithStudent",e);
		} 
	}
	 
	
	@Test(priority=2,enabled=true)
	public void CheckThePostInSharedStudentProfile()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new UserCreate().CreateStudent("b", "");//create student
			new DirectLogin().studentLogin("a");			
			new Navigator().NavigateTo("Course Stream");
			String randomstring=new RandomString().randomstring(20);
			new PostMessage().postMessageAndShare(randomstring, "", "b", "","");
			new Logout().logout();
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			boolean textpresent=new PostMessageValidate().postMessageValidate(randomstring);
			if(textpresent==false)
			{				 
				 Assert.fail("Posted text not shown on share with student dashborad");				
			}
			
			
			
		}
		catch(Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper");
			  Assert.fail("Exception  in testcase CheckThePostInSharedStudentProfile in class SharingAPostWithStudent",e);
		     
	    }
	}
	 
	@Test(priority=3,enabled=true)
	public void CheckThePostWithStudentWithWhomPostIsNotShare()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student a
			new UserCreate().CreateStudent("b", "");//create student b
			new UserCreate().CreateStudent("c", "");//create student c
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			String randomstring=new RandomString().randomstring(20);
			Boolean postedandshared = new PostMessage().postMessageAndShare(randomstring, "", "c", "","");
			if(postedandshared != true)
			Assert.fail("Failed posting message and sharing with a user");	
			new Logout().logout();
			new DirectLogin().studentLogin("b");
			new Navigator().NavigateTo("Course Stream");
			Boolean textpresent = new PostMessageValidate().postMessageValidate(randomstring);
			if(textpresent==true)	
			{
				Assert.fail("Posted text present on the dashboard of student with whom post is not shared");
			}
		
		}
		catch(Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper");
			  Assert.fail("Exception  in testcase CheckThePostWithStudentWithWhomPostIsNotShare in class SharingAPostWithStudent",e);
	    }
		
	}
	 @AfterMethod
		public void TearDown()throws Exception
		{
			Driver.driver.quit();
		}	

}
