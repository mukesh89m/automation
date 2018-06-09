package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R24;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;

public class RenderingVideoFile extends Driver {
	
	@Test(priority = 1, enabled = true)
	public void renderingVideoFile()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("165");
			new Navigator().NavigateTo("Course Stream");
			new FileUpload().fileUpload("165",true);
			//TC row no. 165
			int videoThumbnailPresent = driver.findElements(By.cssSelector("div[class='ls-media__img media_file_link']")).size();
			if(videoThumbnailPresent == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Video Thumbnail not present");
			}
			//TC row no. 166
			 //Validating VideoThumbNail Size
			WebElement thumbnail = driver.findElement(By.cssSelector("div[class='ls-media__img media_file_link']"));
			String styleAttribute = thumbnail.getAttribute("style");
			if(!styleAttribute.contains("width: 100px; height: 60px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Width and Height of video thumbnail or video is not as Expected");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase renderingVideoFile in class RenderingVideoFile", e);
		}
	}

}
