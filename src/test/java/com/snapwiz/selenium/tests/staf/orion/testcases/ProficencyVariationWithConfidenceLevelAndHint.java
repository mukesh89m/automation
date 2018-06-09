
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
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectAnswerAndSubmit;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;


public class ProficencyVariationWithConfidenceLevelAndHint 
{
	@Test(priority=1,enabled=true)
	public void proficencyVariationWithConfidenceLevelAndHintwithCorrectAnswer()
	{
		try
		{
			Driver.startDriver();			
			new LoginUsingLTI().ltiLogin("28"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().lessThanThirtyOnePercentCorrect();
			new Navigator().orionDashboard();
			List<Integer> proficiencyuser28diagtest = new Proficiency().getProficiencyOfEachTLO();
			//Attempting practice test as correct answers with confidence as 'I don't know'
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "correct", 1, true, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser28practestwithconfidence1 = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencyDeclined(Proficienciesuser28practestwithconfidence1, proficiencyuser28diagtest);
	 		
	 		//Attempting practice test as correct answers with confidence as 'Somewhat Confident'
	 		new LoginUsingLTI().ltiLogin("29"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().lessThanThirtyOnePercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "correct", 2, true, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser29practestwithconfidence2 = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencyImproved(Proficienciesuser29practestwithconfidence2, Proficienciesuser28practestwithconfidence1);
	 		
	 		//Attempting practice test as correct answers with confidence as 'Almost Confident'
	 		new LoginUsingLTI().ltiLogin("30"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().lessThanThirtyOnePercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "correct", 3, true, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser30practestwithconfidence3 = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencyImproved(Proficienciesuser30practestwithconfidence3, Proficienciesuser29practestwithconfidence2);
	 		
	 		//Attempting practice test as correct answers with confidence as 'I Know It'
	 		new LoginUsingLTI().ltiLogin("31"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().lessThanThirtyOnePercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "correct", 4, true, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser31practestwithconfidence4 = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencyImproved(Proficienciesuser31practestwithconfidence4, Proficienciesuser30practestwithconfidence3);
			
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method proficencyVariationWithConfidenceLevelAndHint",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void proficencyVariationWithConfidenceLevelAndHintwithInCorrectAnswer()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("32"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().lessThanThirtyOnePercentCorrect();
			new Navigator().orionDashboard();
			List<Integer> oldProficiencies = new Proficiency().getProficiencyOfEachTLO();//fetch proficiencies after daig test
			new PracticeTest().startTest();
			//new SelectAnswerAndSubmit().practiceTestAttempt(1, 20, "incorrect",true,false);
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "incorrect", 1, true, false); //attempt practice test with confidence level 1
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficiencieswithconfidence1 = new Proficiency().getProficiencyOfEachTLO();//fetch proficiencies after practice test by 1st user with 1 confidence level	 		
	 		new Proficiency().isProficiencyDeclined(Proficiencieswithconfidence1, oldProficiencies);	 		
	 		new LoginUsingLTI().ltiLogin("33"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().lessThanThirtyOnePercentCorrect(); //attempt daig test with less than 31 % proficiencies
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "incorrect", 2, true, false);//attempt practice test with confidence level 2
			new PracticeTest().quitTestAndGoToDashboard();	
			List<Integer> profafteruser2practice =  new Proficiency().getProficiencyOfEachTLO();			
	 		new Proficiency().isProficiencySame(profafteruser2practice,Proficiencieswithconfidence1);//Comparison between proficiencies with confidence 1 or confidence 2
	 		//login as student and select select confidence level 3
	 		new LoginUsingLTI().ltiLogin("34"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().lessThanThirtyOnePercentCorrect(); //attempt daig test with less than 31 % proficiencies
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "incorrect", 3, true, false);//attempt practice test with confidence level 3
			new PracticeTest().quitTestAndGoToDashboard();	
			List<Integer> profafteruser3practiceconf3 =  new Proficiency().getProficiencyOfEachTLO();	//fetch proficiencies after practice test with confidence level 3			 		
	 		new Proficiency().isProficiencySame(profafteruser3practiceconf3,Proficiencieswithconfidence1);//Comparison between proficiencies with confidence 1 or confidence 3
	 		//login as student and select 4th confidence level
	 		new LoginUsingLTI().ltiLogin("35"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().lessThanThirtyOnePercentCorrect(); //attempt daig test with less than 31 % proficiencies
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "incorrect", 4, true, false);//attempt practice test with confidence level 4
			new PracticeTest().quitTestAndGoToDashboard();	
			List<Integer> profafteruser4practiceconf4 =  new Proficiency().getProficiencyOfEachTLO();	//fetch proficiencies after practice test with confidence level 4			 		
	 		new Proficiency().isProficiencySame(profafteruser4practiceconf4,Proficiencieswithconfidence1);//Comparison between proficiencies with confidence 1 or confidence 4
	 	}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase proficiencyVariationWithConfidence in class ProficiencyVariationWithConfidence",e);
		}
		
	}
	
	@AfterMethod
	public void TearDown()
	{
		Driver.driver.quit();
	}
}
