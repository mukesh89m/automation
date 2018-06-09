package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R50;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
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
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class VerifyEffectOfVariousGradeReleaseOptions extends Driver{

	@Test(priority = 1, enabled = true)
	public void verifyEffectOfVariousGradeReleaseOptions()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "232");
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "232");
			new Assignment().create(232);
			new Assignment().addQuestionsWithCustomizedQuestionText(232, "qtn-type-true-false-img", assignmentname, 2);
			new LoginUsingLTI().ltiLogin("232_1");	//create a student
			new LoginUsingLTI().ltiLogin("232");//login as instructor
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "232 policy desc", "2", null, false, "1", "", "Release explicitly by the instructor", "", "", "");
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    new Assignment().assignToStudent(232);
		    new LoginUsingLTI().ltiLogin("232_1");//login as student
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
		    Thread.sleep(2000);
		    for(int i=0 ; i<2; i++)
		    	new SelectAnswerAndSubmit().staticAssignment("A");//submit 1st two question
			driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).click();//click on final submit button
		    Thread.sleep(2000);
		    //TC row no. 232
		    int notice = driver.findElements(By.id("assignment-policy-user-answer-status-div")).size();
			if(notice != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For assignment for which immediate feedback is disabled submitting a question the notification is displayed.");
			}
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    //TC row no. 233
		    int size1 = driver.findElements(By.className("ls-assignment-status-grades-released")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On selecting 'Release explicitly by the instructor' in grade release option the student are informed their overall score immediately as they submit.");
			}
			new LoginUsingLTI().ltiLogin("232");//login as instructor
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[title='View Student Responses']")).click(); //click on View Responses
		    Thread.sleep(2000);
		    List<WebElement> grades = driver.findElements(By.xpath("//*[starts-with(@class, 'idb-gradebook-question-content')]"));
		
		    //TC row no. 234
		    if(grades.get(0).getText() == null)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Student response are not stored in gradebook");
		    }
		    driver.findElement(By.cssSelector("div[title='Release Grade for All']")).click(); //click on Release Grade for All
		    Thread.sleep(2000);
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    //TC row no. 237
			String status = new TextFetch().textfetchbyclass("ls-assignment-status-grades-released");
			if(!status.equals("Graded"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After releasing the grade the Status at the instructor side is not updated to \"Graded\".");
			}
			new LoginUsingLTI().ltiLogin("232_1");//login as student
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
			//TC row no. 235
			int size = driver.findElements(By.className("ls-assignment-status-grades-released")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After releasing the grade Student arent informed of their overall score.");
			}
			//TC row no. 236 & 264
			String status1 = new TextFetch().textfetchbyclass("ls-assignment-status");
            if(!status1.contains("Your Status: Score (4/6)"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After releasing the grade the Status at the student side is not updated to Status:Score (4/6).");
			}
			
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyEffectOfVariousGradeReleaseOptions in class VerifyEffectOfVariousGradeReleaseOptions.",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void verifyWhenGradeReleaseOptionsIsAutoReleaseOnSubmission()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "238");
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "238");
            AssignmentResponsesPage assignmentResponsesPage= PageFactory.initElements(driver,AssignmentResponsesPage.class);
			new Assignment().create(238);
			new Assignment().addQuestionsWithCustomizedQuestionText(238, "qtn-type-true-false-img", assignmentname, 2);
			new LoginUsingLTI().ltiLogin("238_1");	//create a student
			new LoginUsingLTI().ltiLogin("238");//login as instructor
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "238 policy desc", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    new Assignment().assignToStudent(238);
		    new LoginUsingLTI().ltiLogin("238_1");//login as student
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
		    Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false,"correct",238);
            new Assignment().nextButtonInQuestionClick();
            new AttemptQuestion().trueFalse(false,"correct",238);
            new Assignment().nextButtonInQuestionClick();
            Thread.sleep(5000);
            driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).click();//click on final submit button
	    	Thread.sleep(5000);
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    //TC row no. 239
			String score = new TextFetch().textfetchbyclass("assignment-score");
			if(!score.contains("4/6"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The student unable to view the performance of all questions in assignment page after auto-release of grades on submission.");
			}
			driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
		    Thread.sleep(15000);
		    new Click().clickByclassname("question-card-label");//click on question card
		  /*  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[title='Assignments']")));
		    Thread.sleep(2000);
		    //TC row no. 239
		    List<WebElement> allElements = driver.findElements(By.className("ls-right-section-status"));
		    if(!allElements.get(2).getText().contains("Status: Score (4/6)"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The student unable to view the performance of all questions in assignment tab after auto-release of grades on submission.");
		    }
*/
			new LoginUsingLTI().ltiLogin("238");//login as instructor
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
			driver.findElement(By.cssSelector("span[title='View Grades']")).click(); //click on View Grades
			Thread.sleep(2000);
			//hover over the student row
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
			//TC row no. 243
            String viewresponseslink = null;
            try {
                viewresponseslink = driver.findElement(By.cssSelector("div[class='ls-view-response-link']")).getText();
            } catch (Exception e) {
                viewresponseslink = driver.findElement(By.cssSelector("div[class='ls-view-response-link']")).getText();
            }
            if(!viewresponseslink.equals("View Response"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On selecting \"Auto-release on assignment submission\" in grade release option after submitting asignment as an instructor \"View Response\" link is not available in assignment Response page..");
			}
			//TC row no. 242
			if(viewresponseslink.equals("Enter Grade"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On selecting \"Auto-release on assignment submission\" in grade release option after submitting asignment as an instructor \"Enter Grade\" link is available in assignment Response page..");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='idb-gradebook-content-coloumn']")));
			Thread.sleep(2000);
			//TC row no. 244
			String responsePage = new TextFetch().textfetchbyclass("view-user-performance-header");
			if(!responsePage.contains(assignmentname))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking view response the instructor doesnt navigate to response page");
			}
			//TC row no. 245
            driver.findElement(By.id("view-user-question-performance-score-box")).clear();//clear the field
            driver.findElement(By.id("view-user-question-performance-score-box")).sendKeys("3"); //provide grade to last essay type question
            new Click().clickByclassname("view-user-question-performance-save-btn"); //click on Save

            String value = driver.findElement(By.id("view-user-question-performance-score-box")).getAttribute("value");
            System.out.println("Value:"+value);
            if(!value.equals("3"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In response page the instructor cannot edit the the score, whereas \"Release as they are being graded\" in grade release option is selected.");
            }

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyWhenGradeReleaseOptionsIsAutoReleaseOnSubmission in class VerifyEffectOfVariousGradeReleaseOptions.",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void verifyWhenGradeReleaseOptionsIsReleaseAsTheyAreBeingGraded()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "254");
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "254");
			new Assignment().create(254);
			new Assignment().addQuestionsWithCustomizedQuestionText(254, "qtn-type-true-false-img", assignmentname, 1);
			new Assignment().addQuestions(254, "qtn-essay-type", assignmentname);
			new LoginUsingLTI().ltiLogin("254_1");	//create a student
			new LoginUsingLTI().ltiLogin("254");//login as instructor
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "254 policy desc", "2", null, false, "1", "", "Release as they are being graded", "", "", "");
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    new Assignment().assignToStudent(254);
		    new LoginUsingLTI().ltiLogin("254_1");//login as student
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
		    Thread.sleep(2000);
		    for(int i=0 ; i<2; i++)
		    	new SelectAnswerAndSubmit().staticAssignment("A");//submit 1st two question
            new Click().clickByid("html-editor-non-draggable"); //clicking on text box of essay type question
            new TextSend().textsendbyid("Correct Essay Answer Text", "html-editor-non-draggable"); //Entering correct answer text
		    driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
		    Thread.sleep(2000);
			driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).click();//click on final submit button
		    Thread.sleep(2000);
		    
		    new LoginUsingLTI().ltiLogin("254");//login as instructor
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[title='View Student Responses']")).click(); //click on View Responses
		    Thread.sleep(2000);
		    List<WebElement> allElements = driver.findElements(By.cssSelector("div[class='idb-gradebook-content-coloumn']"));
		    allElements.get(2).click();//click on last view resonse link
		    Thread.sleep(2000);
		    driver.findElement(By.id("view-user-question-performance-score-box")).sendKeys("2"); //provide grade to last essay type question
		    new Click().clickByclassname("view-user-question-performance-save-btn"); //click on Save
		   
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    //TC row no. 254
			String status = new TextFetch().textfetchbyclass("ls-assignment-status-grades-released");
			if(!status.equals("Graded"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After releasing the grade the Status at the instructor side is not updated to \"Graded\".");
			}
			new LoginUsingLTI().ltiLogin("254_1");//login as student
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
			//TC row no. 255
			String status1 = new TextFetch().textfetchbyclass("ls-assignment-status");
			if(!status1.contains("Your Status: Score (6/6)"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After releasing the grade the Status at the student side is not updated to \"Graded\".");
			}
		    new LoginUsingLTI().ltiLogin("254");//login as instructor
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    driver.findElement(By.cssSelector("span[title='View Grades']")).click(); //click on View Grades
		    Thread.sleep(2000);
		    //hover over the student row
		    Actions action = new Actions(driver);
		    WebElement we = driver.findElement(By.cssSelector("div[class='idb-gradebook-content-coloumn']"));
			action.moveToElement(we).build().perform();
			//TC row no. 258
			String viewresponseslink = driver.findElement(By.cssSelector("div[class='ls-view-response-link']")).getText();
			if(!viewresponseslink.equals("View Response"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On selecting \"Auto-release on assignment submission\" in grade release option after submitting asignment as an instructor \"View Response\" link is not available in assignment Response page..");
			}
			//TC row no. 259
			if(viewresponseslink.equals("Enter Grade"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On selecting \"Auto-release on assignment submission\" in grade release option after submitting asignment as an instructor \"Enter Grade\" link is available in assignment Response page..");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='idb-gradebook-content-coloumn']")));//click on view response link
			Thread.sleep(2000);
			//TC row no. 260
			String responsePage = new TextFetch().textfetchbyclass("view-user-performance-header");
			if(!responsePage.contains(assignmentname))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking view response the instructor doesnt navigate to response page");
			}
			//TC row no.261  
            driver.findElement(By.id("view-user-question-performance-score-box")).clear();//clear the field
            driver.findElement(By.id("view-user-question-performance-score-box")).sendKeys("3"); //provide grade to last essay type question
            new Click().clickByclassname("view-user-question-performance-save-btn"); //click on Save

            String value = driver.findElement(By.id("view-user-question-performance-score-box")).getAttribute("value");
            System.out.println("Value:"+value);
            if(!value.equals("3"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In response page the instructor cannot edit the the score, whereas \"Release as they are being graded\" in grade release option is selected.");
            }
		
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyWhenGradeReleaseOptionsIsReleaseAsTheyAreBeingGraded in class VerifyEffectOfVariousGradeReleaseOptions.",e);
		}
	}
}
