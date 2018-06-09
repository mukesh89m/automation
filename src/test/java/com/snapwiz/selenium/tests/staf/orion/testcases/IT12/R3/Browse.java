package com.snapwiz.selenium.tests.staf.orion.testcases.IT12.R3;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Notification;

public class Browse {
	@Test(priority = 1,enabled = true)
	public void browsequickpreviewanyquestion() {
		try
		{
			 Driver.startDriver();			 
			 new DirectLogin().CMSLogin();
			 Thread.sleep(3000);
			 new SelectCourse().selectcourse(); //Select Course Test this testcase in Biology Course Second chapter.
			 Thread.sleep(3000);
			 new SelectCourse().selectChapterByIndex(0);			//Select third chapter
			 Thread.sleep(3000);
						 
			 Driver.driver.findElement(By.id("content-search-icon")).click();        
			 Driver.driver.findElement(By.id("tab-browse")).click();
			
			 Thread.sleep(3000);

			 Driver.driver.findElement(By.linkText("Select Content Type")).click();
			 Driver.driver.findElement(By.linkText("Search Questions")).click();

			 Thread.sleep(3000);

			 Driver.driver.findElement(By.linkText("Select an option")).click();
			 Thread.sleep(1000);
			 String chapter=ReadTestData.readDataByTagName("chapternames", "chapter1", "1");
			 Driver.driver.findElement(By.linkText(chapter)).click();//click on chapter 1
			 Thread.sleep(3000);

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
		Assert.fail("Exception in Testcase browsequickpreviewanyquestion in class Browse",e);	
		}
		
	}
	
	
	@Test(priority = 2,enabled =true)
	public void browseandquickpreviewnumericentrywithunits() {
		try
		{
			Driver.startDriver();
			new Assignment().create(54);  
			Thread.sleep(3000);
			Driver.driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon
			Thread.sleep(2000);	
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.id("addQuestion")));
			Thread.sleep(2000);			 				 
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("qtn-text-entry-numeric-units-img")));  // Click on Questiontype "NumericEntry with Units.
			/*				 
			WebElement numeric = Driver.driver.findElement(By.id("qtn-text-entry-numeric-units-img"));
			numeric.click();*/
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("question-raw-content")).click(); //Entering Question Text

			Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys("Question Sample Text1");   // Enter the Sample Text.
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
				 
			List<WebElement> answertextboxes = Driver.driver.findElements(By.id("correct-ans-text"));	 //Send 3 values to 3 boxes.
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
					 
			 Driver.driver.findElement(By.id("content-search-icon")).click();    
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.id("tab-browse")).click();
			
			 Thread.sleep(3000);

			 Driver.driver.findElement(By.linkText("Select Content Type")).click();
			 Driver.driver.findElement(By.linkText("Search Questions")).click();

			 Thread.sleep(3000);

			 Driver.driver.findElement(By.linkText("Select an option")).click();
			 Thread.sleep(2000);
			 String chapter=ReadTestData.readDataByTagName("chapternames", "chapter1", "1");
			 Driver.driver.findElement(By.linkText(chapter)).click();//click on chapter 1
			 Thread.sleep(3000);

			 Driver.driver.findElement(By.linkText("Select Question Type")).click();
			 Thread.sleep(2000);
			 
			 Actions action = new Actions(Driver.driver);
			 for(int i = 0;i<=6;i++)
			 {
			 action.sendKeys(Keys.DOWN).build().perform();
			 Thread.sleep(2000);
			 }
			 action.sendKeys(Keys.ENTER).build().perform();
			 
			 Thread.sleep(3000);
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
		catch (Exception e)
		{
			Assert.fail("Exception in test case browseandquickpreviewnumericentrywithunits in class Browse",e);
		}
		
	}
	
	@Test(priority = 3,enabled =true)
	public void browseandquickpreviewmaplenumeric() {
		try
		{
			    Driver.startDriver();
				new Assignment().create(55);  
				Thread.sleep(3000);				
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
				 
				 Driver.driver.findElement(By.id("content-search-icon")).click();    
				 Thread.sleep(2000);
				 Driver.driver.findElement(By.id("tab-browse")).click();
				
				 Thread.sleep(3000);

				 Driver.driver.findElement(By.linkText("Select Content Type")).click();
				 Driver.driver.findElement(By.linkText("Search Questions")).click();

				 Thread.sleep(3000);

				 Driver.driver.findElement(By.linkText("Select an option")).click();
				 Thread.sleep(2000);
				 String chapter=ReadTestData.readDataByTagName("chapternames", "chapter1", "1");
				 Driver.driver.findElement(By.linkText(chapter)).click();//click on chapter 1
				 Thread.sleep(3000);

				 Driver.driver.findElement(By.linkText("Select Question Type")).click();
				 Thread.sleep(2000);
				 
				 Actions action = new Actions(Driver.driver);
				 for(int i = 0;i<=7;i++)
				 {
				 action.sendKeys(Keys.DOWN).build().perform();
				 Thread.sleep(2000);
				 }
				 action.sendKeys(Keys.ENTER).build().perform();
				 
				 Thread.sleep(3000);
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
		catch (Exception e)
		{
			Assert.fail("Exception in testcase browseandquickpreviewmaplenumeric in class Browse",e);
		}
		
	}
	
	@Test(priority = 4,enabled =true)
	public void browseandquickpreviewmaplesymbolic() {
		try
		{
			    Driver.startDriver();
				new Assignment().create(56);  
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
			 
				 Driver.driver.findElement(By.id("content-search-icon")).click();    
				 Thread.sleep(2000);
				 Driver.driver.findElement(By.id("tab-browse")).click();
				
				 Thread.sleep(3000);

				 Driver.driver.findElement(By.linkText("Select Content Type")).click();
				 Driver.driver.findElement(By.linkText("Search Questions")).click();

				 Thread.sleep(3000);

				 Driver.driver.findElement(By.linkText("Select an option")).click();
				 Thread.sleep(2000);
				 String chapter=ReadTestData.readDataByTagName("chapternames", "chapter1", "1");
				 Driver.driver.findElement(By.linkText(chapter)).click();//click on chapter 1
				 Thread.sleep(3000);

				 Driver.driver.findElement(By.linkText("Select Question Type")).click();
				 Thread.sleep(2000);
				 
				 Actions action = new Actions(Driver.driver);
				 for(int i = 0;i<=8;i++)
				 {
				 action.sendKeys(Keys.DOWN).build().perform();
				 Thread.sleep(2000);
				 }
				 action.sendKeys(Keys.ENTER).build().perform();
				 
				 Thread.sleep(3000);
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
		catch (Exception e)
		{
			Assert.fail("Exception in Testcase Browser in method browseandquickpreviewmaplesymbolic",e);
		}		
	}
	@AfterMethod
	public void TearDown()throws Exception
	{
		Driver.driver.quit();
	}
}
