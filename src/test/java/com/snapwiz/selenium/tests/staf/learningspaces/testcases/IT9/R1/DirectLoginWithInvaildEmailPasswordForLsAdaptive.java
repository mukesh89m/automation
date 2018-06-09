package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TextValidate;

public class DirectLoginWithInvaildEmailPasswordForLsAdaptive extends Driver {

private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.DirectLoginWithInvaildEmailPasswordForLsAdaptive");
	
	@Test
	public void directLoginWithInvaildEmailPasswordForLsAdaptive() 
	  {
		 try
		 {
			
			 new DirectLogin().directLogin("30");
		     boolean test2= new TextValidate().IsTextPresent("Username and Password doesn't match.") ;
		       if(test2 == true)
			     {
		    	   logger.log(Level.INFO,"For Invalid combination of email and password error message is shown for LS+Adaptive course");
			     }
			     else
			     {
			    	 Assert.fail("For Invalid combination of email and password error message is not shown for LS+Adaptive course");
			     }
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
					 Assert.fail("Exception TestCase directLoginWithInvaildEmailPasswordForLsAdaptive in class DirectLoginWithInvaildEmailPasswordForLsAdaptive",e);			 
				 }
				
		 
	  }


}
