package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1593;

import java.util.List;

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
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class OwnPerspectiveCommentForInstructor extends Driver{
	
	@Test
	public void ownPerspectiveCommentForInstructor()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("55");//login as instructor
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().addMultiplePerspectiveForDWIneTextBook(3);//add 3 perspective
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
			//TC row no. 55
			String removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, \"Remove Comment\" option is not there for removing the comment.");
			}	
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-hide-comment")));//remove the comment
			Thread.sleep(3000);
			//TC row no. 56
			String message = driver.findElement(By.className("perspective-comment-block")).getText();
			if(!message.contains("This post has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, After removing the comment the required message doesnt appear.");
			}
			List<WebElement> allComments1 = driver.findElements(By.cssSelector("a[title='Comments']"));
			//TC row no. 57
			if(!allComments1.get(4).getText().contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, After removing the comment the comment count gets changed.");
			}
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			new MouseHover().mouserhover("ls-stream-post-comment");	//mouse hover the perspective block
			List<WebElement> allArrows1 = driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allArrows1.get(0)); //click on arrow icon for perspective//TC row no. 58
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-perspective-hide")));//remove the perspective//TC row no. 59
			Thread.sleep(1000);
			//TC row no. 60
			String message1 = driver.findElement(By.className("removed-perspective-block")).getText();
			if(!message1.contains("This perspective has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, After removing the perspective the required message doesnt appear.");
			}
			//TC row no. 61
			String perspectiveCount = driver.findElement(By.cssSelector("a[title='Perspectives']")).getText();
			if(!perspectiveCount.contains("3"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, After removing the perspective the Perspectives count gets changed.");
			}
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 62
			int posts = driver.findElements(By.className("ls-stream-post__action")).size();
			if(posts != 2)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, After removing the perspective the Course stream entry for perspective doesnt get deleted.");
			}
			driver.findElement(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']")).click();	//click on Jump out icon
			Thread.sleep(5000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Perspectives']")));
            Thread.sleep(3000);
            new DiscussionWidget().clickOnArrowIconForPerspective(0, 0);
            //TC row no. 64
            String removePer1 = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",driver.findElement(By.className("ls-perspective-hide")));

			if(!removePer1.contains("Remove Perspective"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, \"Remove Perspective\" option is not there for removing the Perspective.");
			}

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-perspective-hide")));//hide the Perspective
			Thread.sleep(3000);
			//TC row no. 65
			String message2 = driver.findElement(By.className("removed-perspective-block")).getText();
			if(!message2.contains("This perspective has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, After removing the perspective the required message doesnt appear.");
			}
			//TC row no. 66
			String perspectiveCount1 = driver.findElement(By.cssSelector("a[title='Perspectives']")).getText();
			if(!perspectiveCount1.contains("3"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, After removing the perspective the Perspectives count gets changed.");
			}
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 68
			int posts1 = driver.findElements(By.className("ls-stream-post__action")).size();
			if(posts1 != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, After removing the 2nd perspective the Course stream entry for perspective doesnt get deleted.");
			}
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			driver.findElement(By.className("ls-dropdown__toggle")).click();	//click on arow icon//TC row no. 69
			Thread.sleep(3000);
			//TC row no. 70
			String removePer2 = driver.findElement(By.className("ls-hide-post")).getText();
			if(!removePer2.contains("Remove Perspective"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, \"Remove Perspective\" option is not there for removing the comment from Course Stream.");
			}
			driver.findElement(By.className("ls-hide-post")).click(); //remove the perspective
			Thread.sleep(3000);
			//TC row no. 71
			int posts2 = driver.findElements(By.className("ls-stream-post__action")).size();
			if(posts2 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, After clicking \"Remove Perspective\" the 3rd perspective from Course stream the perspective doesnt get deleted from Course stream.");
			}
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("View all perspectives"))); //clicking on View all perspectives link
			Thread.sleep(3000);
			//TC row no. 72
			int message3 = driver.findElements(By.className("removed-perspective-block")).size();
			if(message3 != 3)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, After removing the perspective from course stream  the required message doesnt appear in the corresponding DW.");
			}
			//TC row no. 73
			String perspectiveCount2 = driver.findElement(By.cssSelector("a[title='Perspectives']")).getText();
			if(!perspectiveCount2.contains("3"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For instructor, After removing the 3rd perspective from Course Stream the Perspectives count gets changed.");
			}
			
			new LoginUsingLTI().ltiLogin("55_1");//login as instructor
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			//TC row no. 74
			int perspective1 = driver.findElements(By.className("ls-comment-entry")).size();
			if(perspective1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the perspective it is still visible for other instructor in that particular DW chapter.");
			}
			int message4 = driver.findElements(By.className("removed-perspective-block")).size();
			if(message4 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the perspective the removal message is not visible for other instructor in that particular DW chapter.");
			}
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 75
			int posts4 = driver.findElements(By.className("ls-stream-post__action")).size();
			if(posts4 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the perspective it is still visible in Course Stream for other instructor.");
			}
			
			
			new LoginUsingLTI().ltiLogin("55_2");//login as student
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			//TC row no. 76
			int perspective2 = driver.findElements(By.className("ls-comment-entry")).size();
			if(perspective2 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the perspective it is still visible for other instructor in that particular DW chapter.");
			}
			int message5 = driver.findElements(By.className("removed-perspective-block")).size();
			if(message5 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the perspective the removal message is not visible for student in that particular DW chapter.");
			}
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 77
			int posts3 = driver.findElements(By.className("ls-stream-post__action")).size();
			if(posts3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After an instructor removes the perspective it is still visible in Course Stream for other instructor.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ownPerspectiveCommentForInstructor in class OwnPerspectiveCommentForInstructor",e);
		}
	}

}
