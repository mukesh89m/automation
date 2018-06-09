package com.snapwiz.selenium.tests.staf.orion.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.AddDiscussionInQuestions;
import com.snapwiz.selenium.tests.staf.orion.apphelper.ClickOnquestionCard;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.InstructorDashboardMatrix;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class InstructorSeeDiscussionItemHighlighted 
{
	@Test
	public void instructorSeeDiscussionItemHighlighted()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("927");//login as 1st instructor
			int count1=new InstructorDashboardMatrix().discussioncount();//fetch discussion value for 1st instructor
			new LoginUsingLTI().ltiLogin("9271");//login as 2nd instructor
			int count2=new InstructorDashboardMatrix().discussioncount();//fetch discussion value for 2nd instructor
			new LoginUsingLTI().ltiLogin("9272");//login as student
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 1, "correct", false, false, true);//attempt diagnostic test
			new ClickOnquestionCard().clickOnQuestion(0);//click on question card 1
			String textString=new RandomString().randomstring(3);
			new AddDiscussionInQuestions().AddDiscussion(textString);
			new LoginUsingLTI().ltiLogin("927");//again login as 1st instructor
			int count3=new InstructorDashboardMatrix().discussioncount();
			if(count3==count1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("discussion count not increase for 1st instructor");
			}
			new LoginUsingLTI().ltiLogin("9271");//again login as 2nd instructor
			int count4=new InstructorDashboardMatrix().discussioncount();
			if(count4==count2)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("discussion count not increase 2nd instructor");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase instructorSeeDiscussionItemHighlighted in class InstructorSeeDiscussionItemHighlighted",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}

}
