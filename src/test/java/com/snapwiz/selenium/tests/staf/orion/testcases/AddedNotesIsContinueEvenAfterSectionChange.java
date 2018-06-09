package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;
import com.snapwiz.selenium.tests.staf.orion.uihelper.PerformanceReport;

public class AddedNotesIsContinueEvenAfterSectionChange
{
	@Test
	public void addedNotesIsContinueEvenAfterSectionChange()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("429");//login as student
			new SelectChapterForTest().selectchapterfortest(0, 2);
			new DiagnosticTest().attemptAllCorrect(2, false, false);
			new Navigator().orionDashboard();
			Thread.sleep(2000);
			new PracticeTest().startTest();//Start practice test
			new PracticeTest().practiceTestAttempt(2, 10, "correct", false, false);
			new Navigator().NavigateToStudentReport();
			new PerformanceReport().performancereport();//click on performance report
			List<WebElement> questioncard=Driver.driver.findElements(By.cssSelector("div[class='report-sidebar-question-card-sectn question-card-green']"));//ftech question card
			questioncard.get(0).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("al-question-notesicon-text")).click();////click on add notes icon
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("al-notes-input-text-section")).click();//click on testbox
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("al-notes-input-text-section")).sendKeys("this is notes 1"+Keys.ENTER);//write text
			Thread.sleep(2000);
			boolean notes=	Driver.driver.findElement(By.id("al-user-notes-content")).isDisplayed();//check for notes added or not
			if(notes==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("added notes not shown");
			}
			new Navigator().NavigateToStudentAllActivity();
			
			List<WebElement> addedNotes=Driver.driver.findElements(By.className("my-journal-activity-event-action"));//fetch all activity in my activity page
			addedNotes.get(0).click();//click on added notes in my activity page
			Thread.sleep(2000);
			boolean notes1=	Driver.driver.findElement(By.id("al-user-notes-content")).isDisplayed();//check added note available or not
			if(notes1==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("added notes1 not shown");
			}
			new Navigator().NavigateToStudentAllActivity();
			Driver.driver.findElement(By.id("my-journal-tab-button")).click();//click on Journel tab
			Thread.sleep(2000);	
			List<WebElement> addedNotesjonral=Driver.driver.findElements(By.className("my-journal-activity-event-action"));//click on added notes
			addedNotesjonral.get(0).click();
			Thread.sleep(2000);
			boolean notes2=	Driver.driver.findElement(By.id("al-user-notes-content")).isDisplayed();
			if(notes2==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("added notes1 not shown");
			}
			//login as student of different class section
			new LoginUsingLTI().ltiLogin("4291");
			Thread.sleep(2000);
			new Navigator().NavigateToStudentReport();
			List<WebElement> questioncard1=Driver.driver.findElements(By.cssSelector("div[class='report-sidebar-question-card-sectn question-card-green']"));
			questioncard1.get(0).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("al-question-notesicon-text")).click();
			Thread.sleep(2000);
			boolean notessection=	Driver.driver.findElement(By.id("al-user-notes-content")).isDisplayed();
			if(notessection==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("added notes not shown for another section");
			}
			new Navigator().NavigateToStudentAllActivity();
			List<WebElement> addedNotes1=Driver.driver.findElements(By.className("my-journal-activity-event-action"));
			addedNotes1.get(0).click();
			Thread.sleep(2000);
			boolean notes1section=	Driver.driver.findElement(By.id("al-user-notes-content")).isDisplayed();
			if(notes1section==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("added notes1 not shown for another section");
			}
			new Navigator().NavigateToStudentAllActivity();
			Driver.driver.findElement(By.id("my-journal-tab-button")).click();
			Thread.sleep(2000);
			List<WebElement> addedNotesjonrel1=Driver.driver.findElements(By.className("my-journal-activity-event-action"));
			addedNotesjonrel1.get(0).click();
			Thread.sleep(2000);
			boolean notes2section=	Driver.driver.findElement(By.id("al-user-notes-content")).isDisplayed();
			if(notes2section==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("added notes1 not shown for another section");
			}								
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase addedNotesIsContinueEvenAfterSectionChange in class AddedNotesIsContinueEvenAfterSectionChange",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}

}
