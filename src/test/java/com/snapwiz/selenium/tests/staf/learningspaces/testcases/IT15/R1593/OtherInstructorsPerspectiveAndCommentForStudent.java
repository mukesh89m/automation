package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1593;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class OtherInstructorsPerspectiveAndCommentForStudent extends Driver{

	@Test
	public void otherInstructorsPerspectiveAndCommentForStudent()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("141_1");//create a student
			new LoginUsingLTI().ltiLogin("141");//login as instructor
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			String perspective = new RandomString().randomstring(15); 
			new DiscussionWidget().addPerspectiveForDWIneTextBook(perspective);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			String commentText = new RandomString().randomstring(15); 
			new DiscussionWidget().commentOnPerspective(commentText, 0);	//add a comment to Perspective
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(141);
			
			new LoginUsingLTI().ltiLogin("141_1");//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			new Click().clickBycssselector("div[class='ls-stream-learing-activity-level ls-assignment-learning-activity ellipsis']");
			//driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			Thread.sleep(2000);
			//TC row no. 141
			int hide = driver.findElements(By.className("ls-perspective-hide")).size();
			if(hide != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Option for Report Abuse/Remove perspective appears for students for an instructor added perspective.");
			}
			new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']");
			new Click().clickByclassname("navigate-to-jump-out-icon");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='ls-content-post__footer-comment-link js-toggle-comments']")));//click on Comments link to expand
			Thread.sleep(3000);
            //TC row no. 142
			boolean hide1 = new DiscussionWidget().removeCommentTextNotPresentVerify(0);
			if(hide1 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Option for Report Abuse/Remove comment appears for students for an instructor added comment on a perspective.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase otherInstructorsPerspectiveAndCommentForStudent in class OtherInstructorsPerspectiveAndCommentForStudent",e);
		}
	}

}
