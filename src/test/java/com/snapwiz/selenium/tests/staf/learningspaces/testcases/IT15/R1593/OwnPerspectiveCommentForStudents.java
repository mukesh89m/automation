package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1593;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiscussionWidget;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RunScheduledJobs;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class OwnPerspectiveCommentForStudents extends Driver{
	
	@Test
	public void ownPerspectiveCommentForStudents()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("8");//login as student
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().addMultiplePerspectiveForDWIneTextBook(5);//add 5 perspective
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			String commentText = new RandomString().randomstring(15); 
			new DiscussionWidget().commentOnPerspective(commentText, 0);	//add a comment to Perspective
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			List<WebElement> allComments = driver.findElements(By.cssSelector("a[title='Comments']"));
			for(WebElement comments: allComments)
			{
				if(comments.getText().contains("1"))
				{
					comments.click();	//click on Comments link to expand
					Thread.sleep(3000);
				}
			}
			
			new MouseHover().mouserhover("perspective-comment-block");	//mouse hover the perspective comment block
			List<WebElement> allArrows = driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
			allArrows.get(2).click(); //click on arrow of comment
			Thread.sleep(3000);
			//TC row no. 5
			String removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				Assert.fail("\"Remove Comment\" option is not there for removing the comment.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-hide-comment")));//remove the comment
			Thread.sleep(3000);
			//TC row no. 6
			String message = driver.findElement(By.className("perspective-comment-block")).getText();
			if(!message.contains("This post has been removed."))
			{
				Assert.fail("After removing the comment the required message doesnt appear.");
			}
			List<WebElement> allComments1 = driver.findElements(By.cssSelector("a[title='Comments']"));
			//TC row no. 7
			if(!allComments1.get(4).getText().contains("1"))
			{
				Assert.fail("After removing the comment the comment count gets changed.");
			}
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			new MouseHover().mouserhover("ls-stream-post-comment");	//mouse hover the perspective block
			List<WebElement> allArrows1 = driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allArrows1.get(0)); //click on arrow icon for perspective
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-perspective-hide")));//remove the perspective
			Thread.sleep(1000);
			//TC row no. 10
			String message1 = driver.findElement(By.className("removed-perspective-block")).getText();
			if(!message1.contains("This perspective has been removed."))
			{
				Assert.fail("After removing the perspective the required message doesnt appear.");
			}
			//TC row no. 11
			String perspectiveCount = driver.findElement(By.cssSelector("a[title='Perspectives']")).getText();
			if(!perspectiveCount.contains("5"))
			{
				Assert.fail("After removing the perspective the Perspectives count gets changed.");
			}
			
			new Navigator().NavigateTo("Dashboard");	//navigate to Dashboard
			//TC row no. 12
			String score = driver.findElement(By.className("percent")).getText();
			if(!score.contains("Compared to your classmates"))
			{
				Assert.fail("After removing the perspective the Participation score changes in student Dashboard.");
			}

			new RunScheduledJobs().runScheduledJobsForDashboard();

			new LoginUsingLTI().ltiLogin("8_1");//login as instructor
			//TC row no. 13
			String score1 = driver.findElement(By.className("number")).getText();
			if(!score1.contains("Contributions per student"))
			{
				Assert.fail("After removing the perspective the Participation score changes in instructor Dashboard.");
			}


			new LoginUsingLTI().ltiLogin("8");//login as student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 14
			int posts = driver.findElements(By.className("ls-stream-post__action")).size();
			if(posts != 4)//there will be four posts remaining
			{
				Assert.fail("After removing the perspective the Course stream entry for perspective doesnt get deleted.");
			}
			driver.findElement(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']")).click();	//click on Jump out icon
			Thread.sleep(2000);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
            new DiscussionWidget().clickOnArrowIconForPerspective(0, 0);//click on arrow icon for perspective
			//TC row no. 15-16
			boolean removePer1 = new DiscussionWidget().removePerspectiveTextVerify();
			if(removePer1 == false)
			{
				Assert.fail("\"Remove Perspective\" option is not there for removing the comment.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-perspective-hide")));
			Thread.sleep(3000);
			//TC row no. 17
			String message2 = driver.findElement(By.className("removed-perspective-block")).getText();
			if(!message2.contains("This perspective has been removed."))
			{
				Assert.fail("After removing the perspective the required message doesnt appear.");
			}
			//TC row no. 18
			String perspectiveCount1 = driver.findElement(By.cssSelector("a[title='Perspectives']")).getText();
			if(!perspectiveCount1.contains("5"))
			{
				Assert.fail("After removing the perspective the Perspectives count gets changed.");
			}
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 19
			int posts1 = driver.findElements(By.className("ls-stream-post__action")).size();
			if(posts1 != 3)//there will be three posts remaining
			{
				Assert.fail("After removing the 2nd perspective the Course stream entry for perspective doesnt get deleted.");
			}
			new Navigator().NavigateTo("Dashboard");	//navigate to Dashboard
			//TC row no. 21
			String score2 = driver.findElement(By.className("percent")).getText();
			if(!score2.contains("Compared to your classmates"))
			{
				Assert.fail("After removing the 2nd perspective the Participation score changes in student Dashboard.");
			}
			
			new RunScheduledJobs().runScheduledJobsForDashboard();

			new LoginUsingLTI().ltiLogin("8_1");//login as instructor
			//TC row no. 22
			String score3 = driver.findElement(By.className("number")).getText();
			if(!score3.contains("Contributions per student"))
			{
				Assert.fail("After removing the perspective the Participation score changes in instructor Dashboard.");
			}

			new LoginUsingLTI().ltiLogin("8");//login as student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			driver.findElement(By.className("ls-dropdown__toggle")).click();	//click on arow icon
			Thread.sleep(3000);
			//TC row no. 23-24
			String removePer2 = driver.findElement(By.className("ls-hide-post")).getText();
			if(!removePer2.contains("Remove Perspective"))
			{
				Assert.fail("\"Remove Perspective\" option is not there for removing the comment from Course Stream.");
			}
			driver.findElement(By.className("ls-hide-post")).click(); //remove the perspective
			Thread.sleep(3000);
            driver.findElement(By.className("ls-dropdown__toggle")).click();	//click on arow icon
            driver.findElement(By.className("ls-hide-post")).click(); //remove the perspective
            Thread.sleep(1000);
            driver.findElement(By.className("ls-dropdown__toggle")).click();	//click on arow icon
            driver.findElement(By.className("ls-hide-post")).click(); //remove the perspective
            Thread.sleep(1000);
			//TC row no. 25
			int posts2 = driver.findElements(By.className("ls-stream-post__action")).size();
			if(posts2 != 0)
			{
				Assert.fail("After clicking \"Remove Perspective\" the 3rd perspective from Course stream the perspective doesnt get deleted from Course stream.");
			}
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("View all perspectives"))); //clicking on View all perspectives link
			Thread.sleep(3000);
			//TC row no. 26
			int message3 = driver.findElements(By.className("removed-perspective-block")).size();
			if(message3 != 5)
			{
				Assert.fail("After removing the perspective from course stream  the required message doesnt appear in the corresponding DW.");
			}
			//TC row no. 27
			String perspectiveCount2 = driver.findElement(By.cssSelector("a[title='Perspectives']")).getText();
			if(!perspectiveCount2.contains("5"))
			{
				Assert.fail("After removing the 5th perspective from Course Stream the Perspectives count gets changed.");
			}
			new Navigator().NavigateTo("Dashboard");	//navigate to Dashboard
			//TC row no. 28
			String score4 = driver.findElement(By.className("percent")).getText();
			if(!score4.contains("Compared to your classmates"))
			{
				Assert.fail("After removing the 3rd perspective from Course Stream the Participation score changes in student Dashboard.");
			}
			
			new RunScheduledJobs().runScheduledJobsForDashboard();
			
			new LoginUsingLTI().ltiLogin("8_1");//login as instructor
			//TC row no. 29
			String score5 = driver.findElement(By.className("number")).getText();
			if(!score5.contains("Contributions per student"))
			{
				Assert.fail("After removing the 3rd perspective from Course Stream the Participation score changes in instructor Dashboard.");
			}
			
			new LoginUsingLTI().ltiLogin("8_2");//login as student
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("View all perspectives"))); //clicking on View all perspectives link
			Thread.sleep(3000);
			//TC row no. 30
			int perspective1 = driver.findElements(By.className("ls-comment-entry")).size();
			if(perspective1 != 0)
			{
				Assert.fail("After removing perspective the perspectives are not hidden for other students.");
			}
			//TC row no. 31
			int message4 = driver.findElements(By.className("removed-perspective-block")).size();
			if(message4 != 5)
			{
				Assert.fail("After removing perspective by one student the message for removed perspective is not present for other students .");
			}
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 32
			int posts3 = driver.findElements(By.className("ls-stream-post__action")).size();
			if(posts3 != 0)
			{
				Assert.fail("After removing perspective by one student the Course Stream entries are not deleted for other students .");
			}
			
			new LoginUsingLTI().ltiLogin("8_1");//login as instructor
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("View all perspectives"))); //clicking on View all perspectives link
			Thread.sleep(3000);
			//TC row no. 33
			int perspective2 = driver.findElements(By.className("ls-comment-entry")).size();
			if(perspective2 != 0)
			{
				Assert.fail("After removing perspective the perspectives are not hidden for instructor.");
			}
			//TC row no. 34
			int message5 = driver.findElements(By.className("removed-perspective-block")).size();
			if(message5 != 5)
			{
				Assert.fail("After removing perspective by one student the message for removed perspective is not present for instructor .");
			}
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 35
			int posts4 = driver.findElements(By.className("ls-stream-post__action")).size();
			if(posts4 != 0)
			{
				Assert.fail("After removing perspective by one student the Course Stream entries are not deleted for instructor.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase ownPerspectiveCommentForStudents in class OwnPerspectiveCommentForStudents",e);
		}
	}

}
