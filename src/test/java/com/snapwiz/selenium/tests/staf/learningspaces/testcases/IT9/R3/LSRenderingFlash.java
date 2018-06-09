package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class LSRenderingFlash extends Driver {
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.LSRenderingFlash");
		@Test(priority=1,enabled=true)
		public void shareFlash()
		{
			try
			{

				 logger.log(Level.INFO,"Starting TestCase shareFlash under class LSRenderingFlash");
				 new LoginUsingLTI().ltiLogin("313");
				 new Navigator().NavigateTo("Course Stream");
				 new FileUpload().fileUpload("313",true);				
				 int flashcheck = driver.findElements(By.cssSelector("div[class='ls-media__body media_file_link']")).size();
				 if(flashcheck != 1)				 
					 Assert.fail("Flash file not uploaded");
				 
				 WebElement flash = driver.findElement(By.cssSelector("div[class='ls-media__body media_file_link']"));
				 String flashLinkName = flash.getText();				 
				 String filename = ReadTestData.readDataByTagName("LSRenderingFlash", "filename", "313");
				if(!flashLinkName.equals(filename))
					Assert.fail("The value of the flash file name link does not match with the name of uploaded file");
				 
			}
			catch(Exception e)
			{
				Assert.fail("Exception in TestCase shareFlash in class LSRenderingFlash",e);
			}			
		}
		@Test(priority=2,enabled=false)
		public void flashWidth()
		{
			try
			{

				new LoginUsingLTI().ltiLogin("313");
				new Navigator().NavigateTo("Course Stream");
				driver.findElement(By.cssSelector("i[class='ls-fileicon-img ls--swf-icon']")).click();
				WebElement flash = driver.findElement(By.xpath("//*[starts-with(@id, 'flash_video_embed_')]"));
				  String imgtag = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",flash);
				    System.out.println(imgtag);
				   
			}
			catch(Exception e)
			{				
				Assert.fail("Exception in TestCase flashWidth in class LSRenderingFlash",e);
			}
		}

}
