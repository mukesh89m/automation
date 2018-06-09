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

public class ToleranceForNumericEntryQuestion {
	
	@Test(priority = 1, enabled = true)
	public void toleranceForNumericEntryQuestion() 
	{
		try
		{
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "147");
			String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", "147");
			String numerictext = ReadTestData.readDataByTagName("", "numerictext", "147");
			String optiontext = ReadTestData.readDataByTagName("", "optiontext", "147");
			String tolrence = ReadTestData.readDataByTagName("", "tolrence", "147");
			String tloids = ReadTestData.readDataByTagName("tloids", "tlo", "0");
			int tloId = Integer.parseInt(tloids);
			Driver.startDriver();
			new Assignment().create(147);//create a assignment
			new Assignment().addQuestions(147, "qtn-text-entry-numeric-img", assignmentname, false, false, difficultylevel, false, tloId);//add numeric entry type question
			String winHandleBefore = Driver.driver.getWindowHandle();//Store the current window handle

			//Perform the click operation that opens new window
			Driver.driver.findElement(By.id("preview-the-image-quiz")).click();
			Thread.sleep(3000);
			//Switch to new window opened
			for(String winHandle : Driver.driver.getWindowHandles()){
			    Driver.driver.switchTo().window(winHandle);
			}
			
			
			// Perform the actions on new window
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	//enter values with tolerance with +0.02
		 	int toleranceValue = Integer.parseInt(numerictext);
		 	double toleranceValue1 = toleranceValue+ 0.02;
		 	String toleranceValue2 = Double.toString(toleranceValue1) ;
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue2);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();//click submit
		 	Thread.sleep(2000);
		 	String str = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the preview page if we give answer data+tolerance value  with it doesn't show as correct answer");
		 	}
		 
		 	//enter values with tolerance with -0.02
		 	int toleranceValue6 = Integer.parseInt(numerictext);
		 	double toleranceValue7 = toleranceValue6 - 0.02;
		 	String toleranceValue8 = Double.toString(toleranceValue7) ;
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue8);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str2 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str2.equals("You got it right."))
		 	 {
		 	 	new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer data-tolerance value it doesn't show as correct answer");
		 	 }
		 	 	
		 	 	
		 	 	
		 	//enter alternate answer with tolerance with +0.02
			int toleranceValue9 = Integer.parseInt(optiontext);
			double toleranceValue10 = toleranceValue9+ 0.02;
			String toleranceValue11 = Double.toString(toleranceValue10);
			Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
			Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue11);
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("question-reveview-submit")).click();
			Thread.sleep(2000);
			String str3 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
			if(!str3.equals("You got it right."))
			 {
			 	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the preview page if we give alternate answer data+tolerance value it doesn't show as correct answer");
			 }
			 
			 //enter alternate answer with tolerance with -0.02
			 int toleranceValue12 = Integer.parseInt(optiontext);
			 double toleranceValue13 = toleranceValue12 - 0.02;
			 String toleranceValue14 = Double.toString(toleranceValue13) ;
			 Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
			 Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue14);
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.id("question-reveview-submit")).click();
			 Thread.sleep(2000);
			 String str4 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
			 if(!str4.equals("You got it right."))
			 	 {
			 	 	new Screenshot().captureScreenshotFromTestCase();
			 		Assert.fail("In the preview page if we give alternate answer data-tolerance value it doesn't show as correct answer");
			 	 }
		 	//Close the new window, if that window no more required
		 	Driver.driver.close();
		 	//Switch back to original browser (first window)
		 	Driver.driver.switchTo().window(winHandleBefore);
		 	//edit the tolerance value
		 	Driver.driver.findElement(By.id("tolerance-ans-text")).clear();
		 	Driver.driver.findElement(By.id("tolerance-ans-text")).sendKeys(tolrence);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("done-button")).click(); // Accept answer
		 	Thread.sleep(3000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("saveQuestionDetails1")));// The question is saved. 
			Thread.sleep(5000);
		 	//Store the current window handle
			String winHandleBefore1 = Driver.driver.getWindowHandle();

			//Perform the click operation that opens new window
			Driver.driver.findElement(By.id("preview-the-image-quiz")).click();
			Thread.sleep(3000);
			//Switch to new window opened
			for(String winHandle : Driver.driver.getWindowHandles()){
			    Driver.driver.switchTo().window(winHandle);
			}
			
			
			// Perform the actions on new window
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	//enter values with tolerance with +2
		 	int toleranceValue15 = Integer.parseInt(numerictext);
		 	double toleranceValue16 = toleranceValue15+ 2;
		 	String toleranceValue17 = Double.toString(toleranceValue16) ;
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue17);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str5 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str5.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("If we edit the tolerance value and, In the preview page if we give answer data+tolerance value  with it doesn't show as correct answer.");
		 	}
		 
		 	//enter values with tolerance with -2
		 	int toleranceValue18 = Integer.parseInt(numerictext);
		 	double toleranceValue19 = toleranceValue18 - 2;
		 	String toleranceValue20 = Double.toString(toleranceValue19) ;
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue20);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str6 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str6.equals("You got it right."))
		 	 {
		 	 	new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("If we edit the tolerance value and, In the preview page if we give answer data-tolerance value it doesn't show as correct answer");
		 	 }
		 	 	
		 	 	
		 	 	
		 	//enter alternate answer with tolerance with +2
			int toleranceValue21 = Integer.parseInt(optiontext);
			double toleranceValue22 = toleranceValue21 + 2;
			String toleranceValue23 = Double.toString(toleranceValue22);
			Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
			Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue23);
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("question-reveview-submit")).click();
			Thread.sleep(2000);
			String str7 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
			if(!str7.equals("You got it right."))
			 {
			 	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("If we edit the tolerance value and, In the preview page if we give alternate answer data+tolerance value it doesn't show as correct answer");
			 }
			 
			 //enter alternate answer with tolerance with -2
			 int toleranceValue24 = Integer.parseInt(optiontext);
			 double toleranceValue25 = toleranceValue24 - 2;
			 String toleranceValue26 = Double.toString(toleranceValue25) ;
			 Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
			 Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue26);
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.id("question-reveview-submit")).click();
			 Thread.sleep(2000);
			 String str8 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
			 if(!str8.equals("You got it right."))
			 	 {
			 	 	new Screenshot().captureScreenshotFromTestCase();
			 		Assert.fail("If we edit the tolerance value and, In the preview page if we give alternate answer data-tolerance value it doesn't show as correct answer");
			 	 }
			//Close the new window, if that window no more required
			 Driver.driver.close();
			 //Switch back to original browser (first window)
			 Driver.driver.switchTo().window(winHandleBefore1);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase toleranceForNumericEntryQuestion in class ToleranceForNumericEntryQuestion",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void defaulttoleranceForNumericEntryQuestionByQTIImport() 
	{
		try
		{
			Driver.startDriver();
			new Assignment().create(152);//create a assignment
			Driver.driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.id("addQuestion")));
			Thread.sleep(3000);	
			String filename =  ReadTestData.readDataByTagName("", "filename", "152");
			Actions action = new Actions(Driver.driver);
			WebElement we = Driver.driver.findElement(By.id("import-contents-img"));
		    action.moveToElement(we).build().perform();
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("import-contents-img")));
			new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+filename+"^");
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("start_queue")));
			Thread.sleep(10000);	
			Driver.driver.findElement(By.className("proceedToViewAContent")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("span[class='question-link-labels  ']")).click();
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.className("question-parameters-title")));
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("increase")).click();
			Driver.driver.findElement(By.id("increase")).click();
			Driver.driver.findElement(By.id("increase")).click();
			Thread.sleep(3000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("saveQuestionDetails1")));// The question is saved. 
			Thread.sleep(5000);
		    Driver.driver.findElement(By.id("preview-the-image-quiz")).click();
			Thread.sleep(3000);
		

			String parentHandle = Driver.driver.getWindowHandle();
			for (String winHandle : Driver.driver.getWindowHandles()) 
				{
				 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window) 
				}
			String correctAnswer = Driver.driver.findElement(By.id("1")).getAttribute("renderdata");
			correctAnswer  =correctAnswer.substring(70);
			correctAnswer = correctAnswer.replaceAll("\"", " ");
			correctAnswer = correctAnswer.replaceAll("}", " ");
			correctAnswer = correctAnswer.trim();
			//enter values with tolerance with +0.02
			double toleranceValue = Double.parseDouble(correctAnswer);
		 	double toleranceValue1 = toleranceValue+ 0.02;
		 	String toleranceValue2 = Double.toString(toleranceValue1) ;
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue2);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the preview page if we give answer data+tolerance value  with it doesn't show as correct answer(question has been added with QTI import)");
		 	}
		 
		 	//enter values with tolerance with -0.02
		 	double toleranceValue6 = Double.parseDouble(correctAnswer);
		 	double toleranceValue7 = toleranceValue6 - 0.02;
		 	String toleranceValue8 = Double.toString(toleranceValue7) ;
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue8);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str2 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str2.equals("You got it right."))
		 	 {
		 	 	new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer data-tolerance value it doesn't show as correct answer(question has been added with QTI import)");
		 	 }
		 	Driver.driver.close();                                         // Close the Preview Window
			Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase defaulttoleranceForNumericEntryQuestionByQTIImport in class ToleranceForNumericEntryQuestion",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void editedToleranceForNumericEntryQuestionByQTIImport() 
	{
		try
		{
			Driver.startDriver();
			new Assignment().create(154);//create a assignment
			Driver.driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.id("addQuestion")));
			Thread.sleep(3000);	
			String filename =  ReadTestData.readDataByTagName("", "filename", "154");
			Actions action = new Actions(Driver.driver);
			WebElement we = Driver.driver.findElement(By.id("import-contents-img"));
		    action.moveToElement(we).build().perform();
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("import-contents-img")));
			new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+filename+"^");
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("start_queue")));
			//Driver.driver.findElement(By.id("start_queue")).click(); 
			Thread.sleep(10000);	
			Driver.driver.findElement(By.className("proceedToViewAContent")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("span[class='question-link-labels  ']")).click();
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.className("question-parameters-title")));
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("increase")).click();
			Driver.driver.findElement(By.id("increase")).click();
			Driver.driver.findElement(By.id("increase")).click();
			Thread.sleep(3000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("saveQuestionDetails1")));// The question is saved. 
			Thread.sleep(5000);
		    Driver.driver.findElement(By.id("preview-the-image-quiz")).click();
			Thread.sleep(3000);
		

			String parentHandle = Driver.driver.getWindowHandle();
			for (String winHandle : Driver.driver.getWindowHandles()) 
				{
				 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window) 
				}
			String correctAnswer = Driver.driver.findElement(By.id("1")).getAttribute("renderdata");
			correctAnswer  =correctAnswer.substring(70);
			correctAnswer = correctAnswer.replaceAll("\"", " ");
			correctAnswer = correctAnswer.replaceAll("}", " ");
			correctAnswer = correctAnswer.trim();
			//enter values with tolerance with +2
			double toleranceValue = Double.parseDouble(correctAnswer);
		 	double toleranceValue1 = toleranceValue+ 2;
		 	String toleranceValue2 = Double.toString(toleranceValue1) ;
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue2);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the preview page if we give answer data+edited tolerance value  with it doesn't show as correct answer(question has been added with QTI import)");
		 	}
		 
		 	//enter values with tolerance with -2
		 	double toleranceValue6 = Double.parseDouble(correctAnswer);
		 	double toleranceValue7 = toleranceValue6 - 2;
		 	String toleranceValue8 = Double.toString(toleranceValue7) ;
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue8);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str2 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str2.equals("You got it right."))
		 	 {
		 	 	new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer data-edited tolerance value it doesn't show as correct answer(question has been added with QTI import).");
		 	 }
		 	Driver.driver.close();                                         // Close the Preview Window
			Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase editedToleranceForNumericEntryQuestionByQTIImport in class ToleranceForNumericEntryQuestion",e);
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void blankToleranceForNumericEntryQuestionByQTIImport() 
	{
		try
		{
			Driver.startDriver();
			new Assignment().create(156);//create a assignment
			Driver.driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.id("addQuestion")));
			Thread.sleep(3000);	
			String filename =  ReadTestData.readDataByTagName("", "filename", "156");
			Actions action = new Actions(Driver.driver);
			WebElement we = Driver.driver.findElement(By.id("import-contents-img"));
		    action.moveToElement(we).build().perform();
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("import-contents-img")));
			new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+filename+"^");
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("start_queue")));
			Thread.sleep(10000);	
			Driver.driver.findElement(By.className("proceedToViewAContent")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("span[class='question-link-labels  ']")).click();
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.className("question-parameters-title")));
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("increase")).click();
			Driver.driver.findElement(By.id("increase")).click();
			Driver.driver.findElement(By.id("increase")).click();
			Thread.sleep(3000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("saveQuestionDetails1")));// The question is saved. 
			Thread.sleep(5000);
		    Driver.driver.findElement(By.id("preview-the-image-quiz")).click();
			Thread.sleep(3000);
			String parentHandle = Driver.driver.getWindowHandle();
			for (String winHandle : Driver.driver.getWindowHandles()) 
				{
				 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window) 
				}
			String correctAnswer = Driver.driver.findElement(By.id("1")).getAttribute("renderdata");
			correctAnswer  =correctAnswer.substring(70);
			correctAnswer = correctAnswer.replaceAll("\"", " ");
			correctAnswer = correctAnswer.replaceAll("}", " ");
			correctAnswer = correctAnswer.trim();
			//enter values with no tolerance
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(correctAnswer);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the preview page if we give answer data with no tolerance value  with it doesn't show as correct answer(question has been added with QTI import)");
		 	}
		 
		 	//enter values with tolerance with -2
		 	double toleranceValue6 = Double.parseDouble(correctAnswer);
		 	double toleranceValue7 = toleranceValue6 - 2;
		 	String toleranceValue8 = Double.toString(toleranceValue7) ;
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue8);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str2 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText();
		 	if(!str2.equals("You got it wrong."))
		 	 {
		 	 	new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer data with tolerance value it show as correct answer(question has been added with QTI import).");
		 	 }
		 	Driver.driver.close();                                         // Close the Preview Window
			Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase blankToleranceForNumericEntryQuestionByQTIImport in class ToleranceForNumericEntryQuestion",e);
		}
	}
	
	@Test(priority = 5, enabled = true)
	public void toleranceValueAsEforNumericEntryQuestionByQTIImport() 
	{
		try
		{
			Driver.startDriver();
			new Assignment().create(158);//create a assignment
			Driver.driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.id("addQuestion")));
			Thread.sleep(3000);	
			String filename =  ReadTestData.readDataByTagName("", "filename", "158");
			Actions action = new Actions(Driver.driver);
			WebElement we = Driver.driver.findElement(By.id("import-contents-img"));
		    action.moveToElement(we).build().perform();
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("import-contents-img")));
			new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+filename+"^");
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("start_queue"))); 
			Thread.sleep(10000);	
			Driver.driver.findElement(By.className("proceedToViewAContent")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("span[class='question-link-labels  ']")).click();
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.className("question-parameters-title")));
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("increase")).click();
			Driver.driver.findElement(By.id("increase")).click();
			Driver.driver.findElement(By.id("increase")).click();
			Thread.sleep(3000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("saveQuestionDetails1")));// The question is saved. 
			Thread.sleep(5000);
		    Driver.driver.findElement(By.id("preview-the-image-quiz")).click();
			Thread.sleep(3000);
			

			String parentHandle = Driver.driver.getWindowHandle();
			for (String winHandle : Driver.driver.getWindowHandles()) 
				{
				 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window) 
				}
			String correctAnswer = Driver.driver.findElement(By.id("1")).getAttribute("renderdata");
			correctAnswer  =correctAnswer.substring(70);
			correctAnswer = correctAnswer.replaceAll("\"", " ");
			correctAnswer = correctAnswer.replaceAll("}", " ");
			correctAnswer = correctAnswer.trim();
			//enter values with no tolerance
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(correctAnswer);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str1 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str1.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the preview page if we give answer other than correct with E as tolerance value in XML file then it show as correct answer(question has been added with QTI import)");
		 	}
		 
		 	//enter values with tolerance with -2
		 	double toleranceValue6 = Double.parseDouble(correctAnswer);
		 	double toleranceValue7 = toleranceValue6 - 2;
		 	String toleranceValue8 = Double.toString(toleranceValue7) ;
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue8);
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText();
		 	if(!str.equals("You got it wrong."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the preview page if we give answer other than correct with E as tolerance value in XML file then it show as correct answer(question has been added with QTI import)");
		 	}
		 	
		 	Driver.driver.close();                                         // Close the Preview Window
			Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase toleranceValueAsEforNumericEntryQuestionByQTIImport in class ToleranceForNumericEntryQuestion",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
