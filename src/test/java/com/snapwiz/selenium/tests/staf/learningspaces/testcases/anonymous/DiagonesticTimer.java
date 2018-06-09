package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;


public class DiagonesticTimer extends Driver
{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.DiagonesticTimer");
	/*
	 * 1107-1109
	 */
	@Test
	public void diagonestictimer()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1107");
            new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);
			boolean timevalue=new Timer().timer();
			
			if(timevalue==true)
			{
				logger.log(Level.INFO,"TestCase diagonestictimer Pass ");
			}
			else
			{
				logger.log(Level.INFO,"TestCase diagonestictimer fail ");
				Assert.fail("Timer not shown or timer stop after click on another tab");
			}
			
		}
		catch(Exception e)
	    {
				  Assert.fail("Exception in diagonestictimer testcase of class DiagonesticTimer.",e);
				  
		}
	}
	@AfterMethod
	public void TearDown()throws Exception
	{
		driver.quit();
	}

}
