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

//Akansh 06-01-2013
public class ProficienciesModerateVariation {
	@Test(priority = 1, enabled = true)
	public void proficiencyImprovedWithCorrectAnswerInPracticeTest()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("36"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().thirtyOneToSixtyPercentCorrect();
			new Navigator().orionDashboard();
			List<Integer> proficienciesafterdiagtestuser36 = new Proficiency().getProficiencyOfEachTLO();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "correct", 0, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> proficiencyafterpractestallcorrectuser36 = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencyImproved(proficiencyafterpractestallcorrectuser36, proficienciesafterdiagtestuser36);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exeption in testcase proficiencyVariationWithPracticeTest in class ProficienciesModerateVariation",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void proficiencyDeclinedWithInCorrectAnswerInPracticeTest()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("37"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().thirtyOneToSixtyPercentCorrect();
			new Navigator().orionDashboard();
			List<Integer> proficienciesafterdiagtestuser37 = new Proficiency().getProficiencyOfEachTLO();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "incorrect", 0, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> proficiencyafterpractestallincorrectuser37 = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencyDeclined(proficiencyafterpractestallincorrectuser37, proficienciesafterdiagtestuser37);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exeption in testcase proficiencyVariationWithPracticeTest in class ProficienciesModerateVariation",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void proficiencySameWithAnswerSkippedInPracticeTest()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("38"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().thirtyOneToSixtyPercentCorrect();
			new Navigator().orionDashboard();
			List<Integer> proficienciesafterdiagtestuser38 = new Proficiency().getProficiencyOfEachTLO();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "skip", 0, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> proficiencyafterpractestallskipuser37 = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencySame(proficiencyafterpractestallskipuser37, proficienciesafterdiagtestuser38);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exeption in testcase proficiencyVariationWithPracticeTest in class ProficienciesModerateVariation",e);
		}
	}
	
	@Test(priority=4,enabled=true)
	public void ProficiencyModrateWithIncorrectPracticeAndUsingHints()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("40"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().thirtyOneToSixtyPercentCorrect();//attempt 31 tp 60 % correct answer of daig texst
			new Navigator().orionDashboard();
			List<Integer> proficienciesafterdiagtestuser40 = new Proficiency().getProficiencyOfEachTLO();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "incorrect", 0, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> proficiencyafterpractestallskipuser40 = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencyDeclined(proficiencyafterpractestallskipuser40, proficienciesafterdiagtestuser40);
			
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exeption in testcase ProficiencyModrateWithIncorrectPracticeAndUsingHints in class ProficienciesModerateVariation",e);
		}
	}
	

	@Test(priority=5,enabled=true)
	public void ProficiencyModrateWithSkipAnswerPracticeAndUsingHints()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("41"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().thirtyOneToSixtyPercentCorrect();//attempt 31 tp 60 % correct answer of daig texst
			new Navigator().orionDashboard();
			List<Integer> proficienciesafterdiagtestuser41 = new Proficiency().getProficiencyOfEachTLO();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "skip", 0, true, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> proficiencyafterpractestallskipuser41 = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencySame(proficiencyafterpractestallskipuser41, proficienciesafterdiagtestuser41);
			
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exeption in testcase ProficiencyModrateWithIncorrectPracticeAndUsingHints in class ProficienciesModerateVariation",e);
		}
	}
	@AfterMethod
	public void TearDown()throws Exception
	{
		Driver.driver.quit();
	}
	
}
