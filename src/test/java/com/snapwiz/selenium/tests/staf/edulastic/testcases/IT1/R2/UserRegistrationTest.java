package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.R2;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
//import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.UserRegistration;

public class UserRegistrationTest extends Driver
{
	@Test
	public void userRegistrationTest()
	{
		try
		{
			driver.get(Config.launchURL);
			//new UserRegistration().studentRegistration("1");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase UserRegistrationTest in method userRegistrationTest",e);
		}
	}

}
