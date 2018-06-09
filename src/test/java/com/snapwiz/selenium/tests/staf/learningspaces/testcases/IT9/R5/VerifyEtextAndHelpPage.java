package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R5;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;


public class VerifyEtextAndHelpPage extends Driver
{
	@Test(priority=1, enabled=true)
	public void verifyEtextAndHelpPage()
	{
		try
		{
			  new LoginUsingLTI().ltiLogin("531");
			  new Navigator().NavigateTo("Course Stream");
			  new Navigator().NavigateTo("eTextBook");
		}
		catch(Exception e)
		   {
			Assert.fail("Exception TestCase verifyEtextAndHelpPage in class VerifyEtextAndHelpPage",e);
		   }
		
	}

@Test(priority=2, enabled=false)
public void verifyHelpPageAfterLogoutLogin()
{
	try
	{

		  new LoginUsingLTI().ltiLogin("534");
		  new Navigator().NavigateTo("eTextBook");	  
		  new LoginUsingLTI().ltiLogin("534");		 
		  new Navigator().NavigateTo("eTextBook");		 
	}
	catch(Exception e)
	   {
		Assert.fail("Exception TestCase verifyHelpPageAfterLogoutLogin in class VerifyEtextAndHelpPage",e);
	   }
	
}

}
