package com.snapwiz.selenium.tests.staf.orion.testcases;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.AddNotesInQuestions;
import com.snapwiz.selenium.tests.staf.orion.apphelper.ClickOnquestionCard;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;
import com.snapwiz.selenium.tests.staf.orion.uihelper.PerformanceReport;

public class AddedNotesNotViewToAnotherStudent
{
	@Test(priority=1,enabled=true)
	public void addedNotesNotViewToAnotherStudent()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("714");//login as student
			new SelectChapterForTest().selectchapterfortest(0, 2);//Select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(3, 6, "correct", false, false, true);//attempt diagnostic test
			Thread.sleep(3000);
			new Navigator().NavigateToStudentReport();
			new PerformanceReport().performancereport();
			new ClickOnquestionCard().clickOnQuestion(0);//click on question  which is attempt last in daig
			new AddNotesInQuestions().addnotes("this is notes 1");//add note to above question
			new LoginUsingLTI().ltiLogin("7141");//login as another student
			new SelectChapterForTest().selectchapterfortest(0, 2);//Select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(3, 6, "correct", false, false, true);//attempt diagnostic test
			Thread.sleep(3000);
			new Navigator().NavigateToStudentReport();
			new PerformanceReport().performancereport();
			new ClickOnquestionCard().clickOnQuestion(0);//click on question  which is attempt last in daig
			boolean notes=Driver.driver.findElement(By.id("al-question-notes-count")).isDisplayed();//verify notes available or not
			if(notes==true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("notes avilable for another student also");
			}
			new LoginUsingLTI().ltiLogin("714");//login as 1st student
			new Navigator().NavigateToStudentReport();
			new PerformanceReport().performancereport();
			new ClickOnquestionCard().clickOnQuestion(0);//click on question  which is attempt last in daig
			boolean notes1=Driver.driver.findElement(By.id("al-question-notes-count")).isDisplayed();//verify notes avilable or not
			if(notes1==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("notes avilable for another student also");
			}	
			
			new LoginUsingLTI().ltiLogin("7141");//login as 2nd student 
			new Navigator().NavigateToStudentReport();
			new PerformanceReport().performancereport();
			new ClickOnquestionCard().clickOnQuestion(0);//click on question  which is attempt last in daig
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("al-question-notesicon-text")).click();//click on notes at footer
			Thread.sleep(2000);
			String defalttext=Driver.driver.findElement(By.className("al-notes-input-text-section")).getText();//fetch default text from note text area
			if(!defalttext.contains("Enter notes here and press"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("default text not shown");
			}
			Driver.driver.findElement(By.id("al-close-notes-wrapper")).click();//click on cross button on note text area
			Thread.sleep(2000);
			boolean notesarea=Driver.driver.findElement(By.className("al-display-notes-wrapper")).isDisplayed();//verify after click on cross note area close or not
			if(notesarea==true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("after click on cross button the notes text area its not close ");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase addedNotesNotViewToAnotherStudent in class AddedNotesNotViewToAnotherStudent",e);
			
		}
	}
	
	@Test(priority=2,enabled=true)
	public void Deletenotes()
	{
	
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("721");//login as student
			new SelectChapterForTest().selectchapterfortest(0, 2);//Select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(3, 4, "correct", false, false, true);//attempt diagonestic test
			new Navigator().orionDashboard();
			new PracticeTest().startTest();
			Driver.driver.findElement(By.cssSelector("img[title='Submit']")).click();//click on submit question
			int submitbutton=Driver.driver.findElements(By.cssSelector("img[title='Submit']")).size();
			if(submitbutton>=1)
				Driver.driver.findElement(By.cssSelector("img[title='Submit']")).click();
			Thread.sleep(2000);	
			String rantext=new RandomString().randomstring(3);
			new AddNotesInQuestions().addnotes(rantext);//add notes and verify
			Actions ac=new Actions(Driver.driver);
			WebElement we=Driver.driver.findElement(By.id("al-user-notes-icon-block"));
			ac.moveToElement(we).build().perform();
			Driver.driver.findElement(By.className("al-delete-user-content-notes")).click();
			Thread.sleep(2000);
			int notesblock=Driver.driver.findElements(By.id("al-user-notes-icon-block")).size();
			if(notesblock!=0)
			{
				Assert.fail("after deleting the note its not deleted");
			}			
			String rantext1=new RandomString().randomstring(3);
			new AddNotesInQuestions().addnotes(rantext);//add notes and verify
			new AddNotesInQuestions().addnotes(rantext1);//add notes and verify
			List<WebElement> allnotes=Driver.driver.findElements(By.id("al-user-notes-content"));
			String latestnotes=allnotes.get(0).getText();
			if(!latestnotes.contains(rantext1))
			{
				Assert.fail("latest notes not at top");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase Deletenotes in class AddedNotesNotViewToAnotherStudent",e);
			
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
