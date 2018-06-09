package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;



import com.snapwiz.selenium.tests.staf.learningspaces.*;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Logout;

public class DirectLoginLogout extends Driver {
	
	 private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.DirectLoginLogout");
	
     
	  @Test
	  public void directLoginLogout()  {
		  
		  try
		  {		
		  logger.log(Level.INFO,"Starting Execution of Testcase");
		  new DirectLogin().directLogin("0");
		  new Logout().logout();
		 
		  logger.log(Level.INFO,"TestCase Ran Successfully");
		  }
		  catch(Exception e)
		  {
			  logger.log(Level.SEVERE,"Exception in TestCase DirectLoginLogout",e);			  
		  }
		  
	    } 


}
