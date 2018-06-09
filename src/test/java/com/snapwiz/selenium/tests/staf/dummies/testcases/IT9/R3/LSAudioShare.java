package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.Config;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;

public class LSAudioShare {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.LSAudioShare");
	/*
	 * 295-302
	 */
	@Test
	public void audioFunctionality()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			Driver.driver.findElement(By.linkText("File")).click();
			Thread.sleep(3000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("file-upload-button")));
			Thread.sleep(3000);
			Robot robot = new Robot();
			new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+"img.mp3"); 
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(15000);
			Driver.driver.findElement(By.id("post-submit-button")).click();
			Thread.sleep(10000);
			WebElement audio=Driver.driver.findElement(By.className("media_file_link"));
			String str=audio.getText();
			if(str.contains("img.mp3"))
			{
				audio.click();
				Thread.sleep(7000);
				String widthvalue=Driver.driver.findElement(By.xpath("//*[starts-with(@id, 'audio_')]")).getAttribute("style").substring(7, 12);
				if(widthvalue.equals("500px"))
				{
					logger.log(Level.INFO,"width of audio player is 500px");
					Driver.driver.findElement(By.cssSelector("a[class='jp-pause']")).click();
					Thread.sleep(5000);
					String barvalue=Driver.driver.findElement(By.cssSelector("div[class='jp-play-bar']")).getAttribute("style").substring(7, 12);
					boolean returnbarvalue=barvalue.isEmpty();
					if(returnbarvalue==false)
					{
						logger.log(Level.INFO,"Audio is playing");
					}
					else
					{
						logger.log(Level.INFO,"Audio is  not playing");						
						Assert.fail("Audio Not Playing");
					}
				}
			}
			else
			{
				Assert.fail("Audio File not posted.");
			}
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception in LSAudioShare",e);
			Assert.fail("Exception in LSAudioShare",e);
		}
	}

	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
