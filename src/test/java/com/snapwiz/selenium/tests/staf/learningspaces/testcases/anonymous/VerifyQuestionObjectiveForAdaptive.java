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

public class VerifyQuestionObjectiveForAdaptive extends Driver{
	@Test
	public void verifyQuestionObjectiveForAdaptive()
	{
		try
		{
			String adaptivetest = ReadTestData.readDataByTagName("tocdata", "adaptivetest", "1");
			String questionobjectivetab = ReadTestData.readDataByTagName("VerifyQuestionObjectiveForAdaptive", "questionobjectivetab", "2622");
			new LoginUsingLTI().ltiLogin("2622");
			Thread.sleep(3000);
			new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);		//open a diag test
			new AttemptTest().Diagonestictest();
			new TopicOpen().topicOpen(adaptivetest);	//open adaptive practice
			String questionObjective = driver.findElement(By.className("al-question-objective-content-wrapper")).getText();
			if(!questionObjective.contains("Question Objectives"))
				Assert.fail("'Question Objective' is not present in the performance");
			driver.findElement(By.className("question-association-skill-id")).click();	//click on 1st question objectives
			Thread.sleep(3000);
			String QObjectiveTab  = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			System.out.println("QObjectiveTab: "+QObjectiveTab);
			if(!QObjectiveTab.equals(questionobjectivetab))
				Assert.fail("Lesson linked to the TLO doesnot open in a new tab");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifyQuestionObjectiveForAdaptive in class VerifyQuestionObjectiveForAdaptive",e);
		}
	}

}
