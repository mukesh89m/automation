package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;

public class DirectLoginWithVaildEmailPasswordForAdaptive extends Driver {
	
	@Test
	public void directLoginWithVaildEmailPasswordForAdaptive() 
	  {
		 try
		 {			 
			 new DirectLogin().directLogin("29");
			 boolean dashboardheader=new BooleanValue().booleanbyclass("ls-student-header-wrapper");
			 if(dashboardheader==false)
				 Assert.fail("after login with correct username and password not goes to dashboard");	
		 }
		 catch(Exception e)
		 {
			 Assert.fail("Exception in  TestCase DirectLoginWithVaildEmailPasswordForAdaptive pass",e);			 
		 }
	  }

}
