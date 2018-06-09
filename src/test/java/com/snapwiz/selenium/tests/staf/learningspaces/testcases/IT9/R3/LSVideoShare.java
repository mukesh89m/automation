package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class LSVideoShare extends Driver{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.LSVideoShare");
		@Test
		public void videoShare()
		{
			try
			{

				 logger.log(Level.INFO,"Starting TestCase LSVideoShare");
				 new LoginUsingLTI().ltiLogin("287");
				 new Navigator().NavigateTo("Course Stream");
				 new FileUpload().fileUpload("287",true);
				 Thread.sleep(3000);
				 driver.navigate().refresh();
				 int videoThumbnailPresent = driver.findElements(By.cssSelector("div[class='ls-media__img media_file_link']")).size();
				 if(videoThumbnailPresent == 0)
					 Assert.fail("Video Thumbnail not present");
				 //Validating VideoThumbNail Size
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='ls-media__img media_file_link']")));
                WebElement thumbnail = driver.findElement(By.cssSelector("div[class='ls-media__img media_file_link']"));
				String styleAttribute = thumbnail.getAttribute("style");
				thumbnail.click();
				Thread.sleep(5000);				
				//Validating Full Video Width
				String styleVideoAttribute = driver.findElement(By.xpath("//*[starts-with(@id, 'video_')]")).getAttribute("style");
				if(!styleAttribute.contains("width: 100px; height: 60px") || !styleVideoAttribute.contains("width: 480px"))
					Assert.fail("Width and Height of video thumbnail or video is not as Expected");
				
				
				
			}
			catch(Exception e)
			{
				Assert.fail("Exception in TestCase videoShare in class LSVideoShare",e);
			}
			
			
		}

}
