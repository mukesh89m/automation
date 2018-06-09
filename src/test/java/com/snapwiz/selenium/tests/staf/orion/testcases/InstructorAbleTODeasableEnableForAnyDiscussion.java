package com.snapwiz.selenium.tests.staf.orion.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class InstructorAbleTODeasableEnableForAnyDiscussion
{
	@Test(priority = 1, enabled = true)
	public void instructorAbleTODeasableEnableForAnyDiscussion()
	{
		try
		{
			String anotherSection = ReadTestData.readDataByTagName("", "context_title", "685");
			Driver.startDriver();					
			new LoginUsingLTI().ltiLogin("685");//login as instructor with default section 
			new LoginUsingLTI().ltiLogin("686");//login as instructor with different section
			new Navigator().NavigateToInstructorSettings();
			Driver.driver.findElement(By.cssSelector("div[title='OFF']")).click();
			Thread.sleep(2000);
			boolean turnon=Driver.driver.findElement(By.cssSelector("div[title='ON']")).isDisplayed();
			if(turnon==false)
			{
				Assert.fail("turn on not shown ");
			}
			Driver.driver.findElement(By.cssSelector("div[title='ON']")).click();
			Thread.sleep(2000);
			boolean turnoff=Driver.driver.findElement(By.cssSelector("div[title='OFF']")).isDisplayed();
			if(turnoff==false)
			{
				Assert.fail("turn off not shown ");
			}
			Driver.driver.findElement(By.className("sbSelector")).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("a[title='"+anotherSection+"']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("div[title='OFF']")).click();
			Thread.sleep(2000);
			boolean turnoff1=Driver.driver.findElement(By.cssSelector("div[title='ON']")).isDisplayed();
			if(turnoff1==false)
			{
				Assert.fail("turn off not shown ");
			}
			Driver.driver.findElement(By.cssSelector("div[title='ON']")).click();
			Thread.sleep(2000);
			boolean turnon1=Driver.driver.findElement(By.cssSelector("div[title='OFF']")).isDisplayed();
			if(turnon1==false)
			{
				Assert.fail("turn on not shown ");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase instructorAbleTODeasableEnableForAnyDiscussion in class InstructorAbleTODeasableEnableForAnyDiscussion",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void disableDiscussions()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("688");//login as instructor with default class section
			new Navigator().NavigateToInstructorSettings();
            Driver.driver.findElement(By.cssSelector("div[title='OFF']")).click(); //turn off the discussions for the class
			new LoginUsingLTI().ltiLogin("689");//login as student with default class section 
			new DiagnosticTest().startTest(0, 4);
			new DiagnosticTest().attemptCorrect(4);
			new DiagnosticTest().quitTestAndGoToDashboard();
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			new PracticeTest().submitOnlyQuestion(); //Click on submit button for the question but don't click on Next button
			int discussionoption = Driver.driver.findElements(By.id("al-practice-question-discussion-icon-section")).size();
			if(discussionoption > 0)
				Assert.fail("The option to add discussion is visible even if the instructor has disabled it");
			
			new PracticeTest().quitTestAndGoToDashboard();
			new PracticeTest().startTLOLevelPracticeTest(0);
			new PracticeTest().submitOnlyQuestion(); //Click on submit button for the question but don't click on Next button
			discussionoption = Driver.driver.findElements(By.id("al-practice-question-discussion-icon-section")).size();
			if(discussionoption > 0)
				Assert.fail("The option to add discussion is visible even if the instructor has disabled it");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase disableDiscussions in class InstructorAbleTODeasableEnableForAnyDiscussion",e);
		}
	}
	
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}

}
