package com.snapwiz.selenium.tests.staf.dummies.testcases.IT14.R32;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.Click;

public class StudentAbleToStartPracticeTestFromDashboard
{
	@Test
	public void sudentAbleToStartPracticeTestFromDashboard()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			Thread.sleep(3000);
			//53
			new Click().clickByclassname("start-practice-link");
			//54,55
			new Click().clickByclassname("ls-back-button");
			Thread.sleep(3000);
			new Click().clickByclassname("start-practice-link");
			//56
			new Click().clickByclassname("start-adaptive-practice");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase StudentAbleToStartPracticeTestFromDashboard in test method studentAbleToStartPracticeTestFromDashboard",e);
		}
	}
	@AfterMethod
	public void TearDown() throws Exception
	{
		Driver.driver.quit();
	}	

}
