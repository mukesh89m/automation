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

public class BehaviourOfFlashFile extends Driver {
	
	@Test(priority = 1, enabled = true)
	public void behaviourOfFlashFileWithoutFlashPlugin()
	{
		try
		{
			 String notificationtext;
			 Driver.flashplugin = false;
			 String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "160_1");
			 new LoginUsingLTI().ltiLogin("160_1");
			 new LoginUsingLTI().ltiLogin("160");
			 new Navigator().NavigateTo("Course Stream");
			 new FileUpload().fileUploadAndShare(shareWithInitialString, "studentnametag", "", "160_1");
			 //TC row no. 161
			 String title = new TextFetch().textfetchbyclass("ls-stream-post__head");
			 if(!title.contains("shared a file"))
			 {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Shared a file post is not added in course stream.");
			 }
			 driver.findElement(By.cssSelector("i[class='ls-fileicon-img ls--swf-icon']")).click();
			 Thread.sleep(5000);
			 //TC row no. 163
			 int messagePresent = driver.findElements(By.className("flash-video")).size();
			 if(messagePresent == 0)
			 {
				 new Screenshot().captureScreenshotFromTestCase();
				 Assert.fail("Notification message for missing flash plugin not coming.");
			 }
			 else
			 {
				 notificationtext = driver.findElement(By.className("flash-video")).getText();
				 if(!notificationtext.equals("Please install or enable Adobe Flash Player to view this content."))
				 {
					 new Screenshot().captureScreenshotFromTestCase();
					 Assert.fail("Notification text not coming same as expected.");
				 }
			 }
			 new LoginUsingLTI().ltiLogin("160_1");
			 new Navigator().NavigateTo("Course Stream");
			 //TC row no. 162
			 String title1 = new TextFetch().textfetchbyclass("ls-stream-post__head");
			 if(!title1.contains("shared a file"))
			 {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Flash file is not visible to the instructor to whom it was shared.");
			 }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase behaviourOfFlashFileWithoutFlashPlugin in class BehaviourOfFlashFile.", e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void behaviourOfFlashFileWithFlashPlugin()
	{
		try
		{
			 Driver.flashplugin = true;
			 new LoginUsingLTI().ltiLogin("164");
			 new Navigator().NavigateTo("Course Stream");
			 new FileUpload().fileUpload("164",true);
			 driver.findElement(By.cssSelector("i[class='ls-fileicon-img ls--swf-icon']")).click();
			 Thread.sleep(5000);
			 //TC row no. 164
			 String flashvideo = driver.findElement(By.xpath("//*[starts-with(@id, 'flash_video_embed')]")).getText();
			 if(flashvideo.contains("Please install or enable"))
			 {
				 new Screenshot().captureScreenshotFromTestCase();
				 Assert.fail("Flash not getting played");		
			 }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase behaviourOfFlashFileWithFlashPlugin in class BehaviourOfFlashFile.",e);
		}
	}

}
