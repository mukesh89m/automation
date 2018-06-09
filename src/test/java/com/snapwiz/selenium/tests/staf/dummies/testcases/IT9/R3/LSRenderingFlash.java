package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.snapwiz.selenium.tests.staf.dummies.ReadTestData;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;

public class LSRenderingFlash {
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.LSRenderingFlash");
	
	@Test(priority = 1,enabled = true)
	public void shareFlash()
	{
		try
		{
			Driver.startDriver();
			logger.log(Level.INFO,"Starting TestCase shareFlash under class LSRenderingFlash");
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			new FileUpload().fileUpload("313",true);				
			int flashcheck = Driver.driver.findElements(By.cssSelector("div[class='ls-media__body media_file_link']")).size();
			if(flashcheck == 0)
				Assert.fail("Flash file not uploaded");

			WebElement flash = Driver.driver.findElement(By.cssSelector("div[class='ls-media__body media_file_link']"));
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
	@Test(priority = 2,enabled = true)
	public void flashWidth()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("313",true);
            Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("i[class='ls-fileicon-img ls--swf-icon']")).click();
			WebElement flash = Driver.driver.findElement(By.className("flash-video"));
			String imgtag = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",flash);
			if(!imgtag.contains("width=\"100%\""))
                Assert.fail("width is not 100 %");

		}
		catch(Exception e)
		{				
			Assert.fail("Exception in TestCase flashWidth in class LSRenderingFlash",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception {
		Driver.driver.quit();
	}	
}
