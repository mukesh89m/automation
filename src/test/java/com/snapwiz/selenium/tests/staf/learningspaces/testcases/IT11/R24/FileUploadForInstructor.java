package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R24;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class FileUploadForInstructor extends Driver {
	
	@Test(priority = 1, enabled = true)
	public void fileUploadForInstructor()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("127"); //login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			driver.findElement(By.linkText("File")).click(); //click on File
			Thread.sleep(2000);
			driver.findElement(By.id("post-submit-button")).click();	//click on Submit button
			Thread.sleep(2000);
			//TC row no. 127
			int notice = driver.findElements(By.className("notification-message-wrapper")).size();
			if(notice != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Leave the textbox of post blank and click on Submit then error message is displayed.");
			}
			new FileUpload().fileUpload("127",false);
			Thread.sleep(3000);
			//TC row no. 129
			int progressbar= driver.findElements(By.id("fileupload-progress-bar")).size();
			if(progressbar != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();	
				Assert.fail("While uploading file the Progress Bar is not present.");
			}
			//TC row no. 130 & 133
			String defaulttext = driver.switchTo().frame("iframe-user-file-input-div").findElement(By.xpath("/html/body/font")).getText();
			driver.switchTo().defaultContent();
			if(!defaulttext.equals("Provide a description for this file"))
			{
				new Screenshot().captureScreenshotFromTestCase();	
				Assert.fail("Default Text 'Provide a description for this file' is NOT Present  within the text-box.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase fileUploadForInstructor in class FileUploadForInstructor.", e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void sharedAFile()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("137_1"); //login as instructor
			new LoginUsingLTI().ltiLogin("137"); //login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			new FileUpload().fileUpload("137", false);
			new FileUpload().enterFileUploadText();  //add file description
			driver.findElement(By.id("post-submit-button")).click();
			Thread.sleep(3000);
			String title = new TextFetch().textfetchbyclass("ls-stream-post__head");
			//TC row no. 137
			if(!title.contains("shared a file"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Title \"shared a file\" for a file is not added in course stream when file uploaded with a description.");
			}
			new LoginUsingLTI().ltiLogin("137_1");  	//login as mentor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			String title1= new TextFetch().textfetchbyclass("ls-stream-post__head");
			//TC row no. 138
			if(!title1.contains("shared a file"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The file is not displayed for other instructors of the same class section.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase sharedAFile in class FileUploadForInstructor.", e);
		}
	}
	@Test(priority = 3, enabled = true)
	public void sharedAFileWithoutDesc()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("139"); //login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			new FileUpload().fileUpload("139", true);
			String title = new TextFetch().textfetchbyclass("ls-stream-post__head");
			//TC row no. 139
			if(!title.contains("shared a file"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Title \"shared a file\" for a file is not added in course stream when file uploaded without a description.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase sharedAFileWithoutDesc in class FileUploadForInstructor.", e);
		}
	}
	@Test(priority = 4, enabled = true)
	public void fileSizeLessThan25MB()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("142"); //login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			boolean found = new FileUpload().fileUploadAndValidate("142");	
			if(found == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Unable to upload a file of size less than 25MB");
			}					
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase fileSizeLessThan25MB in class FileUploadForInstructor.", e);
		}
	}
	@Test(priority = 5, enabled = true)
	public void fileSizeMoreThan25MB()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("144"); //login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			boolean found = new FileUpload().fileUploadAndValidate("144");	
			if(found == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("we are able to upload a file of size more than 25MB.");
			}					
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase fileSizeMoreThan25MB in class FileUploadForInstructor.", e);
		}
	}

}
