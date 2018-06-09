package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
/*
 * 282 Behaviour of flash when browser is not having flash plugin
 */
public class LSFlashBehaviour extends Driver{

	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.LSFlashBehaviourWithoutFlashPlugin");
	@Test(priority=1,enabled=true)
	public void flashBehaviourWithoutFlashPlugin()
	{
		try
		{
			 String notificationtext;
			 Driver.flashplugin = false;

			 logger.log(Level.INFO,"Starting TestCase LSFlashBehaviourWithoutFlashPlugin");
			 new LoginUsingLTI().ltiLogin("282");
			 new Navigator().NavigateTo("Course Stream");
			 new FileUpload().fileUpload("282",true);
			 driver.findElement(By.cssSelector("i[class='ls-fileicon-img ls--swf-icon']")).click();
			 Thread.sleep(5000);
			 int messagePresent = driver.findElements(By.className("flash-video")).size();
			 if(messagePresent == 0)
				 Assert.fail("Notification message for missing flash plugin not coming.");
			 else
			 {
				 notificationtext = driver.findElement(By.className("flash-video")).getText();
				 if(!notificationtext.equals("Please install or enable Adobe Flash Player to view this content."))
					 Assert.fail("Notification text not coming same as expected.");
			 }
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase flashBehaviourWithoutFlashPlugin in class LSFlashBehaviour",e);
		}
	}
	@Test(priority=2,enabled=true,dependsOnMethods={"flashBehaviourWithoutFlashPlugin"})
	public void LSFlashBehaviourWithFlashPlugin()
	{
		try
		{
			Driver.flashplugin = true;

			 logger.log(Level.INFO,"Starting TestCase LSFlashBehaviourWithoutFlashPlugin");
			 new LoginUsingLTI().ltiLogin("282");
			 new Navigator().NavigateTo("Course Stream");
			 driver.findElement(By.cssSelector("i[class='ls-fileicon-img ls--swf-icon']")).click();
			 Thread.sleep(5000);
			 String flashvideo = driver.findElement(By.xpath("//*[starts-with(@id, 'flash_video_embed')]")).getText();
			 if(flashvideo.contains("Please install or enable"))
				 Assert.fail("Flash not getting played");			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase LSFlashBehaviourWithFlashPlugin in class LSFlashBehaviour",e);
		}
	}


}
