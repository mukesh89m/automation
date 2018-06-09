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

public class OtherInstructorsPerspectiveAndComment extends Driver{
	
	@Test
	public void otherInstructorsPerspectiveAndComment()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("106_1");//login as instructor
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			String perspective = new RandomString().randomstring(15); 
			new DiscussionWidget().addPerspectiveForDWIneTextBook(perspective);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			String commentText = new RandomString().randomstring(15); 
			new DiscussionWidget().commentOnPerspective(commentText, 0);	//add a comment to Perspective
			
			new LoginUsingLTI().ltiLogin("106_2");//login as other instructor
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			//TC row no. 107
			boolean hide = new DiscussionWidget().removePerspectiveTextNotPresentVerify(0);
			if(hide == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Option for Report Abuse/Remove perspective appears for other instructors for an instructor added perspective.");
			}
			List<WebElement> allComments = driver.findElements(By.cssSelector("a[title='Comments']"));
			for(WebElement comments: allComments)
			{
				if(comments.getText().contains("1"))
				{
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", comments);//click on Comments link to expand
					Thread.sleep(3000);
				}
			}
            //TC row no. 106
			boolean hide1 = new DiscussionWidget().removeCommentTextVerify();
			if(hide1 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Option for Report Abuse/Remove perspective appears for other instructors for an instructor added comment on a perspective.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='jumpout-icon ls-jumpout-img']"))); //clicking on jump out icon
			Thread.sleep(3000);
            boolean hide2 = new DiscussionWidget().removePerspectiveTextNotPresentVerify(0);
            //TC row no. 108
            if(hide2 == true )
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Option for Report Abuse appears for other instructors for an instructor added perspective in new Discussion tab.");
			}
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new MouseHover().mouserhover("ls-stream-post__action");	//mouse hover the entry in Course Stream
            //TC row no. 109
			int reportAbuse = driver.findElements(By.className("ls-post-report-abuse")).size();
			if(reportAbuse != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course Stream, Option for Report Abuse appears for students for an instructor added perspective.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase otherInstructorsPerspectiveAndComment in class OtherInstructorsPerspectiveAndComment",e);
		}
	}

}
