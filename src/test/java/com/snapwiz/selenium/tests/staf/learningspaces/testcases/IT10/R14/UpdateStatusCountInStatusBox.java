package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;

public class UpdateStatusCountInStatusBox extends Driver
{
	
	@Test(priority = 1, enabled = true)
	public void updateStatusCountInStatusBoxForGradedAssignment()
	{
		try
		{
			new Assignment().create(1762);
			new LoginUsingLTI().ltiLogin("17621");  //Creating student with ID 17621student
			new LoginUsingLTI().ltiLogin("17622");  //Creating student with ID 17622student
			new LoginUsingLTI().ltiLogin("17623");
			new LoginUsingLTI().ltiLogin("17624");
			new LoginUsingLTI().ltiLogin("1762"); //  Logging in as instructor 
			new Assignment().assignToStudent(1762);  //Assigning assignment to student 17621
			new Navigator().NavigateTo("Current Assignments");
			new Assignment().updateAssignment(17622,true); // Assigning assignment to student 2
			new Assignment().updateAssignment(17623,true);
			new Assignment().updateAssignment(17624,true);			
			new Navigator().NavigateTo("Assignments");
			new Assignment().statusBoxCheckInInstructorDashBoard(1762);

			new LoginUsingLTI().ltiLogin("17621");			
			new Assignment().submitAssignmentAsStudent(1762); //Attempt test without submit			
			new LoginUsingLTI().ltiLogin("1762");
			new Navigator().NavigateTo("Assignments");
			new Assignment().statusBoxCheckInInstructorDashBoard(17625); //3,1,0,0
			
			new LoginUsingLTI().ltiLogin("17621");
			new Assignment().submitAssignmentAsStudent(17621);
			new LoginUsingLTI().ltiLogin("1762");
			new Navigator().NavigateTo("Assignments");
			new Assignment().statusBoxCheckInInstructorDashBoard(17621);
			
			new LoginUsingLTI().ltiLogin("1762");
			new Assignment().provideGRadeToStudent(17621);
			new Navigator().NavigateTo("Assignments");
			new Assignment().statusBoxCheckInInstructorDashBoard(17623);

			//The sum of the counts of each boxes should add up to the total number of assigned students
			int b1 = new Assignment().statusBoxCount(1762, "Not Started");
			int b2 = new Assignment().statusBoxCount(1762, "In Progress");	
			int b3 = new Assignment().statusBoxCount(1762, "Submitted");
			int b4 = new Assignment().statusBoxCount(1762, "Graded");	
			int totalCount = b1+b2+b3+b4;
			if(totalCount!=4)
			{
				Assert.fail("Total count in all status box is not equal to the total assigned student number");
			}
			
			new LoginUsingLTI().ltiLogin("17622");
			new Assignment().submitAssignmentAsStudent(17622);
			new LoginUsingLTI().ltiLogin("17623");
			new Assignment().submitAssignmentAsStudent(17623);
			new LoginUsingLTI().ltiLogin("17624");
			new Assignment().submitAssignmentAsStudent(17624); 
			new LoginUsingLTI().ltiLogin("1762");
			new Navigator().NavigateTo("Assignments");
			new Assignment().statusBoxCheckInInstructorDashBoard(17624);
			
			new LoginUsingLTI().ltiLogin("1762");
			new Assignment().releaseGrades(1762, "Release Grade for All");
			
			new Assignment().statusBoxCheckInInstructorDashBoard(17624);
			
			//The sum of the counts of each boxes should add up to the total number of assigned students
			  b1 = new Assignment().statusBoxCount(1762, "Not Started");
			  b2 = new Assignment().statusBoxCount(1762, "In Progress");	
			  b3 = new Assignment().statusBoxCount(1762, "Submitted");
			  b4 = new Assignment().statusBoxCount(1762, "Graded");	
			  totalCount = b1+b2+b3+b4;
			if(totalCount!=4)
			{
				Assert.fail("Total count in all status box is not equal to the total assigned student number");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in updateStatusCountInStatusBox in class UpdateStatusCountInStatusBox",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void updateStatusCountInStatusBoxForNonGradedAssignment()
	{
		try
		{
			new Assignment().create(1770);
	
			new LoginUsingLTI().ltiLogin("17701");  //Creating student with ID 17621student
			new LoginUsingLTI().ltiLogin("17702");  //Creating student with ID 17622student
			new LoginUsingLTI().ltiLogin("17703");
			new LoginUsingLTI().ltiLogin("17704");
			new LoginUsingLTI().ltiLogin("1770"); //  Logging in as instructor
			new Assignment().assignToStudent(1770);  //Assigning assignment to student 17701
			
			new Assignment().updateAssignment(17702,true); // Assigning assignment to student 2
			new Assignment().updateAssignment(17703,true);
			new Assignment().updateAssignment(17704,true);			
			new Navigator().NavigateTo("Assignments");
			new Assignment().statusBoxCheckInInstructorDashBoard(1770);
	
			new LoginUsingLTI().ltiLogin("17701");	
			new Assignment().submitAssignmentAsStudent(1770); //submit assignment false
			new LoginUsingLTI().ltiLogin("1770");
			new Navigator().NavigateTo("Assignments");
			new Assignment().statusBoxCheckInInstructorDashBoard(17706); //3,1,0,0
			
			new LoginUsingLTI().ltiLogin("17701");			
			new Assignment().submitAssignmentAsStudent(17701);		
			new LoginUsingLTI().ltiLogin("1770");
			new Assignment().statusBoxCheckInInstructorDashBoard(17701); 
			new LoginUsingLTI().ltiLogin("1770");

			new Assignment().provideFeedback(1770);

			new Navigator().NavigateTo("Assignments");
			new Assignment().statusBoxCheckInInstructorDashBoard(17702);

			int b1 = new Assignment().statusBoxCount(1770, "Not Started");
			int b2 = new Assignment().statusBoxCount(1770, "In Progress");	
			int b3 = new Assignment().statusBoxCount(1770, "Submitted");
			int b4 = new Assignment().statusBoxCount(1770, "Reviewed");	
			int totalCount = b1+b2+b3+b4;
			if(totalCount!=4)
			{
				Assert.fail("Total count in all status box is not equal to the total assigned student number");
			} 
			
			new LoginUsingLTI().ltiLogin("17702");	
			new Assignment().submitAssignmentAsStudent(17702);

			new LoginUsingLTI().ltiLogin("17703");	
			new Assignment().submitAssignmentAsStudent(17703);

			new LoginUsingLTI().ltiLogin("17704");	
			new Assignment().submitAssignmentAsStudent(17704);

			new LoginUsingLTI().ltiLogin("1770");
			new Navigator().NavigateTo("Current Assignments");
			new Navigator().NavigateTo("Assignments");
			new Assignment().provideFeedback(17702);
			Thread.sleep(3000);

			new TabClose().tabClose(2);
			new TabClose().tabClose(1);
			new Assignment().provideFeedback(17703);

			new TabClose().tabClose(2);
			new TabClose().tabClose(1);
			new Assignment().provideFeedback(17704);

			new TabClose().tabClose(2);
			new TabClose().tabClose(1);
			//new Assignment().statusBoxCheckInInstructorDashBoard(17704);
			
			new Assignment().releaseGrades(1770, "Release Feedback for All");
			//new Assignment().statusBoxCheckInInstructorDashBoard(17704);
			
		
		}
		catch(Exception e)
		{
			Assert.fail("Exception in updateStatusCountInStatusBoxForGradedAssignmentForNonGraded in class UpdateStatusCountInStatusBox",e);
		}
	}
	

}
