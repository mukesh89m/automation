package com.snapwiz.selenium.tests.staf.orion.testcases.IT12.R3;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Notification;
import com.snapwiz.selenium.tests.staf.orion.Driver;

import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectCourse;


public class NumericEntryWithUnits {

	 
	 @Test
	  public void numericEntryWithUnits()  {
		 try
		 {
			 Driver.startDriver();
			 
			 new DirectLogin().CMSLogin();
			 Thread.sleep(2000);
			 new SelectCourse().selectcourse(); //Select Course
			 Thread.sleep(2000);
			 new SelectCourse().selectChapterByIndex(0);			//Select third chapter
			 Thread.sleep(2000);
			 new SelectCourse().selectAssessmentByIndex(1); //Open Assessment
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon
			
			 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.id("addQuestion")));
			
			 Thread.sleep(2000);			 
			 
			 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("qtn-text-entry-numeric-units-img")));  // Click on Questiontype "NumericEntry with Units.
			 
			/* WebElement numeric = Driver.driver.findElement(By.id("qtn-text-entry-numeric-units-img"));
			 numeric.click();
			 Thread.sleep(2000);*/
			  Driver.driver.findElement(By.id("question-raw-content")).click(); //Entering Question Text

			  Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys("Question Sample Text");   // Enter the Sample Text.
			  Driver.driver.switchTo().defaultContent();
			  Thread.sleep(2000);
			 
			 WebElement addtextboxwithunits = Driver.driver.findElement(By.id("addtextboxwithunits"));
			 addtextboxwithunits.click();
			 addtextboxwithunits.click();
			 addtextboxwithunits.click();
			 Thread.sleep(2000);
		
			 String robonotification = new Notification().getNotificationMessageFromCMS();
			 if(!robonotification.contains("You have reached maximum number of choices which can be added"))
				 Assert.fail("Robo Notification not as expected after trying adding more than three combo boxes");
			 Thread.sleep(2000);
			 
			 new Search().changeFocus();
			 
			 List<WebElement> answertextboxes = Driver.driver.findElements(By.id("correct-ans-text"));			 //Send 3 values to 3 boxes.
			 answertextboxes.get(0).sendKeys("10");
			 answertextboxes.get(1).sendKeys("20");
			 answertextboxes.get(2).sendKeys("30");

			 Thread.sleep(2000);			 		 
			 
			 List<WebElement> addmorelinks =  Driver.driver.findElements(By.id("add-more-entry"));  // To send units .
			 
			 addmorelinks.get(0).click();			 
			 Driver.driver.findElement(By.xpath("//div[@id='cms-qtextentry-more-units-container']/ul/li[2]")).click();  //feet
			 
			 Thread.sleep(2000);
			 
			 addmorelinks.get(0).click();	
			 Driver.driver.findElement(By.xpath("//div[@id='cms-qtextentry-more-units-container']/ul/li[3]")).click();  //yards
			 
			 Thread.sleep(2000);
			 addmorelinks.get(1).click();	
			 Driver.driver.findElement(By.xpath("(//div[@id='cms-qtextentry-more-units-container']/ul/li[4])[2]")).click();  //
			 
			 Thread.sleep(2000);
			 addmorelinks.get(1).click();
			 Driver.driver.findElement(By.xpath("(//div[@id='cms-qtextentry-more-units-container']/ul/li[5])[2]")).click();  //
			 
			 addmorelinks.get(2).click();	
			 Driver.driver.findElement(By.xpath("(//div[@id='cms-qtextentry-more-units-container']/ul/li[6])[3]")).click();  //
			 Thread.sleep(2000);
			 
			 addmorelinks.get(2).click();	
			 Driver.driver.findElement(By.xpath("(//div[@id='cms-qtextentry-more-units-container']/ul/li[1])[3]")).click();     
			 Driver.driver.findElement(By.id("unit-name-edit-entry-1")).sendKeys("cm");
			 Thread.sleep(2000);
			 
		     Driver.driver.findElement(By.id("done-button")).click();
			 
			 Driver.driver.findElement(By.id("saveQuestionDetails1")).click();  // The question is saved.
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("preview-the-image-quiz")).click();
			 Thread.sleep(2000);
			 
			 String parentHandle = Driver.driver.getWindowHandle();
			 for (String winHandle : Driver.driver.getWindowHandles()) 
			 {
				 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			 }
			 			 
			 Driver.driver.findElement(By.id("1")).click();                               // Add answers in preview.
			 Driver.driver.findElement(By.id("1")).sendKeys("10");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("3")).click();
			 Driver.driver.findElement(By.id("3")).sendKeys("20");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("5")).click();
			 Driver.driver.findElement(By.id("5")).sendKeys("30");
			 Thread.sleep(2000);
			 
			 List<WebElement> dropdowns =  Driver.driver.findElements(By.className("sbToggle"));	// Add Units in Preview.
			 
			 dropdowns.get(0).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.linkText("feet")).click();
			 dropdowns.get(1).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.linkText("meters")).click();
			 
			 dropdowns.get(2).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.linkText("kgs")).click();
			 
			 Driver.driver.findElement(By.id("question-reveview-submit")).click();            // "You got it correct".
			 Thread.sleep(30000);
			 
			 String text = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText(); 
			 if(!text.contains("You got it right."))
			 Assert.fail("Message not found-1");
			 
			 Driver.driver.close();                                         // Close the Preview Window
			 Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
			 
			 Driver.driver.findElement(By.id("preview-the-image-quiz")).click();  // This is to check in to show "You got it wrong" - Click on Preview Again.
			 Thread.sleep(2000);
			 
			 for (String winHandle : Driver.driver.getWindowHandles()) 
			 {
				 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			 }
						 			 
			 Driver.driver.findElement(By.id("1")).click();                               // Add answers in preview.
			 Driver.driver.findElement(By.id("1")).sendKeys("40");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("3")).click();
			 Driver.driver.findElement(By.id("3")).sendKeys("50");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("5")).click();
			 Driver.driver.findElement(By.id("5")).sendKeys("60");
			 Thread.sleep(2000);
			 
			 List<WebElement> dropdowns1 =  Driver.driver.findElements(By.className("sbToggle"));	
			 
			 dropdowns1.get(0).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.linkText("yards")).click();
			 dropdowns1.get(1).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.linkText("lbs")).click();
			 dropdowns1.get(2).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.linkText("cm")).click();
			 
			 Driver.driver.findElement(By.id("question-reveview-submit")).click();
			 Thread.sleep(30000);
			 
			 String text1 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText(); 
			 if(!text1.contains("You got it wrong."))
			 Assert.fail("Message not found-1");
			 
			 Driver.driver.close();                                         // Close the Preview Window
			 Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
			
			 Driver.driver.findElement(By.id("preview-the-image-quiz")).click();  // This is to check in to show "You got it Partially Correct" - Click on Preview Again.
			 Thread.sleep(2000);
			 
			 
			 for (String winHandle : Driver.driver.getWindowHandles()) 
			 {
				 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			 }
						 			 
			 Driver.driver.findElement(By.id("1")).click();                               // Add answers in preview.
			 Driver.driver.findElement(By.id("1")).sendKeys("10");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("3")).click();
			 Driver.driver.findElement(By.id("3")).sendKeys("20");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("5")).click();
			 Driver.driver.findElement(By.id("5")).sendKeys("30");
			 Thread.sleep(2000);
			 
			 List<WebElement> dropdowns2 =  Driver.driver.findElements(By.className("sbToggle"));	
			 
			 dropdowns2.get(0).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.linkText("yards")).click();
			 dropdowns2.get(1).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.linkText("lbs")).click();
			 dropdowns2.get(2).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.linkText("cm")).click();
			 
			 Driver.driver.findElement(By.id("question-reveview-submit")).click();
			 Thread.sleep(30000);
			 
			 String text2 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-partial-correct']")).getText(); 
			 if(!text2.contains("You got it Partially Correct."))
			 Assert.fail("Message not found-1");
			 
			 Driver.driver.close();                                         // Close the Preview Window
			 Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
				 
			 List<WebElement> answertextboxes1 = Driver.driver.findElements(By.id("correct-ans-text"));			 //Send 3 values to 3 boxes. R3.7
			 answertextboxes1.get(0).clear();
			 answertextboxes1.get(0).sendKeys("2000");
			 answertextboxes1.get(1).clear();
			 answertextboxes1.get(1).sendKeys("4000");
			 answertextboxes1.get(2).clear();
			 answertextboxes1.get(2).sendKeys("6000");
			 
			 Driver.driver.findElement(By.id("done-button")).click();
			 
			 Driver.driver.findElement(By.id("saveQuestionDetails1")).click();  // The question is saved.
			 Thread.sleep(2000);
			 
			 String parentHandle1 = Driver.driver.getWindowHandle();
			 Driver.driver.findElement(By.id("preview-the-image-quiz")).click();
			 Thread.sleep(2000);
			 
			 for (String winHandle : Driver.driver.getWindowHandles()) 
			 {
				 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			 }
			 
			 Driver.driver.findElement(By.id("1")).click();                               // Add answers in preview.
			 Driver.driver.findElement(By.id("1")).sendKeys("2000");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("3")).click();
			 Driver.driver.findElement(By.id("3")).sendKeys("4000");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("5")).click();
			 Driver.driver.findElement(By.id("5")).sendKeys("6000");
			 Thread.sleep(2000);
			 
			 List<WebElement> dropdown1 =  Driver.driver.findElements(By.className("sbToggle"));	// Add Units in Preview.
			 
			 dropdown1.get(0).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.linkText("feet")).click();
			 dropdown1.get(1).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.linkText("meters")).click();
			 dropdown1.get(2).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.linkText("kgs")).click();
			 
			 Driver.driver.findElement(By.id("question-reveview-submit")).click();
			 Thread.sleep(30000);
						 
			 String text3 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText(); 
			 if(!text3.contains("You got it right"))
			 Assert.fail("Message not found-1");
			 
			 Driver.driver.close();                                         // Close the Preview Window
			 Driver.driver.switchTo().window(parentHandle1);                // switch back to the original window
		 }
       catch(Exception e)	
       	{
    	   	Assert.fail("Exception in testcase numericEntryWithUnits in class NumbericEntryWithUnits",e);
       	}
	}
	 
	   @AfterMethod
		public void tearDown() throws Exception
		{
			Driver.driver.quit();
		}
}



