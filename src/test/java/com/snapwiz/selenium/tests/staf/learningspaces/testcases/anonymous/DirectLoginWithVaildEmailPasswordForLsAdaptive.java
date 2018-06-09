package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TextValidate;


public class DirectLoginWithVaildEmailPasswordForLsAdaptive extends Driver{
private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.DirectLoginWithVaildEmailPasswordForLsAdaptive");
	
	@Test
	public void directLoginWithVaildEmailPasswordForLsAdaptive() 
	  {
		 try
		 {
			 
			 new DirectLogin().directLogin("31");

		     boolean test2= new TextValidate().IsTextPresent("Activity Summary") ;
		       if(test2 == true)
			     {
		    	   logger.log(Level.INFO,"Able to login with correct email and password for LS+Adaptive Course");
			    	 
			     }
			     else
			     {
			    	 Assert.fail("Unable to login with correct email and password for LS+Adaptive Course");
			     }
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
					 Assert.fail("Exception TestCase directLoginWithVaildEmailPasswordForLsAdaptive in class DirectLoginWithVaildEmailPasswordForLsAdaptive",e);			 
				 }
				 
		 
	  }

}
