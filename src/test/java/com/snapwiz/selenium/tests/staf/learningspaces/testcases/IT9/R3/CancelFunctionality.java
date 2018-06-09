package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import java.util.List;
import org.openqa.selenium.By;
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
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.FileUpload;;

public class CancelFunctionality extends Driver
{	
@Test(priority=1, enabled=true)
public void cancelFunctionalityForPostTab()
	{
		try
			{

				new LoginUsingLTI().ltiLogin("266");
				new LoginUsingLTI().ltiLogin("267");
				new Navigator().NavigateTo("Course Stream");
				String defaulttextpresent = ReadTestData.readDataByTagName("CancelFunctionality", "defaulttext", "266");
				String shareWithInitialString = ReadTestData.readDataByTagName("CancelFunctionality", "shareWithInitialString", "267");
				String studentnametag = ReadTestData.readDataByTagName("CancelFunctionality", "studentnametag", "267");
				driver.findElement(By.linkText("Post")).click();
				driver.findElement(By.className("maininput")).click();
				Thread.sleep(2000);
				driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
				Thread.sleep(3000);
				List<WebElement> suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
				   for (WebElement answerchoice: suggestname)
				   { 					   
				    if(answerchoice.getText().trim().equals(studentnametag))
				    {				    	
				     answerchoice.click();
				     break;
				    }
				   }				   
				  
				driver.findElement(By.id("post-submit-cancel-button")).click();
				Thread.sleep(3000);
				String defaulttext = driver.switchTo().frame("iframe-user-text-input-div").findElement(By.xpath("/html/body/font")).getText();
				driver.switchTo().defaultContent();
				if(!defaulttext.equals(defaulttextpresent))
					Assert.fail("Default Text 'Share your knowledge or seek answers' is NOT present for Post tab");
				driver.findElement(By.linkText("Post")).click();
				if(!driver.findElement(By.className("item-text")).getText().equals("studtitle"))
					Assert.fail("Default class section name not present in the share-with text box");
			}
		catch(Exception e)
			{
				Assert.fail("Exception TestCase cancelFunctionalityForPostTab in class CancelFunctionality",e);	
			}
	
	}

@Test(priority=2, enabled=true)
public void cancelFunctionalityForLinkTab()
	{
		try
			{

				new LoginUsingLTI().ltiLogin("266");
				new LoginUsingLTI().ltiLogin("268");
				new Navigator().NavigateTo("Course Stream");
				String defaulttextpresent = ReadTestData.readDataByTagName("CancelFunctionality", "defaulttext", "268");
				String shareWithInitialString = ReadTestData.readDataByTagName("CancelFunctionality", "shareWithInitialString", "268");
				String studentnametag = ReadTestData.readDataByTagName("CancelFunctionality", "studentnametag", "268");
				driver.findElement(By.linkText("Link")).click();
				Thread.sleep(5000);
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
				     break;
				    }
				   }
				driver.findElement(By.id("post-submit-cancel-button")).click();
				Thread.sleep(3000);
				 String defaulttext = driver.switchTo().frame("iframe-user-link-input-div").findElement(By.xpath("/html/body/font")).getText();
					driver.switchTo().defaultContent();
					if(!defaulttext.equals(defaulttextpresent))
					Assert.fail("Default Text 'Share your link' is NOT present for Link tab");
				driver.findElement(By.linkText("Link")).click();
				if(!driver.findElement(By.className("item-text")).getText().equals("studtitle"))
					Assert.fail("Default class section name not present in the share-with text box");
				
				
			}
		catch(Exception e)
			{
				e.printStackTrace();
				Assert.fail("Exception TestCase cancelFunctionalityForLinkTab in class CancelFunctionality",e);	
			}
	
	}

@Test(priority=3, enabled=true)
public void cancelFunctionalityForFileTab()
	{
		try
			{

				new LoginUsingLTI().ltiLogin("266");
				new LoginUsingLTI().ltiLogin("269");
				new Navigator().NavigateTo("Course Stream");
				String shareWithInitialString = ReadTestData.readDataByTagName("CancelFunctionality", "shareWithInitialString", "269");
				String studentnametag = ReadTestData.readDataByTagName("CancelFunctionality", "studentnametag", "269");
				driver.findElement(By.linkText("File")).click();
				Thread.sleep(5000);
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
				     break;
				    }
				   }
  
					new FileUpload().fileUpload("269", false);
					
                    new FileUpload().enterFileUploadText(); //entering file upload text

					driver.findElement(By.id("post-submit-cancel-button")).click();
					Thread.sleep(3000);
					int file = driver.findElements(By.cssSelector("img[id='file-thumbnail-img']")).size();
					
					if(file != 0)					
						Assert.fail("Uploaded file does not get removed on clicking cancel button");
					driver.findElement(By.linkText("File")).click();
					if(!driver.findElement(By.className("item-text")).getText().equals("studtitle"))
						Assert.fail("Default class section name not present in the share-with text box");
					
			}
		catch(Exception e)
			{
				e.printStackTrace();
				Assert.fail("Exception TestCase cancelFunctionalityForFileTab in class CancelFunctionality",e);	
			}
	
	}



}
