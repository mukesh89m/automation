package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class DiagnosticTestNavigateToQuestion extends Driver
{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.DiagnosticTestNavigateToQuestion");
	/*
	 * 1086-1089
	 */
	@Test
	public void navigateToQuestion()
	{
		try
		{
			driver.manage().deleteAllCookies();
			new LoginUsingLTI().ltiLogin("1086");
            new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);
			new AttemptTest().Diagonestictest();
			boolean performancevalue=driver.findElement(By.className("al-performance-chart-title")).isDisplayed();
			if(performancevalue==true)
			{
				logger.log(Level.INFO,"Performance summary report shown after finish the Diagnostic test");
			}
			else
			{
				logger.log(Level.INFO,"Performance summary report NOt shown after finish the Diagnostic test");
				Assert.fail("Performance summary report NOt shown after finish the Diagnostic test");
			}
			
		}
		catch(Exception e)
	    {
				  Assert.fail("Exception in navigateToQuestion testcase of class DiagnosticTestNavigateToQuestion.", e);
				  
		}
	}

}
