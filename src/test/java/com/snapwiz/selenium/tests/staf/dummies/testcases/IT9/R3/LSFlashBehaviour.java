package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;
/*
 * 282 Behaviour of flash when browser is not having flash plugin
 */
public class LSFlashBehaviour {

	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.LSFlashBehaviourWithoutFlashPlugin");

	@Test(priority = 1,enabled = true)
	public void flashBehaviourWithoutFlashPlugin()
	{
		try
		{
			String notificationtext;
			Driver.flashplugin = false;
			Driver.startDriver();
			logger.log(Level.INFO,"Starting TestCase LSFlashBehaviourWithoutFlashPlugin");
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			new FileUpload().fileUpload("282",true);
			Driver.driver.findElement(By.cssSelector("i[class='ls-fileicon-img ls--swf-icon']")).click();
			Thread.sleep(5000);
			int messagePresent = Driver.driver.findElements(By.className("flash-video")).size();
			if(messagePresent == 0)
				Assert.fail("Notification message for missing flash plugin not coming.");
			else
			{
				notificationtext = Driver.driver.findElement(By.className("flash-video")).getText();
				if(!notificationtext.equals("Please install or enable Adobe Flash Player to view this content."))
					Assert.fail("Notification text not coming same as expected.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase flashBehaviourWithoutFlashPlugin in class LSFlashBehaviour",e);
		}
	}

	@Test(priority = 2,enabled = true)
	public void LSFlashBehaviourWithFlashPlugin()
	{
		try
		{
			Driver.flashplugin = true;
			Driver.startDriver();
			logger.log(Level.INFO,"Starting TestCase LSFlashBehaviourWithoutFlashPlugin");
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("282",true);
			Driver.driver.findElement(By.cssSelector("i[class='ls-fileicon-img ls--swf-icon']")).click();
			Thread.sleep(5000);
			String flashvideo = Driver.driver.findElement(By.xpath("//*[starts-with(@id, 'flash_video_embed')]")).getText();
			if(flashvideo.contains("Please install or enable"))
				Assert.fail("Flash not getting played");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase LSFlashBehaviourWithFlashPlugin in class LSFlashBehaviour",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception {
		Driver.driver.quit();
	}
}
