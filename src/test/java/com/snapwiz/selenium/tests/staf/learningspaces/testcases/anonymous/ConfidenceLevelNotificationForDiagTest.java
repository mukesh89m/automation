package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;

public class ConfidenceLevelNotificationForDiagTest extends Driver{
@Test(priority = 1, enabled = true)	
	public void confidenceLevelNotificationForDiagTest()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("2476");
			new Navigator().NavigateTo("eTextbook");
            new Click().clickBycssselector("a[data-type='diagnostic_assessment']");//click on diag test
			//click on continue button
            new Click().clickByclassname("ls-assessment-continue-btn");
			Thread.sleep(1000);
			String notification = driver.findElement(By.className("al-notification-message-body")).getText();
			if(!notification.equals("Please say how confident you feel about this chapter. You will get more accurate recommendations!"))
				Assert.fail("Student is not getting notification when student does not click the confidence level when starting the diagnostic test.");
			Thread.sleep(8000); //giving seven second sleep
			//check notification after 7 seconds
			int noticeSize = driver.findElements(By.className("al-notification-message-body")).size();
			if(noticeSize != 0)
				Assert.fail("The notification doesnt disappear after 7 seconds.");

            new TopicOpen().chapterOpen(1);//click on 2nd chapter
            Thread.sleep(3000);
            new Click().clickbylistcssselector("a[data-type='diagnostic_assessment']", 1);//click on second chapter's diagnostic test
			//click on continue button
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input.ls-assessment-continue-btn")));
			Thread.sleep(1000);
			String notification1 = driver.findElement(By.className("al-notification-message-body")).getText();
			if(!notification1.equals("Please say how confident you feel about this chapter. You will get more accurate recommendations!"))
				Assert.fail("Student is not getting notification when student does not click the confidence level when starting other chpaters diagnostic test.");

            new TopicOpen().chapterOpen(0);//click on 1st chapter
			new DiagnosticTest().startTest(2); //start a diag test
			//click quit
			driver.findElement(By.cssSelector("div[class='al-quit-diag-test-icon']")).click();
			Thread.sleep(3000);
			//click continue later
			driver.findElement(By.cssSelector("span[class='al-diag-test-continue-later']")).click();
			Thread.sleep(3000);
            new Click().clickBycssselector("a[data-type='diagnostic_assessment']");//click on diag test
			int noticeSize1 = driver.findElements(By.className("al-notification-message-body")).size();
			if(noticeSize1 != 0)
				Assert.fail("The notification 1002 is coming 2nd time time when restarting the diagnostic test for the chapter.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));//click on submit button
            Thread.sleep(2000);
			String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
			if(!notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
				Assert.fail("Doesnt display the notification showing as Recommendation on the Robo icon to the user with the message 'You will build your proficiency faster if you say how confident you feel about the question before answering.'");
			Thread.sleep(8000); //giving seven second sleep
			//check notification after 7 seconds
			int noticeSize2 = driver.findElements(By.className("al-notification-message-body")).size();
			if(noticeSize2 != 0)
				Assert.fail("The notification doesnt disappear after 7 seconds.");
			
			driver.findElement(By.cssSelector("input[type=\"button\"]")).click();   //click on submit button
			driver.findElement(By.cssSelector("input[type=\"button\"]")).click();    //click on submit button
			Thread.sleep(2000);
			int noticeSize3 = driver.findElements(By.className("al-notification-message-body")).size();
			if(noticeSize3 != 0)
				Assert.fail("The notification 1003 is coming for the second question also.");
		
			new AttemptTest().Diagonestictest();    //complete the Diagnostic test

            new Navigator().NavigateTo("eTextbook");
			new PracticeTest().startTest();   //start adaptive practice
			//driver.findElement(By.className("qtn-label")).click(); //click on answer option
			driver.findElement(By.cssSelector("input[type=\"button\"]")).click();   //click on submit button
			Thread.sleep(1000);
			String notice1 =  driver.findElement(By.className("al-notification-message-body")).getText();
			if(!notice1.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
				Assert.fail("Doesnt display the notification showing as Recommendation on the Robo icon to the user with the message 'You will build your proficiency faster if you say how confident you feel about the question before answering.' for adaptive tests.");
			
			driver.findElement(By.cssSelector("input[type=\"button\"]")).click();  //click on submit button
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input[id='next-practice-question-button']")));
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("input[type=\"button\"]")).click();   //click on submit button
			Thread.sleep(2000);
			int noticeSize4 = driver.findElements(By.className("al-notification-message-body")).size();
			if(noticeSize4 != 0)
				Assert.fail("The notification 1003 is coming for the second question also for adaptive tests.");
			
			/*//click on TLO level assessment
			new TabClose().tabClose(1);
			new TOCShow().tocShow();
			new TopicOpen().topicOpen(tlolevelAssessment); 
			Thread.sleep(2000);*/
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in confidenceLevelNotificationForDiagTest in class ConfidenceLevelNotificationForDiagTest",e);
		}
	}
@Test(priority = 2, enabled = true)
public void checkNotificationInPerformanceSummaryPage()
{
	try
	{
		new LoginUsingLTI().ltiLogin("2488");
		new Navigator().NavigateTo("eTextbook");
		new DiagnosticTest().startTest(2);
		new DiagnosticTest().attemptAllCorrect(0, false, false);
        String notification2 = driver.findElement(By.cssSelector("div[class='al-notification-message-wrapper']")).getText();
        System.out.println("notification2: " +notification2);
        String notification = driver.findElement(By.cssSelector("div[class='al-notification-message-body']")).getText();
        System.out.println("notification: " +notification);
        String notification1 = notification.replaceAll("[\n\r]", "");
        System.out.println("notification1: " +notification1);
		if(!notification1.equals("You can click on the graph to see more detail about the questions you answered.You can then click a question item from the right side area to see the details on that question."))
			Assert.fail("On landing on the performance summary page for the first time notification is not displayed.");
		Thread.sleep(7000);  //wait for 7 seconds to notice disappear
		int notificationSize = driver.findElements(By.className("al-notification-message-body")).size();
		if(notificationSize != 0)
			Assert.fail("Performance summary page Notification doesnt disappear after 7 seconds.");

        new Navigator().NavigateTo("eTextbook");
        new DiagnosticTest().startTest(2);//restart the diagnostic test
		int notificationSize1 = driver.findElements(By.className("al-notification-message-body")).size();
		if(notificationSize1 != 0)
			Assert.fail("Performance summary page Notification is shown again when we land on the performance summary page for second time.");
	}
	catch(Exception e)
	{
		Assert.fail("Exception in checkNotificationInPerformanceSummaryPage in class ConfidenceLevelNotificationForDiagTest",e);
	}
}

}
