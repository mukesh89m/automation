package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R149;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PerformanceProficencyPercent;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.StaticAssignmentSubmit;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;

public class GradableAssignmentCourseProficency extends Driver
{
	@Test
	public void gradableAssignmentCourseProficency()
	{
		try
		{
			String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", "41");
			new Assignment().create(41);
			new Assignment().OpenAssignment(assignmentname, 41);
			for(int i=1;i<=10;i++)
			{
				new Assignment().duplicatQuestion();//create static test
			}
			new LoginUsingLTI().ltiLogin("41_1");//create student
			new LoginUsingLTI().ltiLogin("41");//login as instructor
			new Assignment().assignToStudent(41);
			new LoginUsingLTI().ltiLogin("41_1");//login as  student
			new StaticAssignmentSubmit().clickonassignment(0);//open gradable assignment
			new StaticAssignmentSubmit().staticAssignement();
			new LoginUsingLTI().ltiLogin("41_1");//login as  student
			new Navigator().NavigateTo("Proficiency Report");
			boolean noDataNotification=new BooleanValue().booleanbyclass("no-data-notification-message-block");
			if(noDataNotification==false)
				Assert.fail("no data notification not shown even grad not release by instructor");
			new LoginUsingLTI().ltiLogin("41");
			new Assignment().provideGradeToStudentForMultipleQuestions(41);
			new Assignment().releaseGrades(41,"Release Grade for All");
			Thread.sleep(2000);
			new LoginUsingLTI().ltiLogin("41_1");//login as  student
			new Navigator().NavigateTo("Proficiency Report");
			Thread.sleep(3000);
			int proficency=new PerformanceProficencyPercent().performanceProficencyPercent("41_1");//fetch proficency report of above user from database
			String proficencytext=driver.findElement(By.id("al-cource-proficiency-summary")).getText();//fetch proficency report of user from pro
			int pageproficency=Integer.parseInt(proficencytext.substring(0, 2));//
            if(proficency!=pageproficency)
            	Assert.fail("database proficency not equal to diplayed proficency on proficency report page for diagonstic test");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase GradableAssignmentCourseProficency in testmethod gradableAssignmentCourseProficency ",e);
		}
	}

}
