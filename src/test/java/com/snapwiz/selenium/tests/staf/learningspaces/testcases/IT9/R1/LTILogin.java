package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;

public class LTILogin extends Driver{

	  private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.LTILogin");
	 /*
	  * 1-2
	  */
	  
	 @Test
	  public void loginlti()  {
		 try
		 {
			 logger.log(Level.INFO,"Starting TestCase LTILogin");
			 new LoginUsingLTI().ltiLogin("2");
		 }
		 catch(Exception e)
		 {
			 logger.log(Level.SEVERE,"Exception in  TestCase LTILogin",e);	
			 Assert.fail("Problem in LoginUsingLTI",e);
		 }
	 }
	 

	
}
