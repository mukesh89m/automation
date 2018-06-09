package com.snapwiz.selenium.tests.staf.orion.testcases.IT12.R4;

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
import com.snapwiz.selenium.tests.staf.orion.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Notification;

public class Tolerance 
{
	@Test(priority = 1, enabled=false)
	public void toleranceEdit()
	{
	 try 
	 {
		 Driver.startDriver();
		 
		 new DirectLogin().CMSLogin();
		 Thread.sleep(3000);
		 new SelectCourse().selectcourse(); //Select Course
		 Thread.sleep(3000);
		 new SelectCourse().selectChapterByIndex(0);			//Select third chapter
		 Thread.sleep(3000);
		 new SelectCourse().selectAssessmentByIndex(1); //Open Assessment
		 Thread.sleep(3000);
		 Driver.driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon
		
		 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.id("addQuestion")));
			
		 Thread.sleep(3000);			 
		 
		 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("qtn-text-entry-numeric-units-img")));  // Click on Questiontype "NumericEntry with Units.
		 
		/* WebElement numeric = Driver.driver.findElement(By.id("qtn-text-entry-numeric-units-img"));
		 numeric.click();
		 Thread.sleep(3000);*/
		  Driver.driver.findElement(By.id("question-raw-content")).click(); //Entering Question Text

		  Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys("Question Sample Text");   // Enter the Sample Text.
		  Driver.driver.switchTo().defaultContent();
		 
		 Thread.sleep(3000);
		 
		 WebElement addtextboxwithunits = Driver.driver.findElement(By.id("addtextboxwithunits"));
		 addtextboxwithunits.click();
		 addtextboxwithunits.click();
		 addtextboxwithunits.click();
		 Thread.sleep(3000);
	
		 String robonotification = new Notification().getNotificationMessageFromCMS();
		 if(!robonotification.contains("You have reached maximum number of choices which can be added"))
			 Assert.fail("Robo Notification not as expected after trying adding more than three combo boxes");
		 Thread.sleep(3000);
		 
		 Driver.driver.findElement(By.xpath("/html/body")).click();
		 
		 List<WebElement> answertextboxes = Driver.driver.findElements(By.id("correct-ans-text"));			 //Send 3 values to 3 boxes.
		 answertextboxes.get(0).sendKeys("10");
		 answertextboxes.get(1).sendKeys("20");
		 answertextboxes.get(2).sendKeys("30");

		 Thread.sleep(3000);			 		 
		 
		 List<WebElement> addmorelinks =  Driver.driver.findElements(By.id("add-more-entry"));  // To send units .
		 
		 addmorelinks.get(0).click();			 
		 Driver.driver.findElement(By.xpath("//div[@id='cms-qtextentry-more-units-container']/ul/li[2]")).click();  //feet
		 
		 Thread.sleep(3000);
		 
		 addmorelinks.get(0).click();	
		 Driver.driver.findElement(By.xpath("//div[@id='cms-qtextentry-more-units-container']/ul/li[3]")).click();  //yards
		 
		 Thread.sleep(3000);
		 
		 addmorelinks.get(1).click();	
		 Driver.driver.findElement(By.xpath("(//div[@id='cms-qtextentry-more-units-container']/ul/li[4])[2]")).click();  //
		 
		 Thread.sleep(3000);
		 
		 addmorelinks.get(1).click();
		 Driver.driver.findElement(By.xpath("(//div[@id='cms-qtextentry-more-units-container']/ul/li[5])[2]")).click();  //
		 
		 addmorelinks.get(2).click();	
		 Driver.driver.findElement(By.xpath("(//div[@id='cms-qtextentry-more-units-container']/ul/li[6])[3]")).click();  //
		 Thread.sleep(3000);
		 
		 addmorelinks.get(2).click();	
		
		 Driver.driver.findElement(By.xpath("(//div[@id='cms-qtextentry-more-units-container']/ul/li[1])[3]")).click();     
		 Driver.driver.findElement(By.id("unit-name-edit-entry-1")).sendKeys("cm");
		 Thread.sleep(3000);
     
	     Driver.driver.findElement(By.id("tolerance-ans-text")).click();
	     Driver.driver.findElement(By.id("tolerance-ans-text")).clear();
	     Driver.driver.findElement(By.id("tolerance-ans-text")).sendKeys("2.0");
	     
	     Driver.driver.findElement(By.id("done-button")).click();
		 
		 Driver.driver.findElement(By.id("saveQuestionDetails1")).click();  // The question is saved.
		 Thread.sleep(3000);
		 
		 Driver.driver.findElement(By.id("preview-the-image-quiz")).click();
		 Thread.sleep(3000);
		 
		 String parentHandle = Driver.driver.getWindowHandle();
		 for (String winHandle : Driver.driver.getWindowHandles()) 
		 {
			 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		 }
		 			 
		 Driver.driver.findElement(By.id("1")).click();                               // Add answers in preview.
		 Driver.driver.findElement(By.id("1")).sendKeys("12");
		 Thread.sleep(2000);
		 
		 Driver.driver.findElement(By.id("3")).click();
		 Driver.driver.findElement(By.id("3")).sendKeys("18");
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
		 
		 Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 Thread.sleep(3000);
		 
		 String text = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText(); 
		 if(!text.contains("You got it right."))
		 Assert.fail("Message not found-1");
			
		 Driver.driver.close();                                         // Close the Preview Window
		 Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
	 }
	 catch(Exception e)
	 {
		 new Screenshot().captureScreenshotFromTestCase();
 	   	Assert.fail("Exception in testcase numericEntryWithUnits in class NumbericEntryWithUnits",e);
	 }
	}
	
	@Test(priority = 2, enabled=true)
	public void toleranceEmpty()
	{
	 try 
	 {
		 Driver.startDriver();
		 
		 new DirectLogin().CMSLogin();
		 Thread.sleep(3000);
		 new SelectCourse().selectcourse(); //Select Course
		 Thread.sleep(3000);
		 new SelectCourse().selectChapterByIndex(0);			//Select third chapter
		 Thread.sleep(3000);
		 new SelectCourse().selectAssessmentByIndex(1); //Open Assessment
		 Thread.sleep(3000);
		 Driver.driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon
		
		 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.id("addQuestion")));
		
		 Thread.sleep(3000);			 
		 
		 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("qtn-text-entry-numeric-units-img")));  // Click on Questiontype "NumericEntry with Units.
		 
		/* WebElement numeric = Driver.driver.findElement(By.id("qtn-text-entry-numeric-units-img"));
		 numeric.click();
		 Thread.sleep(3000);*/
		  Driver.driver.findElement(By.id("question-raw-content")).click(); //Entering Question Text
          Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys("Question Sample Text");   // Enter the Sample Text.
	      Driver.driver.switchTo().defaultContent();
		 
		 Thread.sleep(3000);
		 
		 WebElement addtextboxwithunits = Driver.driver.findElement(By.id("addtextboxwithunits"));
		 addtextboxwithunits.click();
		 addtextboxwithunits.click();
		 addtextboxwithunits.click();
		 Thread.sleep(3000);
	
		 String robonotification = new Notification().getNotificationMessageFromCMS();
		 if(!robonotification.contains("You have reached maximum number of choices which can be added"))
			 Assert.fail("Robo Notification not as expected after trying adding more than three combo boxes");
		 Thread.sleep(3000);
		 
		 Driver.driver.findElement(By.xpath("/html/body")).click();
		 
		 List<WebElement> answertextboxes = Driver.driver.findElements(By.id("correct-ans-text"));			 //Send 3 values to 3 boxes.
		 answertextboxes.get(0).sendKeys("10");
		 answertextboxes.get(1).sendKeys("20");
		 answertextboxes.get(2).sendKeys("30");

		 Thread.sleep(3000);			 		 
		 
		 List<WebElement> addmorelinks =  Driver.driver.findElements(By.id("add-more-entry"));  // To send units .
		 
		 addmorelinks.get(0).click();			 
		 Driver.driver.findElement(By.xpath("//div[@id='cms-qtextentry-more-units-container']/ul/li[2]")).click();  //feet
		 
		 Thread.sleep(3000);
		 
		 addmorelinks.get(0).click();	
		 Driver.driver.findElement(By.xpath("//div[@id='cms-qtextentry-more-units-container']/ul/li[3]")).click();  //yards
		 
		 Thread.sleep(3000);
		 
		 addmorelinks.get(1).click();	
		 Driver.driver.findElement(By.xpath("(//div[@id='cms-qtextentry-more-units-container']/ul/li[4])[2]")).click();  //
		 
		 Thread.sleep(3000);
		 
		 addmorelinks.get(1).click();
		 Driver.driver.findElement(By.xpath("(//div[@id='cms-qtextentry-more-units-container']/ul/li[5])[2]")).click();  //
		 
		 addmorelinks.get(2).click();	
		 Driver.driver.findElement(By.xpath("(//div[@id='cms-qtextentry-more-units-container']/ul/li[6])[3]")).click();  //
		 Thread.sleep(3000);
		 
		 addmorelinks.get(2).click();	
		
		 Driver.driver.findElement(By.xpath("(//div[@id='cms-qtextentry-more-units-container']/ul/li[1])[3]")).click();     
		 Driver.driver.findElement(By.id("unit-name-edit-entry-1")).sendKeys("cm");
		 
		 Thread.sleep(3000);
     
	     Driver.driver.findElement(By.id("tolerance-ans-text")).click();
	     Driver.driver.findElement(By.id("tolerance-ans-text")).clear();
	 //    Driver.driver.findElement(By.id("tolerance-ans-text")).sendKeys("2");
	     
	     Driver.driver.findElement(By.id("done-button")).click();
		 
		 Driver.driver.findElement(By.id("saveQuestionDetails1")).click();  // The question is saved.
		 Thread.sleep(3000);
		 
		 Driver.driver.findElement(By.id("preview-the-image-quiz")).click();
		 Thread.sleep(3000);
		 
		 String parentHandle = Driver.driver.getWindowHandle();
		 for (String winHandle : Driver.driver.getWindowHandles()) 
		 {
			 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		 }
		 			 
		 Driver.driver.findElement(By.id("1")).click();                               // Add answers in preview.
		 Driver.driver.findElement(By.id("1")).sendKeys("11");
		 Thread.sleep(2000);
		 
		 Driver.driver.findElement(By.id("3")).click();
		 Driver.driver.findElement(By.id("3")).sendKeys("19");
		 Thread.sleep(2000);
		 
		 Driver.driver.findElement(By.id("5")).click();
		 Driver.driver.findElement(By.id("5")).sendKeys("30");
		 Thread.sleep(2000);

		 List<WebElement> dropdowns =  Driver.driver.findElements(By.className("sbToggle"));	// Add Units in Preview.  "You got it correct".
		 
		 dropdowns.get(0).click();
		 Thread.sleep(2000);
		 Driver.driver.findElement(By.linkText("feet")).click();
		 dropdowns.get(1).click();
		 Thread.sleep(2000);
		 Driver.driver.findElement(By.linkText("meters")).click();
		 
		 dropdowns.get(2).click();
		 Thread.sleep(2000);
		 Driver.driver.findElement(By.linkText("kgs")).click();
		 
		 Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 Thread.sleep(3000);
		 
		 String text = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText(); 
		 if(!text.contains("You got it right."))
		 Assert.fail("Message not found-1");
					 
		 Driver.driver.close();                                         // Close the Preview Window
		 Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
		 
		 Driver.driver.findElement(By.id("preview-the-image-quiz")).click();  // This is to check in to show "You got it partial correct" - Click on Preview Again.
		 Thread.sleep(3000);
		 
		 
		 for (String winHandle : Driver.driver.getWindowHandles()) 
		 {
			 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		 }
					 			 
		 Driver.driver.findElement(By.id("1")).click();                               // Add answers in preview - Partially Correct.
		 Driver.driver.findElement(By.id("1")).sendKeys("11");
		 Thread.sleep(2000);
		 
		 Driver.driver.findElement(By.id("3")).click();
		 Driver.driver.findElement(By.id("3")).sendKeys("21");
		 Thread.sleep(2000);
		 
		 Driver.driver.findElement(By.id("5")).click();
		 Driver.driver.findElement(By.id("5")).sendKeys("32");
		 Thread.sleep(2000);
		 
		 List<WebElement> dropdowns1 =  Driver.driver.findElements(By.className("sbToggle"));	
		 
		 dropdowns1.get(0).click();
		 Thread.sleep(2000);
		 Driver.driver.findElement(By.linkText("feet")).click();
		 dropdowns1.get(1).click();
		 Thread.sleep(2000);
		 Driver.driver.findElement(By.linkText("meters")).click();
		 dropdowns1.get(2).click();
		 Thread.sleep(2000);
		 Driver.driver.findElement(By.linkText("kgs")).click();
		 
		 Driver.driver.findElement(By.id("question-reveview-submit")).click();
		 
		 Thread.sleep(3000);
		 
		 String text1 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-partial-correct']")).getText(); 
		 if(!text1.contains("You got it Partially Correct."))
		 Assert.fail("Message not found-1");
		 
		 Driver.driver.close();                                         // Close the Preview Window
		 Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
		 
		 Driver.driver.findElement(By.id("preview-the-image-quiz")).click();  // This is to check in to show "You got it wrong" - Click on Preview Again.
		 Thread.sleep(3000);
		 
		 
		 for (String winHandle : Driver.driver.getWindowHandles()) 
		 {
			 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		 }
					 			 
		 Driver.driver.findElement(By.id("1")).click();                               // Add answers in preview. - You got it Wrong.
		 Driver.driver.findElement(By.id("1")).sendKeys("18");
		 Thread.sleep(2000);
		 
		 Driver.driver.findElement(By.id("3")).click();
		 Driver.driver.findElement(By.id("3")).sendKeys("22");
		 Thread.sleep(2000);
		 
		 Driver.driver.findElement(By.id("5")).click();
		 Driver.driver.findElement(By.id("5")).sendKeys("28");
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
		 
		 Thread.sleep(3000);
		 
		 String text2 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText(); 
		 if(!text2.contains("You got it wrong."))
		 Assert.fail("Message not found-1");
		 
		 Driver.driver.close();                                         // Close the Preview Window
		 Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
	 }
	 catch(Exception e)
	 {
		 new Screenshot().captureScreenshotFromTestCase();
 	   	 Assert.fail("Exception in testcase numericEntryWithUnits in class NumbericEntryWithUnits",e);
	 }
	}
	
	@Test(priority = 3,enabled = false)
	public void importQTIfile()
	{
	try
	{
		 Driver.startDriver();
	     new Assignment().create(72);  
		  Thread.sleep(3000);
		  Driver.driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon
		  ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.id("addQuestion")));
			
			 Thread.sleep(3000);	
			 String filename =  ReadTestData.readDataByTagName("", "filename", "72");

			 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("import-contents-img")));
			  new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+filename+"^");
			  
			  Driver.driver.findElement(By.id("start_queue")).click(); 
			  
			  Driver.driver.findElement(By.className("proceedToViewAContent")).click();
			  Thread.sleep(2000);
			  Driver.driver.findElement(By.cssSelector("span[class='question-link-labels  ']")).click();
			  Thread.sleep(2000);
			  ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.className("question-parameters-title")));
			  Thread.sleep(2000);
			  Driver.driver.findElement(By.id("increase")).click();
			  Driver.driver.findElement(By.id("increase")).click();
			  Driver.driver.findElement(By.id("increase")).click();
			  
			  Driver.driver.findElement(By.id("saveQuestionDetails1")).click();  // The question is saved.
			  Thread.sleep(3000);		 
			  Driver.driver.findElement(By.id("preview-the-image-quiz")).click();
			  Thread.sleep(3000);				 
			  String parentHandle = Driver.driver.getWindowHandle();
			  for (String winHandle : Driver.driver.getWindowHandles()) 
				 {
					 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
					 
				 }
			  String correctAnswer = Driver.driver.findElement(By.id("1")).getAttribute("renderdata");
			  correctAnswer  =correctAnswer.substring(70,74);
				// System.out.println(correctAnswer);
				 
				 //Driver.driver.findElement(By.id("1")).click();                               // Add answers in preview.
				 Driver.driver.findElement(By.id("1")).sendKeys(correctAnswer);
				 Thread.sleep(2000);
				 
				 List<WebElement> dropdowns =  Driver.driver.findElements(By.className("sbToggle"));	// Add Units in Preview.
				 
				 dropdowns.get(0).click();
				 Thread.sleep(2000);
				 Driver.driver.findElement(By.linkText("degrees")).click();
				 Thread.sleep(2000);
				 Driver.driver.findElement(By.id("question-reveview-submit")).click();
				 Thread.sleep(3000);
				 
				 String text = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-partial-correct']")).getText(); 
				 System.out.println(text);
				 if(!text.contains("You got it Partially Correct."))
					 Assert.fail("Message not found-1");
				 
				 Driver.driver.close();                                         // Close the Preview Window
				 Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
		  
				 Driver.driver.findElement(By.id("preview-the-image-quiz")).click();            // Again Preview
				 Thread.sleep(3000);
				 
				 
				 for (String winHandle : Driver.driver.getWindowHandles()) 
				 {
					 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
					 
				 }
				 
				 float i = Float.parseFloat(correctAnswer);   
				 i= i+1;// Convert String to integer and again back to String.
				 System.out.println("i: " + i);
				 
				 Driver.driver.findElement(By.id("1")).sendKeys(Float.toString(i));

				 List<WebElement> dropdown =  Driver.driver.findElements(By.className("sbToggle"));	// Add Units in Preview.
				 
				 dropdown.get(0).click();
				 Thread.sleep(2000);
				 Driver.driver.findElement(By.linkText("m")).click();
				 Thread.sleep(2000);
				 Driver.driver.findElement(By.id("question-reveview-submit")).click();
				 Thread.sleep(3000);
				 
				 String text2 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-wrong']")).getText(); 
				 if(!text2.contains("You got it wrong."))
				 Assert.fail("Message not found-1");
					
				 Driver.driver.close();                                         // Close the Preview Window
				 Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
	}
	catch(Exception e)
	{
		 //new Screenshot().captureScreenshotFromTestCase();
 	   	 Assert.fail("Exception in testcase numericEntryWithUnits in class importQTIfile",e);
	}
	}
	
	@Test(priority = 4,enabled = false)
	public void importQTIAbsoluteValue()
	{
	try
	{
		 Driver.startDriver();
	     new Assignment().create(70);  
		  Thread.sleep(2000);
		  Driver.driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon
		  ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.id("addQuestion")));
			
			 Thread.sleep(3000);	
			 String filename =  ReadTestData.readDataByTagName("", "filename", "70");

			 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("import-contents-img")));
			  new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+filename+"^");
			  
			  Driver.driver.findElement(By.id("start_queue")).click(); 
			  
			  Driver.driver.findElement(By.className("proceedToViewAContent")).click();
			  Thread.sleep(2000);
			  Driver.driver.findElement(By.cssSelector("span[class='question-link-labels  ']")).click();
			  Thread.sleep(2000);
			  ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.className("question-parameters-title")));
			  Thread.sleep(2000);
			  Driver.driver.findElement(By.id("increase")).click();
			  Driver.driver.findElement(By.id("increase")).click();
			  Driver.driver.findElement(By.id("increase")).click();
			  
			  Driver.driver.findElement(By.id("saveQuestionDetails1")).click();  // The question is saved.
			  Thread.sleep(3000);
		 
				 Driver.driver.findElement(By.id("preview-the-image-quiz")).click();
				 Thread.sleep(3000);
				 
				 String parentHandle = Driver.driver.getWindowHandle();
				 for (String winHandle : Driver.driver.getWindowHandles()) 
				 {
					 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
					 
				 }
				 String correctAnswer = Driver.driver.findElement(By.id("1")).getAttribute("renderdata");
				 correctAnswer  =correctAnswer.substring(70,74);
				 System.out.println(correctAnswer);
				 
				 //Driver.driver.findElement(By.id("1")).click();                               // Add answers in preview.
				 Driver.driver.findElement(By.id("1")).sendKeys(correctAnswer);
				 Thread.sleep(2000);
				 
				 List<WebElement> dropdowns =  Driver.driver.findElements(By.className("sbToggle"));	// Add Units in Preview.
				 
				 dropdowns.get(0).click();
				 Thread.sleep(2000);
				 Driver.driver.findElement(By.linkText("degrees")).click();
				 Thread.sleep(2000);
				 Driver.driver.findElement(By.id("question-reveview-submit")).click();
				 Thread.sleep(3000);
				 
				 String text = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText(); 
				 if(!text.contains("You got it right."))
				 Assert.fail("Message not found-1");
				 
				 Driver.driver.close();                                         // Close the Preview Window
				 Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
		  
				 Driver.driver.findElement(By.id("preview-the-image-quiz")).click();            // Again Preview
				 Thread.sleep(3000);
				 
				 
				 for (String winHandle : Driver.driver.getWindowHandles()) 
				 {
					 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
					 
				 }
				 
				 float i = Float.parseFloat(correctAnswer);   
				 i= i+1;// Convert String to integer and again back to String.
				 System.out.println("i: " + i);
				 
				 Driver.driver.findElement(By.id("1")).sendKeys(Float.toString(i));

				 List<WebElement> dropdown =  Driver.driver.findElements(By.className("sbToggle"));	// Add Units in Preview.
				 
				 dropdown.get(0).click();
				 Thread.sleep(2000);
				 Driver.driver.findElement(By.linkText("degrees")).click();
				 Thread.sleep(2000);
				 Driver.driver.findElement(By.id("question-reveview-submit")).click();
				 Thread.sleep(3000);
				 
				 String text1 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-partial-correct']")).getText(); 
				 if(!text1.contains("You got it Partially Correct."))
				 Assert.fail("Message not found-1");
					
				 Driver.driver.close();                                         // Close the Preview Window
				 Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
	}
	catch(Exception e)
	{
		 //new Screenshot().captureScreenshotFromTestCase();
 	   	 Assert.fail("Exception in testcase tolerance in class importQTIAbsoluteValue",e);
	}
	}
	
	@Test(priority = 5,enabled = false)
	public void importQTINoToleranceAtribute()
	{
	try
	{
		 Driver.startDriver();
	     new Assignment().create(70);  
		  Thread.sleep(2000);
		  Driver.driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon
		  ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.id("addQuestion")));
			
			 Thread.sleep(3000);	
			 String filename =  ReadTestData.readDataByTagName("", "filename", "72");

			 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("import-contents-img")));
			  new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+filename+"^");
			  
			  Driver.driver.findElement(By.id("start_queue")).click(); 
			  
			  Driver.driver.findElement(By.className("proceedToViewAContent")).click();
			  Thread.sleep(2000);
			  Driver.driver.findElement(By.cssSelector("span[class='question-link-labels  ']")).click();
			  Thread.sleep(2000);
			  ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.className("question-parameters-title")));
			  Thread.sleep(2000);
			  Driver.driver.findElement(By.id("increase")).click();
			  Driver.driver.findElement(By.id("increase")).click();
			  Driver.driver.findElement(By.id("increase")).click();
			  
			  Driver.driver.findElement(By.id("saveQuestionDetails1")).click();  // The question is saved.
			  Thread.sleep(3000);
		 
				 Driver.driver.findElement(By.id("preview-the-image-quiz")).click();
				 Thread.sleep(3000);
				 
				 String parentHandle = Driver.driver.getWindowHandle();
				 for (String winHandle : Driver.driver.getWindowHandles()) 
				 {
					 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
					 
				 }
				 String correctAnswer = Driver.driver.findElement(By.id("1")).getAttribute("renderdata");
				 correctAnswer  =correctAnswer.substring(70,74);
				 System.out.println(correctAnswer);
				 
				 //Driver.driver.findElement(By.id("1")).click();                               // Add answers in preview.
				 Driver.driver.findElement(By.id("1")).sendKeys(correctAnswer);
				 Thread.sleep(2000);
				 
				 List<WebElement> dropdowns =  Driver.driver.findElements(By.className("sbToggle"));	// Add Units in Preview.
				 
				 dropdowns.get(0).click();
				 Thread.sleep(2000);
				 Driver.driver.findElement(By.linkText("degrees")).click();
				 Thread.sleep(2000);
				 Driver.driver.findElement(By.id("question-reveview-submit")).click();
				 Thread.sleep(3000);
				 
				 String text = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText(); 
				 if(!text.contains("You got it right."))
				 Assert.fail("Message not found-1");
				 
				 Driver.driver.close();                                         // Close the Preview Window
				 Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
		  
				 Driver.driver.findElement(By.id("preview-the-image-quiz")).click();            // Again Preview
				 Thread.sleep(3000);
				 
				 
				 for (String winHandle : Driver.driver.getWindowHandles()) 
				 {
					 Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
					 
				 }
				 
				 float i = Float.parseFloat(correctAnswer);   
				 i= i+1;// Convert String to integer and again back to String.
				 System.out.println("i: " + i);
				 
				 Driver.driver.findElement(By.id("1")).sendKeys(Float.toString(i));

				 List<WebElement> dropdown =  Driver.driver.findElements(By.className("sbToggle"));	// Add Units in Preview.
				 
				 dropdown.get(0).click();
				 Thread.sleep(2000);
				 Driver.driver.findElement(By.linkText("degrees")).click();
				 Thread.sleep(2000);
				 Driver.driver.findElement(By.id("question-reveview-submit")).click();
				 Thread.sleep(3000);
				 
				 String text1 = Driver.driver.findElement(By.cssSelector("div[class='evaluate-question-answer-notification evaluate-question-answer-notification-correct']")).getText(); 
				 if(!text1.contains("You got it right."))
				 Assert.fail("Message not found-1");
					
				 Driver.driver.close();                                         // Close the Preview Window
				 Driver.driver.switchTo().window(parentHandle);                // switch back to the original window
	}
	catch(Exception e)
	{
		 new Screenshot().captureScreenshotFromTestCase();
 	   	 Assert.fail("Exception in testcase tolerance in class importQTIAbsoluteValue",e);
	}
	}
	@AfterMethod
	public void TearDown()throws Exception
	{
		Driver.driver.quit();
	}
}
