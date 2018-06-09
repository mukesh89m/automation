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
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;


public class ProficiencyVariationWithConfidence {
	@Test(priority=1,enabled=true)
	public void proficiencyVariationWithConfidenceAnswerCorrectly()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("20"); //  Logging in as student to orion
			new DiagnosticTest().startTest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().lessThanThirtyOnePercentCorrect();
			new Navigator().orionDashboard();
			List<Integer> proficiencyuser20diagtest = new Proficiency().getProficiencyOfEachTLO();
			for(Integer prof:proficiencyuser20diagtest)
				System.out.println("User 1 After diag profs "+prof);
			//Attempting practice test as correct answers with confidence as 'I don't know'
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(5, "correct", 1, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser20practestwithconfidence1 = new Proficiency().getProficiencyOfEachTLO();
	 		for(Integer prof:Proficienciesuser20practestwithconfidence1)
				System.out.println("User 1 After practice profs "+prof);
	 		new Proficiency().isProficiencyImproved(Proficienciesuser20practestwithconfidence1, proficiencyuser20diagtest);
	 		
	 		//Attempting practice test as correct answers with confidence as 'Somewhat Confident'
	 		new LoginUsingLTI().ltiLogin("21"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().lessThanThirtyOnePercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(5, "correct", 2, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser21practestwithconfidence2 = new Proficiency().getProficiencyOfEachTLO();
	 		for(Integer prof:Proficienciesuser21practestwithconfidence2)
				System.out.println("User 1 After practice profs "+prof);
	 		new Proficiency().isProficiencyImproved(Proficienciesuser21practestwithconfidence2, Proficienciesuser20practestwithconfidence1);
	 		
	 		//Attempting practice test as correct answers with confidence as 'Almost Confident'
	 		new LoginUsingLTI().ltiLogin("22"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().lessThanThirtyOnePercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(5, "correct", 3, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser22practestwithconfidence3 = new Proficiency().getProficiencyOfEachTLO();
	 		for(Integer prof:Proficienciesuser22practestwithconfidence3)
				System.out.println("User 1 After practice profs "+prof);
	 		new Proficiency().isProficiencyImproved(Proficienciesuser22practestwithconfidence3, Proficienciesuser21practestwithconfidence2);
	 		
	 		//Attempting practice test as correct answers with confidence as 'I Know It'
	 		new LoginUsingLTI().ltiLogin("23"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().lessThanThirtyOnePercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(5, "correct", 4, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser23practestwithconfidence4 = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencyImproved(Proficienciesuser23practestwithconfidence4, Proficienciesuser22practestwithconfidence3);
	 		for(Integer prof:Proficienciesuser23practestwithconfidence4)
				System.out.println("User 1 After practice profs "+prof);
	 		
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase proficiencyVariationWithConfidence in class ProficiencyVariationWithConfidence",e);
		}
	}
	
	
	@Test(priority=2,enabled=true)
	public void proficiencyVariationWithConfidence1AnswerInCorrectly()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("24"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().lessThanThirtyOnePercentCorrect();
			new Navigator().orionDashboard();
			List<Integer> oldProficiencies = new Proficiency().getProficiencyOfEachTLO();
			for(Integer prof:oldProficiencies)
				System.out.println("User 1 diag profs "+prof);
			//Attempting practice test as correct answers with confidence as 'I don't know'
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(5, "incorrect", 1, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficiencieswithconfidence1 = new Proficiency().getProficiencyOfEachTLO();
	 		for(Integer prof:Proficiencieswithconfidence1)
				System.out.println("User 1 After practice profs "+prof);
	 		new Proficiency().isProficiencyDeclined(Proficiencieswithconfidence1, oldProficiencies);
	 		
	 		new LoginUsingLTI().ltiLogin("25"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().lessThanThirtyOnePercentCorrect();
			new Navigator().orionDashboard();
			List<Integer> proficiencyafterdiagtest = new Proficiency().getProficiencyOfEachTLO();
			for(Integer prof:proficiencyafterdiagtest)
				System.out.println("User 2 diag profs "+prof);
			//Attempting practice test as incorrect answers with confidence as 'Somewhat Confident'
			new PracticeTest().startTest();
			//new SelectAnswerAndSubmit().practiceTestAttempt(2, 20, "incorrect",false,false);
			new PracticeTest().attemptPracticeQuesFromEachTLO(5, "incorrect", 2, false, false);
			new PracticeTest().quitTestAndGoToDashboard();	
			List<Integer> profafteruser2practice =  new Proficiency().getProficiencyOfEachTLO();
			for(Integer prof:profafteruser2practice)
				System.out.println("User 2 After practice profs "+prof);
	 		new Proficiency().isProficiencySame(profafteruser2practice,Proficiencieswithconfidence1);
	 		
	 	}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase proficiencyVariationWithConfidence in class ProficiencyVariationWithConfidence",e);
		}
	}
	
	@Test(priority=3,enabled=true)
	public void proficiencyVariationWithConfidence2AnswerInCorrectly()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("26"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().lessThanThirtyOnePercentCorrect();
			new Navigator().orionDashboard();
			List<Integer> proficiencyafterdiagtest = new Proficiency().getProficiencyOfEachTLO();
			for(Integer prof:proficiencyafterdiagtest)
				System.out.println("User 1 diag profs "+prof);
			//Attempting practice test as correct answers with confidence as 'Somewhat Confident'
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(5, "incorrect", 3, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficiencieswithconfidence1 = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencyImproved(Proficiencieswithconfidence1, proficiencyafterdiagtest);
	 		for(Integer prof:Proficiencieswithconfidence1)
				System.out.println("User 1 prac profs "+prof);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase proficiencyVariationWithConfidence2AnswerInCorrectly in class ProficiencyVariationWithConfidence",e);
		}
	}
		
		@Test(priority=4,enabled=true)
		public void proficiencyVariationWithConfidence4AnswerInCorrectly()
		{
			try
			{
				Driver.startDriver();
				new LoginUsingLTI().ltiLogin("27"); //  Logging in as student to orion
				new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
				new DiagnosticTest().lessThanThirtyOnePercentCorrect();
				new Navigator().orionDashboard();
				List<Integer> proficiencyafterdiagtest = new Proficiency().getProficiencyOfEachTLO();
				for(Integer prof:proficiencyafterdiagtest)
					System.out.println("User 1 diag profs "+prof);
				//Attempting practice test as correct answers with confidence as 'Somewhat Confident'
				new PracticeTest().startTest();
				new PracticeTest().attemptPracticeQuesFromEachTLO(5, "incorrect", 4, false, false);
				new PracticeTest().quitTestAndGoToDashboard();		
		 		List<Integer> Proficiencieswithconfidence1 = new Proficiency().getProficiencyOfEachTLO();
		 		new Proficiency().isProficiencyImproved(Proficiencieswithconfidence1, proficiencyafterdiagtest);
		 		for(Integer prof:Proficiencieswithconfidence1)
					System.out.println("User 1 prac profs "+prof);
			}
			catch(Exception e)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Exception in testcase proficiencyVariationWithConfidence4AnswerInCorrectly in class ProficiencyVariationWithConfidence",e);
			}
		
	}
	
	@AfterMethod
	public void TearDown()throws Exception
	{
		Driver.driver.quit();
	}
}
