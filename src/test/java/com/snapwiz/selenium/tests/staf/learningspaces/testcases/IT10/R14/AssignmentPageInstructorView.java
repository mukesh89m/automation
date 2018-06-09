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

public class AssignmentPageInstructorView extends Driver
{

	@Test
	public void verifyNameAndPictureOfInstructor()
	{
		try
		{
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "1708");
			new Assignment().create(1708);
            new Assignment().addQuestions(1708, "multiplechoice", assessmentname);

            new LoginUsingLTI().ltiLogin("1708_student");

            new LoginUsingLTI().ltiLogin("1708");
			new Assignment().assignToStudent(1708);

			new LoginUsingLTI().ltiLogin("1708");
			new Navigator().NavigateTo("Assignments");
			new Assignment().assignmentDetailsValidate(1708);
			
			String message = driver.findElement(By.className("ls-assignment-post-label")).getText();
			
			if(!message.trim().equals("posted an assignment."))
			{
				Assert.fail("Default message 'posted an assignment.' is not displayed after posting");
			}
			
			int bookmark = driver.findElements(By.cssSelector("span.ls-assignment-bookmark")).size();
	
			if(bookmark == 0)
			{
				Assert.fail("Bookmark icon is not present ");
			}
			String assignmentTab = driver.findElement(By.cssSelector("span[title='"+"(shor) "+assessmentname+"']")).getText();
			
			if(!assignmentTab.equals(assignmentTab))
				Assert.fail("On clicking assignment name it open the assessment in a new tab");

            new LoginUsingLTI().ltiLogin("1708_student");
            new Assignment().submitAssignmentAsStudent(1708);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifyNameAndPictureOfInstructor in class AssignmentPageInstructorView",e);
		}
		
	}

}
