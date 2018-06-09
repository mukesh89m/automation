package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;

public class VerifyRightSideTileInAssignmentResponsePage extends Driver{
	
	@Test
	public void verifyRightSideTileInAssignmentResponsePage()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("299_1");		//create a student
			
			new LoginUsingLTI().ltiLogin("299");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(299);
			
			new LoginUsingLTI().ltiLogin("299_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));

			String perspective = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective);
			
			new LoginUsingLTI().ltiLogin("299");		//login as instructor
			new Assignment().viewResponseOfDW();
			Thread.sleep(2000);
			String nonGradable = driver.findElement(By.className("dw-non-gradeable-label")).getText();
			if(!nonGradable.contains("Non gradable"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In DW assignment response page the Non gradable label is not shown.");
			}
			String nonGradableIcon = driver.findElement(By.className("dw-non-gradeable-label")).getAttribute("innerHTML");
			if(!nonGradableIcon.contains("non-gradable-info-icon.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In DW assignment response page the i-icon for Non gradable DW assignment is not shown.");
			}
			//TC row no. 300
			String perspectiveWordCount = driver.findElement(By.className("perspective-word-count-wrapper")).getText();
			if(!perspectiveWordCount.contains("Perspective word count:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In non gradable DW assignment response page the Perspective word count is not shown.");
			}
			//TC row no. 301
			String commentCount = driver.findElement(By.className("instructor-dw-comments-count-wrapper")).getText();
			if(!commentCount.contains("comments"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In non gradable DW assignment response page the comment count in right side is not shown.");
			}
			//TC row no. 303
			int feedbackField = driver.findElements(By.className("view-user-question-performance-feedback")).size();
			if(feedbackField == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In non gradable DW assignment response page the Instructor Feedback field is not displayed.");
			}
			//TC row no. 304
			String placeHolder = driver.findElement(By.id("view-user-question-performance-feedback-box")).getAttribute("placeholder");
			if(!placeHolder.contains("Write your feedback here..."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In non gradable DW assignment response page in Instructor Feedback field the deafult text \"Write your feedback here...\"is not displayed.");
			}
			driver.findElement(By.id("view-user-question-performance-feedback-box")).click();//click inside the feedback textbox
			Thread.sleep(2000);
			driver.findElement(By.id("view-user-question-performance-feedback-box")).clear();
			String feedbackText = new RandomString().randomstring(5);
			driver.findElement(By.id("view-user-question-performance-feedback-box")).sendKeys(feedbackText);
			driver.findElement(By.className("view-user-discussion-performance-save-btn")).click();	//click on save button
			Thread.sleep(2000);
			//TC row no. 305, 306
			String saveMessage = driver.findElement(By.id("view-user-question-performance-save-success-message")).getText();
			if(!saveMessage.contains("Saved successfully."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After entering feedback if we click on Save button then message \"Saved Successfully\" is not shown.");
			}
			new TabClose().tabClose(2); //close the tab
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("ls-gradebook-refresh-icon")));//refresh button clicked
			Thread.sleep(2000);
			//TC row no. 307
			String feedbackIcon = driver.findElement(By.cssSelector("span[class='idb-question-feedback-icon']")).getAttribute("innerHTML");
			if(!feedbackIcon.contains("feedback-notification-icon.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After feedback if we click on Save button then the feedback icon is not displayed over gradebook page.");
			}
			new Assignment().viewResponseOfDW();
			Thread.sleep(3000);
			//TC row no. 308
			String feedback = driver.findElement(By.id("view-user-question-performance-feedback-box")).getText();
			if(!feedback.contains(feedbackText))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For non gradable DW assignment If we reopen the response page again than previously entered feedback is absent.");
			}
			
			
			new LoginUsingLTI().ltiLogin("299_1");		//login as student
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidgetForStudent();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='jumpout-icon ls-jumpout-img']")));//click on Jump out icon
			Thread.sleep(2000);
			List<WebElement> feedback1 = driver.findElements(By.cssSelector("div[class='ls-comment-entry  ls-feedback-comment']"));
			//TC row no. 311
			if(!feedback1.get(0).getText().contains(feedbackText))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For non gradable DW assignment the feedback provided by the instructor for the student is not reflected in the discussion tab in the student side.");
			}
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			//TC row no. 312
			String status1 = driver.findElement(By.className("ls-assignment-status")).getText();
			if(!status1.contains("Submitted"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The status of the non gradable DW assignment doesnt change to Submitted after the grade is is released by the instructor.");
			}
			
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			Thread.sleep(6000);
			new Navigator().navigateToTab("Assignments"); //navigate to Assignment tab
			//TC row no. 314
			String status3 = driver.findElement(By.className("assessmentStatus")).getText();
			if(!status3.contains("Submitted"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The status of the non gradable DW assignment doesnt change to Submitted in assignment tab at right over textbook.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyRightSideTileInAssignmentResponsePage in class VerifyRightSideTileInAssignmentResponsePage.",e);
		}
	}

}
