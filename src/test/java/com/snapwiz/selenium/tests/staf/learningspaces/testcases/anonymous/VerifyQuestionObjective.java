package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class VerifyQuestionObjective extends Driver {
@Test
	public void verifyQuestionObjective()
	{
		try
		{
			String questionobjectivetab = ReadTestData.readDataByTagName("VerifyQuestionObjective", "questionobjectivetab", "2556");
			new LoginUsingLTI().ltiLogin("2556");
			Thread.sleep(3000);
			new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);		//open a diag test
			String questionObjective = driver.findElement(By.className("al-question-objective-content-wrapper")).getText();
			if(!questionObjective.contains("Question Objectives"))
				Assert.fail("'Question Objective' is not present in teh performance");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("question-association-skill-id")));
			Thread.sleep(3000);
			String QObjectiveTab  = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			
			if(!QObjectiveTab.trim().equals(questionobjectivetab))
				Assert.fail("Lesson linked to the TLO doesnot open in a new tab");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifyQuestionObjective in class VerifyQuestionObjective",e);
		}
	}

}
