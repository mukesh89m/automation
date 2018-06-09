package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class RecentPostOnTop extends Driver{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.RecentPostOnTop");

	/*
	 *59-61
	 */
	@Test
	public void recentPostOnTop()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("59");
			new Navigator().NavigateTo("Course Stream");
			String texttopost1=new RandomString().randomstring(10);
			new PostMessage().postmessage(texttopost1);
            String texttopost=new RandomString().randomstring(10);
            new PostMessage().postmessage(texttopost);
			if(!driver.findElement(By.className("ls-stream-share__title")).getText().equals(texttopost))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The recently added posts did not appear at the top.");
			}
            new PostMessage().postlink("http://www.gmail.com");
            new PostMessage().postlink("http://www.yahoo.com");
            Thread.sleep(5000);
            List<WebElement> allSharedElements = driver.findElements(By.className("ls-stream-share__title"));
            if(!(allSharedElements.get(0).getText().contains("http://www.yahoo.com")))
                Assert.fail("Recently posted link not appearing on top");

            new FileUpload().fileUpload("59",true);
            new FileUpload().fileUpload("59_newfile",true);
            if((driver.findElement(By.cssSelector("div[class='ls-media__body media_file_link']")).getText()).equals("img.png"))
                Assert.fail("Recently posted File not appearing on top");

		}
		catch (Exception e)
		 {
			  new Screenshot().captureScreenshotFromTestCase();
			  logger.log(Level.SEVERE,"Exception in DirectLogin Application Helper");	
			  Assert.fail("Exception  in testcase recentPostOnTop in class RecentPostOnTop",e);
		  }
	}

}
