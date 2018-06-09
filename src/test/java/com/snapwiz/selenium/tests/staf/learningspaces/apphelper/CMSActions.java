package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class CMSActions extends Driver {
	
	public void searchQuestion(String questionText)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			 driver.findElement(By.id("content-search-icon")).click();
			 Thread.sleep(2000);// Click on Search icon. R3.11
			 driver.findElement(By.id("content-search-field")).click();
			 Thread.sleep(5000);
			 driver.findElement(By.id("content-search-field")).sendKeys(questionText);
			 Thread.sleep(2000);
			 driver.findElement(By.id("search-question-contents-icon")).click();
			 
		}
		catch(Exception e)
		{
		Assert.fail("Exception in app helper searchQuestion in class CMSActions",e);	
		}
		
	}
	
	public void BrowseQuestion(String questionText)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			 driver.findElement(By.id("content-search-icon")).click();
			 driver.findElement(By.id("tab-browse")).click();
			 Thread.sleep(2000);

			 driver.findElement(By.linkText("Select Content Type")).click();
			 driver.findElement(By.linkText("Search Questions")).click();
			 Thread.sleep(2000);

			 driver.findElement(By.linkText("Select Chapters")).click();
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
		WebDriver driver=Driver.getWebDriver();
		try
		{
			 driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon
			 Thread.sleep(2000);
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click();",  driver.findElement(By.id("addQuestion")));
			 Thread.sleep(3000);
			 if(questionType.equals("numericEntryWithUnits"))
			 {
				 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("qtn-text-entry-numeric-units-img")));  // Click on Questiontype "NumericEntry with Units.
				 
				 driver.findElement(By.id("question-raw-content")).click(); //Entering Question Text
					
				 driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questionText);   // Enter the Sample Text.
				 driver.switchTo().defaultContent();
				Thread.sleep(3000);

			 }
			 else if(questionType.equals("mapleNumeric"))
			 {
				 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("qtn-numeric-maple-img")));  // Click on Questiontype "Maple Symbolic
					
				 Thread.sleep(3000);
				 
				 driver.findElement(By.id("question-raw-content")).click();
				
				 driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questionText);   // Enter the Sampe Text.
				 driver.switchTo().defaultContent();

				 Thread.sleep(3000);
			 }
			 else if(questionType.equals("mapleSymbolic"))
			 {
				 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("qtn-math-symbolic-notation-img")));  // Click on Questiontype "Maple Symbolic

				 Thread.sleep(3000);

				 driver.findElement(By.id("question-raw-content")).click();

				 driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questionText);   // Enter the Sampe Text.
				 driver.switchTo().defaultContent();

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
			WebDriver driver=Driver.getWebDriver();
			 driver.findElement(By.id("questionOptions")).click(); // Click on "+" icon
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click();",  driver.findElement(By.id("copyQuestion")));
			
		}
		
		catch(Exception e)
		{
		Assert.fail("Exception in app helper duplicateQuestion in class CMSActions",e);	
		}
		
		
	}

    public void navigateToQuestionNo(int questionNo){
        try{
			WebDriver driver=Driver.getWebDriver();
            new WebDriverWait(driver,180).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@title='Jump To Q#']"))));
            driver.findElement(By.xpath("//a[@title='Jump To Q#']")).click();
            //((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[@title='Jump To Q#']")));
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//a[@title='" + Integer.toString(questionNo) + "'])[2]")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("(//a[@title='" + Integer.toString(questionNo) + "'])[2]")));
            Thread.sleep(3000);


		}
        catch (Exception e) {
            Assert.fail("Exception in app helper navigateToQuestionNo in class CMSActions",e);
        }

    }

}
