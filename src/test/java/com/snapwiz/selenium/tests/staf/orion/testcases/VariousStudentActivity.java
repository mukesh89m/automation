package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.AddNotesInQuestions;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class VariousStudentActivity {
	@Test(priority  = 1, enabled = true)
	public void markedQuestionForReview()
	{
		try
		{
			Driver.startDriver();	
			new LoginUsingLTI().ltiLogin("1719"); //  Logging in as student to orion
			new DiagnosticTest().startTest(0,4);//select 1st chapter for test
			//mark 1st question for review
			Driver.driver.findElement(By.cssSelector("label[id='al-diagtest-markForReview']")).click();
			Thread.sleep(2000);
			new DiagnosticTest().DiagonesticTestQuitBetween(4, 2, "correct", false, false, true);
			new Navigator().NavigateToStudentAllActivity();
			List<WebElement> allHeaders = Driver.driver.findElements(By.className("my-journal-activity-event-action"));
			if(!allHeaders.get(1).getText().equals("Marked a Question for Review"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After marking a question for review in all activity there is no header saying 'Marked a Question for Review'.");
			}
			
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("span[class='question-display-label']")));
			Thread.sleep(2000);
			String question = Driver.driver.findElement(By.className("al-diag-test-question-raw-content")).getText();
			if(question == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking the question which has been marked for review it doesn't go to that question.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase variousStudentActivity in class VariousStudentActivity ",e);
		}
	}
	@Test(priority  = 2, enabled = true)
	public void studentUpdatesANote()
	{
		try
		{
			Driver.startDriver();	
			new LoginUsingLTI().ltiLogin("1721"); //  Logging in as student to orion
			new DiagnosticTest().startTest(1,3);//select 1st chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(4, 5, "correct", false, false, true);
			new Navigator().orionDashboard();
			//take a practice test
			new PracticeTest().startTest();
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
			Thread.sleep(2000);
			new AddNotesInQuestions().addnotes("This is a note.");
			//update the note
			WebElement we = Driver.driver.findElement(By.id("al-user-notes-content"));
			//mouse hover
			Locatable hoverItem = (Locatable) we;
			Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
			mouse.mouseMove(hoverItem.getCoordinates());
			//double click
			Actions ac=new Actions(Driver.driver);
			ac.doubleClick(we).build().perform();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("textarea[class='al-update-user-content-notes']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("textarea[class='al-update-user-content-notes']")).clear();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("textarea[class='al-update-user-content-notes']")).sendKeys("Updated note text.");
			ac.sendKeys(Keys.RETURN).build().perform();
			Thread.sleep(1000);
			new Navigator().NavigateToStudentAllActivity();
			List<WebElement> allHeaders = Driver.driver.findElements(By.className("my-journal-activity-event-action"));
			if(!allHeaders.get(0).getText().equals("Updated a Note"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After updating a note, in all activity there is no header saying 'Updated a Note'.");
			}
			
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase studentUpdatesANote in class VariousStudentActivity ",e);
		}
	}
	
	@Test(priority  = 3, enabled = true)
	public void studentDeletesANote()
	{
		try
		{
			Driver.startDriver();	
			new LoginUsingLTI().ltiLogin("1722"); //  Logging in as student to orion
			new DiagnosticTest().startTest(1,3);//select 1st chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(4, 5, "correct", false, false, true);
			new Navigator().orionDashboard();
			//take a practice test
			new PracticeTest().startTest();
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
			Thread.sleep(2000);
			new AddNotesInQuestions().addnotes("This is a note.");
			WebElement we = Driver.driver.findElement(By.id("al-user-notes-icon-block"));
			//mouse hover
			Locatable hoverItem = (Locatable) we;
			Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
			mouse.mouseMove(hoverItem.getCoordinates());
			Driver.driver.findElement(By.cssSelector("span[class='al-delete-user-content-notes']")).click();
			Thread.sleep(2000);
			
			new Navigator().NavigateToStudentAllActivity();
			List<WebElement> allHeaders = Driver.driver.findElements(By.className("my-journal-activity-event-action"));
			if(!allHeaders.get(0).getText().equals("Deleted a Note"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After updating a note, in all activity there is no header saying 'Updated a Note'.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase studentDeletesANote in class VariousStudentActivity ",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}

}
