package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R149;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class CourseLevelAssessmentOtherthanStaticnotshownTostudent extends Driver
{
	@Test(enabled = false)
	public void courseLevelAssessmentOtherthanStaticnotshownTostudent()
	{
		try
		{
			new Assignment().create(62);//create one course level diagonestic assessment
			new LoginUsingLTI().ltiLogin("62");
			new Navigator().NavigateTo("eTextBook");
			int numberofcourseassessment=driver.findElements(By.className("assessment_media__body")).size();
			new Assignment().create(621);//create one course level static assessment
			new LoginUsingLTI().ltiLogin("62");
			new Navigator().NavigateTo("eTextBook");
			//62,63
			int numberofcourseassessment1=driver.findElements(By.className("assessment_media__body")).size();
			if(numberofcourseassessment!=(numberofcourseassessment1-1))
				Assert.fail("After create a static level course assessment its not shown in study plan to student");
				
		}
		catch (Exception e) 
		{
			Assert.fail("Exception in testcase CourseLevelAssessmentOtherthanStaticnotshownTostudent in testmethod courseLevelAssessmentOtherthanStaticnotshownTostudent",e);
		}
	}
}
