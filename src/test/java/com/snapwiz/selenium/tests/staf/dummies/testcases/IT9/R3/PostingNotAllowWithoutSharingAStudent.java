package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostLinkFileTextPresent;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.Notification;


public class PostingNotAllowWithoutSharingAStudent {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.PostingNotAllowWithoutSharingAStudent");
	/*
	 * 122-129
	 */
	@Test(priority = 1, enabled = true)
	public void ByDefaultValueOfShareWithOptionIsClassSectionName()
	{
		try
		{   
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			new PostLinkFileTextPresent().postLinkFileTextPresent();	
			boolean sharevalue=Driver.driver.findElement(By.className("text--nowrap")).isDisplayed();
			System.out.println("sharevalue: "+sharevalue);
			if(sharevalue==true)
			{
				logger.log(Level.INFO,"share with text present ");
				String sharewithvalue=Driver.driver.findElement(By.className("item-text")).getText();	
				System.out.println("sharewithvalue: "+sharewithvalue);
				if(sharewithvalue.length() != 0)
				{
					logger.log(Level.INFO,"Testcase ByDefaultValueOfShareWithOptionIsClassSectionName Pass");
				}
				else
				{
					logger.log(Level.INFO,"Testcase ByDefaultValueOfShareWithOptionIsClassSectionName Fail");
					Assert.fail("By default the class section name not displayed in the text box.");
				}
			}
			else
			{
				logger.log(Level.INFO,"share with text not present ");
				Assert.fail("Share with option not present with a text box.");
			}
		}		
		catch (Exception e)
		{
			logger.log(Level.SEVERE,"Exception in DirectLogin Application Helper",e);	
			Assert.fail();
		}	 		  
	}

	/*
	 * Student should not allow to share any post with include at least one student
	 */
	@Test(priority = 2,enabled = true)
	public void ErrorMSGShownWithoutSharingAnyStudent()
	{
		try
		{
			Driver.startDriver();			
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			Thread.sleep(5000);
			new PostMessage().postMessageWithoutSubmit(new RandomString().randomstring(20));
			Driver.driver.findElement(By.className("closebutton")).click();
			Driver.driver.findElement(By.id("post-submit-button")).click();
			Thread.sleep(1000);
			int errormsg=Driver.driver.findElements(By.className("notification-message-body")).size();
			if(errormsg != 1) Assert.fail("Notification message not shown while posting without sharing with any class section or student");
			String errortext=new Notification().getNotificationMessage();
			if(!errortext.trim().equals("You need to select at least one user or a group for your post."))
			{
				Assert.fail("Message shown in notification is not as expected");
			}
		}
		catch (Exception e)
		{
			Assert.fail("Exception in TestCase ErrorMSGShownWithoutSharingAnyStudent in class PostingNotAllowWithoutSharingAStudent",e);
		}			
	}

	@AfterMethod
	public void TearDown1()throws Exception
	{
		Driver.driver.quit();
	}

}
