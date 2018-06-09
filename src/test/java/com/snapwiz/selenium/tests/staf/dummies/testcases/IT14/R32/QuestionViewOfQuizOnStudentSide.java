package com.snapwiz.selenium.tests.staf.dummies.testcases.IT14.R32;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.CreateOwnQuizByStudent;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.Click;

public class QuestionViewOfQuizOnStudentSide 
{
	@Test
	public void questionViewOfQuizOnStudentSide()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			Thread.sleep(3000);
			new CreateOwnQuizByStudent().createOwnQuizByStudentFromAllTLO("1");
			//217,218,
			boolean headerOfQuiz=new BooleanValue().booleanbyid("al-practice-test-chapter-label");
			if(headerOfQuiz==false)
				Assert.fail("header of quiz not shown");
			boolean timer=new BooleanValue().booleanbyid("assessmentTimer");
			if(timer==false)
				Assert.fail("timer not shown on question page");
			boolean questionnumber=new BooleanValue().booleanbyclass("al-diag-test-question-label");
			if(questionnumber==false)
				Assert.fail("question number of x of y not shown");
			boolean performanceframe=new BooleanValue().booleanbyclass("difficulty-level-block-wrapper");
			if(performanceframe==false)
				Assert.fail("performance frame not shown");
			//221,222
			new Click().clickByid("al-diagtest-markForReview");//checked mark for review 
			new Click().clickByid("al-diagtest-markForReview");//unchecked mark for review 
			new Click().clickByid("submit-practice-question-button");//223---click on submit button
			new Click().clickByid("next-practice-question-button");//224---click on next button
			new Click().clickByid("submit-practice-question-button");//click on submit button
			new PracticeTest().continueLaterPracticeTestAndGoToDashboard();
			Thread.sleep(3000);
			new Click().clickByclassname("recent-activty-ellipsis");//click on recent open quiz from dashboard
			new PracticeTest().quitTestAndGoToDashboard();
		}
		catch(Exception e)
		{
			Assert.fail("Exception uin testcase QuestionViewOfQuizOnStudentSide in test method QuestionViewOfQuizOnStudentSide",e);
		}
	}
	
	@AfterMethod
	public void TearDown() throws Exception
	{
		Driver.driver.quit();
	}	
}
