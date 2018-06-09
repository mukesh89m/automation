package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R15;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentsDetails;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class StudentAbleToUseSocialElementForAssignment extends Driver
{
	@Test(priority=1,enabled=true)
	public void studentabletousesocialelementforassignment()
	{
		try
		{
			new Assignment().create(2391);//assignment creation
			new LoginUsingLTI().ltiLogin("23911");//student create
			new LoginUsingLTI().ltiLogin("2391");//login as instructor
			new Assignment().assignToStudent(2391);//assign assignment to student
			new LoginUsingLTI().ltiLogin("23911");//login as student	
			new Navigator().NavigateTo("Assignments");//Click on assignment page
			new AssignmentsDetails().countoflike(1);//count like after click on it
			new AssignmentsDetails().countofcomments(1);//count comment
			new Navigator().NavigateTo("Course Stream");//navigate to course stream
			new AssignmentsDetails().countoflike(0);//count like after click on it
			new AssignmentsDetails().countofcomments(2);//count comment
		}
		catch(Exception e)
		{
			Assert.fail("Exception in Testcase studentabletousesocialelementforassignment in class StudentAbleToUseSocialElementForAssignment.",e);
		}	
	}



}
