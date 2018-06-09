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

public class NotifiactionId2004Verifivation extends Driver{
	@Test(priority = 1, enabled = true)
	public void notifiactionId2004VerifivationForChapterLevelPracticeTest()
	{
		try
		{
			String adaptivetest = ReadTestData.readDataByTagName("tocdata", "adaptivetest", "1");
			new LoginUsingLTI().ltiLogin("2519");
			Thread.sleep(3000);
			new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);
			new AttemptTest().Diagonestictest();
			new TopicOpen().topicOpen(adaptivetest);		// start chapter level practice test
			new AttemptTest().AdaptiveTest(10);		//Attempt first 10 questions
			String notification = driver.findElement(By.className("al-notification-message-body")).getText();
			String notifiactionTrimmed = notification.replaceAll("[\n\r]", "");
			if(!notifiactionTrimmed.equals("You've recently completed 10 questions for this topic. You can stop practicing questions at any time by clicking the Quit button on the top right. Would you like to continue practicing?Yes, continue practicing ››No, quit practicing for now and view your performance report ››No, quit practicing and go back to your dashboard ››"))
				Assert.fail("Notification 2004 is not coming properly after submitting the 1st 10 question of adaptive practice.");
			//click on continue in the notification
			driver.findElement(By.cssSelector("span[id='exit-practice-test-block']")).click();
			Thread.sleep(3000);
			new AttemptTest().AdaptiveTest(10);		//Attempt next 10 questions
			String notification1 = driver.findElement(By.className("al-notification-message-body")).getText();
			String notifiactionTrimmed1 = notification1.replaceAll("[\n\r]", "");
			if(!notifiactionTrimmed1.equals("You've recently completed 20 questions for this topic. You can stop practicing questions at any time by clicking the Quit button on the top right. Would you like to continue practicing?Yes, continue practicing ››No, quit practicing for now and view your performance report ››No, quit practicing and go back to your dashboard ››"))
				Assert.fail("Notification 2004 is not coming properly after submitting the next 10 question of adaptive practice.");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in notifiactionId2004VerifivationForChapterLevelPracticeTest in class NotifiactionId2004Verifivation",e);
		}
	}
	
}
