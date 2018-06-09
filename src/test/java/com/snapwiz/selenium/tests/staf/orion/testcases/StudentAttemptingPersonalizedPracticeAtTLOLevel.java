package com.snapwiz.selenium.tests.staf.orion.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectAnswerAndSubmit;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class StudentAttemptingPersonalizedPracticeAtTLOLevel {
	@Test
	public void studentAttemptingPersonalizedPracticeAtTLOLevel()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("6"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diag test for 1st chapter
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);
			new Navigator().orionDashboard();
			Thread.sleep(3000);
			new PracticeTest().startTLOLevelPracticeTest(0);
			for(int i =0; i< 5; i++)
				new PracticeTest().AttemptCorrectAnswer(4);
			new LoginUsingLTI().ltiLogin("6"); //  Again Logging in as same student to orion
			Thread.sleep(3000);
			WebElement menuitem1 = Driver.driver.findElement(By.className("al-preformance-text")); 
			Locatable hoverItem1 = (Locatable) menuitem1;
			Mouse mouse1 = ((HasInputDevices) Driver.driver).getMouse();
			mouse1.mouseMove(hoverItem1.getCoordinates());
			Driver.driver.findElement(By.cssSelector("div[title='Practice']")).click();		//click on 1st TLO of 1st chapter
			Thread.sleep(3000);
			String question = Driver.driver.findElement(By.className("al-diag-test-question-raw-content")).getText();
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		    Thread.sleep(2000);
		    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
		    Thread.sleep(2000);
		    String question1 = Driver.driver.findElement(By.className("al-diag-test-question-raw-content")).getText();
			if(question.equals(question1))
				Assert.fail("A set of questions one after the other are not displayed in a series of screens.");
			
			new LoginUsingLTI().ltiLogin("6"); //  Again Logging in as same student to orion
			Thread.sleep(3000);
			WebElement menuitem2 = Driver.driver.findElement(By.className("al-preformance-text")); 
			Locatable hoverItem2 = (Locatable) menuitem2;
			Mouse mouse2 = ((HasInputDevices) Driver.driver).getMouse();
			mouse2.mouseMove(hoverItem2.getCoordinates());
			Driver.driver.findElement(By.cssSelector("div[title='Practice']")).click();		//click on 1st TLO of 1st chapter
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("div.al-quit-diag-test-icon")).click();	//click on quit icon
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("span.al-view-practice-test-report")).click();	//click to view report
			Thread.sleep(3000);
			
			new LoginUsingLTI().ltiLogin("6"); //  Again Logging in as same student to orion
			Thread.sleep(3000);
			WebElement menuitem3 = Driver.driver.findElement(By.className("al-preformance-text")); 
			Locatable hoverItem3 = (Locatable) menuitem3;
			Mouse mouse3 = ((HasInputDevices) Driver.driver).getMouse();
			mouse3.mouseMove(hoverItem3.getCoordinates());
			Driver.driver.findElement(By.cssSelector("div[title='Practice']")).click();		//click on 1st TLO of 1st chapter
			Thread.sleep(3000);
			String question2 = Driver.driver.findElement(By.className("al-diag-test-question-raw-content")).getText();
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		    Thread.sleep(2000);
		    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
		    Thread.sleep(2000);
		    String question3 = Driver.driver.findElement(By.className("al-diag-test-question-raw-content")).getText();
			if(question2.equals(question3))
				Assert.fail("A set of questions one after the other are not displayed in a series of screens.");
			
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in studentAttemptingPersonalizedPracticeAtTLOLevel in class StudentAttemptingPersonalizedPracticeAtTLOLevel",e);
		}

	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
