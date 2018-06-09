package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class StudentShouldBeAbleToViewDetailsOfDWAssignment extends Driver{

	@Test
	public void studentShouldBeAbleToViewDetailsOfDWAssignment()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("380_1");		//create a student
			new LoginUsingLTI().ltiLogin("380");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(380);

			
			new LoginUsingLTI().ltiLogin("380_1");		//login as student
			new Navigator().NavigateTo("Assignments"); 
			//TC row no. 380
			String posted = driver.findElement(By.className("ls-assignment-post-label")).getText();
			if(!posted.equals("posted an assignment."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the entry for DW assignment is not available in Student side.");
			}
			//TC row no. 381
			int profilePic = driver.findElements(By.className("prof-icon-image")).size();
			if(profilePic == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the instructor thumbnail for DW assignment is not available in Student side.");
			}
			//TC row no. 382
			String instructorName = driver.findElement(By.className("ls-assignment-item-author-name")).getText();
			if(instructorName == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the instructor name who has posted DW assignment is not available in Student side.");
			}
			//TC row no. 383
			int bookmarkIcon = driver.findElements(By.cssSelector("span[class='ls-assignment-bookmark not-bookmarked']")).size();
			if(bookmarkIcon == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the symbol to bookmark DW assignment is not available in Student side..");
			}
			//TC row no. 384
			String DWAssignmentName = driver.findElement(By.className("ls-stream-learning-activity-title")).getText();
			if(DWAssignmentName == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the DW Assignment Name is not available in Student side.");
			}
			//TC row no. 386
			String DWAssignmentIcon = driver.findElement(By.className("ls-assignment-learning-activity-block")).getAttribute("innerHTML");
			if(!DWAssignmentIcon.contains("home-work-icon.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the DW Assignment icon is not available in Student side.");
			}
			//TC row no. 386
			driver.findElement(By.cssSelector("span[class='learning-activity-title']")).click();//click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
			String tab =  driver.findElement(By.xpath("(//span[@class='tab_title'])[4]")).getText();
			if(!tab.contains("Discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page Name of the learning activity doesnt appear as link in Student side.");
			}
			new Navigator().NavigateTo("Assignments"); 
			//TC row no. 386
			String assignmentLinkIcon = driver.findElement(By.cssSelector("div[class='discussion-icon card-icons']")).getCssValue("height");
			if(!assignmentLinkIcon.contains("20px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page Name of the learning activity doesnt appear with icon in Student side..");
			}
			//TC row no. 387
			String DWNameFormat = driver.findElement(By.cssSelector("span[class='learning-activity-title']")).getText();
			if(!DWNameFormat.contains("D1 - "))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Format of DW assignment link is not D1  - <Discussion text> in Student side..");
			}
			//TC row no. 385
			String description = driver.findElement(By.id("idb-additional-note-section")).getText();
			if(!description.contains("Description:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Assignment description is not displayed with label \"Description:\" in Student side.");
			}
			//TC row no. 389
			String gradingPolicy = driver.findElement(By.className("ls-assignment-grading-desc")).getText();
			if(!gradingPolicy.contains("Assignment Reference:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Grading policy description is not displayed with label \"Grading Policy:\" in Student side.");
			}
			//TC row no. 392 393
			boolean dueDate = driver.findElement(By.className("ls-assignment-date-block")).isDisplayed();
			if(dueDate == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Date and time for DW assignment is not displayed in Student side.");
			}
			//TC row no. 395
			String status = driver.findElement(By.className("ls-assignment-status")).getText();
			if(!status.contains("Not Started"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the status for DW assignment is not Not Started in Student side.");
			}
			//TC row no. 396
			String likeLink = driver.findElement(By.className("js-post-like")).getText();
			if(!likeLink.contains("Like"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Like option is not available under DW assignment in Student side.");
			}
			if(!likeLink.contains("0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Like count is not available under DW assignment in Student side.");
			}
			String comment = driver.findElement(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']")).getText();
			if(!comment.contains("Comments"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Comment option is not available under DW assignment in Student side.");
			}
			if(!comment.contains("0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Comment count is not available under DW assignment in Student side.");
			}
			driver.findElement(By.className("ls-post-like-link")).click();	// click on Like
			Thread.sleep(2000);
			//TC row no. 397
			String count = driver.findElement(By.className("ls-post-like-count")).getText();
			if(!count.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page Like count for DW assignment doesnt update if we Hit Like.");
			}
			//TC row no. 398
			String unlike = driver.findElement(By.className("ls-post-like-link")).getText();
			if(!unlike.contains("Unlike"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page for a DW assignment the like option doesnt update to Unlike after we hit Like in Student side.");
			}
			driver.findElement(By.cssSelector("a[title='Comments']")).click();	//click on Comment option
			Thread.sleep(2000);
			//TC row no. 399
			boolean isExpanded = driver.findElement(By.cssSelector("form[class='ls-media ls-stream-post__comments-form']")).isDisplayed();
			if(isExpanded == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Comment block for DW assignment doesnt expand after clicking on Comment in Assignment page in Student side.");
			}

			String commentText ="ghsdfsghjsdg";
			driver.findElement(By.xpath("//div[@class='ls-textarea-focus ls-stream-comment-box redactor_editor']")).sendKeys(commentText);
			driver.findElement(By.className("post-comment")).click();

			//TC row no. 346
			String commString = driver.findElement(By.className("ls-stream-post__comment-text")).getText();
			if(!commString.contains(commentText))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page instructor is unable post any comment for DW assignment.");
			}
			//TC row no. 347
			String likeLinkForComment = driver.findElement(By.cssSelector("span[class='ls-comment-like-count']")).getText();
			if(!likeLinkForComment.contains("0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Like option for a comment is not available under DW assignment.");
			}
			//TC row no. 347
			List<WebElement> ageForComment = driver.findElements(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']"));
			if(ageForComment.get(0).getText()==null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the age for a comment is not available under DW assignment in Student side.");
			}
			driver.findElement(By.cssSelector("span.ls-post-like-count")).click();//like the posted comment
			Thread.sleep(2000);
			//TC row no. 348
			String count1 = driver.findElement(By.className("ls-post-like-count")).getText();
			if(!count1.contains("0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page Like count for comment for DW assignment doesnt update if we Hit Like in Student side.");
			}
			//TC row no. 349
			String unlike1 = driver.findElement(By.className("ls-post-like-count")).getText();
			if(!unlike1.contains("0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page for a DW assignment the like option for a comment doesnt update to Unlike after we hit Like in Student side..");
			}

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase instructorShouldBeAbleToViewDetailsOfDWAssignment in class InstructorShouldBeAbleToViewDetailsOfDWAssignment.",e);
		}
	}
}
