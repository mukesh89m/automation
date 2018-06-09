package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R15;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.By;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

public class AttemptAssignmentAsStudent extends Driver {
	
	@Test(priority=1,enabled=true)
	public void verifyAssignmentPage()
	{
		try
		{
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "2412");
			new Assignment().create(2412); //Creating Assignment
			new LoginUsingLTI().ltiLogin("24120"); //Login as student to create it in DB
			new LoginUsingLTI().ltiLogin("2412"); //Login as instructor
			new Assignment().assignToStudent(2412);
			new LoginUsingLTI().ltiLogin("24120"); //Login as student
			new Navigator().NavigateTo("Assignments");
			String assessment = driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).getText();
			if(!assessment.contains(assessmentname))
				Assert.fail("The assessment created not present in the assessment page");
			new Assignment().clickonAssignment(assessmentname);
			Thread.sleep(60000);
			driver.findElement(By.cssSelector("span[class='tab_icon assignment-icon']")).click();
			boolean tab1 = driver.findElement(By.cssSelector("span[class='tab_icon assignment-icon']")).isDisplayed();// Verifying new tab is open.
			if(tab1==false)
				Assert.fail("Assignment is not able to open in new tab");
			/*Thread.sleep(2000);
			boolean tab2 = driver.findElement(By.cssSelector("span[class='tab_icon lesson-icon']")).isDisplayed(); // Verifying opened Lesson Page
			if(tab2==false)
				Assert.fail("No Lesson page tab is opened");
			driver.findElement(By.cssSelector("span[class='tab_icon lesson-icon']")).click(); // Opening lesson page*/

			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase verifyAssignmentPage in class AttemptAssignmentAsStudent",e);
		}
	}
	
	
	@Test(priority=2,enabled=true)
	public void verifyHeaderOfAssignment()  //2420
	{
		try
		{
			String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", "2420");
			new Assignment().create(2420); // Create Assignment
			new Assignment().addQuestions(2420, "qtn-type-true-false-img", assignmentname);
			new Assignment().addQuestions(2420, "qtn-multiple-choice-img",assignmentname);
			new LoginUsingLTI().ltiLogin("24200"); //Login as student
			new LoginUsingLTI().ltiLogin("2420"); //Login as instructor
			new Assignment().assignToStudent(2420); 	//assign to student
			new LoginUsingLTI().ltiLogin("24200");  //Login as student
			new Navigator().NavigateTo("Assignments");
			new Assignment().clickonAssignment("2420Assessment");
			new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("span[class='current-question-index']")));
			boolean currentquestion = driver.findElement(By.cssSelector("span[class='current-question-index']")).isDisplayed();
			if(currentquestion==false)
				Assert.fail("Not showing current question number");
			boolean toatalquestion = driver.findElement(By.cssSelector("span[class='total-questions']")).isDisplayed();
			  if(toatalquestion==false)
				Assert.fail("Not showing total question number");
			  boolean display = driver.findElement(By.cssSelector("div[class='title-and-question']")).isDisplayed();
			  if(display==false)
					Assert.fail("Not showing name of assignment and question details");
			  driver.findElement(By.className("question-details")).click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase verifyHeaderOfAssignment in class AttemptAssignmentAsStudent",e);
		}
	}
		
	
		@Test(priority=3,enabled=false)
		public void verifyAllQuestionTypesOfAssignment()  //  -- 2435
		{
			try
			{

				// Pre-Conditions for assignment which contains all types of questions.
				
				String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", "2435");
			    new Assignment().create(2435);
				new Assignment().addQuestions(2435, "qtn-type-true-false-img",assignmentname);  //1
				new Assignment().addQuestions(2435, "qtn-multiple-choice-img",assignmentname);  //2
				new Assignment().addQuestions(2435, "qtn-multiple-selection-img",assignmentname); //3
				new Assignment().addQuestions(2435, "qtn-text-entry-img",assignmentname); //4
				new Assignment().addQuestions(2435, "qtn-text-entry-drop-down-img",assignmentname); //5
				new Assignment().addQuestions(2435, "qtn-text-entry-numeric-img",assignmentname); //6
				new Assignment().addQuestions(2435, "qtn-text-entry-numeric-units-img",assignmentname);//7
				new Assignment().addQuestions(2435, "qtn-numeric-maple-img",assignmentname); // 8
				new Assignment().addQuestions(2435, "qtn-math-symbolic-notation-img",assignmentname); //9
				new Assignment().addQuestions(2435, "qtn-essay-type",assignmentname); //10
			
				 
				//Adding passage type different questions:-				
				new Assignment().addPassagetypequestion(2435, "qtn-passage-based-img", assignmentname,"qtn-type-true-false-img"); 		
			    new Assignment().addPassagetypequestion(2435, "qtn-passage-based-img", assignmentname,"qtn-multiple-choice-img");		   
			    new Assignment().addPassagetypequestion(2435, "qtn-passage-based-img", assignmentname,"qtn-multiple-selection-img");	
			    new Assignment().addPassagetypequestion(2435, "qtn-passage-based-img", assignmentname,"qtn-text-entry-img");			
			    new Assignment().addPassagetypequestion(2435, "qtn-passage-based-img", assignmentname,"qtn-text-entry-drop-down-img"); 		
			    new Assignment().addPassagetypequestion(2435, "qtn-passage-based-img", assignmentname,"qtn-text-entry-numeric-img");			  
			    new Assignment().addPassagetypequestion(2435, "qtn-passage-based-img", assignmentname,"qtn-text-entry-numeric-units-img");		
			    new Assignment().addPassagetypequestion(2435, "qtn-passage-based-img", assignmentname,"qtn-numeric-maple-img");		
			    new Assignment().addPassagetypequestion(2435, "qtn-passage-based-img", assignmentname,"qtn-math-symbolic-notation-img");		
			    new Assignment().addPassagetypequestion(2435, "qtn-passage-based-img", assignmentname,"qtn-essay-type");	
			
				// Testing 
				new LoginUsingLTI().ltiLogin("24350"); //Login as student
			    new LoginUsingLTI().ltiLogin("2435"); //Login as instructor
				new Assignment().assignToStudent(2435); 
				new LoginUsingLTI().ltiLogin("24350"); //Login as student
		     
				// attempting answers
				new Assignment().submitAssignmentAsStudent(2435); // --Pending some parts.
				
				
			}
			catch(Exception e)
			{
				Assert.fail("Exception in TestCase verifyAllQuestionTypesOfAssignment in class AttemptAssignmentAsStudent",e);
			}
	}
		@Test(priority=4,enabled=true)
		public void verifyDifferentStatusOfAssignment()  // -2450
		{
			try{
				String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", "2450");
				new LoginUsingLTI().ltiLogin("2450"); //Login as instructor
				new Assignment().create(2450); // Create Assignment
				new Assignment().addQuestions(2450, "qtn-type-true-false-img", assignmentname);
				new Assignment().addQuestions(2450, "qtn-multiple-choice-img",assignmentname);
				new LoginUsingLTI().ltiLogin("24500"); //Login as student
				new LoginUsingLTI().ltiLogin("2450"); //Login as instructor
				new Assignment().assignToStudent(2450); 	//assign to student
				new LoginUsingLTI().ltiLogin("24500");  //Login as student
				new Navigator().NavigateTo("Assignments");
				new Assignment().clickonAssignment(assignmentname);
				// First time opening assignment should navigate to first question.-2455
				new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("span[class='current-question-index']")));
				String getQuestionNumber1=driver.findElement(By.className("current-question-index")).getText();
				if(!getQuestionNumber1.contains("1"))
					Assert.fail("Assingment did not open at First question, first time.");
				// Attempt 1  question and go next.
                new SelectAnswerAndSubmit().staticAssignment("A");//submit first question
				// Login Again and check , its should navigate to last the last un-attempted question -2456
				new LoginUsingLTI().ltiLogin("24500");
				new Navigator().NavigateTo("Assignments");
				new Assignment().clickonAssignment(assignmentname);
				new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("span[class='current-question-index']")));
				String getQuestionNumber2=driver.findElement(By.className("current-question-index")).getText();
				if(!getQuestionNumber2.contains("2"))
				Assert.fail("Assingment did not open last un-attempted question"); 
				//Attempting all questions and submitting it
				new Assignment().submitAssignmentAsStudent(2450);
				//Check for performance page is coming -2457
				new LoginUsingLTI().ltiLogin("24500");
				new Navigator().NavigateTo("Assignments");
				new Assignment().clickonAssignment(assignmentname);
				Thread.sleep(60000);
				int performance = driver.findElements(By.id("performance-chart-performance-summery")).size();
				if(!(performance == 1))
					Assert.fail("Performance page is not coming.");
				}
				catch(Exception e)
				{
					Assert.fail("Exception in TestCase verifyDifferentStatusOfAssignment in class AttemptAssignmentAsStudent",e);
				}
			
			}
		
		@Test(priority=5,enabled=true)
		public void vrifyNextAndPreviousButtonFunction() // -2458
		{
			try{
				
				String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", "2458");
				new LoginUsingLTI().ltiLogin("2458"); //Login as instructor
				new Assignment().create(2458); // Create Assignment
				new Assignment().addQuestions(2458, "qtn-type-true-false-img", assignmentname);
				new Assignment().addQuestions(2458, "qtn-multiple-choice-img",assignmentname);
				new LoginUsingLTI().ltiLogin("24580"); //Login as student
				new LoginUsingLTI().ltiLogin("2458"); //Login as instructor
				new Assignment().assignToStudent(2458); 	//assign to student
				new LoginUsingLTI().ltiLogin("24580");  //Login as student
				new Navigator().NavigateTo("Assignments");
				new Assignment().clickonAssignment(assignmentname);
                new SelectAnswerAndSubmit().staticAssignment("A");//submit first question
				//verify the presence of previous and next button --2464
				int nextbutton = driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).size();
				int previousbutton =  driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--previous long-text-button']")).size();
				if(!(nextbutton==1))
					Assert.fail("Next buttons is not there");
				if(!(previousbutton==1))
					Assert.fail("Previous button is not there");
				// click on previous button-2467
				 driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--previous long-text-button']")).click();
				//Verify only next button is there on first question --2468
			    nextbutton = driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).size();
				if(!(nextbutton==1))
					Assert.fail("Next button is not there for the first question.");
				Thread.sleep(3000);
                previousbutton = driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--previous long-text-button']")).size();
                if(previousbutton==1)
                    Assert.fail("Previous button is there for the first question also.");
				// Go to the last page and verify submit button
				driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).click();
				Thread.sleep(3000);
				driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).click();
				Thread.sleep(3000);
				int submitbutton = driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).size();
				if(!(submitbutton==1))
					Assert.fail("submit button is not there");
				driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).click();//click on Submit
				
				// Un-attempted question is marked as Skipped. --2471
			}
			catch(Exception e)
			{
				Assert.fail("Exception in TestCase vrifyNextAndPreviousButtonFunction in class AttemptAssignmentAsStudent",e);
			}
		}
	


}
