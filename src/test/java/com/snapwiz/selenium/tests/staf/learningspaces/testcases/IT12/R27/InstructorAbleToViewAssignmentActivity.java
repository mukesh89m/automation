package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R27;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class InstructorAbleToViewAssignmentActivity extends Driver
{
	@Test(priority=1,enabled=true)
	public void instructorAbleToViewAssignmentActivity()
	{
		try
		{
			CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Assignment().create(63);
			new LoginUsingLTI().ltiLogin("631");//login as student
			new LoginUsingLTI().ltiLogin("63");//login as instructor
		    new Assignment().assignToStudent(63);

			new LoginUsingLTI().ltiLogin("631");//login as student
			new Navigator().NavigateTo("Assignments");  //navigate to Assignments
			currentAssignments.getAssessmentName().click();//open lesson assignment
			Thread.sleep(10000);
			new AttemptQuestion().trueFalse(false,"incorrect",63);
			driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).click();
          //  new Assignment().submitAssignmentAsStudent(63);

			new LoginUsingLTI().ltiLogin("63");//login as instructor
			new Assignment().provideGRadeToStudent(63);
			new Click().clickBycssselector("div[title='Release Grade for All']");

			new RunScheduledJobs().runScheduledJobs();
            new RunScheduledJobs().runScheduledJobsForDashboard();
            Thread.sleep(1200000);//give sleep time of 20mins

            new LoginUsingLTI().ltiLogin("63");//login as instructor
			String headertext=new TextFetch().textfetchbyclass("assignments-title");
			if(!headertext.contains("Assignments"))
			{
				Assert.fail("Header is not assignment");
			}
			String avgperformane=new TextFetch().textfetchbylistclass("highcharts-title", 0);

			if(!avgperformane.equals("Average Performance"))
			{
				Assert.fail("Average performance is not shown");
			}
			String recgrade=new TextFetch().textfetchbylistclass("highcharts-title", 1);
			if(!recgrade.contains("Recently Graded"))
			{
				Assert.fail("Recently graded is not shown");
			}
			boolean section3=new BooleanValue().booleanbyid("break-up-assignment-section");
			if(section3==false)
			{
				Assert.fail("section 3 breakup all assignment section is not shown");
			}
			boolean barchart=new BooleanValue().booleanbylistcssselector("g[class='highcharts-series highcharts-tracker']", 0);
			if(barchart==false)
			{
				Assert.fail("barchar of recently added graded assignment is not shown");
			}
			String xaxistext=new TextFetch().textfetchbylistclass("highcharts-axis", 0);
			if(!xaxistext.contains("Assignments"))
			{
				Assert.fail("x axis text is not \"assignemnts\"");
			}
			String yaxistext=new TextFetch().textfetchbylistclass("highcharts-axis", 1);
			if(!yaxistext.contains("Score (%)"))
			{
				Assert.fail("y axis text is not \"score %\"");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase InstructorAbleToViewAssignmentActivity in method instructorAbleToViewAssignmentActivity",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void MoreThan5GaradedAssignemnt()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("805");//login as student
			new LoginUsingLTI().ltiLogin("80");//login as instructor
			new Assignment().create(80);//create assignment 1
			new Assignment().create(801);//create assignment 1	
			new Assignment().create(802);//create assignment 1	
			new Assignment().create(803);//create assignment 1	
			new Assignment().create(804);//create assignment 1
			new LoginUsingLTI().ltiLogin("80");//login as instructor
		    new Assignment().assignToStudent(80);//assignment assign to student
		    new LoginUsingLTI().ltiLogin("80");//login as instructor
		    new Assignment().assignToStudent(801);//assignment assign to student
		    new LoginUsingLTI().ltiLogin("80");//login as instructor
		    new Assignment().assignToStudent(802);//assignment assign to student
		    new LoginUsingLTI().ltiLogin("80");//login as instructor
		    new Assignment().assignToStudent(803);//assignment assign to student
		    new LoginUsingLTI().ltiLogin("80");//login as instructor
		    new Assignment().assignToStudent(804);//assignment assign to student
			new LoginUsingLTI().ltiLogin("805");//login as student
			for (int i = 1; i<=5; i++) 
			{
				new Navigator().NavigateTo("Assignments");
				new Click().clickbylistcssselector("span[class='ls-assignment-name instructor-assessment-review']", 0);//click on assignment name
				Thread.sleep(3000);
				new Click().clickBycssselector("a[class='btn btn--primary btn--large btn--submit long-text-button']");//click on submit button
			}
			new LoginUsingLTI().ltiLogin("80");//login as instructor
			new Assignment().provideGRadeToStudent(80);
			new Click().clickBycssselector("div[title='Release Grade for All']");
			new Assignment().provideGRadeToStudent(801);
			new Click().clickBycssselector("div[title='Release Grade for All']");
			new Assignment().provideGRadeToStudent(802);
			new Click().clickBycssselector("div[title='Release Grade for All']");
			new Assignment().provideGRadeToStudent(803);
			new Click().clickBycssselector("div[title='Release Grade for All']");
			new Assignment().provideGRadeToStudent(804);
			new Click().clickBycssselector("div[title='Release Grade for All']");
			new RunScheduledJobs().runScheduledJobs();
            new RunScheduledJobs().runScheduledJobsForDashboard();
            Thread.sleep(1200000);//give sleep time of 20mins
			new LoginUsingLTI().ltiLogin("80");//login as instructor
            if(Integer.parseInt(driver.findElements(By.className("ls-assignment-progress-count")).get(3).getText()) < 5 )
                Assert.fail("The Graded count of assignments on dashboard is not greater than or equal to 5 after releasing grades for 5 assignments");


		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase InstructorAbleToViewAssignmentActivity in method MoreThan5GaradedAssignemnt",e);
		}
	}


}
