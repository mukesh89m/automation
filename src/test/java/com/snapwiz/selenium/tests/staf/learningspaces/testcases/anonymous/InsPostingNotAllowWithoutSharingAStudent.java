package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostLinkFileTextPresent;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;

public class InsPostingNotAllowWithoutSharingAStudent extends Driver{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.InsPostingNotAllowWithoutSharingAStudent");
	/*
	 * 122-129
	 */
	@Test(priority=1,enabled=true)
	public void byDefaultValueOfShareWithOptionIsClassSectionName()
	{
		try
		{   
			new LoginUsingLTI().ltiLogin("122_R24");
			new Navigator().NavigateTo("Course Stream");
			new PostLinkFileTextPresent().postLinkFileTextPresent();			
			driver.findElement(By.className("share-to-ls-label")).click();
		    boolean sharevalue=driver.findElement(By.className("text--nowrap")).isDisplayed();
			if(sharevalue==true)
			{
				logger.log(Level.INFO,"share with text present ");
				String contexttitle =  ReadTestData.readDataByTagName("InsPostingNotAllowWithoutSharingAStudent", "context_title", "122_R24");					
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
			  Assert.fail("Exception in TC byDefaultValueOfShareWithOptionIsClassSectionName in class InsPostingNotAllowWithoutSharingAStudent.", e);
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
			new LoginUsingLTI().ltiLogin("122_R24");
			new Navigator().NavigateTo("Course Stream");
			Thread.sleep(5000);
			String randomtext=new RandomString().randomstring(4);
			if(Config.browser.equals("ie"))
			{		
				new KeysSend().sendKeyBoardKeys(randomtext);
			}
			else if(Config.browser.equals("chrome"))
			{
				WebElement t=driver.findElement(By.id("iframe-user-text-input-div"));
				driver.switchTo().frame(t) ;
				driver.findElement(By.xpath("/html/body/font")).click();
				driver.findElement(By.xpath("/html/body")).sendKeys(randomtext);
				driver.switchTo().defaultContent();
			}
			else
			{
			driver.switchTo().frame("iframe-user-text-input-div").findElement(By.xpath("/html/body/font")).sendKeys(randomtext);
			driver.switchTo().defaultContent();
			}
			driver.findElement(By.className("closebutton")).click();
			driver.findElement(By.id("post-submit-button")).click();
			Thread.sleep(1000);
			int errormsg=driver.findElements(By.className("notification-message-body")).size();
			if(errormsg != 1) Assert.fail("Notification message not shown while posting without sharing with any class section or student");
			String errortext=driver.findElement(By.className("notification-message-body")).getText();
            System.out.println("errortext: "+errortext);
            if(!errortext.trim().equals("You need to select at least one user or a group for your post."))
			{
				Assert.fail("Message shown in notification is not as expected");
			}
		
	    }
		catch (Exception e)
		 {
			  logger.log(Level.SEVERE,"Exception in TestCase ErrorMSGShownWithoutSharingAnyStudent in class InsPostingNotAllowWithoutSharingAStudent",e);	
			  Assert.fail("Exception in TestCase ErrorMSGShownWithoutSharingAnyStudent in class InsPostingNotAllowWithoutSharingAStudent",e);
		 }			
	}


}
