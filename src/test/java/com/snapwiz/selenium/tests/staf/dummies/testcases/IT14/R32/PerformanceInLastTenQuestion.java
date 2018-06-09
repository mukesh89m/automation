package com.snapwiz.selenium.tests.staf.dummies.testcases.IT14.R32;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.CreateOwnQuizByStudent;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PerfomancePageVerification;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.TextFetch;

public class PerformanceInLastTenQuestion
{
	@Test
	public void performanceInLastTenQuestion()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("aa", "");//create student
			new DirectLogin().studentLogin("aa");
			Thread.sleep(3000);
			new CreateOwnQuizByStudent().createOwnQuizByStudentFromAllTLO("1");//create practice quiz
			//231-249
			for(int i=1; i<=10;i++)
			{
				new PracticeTest().attemptQuestion("correct", 0, false, false);//attempt 10 questions correctly
			}
			Thread.sleep(2000);
			int barCount =new PerfomancePageVerification().countOfBar();//count answer bar
			if(barCount!=10)
				Assert.fail("count of bar is not 10after attempt 1o question correctly");
			for(int i=1; i<=3;i++)
			{
				new PracticeTest().attemptQuestion("skip", 0, false, false);//attempt 3 question skip
			}
			int barCount1 =new PerfomancePageVerification().countOfBar();//count answer bar
			if(barCount1!=10)
				Assert.fail("count of bar is not 10after attempt 1o question correctly");
			for(int i=1; i<=3;i++)
			{
				new PracticeTest().attemptQuestion("incorrect", 0, false, false);//attempt 3 question incorrectly
			}
			int barCount2 =new PerfomancePageVerification().countOfBar();//count answer bar
			if(barCount2!=10)
				Assert.fail("count of bar is not 10after attempt 1o question correctly");
			boolean difficultylevel=new BooleanValue().booleanbyclass("al-difficulty-level-status");//--250-251verify difficulty of question
			if(difficultylevel==false)
				Assert.fail("difficulty level of question not shown");
			boolean learningobjective=new BooleanValue().booleanbyclass("al-question-objective-content-wrapper");//----252-254verify LO of question
			if(learningobjective==false)
				Assert.fail("learning objective of question not shown");
			String timevalue1=new TextFetch().textfetchbyclass("timevalue");//fetch time value
			
			System.out.println(timevalue1);
			for(int i=1; i<=1;i++)
			{
				new PracticeTest().attemptQuestion("skip", 0, false, false);
			}
			Thread.sleep(3000);
			String timevalue2=new TextFetch().textfetchbyclass("timevalue");//fethc time value after 3 second
			//256-259
			System.out.println(timevalue2);
			if(timevalue1.equals(timevalue2))
				Assert.fail("time is not running");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase PerformanceInLastTenQuestion in  method performanceInLastTenQuestion",e);
		}
	}
	@AfterMethod
	public void TearDown() throws Exception
	{
		Driver.driver.quit();
	}	
}
