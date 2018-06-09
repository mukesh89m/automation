package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

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
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Widget;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class VerifyingDWOverLessonWhileAssignmentStatusChanges extends Driver{
	
	@Test
	public void verifyingDWOverLessonWhileAssignmentStatusChanges()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("367_1");		//create a student
			new LoginUsingLTI().ltiLogin("367");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new Widget().perspectiveAdd();	//TC row no. 367
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(367);
			WebElement element=driver.findElement(By.partialLinkText("Perspectives"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
			Thread.sleep(500);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("div[class='ls-add-perspective-block ls-content-comment-body']")).sendKeys("sdfghjk dfghj");
			//new Widget().perspectiveAdd();	//TC row no. 369
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			String commentText = new RandomString().randomstring(10);
			new DiscussionWidget().commentOnPerspective(commentText, 0);	//comment on perspective
			String text2 = new TextFetch().textfetchbyclass("ls-perspctive-comments-posted");
			//TC row no. 369
			if(!text2.contains(commentText))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is not able to add the Comment on perspective.");
			}
			
			new Click().clickByclassname("js-comment-like");	//click on Like for Comment
			String text1 = new TextFetch().textfetchbyclass("js-comment-like");
			//TC row no. 370
			if(!text1.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is not able to like the Comment on perspective.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("js-discussion-comment-like")));//click on like for perspective for DW in eTextbook
			Thread.sleep(2000);
			String text4 = new TextFetch().textfetchbyclass("js-discussion-comment-like");
			//TC row no. 370
			if(!text4.contains("Unlike"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is not able to like the DW after assigning it.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("js-discussion-post-like")));//click on like for DW in eTextbook
			
			Thread.sleep(2000);
			String text3 = new TextFetch().textfetchbyclass("js-discussion-post-like");
			//TC row no. 370
			if(!text3.contains("Unlike"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is not able to like the DW after assigning it.");
			}
			
			
			new LoginUsingLTI().ltiLogin("367_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));



			new LoginUsingLTI().ltiLogin("367");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new Widget().perspectiveAdd();	//TC row no. 372
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			String commentText1 = new RandomString().randomstring(10);
			System.out.println("Comment: "+commentText1);
			new DiscussionWidget().commentOnPerspective(commentText1, 0);	//comment on perspective
			List<WebElement> text5 = driver.findElements(By.className("ls-perspctive-comments-posted"));
			//TC row no. 372
			/*if(!text5.get(1).getText().contains(commentText1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is not able to add the Comment on perspective for a In progess DW assignment.");
			}*/
			
			List<WebElement> allCommentLike = driver.findElements(By.className("js-comment-like"));
			allCommentLike.get(1).click();	//click on like for recently added comment
			//TC row no. 373
			List<WebElement> allCommentText = driver.findElements(By.className("js-comment-like"));
			if(!allCommentText.get(1).getText().contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is not able to like the Comment on perspective for a In progess DW assignment.");
			}
			List<WebElement> allPerspectiveLike = driver.findElements(By.className("js-discussion-comment-like"));
			allPerspectiveLike.get(1).click();
			Thread.sleep(2000);
			List<WebElement> allPerspectiveText = driver.findElements(By.className("js-discussion-comment-like"));
			//TC row no. 373
			if(!allPerspectiveText.get(1).getText().contains("Unlike"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is not able to like the DW after assigning it for a In progess DW assignment.");
			}	
			
			new LoginUsingLTI().ltiLogin("367_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));

			String perspective = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective);
			
			
			new LoginUsingLTI().ltiLogin("367");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new Widget().perspectiveAdd();	//TC row no. 375
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			String commentText2 = new RandomString().randomstring(10);
			new DiscussionWidget().commentOnPerspective(commentText2, 0);	//comment on perspective
			List<WebElement> text6 = driver.findElements(By.className("ls-perspctive-comments-posted"));
			//TC row no. 375
			if(!text6.get(0).getText().contains(commentText2))//only 2 perspective will be visible so index for comment is 0
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is not able to add the Comment on perspective for a Submitted DW assignment.");
			}
			
			List<WebElement> allCommentLike1 = driver.findElements(By.className("js-comment-like"));
			allCommentLike1.get(0).click();	//click on like for recently added comment
			//TC row no. 376
			List<WebElement> allCommentText1 = driver.findElements(By.className("js-comment-like"));
			if(!allCommentText1.get(0).getText().contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is not able to like the Comment on perspective for a Submitted DW assignment.");
			}
			List<WebElement> allPerspectiveLike1 = driver.findElements(By.className("js-discussion-comment-like"));
			allPerspectiveLike1.get(1).click();//click on like for perspective
			Thread.sleep(2000);
			List<WebElement> allPerspectiveText1 = driver.findElements(By.className("js-discussion-comment-like"));
			//TC row no. 376
			if(!allPerspectiveText1.get(1).getText().contains("Unlike"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is not able to like the DW after assigning it for a Submitted DW assignment.");
			}
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.cssSelector("span[title='View Student Responses']")).click();	//click on View Student Responses
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("div[title='Release Grade for All']")).click();	//click on Release Grade for All
			Thread.sleep(2000);
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new Widget().perspectiveAdd();	//TC row no. 378
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			String commentText3 = new RandomString().randomstring(10);
			new DiscussionWidget().commentOnPerspective(commentText3, 0);	//comment on perspective
			List<WebElement> text7 = driver.findElements(By.className("ls-perspctive-comments-posted"));
			//TC row no. 378
			if(!text7.get(1).getText().contains(commentText3))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is not able to add the Comment on perspective for a Graded DW assignment.");
			}
			
			List<WebElement> allCommentLike2 = driver.findElements(By.className("js-comment-like"));
            for(WebElement commentlike : allCommentLike2)
            {
                System.out.println("allCommentLike2: "+commentlike.getText());
            }
			//TC row no. 379
			allCommentLike2.get(1).click();	//click on like for recently added comment
			List<WebElement> allCommentText2 = driver.findElements(By.className("js-comment-like"));
            for(WebElement commentlcount : allCommentText2)
            {
                System.out.println("like count for comment: "+commentlcount.getText());
            }
			if(!allCommentText2.get(1).getText().contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is not able to like the Comment on perspective for a Graded DW assignment.");
			}
			List<WebElement> allPerspectiveLike2 = driver.findElements(By.className("js-discussion-comment-like"));
			allPerspectiveLike2.get(1).click();//click on like for perspective
			Thread.sleep(2000);
			List<WebElement> allPerspectiveText2 = driver.findElements(By.className("js-discussion-comment-like"));
			//TC row no. 379
			if(!allPerspectiveText2.get(1).getText().contains("Unlike"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is not able to like the DW after assigning it for a Graded DW assignment.");
			}

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyingDWOverLessonWhileAssignmentStatusChanges in class VerifyingDWOverLessonWhileAssignmentStatusChanges.",e);
		}
	}

}
