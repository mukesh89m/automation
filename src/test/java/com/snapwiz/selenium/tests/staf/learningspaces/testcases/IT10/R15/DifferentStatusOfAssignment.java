package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R15;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class DifferentStatusOfAssignment extends Driver
{
	@Test(priority=1,enabled=true)
	public void diffrentstatusofassignment()
	{
		try
		{
			new Assignment().create(2211);//assignment create
			new LoginUsingLTI().ltiLogin("22111");//student create
			new LoginUsingLTI().ltiLogin("2211");//instructor login
			new Assignment().assignToStudent(2211);//assign assignment
			new LoginUsingLTI().ltiLogin("22111");//student login
			new Navigator().NavigateTo("Assignments");
			String status = driver.findElement(By.className("ls-assignment-status")).getText();
            if(!status.contains("Not Started"))
                Assert.fail("Status is not \"Not started\" after assignment is assigned.");

            new Assignment().openAssignmentFromCourseStream("2211");//open assignment
            new Click().clickByid("close-assignment");//quit the assignment
            new Navigator().NavigateTo("Assignments");
            String statusafterquit = driver.findElement(By.className("ls-assignment-status")).getText();
            if(!statusafterquit.contains("In Progress"))
                Assert.fail("Status is not \"In Progress\" after Quiting the test");

            new Assignment().submitAssignmentAsStudent(2211);
            new Navigator().NavigateTo("Assignments");
            String statusaftersubmit = driver.findElement(By.className("ls-assignment-status")).getText();
            if(!statusaftersubmit.contains("Submitted"))
                Assert.fail("Status is not \"Submitted\" after completion of the test");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase diffrentstatusofassignment in class DifferentStatusOfAssignment",e);
		}
	}

}
