package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R50;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class ShowHintOption extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void showHintOptionEnabled()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "277");
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "277");
			String hint = ReadTestData.readDataByTagName("", "hint", "277");
			new Assignment().create(277);
			new Assignment().addQuestionsWithCustomizedQuestionText(277, "qtn-type-true-false-img", assignmentname, 2);
			new LoginUsingLTI().ltiLogin("277_1");	//create a student
			new LoginUsingLTI().ltiLogin("277");//login as instructor
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "277 policy desc", "2", null, true, "3", "", "", "2", "", "");
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    new Assignment().assignToStudent(277);
		    new LoginUsingLTI().ltiLogin("277_1");//login kas student
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
		    Thread.sleep(5000);
		    new SelectAnswerAndSubmit().staticAssignmentWithShowAnswer("B");//select wrong answer and click submit
		    driver.findElement(By.className("re-attempt-link")).click();//click on reattempt
            driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).click();
            driver.findElement(By.className("re-attempt-link")).click();//click on reattempt
            Thread.sleep(2000);
		    //TC row no . 277
		    int hintSize = driver.findElements(By.cssSelector("img[title='Hint']")).size();
		    if(hintSize == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail(" \"Hint\" button is absent (if the question supports) on the question screen if we select the 'Show hint ' option in Assignment Policy.");
		    }
		    driver.findElement(By.cssSelector("img[title='Hint']")).click();
		    Thread.sleep(2000);
		    //TC row no . 279
		    String hint1 = new TextFetch().textfetchbyid("al-question-hint");
		    if(!hint1.contains(hint))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking hint button The student not able to view the hint as a popup.");
		    }
		   /* new SelectAnswerAndSubmit().staticAssignmentWithShowAnswer("B");//select wrong answer and click submit(here it'll skip)
		    driver.findElement(By.className("re-attempt-link")).click();//click on reattempt
		    Thread.sleep(2000);
		    //TC row no . 278
		    int hintSize1 = driver.findElements(By.cssSelector("img[title='Hint']")).size();
		    if(hintSize1 == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Student’s \"nth\" attempt is more than what is configured by the instructor then \"Hint\" button is absent for for further attempts.");
		    }*/

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase showHintOptionEnabled in class ShowHintOption.",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void showHintOptionDisabled()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "281");
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "281");
			new Assignment().create(281);
			new Assignment().addQuestionsWithCustomizedQuestionText(281, "qtn-type-true-false-img", assignmentname, 2);
			new LoginUsingLTI().ltiLogin("281_1");	//create a student
			new LoginUsingLTI().ltiLogin("281");//login as instructor
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "281 policy desc", "2", null, true, "2", "", "", "", "", "");
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    new Assignment().assignToStudent(281);
		    new LoginUsingLTI().ltiLogin("281_1");//login as student
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
		    new SelectAnswerAndSubmit().staticAssignmentWithShowAnswer("B");//select wrong answer and click submit
		    driver.findElement(By.className("re-attempt-link")).click();//click on reattempt
		    Thread.sleep(2000);
		    //TC row no . 281
		    int hintSize = driver.findElements(By.cssSelector("img[title='Hint']")).size();
		    if(hintSize != 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail(" \"Hint\" button is absent (if the question supports) on the question screen if we select the 'Show hint ' option in Assignment Policy.");
		    }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase showHintOptionDisabled in class ShowHintOption.",e);
		}
	}
	@Test(priority = 3, enabled = true)
	public void showHintOptionWithImmediateFeedbackDisabled()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "282");
			new Assignment().create(282);
			new LoginUsingLTI().ltiLogin("282_1");	//create a student
			new LoginUsingLTI().ltiLogin("282");//login as instructor
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "282 policy desc", "", null, false, "1", "", "", "", "", "");
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    new Assignment().assignToStudent(282);
		    new LoginUsingLTI().ltiLogin("282_1");//login as student
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
		   //TC row no . 282
            (new WebDriverWait(driver, 100))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img[title='Hint']")));
            String hintSize1 = driver.findElement(By.cssSelector("img[title='Hint']")).getAttribute("src");
            if(!hintSize1.contains("hint-button.png"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("With immediate feedback disabled the question having hints the hint is not dispalyed in question page itself.");
		    }
            driver.findElement(By.cssSelector("img[title='Hint']")).click();
            if(!driver.findElement(By.id("al-question-hint")).getText().contains("Hint Text")) {
                Assert.fail("Not showing hint text of question");
            }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase showHintOptionWithImmediateFeedbackDisabled in class ShowHintOption.",e);			
		}
	}
}
