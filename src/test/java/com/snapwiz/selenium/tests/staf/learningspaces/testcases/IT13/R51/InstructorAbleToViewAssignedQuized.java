package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R51;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.StaticAssignmentSubmit;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class InstructorAbleToViewAssignedQuized extends Driver
{
	@Test
	public void instructorAbleToViewAssignedQuized()
	{
		try
		{
			//assignment create
			new Assignment().create(311);
			new LoginUsingLTI().ltiLogin("311");//Login As student
			new LoginUsingLTI().ltiLogin("31");//login as instructor
			new Assignment().assignToStudent(311);//assign assignment to student
			new LoginUsingLTI().ltiLogin("31");
			new Navigator().NavigateTo("Engagement Report");//4-navigate to engaged report 
			//36,44,40
			int quizzedgraph=driver.findElements(By.cssSelector("div[class='assigned-quizzes-completed-chart no-report-grap-image']")).size();
			if(quizzedgraph<1)
			{
				Assert.fail("graph will apper even student not start the assignment");
			}
			new LoginUsingLTI().ltiLogin("311");//login as student
			new Navigator().NavigateTo("Assignments");
			new Click().clickbylistcssselector("span[class='ls-assignment-name instructor-assessment-review']", 0);
			Thread.sleep(3000);
			new LoginUsingLTI().ltiLogin("31");
			//
			new Navigator().NavigateTo("Engagement Report");//4-navigate to engaged report 
			int quizzedgraph1=driver.findElements(By.cssSelector("div[class='assigned-quizzes-completed-chart no-report-grap-image']")).size();
			if(quizzedgraph1<1)
			{
				Assert.fail("graph will apper even  assignment is in in progress condition");
			}
			new LoginUsingLTI().ltiLogin("311");//login as student
            new StaticAssignmentSubmit().staticAssignmentSubmit(0);
			new LoginUsingLTI().ltiLogin("31");
			new Navigator().NavigateTo("Engagement Report");//4-navigate to engaged report 
			String graphvalue=new TextFetch().textfetchbyclass("assigned-quizzes-progress");
			//35,41,38
			if(!graphvalue.contains("100"))
				Assert.fail("graph % not shown");

			//33
			if(!graphvalue.contains("Question Assignments Completed"))
				Assert.fail("The title of first graph in instructor engagement report is not coming as 'Question Assignments Completed'");

            if(!driver.findElement(By.className("assigned-reading-progress")).getText().contains("Graph will appear after your students work on reading assignments."))
                Assert.fail("The text 'Graph will appear after your students work on reading assignments.' not present for the second graph in instructor engagement report");

            if(!driver.findElement(By.className("achievement-summary-chart-label")).getText().contains("Question Performance Summary"))
                Assert.fail("The title of third graph in instructor engagement report is not coming as 'Question Performance Summary'");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in test case InstructorAbleToViewAssignedQuized in method instructorAbleToViewAssignedQuized",e);
		}
	}
}
