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

public class ProficienciesVariationWithHighExistingProfUsingHints
{
	@Test(priority = 1, enabled = true) //Brajesh 08-01-2013
	public void proficienciesVariationWithHighExistingProfUsingHints()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("61"); //  Logging in as student 1 to orion to attempt test
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();  //attempt more than 60 % daig test correctly
			new Navigator().orionDashboard();
			List<Integer> proficiencyafterdiagtest = new Proficiency().getProficiencyOfEachTLO();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "correct", 0, true, false); //Attempt correct using hint
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> proficiencyafterpractestuser61 = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencyImproved(proficiencyafterpractestuser61, proficiencyafterdiagtest);
	 		//attempt practice test answer incorrectly
	 		new LoginUsingLTI().ltiLogin("62"); //  Logging in as student 1 to orion to attempt test
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();  //attempt more than 60 % daig test correctly
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "incorrect", 0, true, false); //Attempt incorrect with hint
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> proficiencyafterpractestuser62 = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencyDeclined(proficiencyafterpractestuser62, proficiencyafterpractestuser61);
	 		//attempt practice test and  skip  answer 
	 		new LoginUsingLTI().ltiLogin("63"); //  Logging in as student 1 to orion to attempt test
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().moreThanSixtyPercentCorrect();  //attempt more than 60 % daig test correctly
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "skip", 0, true, false); //skip with hint
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> proficiencyafterpractestuser63 = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencyDeclined(proficiencyafterpractestuser63, proficiencyafterpractestuser62);	 		
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exeption in testcase proficiencyVariationWithPracticeTest in class ProficienciesVariationWithHighExistingProf",e);
		}
	}
	
	
	@AfterMethod
	public void TearDown()throws Exception
	{
		Driver.driver.quit();
	}

}
