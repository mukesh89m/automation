package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;


public class VerifyStudentGetNotificationWhenQuitTheDaigonesticTest extends Driver
{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.VerifyStudentGetNotificationWhenQuitTheDaigonesticTest");
	/*
	 * 1075-1078
	 */
	@Test
	public void NotificationWhenQuitTheDaigonesticTest()
	{
		try
		{
			driver.manage().deleteAllCookies();
			new LoginUsingLTI().ltiLogin("1075");
			new SelecteTextBook().etextselector();
			new ExpandFirstChapter().expandFirstChapter();
			Thread.sleep(5000);			
			//new  TOCShow().tocShow();
			Thread.sleep(5000);
			
			new DiagnosticTest().startTest(1);
			
			
			driver.findElement(By.cssSelector("div[class='al-quit-diag-test-icon']")).click();
			Thread.sleep(5000);
			
			boolean notificationvalue=driver.findElement(By.className("al-notification-message-header")).isDisplayed();
			boolean continuelater=driver.findElement(By.className("al-diag-test-continue-later")).isDisplayed();
			boolean quittext=driver.findElement(By.className("al-diag-test-quit-block")).isDisplayed();
			boolean closelinkofnotification=driver.findElement(By.className("close-practice-test-notification")).isDisplayed();
			if(notificationvalue==true && continuelater==true && quittext==true && closelinkofnotification==true)
			{
				driver.findElement(By.className("al-diag-test-submit-button")).click();
				Thread.sleep(5000);
				driver.findElement(By.className("al-diag-test-submit-button")).click();
				Thread.sleep(5000);
				driver.findElement(By.cssSelector("div[class='al-quit-diag-test-icon']")).click();
				Thread.sleep(5000);
				
				boolean notificationvalue1=driver.findElement(By.className("al-notification-message-header")).isDisplayed();
				boolean continuelater1=driver.findElement(By.className("al-diag-test-continue-later")).isDisplayed();
				boolean quittext1=driver.findElement(By.className("al-diag-test-quit-block")).isDisplayed();
				boolean closelinkofnotification1=driver.findElement(By.className("close-practice-test-notification")).isDisplayed();
				if(notificationvalue1==true && continuelater1==true && quittext1==true && closelinkofnotification1==true)
				{
					
					driver.findElement(By.className("al-diag-test-continue-later")).click();
					Thread.sleep(5000);
					new DiagnosticTest().startTest(2);
					new AttemptTest().Diagonestictest();
					Thread.sleep(5000);
					new TOCShow().tocShow();
					new TopicOpen().topicOpen("Personalized Practice");
					
					new AttemptTest().AdaptiveTest(5);
					driver.findElement(By.cssSelector("div[class='al-quit-diag-test-icon']")).click();
					try
					{
						int continuelater2=driver.findElements(By.className("al-diag-test-continue-later")).size();
						int quittext2=driver.findElements(By.className("al-diag-test-quit-block")).size();
						if(continuelater2>0 && quittext2>0)
						{
							Assert.fail("Continue later and quit shown after quit the adaptive test in middle");

							
						}
						else
						{
							logger.log(Level.INFO,"Continue later and quit not shown after quit the adaptive test in middle");
						}
						
					}
					catch(Exception e)
					{
						logger.log(Level.INFO,"Continue later and quit not shown after quit the adaptive test in middle",e);
					}
					
					
					
					
				}
				else
				{
					logger.log(Level.INFO,"Notiication of continue-later and Quit test after few question attempt not dispaly.");
					Assert.fail("Notiication of continue-later and Quit test  after few question attempt not dispaly.");
				}
			}
			else
			{
				logger.log(Level.INFO,"Notiication of continue-later and Quit test not dispaly.");
				Assert.fail("Notiication of continue-later and Quit test not dispaly.");
			}
			
		}
		catch(Exception e)
	    {
				  logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper",e);				 
				  Assert.fail("",e);
				  
		}
	}

}
