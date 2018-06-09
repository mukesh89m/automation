package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Proficiency;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Notification;

public class DiagnosticTestMultipleChoice {
	
	@Test(priority=1, enabled = true)
	public void diagnosticTestMultipleSelect()
	{
		try
		{
			String diagtestname = ReadTestData.readDataByTagName("DiagnosticTestMultipleChoice", "diagassessmentname", "84");
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			Driver.startDriver();
			new Assignment().createChapter(84, Integer.parseInt(tloids.get(0)),Integer.parseInt(tloids.get(1)),Integer.parseInt(tloids.get(2)),Integer.parseInt(tloids.get(3)),Integer.parseInt(tloids.get(4)),Integer.parseInt(tloids.get(5)));
			//create diagnostic test
			new Assignment().createPracticeAtChapterLevel(84, "Adaptive Component Diagnostic", false, true, true,Integer.parseInt(tloids.get(0)));
			new Assignment().addMultipleQuestions(84, "qtn-multiple-selection-img", diagtestname, 10, false, true, true, Integer.parseInt(tloids.get(0)));
			new LoginUsingLTI().ltiLogin("84");
			new DiagnosticTest().openLastDiagnosticTest();
			for(int i=1;i<=11;i++)
			new DiagnosticTest().attemptPartialCorrect(0, false);
			new Navigator().orionDashboard();
			List<Integer> prof_noconfidence = new Proficiency().getProficiencyOfEachTLO();
			System.out.println(prof_noconfidence);
			new LoginUsingLTI().ltiLogin("86");
			new DiagnosticTest().openLastDiagnosticTest();
			for(int i=1;i<=11;i++)
			new DiagnosticTest().attemptPartialCorrect(3, false);
			new Navigator().orionDashboard();
			List<Integer> prof_withconfidence = new Proficiency().getProficiencyOfEachTLO();
			System.out.println(prof_withconfidence);
			new Proficiency().isProficiencyDeclined(prof_withconfidence, prof_noconfidence);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase diagnosticTestMultipleSelect in class DiagnosticTestMultipleChoice",e);
		}
	}
	@Test(priority=2, enabled = true)
	public void verifyProficiencyForDiagnosticTest()
	{
		try
		{
			Driver.startDriver();
			//Attempt All questions incorrectly
			new LoginUsingLTI().ltiLogin("92");
			new DiagnosticTest().openLastDiagnosticTest();
			new DiagnosticTest().attemptAllIncorrect(0, false, false);
			new Navigator().orionDashboard();
			List<Integer> prof_allincorrect = new Proficiency().getProficiencyOfEachTLO();
			System.out.println(prof_allincorrect);
			//Attempt Few Questions correctly	
			new LoginUsingLTI().ltiLogin("93");
			new DiagnosticTest().openLastDiagnosticTest();
			new DiagnosticTest().attemptRandomly(0);
			new Navigator().orionDashboard();
			List<Integer> prof_fewcorrect = new Proficiency().getProficiencyOfEachTLO();
			System.out.println(prof_fewcorrect);
			new Proficiency().isProficiencyImproved(prof_fewcorrect, prof_allincorrect);
			//Attempt All Questions Correctly
			new LoginUsingLTI().ltiLogin("94");
			new DiagnosticTest().openLastDiagnosticTest();
			new DiagnosticTest().attemptAllCorrect(0, false, false);
			new Navigator().orionDashboard();
			List<Integer> prof_allcorrect = new Proficiency().getProficiencyOfEachTLO();
			System.out.println(prof_allcorrect);
			new Proficiency().isProficiencyImproved(prof_allcorrect, prof_fewcorrect);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyProficiencyForDiagnosticTest in class DiagnosticTestMultipleChoice",e);
		}
	}

	@Test(priority = 3, enabled = true)
	public void answeringWrongInPracticeTest()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("99");
			new DiagnosticTest().startTest(0, 2);			
			new DiagnosticTest().attemptRandomly(0);
			new Navigator().orionDashboard();
			List<Integer> prof_diagtestallincorrect = new Proficiency().getProficiencyOfEachTLO();
			System.out.println(prof_diagtestallincorrect);
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(1, "incorrect", 0, false, false);
			new Navigator().orionDashboard();
			List<Integer> prof_practicetestallincorrect = new Proficiency().getProficiencyOfEachTLO();
			System.out.println(prof_practicetestallincorrect);
			new Proficiency().isProficiencyDeclined(prof_practicetestallincorrect, prof_diagtestallincorrect);
			new PracticeTest().startTest();
			new PracticeTest().attemptPracticeQuesFromEachTLO(1, "correct", 0, false, false);
			new Navigator().orionDashboard();
			List<Integer> prof_practicetestallcorrect = new Proficiency().getProficiencyOfEachTLO();
			new Proficiency().isProficiencyImproved(prof_practicetestallcorrect, prof_practicetestallincorrect);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase answeringWrongInPracticeTest in class DiagnosticTestMultipleChoice",e);
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void notificationForNoQuestionsAssociated()
	{
		try
		{
			Driver.startDriver();
			List<String> tlonames = new PracticeTest().tloNames();
			new LoginUsingLTI().ltiLogin("253");
			new DiagnosticTest().openLastDiagnosticTest();
			new DiagnosticTest().attemptCorrect(0);
			new DiagnosticTest().quitTestAndGoToDashboard();
			new Navigator().orionDashboard();
			Thread.sleep(3000);
			List<String> objective = new ArrayList<String>();
			List<WebElement> objectives = Driver.driver.findElements(By.className("al-terminal-objective-title"));
			for(WebElement obj : objectives) objective.add(obj.getText());
			if(!objective.contains(tlonames.get(0))) Assert.fail("First TLO name not found");
			if(!objective.contains(tlonames.get(1))) Assert.fail("Second TLO name not found");
			if(!objective.contains(tlonames.get(2))) Assert.fail("Third TLO name not found");
			if(!objective.contains(tlonames.get(3))) Assert.fail("Fourth TLO name not found");
			if(!objective.contains(tlonames.get(4))) Assert.fail("Fifth TLO name not found");
			if(!objective.contains(tlonames.get(5))) Assert.fail("Sixth TLO name not found");
			//System.out.println(Driver.driver.findElement(By.className("al-terminal-objective-title")).getText());
			new PracticeTest().startTest();
			String notification = new Notification().getNotificationMessage();
			if(!notification.contains("No questions found for"))
				Assert.fail("Notification Message not coming as expected in case the chapter level practice test does not have any questions");
		
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase notificationForNoQuestionsAssociated in class DiagnosticTestMultipleChoice",e);
		}
	}
	
	@AfterMethod
	public void TearDown()throws Exception
	{
		new Screenshot().captureScreenshotFromTestCase();
		Driver.driver.quit();
	}
}
