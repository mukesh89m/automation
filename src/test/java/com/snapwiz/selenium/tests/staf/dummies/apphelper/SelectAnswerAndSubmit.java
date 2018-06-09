package com.snapwiz.selenium.tests.staf.dummies.apphelper;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;

public class SelectAnswerAndSubmit {

	public void daigonestianswersubmit(String answeroption)
	{
		try
		{
			if(Driver.driver.findElements(By.className("qtn-label")).size() > 0)
				{
				List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
				for (WebElement answerchoice: answer)
				{
					
					if(answerchoice.getText().trim().equals(answeroption))
					{
						
						answerchoice.click();
						break;
					}
				}
				}
			else if(Driver.driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)
			{
			Driver.driver.findElement(By.cssSelector("input[id='1']")).sendKeys("correct");
			}
			else if(Driver.driver.findElements(By.className("sbHolder")).size() > 0)
			{
				Driver.driver.findElement(By.className("sbToggle")).click();
				Thread.sleep(2000);
				Driver.driver.findElement(By.linkText("Value 1")).click();
				Thread.sleep(5000);
			}
		//	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div/div/label")));
		//	Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button")));
		    Thread.sleep(2000);
			int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
			if(noticesize==1)
			
			{
				String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
				if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
				{
					Driver.driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
					
				}
				
				
			}
			
		}
		catch(Exception e)
		{
			
			Assert.fail("Exception in App helper SelectAnswerAndSubmit",e);
		}
	}
	public void Adaptiveasnswersubmit(String answeroption)
	{
		try
		{
			List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
			for (WebElement answerchoice: answer)
			{
				if(answerchoice.getText().trim().equals(answeroption))
				{
					answerchoice.click();
					break;
				}
			}
			
		
			//((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElements(By.className("qtn-label")));
			Thread.sleep(3000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='button']"))); //submit
			Thread.sleep(3000);
			int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();			
			if(noticesize==1)
			
			{
				System.out.println("notice found");
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[id='submit-practice-question-button']")));
				Thread.sleep(3000);
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[id='next-practice-question-button']")));
				Thread.sleep(3000);
			}
			else
			{
				System.out.println("notice not found");
				Thread.sleep(3000);
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[id='next-practice-question-button']")));
				Thread.sleep(3000);
			}
			
		}
		catch(Exception e)
		{
			
			Assert.fail("Exception in App helper SelectAnswerAndSubmit",e);
		}
		
	}
	
	public void staticanswersubmit(String answeroption)
	{
		try
		{
			List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
			for (WebElement answerchoice: answer)
			{
				
				if(answerchoice.getText().trim().equals(answeroption))
				{
					
					answerchoice.click();
					break;
				}
			}
		//	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div/div/label")));
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type=\"button\"]")));
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type=\"button\"]")));
			Thread.sleep(2000);
			
		}
		catch(Exception e)
		{
			
			Assert.fail("Exception in App helper SelectAnswerAndSubmit",e);
		}
		
	}
	public void directAssignmentselectanswerandsubmit()
	{
		try
		{
			List<WebElement> allelement=Driver.driver.findElements(By.className("qtn-label"));//select answer
			for(WebElement element:allelement)
			{
				element.click();
				break;
			}
			Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).click();//click on submit button			
		}
		catch(Exception e)
		{
			
			Assert.fail("Exception in App helper directAssignmentselectanswerandsubmit",e);
		}
	}
	public void staticAssignment(String answeroption)
	{
		try
		{
			List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
			for (WebElement answerchoice: answer)
			{
				
				if(answerchoice.getText().trim().equals(answeroption))
				{
					
					answerchoice.click();
					break;
				}
			}
			Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).click();//click on submit button			
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			
			Assert.fail("Exception in App helper staticAssignment",e);
		}
	}
	
	public void staticAssignmentWithShowAnswer(String answeroption)
	{
		try
		{
			List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
			for (WebElement answerchoice: answer)
			{
				
				if(answerchoice.getText().trim().equals(answeroption))
				{
					
					answerchoice.click();
					break;
				}
			}
			Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).click();//click on submit button			
			Thread.sleep(2000);
			
		}
		catch(Exception e)
		{
			
			Assert.fail("Exception in App helper staticAssignment",e);
		}
	}
	
}
