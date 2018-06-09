package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.ReadTestData;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;

public class AllowFileSize {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.AllowFileSize");
	/*
	 * 261-263
	 */
	@Test(priority=1,enabled=true)
	public void LessThanTenMbFile()
	{
		try
		{
			   Driver.startDriver();
			   String read = ReadTestData.readDataByTagName("", "filename", "263");
			   System.out.println("read: "+read);
			   new UserCreate().CreateStudent("aa", "");//create student
			   new DirectLogin().studentLogin("aa");
			   new Navigator().NavigateTo("Course Stream");
			   boolean uploadstatus = new FileUpload().fileUploadAndValidate("263");
			   if(uploadstatus == false)
				  Assert.fail("Error in uploading file with size less than 10MB");
				
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper",e);			  
			Assert.fail("Exception  in testcase LessThan10MbFile in class AllowFileSize",e);
		}
	}
	 
	 
	@Test(priority=2,enabled=true)
	public void UploadTenMbFile()
	{
		try
		{
			   Driver.startDriver();			 
			   new UserCreate().CreateStudent("aa", "");//create student
			   new DirectLogin().studentLogin("aa");
			   new Navigator().NavigateTo("Course Stream");
			   new FileUpload().fileUpload("264",true);
			   String filename = ReadTestData.readDataByTagName("AllowFileSize", "filename", "264");
			  	  int errmsg=Driver.driver.findElements(By.className("notification-message-body")).size();
					  if(errmsg>0)
					  {
						  Thread.sleep(3000);
						  String notificationtext = Driver.driver.findElement(By.className("notification-message-body")).getText();
						  if(!notificationtext.equals("Your file upload request is being processed...."))
							Assert.fail("Error during uploading file of size 10MB");
						
					  }			
				
			    Thread.sleep(90000);
				String fileuploadedname = (Driver.driver.findElement(By.cssSelector("ul[class='ls-stream-post__attachments']")).getText());
				System.out.println(fileuploadedname);
				System.out.println(filename);
				if(!fileuploadedname.equals(filename))
					Assert.fail("Uploaded file of size 10MB not found");				
					
					  String shrvalue=Driver.driver.findElement(By.className("ls-stream-post__action")).getText();
					   if(!shrvalue.trim().equals("shared a file"))
					  Assert.fail("Text Shared a file not found after sharing file of 10MB");
				
		}
		catch(Exception e)
		   {
				  logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper",e);
				  Assert.fail("Exception  in testcase Upload10MbFile in class AllowFileSize",e);
				  
		   }
		
	}
	 
	@Test(priority=3,enabled=true)
	public void UploadMoreThanTenMBFile()
	{
		try
		   {
			   Driver.startDriver();			 
			   new UserCreate().CreateStudent("aa", "");//create student
			   new DirectLogin().studentLogin("aa");
			   new Navigator().NavigateTo("Course Stream");
			   Driver.driver.findElement(By.className("share-to-ls-label")).click();
			   boolean uploadstatus = new FileUpload().fileUploadAndValidate("265");
			   if(uploadstatus == true)
					  Assert.fail("File of Size more than 10MB got uploaded");
				  
		   }
		   catch(Exception e)
		   {
				  logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper",e);
				  Assert.fail("Exception  in testcase UploadMoreThan10MBFile in class AllowFileSize",e);
				  
		   }
	}
	@AfterMethod
	  public void tearDown() throws Exception 
	  {
	     Driver.driver.quit();    
	  }
	
}
