package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;

public class SharingAPostWithStudent extends Driver{
	/*
	 * 212,213,124
	 */
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.SharingAPostWithStudent");
	
	@Test(priority=1,enabled=true)
	public void postAText()
	{
		//post a with share with student
		try
		{

			new LoginUsingLTI().ltiLogin("212");			
			new LoginUsingLTI().ltiLogin("213");					
			new LoginUsingLTI().ltiLogin("212");
			new Navigator().NavigateTo("Course Stream");
			String randomstring=new RandomString().randomstring(20);
			String studentname=ReadTestData.readDataByTagName("", "studentname", "212");
			boolean postdone = new PostMessage().postMessageAndShare(randomstring, studentname, "studentnameverify", "212","");	
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

			new LoginUsingLTI().ltiLogin("212");			
			new LoginUsingLTI().ltiLogin("213");				
			new LoginUsingLTI().ltiLogin("212");	
			new Navigator().NavigateTo("Course Stream");
			String randomstring=new RandomString().randomstring(20);
			String studentname=ReadTestData.readDataByTagName("", "studentname", "212");
			new PostMessage().postMessageAndShare(randomstring, studentname, "studentnameverify", "212","");
			new LoginUsingLTI().ltiLogin("213");
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

			new LoginUsingLTI().ltiLogin("212");
			new Navigator().NavigateTo("Course Stream");
			String randomstring=new RandomString().randomstring(20);
			String studentname=ReadTestData.readDataByTagName("", "studentname", "212");			
			Boolean postedandshared = new PostMessage().postMessageAndShare(randomstring,studentname,"studentnameverify","212","");
			if(postedandshared != true)
			Assert.fail("Failed posting message and sharing with a user");			
			new LoginUsingLTI().ltiLogin("214");
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


}
