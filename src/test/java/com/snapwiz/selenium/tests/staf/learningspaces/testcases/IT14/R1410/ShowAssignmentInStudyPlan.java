package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R1410;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectAnswerAndSubmit;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.StaticAssignmentSubmit;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class ShowAssignmentInStudyPlan extends Driver
{
	@Test
	public void showAssignmentInStudyPlan()
	{
		try
		{
			String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", "1");
			new Assignment().create(1);//chapter level assignment create
			for(int i=0;i<3;i++)
			{
				new Assignment().addQuestions(1, "qtn-type-true-false-img", assignmentname);
			}
			new LoginUsingLTI().ltiLogin("1_1");//login as student
			new LoginUsingLTI().ltiLogin("1");//instructor login
			new Assignment().assignToStudent(1);
			new LoginUsingLTI().ltiLogin("1_1");//login as student
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().chapterOpen(0);//open chapter1
			//5
			String tocassignment=new TextFetch().textfetchbyclass("inner-assignment-block");//fetch assignment name and due date
			if(!tocassignment.contains(assignmentname))			
				Assert.fail("assignment name not shown as  toc assignment on study plan page");
			if(!tocassignment.contains("Due Date"))
				Assert.fail("due date not shown as  toc assignment on study plan page");
			String colorofdate=driver.findElement(By.id("ls-toc-assignmnet-due-date")).getAttribute("style");//fetch colcore of due date
			if(!colorofdate.contains("color: red"))
				Assert.fail("color of due date of toc assignment is not red");
			//10,11
			new Click().clickByclassname("inner-assignment-block");//click on assignment on study plan page
			new SelectAnswerAndSubmit().staticAssignment("A");//attempt one question 
			//12
			new Click().clickByclassname("al-quit-diag-test-icon");//quit the assignment
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().chapterOpen(0);//open chapter 1
			//13,14
			String tocassignment1=new TextFetch().textfetchbyclass("inner-assignment-block");
			if(!tocassignment1.contains(assignmentname))
				Assert.fail("assignment name not shown as  toc assignment on study plan page after quit the test");
			String colorofdate1=driver.findElement(By.id("ls-toc-assignmnet-due-date")).getAttribute("style");
			if(!colorofdate1.contains("color: red"))
				Assert.fail("color of due date of toc assignment is not red after quit the test");
			new Click().clickByclassname("inner-assignment-block");
			Thread.sleep(3000);
			new StaticAssignmentSubmit().staticAssignement();//complete assignment
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().chapterOpen(0);
			boolean completeassignmenticon=new BooleanValue().booleanbycssselector("i[class='s s--book ls-toc-sprite toc-assessment-completed-icon']");//verify complete assignment icon
			if(completeassignmenticon==false)
				Assert.fail("complete assignment icon not shown along with complete assignment");
			//17
			new Click().clickByclassname("inner-assignment-block");//click on assignment on study plan page
			boolean performancepage=new BooleanValue().booleanbyclass("preformance-chart-wrapper");
			if(performancepage==false)
				Assert.fail("after clcik on assignment its not land on performance page");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase ShowAssignmentInStudyPlan in test method showAssignmentInStudyPlan",e);
		}
	}
}
