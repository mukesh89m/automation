package com.snapwiz.selenium.tests.staf.dummies.testcases.IT14.R32;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.CreateOwnQuizByStudent;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.Click;

public class DiffrentQuizTypeSupported 
{
	@Test
	public void diffrentQuizTypeSupported()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			Thread.sleep(5000);
			new CreateOwnQuizByStudent().createOwnQuizByStudentFromAllTLO("1");//create custom quiz with practice type
			new Click().clickByid("submit-practice-question-button");//click on submit button
			//6,7
			boolean userResponce=new BooleanValue().booleanbyclass("user-response-data");//verify user responce option
			if(userResponce==false)
				Assert.fail("for practice test after answered each questions user responce nor shown");
			new CreateOwnQuizByStudent().createOwnQuizByStudentFromAllTLO("2");//create custom quiz with test type
			new Click().clickByid("submit-practice-question-button");
			//8
			int userResponceforTestQuiz=Driver.driver.findElements(By.className("user-response-data")).size();
			if(userResponceforTestQuiz!=0)
				Assert.fail("for TEST TYPE quiz test, after answered each questions user responce shown");						
		}
		catch(Exception e)
		{
			Assert.fail("Exception uin testcase DiffrentQuizTypeSupported in test method diffrentQuizTypeSupported",e);
		}
	}
	
	@AfterMethod
	public void TearDown() throws Exception
	{
		Driver.driver.quit();
	}	
}
