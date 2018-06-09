package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1593;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class InstructorRemovesStudentPerspectiveAndComment extends Driver{
	
	@Test
	public void instructorRemovesStudentPerspectiveAndComment()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("78_1");//login as student
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().addMultiplePerspectiveForDWIneTextBook(5);//add 3 perspective
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			String commentText = new RandomString().randomstring(15); 
			new DiscussionWidget().commentOnPerspective(commentText, 0);	//add a comment to Perspective


			new LoginUsingLTI().ltiLogin("78");//login as instructor
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
            Thread.sleep(5000);
            List<WebElement> allComments = driver.findElements(By.xpath("//a[@class='ls-content-post__footer-comment-link js-toggle-comments']"));
            allComments.get(1).click();
			new MouseHover().mouserhover("perspective-comment-block");	//mouse hover the perspective comment block
			List<WebElement> allArrows = driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allArrows.get(2));//click on arrow of comment
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-hide-comment")));//remove the comment//TC row no. 78
			Thread.sleep(3000);
			//TC row no. 79
			String message = driver.findElement(By.className("perspective-comment-block")).getText();
			if(!message.contains("This post has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the comment of a student by the instructor the required message doesnt appear.");
			}
			List<WebElement> allComments1 = driver.findElements(By.cssSelector("a[title='Comments']"));
			//TC row no. 80
			if(!allComments1.get(4).getText().contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the comment of a student by the instructor the comment count gets changed.");
			}
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			new MouseHover().mouserhover("ls-stream-post-comment");	//mouse hover the perspective block
			List<WebElement> allArrows1 = driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allArrows1.get(0)); //click on arrow icon for perspective	//TC row no. 81
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-perspective-hide")));//remove the perspective	//TC row no. 82
			Thread.sleep(1000);
			//TC row no. 83
			String message1 = driver.findElement(By.className("removed-perspective-block")).getText();
			if(!message1.contains("This perspective has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective of a student by the instructor the required message doesnt appear.");
			}
			//TC row no. 84
			String perspectiveCount = driver.findElement(By.cssSelector("a[title='Perspectives']")).getText();
            System.out.println("perspectiveCount: "+perspectiveCount);
            if(!perspectiveCount.contains("5"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective of a student by the instructor the Perspectives count gets changed.");
			}

			new RunScheduledJobs().runScheduledJobsForDashboard();

			new LoginUsingLTI().ltiLogin("78");//login as instructor
			//TC row no. 86
			String score = driver.findElement(By.className("number")).getText();
			if(!score.contains("Contributions per student"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective of a student by the instructor the Participation score changes in instructor Dashboard.");
			}
			
			
			new LoginUsingLTI().ltiLogin("78_1");//login as student
			//TC row no. 85
			String score1 = new TileTextInDashboard().studentParticipationScoreLowerText();
			if(!score1.contains("Compared to your classmates"))			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective of a student by the instructor the Participation score changes in student Dashboard.");
			}

			new LoginUsingLTI().ltiLogin("78");//login as instructor
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='jumpout-icon ls-jumpout-img']")));//click on jump out icon
			Thread.sleep(12000);
			new MouseHover().mouserhover("ls-stream-post-comment");	//mouse hover the perspective block
			List<WebElement> allArrows2 = driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allArrows2.get(0)); //click on arrow icon for perspective	//TC row no. 87
			Thread.sleep(1000);
			//TC row no. 88
			String removeText = driver.findElement(By.className("ls-perspective-hide")).getText();
            System.out.println("--"+removeText);
			if(!removeText.contains("Remove Perspective"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Remove Perspective option is absent from the newly opened discussion tab.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-perspective-hide")));//remove the perspective	//TC row no. 82
			Thread.sleep(1000);
			//TC row no. 89
			String message2 = driver.findElement(By.className("removed-perspective-block")).getText();
			if(!message2.contains("This perspective has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective of a student by the instructor from the newly opened discussion tab the required message doesnt appear.");
			}
			//TC row no. 90
			List<WebElement> perspectiveCount1 = driver.findElements(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"));
			if(!perspectiveCount1.get(1).getText().contains("5"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective of a student by the instructor the Perspectives count gets changed in the newly opened discussion tab.");
			}
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("View all perspectives"))); //clicking on View all perspectives link
			Thread.sleep(3000);
			//TC row no. 91
			int message3 = driver.findElements(By.className("removed-perspective-block")).size();
			if(message3 != 2)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, After removing the perspective from newly opened discussion tab the required message doesnt appear in the corresponding DW.");
			}
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 92
			String posts = driver.findElement(By.className("ls-stream-post__action")).getText();
            System.out.println(posts);
			if(!(posts.contains("posted a perspective")))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, After removing the perspective from newly opened discussion tab the Course stream entry for perspective doesnt get deleted.");
			}
			
			new RunScheduledJobs().runScheduledJobsForDashboard();

			new LoginUsingLTI().ltiLogin("78");//login as instructor
			//TC row no. 94
			String score2 = driver.findElement(By.className("number")).getText();
			if(!score2.contains("Contributions per student"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective of a student by the instructor the Participation score changes in instructor Dashboard.");
			}

			new LoginUsingLTI().ltiLogin("78_1");//login as student
			//TC row no. 93
			String score3 = new TileTextInDashboard().studentParticipationScoreLowerText();
			if(!score3.contains("Compared to your classmates"))			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective of a student by the instructor the Participation score changes in student Dashboard.");
			}

			new LoginUsingLTI().ltiLogin("78");//login as instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			driver.findElement(By.className("ls-dropdown__toggle")).click();	//click on arrow icon	//TC row no. 95
			Thread.sleep(3000);
			//TC row no. 96
			String removePer2 = driver.findElement(By.className("ls-hide-post")).getText();
			if(!removePer2.contains("Remove Perspective"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Perspective\" option is not there for removing the comment from Course Stream.");
			}
			driver.findElement(By.className("ls-hide-post")).click(); //remove the perspective
			Thread.sleep(1000);
            driver.findElement(By.className("ls-dropdown__toggle")).click();	//click on arrow icon
            driver.findElement(By.className("ls-hide-post")).click(); //remove the perspective
            Thread.sleep(1000);
            driver.findElement(By.className("ls-dropdown__toggle")).click();	//click on arrow icon
            driver.findElement(By.className("ls-hide-post")).click(); //remove the perspective
            Thread.sleep(1000);
			//TC row no. 97
			int posts2 = driver.findElements(By.className("ls-stream-post__action")).size();
			if(posts2 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking \"Remove Perspective\" by instructor for a perspective which has been posted by a student from Course stream the perspective doesnt get deleted from Course stream.");
			}
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("View all perspectives"))); //clicking on View all perspectives link
			Thread.sleep(3000);
			//TC row no. 98
			int message4 = driver.findElements(By.className("removed-perspective-block")).size();
			if(message4 != 5)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, After removing the perspective from newly opened discussion tab the required message doesnt appear in the corresponding lesson containing DW.");
			}
			//TC row no. 99
			String perspectiveCount2 = driver.findElement(By.cssSelector("a[title='Perspectives']")).getText();
			if(!perspectiveCount2.contains("5"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the 3rd perspective from Course Stream the Perspectives count gets changed.");
			}

			new RunScheduledJobs().runScheduledJobsForDashboard();
			
			new LoginUsingLTI().ltiLogin("78");//login as instructor
			//TC row no. 101
			String score4 = driver.findElement(By.className("number")).getText();
			if(!score4.contains("Contributions per student"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective of a student by the instructor the Participation score changes in instructor Dashboard.");
			}

			new LoginUsingLTI().ltiLogin("78_1");//login as student
			//TC row no. 100
			String score5 = new TileTextInDashboard().studentParticipationScoreLowerText();
			if(!score5.contains("Compared to your classmates"))			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the perspective of a student by the instructor the Participation score changes in student Dashboard.");
			}
			
			
			new LoginUsingLTI().ltiLogin("78_2");//login as other instructor
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			//TC row no. 102
			int perspective1 = driver.findElements(By.className("ls-comment-entry")).size();
			if(perspective1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the student's perspective it is still visible for other instructor in that particular DW chapter.");
			}
			int message5 = driver.findElements(By.className("removed-perspective-block")).size();
			if(message5 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the student's perspective the removal message is not visible for other instructor in that particular DW chapter.");
			}
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 103
			int posts4 = driver.findElements(By.className("ls-stream-post__action")).size();
			if(posts4 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the student's perspective it is still visible in Course Stream for other instructor.");
			}
			
			
			new LoginUsingLTI().ltiLogin("78_1");//login as student
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			//TC row no. 104
			int perspective2 = driver.findElements(By.className("ls-comment-entry")).size();
			if(perspective2 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the student's perspective it is still visible for student in that particular DW chapter.");
			}
			int message6 = driver.findElements(By.className("removed-perspective-block")).size();
			if(message6 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the student's perspective the removal message is not visible for student in that particular DW chapter.");
			}
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 105
			int posts3 = driver.findElements(By.className("ls-stream-post__action")).size();
			if(posts3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the student's perspective it is still visible in Course Stream for student.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase instructorRemovesStudentPerspectiveAndComment in class InstructorRemovesStudentPerspectiveAndComment",e);
		}
	}

}
