package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R1431;

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
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class VerifyVisualIndicatorForIndirectCourseStreamEntry extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void verifyVisualIndicatorForAddingDiscussion()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("20_2");		//create a student
			new LoginUsingLTI().ltiLogin("20_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TOCShow().tocHide();
			String discussionText = new RandomString().randomstring(15);
			new Discussion().postDiscussion(discussionText);	//post a discussion
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 20
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for a discussion which has been added from discuusion tab of eTextBook.");
			}
			driver.navigate().refresh();
			//TC row no. 22
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for a discussion which has been added from discuusion tab of eTextBook after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("20");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 23
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for a discussion which has been added by a student from discuusion tab of eTextBook.");
			}
			driver.navigate().refresh();
			//TC row no. 25
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for a discussion which has been added by a student from discuusion tab of eTextBook after we refresh the page.");
			}
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TOCShow().tocHide();
			String discussionText1 = new RandomString().randomstring(15);
			new Discussion().postDiscussion(discussionText1);	//post a discussion
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 26
			int size4 = driver.findElements(By.className("new-entry-icon")).size();
			if(size4 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is not displayed for a discussion which has been added by the instructor from discuusion tab of eTextBook.");
			}
			driver.navigate().refresh();
			//TC row no. 28
			int size5 = driver.findElements(By.className("new-entry-icon")).size();
			if(size5 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for a discussion which has been added by the instructor from discuusion tab of eTextBook after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("20_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 29
			int size6 = driver.findElements(By.className("new-entry-icon")).size();
			if(size6 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for a discussion which has been added by the instructor from discuusion tab of eTextBook.");
			}
			driver.navigate().refresh();
			//TC row no. 31
			int size7 = driver.findElements(By.className("new-entry-icon")).size();
			if(size7 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for a discussion which has been added by the instructor from discuusion tab of eTextBook after we refresh the page.");
			}
			
			
			new LoginUsingLTI().ltiLogin("20_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment

			new LoginUsingLTI().ltiLogin("20_2");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 32
			int size8 = driver.findElements(By.className("new-entry-icon")).size();
			if(size8 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 34
			int size9 = driver.findElements(By.className("new-entry-icon")).size();
			if(size9 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("20");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 35
			int size10 = driver.findElements(By.className("new-entry-icon")).size();
			if(size10 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 37
			int size11 = driver.findElements(By.className("new-entry-icon")).size();
			if(size11 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment
			
			new LoginUsingLTI().ltiLogin("20_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 38
			int size12 = driver.findElements(By.className("new-entry-icon")).size();
			if(size12 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 40
			int size13 = driver.findElements(By.className("new-entry-icon")).size();
			if(size13 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyVisualIndicatorForAddingDiscussion in class VerifyVisualIndicatorForIndirectCourseStreamEntry.",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void verifyVisualIndicatorForAddingDiscussionOnLessonText()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("21_2");		//create a student
			new LoginUsingLTI().ltiLogin("21_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TOCShow().tocHide();
			Thread.sleep(4000);
			String discussionText = new RandomString().randomstring(15);
			new Discussion().addDiscussionAfterHighlightingText(discussionText); 	//post a discussion
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 20
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for a discussion which has been added by highlighting text from etextbook.");
			}
			driver.navigate().refresh();
			//TC row no. 22
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for a discussion which has been added highlighting text from etextbook after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("21");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 23
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for a discussion which has been added by highlighting text by a student from etextbook.");
			}
			driver.navigate().refresh();
			//TC row no. 25
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for a discussion which has been added by highlighting text by a student from etextbook after we refresh the page.");
			}
			new LoginUsingLTI().ltiLogin("21_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment
			
			new LoginUsingLTI().ltiLogin("21_2");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 32
			int size8 = driver.findElements(By.className("new-entry-icon")).size();
			if(size8 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 34
			int size9 = driver.findElements(By.className("new-entry-icon")).size();
			if(size9 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("21");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 35
			int size10 = driver.findElements(By.className("new-entry-icon")).size();
			if(size10 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 37
			int size11 = driver.findElements(By.className("new-entry-icon")).size();
			if(size11 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment
			
			new LoginUsingLTI().ltiLogin("21_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 38
			int size12 = driver.findElements(By.className("new-entry-icon")).size();
			if(size12 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 40
			int size13 = driver.findElements(By.className("new-entry-icon")).size();
			if(size13 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyVisualIndicatorForAddingDiscussionOnLessonText in class VerifyVisualIndicatorForIndirectCourseStreamEntry.",e);
		}
	}
	@Test(priority = 3, enabled = true)
	public void verifyVisualIndicatorForLikingWidget()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("22_2");		//create a student
			new LoginUsingLTI().ltiLogin("22_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TOCShow().tocHide();
            new AssignmentSocialElement().clickonlike(0);	//click on like for image widget
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 20
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by liking a image widget from eTextBook.");
			}
			driver.navigate().refresh();
			//TC row no. 22
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by liking a image widget from eTextBook after we refresh the page.");
			}

			new LoginUsingLTI().ltiLogin("22");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 23
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for entry made by liking a image widget from eTextBook.");
			}
			driver.navigate().refresh();
			//TC row no. 25
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by liking a image widget from eTextBook after we refresh the page.");
			}
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TOCShow().tocHide();
            driver.findElement(By.cssSelector("a[title='Like']")).click();
			//new AssignmentSocialElement().clickonlike(0);	//click on like for image widget
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 26
			int size4 = driver.findElements(By.className("new-entry-icon")).size();
			if(size4 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is not displayed for entry made by liking a image widget from eTextBook by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 28
			int size5 = driver.findElements(By.className("new-entry-icon")).size();
			if(size5 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by liking a image widget from eTextBook after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("22_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 29
			int size6 = driver.findElements(By.className("new-entry-icon")).size();
			if(size6 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by liking a image widget from eTextBook by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 31
			int size7 = driver.findElements(By.className("new-entry-icon")).size();
			if(size7 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by liking a image widget from eTextBook after we refresh the page.");
			}
			
			
			new LoginUsingLTI().ltiLogin("22_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment
			
			new LoginUsingLTI().ltiLogin("22_2");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 32
			int size8 = driver.findElements(By.className("new-entry-icon")).size();
			if(size8 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 34
			int size9 = driver.findElements(By.className("new-entry-icon")).size();
			if(size9 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}

			new LoginUsingLTI().ltiLogin("22");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 35
			int size10 = driver.findElements(By.className("new-entry-icon")).size();
			if(size10 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 37
			int size11 = driver.findElements(By.className("new-entry-icon")).size();
			if(size11 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment
			
			new LoginUsingLTI().ltiLogin("22_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 38
			int size12 = driver.findElements(By.className("new-entry-icon")).size();
			if(size12 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 40
			int size13 = driver.findElements(By.className("new-entry-icon")).size();
			if(size13 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyVisualIndicatorForLikingWidget in class VerifyVisualIndicatorForIndirectCourseStreamEntry.",e);
		}
	}
	@Test(priority = 4, enabled = true)
	public void verifyVisualIndicatorForLikingDW()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("23_2");		//create a student
			new LoginUsingLTI().ltiLogin("23_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.ls-discussion-like-link")));	//click on like for DW
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 20
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by liking a DW from eTextBook.");
			}
			driver.navigate().refresh();
			//TC row no. 22
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by liking a DW from eTextBook after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("23");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 23
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for entry made by liking a DW from eTextBook.");
			}
			driver.navigate().refresh();
			//TC row no. 25
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by liking a DW from eTextBook after we refresh the page.");
			}
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.ls-discussion-like-link")));	//click on like for DW
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 26
			int size4 = driver.findElements(By.className("new-entry-icon")).size();
			if(size4 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is not displayed for entry made by liking a DW from eTextBook by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 28
			int size5 = driver.findElements(By.className("new-entry-icon")).size();
			if(size5 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by liking a DW from eTextBook after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("23_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 29
			int size6 = driver.findElements(By.className("new-entry-icon")).size();
			if(size6 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by liking a DW from eTextBook by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 31
			int size7 = driver.findElements(By.className("new-entry-icon")).size();
			if(size7 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by liking a DW from eTextBook after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("23_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment
			
			new LoginUsingLTI().ltiLogin("23_2");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 32
			int size8 = driver.findElements(By.className("new-entry-icon")).size();
			if(size8 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 34
			int size9 = driver.findElements(By.className("new-entry-icon")).size();
			if(size9 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("23");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 35
			int size10 = driver.findElements(By.className("new-entry-icon")).size();
			if(size10 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 37
			int size11 = driver.findElements(By.className("new-entry-icon")).size();
			if(size11 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment
			
			new LoginUsingLTI().ltiLogin("23_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 38
			int size12 = driver.findElements(By.className("new-entry-icon")).size();
			if(size12 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 40
			int size13 = driver.findElements(By.className("new-entry-icon")).size();
			if(size13 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyVisualIndicatorForLikingDW in class VerifyVisualIndicatorForIndirectCourseStreamEntry.",e);
		}
	}
	@Test(priority = 5, enabled = true)
	public void verifyVisualIndicatorForAddingPerspectiveToDW()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("24_2");		//create a student
			new LoginUsingLTI().ltiLogin("24_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TopicOpen().openLessonWithDiscussionWidget();
			String perspective = new RandomString().randomstring(15);
			new DiscussionWidget().addPerspectiveForDWIneTextBook(perspective);
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 20
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by adding perspective to DW from eTextBook.");
			}
			driver.navigate().refresh();
			//TC row no. 22
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by adding perspective to DW from eTextBook after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("24");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 23
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for entry made by adding perspective to DW from eTextBook.");
			}
			driver.navigate().refresh();
			//TC row no. 25
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by adding perspective to DW from eTextBook after we refresh the page.");
			}
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TopicOpen().openLessonWithDiscussionWidget();
			String perspective1 = new RandomString().randomstring(15);
			new DiscussionWidget().addPerspectiveForDWIneTextBook(perspective1);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 26
			int size4 = driver.findElements(By.className("new-entry-icon")).size();
			if(size4 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is not displayed for entry made by adding perspective to DW from eTextBook by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 28
			int size5 = driver.findElements(By.className("new-entry-icon")).size();
			if(size5 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by adding perspective to DW from eTextBook after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("24_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 29
			int size6 = driver.findElements(By.className("new-entry-icon")).size();
			if(size6 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by adding perspective to DW from eTextBook by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 31
			int size7 = driver.findElements(By.className("new-entry-icon")).size();
			if(size7 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by adding perspective to DW from eTextBook after we refresh the page.");
			}
			
			
			new LoginUsingLTI().ltiLogin("24_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment
			
			new LoginUsingLTI().ltiLogin("24_2");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 32
			int size8 = driver.findElements(By.className("new-entry-icon")).size();
			if(size8 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 34
			int size9 = driver.findElements(By.className("new-entry-icon")).size();
			if(size9 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("24");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 35
			int size10 = driver.findElements(By.className("new-entry-icon")).size();
			if(size10 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 37
			int size11 = driver.findElements(By.className("new-entry-icon")).size();
			if(size11 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment
			
			new LoginUsingLTI().ltiLogin("24_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 38
			int size12 = driver.findElements(By.className("new-entry-icon")).size();
			if(size12 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 40
			int size13 = driver.findElements(By.className("new-entry-icon")).size();
			if(size13 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyVisualIndicatorForAddingPerspectiveToDW in class VerifyVisualIndicatorForIndirectCourseStreamEntry.",e);
		}
	}
	@Test(priority = 6, enabled = true)
	public void verifyVisualIndicatorForLikingResource()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("25_2");		//login a student
			new ResourseCreate().resourseCreate(25, 0);
			
			new LoginUsingLTI().ltiLogin("25_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TOCShow().tocHide();
            new Navigator().openFirstResourceFromResourceTab(0);
			List<WebElement> allLikes = driver.findElements(By.cssSelector("a[title='Like']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allLikes.get(1));	//click on like
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 20
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by liking a resource from eTextBook.");
			}
			driver.navigate().refresh();
			//TC row no. 22
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by liking a DW from eTextBook after we refresh the page.");
			}

			new LoginUsingLTI().ltiLogin("25");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 23
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for entry made by liking a DW from eTextBook.");
			}
			driver.navigate().refresh();
			//TC row no. 25
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by liking a DW from eTextBook after we refresh the page.");
			}
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TOCShow().tocHide();
            new Navigator().openFirstResourceFromResourceTab(0);
			List<WebElement> allLikes1 = driver.findElements(By.cssSelector("a[title='Like']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allLikes1.get(1));	//click on like
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 26
			int size4 = driver.findElements(By.className("new-entry-icon")).size();
			if(size4 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is not displayed for entry made by liking a DW from eTextBook by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 28
			int size5 = driver.findElements(By.className("new-entry-icon")).size();
			if(size5 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by liking a DW from eTextBook after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("25_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 29
			int size6 = driver.findElements(By.className("new-entry-icon")).size();
			if(size6 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by liking a DW from eTextBook by the instructor.");
			}
			driver.navigate().refresh();
			//TC row no. 31
			int size7 = driver.findElements(By.className("new-entry-icon")).size();
			if(size7 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by liking a DW from eTextBook after we refresh the page.");
			}
			
			
			new LoginUsingLTI().ltiLogin("25_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment
			
			new LoginUsingLTI().ltiLogin("25_2");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 32
			int size8 = driver.findElements(By.className("new-entry-icon")).size();
			if(size8 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 34
			int size9 = driver.findElements(By.className("new-entry-icon")).size();
			if(size9 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
			
			new LoginUsingLTI().ltiLogin("25");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 35
			int size10 = driver.findElements(By.className("new-entry-icon")).size();
			if(size10 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 37
			int size11 = driver.findElements(By.className("new-entry-icon")).size();
			if(size11 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment
			
			new LoginUsingLTI().ltiLogin("25_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 38
			int size12 = driver.findElements(By.className("new-entry-icon")).size();
			if(size12 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 40
			int size13 = driver.findElements(By.className("new-entry-icon")).size();
			if(size13 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyVisualIndicatorForAddingPerspectiveToDW in class VerifyVisualIndicatorForIndirectCourseStreamEntry.",e);
		}
	}
	
	@Test(priority = 7, enabled = true)
	public void verifyVisualIndicatorOnAddingDiscussionAfterCompletionOfDiagTest()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("26_2");		//create a student
			new LoginUsingLTI().ltiLogin("26_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new DiagnosticTest().startTest(4);
			new DiagnosticTest().attemptAllCorrect(0, false, false);
			driver.findElement(By.className("question-card-question-details")).click();	//click on question from right side question card
			Thread.sleep(2000);
			new Navigator().navigateToTab("Discussion");
			String discussionText = new RandomString().randomstring(15);
			new Discussion().postDiscussion(discussionText);	//post a discussion
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 20
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by adding discusiion after completion of diagnostic test.");
			}
			driver.navigate().refresh();
			//TC row no. 22
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by adding discusiion after completion of diagnostic test.");
			}
			
			new LoginUsingLTI().ltiLogin("26");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 23
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for entry made by adding discusiion after completion of diagnostic test.");
			}
			driver.navigate().refresh();
			//TC row no. 25
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by adding discusiion after completion of diagnostic test.");
			}
			
			
			new LoginUsingLTI().ltiLogin("26_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment
			
			new LoginUsingLTI().ltiLogin("26");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 35
			int size10 = driver.findElements(By.className("new-entry-icon")).size();
			if(size10 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 37
			int size11 = driver.findElements(By.className("new-entry-icon")).size();
			if(size11 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment
			
			new LoginUsingLTI().ltiLogin("26_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 38
			int size12 = driver.findElements(By.className("new-entry-icon")).size();
			if(size12 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 40
			int size13 = driver.findElements(By.className("new-entry-icon")).size();
			if(size13 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyVisualIndicatorOnAddingDiscussionAfterCompletionOfDiagTest in class VerifyVisualIndicatorForIndirectCourseStreamEntry.",e);
		}
	}
	
	@Test(priority = 8, enabled = true)
	public void verifyVisualIndicatorOnAddingDiscussionAfterCompletionOfPracticeTest()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("27_2");		//create a student
			new LoginUsingLTI().ltiLogin("27_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new DiagnosticTest().startTest(4);
			new DiagnosticTest().attemptAllCorrect(0, false, false);
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
            new Navigator().NavigateToOrion();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[data-type='adaptive_assessment']")));	//click on Adaptive Practice
             new WebDriverWait(driver,120).until(ExpectedConditions.visibilityOfElementLocated(By.id("submit-practice-question-button")));
		     driver.findElement(By.id("submit-practice-question-button")).click();//click on submit
			Thread.sleep(3000);
			driver.findElement(By.id("submit-practice-question-button")).click();//click on submit
			Thread.sleep(3000);
			new Navigator().navigateToTab("Discussion"); //navigate to Discussion tab
			String discussionText = new RandomString().randomstring(15);
			new Discussion().postDiscussion(discussionText);	//post a discussion
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 20
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by adding discusiion after completion of Adaptive Practice test.");
			}
			driver.navigate().refresh();
			//TC row no. 22
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by adding discusiion after completion of Adaptive Practice test.");
			}
			
			new LoginUsingLTI().ltiLogin("27");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 23
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for entry made by adding discusiion after completion of Adaptive Practice test.");
			}
			driver.navigate().refresh();
			//TC row no. 25
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by adding discusiion after completion of Adaptive Practice test.");
			}
			
			
			new LoginUsingLTI().ltiLogin("27_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment
			
			
			new LoginUsingLTI().ltiLogin("27");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 35
			int size10 = driver.findElements(By.className("new-entry-icon")).size();
			if(size10 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 37
			int size11 = driver.findElements(By.className("new-entry-icon")).size();
			if(size11 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment
			
			new LoginUsingLTI().ltiLogin("27_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 38
			int size12 = driver.findElements(By.className("new-entry-icon")).size();
			if(size12 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 40
			int size13 = driver.findElements(By.className("new-entry-icon")).size();
			if(size13 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyVisualIndicatorOnAddingDiscussionAfterCompletionOfPracticeTest in class VerifyVisualIndicatorForIndirectCourseStreamEntry.",e);
		}
	}
	@Test(priority = 9, enabled = true)
	public void verifyVisualIndicatorOnAddingDiscussionAfterCompletionOfStaticTest()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("28_2");		//create a student
			new LoginUsingLTI().ltiLogin("28_1");		//login a student
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
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 20
			int size = driver.findElements(By.className("new-entry-icon")).size();
			if(size == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for entry made by adding discusiion after completion of Adaptive Practice test.");
			}
			driver.navigate().refresh();
			//TC row no. 22
			int size1 = driver.findElements(By.className("new-entry-icon")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for entry made by adding discusiion after completion of Adaptive Practice test.");
			}
			
			new LoginUsingLTI().ltiLogin("28");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 23
			int size2 = driver.findElements(By.className("new-entry-icon")).size();
			if(size2 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for entry made by adding discusiion after completion of Adaptive Practice test.");
			}
			driver.navigate().refresh();
			//TC row no. 25
			int size3 = driver.findElements(By.className("new-entry-icon")).size();
			if(size3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for entry made by adding discusiion after completion of Adaptive Practice test.");
			}
			
			
			new LoginUsingLTI().ltiLogin("28_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment
			
			new LoginUsingLTI().ltiLogin("28");		//login a instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 35
			int size10 = driver.findElements(By.className("new-entry-icon")).size();
			if(size10 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream instructor side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 37
			int size11 = driver.findElements(By.className("new-entry-icon")).size();
			if(size11 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of instructor side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment
			
			new LoginUsingLTI().ltiLogin("28_1");		//login a student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 38
			int size12 = driver.findElements(By.className("new-entry-icon")).size();
			if(size12 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is not displayed for adding comment to indirect course stream entry.");
			}
			driver.navigate().refresh();
			//TC row no. 40
			int size13 = driver.findElements(By.className("new-entry-icon")).size();
			if(size13 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course stream of student side, visual indicator is still displayed for adding comment to indirect course stream entry after we refresh the page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyVisualIndicatorOnAddingDiscussionAfterCompletionOfPracticeTest in class VerifyVisualIndicatorForIndirectCourseStreamEntry.",e);
		}
	}
}
