package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1593;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class StudentPerspectiveAndCommentOnInstructorSide extends Driver{
	
	@Test
	public void studentPerspectiveAndCommentOnInstructorSide()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("153_1");		//create a student
			new LoginUsingLTI().ltiLogin("153");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(153);

			new LoginUsingLTI().ltiLogin("153_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			Thread.sleep(2000);
			String perspective = new RandomString().randomstring(5);
            for(int i = 0; i<3; i++)
			    new DiscussionWidget().addPerspectiveForDWAssignment(perspective);
			//add comment to the perspective

			String commentText = new RandomString().randomstring(5);
            new LoginUsingLTI().ltiLogin("153_1");		//login as student
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
            new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission

            List<WebElement> allComments = driver.findElements(By.xpath("//a[@class='ls-content-post__footer-comment-link js-toggle-comments']"));
            Thread.sleep(5000);
            allComments.get(2).click();//click on Comments Link
            Thread.sleep(3000);
            driver.switchTo().activeElement().sendKeys(commentText + Keys.RETURN);
            Thread.sleep(3000);

			new LoginUsingLTI().ltiLogin("153");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			List<WebElement> allComments2 = driver.findElements(By.cssSelector("a[title='Comments']"));
			for(WebElement comments: allComments2)
			{
				if(comments.getText().contains("1"))
				{
					comments.click();	//click on Comments link to expand
					Thread.sleep(3000);
				}
			}
			new MouseHover().mouserhover("perspective-comment-block");	//mouse hover the perspective comment block
			List<WebElement> allArrows = driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allArrows.get(2)); //click on arrow of comment
			Thread.sleep(3000);
			//TC row no. 153
			boolean removeComment = new DiscussionWidget().removeCommentTextVerify();
            System.out.println("removeComment: "+removeComment);
			if(!removeComment)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not there for removing the comment of a student by the instructor after DW is assigned as an Assignment.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-hide-comment")));//remove the comment
			Thread.sleep(3000);
			//TC row no. 154
			String message = driver.findElement(By.className("perspective-comment-block")).getText();
			if(!message.contains("This post has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the comment of a student by the instructor the required message doesnt appear after DW is assigned as an Assignment.");
			}
			List<WebElement> allComments1 = driver.findElements(By.cssSelector("a[title='Comments']"));
			//TC row no. 155 
			if(!allComments1.get(4).getText().contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the comment of a student by the instructor the comment count gets changed.");
			}
			
			new MouseHover().mouserhover("ls-stream-post-comment");	//mouse hover the perspective block
			List<WebElement> allArrows1 = driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allArrows1.get(0)); //click on arrow icon for perspective	//TC row no. 156
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-perspective-hide")));//remove the perspective	//TC row no. 157
			Thread.sleep(1000);
			//TC row no. 158
			String message1 = driver.findElement(By.className("removed-perspective-block")).getText();
			if(!message1.contains("This perspective has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective of a student by the instructor the required message doesnt appear after DW is assigned as an Assignment.");
			}
			//TC row no. 159
			String perspectiveCount = driver.findElement(By.cssSelector("a[title='Perspectives']")).getText();
			if(!perspectiveCount.contains("3"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective of a student by the instructor the Perspectives count gets changed after DW is assigned as an Assignment.");
			}

			new LoginUsingLTI().ltiLogin("153_1");//login as student
			//TC row no. 160
			String score1 = new TileTextInDashboard().studentParticipationScoreLowerText();
			System.out.println("score1:"+score1);
			if(!score1.contains("Compared to your classmates"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective of a student by the instructor the Participation score changes in instructor Dashboard.");
			}
			
			new RunScheduledJobs().runScheduledJobsForDashboard();

			new LoginUsingLTI().ltiLogin("153");//login as instructor
			//TC row no. 161
			String score = driver.findElement(By.className("number")).getText();
			if(!score.contains("Contributions per student"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective of a student by the instructor the Participation score changes in instructor Dashboard.");
			}
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			//TC row no. 162
			String status = driver.findElement(By.className("ls-assignment-status")).getText();
			if(!status.contains("Needs Grading"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the student perspective by the instructor the Assignment status is not Needs Grading in instructor side.");
			}
			driver.findElement(By.className("ls-grade-book-assessment")).click();	//click on View Student Responses
			Thread.sleep(2000);
            new MouseHover().mouserhover("idb-question-manually-graded");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on View Responses
			Thread.sleep(2000);
			//TC row no. 163
			int perspective2 = driver.findElements(By.className("ls-comment-entry")).size();
			if(perspective2 != 3)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the student perspective, instructor goes to gradebook page the student perspective is not visible.");
			}
			
			
			new LoginUsingLTI().ltiLogin("153_2");//login as ANOTHER instructor
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
            driver.findElement(By.linkText("View all perspectives")).click();
            Thread.sleep(2000);
			//TC row no. 164
			int perspective1 = driver.findElements(By.className("ls-comment-entry")).size();
			if(perspective1 == 3)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the student perspective it is still visible for other instructor in that particular DW chapter.");
			}
			int message2 = driver.findElements(By.className("removed-perspective-block")).size();
			if(message2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the student perspective the removal message is not visible for other instructor in that particular DW chapter.");
			}

			new LoginUsingLTI().ltiLogin("153_1");//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			Thread.sleep(60000);
			//TC row no. 165
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
            new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
			int message3 = driver.findElements(By.className("removed-perspective-block")).size();
            System.out.println("Message3:"+message3);
            if(message3 != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the student perspective it is still visible for students in Assignments page.");
			}
			//TC row no. 165
			String messageText1 = driver.findElement(By.className("removed-perspective-block")).getText();
			if(!messageText1.contains("This perspective has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the student perspective the removal message is not visible for students in Assignments page.");
			}
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			//TC row no. 166
			String status1 = driver.findElement(By.className("ls-assignment-status")).getText();
			if(!status1.contains("Submitted"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the student perspective by the instructor the Assignment status is not Submitted in instructor side.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase studentPerspectiveAndCommentOnInstructorSide in class StudentPerspectiveAndCommentOnInstructorSide",e);
		}
	}

}
