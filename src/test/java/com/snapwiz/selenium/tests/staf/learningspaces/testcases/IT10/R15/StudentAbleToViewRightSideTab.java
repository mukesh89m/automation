package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R15;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentsDetails;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class StudentAbleToViewRightSideTab extends Driver
{
	@Test(priority=1,enabled=true)
	public void studentableToviewtightsidetab()
	{
		try
		{
			new Assignment().create(2478);//Assignment create
			new LoginUsingLTI().ltiLogin("24781");//student create
			new LoginUsingLTI().ltiLogin("2478");//login as instructor
			new Assignment().assignToStudent(2478);//assign assignment to student
			new LoginUsingLTI().ltiLogin("24781");//login as student
			new Navigator().NavigateTo("Assignments");//Navigate to assignment page
			driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();
			int helppage = driver.findElements(By.className("close-help-page")).size();
		     if(helppage == 1)
		    	 driver.findElement(By.className("close-help-page")).click();
		     Thread.sleep(5000);
			new AssignmentsDetails().assignmentrightsidetabs("2478");	//call assignmentrightsidetab method		
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase studentableToviewtightsidetab in class StudentAbleToViewRightSideTab.",e);
			
		}
	}

}
