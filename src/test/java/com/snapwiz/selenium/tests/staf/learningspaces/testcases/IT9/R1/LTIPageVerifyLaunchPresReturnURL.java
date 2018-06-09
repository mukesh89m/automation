package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

/*
 * 32
 */

public class LTIPageVerifyLaunchPresReturnURL extends Driver{
	
	@Test
	public void pageVerifyLaunchPresReturnURL()
	{
		try
		{
			 String url = Config.baseLTIURL;
			 driver.get(url);
		     String  presentationUrl = driver.findElement(By.name("launch_presentation_return_url")).getAttribute("value");
			 
			 if(!presentationUrl.equals("http://edugen.wiley.com/edugen/rs-callback"))
				 Assert.fail("Invalid Return URL specified in launch_presentation_return_url field of LTI login form");
			 
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase pageVerifyLaunchPresReturnURL in class LTIPageVerifyLaunchPresReturnURL." , e);
		}
	}	


}
