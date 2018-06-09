package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AttemptTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class NotificationOnClickingMarkForReview extends Driver {
	@Test(priority = 1, enabled = true)
	public void notificationOnClickingMarkForReview()
	{
		try
		{
			String card1topic2 = ReadTestData.readDataByTagName("tocdata", "card1topic2", "1");
			new LoginUsingLTI().ltiLogin("2496");
			new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);
			driver.findElement(By.cssSelector("label[id='al-diagtest-markForReview']")).click();    //click 'Mark for Review'
			Thread.sleep(3000);
			String markReviewNotice = driver.findElement(By.className("al-notification-message-body")).getText();
			if(!markReviewNotice.equals("You can see the questions that you mark for review by clicking on \"My Reports\" under your profile and navigating to the \"Performance Report\". Just look for the questions using the \"Status\" filter."))
				Assert.fail("On clicking 'Mark for review' for the first time, required notification is not coming.");
			driver.findElement(By.cssSelector("input[type=\"button\"]")).click();   //click submit button(notification comes)
			driver.findElement(By.cssSelector("input[type=\"button\"]")).click();	//again click submit button
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("label[id='al-diagtest-markForReview']")).click();  //click 'Mark for Review'
			Thread.sleep(3000);
			int markReviewNoticeSize = driver.findElements(By.className("al-notification-message-body")).size();
			if(markReviewNoticeSize != 0)
				Assert.fail("On clicking 'Mark for Review' on second question notification is still coming.");
			
			new AttemptTest().Diagonestictest();
			new TopicOpen().topicOpen(card1topic2);   //start adaptive practice
			driver.findElement(By.cssSelector("label[id='al-diagtest-markForReview']")).click();    //click 'Mark for Review'
			Thread.sleep(3000);
			int markReviewNoticeSize1 = driver.findElements(By.className("al-notification-message-body")).size();
			if(markReviewNoticeSize1 != 0)
				Assert.fail("On clicking 'Mark for Review' for adaptive practice notification is still coming.");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in notificationOnClickingMarkForReview in class NotificationOnClickingMarkForReview",e);
		}
	}
@Test(priority = 2, enabled = true)
public void verifyMarkForReviewFromPerformnceSummaryPage()
{
	try
	{
		new LoginUsingLTI().ltiLogin("2499");
		new Navigator().NavigateTo("eTextbook");
		new DiagnosticTest().startTest(2);
		new AttemptTest().Diagonestictest();
		driver.findElement(By.cssSelector("div.question-card-question-content")).click();    //click on 1st question from question card
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("label[id='al-diagtest-markForReview']")).click();    //click 'Mark for Review'
		Thread.sleep(3000);
		int markReviewNoticeSize = driver.findElements(By.className("al-notification-message-body")).size();
		if(markReviewNoticeSize == 0)
			Assert.fail("On clicking 'Mark for Review' from navigating from the question card the notification is not displayed.");
	}
	catch(Exception e)
	{
		Assert.fail("Exception in verifyMarkForReviewFromPerformnceSummaryPage in class NotificationOnClickingMarkForReview",e);
	}
}


}
