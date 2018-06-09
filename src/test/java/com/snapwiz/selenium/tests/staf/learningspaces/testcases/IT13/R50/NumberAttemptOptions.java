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

public class NumberAttemptOptions extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void numberAttemptOptions()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "265");
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "265");
			new Assignment().create(265);
			new Assignment().addQuestionsWithCustomizedQuestionText(265, "qtn-type-true-false-img", assignmentname, 2);
			new LoginUsingLTI().ltiLogin("265_1");	//create a student
			new LoginUsingLTI().ltiLogin("265");//login as instructor
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "265 policy desc", "2", null, true, "2", "", "", "", "", "");
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    new Assignment().assignToStudent(265);
		    new LoginUsingLTI().ltiLogin("265_1");//login as student
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
		    Thread.sleep(2000);
		    new SelectAnswerAndSubmit().staticAssignmentWithShowAnswer("B");//select wrong answer and click submit
		    driver.findElement(By.className("re-attempt-link")).click();//click on reattempt
		    Thread.sleep(2000);
		    new SelectAnswerAndSubmit().staticAssignmentWithShowAnswer("B");//select wrong answer and click submit
		    new Click().clickByclassname("next-or-submit-link");	//attempt next question
		    new Click().clickByid("show-question-detials"); //click on question dropdown
		    new Click().clickByclassname("s--check-enable");	//click on question
		    //TC row no. 267
		    String notice = new TextFetch().textfetchbyid("assignment-attempt-exhausted-notification-div");
		    if(!notice.contains("You have exceeded the maximum number of attempts for this question. Please select another question from the dropdown above to proceed."))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Click on submit for a question exceeding the number of attempts then the required notification doesnt appear.");
		    }
		    
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase numberAttemptOptions in class NumberAttemptOptions.",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void numberAttemptOptionsWithImmediateFeedbackDisabled()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "266");
			new Assignment().create(266);
            new Assignment().addQuestions(266,"multiplechoice","");
            new Assignment().addQuestions(266,"multipleselection","");
            new Assignment().addQuestions(266,"writeboard","");
			new LoginUsingLTI().ltiLogin("266_1");	//create a student
			new LoginUsingLTI().ltiLogin("266");//login as instructor
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "266 policy desc", "2", null, false, "1", "", "", "", "", "");
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    new Assignment().assignToStudent(266);
		    new LoginUsingLTI().ltiLogin("266_1");//login as student
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
		    new SelectAnswerAndSubmit().staticAssignment("B");//select wrong answer and click submit
		    //TC 266
		    int reattempt = driver.findElements(By.className("re-attempt-link")).size();
		    if(reattempt != 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("If immediate feedback is disabled then also the link for reattempt is coming.");
		    }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase numberAttemptOptionsWithImmediateFeedbackDisabled in class NumberAttemptOptions.",e);
		}
	}
}
