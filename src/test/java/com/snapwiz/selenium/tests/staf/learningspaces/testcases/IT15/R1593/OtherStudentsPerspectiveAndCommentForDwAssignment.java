package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1593;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
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
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiscussionWidget;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class OtherStudentsPerspectiveAndCommentForDwAssignment extends Driver{
	
	@Test
	public void otherStudentsPerspectiveAndCommentForDwAssignment()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("127_1");		//create a student1
			new LoginUsingLTI().ltiLogin("127_2");		//create a student2
			new LoginUsingLTI().ltiLogin("127_3");		//create a student3
			new LoginUsingLTI().ltiLogin("127_4");		//create a student4
			new LoginUsingLTI().ltiLogin("127_5");		//create a student5

			new LoginUsingLTI().ltiLogin("127");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(127);

			new LoginUsingLTI().ltiLogin("127_1");		//login as student1
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			Thread.sleep(2000);
			String perspective = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective);
			//add comment to the perspective
			String commentText = new RandomString().randomstring(15); 
			List<WebElement> allComments = driver.findElements(By.xpath("//a[@class='ls-content-post__footer-comment-link js-toggle-comments']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allComments.get(0));	//click on Comment which will be at 1st index, at 0th index its in etextbook
			Thread.sleep(3000);
			driver.switchTo().activeElement().sendKeys(commentText+Keys.RETURN);	//add a comment to Perspective

			new LoginUsingLTI().ltiLogin("127_3");		//login as student2
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
            WebDriverWait wait=new WebDriverWait(driver,3500);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));


            new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
            new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
            Thread.sleep(4500);
            List<WebElement> comments= driver.findElements(By.xpath("//a[@class='ls-content-post__footer-comment-link js-toggle-comments']"));
            comments.get(0).click();
            new DiscussionWidget().clickOnArrowIconForPerspective(0, 0);//click on arrow of comment	//TC row no. 111
			//TC row no. 128
			boolean reportAbuse = new DiscussionWidget().reportAbuseTextVerifyForComment();
			if(reportAbuse == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Report Abuse\" option is not there for the comment on perspective of other students.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-hide-comment")));//remove the comment
			Thread.sleep(3000);
			//TC row no. 129
            boolean abuseReported = new DiscussionWidget().abuseReportedTextVerifyForComment();
			if(abuseReported == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Report Abuse for comment on perspective of other students the option doesnt become \"Abuse Reported\".");
			}
            new DiscussionWidget().clickOnArrowIconForPerspective(0, 0);//click on arrow icon for perspective //TC row no. 130
			//TC row no. 131
			boolean reportAbuse1 = new DiscussionWidget().reportAbuseTextVerifyForPerspective();
			if(reportAbuse1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Report Abuse\" option is not there for the Perspective of other students.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-perspective-report-abuse")));//click on Report Abuse
			Thread.sleep(1000);
			//TC row no. 132
            boolean abuseReported1 = new DiscussionWidget().abuseReportedTextVerifyForPerspective();
			if(abuseReported1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Report Abuse for the perspective the option doesnt become \"Abuse Reported\".");
			}
			

			new LoginUsingLTI().ltiLogin("127_2");//login as student3
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
            new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
            new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
            Thread.sleep(4500);
            new DiscussionWidget().reportAbuseForPerspective(0, 0);//click on Report Abuse for perspective

			new LoginUsingLTI().ltiLogin("127_4");//login as student4
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
            new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
            new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
            Thread.sleep(4500);
            new DiscussionWidget().reportAbuseForPerspective(0, 0);//click on Report Abuse for perspective


			new LoginUsingLTI().ltiLogin("127_5");//login as student5
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
            new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
            new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
            Thread.sleep(4500);
            new DiscussionWidget().reportAbuseForPerspective(0, 0);//click on Report Abuse for perspective
			//just refresh the page
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
            new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
            new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
            Thread.sleep(4500);

			//TC row no. 133
			int message = driver.findElements(By.className("removed-perspective-block")).size();
			if(message != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Report Abuse by four students the perspective doesnt get removed.");
			}
			//TC row no. 133
			String messageText = driver.findElement(By.className("removed-perspective-block")).getText();
			if(!messageText.contains("This perspective has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Report Abuse by four students the message for removal of perspective doesnt appear.");
			}

			new LoginUsingLTI().ltiLogin("127_1");		//login as student1
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			//TC row no. 134
			String status = driver.findElement(By.className("ls-assignment-status")).getText();
			if(!status.contains("Submitted"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After abuse reporting of a perspective the perspective Assignment status is not Submitted.");
			}
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
            new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
            new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
            Thread.sleep(4500);
			Thread.sleep(3000);
			//TC row no. 135
			int message1 = driver.findElements(By.className("removed-perspective-block")).size();
			if(message1 != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Report Abuse by four students the perspective doesnt get removed for the student who posted the perspective.");
			}
			//TC row no. 135
			String messageText1 = driver.findElement(By.className("removed-perspective-block")).getText();
			if(!messageText1.contains("This perspective has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Report Abuse by four students the message for removal of perspective doesnt appear for the student who posted the perspective.");
			}
			
			new LoginUsingLTI().ltiLogin("127");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			//TC row no. 136
			int message2 = driver.findElements(By.className("removed-perspective-block")).size();
			if(message2 != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Report Abuse by four students the perspective doesnt get removed from instructor side.");
			}
			//TC row no. 136
			String messageText2 = driver.findElement(By.className("removed-perspective-block")).getText();
			if(!messageText2.contains("This perspective has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Report Abuse by four students the message for removal of perspective doesnt appear in intsructor side.");
			}
			
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			//TC row no. 137
			String status1 = driver.findElement(By.className("ls-assignment-status")).getText();
			if(!status1.contains("Available for Students"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After abuse reporting of a perspective the perspective Assignment status is not Available for Students in Assignment page of instructor.");
			}
			driver.findElement(By.className("ls-grade-book-assessment")).click();	//click on View Student Responses
			Thread.sleep(2000);
            new MouseHover().mouserhover("idb-question-manually-graded");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on View Responses
            Thread.sleep(2000);
			//TC row no. 138
			int perspective1 = driver.findElements(By.className("ls-comment-entry")).size();
			if(perspective1 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In gradebook page of DW assignment the hidden perspectives are not shown.");
			}
			//TC row no. 139
			String visualIndicator = driver.findElement(By.className("ls-perspective-visual-indicator")).getCssValue("background-image");
			if(!visualIndicator.contains("perspective-visual-indicator.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In gradebook page of DW assignment the visual indicator is not shown for hidden perspectives.");
			}
			//TC row no. 140
			String perspectiveCount = driver.findElement(By.cssSelector("a[title='Perspectives']")).getText();
			if(!perspectiveCount.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In gradebook page of DW assignment the perspective count changes if the student removes his perspective.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase otherStudentsPerspectiveAndCommentForDwAssignment in class OtherStudentsPerspectiveAndCommentForDwAssignment",e);
		}
	}

}
