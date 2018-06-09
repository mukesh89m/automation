package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class LSAudioShare extends Driver{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.LSAudioShare");
	/*
	 * 295-302
	 */
    @Test
    public void audioFunctionality()
    {
    	try
    	{

    		new LoginUsingLTI().ltiLogin("295");
    		new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("295", true);
			WebElement audio=driver.findElement(By.className("media_file_link"));
			String str=audio.getText();
            System.out.println("str: "+str);
            if(str.contains("img.mp3"))
			{
				
				audio.click();
                new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[starts-with(@id, 'audio_')]")));
			    String widthvalue=driver.findElement(By.xpath("//*[starts-with(@id, 'audio_')]")).getAttribute("style").substring(7, 12);
			    if(widthvalue.equals("500px"))
			    {
			    	logger.log(Level.INFO,"width of audio player is 500px");
			    	driver.findElement(By.cssSelector("a[class='jp-pause']")).click(); //click on pause
			    	Thread.sleep(6000);
			    	WebElement barvalue=driver.findElement(By.cssSelector("div[class='jp-play-bar']"));//.getAttribute("style");//.substring(7, 1
                    boolean returnbarvalue=barvalue.isDisplayed();
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
    



}
