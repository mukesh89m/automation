package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R24;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class SharingAFile extends Driver{

	@Test(priority = 1, enabled = true)
	public void sharingAFileWithClassSection()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("148_1"); //login as instructor
			new LoginUsingLTI().ltiLogin("150"); //login as instructor
			new LoginUsingLTI().ltiLogin("148"); //login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			new FileUpload().fileUpload("148", true);
			String title = new TextFetch().textfetchbyclass("ls-stream-post__head");
			//TC row no. 148
			if(!title.contains("shared a file"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Title \"shared a file\" for a file is not added in course stream when file share with the default class section.");
			}
			new LoginUsingLTI().ltiLogin("148_1"); //login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			String title1= new TextFetch().textfetchbyclass("ls-stream-post__head");
			//TC row no. 149
			if(!title1.contains("shared a file"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The file is not displayed for other instructors of the same class section.");
			}
			new LoginUsingLTI().ltiLogin("150"); //login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			//TC row no. 150
			int size = driver.findElements(By.className("ls-stream-post__head")).size();
			if(size != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The file is also displayed for other instructor of different class Section.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase sharingAFileWithClassSection in class SharingAFile.", e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void sharingAFileWithInstructor()
	{
		try
		{
			String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "151_1");
			new LoginUsingLTI().ltiLogin("151_1"); //login as instructor
			new LoginUsingLTI().ltiLogin("151_2"); //login as instructor
			new LoginUsingLTI().ltiLogin("151"); //login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			new FileUpload().fileUploadAndShare(shareWithInitialString, "studentnametag", "", "151_1");
			String title = new TextFetch().textfetchbyclass("ls-stream-post__head");
			//TC row no. 151
			if(!title.contains("shared a file"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Title \"shared a file\" for a file is not added in course stream when file share with an instructor.");
			}
			new LoginUsingLTI().ltiLogin("151_1"); //login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			String title1= new TextFetch().textfetchbyclass("ls-stream-post__head");
			//TC row no. 152
			if(!title1.contains("shared a file"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The file is not displayed for the instructors to whom the file has been shared.");
			}
			new LoginUsingLTI().ltiLogin("151_2"); //login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			//TC row no. 153
			int size = driver.findElements(By.className("ls-stream-post__head")).size();
			if(size != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The file is displayed for instructors to whom the file has not been shared.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase sharingAFileWithInstructor in class SharingAFile.", e);
		}
	}
	@Test(priority = 3, enabled = true)
	public void sharingAFileWithMentor()
	{
		try
		{
			String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "157_1");
			new LoginUsingLTI().ltiLogin("157_1"); //login as mentor
			new LoginUsingLTI().ltiLogin("157_2"); //login as mentor
			new LoginUsingLTI().ltiLogin("157"); //login as mentor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			new FileUpload().fileUploadAndShare(shareWithInitialString, "studentnametag", "", "157_1");
			String title = new TextFetch().textfetchbyclass("ls-stream-post__head");
			//TC row no. 157
			if(!title.contains("shared a file"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Title \"shared a file\" for a file is not added in course stream when file share with an mentor.");
			}
			new LoginUsingLTI().ltiLogin("157_1"); //login as mentor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			String title1= new TextFetch().textfetchbyclass("ls-stream-post__head");
			//TC row no. 158
			if(!title1.contains("shared a file"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The file is not displayed for the mentor to whom the file has been shared.");
			}
			new LoginUsingLTI().ltiLogin("157_2"); //login as mentor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			//TC row no. 159
			int size = driver.findElements(By.className("ls-stream-post__head")).size();
			if(size != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The file is displayed for mentor to whom the file has not been shared.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase sharingAFileWithMentor in class SharingAFile.", e);
		}
	}

}
