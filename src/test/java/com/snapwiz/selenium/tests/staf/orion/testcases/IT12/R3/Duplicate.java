package com.snapwiz.selenium.tests.staf.orion.testcases.IT12.R3;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.CMSActions;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Notification;

public class Duplicate {



	 @Test                         
	  public void duplicate()  
	 {
		 try
		 {
			 Driver.startDriver();			                             // Test in orion 212 course. 
			 new DirectLogin().CMSLogin();
			 Thread.sleep(1000);
			 new SelectCourse().selectcourse();
			 Thread.sleep(1000);
			 new SelectCourse().selectChapterByIndex(0);		
			 new SelectCourse().selectAssessmentByIndex(1);
			 Thread.sleep(1000);
			 new CMSActions().addQuestion("mapleNumeric","Question Sample Text1 for maple numeric");
			 Thread.sleep(1000);
			 WebElement addmaplenumeric = Driver.driver.findElement(By.id("addmaplenumeric"));
			 addmaplenumeric.click();
			 addmaplenumeric.click();
			 addmaplenumeric.click();
			 addmaplenumeric.click();
			 addmaplenumeric.click();
			 addmaplenumeric.click();
			 Thread.sleep(1000);

			 String robonotification = new Notification().getNotificationMessageFromCMS();
			 if(!robonotification.contains("You have reached maximum number of choices which can be added"))
				 Assert.fail("Robo Notification not as expected after trying adding more than three combo boxes");
			 Thread.sleep(1000);
			 
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
			 Thread.sleep(1000);
				 
			 new CMSActions().duplicateQuestion();
			 Thread.sleep(3000);
				 
			 String questionStatus = Driver.driver.findElement(By.cssSelector("a[title='Draft']")).getText();
		
			 if(!questionStatus.equals("Draft")) 
				 Assert.fail("Question Status is not draft");
				 Thread.sleep(3000);
			
				 new CMSActions().addQuestion("mapleSymbolic","Question Sample Text2 for maple numeric");               // New Maple symbolic Question
				 Thread.sleep(2000);
			 WebElement addmaplesymbolic = Driver.driver.findElement(By.id("addmaplesymbolic"));
			 addmaplesymbolic.click();
			 addmaplesymbolic.click();
			 addmaplesymbolic.click();
			 addmaplesymbolic.click();
			 addmaplesymbolic.click();
			 addmaplesymbolic.click();
			 Thread.sleep(2000);
			 
			 String robonotification1 = new Notification().getNotificationMessageFromCMS();
			 if(!robonotification1.contains("You have reached maximum number of choices which can be added"))
				 Assert.fail("Robo Notification not as expected after trying adding more than three combo boxes");
			 Thread.sleep(2000);
			  
			 new Search().changeFocus();
			 	
			 List<WebElement> answertextboxes1 = Driver.driver.findElements(By.cssSelector("div[class='right-container math-correct-answer-input-container']"));
			 		
			                                                                                          //Send 6 values to 6 boxes in UI.
			 answertextboxes1.get(0).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText1");			 
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 
			 answertextboxes1.get(1).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText2");	
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 
			 answertextboxes1.get(2).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText3");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 
			 answertextboxes1.get(3).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText4");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 
			 answertextboxes1.get(4).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText5");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
	
			 
			 answertextboxes1.get(5).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText6");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			
             Driver.driver.findElement(By.id("done-button")).click();
			 
			 Driver.driver.findElement(By.id("saveQuestionDetails1")).click();  // The question is saved.
			 Thread.sleep(2000);	
			 
			 new CMSActions().duplicateQuestion();
			 
			 String questionStatus1 = Driver.driver.findElement(By.cssSelector("a[title='Draft']")).getText();
				
			 if(!questionStatus1.equals("Draft")) 
				 Assert.fail("Question Status is not draft");
				 Thread.sleep(3000);
			 
			 new CMSActions().addQuestion("numericEntryWithUnits","Question Sample Text3 for maple numeric");               // New Numeric entry with units questions.
			 Thread.sleep(2000);
			 
			 WebElement addtextboxwithunits = Driver.driver.findElement(By.id("addtextboxwithunits"));
			 addtextboxwithunits.click();
			 addtextboxwithunits.click();
			 addtextboxwithunits.click();
			 Thread.sleep(1000);
		
			 String robonotification2 = new Notification().getNotificationMessageFromCMS();
			 if(!robonotification2.contains("You have reached maximum number of choices which can be added"))
				 Assert.fail("Robo Notification not as expected after trying adding more than three combo boxes");
			 Thread.sleep(1000);
			 
			 new Search().changeFocus();
			 
			 List<WebElement> answertextboxes2 = Driver.driver.findElements(By.id("correct-ans-text"));			 //Send 3 values to 3 boxes.
			 answertextboxes2.get(0).sendKeys("10");
			 answertextboxes2.get(1).sendKeys("20");
			 answertextboxes2.get(2).sendKeys("30");
			 Thread.sleep(1000);			 		 
			 
			 List<WebElement> addmorelinks =  Driver.driver.findElements(By.id("add-more-entry"));  // To send units .
			 
			 addmorelinks.get(0).click();			 
			 Driver.driver.findElement(By.xpath("//div[@id='cms-qtextentry-more-units-container']/ul/li[2]")).click();  //feet
			 
			 Thread.sleep(1000);
			 
			 addmorelinks.get(0).click();	
			 Driver.driver.findElement(By.xpath("//div[@id='cms-qtextentry-more-units-container']/ul/li[3]")).click();  //yards
			 
			 Thread.sleep(1000);
			 addmorelinks.get(1).click();	
			 Driver.driver.findElement(By.xpath("(//div[@id='cms-qtextentry-more-units-container']/ul/li[4])[2]")).click();  //
			 
			 Thread.sleep(1000);
			 addmorelinks.get(1).click();
			 Driver.driver.findElement(By.xpath("(//div[@id='cms-qtextentry-more-units-container']/ul/li[5])[2]")).click();  //
			 
			 addmorelinks.get(2).click();	
			 Driver.driver.findElement(By.xpath("(//div[@id='cms-qtextentry-more-units-container']/ul/li[6])[3]")).click();  //
			 Thread.sleep(1000);
			 
			 addmorelinks.get(2).click();	
			 Driver.driver.findElement(By.xpath("(//div[@id='cms-qtextentry-more-units-container']/ul/li[1])[3]")).click();     
			 Driver.driver.findElement(By.id("unit-name-edit-entry-1")).sendKeys("cm");
			 Thread.sleep(1000);
			 
		     Driver.driver.findElement(By.id("done-button")).click();
			 
			 Driver.driver.findElement(By.id("saveQuestionDetails1")).click();  // The question is saved.
			 Thread.sleep(1000);
			 
			 new CMSActions().duplicateQuestion();
	 		 
			 String questionStatus2 = Driver.driver.findElement(By.cssSelector("a[title='Draft']")).getText();
				
			 if(!questionStatus2.equals("Draft")) 
				 Assert.fail("Question Status is not draft");
				 Thread.sleep(3000);
			
		 }
	       catch(Exception e)	
	       	{
	    	   Assert.fail("Expection",e);
	       	}
		}
	 
	 @AfterMethod
		public void tearDown() throws Exception
		{
			Driver.driver.quit();
		}
	}