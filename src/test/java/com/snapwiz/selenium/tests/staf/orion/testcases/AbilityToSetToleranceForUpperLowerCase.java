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

public class AbilityToSetToleranceForUpperLowerCase {
	@Test(priority = 1, enabled = true)
	public void abilityToSetToleranceForUpperLowerCase()
	{
		try
		{
			Driver.startDriver();	
			String course = Config.course;
			String questiontext = ReadTestData.readDataByTagName("AbilityToSetToleranceForUpperLowerCase", "questiontext", "1380");
			String newChapterName = ReadTestData.readDataByTagName("AbilityToSetToleranceForUpperLowerCase", "newChapterName", "1380");
			String typeOfPractice = ReadTestData.readDataByTagName("AbilityToSetToleranceForUpperLowerCase", "typeOfPractice", "1380");
			String diagassessmentname = ReadTestData.readDataByTagName("AbilityToSetToleranceForUpperLowerCase", "diagassessmentname", "1380");
			String questionset = ReadTestData.readDataByTagName("AbilityToSetToleranceForUpperLowerCase", "questionset", "1380");
			String answertext = "Snapwiz";
		
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			new Assignment().createChapter(1380, Integer.parseInt(tloids.get(0)));
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
		    int ingnoreCase = Driver.driver.findElements(By.id("ignore-text-entry-checkbox")).size();
		    if(ingnoreCase == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Ignore case check box is absent for text entry question.");
		    }
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
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("SNAPWIZ");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the preview page if we give answer in any case (uuper or lower) it doesn't show as correct answer when the ignore case checbox is checked by the author while creating the question.");
		 	}
		 	//enter another solution
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("SnaPWiZ");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str1 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
		 	if(!str1.equals("You got it right."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer in any case (uuper or lower) it doesn't show as correct answer when the ignore case checbox is checked by the author while creating the question.");
		 	}
		 	
		 	//Close the new window, if that window no more required
		 	Driver.driver.close();

		 	//Switch back to original browser (first window)

		 	Driver.driver.switchTo().window(winHandleBefore);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in abilityToSetToleranceForUpperLowerCase in class AbilityToSetToleranceForUpperLowerCase",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void checkWhenIgnoreCaseIsUnchecked()
	{
		try
		{
			Driver.startDriver();	
			String course = Config.course;
			String questiontext = ReadTestData.readDataByTagName("AbilityToSetToleranceForUpperLowerCase", "questiontext", "1385");
			String newChapterName = ReadTestData.readDataByTagName("AbilityToSetToleranceForUpperLowerCase", "newChapterName", "1385");
			String typeOfPractice = ReadTestData.readDataByTagName("AbilityToSetToleranceForUpperLowerCase", "typeOfPractice", "1385");
			String diagassessmentname = ReadTestData.readDataByTagName("AbilityToSetToleranceForUpperLowerCase", "diagassessmentname", "1385");
			String questionset = ReadTestData.readDataByTagName("AbilityToSetToleranceForUpperLowerCase", "questionset", "1385");
			String answertext = "Snapwiz";
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			new Assignment().createChapter(1385, Integer.parseInt(tloids.get(0)));
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
		    //((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElements(By.id("ignore-text-entry-checkbox")));
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
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("SNAPWIZ");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText();
		 	if(!str.equals("You got it wrong."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer in any case (upper or lower) it shows as correct answer when the ignore case checbox is unchecked by the author while creating the question.");
		 	}
		 	//enter another solution
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
		 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='textbox']")));
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys("SnaPWiZ");
		 	Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 	Thread.sleep(2000);
		 	String str1 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText();
		 	if(!str1.equals("You got it wrong."))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("In the preview page if we give answer in any case (upper or lower) it shows as correct answer when the ignore case checbox is unchecked by the author while creating the question.");
		 	}
		 	//Close the new window, if that window no more required
		 	Driver.driver.close();

		 	//Switch back to original browser (first window)

		 	Driver.driver.switchTo().window(winHandleBefore);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in checkWhenIgnoreCaseIsUnchecked in class AbilityToSetToleranceForUpperLowerCase",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
