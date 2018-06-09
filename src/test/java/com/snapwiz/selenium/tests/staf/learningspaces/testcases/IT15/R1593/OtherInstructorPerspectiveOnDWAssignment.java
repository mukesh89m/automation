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

public class OtherInstructorPerspectiveOnDWAssignment extends Driver{
	
	@Test
	public void otherInstructorPerspectiveOnDWAssignment()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("167_1");		//login as instructor2
			new LoginUsingLTI().ltiLogin("167");		//login as instructor1
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			String perspective = new RandomString().randomstring(15); 
			new DiscussionWidget().addPerspectiveForDWIneTextBook(perspective);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			String commentText = new RandomString().randomstring(15); 
			new DiscussionWidget().commentOnPerspective(commentText, 0);	//add a comment to Perspective
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(167);

			new LoginUsingLTI().ltiLogin("167_1");		//login as instructor2
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			//TC row no. 167
            boolean hide = new DiscussionWidget().removePerspectiveTextNotPresentVerify(0);
			if(hide == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Option for Report Abuse/Remove perspective appears for other instructors for an instructor added perspective on a DW assignment.");
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
            //TC row no. 168
			boolean hide1 = new DiscussionWidget().removeCommentTextNotPresentVerify(0);
			if(hide1 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Option for Report Abuse/Remove perspective appears for other instructors for an instructor added comment on a perspective on a DW assignment.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase otherInstructorPerspectiveOnDWAssignment in class OtherInstructorPerspectiveOnDWAssignment",e);
		}
	}

}
