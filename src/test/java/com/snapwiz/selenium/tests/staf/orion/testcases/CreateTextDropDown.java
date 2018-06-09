package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
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

public class CreateTextDropDown {
@Test(priority = 1, enabled = true)
	public void createTextDropDown()
	{
		try
		{
			Driver.startDriver();
			String course = Config.course;
			String questiontext = ReadTestData.readDataByTagName("CreateTextDropDown", "questiontext", "1254");
			String newChapterName = ReadTestData.readDataByTagName("CreateTextDropDown", "newChapterName", "1254");
			String typeOfPractice = ReadTestData.readDataByTagName("CreateTextDropDown", "typeOfPractice", "1254");
			String diagassessmentname = ReadTestData.readDataByTagName("CreateTextDropDown", "diagassessmentname", "1254");
			String questionset = ReadTestData.readDataByTagName("CreateTextDropDown", "questionset", "1254");
			String optiontext = ReadTestData.readDataByTagName("CreateTextDropDown", "optiontext", "1254");
			String tlo1idString = ReadTestData.readDataByTagName("chapternames", "tlo1id", "0");
			int tlo1id = Integer.parseInt(tlo1idString);
			new Assignment().createChapter(1254, tlo1id);
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
			 
			Driver.driver.findElement(By.id("qtn-text-entry-drop-down-img")).click();
				Thread.sleep(2000);
			Driver.driver.findElement(By.id("question-raw-content")).click();
			Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
			Driver.driver.switchTo().defaultContent();
			 	// Adding Entry 1
			Driver.driver.findElement(By.id("entry-0")).click();
			Driver.driver.findElement(By.id("unit-name-edit-entry-0")).clear();
			Driver.driver.findElement(By.id("unit-name-edit-entry-0")).sendKeys(optiontext);
			 	
			// Accepting answer
			WebElement menuitem = Driver.driver.findElement(By.id("entry-1")); 
	        Locatable hoverItem = (Locatable) menuitem;
	        Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
	        mouse.mouseMove(hoverItem.getCoordinates());
	        List<WebElement> selectanswerticks = Driver.driver.findElements(By.className("mark-selected"));        	
	        selectanswerticks.get(1).click(); //select second option as correct answer
			Thread.sleep(2000);
			   // Adding Entry 2
			Actions action = new Actions(Driver.driver);
			action.doubleClick(Driver.driver.findElement(By.id("entry-1")));
			action.perform();
			Driver.driver.findElement(By.id("unit-name-edit-entry-1")).clear();
			Driver.driver.findElement(By.id("unit-name-edit-entry-1")).sendKeys(optiontext);
			  // Adding Entry 3
			action.doubleClick(Driver.driver.findElement(By.id("entry-2")));
			action.perform();
			Driver.driver.findElement(By.id("unit-name-edit-entry-2")).clear();
			Driver.driver.findElement(By.id("unit-name-edit-entry-2")).sendKeys(optiontext);			 	
			//clicking on add more entry
			Driver.driver.findElement(By.id("add-new-entry")).click();
			 	//Adding entry 4
			action.doubleClick(Driver.driver.findElement(By.id("entry-3")));
			Driver.driver.findElement(By.id("unit-name-edit-entry-3")).clear();
			Driver.driver.findElement(By.id("unit-name-edit-entry-3")).sendKeys(optiontext);
			 	// Accepting answer
			Driver.driver.findElement(By.id("done-button")).click(); 
			// number of entries 
			int entries = Driver.driver.findElements(By.cssSelector("li[class='entry-list']")).size();
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("delete-entry")));
			Thread.sleep(2000);
			int entries1 = Driver.driver.findElements(By.cssSelector("li[class='entry-list']")).size();
			if(entries1 > entries)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The entries canbt be deleted for text entry dropdown question.");
			}
			//click on question area
			Driver.driver.findElement(By.id("question-raw-content")).click();
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[id='adddropdown']")));
		//	Driver.driver.findElement(By.id("adddropdown")).click();
			//((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[id='adddropdown']")));
			Thread.sleep(2000);
			/*int box = Driver.driver.findElements(By.cssSelector("span[class='select-box-tag-wrapper question-element-wrapper']")).size();
			if(box != 2)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On click of A-z with down arrow icon in the toolbar is nit adding text entry block.");
			}*/
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
		 	Driver.driver.findElement(By.id("question-raw-content")).click();
		 	int addtext = Driver.driver.findElements(By.cssSelector("img[id='addtextbox']")).size();
		 	if(addtext != 0)
		 	{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("Its showing the 'text entry' block in the tool bar for text drop down question.");
		 	}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase createTextDropDown in class createTextDropDown ",e);
		}
	}
@Test(priority = 2, enabled = true)
public void createTextDropDownWithoutAcceptingAnswer()
{
	try
	{
		Driver.startDriver();
		String course = Config.course;
		String questiontext = ReadTestData.readDataByTagName("CreateTextDropDown", "questiontext", "1255");
		String newChapterName = ReadTestData.readDataByTagName("CreateTextDropDown", "newChapterName", "1255");
		String typeOfPractice = ReadTestData.readDataByTagName("CreateTextDropDown", "typeOfPractice", "1255");
		String diagassessmentname = ReadTestData.readDataByTagName("CreateTextDropDown", "diagassessmentname", "1255");
		String questionset = ReadTestData.readDataByTagName("CreateTextDropDown", "questionset", "1255");
		String optiontext = ReadTestData.readDataByTagName("CreateTextDropDown", "optiontext", "1255");
		String tlo1idString = ReadTestData.readDataByTagName("chapternames", "tlo1id", "0");
		int tlo1id = Integer.parseInt(tlo1idString);
		new Assignment().createChapter(1255, tlo1id);
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
	 
	Driver.driver.findElement(By.id("qtn-text-entry-drop-down-img")).click();
		Thread.sleep(2000);
	Driver.driver.findElement(By.id("question-raw-content")).click();
	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
	Driver.driver.switchTo().defaultContent();
	 	// Adding Entry 1
	Driver.driver.findElement(By.id("entry-0")).click();
	Driver.driver.findElement(By.id("unit-name-edit-entry-0")).clear();
	Driver.driver.findElement(By.id("unit-name-edit-entry-0")).sendKeys(optiontext);
	 	
		 	
		/*// Accepting answer
		WebElement menuitem = Driver.driver.findElement(By.id("entry-1")); 
        Locatable hoverItem = (Locatable) menuitem;
        Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
        mouse.mouseMove(hoverItem.getCoordinates());
        List<WebElement> selectanswerticks = Driver.driver.findElements(By.className("mark-selected"));        	
        selectanswerticks.get(1).click(); //select second option as correct answer
		Thread.sleep(2000);*/
		   // Adding Entry 2
		Actions action = new Actions(Driver.driver);
		action.doubleClick(Driver.driver.findElement(By.id("entry-1")));
		action.perform();
		Driver.driver.findElement(By.id("unit-name-edit-entry-1")).clear();
		Driver.driver.findElement(By.id("unit-name-edit-entry-1")).sendKeys(optiontext);
		  // Adding Entry 3
		action.doubleClick(Driver.driver.findElement(By.id("entry-2")));
		action.perform();
		Driver.driver.findElement(By.id("unit-name-edit-entry-2")).clear();
		Driver.driver.findElement(By.id("unit-name-edit-entry-2")).sendKeys(optiontext);			 	
		//clicking on add more entry
		Driver.driver.findElement(By.id("add-new-entry")).click();
		 	//Adding entry 4
		action.doubleClick(Driver.driver.findElement(By.id("entry-3")));
		Driver.driver.findElement(By.id("unit-name-edit-entry-3")).clear();
		Driver.driver.findElement(By.id("unit-name-edit-entry-3")).sendKeys(optiontext);
		 	// Accepting answer
		//Driver.driver.findElement(By.id("done-button")).click(); 
		Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
	 	Thread.sleep(2000);	
	 	//default question text
	 	String defaultText = Driver.driver.findElement(By.id("question-raw-content")).getText();
	 	if(defaultText.contains("Enter Sample Question Text"))
	 	{
	 		new Screenshot().captureScreenshotFromTestCase();
	 		Assert.fail("Thed default question is not getting removed.");
	 	}
	 	String correct = Driver.driver.findElement(By.cssSelector("li[class='entry-list selected']")).getText();
	 	if(!correct.equals(optiontext))
	 	{
	 		new Screenshot().captureScreenshotFromTestCase();
	 		Assert.fail("If we dont select any answer choice then by default its not taking the first choice as correct for text dropdown question.");
	 	}
	}
	catch(Exception e)
	{
		new Screenshot().captureScreenshotFromTestCase();
		Assert.fail("Exception in testcase createTextDropDownWithoutAcceptingAnswer in class createTextDropDown ",e);
	}
}
@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
