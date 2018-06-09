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

public class ProficienciesModrateVariationWithConfidence 
{
	@Test(priority=1,enabled=true)
	public void ProficienciesModrateVariationWithConfidenceCorrectAnswer()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("42"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().thirtyOneToSixtyPercentCorrect();
			new Navigator().orionDashboard();
			List<Integer> proficiencyuser42diagtest = new Proficiency().getProficiencyOfEachTLO();
			//Attempting practice test as correct answers with confidence as 'I don't know'
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "correct", 1, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser42practestwithconfidence1 = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencySame(Proficienciesuser42practestwithconfidence1, proficiencyuser42diagtest);	 		
	 		//Attempting practice test as correct answers with confidence as 'Somewhat Confident'
	 		new LoginUsingLTI().ltiLogin("43"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().thirtyOneToSixtyPercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "correct", 2, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser43practestwithconfidence2 = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencyImproved(Proficienciesuser43practestwithconfidence2, Proficienciesuser42practestwithconfidence1);	 		
	 		//Attempting practice test as correct answers with confidence as 'Almost Confident'
	 		new LoginUsingLTI().ltiLogin("44"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().thirtyOneToSixtyPercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "correct", 3, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser44practestwithconfidence3 = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencyImproved(Proficienciesuser44practestwithconfidence3, Proficienciesuser43practestwithconfidence2);	 		
	 		///Attempting practice test as correct answers with confidence as 'I Know It'
	 		new LoginUsingLTI().ltiLogin("45"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().thirtyOneToSixtyPercentCorrect();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "correct", 4, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficienciesuser45practestwithconfidence4 = new Proficiency().getProficiencyOfEachTLO();
	 		new Proficiency().isProficiencyImproved(Proficienciesuser45practestwithconfidence4, Proficienciesuser44practestwithconfidence3);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase method ProficienciesModrateVariationWithConfidenceCorrectAnswer in class ProficienciesModrateVariationWithConfidence");
		}
	}
	@Test(priority=2,enabled=true)
	public void ProficienciesModrateVariationWithConfidenceInCorrectAnswer()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("46"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().thirtyOneToSixtyPercentCorrect();
			new Navigator().orionDashboard();
			List<Integer> oldProficiencies = new Proficiency().getProficiencyOfEachTLO();//fetch proficiencies after daig test
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "incorrect", 1, false, false);
			new PracticeTest().quitTestAndGoToDashboard();		
	 		List<Integer> Proficiencieswithconfidence1 = new Proficiency().getProficiencyOfEachTLO();//fetch proficiencies after practice test by 1st user with 1 confidence level	 		
	 		new Proficiency().isProficiencyDeclined(Proficiencieswithconfidence1, oldProficiencies);	 		
	 		new LoginUsingLTI().ltiLogin("47"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().thirtyOneToSixtyPercentCorrect(); //attempt daig test with more than 31 %  less than 60 %proficiencies
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "incorrect", 2, false, false);//attempt practice test with confidence level 2
			new PracticeTest().quitTestAndGoToDashboard();	
			List<Integer> profafteruser2practice =  new Proficiency().getProficiencyOfEachTLO();			
	 		new Proficiency().isProficiencySame(profafteruser2practice,Proficiencieswithconfidence1);//Comparison between proficiencies with confidence 1 or confidence 2
	 		//login as student and select select confidence level 3
	 		new LoginUsingLTI().ltiLogin("48"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().thirtyOneToSixtyPercentCorrect();//attempt daig test with more than 31 %  less than 60 %proficiencies
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "incorrect", 3, false, false);//attempt practice test with confidence level 3
			new PracticeTest().quitTestAndGoToDashboard();	
			List<Integer> profafteruser3practiceconf3 =  new Proficiency().getProficiencyOfEachTLO();	//fetch proficiencies after practice test with confidence level 3			 		
	 		new Proficiency().isProficiencySame(profafteruser3practiceconf3,Proficiencieswithconfidence1);//Comparison between proficiencies with confidence 1 or confidence 3
	 		//login as student and select 4th confidence level
	 		new LoginUsingLTI().ltiLogin("49"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diagnostic test for 1st chapter
			new DiagnosticTest().thirtyOneToSixtyPercentCorrect();//attempt daig test with more than 31 %  less than 60 %proficiencies
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
            new PracticeTest().attemptPracticeQuesFromEachTLO(5, "incorrect", 4, false, false);//attempt practice test with confidence level 4
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
