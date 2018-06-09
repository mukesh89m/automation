package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R24;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.FileUpload;

public class CancelFunctionalityForInstructor extends Driver {

	@Test(priority = 1, enabled = true)
	public void cancelFunctionalityForPost()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("145_1");
			new LoginUsingLTI().ltiLogin("145"); //login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "145_1");
			String studentnametag = ReadTestData.readDataByTagName("", "studentnametag", "145_1");
			String context_title = ReadTestData.readDataByTagName("", "context_title", "145_1");
			String randomtext = new RandomString().randomstring(20);
			new PostMessage().postMessageWithoutSubmit(randomtext);
			driver.findElement(By.className("maininput")).click();	//click on textbox
			Thread.sleep(2000);
			driver.findElement(By.className("closebutton")).click();  //click on closebutton
			Thread.sleep(2000);
			driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
			Thread.sleep(3000);
			List<WebElement> suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
			   for (WebElement answerchoice: suggestname)
			   { 
			    if(answerchoice.getText().trim().equals(studentnametag))
			    {				    	
			    	answerchoice.click();
			     Thread.sleep(2000);
			     break;
			    }
			   }				   
			driver.findElement(By.id("post-submit-cancel-button")).click();   //click on Cancel button
			Thread.sleep(3000);
			driver.findElement(By.linkText("Post")).click();	//click on Post
			Thread.sleep(2000);
			String defaulttext = driver.switchTo().frame("iframe-user-text-input-div").findElement(By.xpath("/html/body/font")).getText();
			driver.switchTo().defaultContent();
			if(!defaulttext.equals("Share your knowledge or seek answers..."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After we fill text and click on cancel then the Default Text 'Share your knowledge or seek answers' is NOT present for Post tab");
			}
			if(!driver.findElement(By.className("item-text")).getText().equals(context_title))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After we fill text and click on cancel then the Default class section name not present in the share-with text box");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase cancelFunctionalityForPost in class CancelFunctionalityForInstructor.", e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void cancelFunctionalityForLink()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("146_1");
			new LoginUsingLTI().ltiLogin("146"); //login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "146_1");
			String studentnametag = ReadTestData.readDataByTagName("", "studentnametag", "146_1");
			String context_title = ReadTestData.readDataByTagName("", "context_title", "146_1");
			new PostMessage().postLinkWithoutSubmit("www.yahoo.com");
			Thread.sleep(2000);
			driver.findElement(By.className("maininput")).click();	//click on textbox
			Thread.sleep(2000);
			driver.findElement(By.className("closebutton")).click();  //click on closebutton
			Thread.sleep(2000);
			driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
			Thread.sleep(3000);
			List<WebElement> suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
			   for (WebElement answerchoice: suggestname)
			   { 
			    if(answerchoice.getText().trim().equals(studentnametag))
			    {				    	
			     answerchoice.click();
			     Thread.sleep(2000);
			     break;
			    }
			   }				   
			  
			driver.findElement(By.id("post-submit-cancel-button")).click();   //click on Cancel button
			Thread.sleep(3000);
			driver.findElement(By.linkText("Link")).click();	//click on Link
			Thread.sleep(2000);
			String defaulttext = driver.switchTo().frame("iframe-user-link-input-div").findElement(By.xpath("/html/body/font")).getText();
			driver.switchTo().defaultContent();
			if(!defaulttext.equals("Share your link..."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After we fill link and click on cancel then the Default Text 'Share your link...' is NOT present for Link tab");
			}
			if(!driver.findElement(By.className("item-text")).getText().equals(context_title))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After we fill link and click on cancel then the Default class section name not present in the share-with text box");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase cancelFunctionalityForLink in class CancelFunctionalityForInstructor.", e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void cancelFunctionalityForFile()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("147_1");
			new LoginUsingLTI().ltiLogin("147"); //login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "147_1");
			String studentnametag = ReadTestData.readDataByTagName("", "studentnametag", "147_1");
			String context_title = ReadTestData.readDataByTagName("", "context_title", "147_1");
			new FileUpload().fileUpload("147",false);
			Thread.sleep(2000);
			new FileUpload().enterFileUploadText();  //add file description
			driver.findElement(By.className("maininput")).click();	//click on textbox
			Thread.sleep(2000);
			driver.findElement(By.className("closebutton")).click();  //click on closebutton
			Thread.sleep(2000);
			driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
			Thread.sleep(3000);
			List<WebElement> suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
			   for (WebElement answerchoice: suggestname)
			   { 
			    if(answerchoice.getText().trim().equals(studentnametag))
			    {				    	
			     answerchoice.click();
			     Thread.sleep(2000);
			     break;
			    }
			   }				   
			  
			driver.findElement(By.id("post-submit-cancel-button")).click();   //click on Cancel button
			Thread.sleep(3000);
			driver.findElement(By.linkText("File")).click();	//click on Link
			Thread.sleep(2000);
			int file = driver.findElements(By.cssSelector("img[id='file-thumbnail-img']")).size();
			if(file != 0)	
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Uploaded file does not get removed on clicking cancel button");
			}
			driver.findElement(By.linkText("File")).click();
			if(!driver.findElement(By.className("item-text")).getText().equals(context_title))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Default class section name not present in the share-with text box");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase cancelFunctionalityForFile in class CancelFunctionalityForInstructor.", e);
		}
	}

}
