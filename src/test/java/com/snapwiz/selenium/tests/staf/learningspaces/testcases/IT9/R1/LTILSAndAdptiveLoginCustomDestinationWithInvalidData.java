package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;

public class LTILSAndAdptiveLoginCustomDestinationWithInvalidData extends Driver {
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.LTILSAndAdptiveLoginCustomDestinationWithInvalidData");
	
	@Test
	public void ltiLSAndAdptiveLoginCustomDestinationWithInvalidData()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("17");
			String text=driver.findElement(By.xpath("html/body/div[2]/div[3]/div[2]")).getText();;
			String texttoverify=ReadTestData.readDataByTagName("", "expectederror", "17");
			if(!text.trim().contains(texttoverify))
				 Assert.fail("User  not redirected to error page when custom-distination, field with invalid data");
			
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception in  TestCase LTILSAndAdptiveLoginCustomDestinationWithInvalidData",e);
			Assert.fail("Exception  in testcase loginlti in class LoginUsingLTI",e);
		}
		
	}

	
}
