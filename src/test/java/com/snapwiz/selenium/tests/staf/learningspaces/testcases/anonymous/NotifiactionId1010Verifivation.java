package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AttemptTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandCollapseChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class NotifiactionId1010Verifivation extends Driver{
	@Test(priority = 1, enabled = true)
	public void notifiactionId1010VerifivationforChapterLevelPracticeTest()
	{
		try
		{
			String adaptivetest = ReadTestData.readDataByTagName("tocdata", "adaptivetest", "1");
			String otherpracticetest = ReadTestData.readDataByTagName("NotifiactionId1010Verifivation", "otherpracticetest", "2507");
			String otherdiagtest = ReadTestData.readDataByTagName("NotifiactionId1010Verifivation", "otherdiagtest", "2507");
			new LoginUsingLTI().ltiLogin("2507");
			Thread.sleep(3000);
			new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);
			new AttemptTest().Diagonestictest();
			new TopicOpen().topicOpen(adaptivetest);  // start chapter level practice test
			driver.findElement(By.className("qtn-label")).click();
			driver.findElement(By.id("submit-practice-question-button")).click();   //on 1st click on submit a notification comes
			driver.findElement(By.id("submit-practice-question-button")).click();
			Thread.sleep(2000);
			String notification = driver.findElement(By.className("al-notification-message-body")).getText();
			if(!notification.equals("Click on the \"Stream\" tab to make your own personal notes and and discussions with your class."))
				Assert.fail("On clicking 'submit' after selecting a answer from the chapter level practice test the required notification is not coming.");
			driver.findElement(By.id("next-practice-question-button")).click();
			driver.findElement(By.className("qtn-label")).click();
			driver.findElement(By.id("submit-practice-question-button")).click();
			Thread.sleep(2000);
			int notificationSize = driver.findElements(By.className("al-notification-message-body")).size();
			if(notificationSize != 0)
				Assert.fail("Notification id 1001 is still coming for 2nd question in chapter level practice test.");
			new TOCShow().tocShow();
			Thread.sleep(2000);
			new ExpandCollapseChapter().collapseChapter(1);
			Thread.sleep(3000);
			new ExpandCollapseChapter().expandChapter(2);
			new TopicOpen().topicOpen(otherdiagtest);  //open diag test for second chapter 
			//click on 'yes' on pop-up to continue the current assessment later
			driver.findElement(By.cssSelector("div[class='reload-assessment-notification-option assessment-notification-option-yes']")).click();
			Thread.sleep(2000);
			new AttemptTest().Diagonestictest();   //finish diag test for second chapter 
			new TopicOpen().topicOpen(otherpracticetest);  //open practice test for chapter 2
			Thread.sleep(3000);
			driver.findElement(By.id("submit-practice-question-button")).click();    //on 1st click on submit a notification comes
			driver.findElement(By.id("submit-practice-question-button")).click();
			Thread.sleep(2000);
			int notificationSize1 = driver.findElements(By.className("al-notification-message-body")).size();
			if(notificationSize1 != 0)
				Assert.fail("Notification id 1001 is still coming for other chapter level practice test.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in notifiactionId1010VerifivationforChapterLevelPracticeTest in class NotifiactionId1010Verifivation",e);
		}

	}
	@Test(priority = 2, enabled = true)
public void notifiactionId1010VerifivationforTLOLevelPracticeTest()
{
	try
	{
		String card2topic2 = ReadTestData.readDataByTagName("tocdata", "card2topic2", "1");
		//String chapter2 = ReadTestData.readDataByTagName("tocdata", "chapter2", "1");
		String otherdiagtest = ReadTestData.readDataByTagName("NotifiactionId1010Verifivation", "otherdiagtest", "25071");
		String otherTLOpracticetest = ReadTestData.readDataByTagName("NotifiactionId1010Verifivation", "otherTLOpracticetest", "25071");
		new LoginUsingLTI().ltiLogin("25071");
		Thread.sleep(3000);
		new Navigator().NavigateTo("eTextbook");
		new DiagnosticTest().startTest(2);
		new AttemptTest().Diagonestictest();
		new TopicOpen().topicOpen(card2topic2);
		driver.findElement(By.className("qtn-label")).click();    //select an answer choice
		driver.findElement(By.id("submit-practice-question-button")).click();  //on 1st click on submit a notification comes
		driver.findElement(By.id("submit-practice-question-button")).click();
		Thread.sleep(2000);
		String notification = driver.findElement(By.className("al-notification-message-body")).getText();
		if(!notification.equals("Click on the \"Stream\" tab to make your own personal notes and and discussions with your class."))
			Assert.fail("On clicking 'submit' then clicking 'next' after selecting a answer from the chapter level practice test the required notification is not coming.");
		driver.findElement(By.id("next-practice-question-button")).click();
		driver.findElement(By.className("qtn-label")).click();  //select an answer choice
		driver.findElement(By.id("submit-practice-question-button")).click();
		Thread.sleep(2000);
		int notificationSize = driver.findElements(By.className("al-notification-message-body")).size();
		if(notificationSize != 0)
			Assert.fail("Notification id 1001 is still coming for 2nd question in TLO level practice test.");
		new TOCShow().tocShow();
		Thread.sleep(2000);
		new ExpandCollapseChapter().collapseChapter(1);
		Thread.sleep(3000);
		new ExpandCollapseChapter().expandChapter(2);
		new TopicOpen().topicOpen(otherdiagtest);  //open diag test for second chapter 
		
		//click on 'yes' on pop-up to continue the current assessment later
		driver.findElement(By.cssSelector("div[class='reload-assessment-notification-option assessment-notification-option-yes']")).click();
		Thread.sleep(2000);
		new AttemptTest().Diagonestictest();   //finish diag test for second chapter 
		new TopicOpen().topicOpen(otherTLOpracticetest); //open a TLO practice test
		Thread.sleep(3000);
		driver.findElement(By.id("submit-practice-question-button")).click();    //on 1st click on submit a notification comes
		driver.findElement(By.id("submit-practice-question-button")).click();
		Thread.sleep(2000);
		int notificationSize1 = driver.findElements(By.className("al-notification-message-body")).size();
		if(notificationSize1 != 0)
			Assert.fail("Notification id 1001 is still coming for other TLO level practice test in other chapters.");
	}
	catch(Exception e)
	{
		Assert.fail("Exception in notifiactionId1010VerifivationforTLOLevelPracticeTest in class NotifiactionId1010Verifivation",e);
	}
}

}
