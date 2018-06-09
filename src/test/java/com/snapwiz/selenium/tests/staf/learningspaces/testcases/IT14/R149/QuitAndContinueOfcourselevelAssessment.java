package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R149;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectAnswerAndSubmit;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class QuitAndContinueOfcourselevelAssessment extends Driver
{
	@Test(enabled = false)
	public void quitAndContinueOfcourselevelAssessment()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("17");
			new Navigator().NavigateTo("eTextBook");
			//17
			new Click().clickByclassname("assessment_media__body");//start assessment
			for(int i=0;i<=5;i++)
			{
				new SelectAnswerAndSubmit().staticanswersubmit("A");
			}
			String questionnumber=new TextFetch().textfetchbyclass("al-diag-test-question-label");
			System.out.println(questionnumber);
			//18
			new Click().clickByclassname("al-quit-diag-test-icon");//quit the assessment
			new Navigator().NavigateTo("eTextBook");
			new Click().clickByclassname("assessment_media__body");
			//19
			String questionnumber1=new TextFetch().textfetchbyclass("al-diag-test-question-label");
			if(!questionnumber.equals(questionnumber1))
				Assert.fail("the question number not same where assessment quit after reopen the assessment");//verify question number is equal or not			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase QuitAndContinueOfcourselevelAssessment in method quitAndContinueOfcourselevelAssessment ",e);
		}
	}
}
