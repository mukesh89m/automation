package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R149;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class NavigationFromCourseLevelAssessmentToOtherAssessemnt  extends Driver
{
	@Test(priority=1,enabled=false)
	public void navigationFromCourseLevelAssessmentToOtherAssessemnt()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("48");
			new Navigator().NavigateTo("eTextBook");
			//48
			new Click().clickByclassname("assessment_media__body");//open course level assessment
			new TOCShow().chaptertree();
			new TopicOpen().chapterOpen(0);
			new DiagnosticTest().startTest(2);//open chapter level diagonestic test
			//49
			new Click().clickBycssselector("div[class='reload-assessment-notification-option assessment-notification-option-yes']");//click on yes link
			new TOCShow().chaptertree();
			new Click().clickByclassname("assessment_media__body");//open course level assessment
			new Click().clickBycssselector("div[class='reload-assessment-notification-option assessment-notification-option-yes']");//click on yes link
		}
		catch (Exception e) 
		{
			Assert.fail("Exception in testcase CustomeAssignmentCourseProficency in testmethod GradblecustomeAssignmentCourseProficency",e);
		}
	}
	@Test(priority=2,enabled=false)
	public void instructorOpenCourseLevelAssessment()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("56");
			new Navigator().NavigateTo("eTextBook");
			//56,57
			new Click().clickByclassname("assessment_media__body");//open course level assessment
			boolean questionreview=new BooleanValue().booleanbyclass("cms-content-question-review-wrapper");
			if(questionreview==false)
				Assert.fail("after click by instructor on course assessment its not goes to question review page");//verify question reviw page
		}
		catch (Exception e) 
		{
			Assert.fail("Exception in testcase CustomeAssignmentCourseProficency in testmethod instructorOpenCourseLevelAssessment",e);
		}
	}
}
