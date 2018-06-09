package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiscussionWidget;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class StatusOfDWAssignment extends Driver{
	
	@Test
	public void statusOfDWAssignment()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("354_1");		//create a student
			new LoginUsingLTI().ltiLogin("354");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(354);
			new Navigator().NavigateTo("Assignments"); 
			//TC row no. 354
			String status = driver.findElement(By.className("ls-assignment-status")).getText();
			if(!status.contains("Available for Students"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After assigning a DW assignment the Status is not Available for Students.");
			}
			//TC row no. 355
			boolean dueDate = driver.findElement(By.className("ls-assignment-date-block")).isDisplayed();
			boolean accesibleAfter = driver.findElement(By.className("ls-assignment-accessible-after-title")).isDisplayed();
			if(dueDate == false || accesibleAfter == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Date and time for DW assignment is not displayed.");
			}
			//TC row no. 356
			String count = driver.findElement(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-first not-started']")).getText();
			if(!count.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Status boxes is not displayed with student count under not started.");
			}
			
			new LoginUsingLTI().ltiLogin("354_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
			String perspective = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective);

			new LoginUsingLTI().ltiLogin("354");		//login as instructor
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			//TC row no. 359
			String status1 = driver.findElement(By.className("ls-assignment-status")).getText();
			if(!status1.contains("Needs Grading"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After submission by the student the DW assignment status doesnt change to Needs Grading in instructor side.");
			}
			//TC row no. 360
			String count1 = driver.findElement(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-third submitted']")).getText();
			if(!count1.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After submission by the student the DW assignment status count doesnt changes in instructor side.");
			}
			driver.findElement(By.cssSelector("span[title='View Student Responses']")).click();	//click on View Student Responses
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("div[title='Release Grade for All']")).click();	//click on Release Grade for All
			Thread.sleep(2000);
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			//TC row no. 361
			String status2 = driver.findElement(By.className("ls-assignment-status")).getText();
			if(!status2.contains("Graded"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After submission by the student the DW assignment status doesnt change to Grading in Progress in instructor side.");
			}
			//TC row no. 362
			String count2 = driver.findElement(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']")).getText();
			if(!count2.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After submission by the student the DW assignment status count doesnt changes in instructor side.");
			}
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 363
			String text = driver.findElement(By.className("ls-stream-post__action")).getText();
			if(!text.contains("posted a perspective"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In instructor side DW assignment entry is not available in the Course Stream.");
			}
			driver.findElement(By.cssSelector("span[class='ls-lesson-title ellipsis']")).click();
			Thread.sleep(5000);
			//TC row no. 364
			String tab = driver.findElement(By.xpath("(//span[@class='tab_title'])[4]")).getText();
			if(!tab.contains("Discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On opening DW assignment from Course Stream the instructor doesnt get redirected to discussion tab view in the e-textbook.");
			}
			Thread.sleep(5000);
			new Navigator().navigateToTab("Assignments"); //navigate to Assignments Tab
			driver.findElement(By.xpath("(//a[@class='ls-assignment-show-assign-this-block'])[1]")).click();//Click on arrow
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-cycle-bg']")));	//click on Open link in Assignments tab
			Thread.sleep(5000);
			//TC row no. 365
			String tab1 = driver.findElement(By.xpath("(//div[@class='tab active'])[2]")).getText();
			if(!tab1.contains("Discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On opening DW assignment from Assu=ignmnets tab present at right side the instructor doesnt get redirected to discussion tab view in the e-textbook.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase statusOfDWAssignment in class StatusOfDWAssignment.",e);
		}
	}
}
