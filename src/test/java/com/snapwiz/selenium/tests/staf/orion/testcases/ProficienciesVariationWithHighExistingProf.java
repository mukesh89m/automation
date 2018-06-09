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

public class ProficienciesVariationWithHighExistingProf {
	
	@Test(priority = 1, enabled = true) //Akansh 06-01-2013
	public void proficiencyImprovedWithCorrectAnswerInPracticeTest()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("58"); //  Logging in as student 1 to orion to attempt test
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();  //attempt more than 60 % daig test correctly
			new Navigator().orionDashboard();
			List<Integer> proficiencyafterdiagtest = new Proficiency().getProficiencyOfEachTLO();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "correct", 0, false, false); //Attempt correct without using hint
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> proficiencyafterpractest = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencyImproved(proficiencyafterpractest, proficiencyafterdiagtest);	 		
	 	
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exeption in testcase proficiencyVariationWithPracticeTest in class ProficienciesVariationWithHighExistingProf",e);
		}
	}
	
	@Test(priority = 2, enabled = true) //Brajesh 08-01-2013
	public void proficiencyDeclinedWithInCorrectAnswerInPracticeTest()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("59"); //  Logging in as student 1 to orion to attempt test
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();   //attempt more than 60 % daig test correctly
			new Navigator().orionDashboard();
			List<Integer> proficiencyafterdiagtest = new Proficiency().getProficiencyOfEachTLO();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "incorrect", 0, false, false); //Attempt incorrect without using hint
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> proficiencyafterpractest = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencyDeclined(proficiencyafterpractest, proficiencyafterdiagtest);	 		
	 	
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exeption in testcase proficiencyImprovedWithInCorrectAnswerInPracticeTest in class ProficienciesVariationWithHighExistingProf",e);
		}
	}
	
	@Test(priority = 3, enabled = true) //Brajesh 08-01-2013
	public void proficiencySameWithSkipAnswerInPracticeTest()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("60"); //  Logging in as student 1 to orion to attempt test
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();    //attempt more than 60 % daig test correctly
			new Navigator().orionDashboard();
			List<Integer> proficiencyafterdiagtest = new Proficiency().getProficiencyOfEachTLO();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "skip", 0, false, false);  //Attempt  without answer without using hint
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> proficiencyafterpractest = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencySame(proficiencyafterpractest, proficiencyafterdiagtest);	 		
	 	
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exeption in testcase proficiencyImprovedWithSkipAnswerInPracticeTest in class ProficienciesVariationWithHighExistingProf",e);
		}
	}
	
	@AfterMethod
	public void TearDown()throws Exception
	{
		Driver.driver.quit();
	}

}
