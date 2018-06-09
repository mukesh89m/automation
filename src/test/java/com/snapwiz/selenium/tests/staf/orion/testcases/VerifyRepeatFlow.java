package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectAnswerAndSubmit;

public class VerifyRepeatFlow {
	@Test(priority=1 , enabled= true)
	public void repeatFlow()
	{
		try
		{
			Driver.startDriver();	
			String practiceassessmentname= ReadTestData.readDataByTagName("VerifyRepeatFlow", "practiceassessmentname", "220");
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			String questiontext= ReadTestData.readDataByTagName("VerifyRepeatFlow", "questiontext", "220");
			//create a chapter
			new Assignment().createChapter(220,Integer.parseInt(tloids.get(0)),Integer.parseInt(tloids.get(1)));
			//create diagnostic test
			new Assignment().createPracticeAtChapterLevel(220, "Adaptive Component Diagnostic", true, false, true);
			//create adaptive test
			new Assignment().createPracticeAtChapterLevel(220, "Adaptive Component Practice", true, false, true);
			new Assignment().addMultipleQuestions(220, "qtn-multiple-choice-img", practiceassessmentname, 9, false, true, true,Integer.parseInt(tloids.get(0)));
			
			new LoginUsingLTI().ltiLogin("220");
			new DiagnosticTest().openLastDiagnosticTest();
			
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);	
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			String [] arr = new SelectAnswerAndSubmit().fetchQuestionOfPracticeTestAttempted(2, 5, "incorrect", false, false);
			String [] arr1 = new SelectAnswerAndSubmit().fetchQuestionOfPracticeTestAttempted(2, 3, "skip", false, false);
			String [] arr2 = new SelectAnswerAndSubmit().fetchQuestionOfPracticeTestAttempted(2, 5, "correct", false, false);
			for(int i =0; i<arr.length; i++)
			{
				System.out.println("InCorrect: "+arr[i]);
			}
			for(int i =0; i<arr2.length; i++)
			{
				System.out.println("Correct: "+arr2[i]);
			}
			String question = Driver.driver.findElement(By.className("al-diag-test-question-raw-content")).getText();
			System.out.println("question: "+question);
			boolean found = false;
			for(int i =0; i<arr.length; i++)
			{
				if(arr[i].equals(question))
				{
					found = true;
					break;
				}
					
			}
			System.out.println("bool "+found);
			if(found == false)
				Assert.fail("When all the questions have exhausted and in repeat flow is not picking up the skipped questions first.");
			
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in verifyRepeatFlow in class VerifyRepeatFlow",e);
		}
	}
@Test(priority=2 , enabled= false)
public void repeatFlowForFiveQuestionSkipped()
{
	try
	{
		Driver.startDriver();	
		String practiceassessmentname= ReadTestData.readDataByTagName("VerifyRepeatFlow", "practiceassessmentname", "221");
		List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids
		String questiontext= ReadTestData.readDataByTagName("VerifyRepeatFlow", "questiontext", "221");
		//create a chapter
		new Assignment().createChapter(220, Integer.parseInt(tloids.get(0)), Integer.parseInt(tloids.get(1)), Integer.parseInt(tloids.get(2)));
		//create diagnostic test
		new Assignment().createPracticeAtChapterLevel(221, "Adaptive Component Diagnostic", true, false, true);
		//create adaptive test
		new Assignment().createPracticeAtChapterLevel(221, "Adaptive Component Practice", true, false, true);
		new Assignment().addMultipleQuestions(221, "qtn-multiple-choice-img", practiceassessmentname, 9, false, false, true,Integer.parseInt(tloids.get(0)));
		new Assignment().addMultipleQuestions(221, "qtn-multiple-choice-img", practiceassessmentname, 10, false, false, true,Integer.parseInt(tloids.get(1)));
		new Assignment().addMultipleQuestions(221, "qtn-multiple-choice-img", practiceassessmentname, 10, false, false, true,Integer.parseInt(tloids.get(2)));
		
		/*new LoginUsingLTI().ltiLogin("221");
		new DiagnosticTest().openLastDiagnosticTest();
		
		new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);	
		new Navigator().orionDashboard();
		new PracticeTest().startTest();*/
	}
	catch(Exception e)
	{
		new Screenshot().captureScreenshotFromTestCase();
		Assert.fail("Exception in repeatFlowForFiveQuestionSkipped in class VerifyRepeatFlow",e);
	}
}

@AfterMethod
public void tearDown() throws Exception
{
	Driver.driver.quit();
}
}
