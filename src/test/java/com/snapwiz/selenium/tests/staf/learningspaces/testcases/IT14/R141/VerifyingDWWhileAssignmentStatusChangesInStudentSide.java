package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Widget;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class VerifyingDWWhileAssignmentStatusChangesInStudentSide extends Driver {

	@Test
	public void verifyingDWWhileAssignmentStatusChangesInStudentSide()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("432_1");		//login as student
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new Widget().perspectiveAdd();	//TC row no. 432
			
			new LoginUsingLTI().ltiLogin("432");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(432);
			
			new LoginUsingLTI().ltiLogin("432_1");		//login as student
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			driver.findElement(By.className("navigate-to-jump-out-icon")).click();//click on Enter Submission
			Thread.sleep(2000);
			//TC row no. 434
			String perspective1 = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective1);
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a[class='ls-content-post__footer-comment-link js-toggle-comments']")).click();
			Thread.sleep(2000);
			String commentText = new RandomString().randomstring(10);
			driver.switchTo().activeElement().sendKeys(commentText+Keys.RETURN);
			String text2 = driver.findElement(By.className("ls-perspctive-comments-posted")).getText();
			//TC row no. 434
			if(!text2.contains(commentText))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Student is not able to add the Comment on perspective.");
			}
			driver.findElement(By.className("js-comment-like")).click();
			Thread.sleep(2000);
			String text1 = new TextFetch().textfetchbyclass("js-comment-like");
			//TC row no. 435
			if(!text1.contains("(1)"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Student is not able to like the Comment on perspective.");
			}
			
			driver.findElement(By.className("js-discussion-comment-like")).click();//click on like for perspective
			Thread.sleep(2000);
			String text4 = new TextFetch().textfetchbyclass("js-discussion-comment-like");
			//TC row no. 435
			if(!text4.contains("Unlike"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Student is not able to like the DW after assigning it.");
			}
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("js-discussion-post-like"))); //click on like for DW in eTextbook
			Thread.sleep(2000);
			String text3 = new TextFetch().textfetchbyclass("js-discussion-post-like");
			//TC row no. 435
			if(!text3.contains("Unlike"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Student is not able to like the DW after it is assigned by instructor.");
			}

			
			new LoginUsingLTI().ltiLogin("432_1");		//login as student
		    new Widget().openWidgetFromAssignmentPage();
			String perspective = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective);
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			driver.findElement(By.className("navigate-to-jump-out-icon")).click();//click on Enter Submission
			Thread.sleep(2000);
			String perspective2 = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective2);
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("a[class='ls-content-post__footer-comment-link js-toggle-comments']")).click();
			Thread.sleep(5000);
			//new Click().clickByclassname("redactor_placeholder");
			String commentText1 = new RandomString().randomstring(10);
            List<WebElement> comment=driver.findElements(By.xpath("//div[@placeholder='Write your comment']"));
			for(WebElement ele:comment){
				if(ele.isDisplayed()){
					ele.sendKeys(commentText1);
				}
			}

			List<WebElement> post=driver.findElements(By.xpath("//div[@class='post-comment']"));
			for(WebElement ele:post){
				if(ele.isDisplayed()){
					System.out.println("here");
					ele.click();
				}
			}
			List<WebElement> text6 = driver.findElements(By.className("ls-perspctive-comments-posted"));
			//TC row no. 437
			if(!text6.get(0).getText().contains(commentText1))//
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Student is not able to add the Comment on perspective for a Submitted DW assignment.");
			}
			
			List<WebElement> allCommentLike1 = driver.findElements(By.className("js-comment-like"));
			allCommentLike1.get(0).click();	//click on like for recently added comment
			//TC row no. 438
			List<WebElement> allCommentText1 = driver.findElements(By.className("js-comment-like"));
			if(!allCommentText1.get(0).getText().contains("(1)"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Student is not able to like the Comment on perspective for a Submitted DW assignment.");
			}
		

			
			new LoginUsingLTI().ltiLogin("432");		//login as instructor
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.cssSelector("span[title='View Student Responses']")).click();	//click on View Student Responses
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("div[title='Release Grade for All']")).click();	//click on Release Grade for All
			Thread.sleep(2000);

			new LoginUsingLTI().ltiLogin("432_1");		//login as student
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			driver.findElement(By.className("navigate-to-jump-out-icon")).click();//click on Enter Submission
			Thread.sleep(2000);
			List<WebElement> allCommentLike2 = driver.findElements(By.cssSelector("a[class='ls-content-post__footer-comment-link js-toggle-comments']"));
			//TC row no. 441
			
			allCommentLike2.get(allCommentLike2.size()-1).click();	//click on like for recently added comment
			List<WebElement> allCommentText2 = driver.findElements(By.className("js-comment-like"));
			if(!allCommentText2.get(0).getText().contains("(1)"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Student is not able to like the Comment on perspective for a Graded DW assignment.");
			}
			List<WebElement> allPerspectiveLike2 = driver.findElements(By.className("js-discussion-comment-like"));
			allPerspectiveLike2.get(1).click();//click on like for perspective
			Thread.sleep(2000);
			List<WebElement> allPerspectiveText2 = driver.findElements(By.className("js-discussion-comment-like"));
			//TC row no. 441
			if(!allPerspectiveText2.get(1).getText().contains("Unlike"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Student is not able to like the DW after assigning it for a Graded DW assignment.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyingDWWhileAssignmentStatusChangesInStudentSide in class VerifyingDWWhileAssignmentStatusChangesInStudentSide.",e);
		}
	}

}
