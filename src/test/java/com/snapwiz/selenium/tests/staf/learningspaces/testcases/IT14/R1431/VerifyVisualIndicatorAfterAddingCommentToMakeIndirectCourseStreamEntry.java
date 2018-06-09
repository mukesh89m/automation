package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R1431;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
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
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class VerifyVisualIndicatorAfterAddingCommentToMakeIndirectCourseStreamEntry extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void verifyVisualIndicatorAfterAddingCommentToDiscussionOnLessonText()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("41_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TOCShow().tocHide();
			Thread.sleep(3000);
			String discussionText = new RandomString().randomstring(15);
			new Discussion().addDiscussionAfterHighlightingText(discussionText); 	//post a discussion
			driver.findElement(By.className("stream-content-posts-list")).click();	//click on discussion on Tab
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.right-post-comment.active > i.ls-icon-img.ls--comments-icon")).click();	//click on Comment link
			Thread.sleep(2000);
			String comment = new RandomString().randomstring(15);
			driver.switchTo().activeElement().sendKeys(comment+Keys.RETURN);	//add the comment
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 41
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for a discussion which has been added by highlighting text from etextbook.");
			}
			driver.navigate().refresh();
			//TC row no. 43
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for a discussion which has been added highlighting text from etextbook after we refresh the page.");
			}

			new LoginUsingLTI().ltiLogin("41");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 44
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for a discussion which has been added by highlighting text by a student from etextbook.");
			}
			driver.navigate().refresh();
			//TC row no. 46
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for a comment on discussion which has been added by highlighting text by a student from etextbook after we refresh the page.");
			}
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TopicOpen().lessonOpen(1, 0);
			String discussionText1 = new RandomString().randomstring(15);
			//post discussion by selecting other texts
			new Discussion().addDiscussionAfterHighlightingText(discussionText1); 	//post a discussion
			
			driver.findElement(By.className("stream-content-posts-list")).click();	//click on discussion on tab
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.right-post-comment.active > i.ls-icon-img.ls--comments-icon")).click();	//click on Comment link
			Thread.sleep(2000);
			String comment1 = new RandomString().randomstring(15);
			driver.switchTo().activeElement().sendKeys(comment1+Keys.RETURN);	//add the comment
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 47
			int size4 = driver.findElements(By.className("new-entry-icon")).size();
			if(size4 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is not displayed for a comment on discussion which has been added by the instructor by highlighting text frometextbook.");
			}
			driver.navigate().refresh();
			//TC row no. 49
			int size5 = driver.findElements(By.className("new-entry-icon")).size();
			if(size5 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for a comment on discussion which has been added by the instructor by highlighting text from etextbook after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("41_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 50
			int size6 = driver.findElements(By.className("new-entry-icon")).size();
			if(size6 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for a comment on discussion which has been added by the instructor by highlighting text from etextbook.");
			}
			driver.navigate().refresh();
			//TC row no. 52
			int size7 = driver.findElements(By.className("new-entry-icon")).size();
			if(size7 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for a comment on discussion which has been added by the instructor by highlighting textfrom etextbook after we refresh the page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyVisualIndicatorAfterAddingCommentToDiscussionOnLessonText in class VerifyVisualIndicatorAfterAddingCommentToMakeIndirectCourseStreamEntry.",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void verifyVisualIndicatorAfterAddingCommentToDiscussion()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("42_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TOCShow().tocHide();
			String discussionText = new RandomString().randomstring(15);
			new Discussion().postDiscussion(discussionText);	//post a discussion
			driver.findElement(By.className("stream-content-posts-list")).click();	//click on discussion on Tab
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.right-post-comment.active > i.ls-icon-img.ls--comments-icon")).click();	//click on Comment link
			Thread.sleep(2000);
			String comment = new RandomString().randomstring(15);
			driver.switchTo().activeElement().sendKeys(comment+Keys.RETURN);	//add the comment
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 41
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for a comment on discussion.");
			}
			driver.navigate().refresh();
			//TC row no. 43
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for a comment on discussion after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("42");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 44
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for a comment on discussion.");
			}
			driver.navigate().refresh();
			//TC row no. 46
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for a comment on discussion after we refresh the page.");
			}
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TopicOpen().lessonOpen(0, 1);
			String discussionText1 = new RandomString().randomstring(15);
			new Discussion().postDiscussion(discussionText1); 	//post a discussion
			driver.findElement(By.className("stream-content-posts-list")).click();	//click on discussion on tab
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.right-post-comment.active > i.ls-icon-img.ls--comments-icon")).click();	//click on Comment link
			Thread.sleep(2000);
			String comment1 = new RandomString().randomstring(15);
			driver.switchTo().activeElement().sendKeys(comment1+Keys.RETURN);	//add the comment
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 47
			int size4 = driver.findElements(By.className("new-entry-icon")).size();
			if(size4 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is not displayed for a comment on discussion.");
			}
			driver.navigate().refresh();
			//TC row no. 49
			int size5 = driver.findElements(By.className("new-entry-icon")).size();
			if(size5 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for a comment on discussion after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("42_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 50
			int size6 = driver.findElements(By.className("new-entry-icon")).size();
			if(size6 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for a comment on discussion.");
			}
			driver.navigate().refresh();
			//TC row no. 52
			int size7 = driver.findElements(By.className("new-entry-icon")).size();
			if(size7 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for a comment on discussion after we refresh the page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyVisualIndicatorAfterAddingCommentToDiscussionOnLessonText in class VerifyVisualIndicatorAfterAddingCommentToMakeIndirectCourseStreamEntry.",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void verifyVisualIndicatorForCommentOnWidget()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("43_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TOCShow().tocHide();
			String comment = new RandomString().randomstring(15);
			new AssignmentSocialElement().addComment(comment);	//comment on image widget
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 41
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by adding comment on image widget from eTextBook.");
			}
			driver.navigate().refresh();
			//TC row no. 43
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by adding comment on image widget from eTextBook after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("43");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 44
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for entry made by adding comment on image widget from eTextBook.");
			}
			driver.navigate().refresh();
			//TC row no. 46
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by adding comment on image widget from eTextBook after we refresh the page.");
			}
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TOCShow().tocHide();
			String comment1 = new RandomString().randomstring(15);
			new AssignmentSocialElement().addComment(comment1);	//comment on image widget
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 47
			int size4 = driver.findElements(By.className("new-entry-icon")).size();
			if(size4 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is not displayed for entry made by adding comment on image widget from eTextBook by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 49
			int size5 = driver.findElements(By.className("new-entry-icon")).size();
			if(size5 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by adding comment on image widget from eTextBook after we refresh the page.");
			}

			new LoginUsingLTI().ltiLogin("43_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 50
			int size6 = driver.findElements(By.className("new-entry-icon")).size();
			if(size6 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by adding comment on image widget from eTextBook by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 52
			int size7 = driver.findElements(By.className("new-entry-icon")).size();
			if(size7 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by adding comment on image widget from eTextBook after we refresh the page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyVisualIndicatorForCommentOnWidget in class VerifyVisualIndicatorForIndirectCourseStreamEntry.",e);
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void verifyVisualIndicatorForCommentDWPerspective()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("44_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TopicOpen().openLessonWithDiscussionWidget();
			String perspective = new RandomString().randomstring(15);
			new DiscussionWidget().addPerspectiveForDWIneTextBook(perspective);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			String commentText = new RandomString().randomstring(15);
			new DiscussionWidget().commentOnPerspective(commentText, 0);
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 41
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by adding comment on DW perspective from eTextBook.");
			}
			driver.navigate().refresh();
			//TC row no. 43
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by adding comment on DW perspective from eTextBook after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("44");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 44
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for entry made by adding comment on DW perspective from eTextBook.");
			}
			driver.navigate().refresh();
			//TC row no. 46
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by adding comment on DW perspective from eTextBook after we refresh the page.");
			}
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TopicOpen().openLessonWithDiscussionWidget();
			String comment1 = new RandomString().randomstring(15);
			new DiscussionWidget().commentOnPerspective(comment1, 0);//comment on perspective
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 47
			int size4 = driver.findElements(By.className("new-entry-icon")).size();
			if(size4 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is not displayed for entry made by adding comment on DW perspective from eTextBook by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 49
			int size5 = driver.findElements(By.className("new-entry-icon")).size();
			if(size5 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by adding comment on DW perspective from eTextBook after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("44_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 50
			int size6 = driver.findElements(By.className("new-entry-icon")).size();
			if(size6 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by adding comment on DW perspective from eTextBook by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 52
			int size7 = driver.findElements(By.className("new-entry-icon")).size();
			if(size7 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by adding comment on DW perspective from eTextBook after we refresh the page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyVisualIndicatorForCommentDWPerspective in class VerifyVisualIndicatorForIndirectCourseStreamEntry.",e);
		}
	}
	@Test(priority = 5, enabled = true)
	public void verifyVisualIndicatorForCommentToResource()
	{
		try
		{
			new ResourseCreate().resourseCreate(45, 0);
			
			new LoginUsingLTI().ltiLogin("45_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TOCShow().tocHide();
			new Navigator().navigateToTab("Resources");	//navigate to Resource tab
			new MouseHover().mouserhover("resource-content-posts-list");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.ls-right-tab-hover-sprite.folder-cycle-bg")));	//click on open
			Thread.sleep(2000);
			List<WebElement> allComments = driver.findElements(By.id("ls-right-post-comment-link"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allComments.get(1));	//click on text area
			Thread.sleep(2000);
			String comment = new RandomString().randomstring(15);
			driver.switchTo().activeElement().sendKeys(comment+Keys.RETURN);	//comment on resource
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 41
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by adding comment on resource.");
			}
			driver.navigate().refresh();
			//TC row no. 43
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by adding comment on resource after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("45");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 44
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for entry made by adding comment on resource.");
			}
			driver.navigate().refresh();
			//TC row no. 46
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by adding comment on resource after we refresh the page.");
			}
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TOCShow().tocHide();
			new Navigator().navigateToTab("Resources");	//navigate to Resource tab
            Thread.sleep(3000);
            driver.findElement(By.className("ls-resource-show-assign-this-block")).click();
			List<WebElement> allOpenTab = driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-cycle-bg']"));
			for(WebElement open: allOpenTab)
			{
				if(open.isDisplayed() == true)
				{
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", open);	//click on open
					Thread.sleep(2000);
				}
			}
			List<WebElement> allComments1 = driver.findElements(By.id("ls-right-post-comment-link"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allComments1.get(1));	//click on text area
			Thread.sleep(2000);
			String comment1 = new RandomString().randomstring(15);
			driver.switchTo().activeElement().sendKeys(comment1+Keys.RETURN);	//comment on resource
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 47
			int size4 = driver.findElements(By.className("new-entry-icon")).size();
			if(size4 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is not displayed for entry made by adding comment on resource by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 49
			int size5 = driver.findElements(By.className("new-entry-icon")).size();
			if(size5 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by adding comment on resource from eTextBook after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("45_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 50
			int size6 = driver.findElements(By.className("new-entry-icon")).size();
			if(size6 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by adding comment on resource by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 52
			int size7 = driver.findElements(By.className("new-entry-icon")).size();
			if(size7 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by adding comment on resource after we refresh the page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyVisualIndicatorForCommentToResource in class VerifyVisualIndicatorForIndirectCourseStreamEntry.",e);
		}
	}
	@Test(priority = 6, enabled = true)
	public void verifyVisualIndicatorForCommentOnAssignment()
	{
		try
		{
			new Assignment().create(46);
			
			new LoginUsingLTI().ltiLogin("46_1");		//create a student
			new LoginUsingLTI().ltiLogin("46");		//login a instructor
			new Assignment().assignToStudent(46);	// assign assignment
			
			new LoginUsingLTI().ltiLogin("46_1");		//login a student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add comment
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 41
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by adding comment on resource.");
			}
			driver.navigate().refresh();
			//TC row no. 43
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by adding comment on resource after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("46");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 44
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for entry made by adding comment on resource.");
			}
			driver.navigate().refresh();
			//TC row no. 46
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by adding comment on resource after we refresh the page.");
			}
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add comment
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 47
			int size4 = driver.findElements(By.className("new-entry-icon")).size();
			if(size4 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is not displayed for entry made by adding comment on resource by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 49
			int size5 = driver.findElements(By.className("new-entry-icon")).size();
			if(size5 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by adding comment on resource from eTextBook after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("46_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 50
			int size6 = driver.findElements(By.className("new-entry-icon")).size();
			if(size6 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by adding comment on resource by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 52
			int size7 = driver.findElements(By.className("new-entry-icon")).size();
			if(size7 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by adding comment on resource after we refresh the page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyVisualIndicatorForCommentOnAssignment in class VerifyVisualIndicatorForIndirectCourseStreamEntry.",e);
		}
	}
	
	@Test(priority = 7, enabled = true)
	public void verifyVisualIndicatorForCommentOnDiscussionForDiagTest()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("47_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new DiagnosticTest().startTest(4);
			new DiagnosticTest().attemptAllCorrect(0, false, false);
			driver.findElement(By.className("question-card-question-details")).click();	//click on question from right side question card
			Thread.sleep(2000);
			new Navigator().navigateToTab("Discussion");
			String discussionText = new RandomString().randomstring(15);
			new Discussion().postDiscussion(discussionText);	//post a discussion
			driver.findElement(By.className("stream-content-posts-list")).click();	//click on discussion on Tab
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.right-post-comment.active > i.ls-icon-img.ls--comments-icon")).click();	//click on Comment link
			Thread.sleep(2000);
			String comment = new RandomString().randomstring(15);
			driver.switchTo().activeElement().sendKeys(comment+Keys.RETURN);	//add the comment
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 41
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by adding comment on discussion for question after completing Diagnostic test.");
			}
			driver.navigate().refresh();
			//TC row no. 43
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by adding comment on discussion for question after completing Diagnostic test after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("47");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 44
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for entry made by adding comment on discussion for question after completing Diagnostic test.");
			}
			driver.navigate().refresh();
			//TC row no. 46
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by adding comment on discussion for question after completing Diagnostic test after we refresh the page.");
			}
			
			driver.findElement(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']")).click();//click on jump out icon
			Thread.sleep(3000);
			new Navigator().navigateToTab("Discussion");
			String discussionText1 = new RandomString().randomstring(15);
			new Discussion().postDiscussion(discussionText1);	//post a discussion
			driver.findElement(By.className("stream-content-posts-list")).click();	//click on discussion on Tab
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.right-post-comment.active > i.ls-icon-img.ls--comments-icon")).click();	//click on Comment link
			Thread.sleep(2000);
			String comment1 = new RandomString().randomstring(15);
			driver.switchTo().activeElement().sendKeys(comment1+Keys.RETURN);	//add the comment
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 47
			int size4 = driver.findElements(By.className("new-entry-icon")).size();
			if(size4 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is not displayed for entry made by adding comment on discussion for question of Diagnostic test by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 49
			int size5 = driver.findElements(By.className("new-entry-icon")).size();
			if(size5 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by adding comment on discussion for question of Diagnostic test after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("47_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 50
			int size6 = driver.findElements(By.className("new-entry-icon")).size();
			if(size6 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by adding comment on discussion for question of Diagnostic test by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 52
			int size7 = driver.findElements(By.className("new-entry-icon")).size();
			if(size7 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by adding comment on discussion for question of Diagnostic test after we refresh the page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyVisualIndicatorForCommentOnDiscussionForDiagTest in class VerifyVisualIndicatorForIndirectCourseStreamEntry.",e);
		}
	}
	@Test(priority = 8, enabled = true)
	public void verifyVisualIndicatorForCommentOnDiscussionForAdaptivePracticeTest()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("48_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new DiagnosticTest().startTest(4);
			new DiagnosticTest().attemptAllCorrect(0, false, false);

			new LoginUsingLTI().ltiLogin("48_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
            new PracticeTest().startTest();
            new PracticeTest().AttemptCorrectAnswer(0,"48_1");
            new Navigator().navigateToTab("Discussion"); //navigate to Discussion tab
			String discussionText = new RandomString().randomstring(15);
			new Discussion().postDiscussion(discussionText);	//post a discussion
			driver.findElement(By.className("stream-content-posts-list")).click();	//click on discussion on Tab
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.right-post-comment.active > i.ls-icon-img.ls--comments-icon")).click();	//click on Comment link
			Thread.sleep(2000);
			String comment = new RandomString().randomstring(15);
			driver.switchTo().activeElement().sendKeys(comment+Keys.RETURN);	//add the comment
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 41
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by adding comment on discussion for question after finishing adaptive practice test.");
			}
			driver.navigate().refresh();
			//TC row no. 43
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by adding comment on discussion for question after finishing adaptive practice test and we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("48");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 44
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for entry made by adding comment on discussion for question of Adaptive practice.");
			}
			driver.navigate().refresh();
			//TC row no. 46
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by adding comment on discussion for question of Adaptive practice after we refresh the page.");
			}
			
			driver.findElement(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']")).click();//click on jump out icon
			Thread.sleep(3000);
			new Navigator().navigateToTab("Discussion");
			String discussionText1 = new RandomString().randomstring(15);
			new Discussion().postDiscussion(discussionText1);	//post a discussion
			driver.findElement(By.className("stream-content-posts-list")).click();	//click on discussion on Tab
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.right-post-comment.active > i.ls-icon-img.ls--comments-icon")).click();	//click on Comment link
			Thread.sleep(2000);
			String comment1 = new RandomString().randomstring(15);
			driver.switchTo().activeElement().sendKeys(comment1+Keys.RETURN);	//add the comment
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 47
			int size4 = driver.findElements(By.className("new-entry-icon")).size();
			if(size4 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is not displayed for entry made by adding comment on discussion for question of Adaptive practice by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 49
			int size5 = driver.findElements(By.className("new-entry-icon")).size();
			if(size5 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by adding comment on discussion for question of Adaptive practice  after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("48_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 50
			int size6 = driver.findElements(By.className("new-entry-icon")).size();
			if(size6 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by adding comment on discussion for question of Adaptive practice by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 52
			int size7 = driver.findElements(By.className("new-entry-icon")).size();
			if(size7 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by adding comment on discussion for question of Adaptive practice after we refresh the page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyVisualIndicatorForCommentOnDiscussionForAdaptivePracticeTest in class VerifyVisualIndicatorForIndirectCourseStreamEntry.",e);
		}
	}
	
	@Test(priority = 9, enabled = true)
	public void verifyVisualIndicatorForCommentOnDiscussionForStaticTest()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("49_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[data-type='static_assessment']")));	//click on static assessment
			Thread.sleep(3000);
			new SelectCourse().selectInvisibleAssignment("1.1 Concept Check");
			new StaticAssignmentSubmit().staticAssesment();//submit the static assessment
			new WebDriverWait(driver,180).until(ExpectedConditions.elementToBeClickable(By.className("question-card-question-details")));
			driver.findElement(By.className("question-card-question-details")).click();//click on question card
			Thread.sleep(2000);
			new Navigator().navigateToTab("Discussion"); //navigate to Discussion tab
			String discussionText = new RandomString().randomstring(15);
			new Discussion().postDiscussion(discussionText);	//post a discussion
			driver.findElement(By.className("stream-content-posts-list")).click();	//click on discussion on Tab
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.right-post-comment.active > i.ls-icon-img.ls--comments-icon")).click();	//click on Comment link
			Thread.sleep(2000);
			String comment = new RandomString().randomstring(15);
			driver.switchTo().activeElement().sendKeys(comment+Keys.RETURN);	//add the comment
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 41
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by adding comment on discussion for a question of Static practice.");
			}
			driver.navigate().refresh();
			//TC row no. 43
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by adding comment on discussion for a question of Static practice after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("49");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 44
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for entry made by adding comment on discussion for a question of Static practice.");
			}
			driver.navigate().refresh();
			//TC row no. 46
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by adding comment on discussion for a question of Static practice after we refresh the page.");
			}
			
			driver.findElement(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']")).click();//click on jump out icon
			Thread.sleep(5000);
			new Navigator().navigateToTab("Discussion");
			String discussionText1 = new RandomString().randomstring(15);
			new Discussion().postDiscussion(discussionText1);	//post a discussion
			driver.findElement(By.className("stream-content-posts-list")).click();	//click on discussion on Tab
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.right-post-comment.active > i.ls-icon-img.ls--comments-icon")).click();	//click on Comment link
			Thread.sleep(2000);
			String comment1 = new RandomString().randomstring(15);
			driver.switchTo().activeElement().sendKeys(comment1+Keys.RETURN);	//add the comment
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 47
			int size4 = driver.findElements(By.className("new-entry-icon")).size();
			if(size4 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is not displayed for entry made by adding comment on discussion for a question of Static practice by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 49
			int size5 = driver.findElements(By.className("new-entry-icon")).size();
			if(size5 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by adding comment on discussion for a question of Static practice after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("49_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 50
			int size6 = driver.findElements(By.className("new-entry-icon")).size();
			if(size6 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by adding comment on discussion for a question of Static practice by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 52
			int size7 = driver.findElements(By.className("new-entry-icon")).size();
			if(size7 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by adding comment on discussion for a question of Static practice after we refresh the page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyVisualIndicatorForCommentOnDiscussionForStaticTest in class VerifyVisualIndicatorForIndirectCourseStreamEntry.",e);
		}
	}
}
