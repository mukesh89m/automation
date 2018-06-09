package com.snapwiz.selenium.tests.staf.orion.testcases.IT12.R3;

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
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Notification;


public class MapleNumeric {
	 
	 @Test
	  public void mapleNumeric()  {
		 try
		 {
			 Driver.startDriver();			 
			 new DirectLogin().CMSLogin();
			 Thread.sleep(2000);
			 new SelectCourse().selectcourse();
			 Thread.sleep(2000);
			 new SelectCourse().selectChapterByIndex(0);		
			 new SelectCourse().selectAssessmentByIndex(1);
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon 
			 Thread.sleep(2000);
			 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.id("addQuestion")));
			 Thread.sleep(2000);
			 
			 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("qtn-numeric-maple-img")));  // Click on Questiontype "Maple Symbolic 
			
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("question-raw-content")).click();
			
			 Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys("Question Sample Text");   // Enter the Sampe Text.
			 Driver.driver.switchTo().defaultContent();
			 
			 Thread.sleep(2000);
			 
			 WebElement addmaplenumeric = Driver.driver.findElement(By.id("addmaplenumeric"));
			 addmaplenumeric.click();
			 addmaplenumeric.click();
			 addmaplenumeric.click();
			 addmaplenumeric.click();
			 addmaplenumeric.click();
			 addmaplenumeric.click();
			 Thread.sleep(2000);
			 
			 String robonotification = new Notification().getNotificationMessageFromCMS();
			 if(!robonotification.contains("You have reached maximum number of choices which can be added"))
				 Assert.fail("Robo Notification not as expected after trying adding more than three combo boxes");
			 Thread.sleep(2000);
			 
			 new Search().changeFocus();
			 	 
			 List<WebElement> answertextboxes = Driver.driver.findElements(By.id("correct-ans-text"));			 //Send 6 values to 6 boxes.
			 answertextboxes.get(0).sendKeys("10");
			 answertextboxes.get(1).sendKeys("20");
			 answertextboxes.get(2).sendKeys("30");
			 answertextboxes.get(3).sendKeys("40");
			 answertextboxes.get(4).sendKeys("50");
			 answertextboxes.get(5).sendKeys("60");
			 
			 Driver.driver.findElement(By.cssSelector("div.right-alt-container-img")).click();
			 Driver.driver.findElement(By.id("alternate-answer-text")).sendKeys("100");
			  		 
			 Driver.driver.findElement(By.id("done-button")).click();
			 
			 Driver.driver.findElement(By.id("saveQuestionDetails1")).click();  // The question is saved.
			 Thread.sleep(3000);
			 
			 String parentHandle = Driver.driver.getWindowHandle();
			 Driver.driver.findElement(By.id("preview-the-image-quiz")).click();
			 Thread.sleep(3000); 
			 
			 for (String winHandle : Driver.driver.getWindowHandles()) 
			 {
				 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			 }
			 
			 		 			 
			 Driver.driver.findElement(By.id("1")).click();                               // Add answers in preview to get "You got is right".
			 Driver.driver.findElement(By.id("1")).sendKeys("10");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("2")).click();
			 Driver.driver.findElement(By.id("2")).sendKeys("20");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("3")).click();
			 Driver.driver.findElement(By.id("3")).sendKeys("30");
			 
			 Driver.driver.findElement(By.id("4")).click();
			 Driver.driver.findElement(By.id("4")).sendKeys("40");
			 
			 Driver.driver.findElement(By.id("5")).click();
			 Driver.driver.findElement(By.id("5")).sendKeys("50");
			 
			 Driver.driver.findElement(By.id("6")).click();
			 Driver.driver.findElement(By.id("6")).sendKeys("60");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("question-reveview-submit")).click();
			 Thread.sleep(5000);
			 
			 String text = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText(); 
			 if(!text.contains("You got it right."))
			 Assert.fail("Message not found-1");
			 Thread.sleep(3000);
			 Driver.driver.close();                                         // Close the Preview Window
			 Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
			 
			 Driver.driver.findElement(By.id("preview-the-image-quiz")).click();  
			 Thread.sleep(3000);
			 
			 for (String winHandle : Driver.driver.getWindowHandles()) 
			 {
				 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			 }
						 			 
			 Driver.driver.findElement(By.id("1")).click();                               // Add answers in preview to show "You got it wrong".
			 Driver.driver.findElement(By.id("1")).sendKeys("70");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("2")).click();
			 Driver.driver.findElement(By.id("2")).sendKeys("80");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("3")).click();
			 Driver.driver.findElement(By.id("3")).sendKeys("90");
			 
			 Driver.driver.findElement(By.id("4")).click();
			 Driver.driver.findElement(By.id("4")).sendKeys("100");
			 
			 Driver.driver.findElement(By.id("5")).click();
			 Driver.driver.findElement(By.id("5")).sendKeys("110");
			 
			 Driver.driver.findElement(By.id("6")).click();
			 Driver.driver.findElement(By.id("6")).sendKeys("120");
			 
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("question-reveview-submit")).click();          
			 Thread.sleep(5000);
			 
			 String text2 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText(); 
			 if(!text2.contains("You got it wrong."))
			 Assert.fail("Message not found-1");
			 
			 Driver.driver.close();                                         // Close the Preview Window
			 Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
			 
			  Driver.driver.findElement(By.id("preview-the-image-quiz")).click();  
			 Thread.sleep(2000);
			 
			 for (String winHandle : Driver.driver.getWindowHandles()) 
			 {
				 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			 }
						 			 
			 Driver.driver.findElement(By.id("1")).click();                               // Add answers in preview to show "You got it Partially correct".
			 Driver.driver.findElement(By.id("1")).sendKeys("10");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("2")).click();
			 Driver.driver.findElement(By.id("2")).sendKeys("20");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("3")).click();
			 Driver.driver.findElement(By.id("3")).sendKeys("30");
			 
			 Driver.driver.findElement(By.id("4")).click();
			 Driver.driver.findElement(By.id("4")).sendKeys("150");
			 
			 Driver.driver.findElement(By.id("5")).click();
			 Driver.driver.findElement(By.id("5")).sendKeys("160");
			 
			 Driver.driver.findElement(By.id("6")).click();
			 Driver.driver.findElement(By.id("6")).sendKeys("170");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("question-reveview-submit")).click();
			 Thread.sleep(5000);
			 
			 String text1 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-partial-correct']")).getText(); 
			 if(!text1.contains("You got it Partially Correct."))
			 Assert.fail("Message not found-1");
			 
			 Driver.driver.close();                                         // Close the Preview Window
			 Driver.driver.switchTo().window(parentHandle);               // switch back to the original window
			 		 
			 Driver.driver.findElement(By.id("preview-the-image-quiz")).click();  
			 Thread.sleep(2000);
			 
			 for (String winHandle : Driver.driver.getWindowHandles()) 
			 {
				 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			 }
						 			 
			 Driver.driver.findElement(By.id("1")).click();                               // Add alternate answer to first box in preview to show "You got it Correct".
			 Driver.driver.findElement(By.id("1")).sendKeys("100");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("2")).click();
			 Driver.driver.findElement(By.id("2")).sendKeys("20");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("3")).click();
			 Driver.driver.findElement(By.id("3")).sendKeys("30");
			 
			 Driver.driver.findElement(By.id("4")).click();
			 Driver.driver.findElement(By.id("4")).sendKeys("40");
			 
			 Driver.driver.findElement(By.id("5")).click();
			 Driver.driver.findElement(By.id("5")).sendKeys("50");
			 
			 Driver.driver.findElement(By.id("6")).click();
			 Driver.driver.findElement(By.id("6")).sendKeys("60");		 
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("question-reveview-submit")).click();
             Thread.sleep(5000);
			 
             String text3 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText(); 
    		 if(!text3.contains("You got it right."))
    		 Assert.fail("Message not found-1");
    		 
			 Driver.driver.close();                                         // Close the Preview Window
			 Driver.driver.switchTo().window(parentHandle);               // switch back to the original window
		 
			 Driver.driver.findElement(By.id("question-raw-content")).click();
			 Thread.sleep(2000);
			// MouseHover.mouserhoverbyid("1");
             new Actions(Driver.driver).moveToElement(Driver.driver.findElement(By.id("1"))).click(Driver.driver.findElement(By.className("delete"))).build().perform();
				/*Locatable hoverItem = (Locatable) Driver.driver.findElement(By.id("1"));
					Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
							mouse.mouseMove(hoverItem.getCoordinates());
			 Driver.driver.findElement(By.className("delete")).click();*/
			 //((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.className("delete")));

			 new Search().changeFocus();
			 
			 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("right-alt-remove-container-img")));
			// Driver.driver.findElement(By.className("right-alt-remove-container-img"));
			 Thread.sleep(2000);

             new Search().changeFocus();
			 
             Driver.driver.findElement(By.id("done-button")).click();
			 
			 Driver.driver.findElement(By.id("saveQuestionDetails1")).click();  // The question is saved.
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("preview-the-image-quiz")).click();
			 Thread.sleep(2000);
			 
			 
			 for (String winHandle : Driver.driver.getWindowHandles()) 
			 {
				 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			 }
			 
			 		 			 
			 Driver.driver.findElement(By.id("1")).click();                               // Add answers in preview to get "You got is right".
			 Driver.driver.findElement(By.id("1")).sendKeys("10");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("2")).click();
			 Driver.driver.findElement(By.id("2")).sendKeys("20");
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("3")).click();
			 Driver.driver.findElement(By.id("3")).sendKeys("30");
			 
			 Driver.driver.findElement(By.id("4")).click();
			 Driver.driver.findElement(By.id("4")).sendKeys("40");
			 
			 Driver.driver.findElement(By.id("5")).click();
			 Driver.driver.findElement(By.id("5")).sendKeys("50");
			 
			 Driver.driver.findElement(By.id("6")).click();
			 Driver.driver.findElement(By.id("6")).sendKeys("60"); 
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("question-reveview-submit")).click();
			 Thread.sleep(3000);
			
			 String text4 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText(); 
			 if(!text4.contains("You got it right."))
			 Assert.fail("Message not found-1");
			 Thread.sleep(3000);					 
			 Driver.driver.close();                                         // Close the Preview Window
			 Driver.driver.switchTo().window(parentHandle);                // switch back to the original window			
		 }
       catch(Exception e)	
       	{
    	   Assert.fail("Exception in testcase mapleSymbolic in class Maple_numeric",e);
       	}
	}
	 @AfterMethod
		public void TearDown()throws Exception
		{
			Driver.driver.quit();
		}
}



