package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R51;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class EnhashmentOfstudentAndInstructorDashboard extends Driver
{
	@Test(priority=1,enabled=true)
	public void enhashmentOfstudentAndInstructorDashboard()
	{
		try
		{
			new Assignment().create(1791);//create assignment
			new LoginUsingLTI().ltiLogin("1791");//login as student
			new LoginUsingLTI().ltiLogin("179");//login as instructor
			new Assignment().assignToStudent(1791);//assign assignemt ot student
			new LoginUsingLTI().ltiLogin("1791");//login as student
			new StaticAssignmentSubmit().staticAssignmentSubmit(0);//submit assignment
            new LoginUsingLTI().ltiLogin("179");//login as instructor to release feedback
            new Assignment().releaseGrades(1791,"Release Feedback for All");
            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("1791");//login as student
			String practiceboxtext=new TextFetch().textfetchbycssselector("div[class='box avg-practice-questions']");//fetch string of practice tile
			if(!practiceboxtext.contains("QUESTIONS ATTEMPTED"))
			{
				Assert.fail("header of 4th tile is not ' PRACTICE Q's ATTEMPTED ' in student dashboard ");//179
			}
			if(!practiceboxtext.contains("1"))//181
			{
				Assert.fail("total number of question not shown in 4th tile of student dashboard");
			}
			if(!practiceboxtext.contains("Top 10% of the class"))//185
			{
				Assert.fail("top 10% of the class text not shown below 4th tile of student dashboard");
			}
			new LoginUsingLTI().ltiLogin("179");//login as instructor--
			String practiceboxtextins=new TextFetch().textfetchbycssselector("div[class='box avg-practice-questions']");//fetch string of practice tile
			String [] instrcutordashboardpracticetile=practiceboxtextins.split("\n");
            System.out.println(instrcutordashboardpracticetile[0]);
			if(!instrcutordashboardpracticetile[0].contains("AVG QUESTIONS ATTEMPTED"))
			{
				Assert.fail("Header of 4th tile in student dashboard is not AVG PRACTICE ATTEMPTED");
			}
			if(!instrcutordashboardpracticetile[1].contains("1"))//186
			{
				Assert.fail("total question attempted not shown in the 4th tile of instructor dashboard");//187
			}
			if(!instrcutordashboardpracticetile[2].contains("Top 10% of the class"))//188
			{
				Assert.fail("Top 10% of the class not shown in the 4th tile of instructor dashboard");
			}
			
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in tescase EnhashmentOfstudentAndInstructorDashboard in method enhashmentOfstudentAndInstructorDashboard",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void enhashmentOfstudentAndInstructorDashboardLS()
	{
		try
		{
			new Assignment().create(1891);
			new LoginUsingLTI().ltiLogin("1891");
			new LoginUsingLTI().ltiLogin("189");
			new Assignment().assignToStudent(1891);
			new LoginUsingLTI().ltiLogin("1891");
			new StaticAssignmentSubmit().staticAssignmentSubmit(0);//submit assignment
            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("1891");
			String practiceboxtext=new TextFetch().textfetchbycssselector("div[class='box avg-practice-questions']");
			if(!practiceboxtext.contains("QUESTIONS ATTEMPTED"))//189
			{
				Assert.fail("Header of 4th tile is not 'PRACTICE Q'S ATTEMPTED' in student dashboard ");
			}
			if(!practiceboxtext.contains("1"))//191
			{
				Assert.fail("total number of question not shown");
			}
			if(!practiceboxtext.contains("Top 10% of the class"))//194
			{
				Assert.fail("top 10% of the class not shown");
			}
			new LoginUsingLTI().ltiLogin("189");
			String practiceboxtextins=new TextFetch().textfetchbycssselector("div[class='box avg-practice-questions']");
			String [] instrcutordashboardpracticetile=practiceboxtextins.split("\n");
			if(!instrcutordashboardpracticetile[0].contains("AVG QUESTIONS ATTEMPTED"))///195
			{
				Assert.fail("Header of tile is not AVG PRACTICE ATTEMPTED in 4th tile of instructor dashboard");
			}
			if(!instrcutordashboardpracticetile[1].contains("1"))//196
			{
				Assert.fail("total question attemped not shown");
			}
			if(!instrcutordashboardpracticetile[2].contains("Top 10% of the class"))//197
			{
				Assert.fail("Top 10% of the class not shown");
			}						
		}
		catch(Exception e)
		{
			Assert.fail("Exception in tescase EnhashmentOfstudentAndInstructorDashboard in method enhashmentOfstudentAndInstructorDashboardLS",e);
		}
	}
}
