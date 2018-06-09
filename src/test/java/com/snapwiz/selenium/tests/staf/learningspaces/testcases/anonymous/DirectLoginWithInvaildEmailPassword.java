package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TextValidate;
/*
 * TC 26
 */
public class DirectLoginWithInvaildEmailPassword extends Driver{
	
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.DirectLoginWithInvaildEmailPassword");
	
	@Test
	public void directLoginWithInvaildEmailPassword() 
	  {
		 try
		 {
			 
			 new DirectLogin().directLogin("26");
	
		     boolean userpassValidate= new TextValidate().IsTextPresent("Username and Password doesn't match.") ;
		       if(userpassValidate==true)
			     {
			    	 logger.log(Level.INFO,"For Invalid combination of email and password error message is shown");
			    	 
			     }
			     else
			     {
			    	 Assert.fail("For Invalid combination of email and password error message is not shown");
			     }
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
					 Assert.fail("Exception TestCase directLoginWithInvaildEmailPassword in class DirectLoginWithInvaildEmailPassword",e);			 
				 }
			 
	  }


}
