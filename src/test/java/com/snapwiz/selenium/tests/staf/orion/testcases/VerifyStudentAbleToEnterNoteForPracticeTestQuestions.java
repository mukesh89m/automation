package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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
import com.snapwiz.selenium.tests.staf.orion.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectAnswerAndSubmit;

public class VerifyStudentAbleToEnterNoteForPracticeTestQuestions {
	@Test(priority = 1, enabled = true)
	public void verifyStudentAbleToEnterNoteForPracticeTestQuestions()
	{
		try
		{
			Driver.startDriver();	
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			//create a chapter
			new Assignment().createChapter(153, Integer.parseInt(tloids.get(0)));
			//create diagnostic test
			new Assignment().createPracticeAtChapterLevel(153, "Adaptive Component Diagnostic", true, false, true, Integer.parseInt(tloids.get(0)));
			//create adaptive test
			new Assignment().createPracticeAtChapterLevel(153, "Adaptive Component Practice", true, false, true, Integer.parseInt(tloids.get(0)));
			new LoginUsingLTI().ltiLogin("153");
			new DiagnosticTest().openLastDiagnosticTest();
			
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);	
			new Navigator().orionDashboard();
			new PracticeTest().openLastPracticeTest();
			Driver.driver.findElement(By.className("qtn-label")).click();
			Thread.sleep(2000);
			//click submit
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		    Thread.sleep(2000);
		    //click submit
		    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		    Thread.sleep(2000);
		    int noteIcon = Driver.driver.findElements(By.cssSelector("span[class='al-question-notesicon-text']")).size();
		    if(noteIcon == 0)
		    	Assert.fail("The Note icon is not present in the footer.");
			//click on note icon in the footer
		    Driver.driver.findElement(By.cssSelector("span[class='al-question-notesicon-text']")).click();		//click on note icon
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("textarea[class='al-notes-input-text-section']")).click();		//click on textarea of note
			Thread.sleep(2000);
			String note = new RandomString().randomstring(3);
			Driver.driver.switchTo().activeElement().sendKeys(note+Keys.ENTER);		//type note 
			Thread.sleep(2000);
			String addedNote = Driver.driver.findElement(By.cssSelector("div[class='al-user-notes-content']")).getText();
			if(!addedNote.equals(note))
				Assert.fail("The text entered for entered is not getting added on presseing ENTER.");
			//add one more note
			Driver.driver.findElement(By.cssSelector("textarea[class='al-notes-input-text-section']")).click();		//click on textarea of note
			Thread.sleep(2000);
			String note1 = new RandomString().randomstring(3);
			Driver.driver.switchTo().activeElement().sendKeys(note1+Keys.ENTER);		//type note 
			Thread.sleep(2000);
			//check for order of notes
			List<WebElement> allNotes = Driver.driver.findElements(By.cssSelector("div[class='al-user-notes-content']"));
			if(!allNotes.get(0).getText().equals(note1) || !allNotes.get(1).getText().equals(note))
				Assert.fail("The recently added notes are not getting on top of existing note.");
			
			//again login
			new LoginUsingLTI().ltiLogin("153");
			new PracticeTest().openLastPracticeTest();
			Driver.driver.findElement(By.className("qtn-label")).click();
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		    Thread.sleep(2000);
		    Driver.driver.findElement(By.cssSelector("span[class='al-question-notesicon-text']")).click();		//click on note icon
			Thread.sleep(2000);
			List<WebElement> allNotes1 = Driver.driver.findElements(By.cssSelector("div[class='al-user-notes-content']"));
			if(!allNotes1.get(0).getText().equals(note1) || !allNotes1.get(1).getText().equals(note))
				Assert.fail("The previously added note are absent.");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifyStudentAbleToEnterNoteForPracticeTestQuestions in class VerifyStudentAbleToEnterNoteForPracticeTestQuestions",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void verifyNoteButtonForTwoQuestion()
	{
		try
		{
			Driver.startDriver();	
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			String practiceassessmentname= ReadTestData.readDataByTagName("VerifyStudentAbleToEnterNoteForPracticeTestQuestions", "practiceassessmentname", "158");
			//create a chapter
			new Assignment().createChapter(158, Integer.parseInt(tloids.get(0)));
			//create diagnostic test
			new Assignment().createPracticeAtChapterLevel(158, "Adaptive Component Diagnostic", true, false, true, Integer.parseInt(tloids.get(0)));
			//create adaptive test
			new Assignment().createPracticeAtChapterLevel(158, "Adaptive Component Practice", true, false, true, Integer.parseInt(tloids.get(0)));
			new Assignment().addQuestions(158, "qtn-multiple-choice-img", practiceassessmentname, false, true, null, true, Integer.parseInt(tloids.get(0)));
			new LoginUsingLTI().ltiLogin("158");
			new DiagnosticTest().openLastDiagnosticTest();
			
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);	
			new Navigator().orionDashboard();
			new PracticeTest().openLastPracticeTest();
			//select answer
			Driver.driver.findElement(By.className("qtn-label")).click();
			Thread.sleep(2000);
			
			//click submit
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		    Thread.sleep(2000);
		    //click submit
		    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		    Thread.sleep(2000);
		    int noteIcon = Driver.driver.findElements(By.cssSelector("span[class='al-question-notesicon-text']")).size();
		    if(noteIcon == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
		    	Assert.fail("The Note icon is not present in the footer for 1st question.");
		    }
			//click on note icon in the footer
		    Driver.driver.findElement(By.cssSelector("span[class='al-question-notesicon-text']")).click();		//click on note icon
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("textarea[class='al-notes-input-text-section']")).click();		//click on textarea of note
			Thread.sleep(2000);
			String note = new RandomString().randomstring(3);
			Driver.driver.switchTo().activeElement().sendKeys(note+Keys.ENTER);		//type note 
			Thread.sleep(2000);
			String addedNote = Driver.driver.findElement(By.cssSelector("div[class='al-user-notes-content']")).getText();
			if(!addedNote.equals(note))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The text entered for 1st question's note is not getting added on presseing ENTER.");
			}
				
			//click NEXT
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
		    Thread.sleep(2000);
		   //select answer
			Driver.driver.findElement(By.className("qtn-label")).click();
			Thread.sleep(2000);
			//click submit
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		    Thread.sleep(2000);
		    int noteIcon1 = Driver.driver.findElements(By.cssSelector("span[class='al-question-notesicon-text']")).size();
		    if(noteIcon1 == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
		    	Assert.fail("The Note icon is not present in the footer for second question.");
		    }
			//click on note icon in the footer
		    Driver.driver.findElement(By.cssSelector("span[class='al-question-notesicon-text']")).click();		//click on note icon
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("textarea[class='al-notes-input-text-section']")).click();		//click on textarea of note
			Thread.sleep(2000);
			String note1 = new RandomString().randomstring(3);
			Driver.driver.switchTo().activeElement().sendKeys(note1+Keys.ENTER);		//type note 
			Thread.sleep(2000);
			String addedNote1 = Driver.driver.findElement(By.cssSelector("div[class='al-user-notes-content']")).getText();
			if(!addedNote1.equals(note1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The text entered for 2nd question's note is not getting added on presseing ENTER.");
			}
				
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifyNoteButtonForTwoQuestion in class VerifyStudentAbleToEnterNoteForPracticeTestQuestions",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void verifyNoteButtonForThreeQuestion()
	{
		try
		{
			Driver.startDriver();	
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			String practiceassessmentname= ReadTestData.readDataByTagName("VerifyStudentAbleToEnterNoteForPracticeTestQuestions", "practiceassessmentname", "162");
			//create a chapter
			new Assignment().createChapter(162, Integer.parseInt(tloids.get(0)));
			//create diagnostic test
			new Assignment().createPracticeAtChapterLevel(162, "Adaptive Component Diagnostic", true, false, true, Integer.parseInt(tloids.get(0)));
			//create adaptive test
			new Assignment().createPracticeAtChapterLevel(162, "Adaptive Component Practice", true, false, true, Integer.parseInt(tloids.get(0)));
			new Assignment().addQuestions(162, "qtn-multiple-choice-img", practiceassessmentname, false, true,null, true, Integer.parseInt(tloids.get(0)));
			new Assignment().addQuestions(162, "qtn-multiple-choice-img", practiceassessmentname, false, true,null, true, Integer.parseInt(tloids.get(0)));
			new LoginUsingLTI().ltiLogin("162");
			new DiagnosticTest().openLastDiagnosticTest();
			
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);	
			new Navigator().orionDashboard();
			new PracticeTest().openLastPracticeTest();
			//select answer
			Driver.driver.findElement(By.className("qtn-label")).click();
			Thread.sleep(2000);
			
			//click submit
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		    Thread.sleep(2000);
		    //click submit
		    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		    Thread.sleep(2000);
		    int noteIcon = Driver.driver.findElements(By.cssSelector("span[class='al-question-notesicon-text']")).size();
		    if(noteIcon == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
		    	Assert.fail("The Note icon is not present in the footer for 1st question.");
		    }
			//click on note icon in the footer
		    Driver.driver.findElement(By.cssSelector("span[class='al-question-notesicon-text']")).click();		//click on note icon
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("textarea[class='al-notes-input-text-section']")).click();		//click on textarea of note
			Thread.sleep(2000);
			String note = new RandomString().randomstring(3);
			Driver.driver.switchTo().activeElement().sendKeys(note+Keys.ENTER);		//type note 
			Thread.sleep(2000);
			String addedNote = Driver.driver.findElement(By.cssSelector("div[class='al-user-notes-content']")).getText();
			if(!addedNote.equals(note))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The text entered for 1st question's note is not getting added on presseing ENTER.");
			}
			//click NEXT
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
		    Thread.sleep(2000);
		   //select answer
			Driver.driver.findElement(By.className("qtn-label")).click();
			Thread.sleep(2000);
			//click submit
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		    Thread.sleep(2000);
		    int noteIcon1 = Driver.driver.findElements(By.cssSelector("span[class='al-question-notesicon-text']")).size();
		    if(noteIcon1 == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
		    	Assert.fail("The Note icon is not present in the footer for second question.");
		    }
			//click on note icon in the footer
		    Driver.driver.findElement(By.cssSelector("span[class='al-question-notesicon-text']")).click();		//click on note icon
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("textarea[class='al-notes-input-text-section']")).click();		//click on textarea of note
			Thread.sleep(2000);
			String note1 = new RandomString().randomstring(3);
			Driver.driver.switchTo().activeElement().sendKeys(note1+Keys.ENTER);		//type note 
			Thread.sleep(2000);
			String addedNote1 = Driver.driver.findElement(By.cssSelector("div[class='al-user-notes-content']")).getText();
			if(!addedNote1.equals(note1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The text entered for 2nd question's note is not getting added on presseing ENTER.");
			}
			
			//click NEXT
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
		    Thread.sleep(2000);
		   //select answer
			Driver.driver.findElement(By.className("qtn-label")).click();
			Thread.sleep(2000);
			//click submit
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		    Thread.sleep(2000);
		    int noteIcon2 = Driver.driver.findElements(By.cssSelector("span[class='al-question-notesicon-text']")).size();
		    if(noteIcon2 == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
		    	Assert.fail("The Note icon is not present in the footer for 3rd question.");
		    }
			//click on note icon in the footer
		    Driver.driver.findElement(By.cssSelector("span[class='al-question-notesicon-text']")).click();		//click on note icon
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("textarea[class='al-notes-input-text-section']")).click();		//click on textarea of note
			Thread.sleep(2000);
			String note2 = new RandomString().randomstring(3);
			Driver.driver.switchTo().activeElement().sendKeys(note2+Keys.ENTER);		//type note 
			Thread.sleep(2000);
			String addedNote2 = Driver.driver.findElement(By.cssSelector("div[class='al-user-notes-content']")).getText();
			if(!addedNote2.equals(note2))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The text entered for 3rd question's note is not getting added on presseing ENTER.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifyNoteButtonForThreeQuestion in class VerifyStudentAbleToEnterNoteForPracticeTestQuestions",e);
		}
	}
	
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
