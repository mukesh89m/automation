package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;

/*
 * 303 306 Rendering an Image
 */
public class LSRenderingImage {
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.LSRenderingImage");
	
	
	@Test(priority = 1,enabled=true)
	public void shareImageOfWidthfiveninenine()
	{
		try
		{
			 Driver.startDriver();
			 logger.log(Level.INFO,"Starting TestCase shareImageOfWidth599 under class LSRenderingImage");
			  new UserCreate().CreateStudent("a", "");//create student
			 new DirectLogin().studentLogin("a");
			 new Navigator().NavigateTo("Course Stream");
			 new FileUpload().fileUpload("303",true);			
			 
			 WebElement image =  Driver.driver.findElement(By.cssSelector("div[class='ls-media__img media_file_link']"));
			
			 String imgtag = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",image);
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
	public void clickOnImageThumbnailfiveninenine()
	{
		try
		{	Driver.startDriver();		
			logger.log(Level.INFO,"Starting TestCase clickOnImageThumbnail599 under class LSRenderingImage");
			 new UserCreate().CreateStudent("a", "");//create student
			 new DirectLogin().studentLogin("a");
			
			new Navigator().NavigateTo("Course Stream");
		    WebElement image =  Driver.driver.findElement(By.cssSelector("div[class='ls-media__img media_file_link']"));			
			image.click();
			Thread.sleep(3000);
			String imgtag = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",image);
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
	public void shareImageOfWidthSixHunderead()
	{
		try
		{
			Driver.startDriver();
			logger.log(Level.INFO,"Starting TestCase shareImageOfWidth600 under class LSRenderingImage");
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			new FileUpload().fileUpload("306",true);
			WebElement image =  Driver.driver.findElement(By.cssSelector("div[class='ls-media__img media_file_link']"));				
		    String imgtag = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",image);
			if(!imgtag.contains("width=\"150px\""))
				Assert.fail("Thumbnail width not equal to 150px");
			 
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase shareImageOfWidth600 under class  LSRenderingImage",e);
		}
	}
	@Test(priority = 4,enabled=true)
	public void clickOnImageThumbnailSixHundread()
	{
		try
		{
			Driver.startDriver();
			logger.log(Level.INFO,"Starting TestCase clickOnImageThumbnail600 under class LSRenderingImage");
		    new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
		    WebElement image =  Driver.driver.findElement(By.cssSelector("div[class='ls-media__img media_file_link']"));			
			image.click();
			Thread.sleep(3000);
			String imgtag = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",image);
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
	public void shareImageOfWidthSixHundreadOne()
	{
		try
		{
			 Driver.startDriver();
			 logger.log(Level.INFO,"Starting TestCase shareImageOfWidth601 under class LSRenderingImage");
			 new UserCreate().CreateStudent("a", "");//create student
			 new DirectLogin().studentLogin("a");
			 new Navigator().NavigateTo("Course Stream");
			 new FileUpload().fileUpload("309",true);						 
			 WebElement image =  Driver.driver.findElement(By.cssSelector("div[class='ls-media__img media_file_link']"));			
			 String imgtag = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",image);
			if(!imgtag.contains("width=\"150px\""))
				AssertJUnit.fail("Thumbnail width not equal to 150px");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase shareImageOfWidth601 in class LSRenderingImage",e);
		}
		
	}
	
	@Test(priority = 6,enabled=true)
	public void clickOnImageThumbnailSizHundreadOne()
	{
		try
		{
			Driver.startDriver();
			logger.log(Level.INFO,"Starting TestCase clickOnImageThumbnail599 under class LSRenderingImage");
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
		    WebElement image =  Driver.driver.findElement(By.cssSelector("div[class='ls-media__img media_file_link']"));			
			image.click();
			Thread.sleep(3000);
			String imgtag = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",image);
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
	@AfterMethod
	public void tearDown() throws Exception 
	{
	    Driver.driver.quit();
	 }
}
