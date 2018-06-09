package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R50;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentPolicy;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectAnswerAndSubmit;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class ShowAnswerOption extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void showAnswerOption()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "295");
			new Assignment().create(295);
            new Assignment().addQuestions(295,"multiplechoice","");
			new LoginUsingLTI().ltiLogin("295");//login as instructor
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "295 policy desc", "", null, true, "3", "1", "", "", "", "1");
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    new Assignment().assignToStudent(295);

		    new LoginUsingLTI().ltiLogin("295_1");//login as student
            new Assignment().openAssignmentFromCourseStream("295");
		    new SelectAnswerAndSubmit().staticAssignment("B");//select wrong answer and click submit

		    //TC row no 295
		    driver.findElement(By.cssSelector("div[class='true-false-student-tick true-false-student-right']")).click(); //clicking on green tick in question review page
            driver.findElement(By.cssSelector("div[class='true-false-student-tick true-false-student-wrong']")).click(); //clicking on red tick in question review page

            String reattemptLink = new TextFetch().textfetchbyclass("re-attempt-link");
            //TC row no 296
            if(!reattemptLink.contains("Reattempt"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("For assignmnets which has Number of attempts more than one for them 're-attempt' link is not there in the notification.");
            }

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase showAnswerOption in class ShowAnswerOption.",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void showAnswer()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "297");
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "297");
			new Assignment().create(297);
			new Assignment().addQuestionsWithCustomizedQuestionText(297,"qtn-type-true-false-img", assignmentname, 2);
			new LoginUsingLTI().ltiLogin("297");//login as instructor
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "297 policy desc", "", null, true, "3", "1", "", "", "", "1");
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(297);
		    new LoginUsingLTI().ltiLogin("297_1");//login as student
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
		    new SelectAnswerAndSubmit().staticAssignmentWithShowAnswer("B");//select wrong answer and click submit
		    //TC row no 297
		    driver.findElement(By.cssSelector("div[class='true-false-student-tick true-false-student-right']")).click(); //clicking on the green tick in front of the answer option to verify its presence
			//TC row no 298
		    String notice = new TextFetch().textfetchbyid("assignment-policy-user-answer-status-div");
			if(!notice.contains("Reattempt"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For assignmnets which has Number of attempts more than one for them 're-attempt' link is not there in the notification.");
			}
		    new Click().clickByclassname("next-or-submit-link");	//attempt next question
		    new SelectAnswerAndSubmit().staticAssignmentWithShowAnswer("A");//select correct answer and click submit
		    new Click().clickByclassname("next-or-submit-link");	//attempt next question
		    driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).click();//click on final submit button
		    Thread.sleep(2000);
		   //TC row no 300
            driver.findElement(By.cssSelector("div[class='true-false-student-tick true-false-student-right']")).click(); //clicking on the green tick in front of the answer option to verify its presence
		    new Click().clickByclassname("next-or-submit-link"); //click on Submit link on pop up
			//TC row no 301
			String report = new TextFetch().textfetchbyclass("report-chart-title");
			if(!report.contains("Performance Summary"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On Submit of question for last time based on number of attempt the \"Reattempt\" link is available.");
			}

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase showAnswer in class ShowAnswerOption.",e);
		}
	}
	@Test(priority = 3, enabled = true)
	public void showAnswerSetToNO()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "304");
			new Assignment().create(304);
			new LoginUsingLTI().ltiLogin("304");//login as instructor
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "304 policy desc", "", null, true, "1", "", "", "", "", "");
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    new Assignment().assignToStudent(304);
		    new LoginUsingLTI().ltiLogin("304_1");//login as student
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
		    new SelectAnswerAndSubmit().staticAssignment("B");//select wrong answer and click submit
		    //TC row no 304
		    int correctAnswer = driver.findElements(By.className("user-response-data")).size();
		    if(correctAnswer != 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The student navigates to the review page with the correct answer if we set the Show Answer to NO in assignment policy.");
		    }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase showAnswerSetToNO in class ShowAnswerOption.",e);
		}
	}
	@Test(priority = 4, enabled = true)
	public void showAnswerWithImmediateFeedbackDisabled()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "305");
			new Assignment().create(305);
			new LoginUsingLTI().ltiLogin("305");//login as instructor
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "305 policy desc", "", null, false, "1", "", "", "", "", "");
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    new Assignment().assignToStudent(305);
		    new LoginUsingLTI().ltiLogin("305_1");//login as student
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
		    new SelectAnswerAndSubmit().staticAssignment("B");//select wrong answer and click submit
		    //TC row no 305
		    int correctAnswer = driver.findElements(By.className("user-response-data")).size();
		    if(correctAnswer != 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The student navigates to the review page with the correct answer if we disable the immediate feedback in assignment policy.");
		    }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase showAnswerWithImmediateFeedbackDisabled in class ShowAnswerOption.",e);
		}
	}
}
