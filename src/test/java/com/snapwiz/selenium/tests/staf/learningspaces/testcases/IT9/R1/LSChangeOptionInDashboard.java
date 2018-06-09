package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
/*
 * 33,34
 */

public class LSChangeOptionInDashboard extends Driver{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.LSChangeOptionInDashboard");
	@Test(priority=1,enabled=true)
	public void changeOptionInDashboard()
	{
		try
		{
		 logger.log(Level.INFO,"Starting TestCase LTILoginResLinkAnyValue");
		 new LoginUsingLTI().ltiLogin("33");
		 if(!("http://edugen.wiley.com/edugen/rs-callback").equals(driver.findElement(By.className("ls-header__title-change")).getAttribute("returnurl")))
				 Assert.fail("Change Option not available in the dashboard header.");
		 driver.findElement(By.className("ls-header__title-change")).click();
		 Thread.sleep(5000);
		 if(!driver.getCurrentUrl().equals("http://edugen.wiley.com/edugen/rs-callback"))
			 Assert.fail("Page not navigated to http://edugen.wiley.com/edugen/rs-callback after clicking Change link on LS dashboard header");				 
		}
		catch(Exception e)
		{
			 logger.log(Level.SEVERE,"Exception in  TestCase ChangeOptionInDashboard in class LSChangeOptionInDashboard",e);
		}
	

	}

}
