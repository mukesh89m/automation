package com.snapwiz.selenium.tests.staf.dummies.testcases.IT14.R32;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.Click;

public class StudentAbleToAttemptPracticetestFromDashboard
{
	@Test
	public void studentAbleToAttemptPracticetestFromDashboard()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Click().clickByclassname("start-practice-link");//click on start practice from dashboard
			new Click().clickByclassname("start-adaptive-practice");//click on start practice test link
			//3,2
			boolean questionnumber=new BooleanValue().booleanbyclass("al-diag-test-question-label");
			if(questionnumber==false)
				Assert.fail("after click on start practice test its not goes to question page ");
			new Navigator().NavigateTo("Dashboard");//navugate to dashboard
			new Click().clickByclassname("start-practice-link");//click on start practice from dashboard
			Thread.sleep(3000);
			new Click().clickByclassname("custom-practice-quiz");//click on craere custom test link
			//4
			boolean customquizdetails=new BooleanValue().booleanbyclass("custom-quiz-details");
			if(customquizdetails==false)
				Assert.fail("after click on crate your own quiz its not goes to custom quiz details. ");			
		}
		catch(Exception e)
		{
			Assert.fail("Exception uin testcase StudentAbleToAttemptPracticetestFromDashboard in test method studentAbleToAttemptPracticetestFromDashboard",e);
		}
	}
	
	@AfterMethod
	public void TearDown() throws Exception
	{
		Driver.driver.quit();
	}	
}
