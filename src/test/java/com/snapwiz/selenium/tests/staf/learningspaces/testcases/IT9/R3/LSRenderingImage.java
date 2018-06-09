package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

/*
 * 303 306 Rendering an Image
 */
public class LSRenderingImage extends Driver{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.LSRenderingImage");
	
	
	@Test(priority = 1,enabled=true)
	public void shareImageOfWidth599()
	{
		try
		{

			 logger.log(Level.INFO,"Starting TestCase shareImageOfWidth599 under class LSRenderingImage");
			 new LoginUsingLTI().ltiLogin("303");
			 new Navigator().NavigateTo("Course Stream");
			 new FileUpload().fileUpload("303",true);			
			 
			 WebElement image =  driver.findElement(By.cssSelector("div[class='ls-media__img media_file_link']"));
			
			 String imgtag = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",image);
			 System.out.println(imgtag);
			if(!imgtag.contains("width=\"150px\""))
				Assert.fail("Thumbnail width not equal to 150px");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase shareImageOfWidth599 under class  LSRenderingImage",e);
		}
		
	}
	
	@Test(priority = 2,enabled=true)
	public void clickOnImageThumbnail599()
	{
		try
		{
			logger.log(Level.INFO,"Starting TestCase clickOnImageThumbnail599 under class LSRenderingImage");
			new LoginUsingLTI().ltiLogin("303");
			
			new Navigator().NavigateTo("Course Stream");
			//driver.findElement(By.cssSelector("i[class='ls-icon-img ls--file-icon']")).click();
		    WebElement image =  driver.findElement(By.cssSelector("div[class='ls-media__img media_file_link']"));
			image.click();
			Thread.sleep(3000);
			String imgtag = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",image);
			if(!imgtag.contains("width: 599px"))
			{				
				Assert.fail("Width of Image after clicking the thumbnail is not equal to 599px");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();		
			Assert.fail("Exception in testcase clickOnImageThumbnail599 under class  LSRenderingImage",e);
		}
		
	}
	
	@Test(priority = 3,enabled=true)
	public void shareImageOfWidth600()
	{
		try
		{

			logger.log(Level.INFO,"Starting TestCase shareImageOfWidth600 under class LSRenderingImage");
			new LoginUsingLTI().ltiLogin("306");
			new Navigator().NavigateTo("Course Stream");
			new FileUpload().fileUpload("306",true);
			 WebElement image =  driver.findElement(By.cssSelector("div[class='ls-media__img media_file_link']"));
				
				 String imgtag = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",image);
				if(!imgtag.contains("width=\"150px\""))
					AssertJUnit.fail("Thumbnail width not equal to 150px");
			 
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase shareImageOfWidth600 under class  LSRenderingImage",e);
		}
	}
	@Test(priority = 4,enabled=true)
	public void clickOnImageThumbnail600()
	{
		try
		{

			logger.log(Level.INFO,"Starting TestCase clickOnImageThumbnail600 under class LSRenderingImage");
			new LoginUsingLTI().ltiLogin("306");
			new Navigator().NavigateTo("Course Stream");
			//driver.findElement(By.cssSelector("i[class='ls-icon-img ls--file-icon']")).click();
		    WebElement image =  driver.findElement(By.cssSelector("div[class='ls-media__img media_file_link']"));
			image.click();
			Thread.sleep(3000);
			String imgtag = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",image);
			if(!imgtag.contains("width: 600px"))
			{
				Assert.fail("Width of Image after clicking the thumbnail is not equal to 600px");
			}
		
		}
		catch(Exception e)
		{		
			Assert.fail("Exception in testcase clickOnImageThumbnail600 under class  LSRenderingImage",e);
		}
		
	}
	
	@Test(priority = 5,enabled=true)
	public void shareImageOfWidth601()
	{
		try
		{

			 logger.log(Level.INFO,"Starting TestCase shareImageOfWidth601 under class LSRenderingImage");
			 new LoginUsingLTI().ltiLogin("309");
			 new Navigator().NavigateTo("Course Stream");
			 new FileUpload().fileUpload("309",true);			
			 
			 WebElement image =  driver.findElement(By.cssSelector("div[class='ls-media__img media_file_link']"));
			
			 String imgtag = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",image);
			if(!imgtag.contains("width=\"150px\""))
				AssertJUnit.fail("Thumbnail width not equal to 150px");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase shareImageOfWidth601 in class LSRenderingImage",e);
		}
		
	}
	
	@Test(priority = 6,enabled=true)
	public void clickOnImageThumbnail601()
	{
		try
		{

			logger.log(Level.INFO,"Starting TestCase clickOnImageThumbnail599 under class LSRenderingImage");
			new LoginUsingLTI().ltiLogin("309");
			new Navigator().NavigateTo("Course Stream");
			//driver.findElement(By.cssSelector("i[class='ls-icon-img ls--file-icon']")).click();
		    WebElement image =  driver.findElement(By.cssSelector("div[class='ls-media__img media_file_link']"));
			image.click();
			Thread.sleep(3000);
			String imgtag = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",image);
			if(!imgtag.contains("width: 600px"))
			{
				Assert.fail("Width of Image after clicking the thumbnail is not equal to 600px");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in TestCase clickOnImageThumbnail601 in class LSRenderingImage",e);
		}
		
	} 

}
