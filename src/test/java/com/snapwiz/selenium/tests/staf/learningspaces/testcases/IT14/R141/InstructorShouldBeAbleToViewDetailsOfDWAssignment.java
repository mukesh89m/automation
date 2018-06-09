package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;

public class InstructorShouldBeAbleToViewDetailsOfDWAssignment extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void instructorShouldBeAbleToViewDetailsOfDWAssignment()
	{
		try
		{
			String gradingpolicy1 = ReadTestData.readDataByTagName("", "gradingpolicy1", "322");
			String additionalnote1 = ReadTestData.readDataByTagName("", "additionalnote1", "322");
            new LoginUsingLTI().ltiLogin("322student");
			new LoginUsingLTI().ltiLogin("322");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(322);
			new Navigator().NavigateTo("Assignments"); 
			//TC row no. 322
			String posted = driver.findElement(By.className("ls-assignment-post-label")).getText();
			if(!posted.equals("posted an assignment."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the entry for DW assignment is not available.");
			}
			//TC row no. 323
			int profilePic = driver.findElements(By.className("prof-icon-image")).size();
			if(profilePic == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the instructor thumbnail for DW assignment is not available.");
			}
			//TC row no. 324
			String instructorName = driver.findElement(By.className("ls-assignment-item-author-name")).getText();
			if(instructorName == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the instructor name who has posted DW assignment is not available.");
			}
			//TC row no. 325
			int bookmarkIcon = driver.findElements(By.cssSelector("span[class='ls-assignment-bookmark not-bookmarked']")).size();
			if(bookmarkIcon == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the symbol to bookmark DW assignment is not available.");
			}
			//TC row no. 326
			String DWAssignmentName = driver.findElement(By.className("ls-assignment-name-block")).getText();
			if(DWAssignmentName == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the DW Assignment Name is not available.");
			}
			//TC row no. 326
			String DWAssignmentIcon = driver.findElement(By.className("ls-assignment-name-block")).getAttribute("innerHTML");
			if(!DWAssignmentIcon.contains("home-work-icon.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the DW Assignment icon is not available.");
			}
			//TC row no. 327
			driver.findElement(By.cssSelector("span[resourcetype='discussion']")).click();
			Thread.sleep(2000);
			String tab = driver.findElement(By.xpath("(//div[@class='tab active'])[2]")).getText();
			System.out.println("tab1::"+tab);
			if(!tab.contains("Discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page Name of the learning activity doesnt appear as link.");
			}
			new Navigator().NavigateTo("Assignments"); 
			//TC row no. 327
			String assignmentLinkIcon = driver.findElement(By.cssSelector("span[class='discussion-icon card-icons']")).getCssValue("height");
			if(!assignmentLinkIcon.contains("20px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page Name of the learning activity doesnt appear with icon.");
			}
			//TC row no. 328
			String DWNameFormat = driver.findElement(By.cssSelector("span[resourcetype='discussion']")).getText();
			if(!DWNameFormat.contains("D1 - "))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Format of DW assignment link is not D1  - <Discussion text> .");
			}
			//TC row no. 330
			String description = driver.findElement(By.id("idb-additional-note-section")).getText();
			if(!description.contains("Description:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Assignment description is not displayed with label \"Description:\".");
			}
			//TC row no. 331
			String gradingPolicy = driver.findElement(By.className("ls-assignment-grading-desc")).getText();
			if(!gradingPolicy.contains("Assignment Reference:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Grading policy description is not displayed with label \"Grading Policy:\"");
			}
			//TC row no. 334
			int viewResponse = driver.findElements(By.cssSelector("span[title='View Student Responses']")).size();
			if(viewResponse == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the View Student Responses link for DW assignment is not available.");
			}
			//TC row no. 334
			int updateResponse = driver.findElements(By.cssSelector("span[title='Update Assignment']")).size();
			if(updateResponse == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Update Assignment link for DW assignment is not available.");
			}
			//TC row no. 334
			int deleteAssignment = driver.findElements(By.cssSelector("span[title='Un-assign Assignment']")).size();
			if(deleteAssignment == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Delete Assignment link for DW assignment is not available.");
			}
			driver.findElement(By.cssSelector("span[title='View Student Responses']")).click();	//click on View Student Responses
			Thread.sleep(2000);
			//TC row no. 335
			String tab1 = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!tab1.contains("Response - (shor)"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("From Assignments page if we click on View Student Respose link then gradebook tab is not opened.");
			}
			new TabClose().tabClose(1);//close response tab
			driver.findElement(By.cssSelector("span[title='Update Assignment']")).click();
			Thread.sleep(2000);
			//TC row no. 336
			int size = driver.findElements(By.id("ir-ls-assign-dialog")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("From Assignments page if we click on update assignment then update assignment form doesnt opened.");
			}
			driver.findElement(By.id("additional-notes")).click();
			driver.findElement(By.id("additional-notes")).clear();
		    driver.switchTo().activeElement().sendKeys(additionalnote1);//edit the additional note
		    driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
		    Thread.sleep(2000);
		    new Assignment().deleteAssignment();
			Thread.sleep(2000);
			//TC row no. 339
			String delete = driver.findElement(By.className("ls-assignment-post-label")).getText();
			if(!delete.contains("No Assignment exists."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("From Assignments page if we click on delete assignment then the assignment doesnt get deleted.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase instructorShouldBeAbleToViewDetailsOfDWAssignment in class InstructorShouldBeAbleToViewDetailsOfDWAssignment.",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void detailOverAssignmentPage()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("340_1");		//create a student
			new LoginUsingLTI().ltiLogin("340");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(340);
			
			new LoginUsingLTI().ltiLogin("340_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			Thread.sleep(1000);
			String perspective = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective);

			new LoginUsingLTI().ltiLogin("340");		//login as instructor
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			//TC row no. 340
			int updateResponse = driver.findElements(By.cssSelector("span[title='Update Assignment']")).size();
			if(updateResponse != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Update Assignment link for submitted DW assignment is available.");
			}
			
			int deleteAssignment = driver.findElements(By.cssSelector("span[title='Delete Assignment']")).size();
			if(deleteAssignment != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Delete Assignment link for submitted DW assignment is available.");
			}
			//TC row no. 341
			boolean dueDate = driver.findElement(By.className("ls-assignment-date-block")).isDisplayed();
			boolean accesibleAfter = driver.findElement(By.className("ls-assignment-accessible-after-title")).isDisplayed();
			if(dueDate == false || accesibleAfter == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Date and time for DW assignment is not displayed.");
			}
			//TC row no. 342
			String likeLink = driver.findElement(By.className("js-post-like")).getText();
			if(!likeLink.contains("Like"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Like option is not available under DW assignment.");
			}
			if(!likeLink.contains("0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Like count is not available under DW assignment.");
			}
			String comment = driver.findElement(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']")).getText();
			if(!comment.contains("Comments"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Comment option is not available under DW assignment.");
			}
			if(!comment.contains("0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page the Comment count is not available under DW assignment.");
			}
			driver.findElement(By.className("ls-post-like-link")).click();	// click on Like
			Thread.sleep(2000);
			//TC row no. 343
			String count = driver.findElement(By.className("ls-post-like-count")).getText();
			if(!count.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page Like count for DW assignment doesnt update if we Hit Like.");
			}
			//TC row no. 344
			String unlike = driver.findElement(By.className("ls-post-like-link")).getText();
			if(!unlike.contains("Unlike"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page for a DW assignment the like option doesnt update to Unlike after we hit Like.");
			}
			driver.findElement(By.cssSelector("a[title='Comments']")).click();	//click on Comment option
			Thread.sleep(2000);
			//TC row no. 345
			boolean isExpanded = driver.findElement(By.cssSelector("form[class='ls-media ls-stream-post__comments-form']")).isDisplayed();
			if(isExpanded == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Comment block for DW assignment doesnt expand after clicking on Comment in Assignment page.");
			}
			String commentText ="ghsdfsghjsdg";
			driver.findElement(By.xpath("//div[@placeholder='Write your comment']")).sendKeys(commentText);
			driver.findElement(By.className("post-comment")).click();

			//TC row no. 346
			String commString = driver.findElement(By.xpath("//div[@class='ls-stream-post__comment-text']")).getText();
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
				Assert.fail("In Assignments page the age for a comment is not available under DW assignment.");
			}
			driver.findElement(By.cssSelector("span.ls-post-like-count")).click();//like the posted comment
			Thread.sleep(2000);
			//TC row no. 348
			String count1 = driver.findElement(By.className("ls-post-like-count")).getText();
			if(!count1.contains("0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page Like count for comment for DW assignment doesnt update if we Hit Like.");
			}
			//TC row no. 349
			String unlike1 = driver.findElement(By.className("ls-post-like-count")).getText();
			if(!unlike1.contains("0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Assignments page for a DW assignment the like option for a comment doesnt update to Unlike after we hit Like.");
			}
			driver.findElement(By.cssSelector("span[title='View Student Responses']")).click();
			Thread.sleep(2000);
			new DiscussionWidget().provideGradeToStudent(0, "2");
			driver.findElement(By.cssSelector("div[title='Release Grade for All']")).click();
			Thread.sleep(2000);
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.cssSelector("span[resourcetype='discussion']")).click();
			Thread.sleep(6000);
			//TC row no. 350
			String tab = driver.findElement(By.xpath("(//div[@class='tab active'])[2]")).getText();
			System.out.println("tab :"+tab);
			if(!tab.contains("Discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On opening DW assignment from Assignments page the instructor doesnt get redirected to discussion tab view in the e-textbook.");
			}
			//TC row no. 351
			String perspectiveText = driver.findElement(By.cssSelector("div[class='ls-comment-entry']")).getText();
			if(!perspectiveText.contains(perspective))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("ON opening DW assignment from Assignments page the instructor doesnt see the perspective.");
			}
			//TC row no. 353
			driver.findElement(By.cssSelector("a[class='ls-chapter-navigation-icon ls-toc-sprite ls-study-plan']")).click();//click on toc icon

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase detailOverAssignmentPage in class InstructorShouldBeAbleToViewDetailsOfDWAssignment.",e);
		}
	}
	

}
