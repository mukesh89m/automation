package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1593;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
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
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;

public class RemovalOfPerspectiveByReportingAbuseForDWAssignmentWithGradeReleased extends Driver{
	
	@Test
	public void removalOfPerspectiveByReportingAbuseForDWAssignmentWithGradeReleased()
	{
		try
		{

            String perspective = new RandomString().randomstring(15);
			new LoginUsingLTI().ltiLogin("189_1");		//create a student1
		
			new LoginUsingLTI().ltiLogin("189");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(189);
			
			new LoginUsingLTI().ltiLogin("189_1");		//login as student1
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			Thread.sleep(2000);

			new DiscussionWidget().addPerspectiveForDWAssignment(perspective);
			
			new LoginUsingLTI().ltiLogin("189");		//login as instructor
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-grade-book-assessment")).click();	//click on View Student Responses
			Thread.sleep(2000);
            new MouseHover().mouserhover("idb-question-manually-graded");
			new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on View Responses
            Thread.sleep(2000);
			driver.findElement(By.id("view-user-question-performance-feedback-box")).sendKeys("This is a FeedbackText");	//provide a feedback
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("view-user-discussion-performance-save-btn")));//click on save button
			Thread.sleep(2000);
			new TabClose().tabClose(2);	//close the feedback tab
			driver.findElement(By.cssSelector("div[class='idb-gradeBook-grades-section-release idb-gradeBook-release-grades-section idb-gradeBook-grades-release']")).click();//release the grades
			Thread.sleep(2000);
			
			new LoginUsingLTI().ltiLogin("189_2");		//login as student2
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
            new DiscussionWidget().clickOnArrowIconForPerspective(0, 0);//click on arrow icon for perspective //TC row no. 189
			//TC row no. 190
            boolean reportAbuse = new DiscussionWidget().reportAbuseTextVerifyForPerspective();
			if(reportAbuse == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Report Abuse\" option is not there for the Perspective of other students after grade is released for the DW.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-perspective-report-abuse")));//click on Report Abuse
			Thread.sleep(1000);
			//TC row no. 191
            boolean abuseReported = new DiscussionWidget().abuseReportedTextVerifyForPerspective();
			if(abuseReported == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Report Abuse for the perspective the option doesnt become \"Abuse Reported\" after grade is released for the DW.");
			}
			
			new LoginUsingLTI().ltiLogin("189_3");//login as student3
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
            new DiscussionWidget().reportAbuseForPerspective(0, 0);//click on Report Abuse

			new LoginUsingLTI().ltiLogin("189_4");//login as student4
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			new DiscussionWidget().reportAbuseForPerspective(0, 0);//click on Report Abuse

			new LoginUsingLTI().ltiLogin("189_5");//login as student5
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
            new DiscussionWidget().reportAbuseForPerspective(0, 0);//click on Report Abuse
			//just refresh the page
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			Thread.sleep(3000);
			//TC row no. 192
			int message = driver.findElements(By.className("removed-perspective-block")).size();
			if(message != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Report Abuse by four students the perspective doesnt get removed after grade is released for the DW.");
			}
			//TC row no. 192
			String messageText = driver.findElement(By.className("removed-perspective-block")).getText();
			if(!messageText.contains("This perspective has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Report Abuse by four students the message for removal of perspective doesnt appear after grade is released for the DW.");
			}

			new LoginUsingLTI().ltiLogin("189_1");		//login as student1
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			//TC row no. 193
			String status = driver.findElement(By.className("assignment-score")).getText();
			if(!status.contains("Score (0/2)"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After abuse reporting of a perspective the perspective Assignment status is not \"Score\" after grade is released for the DW.");
			}
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			Thread.sleep(3000);
            WebDriverWait wait=new WebDriverWait(driver,3500);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
            new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
            new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
            Thread.sleep(4500);
			//TC row no. 194
			int message1 = driver.findElements(By.className("removed-perspective-block")).size();
			if(message1 != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Report Abuse by four students the perspective doesnt get removed for the student who posted the perspective after grade is released for the DW.");
			}
			//TC row no. 194
			String messageText1 = driver.findElement(By.className("removed-perspective-block")).getText();
			if(!messageText1.contains("This perspective has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Report Abuse by four students the message for removal of perspective doesnt appear for the student who posted the perspective after grade is released for the DW.");
			}
			//TC row no. 195
			int feedbackText = driver.findElements(By.cssSelector("div[class='ls-comment-entry  ls-feedback-comment']")).size();
			if(feedbackText == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective for DW the feedback text also disappears after grade is released for the DW.");
			}

			
			new LoginUsingLTI().ltiLogin("189");		//login as instructor
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-grade-book-assessment")).click();	//click on View Student Responses
			Thread.sleep(2000);
            new MouseHover().mouserhover("idb-question-feedback-icon");
			new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on View Responses
            Thread.sleep(2000);
			//TC row no. 197
			String perspective2 = driver.findElement(By.className("ls-comment-entry")).getText();
			if(!perspective2.contains(perspective))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In gradebook page of DW assignment whose grade has been released the hidden perspectives(by reporting abuse) are not shown.");
			}
			//TC row no. 198
			String visualIndicator = driver.findElement(By.className("ls-perspective-visual-indicator")).getCssValue("background-image");
			if(!visualIndicator.contains("perspective-visual-indicator.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In gradebook page of DW assignment whose grade has been released and perspective is removed by \"Reporting Abuse\" the visual indicator is not shown for hidden perspectives.");
			}
			//TC row no. 199
			String perspectiveCount = driver.findElement(By.cssSelector("a[title='Perspectives']")).getText();
			if(!perspectiveCount.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In gradebook page of DW assignment whose grade has been released and perspective is removed by \"Reporting Abuse\" then the perspective count changes.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase removalOfPerspectiveByReportingAbuseForDWAssignmentWithGradeReleased in class RemovalOfPerspectiveByReportingAbuseForDWAssignmentWithGradeReleased",e);
		}
	}
}
