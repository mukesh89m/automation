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

public class ProficiencyVariationWithHints {

	@Test(priority = 1, enabled = true) //Akansh 06-01-2013
	public void proficiencyImprovedWithCorrectAnswerInPracticeTest()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("39_1"); //  Logging in as student 1 to orion to attempt test without hints
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().thirtyOneToSixtyPercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "correct", 0, false, false);//Attempt without using hint
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> proficiencyafterpractestallcorrectwithouthintuser39_1 = new Proficiency().getProficiencyOfEachTLO();
	 		
	 		new LoginUsingLTI().ltiLogin("39_2"); //  Logging in as student 2 to orion
	 		new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().thirtyOneToSixtyPercentCorrect();
			new Navigator().orionDashboard();
			List<Integer> proficiencyafterdiagtestuser39_2 = new Proficiency().getProficiencyOfEachTLO();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "correct", 0, true, false);//Attempt using hint
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> proficiencyafterpractestallcorrectwithhintuser39_2 = new Proficiency().getProficiencyOfEachTLO();
	 		//Validating if proficiency is improved after diag test or not
	 		new Proficiency().isProficiencyImproved(proficiencyafterpractestallcorrectwithhintuser39_2, proficiencyafterdiagtestuser39_2);
	 		//validating if proficiency of chapter after using hint while attempting practice test is less than what we get without using hint. 
	 		new Proficiency().isProficiencyDeclined(proficiencyafterpractestallcorrectwithhintuser39_2,proficiencyafterpractestallcorrectwithouthintuser39_1);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase proficiencyVariationWithPracticeTest in class ProficiencyVariationWithHints",e);
		}
	}
	
	@AfterMethod
	public void TearDown()throws Exception
	{
		Driver.driver.quit();
	}
}
