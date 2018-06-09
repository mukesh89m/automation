package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class LTIAdaptiveLoginWithInvalidResourceLinkId {

	@Test
	public void ltiAdaptiveLoginWithInvalidResourceLinkId()
	{
		try
		{
            new LoginUsingLTI().ltiLogin("25");
			String errorMessage = new TextFetch().textfetchbyclass("al-error-body-content");
            System.out.println("errorMessage: "+errorMessage);
            if(!errorMessage.contains("If you are seeing this frequently, please contact your system administrator for help."))
                Assert.fail("User is able to login to adaptive course using invalid resource link id.");

		}
		catch(Exception e)
		{
            Assert.fail("Exception  in testcase ltiAdaptiveLoginWithInvalidResourceLinkId in class LTIAdaptiveLoginWithInvalidResourceLinkId", e);
		}
	}

}
