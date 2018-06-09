package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;

public class DirectLoginLSAdaptiveBackToWileyplusOptionNotAvilable extends Driver{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.DirectLoginLSAdaptiveBackToWileyplusOptionNotAvilable");
	/*
	 * 41 and 42
	 */
	
	@Test
	public void directLoginLSAdaptiveBackToWileyplusOptionNotAvilable()
	{
		try
		{
			Boolean isPresent = true;
			Boolean backToWiley = true;
			new DirectLogin().directLogin("41");
			Thread.sleep(5000);
	        try
	        {
	        driver.findElement(By.className("ls-header__title-change"));
	        driver.findElement(By.id("al-header-return-url"));
	        }
	        catch(Exception e)
	        {
	        	isPresent = false;
	        	backToWiley=false;
	        	
	        }
	        if(isPresent == true && backToWiley==true)
	        {
	        	
	        	Assert.fail("BackTo WileyPLUS option and change option present on the dashboard");
	        }
	        else
	        {
	        	logger.log(Level.INFO,"BackTo WileyPLUS option and change option not present on the dashboard");
	        	
	        }
	       
	        
			
		}
		catch(Exception e)
		  {
			  logger.log(Level.SEVERE,"Exception in DirectLogin Application Helper",e);
			  Assert.fail("Exception  in testcase ditectlogin in class DirectLogin",e);
		  }
	}


}
