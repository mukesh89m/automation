package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class InstructoAbleToPreviewQuestionsLearningActivities extends Driver
{
	@Test
	public void instructoAbleToPreviewQuestionsLearningActivities()
	{
		try
		{
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "1889");
			new Assignment().create(1889);		
			new LoginUsingLTI().ltiLogin("1889"); //  Logging in as instructor
			new Navigator().NavigateTo("Assignments");
		    new Assignment().assignToStudent(1889);
			//Finding the assignment
		    List<WebElement> assignments =  driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
			 for(WebElement element : assignments)
			 {

				 if(element.getText().contains(assessmentname))
						 {
					 		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
					 		Thread.sleep(3000);
					 		break;
						 }
				 
			 }
			 //Name in the assessment tab
			 String assessmentTab = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			 if(!assessmentTab.contains(assessmentname))
			 {
				 Assert.fail("By clicking on the assignment name specific assignment tab is not opened");
			 }
			 //Finding the correct answer option
			driver.findElement(By.cssSelector("div[class='true-false-student-tick true-false-student-right']")).click();
			 List<WebElement> allElements = driver.findElements(By.className("associated-content-details-label-wrapper"));
			//Finding the 'Difficulty Level:' field
			 String diffiecultyLevel = allElements.get(0).getText();
			 if(!diffiecultyLevel.contains("Difficulty Level:"))
			 {
				 Assert.fail("'Diffculty level' is not displayed for each question ");
			 }
			 //Finding the objective evaluated field
			 String objective = allElements.get(1).getText();
			 if(!objective.contains("Objectives Evaluated:"))
			 {
				 Assert.fail("'Objectives Evaluated' level is not displayed for each question ");
			 }
			 new Navigator().NavigateTo("Assignments");
			 Thread.sleep(3000);
			 //checking for current Assignment tab
			 String currentAssignment = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			 if(!currentAssignment.equals("Current Assignments"))
			 {
				 Assert.fail("On clicking assignment tab instructor doesnt goes back to Assignment page");
			 }
			 
			 
		}
		catch(Exception e)
		{
			Assert.fail("Exception in instructoAbleToPreviewQuestionsLearningActivities in class InstructoAbleToPreviewQuestionsLearningActivities",e);
		}
	}

}
