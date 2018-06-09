package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Proficiency;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class ChapterECF {
	
	@Test(priority = 1, enabled = true)
	public void chapterECFLessQuestions()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("621");
			new DiagnosticTest().startTest(0, 3);
			for(int i=0;i<7;i++)
			new DiagnosticTest().attemptCorrect(0);
			new DiagnosticTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentReport();
			Driver.driver.findElement(By.linkText("View By Chapters")).click();
			if(!Driver.driver.findElement(By.className("al-proficiency-percentage")).getText().trim().equals("") )
				Assert.fail("The chapter proficiency is shown in most challenging report");
			new PracticeTest().startTest();
			for(int i=0;i<5;i++)
			new PracticeTest().attemptQuestion("correct", 2, false, false);
			new PracticeTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentReport();
			Driver.driver.findElement(By.linkText("View By Chapters")).click();
			if(!Driver.driver.findElement(By.className("al-proficiency-percentage")).getText().trim().equals("") )
				Assert.fail("The chapter proficiency is shown in most challenging report");
			//Taking other chapter diagnostic test
			new DiagnosticTest().startTest(1, 3);
			for(int i=0;i<7;i++)
			new DiagnosticTest().attemptCorrect(0);
			new DiagnosticTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentReport();
			Driver.driver.findElement(By.linkText("View By Chapters")).click();
			List<WebElement> chapterlevelproficiency = Driver.driver.findElements(By.className("al-proficiency-percentage"));
			if(!chapterlevelproficiency.get(0).getText().trim().equals(""))
				Assert.fail("The chapter proficiency for first chapter is shown in most challenging report");
			if(!chapterlevelproficiency.get(1).getText().trim().equals(""))
				Assert.fail("The chapter proficiency for third chapter is shown in most challenging report");
			
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in tescase chapterECFLessQuestions in class ChapterECF",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void chapterECFMoreThan10Questions()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("626");
			new DiagnosticTest().startTest(0, 3);
			for(int i=0;i<10;i++)
			new DiagnosticTest().attemptCorrect(0);
			new DiagnosticTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentReport();
			Driver.driver.findElement(By.linkText("View By Chapters")).click();
			String proficiency_after_diag = Driver.driver.findElement(By.className("al-proficiency-percentage")).getText();
			if(proficiency_after_diag.trim().equals("") )
				Assert.fail("The chapter proficiency is shown in most challenging report");
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			for(int i=0;i<10;i++)
				new PracticeTest().AttemptCorrectAnswer(2);
			new PracticeTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentReport();
			Driver.driver.findElement(By.linkText("View By Chapters")).click();
			String proficiency_after_prac = Driver.driver.findElement(By.className("al-proficiency-percentage")).getText();
			if(proficiency_after_diag.trim().equals(proficiency_after_prac))
				Assert.fail("The chapter proficiency after attempting diagnostic test is equal to chapter proficiency after Practice Test in most challenging report");
		}
		
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in tescase chapterECFMoreThan10Questions in class ChapterECF",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void TloECF()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("633");
			new DiagnosticTest().startTest(0, 3);
			new DiagnosticTest().attemptCorrect(0);
			new DiagnosticTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentReport();
			if(!Driver.driver.findElement(By.className("al-proficiency-percentage")).getText().trim().equals(""))
				Assert.fail("The TLO proficiency is shown in most challenging report");
			new Navigator().orionDashboard();
			new PracticeTest().startTLOLevelPracticeTest(2);
			new PracticeTest().AttemptCorrectAnswer(0);
			new PracticeTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentReport();
			List<WebElement> tlolevelproficiency = Driver.driver.findElements(By.className("al-proficiency-percentage"));
			if(!tlolevelproficiency.get(1).getText().trim().equals(""))
				Assert.fail("The proficiency for TLO from Diagnostic Test is shown in most challenging report");
			if(!tlolevelproficiency.get(2).getText().trim().equals(""))
				Assert.fail("The proficiency for TLO from Practice Test is shown in most challenging report");
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in tescase TloECF in class ChapterECF",e);
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void tloECFGreaterValue()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("638");
			new DiagnosticTest().startTest(0, 3);
			new DiagnosticTest().attemptFromParticularTLO(2, 3, "correct", 1);
			new DiagnosticTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentReport();
			List<Integer> profs_after_two_diag_test = new Proficiency().getProficiencyOfEachTLO();
			List<WebElement> tlolevelproficiency = Driver.driver.findElements(By.className("al-proficiency-percentage"));
			if(tlolevelproficiency.get(1).getText().trim().equals(""))
				Assert.fail("The proficiency for TLO from Diagnostic Test is shown in most challenging report");
			new Navigator().orionDashboard();
			new PracticeTest().startTLOLevelPracticeTest(3);
			for(int i=0;i<2;i++)
			new PracticeTest().attemptQuestion("correct", 2, false, false);
			new PracticeTest().quitTestAndGoToDashboard();
			new Navigator().NavigateToStudentReport();
			List<Integer> profs_after_two_attempt = new Proficiency().getProficiencyOfEachTLO();
			if(profs_after_two_diag_test.get(1) > profs_after_two_attempt.get(1)) Assert.fail("");
				
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in tescase tloECFGreaterValue in class ChapterECF",e);
		}
	}
	
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}

}
