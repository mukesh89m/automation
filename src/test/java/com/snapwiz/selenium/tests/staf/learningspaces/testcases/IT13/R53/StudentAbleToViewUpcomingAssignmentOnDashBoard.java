package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R53;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class StudentAbleToViewUpcomingAssignmentOnDashBoard extends Driver
{
	@Test(priority=1,enabled=true)
	public void studentAbleToViewUpcomingAssignmentOnDashBoard()
	{
		try
		{
			String date1=ReadTestData.readDataByTagName("", "duedate", "1");
			String date2=ReadTestData.readDataByTagName("", "duedate", "11");
			String date3=ReadTestData.readDataByTagName("", "duedate", "12");
			String date4=ReadTestData.readDataByTagName("", "duedate", "13");
			new LoginUsingLTI().ltiLogin("1");//login as student
			//create assignment--3
			new Assignment().create(1);
			new Assignment().create(11);
			new Assignment().create(12);
			new Assignment().create(13);
			new Assignment().create(15);
			new LoginUsingLTI().ltiLogin("14");//login as instructor
			new Assignment().assignToStudent(1);//assign assignment to student
			new LoginUsingLTI().ltiLogin("14");//login as instructor
			new Assignment().assignToStudent(11);//assign assignment to student
			new LoginUsingLTI().ltiLogin("14");//login as instructor
			new Assignment().assignToStudent(12);//assign assignment to student
			new LoginUsingLTI().ltiLogin("14");//login as instructor
			new Assignment().assignToStudent(13);//assign assignment to student
			new LoginUsingLTI().ltiLogin("14");//login as instructor
			new Assignment().assignToStudent(15);//assign assignment to student
			new LoginUsingLTI().ltiLogin("1");//login as student
			//--verify number of upcoming assignment shown
			int countofupcomingassignemnt=driver.findElements(By.className("ls-dashboard-upcoming-assignment")).size();
			if(countofupcomingassignemnt!=4)
			{
				Assert.fail("on dashboard there are more than 4 upcoming assignment shown");
			}
			//24,25--verify due date
			String [] date=new String[4];
			int i=0;
			List<WebElement> duedate=driver.findElements(By.className("upcoming-assignment-timestamp"));
			for(WebElement alldate:duedate)
			{
				date[i]=alldate.getText();
				i++;
			}
			if(!date[0].contains(date1))
			{
				Assert.fail("date1 not at top.");
			}
			if(!date[1].contains(date2))
			{
				Assert.fail("date2 not at 2nd.");
			}
			if(!date[2].contains(date3))
			{
				Assert.fail("date3 not at 3rd.");
			}
			if(!date[3].contains(date4))
			{
				Assert.fail("date4 not at 4th");
			}
			//13-15---assignment icon
			int assignmenticon=driver.findElements(By.cssSelector("i[class='ls-assignment-icon ls-upcoming-sprite']")).size();
			if(assignmenticon!=4)
			{
				Assert.fail("for all assignment icon not shown ");
			}
			String nameofassignment=new TextFetch().textfetchbylistclass("ls-dashboard-assignment-type", 0);
			if(!nameofassignment.contains("shor"))
			{
				Assert.fail("name display is not short name");
			}
			//20-23---verify status
			List<WebElement> status=driver.findElements(By.className("ls-right-assignment-status-icon"));
			String stastus1=status.get(0).getAttribute("title");
			if(!stastus1.contains("Not Started"))
			{
				Assert.fail("status1 not ' not started'");
			}
			String stastus2=status.get(1).getAttribute("title");
			if(!stastus2.contains("Not Started"))
			{
				Assert.fail("status2 not ' not started'");
			}
			String stastus3=status.get(2).getAttribute("title");
			if(!stastus3.contains("Not Started"))
			{
				Assert.fail("status3 not ' not started'");
			}
			String stastus4=status.get(3).getAttribute("title");
			if(!stastus4.contains("Not Started"))
			{
				Assert.fail("status4 not ' not started'");
			}
			/*boolean videoshown=new BooleanValue().booleanbyid("wrapper");
			if(videoshown==false)
			{
				Assert.fail("video area not shown");
			}*/
			//26-28--go to assignment page
			new Click().clickByclassname("ls-upcoming-text");//clcik on upcoming item--26,27
			boolean assignmentpage=new BooleanValue().booleanbyclass("ls-assignment-date-block");
			if(assignmentpage==false)
			{
				Assert.fail("after clcik on upcoming link its not goes to assignment page");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testclass StudentAbleToViewUpcomingAssignmentOnDashBoard in method studentAbleToViewUpcomingAssignmentOnDashBoard",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void DashBoardUpdateWithCompletingTheAssignment()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("8");
			new Assignment().create(8);
			new Assignment().create(81);
			new LoginUsingLTI().ltiLogin("81");
			new Assignment().assignToStudent(8);
			new LoginUsingLTI().ltiLogin("81");
			new Assignment().assignToStudent(81);
			new LoginUsingLTI().ltiLogin("8");
			int countofassignment=driver.findElements(By.className("ls-dashboard-upcoming-assignment")).size();//count of assignment t dashboard
			new StaticAssignmentSubmit().staticAssignmentSubmit(0);
			new LoginUsingLTI().ltiLogin("8");
			Thread.sleep(10000);
			int countofassignment1=driver.findElements(By.className("ls-dashboard-upcoming-assignment")).size();//count of assignment t dashboard after submitting one assignment
			if(countofassignment1>countofassignment)
			{
				Assert.fail("after attempt the assignemt its not remove from dashboard");
			}

			new StaticAssignmentSubmit().clickonassignment(0);
            new LoginUsingLTI().ltiLogin("8");
            Thread.sleep(10000);
			String status=driver.findElements(By.className("ls-right-assignment-status-icon")).get(0).getAttribute("title");
            System.out.println("status "+status);
			//10,23
			if(!status.contains("In Progress"))
			{
				Assert.fail("assignmet not change to 'In Progress'after open the assignement ");
			}
			//11,29-32
			new StaticAssignmentSubmit().staticAssignmentSubmit(0);
            new LoginUsingLTI().ltiLogin("8");
			int countofassignment2=driver.findElements(By.className("ls-dashboard-upcoming-assignment")).size();//count of assignment t dashboard after submitting one assignment
			if(countofassignment2!=0)
			{
				Assert.fail("count is not zero after all assignemnt is submitted");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testclass StudentAbleToViewUpcomingAssignmentOnDashBoard in method DashBoardUpdateWithCompletingTheAssignment",e);
		}
	}
	@Test(priority=3,enabled=true)
	public void ImpactOfAssignmentOverSnapshot()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("33");//login as student
			new Assignment().create(33);//create assignment
			new Assignment().create(331);//create assignment
			//37
			new LoginUsingLTI().ltiLogin("331");//login as instructor
			new Assignment().assignToStudent(33);
			new LoginUsingLTI().ltiLogin("331");//login as instructor
			new Assignment().assignToStudent(331);
			new LoginUsingLTI().ltiLogin("33");
			//43,44--sumbit assignment
			new StaticAssignmentSubmit().staticAssignmentSubmit(0);
			new LoginUsingLTI().ltiLogin("331");//login as instructor
			new Assignment().provideGRadeToStudent(33);//grade to student
            new Assignment().releaseGrades(33,"Release Grade for All");
			Thread.sleep(2000);
            new RunScheduledJobs().runScheduledJobs();
            Thread.sleep(5000);
            new RunScheduledJobs().runScheduledJobsForDashboard();
            Thread.sleep(900000); //waiting for 15 minutes for the dashboard graded assignment data to be populated
            new LoginUsingLTI().ltiLogin("33");
            Thread.sleep(10000);
			//45--verify tiles name
			String textoftile=new TextFetch().textfetchbyclass("grade-book");
			if(!textoftile.contains("Overall Score"))
			{
				Assert.fail("Overall Score not shown under Graded Assignment tile on dashboard");
			}
			if(!textoftile.contains("Recently Graded"))
			{
				Assert.fail("Recently Graded not shown on tile");
			}
			if(!textoftile.contains("Upcoming"))
			{
				Assert.fail("Upcoming not shown on tile");
			}
			//--46---verify graph present or not
			boolean circulergraph=new BooleanValue().booleanbylistcssselector("g[class='highcharts-series highcharts-tracker']", 0);
			if(circulergraph==false)
			{
				Assert.fail("circuler graph not not");
			}
			//--47
			boolean gradegraph=new BooleanValue().booleanbylistcssselector("g[class='highcharts-series highcharts-tracker']", 2);
			if(gradegraph==false)
			{
				Assert.fail("gradegraph graph not not");
			}
			//48-50
			boolean upcomingassignment=new BooleanValue().booleanbyclass("ls-dashboard-upcoming-assignment");
			if(upcomingassignment==false)
			{
				Assert.fail("upcoming assignment  not not");
			}
			//51
			new StaticAssignmentSubmit().staticAssignmentSubmit(0);//submitting 2nd assignment
            new LoginUsingLTI().ltiLogin("33");
			//53
			boolean circulergraph1=new BooleanValue().booleanbylistcssselector("g[class='highcharts-series highcharts-tracker']", 0);
			if(circulergraph1==false)
			{
				Assert.fail("circulergraph1  not not");
			}
			//54
			boolean gradegraph1=new BooleanValue().booleanbylistcssselector("g[class='highcharts-series highcharts-tracker']", 2);
			if(gradegraph1==false)
			{
				Assert.fail("gradegraph1  not not");
			}
			//56-59--check upcoming assignment
			int countofassignment=driver.findElements(By.className("ls-dashboard-upcoming-assignment")).size();//count of assignment t dashboard after submitting one assignment
			if(countofassignment!=0)
			{
				Assert.fail("count is not zero after all assignemnt is submitted");
			}
			//61
			driver.quit();
			startDriver("firefox");
			new LoginUsingLTI().ltiLogin("33");//login as student
			int countofassignment1=driver.findElements(By.className("ls-dashboard-upcoming-assignment")).size();//count of assignment t dashboard after submitting one assignment
			if(countofassignment1!=0)
			{
				Assert.fail("dashboard not updated");
			}
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testclass StudentAbleToViewUpcomingAssignmentOnDashBoard in method ImpactOfAssignmentOverSnapshot",e);
		}
	}
}
