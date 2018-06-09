package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1593;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;


public class PerspectiveCommentForStudentsOnDWAssignment extends Driver{
	
	@Test
	public void perspectiveCommentForStudentsOnDWAssignment()
	{
		try
		{


			new LoginUsingLTI().ltiLogin("111_1");		//create a student
			new LoginUsingLTI().ltiLogin("111_2");		//create a student
			new LoginUsingLTI().ltiLogin("111");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(111);



			new LoginUsingLTI().ltiLogin("111_1");	//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
            WebDriverWait wait=new WebDriverWait(driver,300);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
			String perspective = new RandomString().randomstring(5);
            for(int i = 0; i<5; i++)
			    new DiscussionWidget().addPerspectiveForDWAssignment(perspective);
			//add comment to the perspective
			String commentText = new RandomString().randomstring(5);
            List<WebElement> allComments = driver.findElements(By.xpath("//a[@class='ls-content-post__footer-comment-link js-toggle-comments']"));
            Thread.sleep(5000);
            allComments.get(4).click();//click on Comments Link
            Thread.sleep(3000);
            driver.switchTo().activeElement().sendKeys(commentText + Keys.RETURN);
            Thread.sleep(3000);
            //new CommentOnPost().commentOnPost(commentText, 9);//comment is at 9th index
            driver.findElement(By.xpath("(//div[@class='ls-dropdown__toggle'])[6]")).click();
            driver.findElement(By.xpath("//a[text()='Remove Comment']")).click();

			//new DiscussionWidget().removeComment(1,1);
            Thread.sleep(4000);
			//TC row no. 112
			List<WebElement> allMessages = driver.findElements(By.className("perspective-comment-block"));
			if(!allMessages.get(0).getText().contains("This post has been removed."))
			{
				Assert.fail("After removing the comment the required message doesnt appear.");
			}
            List<WebElement> allComments2 = driver.findElements(By.xpath("//a[@class='ls-content-post__footer-comment-link js-toggle-comments']"));
            String str = driver.findElement(By.xpath("//a[@class='ls-content-post__footer-comment-link js-toggle-comments comments_selected']")).getText();
            //TC row no. 113
			if(!str.contains("1"))
			{
				Assert.fail("After removing the  comment count gets changed.");
			}
            new Navigator().NavigateTo("Assignments");	//navigate to Assignments
            driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
            new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
            new DiscussionWidget().removePerspective(0, 0);//remove the perspective	//TC row no. 114, 115

			//TC row no. 116
			List<WebElement> allMessages1 = driver.findElements(By.className("removed-perspective-block"));
			if(!allMessages1.get(0).getText().contains("This perspective has been removed."))
			{
				Assert.fail("After removing the perspective the required message doesnt appear.");
			}
			List<WebElement> allPerspectiveCount = driver.findElements(By.cssSelector("a[title='Perspectives']"));
			//TC row no. 118
			if(!allPerspectiveCount.get(allPerspectiveCount.size()-1).getText().contains("5"))
			{
				Assert.fail("After removing the perspective the Perspectives count gets changed.");
			}
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			//TC row no. 117
			String status = driver.findElement(By.className("ls-assignment-status")).getText();
			if(!status.contains("Submitted"))
			{
				Assert.fail("After removing the perspective the Assignment status is not Submitted.");
			}
			new Navigator().NavigateTo("Dashboard");	//navigate to Dashboard
			//TC row no. 119
			//String score = new TileTextInDashboard().studentParticipationScoreLowerText();
			String score = driver.findElement(By.className("percent")).getText();
			System.out.println("score:"+score);
			if(!score.contains("Compared to your classmates"))
				{
				Assert.fail("After removing the perspective the Participation score changes in student Dashboard.");
			}

			new RunScheduledJobs().runScheduledJobsForDashboard();

			new LoginUsingLTI().ltiLogin("111");		//login as instructor
			//TC row no. 120
			String score1 = driver.findElement(By.className("number")).getText();
			if(!score1.contains("Contributions per student"))
			{
				Assert.fail("After removing the perspective the Participation score changes in instructor Dashboard.");
			}

			new LoginUsingLTI().ltiLogin("111_2");//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
            WebDriverWait wait1=new WebDriverWait(driver,300);
            wait1.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
            new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
			//TC row no. 121
			int perspective2 = driver.findElements(By.className("ls-comment-entry")).size();
			if(perspective2 ==5)
			{
				Assert.fail("After an student removes the perspective it is still visible for other student.");
			}
			int message6 = driver.findElements(By.className("removed-perspective-block")).size();
			if(message6 == 0)
			{
				Assert.fail("After an student removes the perspective the removal message is not visible for other student.");
			}

			new LoginUsingLTI().ltiLogin("111");		//login as instructor
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
            driver.findElement(By.linkText("View all perspectives")).click();
            Thread.sleep(2000);
			//TC row no. 122
			int perspective3 = driver.findElements(By.className("ls-comment-entry")).size();
            System.out.println("perspective3: "+perspective3);
			if(perspective3 == 5)
			{
				Assert.fail("After an student removes the perspective it is still visible for instructor.");
			}
			int message3 = driver.findElements(By.className("removed-perspective-block")).size();
			if(message3 == 0)
			{
				Assert.fail("After an student removes the perspective the removal message is not visible for instructor.");
			}
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			//TC row no. 123
			String status1 = driver.findElement(By.className("ls-assignment-status")).getText();
			if(!status1.contains("Available for Students"))
			{
				Assert.fail("After removing the perspective the Assignment status is not Available for Students in instructor side.");
			}
			driver.findElement(By.className("ls-grade-book-assessment")).click();	//click on View Student Responses
			Thread.sleep(2000);
            new MouseHover().mouserhover("idb-question-manually-graded");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));  //click on View Responses
            Thread.sleep(2000);
			//TC row no. 124
			int perspective1 = driver.findElements(By.className("ls-comment-entry")).size();
			if(perspective1 == 0)
			{
				Assert.fail("In gradebook page of DW assignment the hidden perspectives are not shown.");
			}
			//TC row no. 125
			String visualIndicator = driver.findElement(By.className("ls-perspective-visual-indicator")).getCssValue("background-image");
			if(!visualIndicator.contains("perspective-visual-indicator.png"))
			{
				Assert.fail("In gradebook page of DW assignment the visual indicator is not shown for hidden perspectives.");
			}
			//TC row no. 126
			String perspectiveCount = driver.findElement(By.cssSelector("a[title='Perspectives']")).getText();
			if(!perspectiveCount.contains("5"))
			{
				Assert.fail("In gradebook page of DW assignment the perspective count changes if the student removes his perspective.");
			}
			List<WebElement> commentCount = driver.findElements(By.cssSelector("a[title='Comments']"));
			if(!commentCount.get(6).getText().contains("1"))//count at 6th index
			{
				Assert.fail("In gradebook page of DW assignment the comment count changes if the student removes his comment on perspective.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase perspectiveCommentForStudentsOnDWAssignment in class PerspectiveCommentForStudentsOnDWAssignment",e);
		}
	}

}
