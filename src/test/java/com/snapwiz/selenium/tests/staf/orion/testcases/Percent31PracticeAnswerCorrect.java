package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Proficiency;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectAnswerAndSubmit;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class Percent31PracticeAnswerCorrect {
	@Test(priority = 1,enabled=true)
	public void diagPercent31PracticeAnswerCorrect()
	{
		try
		{
		Driver.startDriver();
		new LoginUsingLTI().ltiLogin("14"); //  Logging in as student to orion
		new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
		new DiagnosticTest().lessThanThirtyOnePercentCorrect();
		new Navigator().orionDashboard();
		List<Integer> oldProficiencies = new Proficiency().getProficiencyOfEachTLO(); //Fetch proficiencies in each TLO after diag test
		//Attempting practice test as correct answers
		new PracticeTest().startTest(); //start the practice test
		new SelectAnswerAndSubmit().practiceTestAttempt(0, 20, "correct",false,false); //Attempt 20 quest correctly without selecting confidence level
		new PracticeTest().quitTestAndGoToDashboard();	//Quit practice test and go to dashboard	
 		List<Integer> newProficiencies = new Proficiency().getProficiencyOfEachTLO(); //Fetch new proficiencies after practice test
		new Proficiency().isProficiencyImproved(newProficiencies, oldProficiencies);
		//Attempting practice test as incorrect answers
		new PracticeTest().startTest();
		new SelectAnswerAndSubmit().practiceTestAttempt(0, 20, "incorrect",false,false);
		new PracticeTest().quitTestAndGoToDashboard();
		List<Integer> newProficiencies_afterincorrect = new Proficiency().getProficiencyOfEachTLO();
		new Proficiency().isProficiencyDeclined(newProficiencies_afterincorrect, newProficiencies);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in Testcase diagPercent31PracticeAnswerCorrect in class Percent31PracticeAnswerCorrect",e);
		}
		
	}
	
	@Test(priority = 2,enabled=true)
	public void diagPercent31PracticeAnswerInCorrect()
	{
		try
		{
		Driver.startDriver();
		new LoginUsingLTI().ltiLogin("15"); //  Logging in as student to orion
		new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
		new DiagnosticTest().lessThanThirtyOnePercentCorrect();
		new Navigator().orionDashboard();
		List<Integer> oldProficiencies = new Proficiency().getProficiencyOfEachTLO();
		//Attempting practice test as correct answers
		new PracticeTest().startTest();
		new SelectAnswerAndSubmit().practiceTestAttempt(0, 20, "incorrect",false,false);
		new PracticeTest().quitTestAndGoToDashboard();		
 		List<Integer> newProficiencies = new Proficiency().getProficiencyOfEachTLO();
		new Proficiency().isProficiencyDeclined(newProficiencies, oldProficiencies);		
		}
		catch(Exception e)
		{
			Assert.fail("Exception in Testcase diagPercent31PracticeAnswerCorrect in class Percent31PracticeAnswerCorrect",e);
		}
	}
	
	@Test(priority = 3,enabled=true)
	public void diagPercent31PracticeAnswerSkipAll()
	{
		try
		{
		Driver.startDriver();
		new LoginUsingLTI().ltiLogin("16"); //  Logging in as student to orion
		new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
		new DiagnosticTest().lessThanThirtyOnePercentCorrect();
		new Navigator().orionDashboard();
		List<Integer> oldProficiencies = new Proficiency().getProficiencyOfEachTLO();
		for(Integer prof:oldProficiencies)
			System.out.println(prof);
		//Attempting practice test as correct answers
		new PracticeTest().startTest();
		new SelectAnswerAndSubmit().practiceTestAttempt(0, 20, "skip",false,false);
		new PracticeTest().quitTestAndGoToDashboard();		
 		List<Integer> newProficiencies = new Proficiency().getProficiencyOfEachTLO();
		new Proficiency().isProficiencySame(newProficiencies, oldProficiencies);		
		}
		catch(Exception e)
		{
			Assert.fail("Exception in Testcase diagPercent31PracticeAnswerCorrect in class Percent31PracticeAnswerCorrect",e);
		}
	}
	
	@AfterMethod
	public void TearDown()throws Exception
	{
		new Screenshot().captureScreenshotFromTestCase();
		Driver.driver.quit();
	}
	
}
