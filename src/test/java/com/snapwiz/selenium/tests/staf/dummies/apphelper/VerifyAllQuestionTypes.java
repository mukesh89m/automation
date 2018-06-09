package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;

public class VerifyAllQuestionTypes 
{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.apphelper.VerfyAllQuestionTypes");
	
	public boolean startDiagTestFirstQuestionShouldGetDelivered()
	{
		try
		{
			String questionNumber = Driver.driver.findElement(By.cssSelector("div[class='al-diag-chapter-details']")).getText();
			if(questionNumber.trim().contains("1 of"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			
			logger.log(Level.SEVERE,"Exception in VerfyAllQuestionTypes",e);
			Assert.fail("Exception in VerfyAllQuestionTypes",e);
			return false;
		}
	}
	public boolean verifyTrueFalseTypeQuestion()
	{
		int testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();
		
		while(testend==1)
		{
			try
			{
				Thread.sleep(3000);
				testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();
				if(testend == 0)
				break;	
				List<WebElement> list = Driver.driver.findElements(By.className("answer-choice-content"));
				
				String choice1 = list.get(0).getText();
				
				String choice2 = list.get(1).getText();
				if(choice1.equals("True") && choice2.equals("False"))
				{
					Driver.driver.findElement(By.className("qtn-label")).click();
					Thread.sleep(5000);
					Driver.driver.findElement(By.className("al-diag-test-submit-button")).click();
					Thread.sleep(5000);
					return true;
				}
				
				Thread.sleep(5000);
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button")));
			
			}
			catch(Exception e)
			{
				Assert.fail("Exception in VerfyAllQuestionTypes",e);
				return false;
			}
		}
		return false;
		
	}
	public boolean firstQuestionShouldGetDeliveredForAdaptive()
	{
		try
		{
			String questionNumber = Driver.driver.findElement(By.cssSelector("div[class='al-diag-test-question-raw-content']")).getText();
			if(questionNumber.trim().equals(""))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		catch(Exception e)
		{
			
			logger.log(Level.SEVERE,"Exception in VerfyAllQuestionTypes",e);
			Assert.fail("Exception in VerfyAllQuestionTypes",e);
			return false;
		}
	}
	public boolean verifyTrueFalseTypeQuestionForAdaptive()
	{

		Boolean found=false;
		for(int i=0;i<50;i++)
		{
			try
			{
				Thread.sleep(3000);
				List<WebElement> list = Driver.driver.findElements(By.className("answer-choice-content"));
				
				String choice1 = list.get(0).getText();
				
				String choice2 = list.get(1).getText();
				if((choice1.trim().equals("True") && choice2.trim().equals("False")) || (choice1.trim().equals("False") && choice2.trim().equals("True")))
				{
					found=true;
					
					Driver.driver.findElement(By.className("qtn-label")).click();
					Thread.sleep(3000);
					Driver.driver.findElement(By.id("submit-practice-question-button")).click();
					Thread.sleep(3000);
					int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
					
					if(noticesize==1)
					
					{
						String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
						
						if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
						{
							Driver.driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
							
						}
						
						
					}
					Driver.driver.findElement(By.id("next-practice-question-button")).click();
					Thread.sleep(3000);			
					int noticesize1 = Driver.driver.findElements(By.className("al-notification-message-body")).size();
					if(noticesize1 == 1)
						
					{
						String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
						System.out.println(notice);
						if(notice.equals("Click on the \"Stream\" tab to make your own personal notes and and discussions with your class."))
						{
							Driver.driver.findElement(By.id("next-practice-question-button")).click();
						}
						
						
					}
					
							
					break;
					
				}
				else
				{
				
				Driver.driver.findElement(By.id("submit-practice-question-button")).click();
				Thread.sleep(3000);
				int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
				
				if(noticesize==1)
				
				{
					String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
					
					if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
					{
						Driver.driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
						
					}
					
					
				}
				Driver.driver.findElement(By.id("next-practice-question-button")).click();
				Thread.sleep(5000);
				int noticesize1 = Driver.driver.findElements(By.className("al-notification-message-body")).size();
				if(noticesize1 == 1)
					
				{
					String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
					System.out.println(notice);
					if(notice.equals("Click on the \"Stream\" tab to make your own personal notes and and discussions with your class."))
					{
						Driver.driver.findElement(By.id("next-practice-question-button")).click();
					}
					
					
				}
				}
				
			}
			catch(Exception e)
			{
				Assert.fail("Exception in VerfyAllQuestionTypes",e);
				found= false;
			}
		}
		return found;
		
	}
	public boolean verifyTrueFalseTypeQuestionForStatic()
	{
		int testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();
		Boolean found=false;
		while(testend==1)
		{
			try
			{
				Thread.sleep(3000);
				testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();
				if(testend == 0)
				break;	
				List<WebElement> list = Driver.driver.findElements(By.className("answer-choice-content"));
				
				String choice1 = list.get(0).getText();
				
				String choice2 = list.get(1).getText();
				if(choice1.trim().equals("True") && choice2.trim().equals("False"))
				{
					found=true;
					
					Driver.driver.findElement(By.className("qtn-label")).click();
					Thread.sleep(5000);
					Driver.driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
					Thread.sleep(5000);
					Driver.driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
					Thread.sleep(5000);	
				    break;
				}
				else
				{
				Driver.driver.findElement(By.className("qtn-label")).click();
				Thread.sleep(5000);
				Driver.driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
				Thread.sleep(5000);
				Driver.driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
				Thread.sleep(5000);	
				}
			}
			catch(Exception e)
			{
				Assert.fail("Exception in VerfyAllQuestionTypes",e);
				found= false;
			}
		}
		return found;
		}

}