package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class VerfifyQuestionObjectiveForStatic extends Driver{
	
	@Test
	public void verfifyQuestionObjectiveForStatic()
	{
		try
		{

			String statictest = ReadTestData.readDataByTagName("tocdata", "statictest", "1");
			String questionobjectivetab = ReadTestData.readDataByTagName("VerfifyQuestionObjectiveForStatic", "questionobjectivetab", "2689");
			new LoginUsingLTI().ltiLogin("2689");
			Thread.sleep(3000);
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().topicOpen(statictest);		//start static test
			String questionObjective = driver.findElement(By.className("al-question-objective-content-wrapper")).getText();
			if(!questionObjective.contains("Question Objectives"))
				Assert.fail("'Question Objective' is not present in the performance");
			driver.findElement(By.className("question-association-skill-id")).click();	//click on 1st question objectives
			Thread.sleep(3000);
			String QObjectiveTab  = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!QObjectiveTab.equals(questionobjectivetab))
				Assert.fail("Lesson linked to the TLO doesnot open in a new or existing tab.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verfifyQuestionObjectiveForStatic in class VerfifyQuestionObjectiveForStatic",e);
		}
	}

}
