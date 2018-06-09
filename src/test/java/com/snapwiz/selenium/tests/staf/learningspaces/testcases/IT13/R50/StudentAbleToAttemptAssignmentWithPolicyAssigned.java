package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R50;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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

public class StudentAbleToAttemptAssignmentWithPolicyAssigned extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void studentAbleToAttemptAssignmentWithPolicyAssigned()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "210");
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "210");
			new Assignment().create(210);
			for(int i =0 ; i<5 ; i++)
				new Assignment().addQuestions(210, "qtn-type-true-false-img", assignmentname);
			new LoginUsingLTI().ltiLogin("210_1");	//create a student
			new LoginUsingLTI().ltiLogin("210");
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "210 Policy description", "2", null, false, "1", "", null, "", "", "");
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    new Assignment().assignToStudent(210);
		    new LoginUsingLTI().ltiLogin("210_1");//login as student
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
		    Thread.sleep(7000);
		    //String points = new TextFetch().textfetchbycssselector("div[class='static-assessment-point-content-box']");
            new UIElement().waitAndFindElement(By.className("static-assessment-point-content-box"));
            String points=new TextFetch().textfetchbyxpath("//div[@class='static-assessment-point-content-box']");
            //TC row no. 214
            System.out.println("Question Point:"+points);

		     int submitButton = driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).size();
		    if(submitButton == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Assignment with policy having immediate feedback disabled is not delivered with submit button.");
		    }
		    
		    for(int i = 0; i<2; i++)
		    	new SelectAnswerAndSubmit().staticAssignment("A");
		    //TC row no. 215
		    int noticSize = driver.findElements(By.id("assignment-policy-user-answer-status-div")).size();
		    if(noticSize != 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking submit button for Assignment with policy having immediate feedback disabled notifiaction is dispalyed." );
		    }
		     //TC row no. 210
		    String points1 = new TextFetch().textfetchbyclass("static-assessment-point-content-box");
		    if(!points.contains("2") || !points1.contains("2"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("ALL questions do not have the same score as defined.");
		    }
		    new Click().clickByid("show-question-detials");	//click on question dropdown
		    List<WebElement> allQuestion = driver.findElements(By.className("s--check-enable"));
		    //TC row no. 212
		    if(!allQuestion.get(0).getText().contains("1") || !allQuestion.get(1).getText().contains("2"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The previously attempted questions are not available and clickable.");
		    }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase studentAbleToAttemptAssignmentWithPolicyAssigned in class StudentAbleToAttemptAssignmentWithPolicyAssigned.",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void verfiyPolicyWithImmediateFeedbackEnabled()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "216");
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "216");
		    new Assignment().create(216);
			new Assignment().addQuestionsWithCustomizedQuestionText(216, "qtn-type-true-false-img", assignmentname, 2);
			new LoginUsingLTI().ltiLogin("216_1");	//create a student
			new LoginUsingLTI().ltiLogin("216");
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "216 Policy description", "2", null, true, "2", "1", null, "", "", "1");
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    new Assignment().assignToStudent(216);
		    new LoginUsingLTI().ltiLogin("216_1");
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
		    Thread.sleep(2000);
		    //TC row no 216
		    int nextButton = driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large next-or-submit-link']")).size();
		    if(nextButton != 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For assignment for which immediate feedback is enabled the question attempt page has next button.");
		    }
		    
		    new SelectAnswerAndSubmit().staticAssignment("B");	//select wrong answer and click submit
			
			String notice = new TextFetch().textfetchbyid("assignment-policy-user-answer-status-div");
			//TC row no 219
			if(notice.length() == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For assignment for which immediate feedback is enabled and Show answer is disabled then on clicking submit for wrong answer the required notification is not coming in question attempt page.");
			}
			//TC row no 220
			if(!notice.contains("Reattempt"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For assignmnets which has Number of attempts more than one for them 're-attempt' link is not there in the notification.");
			}
			//TC row no 221
			if(!notice.contains("Go to next question"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For assignment for which immediate feedback is enabled and Show answer is disabled then on clicking submit the required notification does not have 'Go to next question' link.");
			}
			
			new Click().clickByclassname("re-attempt-link");	//click on reattempt link
			//TC row no 222
			int noticSize = driver.findElements(By.id("assignment-policy-user-answer-status-div")).size();
		    if(noticSize != 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking reattempt link the pop-up doesnt closes." );
		    }
		    //TC row no. 223
		    int submitButton = driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).size();
		    if(submitButton == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking reattempt link the question attempt page doesnot appear.");
		    }
            Thread.sleep(5000);
			new SelectAnswerAndSubmit().staticAssignment("B");	//submit wrong answer for last time(unselect previous)
			
			//TC row no 223
			String notice1 = new TextFetch().textfetchbyid("assignment-policy-user-answer-status-div");
			if(notice1.contains("Reattempt"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On Submit of question for last time based on number of attempt the \"Reattempt\" link is available.");
			}
			new Click().clickByclassname("next-or-submit-link");	//attempt next question
			//TC row no 225
			String question2 = new TextFetch().textfetchbyclass("question-display-label");
			if(!question2.contains("Q 2:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Not navigated to second question");
			}
            Thread.sleep(5000);
			new SelectAnswerAndSubmit().staticAssignment("A"); //submit correct answer
			//TC row no. 228
			String notice3 = new TextFetch().textfetchbyid("assignment-policy-user-answer-status-div");
            System.out.println("Notice3:"+notice3);
            if(!notice3.contains("You got it correct."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On submiting a question correctly then \"You got it correct.\" is absent in pop up.");
			}
			//TC row no. 228
			if(notice3.contains("Reattempt"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On submiting a question correctly then \"Reattempt\" link is present in pop up.");
			}
			//TC row no. 229
			if(!notice3.contains("Go to next question"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On submiting a question correctly then \"Go to next question\" link is absent in pop up.");
			}
		
			//TC row no 231
			if(notice3.length() == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For assignment for which immediate feedback is enabled and Show answer is disabled then on clicking submit for correct answer the required notification is not coming in question attempt page.");
			}
			new Click().clickByclassname("next-or-submit-link");	//attempt next question
			new Assignment().submitButtonInQuestionClick();
			Thread.sleep(2000);
			//TC row no 226
			String notice2 = new TextFetch().textfetchbyid("assignment-policy-user-answer-status-div");
			if(!notice2.contains("Finish assignment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On Submit of last question the in the pop up no \"Submit assignment\" link is there.");
			}
			new Click().clickByclassname("next-or-submit-link"); //click on Submit link on pop up
			//TC row no 227
			String report = new TextFetch().textfetchbyclass("report-chart-title");
			if(!report.contains("Performance Summary"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On Submit of question for last time based on number of attempt the \"Reattempt\" link is available.");
			}
			/*//TC row no 217
			String str ="You got it incorrect. You got it partially correct. You got it correct. You have skipped the question.";
			if(!str.trim().contains(notice))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For assignment for which immediate feedback is enabled on clicking submit the required notification is not coming.");
			}*/
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verfiyPolicyWithImmediateFeedbackEnabled in class StudentAbleToAttemptAssignmentWithPolicyAssigned.",e);
		}
	}
	@Test(priority = 3, enabled = true)
	public void verfiyPolicyWithImmediateFeedbackEnabledShowAnswerEnabled()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "218");
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "218");
			new Assignment().create(218);
			new Assignment().addQuestionsWithCustomizedQuestionText(218, "qtn-type-true-false-img", assignmentname, 2);
			new LoginUsingLTI().ltiLogin("218_1");	//create a student
			new LoginUsingLTI().ltiLogin("218");
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "218 Policy description", "2", null, true, "2", "1", null, "", "", "");
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    new Assignment().assignToStudent(218);
		    new LoginUsingLTI().ltiLogin("218_1");
            new Assignment().openAssignmentFromAssignmentPage(218_1);
		    //select wrong answer and click submit
		    List<WebElement> answer = driver.findElements(By.className("qtn-label"));
			for (WebElement answerchoice: answer)
			{
				
				if(answerchoice.getText().trim().equals("B"))
				{
					
					answerchoice.click();
					break;
				}
			}
			driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).click();//click on submit button
			Thread.sleep(2000);
			//TC row no 218
			int notice = driver.findElements(By.id("assignment-policy-user-answer-status-div")).size();
			if(notice == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For asignment for which immediate feedbak is enabled and Show answer option is set to YES then on submitting a question incorrectly the notification is not dispalyed in review screen.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("next-practice-question-button")));
			Thread.sleep(2000);
			List<WebElement> answer1 = driver.findElements(By.className("qtn-label"));
			for (WebElement answerchoice: answer1)
			{
				
				if(answerchoice.getText().trim().equals("A"))//correct answer
				{
					
					answerchoice.click();
					break;
				}
			}
			driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).click();//click on submit button
			Thread.sleep(2000);
			//TC row no 230
			String notice2 = new TextFetch().textfetchbyid("assignment-policy-user-answer-status-div");
			if(notice2.length() == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For assignment for which immediate feedback is enabled and Show answer option is set to YES then on submitting a question correctly the notification is not dispalyed in review screen.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verfiyPolicyWithImmediateFeedbackEnabledShowAnswerEnabled in class StudentAbleToAttemptAssignmentWithPolicyAssigned.",e);
		}
	}
}
