package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

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
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.uihelper.ComboBox;

public class VerifyUIChangesInQE {
	@Test(priority = 1, enabled = true)
	public void verifyUIChangesInQE()
	{
		try
		{
			Driver.startDriver();	
			String diagassessmentname = ReadTestData.readDataByTagName("VerifyUIChangesInQE", "diagassessmentname", "1410");
			String questionset = ReadTestData.readDataByTagName("VerifyUIChangesInQE", "questionset", "1410");
			String editedassessmentname = ReadTestData.readDataByTagName("VerifyUIChangesInQE", "editedassessmentname", "1410");
			String editedquestionsetname = ReadTestData.readDataByTagName("VerifyUIChangesInQE", "editedquestionsetname", "1410");
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			new Assignment().createChapter(1410, Integer.parseInt(tloids.get(0)));
			new Assignment().createPracticeAtChapterLevel(1410, "Adaptive Component Diagnostic", false, false, false, Integer.parseInt(tloids.get(0)));
			//mouse hover the edit 
			WebElement editAssessment = Driver.driver.findElement(By.className("question-container-header-assessment-edit")); 
			Locatable hoverItem = (Locatable) editAssessment;
			Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
			mouse.mouseMove(hoverItem.getCoordinates());
			Thread.sleep(2000);
			WebElement WE = Driver.driver.findElement(By.cssSelector("div[class='assessment-edit-icon-wrapper']"));
			String editIcon = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",WE);
			if(!editIcon.contains("edit-icon.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Edit icon is absent on mouse hovering the question edit place.");
			}
			//click on edit icon
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div.assessment-edit-icon-wrapper > img")));
		 	Thread.sleep(2000);
		 	//check 'Assessment Name' label
		 	List<WebElement> allLabels = Driver.driver.findElements(By.className("assessment-content-edit-label"));
		 	if(!allLabels.get(0).getText().equals("Assessment Name"))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'Assessment Name' label is absent in pop up window to edit the assessment name.");
		 	}
		 	//check Assessment Name' is  populated or not
		 	String assessmentName = Driver.driver.findElement(By.id("edit-assessment-name")).getAttribute("value");
		 	if(!assessmentName.equals(diagassessmentname))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'Assessment Name' is not populated with the current name of the assessment in the pop up.");
		 	}
		 	//types of practice
		 	List<WebElement> allElements = Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
		 	if(!allElements.get(7).getText().contains("Adaptive Component Diagnostic"))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'Adaptive Component Diagnostic' type is absent in pop up window to edit the assessment name.");
		 	}
		 	
		 	//check 'Current Question Set Name' label
		 	if(!allLabels.get(2).getText().equals("Current Question Set Name"))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'Current Question Set Name' label is absent in pop up window to edit the assessment name.");
		 	}
		 	//check question set Name' is  populated or not
		 	String QsetName = Driver.driver.findElement(By.id("edit-question-set-name")).getAttribute("value");
		 	if(!QsetName.equals(questionset))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("questionset Name is not populated with the current name of the question set in the pop up.");
		 	}
		 	//check for save button
		 	int saveButton = Driver.driver.findElements(By.id("assessment-edit-save-btn")).size();
		 	if(saveButton == 0)
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Save button is absent in the pop up.");
		 	}
		 	//check for close button
		 	int closeIcon = Driver.driver.findElements(By.id("dialog-close")).size();
		 	if(closeIcon == 0)
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Cancel icon is absent in the pop up.");
		 	}
		 	//edit assessment name
		 	Driver.driver.findElement(By.id("edit-assessment-name")).clear();
		 	Driver.driver.findElement(By.id("edit-assessment-name")).sendKeys(editedassessmentname);
		 	Thread.sleep(2000);
		 	//edit question set name
		 	Driver.driver.findElement(By.id("edit-question-set-name")).clear();
		 	Driver.driver.findElement(By.id("edit-question-set-name")).sendKeys(editedquestionsetname);
		 	Thread.sleep(2000);
		 	//click save
		 	Driver.driver.findElement(By.id("assessment-edit-save-btn")).click();
		 	Thread.sleep(2000);
		 	String newAssessmentName = Driver.driver.findElement(By.className("assessment-name-label-wrapper")).getText();
		 	if(!newAssessmentName.contains(editedassessmentname))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The assessment name doen't get changed after editing.");
		 	}
		 	String newQuestionSet = Driver.driver.findElement(By.className("question-container-header-second")).getText();
		 	if(!newQuestionSet.contains(editedquestionsetname))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The question set name doen't get changed after editing.");
		 	}
		 	Thread.sleep(3000);
		 	
		 	
		 	//mouse hover the edit 
			WebElement editAssessment1 = Driver.driver.findElement(By.className("question-container-header-assessment-edit")); 
			Locatable hoverItem1 = (Locatable) editAssessment1;
			Mouse mouse1 = ((HasInputDevices) Driver.driver).getMouse();
			mouse1.mouseMove(hoverItem1.getCoordinates());
			Thread.sleep(2000);
			//click to edit again
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div.assessment-edit-icon-wrapper > img")));
		 	//((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='ssessment-edit-icon-wrapper']")));
		 	Thread.sleep(2000);
		 	//edit assessment name
		 	Driver.driver.findElement(By.id("edit-assessment-name")).clear();
		 	Driver.driver.findElement(By.id("edit-assessment-name")).sendKeys(diagassessmentname);
		 	Thread.sleep(2000);
		 	//edit question set name
		 	Driver.driver.findElement(By.id("edit-question-set-name")).clear();
		 	Driver.driver.findElement(By.id("edit-question-set-name")).sendKeys(questionset);
		 	Thread.sleep(2000);
		 	//select 'Adaptive component practice'
		 	new ComboBox().selectValue(7, "Adaptive Component Practice");
		 	//click save
		 	Driver.driver.findElement(By.id("assessment-edit-save-btn")).click();
		 	Thread.sleep(2000);
		 	String newAssessmentName1 = Driver.driver.findElement(By.className("assessment-name-label-wrapper")).getText();
		 	if(!newAssessmentName1.contains(diagassessmentname))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The assessment name doen't get changed after editing.");
		 	}
		 	String newQuestionSet1 = Driver.driver.findElement(By.className("question-container-header-second")).getText();
		 	if(!newQuestionSet1.contains(questionset))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The question set name doesn't get changed after editing.");
		 	}
		 	//mouse hover the edit 
			WebElement editAssessment2 = Driver.driver.findElement(By.className("question-container-header-assessment-edit")); 
			Locatable hoverItem2 = (Locatable) editAssessment2;
			Mouse mouse2 = ((HasInputDevices) Driver.driver).getMouse();
			mouse2.mouseMove(hoverItem2.getCoordinates());
			Thread.sleep(2000);
			//click to edit again
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div.assessment-edit-icon-wrapper > img")));
			Thread.sleep(2000);
			//types of practice
		 	List<WebElement> allElements1 = Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
		 	String name = allElements1.get(7).getText();
		 	Thread.sleep(2000);
		 	if(!name.contains("Adaptive Component Practice"))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'Adaptive Component Practice' type is absent in pop up window to edit the assessment name.");
		 	}
		 	//
		 	new ComboBox().selectValue(7, "Adaptive Component Diagnostic");
		 	//click save
		 	Driver.driver.findElement(By.id("assessment-edit-save-btn")).click();
		 	Thread.sleep(2000);
		 	//mouse hover the edit 
			WebElement editAssessment3 = Driver.driver.findElement(By.className("question-container-header-assessment-edit")); 
			Locatable hoverItem3 = (Locatable) editAssessment3;
			Mouse mouse3 = ((HasInputDevices) Driver.driver).getMouse();
			mouse3.mouseMove(hoverItem3.getCoordinates());
			Thread.sleep(2000);
			//click to edit again
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div.assessment-edit-icon-wrapper > img")));
			Thread.sleep(2000);
			//edit assessment name
		 	Driver.driver.findElement(By.id("edit-assessment-name")).clear();
		 	Driver.driver.findElement(By.id("edit-assessment-name")).sendKeys(editedassessmentname);
		 	Thread.sleep(2000);
		 	//edit question set name
		 	Driver.driver.findElement(By.id("edit-question-set-name")).clear();
		 	Driver.driver.findElement(By.id("edit-question-set-name")).sendKeys(editedquestionsetname);
		 	Thread.sleep(2000);
		 	//click cancel
		 	Driver.driver.findElement(By.id("dialog-close")).click();
		 	Thread.sleep(2000);
		 	//the assessment should not update
		 	String newAssessmentName2 = Driver.driver.findElement(By.className("assessment-name-label-wrapper")).getText();
		 	if(!newAssessmentName2.contains(diagassessmentname))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The assessment name get changed after editing and clicking cancel.");
		 	}
		 	String newQuestionSet2 = Driver.driver.findElement(By.className("question-container-header-second")).getText();
		 	if(!newQuestionSet2.contains(questionset))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The question set name get changed after editing and clicking cancel.");
		 	}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in verifyUIChangesInQE in class VerifyUIChangesInQE",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void verifyUIChangesInQEForPractice()
	{
		try
		{
			Driver.startDriver();	
			String diagassessmentname = ReadTestData.readDataByTagName("VerifyUIChangesInQE", "diagassessmentname", "1417");
			String questionset = ReadTestData.readDataByTagName("VerifyUIChangesInQE", "questionset", "1417");
			String editedassessmentname = ReadTestData.readDataByTagName("VerifyUIChangesInQE", "editedassessmentname", "1417");
			String editedquestionsetname = ReadTestData.readDataByTagName("VerifyUIChangesInQE", "editedquestionsetname", "1417");
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			new Assignment().createChapter(1417, Integer.parseInt(tloids.get(0)));
			new Assignment().createPracticeAtChapterLevel(1417, "Adaptive Component Practice", false, false, false, Integer.parseInt(tloids.get(0)));
			WebElement editAssessment = Driver.driver.findElement(By.className("question-container-header-assessment-edit")); 
			Locatable hoverItem = (Locatable) editAssessment;
			Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
			mouse.mouseMove(hoverItem.getCoordinates());
			Thread.sleep(2000);
			//click on edit icon
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div.assessment-edit-icon-wrapper > img")));
		 	Thread.sleep(2000);
		 	//edit assessment name
		 	Driver.driver.findElement(By.id("edit-assessment-name")).clear();
		 	Driver.driver.findElement(By.id("edit-assessment-name")).sendKeys(editedassessmentname);
		 	Thread.sleep(2000);
		 	//edit question set name
		 	Driver.driver.findElement(By.id("edit-question-set-name")).clear();
		 	Driver.driver.findElement(By.id("edit-question-set-name")).sendKeys(editedquestionsetname);
		 	Thread.sleep(2000);
		 	//click save
		 	Driver.driver.findElement(By.id("assessment-edit-save-btn")).click();
		 	Thread.sleep(2000);
		 	String newAssessmentName = Driver.driver.findElement(By.className("assessment-name-label-wrapper")).getText();
		 	if(!newAssessmentName.contains(editedassessmentname))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The assessment name doesn't get changed after editing.");
		 	}
		 	String newQuestionSet = Driver.driver.findElement(By.className("question-container-header-second")).getText();
		 	if(!newQuestionSet.contains(editedquestionsetname))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The question set name doesn't get changed after editing.");
		 	}
		 	//mouse hover the edit 
			WebElement editAssessment1 = Driver.driver.findElement(By.className("question-container-header-assessment-edit")); 
			Locatable hoverItem1 = (Locatable) editAssessment1;
			Mouse mouse1 = ((HasInputDevices) Driver.driver).getMouse();
			mouse1.mouseMove(hoverItem1.getCoordinates());
			Thread.sleep(2000);
			//click to edit again
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div.assessment-edit-icon-wrapper > img")));
		 	Thread.sleep(2000);
		 	List<WebElement> allElements1 = Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
		 	String name = allElements1.get(7).getText();
		 	Thread.sleep(2000);
		 	if(!name.contains("Adaptive Component Practice"))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'Adaptive Component Practice' type is absent in pop up window to edit the assessment name.");
		 	}
		 	new ComboBox().selectValue(7, "Adaptive Component Diagnostic");
		 	Driver.driver.findElement(By.id("assessment-edit-save-btn")).click();
		 	Thread.sleep(2000);
		 	//mouse hover the edit 
			WebElement editAssessment2 = Driver.driver.findElement(By.className("question-container-header-assessment-edit")); 
			Locatable hoverItem2 = (Locatable) editAssessment2;
			Mouse mouse2 = ((HasInputDevices) Driver.driver).getMouse();
			mouse2.mouseMove(hoverItem2.getCoordinates());
			Thread.sleep(2000);
			//click to edit again
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div.assessment-edit-icon-wrapper > img")));
		 	Thread.sleep(2000);
		 	List<WebElement> allElements = Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
		 	String name1 = allElements.get(7).getText();
		 	Thread.sleep(2000);
		 	if(!name1.contains("Adaptive Component Diagnostic"))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'Adaptive Component Diagnostic' type is absent in pop up window to edit the assessment name.");
		 	}
		 	Driver.driver.findElement(By.id("assessment-edit-save-btn")).click();
		 	Thread.sleep(2000);
		 	//mouse hover the edit 
			WebElement editAssessment3 = Driver.driver.findElement(By.className("question-container-header-assessment-edit")); 
			Locatable hoverItem3 = (Locatable) editAssessment3;
			Mouse mouse3 = ((HasInputDevices) Driver.driver).getMouse();
			mouse3.mouseMove(hoverItem3.getCoordinates());
			Thread.sleep(2000);
			//click to edit again
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div.assessment-edit-icon-wrapper > img")));
			Thread.sleep(2000);
			//edit assessment name
		 	Driver.driver.findElement(By.id("edit-assessment-name")).clear();
		 	Driver.driver.findElement(By.id("edit-assessment-name")).sendKeys(diagassessmentname);
		 	Thread.sleep(2000);
		 	//edit question set name
		 	Driver.driver.findElement(By.id("edit-question-set-name")).clear();
		 	Driver.driver.findElement(By.id("edit-question-set-name")).sendKeys(questionset);
		 	Thread.sleep(2000);
		 	//click cancel
		 	Driver.driver.findElement(By.id("dialog-close")).click();
		 	Thread.sleep(2000);
		 	//the assessment should not update
		 	String newAssessmentName2 = Driver.driver.findElement(By.className("assessment-name-label-wrapper")).getText();
		 	if(!newAssessmentName2.contains(editedassessmentname))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("The assessment name get changed after editing and clicking cancel.");
		 	}
		 	String newQuestionSet2 = Driver.driver.findElement(By.className("question-container-header-second")).getText();
		 	if(!newQuestionSet2.contains(editedquestionsetname))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The question set name get changed after editing and clicking cancel.");
		 	}
		 	
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in verifyUIChangesInQE in class VerifyUIChangesInQE",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
