package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R51;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class InstructorSeeEmptyReportIfNoData extends Driver
{
	@Test
	public void instructorSeeEmptyReportIfNoData()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("171");//Login as instructor
			new Navigator().NavigateTo("Engagement Report");//-navigate to engaged report 
			String noengaementtext=new TextFetch().textfetchbyclass("no-engagement-report-first-text");
			if(!noengaementtext.contains("Unfortunately, an Engagement Report could not be generated for you at this time since there is not enough data for the system to analyze."))
			{
				Assert.fail("no text shown when there is no student in class section");
			}
			new LoginUsingLTI().ltiLogin("1711");//Login as student
			new LoginUsingLTI().ltiLogin("171");//Login as instructor
			new Navigator().NavigateTo("Engagement Report");//-navigate to engaged report 
			String assignquized=new TextFetch().textfetchbyclass("assigned-quizzes-progress");
			if(!assignquized.contains("Graph will appear after your students work on assigned quizzes."))
			{
				Assert.fail("no text shown in first graph");
			}
			String assignreading=new TextFetch().textfetchbyclass("assigned-reading-progress");
			if(!assignreading.contains("Graph will appear after your students work on reading assignments."))
			{
				Assert.fail("no text shown in 2nd graph");
			}
			String achivmentsummary=new TextFetch().textfetchbyclass("student-achievement-summary");
			if(!achivmentsummary.contains("Once your students practice few questions, the graph will appear."))
			{
				Assert.fail("no text shown in 3rd graph");
			}
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in terstcase InstructorSeeEmptyReportIfNoData in method instructorSeeEmptyReportIfNoData",e);
		}
		
	}
}
