package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;


public class UploadFileForDifferentUser extends Driver
{
	
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.UploadFileForDifferentUser");

@Test(enabled=true)
public void uploadFileForDifferentUser()
{
	try
	{

		String shareWithInitialString = ReadTestData.readDataByTagName("UploadFileForDifferentUser", "shareWithInitialString", "273");
		String studentnametag = ReadTestData.readDataByTagName("UploadFileForDifferentUser", "studentnametag", "273");
		String fileName = ReadTestData.readDataByTagName("UploadFileForDifferentUser", "fileName", "270");
		String fileName1 = ReadTestData.readDataByTagName("UploadFileForDifferentUser", "fileName1", "273");
		Robot robot = new Robot();
		new LoginUsingLTI().ltiLogin("270");
		
		new Navigator().NavigateTo("Course Stream");
		driver.findElement(By.linkText("File")).click();
		
		
		Thread.sleep(3000);
		if(Config.browser.equals("ie") || Config.browser.equals("chrome"))
			 driver.findElement(By.xpath("//*[@id='file-upload-button']")).click();
		else
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='file-upload-button']")));
		Thread.sleep(3000);
		new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+fileName);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
		driver.findElement(By.id("post-submit-button")).click();
		Thread.sleep(50000);
		String text = driver.findElement(By.className("ls-stream-post__action")).getText();
			
		String text1 = driver.findElement(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']")).getText();
			
			if(text.contains("shared a file"))
			{
				 logger.log(Level.INFO,"When a file is uploaded its posted with title 'Shared a file'");
			}
			else
			{
				Assert.fail("When a file is uploaded its NOT posted with title 'Shared a file'");
			}
			new LoginUsingLTI().ltiLogin("271");
			new Navigator().NavigateTo("Course Stream");
			int size = driver.findElements(By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']")).size();
			
			if(size != 0)
			{
				logger.log(Level.INFO,"Previously uploaded file by a different student of same classsection section is present");
			}
			else
			{
				Assert.fail("Previously uploaded file by a different student of same classsection section is absent");
			}
			
			new LoginUsingLTI().ltiLogin("272");
			new Navigator().NavigateTo("Course Stream");
			int size1 = driver.findElements(By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']")).size();
			
			if(size1 == 0)
			{
				logger.log(Level.INFO,"Previously uploaded file by a student of different classsection section is absent");
			}
			else
			{
				Assert.fail("Previously uploaded file by a student of different classsection section is present");
			}
			new LoginUsingLTI().ltiLogin("273_1");
			new LoginUsingLTI().ltiLogin("273");
			new Navigator().NavigateTo("Course Stream");
			driver.findElement(By.linkText("File")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("a.closebutton")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("maininput")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
			Thread.sleep(5000);
			List<WebElement> suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
			   for (WebElement answerchoice: suggestname)
			   { 
				  
			    if(answerchoice.getText().trim().equals(studentnametag))
			    {
			    	
			     answerchoice.click();
			     Thread.sleep(5000);
			     
			    }
			   }
			   if(Config.browser.equals("ie") || Config.browser.equals("chrome"))
					 driver.findElement(By.xpath("//*[@id='file-upload-button']")).click();
				else
					 ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='file-upload-button']")));
				Thread.sleep(5000);
				new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+fileName1);
				
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(5000);
				driver.findElement(By.id("post-submit-button")).click();
				Thread.sleep(50000);
				String text2 = driver.findElement(By.className("ls-stream-post__action")).getText();
				
				String text3 = driver.findElement(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']")).getText();
					
					if(text2.contains("shared a file"))
					{
						 logger.log(Level.INFO,"When a file is uploaded its posted with title 'Shared a file'");
					}
					else
					{
						Assert.fail("When a file is uploaded its NOT posted with title 'Shared a file'");
					}	
			new LoginUsingLTI().ltiLogin("273_1");
			new Navigator().NavigateTo("Course Stream");
			int size2= driver.findElements(By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']")).size();
			
			if(size2 > 0)
			{
				logger.log(Level.INFO,"The file that has been shared with this student is present");
			}
			else
			{
				Assert.fail("The file that has been shared with this student is present");
			}
			
			
	}
	catch(Exception e)
	{
		e.printStackTrace();
		Assert.fail("Exception TestCase uploadFileForDifferentUser in class UploadFileForDifferentUser",e);	
	}
	
}

}
