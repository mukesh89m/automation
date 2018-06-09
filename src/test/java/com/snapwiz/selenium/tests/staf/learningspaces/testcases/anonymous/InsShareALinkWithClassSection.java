package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessageValidate;

public class InsShareALinkWithClassSection extends Driver{
	@Test(priority=1,enabled=true)
	public void ShareALinkWithDefaultClassSection()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("233_R24");
			new Navigator().NavigateTo("Course Stream");
			new PostMessage().postlink("www.google.com");
			if(!new PostMessageValidate().validateLink("www.google.com"))
			    Assert.fail("Link not posted on the course stream.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception  in testcase ShareALinkWithDefaultClassSection in class InsShareALinkWithClassSection",e);	      
		}
	}
	
	@Test(priority=2,enabled=true)
	public void ShareALinkWithotherusersameClassSection()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("233_R24");
			new Navigator().NavigateTo("Course Stream");
			new PostMessage().postlink("www.google.com");
			new LoginUsingLTI().ltiLogin("234_R24");
			new Navigator().NavigateTo("Course Stream");
			if(!new PostMessageValidate().validateLink("www.google.com"))
			    Assert.fail("posted link not present in dashboard of other student of same class section");
		}
		catch(Exception e)
		{
				Assert.fail("Exception  in testcase ShareALinkWithotherusersameClassSection in class InsShareALinkWithClassSection",e);      
		}
	}
	
	@Test(priority=3,enabled=true)
	public void ShareALinkWithotherClassSection()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("233_R24");
			new Navigator().NavigateTo("Course Stream");
			new PostMessage().postlink("www.google.com");
			new LoginUsingLTI().ltiLogin("235_R24");
			new Navigator().NavigateTo("Course Stream");
			if(new PostMessageValidate().validateLink("www.google.com"))
			    Assert.fail("Link share by the user 233 of one class section is visible by user 235 who is of other class section");
		}
		catch(Exception e)
		{
			Assert.fail("Exception  in testcase ShareALinkWithotherClassSection in class InsShareALinkWithClassSection",e); 
		}
	}


}
