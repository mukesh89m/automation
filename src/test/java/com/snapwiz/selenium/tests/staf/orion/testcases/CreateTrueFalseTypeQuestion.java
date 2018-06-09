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
import com.snapwiz.selenium.tests.staf.orion.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;


public class CreateTrueFalseTypeQuestion {
	@Test
	public void createTrueFalseTypeQuestion()
	{
		try
		{
			Driver.startDriver();
			String course = Config.course;
			String questiontext = ReadTestData.readDataByTagName("CreateTrueFalseTypeQuestion", "questiontext", "1251");
			String newChapterName = ReadTestData.readDataByTagName("CreateTrueFalseTypeQuestion", "newChapterName", "1251");
			String typeOfPractice = ReadTestData.readDataByTagName("CreateTrueFalseTypeQuestion", "typeOfPractice", "1251");
			String diagassessmentname = ReadTestData.readDataByTagName("CreateTrueFalseTypeQuestion", "diagassessmentname", "1251");
			String questionset = ReadTestData.readDataByTagName("CreateTrueFalseTypeQuestion", "questionset", "1251");
			String tlo1idString = ReadTestData.readDataByTagName("chapternames", "tlo1id", "0");
			int tlo1id = Integer.parseInt(tlo1idString);
			new Assignment().createChapter(1251, tlo1id);
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
	/*		List<WebElement> elements = Driver.driver.findElements(By.className("collection-assessment-name"));
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
			Thread.sleep(2000);*/
			
			Driver.driver.findElement(By.cssSelector("div.create-practice")).click();
			 Driver.driver.findElement(By.id("assessmentName")).click();
			 Driver.driver.findElement(By.id("assessmentName")).clear();
			 if(typeOfPractice.equals("Adaptive Component Diagnostic"))
				 Driver.driver.findElement(By.id("assessmentName")).sendKeys(diagassessmentname);
			
			 Driver.driver.findElement(By.id("questionSetName")).clear();
			 Driver.driver.findElement(By.id("questionSetName")).sendKeys(questionset);
			
			Driver.driver.findElement(By.id("qtn-type-true-false-img")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("question-raw-content")).click();
		 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	Driver.driver.switchTo().defaultContent();
		 	Driver.driver.findElement(By.id("question-editor-outer-wrapper")).click();
		 	Thread.sleep(2000);
		 	//Setting choice 1 as correct answer
		 	Actions action = new Actions(Driver.driver);
	        WebElement we = Driver.driver.findElement(By.id("choice1"));
	        action.moveToElement(we).build().perform();		
	        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")));
		    Thread.sleep(2000);
		    //check for delet icon
		    int deleteIcon = Driver.driver.findElements(By.className("tree-node-delete-icon")).size();
		    if(deleteIcon > 1)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The delete icon is present for true false type question.");
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
				Assert.fail("We are not allowed to tag in true false type question.");
		    }
		    
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase createTrueFalseTypeQuestion in class CreateTrueFalseTypeQuestion ",e);
			
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
