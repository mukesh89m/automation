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
/*
 * brajesh
 */
public class ProficienciesVariationWithHighExistingProfWithConfidenceLevel
{
	@Test(priority=1,enabled=true)
	public void ProficienciesVariationWithHighExistingProfWithConfidenceLevelanswercorrectly()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("64"); //  Logging in as student to orion
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
			new PracticeTest().attemptPracticeQuesFromEachTLO(4, "correct", 1, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser64practestwithconfidence1 = new Proficiency().getProficiencyOfEachTLO();
	 		for(Integer it:Proficienciesuser64practestwithconfidence1)
			{
				System.out.println(it);
			}
			System.out.println("-------------------");
	 		new Proficiency().isProficiencyImproved(Proficienciesuser64practestwithconfidence1, proficiencyuser64diagtest);
	 		//Attempting practice test as correct answers with confidence as 'Somewhat Confident'
	 		new LoginUsingLTI().ltiLogin("65"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(4, "correct", 2, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser65practestwithconfidence2 = new Proficiency().getProficiencyOfEachTLO();
	 		for(Integer it:Proficienciesuser65practestwithconfidence2)
			{
				System.out.println(it);
			}
			System.out.println("-------------------");
	 		new Proficiency().isProficiencyImproved(Proficienciesuser65practestwithconfidence2, Proficienciesuser64practestwithconfidence1);
	 		//Attempting practice test as correct answers with confidence as 'Almost Confident'
	 		new LoginUsingLTI().ltiLogin("66"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(4, "correct", 3, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser66practestwithconfidence3 = new Proficiency().getProficiencyOfEachTLO();
	 		for(Integer it:Proficienciesuser66practestwithconfidence3)
			{
				System.out.println(it);
			}
			System.out.println("-------------------");
	 		new Proficiency().isProficiencyImproved(Proficienciesuser66practestwithconfidence3, Proficienciesuser65practestwithconfidence2);
	 		///Attempting practice test as correct answers with confidence as 'I Know It'
	 		new LoginUsingLTI().ltiLogin("67"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(4, "correct", 4, false, false);
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
	public void ProficienciesVariationWithHighExistingProfWithConfidenceLevelanswerIncorrectly()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("68"); //  Logging in as student to orion
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
			new PracticeTest().attemptPracticeQuesFromEachTLO(4, "incorrect", 1, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser64practestwithconfidence1 = new Proficiency().getProficiencyOfEachTLO();
	 		for(Integer it:Proficienciesuser64practestwithconfidence1)
			{
				System.out.println(it);
			}
			System.out.println("-------------------");
	 		new Proficiency().isProficiencyDeclined(Proficienciesuser64practestwithconfidence1, proficiencyuser64diagtest);
	 		//Attempting practice test as correct answers with confidence as 'Somewhat Confident'
	 		new LoginUsingLTI().ltiLogin("69"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(4, "incorrect", 2, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser65practestwithconfidence2 = new Proficiency().getProficiencyOfEachTLO();
	 		for(Integer it:Proficienciesuser65practestwithconfidence2)
			{
				System.out.println(it);
			}
			System.out.println("-------------------");
	 		new Proficiency().isProficiencyImproved(Proficienciesuser65practestwithconfidence2, Proficienciesuser64practestwithconfidence1);
	 		//Attempting practice test as correct answers with confidence as 'Almost Confident'
	 		new LoginUsingLTI().ltiLogin("70"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(4, "incorrect", 3, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser66practestwithconfidence3 = new Proficiency().getProficiencyOfEachTLO();
	 		for(Integer it:Proficienciesuser66practestwithconfidence3)
			{
				System.out.println(it);
			}
			System.out.println("-------------------");
	 		new Proficiency().isProficiencyImproved(Proficienciesuser66practestwithconfidence3, Proficienciesuser65practestwithconfidence2);
	 		///Attempting practice test as correct answers with confidence as 'I Know It'
	 		new LoginUsingLTI().ltiLogin("71"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(4, "incorrect", 4, false, false);
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
