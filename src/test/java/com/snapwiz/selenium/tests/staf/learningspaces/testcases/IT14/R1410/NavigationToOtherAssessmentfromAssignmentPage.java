package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R1410;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class NavigationToOtherAssessmentfromAssignmentPage extends Driver
{
	@Test
	public void navigationToOtherAssessmentfromAssignmentPage()
	{
		try
		{
			new Assignment().create(76);
			new LoginUsingLTI().ltiLogin("76_1");
			new LoginUsingLTI().ltiLogin("76");//login as instructor
			new Assignment().assignToStudent(76);//login as student
			new LoginUsingLTI().ltiLogin("76_1");
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().chapterOpen(0);						
			new Click().clickbylist("inner-assignment-block", 0);//click on assignment from study plan
			//new Navigator().NavigateTo("eTextBook");
			new TOCShow().chaptertree();//click on study plan
			new TopicOpen().chapterOpen(0);	
			//77
			new SelectCourse().selectInvisibleAssignment("1.1 Concept Check");//open static assignment
			new Click().clickBycssselector("div[class='reload-assessment-notification-option assessment-notification-option-yes']");//78--click on yes
			boolean assessmentopen=new BooleanValue().booleanbyclass("static-assessment-question-summery");//verify assessment open after click on yes or not
			if(assessmentopen==false)
				Assert.fail("after click on yes static assessment not open");
			//new Navigator().NavigateTo("eTextBook");
			//79
			new TOCShow().chaptertree();
			new TopicOpen().chapterOpen(0);
			new Click().clickbylist("inner-assignment-block", 0);//click on assignment from study plan
			//80
			new Click().clickBycssselector("div[class='reload-assessment-notification-option assessment-notification-option-no']");//click on no  for assignemnt open pop-up
			boolean assessmentopen1=new BooleanValue().booleanbyclass("static-assessment-question-summery");
			if(assessmentopen1==false)
				Assert.fail("after click on no static assessment not open");
			//new Navigator().NavigateTo("eTextBook");
			new TOCShow().chaptertree();
			new TopicOpen().chapterOpen(0);						
			new Click().clickbylist("inner-assignment-block", 0);//click on assignment from study plan
			//81-82
			new Click().clickBycssselector("div[class='reload-assessment-notification-option assessment-notification-option-yes']");//click on yes for assignemnt open pop-up
			boolean assignmentopen=new BooleanValue().booleanbycssselector("div[class='question__details ls-assignment-section-wrapper']");
			if(assignmentopen==false)
				Assert.fail("after click on yes of assignment pop-up assignment not open");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase NavigationToOtherAssessmentfromAssignmentPage in test method navigationToOtherAssessmentfromAssignmentPage",e);
		}
	}


}
