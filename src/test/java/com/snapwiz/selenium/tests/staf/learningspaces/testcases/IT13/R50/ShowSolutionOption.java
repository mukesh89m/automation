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
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class ShowSolutionOption extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void showSolutionOption()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "288");
			String solution = ReadTestData.readDataByTagName("", "solution", "288");
			new Assignment().create(288);
			new LoginUsingLTI().ltiLogin("288_1");	//create a student
			new LoginUsingLTI().ltiLogin("288");//login as instructor
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "288 policy desc", "", null, true, "1", "1", "", "", "", "1");
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    new Assignment().assignToStudent(288);
		    new LoginUsingLTI().ltiLogin("288_1");//login as student
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
		    Thread.sleep(2000);
		    new SelectAnswerAndSubmit().staticAssignment("B");//select wrong answer and click submit
		    //TC row no . 288
		    int solutionSize = driver.findElements(By.cssSelector("img[title='Solution']")).size();
		    System.out.println("solutionSize: "+solutionSize);
		    if(solutionSize == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail(" \"Solution\" button is absent (if the question supports) after clicking submit if we select the 'Show Solution ' option in Assignment Policy.");
		    }
		    driver.findElement(By.cssSelector("img[title='Solution']")).click(); //click on solution button
		    Thread.sleep(2000);
		    //TC row no . 289
		    String solution1 = new TextFetch().textfetchbyclass("al-diag-test-solution-content");
		    System.out.println("solution1:"+solution1);
		    if(!solution1.contains(solution))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking solution button the student not able to view the solution as a popup.");
		    }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase showSolutionOption in class ShowSolutionOption.",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void showSolutionOptionWithShowSolutionEnabledShowAnswerDisabled()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "291");
			new Assignment().create(291);
			new LoginUsingLTI().ltiLogin("291_1");	//create a student
			new LoginUsingLTI().ltiLogin("291");//login as instructor
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "291 policy desc", "", null, true, "1", "", "", "", "", "1");
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    new Assignment().assignToStudent(291);
		    new LoginUsingLTI().ltiLogin("291_1");//login as student
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
		    new SelectAnswerAndSubmit().staticAssignment("B");//select wrong answer and click submit
		    //TC row no . 291
		    int solutionSize = driver.findElements(By.cssSelector("img[title='Solution']")).size();
		    System.out.println("solutionSize: "+solutionSize);
		    if(solutionSize != 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail(" \"Solution\" button is present (if the question supports) after clicking submit if we select the 'Show Solution' option enabled and 'Show answer' disabled in Assignment Policy.");
		    }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase showSolutionOptionWithShowSolutionEnabledShowAnswerDisabled in class ShowSolutionOption.",e);
		}
	}
	@Test(priority = 3, enabled = true)
	public void showSolutionOptionWithShowSolutionEnabledShowAnswerEnabled()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "292");
			new Assignment().create(292);
			new LoginUsingLTI().ltiLogin("292_1");	//create a student
			new LoginUsingLTI().ltiLogin("292");//login as instructor
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "292 policy desc", "", null, true, "3", "1", "", "", "", "1");
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    new Assignment().assignToStudent(292);
		    new LoginUsingLTI().ltiLogin("292_1");//login as student
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
		    new SelectAnswerAndSubmit().staticAssignment("B");//select wrong answer and click submit
		    driver.findElement(By.className("re-attempt-link")).click();//click on reattempt
		    Thread.sleep(2000);
		    driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).click();//click on submit button
			Thread.sleep(5000);
		    //TC row no . 292
		    int solutionSize = driver.findElements(By.cssSelector("img[title='Solution']")).size();
		    System.out.println("solutionSize: "+solutionSize);
		    if(solutionSize == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail(" \"Solution\" button is absent (if the question supports) after clicking submit if Student’s \"nth\" attempt is more to than what is configured by the instructor.");
		    }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase showSolutionOptionWithShowSolutionEnabledShowAnswerEnabled in class ShowSolutionOption.",e);
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void showSolutionOptionWithShowSolutionSetToNO()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "293");
			new Assignment().create(293);
			new LoginUsingLTI().ltiLogin("293_1");	//create a student
			new LoginUsingLTI().ltiLogin("293");//login as instructor
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "293 policy desc", "", null, true, "1", "", "", "", "", "");
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    new Assignment().assignToStudent(293);
		    new LoginUsingLTI().ltiLogin("293_1");//login as student
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
		    Thread.sleep(2000);
		    new SelectAnswerAndSubmit().staticAssignment("B");//select wrong answer and click submit
		    //TC row no . 293
		    int solutionSize = driver.findElements(By.cssSelector("img[title='Solution']")).size();
		    System.out.println("solutionSize: "+solutionSize);
		    if(solutionSize != 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail(" \"Solution\" button is present (if the question supports) after clicking submit if we set the Show Solution to \"No \" in assignment policy.");
		    }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase showSolutionOptionWithShowSolutionSetToNO in class ShowSolutionOption.",e);
		}
	}
	@Test(priority = 5, enabled = true)
	public void showSolutionOptionWithImmediateFeedbackDisabled()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "294");
			new Assignment().create(294);
			new LoginUsingLTI().ltiLogin("294_1");	//create a student
			new LoginUsingLTI().ltiLogin("294");//login as instructor
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "294 policy desc", "", null, false, "1", "", "", "", "", "");
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    new Assignment().assignToStudent(294);
		    new LoginUsingLTI().ltiLogin("294_1");//login as student
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
		    new SelectAnswerAndSubmit().staticAssignment("B");//select wrong answer and click submit
		    //TC row no . 294
		    int solutionSize = driver.findElements(By.cssSelector("img[title='Solution']")).size();
		    System.out.println("solutionSize: "+solutionSize);
		    if(solutionSize != 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail(" \"Solution\" button is present (if the question supports) after clicking submit if we disable the immediate feedback in assignment policy.");
		    }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase showSolutionOptionWithImmediateFeedbackDisabled in class ShowSolutionOption.",e);
		}
	}
}
