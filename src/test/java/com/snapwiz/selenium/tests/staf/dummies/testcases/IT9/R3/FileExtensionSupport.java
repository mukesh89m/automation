package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.snapwiz.selenium.tests.staf.dummies.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.Config;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.ReadTestData;

public class FileExtensionSupport {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.FileExtensionSupport");
    /*
     * 254-260
     */	
	@Test(priority=1,enabled=true)
	public void UploadFileWhichNotSupport()//try to upload file which not support
	{
	   try
	   {
		   Driver.startDriver();
		   String notificationmessage = ReadTestData.readDataByTagName("FileExtensionSupport", "notificationmessage", "254");
		   new UserCreate().CreateStudent("aa", "");//create student
		   new DirectLogin().studentLogin("aa");
		   new Navigator().NavigateTo("Course Stream");
		   Boolean notificationflag = new FileUpload().fileUploadNotificationMessageValidate("254", notificationmessage, false);
		   if(notificationflag == false)
			   Assert.fail("Robot Notification Message is NOT the same as expected while uploading the file");			 
			
	   	}
	   	catch(Exception e)
	   	{
			  logger.log(Level.SEVERE,"Exception  in testcase UploadFileWhichNotSupport in class FileExtensionSupport");
			  Assert.fail("Exception  in testcase UploadFileWhichNotSupport in class FileExtensionSupport",e);
			  
	   	}
	   
	}	

   @Test(priority=2,enabled=true)
   public void RoboNotificationGoneAfterFivesec()
   {
	   try
	   {
		   Driver.startDriver();
		   String filename = ReadTestData.readDataByTagName("FileExtensionSupport", "filename", "254");
		   new UserCreate().CreateStudent("aa", "");//create student
		   new DirectLogin().studentLogin("aa");
		   new Navigator().NavigateTo("Course Stream");
		   Driver.driver.findElement(By.linkText("File")).click();
		   Thread.sleep(5000);
		   ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("//*[@id='file-upload-button']")));
		   Thread.sleep(5000);
		   Robot robot = new Robot();
		   new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename);
		   Thread.sleep(1000);
		   robot.keyPress(KeyEvent.VK_ENTER);
		   robot.keyRelease(KeyEvent.VK_ENTER);
		   Thread.sleep(6000);
		  
			   int errmsg=Driver.driver.findElements(By.className("notification-message-body")).size();
			   if(errmsg>0)
			   {				  
				   Assert.fail("Robo Notification present even after 5 seconds of visiblity");					 
			   }
			  	 
	   }
	   catch(Exception e)
	   {
			  logger.log(Level.SEVERE,"Exception  in testcase RoboNotificationGoneafter5sec in class FileExtensionSupport");			
			  Assert.fail("Exception  in testcase RoboNotificationGoneafter5sec in class FileExtensionSupport",e);		  
	   }
   }
  

   @Test(priority=3,enabled=true)
   public void UploadSupportedFile()
   {
	   try
	   {
		   Driver.startDriver();
		   String notificationmessage = ReadTestData.readDataByTagName("FileExtensionSupport", "notificationmessage", "254");
		   new UserCreate().CreateStudent("a", "");//create student
		   new DirectLogin().studentLogin("a");
		   new Navigator().NavigateTo("Course Stream");
		   boolean notificationmessagestatus = new FileUpload().fileUploadNotificationMessageValidate("254", notificationmessage, false);
		   if(notificationmessagestatus == false)
			   Assert.fail("Robot Notification message is not as expected while uploading a non suported extention file");
		   Boolean uploadstatus =  new FileUpload().fileUploadAndValidate("256");
		   if(uploadstatus == false)
			   Assert.fail("File Not uploaded successfully");
		 		   
	   }
	   catch(Exception e)
	   {
			  logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper",e);			 
			  Assert.fail();			  
	   }	   	   
   }   
   
   @Test(priority=4,enabled=true)
   public void VerifyTitleWithDiffrentStudentOfSameClassSection()
   {
	   try
	   {
		   Driver.startDriver();
		   String filename = ReadTestData.readDataByTagName("FileExtensionSupport", "filename", "256");
		   new UserCreate().CreateStudent("a", "");//create student
		   new DirectLogin().studentLogin("a");
		   new Navigator().NavigateTo("Course Stream");
		   boolean fileuploaded = new FileUpload().fileUploadAndValidate("256");
		   if(fileuploaded == false) Assert.fail("File Upload Failed");
           new Logout().logout();
		   new UserCreate().CreateStudent("b", "");//create student
		   new DirectLogin().studentLogin("b");
		   new Navigator().NavigateTo("Course Stream");
		   boolean uploadvalidated = new FileUpload().validateFilePosted(filename);		
		   if(uploadvalidated == false) Assert.fail("File Upload by first user not found when logged in by the second user of the same class section");
			   
	   }
	   catch(Exception e)
	   {
			  Assert.fail("Exception  in testcase VerifyTitleWithDiffrentStudentOfSameClassSection in class FileExtensionSupport",e);
			  
	   }
	   
   }
   
	@Test(priority=5,enabled=true)
	public void UploadFileWithSpecialCharterOfname()
	{
		try
		{
			   Driver.startDriver();
			   new UserCreate().CreateStudent("a", "");//create student
			   new DirectLogin().studentLogin("a");
			   new Navigator().NavigateTo("Course Stream");
			   boolean uploadstatus = new FileUpload().fileUploadAndValidate("258");
			   if(uploadstatus == false)
				   Assert.fail("Uploading a file with a special charater in its name Failed");		   			   			   
		   }
		   catch(Exception e)
		   {
				  Assert.fail("Exception in testcase UploadFileWithSpecialCharterOfname in class FileExtensionSupport",e);				  
		   }	
	}	
	
	 

	@Test(priority=6,enabled=true)
	public void ErrorMshShownWhileProbleminUploading()
	{
		try
		   {
			   Driver.startDriver();			 
			   new UserCreate().CreateStudent("a", "");//create student
			   new DirectLogin().studentLogin("a");
			   new Navigator().NavigateTo("Course Stream");
			   boolean uploadstatus = new FileUpload().fileUploadAndValidate("254");
			   if(uploadstatus == true)
				   Assert.fail("Error Message Not Shown in case of problem in file upload");
		   }
		   catch(Exception e)
		   {
				  logger.log(Level.SEVERE,"Exception in ErrorMshShownWhileProbleminUploading Application Helper in class FileExtensionSupport",e);
				  Assert.fail();  
		   }
	}
	

	
	@Test(priority=7,enabled=true)
	public void ErrorMsgOffAfterThreesec()
	{
		try
		   {
			   Driver.startDriver();
			   String notificationmessage = ReadTestData.readDataByTagName("FileExtensionSupport", "notificationmessage", "2541");
			   new UserCreate().CreateStudent("a", "");//create student
			   new DirectLogin().studentLogin("a");
			   new Navigator().NavigateTo("Course Stream");
			   boolean notificationmessagestatus = new FileUpload().fileUploadNotificationMessageValidate("2541", notificationmessage, false);
			   if(notificationmessagestatus == false)
				   Assert.fail("Robot Notification message is not as expected while uploading a non suported extention file");
			   
		   }
		catch(Exception e)
		   {
				  logger.log(Level.SEVERE,"Exception in ErrorMsgOffAfter3sec Application Helper",e);				
				  Assert.fail("Exception in ErrorMsgOffAfter3sec Application Helper in class FileExtensionSupport",e);				  
		   }
	}
	@AfterMethod
	  public void tearDown() throws Exception 
	  {
	    Driver.driver.quit();
	  }
}
