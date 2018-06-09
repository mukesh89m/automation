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
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.CommentOnPost;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Discussion;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiscussionWidget;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ResourseCreate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class RemovePostAndRemoveCommentOptionAtStudentSide extends Driver{
	
	@Test
	public void removePostAndRemoveCommentOptionAtStudentSide()
	{
		try
		{

			new ResourseCreate().resourseCreate(200, 0); //upload a resource
			new Assignment().create(200);	//create Assignment

			new LoginUsingLTI().ltiLogin("200_1");		//login as student1
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			String randomtext = new RandomString().randomstring(15);
			List<WebElement> allToggleIcon;
            String removeComment = "";
            String removePost = "";
			new PostMessage().postmessage(randomtext);
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the post
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for comment
			Thread.sleep(2000);
			//TC row no. 203
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				Assert.fail("\"Remove Comment\" option is not available in student side CS for a comment on post.");
			}
			allToggleIcon.get(0).click();	//click on arrow for post
			Thread.sleep(2000);
			//TC row no. 200
			removePost = driver.findElement(By.className("ls-hide-post")).getText();
			if(!removePost.contains("Remove Post"))
			{
				Assert.fail("\"Remove Post\" option is not available in student side CS for a post.");
			}
			new FileUpload().fileUpload("200", true);
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the file upload
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for comment
			Thread.sleep(2000);
			//TC row no. 204
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				Assert.fail("\"Remove Comment\" option is not available in student side CS for a comment on uploaded file.");
			}
			allToggleIcon.get(0).click();	//click on arrow for uploaded file
			Thread.sleep(2000);
			//TC row no. 201
			removePost = driver.findElement(By.className("ls-hide-post")).getText();
			if(!removePost.contains("Remove Post"))
			{
				Assert.fail("\"Remove Post\" option is not available in student side CS for a File upload.");
			}
			
			new PostMessage().postlink("www.google.com");	//post a link
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the link
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for comment
			Thread.sleep(2000);
			//TC row no. 205
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				Assert.fail("\"Remove Comment\" option is not available in student side CS for a comment on posted link.");
			}
			allToggleIcon.get(0).click();	//click on arrow for posted link
			Thread.sleep(2000);
			//TC row no. 202
			removePost = driver.findElement(By.className("ls-hide-post")).getText();
			if(!removePost.contains("Remove Post"))
			{
				Assert.fail("\"Remove Post\" option is not available in student side CS for a posted a link.");
			}
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TOCShow().tocHide();
			new Discussion().postDiscussion(randomtext);	//post a discussion
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the posted discussion
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for comment on discussion
			Thread.sleep(2000);
			//TC row no. 207
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				Assert.fail("\"Remove Comment\" option is not available in student side CS for a comment on discussion.");
			}
			allToggleIcon.get(0).click();	//click on arrow for posted discussion
			Thread.sleep(2000);
			//TC row no. 206
			removePost = driver.findElement(By.className("ls-hide-post")).getText();
			if(!removePost.contains("Remove Post"))
			{
				Assert.fail("\"Remove Post\" option is not available in student side CS for a posted discussion.");
			}
			
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TOCShow().tocHide();
			driver.findElement(By.cssSelector("i.ls-icon-img.ls--like-icon")).click();	//click on Like on image widget
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the image widget CS entry
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for comment to the image widget CS entry
			Thread.sleep(2000);
			//TC row no. 209
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				Assert.fail("\"Remove Comment\" option is not available in student side CS for a comment on the image widget CS entry.");
			}

			
			
			new LoginUsingLTI().ltiLogin("200");		//login as instructor
			new Assignment().assignToStudent(200);  //Assigning assignment

			new LoginUsingLTI().ltiLogin("200_1");		//login as student1
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the assigned assignment
			/*allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));*/
            driver.findElement(By.xpath("(//div[@class='ls-dropdown__toggle'])[1]")).click();
			/*allToggleIcon.get(1).click();	//click on arrow for comment to the assigned assignment
			Thread.sleep(2000);*/
			//TC row no. 216
			removeComment = driver.findElement(By.xpath("//a[text()='Remove Comment']")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				Assert.fail("\"Remove Comment\" option is not available in student side CS for a comment on assigned assignment.");
			}

			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TOCShow().tocHide();	//open first chapters first lesson
			new Navigator().navigateToTab("Resources");
			new Navigator().openResourceFromResourceTabFromCMS(200);	//open resource
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.ls-right-post-like-link")));//like the resource
			Thread.sleep(2000);		
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(1);	//add a comment to the assigned assignment
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for comment to the assigned assignment
			Thread.sleep(2000);
			//TC row no. 218
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				Assert.fail("\"Remove Comment\" option is not available in student side CS for a comment on resource.");
			}

			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TOCShow().tocHide();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Comments']")));
			Thread.sleep(2000);
			driver.switchTo().activeElement().sendKeys(randomtext+Keys.RETURN);	//add comment to image widget
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(0).click();	//click on arrow for comment to the comment on image widget
			Thread.sleep(2000);
			//TC row no. 220
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				Assert.fail("\"Remove Comment\" option is not available for image widget comment in eTextbook.");
			}
			driver.findElement(By.cssSelector("div.toggle-widget-size.toggle-widget-size-collapse")).click();//click on collapse
			Thread.sleep(2000);
			
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().addPerspectiveForDWIneTextBook(randomtext);	//add a perspective
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new AssignmentSocialElement().clickoncommnetcoursestream(0);	//add a comment to the perspective
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(1).click();	//click on arrow for comment to the perspective
			Thread.sleep(2000);
			//TC row no. 213, 214
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				Assert.fail("\"Remove Comment\" option is not available in student side CS for a comment on the perspective.");
			}
			allToggleIcon.get(0).click();	//click on arrow for posted discussion
			Thread.sleep(2000);
			//TC row no. 212
			removePost = driver.findElement(By.className("ls-hide-post")).getText();
			if(!removePost.contains("Remove Perspective"))
			{
				Assert.fail("\"Remove Perspective\" option is not available in student side CS for the added perspective to DW.");
			}

			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			List<WebElement> allComments = driver.findElements(By.cssSelector("a[title='Comments']"));
			for(WebElement comments: allComments)
			{
				if(comments.getText().contains("1"))
				{
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", comments);//click on Comments link to expand
					Thread.sleep(3000);
				}
			}
			new MouseHover().mouserhover("perspective-comment-block");	//mouse hover the perspective comment block
			List<WebElement> allArrows = driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allArrows.get(1));//click on arrow of comment
			Thread.sleep(3000);
			//TC row no. 222
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				Assert.fail("\"Remove Comment\" option is not there in DW lesson.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='jumpout-icon ls-jumpout-img']")));//click on jump out icon
			Thread.sleep(1000);
			List<WebElement> allComments1 = driver.findElements(By.cssSelector("a[title='Comments']"));
			for(WebElement comments: allComments1)
			{
				if(comments.getText().contains("1"))
				{
					comments.click();	//click on Comments link to expand
					Thread.sleep(3000);
				}
			}
            List<WebElement> allHover = driver.findElements(By.cssSelector("div[class='ls-perspctive-comments-posted']"));
            new MouseHover().mouserhoverbywebelement(allHover.get(1));
			List<WebElement> allArrows1 = driver.findElements(By.cssSelector("div[class='ls-dropdown__toggle']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allArrows1.get(3));//click on arrow of comment
			Thread.sleep(3000);
			//TC row no. 223
			List<WebElement> removeComment1 = driver.findElements(By.className("ls-hide-comment"));
			if(!removeComment1.get(1).getText().contains("Remove Comment"))
			{
				Assert.fail("\"Remove Comment\" option is not there in DW lesson.");
			}
			
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.cssSelector("a[title='Comments']")).click();//click on Commnets link
			Thread.sleep(2000);
			allToggleIcon.clear();	//clear the List
			allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(0).click();	//click on arrow for comment to the comment on image widget
			Thread.sleep(2000);
			//TC row no. 224
			removeComment = driver.findElement(By.className("ls-hide-comment")).getText();
			if(!removeComment.contains("Remove Comment"))
			{
				Assert.fail("\"Remove Comment\" option is not available for comments on Assignments in Assignments page.");
			}

			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().lessonOpen(0, 0);//open first chapters first lesson
            new TopicOpen().ResourcesOpenInNewtab(0, 0);
            driver.findElement(By.cssSelector("textarea[class='ls-textarea-focus resource-post-comment']")).sendKeys("This is comment");
            driver.findElement(By.cssSelector("div[class='post-comment resource-post-comment']")).click();
            Thread.sleep(2000);

			//new CommentOnPost().commentOnPost("Comment on resource", 2);//comment on resource
			//allToggleIcon.clear();	//clear the List
			/*allToggleIcon = driver.findElements(By.className("ls-dropdown__toggle"));
			allToggleIcon.get(0).click();	//click on arrow for comment to the comment on image widget
			Thread.sleep(2000);*/
            driver.findElement(By.xpath("//div[@class='ls-dropdown__toggle']")).click();
            Thread.sleep(2000);
		    //TC row no. 225
			String removeComment2 = driver.findElement(By.xpath("(//a[text()='Remove Comment'])")).getText();
            System.out.println(removeComment2);
            if(!removeComment2.contains("Remove Comment"))
			{
				Assert.fail("\"Remove Comment\" option is not available for resource on opening resource from resource tab.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase removePostAndRemoveCommentOptionAtStudentSide in class RemovePostAndRemoveCommentOptionAtStudentSide",e);
		}
	}

}
