package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TextValidate;

public class DirectLoginWithInvaildEmailPasswordForAdaptive extends Driver{
private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin");
	
	@Test
	public void directLoginWithInvaildEmailPasswordForAdaptive() 
	  {
		 try
		 {
			 
			 new DirectLogin().directLogin("28");

		     boolean test2= new TextValidate().IsTextPresent("Username and Password doesn't match.") ;
		       if(test2 == true)
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
					 Assert.fail("Exception TestCase directLoginWithInvaildEmailPasswordForAdaptive in class DirectLoginWithInvaildEmailPasswordForAdaptive",e);			 
				 }
				
		 
	  }



}
