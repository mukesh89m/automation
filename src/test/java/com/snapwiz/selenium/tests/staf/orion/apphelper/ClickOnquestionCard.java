package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ClickOnquestionCard extends Driver
{
	public void clickonquestioncard(int questioncardindex)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			List<WebElement> allelement=driver.findElements(By.xpath("//*[starts-with(@id, 'question_card_id_')]"));
			questioncardindex=1;
			   for(WebElement elements:allelement)
			   {
				   if(questioncardindex==1)
				   {
					   boolean questionno=driver.findElement(By.className("question-card-question-no")).isDisplayed();//question number check in card					   
					   if(questionno==false)
						   Assert.fail("Question number not shown");
					   boolean questionpoint=driver.findElement(By.className("question-card-content")).isDisplayed();//question point check in card
					   if(questionpoint==false)
						   Assert.fail("Question point not shown");
					   boolean questiontimetaken=driver.findElement(By.className("question-card-time-taken")).isDisplayed();//question time taken check in card
					   if(questiontimetaken==false)
						   Assert.fail("Question time taken not shown");					   
					   elements.click();
					   Thread.sleep(3000);
					   break;
				   }
				   questioncardindex++;
			   }
			  
		}
		catch(Exception e)
		{
			Assert.fail("Exception in appHelper clickonquestioncard in class  ClickOnquestionCard",e);
		}
	}
	
	public void clickOnQuestion(int index)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			Thread.sleep(2000);
			List<WebElement> allcard=driver.findElements(By.className("question-card-question-no"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",allcard.get(index));
			Thread.sleep(2000);
            ReportUtil.log("Clicking on Question Card", "Clicked on Question card in the report", "info");
        }
		catch(Exception e)
		{
			Assert.fail("Exception in appHelper clickOnQuestion in class  ClickOnquestionCard",e);
		}
	}

}
