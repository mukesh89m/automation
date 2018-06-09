package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R51;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class EngagementReport extends Driver
{
	@Test
	public void engagementReport()
	{
		try
		{
			//attempt daigonestic test
			for(int i=2; i<=3; i++)
			{
				new LoginUsingLTI().ltiLogin("1_"+String.valueOf(i));//Login as student
				new Navigator().NavigateTo("Learning Content");
				new DiagnosticTest().startTest(3);
				new DiagnosticTest().DiagonesticTestQuitBetween(4, 10, "correct", false, false, false);
			}
			new LoginUsingLTI().ltiLogin("1");
			//2-3--login as instructor
			new LoginUsingLTI().ltiLogin("1_1");//same user id but in different class section
			new Navigator().NavigateTo("Engagement Report");//4-navigate to 
			
			//5-7--check data
			boolean assigntask=new BooleanValue().booleanbyclass("assigned-heading-text");
			if(assigntask==false)
			{
				Assert.fail("assign task not shown");
			}
			
			boolean nonassigntask=new BooleanValue().booleanbyclass("non-assigned-heading-text");
			if(nonassigntask==false)
			{
				Assert.fail("non assign task not shown");
			}
			int dataofstudent=driver.findElements(By.className("students-report-name-title")).size();
			if(dataofstudent==0)
			{
				Assert.fail("student dta not avilable");
			}
			//-8--change class section name
			new ComboBox().selectValue(0, Config.context_title);
			Thread.sleep(2000);
			new Navigator().NavigateTo("Engagement Report");//8-navigate to engagement report
			String headerofpage=new TextFetch().textfetchbyclass("student-engagement-report-text");//9--header of page
			if(!headerofpage.contains("Student Engagement Report"))
			{
				Assert.fail("page header is not Student Engagement Report");
			}
			//13--
			String coursecompletionsummary=new TextFetch().textfetchbyclass("course-completion-summary-text");//11--summary
			if(!coursecompletionsummary.contains("Course Completion Summary"))
			{
				Assert.fail("Course Completion Summary is not shown");
			}
			boolean quizzprogress=new BooleanValue().booleanbyclass("assigned-quizzes-progress");
			if(quizzprogress==false)
			{
				Assert.fail("quiz progress not shown");
			}
			boolean readingprogress=new BooleanValue().booleanbyclass("assigned-reading-progress");
			if(readingprogress==false)
			{
				Assert.fail("reading progress not shown");
			}
			boolean achivmentsummary=new BooleanValue().booleanbyclass("student-achievement-summary");
			if(achivmentsummary==false)
			{
				Assert.fail("achivment summary not shown");
			}
			//15
			boolean performancebystudent=new BooleanValue().booleanbyclass("performance-by-students-text");
			if(performancebystudent==false)
			{
				Assert.fail("performance by student not shown");
			}
			//16
			boolean email=new BooleanValue().booleanbyclass("student-performance-report-email-section");
			if(email==false)
			{
				Assert.fail("email not shown");
			}
			
			boolean Exporttocsv=new BooleanValue().booleanbycssselector("img[title='Export to CSV']");
			if(Exporttocsv==false)
			{
				Assert.fail("Exporttocsv not shown");
			}
			//17--details of table
			String tabledetails=new TextFetch().textfetchbyclass("students-performance-report-content-header");
			if(!tabledetails.contains("Name"))
			{
				Assert.fail("Name column not found");
			}
			if(!tabledetails.contains("Items you have assigned"))
			{
				Assert.fail("'Items you have assigned' column not found");
			}
			if(!tabledetails.contains("Not Started"))
			{
				Assert.fail("Not Started column not found");
			}
			if(!tabledetails.contains("In Progress"))
			{
				Assert.fail("In Progress column not found");
			}
			if(!tabledetails.contains("Completed"))
			{
				Assert.fail("Completed column not found");
			}
			if(!tabledetails.contains("Study & Practice Completion"))
			{
				Assert.fail("'Study & Practice Completion' column not found");
			}
			if(!tabledetails.contains("Reading"))
			{
				Assert.fail("Reading column not found");
			}
			if(!tabledetails.contains("Assessments"))
			{
				Assert.fail("Assessments column not found");
			}
			if(!tabledetails.contains("Attempted"))
			{
				Assert.fail("Attempted column not found");
			}
			if(!tabledetails.contains("Total Grade"))
			{
				Assert.fail("'Total Grade' column not found");
			}
			//18---student count
			int studentcount=driver.findElements(By.className("students-performance-checkbox")).size();
			if(studentcount<1)
			{
				Assert.fail("Student row not shown");
			}
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in test case EngagementReport in method engagementReport",e);
		}
	}
}
