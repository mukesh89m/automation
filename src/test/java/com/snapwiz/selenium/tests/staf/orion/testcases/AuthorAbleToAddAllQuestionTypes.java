package com.snapwiz.selenium.tests.staf.orion.testcases;

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
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class AuthorAbleToAddAllQuestionTypes {
	@Test(priority = 1, enabled = true)
	public void authorAbleToAddAllQuestionTypes()
	{
		try
		{
			Driver.startDriver();
			String course = Config.course;
			String questiontext = ReadTestData.readDataByTagName("AuthorAbleToAddAllQuestionTypes", "questiontext", "1246");
			String chapterName = ReadTestData.readDataByTagName("AuthorAbleToAddAllQuestionTypes", "chapterName", "1246");
			String answertext = ReadTestData.readDataByTagName("AuthorAbleToAddAllQuestionTypes", "answertext", "1246");
			String optiontext = ReadTestData.readDataByTagName("AuthorAbleToAddAllQuestionTypes", "optiontext", "1246");
			new DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
			if(title.equals("Course Content Details"))
				{
					Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
					if(chapterName == null)
						{
							Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
						}
					else
					 {
						 List <WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
						 for(WebElement chapters: allChapters)
						 {
							 if(chapters.getText().contains(chapterName))
							 {
								 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters); 
								 Thread.sleep(3000);
								 break;
							 }
							 
						 }
						 
					 }
				}
			
			List<WebElement> elements = Driver.driver.findElements(By.className("collection-assessment-name"));
			for(WebElement content : elements)
				{
		
					if(content.getText().trim().equals("Diagnostic - Introduction to Financial..."))
						{
							
							((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", content); 
							Thread.sleep(3000);
							break;
						}
				}
			
			Driver.driver.findElement(By.id("questionOptions")).click();
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("addQuestion")));
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("qtn-text-entry-img")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("question-raw-content")).click();
		 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	Driver.driver.switchTo().defaultContent();
		 	// Adding correct answer choice
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
		    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(answertext);	
		    Thread.sleep(3000);
			// Rest two boxes are filled automatically.
		    //Adding alternate answer choice.
		    Driver.driver.findElement(By.id("right-alt-container-1")).click();
		    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
		 	Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
		    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);
		    boolean altAns = Driver.driver.findElement(By.cssSelector("div[class='alternate-answer-wrapper-1 text-and-label-wrapper']")).isDisplayed();
		    if(altAns == false)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking + symbol near Correct Answer the text block to enter alternate answer doesn't appear.");
		    }
		    Driver.driver.findElement(By.id("done-button")).click(); // Accept answer
		    Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
		 	Thread.sleep(2000);	
		 	String correctAnswer = Driver.driver.findElement(By.cssSelector("input[type='textbox']")).getAttribute("renderdata");
		 	if(!correctAnswer.contains(answertext) || !correctAnswer.contains(optiontext))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("For text entry type question if we provide any alternate the correct answer doesn't get saved.");
		 	}
		 		 	
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
		 	//Close the new window, if that window no more required
		 	Driver.driver.close();

		 	//Switch back to original browser (first window)

		 	Driver.driver.switchTo().window(winHandleBefore);
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase authorAbleToAddAllQuestionTypes in class AuthorAbleToAddAllQuestionTypes ",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void textEntryTypeQuestionWithoutAlternateSolution()
	{
		try
		{
			Driver.startDriver();
			String course = Config.course;
			String questiontext = ReadTestData.readDataByTagName("AuthorAbleToAddAllQuestionTypes", "questiontext", "1247");
			String chapterName = ReadTestData.readDataByTagName("AuthorAbleToAddAllQuestionTypes", "chapterName", "1247");
			String answertext = ReadTestData.readDataByTagName("AuthorAbleToAddAllQuestionTypes", "answertext", "1247");
			new DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
			if(title.equals("Course Content Details"))
				{
					Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
					if(chapterName == null)
						{
							Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
						}
					else
					 {
						 List <WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
						 for(WebElement chapters: allChapters)
						 {
							 if(chapters.getText().contains(chapterName))
							 {
								 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters); 
								 Thread.sleep(3000);
								 break;
							 }
							 
						 }
						 
					 }
				}
			
			List<WebElement> elements = Driver.driver.findElements(By.className("collection-assessment-name"));
			for(WebElement content : elements)
				{
		
					if(content.getText().trim().equals("Diagnostic - Introduction to Financial..."))
						{
							
							((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", content); 
							Thread.sleep(3000);
							break;
						}
				}
			
			Driver.driver.findElement(By.id("questionOptions")).click();
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("addQuestion")));
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("qtn-text-entry-img")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("question-raw-content")).click();
		 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	Driver.driver.switchTo().defaultContent();
		 	// Adding correct answer choice
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
		    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(answertext);	
		    Thread.sleep(3000);
			// Rest two boxes are filled automatically.
		    //Adding alternate answer choice.
		    Driver.driver.findElement(By.id("right-alt-container-1")).click();
		    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
		    boolean altAns = Driver.driver.findElement(By.cssSelector("div[class='alternate-answer-wrapper-1 text-and-label-wrapper']")).isDisplayed();
		    if(altAns == false)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking + symbol near Correct Answer the text block to enter alternate answer doesn't appear.");
		    }
		    Driver.driver.findElement(By.id("done-button")).click(); // Accept answer
		    Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
		 	Thread.sleep(2000);	
		 	String correctAnswer = Driver.driver.findElement(By.cssSelector("input[type='textbox']")).getAttribute("renderdata");
		 	if(!correctAnswer.contains(answertext))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("For text entry type question if we do not provide any alternate the correct answer doesn't get saved.");
		 	}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase authorAbleToAddAllQuestionTypes in class AuthorAbleToAddAllQuestionTypes ",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void textEntryTypeQuestionWithoutCorrectSolution()
	{
		try
		{
			Driver.startDriver();
			String course = Config.course;
			String questiontext = ReadTestData.readDataByTagName("AuthorAbleToAddAllQuestionTypes", "questiontext", "1248");
			String chapterName = ReadTestData.readDataByTagName("AuthorAbleToAddAllQuestionTypes", "chapterName", "1248");
			new DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
			if(title.equals("Course Content Details"))
				{
					Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
					if(chapterName == null)
						{
							Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
						}
					else
					 {
						 List <WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
						 for(WebElement chapters: allChapters)
						 {
							 if(chapters.getText().contains(chapterName))
							 {
								 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters); 
								 Thread.sleep(3000);
								 break;
							 }
							 
						 }
						 
					 }
				}
			
			List<WebElement> elements = Driver.driver.findElements(By.className("collection-assessment-name"));
			for(WebElement content : elements)
				{
		
					if(content.getText().trim().equals("Diagnostic - Introduction to Financial..."))
						{
							
							((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", content); 
							Thread.sleep(3000);
							break;
						}
				}
			
			Driver.driver.findElement(By.id("questionOptions")).click();
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("addQuestion")));
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("qtn-text-entry-img")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("question-raw-content")).click();
		 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	Driver.driver.switchTo().defaultContent();
		 	// Adding correct answer choice
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
		    Thread.sleep(3000);
		    Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
		 	Thread.sleep(2000);	
		 	String correctAnswer = Driver.driver.findElement(By.cssSelector("input[type='textbox']")).getAttribute("renderdata");
		 	if(correctAnswer.contains("renderdata"))
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("For text entry type question if we do not provide the correct answer doesn't get saved.");
		 	}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase authorAbleToAddAllQuestionTypes in class AuthorAbleToAddAllQuestionTypes ",e);
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void textEntryTypeQuestion()
	{
		try
		{
			Driver.startDriver();
			String course = Config.course;
			String chapterName = ReadTestData.readDataByTagName("AuthorAbleToAddAllQuestionTypes", "chapterName", "1249");
			new DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
			if(title.equals("Course Content Details"))
				{
					Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
					if(chapterName == null)
						{
							Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
						}
					else
					 {
						 List <WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
						 for(WebElement chapters: allChapters)
						 {
							 if(chapters.getText().contains(chapterName))
							 {
								 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters); 
								 Thread.sleep(3000);
								 break;
							 }
							 
						 }
						 
					 }
				}
			
			List<WebElement> elements = Driver.driver.findElements(By.className("collection-assessment-name"));
			for(WebElement content : elements)
				{
		
					if(content.getText().trim().equals("Diagnostic - Introduction to Financial..."))
						{
							
							((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", content); 
							Thread.sleep(3000);
							break;
						}
				}
			
			Driver.driver.findElement(By.id("questionOptions")).click();
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("addQuestion")));
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("qtn-text-entry-img")).click();
			Thread.sleep(2000);
			Actions action = new Actions(Driver.driver);
			action.doubleClick(Driver.driver.findElement(By.id("question-raw-content")));
			action.perform();
			Thread.sleep(2000);
			String defaultQuestion1 = Driver.driver.findElement(By.id("question-raw-content")).getText();
			if(defaultQuestion1.length()>0)
			{
				new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("After clicking question textbox the default text doesn't get removed.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase authorAbleToAddAllQuestionTypes in class AuthorAbleToAddAllQuestionTypes ",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
