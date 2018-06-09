package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectAnswerAndSubmit;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class VerifyProficiencyForPracticeTestAfterDiagnosticTest {
@Test	
	public void verifyProficiencyForPracticeTestAfterDiagnosticTest()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("96"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diag test for 1st chapter
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);
			Driver.driver.findElement(By.cssSelector("img[title=\"ORION Dashboard\"]")).click();
			Thread.sleep(3000);
			List<String> stringarray = new ArrayList<String>();
			List<WebElement> allProficiency = Driver.driver.findElements(By.className("al-proficiency-percentage"));
			for (WebElement element: allProficiency) 
			{
				stringarray.add(element.getText());
				
			}	
		    String [] proficiency = stringarray.toArray(new String[stringarray.size()]);
			WebElement TLO = Driver.driver.findElement(By.className("al-preformance-text")); 
			Locatable hoverItem = (Locatable)TLO;
			Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
			mouse.mouseMove(hoverItem.getCoordinates());
			//click on 1st TLO of chapter 2
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.cssSelector("div[title='Practice']")));	
			Thread.sleep(3000);
			new SelectAnswerAndSubmit().practiceTestAttempt(3, 10, "incorrect",false,false);
			Driver.driver.findElement(By.cssSelector("img[title=\"ORION Dashboard\"]")).click();
			Thread.sleep(3000);
			stringarray.clear();
			List<WebElement> allProficiency1 = Driver.driver.findElements(By.className("al-proficiency-percentage"));
			for(WebElement element: allProficiency1)
					{
						stringarray.add(element.getText());
					}
			String [] proficiency1 = stringarray.toArray(new String[stringarray.size()]);
			boolean verify = true;
			if (proficiency.length == proficiency1.length)
			   {
			      for (int i = 0; i < proficiency.length; i++)
			       {
			    	  String str = proficiency[i].substring(0, 2);
			    	  int prof = Integer.parseInt(str);
			    	  String str1 = proficiency1[i].substring(0, 2);
			    	  int prof1 = Integer.parseInt(str1);
			          if (prof <= prof1)
			          {
			        	 verify = false; 
			          }
			          else
			        	  verify = true; 
			       }
			    }
			   
			if(verify == false)
				Assert.fail("After the Diag test correctly, On attempting practice test incorrectly, the proficiecy level doesn't get down.");
			
			
			//again attempt practice test correctly
			WebElement TLO1 = Driver.driver.findElement(By.className("al-preformance-text")); 
			Locatable hoverItem1 = (Locatable)TLO1;
			Mouse mouse1 = ((HasInputDevices) Driver.driver).getMouse();
			mouse1.mouseMove(hoverItem1.getCoordinates());
			//click on 1st TLO of chapter 1
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.cssSelector("div[title='Practice']")));	
			Thread.sleep(3000);
			new SelectAnswerAndSubmit().practiceTestAttempt(3, 20, "correct",false,false);
			Driver.driver.findElement(By.cssSelector("img[title=\"ORION Dashboard\"]")).click();
			Thread.sleep(3000);
			stringarray.clear();
			List<WebElement> allProficiency2 = Driver.driver.findElements(By.className("al-proficiency-percentage"));
			for(WebElement element: allProficiency2)
					{
						stringarray.add(element.getText());
					}
			String [] proficiency2 = stringarray.toArray(new String[stringarray.size()]);
			boolean verify1 = true;
			if (proficiency1.length == proficiency2.length)
			   {
			      for (int i = 0; i < proficiency1.length; i++)
			       {
			    	  String str = proficiency1[i].substring(0, 2);
			    	  int prof = Integer.parseInt(str);
			    	  System.out.println("prof: "+prof);
			    	  String str1 = proficiency2[i].substring(0, 2);
			    	  int prof1 = Integer.parseInt(str1);
			    	  System.out.println("prof1: "+prof1);
			          if (prof < prof1)
			          {
			        	 verify1 = false; 
			          }
			          else
			        	  verify1 = true; 
			       }
			    }
			if(verify1 == true)
				Assert.fail("On attempting practice test correctly, the proficiecy level doesn't get up.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifyProficiencyForPracticeTestAfterDiagnosticTest in class VerifyProficiencyForPracticeTestAfterDiagnosticTest",e);
		}
	}
@AfterMethod
public void tearDown() throws Exception
{
	Driver.driver.quit();
}
}
