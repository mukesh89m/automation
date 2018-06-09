package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class InsAllowFileSize extends Driver{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.AllowFileSize");
	/*
	 * 261-263
	 */
	@Test(priority=1,enabled=true)
	public void LessThan10MbFile()
	{
		try
		{
			   new LoginUsingLTI().ltiLogin("263_R24");
			   new Navigator().NavigateTo("Course Stream");
			   driver.findElement(By.className("share-to-ls-label")).click();
			   boolean uploadstatus = new FileUpload().fileUploadAndValidate("263_R24");
			   if(uploadstatus == false)
				  Assert.fail("Error in uploading file with size less than 10MB");
				
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper",e);			  
			Assert.fail("Exception  in testcase LessThan10MbFile in class AllowFileSize",e);
		}
	}
	 
	 
	@Test(priority=2,enabled=false)
	public void Upload10MbFile()
	{
		try
		{
			   new LoginUsingLTI().ltiLogin("264_R24");
			   new Navigator().NavigateTo("Course Stream");
			   driver.findElement(By.className("share-to-ls-label")).click();
			   new FileUpload().fileUpload("264_R24",true);
			   String filename = ReadTestData.readDataByTagName("AllowFileSize", "filename", "264_R24");
			  	  int errmsg=driver.findElements(By.className("notification-message-body")).size();
					  if(errmsg>0)
					  {
						  Thread.sleep(3000);
						  String notificationtext = driver.findElement(By.className("notification-message-body")).getText();
						  if(!notificationtext.equals("Your file upload request is being processed...."))
							Assert.fail("Error during uploading file of size 10MB");
						
					  }			
			    Thread.sleep(3000);
			    driver.navigate().refresh();
			    new Navigator().NavigateTo("Course Stream");
				String fileuploadedname = (driver.findElement(By.cssSelector("ul[class='ls-stream-post__attachments']")).getText());
				System.out.println(fileuploadedname);
				System.out.println(filename);
				if(!fileuploadedname.equals(filename))
					Assert.fail("Uploaded file of size 10MB not found");				
					
					  String shrvalue=driver.findElement(By.className("ls-stream-post__action")).getText();
					   if(!shrvalue.trim().equals("shared a file"))
					  Assert.fail("Text Shared a file not found after sharing file of 10MB");
				
		}
		catch(Exception e)
		   {
				  logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper",e);
				  Assert.fail("Exception  in testcase Upload10MbFile in class AllowFileSize",e);
				  
		   }
		
	}
	 
	@Test(priority=3,enabled=false)
	public void UploadMoreThan10MBFile()
	{
		try
		   {
			   new LoginUsingLTI().ltiLogin("265_R24");
			   new Navigator().NavigateTo("Course Stream");
			   driver.findElement(By.className("share-to-ls-label")).click();
			   boolean uploadstatus = new FileUpload().fileUploadAndValidate("265_R24");
			   if(uploadstatus == true)
					  Assert.fail("File of Size more than 10MB got uploaded");
				  
		   }
		   catch(Exception e)
		   {
				  logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper",e);
				  Assert.fail("Exception  in testcase UploadMoreThan10MBFile in class AllowFileSize",e);
				  
		   }
	}


}
