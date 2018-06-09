package com.snapwiz.selenium.tests.staf.orion.testcases.IT12.R3;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Notification;


public class Maple_symbolic {

	 
	 @Test
	  public void mapleSymbolic()  {
		 try
		 {
			 Driver.startDriver();			 
			 new DirectLogin().CMSLogin();
			 Thread.sleep(3000);
			 new SelectCourse().selectcourse();
			 Thread.sleep(3000);
			 new SelectCourse().selectChapterByIndex(0);		
			 new SelectCourse().selectAssessmentByIndex(1);
			 Thread.sleep(3000);
			 WebElement questionplusicon=Driver.driver.findElement(By.id("questionOptions")); // Click on "+" icon
			 questionplusicon.click();
			 Thread.sleep(3000);
			 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.id("addQuestion")));
			 Thread.sleep(3000);
			 
			 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("qtn-math-symbolic-notation-img")));  // Click on Questiontype "Maple Symbolic 
			
			 Thread.sleep(3000);
			 
			 Driver.driver.findElement(By.id("question-raw-content")).click();
			
			 Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys("Question Sample Text");   // Enter the Sampe Text.
			 Driver.driver.switchTo().defaultContent();
			 
			 Thread.sleep(3000);
			 
			 WebElement addmaplesymbolic = Driver.driver.findElement(By.id("addmaplesymbolic"));
			 addmaplesymbolic.click();
			 addmaplesymbolic.click();
			 addmaplesymbolic.click();
			 addmaplesymbolic.click();
			 addmaplesymbolic.click();
			 addmaplesymbolic.click();
			 Thread.sleep(3000);

			 String robonotification = new Notification().getNotificationMessageFromCMS();
			 if(!robonotification.contains("You have reached maximum number of choices which can be added"))
				 Assert.fail("Robo Notification not as expected after trying adding more than three combo boxes");
			 Thread.sleep(3000);

			 new Search().changeFocus();
			 	
			 List<WebElement> answertextboxes = Driver.driver.findElements(By.cssSelector("div[class='right-container math-correct-answer-input-container']"));
			 Thread.sleep(3000);		
			                                                                                          //Send 6 values to 6 boxes in UI.
			 answertextboxes.get(0).click();
			 Thread.sleep(3000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText1");			 
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 
			 answertextboxes.get(1).click();
			 Thread.sleep(3000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText2");	
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 
			 answertextboxes.get(2).click();
			 Thread.sleep(3000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText3");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 
			 answertextboxes.get(3).click();
			 Thread.sleep(3000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText4");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 
			 answertextboxes.get(4).click();
			 Thread.sleep(3000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText5");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 
			 answertextboxes.get(5).click();
			 Thread.sleep(3000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText6");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();

			 Driver.driver.findElement(By.id("done-button")).click();
			 
			 Driver.driver.findElement(By.id("saveQuestionDetails1")).click();  // The question is saved.
			 Thread.sleep(5000);
			 
			 String parentHandle = Driver.driver.getWindowHandle();
			 Driver.driver.findElement(By.id("preview-the-image-quiz")).click();
			 Thread.sleep(3000);
			 
			 for (String winHandle : Driver.driver.getWindowHandles()) 
			 {
				 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			 }
			 
			                                                                          // Add answers in preview to show "You got it correct".
			 List<WebElement> answertextboxes1 = Driver.driver.findElements(By.className("math-correct-answer"));
			 
			 answertextboxes1.get(0).click();
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText1");			 
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 Thread.sleep(3000);
			 
			 answertextboxes1.get(1).click();
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText2");	
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 Thread.sleep(3000);
			 
			 answertextboxes1.get(2).click();
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText3");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 Thread.sleep(3000);

			 answertextboxes1.get(3).click();
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText4");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 Thread.sleep(3000);
			 
			 answertextboxes1.get(4).click();
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText5");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 Thread.sleep(3000);
			 
			 answertextboxes1.get(5).click();
			 Thread.sleep(3000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText6");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 
			 Thread.sleep(3000);	 
			 Driver.driver.findElement(By.id("question-reveview-submit")).click();          
			 Thread.sleep(30000);
			 String text = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText(); 
			 if(!text.contains("You got it right."))
			 Assert.fail("Message not found-1");
			 Thread.sleep(3000);
			 Driver.driver.close();                                         // Close the Preview Window
			 Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
		
			 Thread.sleep(5000);
			 
			Driver.driver.findElement(By.id("preview-the-image-quiz")).click();      
			 Thread.sleep(5000);
			 
			 for (String winHandle : Driver.driver.getWindowHandles()) 
			 {
				 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			 }
			 
			 																				// Add answers in preview to show "You got it wrong".
			 List<WebElement> answertextboxes2 = Driver.driver.findElements(By.className("math-correct-answer"));
			 
			 answertextboxes2.get(0).click();
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SamplesText1");			 
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 Thread.sleep(3000);
			 
			 answertextboxes2.get(1).click();
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SamplesText2");	
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 Thread.sleep(3000);
			 
			 answertextboxes2.get(2).click();
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SamplesText3");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 Thread.sleep(3000);

			 answertextboxes2.get(3).click();
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SamplesText4");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 Thread.sleep(3000);
			 
			 answertextboxes2.get(4).click();
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SamplesText5");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 Thread.sleep(3000);
			 
			 answertextboxes2.get(5).click();
			 Thread.sleep(3000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SamplesText6");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 
			 Thread.sleep(3000);	 
			 Driver.driver.findElement(By.id("question-reveview-submit")).click();          
			 Thread.sleep(30000);
			
			 String text2 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText(); 
			 if(!text2.contains("You got it wrong."))
			 Assert.fail("Message not found-1");
			 Thread.sleep(3000);
			 Driver.driver.close();                                         // Close the Preview Window
			 Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
		
			 Thread.sleep(5000);
			 
			Driver.driver.findElement(By.id("preview-the-image-quiz")).click();      
			 Thread.sleep(5000);
			
			 for (String winHandle : Driver.driver.getWindowHandles()) 
			 {
				 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			 }
			 
			                                                            // Add answers in preview to show "You got Partially correct".
			 List<WebElement> answertextboxes3 = Driver.driver.findElements(By.className("math-correct-answer"));
			 
			 answertextboxes3.get(0).click();
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText10");			 
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 Thread.sleep(3000);
			 
			 answertextboxes3.get(1).click();
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText20");	
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 Thread.sleep(3000);
			 
			 answertextboxes3.get(2).click();
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText30");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 Thread.sleep(3000);

			 answertextboxes3.get(3).click();
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText4");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 Thread.sleep(3000);			 
			 answertextboxes3.get(4).click();
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText5");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 Thread.sleep(3000);			 
			 answertextboxes3.get(5).click();
			 Thread.sleep(3000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText6");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();			 
			 Thread.sleep(3000);
			 Driver.driver.findElement(By.id("question-reveview-submit")).click();          
			 Thread.sleep(30000);
			 
			 String text3 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-partial-correct']")).getText(); 
			 if(!text3.contains("You got it Partially Correct."))
			 Assert.fail("Message not found-1");
			 
			 Thread.sleep(3000);
			 Driver.driver.close();                                         // Close the Preview Window
			 Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
			 			 
		 }
       catch(Exception e)	
       	{
    	   	Assert.fail("Exception in testcase mapleSymbolic in class Maple_Symbolic",e);	
       	}
	}
	 @AfterMethod
		public void TearDown()throws Exception
		{
			Driver.driver.quit();
		}
}



