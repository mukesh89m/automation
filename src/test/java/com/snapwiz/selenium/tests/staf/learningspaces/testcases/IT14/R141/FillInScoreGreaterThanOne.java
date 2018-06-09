package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;

public class FillInScoreGreaterThanOne extends Driver {
	
	@Test
	public void fillInScoreGreaterThanOne()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("209_1");		//create a student
			
			new LoginUsingLTI().ltiLogin("209");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(209);
			
			new LoginUsingLTI().ltiLogin("209_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			Thread.sleep(2000);
			String perspective = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective);
			
			new LoginUsingLTI().ltiLogin("209");		//login as instructor
			new Assignment().viewResponseOfDW();
			Thread.sleep(3000);
			//TC row no. 206
			String totalPoints = driver.findElement(By.id("question-points")).getText();
			if(!totalPoints.contains("2"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In DW assignment response page the total score is not shown.");
			}
			driver.findElement(By.id("view-user-question-performance-score-box")).click();
			driver.findElement(By.id("view-user-question-performance-score-box")).sendKeys("2"); //enter score equal to the total points
			//TC row no. 207, 208
			String scoredPoints = driver.findElement(By.id("view-user-question-performance-score-box")).getAttribute("value");
			if(!scoredPoints.contains("2"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In DW assignment response page Numerator <= denominator is not allowed.");
			}
			driver.findElement(By.id("view-user-question-performance-score-box")).click();
			driver.findElement(By.id("view-user-question-performance-score-box")).clear();
			driver.findElement(By.id("view-user-question-performance-score-box")).sendKeys("5"); //enter score greater the total points
			
			//TC row no. 210
			String red = driver.findElement(By.id("view-user-question-performance-score-box")).getAttribute("style");
			if(!red.contains("red"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In DW assignment response page on entering score greater than total score the Grade box border doesnt become red.");
			}
			
			//TC row no. 209
			String disabled = driver.findElement(By.className("view-user-discussion-performance-save-btn")).getAttribute("style");
			if(!disabled.contains("opacity: 0.3;"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In DW assignment response page the save button does diable on entering marks greater than total marks.");
			}
			//TC row no. 212
			String perspectiveWordCount = driver.findElement(By.className("perspective-word-count-wrapper")).getText();
			if(!perspectiveWordCount.contains("Perspective word count:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In DW assignment response page the Perspective word count is not shown.");
			}
			//TC row no. 213
			String commentCount = driver.findElement(By.className("instructor-dw-comments-count-wrapper")).getText();
			if(!commentCount.contains("comments"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In DW assignment response page the comment count in right side is not shown.");
			}
			//TC row no. 215
			int feedbackField = driver.findElements(By.className("view-user-question-performance-feedback")).size();
			if(feedbackField == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In DW assignment response page the Instructor Feedback field is not displayed.");
			}
			//TC row no. 216
			String placeHolder = driver.findElement(By.id("view-user-question-performance-feedback-box")).getAttribute("placeholder");
			if(!placeHolder.contains("Write your feedback here..."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In DW assignment response page in Instructor Feedback field the deafult text \"Write your feedback here...\"is not displayed.");
			}
			driver.findElement(By.id("view-user-question-performance-feedback-box")).click();//click inside the feedback textbox
			Thread.sleep(2000);
			driver.findElement(By.id("view-user-question-performance-feedback-box")).clear();
			String feedbackText = new RandomString().randomstring(5);
			driver.findElement(By.id("view-user-question-performance-feedback-box")).sendKeys(feedbackText);
			driver.findElement(By.id("view-user-question-performance-score-box")).click();
			driver.findElement(By.id("view-user-question-performance-score-box")).clear();
			driver.findElement(By.id("view-user-question-performance-score-box")).sendKeys("2"); //enter a valid score
			driver.findElement(By.className("view-user-discussion-performance-save-btn")).click();	//click on save button
			Thread.sleep(2000);
			//TC row no. 218
			String saveMessage = driver.findElement(By.id("view-user-question-performance-save-success-message")).getText();
			if(!saveMessage.contains("Saved successfully."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After entering valid score and feedback if we click on Save button then message \"Saved Successfully\" is not shown.");
			}
			new TabClose().tabClose(2); //close the tab
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("ls-gradebook-refresh-icon")));//refresh button clicked
			Thread.sleep(2000);
			//TC row no. 219
			String score = driver.findElement(By.cssSelector("span[class='idb-gradebook-question-content idb-question-fully-scored']")).getText();
			if(!score.equals("2.0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After entering valid score and feedback if we click on Save button then the score is not displayed over gradebook page.");
			}
			//TC row no. 220
			String feedbackIcon = driver.findElement(By.cssSelector("span[class='idb-question-feedback-icon']")).getAttribute("innerHTML");
			if(!feedbackIcon.contains("feedback-notification-icon.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After feedback if we click on Save button then the feedback icon is not displayed over gradebook page.");
			}
			new MouseHover().mouserhover("idb-gradebook-question-content");	
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ls-view-response-link']")));	//click on View Response link
			Thread.sleep(3000);
			//TC row no. 221
			String feedback = driver.findElement(By.id("view-user-question-performance-feedback-box")).getText();
			if(!feedback.contains(feedbackText))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("If we reopen the response page again than previously entered feedback is absent.");
			}
			//TC row no. 221
			String score1 = driver.findElement(By.id("view-user-question-performance-score-box")).getAttribute("value");
			if(!score1.contains("2"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("If we reopen the response page again than previously entered score is absent.");
			}
			new TabClose().tabClose(2); //close the tab
			driver.findElement(By.cssSelector("div[title='Release Grade for All']")).click(); //click on Release Grade for All button
			//TC row no. 222
			int size = driver.findElements(By.cssSelector("div[class='idb-gradeBook-grades-section-release idb-gradeBook-release-grades-section idb-gradeBook-grades-released']")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After we click on release grade button the button doesnt get updated to grade released.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("ls-gradebook-refresh-icon")));//refresh button clicked
			Thread.sleep(2000);
			//TC row no. 223
			String status = driver.findElement(By.className("ls-assignment-status-grades-released")).getText();
			if(!status.equals("Graded"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After we click on release grade button the status of the DW assignment doesnt change to Graded.");
			}

			new LoginUsingLTI().ltiLogin("209_1");		//login as student
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidgetForStudent();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='jumpout-icon ls-jumpout-img']")));//click on Jump out icon
			Thread.sleep(2000);
            boolean found = false;
			List<WebElement> feedback1 = driver.findElements(By.cssSelector("div[class='ls-comment-entry  ls-feedback-comment']"));
            for (WebElement l: feedback1)
            {
                if(l.getText().contains(feedbackText))
                {
                    found = true;
                    break;
                }
            }
			//TC row no. 224
			if(found == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The feedback provided by the instructor for the student is not reflected in the discussion tab in the student side.");
			}
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			//TC row no. 225
			String status1 = driver.findElement(By.className("ls-assignment-status")).getText();
			if(!status1.contains("Score"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The status of the DW assignment doesnt change to Graded after the grade is is released by the instructor.");
			}
			//TC row no. 226
			String status2 = driver.findElement(By.className("ls-assignment-status-grades-released")).getText();
			if(!status2.contains("(2/2)"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Points awarded to the student is not visible in Assignments page in the student side.");
			}
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			Thread.sleep(2000);
			new Navigator().navigateToTab("Assignments"); //navigate to Assignment tab
			//TC row no. 227
			String status3 = driver.findElement(By.className("assessmentStatus")).getText();
			if(!status3.contains("Score"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The status of the DW assignment doesnt change to Graded in assignment tab at right over textbook.");
			}
			//TC row no. 228
			if(!status3.contains("2/2"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The points awarded is not displayed in assignment tab at right over textbook.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase fillInScoreGreaterThanOne in class FillInScoreGreaterThanOne.",e);
		}
	}

}
