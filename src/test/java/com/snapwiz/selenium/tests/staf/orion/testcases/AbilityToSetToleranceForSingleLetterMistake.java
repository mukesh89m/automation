package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class AbilityToSetToleranceForSingleLetterMistake {
	@Test(priority = 1, enabled = true)
	public void abilityToSetToleranceForSingleLetterMistake()
	{
		try
		{
			Driver.startDriver();	
			String course = Config.course;
			String questiontext = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "questiontext", "1388");
			String newChapterName = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "newChapterName", "1388");
			String typeOfPractice = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "typeOfPractice", "1388");
			String diagassessmentname = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "diagassessmentname", "1388");
			String questionset = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "questionset", "1388");
			String answertext = "answer";
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			new Assignment().createChapter(1388, Integer.parseInt(tloids.get(0)));
			new DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
			if(title.equals("Course Content Details"))
				{
					Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
					if(newChapterName == null)
						{
							Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
						}
					else
					 {
						 List <WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
						 for(WebElement chapters: allChapters)
						 {
							 if(chapters.getText().contains(newChapterName))
							 {
								 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters); 
								 Thread.sleep(3000);
								 break;
							 }
							 
						 }
						 
					 }
				}
			Driver.driver.findElement(By.cssSelector("div.create-practice")).click();
			Driver.driver.findElement(By.id("assessmentName")).click();
			Driver.driver.findElement(By.id("assessmentName")).clear();
			if(typeOfPractice.equals("Adaptive Component Diagnostic"))
				 Driver.driver.findElement(By.id("assessmentName")).sendKeys(diagassessmentname);
			
			Driver.driver.findElement(By.id("questionSetName")).clear();
			Driver.driver.findElement(By.id("questionSetName")).sendKeys(questionset);
			
			Driver.driver.findElement(By.id("qtn-text-entry-img")).click();
			
			Driver.driver.findElement(By.id("question-raw-content")).click();
		 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	Driver.driver.switchTo().defaultContent();
		 	// Adding correct answer choice
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
		    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(answertext);	
		    Thread.sleep(3000);
			// Rest two boxes are filled automatically.
		    int ingnoreCase = Driver.driver.findElements(By.id("allow-single-letter-mistake-entry-checkbox")).size();
		    if(ingnoreCase == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("allow single letter mistake entry checkbox is absent for text entry question.");
		    }
		    //uncheck ignore case checkbox
		    Driver.driver.findElement(By.id("ignore-text-entry-checkbox")).click();
		    Thread.sleep(2000);
		    //check the allow-single-letter-mistake-entry-checkbox
		    Driver.driver.findElement(By.id("allow-single-letter-mistake-entry-checkbox")).click();
		    Thread.sleep(2000);
		   
		    Driver.driver.findElement(By.id("done-button")).click(); // Accept answer
		    
		 	Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
		 	Thread.sleep(2000);	
		 	//Store the current window handle
		 	String winHandleBefore = Driver.driver.getWindowHandle();

		 	//Perform the click operation that opens new window
		 	Driver.driver.findElement(By.id("preview-the-image-quiz")).click();//click on preview
		 	Thread.sleep(2000);
		 	//Switch to new window opened
		 	for(String winHandle : Driver.driver.getWindowHandles()){
		 		Driver.driver.switchTo().window(winHandle);
		 	}

		 	// Perform the actions on new window
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	//enter answer with one letter deleted
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("answe");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake it doesn't show as correct answer when the Allow single letter mistake checbox is checked by the author while creating the question.");
		 	}
		 	//enter another solution with one extra letter
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("answerd");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str1 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str1.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake it doesn't show as correct answer when the Allow single letter mistake checbox is checked by the author while creating the question.");
		 	}
		 	//enter another solution with one extra letter in middle
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("ansdwer");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str2 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str2.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake it doesn't show as correct answer when the Allow single letter mistake checbox is checked by the author while creating the question.");
		 	}
		 	
		 	//enter another solution delete letter in middle
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("anwer");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str3 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str3.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake it doesn't show as correct answer when the Allow single letter mistake checbox is checked by the author while creating the question.");
		 	}
		 	//enter another solution with appending a special character
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("answer,");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str4 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str4.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake it doesn't show as correct answer when the Allow single letter mistake checbox is checked by the author while creating the question.");
		 	}
		 	
		 	//Close the new window, if that window no more required
		 	Driver.driver.close();

		 	//Switch back to original browser (first window)

		 	Driver.driver.switchTo().window(winHandleBefore);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in abilityToSetToleranceForSingleLetterMistake in class AbilityToSetToleranceForSingleLetterMistake",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void checkWhenSingleLetterMistakeIsUnchecked()
	{
		try
		{
			Driver.startDriver();	
			String course = Config.course;
			String questiontext = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "questiontext", "1393");
			String newChapterName = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "newChapterName", "1393");
			String typeOfPractice = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "typeOfPractice", "1393");
			String diagassessmentname = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "diagassessmentname", "1393");
			String questionset = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "questionset", "1393");
			String answertext = "answer";
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			new Assignment().createChapter(1393, Integer.parseInt(tloids.get(0)));
			new DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
			if(title.equals("Course Content Details"))
				{
					Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
					if(newChapterName == null)
						{
							Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
						}
					else
					 {
						 List <WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
						 for(WebElement chapters: allChapters)
						 {
							 if(chapters.getText().contains(newChapterName))
							 {
								 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters); 
								 Thread.sleep(3000);
								 break;
							 }
							 
						 }
						 
					 }
				}
			Driver.driver.findElement(By.cssSelector("div.create-practice")).click();
			Driver.driver.findElement(By.id("assessmentName")).click();
			Driver.driver.findElement(By.id("assessmentName")).clear();
			if(typeOfPractice.equals("Adaptive Component Diagnostic"))
				 Driver.driver.findElement(By.id("assessmentName")).sendKeys(diagassessmentname);
			
			Driver.driver.findElement(By.id("questionSetName")).clear();
			Driver.driver.findElement(By.id("questionSetName")).sendKeys(questionset);
			
			Driver.driver.findElement(By.id("qtn-text-entry-img")).click();
			
			Driver.driver.findElement(By.id("question-raw-content")).click();
		 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	Driver.driver.switchTo().defaultContent();
		 	// Adding correct answer choice
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
		    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(answertext);	
		    Thread.sleep(3000);
		    //uncheck ignore case checkbox
		    Driver.driver.findElement(By.id("ignore-text-entry-checkbox")).click();
		    Thread.sleep(2000);
		    int ingnoreCase = Driver.driver.findElements(By.id("ignore-text-entry-checkbox")).size();
		    if(ingnoreCase == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Ignore case check box is absent for text entry question.");
		    }
		    Driver.driver.findElement(By.id("done-button")).click(); // Accept answer
		    
		   // new ComboBox().selectValue(4, "Publish");
		 	Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
		 	Thread.sleep(2000);	
		 	//Store the current window handle
		 	String winHandleBefore = Driver.driver.getWindowHandle();

		 	//Perform the click operation that opens new window
		 	Driver.driver.findElement(By.id("preview-the-image-quiz")).click();//click on preview
		 	Thread.sleep(2000);
		 	//Switch to new window opened
		 	for(String winHandle : Driver.driver.getWindowHandles()){
		 		Driver.driver.switchTo().window(winHandle);
		 	}
		 	
		 // Perform the actions on new window
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	//enter answer with one letter deleted
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("answe");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText();
		 	if(!str.equals("You got it wrong."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake it show as correct answer when the Allow single letter mistake checbox is unchecked by the author while creating the question.");
		 	}
		 	//enter another solution with one extra letter
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("answerd");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str1 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText();
		 	if(!str1.equals("You got it wrong."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake it show as correct answer when the Allow single letter mistake checbox is unchecked by the author while creating the question.");
		 	}
		 	//enter another solution with one extra letter in middle
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("ansdwer");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str2 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText();
		 	if(!str2.equals("You got it wrong."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake it show as correct answer when the Allow single letter mistake checbox is unchecked by the author while creating the question.");
		 	}
		 	
		 	//enter another solution delete letter in middle
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("anwer");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str3 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText();
		 	if(!str3.equals("You got it wrong."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake it show as correct answer when the Allow single letter mistake checbox is unchecked by the author while creating the question.");
		 	}
		 	//enter another solution with appending a special character
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("answer,");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str4 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText();
		 	if(!str4.equals("You got it wrong."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake it show as correct answer when the Allow single letter mistake checbox is unchecked by the author while creating the question.");
		 	}
		 	//Close the new window, if that window no more required
		 	Driver.driver.close();

		 	//Switch back to original browser (first window)

		 	Driver.driver.switchTo().window(winHandleBefore);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in checkWhenSingleLetterMistakeIsUnchecked in class AbilityToSetToleranceForSingleLetterMistake",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void checkWhenBothIgnoreCaseAndSingleLetterMistakeAreChecked()
	{
		try
		{
			Driver.startDriver();	
			String course = Config.course;
			String questiontext = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "questiontext", "1396");
			String newChapterName = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "newChapterName", "1396");
			String typeOfPractice = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "typeOfPractice", "1396");
			String diagassessmentname = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "diagassessmentname", "1396");
			String questionset = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "questionset", "1396");
			String answertext = "answer";
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			new Assignment().createChapter(1396, Integer.parseInt(tloids.get(0)));
			new DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
			if(title.equals("Course Content Details"))
				{
					Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
					if(newChapterName == null)
						{
							Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
						}
					else
					 {
						 List <WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
						 for(WebElement chapters: allChapters)
						 {
							 if(chapters.getText().contains(newChapterName))
							 {
								 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters); 
								 Thread.sleep(3000);
								 break;
							 }
							 
						 }
						 
					 }
				}
			Driver.driver.findElement(By.cssSelector("div.create-practice")).click();
			Driver.driver.findElement(By.id("assessmentName")).click();
			Driver.driver.findElement(By.id("assessmentName")).clear();
			if(typeOfPractice.equals("Adaptive Component Diagnostic"))
				 Driver.driver.findElement(By.id("assessmentName")).sendKeys(diagassessmentname);
			
			Driver.driver.findElement(By.id("questionSetName")).clear();
			Driver.driver.findElement(By.id("questionSetName")).sendKeys(questionset);
			
			Driver.driver.findElement(By.id("qtn-text-entry-img")).click();
			
			Driver.driver.findElement(By.id("question-raw-content")).click();
		 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	Driver.driver.switchTo().defaultContent();
		 	// Adding correct answer choice
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
		    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(answertext);	
		    Thread.sleep(3000);
			// Rest two boxes are filled automatically.
		    int ingnoreCase = Driver.driver.findElements(By.id("allow-single-letter-mistake-entry-checkbox")).size();
		    if(ingnoreCase == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("allow single letter mistake entry checkbox is absent for text entry question.");
		    }
		    //uncheck ignore case checkbox
		   // Driver.driver.findElement(By.id("ignore-text-entry-checkbox")).click();
		    Thread.sleep(2000);
		    //check the allow-single-letter-mistake-entry-checkbox
		    Driver.driver.findElement(By.id("allow-single-letter-mistake-entry-checkbox")).click();
		    Thread.sleep(2000);
		   
		    Driver.driver.findElement(By.id("done-button")).click(); // Accept answer
		    
		 	Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
		 	Thread.sleep(2000);	
		 	//Store the current window handle
		 	String winHandleBefore = Driver.driver.getWindowHandle();

		 	//Perform the click operation that opens new window
		 	Driver.driver.findElement(By.id("preview-the-image-quiz")).click();//click on preview
		 	Thread.sleep(2000);
		 	//Switch to new window opened
		 	for(String winHandle : Driver.driver.getWindowHandles()){
		 		Driver.driver.switchTo().window(winHandle);
		 	}

		 	// Perform the actions on new window
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	//1. User has given the word with all letters caps or few letter caps and removed a single letter
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("ANSWE");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with lower or upper case it doesn't show as correct answer when the Allow single letter mistake checbox is unchecked and Ignore Case is checked by the author while creating the question.");
		 	}
		 	//2. User has given the word in any case and replaced a single letter
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("ANSweQ");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str1 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str1.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with lower or upper case it doesn't show as correct answer when the Allow single letter mistake checbox is unchecked and Ignore Case is checked by the author while creating the question.");
		 	}
		 	//3. User has given the word in any case and added a new letter anywhere between the word
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("answekr");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str2 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str2.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with lower or upper case it doesn't show as correct answer when the Allow single letter mistake checbox is unchecked and Ignore Case is checked by the author while creating the question.");
		 	}
		 	
		 	//enter solution and replace a single letter
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("ANSWAR");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str3 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str3.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with lower or upper case it doesn't show as correct answer when the Allow single letter mistake checbox is unchecked and Ignore Case is checked by the author while creating the question.");
		 	}
		 	//enter another solution with appending a special character
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("aNSwer,");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str4 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str4.equals("You got it Right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake, with lower or upper case it doesn't show as correct answer when the Allow single letter mistake checbox and Ignore Case are checked by the author while creating the question.");
		 	}
		 	//4. User has given the word in any case and added a new single letter and replaced a single letter or interchanged a single letter
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("ancwerl");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str5 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText();
		 	if(!str5.equals("You got it Wrong."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake, with lower or upper case it doesn't show as correct answer when the Allow single letter mistake checbox and Ignore Case are checked by the author while creating the question.");
		 	}
		 	//Close the new window, if that window no more required
		 	Driver.driver.close();

		 	//Switch back to original browser (first window)

		 	Driver.driver.switchTo().window(winHandleBefore);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in checkWhenBothIgnoreCaseAndSingleLetterMistakeAreChecked in class AbilityToSetToleranceForSingleLetterMistake",e);
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void  checkWhenIgnoreCaseIsCheckedAndAllowSingleLetterMistakeUnchecked()
	{
		try
		{
			Driver.startDriver();	
			String course = Config.course;
			String questiontext = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "questiontext", "1403");
			String newChapterName = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "newChapterName", "1403");
			String typeOfPractice = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "typeOfPractice", "1403");
			String diagassessmentname = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "diagassessmentname", "1403");
			String questionset = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "questionset", "1403");
			String answertext = "answer";
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			new Assignment().createChapter(1403, Integer.parseInt(tloids.get(0)));
			new DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
			if(title.equals("Course Content Details"))
				{
					Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
					if(newChapterName == null)
						{
							Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
						}
					else
					 {
						 List <WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
						 for(WebElement chapters: allChapters)
						 {
							 if(chapters.getText().contains(newChapterName))
							 {
								 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters); 
								 Thread.sleep(3000);
								 break;
							 }
							 
						 }
						 
					 }
				}
			Driver.driver.findElement(By.cssSelector("div.create-practice")).click();
			Driver.driver.findElement(By.id("assessmentName")).click();
			Driver.driver.findElement(By.id("assessmentName")).clear();
			if(typeOfPractice.equals("Adaptive Component Diagnostic"))
				 Driver.driver.findElement(By.id("assessmentName")).sendKeys(diagassessmentname);
			
			Driver.driver.findElement(By.id("questionSetName")).clear();
			Driver.driver.findElement(By.id("questionSetName")).sendKeys(questionset);
			
			Driver.driver.findElement(By.id("qtn-text-entry-img")).click();
			
			Driver.driver.findElement(By.id("question-raw-content")).click();
		 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	Driver.driver.switchTo().defaultContent();
		 	// Adding correct answer choice
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
		    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(answertext);	
		    Thread.sleep(3000);
			// Rest two boxes are filled automatically.
		    int ingnoreCase = Driver.driver.findElements(By.id("allow-single-letter-mistake-entry-checkbox")).size();
		    if(ingnoreCase == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("allow single letter mistake entry checkbox is absent for text entry question.");
		    }
		   //uncheck ignore case checkbox
		   // Driver.driver.findElement(By.id("ignore-text-entry-checkbox")).click();
		    Thread.sleep(2000);
		    //check the allow-single-letter-mistake-entry-checkbox
		   // Driver.driver.findElement(By.id("allow-single-letter-mistake-entry-checkbox")).click();
		    Thread.sleep(2000);
		   
		    Driver.driver.findElement(By.id("done-button")).click(); // Accept answer
		    
		 	Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
		 	Thread.sleep(2000);	
		 	//Store the current window handle
		 	String winHandleBefore = Driver.driver.getWindowHandle();

		 	//Perform the click operation that opens new window
		 	Driver.driver.findElement(By.id("preview-the-image-quiz")).click();//click on preview
		 	Thread.sleep(2000);
		 	//Switch to new window opened
		 	for(String winHandle : Driver.driver.getWindowHandles()){
		 		Driver.driver.switchTo().window(winHandle);
		 	}

		 	// Perform the actions on new window
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	//enter answer with one letter deleted 
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("ANSWER");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake, with lower or upper case it doesn't show as correct answer when the Allow single letter mistake checbox and Ignore Case are checked by the author while creating the question.");
		 	}
		 	//enter another solution with one extra letter
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("ANSwerD");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str1 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText();
		 	if(!str1.equals("You got it wrong."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake, with lower or upper case it doesn't show as correct answer when the Allow single letter mistake checbox and Ignore Case are checked by the author while creating the question.");
		 	}
		 	//enter another solution with one extra letter in middle
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("answr");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str2 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText();
		 	if(!str2.equals("You got it wrong."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake, with lower or upper case it doesn't show as correct answer when the Allow single letter mistake checbox and Ignore Case are checked by the author while creating the question.");
		 	}
		 	
		 	//enter another solution delete letter in middle
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("ANSWERk");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str3 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText();
		 	if(!str3.equals("You got it wrong."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake, with lower or upper case it doesn't show as correct answer when the Allow single letter mistake checbox and Ignore Case are checked by the author while creating the question.");
		 	}
		 	//enter another solution with appending a special character
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("aNSwer,");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str4 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText();
		 	if(!str4.equals("You got it wrong."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake, with lower or upper case it doesn't show as correct answer when the Allow single letter mistake checbox and Ignore Case are checked by the author while creating the question.");
		 	}
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("ANSWAR");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str5 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText();
		 	if(!str5.equals("You got it wrong."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake, with lower or upper case it doesn't show as correct answer when the Allow single letter mistake checbox and Ignore Case are checked by the author while creating the question.");
		 	}
		 	//Close the new window, if that window no more required
		 	Driver.driver.close();

		 	//Switch back to original browser (first window)

		 	Driver.driver.switchTo().window(winHandleBefore);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in checkWhenIgnoreCaseIsCheckedAndAllowSingleLetterMistakeUnchecked in class AbilityToSetToleranceForSingleLetterMistake",e);
		}
	}
	@Test(priority = 5, enabled = true)
	public void  checkWhenIgnoreCaseIsUncheckedAndAllowSingleLetterMistakeChecked()
	{
		try
		{
			Driver.startDriver();	
			String course = Config.course;
			String questiontext = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "questiontext", "1407");
			String newChapterName = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "newChapterName", "1407");
			String typeOfPractice = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "typeOfPractice", "1407");
			String diagassessmentname = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "diagassessmentname", "1407");
			String questionset = ReadTestData.readDataByTagName("AbilityToSetToleranceForSingleLetterMistake", "questionset", "1407");
			String answertext = "answer";
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			new Assignment().createChapter(1407, Integer.parseInt(tloids.get(0)));
			new DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
			if(title.equals("Course Content Details"))
				{
					Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
					if(newChapterName == null)
						{
							Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
						}
					else
					 {
						 List <WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
						 for(WebElement chapters: allChapters)
						 {
							 if(chapters.getText().contains(newChapterName))
							 {
								 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters); 
								 Thread.sleep(3000);
								 break;
							 }
							 
						 }
						 
					 }
				}
			Driver.driver.findElement(By.cssSelector("div.create-practice")).click();
			Driver.driver.findElement(By.id("assessmentName")).click();
			Driver.driver.findElement(By.id("assessmentName")).clear();
			if(typeOfPractice.equals("Adaptive Component Diagnostic"))
				 Driver.driver.findElement(By.id("assessmentName")).sendKeys(diagassessmentname);
			
			Driver.driver.findElement(By.id("questionSetName")).clear();
			Driver.driver.findElement(By.id("questionSetName")).sendKeys(questionset);
			
			Driver.driver.findElement(By.id("qtn-text-entry-img")).click();
			
			Driver.driver.findElement(By.id("question-raw-content")).click();
		 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	Driver.driver.switchTo().defaultContent();
		 	// Adding correct answer choice
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
		    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(answertext);	
		    Thread.sleep(3000);
			// Rest two boxes are filled automatically.
		    int ingnoreCase = Driver.driver.findElements(By.id("allow-single-letter-mistake-entry-checkbox")).size();
		    if(ingnoreCase == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("allow single letter mistake entry checkbox is absent for text entry question.");
		    }
		   //uncheck ignore case checkbox
		    Driver.driver.findElement(By.id("ignore-text-entry-checkbox")).click();
		    Thread.sleep(2000);
		    //check the allow-single-letter-mistake-entry-checkbox
		    Driver.driver.findElement(By.id("allow-single-letter-mistake-entry-checkbox")).click();
		    Thread.sleep(2000);
		   
		    Driver.driver.findElement(By.id("done-button")).click(); // Accept answer
		    
		 	Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
		 	Thread.sleep(2000);	
		 	//Store the current window handle
		 	String winHandleBefore = Driver.driver.getWindowHandle();

		 	//Perform the click operation that opens new window
		 	Driver.driver.findElement(By.id("preview-the-image-quiz")).click();//click on preview
		 	Thread.sleep(2000);
		 	//Switch to new window opened
		 	for(String winHandle : Driver.driver.getWindowHandles()){
		 		Driver.driver.switchTo().window(winHandle);
		 	}

		 	// Perform the actions on new window
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	//enter answer with one letter added 
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("answerl");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake, with lower or upper case it doesn't show as correct answer when the Allow single letter mistake checbox and Ignore Case are checked by the author while creating the question.");
		 	}
		 	//enter another solution with one deleted
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("answe");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str1 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str1.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake, with lower or upper case it doesn't show as correct answer when the Allow single letter mistake checbox and Ignore Case are checked by the author while creating the question.");
		 	}
		 	//enter another solution with one letter interchanged
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("anwser");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str2 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText();
		 	if(!str2.equals("You got it wrong."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake, with lower or upper case it doesn't show as correct answer when the Allow single letter mistake checbox and Ignore Case are checked by the author while creating the question.");
		 	}
		 	
		 	//2 added a new single character anywhere inbetween the word with the letter in caps or a number
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("ansdwer");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str3 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str3.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer with one letter mistake, with lower or upper case it doesn't show as correct answer when the Allow single letter mistake checbox and Ignore Case are checked by the author while creating the question.");
		 	}
		 	
		 	//Close the new window, if that window no more required
		 	Driver.driver.close();

		 	//Switch back to original browser (first window)

		 	Driver.driver.switchTo().window(winHandleBefore);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in checkWhenIgnoreCaseIsCheckedAndAllowSingleLetterMistakeUnchecked in class AbilityToSetToleranceForSingleLetterMistake",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
