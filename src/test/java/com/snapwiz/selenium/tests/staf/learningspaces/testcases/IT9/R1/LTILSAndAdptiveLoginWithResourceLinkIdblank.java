package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;

/*
 * 21
 */
public class LTILSAndAdptiveLoginWithResourceLinkIdblank extends Driver{
	@Test
	public void ltiLSAndAdptiveLoginWithResourceLinkIdblank()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("21");
			boolean dashboard=new BooleanValue().booleanbyclass("ls-dashboard-container");
			if(dashboard==false)
				Assert.fail("User still lands in Dashboard with Resource Link id left blank during LTILogin.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in  TestCase ltiLSAndAdptiveLoginWithResourceLinkIdblank in class LTILSAndAdptiveLoginWithResourceLinkIdblank. ", e);
		}	
	}

}
