package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1593;

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

public class InstructorPerspectiveAndComment extends Driver{
	
	@Test
	public void instructorPerspectiveAndComment()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("51");//login as instructor
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			String perspective = new RandomString().randomstring(15); 
			new DiscussionWidget().addPerspectiveForDWIneTextBook(perspective);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			String commentText = new RandomString().randomstring(15); 
			new DiscussionWidget().commentOnPerspective(commentText, 0);	//add a comment to Perspective
			
			new LoginUsingLTI().ltiLogin("51_1");//login as student
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			new MouseHover().mouserhover("ls-stream-post-comment");	//mouse hover the perspective block
			//TC row no. 52
			int hide = driver.findElements(By.className("ls-perspective-hide")).size();
			if(hide!=0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Option for Report Abuse appears for students for an instructor added perspective.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='jumpout-icon ls-jumpout-img']"))); //clicking on jump out icon
			Thread.sleep(3000);
			WebElement we = driver.findElement(By.xpath(".//*[@id='ls-learn-content']/div[2]/div[2]/div[2]/div/div[3]/ul[1]/li/div[3]/div[2]"));
			new MouseHover().mouserhoverbywebelement(we);
			//TC row no. 53
			int hide1 = driver.findElements(By.className("ls-perspective-hide")).size();
			if(hide1 !=0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Option for Report Abuse appears for students for an instructor added perspective in new Discussion tab.");
			}
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			new MouseHover().mouserhover("ls-stream-post__action");	//mouse hover the entry in Course Stream
			//TC row no. 54
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
			Assert.fail("Exception in testcase instructorPerspectiveAndComment in class InstructorPerspectiveAndComment",e);
		}
	}

}
