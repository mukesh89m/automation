package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1511;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class DiscussionAndNotesAddForStaticAssessment extends Driver
{
	@Test(priority=1,enabled=true)
	public void discussionAndNotesAddForStaticAssessment()
	{
		try
		{

			String randomtext=new RandomString().randomstring(3);
			new LoginUsingLTI().ltiLogin("167");
			new Navigator().NavigateTo("e-Textbook");
            new SelectCourse().selectInvisibleAssignment("1.1 Concept Check");
			new AttemptTest().StaticTest();//attempt static test
			//167-182
			new Discussion().DiscussionAndNotesBookmarkedValidation(randomtext,true);//add discusiion and notes and delete and unbookmark
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase DiscussionAndNotesAddForStaticAssessment in method discussionAndNotesAddForStaticAssessment",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void addDiscussionAndNotesOnStaticQuestionReviewPage()
	{
		try
		{

			String randomtext=new RandomString().randomstring(3);
			new LoginUsingLTI().ltiLogin("185");//login as student
			new Navigator().NavigateTo("e-Textbook");
            new SelectCourse().selectInvisibleAssignment("1.2 Concept Check");
			new Click().clickbyxpath("//input[@title='Submit Answer']");//click on submit button of question 1
			//185-199
			new Discussion().DiscussionAndNotesBookmarkedValidation(randomtext,false);//add notes and discussion on question review page
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase DiscussionAndNotesAddForStaticAssessment in method addDiscussionAndNotesOnStaticQuestionReviewPage",e);

		}
	}
	

}
