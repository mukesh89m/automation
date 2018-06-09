package com.snapwiz.selenium.tests.staf.orion.apphelper;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;

public class FileUpload {
	
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.orion.apphelper.FileUpload");
	public void fileUpload(String dataIndex,boolean upload)
	{
		try
		{	
			String filename =  ReadTestData.readDataByTagName("", "filename", dataIndex);
			 logger.log(Level.INFO,"Starting File Upload");
			 Driver.driver.findElement(By.linkText("File")).click();
			 //((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/ul/li[3]/a")));
			 Thread.sleep(2000);
			 if(Config.browser.equals("ie") || Config.browser.equals("chrome"))
			 Driver.driver.findElement(By.xpath("//*[@id='file-upload-button']")).click();
			 else
			 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("//*[@id='file-upload-button']")));
			 Thread.sleep(2000);
			 new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+filename+"^");
			 Thread.sleep(20000);
			 if(upload == true)
			 {
			 Driver.driver.findElement(By.id("post-submit-button")).click();
			 Thread.sleep(10000);
			 }
			 logger.log(Level.INFO,"File Upload Completed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in app helper fileUpload in class FileUpload",e);
		}
	}
	
	public boolean fileUploadAndValidate(String dataIndex)
	{
		boolean uploaded = true;
		try
		{
			   String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);
			   Driver.driver.findElement(By.linkText("File")).click();
			   Thread.sleep(2000);
			   if(Config.browser.equals("ie") || Config.browser.equals("chrome"))
					 Driver.driver.findElement(By.xpath("//*[@id='file-upload-button']")).click();
					 else
					 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("//*[@id='file-upload-button']")));
			   Thread.sleep(5000);
			   Robot robot = new Robot();
			   new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+filename);
			   Thread.sleep(1000);
			   robot.keyPress(KeyEvent.VK_ENTER);
			   robot.keyRelease(KeyEvent.VK_ENTER);
			   Thread.sleep(2000);
			  	  int errmsg=Driver.driver.findElements(By.className("notification-message-body")).size();
					  if(errmsg>0)
					  {
						  
						  String notificationtext = Driver.driver.findElement(By.className("notification-message-body")).getText();
						  if(!notificationtext.equals("Your file upload request is being processed...."))
							 uploaded = false;
						
					  }
				if(uploaded == true)
				{
				System.out.println("Clicking on submit");
			    Thread.sleep(15000);
			    Driver.driver.findElement(By.id("post-submit-button")).click();
			    Thread.sleep(15000);
				String fileuploadedname = (Driver.driver.findElement(By.cssSelector("div[class='ls-media__body media_file_link']")).getText());
				if(!fileuploadedname.equals(filename))
					uploaded=false;
				}
				if(uploaded == true)
				{
					System.out.println("Verifying filename");
					  String shrvalue=Driver.driver.findElement(By.className("ls-stream-post__action")).getText();
					   if(!shrvalue.trim().equals("shared a file"))
					   {
						   uploaded=false;				 
					   }
				}
			 
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper fileUploadValidate in class FileUpload",e);
		}
		return uploaded;
	}
	
	public boolean fileUploadNotificationMessageValidate(String dataIndex,String notificationMessage,boolean uploadFile)
	{
		boolean uploaded = true;
		try
		{
			   String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);
			   Driver.driver.findElement(By.linkText("File")).click();
			   
			   if(Config.browser.equals("ie") || Config.browser.equals("chrome"))
					 Driver.driver.findElement(By.xpath("//*[@id='file-upload-button']")).click();
					 else
					 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("//*[@id='file-upload-button']")));
			   Thread.sleep(3000);
			   Robot robot = new Robot();
			   new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+filename);
			   Thread.sleep(1000);
			   robot.keyPress(KeyEvent.VK_ENTER);
			   robot.keyRelease(KeyEvent.VK_ENTER);
			   Thread.sleep(2000);
			  	  int errmsg=Driver.driver.findElements(By.className("notification-message-body")).size();
					  if(errmsg>0)
					  {						
						  String notificationtext = Driver.driver.findElement(By.className("notification-message-body")).getText();		
						  System.out.println(notificationtext);
						  if(!notificationtext.equals(notificationMessage))
							 return false;						
					  }
					  else
					  {
						  Assert.fail("Notification Message during file upload did not appear.");
					  }
					  if(uploadFile == true)
					  {
						  	Thread.sleep(15000);
						  	Driver.driver.findElement(By.id("post-submit-button")).click();
						  	Thread.sleep(15000);
						  	String fileuploadedname = (Driver.driver.findElement(By.cssSelector("div[class='ls-media__body media_file_link']")).getText());
						  	if(!fileuploadedname.equals(filename))
						  		uploaded=false;
					  }
			 
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper fileUploadValidate in class FileUpload",e);
		}
		return uploaded;
	}
	
	public boolean validateFilePosted(String fileNameToValidate)
	{
		boolean filefound = false;
		try
		{
		Thread.sleep(5000);
		List<WebElement> links = Driver.driver.findElements(By.className("ls-stream-post__attachments"));
		for(WebElement linktext : links)
			{
			if(linktext.getText().trim().equals(fileNameToValidate))
					{
						System.out.println("File Found in using attachements classname");
						filefound = true;
						break;
					}
			
			}
		if(filefound == false)
		{
			links = Driver.driver.findElements(By.cssSelector("div[class='ls-media__body media_file_link']"));
			for(WebElement linktext : links)
				{
				if(linktext.getText().trim().equals(fileNameToValidate))
						{
							System.out.println("File Found in using media_file_link classname");
							filefound = true;
							break;
						}
				
				}
		}
		
		if(filefound == true)
		{
			System.out.println("Validating the shared a file string");
			WebElement posttext = Driver.driver.findElement(By.className("ls-stream-post__action"));
		
				if(!posttext.getText().trim().equals("shared a file"))
						{								
							filefound = false;
						}				
		}
		
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper validateLink in class PostMessageValidate",e);
		}
		return filefound;
	}
}
