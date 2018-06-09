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
import com.snapwiz.selenium.tests.staf.orion.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.uihelper.ComboBox;

public class CreateNumericEntryQuestion {
@Test(priority = 1, enabled = true)
	public void createNumericEntryQuestion()
	{
		try
		{
			Driver.startDriver();
			String course = Config.course;
			String questiontext = ReadTestData.readDataByTagName("CreateNumericEntryQuestion", "questiontext", "1257");
			String newChapterName = ReadTestData.readDataByTagName("CreateNumericEntryQuestion", "newChapterName", "1257");
			String typeOfPractice = ReadTestData.readDataByTagName("CreateNumericEntryQuestion", "typeOfPractice", "1257");
			String diagassessmentname = ReadTestData.readDataByTagName("CreateNumericEntryQuestion", "diagassessmentname", "1257");
			String questionset = ReadTestData.readDataByTagName("CreateNumericEntryQuestion", "questionset", "1257");
			String optiontext = ReadTestData.readDataByTagName("CreateNumericEntryQuestion", "optiontext", "1257");
			String numerictext = ReadTestData.readDataByTagName("CreateNumericEntryQuestion", "numerictext", "1257");
			
			String tlo1idString = ReadTestData.readDataByTagName("chapternames", "tlo1id", "0");
			int tlo1id = Integer.parseInt(tlo1idString);
			new Assignment().createChapter(1257, tlo1id);
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
		Driver.driver.findElement(By.id("qtn-text-entry-numeric-img")).click();
		Thread.sleep(2000);
		Driver.driver.findElement(By.id("question-raw-content")).click();
	 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
	 	Driver.driver.switchTo().defaultContent();
	 	
	 	// Adding correct answer choice
	 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
	 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
	    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(numerictext);	
	   
	    Thread.sleep(3000);
	 	// Rest two boxes are filled automatically.
	  //Adding alternate answer choice.
	    Driver.driver.findElement(By.id("right-alt-container-1")).click();
	    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
	 	Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
	    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);
	   /* // Adding tolerance
	    Driver.driver.findElement(By.id("tolerance-ans-text")).click();
	 	Driver.driver.findElement(By.id("tolerance-ans-text")).sendKeys(tolrence);
	 	Thread.sleep(2000);*/
	    Driver.driver.findElement(By.id("done-button")).click(); // Accept answer
	 	  Thread.sleep(2000);
	 	String tolerance = Driver.driver.findElement(By.id("tolerance-ans-text")).getAttribute("initial-data");
	 	//it will be null for default value
	 	if(tolerance.length()> 0)
	 	{
	 		new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Default tolerance for numerc entryy question is not 0.02");
	 	}
	 	
	 	//add tag
	    String text = new RandomString().randomstring(5);
	    Driver.driver.findElement(By.cssSelector("input[role='textbox']")).click();
	    Thread.sleep(2000);
	    Driver.driver.findElement(By.cssSelector("input[role='textbox']")).sendKeys(text);
	    Thread.sleep(2000);
	    Driver.driver.findElement(By.id("question-editor-outer-wrapper")).click();
	 	Thread.sleep(3000);
	 	//check the added tag
	 	String tag = Driver.driver.findElement(By.className("tagit-label")).getText();
	 	if(!tag.equals(text))
	    {
	    	new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("We are not allowed to tag in text drop down type question.");
	    }
	 	
	 	
	 	//default question text
	 	String defaultText = Driver.driver.findElement(By.id("question-raw-content")).getText();
	 	if(defaultText.contains("Enter Sample Question Text"))
	 	{
	 		new Screenshot().captureScreenshotFromTestCase();
	 		Assert.fail("Thed default question is not getting removed.");
	 	}
	 	//click question area
	 	Driver.driver.findElement(By.id("question-raw-content")).click();
	 	//check for text drop down block in tool bar
	 	int adddropdown = Driver.driver.findElements(By.cssSelector("img[id='adddropdown']")).size();
	 	if(adddropdown != 0)
	 	{
	 		new Screenshot().captureScreenshotFromTestCase();
	 		Assert.fail("Its showing the 'add drop down' block in the tool bar for text drop down question.");
	 	}
	 	new ComboBox().selectValue(4, "Publish");
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
	 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(optiontext);
	 	Thread.sleep(2000);
	 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
	 	Thread.sleep(2000);
	 	String str = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
	 	if(!str.equals("You got it right."))
	 	{
	 		new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("In the preview page if we give alternate answer data it doesn't show as correct answer");
	 	}
	 	//enter values with tolerance 
	 	int toleranceValue = Integer.parseInt(optiontext);
	 	double toleranceValue1 = toleranceValue+ 0.02;
	 	String toleranceValue2 = Double.toString(toleranceValue1) ;
	 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue2);
	 	String str1 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
	 	if(!str1.equals("You got it right."))
	 	{
	 		new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("In the preview page if we give alternate answer with tolerance it doesn't show as correct answer");
	 	}
	 	//Close the new window, if that window no more required
	 	Driver.driver.close();

	 	//Switch back to original browser (first window)

	 	Driver.driver.switchTo().window(winHandleBefore);
	 	
	 //	Driver.driver.findElement(By.id("display-write-board-checkbox")).click(); // Allow for using white board.
	}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase createNumericEntryQuestion in class CreateNumericEntryQuestion ",e);
		}
	}

@Test(priority = 2, enabled = true)
public void updateToleranceValueAndCheck()
{
	try
	{
		Driver.startDriver();
		String course = Config.course;
		String questiontext = ReadTestData.readDataByTagName("CreateNumericEntryQuestion", "questiontext", "1258");
		String newChapterName = ReadTestData.readDataByTagName("CreateNumericEntryQuestion", "newChapterName", "1258");
		String typeOfPractice = ReadTestData.readDataByTagName("CreateNumericEntryQuestion", "typeOfPractice", "1258");
		String diagassessmentname = ReadTestData.readDataByTagName("CreateNumericEntryQuestion", "diagassessmentname", "1258");
		String questionset = ReadTestData.readDataByTagName("CreateNumericEntryQuestion", "questionset", "1258");
		String optiontext = ReadTestData.readDataByTagName("CreateNumericEntryQuestion", "optiontext", "1258");
		String numerictext = ReadTestData.readDataByTagName("CreateNumericEntryQuestion", "numerictext", "1258");
		String tolrence = ReadTestData.readDataByTagName("CreateNumericEntryQuestion", "tolrence", "1258");
		String tlo1idString = ReadTestData.readDataByTagName("chapternames", "tlo1id", "0");
		int tlo1id = Integer.parseInt(tlo1idString);
		new Assignment().createChapter(1258, tlo1id);
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
	Driver.driver.findElement(By.id("qtn-text-entry-numeric-img")).click();
	Thread.sleep(2000);
	Driver.driver.findElement(By.id("question-raw-content")).click();
 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
 	Driver.driver.switchTo().defaultContent();
 	
 	// Adding correct answer choice
 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(numerictext);	
   
    Thread.sleep(3000);
 	// Rest two boxes are filled automatically.
  //Adding alternate answer choice.
    Driver.driver.findElement(By.id("right-alt-container-1")).click();
    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
 	Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);
    // Adding tolerance
    Driver.driver.findElement(By.id("tolerance-ans-text")).click();
 	Driver.driver.findElement(By.id("tolerance-ans-text")).sendKeys(tolrence);
 	Thread.sleep(2000);
    Driver.driver.findElement(By.id("done-button")).click(); // Accept answer
 	Thread.sleep(2000);
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
 	//enter values with tolerance with +2
 	int toleranceValue = Integer.parseInt(optiontext);
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
		Assert.fail("In the preview page if we give alternate answer data it doesn't show as correct answer");
 	}
 	//enter values with tolerance with +1
 	int toleranceValue3 = Integer.parseInt(optiontext);
 	double toleranceValue4 = toleranceValue3+ 1;
 	String toleranceValue5 = Double.toString(toleranceValue4) ;
 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue5);
 	Thread.sleep(2000);
 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
 	Thread.sleep(2000);
 	String str1 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
 	if(!str1.equals("You got it right."))
 	{
 		new Screenshot().captureScreenshotFromTestCase();
		Assert.fail("In the preview page if we give alternate answer data it doesn't show as correct answer");
 	}
 	//enter values with tolerance with -2
 	 	int toleranceValue6 = Integer.parseInt(optiontext);
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
 			Assert.fail("In the preview page if we give alternate answer data it doesn't show as correct answer");
 	 	}
 	 //enter values with tolerance with -1
 	 	int toleranceValue9 = Integer.parseInt(optiontext);
 	 	double toleranceValue10 = toleranceValue9 - 1;
 	 	String toleranceValue11 = Double.toString(toleranceValue10) ;
 	 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).clear();
 	 	Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(toleranceValue11);
 	 	Thread.sleep(2000);
 	 	Driver.driver.findElement(By.id("question-reveview-submit")).click();
 	 	Thread.sleep(2000);
 	 	String str3 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText();
 	 	if(!str3.equals("You got it right."))
 	 	{
 	 		new Screenshot().captureScreenshotFromTestCase();
 			Assert.fail("In the preview page if we give alternate answer data it doesn't show as correct answer");
 	 	}
 	//Close the new window, if that window no more required
 	Driver.driver.close();

 	//Switch back to original browser (first window)

 	Driver.driver.switchTo().window(winHandleBefore);
 	
}
	catch(Exception e)
	{
		new Screenshot().captureScreenshotFromTestCase();
		Assert.fail("Exception in testcase updateToleranceValueAndCheck in class CreateNumericEntryQuestion ",e);
	}
}
@AfterMethod
public void tearDown() throws Exception
{
	Driver.driver.quit();
}
}
