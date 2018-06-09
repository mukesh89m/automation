package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Logout;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;

public class ShareALinkWithClassSection {
	/*
	 * 233-235
	 */
	@Test(priority = 1,enabled = true)
	public void ShareALinkWithDefaultClassSection()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			new PostMessage().postlink("www.google.com");
			if(!new PostMessageValidate().validateLink("www.google.com"))			
				Assert.fail("Link not posted on the course stream.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception  in testcase ShareALinkWithDefaultClassSection in class ShareALinkWithClassSection",e);	      
		}
	}

	@Test(priority = 2,enabled = true)
	public void ShareALinkWithotherusersameClassSection()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			new PostMessage().postlink("www.yahoo.com");
			new Logout().logout();
			
			new UserCreate().CreateStudent("b", "");//create student
			new DirectLogin().studentLogin("b");
			new Navigator().NavigateTo("Course Stream");
			if(!new PostMessageValidate().validateLink("www.yahoo.com"))
				Assert.fail("posted link not present in dashboard of other student of same class section");
		}
		catch(Exception e)
		{
			Assert.fail("Exception  in testcase ShareALinkWithotherusersameClassSection in class ShareALinkWithClassSection",e);      
		}
	}

	//provision for sharing with other class section is not there
	@Test(priority = 3,enabled = false)
	public void ShareALinkWithotherClassSection()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			new PostMessage().postlink("www.gmail.com");
			new Logout().logout();
			
			new UserCreate().CreateStudent("b", "");//create student
			new DirectLogin().studentLogin("b");
			new Navigator().NavigateTo("Course Stream");
			if(new PostMessageValidate().validateLink("www.gmail.com"))
				Assert.fail("Link share by the user 233 of one class section is visible by user 235 who is of other class section");
		}
		catch(Exception e)
		{
			Assert.fail("Exception  in testcase ShareALinkWithotherClassSection in class ShareALinkWithClassSection",e); 
		}
	}

	@AfterMethod
	public void tearDown() throws Exception 
	{
		Driver.driver.quit();    
	}
}
