package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R149;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class CourseLevelDiagonesticTestProficency extends Driver
{
	@Test(priority = 1,enabled = true)
	public void courseLevelDiagonesticTestProficency()
	{
		try 
		{
			new LoginUsingLTI().ltiLogin("22");
			new Navigator().NavigateTo("eTextBook");
			new DiagnosticTest().startTest(2);
			new DiagnosticTest().attemptAllCorrect(0, false, false);//attempt diagnostic test with no confidence level
			new Navigator().NavigateTo("Proficiency Report");//navigate to proficiency report
			Thread.sleep(2000);
			double proficency = new PerformanceProficencyPercent().performanceProficencyPercent("22");//fetch proficiency report of above user from database
            System.out.println("proficency in DB: "+proficency);
			String proficencytext = driver.findElement(By.id("al-cource-proficiency-summary")).getText();//fetch proficiency report of user from pro
			double pageproficency = Double.parseDouble(proficencytext.substring(0, proficencytext.length()-1));//ignore the % sign in substring
            System.out.println("pageproficency: "+pageproficency);
            int prof = new Double(proficency).compareTo(new Double(pageproficency));
            System.out.println("prof: "+prof);
            if(prof != 0)
            	Assert.fail("database proficiency not equal to displayed proficiency on proficiency report page for diagonstic test");
		}
		catch (Exception e)
		{
			Assert.fail("Exception in testcase CourseLevelDiagonesticTestProficency in testmethod courseLevelDiagonesticTestProficency", e);
		}
	}
	
	@Test(priority = 2,enabled = true,dependsOnMethods  =  {"courseLevelDiagonesticTestProficency"})
	public void courseLevelpracticeTestProficency()
	{
		try
		{
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "22");
			/*new Assignment().create(22);
			for(int i = 1;i<= 15;i++)
			{
				new Assignment().addQuestions(22, "qtn-type-true-false-img", assignmentname);//create static test
			}
			for(int i = 1;i<= 10;i++)
			{
				new Assignment().addQuestions(221, "qtn-type-true-false-img", assignmentname);
			}*/
			new LoginUsingLTI().ltiLogin("22");
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().chapterOpen(0);
		/*	new DiagnosticTest().startTest(2);
			new DiagnosticTest().attemptAllCorrect(2,false,false);*/
			new PracticeTest().startTest();
			for(int i = 1;i<= 14;i++)
			{
				new PracticeTest().AttemptCorrectAnswer(0,"22");
			}
			new Click().clickBycssselector("div[class = 'al-quit-diag-test-wrapper ls-practice-test-quit']");
			new Click().clickByclassname("ls-practice-test-view-report");
			new Navigator().NavigateTo("Proficiency Report");//navigate to proficency report
			Thread.sleep(3000);
			int proficency = new PerformanceProficencyPercent().performanceProficencyPercent("22");//fetch proficency report of above user from database
            System.out.println("DB "+proficency);
			String proficencytext = driver.findElement(By.id("al-cource-proficiency-summary")).getText();//fetch proficency report of user from pro
			int pageproficency = Integer.parseInt(proficencytext.substring(0, proficencytext.length()-1));//ignore the % sign in substring
            System.out.println("Page "+pageproficency);
            if(proficency != pageproficency)
            	Assert.fail("database proficency not equal to diplayed proficency on proficency report page for diagonstic test");
		}
		catch (Exception e) 
		{
			Assert.fail("Exception in testcase CourseLevelDiagonesticTestProficency in testmethod courseLevelpracticeTestProficency",e);
		}
	}
}
