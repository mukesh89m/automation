package com.snapwiz.selenium.tests.staf.orion.testcases.IT12.R3;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.CMSActions;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Notification;

public class Search 
{

	@Test(priority = 1, enabled=true)
	  public void searchnumericEntryWithUnits()  {
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
			 new CMSActions().addQuestion("numericEntryWithUnits","Valid Numeric entry Text for Searching2");
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

             changeFocus();

			 Thread.sleep(2000);
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
			 
			 new CMSActions().searchQuestion("Valid Numeric entry Text for Searching2");             // Used AppHelper. 
			 Thread.sleep(2000);
			 
			String checkbox1id =  Driver.driver.findElement(By.className("content-search-qtn-chk")).getAttribute("id");       // Click on CheckBox
			System.out.println("id is "+checkbox1id);
			Driver.driver.findElement(By.xpath("//label[@id='"+checkbox1id+"']")).click();
			Thread.sleep(3000);
			WebElement menuitem = Driver.driver.findElement(By.id("content-search-results-block"));               // Mousehover and then click on "Quick Preview.
			Locatable hoverItem = (Locatable) menuitem;
				Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
						mouse.mouseMove(hoverItem.getCoordinates());
						 Driver.driver.findElement(By.id("expand-question-content_"+checkbox1id+"")).click();
						 Thread.sleep(3000);
	  }
		 
		 	catch(Exception e)
		 		{
		 		new Screenshot().captureScreenshotFromTestCase();
		 		Assert.fail("Exception in testcase NumericEntry with Units in class Search",e);
		 		}
	}
	
	@Test(priority = 2, enabled=true)
	  public void searchmapleNumeric()  {
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
			 new CMSActions().addQuestion("mapleNumeric", "Search MapleNumeric Dummy Text1");
			 
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

             changeFocus();
			 	 
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
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("content-search-icon")).click();            // Click on Search icon. R3.11
			 Driver.driver.findElement(By.id("content-search-field")).click();
			 Driver.driver.findElement(By.id("content-search-field")).sendKeys("Search MapleNumeric Dummy Text1");
			 Driver.driver.findElement(By.id("search-question-contents-icon")).click();
			 Thread.sleep(2000);
			 
			 String checkbox1id =  Driver.driver.findElement(By.className("content-search-qtn-chk")).getAttribute("id");
				System.out.println("id is "+checkbox1id);
				Driver.driver.findElement(By.xpath("//label[@id='"+checkbox1id+"']")).click();
				Thread.sleep(3000);
				WebElement menuitem = Driver.driver.findElement(By.id("content-search-results-block")); 
				Locatable hoverItem = (Locatable) menuitem;
					Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
							mouse.mouseMove(hoverItem.getCoordinates());
							 Driver.driver.findElement(By.id("expand-question-content_"+checkbox1id+"")).click();	
							 Thread.sleep(3000);
		 }		 // End of try block
			 catch(Exception e)	
		       	{
				 Assert.fail("Exception in testcase MapleNumeric in class Search",e);
		       	}
		 } // End of this Method
	
	@Test(priority = 3, enabled=true)
	  public void searchmapleSymbolic()  {
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
			 new CMSActions().addQuestion("mapleSymbolic", "Search MapleSymbolic Dummy Text 2");
			 Thread.sleep(2000);
			 WebElement addmaplesymbolic = Driver.driver.findElement(By.id("addmaplesymbolic"));
			 addmaplesymbolic.click();
			 addmaplesymbolic.click();
			 addmaplesymbolic.click();
			 addmaplesymbolic.click();
			 addmaplesymbolic.click();
			 addmaplesymbolic.click();
			 Thread.sleep(2000);
			 
			 String robonotification = new Notification().getNotificationMessageFromCMS();
			 if(!robonotification.contains("You have reached maximum number of choices which can be added"))
				 Assert.fail("Robo Notification not as expected after trying adding more than three combo boxes");
			 Thread.sleep(2000);

             changeFocus();
			 	
			 List<WebElement> answertextboxes = Driver.driver.findElements(By.cssSelector("div[class='right-container math-correct-answer-input-container']"));
			                                                                                          //Send 6 values to 6 boxes in UI.
			 answertextboxes.get(0).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText1");			 
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 
			 answertextboxes.get(1).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText2");	
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 
			 answertextboxes.get(2).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText3");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 
			 answertextboxes.get(3).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText4");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 
			 answertextboxes.get(4).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText5");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
	
			 
			 answertextboxes.get(5).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys("SampleText6");
			 Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			 
             Driver.driver.findElement(By.id("done-button")).click();
			 
			 Driver.driver.findElement(By.id("saveQuestionDetails1")).click();  // The question is saved.
			 Thread.sleep(2000);
			 
			 Driver.driver.findElement(By.id("content-search-icon")).click();            // Click on Search icon. R3.11
			 Driver.driver.findElement(By.id("content-search-field")).click();
			 Driver.driver.findElement(By.id("content-search-field")).sendKeys("Search MapleSymbolic Dummy Text 2");
			 Driver.driver.findElement(By.id("search-question-contents-icon")).click();
			 Thread.sleep(2000);
			 
			 String checkbox1id =  Driver.driver.findElement(By.className("content-search-qtn-chk")).getAttribute("id");
				System.out.println("id is "+checkbox1id);
				Driver.driver.findElement(By.xpath("//label[@id='"+checkbox1id+"']")).click();
				 Thread.sleep(3000);
				WebElement menuitem = Driver.driver.findElement(By.id("content-search-results-block")); 
				Locatable hoverItem = (Locatable) menuitem;
					Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
							mouse.mouseMove(hoverItem.getCoordinates());
							 Driver.driver.findElement(By.id("expand-question-content_"+checkbox1id+"")).click();
							 Thread.sleep(3000);
		 }
			 catch(Exception e)	
		       	{
		    	   	new Screenshot().captureScreenshotFromTestCase();
		    	   	Assert.fail("Exception in testcase MapleSymbolic in class MapleSymbolic",e);
		       	}
	    }

    public void changeFocus()
    {
        try {
            Driver.driver.findElement(By.className("assessment-name")).click(); //clicking on snapwiz logo
        }
        catch (Exception e)
        {
            Assert.fail("Exception while clicking on assignment name text to change the focus while creating a question",e);
        }
    }
	
	@AfterMethod
	public void tearDown() throws Exception
	{
	Driver.driver.quit();
	}
}


