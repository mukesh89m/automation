package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;

public class LTILSAndAdaptiveLogin extends Driver {

	
	 @Test
	  public void lsandadaptivelogin()  
	 {
		 try
		 {
			 new LoginUsingLTI().ltiLogin("5");
			 boolean dashboard=new BooleanValue().booleanbyclass("ls-dashboard-container");
			 if(dashboard==false)
				 Assert.fail("after login not goes to dashbaord");
		 }
		 catch(Exception e)
		 {
			 Assert.fail("Exception in  TestCase LTILogin",e);
		 }	
		
	 }	 


}
