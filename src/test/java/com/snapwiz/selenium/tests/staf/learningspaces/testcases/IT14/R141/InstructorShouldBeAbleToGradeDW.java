package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class InstructorShouldBeAbleToGradeDW extends Driver{
	
	@Test
	public void instructorShouldBeAbleToGradeDW()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("137_1");		//create a student
			new LoginUsingLTI().ltiLogin("137");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(137);
			
			new LoginUsingLTI().ltiLogin("137_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			Thread.sleep(2000);
			String perspective = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective);

			new LoginUsingLTI().ltiLogin("137");		//login as instructor
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.cssSelector("span[title='View Student Responses']")).click();	//click on View Student Responses
            Thread.sleep(2000);
			//TC row no. 137, 138
			String discussionTab = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!discussionTab.contains("Response - (shor)"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking \"View Student Responses\" the Discussion doesnt open in a new tab with name  \"Response - <Assignment Name>\" for DW assignment.");
			}
			//TC row no. 139
			String assignementHeader = driver.findElement(By.id("idb-gradeBook-title")).getText();
			if(!assignementHeader.equals("Assignment Responses"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Assignment Responses header is absent over Grading Page for DW assignment.");
			}
			String refreshIcon = driver.findElement(By.cssSelector("span[class='ins-assignment-button-sprite instructor-assignment-refresh']")).getCssValue("background-image");
			if(!refreshIcon.contains("refresn-and-new-assignment.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Icon to refresh is absent over Grading Page for DW assignment.");
			}
			String lastRefreshTime = driver.findElement(By.id("last-refresh-time")).getText();
			if(lastRefreshTime == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Last Refresh Time is absent over Grading Page for DW assignment.");
			}
			//TC row no. 140
			String assignemntName = driver.findElement(By.cssSelector("div[class='ls-assignment-name-block']")).getText();
			if(assignemntName == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Assignment name is absent in Assignment detail section over Grading Page for DW assignment.");
			}
			String icon = driver.findElement(By.cssSelector("img[align='left']")).getAttribute("src");
			if(!icon.contains("home-work-icon.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The icon is absent near the DW Assignment name.");
			}		
			String assignemntLink = driver.findElement(By.cssSelector("div[class='ls-resource-links ellipsis']")).getText();
			if(assignemntLink == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Assignment link is absent in Assignment detail seaction over Grading Page for DW assignment.");
			}
			String icon1 = driver.findElement(By.cssSelector("span[class='discussion-icon card-icons']")).getCssValue("height");
			if(!icon1.contains("20px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The icon is absent near the DW Assignment Link.");
			}
			String totalPoints = driver.findElement(By.cssSelector("span[class='ls-assignment-grading-title ls-assignment-total-points']")).getText();
			if(!totalPoints.contains("Total Points:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Total Points for gradable assignment is absent near the DW Assignment name.");
			}
			List<WebElement> likes = driver.findElements(By.cssSelector("span[class='ls-post-like-link']"));
			if(!likes.get(1).getText().contains("Like"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Like social Element is absent over Grading Page.");
			}
			List<WebElement> comments = driver.findElements(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']"));
			if(!comments.get(1).getText().contains("Comments"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Comments social Element is absent over Grading Page for DW assignment.");
			}
			String postMessageButton = driver.findElement(By.className("ls-post-a-message")).getText();
			if(!postMessageButton.contains("Post a message"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Post a message button is absent over Grading Page for DW assignment.");
			}
			String studentName = driver.findElement(By.cssSelector("span[class='idb-gradebook-assignment-username']")).getText();
			if(studentName == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Table listing rows for each student linked to assignment is absent over Grading Page for DW assignment.");
			}
			List<WebElement> status = driver.findElements(By.className("ls-assignment-status"));
			if(!status.get(1).getText().contains("Status"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Status of the assignment is absent over Grading Page for DW assignment.");
			}
			List<WebElement> dueDate = driver.findElements(By.className("ls-assignment-date-block"));
			if(!dueDate.get(1).getText().contains("Due Date"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Due date of assignment is absent over Grading Page for DW assignment.");
			}
			List<WebElement> accessibleAfter = driver.findElements(By.className("ls-assignment-accessible-after-title"));
			if(!accessibleAfter.get(1).getText().contains("Accessible After:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Accessible After date of assignment is absent over Grading Page for DW assignment.");
			}
			int statusBox = driver.findElements(By.className("ls-instructor-activity-cards-holder")).size();
			if(statusBox != 2)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Status box is absent over Grading Page for DW assignment.");
			}
			int releaseGrade = driver.findElements(By.cssSelector("div[class='idb-gradeBook-grades-section-release idb-gradeBook-release-grades-section idb-gradeBook-grades-release']")).size();
			if(releaseGrade != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Release grade button is absent over Grading Page for DW assignment.");
			}
            driver.findElement(By.cssSelector("a[resourcetype='discussion']")).click();//click on DW from Grading page
			Thread.sleep(5000);
			//TC row no. 141
			String discussionTab1 = driver.findElement(By.xpath("(//div[@class='tab active'])[2]")).getText();
			if(!discussionTab1.contains("Discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor doesnt get redirected to discussion tab view in the e-textbook on clicking DW link from Grading Page.");
			}
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.cssSelector("span[title='View Student Responses']")).click();	//click on View Student Responses
            Thread.sleep(2000);
            List<WebElement> allLikes = driver.findElements(By.cssSelector("span[class='ls-post-like-link']"));
			allLikes.get(1).click(); //click on Like
			Thread.sleep(2000);
			List<WebElement> allLikeCount = driver.findElements(By.cssSelector("span.ls-post-like-count"));
			//TC row no. 143
			String likeCount = allLikeCount.get(1).getText(); 
			if(!likeCount.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Like count doesnt get updated on clicking like for DW assignment from Grading Page.");
			}
			List<WebElement> allUnlike = driver.findElements(By.cssSelector("span.ls-post-like-link"));
			//TC row no. 144
			String unlike = allUnlike.get(1).getText(); 
			if(!unlike.contains("Unlike"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Like doesnt get updated to Unlike on clicking like for DW assignment from Grading Page.");
			}
			List<WebElement> allComments = driver.findElements(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']"));
			allComments.get(1).click();	//click on Comments
			List<WebElement> allCommentExpanded = driver.findElements(By.cssSelector("form[class='ls-media ls-stream-post__comments-form']"));
			//TC row no. 145
			boolean isCommentExpanded = allCommentExpanded.get(1).isDisplayed();
			if(isCommentExpanded == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Comment link the Comment block doesnt expand for DW assignment from Grading Page.");
			}
			allComments.get(1).click();	//collapse Comments
			String comment = new RandomString().randomstring(5);
			new DiscussionWidget().commentOnDWFromGradingPage(comment);	//TC row no. 147
			allComments.get(1).click();	//collapse Comments
			List<WebElement> allCommentSummary = driver.findElements(By.cssSelector("div[class='ls-stream-post__comments']"));
			//TC row no. 142
			boolean commentSummary = allCommentSummary.get(1).isDisplayed();
			if(commentSummary == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Social elements is not available in summary format for DW assignment in Grading Page.");
			}
			allComments.get(1).click();	//expand Comments
			List<WebElement> allCommentSummary1 = driver.findElements(By.cssSelector("div[class='ls-stream-post__comments']"));
			//TC row no. 146
			boolean commentSummary1 = allCommentSummary1.get(1).isDisplayed();
			if(commentSummary1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Available comments is not displayed on clicking Comments for DW assignment in Grading Page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase instructorShouldBeAbleToGradeDW in class InstructorShouldBeAbleToGradeDW.",e);
		}
	}

}
