package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R15;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentStatusBoxData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;

public class CountOfBoxesChangeAsPerFilterSelected extends Driver
{
	@Test(priority=1,enabled=true)
	public void countofboxeschangeasperfilterselected()
	{
		try
		{
			new Assignment().create(2341);//create assignment1
			new Assignment().create(23411);//create assignment2
			new Assignment().create(23412);//create assignment2
			new LoginUsingLTI().ltiLogin("23413");//create student
			new LoginUsingLTI().ltiLogin("2341");//1st instructor login
			new Assignment().assignToStudent(2341);//assign 1st assignment to student
			new LoginUsingLTI().ltiLogin("23411");//2nd instructor login
			new Assignment().assignToStudent(23411);//assign 2nd assignment to student
			new LoginUsingLTI().ltiLogin("23412");//3rd intructor login
			new Assignment().assignToStudent(23412);//Assign 3rd assignment to student
			new LoginUsingLTI().ltiLogin("23413");//student login
			new Navigator().NavigateTo("Assignments");
			int numberofassignment=new AssignmentStatusBoxData().countofassignmentsinbox("Not Started");//number of assignment count in box which is not started
			if(numberofassignment!=3)
				Assert.fail("number of not started assigment not equal to -total number of assignment");
			new Assignment().submitAssignmentAsStudent(23411);//submitted 2nd  assignment
			new Navigator().NavigateTo("Assignments");
			new ComboBox().selectValue(0,"Question Assignment");
			Thread.sleep(2000);
			int numberofnotstartedgradedassignment=new AssignmentStatusBoxData().countofassignmentsinbox("Not Started");//count number of graded assignment which is in not started 
			int numberofsubmittedgraddedassignment=new AssignmentStatusBoxData().countofassignmentsinbox("Submitted");//count number of graded assignment which is in submitted state
			int totalnumberofgradedassignment=numberofnotstartedgradedassignment+numberofsubmittedgraddedassignment;
			if(totalnumberofgradedassignment!=2)
				Assert.fail("Number of graded assignment is not equal to posted graded Assignment.Not Filted as selected from drop down");			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in Testcase countofboxeschangeasperfilterselected in class countofboxeschangeasperfilterselected.",e);
		}
	}


}
