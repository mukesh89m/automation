package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;

public class LSVideoShare {
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.LSVideoShare");
	@Test
	public void videoShare()
	{
		try
		{
			Driver.startDriver();
			logger.log(Level.INFO,"Starting TestCase LSVideoShare");
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			new FileUpload().fileUpload("287",true);
			Thread.sleep(5000);
			Driver.driver.navigate().refresh();
			int videoThumbnailPresent = Driver.driver.findElements(By.cssSelector("div[class='ls-media__img media_file_link']")).size();
			if(videoThumbnailPresent == 0)
				Assert.fail("Video Thumbnail not present");
			//Validating VideoThumbNail Size
			WebElement thumbnail = Driver.driver.findElement(By.cssSelector("div[class='ls-media__img media_file_link']"));
			String styleAttribute = thumbnail.getAttribute("style");
			thumbnail.click();
			Thread.sleep(5000);				
			//Validating Full Video Width
			String styleVideoAttribute = Driver.driver.findElement(By.xpath("//*[starts-with(@id, 'video_')]")).getAttribute("style");
			if(!styleAttribute.contains("width: 100px; height: 60px") || !styleVideoAttribute.contains("width: 480px"))
				Assert.fail("Width and Height of video thumbnail or video is not as Expected");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase videoShare in class LSVideoShare",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception {
		Driver.driver.quit();  
	}
}
