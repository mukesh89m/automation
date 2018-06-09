package com.snapwiz.selenium.tests.staf.orion.testcases;


import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;

public class LoginLTI {
	
	@Test(priority=1,enabled=true)
	public void loginLTI()
	{
		try
		{
//		Driver.startDriver();
		/*List<Double> profs = DBConnect.getProficienciesFromMongo();
		for(Double prof : profs)
			System.out.println(prof);*/
		//new LoginUsingLTI().ltiLogin("2"); //  Logging in as student to orion
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase LoginLTI",e);
		}
	}

}
