package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import java.util.logging.Logger;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.FileUpload;;

public class FileTabWorking extends Driver{
	
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.FileTabWorking");
	
	
	@Test(priority=1, enabled = false) //not able to check the status of the progress bar through automation while file upload, so disabling the TC
	public void submitButtonDisableCheck()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("246");
			new Navigator().NavigateTo("Course Stream");
			driver.findElement(By.linkText("Link")).click();
			Thread.sleep(5000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id='file-upload-button']")));
			Thread.sleep(5000);
			new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+"img.jpg");
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			logger.log(Level.INFO,"File Seclected");
			boolean btndisable1=driver.findElement(By.xpath("//*[@id='post-submit-button']")).isEnabled();
			if(btndisable1)
			{
			  logger.log(Level.INFO,"Submit Button is Enabled: Testcase submitButtonDisableCheck fails");
			  Assert.fail("Submit Button is Enabled: Testcase submitButtonDisableCheck fails");
			}
			else
			{
				logger.log(Level.INFO,"Submit Button is Disabled: Testcase submitButtonDisableCheck pass");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception TestCase DefaultMessageForLinkTab in class FileTabWorking",e);	
		}
	}
		  
	@Test(priority=2, enabled = true)	
	public void checkProgressBar()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("247");
			new Navigator().NavigateTo("Course Stream");
			new FileUpload().fileUpload("247",false);
			Thread.sleep(3000);
			int progressbar= driver.findElements(By.id("fileupload-progress-bar")).size();
			if(progressbar != 1)
				Assert.fail("Progress Bar not present:");
			WebElement imgtext =  driver.findElement(By.id("fileupload-progress-bar"));
			System.out.println(imgtext.getAttribute("style"));
			
		  
		 }
		 catch(Exception e)
		{
			 e.printStackTrace();
			  Assert.fail("Exception TestCase checkProgressBar in class FileTabWorking",e);	
		}
	}
 		  
	@Test(priority=3, enabled = true)	
	public void checkDefaultText()
	{
		try
		{

			String defaulttextpresent = ReadTestData.readDataByTagName("FileTabWorking", "defaulttext", "248");
			new LoginUsingLTI().ltiLogin("248");
			new Navigator().NavigateTo("Course Stream");
			new FileUpload().fileUpload("248",false);
			Thread.sleep(3000);
			 String defaulttext = driver.switchTo().frame("iframe-user-file-input-div").findElement(By.xpath("/html/body/font")).getText();
				driver.switchTo().defaultContent();
			if(!defaulttext.equals(defaulttextpresent))
					Assert.fail("Default Text 'Provide a description for this file' is NOT Present  within the text-box");
				
		 }
		 catch(Exception e)
		{
			 e.printStackTrace();
			 Assert.fail("Exception TestCase checkDefaultText in class FileTabWorking",e);	
		}
		
	}
	
	@Test(priority=4, enabled = true)
	public void submitButtonEnableAfterUpload()
	{
		try{

				new LoginUsingLTI().ltiLogin("246");
				new Navigator().NavigateTo("Course Stream");
				driver.findElement(By.linkText("File")).click();
				Thread.sleep(5000);
				WebElement btndisable1 = driver.findElement(By.xpath("//*[@id='post-submit-button']"));
				boolean btn1=btndisable1.isEnabled();
				System.out.println(btn1);
				if(btn1==false)
				{
					logger.log(Level.INFO,"Button is disabled: Testcase submitButtonEnableAfterUpload fail");
					Assert.fail("Submit button Button is disabled: Testcase submitButtonEnableAfterUpload fail");
				}
				else
				{
					logger.log(Level.INFO,"Button is enabled: Testcase submitButtonEnableAfterUpload pass");
				}
				
				
		}
		catch(Exception e)
		{
			logger.log(Level.INFO,"Exception in TestCase submitButtonEnableAfterUpload", e);
			Assert.fail("Exception in TestCase submitButtonEnableAfterUpload", e);
		}
	}

	
}
