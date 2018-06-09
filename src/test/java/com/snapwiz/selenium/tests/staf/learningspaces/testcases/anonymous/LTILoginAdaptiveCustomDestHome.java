package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;


import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TextValidate;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * 20
 */
public class LTILoginAdaptiveCustomDestHome extends Driver {
	@Test
	public void LoginAdaptiveCustomDestHome()
	{
		 try
		 {
		 new LoginUsingLTI().ltiLogin("20");
		 Boolean errorPresent = new TextValidate().IsTextPresent("Something went wrong in processing your request.");
		 if(errorPresent == true)
			 Assert.fail("LTI login failed with Course Id of Adaptive and custom_destination field as snapwiz-home.");
		
		 }
		 catch(Exception e)
		 {
             Assert.fail("Exception in  TestCase LTILoginAdaptiveCustomDestHome in class LTILoginAdaptiveCustomDestHome.",e);
		 }
	}


}
