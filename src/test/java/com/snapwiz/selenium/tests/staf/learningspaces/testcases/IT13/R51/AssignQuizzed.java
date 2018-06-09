package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R51;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class AssignQuizzed extends Driver
{
	@Test
	public void assignQuizzed()
	{
		try
		{
			new Assignment().create(20);//Assignment create
			new LoginUsingLTI().ltiLogin("20_1");//login as student
			new LoginUsingLTI().ltiLogin("20");//login as instructor
			new Assignment().assignToStudent(20);//assign assignment to student
			new LoginUsingLTI().ltiLogin("20");//login as instructor
            new UploadResources().uploadResources("20",true,false,true); //upload a resource
            new AssignLesson().assignResourceFromMyResources(20); // assign the uploaded resource to instructor
			new LoginUsingLTI().ltiLogin("20_1");//login a student
            new StaticAssignmentSubmit().staticAssignmentSubmit(0);
			new Navigator().NavigateTo("Assignments");
			//resources attempt
			new Click().clickByclassname("learning-activity-title");
			//20,23
			new LoginUsingLTI().ltiLogin("20");//login as instructor
			new Navigator().NavigateTo("Engagement Report");//4-navigate to
			//21,22
			boolean assignquizzed=new BooleanValue().booleanbyclass("assigned-quizzes-completed-chart");//21
			if(assignquizzed==false)
			{
				Assert.fail("after completing assignment, by student quizzed graph not shown");
			}
			//24
			boolean assignReading=new BooleanValue().booleanbyclass("assigned-reading-completed-chart");//23
			if(assignReading==false)
			{
				Assert.fail("after completing resources ,by student reading graph not shown");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in class AssignQuizzed in method assignQuizzed",e);
		}
	}

}
