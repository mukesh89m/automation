package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1593;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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

public class OwnPerspectiveCommentForInstructorOnDWAssignment extends Driver{

	@Test
	public void ownPerspectiveCommentForInstructorOnDWAssignment()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("143_1");//create a student
			new LoginUsingLTI().ltiLogin("143");//login as instructor
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			String perspective = new RandomString().randomstring(15); 
			new DiscussionWidget().addPerspectiveForDWIneTextBook(perspective);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			String commentText = new RandomString().randomstring(15); 
			new DiscussionWidget().commentOnPerspective(commentText, 0);	//add a comment to Perspective
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(143);
            driver.findElement(By.xpath("//a[@class='ls-content-post__footer-perspective-link js-toggle-comments']")).click();
			//new MouseHover().mouserhover("perspective-comment-block");	//mouse hover the perspective comment block
            driver.findElement(By.xpath("//a[@class='ls-content-post__footer-comment-link js-toggle-comments']")).click();
            driver.findElement(By.xpath("(//div[@class='ls-dropdown__toggle'])[2]")).click();
            driver.findElement(By.xpath("//a[text()='Remove Comment']")).click();
            Thread.sleep(2000);

		    /*List<WebElement> allArrows = driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allArrows.get(1));//click on arrow of comment
			Thread.sleep(3000);
			
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-hide-comment")));//remove the comment//TC row no. 143
			Thread.sleep(3000);*/
			//TC row no. 144
			String message = driver.findElement(By.className("perspective-comment-block")).getText();
			if(!message.contains("This post has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, After removing the comment the required message doesnt appear.");
			}
			List<WebElement> allComments1 = driver.findElements(By.cssSelector("a[title='Comments']"));
			//TC row no. 145
			if(!allComments1.get(3).getText().contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, After removing the comment the comment count gets changed.");
			}
			new MouseHover().mouserhover("ls-stream-post-comment");	//mouse hover the perspective block
			List<WebElement> allArrows1 = driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allArrows1.get(0)); //click on arrow icon for perspective//TC row no. 146
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-perspective-hide")));//remove the perspective//TC row no. 147
			Thread.sleep(1000);
			//TC row no. 148
			String message1 = driver.findElement(By.className("removed-perspective-block")).getText();
			if(!message1.contains("This perspective has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, After removing the perspective the required message doesnt appear.");
			}
			//TC row no. 149
			String perspectiveCount = driver.findElement(By.cssSelector("a[title='Perspectives']")).getText();
			if(!perspectiveCount.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, After removing the perspective the Perspectives count gets changed.");
			}
			
			
			new LoginUsingLTI().ltiLogin("143_2");//login as ANOTHER instructor
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			//TC row no. 150
			int perspective1 = driver.findElements(By.className("ls-comment-entry")).size();
			if(perspective1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the perspective it is still visible for other instructor in that particular DW chapter.");
			}
			int message2 = driver.findElements(By.className("removed-perspective-block")).size();
			if(message2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the perspective the removal message is not visible for other instructor in that particular DW chapter.");
			}
			
			new LoginUsingLTI().ltiLogin("143_1");//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			Thread.sleep(2000);
            driver.findElement(By.xpath("//a[@class='ls-content-post__footer-perspective-link js-toggle-comments']")).click();
            driver.findElement(By.className("navigate-to-jump-out-icon")).click();
            Thread.sleep(3000);
			//TC row no. 151
			int message3 = driver.findElements(By.className("removed-perspective-block")).size();
			if(message3 != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the perspective it is still visible for students in Assignments page.");
			}
			//TC row no. 151
			String messageText1 = driver.findElement(By.className("removed-perspective-block")).getText();
			if(!messageText1.contains("This perspective has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the perspective the removal message is not visible for students in Assignments page.");
			}
			String perspective3 = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective3);//add perspective

			
			new LoginUsingLTI().ltiLogin("143");//login as instructor
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-grade-book-assessment")).click();	//click on View Student Responses
			Thread.sleep(3000);
            new MouseHover().mouserhover("idb-question-manually-graded");
			new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); ////click on View Responses
            Thread.sleep(2000);
			driver.findElement(By.id("view-other-perspective-link")).click();	//click on view-other-perspective-link
			Thread.sleep(2000);
			int perspective2 = driver.findElements(By.className("ls-comment-entry")).size();
			if(perspective2 != 2)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the perspective, student submits the DW assignment, and instructor goes to gradebook page the instructor perspective is not visible.");
			}
            new TextSend().textsendbyid("1","view-user-question-performance-score-box");
            new TextSend().textsendbyid("This is instructor feedback on discussion widget","view-user-question-performance-feedback-box");
            new Click().clickByclassname("view-user-discussion-performance-save-btn");
            Assert.assertEquals("Saved successfully.",new TextFetch().textfetchbyid("view-user-question-performance-save-success-message"));
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ownPerspectiveCommentForInstructorOnDWAssignment in class OwnPerspectiveCommentForInstructorOnDWAssignment",e);
		}
	}

}
