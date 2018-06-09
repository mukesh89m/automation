package com.snapwiz.selenium.tests.staf.orion.testcases.IT12.R9;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.orion.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class ToleranceForNumericEntryWithUnitsQuestion {
	
	@Test(priority = 1, enabled = true)
	public void toleranceForNumericEntryWithUnitsQuestion() 
	{
		try
		{
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "160");
			String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", "160");
			String numerictext = ReadTestData.readDataByTagName("", "numerictext", "160");
			String unitoption = ReadTestData.readDataByTagName("", "unitoption", "160");
			Driver.startDriver();
			new Assignment().create(160);//create a assignment
			new Assignment().addQuestions(160, "qtn-text-entry-numeric-units-img", assignmentname, false, false, difficultylevel, false);//add numeric entry with units type question
			String winHandleBefore = Driver.driver.getWindowHandle();//Store the current window handle
			//Perform the click operation that opens new window
			Driver.driver.findElement(By.id("preview-the-image-quiz")).click();//click preview
			Thread.sleep(3000);
			//Switch to new window opened
			for(String winHandle : Driver.driver.getWindowHandles()){
			    Driver.driver.switchTo().window(winHandle);
			}
			// Perform the actions on new window
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	//enter values with tolerance with +1
		 	int toleranceValue = Integer.parseInt(numerictext);
		 	double toleranceValue1 = toleranceValue + 1;
		 	String toleranceValue2 = Double.toString(toleranceValue1) ;
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue2);
		 	Thread.sleep(2000);
		 	//list all the units in the dropdown
		 	List<WebElement> dropdowns =  Driver.driver.findElements(By.className("sbToggle"));	// Add Units in Preview.  "You got it correct".
		 	dropdowns.get(0).click();//click on unit dropdown
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(unitoption)).click();//select unit
			Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();//click submit
		 	Thread.sleep(2000);
		 	String str = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the preview page for numeric entry question with units type question, if we give answer data+tolerance value  with it doesn't show as correct answer");
		 	}
		 
		 	//enter values with tolerance with -1
		 	int toleranceValue6 = Integer.parseInt(numerictext);
		 	double toleranceValue7 = toleranceValue6 - 1;
		 	String toleranceValue8 = Double.toString(toleranceValue7) ;
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue8);
		 	Thread.sleep(2000);
			dropdowns.get(0).click();//click on unit dropdown
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(unitoption)).click();//select unit
			Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();//click submit
		 	Thread.sleep(2000);
		 	String str2 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str2.equals("You got it right."))
		 	 {
		 	 	new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page for numeric entry question with units type question, if we give answer data-tolerance value it doesn't show as correct answer.(Default tolerance is not 1)");
		 	 }
		 	//enter the correct answer
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(numerictext);
		 	Thread.sleep(2000);

		 	dropdowns.get(0).click();//click on unit dropdown
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(unitoption)).click();//select unit
			Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();//click submit
		 	Thread.sleep(2000);
		 	String str3 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str3.equals("You got it right."))
		 	 {
		 	 	new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page for numeric entry question with units type question, if we give correct answer data it doesn't show as correct answer");
		 	 }
		 	Driver.driver.close();                                         // Close the Preview Window
			Driver.driver.switchTo().window(winHandleBefore);                // switch back to the original window
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase toleranceForNumericEntryWithUnitsQuestion in class ToleranceForNumericEntryQuestion",e);
		}
	}
	
	
	@Test(priority = 2, enabled = true)
	public void defaulttoleranceForNumericEntryWithUnitsQuestionByQTIImport() 
	{
		try
		{
			Driver.startDriver();
			new Assignment().create(162);//create a assignment
			Driver.driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.id("addQuestion")));
			Thread.sleep(3000);	
			String filename =  ReadTestData.readDataByTagName("", "filename", "162");
			Actions action = new Actions(Driver.driver);
			WebElement we = Driver.driver.findElement(By.id("import-contents-img"));
		    action.moveToElement(we).build().perform();
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("import-contents-img")));
			new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+filename+"^");
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("start_queue")));
			Thread.sleep(10000);	
			Driver.driver.findElement(By.className("proceedToViewAContent")).click();//click on proceed button
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("span[class='question-link-labels  ']")).click();//go to next question
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.className("question-parameters-title")));//click on algo parameter
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("increase")).click();//click on plus icon
			Driver.driver.findElement(By.id("increase")).click();
			Driver.driver.findElement(By.id("increase")).click();
			Thread.sleep(3000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("saveQuestionDetails1")));// The question is saved. 
			Thread.sleep(5000);
			String unit = Driver.driver.findElement(By.cssSelector("option[selected='selected']")).getAttribute("value");
			//Store the current window handle
			String winHandleBefore = Driver.driver.getWindowHandle();
			Driver.driver.findElement(By.id("preview-the-image-quiz")).click();//click preview
			Thread.sleep(3000);
			//Switch to new window opened
			for(String winHandle : Driver.driver.getWindowHandles()){
			    Driver.driver.switchTo().window(winHandle);
			}
			// Perform the actions on new window
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	String correctAnswer = Driver.driver.findElement(By.id("1")).getAttribute("renderdata");
		 	correctAnswer  =correctAnswer.substring(70);
			correctAnswer = correctAnswer.replaceAll("\"", " ");
			correctAnswer = correctAnswer.replaceAll("}", " ");
			correctAnswer = correctAnswer.trim();
		 	//enter values with tolerance with +1
			double toleranceValue = Double.parseDouble(correctAnswer);
		 	double toleranceValue1 = toleranceValue + 1;
		 	String toleranceValue2 = Double.toString(toleranceValue1) ;
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue2);
		 	Thread.sleep(2000);
		 	//list all the units in the dropdown
		 	List<WebElement> dropdowns =  Driver.driver.findElements(By.className("sbToggle"));	// Add Units in Preview.  "You got it correct".
		 	dropdowns.get(0).click();//click on unit dropdown
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(unit)).click();//select unit
			Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();//click submit
		 	Thread.sleep(2000);
		 	String str = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the preview page for numeric entry question with units type question, if we give answer data+tolerance value  with it doesn't show as correct answer");
		 	}
		 
		 	//enter values with tolerance with -1
		 	double toleranceValue6 = Double.parseDouble(correctAnswer);
		 	double toleranceValue7 = toleranceValue6 - 1;
		 	String toleranceValue8 = Double.toString(toleranceValue7) ;
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue8);
		 	Thread.sleep(2000);
			dropdowns.get(0).click();//click on unit dropdown
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(unit)).click();//select unit
			Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();//click submit
		 	Thread.sleep(2000);
		 	String str2 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str2.equals("You got it right."))
		 	 {
		 	 	new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page for numeric entry question with units type question, if we give answer data-tolerance value it doesn't show as correct answer.(Default tolerance is not 1)");
		 	 }
		 	//enter the correct answer
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(correctAnswer);
		 	Thread.sleep(2000);

		 	dropdowns.get(0).click();//click on unit dropdown
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(unit)).click();//select unit
			Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();//click submit
		 	Thread.sleep(2000);
		 	String str3 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str3.equals("You got it right."))
		 	 {
		 	 	new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page for numeric entry question with units type question, if we give correct answer data it doesn't show as correct answer");
		 	 }
		 	Driver.driver.close();                                         // Close the Preview Window
			Driver.driver.switchTo().window(winHandleBefore);                // switch back to the original window
		 	
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase defaulttoleranceForNumericEntryWithUnitsQuestionByQTIImport in class ToleranceForNumericEntryWithUnitsQuestion",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
