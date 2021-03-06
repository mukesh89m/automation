package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class RightSideDetailsForDWAssignment extends Driver{
	
	@Test
	public void rightSideDetailsForDWAssignment()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("176_1");		//create a student
			new LoginUsingLTI().ltiLogin("176");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(176);
			
			new LoginUsingLTI().ltiLogin("176_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
			String perspective = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective);
			
			new LoginUsingLTI().ltiLogin("176");		//login as instructor
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.cssSelector("span[title='View Student Responses']")).click();	//click on View Student Responses
            Thread.sleep(1000);
            //TC row no. 176
			List<WebElement> status = driver.findElements(By.className("ls-assignment-status"));
			if(!status.get(1).getText().contains("Status"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Status of the assignment is absent over Grading Page for DW assignment.");
			}
			List<WebElement> allIcon = driver.findElements(By.cssSelector("img[class='star-icon']"));
			//TC row no. 176
			if(!allIcon.get(3).getAttribute("src").contains("warning.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Status of the assignment dont have the icon present over Grading Page for DW assignment.");
			}
			//TC row no. 177
			List<WebElement> dueDate = driver.findElements(By.className("ls-assignment-date-block"));
			if(!dueDate.get(1).getText().contains("Due Date"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Due date of assignment is absent over Grading Page for DW assignment.");
			}
			//TC row no. 177
			if(!allIcon.get(4).getAttribute("src").contains("calender-icon.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Due date of assignment dont have the icon present over Grading Page for DW assignment.");
			}
			//TC row no. 178
			List<WebElement> accessibleAfter = driver.findElements(By.className("ls-assignment-accessible-after-title"));
			if(!accessibleAfter.get(1).getText().contains("Accessible After:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Accessible After date of assignment is absent over Grading Page for DW assignment.");
			}
			//TC row no. 178
			if(!allIcon.get(5).getAttribute("src").contains("clock.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Accessible After date of the assignment dont have the icon present over Grading Page for DW assignment.");
			}
			//TC row no. 179
			int statusBox = driver.findElements(By.className("ls-instructor-activity-cards-holder")).size();
			if(statusBox != 2)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Status box is absent over Grading Page for DW assignment.");
			}
			//TC row no. 179
			String count = driver.findElement(By.id("ls-assignment-submitted-count")).getText();
			if(!count.equals("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Status boxes is not displayed with student count.");
			}
			
			new Assignment().viewResponseOfDW();
			//TC row no. 181
			List<WebElement> allElements = driver.findElements(By.xpath("//div[contains(@class,'tab active')]"));
            if(allElements.size() != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking View Response link Responses tab doesnt open in a new tab.");
			}
			//TC row no. 183
			String tab = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!tab.contains("Response - (shor)"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The name of the tab is not - \"Response - <Assignment Name>\" when opened from clicking View Response link from grading page for a DW assignment.");
			}
			//TC row no. 184
			String header = driver.findElement(By.className("instructor-dw-response-view-header")).getText();
			if(header == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the assigment response page DW assignment name is missing.");
			}
			String studentName = driver.findElement(By.className("instructor-dw-student-name")).getText();
			if(studentName.length() == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the assigment response page student name is missing.");
			}
			String studentLabel = driver.findElement(By.className("instructor-dw-response-student-details")).getText();
			if(!studentLabel.contains("Student:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the assigment response page \"Student:\" label is missing.");
			}
			String questionText = driver.findElement(By.className("ls-dialog-txt")).getText();
			if(questionText == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the assigment response page the DW question is missing.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase rightSideDetailsForDWAssignment in class RightSideDetailsForDWAssignment.",e);
		}
	}

}
