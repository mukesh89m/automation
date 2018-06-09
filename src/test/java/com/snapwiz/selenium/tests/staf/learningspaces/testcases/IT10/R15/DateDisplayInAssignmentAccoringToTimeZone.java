package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R15;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class DateDisplayInAssignmentAccoringToTimeZone extends Driver
{
	@Test(priority=1,enabled=true)
	public void datadisplayinassignmentacccoringtotimezone()
	{
		try
		{		
			new Assignment().create(2203);//Assignment create
			new LoginUsingLTI().ltiLogin("22031");//student create
			new LoginUsingLTI().ltiLogin("2203");//login as instructor
			new Assignment().assignToStudent(2203);
			new LoginUsingLTI().ltiLogin("22031");//login as student
			new Navigator().NavigateTo("Assignments");
			String datetext=driver.findElement(By.className("ls-assignement-not-submitted")).getText();
			String dateverify=ReadTestData.readDataByTagName("", "date", "2203");
			if(!datetext.trim().contains(dateverify))
				Assert.fail("date Not shown with assignment");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase datadisplayinassignmentacccoringtotimezone in class DateDisplayInAssignmentAccoringToTimeZone",e);
		}
	}

}
