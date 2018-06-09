package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R15;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentsDetails;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class DetailsOfAssignments extends Driver
{
	@Test(priority=1,enabled=true)
	public void detailsofassignments()
	{
		try
		{
			new Assignment().create(2352);//Assignment create
			new LoginUsingLTI().ltiLogin("23521");//student create
			new LoginUsingLTI().ltiLogin("2352");//login as instructor
			new Assignment().assignToStudent(2352);//assign assignment to student
			new LoginUsingLTI().ltiLogin("23521");//login as student
			new Navigator().NavigateTo("Assignments");//Navigate to assignment page
			new AssignmentsDetails().assignmentsdetails();//Verify all assignment text		
		}
		catch(Exception e)
		{
			Assert.fail("Exception in Testcase detailsofassignments in class DetailsOfAssignments.",e);
		}
	}	
	@Test(priority=2,enabled=true,dependsOnMethods={"detailsofassignments"})
	public void assignmentbookmark()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("23521");//login as student
			new Navigator().NavigateTo("Assignments");//navigate to assignments page
			new AssignmentsDetails().bookMarks();//verify bookmark icon
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in Testcase assignmentbookmark in class DetailsOfAssignments.",e);
		}		
	}
	@Test(priority=3,enabled=true)
	public void nameOfAssignment()
	{
		try
		{
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "2352");
			new LoginUsingLTI().ltiLogin("23521");//login as student
            new Assignment().submitAssignmentAsStudent(2352);
            new Navigator().NavigateTo("Assignments");
			new Assignment().clickonAssignment(assessmentname);
            Thread.sleep(3000);
            String performancetext = driver.findElement(By.className("report-chart-title")).getText();//fetch text from performance page
            if(!performancetext.trim().equals("Performance Summary"))
                Assert.fail("performance summary page is not shown after submitting the assignment.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in Testcase nameOfAssignment in class DetailsOfAssignments.",e);
		}
	}
	@Test(priority = 4, enabled = true)
	public void gradeofassignment()
	{
		try
		{
            String additionalnote = ReadTestData.readDataByTagName("","additionalnote", "2371");
            String gradingpolicy = ReadTestData.readDataByTagName("","gradingpolicy", "2371");
			new Assignment().create(2371);
			new Assignment().create(2372);
			new LoginUsingLTI().ltiLogin("23711");
			new LoginUsingLTI().ltiLogin("2371");
			new Assignment().assignToStudent(2371);
			new LoginUsingLTI().ltiLogin("2372");
			new Assignment().assignToStudent(2372);
			new LoginUsingLTI().ltiLogin("23711");
			new Navigator().NavigateTo("Assignments");
			String assignmentsname=ReadTestData.readDataByTagName("", "assessmentname", "2371");			
			
			new AssignmentsDetails().gradedAssignments();
            String desc = driver.findElement(By.id("idb-additional-note-section")).getText();
            if(!desc.contains(additionalnote))
                Assert.fail("Description of assignment is not as provided by the instructor.");

			new Navigator().NavigateTo("Assignments");
			String assignmentsname1=ReadTestData.readDataByTagName("", "assessmentname", "2372");
            String policy = driver.findElement(By.className("ls-assignment-grading-desc")).getText();
            if(!policy.contains(gradingpolicy))
                Assert.fail("Description of assignment is not as provided by the instructor.");

			new Assignment().clickonAssignment(assignmentsname);
			Thread.sleep(6000);
			if(!driver.findElement(By.className("current-question-index")).getText().contains("(1 of"))
				Assert.fail("The first question is not displayed after opening the gradeable assessment");
			new Navigator().NavigateTo("Assignments");
			new Assignment().clickonAssignment(assignmentsname1);
            if(!driver.findElement(By.className("current-question-index")).getText().contains("(1 of"))
				Assert.fail("The first question is not displayed after opening the non gradeable assessment"); 
			new Navigator().NavigateTo("Assignments");
			String statuscount = driver.findElement(By.id("ls-in-progress-assignment")).getText();
			statuscount = statuscount.replaceAll("\\n", " ");
			if(!statuscount.equals("2 In Progress")) Assert.fail("Status box not showing count as 2 In Progress even after both the assessments are In Progress");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in Testcase gradeofassignment in class DetailsOfAssignments.",e);
		}
		
		
	}


}
