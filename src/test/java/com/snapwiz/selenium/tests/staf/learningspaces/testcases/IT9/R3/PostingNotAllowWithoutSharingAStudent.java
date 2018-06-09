package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Notification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;


public class PostingNotAllowWithoutSharingAStudent extends Driver{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.PostingNotAllowWithoutSharingAStudent");
	/*
	 * 122-129
	 */
	@Test(priority=1,enabled=true)
	public void ByDefaultValueOfShareWithOptionIsClassSectionName()
	{
		try
		{   

			new LoginUsingLTI().ltiLogin("122");
			new Navigator().NavigateTo("Course Stream");
			new PostLinkFileTextPresent().postLinkFileTextPresent();	
		    boolean sharevalue=driver.findElement(By.className("text--nowrap")).isDisplayed();
			if(sharevalue==true)
			{
				logger.log(Level.INFO,"share with text present ");
				String contexttitle =  ReadTestData.readDataByTagName("", "context_title", "122");
				String sharewithvalue=driver.findElement(By.className("item-text")).getText();
				if(contexttitle.equals((sharewithvalue).trim()))
				{
					logger.log(Level.INFO,"Testcase ByDefaultValueOfShareWithOptionIsClassSectionName Pass");
				}
				else
				{
					logger.log(Level.INFO,"Testcase ByDefaultValueOfShareWithOptionIsClassSectionName Fail");
					Assert.fail("By default  the class section name not displayed in the text box.");
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
	@Test(priority=2,enabled=true)
	public void ErrorMSGShownWithoutSharingAnyStudent()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("122");
			new Navigator().NavigateTo("Course Stream");
			Thread.sleep(5000);
		    new PostMessage().postMessageWithoutSubmit(new RandomString().randomstring(20));
			driver.findElement(By.className("closebutton")).click();
			driver.findElement(By.id("post-submit-button")).click();
			Thread.sleep(1000);
			int errormsg=driver.findElements(By.className("notification-message-body")).size();
			if(errormsg != 1) Assert.fail("Notification message not shown while posting without sharing with any class section or student");
			String errortext=new Notification().getNotificationMessage();
			String errortextverify =  ReadTestData.readDataByTagName("", "errortextverify", "122");
			if(!errortext.trim().equals(errortextverify))
			{
				Assert.fail("Message shown in notification is not as expected");
			}
		
	    }
		catch (Exception e)
		 {
			  Assert.fail("Exception in TestCase ErrorMSGShownWithoutSharingAnyStudent in class PostingNotAllowWithoutSharingAStudent",e);
		 }			
	}
	


}
