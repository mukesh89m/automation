package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class VerifyEachStatusBox extends Driver
{
	
	@Test
	public void verifyEachStatusBox()
	{
		try
		{
		String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "1756");
		String notStartedCount = ReadTestData.readDataByTagName("", "notStarted", "1756");
		String inProgressCount = ReadTestData.readDataByTagName("", "inProgress", "1756");
		String submittedCount = ReadTestData.readDataByTagName("", "submitted", "1756");
		String reviewedCount = ReadTestData.readDataByTagName("", "reviewed", "1756");
		String gradedCount = ReadTestData.readDataByTagName("", "graded", "1756");
		String gradeable = ReadTestData.readDataByTagName("", "gradeable", "1756");
		new Assignment().create(1756);		
		new LoginUsingLTI().ltiLogin("1756");
		new Assignment().assignToStudent(1756);
		new LoginUsingLTI().ltiLogin("1756");
		
		new Navigator().NavigateTo("Assignments");
		int index = 0;
		List<WebElement> assignments =  driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
		 for(WebElement element : assignments)
		 {
			 if(element.getText().contains(assessmentname))
					 {
				 		break;
					 }
			 index++;
		 }
		 
		 List<WebElement> notstarted = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-first not-started']"));
		 
		 List<WebElement> inprogress = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-second in-progress']"));
		 
		 List<WebElement> submitted = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-third submitted']"));
		 
		 List<WebElement> reviewed = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']"));
		 
		 List<WebElement> graded = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']"));

		 if(!notstarted.get(index).getText().replaceAll("[\n\r]", "").equals(notStartedCount+"Not Started"))
			 Assert.fail("Not Started box doesn't display the count of students who have not yet started the assignment");
			
		 if(!inprogress.get(index).getText().replaceAll("[\n\r]", "").contains(inProgressCount+"In Progress"))
			 Assert.fail("In Progress box doesnt display the count of students who are currently taking the assignment");
		 
		 if(!submitted.get(index).getText().replaceAll("[\n\r]", "").contains(submittedCount+"Submitted"))
			 Assert.fail("Submitted box doesnt display the count of Students who have submitted the assignments");

		 if(gradeable.equals("false"))
		 {
			 if(!reviewed.get(index).getText().replaceAll("[\n\r]", "").contains(reviewedCount+"Reviewed"))
			 {
				 Assert.fail("Reviewed box doesnt display the count of student for whom non gradable assessment has been reviewed");
		 	 }
		 }
		 else
		 {
			 if(!graded.get(index).getText().replaceAll("[\n\r]", "").contains(gradedCount+"Graded"))
			 {
				 Assert.fail("Graded box doesn't display the count of student for whom assessment has been graded");
			 }
		 }
	
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifyEachStatusBox in class VerifyEachStatusBox",e);
		}
	}

}
