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

public class ProficienciesVariationWithHighExistingProfWithConfidenceLevelAndHints 
{
	@Test(priority=1,enabled=true)
	public void ProficienciesVariationWithHighExistingProfWithConfidenceLevelAndHintsanswercorrectly()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("72"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();
			new Navigator().orionDashboard();
			List<Integer> proficiencyuser64diagtest = new Proficiency().getProficiencyOfEachTLO();
			for(Integer it:proficiencyuser64diagtest)
			{
				System.out.println(it);
			}
			System.out.println("-------------------");
			//Attempting practice test as correct answers with confidence as 'I don't know'
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(3, "correct", 1, true, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser64practestwithconfidence1 = new Proficiency().getProficiencyOfEachTLO();
	 		for(Integer it:Proficienciesuser64practestwithconfidence1)
			{
				System.out.println(it);
			}
			System.out.println("-------------------");
	 		new Proficiency().isProficiencyDeclined(Proficienciesuser64practestwithconfidence1, proficiencyuser64diagtest);
	 		//Attempting practice test as correct answers with confidence as 'Somewhat Confident'
	 		new LoginUsingLTI().ltiLogin("73"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(3, "correct", 2, true, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser65practestwithconfidence2 = new Proficiency().getProficiencyOfEachTLO();
	 		for(Integer it:Proficienciesuser65practestwithconfidence2)
			{
				System.out.println(it);
			}
			System.out.println("-------------------");
	 		new Proficiency().isProficiencyImproved(Proficienciesuser65practestwithconfidence2, Proficienciesuser64practestwithconfidence1);
	 		//Attempting practice test as correct answers with confidence as 'Almost Confident'
	 		new LoginUsingLTI().ltiLogin("74"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(3, "correct", 3, true, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser66practestwithconfidence3 = new Proficiency().getProficiencyOfEachTLO();
	 		for(Integer it:Proficienciesuser66practestwithconfidence3)
			{
				System.out.println(it);
			}
			System.out.println("-------------------");
	 		new Proficiency().isProficiencyImproved(Proficienciesuser66practestwithconfidence3, Proficienciesuser65practestwithconfidence2);
	 		///Attempting practice test as correct answers with confidence as 'I Know It'
	 		new LoginUsingLTI().ltiLogin("75"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(3, "correct", 4, true, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser67practestwithconfidence4 = new Proficiency().getProficiencyOfEachTLO();
	 		for(Integer it:Proficienciesuser67practestwithconfidence4)
			{
				System.out.println(it);
			}
			System.out.println("-------------------");
	 		new Proficiency().isProficiencyImproved(Proficienciesuser67practestwithconfidence4, Proficienciesuser66practestwithconfidence3);
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exeption in testcase ProficienciesVariationWithHighExistingProfWithConfidenceLevelanswercorrectly in class ProficienciesVariationWithHighExistingProfWithConfidenceLevel",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void ProficienciesVariationWithHighExistingProfWithConfidenceLevelAndHintsanswerIncorrectly()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("76"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();
			new Navigator().orionDashboard();
			List<Integer> proficiencyuser64diagtest = new Proficiency().getProficiencyOfEachTLO();
			for(Integer it:proficiencyuser64diagtest)
			{
				System.out.println(it);
			}
			System.out.println("-------------------");
			//Attempting practice test as correct answers with confidence as 'I don't know'
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(3, "incorrect", 1, true, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser64practestwithconfidence1 = new Proficiency().getProficiencyOfEachTLO();
	 		for(Integer it:Proficienciesuser64practestwithconfidence1)
			{
				System.out.println(it);
			}
			System.out.println("-------------------");
	 		new Proficiency().isProficiencyDeclined(Proficienciesuser64practestwithconfidence1, proficiencyuser64diagtest);
	 		//Attempting practice test as correct answers with confidence as 'Somewhat Confident'
	 		new LoginUsingLTI().ltiLogin("77"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(3, "incorrect", 2, true, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser65practestwithconfidence2 = new Proficiency().getProficiencyOfEachTLO();
	 		for(Integer it:Proficienciesuser65practestwithconfidence2)
			{
				System.out.println(it);
			}
			System.out.println("-------------------");
	 		new Proficiency().isProficiencyImproved(Proficienciesuser65practestwithconfidence2, Proficienciesuser64practestwithconfidence1);
	 		//Attempting practice test as correct answers with confidence as 'Almost Confident'
	 		new LoginUsingLTI().ltiLogin("78"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(3, "incorrect", 3, true, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser66practestwithconfidence3 = new Proficiency().getProficiencyOfEachTLO();
	 		for(Integer it:Proficienciesuser66practestwithconfidence3)
			{
				System.out.println(it);
			}
			System.out.println("-------------------");
	 		new Proficiency().isProficiencyImproved(Proficienciesuser66practestwithconfidence3, Proficienciesuser65practestwithconfidence2);
	 		///Attempting practice test as correct answers with confidence as 'I Know It'
	 		new LoginUsingLTI().ltiLogin("79"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(3, "incorrect", 4, true, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser67practestwithconfidence4 = new Proficiency().getProficiencyOfEachTLO();
	 		for(Integer it:Proficienciesuser67practestwithconfidence4)
			{
				System.out.println(it);
			}
			System.out.println("-------------------");
	 		new Proficiency().isProficiencyImproved(Proficienciesuser67practestwithconfidence4, Proficienciesuser66practestwithconfidence3);
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exeption in testcase ProficienciesVariationWithHighExistingProfWithConfidenceLevelanswercorrectly in class ProficienciesVariationWithHighExistingProfWithConfidenceLevel",e);
		}
	}
	
	
	@AfterMethod
	public void TearDown()throws Exception
	{
		Driver.driver.quit();
	}

}
