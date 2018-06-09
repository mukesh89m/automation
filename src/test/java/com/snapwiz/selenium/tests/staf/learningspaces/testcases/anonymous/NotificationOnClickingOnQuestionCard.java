package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AttemptTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;

public class NotificationOnClickingOnQuestionCard extends Driver{
	@Test
	public void notificationOnClickingOnQuestionCard()
	{
		try
		{
			String card1topic1 = ReadTestData.readDataByTagName("tocdata", "card1topic1", "1");
			new LoginUsingLTI().ltiLogin("2500");
			new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);
			new AttemptTest().Diagonestictest();
			driver.findElement(By.cssSelector("div.question-card-question-content")).click();    //click on 1st question from question card
			Thread.sleep(2000);
			String notification = driver.findElement(By.className("al-notification-message-body")).getText();
			if(!notification.equals("Want to see the graphs again? Just click on this icon near the top of the page."))
				Assert.fail("On clicking 'Mark for review' for the first time, required notification is not coming.");
			//click on graph icon
			driver.findElement(By.cssSelector("img[title=\"Performance by Questions\"]")).click();
			Thread.sleep(2000);
			List<WebElement> allQuestions = driver.findElements(By.cssSelector("div.question-card-question-content"));
			allQuestions.get(1).click();
			Thread.sleep(2000);
			int notificationSize = driver.findElements(By.className("al-notification-message-body")).size();
			if(notificationSize != 0)
				Assert.fail("Clicking anyother question from the question card the notification is coming.");
			
			new TabClose().tabClose(1);
			new TOCShow().tocShow();
			//restart the diag test
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText(card1topic1)));
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("div.question-card-question-content")).click();    //click on 1st question from question card
			Thread.sleep(2000);	
			int notificationSize1 = driver.findElements(By.className("al-notification-message-body")).size();
			if(notificationSize1 != 0)
				Assert.fail("Again Clicking question from the question card the notification is coming.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in notificationOnClickingOnQuestionCard in class NotificationOnClickingOnQuestionCard",e);
		}
	}

}
