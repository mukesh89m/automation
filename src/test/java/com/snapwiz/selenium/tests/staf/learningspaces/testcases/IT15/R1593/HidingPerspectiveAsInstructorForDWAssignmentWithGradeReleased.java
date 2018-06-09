package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1593;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
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
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;

public class HidingPerspectiveAsInstructorForDWAssignmentWithGradeReleased extends Driver{

	@Test
	public void hidingPerspectiveAsInstructorForDWAssignmentWithGradeReleased()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("179_1");		//create a student
			new LoginUsingLTI().ltiLogin("179");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(179);

			new LoginUsingLTI().ltiLogin("179_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
            WebDriverWait wait=new WebDriverWait(driver,300);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
			String perspective = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective);

			new LoginUsingLTI().ltiLogin("179");		//login as instructor
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-grade-book-assessment")).click();	//click on View Student Responses
			Thread.sleep(3000);
            new MouseHover().mouserhover("idb-question-manually-graded");
			new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on View Responses
            Thread.sleep(2000);
			driver.findElement(By.id("view-user-question-performance-feedback-box")).click();//click inside feedback box
			Thread.sleep(2000);
			driver.findElement(By.id("view-user-question-performance-feedback-box")).sendKeys("This is a FeedbackText");	//provide a feedback
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("view-user-discussion-performance-save-btn")));//click on save button
			Thread.sleep(2000);
			new TabClose().tabClose(2);	//close the feedback tab
			driver.findElement(By.cssSelector("div[class='idb-gradeBook-grades-section-release idb-gradeBook-release-grades-section idb-gradeBook-grades-release']")).click();//release the grades
			Thread.sleep(2000);
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.cssSelector("span[resourcetype='discussion']")).click();	//click on DW assignment
			Thread.sleep(2000);
            new DiscussionWidget().clickOnArrowIconForPerspective(0, 0);
			//TC row no. 180
            boolean removePerspective = new DiscussionWidget().removePerspectiveTextVerify();
			if(removePerspective == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Perspective\" option is not there for student's own perspective after the grade is released for the DW.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-perspective-hide")));//click on Remove Perspective
			Thread.sleep(1000);
			//TC row no. 181
			int perspective1 = driver.findElements(By.className("ls-comment-entry")).size();
			if(perspective1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Remove Perspective for DW assignment whose grade has been released the perspective doesnt get removed.");
			}
			//TC row no. 181
			String messageText = driver.findElement(By.className("removed-perspective-block")).getText();
			if(!messageText.contains("This perspective has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Remove Perspective for DW assignemnt whose grade has been released the message for removal of perspective doesnt appear.");
			}
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			//TC row no. 182
			String status = driver.findElement(By.className("ls-assignment-status")).getText();
			if(!status.contains("Graded"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective for DW assignment whose grade has been released the status doesnt remain \"Graded\" in instructor side.");
			}
			driver.findElement(By.className("ls-grade-book-assessment")).click();	//click on View Student Responses
			Thread.sleep(3000);
            new MouseHover().mouserhover("idb-question-feedback-icon");
			new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); ////click on View Responses
            Thread.sleep(2000);
			//TC row no. 183
			String perspective2 = driver.findElement(By.className("ls-comment-entry")).getText();
			if(!perspective2.contains(perspective))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In gradebook page of DW assignment whose grade has been released the hidden perspectives are not shown.");
			}
			//TC row no. 184
			String visualIndicator = driver.findElement(By.className("ls-perspective-visual-indicator")).getCssValue("background-image");
			if(!visualIndicator.contains("perspective-visual-indicator.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In gradebook page of DW assignment whose grade has been released the visual indicator is not shown for hidden perspectives.");
			}
			//TC row no. 185
			String perspectiveCount = driver.findElement(By.cssSelector("a[title='Perspectives']")).getText();
			if(!perspectiveCount.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In gradebook page of DW assignment whose grade has been released the perspective count changes if the instructor removes his perspective.");
			}
			
			new LoginUsingLTI().ltiLogin("179_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			Thread.sleep(2000);
			//TC row no. 186
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
            new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
			String messageText1 = driver.findElement(By.className("removed-perspective-block")).getText();
			if(!messageText1.contains("This perspective has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Remove Perspective for DW assignemnt whose grade has been released the message for removal of perspective doesnt appear in student side.");
			}
			//TC row no. 187
			int feedbackText = driver.findElements(By.cssSelector("div[class='ls-comment-entry  ls-feedback-comment']")).size();
			if(feedbackText == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective for DW the feedback text also disappears in student side.");
			}
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			//TC row no. 188
			String status1 = driver.findElement(By.className("ls-assignment-status")).getText();
			if(!status1.contains("Score"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective for DW assignment whose grade has been released the status doesnt remain \"Score\" in student side.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase hidingPerspectiveAsInstructorForDWAssignmentWithGradeReleased in class HidingPerspectiveAsInstructorForDWAssignmentWithGradeReleased",e);
		}
	}

}
