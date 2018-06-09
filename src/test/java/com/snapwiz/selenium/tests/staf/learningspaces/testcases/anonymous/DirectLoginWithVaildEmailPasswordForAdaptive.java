package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TextValidate;

public class DirectLoginWithVaildEmailPasswordForAdaptive extends Driver {
private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcase.DirectLoginWithVaildEmailPasswordForAdaptive");
	
	@Test
	public void directLoginWithVaildEmailPasswordForAdaptive() 
	  {
		 try
		 {
			 
			 new DirectLogin().directLogin("29");

			 boolean test2= new TextValidate().IsTextPresent("BUILD YOUR PROFICIENCY") ;
		       if(test2==true)
			     {
		    	   logger.log(Level.INFO," In Adaptive course able to login with correct email and password");
			     }
			     else
			     {
			    	 Assert.fail("Unable to login with correct email and password in adaptive course");
			     }
				 }
				 catch(Exception e)
				 {
					 logger.log(Level.SEVERE,"Exception in  TestCase DirectLoginWithVaildEmailPasswordForAdaptive pass",e);			 
				 }
				
		 
	  }

}
