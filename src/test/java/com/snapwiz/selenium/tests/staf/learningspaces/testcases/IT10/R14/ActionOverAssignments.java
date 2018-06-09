package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.By;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class ActionOverAssignments extends Driver{
	
	@Test(priority=1,enabled=true)
	public void actionOverAssignment()
	{
		try
		{
		new Assignment().create(1730);
        new Assignment().addQuestions(1730, "multiplechoice", "");
        new Assignment().addQuestions(1730, "multipleselection", "");
        new Assignment().addQuestions(1730, "writeboard", "");
        new Assignment().addQuestions(1730, "textentry", "");
        new Assignment().addQuestions(1730, "advancednumeric", "");
        new Assignment().addQuestions(1730, "textdropdown", "");
        new Assignment().addQuestions(1730, "numericentrywithunits", "");
        new Assignment().addQuestions(1730, "essay", "");
		new LoginUsingLTI().ltiLogin("17301"); //Creating user student 1
		new LoginUsingLTI().ltiLogin("17302");//Creating user student 2
		new LoginUsingLTI().ltiLogin("1730"); //  Logging in as instructor
	    new Assignment().assignToStudent(1730);  //Assigning assignment to student 1
	    new LoginUsingLTI().ltiLogin("17303");
	    new Assignment().updateAssignment(17303,true); // Assigning assignment to student 2
	    Thread.sleep(2000);
	 // Validating if View responses and Update Assignment links available under actions.
	    new Navigator().NavigateTo("Assignments");
	    driver.findElement(By.cssSelector("span[class='assign-more']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.linkText("Cancel")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("span[class='ls-grade-book-assessment']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("span[id='close-responses']")).click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase actionOverAssignment in class ActionOverAssignments",e);
		}
	}

	
	@Test(priority=2,enabled=true, dependsOnMethods = {"actionOverAssignment"})
	public void submitAssignment()
	{
		try
		{
		new LoginUsingLTI().ltiLogin("17301"); //Logging in as student 1 to submit the assignment
		new Assignment().submitAssignmentAsStudent(1730);
		new LoginUsingLTI().ltiLogin("1730"); //Logging in as instructor
		// Validating if View responses and Update Assignment links available under actions.
		new Navigator().NavigateTo("Assignments");
		if(driver.findElements(By.cssSelector("span[class='assign-more']")).size() == 0)
			Assert.fail("Update Assignment link not present after student 1 attempts the test");
		if(driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']")).size() == 0)
			Assert.fail("View Responses link not present after student 1 attempts the test");
	    driver.findElement(By.cssSelector("span[class='assign-more']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.linkText("Cancel")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("span[class='ls-grade-book-assessment']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("span[id='close-responses']")).click();
	    new LoginUsingLTI().ltiLogin("17302");  // Logging in as student 2 to attempt the assignment
	    new Assignment().submitAssignmentAsStudent(1730);
		new LoginUsingLTI().ltiLogin("1730"); //Logging in as instructor
		// Validating if View responses and Update Assignment links available under actions.
		new Navigator().NavigateTo("Assignments");
		if(driver.findElements(By.cssSelector("span[class='assign-more']")).size() != 0)
			Assert.fail("Update Assignment link present after student 2 attempts the test");
		
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase submitAssignment in class ActionOverAssignments",e);
		}
	}
	

}
