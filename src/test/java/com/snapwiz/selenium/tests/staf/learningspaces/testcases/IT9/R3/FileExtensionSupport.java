package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class FileExtensionSupport extends Driver{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.FileExtensionSupport");
    /*
     * 254-260
     */	
	@Test(priority=1,enabled=true)
	public void UploadFileWhichNotSupport()//try to upload file which not support
	{
	   try
	   {
		   String notificationmessage = ReadTestData.readDataByTagName("FileExtensionSupport", "notificationmessage", "254");
		   new LoginUsingLTI().ltiLogin("254");
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
   public void RoboNotificationGoneafter5sec()
   {
	   try
	   {

		   String filename = ReadTestData.readDataByTagName("FileExtensionSupport", "filename", "254");
		   new LoginUsingLTI().ltiLogin("254");
		   new Navigator().NavigateTo("Course Stream");
		   driver.findElement(By.linkText("File")).click();
		   Thread.sleep(5000);
		   ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='file-upload-button']")));
		   Thread.sleep(5000);
		   Robot robot = new Robot();
		   new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+filename);
		   Thread.sleep(1000);
		   robot.keyPress(KeyEvent.VK_ENTER);
		   robot.keyRelease(KeyEvent.VK_ENTER);
		   Thread.sleep(6000);
		  
			   int errmsg=driver.findElements(By.className("notification-message-body")).size();
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

		   String notificationmessage = ReadTestData.readDataByTagName("FileExtensionSupport", "notificationmessage", "254");
		   new LoginUsingLTI().ltiLogin("254");
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

		   String filename = ReadTestData.readDataByTagName("FileExtensionSupport", "filename", "256");
		   new LoginUsingLTI().ltiLogin("256");
		   new Navigator().NavigateTo("Course Stream");
		   boolean fileuploaded = new FileUpload().fileUploadAndValidate("256");
		   if(fileuploaded == false) Assert.fail("File Upload Failed");
		   new LoginUsingLTI().ltiLogin("257");
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

			   new LoginUsingLTI().ltiLogin("258");
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

			   new LoginUsingLTI().ltiLogin("254");
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
	public void ErrorMsgOffAfter3sec()
	{
		try
		   {

			   String notificationmessage = ReadTestData.readDataByTagName("FileExtensionSupport", "notificationmessage", "2541");
			   new LoginUsingLTI().ltiLogin("2541");
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


	

}
