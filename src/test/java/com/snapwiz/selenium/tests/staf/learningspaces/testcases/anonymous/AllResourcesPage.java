package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UpdateContentIndex;


public class AllResourcesPage extends Driver {
	
	@Test
	public void assignThisForm()
	{
		try
		{
			//new Assignment().create(1828);
			//new UpdateContentIndex().updatecontentindex("1828");
			startDriver("chrome");
			new LoginUsingLTI().ltiLogin("18281");  //Creating student with ID 18001student
			new LoginUsingLTI().ltiLogin("1828"); //  Logging in as instructor
			new Assignment().assignFormValidate(1828); 
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase assignThisForm in class AllResourcesPage",e);
		}
	}

}
