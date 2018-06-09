package com.snapwiz.selenium.tests.staf.orion.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

/*
 * check login as different users
 * Brajesh-22-01-2014
 */
public class HandleMultipleRoleForUsers
{
	@Test
	public void MultipleRoleOfUsers()
	{
		try
		{			
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("692");//login as student
			new LoginUsingLTI().ltiLogin("6921");//login as instructor as same name as student			
			new LoginUsingLTI().ltiLogin("6922");//login as mentor as same name as student	
			new LoginUsingLTI().ltiLogin("6923");//login as instructor 
			new LoginUsingLTI().ltiLogin("6924");//login as student as same name as instructor
			new LoginUsingLTI().ltiLogin("6925");//login as mentor as same name as instructor
			new LoginUsingLTI().ltiLogin("6926");//login as student of default class section
			new LoginUsingLTI().ltiLogin("6927");//login as same instructor as 6926 as different class section
			new LoginUsingLTI().ltiLogin("6928");//login as same mentor as 6926 as different class section
			new LoginUsingLTI().ltiLogin("701");//login as student
			new LoginUsingLTI().ltiLogin("702");//login as instructor
			new LoginUsingLTI().ltiLogin("703");//login as mentor
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in method MultipleRoleOfUsers in class HandlemMultipleroleForUsres ",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
