package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1593;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentSocialElement;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Discussion;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiscussionWidget;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ResourseCreate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class RemovePostRemoveCommentOptionAtInstructorSide extends Driver{
	
	@Test
	public void removePostRemoveCommentOptionAtInstructorSide()
	{
		try
		{

			new ResourseCreate().resourseCreate(226, 0); //upload a resource
			new Assignment().create(226);	//create Assignment
			
			new LoginUsingLTI().ltiLogin("226_1");		//login as student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			String randomtext = new RandomString().randomstring(15);
            List<WebElement> allToggleIcon;
            String removeComment = "";
            String removePost = "";
            new PostMessage().postmessage(randomtext);		//add a post
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the post
			
			new LoginUsingLTI().ltiLogin("226");		//login as instructor
			new Navigator().NavigateTo("Course Stream");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Comments']")));//click on Comment to expand
			Thread.sleep(2000);
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for student's comment
			Thread.sleep(2000);
			//TC row no. 229
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available in instructor CS for a student comment on post.");
			}
			allToggleIcon.get(0).click();	//click on arrow for student's post
			Thread.sleep(2000);
			//TC row no. 226
			removePost = driver.findElement(By.className("ls-hide-post")).getText();
			if(!removePost.contains("Remove Post"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Post\" option is not available in instructor CS for a student's post.");
			}
			new PostMessage().postmessage(randomtext);	//add a post 
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the post
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for instructor comment
			Thread.sleep(2000);
			//TC row no. 229
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available in instructor side CS for his own comment.");
			}
			allToggleIcon.get(0).click();	//click on arrow for instructor post
			Thread.sleep(2000);
			//TC row no. 226
			removePost = driver.findElement(By.className("ls-hide-post")).getText();
			if(!removePost.contains("Remove Post"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Post\" option is not available in instructor side CS for his own post.");
			}
			
			new LoginUsingLTI().ltiLogin("226_1");		//login as student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new FileUpload().fileUpload("226", true);	//upload file
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the file
			
			new LoginUsingLTI().ltiLogin("226");		//login as instructor
			new Navigator().NavigateTo("Course Stream");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Comments']")));//click on Comment to expand
			Thread.sleep(2000);
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for student's comment
			Thread.sleep(2000);
			//TC row no. 230
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available in instructor CS for a student comment on file upload.");
			}
			allToggleIcon.get(0).click();	//click on arrow for student's post
			Thread.sleep(2000);
			//TC row no. 227
			removePost = driver.findElement(By.className("ls-hide-post")).getText();
			if(!removePost.contains("Remove Post"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Post\" option is not available in instructor CS for a student's file upload.");
			}
			new FileUpload().fileUpload("226", true);	//upload file
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the file
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for instructor comment
			Thread.sleep(2000);
			//TC row no. 230
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available in instructor side CS for his own comment on file.");
			}
			allToggleIcon.get(0).click();	//click on arrow for instructor post
			Thread.sleep(2000);
			//TC row no. 227
			removePost = driver.findElement(By.className("ls-hide-post")).getText();
			if(!removePost.contains("Remove Post"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Post\" option is not available in instructor side CS for his own file upload.");
			}
			
			new LoginUsingLTI().ltiLogin("226_1");		//login as student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new PostMessage().postlink("www.google.com");	//post a link
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the posted link
			
			new LoginUsingLTI().ltiLogin("226");		//login as instructor
			new Navigator().NavigateTo("Course Stream");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Comments']")));//click on Comment to expand
			Thread.sleep(2000);
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for student's comment
			Thread.sleep(2000);
			//TC row no. 231
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available in instructor CS for a student comment on posted link.");
			}
			allToggleIcon.get(0).click();	//click on arrow for student's posted link
			Thread.sleep(2000);
			//TC row no. 228
			removePost = driver.findElement(By.className("ls-hide-post")).getText();
			if(!removePost.contains("Remove Post"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Post\" option is not available in instructor CS for a student's posted link.");
			}
			new PostMessage().postlink("www.google.com");	//post a link
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the posted link
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for instructor comment
			Thread.sleep(2000);
			//TC row no. 231
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available in instructor side CS for his own comment on posted link.");
			}
			allToggleIcon.get(0).click();	//click on arrow for instructor post
			Thread.sleep(2000);
			//TC row no. 228
			removePost = driver.findElement(By.className("ls-hide-post")).getText();
			if(!removePost.contains("Remove Post"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Post\" option is not available in instructor side CS for his own posted link.");
			}

			new LoginUsingLTI().ltiLogin("226_1");		//login as student
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TOCShow().tocHide();
			new Discussion().postDiscussion(randomtext);	//post a discussion
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the posted discussion
			
			new LoginUsingLTI().ltiLogin("226");		//login as instructor
			new Navigator().NavigateTo("Course Stream");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Comments']")));//click on Comment to expand
			Thread.sleep(2000);
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for student's comment
			Thread.sleep(2000);
			//TC row no. 233
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available in instructor CS for a student comment on posted link.");
			}
			allToggleIcon.get(0).click();	//click on arrow for student's posted link
			Thread.sleep(2000);
			//TC row no. 232
			removePost = driver.findElement(By.className("ls-hide-post")).getText();
			if(!removePost.contains("Remove Post"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Post\" option is not available in instructor CS for a student's posted link.");
			}
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TOCShow().tocHide();
			new Discussion().postDiscussion(randomtext);	//post a discussion
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the posted link
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for instructor comment
			Thread.sleep(2000);
			//TC row no. 233
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available in instructor side CS for his own comment on posted link.");
			}
			allToggleIcon.get(0).click();	//click on arrow for instructor post
			Thread.sleep(2000);
			//TC row no. 232
			removePost = driver.findElement(By.className("ls-hide-post")).getText();
			if(!removePost.contains("Remove Post"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Post\" option is not available in instructor side CS for his own posted link.");
			}
			
			new LoginUsingLTI().ltiLogin("226_1");		//login as student
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TOCShow().tocHide();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Comments']")));
			Thread.sleep(2000);
			driver.switchTo().activeElement().sendKeys(randomtext+Keys.RETURN);	//add comment to image widget
			driver.findElement(By.cssSelector("div.toggle-widget-size.toggle-widget-size-collapse")).click();//click on collapse
			Thread.sleep(2000);
			
			new LoginUsingLTI().ltiLogin("226");		//login as instructor
			new Navigator().NavigateTo("Course Stream");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Comments']")));//click on Comment to expand
			Thread.sleep(2000);
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for student's comment
			Thread.sleep(2000);
			//TC row no. 235
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available in instructor CS for a student comment on image widget.");
			}
			
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TOCShow().tocHide();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Comments']")));
			Thread.sleep(2000);
			driver.switchTo().activeElement().sendKeys(randomtext+Keys.RETURN);	//add comment to image widget
			driver.findElement(By.cssSelector("div.toggle-widget-size.toggle-widget-size-collapse")).click();//click on collapse
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Comments']")));//click on Comment to expand
			Thread.sleep(2000);
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(2).click();	//click on arrow for instructor comment
			Thread.sleep(2000);
			//TC row no. 235
			List<WebElement> removeComment1;
			removeComment1 = driver.findElements(By.className("ls-hide-comment"));
			if(!removeComment1.get(1).getText().contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available in instructor side CS for his own comment on image widget.");
			}
			
			new LoginUsingLTI().ltiLogin("226");		//login as instructor
			new Assignment().assignToStudent(226);  //Assigning assignment
		
			new LoginUsingLTI().ltiLogin("226_1");		//login as student
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the assigned assignment
			
			new LoginUsingLTI().ltiLogin("226");		//login as instructor
			new Navigator().NavigateTo("Course Stream");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Comments']")));//click on Comment to expand
			Thread.sleep(2000);
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for student's comment
			Thread.sleep(2000);
			//TC row no. 242
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available in instructor CS for a student comment on Assigned Assignment.");
			}
			
			new LoginUsingLTI().ltiLogin("226");		//login as instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the assigned assignment
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(2).click();	//click on arrow for instructor comment
			Thread.sleep(2000);
			//TC row no. 242
			removeComment1 = driver.findElements(By.className("ls-hide-comment"));
			if(!removeComment1.get(1).getText().contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available in instructor side CS for his own comment on Assigned Assignment.");
			}
			
			new LoginUsingLTI().ltiLogin("226_1");		//login as student
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TOCShow().tocHide();	//open first chapters first lesson
			new Navigator().navigateToTab("Resources");
			new Navigator().openResourceFromResourceTabFromCMS(226);	//open resource
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.ls-right-post-like-link")));//like the resource
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the resource entry
			
			new LoginUsingLTI().ltiLogin("226");		//login as instructor
			new Navigator().NavigateTo("Course Stream");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Comments']")));//click on Comment to expand
			Thread.sleep(2000);
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for student's comment
			Thread.sleep(2000);
			//TC row no. 244
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available in instructor CS for a student comment on resource entry.");
			}
			
			new LoginUsingLTI().ltiLogin("226");		//login as instructor
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the resource entry
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(2).click();	//click on arrow for instructor comment
			Thread.sleep(2000);
			//TC row no. 244
			removeComment1 = driver.findElements(By.className("ls-hide-comment"));
			if(!removeComment1.get(1).getText().contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available in instructor side CS for his own comment on Assigned Assignment.");
			}
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TOCShow().tocHide();	//open first chapters first lesson
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Comments']")));
			Thread.sleep(2000);
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(0).click();	//click on arrow for student's comment
			Thread.sleep(2000);
			//TC row no. 246
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available in instructor CS for a student comment on image widget.");
			}
			allToggleIcon.get(1).click();	//click on arrow for student's comment
			Thread.sleep(2000);
			//TC row no. 246
			removeComment1 = driver.findElements(By.className("ls-hide-comment"));
			if(!removeComment1.get(1).getText().contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available in instructor CS for a student comment on image widget.");
			}
			
			new LoginUsingLTI().ltiLogin("226_1");		//login as student
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().addPerspectiveForDWIneTextBook(randomtext);	//add a perspective
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the perspective
			
			new LoginUsingLTI().ltiLogin("226");		//login as instructor
			new Navigator().NavigateTo("Course Stream");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Comments']")));//click on Comment to expand
			Thread.sleep(2000);
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for student's comment
			Thread.sleep(2000);
			//TC row no. 239
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available in instructor CS for a student comment on perspective on DW.");
			}
			allToggleIcon.get(0).click();	//click on arrow for student's post
			Thread.sleep(2000);
			//TC row no. 240
			removePost = driver.findElement(By.className("ls-hide-post")).getText();
			if(!removePost.contains("Remove Perspective"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Perspective\" option is not available in instructor CS for a student's perspective on DW.");
			}
			
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().addPerspectiveForDWIneTextBook(randomtext);	//add a perspective
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the perspective
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for instructor's own comment
			Thread.sleep(2000);
			//TC row no. 239

			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available in instructor CS for instructor's own comment on perspective on DW.");
			}
			allToggleIcon.get(0).click();	//click on arrow for instructor's post
			Thread.sleep(2000);
			//TC row no. 240
			removePost = driver.findElement(By.className("ls-hide-post")).getText();
			if(!removePost.contains("Remove Perspective"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Perspective\" option is not available in instructor CS for instructor's own perspective on DW.");
			}

            new LoginUsingLTI().ltiLogin("226");		//login as instructor

            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='jumpout-icon ls-jumpout-img']")));//click on jump out icon
			Thread.sleep(1000);
			List<WebElement> allComments = driver.findElements(By.cssSelector("a[title='Comments']"));
			allComments.get(4).click(); //click on comments link to expand
			new MouseHover().mouserhover("perspective-comment-block");	//mouse hover the perspective comment block
			driver.findElement(By.xpath("(//div[@class='ls-dropdown__toggle'])[2]")).click();	//click on arrow icon
			Thread.sleep(2000);
			//TC row no. 249
		   removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available new discussion tab for student's comment.");
			}
			allComments.get(5).click(); //click on comments link to expand
			Thread.sleep(2000);
			List<WebElement> allHover = driver.findElements(By.className("perspective-comment-block"));
			new MouseHover().mouserhoverbywebelement(allHover.get(1));//mouse hover the perspective comment block
			driver.findElement(By.xpath("(//div[@class='ls-dropdown__toggle'])[4]")).click();	//click on arrow icon
			Thread.sleep(2000);
			//TC row no. 249
			removeComment1 = driver.findElements(By.className("ls-hide-comment"));
			if(!removeComment1.get(1).getText().contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available new discussion tab for instructor's own comment.");
			}
			
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.cssSelector("a[title='Comments']")).click();//click on Commnets link
			Thread.sleep(2000);
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(0).click();	//click on arrow for comment 
			Thread.sleep(2000);
			//TC row no. 250
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available for students's comments on Assignments in Assignments page.");
			}
			allToggleIcon.get(1).click();	//click on arrow for comment 
			Thread.sleep(2000);
			//TC row no. 250
			removeComment1 = driver.findElements(By.className("ls-hide-comment"));
			if(!removeComment1.get(1).getText().contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available for instructor's own comments on Assignments in Assignments page.");
			}
			
			driver.findElement(By.className("ls-grade-book-assessment")).click();	//click on Student View Response
			Thread.sleep(2000);
			List<WebElement> allComments1 = driver.findElements(By.cssSelector("a[title='Comments']"));
			allComments1.get(1).click();//click on Comments link
			Thread.sleep(2000);
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(2).click();	//click on arrow for comment 
			Thread.sleep(2000);
			//TC row no. 251
			removeComment1 = driver.findElements(By.className("ls-hide-comment"));
			if(!removeComment1.get(2).getText().contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available for students's comments on Assignments gradebook page.");
			}
			allToggleIcon.get(3).click();	//click on arrow for comment 
			Thread.sleep(2000);
			//TC row no. 251
			removeComment1 = driver.findElements(By.className("ls-hide-comment"));
			if(!removeComment1.get(3).getText().contains("Remove Comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Remove Comment\" option is not available for instructor's own comments on Assignments gradebook page.");
			}

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase removePostRemoveCommentOptionAtInstructorSide in class RemovePostRemoveCommentOptionAtInstructorSide",e);
		}
	}

}
