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

public class HidingPerspectiveAsStudentForDWAssignmentWithGradeReleased extends Driver{
	
	@Test
	public void hidingPerspectiveAsStudentForDWAssignmentWithGradeReleased()
	{
		try
		{

            String perspective = new RandomString().randomstring(5);
			new LoginUsingLTI().ltiLogin("170_1");		//create a student
			new LoginUsingLTI().ltiLogin("170");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(170);
			
			new LoginUsingLTI().ltiLogin("170_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			Thread.sleep(2000);

			new DiscussionWidget().addPerspectiveForDWAssignment(perspective);

			new LoginUsingLTI().ltiLogin("170");		//login as instructor
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-grade-book-assessment")).click();	//click on View Student Responses
			Thread.sleep(3000);
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

			new LoginUsingLTI().ltiLogin("170_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			Thread.sleep(2000);
            WebDriverWait wait=new WebDriverWait(driver,3500);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
            new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
            new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
            Thread.sleep(4500);
			new DiscussionWidget().clickOnArrowIconForPerspective(0, 0);//click on arrow of Perspective	//TC row no. 170
			Thread.sleep(3000);
			//TC row no. 171
			boolean removePerspective= new DiscussionWidget().removePerspectiveTextVerify();
			if(removePerspective == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Perspective\" option is not there for student's own perspective after the grade is relesed for the DW.");
			}
            new DiscussionWidget().removePerspective(0, 0);//click on Remove Perspective
			//TC row no. 172
			String perspective1 = driver.findElement(By.className("ls-comment-entry")).getText();
            System.out.println("perspective1: "+perspective1);
			if(perspective1.contains(perspective))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Remove Perspective for DW assignemnt whose grade has been released the perspective doesnt get removed.");
			}
			//TC row no. 172
			String messageText = driver.findElement(By.className("removed-perspective-block")).getText();
			if(!messageText.contains("This perspective has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Remove Perspective for DW assignemnt whose grade has been released the message for removal of perspective doesnt appear.");
			}
			//TC row no. 173
			int feedbackText = driver.findElements(By.cssSelector("div[class='ls-comment-entry  ls-feedback-comment']")).size();
			if(feedbackText == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective for DW the feedback text also disappears.");
			}
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			//TC row no. 174
			String status = driver.findElement(By.className("ls-assignment-status")).getText();
			if(!status.contains("Score"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective for DW assignment whose grade has been released the status doesnt remain \"Score\" in student side.");
			}

			
			new LoginUsingLTI().ltiLogin("170");		//login as instructor
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			//TC row no. 175
			String status1 = driver.findElement(By.className("ls-assignment-status")).getText();
			if(!status1.contains("Graded"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective for DW whose grade has been released the status doesnt remain Graded in instructor side.");
			}
			driver.findElement(By.className("ls-grade-book-assessment")).click();	//click on View Student Responses
			Thread.sleep(2000);
            new MouseHover().mouserhover("idb-question-feedback-icon");
			new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on View Responses
            Thread.sleep(2000);
			//TC row no. 176
			int perspective2 = driver.findElements(By.className("ls-comment-entry")).size();
			if(perspective2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In gradebook page of DW assignment whose grade has been released the hidden perspectives are not shown.");
			}
			//TC row no. 177
			String visualIndicator = driver.findElement(By.className("ls-perspective-visual-indicator")).getCssValue("background-image");
			if(!visualIndicator.contains("perspective-visual-indicator.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In gradebook page of DW assignment whose grade has been released the visual indicator is not shown for hidden perspectives.");
			}
			//TC row no. 178
			String perspectiveCount = driver.findElement(By.cssSelector("a[title='Perspectives']")).getText();
			if(!perspectiveCount.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In gradebook page of DW assignment whose grade has been released the perspective count changes if the student removes his perspective.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase hidingPerspectiveAsStudentForDWAssignmentWithGradeReleased in class HidingPerspectiveAsStudentForDWAssignmentWithGradeReleased",e);
		}
	}

}
