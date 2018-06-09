package com.snapwiz.selenium.tests.staf.orion.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;

public class CMSActions {
	
	public void searchQuestion(String questionText)
	{
		try
		{
			 Driver.driver.findElement(By.id("content-search-icon")).click(); 
			 Thread.sleep(2000);// Click on Search icon. R3.11
			 Driver.driver.findElement(By.id("content-search-field")).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.id("content-search-field")).sendKeys(questionText);
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.id("search-question-contents-icon")).click();
			 
		}
		catch(Exception e)
		{
		Assert.fail("Exception in app helper searchQuestion in class CMSActions",e);	
		}
		
	}
	
	public void BrowseQuestion(String questionText)
	{
		try
		{
			 Driver.driver.findElement(By.id("content-search-icon")).click();        
			 Driver.driver.findElement(By.id("tab-browse")).click();
			 Thread.sleep(2000);

			 Driver.driver.findElement(By.linkText("Select Content Type")).click();
			 Driver.driver.findElement(By.linkText("Search Questions")).click();
			 Thread.sleep(2000);

			 Driver.driver.findElement(By.linkText("Select Chapters")).click();
			 Thread.sleep(1000);
			 
			 
		}
		catch(Exception e)
		{
		Assert.fail("Exception in app helper searchQuestion in class CMSActions",e);	
		}
		
	}
	
	public void addQuestion(String questionType,String questionText) 
	/*
	 * questionType can be passed as 'numericEntryWithUnits' , 'mapleNumeric', 'mapleSymbolic'
	 */
	{
		try
		{
			 Driver.driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon 
			 Thread.sleep(2000);
			 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.id("addQuestion")));
			 Thread.sleep(3000);
			 if(questionType.equals("numericEntryWithUnits"))
			 {
				 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("qtn-text-entry-numeric-units-img")));  // Click on Questiontype "NumericEntry with Units.
				 
				 Driver.driver.findElement(By.id("question-raw-content")).click(); //Entering Question Text
					
				 Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questionText);   // Enter the Sample Text.
				 Driver.driver.switchTo().defaultContent();
				Thread.sleep(3000);	 
				
			 }
			 else if(questionType.equals("mapleNumeric"))
			 {
				 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("qtn-numeric-maple-img")));  // Click on Questiontype "Maple Symbolic 
					
				 Thread.sleep(3000);
				 
				 Driver.driver.findElement(By.id("question-raw-content")).click();
				
				 Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questionText);   // Enter the Sampe Text.
				 Driver.driver.switchTo().defaultContent();
				 
				 Thread.sleep(3000);
			 }
			 else if(questionType.equals("mapleSymbolic"))
			 {
				 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("qtn-math-symbolic-notation-img")));  // Click on Questiontype "Maple Symbolic 
					
				 Thread.sleep(3000);
				 
				 Driver.driver.findElement(By.id("question-raw-content")).click();
				
				 Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questionText);   // Enter the Sampe Text.
				 Driver.driver.switchTo().defaultContent();
				 
				 Thread.sleep(3000);
			 }
		}
		
		catch(Exception e)
		{
		Assert.fail("Exception in app helper addQuestion in class CMSActions",e);	
		}
		
		
	}
	
	public void duplicateQuestion()
	{
		try
		{
			 Driver.driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon
			 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.id("copyQuestion")));
			
		}
		
		catch(Exception e)
		{
		Assert.fail("Exception in app helper duplicateQuestion in class CMSActions",e);	
		}
		
		
	}

    public void navigateToQuestionNo(int questionNo){
        try{
            Driver.driver.findElement(By.linkText("Jump To Q#")).click();
            Driver.driver.findElement(By.linkText(Integer.toString(questionNo))).click();
            Thread.sleep(2000);
        }
        catch (Exception e) {
            Assert.fail("Exception in app helper navigateToQuestionNo in class CMSActions",e);
        }

    }

}
