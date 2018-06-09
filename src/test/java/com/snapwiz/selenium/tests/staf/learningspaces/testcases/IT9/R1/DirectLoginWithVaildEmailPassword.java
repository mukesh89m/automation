package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;

public class DirectLoginWithVaildEmailPassword extends Driver {
	
	@Test
	public void directLoginWithVaildEmailPassword() 
	  {
		 try
		 {			
			 new DirectLogin().directLogin("27");
			 boolean dashboardheader=new BooleanValue().booleanbyclass("ls-student-header-wrapper");
			 if(dashboardheader==false)
				 Assert.fail("after login with correct username and password not goes to dashboard");			 
		 }
		 catch(Exception e)
		 {
			 Assert.fail("Exception TestCase directLoginWithVaildEmailPassword in class DirectLoginWithVaildEmailPassword",e);			 
		 }	 				 
	  }

}
