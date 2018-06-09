package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;

public class DirectLoginLSAdaptiveBackToWileyplusOptionNotAvilable extends Driver{
	/*
	 * 41 and 42
	 */
	
	@Test
	public void directLoginLSAdaptiveBackToWileyplusOptionNotAvilable()
	{
		try
		{
			new DirectLogin().directLogin("41");
			Thread.sleep(3000);
	        int titlechange = driver.findElements(By.className("ls-header__title-change")).size();;
	        int bechtowilyplus = driver.findElements(By.id("al-header-return-url")).size();
	        if(titlechange==1)	        
	        	Assert.fail("change option present on the dashboard");
	       
	        if(bechtowilyplus==1)	        
	        	Assert.fail("BackTo WileyPLUS present on the dashboard");	        	       
		}
		catch(Exception e)
		  {
			  Assert.fail("Exception  in testcase ditectlogin in class DirectLogin",e);
		  }
	}


}
