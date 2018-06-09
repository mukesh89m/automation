package com.snapwiz.selenium.tests.staf.orion.testcases.IT12.R9;

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

public class ToleranceForMapleNumericQuestion {
	
	@Test(priority = 1, enabled = true)
	public void toleranceForMapleNumericQuestion()
	{
		try
		{
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "161");
			String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", "161");
			String maplenumeric = ReadTestData.readDataByTagName("", "maplenumeric", "161");
			Driver.startDriver();
			new Assignment().create(161);//create a assignment
			new Assignment().addQuestions(161, "qtn-numeric-maple-img", assignmentname, false, false, difficultylevel, false);//add maple numeric type question
			String winHandleBefore = Driver.driver.getWindowHandle();//Store the current window handle
			Driver.driver.findElement(By.id("preview-the-image-quiz")).click();//click preview
			Thread.sleep(3000);
			//Switch to new window opened
			for(String winHandle : Driver.driver.getWindowHandles()){
			    Driver.driver.switchTo().window(winHandle);
			}
			// Perform the actions on new window
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	//enter values with no tolerance 
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(maplenumeric);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();//click submit
		 	Thread.sleep(2000);
		 	String str = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the preview page for maple numeric question type, if we give answer data without tolerance value  with it doesn't show as correct answer");
		 	}
		 	//enter values with tolerance 
		 	int toleranceValue = Integer.parseInt(maplenumeric);
		 	double toleranceValue1 = toleranceValue+ 0.02;
		 	String toleranceValue2 = Double.toString(toleranceValue1) ;
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue2);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();//click submit
		 	Thread.sleep(2000);
		 	String str1 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText();
		 	if(!str1.equals("You got it wrong."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the preview page for maple numeric question type, by default the tolerance value is present.");
		 	}
		 	Driver.driver.close();                                         // Close the Preview Window
			Driver.driver.switchTo().window(winHandleBefore);                // switch back to the original window
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase toleranceForMapleNumericQuestion in class ToleranceForMapleNumericQuestion",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void defaulttoleranceForMapleNumericEntryQuestionByQTIImport() 
	{
		try
		{
			Driver.startDriver();
			new Assignment().create(163);//create a assignment
			Driver.driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.id("addQuestion")));
			Thread.sleep(3000);	
			String filename =  ReadTestData.readDataByTagName("", "filename", "163");
			Actions action = new Actions(Driver.driver);
			WebElement we = Driver.driver.findElement(By.id("import-contents-img"));
		    action.moveToElement(we).build().perform();
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("import-contents-img")));
			new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+filename+"^");
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("start_queue")));
			Thread.sleep(10000);	
			Driver.driver.findElement(By.className("proceedToViewAContent")).click();//click proceed
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("span[class='question-link-labels  ']")).click();//click to go to next questio
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.className("question-parameters-title")));//click on algo parameter
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("increase")).click();//click on plus icon 
			Driver.driver.findElement(By.id("increase")).click();
			Driver.driver.findElement(By.id("increase")).click();
			Thread.sleep(3000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("saveQuestionDetails1")));// The question is saved. 
			Thread.sleep(5000);
			String winHandleBefore = Driver.driver.getWindowHandle();//Store the current window handle
			Driver.driver.findElement(By.id("preview-the-image-quiz")).click();//click preview
			Thread.sleep(3000);
			//Switch to new window opened
			for(String winHandle : Driver.driver.getWindowHandles()){
			    Driver.driver.switchTo().window(winHandle);
			}
			// Perform the actions on new window
			String correctAnswer = Driver.driver.findElement(By.id("1")).getAttribute("renderdata");//get the correct answer
			correctAnswer  =correctAnswer.substring(70);
			correctAnswer = correctAnswer.replaceAll("\"", " ");
			correctAnswer = correctAnswer.replaceAll("}", " ");
			correctAnswer = correctAnswer.trim();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	//enter values with no tolerance 
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(correctAnswer);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();//click submit
		 	Thread.sleep(2000);
		 	String str = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the preview page for maple numeric question type, if we give answer data without tolerance value  with it doesn't show as correct answer");
		 	}
		 	//enter values with tolerance 
		 	int toleranceValue = Integer.parseInt(correctAnswer);
		 	double toleranceValue1 = toleranceValue+ 0.02;
		 	String toleranceValue2 = Double.toString(toleranceValue1) ;
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue2);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();//click submit
		 	Thread.sleep(2000);
		 	String str1 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText();
		 	if(!str1.equals("You got it wrong."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the preview page for maple numeric question type, by default the tolerance value is present.");
		 	}
		 	Driver.driver.close();                                         // Close the Preview Window
			Driver.driver.switchTo().window(winHandleBefore);                // switch back to the original window
		 	
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase defaulttoleranceForMapleNumericEntryQuestionByQTIImport in class ToleranceForMapleNumericQuestion",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void emptyToleranceInXMLForMapleNumericEntryQuestionByQTIImport() 
	{
		try
		{
			Driver.startDriver();
			new Assignment().create(164);//create a assignment
			Driver.driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.id("addQuestion")));
			Thread.sleep(3000);	
			String filename =  ReadTestData.readDataByTagName("", "filename", "164");
			Actions action = new Actions(Driver.driver);
			WebElement we = Driver.driver.findElement(By.id("import-contents-img"));
		    action.moveToElement(we).build().perform();
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("import-contents-img")));
			new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+filename+"^");
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("start_queue")));
			Thread.sleep(10000);	
			Driver.driver.findElement(By.className("proceedToViewAContent")).click();//click on Proceed button
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("span[class='question-link-labels  ']")).click();//go to next question
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.className("question-parameters-title")));//click on algo parameter
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("increase")).click();//lick on plus icon
			Driver.driver.findElement(By.id("increase")).click();
			Driver.driver.findElement(By.id("increase")).click();
			Thread.sleep(3000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("saveQuestionDetails1")));// The question is saved. 
			Thread.sleep(5000);
			String winHandleBefore = Driver.driver.getWindowHandle();//Store the current window handle
			Driver.driver.findElement(By.id("preview-the-image-quiz")).click();//click preview
			Thread.sleep(3000);
			//Switch to new window opened
			for(String winHandle : Driver.driver.getWindowHandles()){
			    Driver.driver.switchTo().window(winHandle);
			}
			// Perform the actions on new window
			String correctAnswer = Driver.driver.findElement(By.id("1")).getAttribute("renderdata");//get the correct answer
			correctAnswer  =correctAnswer.substring(70);
			correctAnswer = correctAnswer.replaceAll("\"", " ");
			correctAnswer = correctAnswer.replaceAll("}", " ");
			correctAnswer = correctAnswer.trim();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	//enter values with no tolerance 
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(correctAnswer);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();//click submit
		 	Thread.sleep(2000);
		 	String str = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the preview page for maple numeric question type, if we give answer data without tolerance value  with it doesn't show as correct answer");
		 	}
		 	//enter values with tolerance 
		 	double toleranceValue = Double.parseDouble(correctAnswer);
		 	toleranceValue = toleranceValue+ 0.02;
		 	String toleranceValue2 = Double.toString(toleranceValue) ;
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue2);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();//click submit
		 	Thread.sleep(2000);
		 	String str1 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText();
		 	if(!str1.equals("You got it wrong."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the preview page for maple numeric question type, by default the tolerance value is present.");
		 	}
		 	Driver.driver.close();                                         // Close the Preview Window
			Driver.driver.switchTo().window(winHandleBefore);                // switch back to the original window
		 	
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase emptyToleranceInXMLForMapleNumericEntryQuestionByQTIImport in class ToleranceForMapleNumericQuestion",e);
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void editedToleranceInXMLForMapleNumericEntryQuestionByQTIImport() 
	{
		try
		{
			Driver.startDriver();
			new Assignment().create(166);//create a assignment
			Driver.driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.id("addQuestion")));
			Thread.sleep(3000);	
			String filename =  ReadTestData.readDataByTagName("", "filename", "166");
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
			String winHandleBefore = Driver.driver.getWindowHandle();//Store the current window handle
			Driver.driver.findElement(By.id("preview-the-image-quiz")).click();//click preview
			Thread.sleep(3000);
			//Switch to new window opened
			for(String winHandle : Driver.driver.getWindowHandles()){
			    Driver.driver.switchTo().window(winHandle);
			}
			// Perform the actions on new window
			String correctAnswer = Driver.driver.findElement(By.id("1")).getAttribute("renderdata");//get the correct answer
			correctAnswer  =correctAnswer.substring(70);
			correctAnswer = correctAnswer.replaceAll("\"", " ");
			correctAnswer = correctAnswer.replaceAll("}", " ");
			correctAnswer = correctAnswer.trim();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	//enter values with no tolerance 
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(correctAnswer);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();//click submit
		 	Thread.sleep(2000);
		 	String str = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the preview page for maple numeric question type, if we give answer data without tolerance value  with it doesn't show as correct answer");
		 	}
		 	//enter values with tolerance + 2
		 	double toleranceValue = Double.parseDouble(correctAnswer);
		 	toleranceValue = toleranceValue+ 2;
		 	String toleranceValue2 = Double.toString(toleranceValue) ;
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue2);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();//click submit
		 	Thread.sleep(2000);
		 	String str1 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str1.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the preview page for maple numeric question type, if we give answer data + tolerance value with it doesn't show as correct answer, where as tolerance attribute is set to 2 in XML file.");
		 	}
		 	//enter values with tolerance - 2
		 	double toleranceValue3 = Double.parseDouble(correctAnswer);
		 	toleranceValue3 = toleranceValue3 - 2;
		 	String toleranceValue4 = Double.toString(toleranceValue3) ;
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue4);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();//click submit
		 	Thread.sleep(2000);
		 	String str2 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str2.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the preview page for maple numeric question type, if we give answer data - tolerance value with it doesn't show as correct answer, where as tolerance attribute is set to 2 in XML file.");
		 	}
		 	Driver.driver.close();                                         // Close the Preview Window
			Driver.driver.switchTo().window(winHandleBefore);                // switch back to the original window
		 	
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase emptyToleranceInXMLForMapleNumericEntryQuestionByQTIImport in class ToleranceForMapleNumericQuestion",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
