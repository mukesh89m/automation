package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1511;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Discussion;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;

public class DiscussionAddFromPerformancePageAppearUnderStarTab extends Driver
{
	@Test(priority=1,enabled=true)
	public void discussionAddFromPerformancePageAppearUnderStarTab()
	{
		try
		{

			String randomtext=new RandomString().randomstring(10);
			new LoginUsingLTI().ltiLogin("95");
			new Navigator().NavigateTo("e-Textbook");
			Thread.sleep(2000);
			new DiagnosticTest().startTest(2);
			new DiagnosticTest().DiagonesticTestQuitBetween(0, 10, "correct", false, false, true);
			new Discussion().DiscussionAndNotesBookmarkedValidation(randomtext,true);//add discusiion and notes and delete and unbookmark
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase DiscussionAddFromPerformancePageAppearUnderStarTab in method discussionAddFromPerformancePageAppearUnderStarTab",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void DiscussionPostForPracticeTestQuestion()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("95");
			String randomtext=new RandomString().randomstring(10);
			new Navigator().NavigateTo("e-Textbook");
			new PracticeTest().startTest();
			for(int i=0;i<10;i++)
			{
				new PracticeTest().AttemptCorrectAnswer(0,"95");
			}
			new PracticeTest().quitThePracticeTest();
			new Discussion().DiscussionAndNotesBookmarkedValidation(randomtext,true);//add discusiion and notes and delete and unbookmark
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test(priority=3,enabled=true)
	public void discussionAddFromProficencyReportPage()
	{
		try
		{

			String randomtext=new RandomString().randomstring(3);
			new LoginUsingLTI().ltiLogin("95");//login as student
			new Navigator().NavigateTo("Proficiency Report");
			new Discussion().DiscussionAndNotesBookmarkedValidation(randomtext,true);//add discusiion and notes and delete and unbookmark
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase DiscussionAddFromPerformancePageAppearUnderStarTab in method discussionAddFromPerformancePageAppearUnderStarTab",e);
		}
	}
	

}
