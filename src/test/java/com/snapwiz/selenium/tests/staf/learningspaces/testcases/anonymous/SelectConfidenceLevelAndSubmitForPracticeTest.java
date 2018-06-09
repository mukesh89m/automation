package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class SelectConfidenceLevelAndSubmitForPracticeTest extends Driver{
	
	@Test
	public void selectConfidenceLevelAndSubmitForPracticeTest()
	{
		try
		{
			
			String card1topic2 = ReadTestData.readDataByTagName("tocdata", "card1topic2", "1");
			new LoginUsingLTI().ltiLogin("2606");
			new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);
			new AttemptTest().Diagonestictest();
			new TopicOpen().topicOpen(card1topic2);
			driver.findElement(By.className("qtn-label")).click();		//select an answer choice
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("confidence-level-somewhat")));  //select confidence  level
			Thread.sleep(3000);
			driver.findElement(By.id("submit-practice-question-button")).click();
			driver.findElement(By.id("next-practice-question-button")).click();
			Thread.sleep(3000);
			String question = driver.findElement(By.className("al-diag-test-question-raw-content")).getText();
			if(question == null)
				Assert.fail("After selecting confidence level and then clicking Submit then Next, the next question is not getting displayed.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in selectConfidenceLevelAndSubmitForPracticeTest in class SelectConfidenceLevelAndSubmitForPracticeTest",e);
		}
	}

}
