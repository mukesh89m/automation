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

public class OtherStudentsPerspectiveAndComment extends Driver{
	
	@Test
	public void otherStudentsPerspectiveAndComment()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("36_1");//login as student
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().addMultiplePerspectiveForDWIneTextBook(3);//add 3 perspective
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			String commentText = new RandomString().randomstring(15); 
			new DiscussionWidget().commentOnPerspective(commentText, 0);	//add a comment to Perspective

			new LoginUsingLTI().ltiLogin("36_2");//login as student
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
            Thread.sleep(5000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
            Thread.sleep(1000);
			Thread.sleep(3000);
            driver.findElement(By.xpath("//a[@title='View all perspectives']")).click(); //Click on view all perspectives
            List<WebElement> allComments = driver.findElements(By.cssSelector("a[title='Comments']"));
			for(WebElement comments: allComments)
			{
				if(comments.getText().contains("1"))
				{
					comments.click();	//click on Comments link to expand
					Thread.sleep(3000);
				}
			}
			new DiscussionWidget().clickOnArrowIconForPerspective(2, 2);//click on arrow of comment  //TC row no. 36
			//TC row no. 37
            boolean reportAbuse = new DiscussionWidget().reportAbuseTextVerifyForComment();
            System.out.println("Report Abuse:"+reportAbuse);
            if(reportAbuse == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Report Abuse\" option is not there for the comment.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-hide-comment")));//remove the comment
			Thread.sleep(3000);
			//TC row no. 38
            boolean abuseReported = new DiscussionWidget().abuseReportedTextVerifyForComment();
			if(abuseReported == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Report Abuse for comment on perspective the option doesnt become \"Abuse Reported\".");
			}
            new DiscussionWidget().clickOnArrowIconForPerspective(0, 0);//click on arrow icon for perspective //TC row no. 39
			//TC row no. 40
            boolean reportAbuse1 = new DiscussionWidget().reportAbuseTextVerifyForPerspective();
			if(reportAbuse1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Report Abuse\" option is not there for the perspective.");
			}
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-perspective-report-abuse")));//click on Report Abuse
			Thread.sleep(1000);
			//TC row no. 41
            boolean abuseReported1 = new DiscussionWidget().abuseReportedTextVerifyForPerspective();
			if(abuseReported1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Report Abuse for the perspective the option doesnt become \"Abuse Reported\".");
			}
			
			new LoginUsingLTI().ltiLogin("36_3");//login as student
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
            new DiscussionWidget().reportAbuseForPerspective(0, 0);//click on Report Abuse for Perspective
			
			new LoginUsingLTI().ltiLogin("36_4");//login as student
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
            new DiscussionWidget().reportAbuseForPerspective(0, 0);//click on Report Abuse for Perspective
			new LoginUsingLTI().ltiLogin("36_5");//login as student
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
            new DiscussionWidget().reportAbuseForPerspective(0, 0);//click on Report Abuse for Perspective
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			//TC row no. 42
			int message = driver.findElements(By.className("removed-perspective-block")).size();
			if(message != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Report Abuse by four students the perspective doesnt get removed.");
			}
			//TC row no. 43
			String messageText = driver.findElement(By.className("removed-perspective-block")).getText();
			if(!messageText.contains("This perspective has been removed."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Report Abuse by four students the message for removal of perspective doesnt appear.");
			}
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 44
			int posts = driver.findElements(By.className("ls-stream-post__action")).size();
			if(posts != 2)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Report Abuse by four students the Course Stream entry is not deleted.");
			}

  			driver.findElement(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']")).click();	//click on Jump out icon
			Thread.sleep(7000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Perspectives']")));
            Thread.sleep(3000);
            new DiscussionWidget().clickOnArrowIconForPerspective(0, 0);//click on arrow icon for perspective
			//TC row no. 45, 46
            boolean reportAbuse2 = new DiscussionWidget().reportAbuseTextVerifyForPerspective();
			if(reportAbuse2 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Report Abuse\" option is not there for the 2nd perspective.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-perspective-report-abuse")));//click on Report Abuse
			Thread.sleep(1000);
			//TC row no. 47
            boolean abuseReported2 = new DiscussionWidget().abuseReportedTextVerifyForPerspective();
			if(abuseReported2 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking Report Abuse for the perspective the option doesnt become \"Abuse Reported\".");
			}
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			List<WebElement> allToggle = driver.findElements(By.className("ls-dropdown__toggle"));	//click on arow icon
			allToggle.get(2).click();	//click on toggle icon of 3rd perspective
			Thread.sleep(3000);
			//TC row no. 48-49
			List<WebElement> allText = driver.findElements(By.className("ls-post-report-abuse"));
			if(!allText.get(1).getText().contains("Report Abuse"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Report Abuse\" option is not there for reporting the perspective from Course Stream.");
			}
			List<WebElement> allAbuseButton = driver.findElements(By.className("ls-post-report-abuse"));
			allAbuseButton.get(1).click();	//click on Report Abuse for 3rd perspective
			Thread.sleep(3000);
			allToggle.get(2).click();	//click on toggle icon of 3rd perspective
			//TC row no. 50
			List<WebElement> allAbuseText = driver.findElements(By.className("ls-post-report-abuse"));
			if(!allAbuseText.get(1).getText().contains("Abuse Reported"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Abuse Reported\" option is not there for reporting the perspective from Course Stream.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase otherStudentsPerspectiveAndComment in class OtherStudentsPerspectiveAndComment",e);
		}
	}

}
