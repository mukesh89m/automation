package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;


public class LSNormalLoginWileyPlusNotPresentForAdaptive extends Driver{
	
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.LSNormalLoginWileyPlusNotPresentForAdaptive");
	@Test
	public void NormalLoginWileyPlusNotPresentForAdaptive() {
		try
		{
			logger.log(Level.INFO,"Starting TestCase LSNormalLoginWileyPlusNotPresent");
			new DirectLogin().directLogin("38");
			int present = driver.findElements(By.className("ls-header__title-change")).size();
			if(present != 0)
				 Assert.fail("Back to WileyPlus option available over dashboard in case of Direct Login");
			
			
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception in  TestCase LSNormalLoginWileyPlusNotPresentForAdaptive",e);
			Assert.fail("Exception in  TestCase LSNormalLoginWileyPlusNotPresentForAdaptive",e);
		}
	}

}
