package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;

public class LTILSAndAdptiveLoginWithValidResourceLinkId extends Driver {

	/*
	 * 23
	 */
	@Test
	public void ltiLSAndAdptiveLoginWithValidResourceLinkId(){
		try
		{
			new LoginUsingLTI().ltiLogin("23");
			boolean dashboard=new BooleanValue().booleanbyclass("ls-dashboard-container");
			if(dashboard==false)
				Assert.fail("User doesnt go to Dashboard with valid resource link id.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in  TestCase ltiLSAndAdptiveLoginWithValidResourceLinkId in class LTILSAndAdptiveLoginWithValidResourceLinkId. ", e);
		}		
	}

}
