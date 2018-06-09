package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class VerifySocialElementsOfDWAssignment extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void verifySocialElementsOfDWAssignment()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("185_1");		//create a student
			new LoginUsingLTI().ltiLogin("185_2");		//create a student
			new LoginUsingLTI().ltiLogin("185");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(185);
			
			new LoginUsingLTI().ltiLogin("185_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			Thread.sleep(2000);
			String perspective = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective);
			
			new LoginUsingLTI().ltiLogin("185_2");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			Thread.sleep(2000);
			String perspective1 = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective1);
			
			new LoginUsingLTI().ltiLogin("185");		//login as instructor
			new Assignment().viewResponseOfDW();
			Thread.sleep(2000);
			//TC row no 185
			String likeLink = driver.findElement(By.className("js-discussion-post-like")).getText();
			if(!likeLink.contains("Like"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Like link for the DW is absent in DW assignment response page.");
			}
			if(!likeLink.contains("0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Like count for the DW is absent in DW assignment response page.");
			}
			String perspectiveLink = driver.findElement(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")).getText();
			if(!perspectiveLink.contains("Perspectives"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Perspectives  link is absent in DW assignment response page.");
			}
			if(perspectiveLink.contains("0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Perspective count is absent in DW assignment response page.");
			}
			//TC row no. 187
			boolean isExpanded = driver.findElement(By.className("ls-stream-post-comment  ")).isDisplayed();
			if(isExpanded == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Pers[ective bolck is not expanded in DW assignment response page.");
			}
			driver.findElement(By.className("ls-discussion-like-link")).click();
			Thread.sleep(2000);
			//TC row no. 186
			String likeLink1 = driver.findElement(By.className("js-discussion-post-like")).getText();
			if(likeLink1.contains("Unlike"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The instructor is able to click \"Like\" for the DW.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives")));	//click on Perspective link
			Thread.sleep(2000);
			int addPerspective = driver.findElements(By.name("perspective")).size();
			if(addPerspective != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The instructor is able to add Perspective for the DW.");
			}
			//TC row no. 188
			String addedPerspective = driver.findElement(By.className("ls-comment-entry")).getText();
			if(!addedPerspective.contains(perspective))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Perspectives entered by the specific student doesnt show up in DW assignment response page.");
			}
			//TC row no. 189
			String addedPerspective1 = driver.findElement(By.className("ls-comment-entry")).getText();
			if(addedPerspective1.contains(perspective1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Perspectives entered by other student is also show up in DW assignment response page.");
			}
			//TC row no 195
			String likeLinkOnPerspective = driver.findElement(By.className("js-discussion-comment-like")).getText();
			if(!likeLinkOnPerspective.contains("Like"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Like link for perspective is absent in DW assignment response page.");
			}
			if(!likeLinkOnPerspective.contains("0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Like count for perspective is absent in DW assignment response page.");
			}
			String commentLink = driver.findElement(By.cssSelector("a[class='ls-content-post__footer-comment-link js-toggle-comments']")).getText();
			if(!commentLink.contains("Comments"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Comments link for the perspective is absent in DW assignment response page.");
			}
			if(!commentLink.contains("0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Comments count for the perspective is absent in DW assignment response page.");
			}
			//TC row no. 194
			List<WebElement> time = driver.findElements(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']"));
			if(time.get(2).getText() == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Date and time for perspective is not  displayed for the perspective in DW assignment response page.");
			}
			List<WebElement> allLikes = driver.findElements(By.className("ls-discussion-like-link"));
			if(allLikes.size() > 1)
			{
				for(WebElement l:allLikes)
				{
					if(l.isDisplayed() == true)
					{
						l.click();// click on Like for perspective
						Thread.sleep(2000);
					}
				}
			}
			else
			{
				driver.findElement(By.className("ls-discussion-like-link")).click();	// click on Like for perspective
				Thread.sleep(2000);
			}
			//TC row no. 196
			String unlike = driver.findElement(By.className("ls-discussion-like-link")).getText();
			if(unlike.contains("Unlike"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is able to like over perspective in DW assignment response page.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifySocialElementsOfDWAssignment in class VerifySocialElementsOfDWAssignment.",e);
		}
	}
	

	@Test(priority = 2, enabled = true)
	public void orderOfPerspective()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("190_1");		//create a student
			new LoginUsingLTI().ltiLogin("190_2");		//create a student
			new LoginUsingLTI().ltiLogin("190_3");		//create a student
			
			new LoginUsingLTI().ltiLogin("190");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(190);
			
			new LoginUsingLTI().ltiLogin("190_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
			String perspective = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective);
			
			new LoginUsingLTI().ltiLogin("190");		//login as instructor
			new Assignment().viewResponseOfDW();	//click on View Response link
			Thread.sleep(2000);
			//TC row no. 193
			int size = driver.findElements(By.id("view-other-perspective-link")).size();
			if(size != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("View other perspective link is not shown in DW assignment response page.");
			}
			
			new LoginUsingLTI().ltiLogin("190_2");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
			String perspective1 = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective1);

			new LoginUsingLTI().ltiLogin("190_3");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
			String perspective2 = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective2);
			
			new LoginUsingLTI().ltiLogin("190");		//login as instructor
			new Assignment().viewResponseOfDW();	//click on View Response link
			Thread.sleep(3000);
			driver.findElement(By.id("view-other-perspective-link")).click();	//click on View Response link
			Thread.sleep(2000);
			//TC row no. 193
			List<WebElement> allPerspective = driver.findElements(By.className("ls-comment-entry"));
			if(!allPerspective.get(0).getText().contains(perspective) || !allPerspective.get(1).getText().contains(perspective1) || !allPerspective.get(2).getText().contains(perspective2))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The perspectives is not ordered based on the age with the earliest perspective on the top and the latest at the bottom in DW assignment response page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase orderOfPerspective in class VerifySocialElementsOfDWAssignment.",e);
		}
	}
	@Test(priority = 3, enabled = true)
	public void clickOnCommentLinkOnPerspective() {
		try {
			new LoginUsingLTI().ltiLogin("197_1");        //create a student
			new LoginUsingLTI().ltiLogin("197_2");        //create a student
			new LoginUsingLTI().ltiLogin("197_3");        //create a student

			new LoginUsingLTI().ltiLogin("197");        //login as instructor
			new Navigator().NavigateTo("eTextbook");    //navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(197);

			new LoginUsingLTI().ltiLogin("197_1");        //login as student
			new Navigator().NavigateTo("Assignments");    //navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();    //click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
			String perspective = new RandomString().randomstring(10);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective);
			new Navigator().NavigateTo("Assignments");    //navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();    //click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
			String commentText = new RandomString().randomstring(10);
			new DiscussionWidget().studentCommentOnPerspective(commentText, 0);//student adds comment to the perspective

			new LoginUsingLTI().ltiLogin("197_2");        //login as student
			new Navigator().NavigateTo("Assignments");    //navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();    //click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
			String perspective1 = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective1);

			new LoginUsingLTI().ltiLogin("197_3");        //login as student
			new Navigator().NavigateTo("Assignments");    //navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();    //click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
			String perspective2 = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective2);

			new LoginUsingLTI().ltiLogin("197");        //login as instructor
			new Assignment().viewResponseOfDW();    //click on View Response link
			Thread.sleep(3000);
			//TC row no. 201
			String viewOtherPers = driver.findElement(By.id("view-other-perspective-link")).getText();
			if (!viewOtherPers.contains("View other perspective")) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The link \"View Other Perspective\" doesnt appear in DW assignment response page.");
			}
			driver.findElement(By.id("view-other-perspective-link")).click();    //click
			Thread.sleep(2000);
			List<WebElement> allPerspective = driver.findElements(By.className("ls-comment-entry"));
			List<WebElement> allComments = driver.findElements(By.cssSelector("a[class='ls-content-post__footer-comment-link js-toggle-comments']"));
			allComments.get(0).click();    //click on Comment
			Thread.sleep(6000);
			List<WebElement> allCommentText = driver.findElements(By.cssSelector("div[class='ls-perspctive-comments-posted']"));
			//TC row no. 197
			if (!allCommentText.get(0).getText().contains(commentText)) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Comments link the comment doesnt appear expanded assignment response page.");
			}
			List<WebElement> allAges = driver.findElements(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']"));
			//TC row no. 198
			if (allAges.get(3).getText() == null) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Comment on perspective by any user is not displayed with age in  assignment response page.");
			}
			String like = driver.findElement(By.cssSelector("a[class='js-comment-like']")).getText();
			//TC row no. 199
			if (!like.contains("0")) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On Like count under the comment for perspective is not dispalyed in assignment response page.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='js-comment-like']")));    //click on Like for comment on perspective
			Thread.sleep(5000);
			String like1 = driver.findElement(By.cssSelector("a[class='js-comment-like']")).getText();
			//TC row no. 200
			if (like1.contains("1")) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is able to like comment over perspective in assignment response page.");
			}
			//TC row no. 202
			if (!allPerspective.get(0).getText().contains(perspective) || !allPerspective.get(1).getText().contains(perspective1) || !allPerspective.get(2).getText().contains(perspective2)) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("All the perspective added by other students is not displayed below the perspectives of particular student in DW assignment response page.");
			}
			//TC row no. 204
			int hidePerspective = driver.findElements(By.cssSelector("a[title='Hide other perspective']")).size();
			if (hidePerspective == 0) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The link \"Hide other perspective\" doesnt appear in DW assignment response page.");
			}
		} catch (Exception e) {
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase clickOnCommentLinkOnPerspective in class VerifySocialElementsOfDWAssignment.", e);
		}
	}
}
